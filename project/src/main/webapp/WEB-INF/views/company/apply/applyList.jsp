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
	<%@ include file="/WEB-INF/views/company_menuBar.jsp"%>
	<br>
	<c:choose>
		<c:when test="${empty list }">
			지원한 회원 정보가 없습니다.<br>
		</c:when>
		<c:otherwise>
			<c:forEach var="resume" items="${list }">
				제목 : ${resume.resume_title }
			<br>
			<br>
			</c:forEach>
		</c:otherwise>
	</c:choose>

</body>
</html>