package market;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import main.AppMain;
import main.Page;

public class MarketPost extends Page{
   // 서쪽
   JPanel p_west;
   JButton bt_regist;
   JTextField t_title;
   JTextField t_price;
   JTextArea t_detail;
   JScrollPane scroll;
   JButton bt_web;
   JButton bt_file;
   Canvas can;
   JButton bt_edit;
   JButton bt_del;
   
   // 센터
   JPanel p_center;
   JPanel p_search; // 검색 컴포넌트 올려두는 패널
   Choice ch_category; // 검색 카테고리
   JTextField t_keyword; // 검색어입력
   JButton bt_search; 
   
   JTable table;
   JScrollPane scroll_table;
   // 캔버스의 사진
   Toolkit kit= Toolkit.getDefaultToolkit();
   Image image;
   JFileChooser chooser= new JFileChooser("D:\\Workspace\\KoreaIT_project_2\\workspace\\Porject2\\res");
   String filename; // 유저의 복사에 의해 생성된 파일명
   // 테이블
   String[] columns= {"pk_usermarket", "title ", "price ", "regdate", "pk_user "}; // 컬럼배열
   String[][] records= {};// 레코드 배열
    

   public MarketPost(AppMain appMain) {
      super(appMain);
      // -----------------------------------------------[생성]
      // 서쪽 관련
      p_west= new JPanel();
      bt_regist= new JButton("상품등록");
      t_title= new JTextField();
      t_price= new JTextField();
      t_detail= new JTextArea();
      scroll= new  JScrollPane(t_detail);
      bt_web= new JButton("웹에서 파일 찾기");
      bt_file= new JButton("내 컴퓨터에서 파일 찾기");
      
      // 내부익명 클래스는 외부클래스의 멤버변수, 메소드를 접근가능.
      can= new Canvas() { // {}붙으며 extends효과
         public void paint(Graphics g) {
            g.drawImage(image, 0, 0, 180, 180, can);
         }
      };
      bt_edit= new JButton("수정");
      bt_del= new JButton("삭제");
      
      
      // 센터
      p_center= new JPanel();
      p_search= new JPanel();
      
      ch_category= new Choice();
      // 검색 카테고리 등록
      ch_category.add("선택");
      ch_category.add("작성자");
      ch_category.add("내용");
      
      t_keyword= new JTextField();
      bt_search= new JButton("검색");
      
      table= new JTable(new AbstractTableModel() {
         public int getRowCount() {
            return records.length;
         }
         public int getColumnCount() {
            return columns.length;
         }
         // 컬럼 제목
         public String getColumnName(int col) {
            return columns[col];
         }
         // 각 셀에 들어갈 데이터를 이차원 배열로부터 구함
         public Object getValueAt(int row, int col) {
            return records[row][col];
         }
         // JTable의 각 셀의 값을 지정. 셀을 편집한 후 엔터치는 순간 아래의 메소드 호출
         public void setValueAt(Object val, int row, int col) {
            records[row][col]=(String)val; 
            updateMarket(); // 수정
         }
         
         public boolean isCellEditable(int row, int col) {
            if(col==0) { // 첫번쩨 열인 product_id만 읽기전용으로 셋팅
               return false;
            }else {
               return true;
            }
         }
      });
      
      scroll_table= new JScrollPane(table);
      // -----------------------------------------------[스타일, 레이아웃]
      // 공통크기
      Dimension d= new Dimension(180, 30); 
      setLayout(new BorderLayout());
      
      // 서쪽
      p_west.setPreferredSize(new Dimension(200, 700));
      scroll.setPreferredSize(new Dimension(180, 180));
      can.setPreferredSize(new Dimension(180, 180));
      can.setBackground(Color.DARK_GRAY);
      
      t_title.setPreferredSize(d);
      t_price.setPreferredSize(d);
      t_detail.setPreferredSize(d);
      
      // 센터
      p_center.setLayout(new BorderLayout());
      ch_category.setPreferredSize(d);
      t_keyword.setPreferredSize(new Dimension(450, 30));
      
      // -----------------------------------------------[조립]
      // 서쪽
      p_west.add(bt_regist);
      p_west.add(t_title);
      p_west.add(t_price);
      p_west.add(t_detail);
      p_west.add(scroll);
      p_west.add(bt_web);
      p_west.add(bt_file);
      p_west.add(can);
      p_west.add(bt_edit);
      p_west.add(bt_del);
      add(p_west, BorderLayout.WEST);
      
      // 센터
      p_search.add(ch_category);
      p_search.add(t_keyword);
      p_search.add(bt_search);
      p_center.add(p_search, BorderLayout.NORTH);
      p_center.add(scroll_table);
      add(p_center);
      
      // -----------------------------------------------[리스너]
      bt_regist.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// 유효성 체크
			Integer.parseInt(t_price.getText());
			regist();
		}
	});
      bt_edit.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			edit();
  		}
  	});
      bt_del.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			delete();
    		}
    	});
      
   }
   // -------------------------------------------------------------------[메소드]
   // 상품 등록
   public void regist() {
	   /* DB는 지금 연결 못함. AppMain에서 일괄 처리 필요하므로 대충 적어두고 주석 처리
	   PreparedStatement pstmt= null;
	   String sql= "insert into usermarket(title, price, content, filename) values(?,?,?,?)";
	   try {
		pstmt= this.getAppMain().getCon().prepareStatement(sql);
		
		// 바인드 변수 처리
		 pstmt.setString(1, t_title.getText()); // 제목
		 pstmt.setInt(2, Integer.parseInt(t_price.getText()); // 가격(무조건 숫자만 가능하게 유효성 처리)
		 pstmt.setString(3, t_detail.getText()); // 상세 내용
		 pstmt.setString(4, filename); // 이미지 명
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	*/
   }
   public void edit() {
	   /* DB는 지금 연결 못함. AppMain에서 일괄 처리 필요하므로 대충 적어두고 주석 처리 */
	   PreparedStatement pstmt= null;
	   String sql= "update usermarket set title=?, price=?, content=?, filename=?";
	   try {
		pstmt= this.getAppMain().getCon().prepareStatement(sql);
		
		// 바인드 변수 처리
		 pstmt.setString(1, t_title.getText()); // 제목
		 pstmt.setInt(2, Integer.parseInt(t_price.getText()); // 가격(무조건 숫자만 가능하게 유효성 처리)
		 pstmt.setString(3, t_detail.getText()); // 상세 내용
		 pstmt.setString(4, filename); // 이미지 명
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
   }
}