<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/css/food/everyFoodView.css" type="text/css"/>
<script>
	var foodlist = ${bvo.foodlist};
	var foodcalories = ${bvo.foodcalories};
	var boardNo = ${bvo.no};
</script>
<script src="/js/food/everyFoodView.js"></script>
<div id="everyFoodView-container">
	<div id="titleBox">
		<h2>${bvo.title}</h2>
		<div id="otherData">
			<span>${bvo.nickname}</span>
			&nbsp;&nbsp;/&nbsp;&nbsp;
			<span>${bvo.writedate}</span>
			&nbsp;&nbsp;/&nbsp;&nbsp;
			<img src="img/food/hitIcon-resized.png">
			<span>&nbsp;${bvo.hit}</span>
		</div>
	</div>
	<div id="foodMeta">
		<h4>[${bvo.meals}]&nbsp;${bvo.sumcalories}&nbsp;kcal</h4>
	</div>
	<c:if test="${userInfo.nickname==bvo.nickname}">
		<div id="modifyAndDel">
			<button class="btn btn-secondary" onclick="boardEdit(${bvo.no})">수정</button>
			<button class="btn btn-secondary" onclick="boardDel(${bvo.no})">삭제</button>
		</div>
	</c:if>
	<div id="foodList">
		<!-- 자바스크립트로 음식 목록 표시 -->
	</div>
	<div id="foodImages">
		<c:if test="${bvo.img1!=null}">
			<img src="img/food/${bvo.img1}">
		</c:if>
		<c:if test="${bvo.img2!=null}">
			<img src="img/food/${bvo.img2}">
		</c:if>
		<c:if test="${bvo.img3!=null}">
			<img src="img/food/${bvo.img3}">
		</c:if>
	</div>
	<div id="contents">
		${bvo.contents}
	</div>
	<h5>댓글 작성</h5>
	<form id="replyFrm" method="post" action="/everyFoodReplyOk">
		<input type="hidden" name="no" value="${bvo.no}">
		<textarea id="replyContent" name="contents"></textarea>
		<button id="replyOkBtn">등록</button>
	</form>
	<div id="replyList">
		<c:forEach var="re" items="${reply}">
			<div class="replyEach">
				<div class="replyTop">
					<span>${re.nickname}</span>
					<span>${re.writedate}</span>
				</div>
				<div class="replyBottom">
					<p>${re.contents}</p>
					<c:if test="${userInfo.nickname==re.nickname}">
						<div class="buttonZone">
							<button class="replyEditBtn">수정</button>
							<button onclick="replyDel(${re.no})">삭제</button>
						</div>
					</c:if>
				</div>
				<form class="replyEditFrm" method="post" action="/replyEditOk">
					<input type="hidden" name="boardNo" value="${bvo.no}">
					<input type="hidden" name="no" value="${re.no}">
					<textarea class="replyEditContent" name="contents">${re.contents}</textarea>
					<button class="replyEditOkBtn">등록</button>
				</form>
			</div>
		</c:forEach>
	</div>
</div>