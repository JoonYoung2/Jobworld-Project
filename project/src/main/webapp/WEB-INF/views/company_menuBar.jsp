<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${empty sessionScope.comp_id }">
	<div class="user_navbar_page">
		<div class="navbar_left_location">
		
		</div>
		<div class="navbar_center_location">
			<div class="navbar_header">
				<div class="navbar_user_search">
					<a href="/company"><img src="/resources/img/jobworldImg.png"></a>
					<form id="searchSubmit" action="/search/company" method="post">
						<input type="hidden" value="${sessionScope.comp_id }" name="compId">
						<input class="searchBox" type="text" name="companySearch">
					</form><button onclick="searchImg();"><img class="searchImg" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png"></button>
				</div>
				<div class="navbar_menu">
					<div>
						<!-- 다른 메뉴 넣을거 -->
					</div>
					<div>
						<a href="/company/login">로그인</a> <a href="/company/register">회원가입</a>
					</div>
				</div>
			</div>
		</div>	
		<div class="navbar_right_location">
		
		</div>	
	</div>
</c:if>
<c:if test="${not empty sessionScope.comp_id }">
	<div class="user_navbar_page">
		<div class="navbar_left_location">
		
		</div>
		<div class="navbar_center_location">
			<div class="navbar_header">
				<div class="navbar_user_search">
					<a href="/company"><img src="/resources/img/jobworldImg.png"></a>
					<form id="searchSubmit" action="/search/company" method="post">
						<input type="hidden" value="${sessionScope.comp_id }" name="compId">
						<input class="searchBox" type="text" name="companySearch">
					</form><button onclick="searchImg();"><img class="searchImg" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png"></button>
				</div>
				<div class="navbar_menu">
					<div>
						<!-- 다른 메뉴 넣을거 -->
					</div>
					<div>
						<a href="/recruit/info?compId=${sessionScope.comp_id }">채용리스트</a> <a href="/company/logout">로그아웃</a>
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