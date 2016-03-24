package repository;
//We need to import the java.sql package to use JDBC
import java.sql.*;

import pojos.Employee;

//for reading from the command line
import java.io.*;

public class DataAccess {
	 private static DataAccess instance = null;
	 private Connection con;
	 
	 protected DataAccess() {
	      // Exists only to defeat instantiation.
	   }
	 public static DataAccess getInstance() {
	      if(instance == null) {
	         instance = new DataAccess();
	         instance.initialize();
	      }
	      return instance;
	 }
	
	private boolean initialize(){
		//THIS WILL BE DIFFERENT FOR EACH PERSON
		//eventually will want to move this to a config file that we each have a diff 
		//local copy of
		String connectURL = "jdbc:oracle:thin:@localhost:1522:ug"; 
		String username = "ora_i0l8";
		String password = "a33435124";
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			con = DriverManager.getConnection(connectURL,username,password);
			System.out.println("\nConnected to Oracle!");
			return true;
		} catch (SQLException ex){
			System.out.println("Message: " + ex.getMessage());
			return false;
	   }
	}
	
	public Employee GetEmployeeByName(String employeeName){
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM employees WHERE name = "+ employeeName);
			int employeeId = Integer.parseInt(rs.getString("employeeId"));
			Employee emp = new Employee(employeeId);
			return emp;
		}catch (SQLException ex){
			System.out.println("Message: " + ex.getMessage());
			return null;
		}
		
	}
	
	
}
