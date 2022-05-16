<!-- 구글폰트 -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
<!-- CSS -->
<link rel="stylesheet" href="/css/mypage/myWrite.css" type="text/css">

<div class="mypage-main container">
	<%@include file="/WEB-INF/views/inc/mypage/mypageNav.jsp" %>

	<div class="board-box">
		<nav class="switch">
			<a href="/mypage/myWrite">게시글</a> <a href="/mypage/myComment">댓글</a> <select class="switch-select">
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
</div>