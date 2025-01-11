package com.accessfile;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.filesharing.Dbconn;

/**
 * Servlet implementation class sendrequest
 */
@WebServlet("/sendrequest")
public class sendrequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sendrequest() {
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
		String[] filename = request.getParameterValues("Checkbox1");
		HttpSession session = request.getSession(false);
		String name=(String)session.getAttribute("unam");
	int i=0;
		Date dd=new Date();
		try {
			for (String s : username) {
				String filenames=filename[i];
				System.out.println(filenames+"CheckBox Values" + s);
			
				Connection con = Dbconn.conn();
				Statement st = con.createStatement();
				Statement stavailable = con.createStatement();
				ResultSet rsavailable = stavailable
						.executeQuery("select * from requestdata where touser='"+s+"' and fromuser='"+name+"' ");
				if(rsavailable.next()) {
					PrintWriter out1 = null;
						response.setContentType("text/html;charset=UTF-8");
						out1 = response.getWriter();
						out1.println("<html><script>alert('Request Already Send');</script><body>");
						out1.println("");
						out1.println("</body></html>");
					}
					
				else
				{
				
			st.executeUpdate("insert into requestdata values('"+ name + "' , '"+ s + "' , 'Send','"+dd+"','"+filenames+"')");				
			pw.println("<html><script>alert('Send Request Success');</script><body>");
			pw.println("");
			pw.println("</body></html>");
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/SendRequest.jsp");
		rd.include(request, response);
	}

}
