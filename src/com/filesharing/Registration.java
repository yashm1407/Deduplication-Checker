package com.filesharing;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Registration")
public class Registration extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public Registration() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
		    HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = request.getParameter("txtName");
		String addres = request.getParameter("txtAddress");
		String email = request.getParameter("txtEmail");
		String gender = request.getParameter("rdoGender");
		String pwwd = request.getParameter("passPassword");
		String cntct = request.getParameter("txtContact");
			try {
				Connection con = Dbconn.conn();
				Statement st = con.createStatement();
			    st.executeUpdate("insert into register values('" + name
						+ "' , '" + addres + "'  , '" + gender + "' , '" + email
						+ "' ," + cntct + " , '" + pwwd
						+ "')");

			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			pw.println("<html><script>alert('Register Successfully');</script><body>");
			pw.println("");
			pw.println("</body></html>");
			RequestDispatcher rd = request.getRequestDispatcher("/UserLogin.jsp");
			rd.include(request, response); 

	}

}
