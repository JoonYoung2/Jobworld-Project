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
	<%@ include file="../../company_menuBar.jsp"%>
	<form action="/recruit/write" method="post">
	<input type="hidden" name="compId" value="${sessionScope.comp_id }">
		제목 : <input type="text" name="recruitTitle"><br>
		경력 : <input type="text" name="recruitCareer"><br>
		학력 : <input type="text" name="recruitEducation"><br>
		고용 형태 : <input type="text" name="recruitEmployment"><br>
		급여 : <input type="text" name="recruitSalary"><br>
		근무 지역 : <input type="text" name="recruitArea"><br>
		근무 시간대 : <input type="text" name="recruitTime"><br>
		<button type="submit">등록하기</button>
	</form>
</body>
</html>