package com.MyServlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.cj.jdbc.ClientPreparedStatement;

/**
 * Servlet implementation class Login
 */
@WebServlet("/loginfaculty")
public class LoginFaculty extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 System.out.println("Connection Successfully");
		String Faculty_id =request.getParameter("faculty_id");
		String password =request.getParameter("password");
		String jdbcUrl = "jdbc:mysql://localhost:3306/tms";
        String username = "root";
        String pass = "passowrd";
        HttpSession session =request.getSession();
      // PrintWriter out = response.getWriter();
        RequestDispatcher dispatcher =null;
       
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con =DriverManager.getConnection(jdbcUrl,username,pass);
			
			 ClientPreparedStatement pst =(ClientPreparedStatement) con.prepareStatement("select *from faculty where Faculty_Id =? AND Password=?");
			 pst.setString(1, Faculty_id);
			 pst.setString(2, password);
			 ResultSet rs=pst.executeQuery();
			 if(rs.next()) {
				 session.setAttribute("Status", "Success");
				 session.setAttribute("Name", rs.getString("Faculty_Name"));
				 session.setAttribute("Id", rs.getString("Faculty_Id"));
				 dispatcher = request.getRequestDispatcher("Faculty.jsp");
			 }else {
				 session.setAttribute("Status", "Failed");
				 dispatcher=request.getRequestDispatcher("LoginFAculty.jsp");
			 }
			 
			 dispatcher.forward(request, response);
			 rs.close();
			 pst.close();
			 con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

