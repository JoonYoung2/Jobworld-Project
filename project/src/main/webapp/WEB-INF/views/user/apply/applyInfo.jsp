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
	<br> 
	${resume.resume_title }
	<br>
	<img style="padding-left: 200px; padding-top: 100px; width: 450px; height: 450px;"
		src="resources/upload/${sessionScope.user_id }/${resume.user_img}" alt="..." />
	<br>
	<form action="apply.do" method="post">
		<input type="hidden" name="resume_id" value="${resume.resume_id }">
		<input type="hidden" name="recruit_id" value="${recruit_id }">
		<button type="submit">지원하기</button>
	</form>
	|
	<a href="resumeUpdate?resume_id=${resume.resume_id }">이력서 수정하기</a>
</body>
</html>