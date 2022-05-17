<link rel="stylesheet" href="/css/mypage/myWrite.css" type="text/css">
<script src="/js/mypage/myWrite.js"></script>
<div class="mypage-main container">
	<%@include file="/WEB-INF/views/inc/mypage/mypageNav.jsp" %>
	
	<div class="board-box">
		<h3>나의 활동</h3>
		<nav class="switch">
			<a href="/mypage/myWrite">게시글</a>
			<a href="/mypage/myComment">댓글</a>
			<select id="selectCategory" class="switch-select">
				<option value="">전체</option>
				<option value="everyMeal">모두의 식단</option>
				<option value="myExercise">나만의 운동</option>
				<option value="free">자유 게시판</option>
				<option value="challenge">챌린지 게시판</option>
				<option value="ba">Before&After</option>
			</select>
		</nav>
		<table>
			<thead>
				<tr>
					<th>게시판</th>
					<th>제목</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bList" items="${myAllBoardList }">
					<tr>
						<td>
							<c:if test="${bList.category=='everyMeal' }">모두의 식단</c:if>
							<c:if test="${bList.category=='myExercise' }">나만의 운동</c:if>
							<c:if test="${bList.category=='free' }">자유 게시판</c:if>
							<c:if test="${bList.category=='challenge' }">챌린지 게시판</c:if>
							<c:if test="${bList.category=='ba' }">Before&After</c:if>
						</td>
						<td><a href="#">${bList.title }</a></td>
						<td>${bList.writedate }</td>
						<td>${bList.hit }</td>
					</tr>
				</c:forEach>
				<!-- <tr>
					<td>모두의 식단</td>
					<td>글제목 글제목 글제목 글제목 글제목</td>
					<td>2022-05-06</td>
					<td>1</td>
				</tr>
				<tr>
					<td>자유 게시판</td>
					<td>글제목 글제목 글제목 글제목 글제목</td>
					<td>2022-05-06</td>
					<td>1</td>
				</tr>
				<tr>
					<td>모두의 운동</td>
					<td>글제목 글제목 글제목 글제목 글제목</td>
					<td>2022-05-06</td>
					<td>1</td>
				</tr>
				<tr>
					<td>모두의 식단</td>
					<td>글제목 글제목 글제목 글제목 글제목</td>
					<td>2022-05-06</td>
					<td>2</td>
				</tr> -->
			</tbody>
		</table>
	</div>
</div>