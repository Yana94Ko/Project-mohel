<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/css/food/everyFoodList.css" type="text/css"/>
<script type="text/javascript" src="/js/pagination.js"></script>
<script>
	var pageNum = ${pvo.pageNum};
	var totalPage = ${pvo.totalPage};
</script>
<script src="/js/food/everyFoodList.js"></script>
<div id="everyFoodList-container">
	<div id="everyFoodList-best">
		<h3>이번 달의 베스트 유저</h3>
		<ul>
			<c:forEach var="user" items="${bestUser}" varStatus="vs">
				<li>${vs.index+1}. ${user}</li>
			</c:forEach>
		</ul>
	</div>
	<div id="everyFoodList-view">
		<h1>모두의 식단</h1>
		<ul id="everyFoodList-contents">
			<c:forEach var="info" items="${everyMealList}">
				<li onclick="location.href='/everyFoodView?no=${info.no}'">
					<div>
						<img class="everyFoodList-thumbnail" src="img/food/${info.img1}">
					</div>
					<span class="everyFoodList-title" title="${info.title}">${info.title}</span>
					<span class="partAndCal">
						<strong>[${info.contents}]</strong> ${info.sumcalories}kcal
					</span>
					<div class="dateAndHit">
							${info.writedate}&nbsp;&nbsp;&nbsp;&nbsp;조회수 ${info.hit}
					</div>
					<div class="everyFoodList-nickname">
						<strong>[작성자]</strong> ${info.nickname}
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div id="everyFoodList-pagination"></div>
</div>