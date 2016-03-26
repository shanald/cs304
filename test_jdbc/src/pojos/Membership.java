package src.pojos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Membership {
	private static final String NULL = null;
	int customerID;
	char type;
	int validFrom;
	int validTo;
	double amountPaid;
	double fees;
	private Connection con;

	public static void man(String[] args) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection( "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug",
				"ora_s4t8", "a38993127");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT CustomerID FROM Customers"); // RETRIEVE CUSTOMERIDS
			
		// ADD MEMBERSHIP TYPE TO CUSTOMER TABLE
				while (rs.next()) {
					if (rs.equals(NULL)); // NULL FOR NOW BUT SHOULD BE ID OF CUSTOMER WHO WANTS MEMBERSHIP
					Statement stmt1 = con.createStatement();
					ResultSet rs1 = stmt1.executeQuery("ALTER TABLE Customers ADD type char");
					System.out.println(rs1.getString(1));
					stmt.close();
					stmt1.close();
					con.close();
					}
	}
    
	
	/*
      public void addMembership(int customerID, char type,int validFrom, int validTo, double amountPaid,
    		 double fees) {
    	  
    	  Customers cust = new Customers(con);
    	//  if (customerID =)
    	  
    	*/ 
    }
    
    
