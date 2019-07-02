package com.zz.ikeeping.service.login.service.impl;

import com.zz.ikeeping.common.jwt.JwtUtil;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.service.login.dao.CartDao;
import com.zz.ikeeping.service.login.model.LoginToken;
import com.zz.ikeeping.service.login.service.CartVoService;
import com.zz.ikeeping.service.login.utils.UserTokenUtil;
import com.zz.ikeeping.service.login.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class CartVoServiceImpl implements CartVoService {
/**
 * @author : nick
 * @date : 2019-06-30 21:32
 **/
    @Autowired
    private CartDao cartDao;


    @Override
    public R all() {
//        LoginToken loginToken= UserTokenUtil.parseToken(JwtUtil.parseJWT(token));
//        int id = loginToken.getUid();
        List<CartVo> cartVos = cartDao.selectAll();
        return R.setOK("OK",cartVos);
    }

    @Override
    public R delete(@RequestParam(value="id",required=true) List<Integer> id) {
        int i = cartDao.deleteById(id);
        if (i ==0 ){
            return R.setERROR("删除失败");
        }
        return R.setOK("删除成功");
    }
}
