package cn.webro.util;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 邮件发送
 * Titlte: EmailSend
 * Company: net
 * @author min
 * @date 2018年3月16日
 */
public class EmailUtil {

	/**
	 *  设置服务器
	 */
    private static final String KEY_SMTP = "mail.smtp.host";
    /**
	 *  设置服务器
	 */
    private static String VALUE_SMTP;
    /**
     *  服务器验证
     */
    private static final String KEY_AUTH = "mail.smtp.auth";
    /**
     *  服务器验证
     */
    private static String VALUE_AUTH;
    /**
     *  发件人
     */
    private static String SEND_USER;
    /**
     * 用户名
     */
    private static String SEND_UNAME;
    /**
     * 密码
     */
    private static String SEND_PWD;
    /**
     * 加载资源文件
     */
    private ResourceBundle bundle = ResourceBundle.getBundle("custom/email");;
    // 建立会话
    private MimeMessage message;
    private Session session;

    
	//激活邮件标题
	public static final String JIHUOHEADNAME = "山东农业大学水肥一体化云平台-邮箱激活";
	//激活邮件内容
	public static final String JIHUOSENDHTML = "<div style=\"background:#f7f7f7;overflow:hidden\">"
			+ "<div style=\"background:#fff;border:1px solid #ccc;margin:2%;padding:0 30px\">"
			+ "<p style=\"margin:0;padding:0;font-size:14px;line-height:30px;color:#333;font-family:arial,sans-serif;font-weight:bold\">亲爱的用户：</p>"+
			"<p>您好！您正在进行邮箱激活，点击下方激活链接进行激活：</p>"+
			"<p>"+
				" <a href=\"http://FUWUQIDIZHI/CPO/mail.html?id=IDCODE&email=EMAILCODE\" title=\"点击激活\" target=\"_blank\" >点击此链接进行激活</a>"+
			"</p>"+
			"<p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:&#39;宋体&#39;,arial,sans-serif\">山东农业大学水平肥一体化云平台</p>"+
			"<p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:&#39;宋体&#39;,arial,sans-serif\">CURRDATE</p>"
			+ "</div></div>";
	
	//验证码邮件标题
	public static final String YANZHENGMAHEADNAME = "山东农业大学水肥一体化云平台-验证码";
	//验证码邮件内容
	public static final String YANZHENGMASENDHTML = "<div style=\"background:#f7f7f7;overflow:hidden\">"
			+ "<div style=\"background:#fff;border:1px solid #ccc;margin:2%;padding:0 30px\">"
			+ "<p style=\"margin:0;padding:0;font-size:14px;line-height:30px;color:#333;font-family:arial,sans-serif;font-weight:bold\">亲爱的用户：</p>"+
			"<p>您好！您的验证码为:：</p>"+
			"<p>"+
			"<b style=\"font-size:18px;color:#f90\">CODE</b>"+
			"</p>"+
			"<p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:&#39;宋体&#39;,arial,sans-serif\">山东农业大学水平肥一体化云平台</p>"+
			"<p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:&#39;宋体&#39;,arial,sans-serif\">CURRDATE</p>"
			+ "</div></div>";
		
	
	
	 /**
     * 初始化方法
     */
    public EmailUtil() {
    	
    	//bundle = ResourceBundle.getBundle("custom/email");
    	VALUE_SMTP = bundle.getString("mail.smtp.host");
    	VALUE_AUTH = bundle.getString("mail.smtp.auth");
    	SEND_USER = bundle.getString("mail.from");
    	SEND_UNAME = bundle.getString("mail.username");
    	SEND_PWD = bundle.getString("mail.password");
    	
        Properties props = System.getProperties();
        props.setProperty(KEY_SMTP, VALUE_SMTP);
        props.put(KEY_AUTH, VALUE_AUTH);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.port", "465");
        
        
        //props.put("mail.smtp.auth", "true");
        session =  Session.getInstance(props, new Authenticator(){
              protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(SEND_UNAME, SEND_PWD);
              }});
        session.setDebug(false);
        message = new MimeMessage(session);
    }
	
	/**
	 * 当前日期
	 * @return
	 */
	public static String currDate() {
		
		return new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
		
	}
    
    /**
     * 发送激活邮件
     * @param toEmail
     *            收件人地址
     */
    public boolean sendjihuoEmail(String id,String toEmail) {
        try {
            // 发件人
            InternetAddress from = new InternetAddress(SEND_USER);
            message.setFrom(from);
            // 收件人
            InternetAddress to = new InternetAddress(toEmail);
            message.setRecipient(Message.RecipientType.TO, to);
            // 邮件标题
            message.setSubject(JIHUOHEADNAME);
            message.setSentDate(new Date()); 
//            String content = sendHtml.toString();
            // 邮件内容,也可以使纯文本"text/plain"--网页"text/html"
           
            String content = JIHUOSENDHTML.replace("IDCODE",id).replace("EMAILCODE", toEmail).replace("CURRDATE", currDate()).replace("FUWUQIDIZHI",  bundle.getString("mail.fuwuqi"));
            message.setContent(content, "text/html;charset=utf-8");
            message.saveChanges();
            Transport transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(VALUE_SMTP, SEND_UNAME, SEND_PWD);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
//            System.out.println("send success!");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
    
    
    /**
     * 发送验证码邮件
     * @param toEmail
     *            收件人地址
     */
    public boolean sendyanzhengmaEmail(int yanzhengma,String toEmail) {
        try {
            // 发件人
            InternetAddress from = new InternetAddress(SEND_USER);
            message.setFrom(from);
            // 收件人
            InternetAddress to = new InternetAddress(toEmail);
            message.setRecipient(Message.RecipientType.TO, to);
            // 邮件标题YANZHENGMAHEADNAME
            message.setSubject(YANZHENGMAHEADNAME);
            message.setSentDate(new Date()); 
//            String content = sendHtml.toString();
            // 邮件内容,也可以使纯文本"text/plain"--网页"text/html"
           
            String content = YANZHENGMASENDHTML.replace("CODE",String.valueOf(yanzhengma)).replace("CURRDATE", currDate());
            message.setContent(content, "text/html;charset=utf-8");
            message.saveChanges();
            Transport transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(VALUE_SMTP, SEND_UNAME, SEND_PWD);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
//            System.out.println("send success!");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
 
    
    
    public static void main(String[] args) {
		EmailUtil emailSend = new EmailUtil();
		boolean result = emailSend.sendjihuoEmail("0D762649CCE04B1BFF1FE67D13B29599","853553545@qq.com");
////		boolean result = emailSend.sendEmail("a471984764@qq.com");
		System.out.println(result);
	}

}
