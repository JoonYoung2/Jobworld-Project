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
	<%@ include file="/WEB-INF/views/company_menuBar.jsp"%>
	<br>
	<c:choose>
		<c:when test="${empty list }">
			등록된 채용정보가 없습니다.<br>
			<a href="/recruit/write">채용등록</a>
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
							<td>${recruit.recruitTitle }</td>
							<td>${recruit.recruitCareer }</td>
							<td>${recruit.recruitEducation }</td>
							<td>${recruit.recruitEmployment }</td>
							<td>${recruit.recruitSalary }</td>
							<td>${recruit.recruitArea }</td>
							<td>${recruit.recruitTime }</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="7">
							<form action="/apply/user/list" method="post">
								<input type="hidden" name="recruitId" value="${recruit.recruitId }">
								<button type="submit">지원 현황</button>
							</form>
							<a href="/recruit/write">채용등록</a>
						</td>
					</tr>
				</table>
			<br>
			<br>
			
		</c:otherwise>
	</c:choose>

</body>
</html>