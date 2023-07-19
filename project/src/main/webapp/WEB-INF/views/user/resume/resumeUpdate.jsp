<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/style.css">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<title>JobWorld</title>
</head>
<body>
<%@ include file="/WEB-INF/views/user_menuBar.jsp"%>
<br>
<section class="resume_info_page">
	<form action="personalInfoUpdate.do" method="post" enctype="multipart/form-data" id="personalInfo">
		<div class="resume_header">
			<div class="resume_header_location">
				<div class="resume_title">
					<div>
						<input type="text" name="resume_title" value="${resume.resume_title }" style="all:unset; width: 900px;">
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
								<div class="user__name_input"><span style="font-size:10px;">이름<span style="color:red;">*</span></span><br><input name="user_nm" type="text" value="${resume.user_nm }" readonly="readonly"></div>
								<div class="user__birthday_input"><span style="font-size:10px;">생년월일<span style="color:red;">*</span></span><br><input type="text" name="user_birthday" value="${resume.user_birthday }" readonly="readonly"></div>
								<div class="user__phone"><span style="font-size:10px;">휴대폰번호<span style="color:red;">*</span></span><br><input type="text" name="user_phone_num" value="${resume.user_phone_num }"></div>						
							</div>
							<input type="hidden" id="sample6_postcode" name="zip_cd" value="${resume.zip_cd }" readonly="readonly">
							<input type="button" id="codeZip" onClick="sample6_execDaumPostcode()" value="우편번호" style="display:none;">
							<input type="hidden" id="sample6_extraAddress" readonly="readonly">
							<div class="user_info_box_batch user_info_box_batch_two">
								<div class="user__address__info" onclick="addressInputClick();"><span style="font-size:10px;">주소</span><br><input style="width:300px;" id="sample6_address" type="text" name="address_info" value="${resume.address_info }" readonly="readonly"></div>	
								<div class="user__address__detail"><span style="font-size:10px;">상세주소</span><br><input id="sample6_detailAddress" name="address_detail" type="text" value="${resume.address_detail }"></div>					
								<div class="user__email"><span style="font-size:10px;">이메일<span style="color:red;">*</span></span><br><input type="text" name="user_email" value="${resume.user_email }"></div>
							</div>
							<div class="resume_person_info_update">
								<div class="user_info_box_batch resume_person_button_zone">
							 		<button class="resume_person_info_save_button" onclick="personalInfoSubmit();"><b>저장</b></button><button type="reset" class="resume_person_info_save_button" id="cancelButton" onclick="fileCancel()"><b>취소</b></button>
								 </div>
							 </div>
						</div>
							
						<!-- User Image Zone -->
						<div class="resume_update_img_zone">
							<a onclick="fileSelect();"><img id="userImgFile"style="width: 130px;" src="resources/upload/${sessionScope.user_id }/${resume.user_img}" alt="..." /></a>
							<a onclick="fileSelect();"><img id="preview" alt="Preview"/></a>
							<input type="hidden" name="resume_id" value="${resume.resume_id }">
							<input type="hidden" name="user_id" value="${resume.user_id }">
							<input type="file" name="file" id="file" style="display:none;" onchange="previewImage(event);">
						</div>
					</div>
				</div>	
			</div>
		</div>
	</form>
	<script>
		function fileSelect(){
			document.getElementById('file').click();
		}
		function previewImage(event) {
			  var input = event.target;
			  var reader = new FileReader();

			  reader.onload = function() {
			    var imgElement = document.getElementById('preview');
			    imgElement.src = reader.result;
			  };

			  reader.readAsDataURL(input.files[0]);
			  
			  document.getElementById('preview').style.display='block';
			  document.getElementById('userImgFile').style.display='none';
			}
		function fileCancel(){
			document.getElementById('cancelButton').click();
			document.getElementById('preview').style.display='none';
			document.getElementById('userImgFile').style.display='block';
		}
		
		function addressInputClick(){
			document.getElementById('codeZip').click();
		}
		
		function personalInfoSubmit(){
			alert("인적사항이 저장되었습니다.");
			document.getElementById('personalInfo').submit();
		}
	</script>
</section>
<script	src="resources/js/search_road.js"></script>
</body>
</html>