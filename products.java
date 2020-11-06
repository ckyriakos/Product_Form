package com.example;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import java.sql.DriverManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class products extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			  throws IOException, ServletException{
		boolean exists = false;
		String pbarcode=request.getParameter("pbarcode");
		String pname=request.getParameter("pname");
		String pcolor=request.getParameter("pcolor");
		String pdescription=request.getParameter("pdescription");
		List result = new ArrayList();
		PrintWriter pwriter = response.getWriter();
		Connection myConn=null;
		
		/****************  START OF JDBC STUFF  *******************/
		String url = "jdbc:postgresql://0.0.0.0:5432/products";
		String dbUser = "postgres";
		String dbPassword ="admin";
		
		try {
			Class.forName("org.postgresql.Driver");
			myConn = DriverManager.getConnection(url, dbUser, dbPassword); // 1. Get a connection to database
			Statement myStmt = myConn.createStatement();                              // 2. Create a statement
			
			
			// Search DB for the product that they gave
			ResultSet searchResult = myStmt.executeQuery("SELECT pbarcode FROM products" +  ";");
			  // 3. Form the SQL Query
				
				myStmt.executeUpdate("INSERT INTO products" + " (pbarcode, pname, pcolor, pdescription) " + " values('" + pbarcode+ "', '" + pname + "','" + pcolor + "','" + pdescription + "');");
				pwriter.println("<h2>Insert Successful!</h2>");
				result.add(pbarcode);
				result.add(pname);
				result.add(pcolor);
				result.add(pdescription);
				request.setAttribute("details", result);
				RequestDispatcher view = request.getRequestDispatcher("result.jsp");
				view.forward(request, response);
			
		}	
		
		catch(Exception exc) {
			//pwriter.println("<p>Error occured: " + exc.toString() + "</p>");
			pwriter.println("<p>Error occured: Item already exists in the database</p>"); 
			System.err.println("An error occurred while connecting PostgreSQL database: products");
			exc.printStackTrace();
			System.out.println("\nError again my friend...");
		}
		
		
		pwriter.close();
	}
}
		
