package com.snfq.module.flow.util;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MailUtil {

	private MimeMessage mimeMsg; // MIME邮件对象
	private Session session; // 邮件会话对象
	private Properties props; // 系统属性
	private boolean needAuth = false; // smtp是否需要认证
	// smtp认证用户名和密码
	private String username;
	private String password;
	private Multipart mp; // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象

	/**
	 * Constructor
	 * 
	 * @param smtp
	 *            邮件发送服务器
	 */
	public MailUtil(String smtp) {
		setSmtpHost(smtp);
		createMimeMessage();
	}

	/**
	 * 设置邮件发送服务器
	 * 
	 * @param hostName
	 *            String
	 */
	public void setSmtpHost(String hostName) {
//		System.out.println("设置系统属性：mail.smtp.host = " + hostName);
		if (props == null)
			props = System.getProperties(); // 获得系统属性对象
		props.put("mail.smtp.host", hostName); // 设置SMTP主机
	}

	/**
	 * 创建MIME邮件对象
	 * 
	 * @return
	 */
	public boolean createMimeMessage() {
		try {
//			System.out.println("准备获取邮件会话对象！");
			session = Session.getDefaultInstance(props, null); // 获得邮件会话对象
		} catch (Exception e) {
			System.err.println("获取邮件会话对象时发生错误！" + e);
			return false;
		}

//		System.out.println("准备创建MIME邮件对象！");
		try {
			mimeMsg = new MimeMessage(session); // 创建MIME邮件对象
			mp = new MimeMultipart();

			return true;
		} catch (Exception e) {
			System.err.println("创建MIME邮件对象失败！" + e);
			return false;
		}
	}

	/**
	 * 设置SMTP是否需要验证
	 * 
	 * @param need
	 */
	public void setNeedAuth(boolean need) {
//		System.out.println("设置smtp身份认证：mail.smtp.auth = " + need);
		if (props == null)
			props = System.getProperties();
		if (need) {
			props.put("mail.smtp.auth", "true");
		} else {
			props.put("mail.smtp.auth", "false");
		}
	}

	/**
	 * 设置用户名和密码
	 * 
	 * @param name
	 * @param pass
	 */
	public void setNamePass(String name, String pass) {
		username = name;
		password = pass;
	}

	/**
	 * 设置邮件主题
	 * 
	 * @param mailSubject
	 * @return
	 */
	public boolean setSubject(String mailSubject) {
//		System.out.println("设置邮件主题！");
		try {
			mimeMsg.setSubject(mailSubject);
			return true;
		} catch (Exception e) {
			System.err.println("设置邮件主题发生错误！");
			return false;
		}
	}

	/**
	 * 设置邮件正文
	 * 
	 * @param mailBody
	 *            String
	 */
	public boolean setBody(String mailBody) {
		try {
			BodyPart bp = new MimeBodyPart();
			String nMailBody = mailBody;// MimeUtility.encodeText( mailBody) ;
			bp.setContent("" + nMailBody, "text/html;charset=GBK");
			// bp.setText(""+nMailBody);
			mp.addBodyPart(bp);

			return true;
		} catch (Exception e) {
			System.err.println("设置邮件正文时发生错误！" + e);
			return false;
		}
	}

	/**
	 * 添加附件
	 * 
	 * @param filename
	 *            String
	 */
	public boolean addFileAffix(String filename) {

//		System.out.println("增加邮件附件：" + filename);
		try {
			if (filename != null && filename.trim().length() > 0) {
				String[] arr = filename.split(",");
				int receiverCount = arr.length;
				if (receiverCount > 0) {
					for (int i = 0; i < receiverCount; i++) {
						BodyPart bp = new MimeBodyPart();
						FileDataSource fileds = new FileDataSource(arr[i]);
						bp.setDataHandler(new DataHandler(fileds));

						// 附件名乱码问题
						String mailFileName = MimeUtility.encodeText(fileds
								.getName());
						bp.setFileName(mailFileName);

						mp.addBodyPart(bp);
					}
				}
			}

			return true;
		} catch (Exception e) {
			System.err.println("增加邮件附件：" + filename + "发生错误！" + e);
			return false;
		}
	}

	/**
	 * 设置发信人
	 * 
	 * @param from
	 *            String
	 */
	public boolean setFrom(String from) {
//		System.out.println("设置发信人！");
		try {
			mimeMsg.setFrom(new InternetAddress(from)); // 设置发信人
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 设置收信人
	 * 
	 * @param to
	 *            String
	 */
	public boolean setTo(String to) {
		if (to == null)
			return false;
		try {
			if (to != null && to.trim().length() > 0) {
				String[] arr = to.split(",");
				int receiverCount = arr.length;
				if (receiverCount > 0) {
					for (int i = 0; i < receiverCount; i++) {
						mimeMsg.addRecipients(Message.RecipientType.TO,
								InternetAddress.parse(arr[i]));
					}
				}
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 设置抄送人
	 * 
	 * @param copyto
	 *            String
	 */
	public boolean setCopyTo(String copyto) {
		if (copyto == null)
			return false;
		try {
			mimeMsg.setRecipients(Message.RecipientType.CC,
					(Address[]) InternetAddress.parse(copyto));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 设置密送人
	 * 
	 * @param Bcopyto
	 *            String
	 */
	public boolean setBCC(String Bcopyto) {
//		System.out.println(Bcopyto);
		if (Bcopyto == null)
			return false;
		try {
			if (Bcopyto != null && Bcopyto.trim().length() > 0) {
				String[] arr = Bcopyto.split(",");
				int receiverCount = arr.length;
				if (receiverCount > 0) {
					for (int i = 0; i < receiverCount; i++) {
						mimeMsg.addRecipients(Message.RecipientType.BCC,
								InternetAddress.parse(arr[i]));
					}
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 发送邮件
	 */
	public boolean sendOut() {
		try {
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
//			System.out.println("正在发送邮件....");

			MailcapCommandMap mc = (MailcapCommandMap) CommandMap
					.getDefaultCommandMap();
			mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
			mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
			mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
			mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
			mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
			CommandMap.setDefaultCommandMap(mc);

			Session mailSession = Session.getInstance(props, null);
			Transport transport = mailSession.getTransport("smtp");
//			System.out.println((String) props.get("mail.smtp.host"));
//			System.out.println(username);
//			System.out.println(password);
			transport.connect((String) props.get("mail.smtp.host"), username,
					password);
//			transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.TO));
			// transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.CC));
			 transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.BCC));
			// transport.send(mimeMsg);

			System.out.println("发送邮件成功！");
			transport.close();

			return true;
		} catch (Exception e) {
			System.err.println("邮件发送失败！" + e);
			return false;
		}
	}

	/**
	 * 调用sendOut方法完成邮件发送
	 * 
	 * @param smtp
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 * @param username
	 * @param password
	 * @return boolean
	 */
	public static boolean send(String smtp, String from, String to,
			String subject, String content, String username, String password) {
		MailUtil theMail = new MailUtil(smtp);
		theMail.setNeedAuth(true); // 需要验证

		if (!theMail.setSubject(subject))
			return false;
		if (!theMail.setBody(content))
			return false;
		if (!theMail.setTo(to))
			return false;
		if (!theMail.setFrom(from))
			return false;
		theMail.setNamePass(username, password);

		if (!theMail.sendOut())
			return false;
		return true;
	}

	/**
	 * 调用sendOut方法完成邮件发送,带抄送
	 * 
	 * @param smtp
	 * @param from
	 * @param to
	 * @param copyto
	 * @param subject
	 * @param content
	 * @param username
	 * @param password
	 * @return boolean
	 */
	public static boolean sendAndCc(String smtp, String from, String to,
			String copyto, String subject, String content, String username,
			String password) {
		MailUtil theMail = new MailUtil(smtp);
		theMail.setNeedAuth(true); // 需要验证

		if (!theMail.setSubject(subject))
			return false;
		if (!theMail.setBody(content))
			return false;
		if (!theMail.setTo(to))
			return false;
		if (!theMail.setCopyTo(copyto))
			return false;
		if (!theMail.setFrom(from))
			return false;
		theMail.setNamePass(username, password);

		if (!theMail.sendOut())
			return false;
		return true;
	}

	/**
	 * 调用sendOut方法完成邮件发送,带附件
	 * 
	 * @param smtp
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 * @param username
	 * @param password
	 * @param filename
	 *            附件路径
	 * @return
	 */
	public static boolean send(String smtp, String from, String to,
			String subject, String content, String username, String password,
			String filename) {
		MailUtil theMail = new MailUtil(smtp);
		theMail.setNeedAuth(true); // 需要验证

		if (!theMail.setSubject(subject))
			return false;
		if (!theMail.setBody(content))
			return false;
		if (!theMail.addFileAffix(filename))
			return false;
		if (!theMail.setTo(to))
			return false;
		if (!theMail.setFrom(from))
			return false;
		theMail.setNamePass(username, password);

		if (!theMail.sendOut())
			return false;
		return true;
	}

	/**
	 * 调用sendOut方法完成邮件发送,带附件和抄送
	 * 
	 * @param smtp
	 * @param from
	 * @param to
	 * @param copyto
	 * @param subject
	 * @param content
	 * @param username
	 * @param password
	 * @param filename
	 * @return
	 */
	public static boolean sendAndCc(String smtp, String from, String to,
			String copyto, String subject, String content, String username,
			String password, String filename) {
//		System.out.println("sendAndCc");
		MailUtil theMail = new MailUtil(smtp);
		theMail.setNeedAuth(false); // 需要验证
//		System.out.println("setSubject");
		if (!theMail.setSubject(subject))
			return false;
		theMail.setBody(content);
//		System.out.println("setBody");
		if(!theMail.setBCC(to)) return false;
		
//		System.out.println("setBCC");
//		if (!theMail.setTo(to))	return false;
		// if(!theMail.setCopyTo(copyto)) return false;
		if (!theMail.setFrom(from))
			return false;
		
//		System.out.println("setFrom");
		theMail.setNamePass(username, password);

		theMail.addFileAffix(filename);
		
		if (!theMail.sendOut())
			return false;
		
//		System.out.println("sendOut");
		return true;
	}

	// 发邮件测试！
	// public static void main(String[] args) {
	// String smtp = "smtp.qq.com";
	// String from = "liangjiajie@sz-rst.com";
	// String to = "2858132661@qq.com";
	// String copyto = "2858132661@qq.com";
	// String subject = "这是一个测试邮件";
	// String content = "测试啊测试！测试啊测试！";
	// String username="liangjiajie@sz-rst.com";
	// String password="1qaz@WSX3edc";
	// String filename = "D:\\test.jpg";
	// Mail.sendAndCc(smtp, from, to, copyto, subject, content, username,
	// password, filename);
	// }
}