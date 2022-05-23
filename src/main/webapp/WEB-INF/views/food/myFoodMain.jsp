<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/css/food/myFoodMain.css" type="text/css"/>
<link href='/fullcalendar/core/main.css' rel='stylesheet' />
<link href='/fullcalendar/daygrid/main.css' rel='stylesheet' />
<link href='/fullcalendar/timegrid/main.css' rel='stylesheet' />
<script src='/fullcalendar/core/main.js'></script>
<script src='/fullcalendar/interaction/main.js'></script>
<script src='/fullcalendar/daygrid/main.js'></script>
<script src='/fullcalendar/timegrid/main.js'></script>

<script>
	var dateJson = ${dateJson};
	var today = '${today}';
	var foodList = ${foodList};
	var recommendCal = ${recommendCal};
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
			<img src="/img/food/calendar.png" data-toggle="modal" data-target="#exampleModal"/>
		</div>
		
<!-- 칼로리 표시 영역 -->
		<div id="myFoodMain-input-calorie">
			<div id="myFoodMain-input-calorie-recommend">
				<span>오늘 섭취 권장 칼로리</span>
				<div id="myFoodMain-input-calorie-recommend-bar"></div>
				<span><span id="recommendCalorie">${recommendCal}</span> kcal</span>
			</div>
			<div id="myFoodMain-input-calorie-my">
				<span>오늘 나의 식단 칼로리</span>
				<div id="myFoodMain-input-calorie-my-bar">
					<div id="my-bar-inner"></div>
				</div>
				<span><span id="todayCalorieTotal">0</span> kcal</span>
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
					
				</ul>
			</div>
			<div class="myFoodMain-input-selected" id="selected1">
			</div>
			<div class="myFoodMain-input-calorie-each">
				<span>아침 식단 칼로리</span>
				<div class="myFoodMain-input-calorie-each-bar">
					<!-- 칼로리 막대 표시 필요 -->
				</div>
				<span><span id="todayCalorie1">0</span> kcal</span>
			</div>
			<div class="myFoodMain-input-each-save myFoodMain-btn" id="myFoodBtn1">저장</div>
			<div class="myFoodMain-btn myFoodMain-shareEveryOne">
				모두의 식단에 공유하기
			</div>
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
					
				</ul>
			</div>
			<div class="myFoodMain-input-selected" id="selected2">
			</div>
			<div class="myFoodMain-input-calorie-each">
				<span>오전간식 식단 칼로리</span>
				<div class="myFoodMain-input-calorie-each-bar">
					<!-- 칼로리 막대 표시 필요 -->
				</div>
				<span><span id="todayCalorie2">0</span> kcal</span>
			</div>
			<div class="myFoodMain-input-each-save myFoodMain-btn" id="myFoodBtn2">저장</div>
			<div class="myFoodMain-btn myFoodMain-shareEveryOne">
				모두의 식단에 공유하기
			</div>
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
					
				</ul>
			</div>
			<div class="myFoodMain-input-selected" id="selected3">
			</div>
			<div class="myFoodMain-input-calorie-each">
				<span>점심 식단 칼로리</span>
				<div class="myFoodMain-input-calorie-each-bar">
					<!-- 칼로리 막대 표시 필요 -->
				</div>
				<span><span id="todayCalorie3">0</span> kcal</span>
			</div>
			<div class="myFoodMain-input-each-save myFoodMain-btn" id="myFoodBtn3">저장</div>
			<div class="myFoodMain-btn myFoodMain-shareEveryOne">
				모두의 식단에 공유하기
			</div>
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
					
				</ul>
			</div>
			<div class="myFoodMain-input-selected" id="selected4">
			</div>
			<div class="myFoodMain-input-calorie-each">
				<span>오후간식 식단 칼로리</span>
				<div class="myFoodMain-input-calorie-each-bar">
					<!-- 칼로리 막대 표시 필요 -->
				</div>
				<span><span id="todayCalorie4">0</span> kcal</span>
			</div>
			<div class="myFoodMain-input-each-save myFoodMain-btn" id="myFoodBtn4">저장</div>
			<div class="myFoodMain-btn myFoodMain-shareEveryOne">
				모두의 식단에 공유하기
			</div>
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
					
				</ul>
			</div>
			<div class="myFoodMain-input-selected" id="selected5">
			</div>
			<div class="myFoodMain-input-calorie-each">
				<span>저녁 식단 칼로리</span>
				<div class="myFoodMain-input-calorie-each-bar">
					<!-- 칼로리 막대 표시 필요 -->
				</div>
				<span><span id="todayCalorie5">0</span> kcal</span>
			</div>
			<div class="myFoodMain-input-each-save myFoodMain-btn" id="myFoodBtn5">저장</div>
			<div class="myFoodMain-btn myFoodMain-shareEveryOne">
				모두의 식단에 공유하기
			</div>
		</div>
	</div>
</div>
<!-- 날짜 선택 모달 -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">날짜 선택</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="calendar">
      
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
