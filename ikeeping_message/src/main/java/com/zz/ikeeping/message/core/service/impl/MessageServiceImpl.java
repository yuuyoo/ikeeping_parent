package com.zz.ikeeping.message.core.service.impl;



import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zz.ikeeping.common.config.ProjectConfig;
import com.zz.ikeeping.common.model.ActiveCode;
import com.zz.ikeeping.common.model.EmailMsg;
import com.zz.ikeeping.common.util.*;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.message.core.dao.MessageLogMapper;
import com.zz.ikeeping.message.core.dao.MessageMapper;
import com.zz.ikeeping.message.core.dao.MsgReceiveMapper;
import com.zz.ikeeping.message.core.entity.Message;
import com.zz.ikeeping.message.core.entity.MessageLog;
import com.zz.ikeeping.message.core.entity.MsgReceive;
import com.zz.ikeeping.message.core.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *@Author feri
 *@Date Created in 2019/6/13 10:48
 */
@Service
// @Service
public class MessageServiceImpl implements MessageService {
   /* @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private MessageLogMapper logMapper;
    @Autowired
    private MsgReceiveMapper receiveMapper;*/

    @Autowired
    private JedisUtil jedisUtil;



    @Override
    @Transactional
    public R sendMsg(Message message, String ip) {
        int count=0;
        if(message.getType()<4){
            //短信的话 需要校验 一个手机号一分钟只能发1次
            //一个手机号一天只能发20条
            String c=jedisUtil.get(ProjectConfig.SMSPREDAY+message.getReceive());
            if(c!=null && c.matches("[0-9]{1,2}")){
                count=Integer.parseInt(c);
                if(count>=20){
                    return R.setERROR("亲，一个手机号一天只能发送指定数量的短信");
                }
            }
            //一个手机号一分钟只能发送1次
            if(jedisUtil.exists(ProjectConfig.SMSPRELIMIT+message.getReceive())){
                //存在说明1分钟只能发送过短信
                return R.setERROR("一个手机号一分钟只能发送1次短信");
            }
        }
        //检查是否可以发送
        int code= CodeUtil.createNum(6);
        int flag=3;
        String info="";
        boolean isflag=false;
        try{
            //发送信息
            switch (message.getType()){
                case 1://短信验证码
                    if(jedisUtil.exists(ProjectConfig.SMSCODE+message.getReceive())){
                        //上次的验证码还没失效
                        code=Integer.parseInt(jedisUtil.get(ProjectConfig.SMSCODE+message.getReceive()));
                    }else {
                        code = CodeUtil.createNum(6);
                        isflag=true;
                    }
                    info="发送短信验证码："+code;
                    //验证码 三分钟有效
                    SmsUtil.mobileQuery(message.getReceive(),code);
                    flag=1;//发送成功
                    //1分钟
                    jedisUtil.setex(ProjectConfig.SMSPRELIMIT+message.getReceive(),60,"短信发送限制");
                    //1天
                    jedisUtil.setex(ProjectConfig.SMSPREDAY+message.getReceive(), TimeUtil.getLastSeconds(),(count+1)+"");
                    if(isflag){
                        //记录验证码
                        jedisUtil.setex(ProjectConfig.SMSCODE+message.getReceive(),180,code+"");
                    }
                    break;
                case 4://邮箱激活码
                    info="发送邮箱激活码："+code;
                    EmailMsg emailMsg=new EmailMsg();
                    emailMsg.setCompany(ProjectConfig.projects.get(message.getPcode()));
                    ActiveCode code1=new ActiveCode();
                    code1.setEmail(message.getReceive());
                    code1.setCode(code);
                    String mw= EncryptionUtil.AESEnc(ProjectConfig.AESKEYACTIVECODE, JSON.toJSONString(code1));
                    String u=ProjectConfig.ACTIVEURL+"?data="+mw+"&email="+EncryptionUtil.AESEnc(ProjectConfig.AESKEYACTIVECODE,"sasasassas");
                    emailMsg.setContent("欢迎注册："+emailMsg.getCompany()+",请激活使用，<a href=''></a>");
                    EmailUtil.sendEmail(emailMsg);
                    flag=1;
                    break;
            }
        }catch (Exception e){
            flag=2;
        }finally {
            //记录消息发送信息
            // messageMapper.insert(message);
            //记录日志
            MessageLog log=new MessageLog();
            log.setMid(message.getId());
            log.setInfo(info);
            log.setIp(ip);
            // logMapper.insert(log);
            //记录收件人
            MsgReceive receive=new MsgReceive();
            receive.setNo(message.getReceive());
            receive.setFlag(message.getType()<4?1:2);
            // receiveMapper.insert(receive);

        }
        //记录到数据库
        return null;
    }

    //发送短信验证码
    private R sendSms(Message message,String ip){
        int count=0;
        //短信的话 需要校验 一个手机号一分钟只能发1次
        //一个手机号一天只能发20条
        //获取当前的手机号今天的发送次数
        String c=jedisUtil.get(ProjectConfig.SMSPREDAY+message.getReceive());
        if(c!=null && c.matches("[0-9]{1,2}")){
            count=Integer.parseInt(c);
            if(count>=20){
                return R.setERROR("亲，一个手机号一天只能发送指定数量的短信");
            }
        }
        //一个手机号一分钟只能发送1次
        if(jedisUtil.exists(ProjectConfig.SMSPRELIMIT+message.getReceive())){
            //存在说明1分钟只能发送过短信
            return R.setERROR("一个手机号一分钟只能发送1次短信");
        }
        int code=0;
        int flag=3;
        String info="";
        boolean isflag=false;
        if(jedisUtil.exists(ProjectConfig.SMSCODE+message.getReceive())){
            //上次的验证码还没失效
            code=Integer.parseInt(jedisUtil.get(ProjectConfig.SMSCODE+message.getReceive()));
        }else {
            code = CodeUtil.createNum(6);
            isflag=true;
        }
        info="发送短信验证码："+code;
        //验证码 三分钟有效
        SmsUtil.mobileQuery(message.getReceive(),code);
        flag=1;//发送成功
        //1分钟
        String s1=jedisUtil.setex(ProjectConfig.SMSPRELIMIT+message.getReceive(),60,"短信发送限制");
        //1天
        String s2=jedisUtil.setex(ProjectConfig.SMSPREDAY+message.getReceive(),TimeUtil.getLastSeconds(),(count+1)+"");

        if(isflag){
            //记录验证码
            String s3=jedisUtil.setex(ProjectConfig.SMSCODE+message.getReceive(),600,code+"");

        }
        // save(message,info,ip);
        return R.setOK("OK",null);
    }
    //新增数据库记录
    private void save(Message message,String info,String ip){
        //记录消息发送信息
      //   messageMapper.insert(message);
        //记录日志
        MessageLog log=new MessageLog();
        log.setMid(message.getId());
        log.setInfo(info);
        log.setIp(ip);
        //logMapper.insert(log);
        //记录收件人
        MsgReceive receive=new MsgReceive();
        receive.setNo(message.getReceive());
        receive.setFlag(message.getType()<4?1:2);
        // receiveMapper.insert(receive);
    }
    //发送邮箱
    private R sendEmail(Message message,String ip){
        String info;
        int code=CodeUtil.createNum(6);
        int flag=2;
        info="发送邮箱激活码："+code;
        EmailMsg emailMsg=new EmailMsg();
        emailMsg.setCompany(ProjectConfig.projects.get(message.getPcode()));
        ActiveCode code1=new ActiveCode();
        code1.setEmail(message.getReceive());
        code1.setCode(code);
        String mw= EncryptionUtil.AESEnc(ProjectConfig.AESKEYACTIVECODE,JSON.toJSONString(code1));
        String u=ProjectConfig.ACTIVEURL+"?data="+mw+"&email="+EncryptionUtil.AESEnc(ProjectConfig.AESKEYACTIVECODE,"sasasassas");
        emailMsg.setContent("欢迎注册："+emailMsg.getCompany()+",请激活使用，<a href=''></a>");
        EmailUtil.sendEmail(emailMsg);
        flag=1;
        save(message,info,ip);
        return R.setOK("OK",null);
    }
    /*@Override
    public R page(int page, int count) {
        PageHelper.startPage(page, count);
        PageInfo<Message> pageInfo=new PageInfo<>(messageMapper.selectAll());
        return R.setOK("OK",pageInfo);
    }
*/
    @Override
    public R checkCode(String phone, int code) {
        String key=ProjectConfig.SMSCODE+phone;
        if(jedisUtil.exists(key)){
            String v=jedisUtil.get(key);
            if(v!=null){
                if(Integer.parseInt(v)==code){
                    return R.setOK("验证码正确",null);
                }else {
                    return R.setERROR("验证码输入错误");
                }
            }
        }
        return R.setERROR("亲，验证码过期，请重新获取验证码");
    }

    @Override
    @Transactional
    public R sendMessage(Message message, String ip) {
        if(message.getType()<4){
            //短信
           return sendSms(message, ip);
        }else {
            //邮箱
            return sendEmail(message,ip);
        }
    }
}
