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
						<div class="col-lg-3 col-sm-3 mb-3 user_recruit_space">
							<!-- Portfolio item 1-->
							<div class="portfolio-item">
								<a href="recruitInfo.go?recruit_id=${recruit.recruit_id }">
									<img class="img-fluid recruit_list_img_size" src="resources/company_upload/${recruit.comp_id }/${recruit.comp_brand_img }" alt="..." />
									<br> 제목 : ${recruit.recruit_title }
									<br> 회사명 : ${recruit.comp_nm }
								</a>
							</div>
						</div>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</section>
</body>
</html>