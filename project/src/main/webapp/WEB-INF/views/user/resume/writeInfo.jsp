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
<%@ include file="/WEB-INF/views/user_menuBar.jsp"%>
<br>
<c:if test = "${empty resume }">
	<a href="resumeWrite">이력서 등록</a>
</c:if>
<section class="resume_info_page">
	<div class="resume_header">
		<div class="resume_header_location">
			<div class="resume_title">
				<div>
					${resume.resume_title }
				</div>
			</div>
			<div>
				<div style="display:flex; justify-content:space-between; width:90%; padding: 10px 0;">
					<div><b>인적사항</b></div> <div style="font-size: 12px;"><span style="color:red;">*</span> <span style="color: rgba(0, 0, 0, 0.5)">필수 입력 정보입니다.</span></div>
				</div>
				<div class="resume_user_info">
					<div class="user_info__box">
						<div class="user_info_box_batch">
							<div class="user__name"><span style="font-size:10px;">이름<span style="color:red;">*</span></span><br>${resume.user_nm }</div>
							<div class="user__birthday"><span style="font-size:10px;">생년월일<span style="color:red;">*</span></span><br>${resume.user_birthday }</div>
							<div class="user__phone"><span style="font-size:10px;">휴대폰번호<span style="color:red;">*</span></span><br>${resume.user_phone_num }</div>						
						</div>
						<div class="user_info_box_batch user_info_box_batch_two">
							<div class="user__address"><span style="font-size:10px;">주소</span><br>${resume.address_info } ${resume.address_detail }</div>						
							<div class="user__email"><span style="font-size:10px;">이메일<span style="color:red;">*</span></span><br>${resume.user_email }</div>
						</div>
					</div>
					<div>
						<img style="width: 100px; height: 100;" src="resources/upload/${sessionScope.user_id }/${resume.user_img}" alt="..." />
					</div>
				</div>
			</div>	
		</div>
	</div>
</section>
	<div class="resume_update_button">
		<a href="resumeUpdate?resume_id=${resume.resume_id }">
			<div class="resume_update_button_size">
				이력서수정
			</div>
		</a>
	</div>
</body>
</html>