package Customers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Customers {
	/* Variables from the database model: */
	int cid ;
	String name ;
	String phone;
	String sAddress;
	String pCode;
	String email;



/* The connection to the database (necessary to execute queries): */
private Connection con;

public Customers(Connection con) {
	this.con = con;
}

public int getCid() {
	return cid;
}

public String getName() {
	return name;
}

public String getAddress() {
	return sAddress;
}

public String getPhone() {
	return phone;
}


//If a customer takes a class for first time then he/she have to signup for an account
//if he/she already have an account then he/she can login 
public boolean signup(int cid, String name, 
String phone,String sAddress,String pCode,String email)
{
	try {
	PreparedStatement  ps;
	
	//Statement stmt = con.createStatement();
	ResultSet rs;
	
	
	rs = ps.executeQuery("SELECT customerID" + "Customer(customerID, name, phoneNumber, streetaddress, postalcode,emailAddress )"
	+ "WHERE customerID = ?");
	ps.setInt(1, cid);
	if (rs.next()){
		return false ;}
		
	else
		{ps = con.prepareStatement("INSERT INTO " +
		   "Customer(customerID, name, phoneNumber, streetaddress, postalcode,emailAddress )" +
					" VALUES (?, ?, ?, ?, ?, ?)");
		ps.setInt(1, cid);
		ps.setString(2, name);
		ps.setString(3,  phone);
		ps.setString(4, (sAddress.length() == 0) ? null : sAddress);
		ps.setString(5,  pCode);
		ps.setString(6, email);
		ps.executeUpdate();
		con.commit();
		ps.close();
		
		//Register class for the new customer 
		ps = con.prepareStatement("INSERT INTO CustomerTakesClass VALUES " +
				"(?, ?, ?, ?)");
					
		ps.executeUpdate();
		con.commit();
		ps.close();}}
		
		

	 catch (SQLException e) {
		System.out.println("Could not register user: " + e.getMessage());
		rollback();
		return false;
	}
	return true;
}




/** @return true if the customer logs in successfully */
public boolean login(int cid, String email) {
	
	ResultSet  rs;
	PreparedStatement  ps;

	try
	{
		ps = con.prepareStatement("SELECT *," +
				"FROM Customer C " +
				"WHERE customerID = ? AND emailAddress = ?" );
		
		ps.setInt(1, cid);
		ps.setString(2, email);
		
		rs = ps.executeQuery();
		
		
		ps.close();
		return true;
	}
	catch (SQLException ex)
	{
		System.out.println("Could not login: " + ex.getMessage());
	}	
	
	return false;

	








}

 

















}