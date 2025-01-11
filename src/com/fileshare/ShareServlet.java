package com.fileshare;

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
import javax.servlet.http.HttpSession;

import com.filesharing.Dbconn;

/**
 * Servlet implementation class ShareServlet
 */
@WebServlet("/ShareServlet")
public class ShareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareServlet() {
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
		PrintWriter pw = response.getWriter();
		String[] username = request.getParameterValues("Checkbox");
		String filename =request.getParameter("filename");
		HttpSession session = request.getSession(false);
		String name=(String)session.getAttribute("unam");
		String Checked[] = username;
		int i = 0;
		try {
			for (String s : username) {
				Checked[i] = s;
				System.out.println("CheckBox Values" + s);
				i++;

				Connection con = Dbconn.conn();
				Statement st = con.createStatement();
				try {
					
					String email="";
					String FileKey = "";
					String query1 = "select PrivateKey from fileinfo where filename='"+ filename + "'";
					ResultSet rs1 = st.executeQuery(query1);

					while (rs1.next()) {
						FileKey = rs1.getString(1);

						// SendMail.mailSend(filename, "", email);
					}
					String query = "select email from register where name='"+ s + "'";
					ResultSet rs = st.executeQuery(query);
					while (rs.next()) {
						email = rs.getString(1);
						// String
						// filename=String.valueOf(session.getAttribute("FileName"));
						
					}
					System.out.println("Mail Send " + filename + " to "+ email +" key =" + FileKey);

					SendMail.mailSend(filename, FileKey, email);
					pw.println("<html><script>alert('Mail Send Success');</script><body>");
					pw.println("");
					pw.println("</body></html>");
					System.out.println("");

				} catch (Exception e) {
				}

				st.executeUpdate("insert into access values('"+ name + "' , '"+ filename + "' , '" + s + "')");				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		pw.println("<html><script>alert('File Share Success');</script><body>");
		pw.println("");
		pw.println("</body></html>");
		RequestDispatcher rd = request.getRequestDispatcher("/ShareFileName.jsp");
		rd.include(request, response);
	}

}
