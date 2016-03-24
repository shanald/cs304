package views;

import javax.swing.*;

public class ClassesView extends JPanel {
	private JTable classesTable;
	
	/*
	 * Set up what the clases view should look like
	 * Functionality:
	 * See how many people are in each class and waitlist (aggregation)
	 * See classes all customers have taken (division)
	 */
	public void initPanel() {
		String [] colNames = {"Class Type", "Capacity", "In Class", "WaitList", "Times"};
		classesTable = new JTable(null, colNames);
		this.add(classesTable);
    }
}
