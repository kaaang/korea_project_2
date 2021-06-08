package customer;

import main.AppMain;
import main.Page;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomerCenter extends Page{
	JPanel p_west;
	JPanel p_east;
	JPanel p_south;

	JPanel p_center;
	JPanel p_search;
	Choice ch_category;
	JTextField t_keyword;
	JButton bt_search;

	JTable table;
	JTable comTable;
	JScrollPane scroll_table;
	JScrollPane comScroll_table;

	Thread thread;

	String[] columns = {"no", "제목", "내용", "등록일", "답변여부"};
	String[][] records = {};

	String[] ComColumns = {"no", "제목", "내용", "등록일"};
	String[][] ComRecords = {};

	public CustomerCenter(AppMain appMain) {
		super(appMain);

		p_south=new JPanel();
		p_west = new JPanel();
		p_east = new JPanel();
		p_center = new JPanel();
		p_search = new JPanel();

		ch_category = new Choice();
		for(String string_category : columns){
			ch_category.add(string_category);
		}


		t_keyword = new JTextField();
		bt_search = new JButton("검색");

		table = new JTable(new AbstractTableModel() {
			public int getRowCount() {
				return records.length;
			}

			public int getColumnCount() {
				return columns.length;
			}

			public String getColumnName(int col) {
				return columns[col];
			}

			public Object getValueAt(int row, int col) {
				return records[row][col];
			}

			public void setValueAt(Object val, int row, int col) {
				records[row][col] = (String) val;
			}

			public boolean isCellEditable(int row, int col) {
				if (col == 0) {
					return false;
				} else {
					return true;
				}
			}
		});
		comTable = new JTable(new AbstractTableModel() {
			public int getRowCount() {
				return ComRecords.length;
			}

			public int getColumnCount() {
				return ComColumns.length;
			}

			public String getColumnName(int col) {
				return ComColumns[col];
			}

			public Object getValueAt(int row, int col) {
				return ComRecords[row][col];
			}

			public void setValueAt(Object val, int row, int col) {
				ComRecords[row][col] = (String) val;
			}

			public boolean isCellEditable(int row, int col) {
				if (col == 0) {
					return false;
				} else {
					return true;
				}
			}
		});

		scroll_table = new JScrollPane(table);
		comScroll_table = new JScrollPane(comTable);

		comScroll_table.setVisible(false);


		Dimension d = new Dimension(180, 30);
		setLayout(new BorderLayout());

		p_center.setLayout(new BorderLayout());
		ch_category.setPreferredSize(d);
		t_keyword.setPreferredSize(new Dimension(450, 30));

		//모양 잡아주기
		p_west.setPreferredSize(new Dimension(7,800));
		add(p_west, BorderLayout.WEST);
		p_east.setPreferredSize(new Dimension(7,800));
		add(p_east, BorderLayout.EAST);
		p_south.setPreferredSize(new Dimension(1160,42));
		add(p_south, BorderLayout.SOUTH);

		// 검색영역
		p_search.add(ch_category);
		p_search.add(t_keyword);
		p_search.add(bt_search);
		p_center.add(p_search, BorderLayout.NORTH);
		p_center.add(scroll_table);
		add(p_center);

		thread = new Thread() {
			@Override
			public void run() {
				getList();
			}
		};
		thread.start();


		//이벤트리스너
		bt_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
	}



	private void getList() {
		CustomerDao conn = new CustomerDao();

		try {
			java.util.List<CustomerDto> list= conn.selectAll();
			refresh(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void search() {
	}

	private void refresh(List<CustomerDto> list){
		String[][] data = new String[list.size()][columns.length];

		int index = 0;
		while (index < list.size()) {
			data[index][0] = list.get(index).getPk_user();
//			data[index][1] = list.get(index).getId();
//			data[index][2] = list.get(index).getPass();
//			data[index][3] = list.get(index).getName();
//			data[index][4] = list.get(index).getPhone();
//			data[index][5] = list.get(index).getEmail();
			index++;
		}

		records = data;

		table.updateUI();
	}
}