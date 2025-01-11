package com.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fileshare.SendMail;
import com.filesharing.Dbconn;

/**
 * Servlet implementation class requestaccept
 */
@WebServlet("/requestaccept")
public class requestaccept extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public requestaccept() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String UserName = request.getParameter("usernamedb");
		String[] Uname=UserName.split(",");
		System.out.println("UserName:"+Uname[0]+Uname[1]);
		PrintWriter pw=response.getWriter();
		
		String fromuser=Uname[0].toString();
		String touser=Uname[1].toString();
		String filename=Uname[2].toString();		
		
		try
		{	
			Connection con=(Connection) Dbconn.conn();
			Statement st=con.createStatement();
			String qryupdate="update requestdata set status='Accept' where fromuser='"+fromuser+"' and touser ='"+touser+"'";
			
			st.executeUpdate(qryupdate);
			st.executeUpdate("insert into access values('"+ touser + "' , '"+ filename + "' , '" + fromuser + "')");				
			String FileKey = "";
			String query1 = "select PrivateKey from fileinfo where filename='"+ filename + "' and user='"+touser+"'";
			ResultSet rs1 = st.executeQuery(query1);

			while (rs1.next()) {
				FileKey = rs1.getString(1);

				// SendMail.mailSend(filename, "", email);
			}
			SendMail.mailSend(filename, FileKey, fromuser);
			
			pw.println("<script> alert('Accepted Successfully');</script>");
			RequestDispatcher rd = request.getRequestDispatcher("/AcceptRequest.jsp");
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
