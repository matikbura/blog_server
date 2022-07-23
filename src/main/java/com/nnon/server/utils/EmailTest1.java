/**
 * 
 */
package com.nnon.server.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class EmailTest1 {
	 private String host = "smtp.qq.com";
	    private static String user = "601084853@qq.com";
	    private String from = "601084853@qq.com";
	    private String to = "601084853@qq.com";
	    private List<String> recipientT0List = new ArrayList<>();

	    public void setAddress(String from, String to) {
	        this.from = from;
	        this.to = to;
	    }
	    public void send(String subject,String htmltxt) {
	        Properties props = new Properties();
			// 开启debug调试
			props.setProperty("mail.debug", "true");
			// 发送服务器需要身份验证
			props.setProperty("mail.smtp.auth", "true");
			// 设置邮件服务器主机名
			props.setProperty("mail.host", "smtp.qq.com");
			// 发送邮件协议名称
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.imap.partialfetch", "false");
			// 开启SSL加密，否则会失败

			// 创建session
			Session session = Session.getInstance(props);
	        session.setDebug(true);
	        MimeMessage message = new MimeMessage(session);

	        try {
	            message.setFrom(new InternetAddress(from));
	            if(to.contains(";"))
	            {
	            	String[] toResult=to.split(";");
	            	if(toResult!=null)
	            	{
	            		for(String result:toResult)
	            		{
	            			if(result!=null&& !recipientT0List.contains(result))
	            			{
	            				recipientT0List.add(result);
	            			}
	            		}
	            	}
	            	if (recipientT0List.size() > 0) {
		                InternetAddress[] sendTo = new InternetAddress[recipientT0List.size()];
		                for (int i = 0; i < recipientT0List.size(); i++) {
		                    sendTo[i] = new InternetAddress(recipientT0List.get(i), "", "UTF-8");
		                }
		                message.addRecipients(MimeMessage.RecipientType.TO, sendTo);
		            }
	            }
	            else
	            {
	            	  message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	            }

	            message.setSubject(subject);


	            MimeMultipart mp = new MimeMultipart("related");
	            MimeBodyPart htmlPart = new MimeBodyPart();
	            htmlPart.setContent(htmltxt,"text/html;charset=utf-8");
	            mp.addBodyPart(htmlPart);
	            message.setContent(mp);
	            message.saveChanges();
	            Transport transport = session.getTransport();
	            transport.connect("smtp.qq.com", user, "lbywxrcyhhvubeii");
	            transport.sendMessage(message, message.getAllRecipients());
	            transport.close();

	        } catch (Exception e) {

	            e.printStackTrace();

	        }

	    }
	    public static void sendCheckStatusNumb(String tableInfo)
	    {
	    	EmailTest1 cn = new EmailTest1();

	        // ���÷����˵�ַ���ռ��˵�ַ���ʼ�����
	    	String subject = "��ʱ����-�������©�����ݼ����֪ͨ"; // �ʼ�����
	        //cn.setAddress(user, "123456@qq.com", "Դ����");
	     //   String today = DateUtils.formatDate(new Date(), DateUtils.FORMAT_MAIL);
	     //   tableInfo = "<tr> <td>test</td><td>test2</td><td>test3</td></tr>";
	    	Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH) + 1;
            int day = now.get(Calendar.DAY_OF_MONTH);
            String content = String.format(
                    "<html>" +
                    "<body>��λ�쵼��ͬ�£�</br>" +
                    "&emsp;&emsp;���Ǻ�!</br>" +
                    "&emsp;&emsp;%d��%d��%d����GBMϵͳ�д������¿���©�����ݣ�����:</br> "
                    + " <table border=\"1\"  > <tr bgcolor=\"green\"  > <th align = \"center\">��   ��</th> <th align = \"center\" >�������</th> <th align = \"center\">���</th> <th align = \\\"center\\\">�÷���</th><th align = \\\"center\\\">װ����</th> </tr> "
                    +tableInfo+" </table>" +
                   // +tableInfo+ 
                    "</br> " +
                    "</br> " +
                    "<img src=\"cid:gamclogo\"></br> ���ʼ�Ϊϵͳ�Զ����ͣ�����ֱ�ӻظ��� " +
                    "</body>" +
                    "</html>", year,month,day);

	        cn.send(subject,content);;

	    }
	    public static void sendNotify4CheckDuplicateLOU(String effectiveDate,String tableInfo)
	    {
	    	 String subject = "��ʱ����-ͬ��P�ܳɼ����֪ͨ"; // �ʼ�����
	    	 EmailTest1 cn = new EmailTest1();
	    	Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH) + 1;
            int day = now.get(Calendar.DAY_OF_MONTH);
            String content = String.format(
                    "<html>" +
                    "<body>��λ�쵼��ͬ�£�</br>" +
                    "&emsp;&emsp;���Ǻ�!</br>" +
                    "&emsp;&emsp;%d��%d��%d����GBMϵͳʹ����Чʱ�䡾"+effectiveDate+"�������˾�ȷ��ѯ��飬�����ظ�����ŵ�P�ܳɣ��б����£�</br>&emsp;&emsp;</br> "
                    + " <table border=\"1\"  > <tr bgcolor=\"green\"  > <th align = \"center\">����</th> <th align = \"center\" >��������</th> <th align = \"center\">�����</th><th align = \\\"center\\\">��Чʱ��</th><th align = \\\"center\\\">ʧЧʱ��</th>  </tr> "
                    +tableInfo+" </table>" +  
                    "</br> " +
                    "</br> " +
                    "<img src=\"cid:gamclogo\"></br> ���ʼ�Ϊϵͳ�Զ����ͣ�����ֱ�ӻظ��� " +
                    "</body>" +
                    "</html>", year,month,day);

	        cn.send(subject,content);
	    }
	    public static void sendNotify4CheckColorNumb(String tableInfo)
	    {
	    	
	 	    String subject = "��ʱ����-��ɫ������֪ͨ"; // �ʼ�����
	 	    EmailTest1 cn = new EmailTest1();
	    	Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH) + 1;
            int day = now.get(Calendar.DAY_OF_MONTH);
            String content = String.format(
                    "<html>" +
                    "<body>��λ�쵼��ͬ�£�</br>" +
                    "&emsp;&emsp;���Ǻ�!</br>" +
                    "&emsp;&emsp;%d��%d��%d����GBMϵͳ��������ɫ��ά�������飬������£�</br>&emsp;&emsp;��������ڶ�Ӧ������û��������ɫ��:</br> "
                    + " <table border=\"1\"  > <tr bgcolor=\"green\"  > <th align = \"center\">����</th> <th align = \"center\" >�����</th> <th align = \"center\">�����</th>  </tr> "
                    +tableInfo+" </table>" +  
                    "</br> " +
                    "</br> " +
                    "<img src=\"cid:gamclogo\"></br> ���ʼ�Ϊϵͳ�Զ����ͣ�����ֱ�ӻظ��� " +
                    "</body>" +
                    "</html>", year,month,day);

	        cn.send(subject,content);
	    }
}

