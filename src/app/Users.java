package app;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Users {
	Document document;	//DOM����XML�ļ���õ���Document
	NodeList userlist;	//����user�ڵ���б�
	
	//��ʼ����������DOM��
	Users(){
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db=dbf.newDocumentBuilder();
			document=db.parse(this.getClass().getResourceAsStream("/data/users.xml"));
			userlist=document.getElementsByTagName("user");
		}catch(ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	//��������
	public void modifyPass(int id,String pass) {
		pass=MD5.md5(MD5.salt+pass);
		for(int i=0;i<userlist.getLength();i++) {
			Node user=userlist.item(i);
			if(Integer.parseInt(user.getChildNodes().item(0).getTextContent())==id)
			{
				user.getChildNodes().item(2).setTextContent(pass);
				break;
			}
		}
		
		TransformerFactory transformerFactory=TransformerFactory.newInstance();
		try {
			Transformer transformer=transformerFactory.newTransformer();
			transformer.transform(new DOMSource(document), new StreamResult("src/data/users.xml"));
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	//�ж��˻��Ƿ����
	public boolean isExist(String account) {
		for(int i=0;i<userlist.getLength();i++) {
			Node user=userlist.item(i);
			if(account.equals(user.getChildNodes().item(1).getTextContent()))
				return true;
		}
		
		return false;
	}
	
	//��֤��ȫ�����Ƿ���ȷ
	public boolean authenticationInformation(String account,String question,String answer) {
		answer=MD5.md5(MD5.salt+answer);
		for(int i=0;i<userlist.getLength();i++) {
			Node user=userlist.item(i);
			if(account.equals(user.getChildNodes().item(1).getTextContent()) && question.equals(user.getChildNodes().item(3).getTextContent()) && answer.equals(user.getChildNodes().item(4).getTextContent()))
				return true;
		}
		
		return false;
	}
	
	//��֤�˺�����
	public int authentication(String account,String pass) {
		pass=MD5.md5(MD5.salt+pass);
		for(int i=0;i<userlist.getLength();i++) {
			Node user=userlist.item(i);
			if(account.equals(user.getChildNodes().item(1).getTextContent()) && pass.equals(user.getChildNodes().item(2).getTextContent()))
				return Integer.valueOf(user.getChildNodes().item(0).getTextContent());
		}
		
		return 0;
	}
	
	//ͨ���˺�ȡid
	public int getId(String account) {
		for(int i=0;i<userlist.getLength();i++) {
			Node user=userlist.item(i);
			if(account.equals(user.getChildNodes().item(1).getTextContent()))
				return Integer.parseInt(user.getChildNodes().item(0).getTextContent());
		}
		
		return 0;
	}
	
	//ͨ��idȡ��߷�
	public int getMaxScore(int id) {
		for(int i=0;i<userlist.getLength();i++) {
			Node user=userlist.item(i);
			if(Integer.parseInt(user.getChildNodes().item(0).getTextContent())==id)
				return Integer.parseInt(user.getChildNodes().item(5).getTextContent());
		}
		return 0;
	}
	
	//ͨ��idȡ��Ϸ����
	public int getTime(int id) {
		for(int i=0;i<userlist.getLength();i++) {
			Node user=userlist.item(i);
			if(Integer.parseInt(user.getChildNodes().item(0).getTextContent())==id)
				return Integer.parseInt(user.getChildNodes().item(6).getTextContent());
		}
		
		return 0;
	}
	
	//ͨ��idȡ�˺�
	public String getAccount(int id) {
		for(int i=0;i<userlist.getLength();i++) {
			Node user=userlist.item(i);
			if(Integer.parseInt(user.getChildNodes().item(0).getTextContent())==id)
				return user.getChildNodes().item(1).getTextContent();
		}
		
		return "";
	}
	
	//ȡ�û�����
	public int getUserCount() {
		return userlist.getLength();
	}
	
	//������߷ֺ;���
	public void modifyMaxScoreAndTime(int id,int maxscore,int time) {
		for(int i=0;i<userlist.getLength();i++) {
			Node user=userlist.item(i);
			if(Integer.parseInt(user.getChildNodes().item(0).getTextContent())==id)
			{
				user.getChildNodes().item(5).setTextContent(String.valueOf(maxscore));
				user.getChildNodes().item(6).setTextContent(String.valueOf(time));
				break;
			}
		}
		
		TransformerFactory transformerFactory=TransformerFactory.newInstance();
		try {
			Transformer transformer=transformerFactory.newTransformer();
			transformer.transform(new DOMSource(document), new StreamResult("src/data/users.xml"));
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	public void modifyMaxScore(int id,int maxscore){
		for(int i=0;i<userlist.getLength();i++) {
			Node user=userlist.item(i);
			if(Integer.parseInt(user.getChildNodes().item(0).getTextContent())==id)
			{
				user.getChildNodes().item(5).setTextContent(String.valueOf(maxscore));
				break;
			}
		}

		TransformerFactory transformerFactory=TransformerFactory.newInstance();
		try {
			Transformer transformer=transformerFactory.newTransformer();
			transformer.transform(new DOMSource(document), new StreamResult("src/data/users.xml"));
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	//����û�
	public void addUser(int id,String account,String pass,String question,String answer,int maxscore,int time) {
		answer=MD5.md5(MD5.salt+answer);
		pass=MD5.md5(MD5.salt+pass);
		Element element=document.createElement("user");
		
		Element child_element=document.createElement("id");
		child_element.appendChild(document.createTextNode(String.valueOf(id)));
		element.appendChild(child_element);
		
		child_element=document.createElement("account");
		child_element.appendChild(document.createTextNode(account));
		element.appendChild(child_element);
		
		child_element=document.createElement("pass");
		child_element.appendChild(document.createTextNode(pass));
		element.appendChild(child_element);
		
		child_element=document.createElement("question");
		child_element.appendChild(document.createTextNode(question));
		element.appendChild(child_element);
		
		child_element=document.createElement("answer");
		child_element.appendChild(document.createTextNode(answer));
		element.appendChild(child_element);
		
		child_element=document.createElement("maxscore");
		child_element.appendChild(document.createTextNode(String.valueOf(maxscore)));
		element.appendChild(child_element);
		
		child_element=document.createElement("time");
		child_element.appendChild(document.createTextNode(String.valueOf(time)));
		element.appendChild(child_element);
		
		document.getElementsByTagName("users").item(0).appendChild(element);

		TransformerFactory transformerFactory=TransformerFactory.newInstance();
		try {
			Transformer transformer=transformerFactory.newTransformer();
			transformer.transform(new DOMSource(document), new StreamResult("src/data/users.xml"));
		} catch (TransformerException e) {
			e.printStackTrace();
		}	
	}
}

//ʵ��md5����
class MD5 {
	public static String salt="asdfji1SDxx)(^5@!*&^>?|00xxSAD1x";	//Ӳ�������ֵ����ֹmd5���ʺ���ƽ�
	
	public static String md5(String plainText) {
        //����һ���ֽ�����
        byte[] secretBytes = null;
        try {
              // ����һ��MD5���ܼ���ժҪ  
            MessageDigest md = MessageDigest.getInstance("MD5");
            //���ַ������м���
            md.update(plainText.getBytes());
            //��ü��ܺ������
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("û��md5����㷨��");
        }
        //�����ܺ������ת��Ϊ16��������
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16��������
        // �����������δ��32λ����Ҫǰ�油0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
