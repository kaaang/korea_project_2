package login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JoinForm extends JPanel{
	JPanel nul;
	JPanel nul2;
	JLabel l_id;
	JLabel l_nick;
	JLabel l_pass;
	JLabel l_name;
	JLabel l_phone;
	JLabel l_email;
	JLabel l_ymd;
	JLabel ymd;
	
	JTextField t_id;
	JTextField t_nick;
	JTextField t_pass;
	JTextField t_name;
	JTextField t_phone;
	JTextField t_email;
	JTextField t_ymd;
	
	JButton join;
	JButton cancel;
	
	
	
	JLabel id_info;
	JLabel nick_info;
	boolean logincheck=false;
	boolean nickcheck=false;
	
	
	

	MainForm main;
	public JoinForm(MainForm main) {
		this.main = main;
		nul = new JPanel();
		nul2 = new JPanel();
		
		l_id = new JLabel("ID");
		l_nick = new JLabel("닉네임");
		l_pass = new JLabel("PASS");
		l_name = new JLabel("NAME");
		l_phone = new JLabel("PHONE");
		l_email = new JLabel("e-mail");
		l_ymd = new JLabel("생년월일");
		ymd = new JLabel("예시 : 950309");
		
		t_id = new JTextField(20);
		t_nick = new JTextField(20);
		t_pass = new JTextField(20);
		t_name = new JTextField(20);
		t_phone = new JTextField(20);
		t_email = new JTextField(20);
		t_ymd = new JTextField(20);
		
		join = new JButton("회원가입");
		cancel = new JButton("취소");
		
		id_info = new JLabel("ID는 4자리 이상 입력하세요.");
		nick_info = new JLabel("닉네임을 입력하세요.");
		
		
	
		l_id.setPreferredSize(new Dimension(100, 30));
		l_pass.setPreferredSize(new Dimension(100, 30));
		l_name.setPreferredSize(new Dimension(100, 30));
		l_phone.setPreferredSize(new Dimension(100, 30));
		l_email.setPreferredSize(new Dimension(100, 30));
		l_ymd.setPreferredSize(new Dimension(100, 30));
		ymd.setPreferredSize(new Dimension(380, 30));
		ymd.setHorizontalAlignment(JLabel.CENTER);
		id_info.setPreferredSize(new Dimension(380, 30));
		id_info.setHorizontalAlignment(JLabel.CENTER);
		nick_info.setPreferredSize(new Dimension(380, 30));
		nick_info.setHorizontalAlignment(JLabel.CENTER);
		l_nick.setPreferredSize(new Dimension(100, 30));
		
		join.setPreferredSize(new Dimension(130, 40));
		cancel.setPreferredSize(new Dimension(130, 40));
		
		nul.setPreferredSize(new Dimension(380,200));
		nul.setBackground(Color.gray);
		nul2.setPreferredSize(new Dimension(380,100));
		nul2.setBackground(Color.gray);
		
		
		
		
		
		t_id.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				id_check();
			}
		});
		t_nick.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				nick_check();
			}
		});
		
		
		
		
		
		add(nul);
		
		
		add(l_id);
		add(t_id);
		add(id_info);
		
		
		
		add(l_pass);
		add(t_pass);
		
		
		add(l_nick);
		add(t_nick);
		add(nick_info);
		
		add(l_name);
		add(t_name);
		
		add(l_phone);
		add(t_phone);
		
		add(l_email);
		add(t_email);
		
		add(l_ymd);
		add(t_ymd);
		add(ymd);
		
		
		add(nul2);
		
		add(join);
		add(cancel);
		
		
		
		
		
		join.addActionListener(new ActionListener() {
			String db_pass = t_pass.getText();
			String db_name = t_name.getText();
			String db_phone = t_phone.getText();
			String db_email = t_email.getText();
			String db_ymd= t_ymd.getText();
			
			
			
			public void actionPerformed(ActionEvent e) {
				if(db_pass.equals("") || db_name.equals("") || db_phone.equals("") || db_email.equals("") || db_ymd.equals("")) {
					JOptionPane.showMessageDialog(main, "모든 정보를 입력해주세요");
					return;
				}else {
					if(logincheck==true && nickcheck==true) {
						insertJoin();					
					}else if(!logincheck) {
						id_check();
					}else if(!nickcheck) {
						nick_check();
					}					
				}
				
				
				
				
			}
		});
		
		
		
		setVisible(false);
		setBackground(Color.gray);
		setPreferredSize(new Dimension(380, 800));
		setLayout(new FlowLayout());
	}
	
	public void insertJoin() {
		String db_id = t_id.getText();
		String db_nick = t_nick.getText();
		String db_pass = t_pass.getText();
		String db_name = t_name.getText();
		String db_phone = t_phone.getText();
		String db_email = t_email.getText();
		String db_ymd= t_ymd.getText();
		
		
		JoinDto joinDto = new JoinDto(db_id, db_pass, db_name, db_phone, db_email, db_ymd, db_nick);
		JoinDao joinDao = new JoinDao();
		try {
			int result = joinDao.insertJoin(joinDto);
			if(result>0) {
				JOptionPane.showMessageDialog(main, "회원가입 성공");
				main.showLogin();
			}else {
				JOptionPane.showMessageDialog(main, "회원가입 실패");				
				main.showLogin();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(main, "회원가입 실패");				
			main.showLogin();
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public void id_check() {
		String input_id=t_id.getText();
		String db_id="";
		
		if(input_id.length()<4) {
			JOptionPane.showMessageDialog(main, "아이디는 4자리 이상 입력하세요");
			t_id.requestFocus();
			return;
		}else {
			LoginDao conn=new LoginDao();
			try {
				LoginDto login_check = conn.id_check(input_id);
				db_id = login_check.getId();
				if(input_id.equals(db_id)) {
					id_info.setText("ID가 증복됩니다.");
					JOptionPane.showMessageDialog(main, "아이디가 중복됩니다.");
					t_id.requestFocus();
					return;
				}
			} catch (Exception e) {
//			e.printStackTrace();
				logincheck=true;
				id_info.setText("사용 가능한 아이디 입니다.");
			}			
		}
		
	}
	
	
	public void nick_check() {
		String input_nick=t_nick.getText();
		String db_nick="";
		
		if(input_nick.equals("")) {
			JOptionPane.showMessageDialog(main, "닉네임에 공백을 입력하실 수 없습니다.");
		}else {
			LoginDao conn=new LoginDao();
			try {
				LoginDto login_check = conn.nick_check(input_nick);
				db_nick = login_check.getId();
				if(input_nick.equals(db_nick)) {
					nick_info.setText("닉네임이 중복됩니다.");
					JOptionPane.showMessageDialog(main, "닉네임이 중복됩니다.");
				}
			} catch (Exception e) {
//			e.printStackTrace();
				nickcheck=true;
				nick_info.setText("사용 가능한 닉네임 입니다.");
			}
			
		}
		
		
	}
	
	
	
	
	
	
	
}