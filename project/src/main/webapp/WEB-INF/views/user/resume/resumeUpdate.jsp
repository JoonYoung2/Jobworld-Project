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
	<img style="padding-left: 200px; padding-top: 100px; width: 450px; height: 450px;"
			 src="resources/upload/${sessionScope.user_id }/${resume.user_img}" alt="..." />
			 <br>
			 
			 <form action="imgUpdate.do" method="post" enctype="multipart/form-data">
			 <input type="hidden" name="resume_id" value="${resume.resume_id }">
			 <input type="hidden" name="user_id" value="${resume.user_id }">
			 	<input type="file" name="file">
			 	<button type="submit">사진 수정하기</button>
			 </form>
			 
			 <form action="resumeUpdate.do" method="post">
			 	<input type="hidden" name="resume_id" value="${resume.resume_id }">
			 	<input type="text" name="resume_title" value="${resume.resume_title }">
			 	<button type="submit">수정하기</button>
			 </form>

</body>
</html>