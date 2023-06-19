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
<form action="memberRegister.do" method="post">
<input type="hidden" name="login_type" value="0">
	아이디 : <input type="text" name="user_id" value="${member.user_id }"><br>
	비밀번호 : <input type="password" name="user_pw" value="${member.user_pw }"><br>
	비밀번호 확인 : <input type="password" name="user_pwCheck" value="${member.user_pwCheck }"><br>
	이름 : <input type="text" name="user_nm" value="${member.user_nm }"><br>
	생년월일 : <input type="text" name="user_birthday" value="${member.user_birthday }" placeholder="ex)19001101" maxlength='8'><br>
	이메일 : <input type="text" name="user_email" value="${member.user_email }" placeholder="ex)example@example.com"><br>
	전화번호 : <input type="text" name="user_phone_num" value="${member.user_phone_num }" placeholder="ex)01012341234"><br>
	우편번호 : <input type="text" name="zip_cd" value="${member.zip_cd }" ><br>
	주소 : <input type="text" name="address_info" value="${member.address_info }"><br>
	상세 주소 : <input type="text" name="address_detail" value="${member.address_detail }"><br>
	<button type="submit">회원가입</button>
</form>
</body>
</html>