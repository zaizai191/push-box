package app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Account extends JFrame{
	Users users=new Users();	//User�����ڶ��û���Ϣ��XML�ļ����в���
	JTabbedPane jtab;	//�����¼��ע�ᣬ���ý���
	
	Account(JFrame mainFrame){
		this.setSize(500,350);
		this.setLocationRelativeTo(null);
		this.setTitle("�˺Ź���");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e){
				mainFrame.setVisible(true);
			}
		});
		
		
		jtab=new JTabbedPane(JTabbedPane.TOP);
		JPanel panel_login=new PanelLogin();
		JPanel panel_register=new PanelRegister();
		JPanel PanelResetting=new PanelResetting();
		jtab.add("��¼", panel_login);
		jtab.add("ע��", panel_register);
		jtab.add("����", PanelResetting);
		this.add(jtab);
		
		this.setVisible(true);
	}
	
	//��¼����
	class PanelLogin extends JPanel{
		PanelLogin(){
			this.setLayout(null);
			JLabel label_account=new JLabel("�˺�:");
			label_account.setBounds(80,40,50,30);
			this.add(label_account);
			
			JTextField text_account=new JTextField();
			text_account.setBounds(130,40,250,30);
			this.add(text_account);
			
			JLabel label_pass=new JLabel("����:");
			label_pass.setBounds(80,80,50,30);
			this.add(label_pass);
			
			JPasswordField text_pass=new JPasswordField();
			text_pass.setBounds(130,80,250,30);
			this.add(text_pass);
			
			JButton btn_login=new JButton("��¼");
			btn_login.setBounds(180, 130, 100, 30);
			btn_login.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(text_account.getText().isEmpty() || String.valueOf(text_pass.getPassword()).isEmpty()) {
						JOptionPane.showMessageDialog(Account.this, "�˺ź����벻��Ϊ��", "��¼ʧ��", JOptionPane.WARNING_MESSAGE);
					}
					else {
						int id=users.authentication(text_account.getText(),String.valueOf(text_pass.getPassword()));
						if(id!=0) {
							GameApp.login(id);
							Account.this.dispose();
						}
						else {
							JOptionPane.showMessageDialog(Account.this, "�˺Ż��������", "��¼ʧ��", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
			this.add(btn_login);
		}	
	}
	
	//ע�����
	class PanelRegister extends JPanel{
		PanelRegister(){
			this.setLayout(null);
			this.setLayout(null);
			JLabel label_account=new JLabel("�˺�:");
			label_account.setBounds(80,40,50,30);
			this.add(label_account);
			JTextField text_account=new JTextField();
			text_account.setBounds(130,40,250,30);
			this.add(text_account);
			
			JLabel label_pass=new JLabel("����:");
			label_pass.setBounds(80,80,50,30);
			this.add(label_pass);
			JPasswordField text_pass=new JPasswordField();
			text_pass.setBounds(130,80,250,30);
			this.add(text_pass);
			
			JLabel label_question=new JLabel("����:");
			label_question.setBounds(80,120,50,30);
			this.add(label_question);
			JComboBox comboBox_question=new JComboBox();
			String select[]= {"����������ѧ�ǣ�","��ĸ�׵������ǣ�","�㸸�׵������ǣ�","��������Ҫ�����ǣ�"};
			comboBox_question.setModel(new DefaultComboBoxModel(select));
			comboBox_question.setBounds(130,120,250,30);
			this.add(comboBox_question);
			
			JLabel label_answer=new JLabel("��:");
			label_answer.setBounds(80,160,50,30);
			this.add(label_answer);
			JTextField text_answer=new JTextField();
			text_answer.setBounds(130,160,250,30);
			this.add(text_answer);
			
			JButton btn_register=new JButton("ע��");
			btn_register.setBounds(180, 210, 100, 30);
			btn_register.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(text_account.getText().isEmpty() || String.valueOf(text_pass.getPassword()).isEmpty() || text_answer.getText().isEmpty()) {
						JOptionPane.showMessageDialog(Account.this, "�˺š�����ʹ𰸲���Ϊ��", "ע��ʧ��", JOptionPane.WARNING_MESSAGE);
					}
					else {
						if(users.isExist(text_account.getText())){
							JOptionPane.showMessageDialog(Account.this, "�˺��Ѵ���", "ע��ʧ��", JOptionPane.ERROR_MESSAGE);
						}
						else {
							users.addUser(users.getUserCount()+1,text_account.getText() ,String.valueOf(text_pass.getPassword()), comboBox_question.getSelectedItem().toString(), text_answer.getText(), 0, 0);
							JOptionPane.showMessageDialog(Account.this, "ע��ɹ�", "ע��ɹ�", JOptionPane.INFORMATION_MESSAGE);
							jtab.setSelectedIndex(0);
						}
					}
				}
			});
			this.add(btn_register);
		}
	}
	
	//�����������
	class PanelResetting extends JPanel{
		PanelResetting(){
			this.setLayout(null);
			this.setLayout(null);
			JLabel label_account=new JLabel("�˺�:");
			label_account.setBounds(80,40,50,30);
			this.add(label_account);
			JTextField text_account=new JTextField();
			text_account.setBounds(130,40,250,30);
			this.add(text_account);
			
			JLabel label_question=new JLabel("����:");
			label_question.setBounds(80,80,50,30);
			this.add(label_question);
			JComboBox comboBox_question=new JComboBox();
			String select[]= {"����������ѧ�ǣ�","��ĸ�׵������ǣ�","�㸸�׵������ǣ�","��������Ҫ�����ǣ�"};
			comboBox_question.setModel(new DefaultComboBoxModel(select));
			comboBox_question.setBounds(130,80,250,30);
			this.add(comboBox_question);
			
			JLabel label_answer=new JLabel("��:");
			label_answer.setBounds(80,120,50,30);
			this.add(label_answer);
			JTextField text_answer=new JTextField();
			text_answer.setBounds(130,120,250,30);
			this.add(text_answer);
			
			JLabel label_pass=new JLabel("������:");
			label_pass.setBounds(80,160,50,30);
			this.add(label_pass);
			JPasswordField text_pass=new JPasswordField();
			text_pass.setBounds(130,160,250,30);
			this.add(text_pass);
			
			JButton btn_restting=new JButton("����");
			btn_restting.setBounds(180, 210, 100, 30);
			btn_restting.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(text_account.getText().isEmpty()  || text_answer.getText().isEmpty() || String.valueOf(text_pass.getPassword()).isEmpty()) {
						JOptionPane.showMessageDialog(Account.this, "�˺š��𰸺������벻��Ϊ��", "����ʧ��", JOptionPane.WARNING_MESSAGE);
					}
					else {
						if(users.authenticationInformation(text_account.getText(), comboBox_question.getSelectedItem().toString(), text_answer.getText())) {
							users.modifyPass(users.getId(text_account.getText()),String.valueOf(text_pass.getPassword()));
							JOptionPane.showMessageDialog(Account.this, "���óɹ�", "���óɹ�", JOptionPane.INFORMATION_MESSAGE);
							jtab.setSelectedIndex(0);
						}else {
							JOptionPane.showMessageDialog(Account.this, "��Ϣ����", "����ʧ��", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
			this.add(btn_restting);
		}
	}
	
}
