//각 파트별 식단 정보(아침,오전간식 등)
var foods = new Array(5);
//각 파트별 총 칼로리
var todayCalArr = [0,0,0,0,0];
var DBexist = [false,false,false,false,false];
//[jsp에서 선언된 변수]
//dateJson, today, foodList, recommendCal
window.onload=function(){
	
	/*document.getElementById("myFoodMain-shareEveryOne").addEventListener("click", function(){
		location.href="/everyFoodWrite";
	});*/
	
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
			url.set("year", arg.start.getFullYear());
			url.set("month", arg.start.getMonth()+1);
			url.set("date", arg.start.getDate());
			location.href = location.pathname+"?"+url.toString();
		}
	});
	calendar.render();
	
	const searchBtn = document.querySelectorAll(".myFoodMain-input-searchBtn");
	const searchInput = document.querySelectorAll(".myFoodMain-input-search");
	
	for(var i=0; i<searchBtn.length; i++){
		searchInput[i].idx = i;
		searchInput[i].addEventListener("keyup", function(event){
			if(event.keyCode==13){
				searchBtn[this.idx].click();
			}
		});
		searchBtn[i].idx = i;
		searchBtn[i].addEventListener("click", function(){
			var clickIdx = this.idx;
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
					
					for(var j=0; j<results.length; j++){
						var name = results[j].DESC_KOR;
						var cal = Math.floor(results[j].NUTR_CONT1);
						var code = results[j].FOOD_CD;
						resultLI += `<li>${results[j].NUM}</li>`
								   +`<li class="foodSearchResult" title="[제조사] ${results[j].MAKER_NAME}" onclick="addFood('${code}','${name}',${cal},${clickIdx})">`
								   +`${results[j].DESC_KOR}</li>`
								   +`<li title="[1회 제공량]&nbsp;${results[j].SERVING_SIZE} G">${Math.floor(results[j].NUTR_CONT1)} kcal</li>`;
					}
					resultList.innerHTML = resultLI;
				}
			});//ajax 끝
			
		});//검색버튼 addEventListener 끝
	}
	console.log("-----DB에서 가져온 식단 정보(JSON 파싱 전)(foodList)-----");
	console.log(foodList);
	foodList.forEach(function(content){
		DBexist[content.meals-1] = true;
		var obj = new Object();
		obj.meals = content.meals;
		obj.foodlist = JSON.parse(content.foodlist);
		obj.foodcodes = JSON.parse(content.foodcodes);
		obj.foodcalories = JSON.parse(content.foodcalories);
		obj.foodcalories.forEach(function(cal){
			todayCalArr[obj.meals-1] += cal;
		});
		document.getElementById("todayCalorie"+obj.meals).innerText = todayCalArr[obj.meals-1];
		foods[obj.meals-1] = obj;
	});
	var todayCalTotal = 0;
	for(var index in todayCalArr){
		todayCalTotal += todayCalArr[index];
	}
	updateTotalCalorie(todayCalTotal, true);
	
	const plusButton = document.querySelectorAll('.myFoodMain-input-menu>img');
	const searchZone = document.getElementsByClassName('myFoodMain-input-searchZone');
	
	for(var i=0; i<plusButton.length; i++){
		if(DBexist[i]){
			searchZone[i].style.display = "block";
		}else{
			searchZone[i].style.display = "none";
		}
		plusButton[i].addEventListener("click", function(){
			var next = this.parentNode.nextSibling.nextSibling;
			
			if(next.style.display=="block"){
				next.style.display = "none";
			}else{
				next.style.display = "block";
			}
		});
	}
	
	console.log("-----각 파트별 총 칼로리(todayCalArr)-----");
	console.log(todayCalArr);
	console.log("-----DB에서 가져온 식단 정보(JSON 파싱 후)(foods)-----");
	console.log(foods);
	
	for(var fidx=0; fidx<5; fidx++){//각 파트별로 반복
		if(foods[fidx]!=null){//DB에 데이터가 있었을 경우
			var s = document.getElementById("selected"+foods[fidx].meals);
			foods[fidx].foodlist.forEach(function(food){
				var tag = document.createElement("button");
				var fname = document.createTextNode(food);
				tag.appendChild(fname);
				tag.className = "btn btn-success";
				s.appendChild(tag);
				addFoodDeleteEvent(tag);
			});
			//데이터 저장 버튼 
		}else{//DB에 데이터가 없었을 경우
			foods[fidx] = new Object();
			foods[fidx].meals = fidx+1;
			foods[fidx].foodlist = [];
			foods[fidx].foodcodes = [];
			foods[fidx].foodcalories = [];
		}
	}
	
	var saveBtn = [...document.getElementsByClassName("myFoodMain-input-each-save")];
	for(var z=0; z<5; z++){
		saveBtn[z].addEventListener("click", function(event){
			var idx = event.target.id.substr(-1)-1;
			var param;

			if(DBexist[idx]){//DB에 해당 파트의 데이터가 있는 경우
				if(foods[idx].foodlist.length>0){
					//DB에 원래 데이터가 있으며 저장할 데이터도 있을 경우
					//UPDATE
					param = {
						"action" : "UPDATE",
						"today" : today,
						"meals" : foods[idx].meals,
						"foodlist" : JSON.stringify(foods[idx].foodlist),
						"foodcodes" : JSON.stringify(foods[idx].foodcodes),
						"foodcalories" : JSON.stringify(foods[idx].foodcalories)
					};
				}else{
					//DB에 원래 데이터가 있지만 저장할 데이터는 없는 경우
					//DELETE
					param = {
						"action" : "DELETE",
						"data" : ""+foods[idx].meals
					};
				}
			}else{//DB에 해당 파트의 데이터가 없는 경우
				if(foods[idx].foodlist.length>0){
					//DB에는 데이터가 없지만 저장할 데이터가 있다면
					//INSERT
					param = {
						"action" : "INSERT",
						"today" : today,
						"meals" : foods[idx].meals,
						"foodlist" : JSON.stringify(foods[idx].foodlist),
						"foodcodes" : JSON.stringify(foods[idx].foodcodes),
						"foodcalories" : JSON.stringify(foods[idx].foodcalories)
					};
				}else{
					//DB에 데이터가 없고 저장할 데이터도 없다면
					//아무것도 하지 않음
					alert("변경된 정보가 없습니다");
					return;
				}
			}
			console.log(param);
			console.log(JSON.stringify(param));
			$.ajax({
				url : "/myFoodMain/saveInfo",
				type : "POST",
				dataType : "text",
				data : param,
				success : function(r){
					alert(r);
					location.reload();
				}
			});
		});
	}
	const every = document.getElementsByClassName("myFoodMain-shareEveryOne");
	const everyBtn = [...every];
	for(var i=0; i<5; i++){
		everyBtn[i].addEventListener("click", function(){
			const sbPart = everyBtn.indexOf(this);
			if(!DBexist[sbPart]){
				alert("식단 데이터가 없습니다.\n선택하신 음식이 있다면 저장 후 진행해 주세요.");
				return;
			}
			sessionStorage.setItem("meals", sbPart+1);
			sessionStorage.setItem("foodlist", JSON.stringify(foods[sbPart].foodlist));
			sessionStorage.setItem("foodcodes", JSON.stringify(foods[sbPart].foodcodes));
			sessionStorage.setItem("foodcalories", JSON.stringify(foods[sbPart].foodcalories));
			sessionStorage.setItem("today", today);
			location.href="/everyFoodWrite";
		});
	}
};//onload 끝
function clear(parentNode){
	while (parentNode.hasChildNodes()) {
		parentNode.firstChild.remove();
	}
}
function addFood(code, name, cal, idx){//각 구역의 식품 정보를 담은 배열에 선택한 음식 정보 추가
	console.log("---식품 추가 전---");
	console.log(foods[idx]);
	foods[idx].foodlist.push(name);
	foods[idx].foodcodes.push(code);
	foods[idx].foodcalories.push(cal);
	console.log("---식품 추가 후---");
	console.log(foods[idx]);
	addFoodButton("selected"+(idx+1), name);
	updateTotalCalorie(cal, true);
	var todayCal = document.getElementById("todayCalorie"+(idx+1));
	todayCal.innerText = parseInt(todayCal.innerText)+cal;
	todayCalArr[idx] += cal;
}
function addFoodButton(targetId, foodName){
	var target = document.getElementById(targetId);
	var tag = document.createElement("button");
	var fname = document.createTextNode(foodName);
	tag.appendChild(fname);
	tag.className = "btn btn-success food-btn";
	target.appendChild(tag);
	addFoodDeleteEvent(tag);
}
function addFoodDeleteEvent(targetTag){//클릭한 음식을 삭제할 이벤트 추가
	targetTag.addEventListener("click", function(event){
		const nodes = [...event.target.parentElement.children];//[클릭한 식품 버튼이 속한 파트]의 모든 식품버튼
		const index = nodes.indexOf(event.target);//클릭한 버튼의 인덱스
		const partIdx = parseInt(event.target.parentElement.id.substr(-1))-1;//클릭한 버튼이 있는 파트의 인덱스 
		const deleteCal = foods[partIdx].foodcalories[index];//삭제할 음식의 칼로리
		updateTotalCalorie(deleteCal, false);
		todayCalArr[partIdx] -= deleteCal;//해당 파트 칼로리 데이터 갱신
		//해당 파트 칼로리 텍스트 갱신
		document.getElementById("todayCalorie"+(partIdx+1)).innerText = todayCalArr[partIdx];
		//해당 식품 정보 삭제
		console.log("삭제 전 식품정보");
		console.log(foods[partIdx]);
		foods[partIdx].foodlist.splice(index, 1);
		foods[partIdx].foodcodes.splice(index, 1);
		foods[partIdx].foodcalories.splice(index, 1);
		console.log("삭제 후 식품정보");
		console.log(foods[partIdx]);
		//버튼 삭제
		event.target.remove();
	});
}
function updateTotalCalorie(updateCal, isPlus){
	var todayCalorieTotal = document.getElementById("todayCalorieTotal");
	var totalCal = parseInt(todayCalorieTotal.innerText);
	var innerBar = document.getElementById("my-bar-inner");
	var innerBarWidth = 0;
	if(isPlus){
		innerBarWidth = (totalCal+updateCal)/recommendCal*100;
		todayCalorieTotal.innerText = totalCal+updateCal;
	}else{
		innerBarWidth = (totalCal-updateCal)/recommendCal*100;
		todayCalorieTotal.innerText = totalCal-updateCal;
	}
	if(innerBarWidth>100){
		innerBarWidth = 100;
	}
	innerBar.style.width = innerBarWidth+"%";
}