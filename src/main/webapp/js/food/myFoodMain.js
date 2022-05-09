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
	alert("dateJson.year = "+dateJson.year);
	alert("dateJson.month = "+dateJson.month);
	alert("dateJson.date = "+dateJson.date);
	alert("dateJson.day = "+dateJson.day);
	alert("이번 달의 첫 날의 요일 = "+firstDay);
	
	const url = new URLSearchParams(location.search);
	url.set("year", dateJson.year);
	url.set("month", dateJson.month);
	for(var i=1; i<=lastDay; i++){
		url.set("date", i);
		dateList.innerHTML += `<a href="${location.pathname+'?'+url.toString()}"><li>${i}일(${dayStr[(firstDay+i-1)%7]})</li></a>`;
	}
};