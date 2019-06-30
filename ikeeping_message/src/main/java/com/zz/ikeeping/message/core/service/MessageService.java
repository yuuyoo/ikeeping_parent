package com.zz.ikeeping.message.core.service;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.message.core.entity.Message;

/**
 *@Author feri
 *@Date Created in 2019/6/13 10:47
 */
public interface MessageService {
    R sendMsg(Message message, String ip);
    /*R page(int page, int count);
     */
    R checkCode(String phone, int code);
    R sendMessage(Message message, String ip);

}
