package com.filesharing;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Loginpage
 */
@WebServlet("/Loginpage")
public class Loginpage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Loginpage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter pw = response.getWriter();
		String Usrname = request.getParameter("username");
		
		String Passwd = request.getParameter("password");
		Connection con;
		try {
			con = Dbconn.conn();
			Statement st = con.createStatement();
			ResultSet rs;
			rs = st.executeQuery("select * from register where email='"
					+ Usrname + "' and pwd='" + Passwd + "' ");
			if (rs.next()) {
				session.setAttribute("email", rs.getString("name"));
				session.setAttribute("unam", Usrname);
				System.out.println("UserName:- " + Usrname + "Password:-"+ Passwd);
				pw.println("<html><script>alert('Login Successfully');</script><body>");
				pw.println("");
				pw.println("</body></html>");
					RequestDispatcher rd = request
						.getRequestDispatcher("/UserProfile.jsp");
				rd.include(request, response);
			}
			else
			{
				pw.println("<html><script>alert('Incorrect Username or Password.....');</script><body>");
				pw.println("");
				pw.println("</body></html>");
				RequestDispatcher rd = request
						.getRequestDispatcher("/UserLogin.jsp");
				rd.include(request, response);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
