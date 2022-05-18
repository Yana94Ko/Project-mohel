<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/css/mypage/myComment.css" type="text/css">
<script src="/js/mypage/myComment.js"></script>

<div class="mypage-main container">
	<%@include file="/WEB-INF/views/inc/mypage/mypageNav.jsp" %>

	<div class="board-box">
		<h3>나의 활동</h3>
		
		<%@include file="/WEB-INF/views/inc/mypage/myActivitySwitchAndSelectBox.jsp" %>
		
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
				<c:forEach var="reply" items="${myReplyList }">
					<tr>
						<td>
							<c:if test="${reply.category=='everyMeal'}">모두의 식단</c:if>
							<c:if test="${reply.category=='myExercise'}">나만의 운동</c:if>
							<c:if test="${reply.category=='free'}">자유 게시판</c:if>
							<c:if test="${reply.category=='challenge'}">챌린지 게시판</c:if>
							<c:if test="${reply.category=='ba'}">Before&After</c:if>
						</td>
						<td>
							<a <c:if test="${reply.category=='everyMeal'}">href="/mypage/everyFoodView?no=${reply.boardNo}"</c:if>
								<c:if test="${reply.category=='myExercise'}">href="/exercise/exerciseView?no=${reply.boardNo}"</c:if>
								<c:if test="${reply.category!='everyMeal' && reply.category!='myExercise'}">href="/board/boardView?no=${reply.boardNo}&category=${reply.category }"</c:if>
							>${reply.title }</a>
						</td>
						<td>${reply.contents}</td>
						<td>${reply.writedate }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${myReplyList.size()!=0 }">
			<%@include file="/WEB-INF/views/inc/mypage/myActivityPaging.jsp" %>
		</c:if>
	</div>
</div>