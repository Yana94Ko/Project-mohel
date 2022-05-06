<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="<%=request.getContextPath()%>" />

<!DOCTYPE html>

<html>
<head>

<!-- Bootstrap CSS by bootswatch -->
<link
	href="https://cdn.jsdelivr.net/npm/bootswatch@5.0.1/dist/minty/bootstrap.min.css"
	rel="stylesheet" crossorigin="anonymous" />
<link href="${url}/css/exercise/exercise.css" rel="stylesheet"
	type="text/css" />

<meta charset="UTF-8">
<style>
/*검색바*/
.search-bar {
	width: 500px;
	height: 50px;
	position: relative;
	left: 15px;
}
/*나만의 운동기록 버튼*/
#write {
	width: 400px;
	left: 0px;
	right: 0;
	margin: auto;
	position: relative;
}
/*나만의 운동기록리스트*/
#exercise-main-tbl {
	position: relative;
	margin-top: 20px;
}
#exercise-main-tbl{
	overflow:hidden;
	width:100%;
	height:80px;
	text-align:center;
	border-bottom: 1px solid #bdbdbd;
	font-weight: 600;
}
#exercise-head>th:nth-child(6n+2){
	overflow:hidden;
	text-overflow:hidden;
	width:20%;
}
#exercise-head>th:nth-child(6n+3){
	overflow:hidden;
	text-overflow:hidden;
	width:45%;
}
</style>
<script>
$(function(){
    $("#search").keyup(function(){
        var value = $(this).val().toLowerCase();
        
        $("#searchList>tr").filter(function(){
            var idx = $(this).text().toLowerCase().indexOf(value); 
            $(this).toggle(idx>-1);
        });
    });
});

</script>
<title>나만의 운동</title>

</head>
<body>
	<div class="container" style="text-align: center">
		<h1 id='main_text'>나의 운동</h1>

		<div>
			<form id="search-box">
				<div class="mx-auto mt-5 search-bar input-group mb-3">
					<input name="q" type="text" class="form-control rounded-pill"
						placeholder="함께하고 싶은 운동을 검색해보세요">

				</div>
			</form>
			<div id='write-box'>
				<button type="button" class="btn btn-primary" id='write'
					onclick="location.href='/exercise/exerciseWrite?category=운동&nickname=ㅇㅇ' ">나만의
					운동 글쓰기</button>
			</div>
		</div>



		<table class="table table-hover" id="exercise-main-tbl">
			<thead id="exercise-tbl">
				<tr id="exercise-head">
					<th scope="col">번호</th>
					<th scope="col">제목</th>
					<th scope="col">내용</th>
					<th scope="col">작성자</th>
					<th scope="col">작성일</th>
					<th scope="col">조회수</th>
				</tr>
			</thead>
			<tbody id="exercise-contents">

				<c:forEach var="vo" items="${lst }">
					<tr class="table-light"
						onclick="location.href='/exercise/exerciseView?no=${vo.no}'" id="exercise-head">

						<td>${vo.no }</td>
						<td>${vo.title }</td>
						<td>${vo.contents }</td>
						<td>${vo.nickname }</td>
						<td>${vo.writedate }</td>
						<td>${vo.hit }</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
			<!-- 페이징 -->
	<br/>
	<ul class="paging">
		<!-- 이전페이지 -->		
		<c:if test="${pVO.pageNum==1}">
			<li><a href="#">prev</a>
		</c:if>
		<c:if test="${pVO.pageNum>1}">
			<li><a href="/myapp/exercise/exerciseList?pageNum=${pVO.pageNum-1}<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord }</c:if>">prev</a></li>
		</c:if>
		<!-- 페이지 번호 -->
		<c:forEach var="p" begin="${pVO.startPage}" end="${pVO.startPage+pVO.onePageCount-1}">
			<!-- 총페이지수 보다 출력할 페이지 번호가 작을때 -->
			<c:if test="${p<=pVO.totalPage}">
				<c:if test="${p==pVO.pageNum}">
					<li style="background-color:red">
				</c:if>
				<c:if test="${p!=pVO.pageNum }">
					<li>
				</c:if>
				<li><a href="/myapp/exercise/exerciseList?pageNum=${p}<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord }</c:if>">${p}</a></li>
			</c:if>
		</c:forEach>
			
		<!-- 다음페이지 -->
		<c:if test="${pVO.pageNum==pVO.totalPage }">
			<li>next</li>
		</c:if>
		<c:if test="${pVO.pageNum<pVO.totalPage }">
			<li><a href="/myapp/exercise/exerciseList?pageNum=${pVO.pageNum+1 }<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord }</c:if>">next</a></li>
		</c:if>
			
	</ul>
</div>
</body>
</html>