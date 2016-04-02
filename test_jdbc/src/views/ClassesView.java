package src.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ClassesView extends JPanel implements ActionListener {
		
	/**
	 * This view shows the list of classes as a table
	 */
	private static final long serialVersionUID = 1L;

	// There are three view modes, one to see the classes the current user is in or waitlisted
	// One to see All classes
	// And one to see any classes that all customers have taken (this is purely to demo division)
	private enum ClassViewMode {
		CURRENT_USER,
		ALL,
		DIVIDE,
	}
	
	private JButton refreshButton;
	private ClassViewMode mode;
	// These are the actual buttons to change modes -- there are two at any given time and they don't change
	private JButton[] modeButtons;
	// These are references to the two modeButtons, since the function changes depending on what mode they are in
	// At any given time one of these will be null
	private JButton currentUserButton;
	private JButton allClassesButton;
	private JButton divideButton;
	
	private JLabel tableLabel;
	private JTable classesTable;
	private DefaultTableModel tableModel;
	
	private JPanel buttonsPane;
	private JPanel tableTitlePane;
	private JPanel tablePane;
	
	public ClassesView() {
		initPanel();
	}
	
	/*
	 * Set up what the classes view should look like
	 * Functionality:
	 * See how many people are in each class and waitlist (aggregation)
	 * See classes all customers have taken (division)
	 */
	public void initPanel() {
		buttonsPane = new JPanel();
		tableTitlePane = new JPanel();
		tablePane = new JPanel();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(buttonsPane);
		this.add(tableTitlePane);
		this.add(tablePane);
		
		refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(this);
		buttonsPane.add(refreshButton);
		mode = ClassViewMode.CURRENT_USER;
		tableLabel = new JLabel("My Classes");
		modeButtons = new JButton[2];
		for (int i = 0; i < 2; i++) {
			modeButtons[i] = new JButton();
			modeButtons[i].addActionListener(this);
			buttonsPane.add(modeButtons[i]);
		}
		changeMode(mode);
		tableTitlePane.add(tableLabel);
		resetMaximums();
		if (modeButtons[0] == null || modeButtons[1] == null ) {
			System.out.println("FUCK");
		}
		
		String [] colNames = {"Class Type", "Capacity", "In Class", "WaitList", "Times"};
		tableModel = new DefaultTableModel(colNames, 0);
		classesTable = new JTable(tableModel);
		String [] placeHolderRow = {"aType", "0", "0", "0", "Sunday 10 am - 11am"};
		tableModel.addRow(colNames);
		tableModel.addRow(placeHolderRow);
		tablePane.add(classesTable);
    }
	
	// Make sure to keep layout looking okay when we change text
	// This should get called any time anything in the top two panels changes or stuff
	// might disappear from the screen
	private void resetMaximums() {
		buttonsPane.setMaximumSize(buttonsPane.getPreferredSize());
		tableTitlePane.setMaximumSize(tableTitlePane.getPreferredSize());
	}
	
	private void refresh() {
		// TODO reload date fromDB
		System.out.println("Classes View: refresh");
	}
	
	private void changeMode(ClassViewMode newMode) {
		switch (newMode) {
		case CURRENT_USER:
			tableLabel.setText("My Classes");
			currentUserButton = null;
			modeButtons[0].setText("All Classes");
			allClassesButton = modeButtons[0];
			modeButtons[1].setText("Classes Everyone takes");
			divideButton = modeButtons[1];
			mode = ClassViewMode.CURRENT_USER;
			resetMaximums();
			
			// TODO rearrange table for My Classes and load data
			break;
		case ALL:
			tableLabel.setText("All Classes");
			mode = ClassViewMode.ALL;
			allClassesButton = null;
			modeButtons[0].setText("My Classes");
			currentUserButton = modeButtons[0];
			modeButtons[1].setText("Classes Everyone takes");
			divideButton = modeButtons[1];
			resetMaximums();

			// TODO rearrange table for All Classes and load data
			break;
		case DIVIDE:
			tableLabel.setText("Classes Everyone takes");
			mode = ClassViewMode.DIVIDE;
			divideButton = null;
			modeButtons[0].setText("My Classes");
			currentUserButton = modeButtons[0];
			modeButtons[1].setText("All Classes");
			allClassesButton = modeButtons[1];
			resetMaximums();
			
			// TODO rearrange table for Divide and load data
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == refreshButton) {
			refresh();
		} else if (e.getSource() == currentUserButton) {
			changeMode(ClassViewMode.CURRENT_USER);
		} else if (e.getSource() == allClassesButton) {
			changeMode(ClassViewMode.ALL);
		} else if (e.getSource() == divideButton) {
			changeMode(ClassViewMode.DIVIDE);
		}
	}
}