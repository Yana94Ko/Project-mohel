window.onload=function(){
	
	const plusButton = document.querySelectorAll('.myFoodMain-input-menu>img');
	
	for(var i=0; i<plusButton.length; i++){
		plusButton[i].addEventListener("click", function(){
			var next = this.parentNode.nextSibling.nextSibling;
			
			if(next.style.display=="block"){
				next.style.display = "none";
			}else{
				next.style.display = "block";
			}
		});
	}
	document.getElementById("myFoodMain-shareEveryOne").addEventListener("click", function(){
		location.href="/everyFoodWrite";
	});
	
	const dayStr = ['일', '월', '화', '수', '목', '금', '토'];
	const sDay = document.getElementById("selectedDay");
	sDay.innerText = `${dateJson.year}-${dateJson.month}-${dateJson.date}(${dayStr[dateJson.day%7]})`;
	
	const first = new Date(dateJson.year, dateJson.month-1, 1);
	const last = new Date(dateJson.year, dateJson.month, 0);
	const dateList = document.getElementById('myFoodMain-date-ul');
	const lastDay = last.getDate();
	const firstDay = first.getDay();
	//alert("dateJson.year = "+dateJson.year);
	//alert("dateJson.month = "+dateJson.month);
	//alert("dateJson.date = "+dateJson.date);
	//alert("dateJson.day = "+dateJson.day);
	//alert("이번 달의 첫 날의 요일 = "+firstDay);
	
	const url = new URLSearchParams(location.search);
	url.set("year", dateJson.year);
	url.set("month", dateJson.month);
	for(var i=1; i<=lastDay; i++){
		if(dateJson.date==i){
			dateList.innerHTML += `<li><button class="btn btn-secondary" id="today" disabled>${i}일(${dayStr[(firstDay+i-1)%7]})</button></li>`;			
		}else{
			url.set("date", i);
			dateList.innerHTML += `<li><button class="btn btn-info" onclick="location.href='${location.pathname+'?'+url.toString()}'">${i}일(${dayStr[(firstDay+i-1)%7]})</button></li>`;
		}
		
	}

	var calendarEl = document.getElementById('calendar');
	var calendar = new FullCalendar.Calendar(calendarEl, {
		dayRender: function (date) {
        	if(date.date.getDate()==dateJson.date && date.date.getMonth()+1==dateJson.month){
				date.el.style.backgroundColor = 'lightgray';
			}
    	},
		defaultDate: today,
		height:500,
		titleFormat: function(date){
			return date.date.year+"년"+(date.date.month+1)+"월";
		},
		monthNames: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
		monthNamesShort: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
		dayNames: ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"],
		dayNamesShort: ["일", "월", "화", "수", "목", "금", "토"],
		buttonText: {
			today: "오늘",
			month: "월별",
			week: "주별",
			day: "일별",
		},
		plugins: ['interaction', 'dayGrid'],
		header: {
			left: 'prevYear,prev,next,nextYear',
			center: 'title',
			right: 'today'
		},
		selectable: true,
		selectMirror: true,
		select: function(arg) {
			alert("클릭됨");
			url.set("year", arg.start.getFullYear());
			url.set("month", arg.start.getMonth()+1);
			url.set("date", arg.start.getDate());
			location.href = location.pathname+"?"+url.toString();
		}
	});
	calendar.render();
	
	const searchBtn = document.querySelectorAll(".myFoodMain-input-searchBtn");
	
	for(var i=0; i<searchBtn.length; i++){
		searchBtn[i].addEventListener("click", function(){
			var searchWord = this.previousSibling.previousSibling.value;
			if(searchWord==""){
				alert('검색어를 입력하세요');
				return;
			}
			var resultZone = this.nextSibling.nextSibling;
			resultZone.style.display = "block";
			var resultList = resultZone.childNodes[1];
			clear(resultList);
			resultList.innerHTML = `<h3 style="text-align:center;">검색중...</h3>`;
			//[식품영양정보 api 문서 URL]
			//https://www.foodsafetykorea.go.kr/api/newDatasetDetail.do
			//
			//[요청 URL]
			//http://openapi.foodsafetykorea.go.kr/api/인증키/서비스명/
			//	요청파일타입/요청시작위치/요청종료위치/변수명=값&변수명=값2...
			var searchURL = "http://openapi.foodsafetykorea.go.kr/api/4b9137024f7d489884eb/I2790/json/1/50/"
						  + "DESC_KOR="+searchWord;
			$.ajax({
				url: searchURL,
				type: "GET",
				dataType: "json",
				success: function(result){
					console.log(result);
					if(result.I2790.row==null){
						alert('검색 결과가 없습니다');
						resultZone.style.display = "none";
						return;
					}
					console.log(result.I2790.row);
					var results = result.I2790.row;
					var resultLI = "<li>No.</li><li>음식명</li><li>칼로리</li>";
					for(var i=0; i<results.length; i++){
						resultLI += `<li>${results[i].NUM}</li><li>${results[i].DESC_KOR}</li>`
								   +`<li title="[1회 제공량]&nbsp;${results[i].SERVING_SIZE} G">${results[i].NUTR_CONT1} kcal</li>`;
					}
					resultList.innerHTML = resultLI;
				}
			});
		});
	}
	var foods = new Array(5);
	console.log(foodList);
	foodList.forEach(function(content, idx){
		var obj = new Object();
		obj.meals = content.meals;
		obj.foodlist = JSON.parse(content.foodlist);
		obj.foodcodes = JSON.parse(content.foodcodes);
		obj.foodcalories = JSON.parse(content.foodcalories);
		foods[content.meals-1] = obj;
	});
	console.log(foods);
	for(var i=0; i<5; i++){
		console.log(foods[i]==null);
	}
	
};
function clear(parentNode){
	while (parentNode.hasChildNodes()) {
		parentNode.firstChild.remove();
	}
}
