<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			</tbody>
		</table>
		<!-- 페이징 -->
		<ul class="paging">
			<!-- 이전 페이지 -->
			<c:if test="${pVO.pageNum<=pVO.onePageCount}">
				<li>prev</li>
			</c:if>
			<c:if test="${pVO.pageNum>pVO.onePageCount}">
				<li>
					<a href="/mypage/myWrite?pageNum=${pVO.startPage-pVO.onePageCount}
					<c:if test="${category!=null}">&category=${category}</c:if>
					<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord}</c:if>">prev</a>
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
				<li>next</li>
			</c:if>
			<c:if test="${pVO.totalPage-pVO.startPage>=pVO.onePageCount }">
				<li>
					<a href="/mypage/myWrite?pageNum=${pVO.startPage+pVO.onePageCount}
					<c:if test="${category!=null}">&category=${category}</c:if>
					<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord}</c:if>">next</a>
				</li>
			</c:if>
		</ul>
	</div>
</div>