package com;

import java.sql.*;

public class fund { // A common method to connect to the DB

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// DB CONNECTION
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fundmanagement", "root", "");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

//----------------------INSERT CRUD-----------------------------------------------------------------------------
	public String insertFund(String code, String name, String price, String location) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " INSERT INTO fund(`code`,`name`,`price`,`location`)" + " VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, code);
			preparedStmt.setString(2, name);
			preparedStmt.setDouble(3, Double.parseDouble(price));
			preparedStmt.setString(4, location);

			// execute the statement
			preparedStmt.execute();
			con.close();
			String newFund = readFund();
			output = "{\"status\":\"success\", \"data\": \"" + newFund + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the fund details.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

//------------READ CRUD----------------------------------------------------------------------
	public String readFund() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Front end View of table
			output = "<table border='2'><tr><th> Funder Code</th>" + "<th>Funder Name</th>" + "<th>Funder Price</th>"
					+ "<th>Funder Location</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "SELECT * FROM fund";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				String code = rs.getString("code");
				String name = rs.getString("name");
				String price = Double.toString(rs.getDouble("price"));
				String location = rs.getString("location");

				//Add into the html table
				output += "<tr><td>" + code + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + price + "</td>";
				output += "<td>" + location + "</td>";

				//delete and update buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-code='" + code + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-code='" + code + "'></td></tr>";
			}
			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) 
			{
			output = "Error while reading the fund details.";
			System.err.println(e.getMessage());
			}
		return output;
	}

//-----------------UPDATE CRUD--------------------------------------------------------------------------------
	public String updateFund(String code, String name, String price, String location) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			// create a prepared statement
			String query = "UPDATE `fund` SET `name`=?,`price`=?,`location`=? WHERE `code`=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setDouble(2, Double.parseDouble(price));
			preparedStmt.setString(3, location);
			preparedStmt.setString(4, code);

			// execute the statement
			preparedStmt.execute();
			con.close();
			String newFund = readFund();
			output = "{\"status\":\"success\", \"data\": \"" + newFund + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the fund details.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

//    ------------------DELETE CRUD--------------------------------------------------------------------
	public String deleteFund(String code) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			// create a prepared statement
			String query = "DELETE FROM fund WHERE code=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, code);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newFund = readFund();
			output = "{\"status\":\"success\", \"data\": \"" + newFund + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the fund details.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
