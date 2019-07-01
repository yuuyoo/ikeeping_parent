package com.zz.ikeeping.common.util;


import com.zz.ikeeping.common.config.ProjectConfig;
import com.zz.ikeeping.common.model.EmailMsg;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;


/*
 * 基于JDK实现邮件发送
 * */
public class EmailUtil {
	public static void sendEmail(EmailMsg msg){
		//邮箱
		String myAccount = ProjectConfig.EMAIL;
		//授权码-不是密码 邮箱规定，第三方平台登录邮箱必须实现授权码
		String myPass = ProjectConfig.EMAILPASS;
		//邮箱服务器
		String SMTPHost = ProjectConfig.EMAILHOST;
		//设置属性信息
		Properties prop = new Properties();
		//设置协议
		prop.setProperty("mail.transport.protocol", "smtp");
		//邮件服务器
		prop.setProperty("mail.smtp.host", SMTPHost);
		//认证
		prop.setProperty("mail.smtp.auth", "true");
		//1、创建会话
		Session session = Session.getDefaultInstance(prop);
		//设置是否需要调试
		session.setDebug(false);
		//2、创建发送信息
		MimeMessage message = createMsg(session,myAccount,msg);
		//4发送信息操作
		try {
			Transport tran = session.getTransport();
			//连接
			tran.connect(myAccount, myPass);
			//发送消息 
			tran.sendMessage(message, message.getAllRecipients());
			//关闭
			tran.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//生成邮件消息
	private static MimeMessage createMsg(Session session, String myAccount, EmailMsg msg) {
		//创建消息对象
		MimeMessage message = new MimeMessage(session);
		//设置
		try {
			//3.1发送方
			message.setFrom(new InternetAddress(myAccount, msg.getTitle(), "utf-8"));
			//3.2设置接收方
			/*
			 * MimeMessage.RecipientType.TO 
			 * MimeMessage.RecipientType.CC 
			 * MimeMessage.RecipientType.BCC 
			 * */
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(msg.getEmail(), msg.getCompany(), "utf-8"));
			//3.3 设置主题
			message.setSubject(msg.getTitle(),"utf-8");
			//设置正文信息
			message.setContent(msg.getContent(),"text/html;charset=utf-8");
			//设置日期
			message.setSentDate(new Date());
			//保存
			message.saveChanges();
		} catch (Exception e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
}
