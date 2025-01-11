package com.file;

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
import javax.servlet.http.HttpSession;

import com.filesharing.Dbconn;
import com.filesharing.EncryptDecrypt;

/**
 * Servlet implementation class updateFile
 */
@WebServlet("/updateFile")
public class updateFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateFile() {
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
		HttpSession session=request.getSession(false); 
		PrintWriter pw = response.getWriter();
		String updatedText=request.getParameter("UpdatedText");
		String pkey=request.getParameter("key");
		String FileName=(String)session.getAttribute("FileName");
		try{
		
			EncryptDecrypt ed = new EncryptDecrypt();
			String EncryptedTxt =ed.encrypt(pkey,updatedText);
			Connection con = Dbconn.conn();
			Statement st = con.createStatement();
		
			
		    st.executeUpdate("update fileinfo set data='"+EncryptedTxt+"' where filename='"+FileName+"'");
		    pw.println("<html><script>alert('File Update Successfully...');</script><body>");
			pw.println("");
			pw.println("</body></html>");
		    RequestDispatcher rd = request.getRequestDispatcher("/UserProfile.jsp");
			rd.include(request, response); 
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
