<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/style.css">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<title>JobWorld</title>
</head>
<body>
${msg }<br>
<div class="register_page">
	<div class="register_location">
		<form action="/member/register" method="post" id="f">
			<input type="hidden" id="sample6_extraAddress" readonly="readonly">
			<table class="join_table">
				<tr>
				    <td style="width:30%">
				    	아이디
				    </td>
				    <td style="width:100%">
				    	<div>
				    	<input type="text" id="id" class="id" name="userId" value="${member.userId }" onkeydown="id_click();" onblur="id_blur();" placeholder="ID"><br>
							<span id="member_id" style="color: red"></span>
						</div>
					</td>
				</tr>
				<tr>
				    <td>비밀번호</td>
				    <td><input type="password" id="pw" name="userPw" value="${member.userPw }" onkeydown="pw_click();" onblur="pw_blur();" placeholder="PASSWORD">
			<br><span id="member_pw" style="color: red"></span></td>
				</tr>
				<tr>
				    <td>비밀번호 확인</td>
				    <td><input type="password" id="pwCheck" name="userPwCheck" value="${member.userPwCheck }" placeholder="PASSWORDCHECK"></td>
				</tr>
				<tr>
				    <td>이름</td>
				    <td><input type="text" id="nm" name="userNm" value="${member.userNm }"></td>
				</tr>
				<tr>
				    <td>생년월일</td>
				    <td><input type="number" min="10000000" max="99999999" id="birth" name="userBirthday" placeholder="19001010" <c:if test="${member.userBirthday eq 0 }">value=""</c:if><c:if test="${member.userBirthday > 0 }">value="${member.userBirthday }"</c:if> onkeydown="birth_click();" onblur="birth_blur();" maxlength='8'>
			<br><span id="member_birth" style="color: red"></span></td>
				</tr>
				<tr>
				    <td>email</td>
				    <td><input type="text" id="email" name="userEmail"  placeholder="example@example.com" value="${member.userEmail }" onkeydown="email_click();" onblur="email_blur();">
				    <br><span id="member_email" style="color: red"></span></td>
				</tr>
				<tr>
				    <td>휴대폰</td>
				    <td><input type="text" id="phone" name="userPhoneNum" placeholder="01012345678" value="${member.userPhoneNum }" onkeydown="phone_click();" onblur="phone_blur();" maxlength='11'>
			<br><span id="member_phone" style="color: red"></span></td>
				</tr>
				<tr>
				    <td>우편번호</td>
				    <td><input type="text" id="sample6_postcode" name="zipCd" value="${member.zipCd }" readonly="readonly"> <input type="button" onClick="sample6_execDaumPostcode()" value="우편번호"></td>
				</tr>
				<tr>
				    <td>주소</td>
				    <td><input type="text" id="sample6_address" name="addressInfo" value="${member.addressInfo }" readonly="readonly"></td>
				</tr>
				<tr>
				    <td>상세 주소</td>
				    <td><input type="text" id="sample6_detailAddress" name="addressDetail" value="${member.addressDetail }"></td>
				</tr>
		    </table>
		    <div class="join_button_location"><input class="join_button join_button_left" type="button" value="회원가입" onclick="check();"> <input type="button" class="join_button join_button_right" onclick="window.history.back()"value="뒤로가기"></div>
			
		</form>
	</div>
</div>

<script src="/resources/js/register_regex.js"></script>
<script src="/resources/js/search_road.js"></script>
</body>
</html>