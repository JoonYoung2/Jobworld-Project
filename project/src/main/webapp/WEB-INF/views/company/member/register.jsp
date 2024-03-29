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

	${msg }
	<br>
	<div class="register_page">
		<div class="register_location">
			<form action="/company/register" method="post" enctype="multipart/form-data" id="f">
				<table class="join_table">
					<tr>
						<td style="width: 30%">아이디</td>
						<td style="width: 100%">
							<div>
								<input type="text" id="id" class="id" name="compId"
									value="${comp.compId }" onkeydown="id_click();"
									onblur="id_blur();" placeholder="ID"><br> <span
									id="member_id" style="color: red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" id="pw" name="compPw"
							value="${comp.compPw }" onkeydown="pw_click();"
							onblur="pw_blur();" placeholder="PASSWORD"> <br>
						<span id="member_pw" style="color: red"></span></td>
					</tr>
					<tr>
						<td>비밀번호 확인</td>
						<td><input type="password" id="pwCheck" name="compPwCheck"
							value="${comp.compPwCheck }" placeholder="PASSWORDCHECK"></td>
					</tr>
					<tr>
						<td>회사명</td>
						<td><input type="text" id="nm" name="compNm"
							value="${comp.compNm }"></td>
					</tr>
					<tr>
						<td>업종</td>
						<td><select name="compBusinessType" id="business">
								<option value="">선택</option>
								<option value="서비스업" ${comp.compBusinessType == '서비스업' ? 'selected="selected"' : ''}>서비스업</option>
								<option value="금융·은행업" ${comp.compBusinessType == '금융·은행업' ? 'selected="selected"' : ''}>금융·은행업</option>
								<option value="IT·정보통신업" ${comp.compBusinessType == 'IT·정보통신업' ? 'selected="selected"' : ''}>IT·정보통신업</option>
								<option value="판매·유통업" ${comp.compBusinessType == '판매·유통업' ? 'selected="selected"' : ''}>판매·유통업</option>
								<option value="제조·생산·화학업" ${comp.compBusinessType == '제조·생산·화학업' ? 'selected="selected"' : ''}>제조·생산·화학업</option>
								<option value="교육업" ${comp.compBusinessType == '교육업' ? 'selected="selected"' : ''}>교육업</option>
								<option value="건설업" ${comp.compBusinessType == '건설업' ? 'selected="selected"' : ''}>건설업</option>
								<option value="의료·제약업" ${comp.compBusinessType == '의료·제약업' ? 'selected="selected"' : ''}>의료·제약업</option>
								<option value="미디어·광고업" ${comp.compBusinessType == '미디어·광고업' ? 'selected="selected"' : ''}>미디어·광고업</option>
								<option value="문화·예술·디자인업" ${comp.compBusinessType == '문화·예술·디자인업' ? 'selected="selected"' : ''}>문화·예술·디자인업</option>
								<option value="기관·협회" ${comp.compBusinessType == '기관·협회' ? 'selected="selected"' : ''}>기관·협회</option>
						</select> <br>
						<span id="member_birth" style="color: red"></span></td>
					</tr>
					<tr>
						<td>사원수</td>
						<td><input type="text" id="emplNum" name="compEmplNum" placeholder="" value="${comp.compEmplNum }" > <br>
					</tr>
					<tr>
						<td>회사 규모</td>
						<td>
							<select name="compSize" id="size">
								<option value="">선택</option>
								<option value="중소기업" ${comp.compSize == '중소기업' ? 'selected="selected"' : ''}>중소기업</option>
								<option value="대기업" ${comp.compSize == '대기업' ? 'selected="selected"' : ''}>대기업</option>
								<option value="스타트업" ${comp.compSize == '스타트업' ? 'selected="selected"' : ''}>스타트업</option>
							</select>
							
							<span id="member_phone" style="color: red"></span>
						</td>
					</tr>
					<tr>
						<td>회사 사이트</td>
						<td><input type="text" id="site" name="compSite" value="${comp.compSite }">
						</td>
					</tr>
					<tr>
						<td>기업 로고</td>
						<td>
							<input type="file" name="file" id="file">
						</td>
					</tr>
				</table>
				<div class="join_button_location">
					<input class="join_button join_button_left" type="button" value="기업등록" onclick="check();"> 
						<input type="button" class="join_button join_button_right" onclick="window.history.back()" value="뒤로가기">
				</div>
			</form>
		</div>
	</div>
	
	<script src="/resources/js/company_register_regex.js"></script>
</body>
</html>