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
<c:choose>
	<c:when test="${empty sessionScope.comp_id }">
		로그인 해 주시기 바랍니다.
	</c:when>
	<c:otherwise>
		<c:if test="${list.size() == 0 }">
			채용등록을 해 주시기 바랍니다.
		</c:if>
		<c:if test="${list.size() > 0 }">
			<section class="page-section" id="portfolio">
				<div class="container">
					<div class="text-center">
						<h2 class="section-heading text-uppercase">${compNm } RECRUIT INFO</h2>
						<h3 class="section-subheading text-muted"></h3>
					</div>
					<div class="row">
						<c:forEach var="recruit" items="${list }">
							<div class="col-lg-3 col-sm-3 mb-3 recruit_parents">
								<a href="applyUserInfo?recruit_id=${recruit.recruitId }">
								<!-- Portfolio item 1-->
									<div class="user_recruit_space">
										<div class="portfolio-item user_img_space">
											<img class="recruit_list_img_size" src="/resources/company_upload/${recruit.compId }/${recruit.compBrandImg }" alt="..." />
											<div class="user_recruit_location company_name">${recruit.compNm }</div>
											<div class="user_recruit_location company_title">${recruit.recruitTitle }</div>
										</div>
									</div>
								</a>												
							</div>
						</c:forEach>
					</div>
				</div>
			</section>
		</c:if>
	</c:otherwise>
</c:choose>

</body>
</html>