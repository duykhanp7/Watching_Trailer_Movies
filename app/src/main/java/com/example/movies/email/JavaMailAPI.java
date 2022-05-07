package com.example.movies.email;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.movies.utils.Utils;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class JavaMailAPI {

    //Variables
    @SuppressLint("StaticFieldLeak")
    private Context mContext;
    private Session mSession;

    private String mEmail;
    private String mSubject;
    private String mMessage;
    private String filePath;
    private ExecutorService executorService;

    //Constructor
    public JavaMailAPI(Context mContext, String mEmail, String mSubject, String mMessage,String filePath) {
        this.mContext = mContext;
        this.mEmail = mEmail;
        this.mSubject = mSubject;
        this.mMessage = mMessage;
        this.filePath = filePath;
    }

    public void sendEmail(){
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //Creating properties
                
                Properties props = new Properties();

                //Configuring properties for gmail
                //If you are not using gmail you may need to change the values
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");

                //Creating a new session
                mSession = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                            //Authenticating the password
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(Utils.EMAIL, Utils.PASSWORD);
                            }
                        });

                try {
                    //Creating MimeMessage object
                    MimeMessage mm = new MimeMessage(mSession);

                    //Setting sender address
                    mm.setFrom(new InternetAddress(Utils.EMAIL));
                    //Adding receiver
                    mm.addRecipient(Message.RecipientType.TO, new InternetAddress(mEmail));
                    //Adding subject
                    mm.setSubject(mSubject);
                    //Adding message
                    mm.setText(mMessage);
                    //Sending email

                    Multipart multipart = new MimeMultipart();

                    MimeBodyPart messageBodyPart = new MimeBodyPart();
                    messageBodyPart.setText("File Điểm");

                    multipart.addBodyPart(messageBodyPart);

                    MimeBodyPart messageBodyPartAddFiles = new MimeBodyPart();
                    try {
                        messageBodyPartAddFiles.attachFile("C:\\Users\\duykhan\\Desktop\\text.txt");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    multipart.addBodyPart(messageBodyPartAddFiles);

                    mm.setContent(multipart);

                    Transport.send(mm);

                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}