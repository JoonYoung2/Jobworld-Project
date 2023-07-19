<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/style.css">
<title>JobWorld</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/company_menuBar.jsp"%>
	<form action="recruitWrite.do" method="post">
	<input type="hidden" name="comp_id" value="${sessionScope.comp_id }">
		제목 : <input type="text" name="recruit_title"><br>
		경력 : <input type="text" name="recruit_career"><br>
		학력 : <input type="text" name="recruit_education"><br>
		고용 형태 : <input type="text" name="recruit_employment"><br>
		급여 : <input type="text" name="recruit_salary"><br>
		근무 지역 : <input type="text" name="recruit_area"><br>
		근무 시간대 : <input type="text" name="recruit_time"><br>
		<button type="submit">등록하기</button>
	</form>
</body>
</html>