package market;

import java.awt.Canvas;
import java.awt.Choice;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

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
	// ����
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
	
	// ����
	JPanel p_center;
	JPanel p_search; // �˻� ������Ʈ �÷��δ� �г�
	Choice ch_category; // �˻� ī�װ�
	JTextField t_keyword; // �˻����Է�
	JButton bt_search; 
	
	JTable table;
	JScrollPane scroll_table;
	// ĵ������ ����
		Toolkit kit= Toolkit.getDefaultToolkit();
		Image image;
		JFileChooser chooser;
		String filename; // ������ ���翡 ���� ������ ���ϸ�
	// ���̺�
		String[] columns= {"pk_usermarket", "title ", "price ", "regdate", "pk_user "}; // �÷��迭
		String[][] records= {};// ���ڵ� �迭
	public MarketPost(AppMain appMain) {
		super(appMain);
		// -----------------------------------------------[����]
		// ���� ����
		p_west= new JPanel();
		bt_regist= new JButton("��ǰ���");
		t_title= new JTextField();
		t_price= new JTextField();
		t_detail= new JTextArea();
		scroll= new  JScrollPane(t_detail);
		bt_web= new JButton("������ ���� ã��");
		bt_file= new JButton("�� ��ǻ�Ϳ��� ���� ã��");
		
		// �����͸� Ŭ������ �ܺ�Ŭ������ �������, �޼ҵ带 ���ٰ���.
		can= new Canvas() { // {}������ extendsȿ��
			public void paint(Graphics g) {
				g.drawImage(image, 0, 0, 180, 180, can);
			}
		};
		bt_edit= new JButton("����");
		bt_del= new JButton("����");
		
		
		// ����
		p_center= new JPanel();
		p_search= new JPanel();
		
		ch_category= new Choice();
		// �˻� ī�װ� ���
		ch_category.add("����");
		ch_category.add("�ۼ���");
		ch_category.add("����");
		
		t_keyword= new JTextField();
		bt_search= new JButton("�˻�");
		
		table= new JTable(new AbstractTableModel() {
			public int getRowCount() {
				return records.length;
			}
			public int getColumnCount() {
				return columns.length;
			}
			// �÷� ����
			public String getColumnName(int col) {
				return columns[col];
			}
			// �� ���� �� �����͸� ������ �迭�κ��� ����
			public Object getValueAt(int row, int col) {
				return records[row][col];
			}
			// JTable�� �� ���� ���� ����
			// ���� ������ �� ����ġ�� ���� �Ʒ��� �޼ҵ� ȣ��
			public void setValueAt(Object val, int row, int col) {
				records[row][col]=(String)val; 
//				updateProduct();
//				System.out.println(row+","+col+"��° ���� �����ʹ� "+val+"�� �ٲܰԿ�~");
			}
			// �ٸ� �޼ҵ�� ��ģ������, �Ʒ��� isCe;;Editable�޼��嵵 ȣ���ڰ� JTable
			public boolean isCellEditable(int row, int col) {
				if(col==0) { // ù���� ���� product_id�� �б��������� ����
					return false;
				}else {
					return true;
				}
			}
		});
		
		scroll_table= new JScrollPane(table);
	}
	

}