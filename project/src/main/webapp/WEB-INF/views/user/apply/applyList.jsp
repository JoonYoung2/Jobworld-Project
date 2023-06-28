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
	<c:choose>
		<c:when test="${empty list }">
			지원한 회원 정보가 없습니다.<br>
		</c:when>
		<c:otherwise>
		총 지원한 수 : ${list.size() }<br>
			<c:forEach var="recruit" items="${list }">
				회사정보<br>
				회사명 : ${recruit.comp_nm }<br>
				직원수 : ${recruit.comp_empl_num }<br>
				회사규모 : ${recruit.comp_size }<br>
				홈페이지 : ${recruit.comp_site }<br>
				<br><br>
				모집정보<br>
				경력 : ${recruit.recruit_career }<br>
				학력 : ${recruit.recruit_education }<br>
				고용형태 : ${recruit.recruit_employment }<br>
				급여 : ${recruit.recruit_salary }<br>
				근무지역 : ${recruit.recruit_area }<br>
				근무시간대 : ${recruit.recruit_time }<br>
				<br><br>
				지원결과 : <c:if test="${recruit.state eq 0 }">심사중</c:if>
				<c:if test="${recruit.state eq 1 }">서류통과</c:if>
				<c:if test="${recruit.state eq 2 }">1차면접통과</c:if>
				<c:if test="${recruit.state eq 3 }">2차면접통과</c:if>
				<c:if test="${recruit.state eq 4 }">최종합격</c:if>
				<c:if test="${recruit.state eq 5 }">불합격</c:if>
				
				<form action="applyCancel.do" method="post">
					<input type="hidden" name="apply_id" value="${recruit.apply_id }">
					<input type="hidden" name="user_id" value="${sessionScope.user_id }">
					<button type="submit">지원취소하기</button>
				</form>
				<br><br>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</body>
</html>