<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML>
<html>

<head>
<title>Login</title>
<!--meta tags -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords"
	content="Track Login Form Responsive Widget,Login form widgets, Sign up Web forms , Login signup Responsive web form,Flat Pricing table,Flat Drop downs,Registration Forms,News letter Forms,Elements" />
<script type="application/x-javascript">
	
		addEventListener("load", function () {
			setTimeout(hideURLbar, 0);
		}, false);

		function hideURLbar() {
			window.scrollTo(0, 1);
		}
	
</script>
<!-- //Meta Tags -->
<!-- Font-Awesome-CSS -->
<link href="css/font-awesome.css" rel="stylesheet">
<!--// Font-Awesome-CSS -->
<!-- Required Css -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<!--// Required Css -->
<!--fonts-->
<link href="//fonts.googleapis.com/css?family=Cinzel:400,700,900"
	rel="stylesheet">
<!--//fonts-->
</head>

<body>
	<!--background
	<h1>Track Login Form</h1>
	<!-- Main-Content -->
	<div class="main-w3layouts-form">
		<h2>Login to your account</h2>
		<!-- main-w3layouts-form -->
		<form action="Control_Login?action=account" method="post">
			<div class="fields-w3-agileits">
				<span class="fa fa-user" aria-hidden="true"></span> <input
					type="text" name="Username" required=""
					value="${requestScope.user_reply }" placeholder="Username" /><br>
			
			</div>
			<div class="fields-w3-agileits">
				<span class="fa fa-key" aria-hidden="true"></span> <input
					type="password" name="Password" required="" placeholder="Password" />
			</div>
			<div class="fields-w3-agileits">
				<a style="color: red">${requestScope.incorrect }</a>
			</div>	
			<div class="remember-section-wthree">
				<ul>
					<li>
						<!--<label class="anim">
							<input type="checkbox" class="checkbox">
							<span> Remember me ?</span> 
						</label>-->
					</li>
					<li><a href="#">Forgot password?</a></li>
				</ul>
				<div class="clear"></div>
			</div>
			<input type="submit" value="Login" />
		</form>
		<!--// main-w3layouts-form -->

		<!-- Social icons -->
		<div class="footer_grid-w3ls">
			<h5 class="sub-hdg-w3l">or connect to social profiles</h5>
			<ul class="social-icons-agileits-w3layouts">
				<li><a href="http://www.facebook.com/dialog/oauth?client_id=1057645477779580&redirect_uri=http://localhost:8080/Project_NMCNPM/login-with-facebook" class="fa fa-facebook"></a></li>
				<li><a href="#" class="fa fa-twitter"></a></li>
				<li><a href="#" class="fa fa-google-plus"></a></li>
			</ul>
		</div>
		<!--// Social icons -->
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
</body>

</html>