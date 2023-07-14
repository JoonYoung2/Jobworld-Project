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
			등록된 채용정보가 없습니다.<br>
			<a href="recruitWrite">채용등록</a>
		</c:when>
		<c:otherwise>
			<c:forEach var="recruit" items="${list }">
			제목 : ${recruit.recruit_title }<br>
			경력 : ${recruit.recruit_career }<br>
			학력 : ${recruit.recruit_education }<br>
			고용형태 : ${recruit.recruit_employment }<br>
			급여 : ${recruit.recruit_salary }<br>
			근무지역 : ${recruit.recruit_area }<br>
			근무시간대 : ${recruit.recruit_time }<br>
			<form action="userApplyList.do" method="post">
				<input type="hidden" name="recruit_id" value="${recruit.recruit_id }">
				<button type="submit">지원 현황</button>
			</form>
			<br>
			<br>
			</c:forEach>
			<a href="recruitWrite">채용등록</a>
		</c:otherwise>
	</c:choose>

</body>
</html>