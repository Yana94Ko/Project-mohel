<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
<head>
<link href="${url}/css/exercise/every_exerciseList.css" rel="stylesheet"
	type="text/css" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<h1 id='main_text'>모두의 운동</h1>

		<form id="search-box">
			<div class="mx-auto mt-5 search-bar input-group mb-3">
				<input name="q" type="text" class="form-control rounded-pill"
					id="search-keyword" placeholder="함께하고 싶은 운동을 검색해보세요">
			</div>
		</form>
		<div id="recruitment_box">
			<button type="submit" id="recruitment" class="btn btn-primary"
				onclick="location.href='/exercise/every_exerciseWrite'">운동원
				모집하기</button>
		</div>

	<div id="thumbnail-list">
		<c:forEach var="vo" items="${lst }" varStatus="st">
			<div id="wrap">
				<div class="service_item"
					onclick="location.href='/exercise/every_exerciseView?no=${vo.no}'">
					<h6 class="m-top-30">
						<c:if test="${vo.img!=null}">
							<img src="/img/every_exercise/${vo.img}" name="img"
								onmouseover="this.style.transform='scale(1.1)'; this.style.transition='all 0.5s'"
								onmouseleave="this.style.transform='scale(1)'"
								id="every_exercise_img">
						</c:if>
						<c:if test="${vo.img==null}">
							<img src="/img/every_exercise/dumbbell.png" name="img"
								onmouseover="this.style.transform='scale(1.1)'; this.style.transition='all 0.5s'"
								onmouseleave="this.style.transform='scale(1)'"
								id="every_exercise_img">
						</c:if>
					</h6>
					<div class="separator_small"></div>
					<p id="thumbnail-text" class="thumb-text">
						<strong>${vo.title }</strong><br />${vo.hashtag}<br />${vo.startdate }
						- ${vo.enddate } <br />
						<textarea style="display: none" id="placeinfo${st.index}">${vo.placeinfo}</textarea>
						장소 <span id="addressname${st.index}"></span> <br />참가자 수
						${vo.applicantCnt} / 최대 인원 ${vo.applicantMax }<br />조회수 ${vo.hit}
					</p>
				</div>
			</div>
		</c:forEach>

	</div>
	<div style="clear: both"></div>
	<!-- 페이징 -->
	<br />
	<!-- 페이징 -->
	<ul class="paging">
		<!-- 이전페이지 -->
		<c:if test="${pVO.pageNum==1}">
			<li><a href="#"><img
					src="${url}/img/exercise/left-arrow.png" id="left-btn">prev</a>
		</c:if>
		<c:if test="${pVO.pageNum>1}">
			<li><a
				href="/exercise/every_exerciseList?pageNum=${pVO.pageNum-1}<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord }</c:if>"><img
					src="${url}/img/exercise/left-arrow.png" id="left-btn">prev</a></li>
		</c:if>
		<!-- 페이지 번호 -->
		<c:forEach var="p" begin="${pVO.startPage}"
			end="${pVO.startPage+pVO.onePageCount-1}">
			<!-- 총페이지수 보다 출력할 페이지 번호가 작을때 -->
			<c:if test="${p<=pVO.totalPage}">
				<c:if test="${p==pVO.pageNum}">
					<li>
				</c:if>
				<c:if test="${p!=pVO.pageNum }">
					<li>
				</c:if>
				<a
					href="/exercise/every_exerciseList?pageNum=${p}<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord }</c:if>">${p}</a>
				</li>
			</c:if>

		</c:forEach>

		<!-- 다음페이지 -->
		<c:if test="${pVO.pageNum==pVO.totalPage }">
			<li>next<a href="#"><img
					src="${url}/img/exercise/right-arrow.png" id="right-btn"></a></li>
		</c:if>
		<c:if test="${pVO.pageNum<pVO.totalPage }">
			<li><a
				href="/exercise/every_exerciseList?pageNum=${pVO.pageNum+1 }<c:if test='${pVO.searchWord!=null}'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord }</c:if>">next<img
					src="${url}/img/exercise/right-arrow.png" id="right-btn"></a></li>
		</c:if>

	</ul>

	</div>
	<script type="text/javascript"
		src="${url}/js/exercise/every_exerciseList.js"></script>