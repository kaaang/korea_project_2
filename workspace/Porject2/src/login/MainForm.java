package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainForm extends JFrame{

	JPanel p_west;
	Image img=null;
	Toolkit kit;
	
	
	LoginForm p_center;
	
	
	public MainForm() {
		p_west = new JPanel();
		p_center = new LoginForm();
		
		
		p_west.setPreferredSize(new Dimension(1000, 800));
		p_west.setBackground(Color.DARK_GRAY);
		ImagePanel panel = new ImagePanel(new ImageIcon("D:\\korea_project_2\\workspace\\Porject2\\res\\img.jpg").getImage());
		p_west.add(panel);
		pack();
		
		
		add(p_west,BorderLayout.WEST);
		add(p_center,BorderLayout.CENTER);
		
		
		setSize(1400, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Riding Mate");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	
	
	public static void main(String[] args) {
		new MainForm();
	}
}
