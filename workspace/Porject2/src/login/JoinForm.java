package login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JoinForm extends JPanel{
	JPanel nul;
	JPanel nul2;
	JLabel l_id;
	JLabel l_pass;
	JLabel l_name;
	JLabel l_phone;
	JLabel l_email;
	JLabel l_ymd;
	JLabel ymd;
	JLabel l_nick;
	
	JTextField t_id;
	JTextField t_pass;
	JTextField t_name;
	JTextField t_phone;
	JTextField t_email;
	JTextField t_ymd;
	JTextField t_nick;
	
	JButton join;
	JButton cancel;
	
	


	
	public JoinForm() {
		nul = new JPanel();
		nul2 = new JPanel();
		
		l_id = new JLabel("ID");
		l_pass = new JLabel("PASS");
		l_name = new JLabel("NAME");
		l_phone = new JLabel("PHONE");
		l_email = new JLabel("e-mail");
		l_ymd = new JLabel("생년월일");
		ymd = new JLabel("예시 : 950309");
		l_nick = new JLabel("닉네임");
		
		t_id = new JTextField(20);
		t_pass = new JTextField(20);
		t_name = new JTextField(20);
		t_phone = new JTextField(20);
		t_email = new JTextField(20);
		t_ymd = new JTextField(20);
		t_nick = new JTextField(20);
		
		join = new JButton("회원가입");
		cancel = new JButton("취소");
		
		
	
		l_id.setPreferredSize(new Dimension(100, 30));
		l_pass.setPreferredSize(new Dimension(100, 30));
		l_name.setPreferredSize(new Dimension(100, 30));
		l_phone.setPreferredSize(new Dimension(100, 30));
		l_email.setPreferredSize(new Dimension(100, 30));
		l_ymd.setPreferredSize(new Dimension(100, 30));
		ymd.setPreferredSize(new Dimension(380, 30));
		ymd.setHorizontalAlignment(JLabel.CENTER);
		l_nick.setPreferredSize(new Dimension(100, 30));
		
		join.setPreferredSize(new Dimension(130, 40));
		cancel.setPreferredSize(new Dimension(130, 40));
		
		nul.setPreferredSize(new Dimension(380,200));
		nul.setBackground(Color.gray);
		nul2.setPreferredSize(new Dimension(380,200));
		nul2.setBackground(Color.gray);
		
		
		
		
		add(nul);
		
		
		add(l_id);
		add(t_id);
		
		add(l_pass);
		add(t_pass);
		
		add(l_name);
		add(t_name);
		
		add(l_phone);
		add(t_phone);
		
		add(l_email);
		add(t_email);
		
		add(l_ymd);
		add(t_ymd);
		add(ymd);
		
		add(l_nick);
		add(t_nick);
		
		add(nul2);
		
		add(join);
		add(cancel);
		
		
		setVisible(false);
		setBackground(Color.gray);
		setPreferredSize(new Dimension(380, 800));
		setLayout(new FlowLayout());
	}
}
