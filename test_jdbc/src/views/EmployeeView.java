package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class EmployeeView extends JPanel{
	
	private JList list;
	private DefaultListModel model;
	
	public EmployeeView(){
		initPanel();
		this.setVisible(true);
	}
	
	/*
	 * Set up what the employee view should look like
	 * Functionality:
	 * project and select employees
	 */
	public void initPanel() {
		setUpCheckBoxes();
		setUpFilters();
		setUpResultsSpace();
    }
	
	private void setUpCheckBoxes(){
		int numAttr = 7;
		JCheckBox[] checkArray = new JCheckBox[numAttr];
		String[] 
			attrNames = {"sin", "name", "job title", "phone number", "address", "postal Code", "email"};
		JLabel projectText = new JLabel("Select possible projections for employees:");
		projectText.setForeground(Color.DARK_GRAY);
		projectText.setBackground(Color.GRAY);
		projectText.setFont(new Font("Serif", Font.PLAIN, 24));
		this.add(projectText);
		for(int i = 0; i < numAttr; i++){
			checkArray[i] = new JCheckBox(attrNames[i]);
			this.add(checkArray[i]);
			checkArray[i].setFont(new Font("Serif", Font.PLAIN, 24));
			checkArray[i].setSelected(true);
		}
	}
	
	private void setUpFilters(){
		JLabel projectText = new JLabel("Filter employees on:");
		projectText.setForeground(Color.DARK_GRAY);
		projectText.setBackground(Color.GRAY);
		projectText.setFont(new Font("Serif", Font.PLAIN, 24));
		this.add(projectText);
		
		//is an instructor
		JCheckBox isInstructor = new JCheckBox("is an instructor");
		this.add(isInstructor);
		isInstructor.setFont(new Font("Serif", Font.PLAIN, 24));
		isInstructor.setSelected(false);
		
		//is a manager
		JCheckBox isManager = new JCheckBox("is a manager");
		this.add(isManager);
		isManager.setFont(new Font("Serif", Font.PLAIN, 24));
		isManager.setSelected(false);
		
		//name starts with
		JTextField nameField = new JTextField(24);
		this.add(nameField);
		
		JLabel nameLabel = new JLabel("name starts with");
		nameLabel.setForeground(Color.DARK_GRAY);
		nameLabel.setBackground(Color.GRAY);
		nameLabel.setFont(new Font("Serif", Font.PLAIN, 24));
		this.add(nameLabel);
	}
	
	private void setUpResultsSpace(){
		 model = new DefaultListModel<String>();
	     list = new JList<String>(model);
	     list.setFixedCellWidth(1000);
	     for (int i = 0; i < 15; i++){
	    	 model.addElement("Element " + i);
	     }
	    this.add(list);
	}
}
