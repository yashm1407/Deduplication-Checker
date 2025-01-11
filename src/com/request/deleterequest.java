package com.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.filesharing.Dbconn;

/**
 * Servlet implementation class deleterequest
 */
@WebServlet("/deleterequest")
public class deleterequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleterequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String UserName = request.getParameter("usernamedb");
		String[] Uname=UserName.split(",");
		System.out.println("UserName:"+Uname[0]+Uname[1]);
		PrintWriter pw=response.getWriter();
		
		String fromuser=Uname[0].toString();
		String touser=Uname[1].toString();
				
		
		try
		{	
			Connection con=(Connection) Dbconn.conn();
			Statement st=con.createStatement();
			String qryupdate="update requestdata set status='Reject' where fromuser='"+fromuser+"' and touser ='"+touser+"'";
			
			st.executeUpdate(qryupdate);
			
			pw.println("<script> alert('Reject Successfully');</script>");
			RequestDispatcher rd = request.getRequestDispatcher("/UserProfile.jsp");
					rd.include(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
