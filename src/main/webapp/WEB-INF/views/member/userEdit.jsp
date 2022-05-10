<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 구글폰트 -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">	
<!-- CSS -->
<link rel="stylesheet" href="/css/member/userEdit.css" type="text/css">



<div id="contents">
			<section class="mypage-box">
			
				<h3>마이 페이지</h3>
				
<nav class="lnb-box">
					<a href="/member/userEdit" >&nbsp;&nbsp;&nbsp; <i class="bi bi-gear-fill"></i>&nbsp; 나의 정보</a>
					<a href="/member/myWrite" >&nbsp;&nbsp;&nbsp; <i class="bi bi-layout-text-sidebar-reverse"></i>&nbsp; 내가 쓴글</a>
					<a href="/member/myExercise" >&nbsp;&nbsp;&nbsp; <i class="bi bi-people-fill"></i>&nbsp; 참여한 운동</a>   
					<a href="/member/userDel" >&nbsp;&nbsp;&nbsp; <i class="bi bi-x-circle-fill"></i>&nbsp; 회원탈퇴</a>

				</nav>
</section>
</div>
<div class="info-box">
	 <div class="info-personal">
	 	<form id="memberEditForm" name="myform" onsubmit="">
	 	    	<div class="info-profile">
		 	   		<a href=""><img src="/img/member/defaultProfile.png" width="15%"></a>
		 	   	</div>
			 	   	<div class="info-top">
				 	   	<ul>
			 				<li>닉네임</li>
			 				<li>goguma</li>
			 				<li>이름</li>
			 				<li>고구마</li>
			 				<li>아이디</li>
			 				<li>goguma@gmail.com</li>
			 				<li>연락처</li>
			 				<li>010-1234-1234</li>
			 			</ul>
		 			</div>
		 			<div class="info-box-02">
		 				<h4>나의 활동량</h4>
			 			<ul>	
							<li>
								<label for="select1">거의 운동을 하지 않음</label>
								<input id="select1" name="active" type="radio" value="1.2">
							</li>
							<li>
								<label for="select2">가벼운 운동 (주 1~3일)</label>
								<input id="select2" name="active" type="radio" value="1.375">
							</li>
							<li>
								<label for="select3">보통 (주 3~5일)</label>
								<input id="select3" name="active" type="radio" value="1.55">
							</li>
							<li>
								<label for="select4">적극적으로 운동 (주 5~7일)</label>
								<input id="select4" name="active" type="radio" value="1.725">
							</li>
							<li>
								<label for="select5">매우 적극적으로 운동 (매일)</label>
								<input id="select5" name="active" type="radio" value="1.9">
							</li>
						</ul>
					</div>
					<div class="info-box-03">
						
					</div>
			
	 	</form>
	 </div>
	</div>
