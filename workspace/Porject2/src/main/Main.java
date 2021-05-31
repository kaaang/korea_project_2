package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame{

	String[] menu_title = {"회원 관리","바이크 관리","중고장터 관리","예약관리","기업 관리","고객 센터"};
	Main_Button[] bt_menu = new Main_Button[menu_title.length];
	
	
	//set-west
	JPanel p_west;
	
	//test
	JPanel p_center;
	JPanel p_east;
	
	
	public Main() {
		//create-west
		p_west = new JPanel();
		for (int i = 0; i < bt_menu.length; i++) {
			bt_menu[i] = new Main_Button(menu_title[i]);
			bt_menu[i].setId(i);
			bt_menu[i].setPreferredSize(new Dimension(110, 30));
		}
		
		
		
		//set-west
		p_west.setPreferredSize(new Dimension(120, 800));
		p_west.setLayout(new FlowLayout());
		p_west.setBackground(Color.BLUE);
		
		
		
		
		//test
		p_center = new JPanel();
		p_center.setPreferredSize(new Dimension(900, 800));
		p_center.setBackground(Color.BLACK);
		add(p_center,BorderLayout.CENTER);
		
		p_east = new JPanel();
		p_east.setPreferredSize(new Dimension(260, 800));
		p_east.setBackground(Color.YELLOW);
		add(p_east,BorderLayout.EAST);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//add-west
		for(JButton bt : bt_menu) {
			p_west.add(bt);
		}
		add(p_west,BorderLayout.WEST);
		
		
		//view
		setTitle("Riding Mate");
		setBounds(200, 100, 1400, 800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	
	
	public static void main(String[] args) {
		new Main();
	}
}
