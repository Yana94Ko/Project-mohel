<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/css/mypage/myComment.css" type="text/css">

<div class="mypage-main container">
	<%@include file="/WEB-INF/views/inc/mypage/mypageNav.jsp" %>

	<div class="board-box">
		<h3>나의 활동</h3>
		<nav class="switch">
			<a href="/mypage/myWrite">게시글</a>
			<a href="/mypage/myComment">댓글</a>
			<select id="selectCategory" class="switch-select">
				<option value="" <c:if test="${category==null}">selected</c:if>>전체</option>
				<option value="everyMeal" <c:if test="${category=='everyMeal'}">selected</c:if>>모두의 식단</option>
				<option value="myExercise" <c:if test="${category=='myExercise'}">selected</c:if>>나만의 운동</option>
				<option value="free" <c:if test="${category=='free'}">selected</c:if>>자유 게시판</option>
				<option value="challenge" <c:if test="${category=='challenge'}">selected</c:if>>챌린지 게시판</option>
				<option value="ba" <c:if test="${category=='ba'}">selected</c:if>>Before&After</option>
			</select>
		</nav>
		<table>
			<thead>
				<tr>
					<th>게시판</th>
					<th>제목</th>
					<th>댓글 내용</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>모두의 식단</td>
					<td>제목</td>
					<td>댓글내용 댓글내용 댓글내용 댓글내용</td>
					<td>05-16</td>
				</tr>
				<tr>
					<td>자유 게시판</td>
					<td>댓글내용 댓글내용 댓글내용 댓글내용</td>
				</tr>
				<tr>
					<td>모두의 운동</td>
					<td>댓글내용 댓글내용 댓글내용 댓글내용</td>
				</tr>
				<tr>
					<td>모두의 식단</td>
					<td>댓글내용 댓글내용 댓글내용 댓글내용</td>
				</tr>
				<tr>
					<td>자유 게시판</td>
					<td>댓글내용 댓글내용 댓글내용 댓글내용</td>
				</tr>
				<tr>
					<td>Before&After</td>
					<td>댓글내용 댓글내용 댓글내용 댓글내용</td>
				</tr>
				<tr>
					<td>챌린지 게시판</td>
					<td>댓글내용 댓글내용 댓글내용 댓글내용</td>
				</tr>
				<tr>
					<td>모두의 운동</td>
					<td>댓글내용 댓글내용 댓글내용 댓글내용</td>
				</tr>
				<tr>
					<td>모두의 식단</td>
					<td>댓글내용 댓글내용 댓글내용 댓글내용</td>
				</tr>
				<tr>
					<td>자유 게시판</td>
					<td>댓글내용 댓글내용 댓글내용 댓글내용</td>
				</tr>
				<tr>
					<td>챌린지 게시판</td>
					<td>댓글내용 댓글내용 댓글내용 댓글내용</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>