package app;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Charts extends JFrame{
	UserInfo[] userList;	//储存读取出的所有用户信息

	Charts(JFrame mainFrame){
		this.setSize(500,350);
		this.setLocationRelativeTo(null);
		this.setTitle("排行榜");
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
		score_list[0]=String.format("%-15s%-15s%-15s", "排名","用户","分数");
		for(int i=0;i<userList.length;i++) {
			score_list[i+1]=String.format("%-15s%-15s%-15s", i+1,userList[i].account,userList[i].maxscore);
		}
		JList score_charts=new JList(score_list);
		JScrollPane score_scroll= new JScrollPane(score_charts);
		score_scroll.setBounds(10,5,230,300);
		score_scroll.setBorder(BorderFactory.createTitledBorder("分数排行"));
		this.add(score_scroll);
		
		selectionSort(userList,false);
		String[] time_list=new String[userList.length+1];
		time_list[0]=String.format("%-15s%-15s%-15s", "排名","用户","局数");
		for(int i=0;i<userList.length;i++) {
			time_list[i+1]=String.format("%-15s%-15s%-15s", i+1,userList[i].account,userList[i].time);
		}
		JList time_charts=new JList(time_list);
		JScrollPane time_scroll= new JScrollPane(time_charts);
		time_scroll.setBounds(255,5,230,300);
		time_scroll.setBorder(BorderFactory.createTitledBorder("局数排行"));
		this.add(time_scroll);

		this.setVisible(true);
	}
	
	//插入排序，第二个参数为排序关键字，true按最高分排序，false按局数排序
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
	
	//交换userList中两个用户的信息
	private void swapUserInfo(UserInfo[] userList,int i,int j) {
		UserInfo temp=userList[i];
		userList[i]=userList[j];
		userList[j]=temp;
	}
	
	//将用户信息从xml文件读取到userList
	private void loadUserInfo() {
		Users users=new Users();
		userList=new UserInfo[users.getUserCount()];
		for(int i=0;i<userList.length;i++) {
			userList[i]=new UserInfo(users.getAccount(i+1),users.getMaxScore(i+1),users.getTime(i+1));
		}
	}
	
	//该类储存用户信息
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
