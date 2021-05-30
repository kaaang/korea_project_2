package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame{

	String[] menu_title = {"雀盔 包府","官捞农 包府","吝绊厘磐 包府","扁诀 包府"};
	Main_Button[] bt_menu = new Main_Button[menu_title.length];
	
	
	//set-west
	JPanel p_west;
	
	
	
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
