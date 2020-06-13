package com.bishop.utils;

import com.sun.deploy.security.CertStore;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {
	private static final Properties pro = new Properties();


	static {
		InputStream in = MailUtils.class.getClassLoader().getResourceAsStream("mail.properties");
		try {
			pro.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		// 1.创建一个程序与邮件服务器会话对象 Session

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol",pro.get("mail.protocol").toString());
		props.setProperty("mail.host", pro.get("mail.host").toString());
		props.setProperty("mail.smtp.auth", "true");// 指定验证为true
		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(pro.get("mail.username").toString(), pro.get("mail.password").toString());
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress(pro.get("mail.address").toString())); // 设置发送者

		message.setRecipient(RecipientType.TO, new InternetAddress(email)); // 设置发送方式与接收者

		message.setSubject("用户激活");
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

		message.setContent(emailMsg, "text/html;charset=utf-8");

		// 3.创建 Transport用于将邮件发送

		Transport.send(message);
	}

}
