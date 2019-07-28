package com.github.joine.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 邮件发送工具类
 *
 * @Author: JenphyJohn
 * @Date: 2019/6/7 12:43 PM
 */
@Component
public class MailSupport {

    private static JavaMailSender javaMailSender;

    public static JavaMailSender getJavaMailSender() {
        return javaMailSender;
    }

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        MailSupport.javaMailSender = javaMailSender;
    }

    private static final Logger logger = LoggerFactory.getLogger(MailSupport.class);

    /**
     * 发送一个简单格式的邮件
     *
     * @param mailBean
     */
    public static void sendSimpleMail(MailBean mailBean) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //邮件发送人
            simpleMailMessage.setFrom(mailBean.getSender());
            //邮件接收人
            simpleMailMessage.setTo(mailBean.getRecipient());
            //邮件主题
            simpleMailMessage.setSubject(mailBean.getSubject());
            //邮件内容
            simpleMailMessage.setText(mailBean.getContent());
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败", e);
        }
    }

    /**
     * 发送一个HTML格式的邮件
     *
     * @param mailBean
     */
    public static void sendHTMLMail(MailBean mailBean) {
        try {
            MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = createHelper(mimeMailMessage, mailBean);
            StringBuilder sb = new StringBuilder();
            sb.append("<h1>SpirngBoot测试邮件HTML</h1>")
                    .append("\"<p style='color:#F00'>你是真的太棒了！</p>")
                    .append("<p style='text-align:right'>右对齐</p>");
            mimeMessageHelper.setText(sb.toString(), true);
            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }
    }

    /**
     * 发送带附件格式的邮件
     *
     * @param mailBean
     */
    public static void sendAttachmentMail(MailBean mailBean) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(mailBean.getSender());
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            mimeMessageHelper.setText(mailBean.getContent());
            //文件路径
            FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/mail.png"));
            mimeMessageHelper.addAttachment("mail.png", file);

            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }
    }

    /**
     * 发送带静态资源的邮件
     *
     * @param mailBean
     */
    public static void sendInlineMail(MailBean mailBean) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(mailBean.getSender());
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            mimeMessageHelper.setText("<html><body>带静态资源的邮件内容，这个一张IDEA配置的照片:<img src='cid:picture' /></body></html>", true);
            //文件路径
            FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/mail.png"));
            mimeMessageHelper.addInline("picture", file);

            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }
    }

//    /**
//     * 发送基于Freemarker模板的邮件
//     *
//     * @param mailBean
//     */
//    public static void sendTemplateMail(MailBean mailBean) {
//        MimeMessage mimeMailMessage = null;
//        try {
//            mimeMailMessage = javaMailSender.createMimeMessage();
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
//            mimeMessageHelper.setFrom(mailBean.getSender());
//            mimeMessageHelper.setTo(mailBean.getRecipient());
//            mimeMessageHelper.setSubject(mailBean.getSubject());
//
//            Map<String, Object> model = new HashMap<String, Object>();
//            model.put("content", mailBean.getContent());
//            model.put("title", "标题Mail中使用了FreeMarker");
//            Template template = configuration.getTemplate("mail.ftl");
//            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
//            mimeMessageHelper.setText(text, true);
//
//            javaMailSender.send(mimeMailMessage);
//        } catch (Exception e) {
//            logger.error("邮件发送失败", e.getMessage());
//        }
//
//    }

    private static MimeMessageHelper createHelper(MimeMessage mimeMailMessage, MailBean mailBean) throws MessagingException {
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setFrom(mailBean.getSender());
        mimeMessageHelper.setTo(mailBean.getRecipient());
        mimeMessageHelper.setSubject(mailBean.getSubject());
        return mimeMessageHelper;
    }

    public static class MailBean {
        /**
         * 邮件发送人
         */
        private String sender;
        /**
         * 邮件接收人
         */
        private String recipient;
        /**
         * 邮件主题
         */
        private String subject;
        /**
         * 邮件内容
         */
        private String content;

        public String getSender() {
            return sender;
        }

        public MailBean setSender(String sender) {
            this.sender = sender;
            return this;
        }

        public String getRecipient() {
            return recipient;
        }

        public MailBean setRecipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public String getSubject() {
            return subject;
        }

        public MailBean setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public String getContent() {
            return content;
        }

        public MailBean setContent(String content) {
            this.content = content;
            return this;
        }
    }
}
