package app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Account extends JFrame{
	Users users=new Users();	//User类用于对用户信息的XML文件进行操作
	JTabbedPane jtab;	//管理登录，注册，重置界面
	
	Account(JFrame mainFrame){
		this.setSize(500,350);
		this.setLocationRelativeTo(null);
		this.setTitle("账号管理");
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
		jtab.add("登录", panel_login);
		jtab.add("注册", panel_register);
		jtab.add("重置", PanelResetting);
		this.add(jtab);
		
		this.setVisible(true);
	}
	
	//登录界面
	class PanelLogin extends JPanel{
		PanelLogin(){
			this.setLayout(null);
			JLabel label_account=new JLabel("账号:");
			label_account.setBounds(80,40,50,30);
			this.add(label_account);
			
			JTextField text_account=new JTextField();
			text_account.setBounds(130,40,250,30);
			this.add(text_account);
			
			JLabel label_pass=new JLabel("密码:");
			label_pass.setBounds(80,80,50,30);
			this.add(label_pass);
			
			JPasswordField text_pass=new JPasswordField();
			text_pass.setBounds(130,80,250,30);
			this.add(text_pass);
			
			JButton btn_login=new JButton("登录");
			btn_login.setBounds(180, 130, 100, 30);
			btn_login.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(text_account.getText().isEmpty() || String.valueOf(text_pass.getPassword()).isEmpty()) {
						JOptionPane.showMessageDialog(Account.this, "账号和密码不能为空", "登录失败", JOptionPane.WARNING_MESSAGE);
					}
					else {
						int id=users.authentication(text_account.getText(),String.valueOf(text_pass.getPassword()));
						if(id!=0) {
							GameApp.login(id);
							Account.this.dispose();
						}
						else {
							JOptionPane.showMessageDialog(Account.this, "账号或密码错误", "登录失败", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
			this.add(btn_login);
		}	
	}
	
	//注册界面
	class PanelRegister extends JPanel{
		PanelRegister(){
			this.setLayout(null);
			this.setLayout(null);
			JLabel label_account=new JLabel("账号:");
			label_account.setBounds(80,40,50,30);
			this.add(label_account);
			JTextField text_account=new JTextField();
			text_account.setBounds(130,40,250,30);
			this.add(text_account);
			
			JLabel label_pass=new JLabel("密码:");
			label_pass.setBounds(80,80,50,30);
			this.add(label_pass);
			JPasswordField text_pass=new JPasswordField();
			text_pass.setBounds(130,80,250,30);
			this.add(text_pass);
			
			JLabel label_question=new JLabel("问题:");
			label_question.setBounds(80,120,50,30);
			this.add(label_question);
			JComboBox comboBox_question=new JComboBox();
			String select[]= {"你所读的中学是？","你母亲的姓名是？","你父亲的姓名是？","对你最重要的人是？"};
			comboBox_question.setModel(new DefaultComboBoxModel(select));
			comboBox_question.setBounds(130,120,250,30);
			this.add(comboBox_question);
			
			JLabel label_answer=new JLabel("答案:");
			label_answer.setBounds(80,160,50,30);
			this.add(label_answer);
			JTextField text_answer=new JTextField();
			text_answer.setBounds(130,160,250,30);
			this.add(text_answer);
			
			JButton btn_register=new JButton("注册");
			btn_register.setBounds(180, 210, 100, 30);
			btn_register.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(text_account.getText().isEmpty() || String.valueOf(text_pass.getPassword()).isEmpty() || text_answer.getText().isEmpty()) {
						JOptionPane.showMessageDialog(Account.this, "账号、密码和答案不能为空", "注册失败", JOptionPane.WARNING_MESSAGE);
					}
					else {
						if(users.isExist(text_account.getText())){
							JOptionPane.showMessageDialog(Account.this, "账号已存在", "注册失败", JOptionPane.ERROR_MESSAGE);
						}
						else {
							users.addUser(users.getUserCount()+1,text_account.getText() ,String.valueOf(text_pass.getPassword()), comboBox_question.getSelectedItem().toString(), text_answer.getText(), 0, 0);
							JOptionPane.showMessageDialog(Account.this, "注册成功", "注册成功", JOptionPane.INFORMATION_MESSAGE);
							jtab.setSelectedIndex(0);
						}
					}
				}
			});
			this.add(btn_register);
		}
	}
	
	//重置密码界面
	class PanelResetting extends JPanel{
		PanelResetting(){
			this.setLayout(null);
			this.setLayout(null);
			JLabel label_account=new JLabel("账号:");
			label_account.setBounds(80,40,50,30);
			this.add(label_account);
			JTextField text_account=new JTextField();
			text_account.setBounds(130,40,250,30);
			this.add(text_account);
			
			JLabel label_question=new JLabel("问题:");
			label_question.setBounds(80,80,50,30);
			this.add(label_question);
			JComboBox comboBox_question=new JComboBox();
			String select[]= {"你所读的中学是？","你母亲的姓名是？","你父亲的姓名是？","对你最重要的人是？"};
			comboBox_question.setModel(new DefaultComboBoxModel(select));
			comboBox_question.setBounds(130,80,250,30);
			this.add(comboBox_question);
			
			JLabel label_answer=new JLabel("答案:");
			label_answer.setBounds(80,120,50,30);
			this.add(label_answer);
			JTextField text_answer=new JTextField();
			text_answer.setBounds(130,120,250,30);
			this.add(text_answer);
			
			JLabel label_pass=new JLabel("新密码:");
			label_pass.setBounds(80,160,50,30);
			this.add(label_pass);
			JPasswordField text_pass=new JPasswordField();
			text_pass.setBounds(130,160,250,30);
			this.add(text_pass);
			
			JButton btn_restting=new JButton("重置");
			btn_restting.setBounds(180, 210, 100, 30);
			btn_restting.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(text_account.getText().isEmpty()  || text_answer.getText().isEmpty() || String.valueOf(text_pass.getPassword()).isEmpty()) {
						JOptionPane.showMessageDialog(Account.this, "账号、答案和新密码不能为空", "重置失败", JOptionPane.WARNING_MESSAGE);
					}
					else {
						if(users.authenticationInformation(text_account.getText(), comboBox_question.getSelectedItem().toString(), text_answer.getText())) {
							users.modifyPass(users.getId(text_account.getText()),String.valueOf(text_pass.getPassword()));
							JOptionPane.showMessageDialog(Account.this, "重置成功", "重置成功", JOptionPane.INFORMATION_MESSAGE);
							jtab.setSelectedIndex(0);
						}else {
							JOptionPane.showMessageDialog(Account.this, "信息错误", "重置失败", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
			this.add(btn_restting);
		}
	}
	
}
