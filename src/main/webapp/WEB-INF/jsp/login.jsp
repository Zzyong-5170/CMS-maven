<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
	<%
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		//response.sendRedirect("/login.jsp");
	%>
</body>
</html>