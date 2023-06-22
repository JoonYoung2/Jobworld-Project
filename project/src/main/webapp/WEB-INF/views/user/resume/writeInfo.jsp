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
	<c:when test="${empty msg }">
		작성한 이력서가 없습니다.<br>
		<a href="resumeWrite">이력서 쓰기</a>
	</c:when>
	<c:otherwise>
		<img style="padding-left: 200px; padding-top: 100px; width: 450px; height: 450px;"
			 src="resources/upload/${sessionScope.user_id }/${resume.user_img}" alt="..." />
		<input type="text" name="resume_title" value="${resume.resume_title }" readonly="readonly"><br>
		<a href="resumeUpdate?resume_id=${resume.resume_id }">이력서 수정</a>
	</c:otherwise>
</c:choose>

</body>
</html>