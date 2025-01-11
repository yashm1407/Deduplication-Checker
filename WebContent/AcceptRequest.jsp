<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.Format"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.filesharing.Dbconn"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>End-to-End Information Security</title>
<meta name="keywords" content="press blog theme, free css template, blogger, templatemo" />
<meta name="description" content="Press Theme - Free Blog Template for everyone" />
<link href="css/templatemo_style.css" rel="stylesheet" type="text/css" />

<link href="css/svwp_style.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="js/jquery.slideViewerPro.1.0.js" type="text/javascript"></script>

<!-- Optional plugins  -->
<script src="js/jquery.timers.js" type="text/javascript"></script>


</head>
<body>

<div id="templatemo_header_wrapper">
	<div id="templatemo_header">
    
    	<div id="site_title" style="width:1000px">
          <h1><jsp:include page="titlepage.jsp"></jsp:include></h1>
      	</div> <!-- end of site_title -->
        
        <div id="templatemo_menu" style="font-size:5%;width:1150px;align:center" >
           <ul>
					<jsp:include page="menu.jsp"></jsp:include>
				</ul> 	
        </div> <!-- end of templatemo_menu -->
    
    </div>
</div> <!-- end of templatemo_header -->

<div id="templatemo_middle_wrapper">
	<div id="templatemo_middle" align="center">
    
        <div id="templatemo_content" align="center" style="width: 100%">
        
            
            
         
            <div class="content_box_wrapper" align="center">
            	<div class="content_box">
                
                	<div class="post_section" align="center">
            
                        <h2><a href="blog_post.html"><font size="4" color="blue">Welcome</font>
									<font size="4" color="red"> 
									 <%= session.getAttribute("unam")%> 
 	 </font></a></h2>
        
                 <form enctype="multipart/form-data"  role="form" action="Download">
			<div class="form-group">
			
		   
		   <% 
        	Connection connection = Dbconn.conn();
            Statement statement = connection.createStatement();
					Statement stat = connection.createStatement();
          String username=(String)session.getAttribute("unam");
          ResultSet rs =stat.executeQuery("select * from requestdata where touser='"+username+"'") ; 
          if(!rs.next()){
        	  
          }
         
          
         
          else{
        	  %>
        	  <table width=100%>
	       <tr>
			<td class="col-md-2"><label for="txt_Uname"><h3>From User Name</h3> </label></td>
			<td class="col-md-2"><label for="txt_Uname"><h3>To User Name</h3> </label></td>
			<td class="col-md-2"><label for="txt_Uname"><h3>File Name</h3> </label></td>
			<td class="col-md-2"><label for="txt_Uname"><h3>Accept</h3> </label></td>
			<td class="col-md-2"><label for="txt_Uname"><h3>Reject</h3> </label></td>
			<td class="col-md-2"><label for="txt_Uname"><h3>Status</h3> </label></td>
			
		   </tr>
        	  <%
            ResultSet resultset =statement.executeQuery("select * from requestdata where touser='"+username+"'") ; 
			while(resultset.next())
             {
       		 %>	
       		 <tr>
           
              <td> <%= resultset.getString("fromuser") %> </td>
                <td> <%= resultset.getString("touser") %> </td>
               <td> <%= resultset.getString("filename") %> </td>
               <td> <a href="requestaccept?usernamedb=<%=resultset.getString("fromuser")%>,<%=resultset.getString("touser")%>,<%=resultset.getString("filename")%>"><input type="button" name="accept" value="Accept" class="btn btn-info"></a></td>
               <td> <a href="deleterequest?usernamedb=<%=resultset.getString("fromuser")%>,<%=resultset.getString("touser")%>,<%=resultset.getString("filename")%>" ><input type="button" name="Delete" value="Delete" class="btn btn-danger" ></a></td>
           <td> <%= resultset.getString("status") %> </td>
           </tr>
       		  <% 
             } 
          }
              %>
 
			</table>
			</div>
			</form> 			
				
                                          
                    </div>
                
                </div> <!-- end of content_box -->
            </div> <!-- end of content_box_wrapper -->
        
        </div> <!-- end of templatemo_content -->
      
        
        <div class="cleaner"></div>    
    </div> <!-- end of templatemo_content -->
    
    <div id="templatemo_footer">
    
       
        
        <div class="col_w210">
             
 
        </div>
        
        <div class="col_w210">
            
        </div>
        
        <div class="col_w210 col_last">
           
        </div>
        
        
        <div class="cleaner"></div>
    </div> <!-- end of footer -->
    
    <div id="templatemo_copyright">
          </div>
    
</div> <!-- end of templatemo_content_wrapper -->

<div align=center></div></body>
</html>
