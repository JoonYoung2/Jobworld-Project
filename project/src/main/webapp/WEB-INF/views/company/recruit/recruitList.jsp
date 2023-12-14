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
	<br>
	<c:choose>
		<c:when test="${empty list }">
			등록된 채용정보가 없습니다.<br>
			<a href="recruitWrite">채용등록</a>
		</c:when>
		<c:otherwise>
				<table id="adminRecruitListForm">
					<c:forEach var="recruit" items="${list }">
						<tr>
							<th>제목</th>
							<th>경력</th>
							<th>학력</th>
							<th>고용형태</th>
							<th>급여</th>
							<th>근무지역</th>
							<th>근무시간대</th>
						</tr>
						<tr>
							<td>${recruit.recruit_title }</td>
							<td>${recruit.recruit_career }</td>
							<td>${recruit.recruit_education }</td>
							<td>${recruit.recruit_employment }</td>
							<td>${recruit.recruit_salary }</td>
							<td>${recruit.recruit_area }</td>
							<td>${recruit.recruit_time }</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="7">
							<form action="userApplyList.do" method="post">
								<input type="hidden" name="recruit_id" value="${recruit.recruit_id }">
								<button type="submit">지원 현황</button>
							</form>
							<a href="recruitWrite">채용등록</a>
						</td>
					</tr>
				</table>
			<br>
			<br>
			
		</c:otherwise>
	</c:choose>

</body>
</html>