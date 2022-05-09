<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/css/food/myFoodMain.css" type="text/css"/>
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.0/main.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.0/main.css" type="text/css"/>

<script>
	document.addEventListener('DOMContentLoaded', function() {
		var calendarEl = document.getElementById('calendar');
		var calendar = new FullCalendar.Calendar(calendarEl, {
			initialView : 'dayGridMonth',
			plugins: ['interaction', 'dayGrid'],
			header : {
				left : 'prevYear,prev,next,nextYear today',
				center : 'title',
				right : 'dayGridMonth,dayGridWeek,dayGridDay'
			},
			selectable : true,
			selectMirror : true,
			select : function(arg) {
				alert("클릭됨");
				console.log(arg);
			}
		});
		calendar.render();
	});
</script>
<script>
	var dateJson = ${dateJson};
</script>
<script src="/js/food/myFoodMain.js"></script>

<div id="myFoodMain-container">
	<!-- 나만의 식단 메인 왼쪽 -->
	<div id="myFoodMain-date">
		<ul id="myFoodMain-date-ul">
			<!-- 자바스크립트로 날짜 추가 -->
		</ul>
	</div>
	
	<div id="myFoodMain-input"><!-- 나만의 식단 메인 오른쪽 -->
	
		<!-- 제목, 캘린더 -->
		<div id="myFoodMain-input-title">
			<h1>나만의 식단 입력하기</h1>
			<span id="selectedDay"></span>
			<img src="/img/food/calendar.png"/>
		</div>
		
		<!-- 칼로리 표시 영역 -->
		<div id="myFoodMain-input-calorie">
			<div id="myFoodMain-input-calorie-recommend">
				<span>오늘 섭취 권장 칼로리</span>
				<div id="myFoodMain-input-calorie-recommend-bar">
					<!-- 칼로리 막대 표시 필요 -->
				</div>
				<span>1,886 kcal</span>
			</div>
			<div id="myFoodMain-input-calorie-my">
				<span>오늘 나의 식단 칼로리</span>
				<div id="myFoodMain-input-calorie-my-bar">
					<!-- 칼로리 막대 표시 필요 -->
				</div>
				<span>573 kcal</span>
			</div>
		</div>
		
		<!-- 아침 -->
		<div class="myFoodMain-input-menu">
			&nbsp;&nbsp;&nbsp;아침
			<img src="/img/food/plus.png"/>
		</div>
		<div class="myFoodMain-input-searchZone">
			<input type="text" class="myFoodMain-input-search"/>
			<img src="/img/food/searchBtn.png" class="myFoodMain-input-searchBtn"/>
			<div class="myFoodMain-input-searchResult">
				<ul>
					<li>No.</li>
					<li>음식명</li>
					<li>칼로리</li>
					<li>1</li>
					<li>막창</li>
					<li>870 kcal</li>
					<li>2</li>
					<li>마라탕</li>
					<li>1024 kcal</li>
					<li>3</li>
					<li>탕수육</li>
					<li>1353 kcal</li>
					<li>4</li>
					<li>감바스</li>
					<li>715 kcal</li>
					<li>5</li>
					<li>살라미</li>
					<li>776 kcal</li>
				</ul>
			</div>
			<div class="myFoodMain-input-selected">
			</div>
			<div class="myFoodMain-input-calorie-each">
				<span>오늘 나의 식단 칼로리</span>
				<div class="myFoodMain-input-calorie-each-bar">
					<!-- 칼로리 막대 표시 필요 -->
				</div>
				<span>573 kcal</span>
			</div>
			<div class="myFoodMain-input-each-save myFoodMain-btn">저장</div>
		</div>
		
		<!-- 오전간식 -->
		<div class="myFoodMain-input-menu">
			&nbsp;&nbsp;&nbsp;오전간식
			<img src="/img/food/plus.png"/>
		</div>
		<div class="myFoodMain-input-searchZone">
			<input type="text" class="myFoodMain-input-search"/>
			<img src="/img/food/searchBtn.png" class="myFoodMain-input-searchBtn"/>
			<div class="myFoodMain-input-searchResult">
				<ul>
					<li>No.</li>
					<li>음식명</li>
					<li>칼로리</li>
					<li>1</li>
					<li>막창</li>
					<li>870 kcal</li>
					<li>2</li>
					<li>마라탕</li>
					<li>1024 kcal</li>
					<li>3</li>
					<li>탕수육</li>
					<li>1353 kcal</li>
					<li>4</li>
					<li>감바스</li>
					<li>715 kcal</li>
					<li>5</li>
					<li>살라미</li>
					<li>776 kcal</li>
				</ul>
			</div>
			<div class="myFoodMain-input-selected">
			</div>
			<div class="myFoodMain-input-calorie-each">
				<span>오늘 나의 식단 칼로리</span>
				<div class="myFoodMain-input-calorie-each-bar">
					<!-- 칼로리 막대 표시 필요 -->
				</div>
				<span>573 kcal</span>
			</div>
			<div class="myFoodMain-input-each-save myFoodMain-btn">저장</div>
		</div>
		
		<!-- 점심 -->
		<div class="myFoodMain-input-menu">
			&nbsp;&nbsp;&nbsp;점심
			<img src="/img/food/plus.png"/>
		</div>
		<div class="myFoodMain-input-searchZone">
			<input type="text" class="myFoodMain-input-search"/>
			<img src="/img/food/searchBtn.png" class="myFoodMain-input-searchBtn"/>
			<div class="myFoodMain-input-searchResult">
				<ul>
					<li>No.</li>
					<li>음식명</li>
					<li>칼로리</li>
					<li>1</li>
					<li>막창</li>
					<li>870 kcal</li>
					<li>2</li>
					<li>마라탕</li>
					<li>1024 kcal</li>
					<li>3</li>
					<li>탕수육</li>
					<li>1353 kcal</li>
					<li>4</li>
					<li>감바스</li>
					<li>715 kcal</li>
					<li>5</li>
					<li>살라미</li>
					<li>776 kcal</li>
				</ul>
			</div>
			<div class="myFoodMain-input-selected">
			</div>
			<div class="myFoodMain-input-calorie-each">
				<span>오늘 나의 식단 칼로리</span>
				<div class="myFoodMain-input-calorie-each-bar">
					<!-- 칼로리 막대 표시 필요 -->
				</div>
				<span>573 kcal</span>
			</div>
			<div class="myFoodMain-input-each-save myFoodMain-btn">저장</div>
		</div>
		
		<!-- 오후간식 -->
		<div class="myFoodMain-input-menu">
			&nbsp;&nbsp;&nbsp;오후간식
			<img src="/img/food/plus.png"/>
		</div>
		<div class="myFoodMain-input-searchZone">
			<input type="text" class="myFoodMain-input-search"/>
			<img src="/img/food/searchBtn.png" class="myFoodMain-input-searchBtn"/>
			<div class="myFoodMain-input-searchResult">
				<ul>
					<li>No.</li>
					<li>음식명</li>
					<li>칼로리</li>
					<li>1</li>
					<li>막창</li>
					<li>870 kcal</li>
					<li>2</li>
					<li>마라탕</li>
					<li>1024 kcal</li>
					<li>3</li>
					<li>탕수육</li>
					<li>1353 kcal</li>
					<li>4</li>
					<li>감바스</li>
					<li>715 kcal</li>
					<li>5</li>
					<li>살라미</li>
					<li>776 kcal</li>
				</ul>
			</div>
			<div class="myFoodMain-input-selected">
			</div>
			<div class="myFoodMain-input-calorie-each">
				<span>오늘 나의 식단 칼로리</span>
				<div class="myFoodMain-input-calorie-each-bar">
					<!-- 칼로리 막대 표시 필요 -->
				</div>
				<span>573 kcal</span>
			</div>
			<div class="myFoodMain-input-each-save myFoodMain-btn">저장</div>
		</div>
		
		<!-- 저녁 -->
		<div class="myFoodMain-input-menu">
			&nbsp;&nbsp;&nbsp;저녁
			<img src="/img/food/plus.png"/>
		</div>
		<div class="myFoodMain-input-searchZone">
			<input type="text" class="myFoodMain-input-search"/>
			<img src="/img/food/searchBtn.png" class="myFoodMain-input-searchBtn"/>
			<div class="myFoodMain-input-searchResult">
				<ul>
					<li>No.</li>
					<li>음식명</li>
					<li>칼로리</li>
					<li>1</li>
					<li>막창</li>
					<li>870 kcal</li>
					<li>2</li>
					<li>마라탕</li>
					<li>1024 kcal</li>
					<li>3</li>
					<li>탕수육</li>
					<li>1353 kcal</li>
					<li>4</li>
					<li>감바스</li>
					<li>715 kcal</li>
					<li>5</li>
					<li>살라미</li>
					<li>776 kcal</li>
				</ul>
			</div>
			<div class="myFoodMain-input-selected">
			</div>
			<div class="myFoodMain-input-calorie-each">
				<span>오늘 나의 식단 칼로리</span>
				<div class="myFoodMain-input-calorie-each-bar">
					<!-- 칼로리 막대 표시 필요 -->
				</div>
				<span>573 kcal</span>
			</div>
			<div class="myFoodMain-input-each-save myFoodMain-btn">저장</div>
		</div>
		<div class="myFoodMain-btn" id="myFoodMain-shareEveryOne">
			모두의 식단에 공유하기
		</div>
	</div>
</div>
<div id="calendar"></div>