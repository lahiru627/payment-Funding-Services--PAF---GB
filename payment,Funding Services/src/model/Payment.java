package model;

import java.sql.*;

public class Payment
	{ 	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	}
	
//insert payment --------------------------------------------------
	
public String insertPayment(String CusID,String CusName, String Pcode, String Amount, String date)
{
	String output = "";
	try
	{
		Connection con = connect();
		
		if (con == null)
		{return "Error while connecting to the database for inserting."; }
		
		// create a prepared statement
		String query = " insert into payments (customerID,customerName,projectCode,paymentAmount,paymentdate)"
						+ " values (?, ?, ?, ?, ?)";
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
		//preparedStmt.setInt(1, 0);
		preparedStmt.setString(1, CusID);
		preparedStmt.setString(2, CusName);
		preparedStmt.setString(3, Pcode);
		preparedStmt.setDouble(4, Double.parseDouble(Amount));
		preparedStmt.setString(5, date);
		
		// execute the statement

		preparedStmt.execute();
		con.close();
		String newPayment = readPayments(); 
		 output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
		
		//output = "Inserted successfully";
	}
	catch (Exception e)
	{
		
		//output = "Error while inserting the Payment.";
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the data.\"}";

		System.err.println(e.getMessage());
		System.out.println(e.getMessage());
		System.out.println(e);
		e.printStackTrace();
	}
	return output;
}

// read payments---------------------------------------------------------

public String readPayments()
{
	String output = "";
	try
	{
		
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for reading."; }

		// Prepare the html table to be displayed
		output = "<table border='1'><tr><th>Customer Id</th><th>Customer Name</th>" +
				"<th>Project Code</th>" +
				"<th>Payment Amount</th>" +
				"<th>Payment Date</th>" +
				"<th>Update</th><th>Remove</th></tr>";
		
		String query = "select * from payments";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		// iterate through the rows in the result set
		while (rs.next())
		{

				String paymentID = Integer.toString(rs.getInt("paymentID"));
				String customerID = rs.getString("customerID");
				String customerName = rs.getString("customerName");
				String projectCode = rs.getString("projectCode");
				String paymentAmount = Double.toString(rs.getDouble("paymentAmount"));
				String paymentdate = rs.getString("paymentdate");

				// Add into the html table
				output += "<tr><td>" + customerID + "</td>";
				output += "<td>" + customerName + "</td>";
				output += "<td>" + projectCode + "</td>";
				output += "<td>" + paymentAmount + "</td>";
				output += "<td>" + paymentdate + "</td>";
				

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn-secondary'></td>"
						+ "<td><button class='btnRemove btn btn-danger' name='btnRemove' id ='btnRemove' value='"+ paymentID +"' >Remove</button></td></tr>";
						
		}
		con.close();
			
		// Complete the html table
		output += "</table>";
	}
	catch (Exception e)
	{
		output = "Error while reading the payments.";
		System.err.println(e.getMessage());
	}
	return output;
}

// update payments ---------------------------------------------------------

public String updatePayment(String ID, String CusID, String CusName, String Pcode, String Amount, String Date)

{
	String output = "";
	try
	{
		Connection con = connect();
		
		if (con == null)
		{return "Error while connecting to the database for updating."; }

		// create a prepared statement
		String query = "UPDATE payments SET customerID=?,customerName=?,projectcode=?,paymentAmount=? ,paymentdate=?  WHERE paymentID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);

		// binding values
		preparedStmt.setString(1, CusID);
		preparedStmt.setString(2, CusName);
		preparedStmt.setString(3, Pcode);
		preparedStmt.setDouble(4, Double.parseDouble(Amount));
		preparedStmt.setString(5, Date);
		preparedStmt.setInt(6, Integer.parseInt(ID));

		// execute the statement
		preparedStmt.execute();
		con.close();
		
		output = "Updated successfully";
		 String newPayment = readPayments(); 
		 output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}"; 

	}
	catch (Exception e)
	{
		//output = "Error while updating the payment.";
		output = "{\"status\":\"error\", \"data\": \"Error while updating the order.\"}"; 

		System.err.println(e.getMessage());
	}
	return output;
}

//Delete payments ---------------------------------------------------------------------

public String deletePayment(String paymentID)
{
	String output = "";
	try
	{
		Connection con = connect();
		
		if (con == null)
		{return "Error while connecting to the database for deleting."; }

		// create a prepared statement
		String query = "delete from payments where paymentID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
		preparedStmt.setInt(1, Integer.parseInt(paymentID));

		// execute the statement
		preparedStmt.execute();
		con.close();
		
		output = "Deleted successfully";
		 String newPayment = readPayments();
		 output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";

	}
	catch (Exception e)
	{
		//output = "Error while deleting the funding.";
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the order.\"}"; 

		System.err.println(e.getMessage());
	}
	return output;
}


}