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
	<br>
	<c:choose>
		<c:when test="${empty list }">
			지원한 회원 정보가 없습니다.<br>
		</c:when>
		<c:otherwise>
		총 지원자 수 : ${list.size() }<br>
			<c:forEach var="resume" items="${list }">
			<img style="padding-left: 200px; padding-top: 100px; width: 450px; height: 450px;"
			 src="resources/upload/${resume.user_id }/${resume.user_img}" alt="..." /><br>
				제목 : ${resume.resume_title }<br>
				유저정보<br>
				생년월일 : ${resume.user_birthday }<br>
				email : ${resume.user_email }<br>
				이름 : ${resume.user_nm }<br>
				<form action="applyStateUpdate.do" method="post">
				<input type="hidden" name="apply_id" value="${resume.apply_id }">
					<select name="state">
						<option value="0" ${resume.state == 0 ? 'selected="selected"' : '' }>심사중</option>
						<option value="1" ${resume.state == 1 ? 'selected="selected"' : '' }>서류통과</option>
						<option value="2" ${resume.state == 2 ? 'selected="selected"' : '' }>1차면접통과</option>
						<option value="3" ${resume.state == 3 ? 'selected="selected"' : '' }>2차면접통과</option>
						<option value="4" ${resume.state == 4 ? 'selected="selected"' : '' }>합격</option>
						<option value="5" ${resume.state == 5 ? 'selected="selected"' : '' }>불합격</option>
					</select>
					<button type="submit">수정하기</button>
				</form>
			<br>
			<br>
			</c:forEach>
		</c:otherwise>
	</c:choose>

</body>
</html>