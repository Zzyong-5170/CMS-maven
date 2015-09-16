<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%-- 获取项目的根路径 --%>
<c:set value="${pageContext.request.contextPath}" var="aPath"></c:set>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<title>Free HTML5 Bootstrap Admin Template</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
		<meta name="author" content="Muhammad Usman">
		<!-- The styles -->
		<link   rel="stylesheet"  type="text/css" href="/plugin/resources/plugins/css/bootstrap-cerulean.min.css">
		<link href="/plugin/resources/plugins/css/charisma-app.css"   type="text/css" rel="stylesheet">
		<link href='/plugin/resources/plugins/bower_components/fullcalendar/dist/fullcalendar.css'   type="text/css" rel='stylesheet'>
		<link href='/plugin/resources/plugins/bower_components/fullcalendar/dist/fullcalendar.print.css'  type="text/css"  rel='stylesheet' media='print'>
		<link href='/plugin/resources/plugins/bower_components/chosen/chosen.min.css'  type="text/css" rel='stylesheet'>
		<link href='/plugin/resources/plugins/bower_components/colorbox/example3/colorbox.css'  type="text/css" rel='stylesheet'>
		<link href='/plugin/resources/plugins/bower_components/responsive-tables/responsive-tables.css'   type="text/css" rel='stylesheet'>
		<link href='/plugin/resources/plugins/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css'  type="text/css" rel='stylesheet'>
		<link href='/plugin/resources/plugins/css/jquery.noty.css'  type="text/css" rel='stylesheet'>
		<link href='/plugin/resources/plugins/css/noty_theme_default.css'  type="text/css" rel='stylesheet'>
		<link href='/plugin/resources/plugins/css/elfinder.min.css'  type="text/css" rel='stylesheet'>
		<link href='/plugin/resources/plugins/css/elfinder.theme.css'  type="text/css" rel='stylesheet'>
		<link href='/plugin/resources/plugins/css/jquery.iphone.toggle.css'  type="text/css" rel='stylesheet'>
		<link href='/plugin/resources/plugins/css/uploadify.css'   type="text/css" rel='stylesheet'>
		<link href='/plugin/resources/plugins/css/animate.min.css'  type="text/css"  rel='stylesheet'>
		
		<!-- jQuery -->
		<script src="/plugin/resources/plugins/bower_components/jquery/jquery.min.js" type="text/javascript"></script>
		
		<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
		    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		    <![endif]-->
		
		<!-- The fav icon -->
		<link rel="shortcut icon" href="/plugin/resources/plugins/img/favicon.ico">
	
	</head>

<body>
	<div class="ch-container">
		<div class="row">

			<div class="row">
				<div class="col-md-12 center login-header">
					<h2>Welcome to Charisma</h2>
				</div>
				<!--/span-->
			</div>
			<!--/row-->

			<div class="row">
				<div class="well col-md-5 center login-box">
					<div class="alert alert-info">
						Please login with your Username and Password.
						<p>${nameError}</p>
						<p>${passwordError}</p>
						<p>${dbError}</p>
					</div>
					<form class="form-horizontal" action="${aPath}sysLogin/login" method="post">
						<fieldset>
							<div class="input-group input-group-lg">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user red"></i></span> <input type="text"
									name="username" class="form-control" placeholder="Username">
							</div>
							<div class="clearfix"></div>
							<br>

							<div class="input-group input-group-lg">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock red"></i></span> <input
									name="password" type="password" class="form-control" placeholder="Password">
							</div>
							<div class="clearfix"></div>

							<div class="input-prepend">
								<label class="remember" for="remember"><input
									type="checkbox" id="remember"> Remember me</label>
							</div>
							<div class="clearfix"></div>

							<p class="center col-md-5">
								<button type="submit" class="btn btn-primary">Login</button>
							</p>
						</fieldset>
					</form>
				</div>
				<!--/span-->
			</div>
			<!--/row-->
		</div>
		<!--/fluid-row-->

	</div>
	<!--/.fluid-container-->

	<!-- external javascript -->
	<script src="/plugin/resources/plugins/bower_components/bootstrap/dist/js/bootstrap.min.js"  type="text/javascript"></script>
	<!-- library for cookie management -->
	<script src="/plugin/resources/plugins/js/jquery.cookie.js"  type="text/javascript"></script>
	<!-- calender plugin -->
	<script src='/plugin/resources/plugins/bower_components/moment/min/moment.min.js'  type="text/javascript"></script>
	<script src='/plugin/resources/plugins/bower_components/fullcalendar/dist/fullcalendar.min.js'  type="text/javascript"></script>
	<!-- data table plugin -->
	<script src='/plugin/resources/plugins/js/jquery.dataTables.min.js'  type="text/javascript"></script>
	<!-- select or dropdown enhancer -->
	<script src="/plugin/resources/plugins/bower_components/chosen/chosen.jquery.min.js"  type="text/javascript"></script>
	<!-- plugin for gallery image view -->
	<script src="/plugin/resources/plugins/bower_components/colorbox/jquery.colorbox-min.js"  type="text/javascript"></script>
	<!-- notification plugin -->
	<script src="/plugin/resources/plugins/js/jquery.noty.js"  type="text/javascript"></script>
	<!-- library for making tables responsive -->
	<script src="/plugin/resources/plugins/bower_components/responsive-tables/responsive-tables.js"  type="text/javascript"></script>
	<!-- tour plugin -->
	<script src="/plugin/resources/plugins/bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"  type="text/javascript"></script>
	<!-- star rating plugin -->
	<script src="/plugin/resources/plugins/js/jquery.raty.min.js"  type="text/javascript"></script>
	<!-- for iOS style toggle switch -->
	<script src="/plugin/resources/plugins/js/jquery.iphone.toggle.js"  type="text/javascript"></script>
	<!-- autogrowing textarea plugin -->
	<script src="/plugin/resources/plugins/js/jquery.autogrow-textarea.js"  type="text/javascript"></script>
	<!-- multiple file upload plugin -->
	<script src="/plugin/resources/plugins/js/jquery.uploadify-3.1.min.js"  type="text/javascript"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="/plugin/resources/plugins/js/jquery.history.js"  type="text/javascript"></script>
	<!-- application script for Charisma demo -->
	<script src="/plugin/resources/plugins/js/charisma.js"  type="text/javascript"></script>
</body>
</html>
