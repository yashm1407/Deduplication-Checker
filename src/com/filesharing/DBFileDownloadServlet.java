package com.filesharing;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.sql.Blob;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/DBFileDownloadServlet")
public class DBFileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String origional = "";

	PrintWriter out = null;

	public DBFileDownloadServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
//		PrintWriter prw;// = response.getWriter();
		ServletOutputStream os = response.getOutputStream();
		String keyfile = request.getParameter("Keyfile");
		System.out.println("Key:-" + keyfile);
		String strFileName = (String) session.getAttribute("strFileName");
		System.out.println("FileName:-" + strFileName);
		String fileType = strFileName.substring(strFileName.indexOf(".") + 1,
				strFileName.length());

		Connection conn;
		try {
			conn = Dbconn.conn();
			Statement st = conn.createStatement();

			ResultSet rs = st
					.executeQuery("select * from fileinfo where filename='"
							+ strFileName + "' ");
			if (rs.next()) {
				if (keyfile.equals(rs.getString("PrivateKey"))) {

					try {
						String sql = "SELECT * FROM fileinfo WHERE filename = ?";
						PreparedStatement statement = conn
								.prepareStatement(sql);
						statement.setString(1, strFileName);
						ResultSet result = statement.executeQuery();

						if (result.next()) {

							if (fileType.trim().equalsIgnoreCase("txt")) {

								// Statement st=conn.createStatement();
								ResultSet rsDb = st
										.executeQuery("select * from fileinfo where filename='"
												+ strFileName + "'");
								EncryptDecrypt ed = new EncryptDecrypt();
								while (rsDb.next()) {
									origional += ed.decrypt(
											rsDb.getString("PrivateKey"),
											rsDb.getString("data"));
									System.out.println("Decrypted Text "
											+ origional);
								}
								response.setHeader("Content-Type",
										"application/octet-stream");
								response.setContentType("text/plain");
								response.setHeader("Content-Disposition",
										"attachment; filename=\"" + strFileName
												+ "\"");
								ServletOutputStream op = response
										.getOutputStream();
								op.println(origional);
								System.out.println("Decrypted Text "
										+ origional);

							} else if (fileType.trim().equalsIgnoreCase("docx")) {
								// Statement st=conn.createStatement();
								ResultSet rsDb = st
										.executeQuery("select * from fileinfo  where filename='"
												+ strFileName + "'");
								EncryptDecrypt ed = new EncryptDecrypt();
								while (rsDb.next()) {
									origional += ed.decrypt(
											rsDb.getString("PrivateKey"),
											rsDb.getString("data"));
									System.out.println("Decrypted Text "
											+ origional);
								}
								response.setContentType("text/html");
								response.setHeader("Content-Disposition",
										"attachment; filename=\"" + strFileName
												+ "\"");
								XWPFDocument docx = new XWPFDocument();
								XWPFParagraph paragraphx = docx
										.createParagraph();
								XWPFRun runx = paragraphx.createRun();
								runx.setText(origional);// read line by line
														// file Input to Output
								docx.write(os);// write line by line

							} else if (fileType.trim().equalsIgnoreCase("pdf")) {
								try {
									conn = Dbconn.conn();
									ResultSet rsDb = st
											.executeQuery("select * from fileinfo where filename='"
													+ strFileName + "'");
									EncryptDecrypt ed = new EncryptDecrypt();
									while (rsDb.next()) {
										origional += ed.decrypt(rsDb.getString("PrivateKey"),rsDb.getString("data"));
										System.out.println("Decrypted Text "+ origional);
									}
									response.setContentType("application/pdf");
									response.setHeader("Content-Disposition","attachment; filename=\""+ strFileName + "\"");
									Document document = new Document();
									PdfWriter.getInstance(document, os);
									document.open();
									document.add(new Paragraph(origional));
									document.close();
								} catch (DocumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {

								response.setHeader("Content-Type",
										"application/octet-stream");
								// response.setContentType("audio/3gpp");
								response.setHeader("Content-Disposition",
										"attachment; filename=\"" + strFileName
												+ "\"");
								byte[] imageData = null;
								String sql1 = "select * from fileinfo where filename=?";
								PreparedStatement statement1 = conn
										.prepareStatement(sql1);
								statement1.setString(1, strFileName);
								ResultSet result1 = statement1.executeQuery();

								if (result1.next()) {
									Blob blob = result1.getBlob("data");
									imageData = blob.getBytes(1,
											(int) blob.length());
									InputStream inputStream = blob
											.getBinaryStream();
									OutputStream o = response.getOutputStream();
									o.write(imageData);
									inputStream.close();
									// outputStream.close();

									System.out.println("File saved");

								}
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					
					response.sendRedirect("KeyFile.jsp?key=1");

					
				}
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
