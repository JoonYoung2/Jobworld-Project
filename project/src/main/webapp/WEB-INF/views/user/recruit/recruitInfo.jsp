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
<body class="body_location">

<section class="recruit_info_page">
	<div style="font-size:20px;">
		채용정보
	</div>	
	<div class="recruit_info_frame">
		<div class="recruit_info">
			<div class="recruit_title">
				<span class="company_name">${recruit.compNm }</span><br>
				<span class="title_font">${recruit.recruitTitle }</span>
			</div>
			<div class="recruit_apply_condition">
				<div>
					<table>
						<tr>
							<td><b>지원자격</b></td>
							<td></td>
						</tr>
						<tr>
							<td><span class="recruit_font_color">경력</span></td>
							<td>${recruit.recruitCareer }</td>
						</tr>
						<tr>
							<td><span class="recruit_font_color">학력</span></td>
							<td>${recruit.recruitEducation }</td>
						</tr>
					</table>
				</div>
				
				<div style="padding-left:150px;">
					<table>
						<tr>
							<td><b>근무조건</b></td>
							<td></td>
						</tr>
						<tr>
							<td><span class="recruit_font_color">고용형태</span></td>
							<td>${recruit.recruitEmployment }</td>
						</tr>
						<tr>
							<td><span class="recruit_font_color">급여</span></td>
							<td>${recruit.recruitSalary }</td>
						</tr>
						<tr>
							<td><span class="recruit_font_color">지역</span></td>
							<td>${recruit.recruitArea }</td>
						</tr>
						<tr>
							<td><span class="recruit_font_color">시간</span></td>
							<td>${recruit.recruitTime }</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		
		<div class="recruit_company_info">
			<div class="company_info_img">
				<div>
					<img class="recruit_list_img_size" src="/resources/company_upload/${recruit.compId }/${recruit.compBrandImg }" alt="..." />
				</div>
			</div>
			<table>
				<tr>
					<td><b>기업정보</b></td>
					<td></td>
				</tr>
				<tr>
					<td><span class="recruit_font_color">업종(산업)</span></td>
					<td>${recruit.compBusinessType }</td>
				</tr>
				<tr>
					<td><span class="recruit_font_color">사원수</span></td>
					<td>${recruit.compEmplNum }</td>
				</tr>
				<tr>
					<td><span class="recruit_font_color">기업형태</span></td>
					<td>${recruit.compSize }</td>
				</tr>
				<tr>
					<td><span class="recruit_font_color">홈페이지</span></td>
					<td>${recruit.compSite }</td>
				</tr>
			</table>
		</div>
	</div>
</section>
<section>
	<div class="recruit_apply_button">
		<c:choose>
			<c:when test="${empty sessionScope.user_id }">
				<a onclick="loginWindow();">
					<div class="apply_button">
						로그인
					</div>
				</a>
			</c:when>
			<c:otherwise>
				<a onclick="applyWindow(${recruit.recruitId});">
					<div class="apply_button">
						즉시지원
					</div>
				</a>
			</c:otherwise>
		</c:choose>
	</div>
</section>	
	<script src="/resources/js/new_window.js"></script>
</body>
</html>