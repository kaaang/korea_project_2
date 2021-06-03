package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.AppMain;

public class MainForm extends JFrame{

	JPanel p_west;
	Image img=null;
	Toolkit kit;
	
	JPanel p_center;
	
	LoginForm login;
	JoinForm join;
	
	AppMain appMain;
	
	
	public MainForm() {
		p_west = new JPanel();
		p_center = new JPanel();
		login = new LoginForm();
		join = new JoinForm();
		appMain = new AppMain();
		
		
		
		
		login.login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("로그인 정보 확인");
				appMain.setVisible(true);
				MainForm.this.setVisible(false);
			}
		});
		login.join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showJoin();
			}
		});
		join.join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("회원가입 정보 저장");
				showLogin();
			}
		});
		join.cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showLogin();				
			}
		});
		
		
		p_west.setPreferredSize(new Dimension(1000, 800));
		p_west.setBackground(Color.DARK_GRAY);
		ImagePanel panel = new ImagePanel(new ImageIcon("D:\\korea_project_2\\workspace\\Porject2\\res\\img.jpg").getImage());
		p_west.add(panel);
		pack();
		
		
		add(p_west,BorderLayout.WEST);
		p_center.add(login);
		p_center.add(join);
		add(p_center,BorderLayout.CENTER);
		
		
		
		setSize(1400, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Riding Mate");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	public void showJoin() {
		login.setVisible(false);
		join.setVisible(true);
		p_center.updateUI();
		
	}
	public void showLogin() {
		login.setVisible(true);
		join.setVisible(false);
		p_center.updateUI();
		
	}
	
	
	
	
	public static void main(String[] args) {
		new MainForm();
	}
}
