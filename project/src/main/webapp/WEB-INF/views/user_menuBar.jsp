<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

	<c:if test="${empty sessionScope.user_id }">
	<div class="user_navbar_page">
		<div class="navbar_left_location">
		
		</div>
		<div class="navbar_center_location">
			<div class="navbar_header">
				<div class="navbar_user_search">
					<a href="/"><img src="resources/img/jobworldImg.png"></a> 
					<form id="searchSubmit" action="/userSearch.do" method="post"><input class="searchBox" type="text" name="userSearch"></form><button onclick="searchImg();"><img class="searchImg" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png"></button>
				</div>
				<div class="navbar_menu">
					<div>
						<!-- 다른 메뉴 넣을거 -->
					</div>
					<div>
						<a onclick="loginWindow();" style="cursor:pointer;">로그인</a> <a onclick="registerWindow();" style="cursor:pointer;">회원가입</a>			
					</div>
				</div>
			</div>
		</div>	
		<div class="navbar_right_location">
		
		</div>	
	</div>
	<script src="resources/js/new_window.js"></script>
	</c:if>
	
	<c:if test="${sessionScope.login_type eq 0}">
	<div class="user_navbar_page">
		<div class="navbar_left_location">
		
		</div>
		<div class="navbar_center_location">
			<div class="navbar_header">
				<div class="navbar_user_search">
					<a href="/"><img src="resources/img/jobworldImg.png"></a> 
					<form id="searchSubmit" action="/userSearch.do" method="post"><input class="searchBox" type="text" name="userSearch"></form><button onclick="searchImg();"><img class="searchImg" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png"></button>
				</div>
				<div class="navbar_menu">
					<div>
						<!-- 다른 메뉴 넣을거 -->
					</div>
					<div>
						<a href="resumeInfo?user_id=${sessionScope.user_id }">이력서</a> <a href="companyApplyList?user_id=${sessionScope.user_id }">입사지원현황</a> <a href="memberLogout">로그아웃</a>			
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
				<div class="navbar_user_search">
					<a href="/"><img src="resources/img/jobworldImg.png"></a> 
					<form id="searchSubmit" action="/userSearch.do" method="post"><input class="searchBox" type="text" name="userSearch"></form><button onclick="searchImg();"><img class="searchImg" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png"></button>
				</div>
				<div class="navbar_menu">
					<div>
						<!-- 다른 메뉴 넣을거 -->
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
	
	<script>
		var searchSubmit = document.getElementById("searchSubmit");
		function searchImg(){
			searchSubmit.submit();
		}
	</script>