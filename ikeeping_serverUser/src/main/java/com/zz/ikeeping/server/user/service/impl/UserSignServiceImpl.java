package com.zz.ikeeping.server.user.service.impl;


import com.zz.ikeeping.common.config.ProjectConfig;
import com.zz.ikeeping.common.jwt.JwtUtil;
import com.zz.ikeeping.common.model.LoginToken;
import com.zz.ikeeping.common.util.JedisUtil;
import com.zz.ikeeping.common.util.TimeUtil;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Points;
import com.zz.ikeeping.entity.Sign;
import com.zz.ikeeping.server.user.dao.PointsMapper;
import com.zz.ikeeping.server.user.dao.UserPointsMapper;
import com.zz.ikeeping.server.user.dao.UserSignDao;
import com.zz.ikeeping.server.user.service.UserSignService;
import com.zz.ikeeping.server.user.util.RandomUtil;
import com.zz.ikeeping.server.user.util.UserTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
public class UserSignServiceImpl implements UserSignService{
    @Autowired
    private UserSignDao signDao;
    @Autowired
    private UserPointsMapper userPointsMapper;
    @Autowired
    private PointsMapper pointsMapper;
    @Autowired
    private JedisUtil jedisUtil;
    @Override
    @Transactional
    public R saveSign(String token) {
        //1、今日是否已经签过 ---今日签过的人 存储到Redis-当天有效 Set集合  uid/phone
        LoginToken loginToken= UserTokenUtil.parseToken(JwtUtil.parseJWT(token));
        boolean isSign=true;
        boolean isFirst=false;
        //1、验证存储签到的集合是否存在
        if(jedisUtil.exists(ProjectConfig.SIGNKEY)){
            //2、验证当前的登录用户是否签到
            if(jedisUtil.sismember(ProjectConfig.SIGNKEY,loginToken.getPhone())){
                isSign=false;
                return R.setERROR("亲，今天已经签到了，请明天再来！");
            }
        }else {
            isFirst=true;
        }
        if(isSign){
            //3、今天可以签到
            Sign lastSign=signDao.selectByUidLast(loginToken.getUid());
            int extra=0;
            String info="";
            int lxdays=1;
            int score= RandomUtil.createNum(3,5);
            //4、之前是否签过道
            if(lastSign==null){
                //5、这是第一次签到
                extra=ProjectConfig.NEWSCORE;
                info="第一次签到：基础积分："+score+",额外赠送积分："+extra;
            }else {
                //断签、连续签到
                //6、验证是否连续签到
                if(TimeUtil.getDistanceDays(lastSign.getCreatetime())==1){
                    //连续
                    lxdays=lastSign.getDays()+1;
                    //7、校验连续签到的天数
                    if(lxdays%365==0){
                        //连续签到1年 或者2年
                        extra=lxdays;
                        info="恭喜你，连续签到："+lxdays+"天，特别奖励:"+lxdays+"积分";
                    }else if(lxdays%30==0){
                        extra=50;
                        info="恭喜你连续签到："+lxdays+"天，特别奖励:50积分";
                    }else if(lxdays%5==0){
                        extra=10;
                        info="恭喜你连续签到："+lxdays+"天，特别奖励:10积分";
                    }else if (lxdays%3==0){
                        extra=score;
                        info="恭喜你连续签到："+lxdays+"天，特别奖励:"+lxdays+"积分";
                    }
                }else {
                    //8、断签 重新签到
                    info="你上次是在："+TimeUtil.getFormat(lastSign.getCreatetime())+"签到";
                }
            }
            //9、保存数据到数据库
            //签到数据
            Sign userSign=new Sign();
            userSign.setUid(loginToken.getUid());
            userSign.setDays(lxdays);
            userSign.setExtrascore(extra);
            userSign.setScore(score);
            signDao.insert(userSign);
            //积分变动表
            Points points=new Points();
            points.setScore(score+extra);
            points.setUid(loginToken.getUid());
            points.setStartdate(new Date());
            points.setEnddate(TimeUtil.getDays(ProjectConfig.NEWSEXPIRE));
            pointsMapper.insert(points);
            //更新用户积分
            userPointsMapper.update(loginToken.getUid(),score+extra);

            //10、保存签到信息到Redis
            jedisUtil.sadd(ProjectConfig.SIGNKEY,loginToken.getPhone());
            //11、如果是今天的第一次签到 ，需要设置签到数据的key的有效期 当天剩余的秒数
            if(isFirst){
                jedisUtil.expire(ProjectConfig.SIGNKEY,TimeUtil.getLastSeconds());
            }
            return R.setOK("签到成功",userSign);
        }
        return null;
    }

    @Override
    public R queryByDays(String token, int days) {
        int uid=UserTokenUtil.parseTokenId(jedisUtil.get(ProjectConfig.TOKENJWT+token));
        return R.setOK("OK",signDao.selectByUidDays(uid,days));
    }

    @Override
    public R queryByUid(String token) {
        return R.setOK("OK",signDao.selectByUid(UserTokenUtil.parseTokenId(jedisUtil.get(ProjectConfig.TOKENJWT+token))));
    }

    @Override
    public R querySingle(String token) {
        return R.setOK("OK",signDao.selectByUidLast(UserTokenUtil.parseTokenId(jedisUtil.get(ProjectConfig.TOKENJWT+token))));
    }
}
