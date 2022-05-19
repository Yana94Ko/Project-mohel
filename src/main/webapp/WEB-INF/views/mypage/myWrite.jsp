<link rel="stylesheet" href="/css/mypage/myWrite.css" type="text/css">

<div class="mypage-main container">
	<%@include file="/WEB-INF/views/inc/mypage/mypageNav.jsp" %>
	
	<div class="board-box">
		<h3>나의 활동</h3>
		<nav class="switch">
			<a href="/mypage/myWrite">게시글</a>
			<a href="/mypage/myComment">댓글</a>
			<select class="switch-select">
				<option value="all">전체</option>
				<option value="">나만의 식단</option>
				<option value="">모두의 식단</option>
				<option value="">나만의 운동</option>
				<option value="">자유 게시판</option>
				<option value="">챌린지 게시판</option>
				<option value="">Before&After</option>
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