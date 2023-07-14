<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

	<c:if test="${empty sessionScope.user_id }">
	<div class="user_navbar_page">
		<div class="navbar_left_location">
		
		</div>
		<div class="navbar_center_location">
			<div class="navbar_header">
				<div>
					<a href="/">Home</a> <a href="#">검색기능</a>
				</div>
				<div class="navbar_menu">
					<div>
						다른 메뉴들~~~
					</div>
					<div>
						<a href="memberLogin">로그인</a> <a href="memberRegister">회원가입</a>			
					</div>
				</div>
			</div>
		</div>	
		<div class="navbar_right_location">
		
		</div>	
	</div>
	</c:if>
	
	<c:if test="${sessionScope.login_type eq 0}">
	<div class="user_navbar_page">
		<div class="navbar_left_location">
		
		</div>
		<div class="navbar_center_location">
			<div class="navbar_header">
				<div>
					<a href="/"><img src="resources/img/jobworlImg.png"></a> <a href="#">검색기능</a>
				</div>
				<div class="navbar_menu">
					<div>
						다른 메뉴들~~~
					</div>
					<div>
						<a href="resumeInfo?user_id=${sessionScope.user_id }">이력서 보기</a> | <a href="companyApplyList?user_id=${sessionScope.user_id }">입사지원현황</a> | <a href="memberLogout">로그아웃</a>			
					</div>
				</div>
			</div>
		</div>	
		<div class="navbar_right_location">
		
		</div>	
	</div>
		
	</c:if>
	
	<c:if test="${sessionScope.login_type eq 1}">
	<div class="user_navbar_page">
		<div class="navbar_left_location">
		
		</div>
		<div class="navbar_center_location">
			<div class="navbar_header">
				<div>
					<a href="/">Home</a> <a href="#">검색기능</a>
				</div>
				<div class="navbar_menu">
					<div>
						다른 메뉴들~~~
					</div>
					<div>
						<a href="admin">Admin</a> | <a href="memberLogout">로그아웃</a>			
					</div>
				</div>
			</div>
		</div>	
		<div class="navbar_right_location">
		
		</div>	
	</div>
	</c:if>