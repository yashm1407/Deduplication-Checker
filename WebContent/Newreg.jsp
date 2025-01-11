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
<meta name="keywords"
	content="press blog theme, free css template, blogger, templatemo" />
<meta name="description"
	content="Press Theme - Free Blog Template for everyone" />
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

			<div id="site_title" style="width: 1000px">
				<h1 ><jsp:include page="titlepage.jsp"></jsp:include></h1>
			</div>
			<!-- end of site_title -->

			<div id="templatemo_menu"
				style="font-size: 5%; width: 1150px; align: center">
				<ul>
					<li><a href="UserLogin.jsp" class="classname">Login</a></li>
					<li><a href="Newreg.jsp" class="classname">Register</a></li>

				</ul>
			</div>
			<!-- end of templatemo_menu -->

		</div>
	</div>
	<!-- end of templatemo_header -->

	<div id="templatemo_middle_wrapper">
		<div id="templatemo_middle" align="center">

			<div id="templatemo_content" align="center" style="width: 100%">




				<div class="content_box_wrapper" align="center">
					<div class="content_box">

						<div class="post_section" align="center">

							<h2>
								<a href="blog_post.html"><font size="4" color="blue"></font>
									<font size="4" color="red"> </font></a>
							</h2>



							<form name="frmReg" action="Registration" method="post">

								<table >
									<tr>
										<td class="lable" style="font-size: 15px">User Name:</td>
										<td><input type="text" required name="txtName"
											style="height: 25px; width: 250px;" /></td>
									</tr>
									<tr>
										<td class="lable">Address:</td>
										<td><input type="text" name="txtAddress"
											style="height: 70px; width: 250px;" required /></td>
									</tr>
									<tr>
										<td class="lable">Gender:</td>

										<td align=left><input type="radio" name="rdoGender"
											value="Male" id="male" required />Male <input type="radio"
											name="rdoGender" value="FeMale" id="female" required />Female</td>
									</tr>
									<tr>
										<td class="lable">Email:</td>
										<td><input type="text" required
											pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
											name="txtEmail" style="height: 25px; width: 250px;" /></td>
									</tr>
									<tr>
										<td class="lable">Contact No:</td>
										<td><input type="text" required
											pattern="[7-9]{1}[0-9]{9}" maxlength="10"
											title="Not a valid Phone Number" name="txtContact"
											style="height: 25px; width: 250px;" /></td>
									</tr>
									<tr>
										<td class="lable">Password:</td>
										<td><input type="password" required name="passPassword"
											style="width: 250px; height: 25px;" /></td>
									</tr>
									<tr>

										<td colspan="2" align="center"><input type="submit"
											value="Save Details" style="height: 30px;" /></td>
									</tr>

								</table>

							</form>

						</div>

					</div>
					<!-- end of content_box -->
				</div>
				<!-- end of content_box_wrapper -->

			</div>
			<!-- end of templatemo_content -->



			<div class="cleaner"></div>
		</div>
		<!-- end of templatemo_content -->

		<div id="templatemo_footer">



			<div class="col_w210"></div>

			<div class="col_w210"></div>

			<div class="col_w210 col_last"></div>


			<div class="cleaner"></div>
		</div>
		<!-- end of footer -->

		<div id="templatemo_copyright"></div>

	</div>
	<!-- end of templatemo_content_wrapper -->

	<div align=center></div>
</body>
</html>
