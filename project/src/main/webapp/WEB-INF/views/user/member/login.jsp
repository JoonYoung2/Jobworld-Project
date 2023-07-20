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

	<div class="login_page">
		<div class="login_location">
			<div id="msg">${msg }</div>
			<div class="member_login_text">
				<div class="login_text_div">Member Login</div>
			</div>
			<form action="memberLogin.do" method="post">
				<div class="login_form">
					<div class="login_input">
						<input type="text" name="user_id" value="${member.user_id }"
							placeholder="ID"><br> <input type="password"
							name="user_pw" value="${member.user_pw }"
							placeholder="PASSWORD"><br>
					</div>
					<div>
						<button type="submit" class="login_button">LOGIN</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>