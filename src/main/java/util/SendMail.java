package util;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	// 발신자 , 수신자, 제목, 내용 이렇게 매개변수를 받아서 보낼 거야~!
	// 매개변수 : 발신자 , 수신자, 제목, 내용
	
	public static void sendMail(String from, String to, String subject, String content) {
		try {
			//1. 발신자(메일서버) 정보 설정(Property에다)
			Properties prop = System.getProperties();
			prop.put("mail.smtp.host", "smtp.naver.com");
			prop.put("mail.smtp.port", "465");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.ssl.enable","true");
			prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
			
			//2. Session 객체 생성 : http세션이 아니라 라이브러리에 다운 받은 클래스 이름 
			Session session = Session.getDefaultInstance(prop, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("soso0678", "gdgd01!!"); //네이버 아이디/비번
					
				}
			});
			session.setDebug(true);
			
			//3. MimeMessage 객체 생성(발신자, 수신자, 제목 내용 설정)
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(from)); //발신자
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to)); //수신자
			mimeMessage.setSubject(subject); //제목
			mimeMessage.setText(content); //내용
			
			//4. 메일 전송
			Transport.send(mimeMessage); //전송됨
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		sendMail("soso0678@naver.com","eunssol01@gmail.com","제목","내용");
	      String tempPwd ="";
	      for (int i=0; i<3; i++) {
	         tempPwd += (char)((Math.random()*26)+65);
	      }
	      for (int i=0; i<3; i++) {
	         tempPwd += (int)((Math.random()*9));
	      }
	      System.out.println(tempPwd);

	
	
	}
	
}
