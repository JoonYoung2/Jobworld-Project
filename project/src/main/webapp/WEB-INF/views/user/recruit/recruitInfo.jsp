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
	경력 : ${recruit.recruit_career }<br>
	학력 : ${recruit.recruit_education }<br>
	고용형태 : ${recruit.recruit_employment }<br>
	급여 : ${recruit.recruit_salary }<br>
	근무지역 : ${recruit.recruit_area }<br>
	근무시간대 : ${recruit.recruit_time }<br>
	<a href="apply">지원하기</a>
</body>
</html>