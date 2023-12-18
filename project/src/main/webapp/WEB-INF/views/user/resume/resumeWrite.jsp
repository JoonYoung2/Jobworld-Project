<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/style.css">
<title>JobWorld</title>
</head>
<body>
<%@ include file="/WEB-INF/views/user_menuBar.jsp"%>
<br>
${msg }
<br>
<form action="/resume/write" method="post" enctype="multipart/form-data">
<input type="hidden" name="loginType" value="0">
	<input type="hidden" name="userId" value="${sessionScope.user_id }"><br>
	이력서 제목 : <input type="text" name="resumeTitle" value="${resume.resumeTitle }"><br>
	<c:if test="${not empty resume.userImg }">
		<img style="padding-left: 200px; padding-top: 100px; width: 450px; height: 450px;"
			 src="/resources/upload/${sessionScope.user_id }/${resume.userImg}" alt="..." />
	</c:if>
	<input type="file" name="file"><br>
	<button type="submit">이력서 저장</button>
</form>
</body>
</html>