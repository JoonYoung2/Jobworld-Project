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
	<br>
	<br>
	
	<c:choose>
		<c:when test="${empty list }">
			지원한 회원 정보가 없습니다.<br>
		</c:when>
		<c:otherwise>
			<c:forEach var="resume" items="${list }">
				<section class="resume_info_page">
					<div class="resume_header">
						<div class="resume_header_location">
							<div class="resume_title">
								<div>
									${resume.resumeTitle }
								</div>
							</div>
							<div>
								<div style="display:flex; justify-content:space-between; width:90%; padding: 10px 0;">
									<div><b>인적사항</b></div>
								</div>
								<div class="resume_user_info">
									<div class="user_info__box">
										<div class="user_info_box_batch user_status_box">
											<div class="status_location">
										 		<form action="/apply/state/update" method="post" id="${resume.applyId }">
													<input type="hidden" name="applyId" value="${resume.applyId }" id="applyId">
													<select name="state" onchange="statusUpdate(${resume.applyId });" style="all:unset;">
														<option value="0" ${resume.state == 0 ? 'selected="selected"' : '' } >심사중</option>
														<option value="1" ${resume.state == 1 ? 'selected="selected"' : '' } >서류통과</option>
														<option value="2" ${resume.state == 2 ? 'selected="selected"' : '' } >1차면접통과</option>
														<option value="3" ${resume.state == 3 ? 'selected="selected"' : '' } >2차면접통과</option>
														<option value="4" ${resume.state == 4 ? 'selected="selected"' : '' } >합격</option>
														<option value="5" ${resume.state == 5 ? 'selected="selected"' : '' } >불합격</option>
													</select>
												</form>
											 </div>
										 </div>
										<div class="user_info_box_batch">
											<div class="user__name"><span style="font-size:10px;">이름</span><br>${resume.userNm }</div>
											<div class="user__birthday"><span style="font-size:10px;">생년월일</span><br>${resume.userBirthday }</div>
											<div class="user__phone"><span style="font-size:10px;">휴대폰번호</span><br>${resume.userPhoneNum }</div>
										</div>
										<div class="user_info_box_batch user_info_box_batch_two">
											<div class="user__address"><span style="font-size:10px;">주소</span><br>${resume.addressInfo } ${resume.addressDetail }</div>
											<div class="user__email"><span style="font-size:10px;">이메일</span><br>${resume.userEmail }</div>
										</div>
									</div>
									<div>
										<img style="width: 140px; height: 100;" src="/resources/upload/${resume.userId }/${resume.userImg}" alt="..." />
									</div>
								</div>
							</div>	
						</div>
					</div>
				</section>
			</c:forEach>
		</c:otherwise>
		
	</c:choose>
<script>
	function statusUpdate(applyId){
		document.getElementById(applyId).submit();
	}
</script>
</body>
</html>