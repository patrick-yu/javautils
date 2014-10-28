package org.patrickyu.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.patrickyu.util.vo.MailContent;
import org.patrickyu.util.vo.MailRecipients;
import org.patrickyu.util.vo.MailSender;
import org.patrickyu.util.vo.MailServer;

public class MailUtils {

	public static void send(final MailServer server, MailSender sender, MailRecipients recipients, MailContent content) {
		Properties props = new Properties();
		props.put("mail.smtp.host", server.getHost());
		props.put("mail.smtp.port", server.getPort());

		Session session = Session.getDefaultInstance(props);
		if (server.isAuth()) {
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					// TODO Auto-generated method stub
					return new PasswordAuthentication(server.getUsername(), server.getPassword());
				}
			});
		}

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender.getEmail(), sender.getName()));
			Address[] to = recipients.getRecipients();
			Address[] cc = recipients.getCarbonCopies();
			Address[] bcc = recipients.getBlindCarbonCopies();
			if ( to.length > 0 )
				message.setRecipients(Message.RecipientType.TO,to);
			if ( cc.length > 0 )
				message.setRecipients(Message.RecipientType.CC, cc);
			if ( bcc.length > 0 )
				message.setRecipients(Message.RecipientType.BCC, bcc);
			message.setSubject(content.getTitle());
			if ( content.isHtml() )
				message.setContent(content.getContent(), "text/html; charset=utf-8");
			else
				message.setText(content.getContent());

			Transport.send(message);

		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		MailServer server = new MailServer();
		server.setAuth(true);
		server.setHost("smtp.gmail.com");
		server.setPassword("djsmgkfn9");
		server.setPort(587);
		server.setUsername("stargatex@gmail.com");

		MailSender sender = new MailSender("stargatex@gmail.com", "유영민");
		MailRecipients recipients = new MailRecipients();
		recipients.addRecipient("stargatex@naver.com", "별문지기");
		recipients.addBlindCarbonCopy("stargatex@nate.com", "유영민");

		MailContent content = new MailContent();
		content.setContent("<html><body><h1>Hello</h1><br>유<b>영민</b></body></html>");
		content.setHtml(true);
		content.setTitle("시험중입니다.");

		MailUtils.send(server, sender, recipients, content);
	}
}
