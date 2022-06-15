/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author SE150853 Nguyen Huynh Minh Khoi
 */
public class SendEmail {

    public String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public boolean sendEmail(User user, String code) {
        boolean test = false;

        String toEmail = user.getEmail();
        String fromEmail = "namphse150924@fpt.edu.vn";
        String password = "13073105";

        try {

            // your host email smtp server details
            Properties pr = new Properties();
            pr.put("mail.smtp.host", "smtp.gmail.com");
            pr.put("mail.smtp.port", "465");
            pr.put("mail.smtp.auth", "true");
            pr.put("mail.smtp.starttls.enable", "true");
            pr.put("mail.smtp.starttls.required", "true");
            pr.put("mail.smtp.ssl.protocols", "TLSv1.2");
            pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            //get session to authenticate the host email address and password
            Session session = Session.getInstance(pr, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            //set email message details
            Message mess = new MimeMessage(session);

            //set from email address
            mess.setFrom(new InternetAddress(fromEmail));
            //set to email address or destination email address
            mess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

            //set email subject
            mess.setSubject("Confirm Register");

            String message = "<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n "
                    + "\n"
                    + "<head>\n "
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + "    <h3 style=\"color: blue;\">Xin chào " + user.getFullName() + " !</h3>\n"
                    + "    <div>Mã xác minh tài khoản của bạn là : " + code + "</div>\n"
                    + "    <div>Thư này được tạo ra tự động.</div>\n"
                    + "    <div>Nếu bạn cần trợ giúp hoặc có câu hỏi, hãy gửi email đến minhkhoi11.04nhmk@gmail.com bất cứ lúc nào.</div>\n"
                    + "    <h3 style=\"color: blue;\">Trân trọng!</h3>\n"
                    + "\n"
                    + "</body>\n"
                    + "\n"
                    + "</html>";

            mess.setContent(message, "text/html; charset=UTF-8");

            //set message text
//            mess.setText(message);
//            //send the message
            Transport.send(mess);

            test = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }

    public boolean sendEmailDelete(User user, String time, String childrenPitchName, String pitchName, String date, String reason) {
        boolean test = false;

        String toEmail = user.getEmail();
        String fromEmail = "namphse150924@fpt.edu.vn";
        String password = "13073105";

        try {

            // your host email smtp server details
            Properties pr = new Properties();
            pr.put("mail.smtp.host", "smtp.gmail.com");
            pr.put("mail.smtp.port", "465");
            pr.put("mail.smtp.auth", "true");
            pr.put("mail.smtp.starttls.enable", "true");
            pr.put("mail.smtp.starttls.required", "true");
            pr.put("mail.smtp.ssl.protocols", "TLSv1.2");
            pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            //get session to authenticate the host email address and password
            Session session = Session.getInstance(pr, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            //set email message details
            Message mess = new MimeMessage(session);

            //set from email address
            mess.setFrom(new InternetAddress(fromEmail));
            //set to email address or destination email address
            mess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

            //set email subject
            mess.setSubject("Delete Booking");

            String message = "<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n "
                    + "\n"
                    + "<head>\n "
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + "    <h3 style=\"color: blue;\">Xin chào " + user.getFullName() + " !</h3>\n"
                    + "    <div>Chủ sân đã xóa lịch đặt sân của bạn vào lúc : " + time + " , ngày " + date +"</div>\n"
                    + "    <div>Tại sân : " + pitchName + "</div>\n"
                    + "    <div>Có sân con : " + childrenPitchName + "</div>\n"
                    + "    <div>Lý do : " + reason + "</div>\n"
                    + "    <div>Thư này được tạo ra tự động.</div>\n"
                    + "    <div>Nếu bạn cần trợ giúp hoặc có câu hỏi, hãy gửi email đến minhkhoi11.04nhmk@gmail.com bất cứ lúc nào.</div>\n"
                    + "    <h3 style=\"color: blue;\">Trân trọng!</h3>\n"
                    + "\n"
                    + "</body>\n"
                    + "\n"
                    + "</html>";

            mess.setContent(message, "text/html; charset=UTF-8");

            //set message text
//            mess.setText(message);
//            //send the message
            Transport.send(mess);

            test = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }
    
    public boolean sendEmailConfirmOwner(User user) {
        boolean test = false;

        String toEmail = user.getEmail();
        String fromEmail = "namphse150924@fpt.edu.vn";
        String password = "13073105";

        try {

            // your host email smtp server details
            Properties pr = new Properties();
            pr.put("mail.smtp.host", "smtp.gmail.com");
            pr.put("mail.smtp.port", "465");
            pr.put("mail.smtp.auth", "true");
            pr.put("mail.smtp.starttls.enable", "true");
            pr.put("mail.smtp.starttls.required", "true");
            pr.put("mail.smtp.ssl.protocols", "TLSv1.2");
            pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            //get session to authenticate the host email address and password
            Session session = Session.getInstance(pr, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            //set email message details
            Message mess = new MimeMessage(session);

            //set from email address
            mess.setFrom(new InternetAddress(fromEmail));
            //set to email address or destination email address
            mess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

            //set email subject
            mess.setSubject("Delete Booking");

            String message = "<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n "
                    + "\n"
                    + "<head>\n "
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + "    <h3 style=\"color: blue;\">Xin chào " + user.getFullName() + " !</h3>\n"
                    + "    <div>Chủ sân đã xác nhận cho bạn trở thành chủ sân</div>\n"
                    + "    <div>Thư này được tạo ra tự động.</div>\n"
                    + "    <div>Nếu bạn cần trợ giúp hoặc có câu hỏi, hãy gửi email đến minhkhoi11.04nhmk@gmail.com bất cứ lúc nào.</div>\n"
                    + "    <h3 style=\"color: blue;\">Trân trọng!</h3>\n"
                    + "\n"
                    + "</body>\n"
                    + "\n"
                    + "</html>";

            mess.setContent(message, "text/html; charset=UTF-8");

            //set message text
//            mess.setText(message);
//            //send the message
            Transport.send(mess);

            test = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }
}
