<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="<%=request.getContextPath()%>" />
<link rel="stylesheet" href="/css/home.css" type="text/css">

<main class="main">
	<div class="body-01">

		<div class="carousel">
			<div class="carousel-inner">
				<div style="background-image: url('/img/mohel-main-01.png')"
					class="carousel-item"></div>
				<div style="background-image: url('/img/mohel-main-02.png')"
					class="carousel-item"></div>
				<div style="background-image: url('/img/mohel-main-03.png')"
					class="carousel-item"></div>

			</div>
			<div class="carousel-controls">
				<span class="prev"></span> <span class="next"></span>
			</div>
			<div class="carousel-indicators"></div>
		</div>
	</div>
	<div class="body-02">
		<div class="pricing-table">
			<div class="pricing-card">
				<div class="pricing-card-01"
					onclick="location.href='${url}/myFoodMain'">
					<h3 class="pricing-card-header">나만의 식단</h3>
					<div class="price">🥄</div>
					<ul>
						<li><strong>나만의 식단</strong>으로<br>
						오늘 먹은 음식<br>
						<strong>기록하기!</strong></li>

					</ul>
				</div>
			</div>

			<div class="pricing-card">
				<div class="pricing-card-02"
					onclick="location.href='${url}/everyFoodList'">
					<h3 class="pricing-card-header">모두의 식단</h3>
					<div class="price">🥗</div>
					<ul>
						<li><strong>모두의 식단</strong>으로<br>
						나의 식단들을<br>
						<strong>공유하기!</strong></li>
					</ul>
				</div>
			</div>

			<div class="pricing-card">
				<div class="pricing-card-03"
					onclick="location.href='${url}/exercise/exerciseList'">
					<h3 class="pricing-card-header">나만의 운동</h3>
					<div class="price">🏊‍♂️</div>
					<ul>
						<li><strong>나만의 운동</strong>으로<br>
						오늘의 운동을<br>
						<strong>기록하기!</strong></li>
					</ul>
				</div>
			</div>
			<div class="pricing-card">
				<div class="pricing-card-04"
					onclick="location.href='${url}/exercise/every_exerciseList'">
					<h3 class="pricing-card-header">모두의 운동</h3>
					<div class="price">‍⛹️‍♀️⛹️‍♂️</div>
					<ul>
						<li><strong>모두의 운동</strong>으로<br>
						같이 운동할 사람<br>
						<strong>모집하기!</strong></li>
					</ul>

				</div>
			</div>
		</div>
	</div>
	<div class="body-03">
		<h3>
			👍 <strong>모두의 식단</strong> 👍
		</h3>
		<div class="every-01">
			<c:forEach var="fvo" items="${everyFoodBest}">
				<div class="pic1">
					<a href="/everyFoodView?no=${fvo.no}" class="pic">
						<img src="/img/food/${fvo.img1}">
					</a>
					<ul>
						<li><strong>[${fvo.meals}]</strong>&nbsp;${fvo.title}</li>
						<li>${fvo.writedate} | 조회수 ${fvo.hit}</li>
					</ul>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="body-03">

		<h3>
			🥇 <strong>모두의 운동</strong> 🥇
		</h3>
		<div class="every-01">
			<c:forEach var="vo" items="${lst}" varStatus="st">
				<div class="pic1">
					<a href="/exercise/every_exerciseView?no=${vo.no}" class="pic">
					<c:if test="${vo.img!=null}">
					<img src="/img/every_exercise/${vo.img}" name="img">
					</a>
					</c:if>
					<c:if test="${vo.img==null}">
					<img src="/img/every_exercise/dumbbell.png" name="img">
					</c:if>
					<p id="thumbnail-text" class="thumb-text">
						<strong>${vo.title }</strong><br />${vo.hashtag}<br />${vo.startdate }
						- ${vo.enddate } <br />
						<textarea style="display: none" id="placeinfo${st.index}">${vo.placeinfo}</textarea>
						장소 <span id="addressname${st.index}"></span> <br />참가자 수
						${vo.applicantCnt} / 최대 인원 ${vo.applicantMax }<br />조회수 ${vo.hit}
					</p>
				</div>
			</c:forEach>
		</div>
	</div>

	<script src="/js/home.js"></script>
</main>
