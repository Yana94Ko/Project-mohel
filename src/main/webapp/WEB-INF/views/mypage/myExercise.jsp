<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/css/mypage/myExercise.css" type="text/css">

<div class="mypage-main container">
	<%@include file="/WEB-INF/views/inc/mypage/mypageNav.jsp" %>

	<div class="board-box">
		<h3>참여한 운동</h3>
		<table>
			<thead>
				<tr>
					<th>게시자</th>
					<th>제목</th>
					<th>상태</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="myExercise" items="${myExerciseList}">
					<tr class="post-info">
						<td rowspan="2">${myExercise.leader}</td>
						<td title="${myExercise.title}"><a href="/exercise/every_exerciseView?no=${myExercise.no}">${myExercise.title}</a></td>
						<c:if test="${myExercise.status==1 }"><td rowspan="2" style="color: green;">참여</td></c:if>
						<c:if test="${myExercise.status==0 }"><td rowspan="2" style="color: red;">신청 중...</td></c:if>
					</tr>
					<tr class="hashtag">
						<td title="${myExercise.hashtag}"><a href="/exercise/every_exerciseView?no=${myExercise.no}">${myExercise.hashtag}</a></td>
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
						<a href="/mypage/myExercise?pageNum=${pVO.startPage-pVO.onePageCount}
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
								<a href="/mypage/myExercise?pageNum=${p}
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
						<a href="/mypage/myExercise?pageNum=${pVO.startPage+pVO.onePageCount}
						<c:if test="${category!=null}">&category=${category}</c:if>
						<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord}</c:if>">Next</a>
					</li>
				</c:if>
			</ul>
		</c:if>
	</div>
</div>