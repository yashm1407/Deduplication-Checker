package com.file;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;


import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.algo.HashGeneratorUtils;
import com.filesharing.Dbconn;
import com.filesharing.EncryptDecrypt;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

/**
 * Servlet implementation class fileUpload
 */
@WebServlet("/fileUpload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class fileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String p_key, pkey, encdata, decdata;
	public static String outputs="output";	

	ArrayList<String> decryptdata = new ArrayList<String>();
	public static long TimeRequired;
	public Boolean repeat;
	public int row = 0;
	public double duplication;
	public double threshold=1.0;
	public double threshold1=0.80;
	

public String filepath = "C:/Data/split/";
	public String strOriginal = null;
	// public String PriKey;
	public String contactNo, ContentType, data = null;
	public String KeyDetails11 = "Privatekey:";
	public long size, starttime, endtime, totaltime;
	private static Logger logger = Logger.getAnonymousLogger();
	Connection conn = null, conn11 = null;

	public long numsplitvalue = 1;
	ArrayList<String> strRecord = new ArrayList<String>();

	public ArrayList<String> arrServers = new ArrayList<String>();
	double TotalLoad = 0;

	public fileUpload() {
		super();
		// 
	}

	public String keyData() {
		StringBuilder ss = new StringBuilder();
		Random r = new Random();
		char ch;

		for (int i = 0; i < 5; i++) {
			ch = (char) (Math.floor(26 * r.nextDouble() + 65));
			ss.append(ch);
		}

		return ss.toString();

	}

	

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		InputStream inputStream = null;
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession(false);
		long firsttime = System.currentTimeMillis();
		decryptdata.clear();
		// PriKey = keyData();
		pkey = keyData();
		String uname = null;
		String username = (String) session.getAttribute("unam");
		int flag = 0;
		Part filePart = request.getPart("file1");
		ContentType = filePart.getContentType();
		System.out.println("ContentType=>" + ContentType);
		String fileName = getFileName(filePart);
		session.setAttribute("FILEName", fileName);

		if (filePart != null) {
			// System.out.println("In file Part FileName=>" +
			// filePart.getName());
			System.out.println("Size:-" + filePart.getSize());
			// System.out.println(filePart.getContentType());
			System.out.print("FileName:-" + fileName);
			System.out.println("UserName:-" + username);

			inputStream = filePart.getInputStream();

		}
		size = filePart.getSize();
		try {
			Connection conn = Dbconn.conn();
			EncryptDecrypt ed = new EncryptDecrypt();

			Statement stavailable = conn.createStatement();
			ResultSet rsavailable = stavailable
					.executeQuery("select * from fileinfo where user='"
							+ username + "' and filename='" + fileName + "'");
			if (rsavailable.next()) {
				PrintWriter out1 = null;
				response.setContentType("text/html;charset=UTF-8");
				out1 = response.getWriter();
				out1.println("<html><script>alert('File Already Exists');</script><body>");
				out1.println("");
				out1.println("</body></html>");
			} else {
				if (ContentType.equals("text/plain")) {
					strOriginal = getStringFromInputStream(inputStream);
					System.out.println("Origional:-" + strOriginal);
					writeFile(fileName,strOriginal);

				} else if (ContentType.equals("application/pdf")) {
					strOriginal = getpdfFromInputStream(inputStream);
					System.out.println("Origional:-" + strOriginal);

				} else if (ContentType.equals("image/jpeg")) {

				} else if (ContentType.equals("text/html")||(ContentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))) {
					strOriginal = getdocFromInputStream(inputStream);
					System.out.println("Origional:-" + strOriginal);
				}

				String sha256=HashGeneratorUtils.generateSHA256(strOriginal);
				ResultSet rs = stavailable.executeQuery("select * from tblsha where Data_Sha='" + sha256 + "' ");
				
				
					while (rs.next()) {
						String Email_ID=rs.getString("Email_ID");
						if (true) {
							flag=1;
							ResultSet rs1 = stavailable.executeQuery("select * from fileinfo where user='" + Email_ID + "' ");
							if(rs1.next())
							{
							uname=rs1.getString("user");
							System.out.println("UserName:-"
									+ rs1.getString("user")+"FileName:="+rs1.getString("filename"));
							} 
							break;
						} 
					}
								if (flag == 0) {
									
								Statement stFileInfo = conn.createStatement();

								String EncryptedFile = ed.encrypt(pkey,
										strOriginal);
								long Lasttime = System.currentTimeMillis();
								totaltime = Lasttime - firsttime;
								stFileInfo
										.executeUpdate("insert into fileinfo values('"
												+ username+ "','"+ fileName	+ "', '"
												+ EncryptedFile	+ "','"	+ pkey+ "','"
												+ size+ "','"+ totaltime + "','"
												+ ContentType + "')");
								Statement st1 = conn.createStatement();
								 st1.executeUpdate("insert into tblsha values('"
										+ username + "' ,'"
										+ fileName + "','"
										+ sha256 + "')");
								pw.println("<html><script>alert('File Upload Successfully...');</script><body>");
								pw.println("");
								pw.println("</body></html>");
									}
								if(flag==1)
								{
									pw.println("<html><script>alert('Data Duplication...Please Contact UserName:="+uname+"');</script><body>");
									pw.println("");
									pw.println("</body></html>");
								}
				}
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		} finally {
			if (conn != null) {

				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}

		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/upload.jsp");
		rd.include(request, response);
	}

	public void writeFile(String fileName, String fileData) {
		try {
			File file = new File(filepath + fileName);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(fileData);
			bw.close();
			System.out.println("Done");
		} catch (Exception w) {
		}

	}

	

	public void readWrite(RandomAccessFile raf, BufferedOutputStream bw,
			long numBytes, int serverId, String ServerIp, String FileName)
			throws IOException {

		System.out.println("----1111111111111111111111111111111--- ");

		byte[] buf = new byte[(int) numBytes];
		int val = raf.read(buf);
		if (val != -1) {
			bw.write(buf);
		}
		
	}

	private String getpdfFromInputStream(InputStream is) throws IOException {

		PdfReader pdfReader = new PdfReader(is);
		// Get the number of pages in pdf.
		int pages = pdfReader.getNumberOfPages();
		// Iterate the pdf through pages.
		for (int i = 1; i <= pages; i++) {
			// Extract the page content using PdfTextExtractor.
			data= PdfTextExtractor.getTextFromPage(pdfReader, i);
			// Print the page content on console.

		}
		return data;
	}

	private String getdocFromInputStream(InputStream is) throws IOException {

		try {
			// It is used to create MS-Word Document with .docx file format.
			XWPFDocument docx = new XWPFDocument(is);
			// It is a basic parser class used to extract the simple text from a
			// Word document.
			XWPFWordExtractor wx = new XWPFWordExtractor(docx);
			data = wx.getText();
			//System.out.println("text = " + data);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	String getFileName(Part filePart) {

		String partHeader = filePart.getHeader("content-disposition");
		logger.info("Part Header = " + partHeader);

		for (String cd : filePart.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
				return fileName.substring(fileName.lastIndexOf('/') + 1)
						.substring(fileName.lastIndexOf('\\') + 1);
				// return cd.substring(cd.indexOf('=') + 1).trim().replace("\"",
				// "") ;
			}

		}

		return null;

	}

	private String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				strRecord.add(line);
				sb.append(line);
				sb.append("\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}



}
