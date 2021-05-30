package main;

import javax.swing.JButton;

public class Main_Button extends JButton{
	private int id;
	
	public Main_Button(String title) {
		super(title);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
