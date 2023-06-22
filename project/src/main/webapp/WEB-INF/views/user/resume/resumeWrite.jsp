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
<form action="resumeWrite.do" method="post" enctype="multipart/form-data">
<input type="hidden" name="login_type" value="0">
	<input type="hidden" name="user_id" value="${sessionScope.user_id }"><br>
	이력서 제목 : <input type="text" name="resume_title" value="${resume.resume_title }"><br>
	<c:if test="${not empty resume.user_img }">
		<img style="padding-left: 200px; padding-top: 100px; width: 450px; height: 450px;"
			 src="resources/upload/${sessionScope.user_id }/${resume.user_img}" alt="..." />
	</c:if>
	<input type="file" name="file"><br>
	<button type="submit">이력서 저장</button>
</form>
</body>
</html>