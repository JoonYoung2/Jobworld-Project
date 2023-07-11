<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${empty sessionScope.comp_id }">
	<a href="company">Home</a> | <a href="companyLogin">로그인</a> | <a href="companyRegister">회원가입</a>
</c:if>
<c:if test="${not empty sessionScope.comp_id }">
	<a href="company">Home</a> | <a href="recruitInfo?comp_id=${sessionScope.comp_id }">채용리스트</a> | <a href="companyLogout">로그아웃</a>
</c:if>