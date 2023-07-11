<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/style.css">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/WEB-INF/views/user_menuBar.jsp"%>
<br>
${msg }
<form action="memberLogin.do" method="post">
	<input type="text" name="user_id" value="${user_id }"><br>
	<input type="password" name="user_pw" value=""><br>
	<button type="submit">로그인</button>
</form>
</body>
</html>