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
	<body style="background-color: rgba(0, 0, 0, 0.07)">
		<section class="apply_info_page">
			<div class="apply_info_header">
				<div style="font-size:13px; color: #3399FF;"><b>${resume.compNm }</b></div>
				<div style="font-size:16px;">${resume.recruitTitle }</div>
			</div>
			<div class="apply_info_body">
				<div class="apply_user_info">
					<div class="user_info_top" style="font-size:15;">
						<b>지원이력서</b>
					</div>
					<div class="user_info_middle">
						${resume.resumeTitle }
					</div>
					<div class="user_info_bottom">
						<div><span class="apply_font_color">이메일</span> <span class="apply_span_location">${resume.userEmail }</span> <span class="apply_span_location apply_font_color">|</span> <span class="apply_span_location apply_font_color">휴대폰번호</span> <span class="apply_span_location">${resume.userPhoneNum }</span></div> <div><span class="apply_span_location apply_user_update_button"><a href="/resume/update?resumeId=${resume.resumeId }">수정</a></span></div>
					</div>
				</div>
				<p style="font-size:12px;">
					* 개인정보보호를 위해 개인정보가 포함된 파일은 사전동의 없이 삭제될 수 있습니다.<br>
					* 제출서류는 채용 마감 후 90일까지 지원기업에게 제공됩니다.<br>
					<span>제출에 동의할 경우에만 [지원하기] 버튼을 클릭해주세요.</span><br>
					<span>동의하지 않을 경우 입사지원이 불가능합니다.</span>
				</p>
				<form action="/apply" method="post">
				<input type="hidden" name="resumeId" value="${resume.resumeId }">
				<input type="hidden" name="recruitId" value="${resume.recruitId }">
				<div class="apply_button_location">
					<button type="submit" class="apply_info_button">지원하기</button>
				</div>
			</form>	
			</div>
		</section>
		
			
		
	</body>
</html>