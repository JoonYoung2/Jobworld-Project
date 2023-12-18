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
<%@ include file="/WEB-INF/views/user_menuBar.jsp"%>
<br>
<section class="resume_info_page">
	<form action="/resume/personal/update" method="post" enctype="multipart/form-data" id="personalInfo">
		<div class="resume_header">
			<div class="resume_header_location">
				<div class="resume_title">
					<div>
						<input id="title" type="text" name="resumeTitle" value="${resume.resumeTitle }" style="all:unset; width: 900px;">
					</div>
				</div>
				<div>
					<div style="display:flex; justify-content:space-between; width:90%; padding: 10px 0;">
						<div><b>인적사항</b></div> <div style="font-size: 12px;"><span style="color:red;">*</span> <span style="color: rgba(0, 0, 0, 0.5)">필수 입력 정보입니다.</span></div>
					</div>
					
					<!-- User Info Zone -->
					<div class="resume_user_info">
						<div class="user_info__box">
							<div class="user_info_box_batch">
								<div class="user__name_input"><span style="font-size:10px;">이름<span style="color:red;">*</span></span><br><input id="nm" name="userNm" type="text" value="${resume.userNm }" readonly="readonly"></div>
								<div class="user__birthday_input"><span style="font-size:10px;">생년월일<span style="color:red;">*</span></span><br><input id="birth" type="number" min="10000000" max="99999999" name="userBirthday" value="${resume.userBirthday }" readonly="readonly"></div>
								<div class="user__phone"><span style="font-size:10px;">휴대폰번호<span style="color:red;">*</span></span><br><input id="phone" type="text" name="userPhoneNum" value="${resume.userPhoneNum }"></div>
							</div>
							<input type="hidden" id="sample6_postcode" name="zipCd" value="${resume.zipCd }" readonly="readonly">
							<input type="button" id="codeZip" onClick="sample6_execDaumPostcode()" value="우편번호" style="display:none;">
							<input type="hidden" id="sample6_extraAddress" readonly="readonly">
							<div class="user_info_box_batch user_info_box_batch_two">
								<div class="user__address__info" onclick="addressInputClick();"><span style="font-size:10px;">주소</span><br><input style="width:300px;" id="sample6_address" type="text" name="addressInfo" value="${resume.addressInfo }" readonly="readonly"></div>
								<div class="user__address__detail"><span style="font-size:10px;">상세주소</span><br><input id="sample6_detailAddress" name="addressDetail" type="text" value="${resume.addressDetail }" style="width:100px;"></div>
								<div class="user__email"><span style="font-size:10px;">이메일<span style="color:red;">*</span></span><br><input id="email" type="text" name="userEmail" value="${resume.userEmail }"></div>
							</div>
							<div class="resume_person_info_update">
								<div class="user_info_box_batch resume_person_button_zone">
							 		<button class="resume_person_info_save_button" type="button" onclick="personalCheck();"><b>저장</b></button><button type="reset" class="resume_person_info_save_button" id="cancelButton" onclick="fileCancel()"><b>취소</b></button>
								 </div>
							 </div>
						</div>
						
						<!-- User Image Zone -->
						<div class="resume_update_img_zone">
							<a onclick="fileSelect();"><img id="userImgFile"style="width: 130px;" src="/resources/upload/${sessionScope.user_id }/${resume.userImg}" alt="..." /></a>
							<a onclick="fileSelect();"><img id="preview" alt="Preview"/></a>
							<input type="hidden" name="resumeId" value="${resume.resumeId }">
							<input type="hidden" name="userId" value="${resume.userId }">
							<input type="file" name="file" id="file" style="display:none;" onchange="previewImage(event);">
						</div>
					</div>
				</div>	
			</div>
		</div>
	</form>
</section>
<script src="/resources/js/resume_update_regex.js"></script>
<script	src="/resources/js/search_road.js"></script>
</body>
</html>