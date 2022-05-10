<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 구글폰트 -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">	
<!-- CSS -->
<link rel="stylesheet" href="/css/member/myWrite.css" type="text/css">



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

<div class="board-box">
	<nav class="switch">
		<a href="/member/myWrite">게시글</a>
		<a href="/member/myComment">댓글</a>
		<select class="switch-select">
			<option>전체</option>
			<option>모두의 식단</option>
			<option>모두의 운동</option>
			<option>자유 게시판</option>
			<option>챌린지 게시판</option>
			<option>Before&After</option>
		</select>
	</nav>
	<table>
		<thead>
			<tr>
				<th>게시판</th>
				<th>제목</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>모두의 식단</td>
				<td>글제목 글제목 글제목 글제목 글제목</td>
			</tr>
			<tr>
				<td>자유 게시판</td>
				<td>글제목 글제목 글제목 글제목 글제목</td>
			</tr>
			<tr>
				<td>모두의 운동</td>
				<td>글제목 글제목 글제목 글제목 글제목</td>
			</tr>
			<tr>
				<td>모두의 식단</td>
				<td>글제목 글제목 글제목 글제목 글제목</td>
			</tr>
			<tr>
				<td>자유 게시판</td>
				<td>글제목 글제목 글제목 글제목 글제목</td>
			</tr>
			<tr>
				<td>Before&After</td>
				<td>글제목 글제목 글제목 글제목 글제목</td>
			</tr>
			<tr>
				<td>챌린지 게시판</td>
				<td>글제목 글제목 글제목 글제목 글제목</td>
			</tr>
						<tr>
				<td>모두의 운동</td>
				<td>글제목 글제목 글제목 글제목 글제목</td>
			</tr>
			<tr>
				<td>모두의 식단</td>
				<td>글제목 글제목 글제목 글제목 글제목</td>
			</tr>
			<tr>
				<td>자유 게시판</td>
				<td>글제목 글제목 글제목 글제목 글제목</td>
			</tr>
			<tr>
				<td>챌린지 게시판</td>
				<td>글제목 글제목 글제목 글제목 글제목</td>
			</tr>
		</tbody>
	</table>
</div>