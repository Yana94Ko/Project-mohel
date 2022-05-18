<!-- 페이징 -->
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