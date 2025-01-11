<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.Format"%>
<%@page import="java.sql.ResultSet"%><%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.*"%>
<%@page import="com.filesharing.Dbconn"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.filesharing.Dbconn"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.filesharing.*"%>
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
<script type="text/javascript">
	function trim(s) {
		return s.replace(/^s*/, "").replace(/s*$/, "");
	}
	function validate() {
		var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		if (trim(document.form1.username.value) == "") {
			alert("Please Enter User Name.....");
			document.form1.username.focus();
			document.form1.username.value == "";
			return false;
		}

		else if (trim(document.form1.password.value) == "") {
			alert("Please Enter Password.......");
			document.form1.password.focus();
			document.form1.password.value == "";
			return false;
		}

	}

	function backButtonOverride() {
		setTimeout("backButtonOverrideBody()", 1);
	}

	function backButtonOverrideBody() {
		try {
			history.forward();
		} catch (e) {
		}
		setTimeout("backButtonOverrideBody()", 50);
	}
</script>

</head>
<body>

<div id="templatemo_header_wrapper">
	<div id="templatemo_header">
    
    	<div id="site_title" style="width:1000px">
          <h1 ><jsp:include page="titlepage.jsp"></jsp:include></h1>
      	</div> <!-- end of site_title -->
        
        <div id="templatemo_menu" style="font-size:5%;width:1150px;align:center" >
            <ul>
              	<li ><a href="UserLogin.jsp" class="classname">Login</a></li>
              		<li><a href="Newreg.jsp" class="classname">Register</a></li>
							
							</ul>    	
        </div> <!-- end of templatemo_menu -->
    
    </div>
</div> <!-- end of templatemo_header -->

<div id="templatemo_middle_wrapper">
	<div id="templatemo_middle" align="center">
    	<center>
        <div id="templatemo_content" align="center" style="width: 100%">
        
            
            
         
            <div class="content_box_wrapper" align="center">
            	<div class="content_box">
                
                	<div class="post_section" align="center">
            
                        <h2><a href="blog_post.html"><font size="4" color="blue"></font>
									<font size="4" color="red"> 
								 	 </font></a></h2>
  
 
 
 
 
 	<form name="form1" 
						action=Loginpage method="post">
						<table
							style="font-family: monotype corsiva; font-size: 20px; font-weight: bold"
							align="center" background="images/log.jpg" height="250"
							width="145" cellspacing="10" cellpadding="10" colspan="8">

							<tr>

								<td>Email_ID</td>
								<td width="145" align="right"><input name="username"
									id="userName" type="text" style="width: 230px;" value=''
									required  /></td>
							</tr>
							<tr>
								<td>Password</td>
								<td><input style="width:230px" type="password" name="password" required 
									value="" /></td>
							</tr>
							<%
								String reg = (String) session.getAttribute("reg");
								if (reg == null) {
								} else {
							%><tr>
								<td colspan="2"><%=reg%></td>
							</tr>
							<%
								}
							%>
							<tr>
								<td align="center" colspan="2"><input type="submit"
									value="Login"
									style="font-family: monotype corsiva; font-size: 20px; font-weight: bold"></td>
							</tr>

						</table>

					</form>
 
  
                    </div>
                
                </div> <!-- end of content_box -->
            </div> <!-- end of content_box_wrapper -->
        
        </div> <!-- end of templatemo_content -->
        </center>    
		
        
        <div class="cleaner">
        
        </div>    
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
