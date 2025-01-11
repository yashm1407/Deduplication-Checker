package com.file;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.filesharing.Dbconn;

/**
 * Servlet implementation class deletefile
 */
@WebServlet("/deletefile")
public class deletefile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deletefile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter pw = response.getWriter();
		 String username=(String)session.getAttribute("unam");

		System.out.print("UserName:-" + username);
		String filename = request.getParameter("filenamedb");

		System.out.print("\nFileName:-" + filename);
		try {

			Connection conn = Dbconn.conn(); // connection to the database

			// queries the database
			String sql = "Delete FROM fileinfo WHERE user = ? and filename =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, filename);
			 statement.executeUpdate();
			 pw.println("<html><script>alert('File Delete Successfully...');</script><body>");
				pw.println("");
				pw.println("</body></html>");
			RequestDispatcher rd = request
					.getRequestDispatcher("/DownloadFile.jsp");

			rd.include(request, response);
		} catch (Exception e) {
			System.out.print(" " + e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
