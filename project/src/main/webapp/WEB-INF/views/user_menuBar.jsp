<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

	<c:if test="${empty sessionScope.user_id }">
		<a href="/">Home</a> | <a href="memberLogin">로그인</a> | <a href="memberRegister">회원가입</a>
	</c:if>
	
	<c:if test="${sessionScope.login_type eq 0}">
		<a href="/">Home</a> | <a href="resumeInfo?user_id=${sessionScope.user_id }">이력서 작성</a> | <a href="memberLogout">로그아웃</a>
	</c:if>
	
	<c:if test="${sessionScope.login_type eq 1}">
		<a href="/">Home</a> | <a href="adminHome">Admin</a> | <a href="memberLogout">로그아웃</a>
	</c:if>