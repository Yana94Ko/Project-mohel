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
						
						<td title="${reply.title }">
							<a <c:if test="${reply.category=='everyMeal'}">href="/mypage/everyFoodView?no=${reply.boardNo}"</c:if>
								<c:if test="${reply.category=='myExercise'}">href="/exercise/exerciseView?no=${reply.boardNo}"</c:if>
								<c:if test="${reply.category!='everyMeal' && reply.category!='myExercise'}">href="/board/boardView?no=${reply.boardNo}&category=${reply.category }"</c:if>
							>${reply.title }</a>
						</td>
						<td title="${reply.contents}">${reply.contents}</td>
						<td>${reply.writedate }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- 페이징 -->
		<c:if test="${myReplyList.size()!=0 }">
			<ul class="paging">
				<!-- 이전 페이지 -->
				<c:if test="${pVO.pageNum<=pVO.onePageCount}">
					<li>Prev</li>
				</c:if>
				<c:if test="${pVO.pageNum>pVO.onePageCount}">
					<li>
						<a href="/mypage/myComment?pageNum=${pVO.startPage-pVO.onePageCount}
						<c:if test="${category!=null}">&category=${category}</c:if>
						<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord}</c:if>">Prev</a>
					</li>
				</c:if>
				
				<!-- 페이지 번호 -->
				<c:forEach var="p" begin="${pVO.startPage}" end="${pVO.startPage+pVO.onePageCount-1}">
					<c:if test="${p<=pVO.totalPage }">
						<c:if test="${p==pVO.pageNum}">
							<li>${p}</li>
						</c:if>
						<c:if test="${p!=pVO.pageNum}">
							<li>
								<a href="/mypage/myComment?pageNum=${p}
								<c:if test="${category!=null}">&category=${category}</c:if>
								<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord}</c:if>">${p}</a>
							</li>
						</c:if>
					</c:if>
				</c:forEach>
				
				<!-- 다음 페이지 -->
				<c:if test="${pVO.totalPage-pVO.startPage<pVO.onePageCount }">
					<li>Next</li>
				</c:if>
				<c:if test="${pVO.totalPage-pVO.startPage>=pVO.onePageCount }">
					<li>
						<a href="/mypage/myComment?pageNum=${pVO.startPage+pVO.onePageCount}
						<c:if test="${category!=null}">&category=${category}</c:if>
						<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord}</c:if>">Next</a>
					</li>
				</c:if>
			</ul>
		</c:if>
	</div>
</div>