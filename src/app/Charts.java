package app;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Charts extends JFrame{
	UserInfo[] userList;	//�����ȡ���������û���Ϣ

	Charts(JFrame mainFrame){
		this.setSize(500,350);
		this.setLocationRelativeTo(null);
		this.setTitle("���а�");
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosed(WindowEvent e){
				mainFrame.setVisible(true);
			}
		});
		
		loadUserInfo();
		
		selectionSort(userList,true);
		String[] score_list=new String[userList.length+1];
		score_list[0]=String.format("%-15s%-15s%-15s", "����","�û�","����");
		for(int i=0;i<userList.length;i++) {
			score_list[i+1]=String.format("%-15s%-15s%-15s", i+1,userList[i].account,userList[i].maxscore);
		}
		JList score_charts=new JList(score_list);
		JScrollPane score_scroll= new JScrollPane(score_charts);
		score_scroll.setBounds(10,5,230,300);
		score_scroll.setBorder(BorderFactory.createTitledBorder("��������"));
		this.add(score_scroll);
		
		selectionSort(userList,false);
		String[] time_list=new String[userList.length+1];
		time_list[0]=String.format("%-15s%-15s%-15s", "����","�û�","����");
		for(int i=0;i<userList.length;i++) {
			time_list[i+1]=String.format("%-15s%-15s%-15s", i+1,userList[i].account,userList[i].time);
		}
		JList time_charts=new JList(time_list);
		JScrollPane time_scroll= new JScrollPane(time_charts);
		time_scroll.setBounds(255,5,230,300);
		time_scroll.setBorder(BorderFactory.createTitledBorder("��������"));
		this.add(time_scroll);

		this.setVisible(true);
	}
	
	//�������򣬵ڶ�������Ϊ����ؼ��֣�true����߷�����false����������
	private void selectionSort(UserInfo[] userList,boolean key) {
		if(key) {
			for(int i=0;i<userList.length-1;i++) {
				int max=i;
				for(int j=i+1;j<userList.length;j++) {
					if(userList[j].maxscore>userList[max].maxscore) {
						max=j;
					}
				}
				swapUserInfo(userList,i,max);
			}
		}else {
			for(int i=0;i<userList.length-1;i++) {
				int max=i;
				for(int j=i+1;j<userList.length;j++) {
					if(userList[j].time>userList[max].time) {
						max=j;
					}
				}
				swapUserInfo(userList,i,max);
			}
		}
	}
	
	//����userList�������û�����Ϣ
	private void swapUserInfo(UserInfo[] userList,int i,int j) {
		UserInfo temp=userList[i];
		userList[i]=userList[j];
		userList[j]=temp;
	}
	
	//���û���Ϣ��xml�ļ���ȡ��userList
	private void loadUserInfo() {
		Users users=new Users();
		userList=new UserInfo[users.getUserCount()];
		for(int i=0;i<userList.length;i++) {
			userList[i]=new UserInfo(users.getAccount(i+1),users.getMaxScore(i+1),users.getTime(i+1));
		}
	}
	
	//���ഢ���û���Ϣ
	class UserInfo{
		String account;
		int maxscore;
		int time;
		UserInfo(String account,int maxscore,int time){
			this.account=account;
			this.maxscore=maxscore;
			this.time=time;
		}
	}
}
