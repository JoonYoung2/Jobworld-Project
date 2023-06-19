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
<c:choose>
	<c:when test="${empty resume.user_id }">
		<a href="resumeWrite">이력서 쓰기</a>
	</c:when>
	<c:otherwise>
		<a href="resumeUpdate">이력서 수정</a>
	</c:otherwise>
</c:choose>

</body>
</html>