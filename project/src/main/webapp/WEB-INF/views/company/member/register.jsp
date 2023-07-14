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
	${msg }
<form action="companyRegister.do" method="post" enctype="multipart/form-data">
	아이디 : <input type="text" name="comp_id" value="${comp.comp_id }"><br>
	비밀번호 : <input type="password" name="comp_pw" value="${comp.comp_pw }"><br>
	비밀번호 확인 : <input type="password" name="comp_pwCheck" value="${comp.comp_pwCheck }"><br>
	기업명 : <input type="text" name="comp_nm" value="${comp.comp_nm }"><br>
	업종 : <select name="comp_business_type">
		<option value="">선택</option>
		<option value="서비스업" ${comp.comp_business_type == '서비스업' ? 'selected="selected"' : ''}>서비스업</option>
		<option value="금융·은행업" ${comp.comp_business_type == '금융·은행업' ? 'selected="selected"' : ''}>금융·은행업</option>
		<option value="IT·정보통신업" ${comp.comp_business_type == 'IT·정보통신업' ? 'selected="selected"' : ''}>IT·정보통신업</option>
		<option value="판매·유통업" ${comp.comp_business_type == '판매·유통업' ? 'selected="selected"' : ''}>판매·유통업</option>
		<option value="제조·생산·화학업" ${comp.comp_business_type == '제조·생산·화학업' ? 'selected="selected"' : ''}>제조·생산·화학업</option>
		<option value="교육업" ${comp.comp_business_type == '교육업' ? 'selected="selected"' : ''}>교육업</option>
		<option value="건설업" ${comp.comp_business_type == '건설업' ? 'selected="selected"' : ''}>건설업</option>
		<option value="의료·제약업" ${comp.comp_business_type == '의료·제약업' ? 'selected="selected"' : ''}>의료·제약업</option>
		<option value="미디어·광고업" ${comp.comp_business_type == '미디어·광고업' ? 'selected="selected"' : ''}>미디어·광고업</option>
		<option value="문화·예술·디자인업" ${comp.comp_business_type == '문화·예술·디자인업' ? 'selected="selected"' : ''}>문화·예술·디자인업</option>
		<option value="기관·협회" ${comp.comp_business_type == '기관·협회' ? 'selected="selected"' : ''}>기관·협회</option>
	</select><br>
	사원수 : <input type="text" name="comp_empl_num" value="${comp.comp_empl_num }"><br> 
	기업 규모 : <select name="comp_size">
		<option value="">선택</option>
		<option value="중소기업" ${comp.comp_size == '중소기업' ? 'selected="selected"' : ''}>중소기업</option>
		<option value="대기업" ${comp.comp_size == '대기업' ? 'selected="selected"' : ''}>대기업</option>
		<option value="스타트업" ${comp.comp_size == '스타트업' ? 'selected="selected"' : ''}>스타트업</option>
	</select><br>
	회사 사이트 : <input type="text" name="comp_site" value="${comp.comp_site }"><br>
	기업 로고 : <input type="file" name="file"><br>
	<button type="submit">기업등록</button>
</form>
</body>
</html>