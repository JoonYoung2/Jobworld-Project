<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/resources/img/jobworldImg.png" rel="shortcut icon" type="image/x-icon">
<link rel="stylesheet" href="/resources/css/style.css">
<title>JobWorld</title>
</head>
<body>
	<%@ include file="../user_menuBar.jsp"%>
	<br>
	<br>
	<br>

	<section class="page-section" id="portfolio">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">JOB WORLD RECRUIT INFO</h2>
				<h3 class="section-subheading text-muted"></h3>
			</div>
			<div class="row">
				<c:if test="${list.size() > 0 }">
					<c:forEach var="recruit" items="${list }">
							<div class="col-lg-3 col-sm-3 mb-3 recruit_parents">
<%-- 								<a href="/recruit/user/info?recruitId=${recruit.recruitId }"> --%>
								<a onclick="newWindow(${recruit.recruitId});" style="cursor:pointer;">
								<!-- Portfolio item 1-->
									<div class="user_recruit_space">
										<div class="portfolio-item user_img_space">
											<div style="height:120px; display:flex; justify-content:center; align-items:center;">
												<img class="recruit_list_img_size" src="/resources/company_upload/${recruit.compId }/${recruit.compBrandImg }" alt="..." />
											</div>
											<div class="user_recruit_location company_name">${recruit.compNm }</div>
											<div class="user_recruit_location company_title">${recruit.recruitTitle }</div>
										</div>
									</div>
<!-- 								
</a> -->							
								</a>
							</div>
	
					</c:forEach>
				</c:if>
			</div>
		</div>
	</section>
	<script src="/resources/js/new_window.js"></script>
</body>
</html>