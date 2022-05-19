<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/css/mypage/myWrite.css" type="text/css">
<script src="/js/mypage/myWrite.js"></script>
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
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bList" items="${myBoardList }">
					<tr>
						<td>
							<c:if test="${bList.category=='everyMeal'}">모두의 식단</c:if>
							<c:if test="${bList.category=='myExercise'}">나만의 운동</c:if>
							<c:if test="${bList.category=='free'}">자유 게시판</c:if>
							<c:if test="${bList.category=='challenge'}">챌린지 게시판</c:if>
							<c:if test="${bList.category=='ba'}">Before&After</c:if>
						</td>
						
						<td title="${bList.title }">
							<a <c:if test="${bList.category=='everyMeal'}">href="/mypage/everyFoodView?no=${bList.no}"</c:if>
								<c:if test="${bList.category=='myExercise'}">href="/exercise/exerciseView?no=${bList.no}"</c:if>
								<c:if test="${bList.category!='everyMeal' && bList.category!='myExercise'}">href="/board/boardView?no=${bList.no}&category=${bList.category }"</c:if>
							>${bList.title }</a>
						</td>
						<td>${bList.writedate }</td>
						<td>${bList.hit }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- 페이징 -->
		<c:if test="${myBoardList.size()!=0 }">
			<ul class="paging">
				<!-- 이전 페이지 -->
				<c:if test="${pVO.pageNum<=pVO.onePageCount}">
					<li>Prev</li>
				</c:if>
				<c:if test="${pVO.pageNum>pVO.onePageCount}">
					<li>
						<a href="/mypage/myWrite?pageNum=${pVO.startPage-pVO.onePageCount}
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
								<a href="/mypage/myWrite?pageNum=${p}
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
						<a href="/mypage/myWrite?pageNum=${pVO.startPage+pVO.onePageCount}
						<c:if test="${category!=null}">&category=${category}</c:if>
						<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord}</c:if>">Next</a>
					</li>
				</c:if>
			</ul>
		</c:if>
	</div>
</div>