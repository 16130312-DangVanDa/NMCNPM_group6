<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>index</title>
</head>
<body>
<%
String id = (String) request.getAttribute("id");
String name =(String) request.getAttribute("name");
if((id)!=null){
%>

<link href="css/font-awesome.css" rel="stylesheet">
<!--// Font-Awesome-CSS -->
<!-- Required Css -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<!--// Required Css -->
<!--fonts-->
<link href="//fonts.googleapis.com/css?family=Cinzel:400,700,900"
	rel="stylesheet">
	<div class="main-w3layouts-form">
		<h2>Loging by Facebook success !</h2>
		<!-- main-w3layouts-form -->
		<form action="" method="post">
			<h5 style="color:yellow">Facebook ID : <%=id%></h5>
			<h5 style="color:yellow">Facebook Fullname : <%=name%></h5>
						<a href="login.jsp">Processing to login page !</a>
			
		</form>

	
	</div>
	<!--// Main-Content-->
	<!-- copyright -->
	<div class="copyright-w3-agile">
		<p>
			&copy; 2018 Track Login Form. All Rights Reserved | Design by <a
				href="http://w3layouts.com/" target="_blank">W3layouts</a>
		</p>
	</div>
	<!--// copyright -->
	<!--//background-->

<%} else{%>
	<div class="main-w3layouts-form">
		<h2> Please login to system !</h2>
		<!-- main-w3layouts-form -->
		<form action="" method="post">
			<a href="login.jsp">Processing to login page !</a>
		</form>
	</div>
	<%} %>
</body>
</html>