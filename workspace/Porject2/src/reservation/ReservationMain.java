package reservation;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import main.AppMain;
import main.Page;

public class ReservationMain extends Page {
   // 서쪽
   JPanel p_west;
   JButton bt_regist;
   JTextField t_user; // 유저 이름 
   JTextField t_bike; //  유저 바이크
   JTextField t_price; // 가격
   JTextField t_wanted; // 원하는 개수
   JTextArea t_memo; // 필요한 물건 및 예약내용
   JScrollPane scroll;


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
   String filename; // 유저의 복사에 의해 생성된 파일명
   // 테이블
   String[] columns= {"pk_user", "pk_mybike", "price", "pk_booking", "pk_wanted"}; // 컬럼배열
   String[][] records= {};// 레코드 배열
   
   BookingDto bookingDto= new BookingDto();
   BookingDao bookingDao= new BookingDao();

   
   public ReservationMain(AppMain appMain) {
      super(appMain);
      // -----------------------------------------------[생성]
      // 서쪽 관련
      p_west= new JPanel();
      bt_regist= new JButton("상품 예약");
      t_user= new JTextField();
      t_bike= new JTextField();
      t_price= new JTextField();
      t_wanted= new JTextField();
      t_memo= new JTextArea();
      scroll= new  JScrollPane(t_memo);

      
      // PlaceHolder
      t_user.setForeground(Color.GRAY);
      t_user.addFocusListener(new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
              if (t_user.getText().equals("User")) {
            	  t_user.setText("");
            	  t_user.setForeground(Color.BLACK);
              }
          }
          @Override
          public void focusLost(FocusEvent e) {
              if (t_user.getText().isEmpty()) {
            	  t_user.setForeground(Color.GRAY);
            	  t_user.setText("User");
              }
          }
          });
      
      
      
      t_bike.setForeground(Color.GRAY);
      t_bike.addFocusListener(new FocusListener() {
    	  @Override
    	  public void focusGained(FocusEvent e) {
    		  if (t_bike.getText().equals("bike")) {
    			  t_bike.setText("");
    			  t_bike.setForeground(Color.BLACK);
    		  }
    	  }
    	  @Override
    	  public void focusLost(FocusEvent e) {
    		  if (t_bike.getText().isEmpty()) {
    			  t_bike.setForeground(Color.GRAY);
    			  t_bike.setText("bike");
    		  }
    	  }
      });
      
      
      
      t_price.setForeground(Color.GRAY);
      t_price.addFocusListener(new FocusListener() {
    	  @Override
    	  public void focusGained(FocusEvent e) {
    		  if (t_price.getText().equals("price")) {
    			  t_price.setText("");
    			  t_price.setForeground(Color.BLACK);
    		  }
    	  }
    	  @Override
    	  public void focusLost(FocusEvent e) {
    		  if (t_price.getText().isEmpty()) {
    			  t_price.setForeground(Color.GRAY);
    			  t_price.setText("price");
    		  }
    	  }
      });
      
      
      t_wanted.setForeground(Color.GRAY);
      t_wanted.addFocusListener(new FocusListener() {
    	  @Override
    	  public void focusGained(FocusEvent e) {
    		  if (t_wanted.getText().equals("booking")) {
    			  t_wanted.setText("");
    			  t_wanted.setForeground(Color.BLACK);
    		  }
    	  }
    	  @Override
    	  public void focusLost(FocusEvent e) {
    		  if (t_wanted.getText().isEmpty()) {
    			  t_wanted.setForeground(Color.GRAY);
    			  t_wanted.setText("booking");
    		  }
    	  }
      });
      
      
      t_memo.setForeground(Color.GRAY);
      t_memo.addFocusListener(new FocusListener() {
    	  @Override
    	  public void focusGained(FocusEvent e) {
    		  if (t_memo.getText().equals("wanted")) {
    			  t_memo.setText("");
    			  t_memo.setForeground(Color.BLACK);
    		  }
    	  }
    	  @Override
    	  public void focusLost(FocusEvent e) {
    		  if (t_memo.getText().isEmpty()) {
    			  t_memo.setForeground(Color.GRAY);
    			  t_memo.setText("wanted");
    		  }
    	  }
      });
      
      
      

      bt_edit= new JButton("수정");
      bt_del= new JButton("삭제");
      
      
      // 센터
      p_center= new JPanel();
      p_search= new JPanel();
      
      ch_category= new Choice();
      // 검색 카테고리 등록
      ch_category.add("Select");
      ch_category.add("Writer");
      ch_category.add("Content");
      
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

      t_bike.setPreferredSize(d);
      t_wanted.setPreferredSize(d);
      t_price.setPreferredSize(d);
      t_user.setPreferredSize(d);
      // 센터
      p_center.setLayout(new BorderLayout());
      ch_category.setPreferredSize(d);
      t_keyword.setPreferredSize(new Dimension(450, 30));
      
      // -----------------------------------------------[조립]
      
      // 서쪽
      p_west.add(bt_regist);
      p_west.add(t_bike);
      p_west.add(t_wanted);
      p_west.add(t_user);
      p_west.add(t_price);
      p_west.add(scroll);

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
   
    // 등록
      
     bt_regist.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	  try {
				// 유효성 체크
				Integer.parseInt(t_price.getText());				
				if(JOptionPane.showConfirmDialog(ReservationMain.this.getAppMain(), "등록 하시겠습니까?")== JOptionPane.OK_OPTION){
					insertBookingPost();
					selectBookingList();
				}
			}catch(NumberFormatException e1){
				JOptionPane.showMessageDialog(ReservationMain.this.getAppMain(), "가격은 숫자만 입력 가능합니다.");
				t_price.setText("");
				t_price.requestFocus();
			}

      }
   });
     
     // 수정
     bt_edit.addActionListener(new ActionListener() {
 		public void actionPerformed(ActionEvent e) {
 			if(JOptionPane.showConfirmDialog(ReservationMain.this.getAppMain(), "등록 하시겠습니까?")== JOptionPane.OK_OPTION){
 				updateBooking();
 			}
 		}
 	});
     // 삭제
     bt_del.addActionListener(new ActionListener() {
   		public void actionPerformed(ActionEvent e) {
	  			if(JOptionPane.showConfirmDialog(ReservationMain.this.getAppMain(), "등록 하시겠습니까?")== JOptionPane.OK_OPTION){
					
	  			}
   		}
   	});
     
     //검색
     bt_search.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
           search();
        }
     });

     
     // 테이블 연결
     table.addMouseListener(new MouseAdapter() {
		public void mouseReleased(MouseEvent e) {
			selectBookingList();
		}
	});
   }
 
     
     

     
     
  
   

			
   
 
   
   // 상품 등록
   public void insertBookingPost() {
	   
	   bookingDto.setPk_user(t_user.getText());
	   bookingDto.setPk_mybike(Integer.parseInt(t_bike.getText()));
	   bookingDto.setPrice(t_price.getText());
	   bookingDto.setPk_wanted(Integer.parseInt(t_wanted.getText()));
	   bookingDto.setMemo(t_memo.getText());
	   
	   try {
		int result= bookingDao.insertBooking(bookingDto);
		if(result>0) {
			JOptionPane.showMessageDialog(this.getAppMain(), "등록 완료");
		}else {
			JOptionPane.showMessageDialog(this.getAppMain(), "등록 실패");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
   }
   
   public void delete() {
      System.out.println("상품을 삭제하셨습니다.");
   }
   
   
   public void updateBooking() {
      System.out.println("상품을 수정하셨습니다.");
	   int pk_booking= Integer.parseInt((String)table.getValueAt(table.getSelectedRow(), 0));
	  
	   bookingDto.setPk_user(t_user.getText());
	   bookingDto.setPk_mybike(Integer.parseInt(t_bike.getText()));
	   bookingDto.setPrice(t_price.getText());
	   bookingDto.setPk_wanted(Integer.parseInt(t_wanted.getText()));
	   bookingDto.setMemo(t_memo.getText());
	   try {
			int result= bookingDao.updateBooking(bookingDto);
			if(result>0) {
				JOptionPane.showMessageDialog(this.getAppMain(), "수정 완료");
			}else {
				JOptionPane.showMessageDialog(this.getAppMain(), "수정 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
   }
   
   
   
   public void search() {
      System.out.println("상품을 검색하셨습니다.");

   }
   
   public void selectBookingList() {
	   BookingDao bookingDao = new BookingDao();
	   try {
		List<BookingDto> selectBooking = bookingDao.selectBooking();
		showtable(selectBooking);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	   table.updateUI();
   }
   public void showtable(List<BookingDto> selectBooking) {
	   
	   String[][]data = new String[selectBooking.size()][columns.length];
	   
	   int index = 0;
	   while(index<selectBooking.size()) {
		   data[index][0] = selectBooking.get(index).getPk_user();
		   data[index][1] = Integer.toString(selectBooking.get(index).getPk_mybike()); 
		   data[index][2] = selectBooking.get(index).getPrice();
		   data[index][3] = Integer.toString(selectBooking.get(index).getPk_wanted());
		   data[index][4] = selectBooking.get(index).getMemo();
		   index++;
	   }
	   
	   records = data;
	   
	   table.updateUI();
   }
 
   
   

   
   
  }







