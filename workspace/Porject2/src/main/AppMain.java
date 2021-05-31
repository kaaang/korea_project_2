package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AppMain extends JFrame{
	JPanel p_west;
	JPanel p_top;
	JPanel p_sub;
	
	String[] top_title= {"ȸ�� ����","����ũ ����","�߰����� ����","�������","��� ����","�� ����"};
	CustomButton[] bt_top = new CustomButton[top_title.length];
	String[] sub_user = {"ȸ��","����ũ","����","����"};
	CustomButton[] bt_user = new CustomButton[sub_user.length];
	String[] sub_bike = {"��ü��ȸ"};
	CustomButton[] bt_bike = new CustomButton[sub_bike.length];
	String[] sub_market = {"�Խñ�","�α�","����"};
	CustomButton[] bt_market = new CustomButton[sub_market.length];
	String[] sub_customer = {"�Խñ�","�̴亯"};
	CustomButton[] bt_customer = new CustomButton[sub_customer.length];
	String[] sub_reservation = {"�Խñ�"};
	CustomButton[] bt_reservation = new CustomButton[sub_reservation.length];
	String[] sub_company = {"��ü��ȸ"};
	CustomButton[] bt_company = new CustomButton[sub_company.length];
	
	
	public AppMain() {
		p_west = new JPanel();
		p_top = new JPanel();
		p_sub = new JPanel();
		for(int i=0;i<top_title.length ;i++) {
			bt_top[i] = new CustomButton(top_title[i]);
			bt_top[i].setId(i);
		}
		
		
		
		
		p_west.setPreferredSize(new Dimension(240, 800));
		p_west.setBackground(Color.BLACK);
		p_top.setPreferredSize(new Dimension(110, 800));
		p_top.setBackground(Color.DARK_GRAY);
		p_sub.setPreferredSize(new Dimension(110, 800));
		p_sub.setBackground(Color.GRAY);
		
		
		
		
		for (JButton bt: bt_top) {
			bt.setPreferredSize(new Dimension(110, 35));
			bt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object obj = e.getSource();
					CustomButton bt = (CustomButton)obj;
					if(bt.getId()==0) {
						createUser(sub_user,bt_user);
					}else if(bt.getId()==1) {
						createUser(sub_bike,bt_bike);						
					}else if(bt.getId()==2) {
						createUser(sub_market,bt_market);						
					}else if(bt.getId()==3) {
						createUser(sub_customer,bt_customer);						
					}else if(bt.getId()==4) {
						createUser(sub_reservation,bt_reservation);						
					}else if(bt.getId()==5) {
						createUser(sub_company,bt_company);						
					}
				}
			});
			p_top.add(bt);
		}
		p_west.add(p_top);
		p_west.add(p_sub);
		add(p_west,BorderLayout.WEST);
		
		
		
		

		setSize(1400, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Riding Mate");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	public void createUser(String[] sub,CustomButton[] bt_sub) {
		p_sub.removeAll();
		for(int i=0;i<sub.length ;i++) {
			bt_sub[i] = new CustomButton(sub[i]);
			bt_sub[i].setId(i);
		}
		for(JButton bt : bt_sub) {
			bt.setPreferredSize(new Dimension(110, 35));
			p_sub.add(bt);
		}
		p_sub.updateUI();
		
	}
	
	
	public static void main(String[] args) {
		new AppMain();
	}
}
