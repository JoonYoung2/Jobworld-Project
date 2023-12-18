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
	<%@ include file="/WEB-INF/views/user_menuBar.jsp"%>
	<section class="recruit_info_page">
		<c:choose>
			<c:when test="${empty list }">
				<div align="center">
					<div style="width:60%;">
						<script>
							alert("지원한 기업이 없습니다. \n입사지원 후 클릭해주세요.");
							window.history.back();
						</script>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div align="center">
					<div style="width:60%; text-align:left;">
						총 지원한 수 : ${list.size() }<br>
						<div style="font-size: 20px;">채용정보</div>
					</div>
				</div>
				<c:forEach var="recruit" items="${list }">
					<div align="center">
						<div style="width:60%;">
							<div class="recruit_info_frame">
								<div class="recruit_info">
									<div class="recruit_title">
										<span class="company_name">${recruit.compNm }</span><br> <span
											class="title_font">${recruit.recruitTitle }</span>
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
		
										<div style="padding-left: 150px;">
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
											<img class="recruit_list_img_size"
												src="/resources/company_upload/${recruit.compId }/${recruit.compBrandImg }"
												alt="..." />
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
							<div style="text-align:left;">
								지원결과 : <c:if test="${recruit.state eq 0 }">심사중</c:if>
								<c:if test="${recruit.state eq 1 }">서류통과</c:if>
								<c:if test="${recruit.state eq 2 }">1차면접통과</c:if>
								<c:if test="${recruit.state eq 3 }">2차면접통과</c:if>
								<c:if test="${recruit.state eq 4 }">최종합격</c:if>
								<c:if test="${recruit.state eq 5 }">불합격</c:if>
								<form action="/apply/cancel" method="post">
									<input type="hidden" name="applyId" value="${recruit.applyId }">
									<input type="hidden" name="userId"
										value="${sessionScope.user_id }">
									<button type="submit">지원취소하기</button>
								</form>
							</div>
							<br>
							<br>
						</div>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</section>

</body>
</html>