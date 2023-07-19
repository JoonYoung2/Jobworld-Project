<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${empty sessionScope.comp_id }">
	<div class="user_navbar_page">
		<div class="navbar_left_location">
		
		</div>
		<div class="navbar_center_location">
			<div class="navbar_header">
				<div>
					<a href="/company"><img src="resources/img/jobworldImg.png"></a> <a href="#">검색기능</a>
				</div>
				<div class="navbar_menu">
					<div>
						다른 메뉴들~~~
					</div>
					<div>
						<a href="companyLogin">로그인</a> <a href="companyRegister">회원가입</a>			
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
				<div>
					<a href="/company"><img src="resources/img/jobworldImg.png"></a> <a href="#">검색기능</a>
				</div>
				<div class="navbar_menu">
					<div>
						다른 메뉴들~~~
					</div>
					<div>
						<a href="recruitInfo?comp_id=${sessionScope.comp_id }">채용리스트</a> <a href="companyLogout">로그아웃</a>			
					</div>
				</div>
			</div>
		</div>	
		<div class="navbar_right_location">
		
		</div>	
	</div>
</c:if>