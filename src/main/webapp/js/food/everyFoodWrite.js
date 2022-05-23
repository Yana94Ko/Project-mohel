window.onload=function(){
	const mealPart = ["아침","오전간식","점심","오후간식","저녁"];
	const dataTag = document.getElementsByClassName("everyFoodWrite-data");
	dataTag[0].value = sessionStorage.getItem("meals");
	dataTag[1].value = sessionStorage.getItem("foodlist");
	dataTag[2].value = sessionStorage.getItem("foodcodes");
	dataTag[3].value = sessionStorage.getItem("foodcalories");
	const dateInfo = `${sessionStorage.getItem("today")} (${mealPart[dataTag[0].value-1]})`;
	document.getElementById("everyFoodWrite-date").innerText = dateInfo;
	
	const btn = document.getElementById("everyFoodWrite-selectPicture");
	btn.addEventListener("click", function(){
		document.getElementById("select").click();
	});
	//TODO 폼 데이터를 컨트롤러에서 받아보기
	const imgInput = document.getElementById("select");
	imgInput.addEventListener("change", function(){
		imageClear();
		if(!imageCheck(this)){
			return;
		}
		readImage(this);
		console.log(this.files);
	});
	const submitBtn = document.getElementById("everyFoodWrite-shareEveryOne");
	submitBtn.addEventListener("click", function(){
		if(imgInput.value==""){
			alert("이미지가 없습니다.\n1~3개의 이미지를 선택해야 합니다.");
			return;
		}
		const title = document.getElementById("everyFoodWrite-input-title");
		if(title.value==""){
			alert("제목을 입력해 주세요");
			return;
		}
		const contents = document.getElementById("everyFoodWrite-input-content");
		if(contents.value==""){
			alert("내용을 입력해 주세요");
			return;
		}
		alert("식단이 공유되었습니다");
		document.getElementById("everyFoodWriteFrm").submit();
	});
}//onload 끝

function readImage(input){
	//input 태그에 파일이 있을 경우
	if (input.files) {
		//forEach 사용을 위해 files를 배열화(files는 forEach 사용 불가);
		const fArr = Array.from(input.files);

		fArr.forEach(function(file, index) {
			//3개를 초과하여 이미지 선택시 초과분에 대해서는 작업하지 않음
			if (index > 2) {
				return;
			}

			const fReader = new FileReader();
			fReader.onload = function() {
				const img = document.getElementById("image" + (index + 1));
				img.src = this.result;
				img.style.width = "100px";
				img.style.height = "100px";
				img.style.display = "block";
			}
			fReader.readAsDataURL(file);
		});
	}
}
function imageClear(){
	const img1 = document.getElementById("image1");
	const img2 = document.getElementById("image2");
	const img3 = document.getElementById("image3");
	img1.src = "/";
	img1.style.display = "none";
	img2.src = "/";
	img2.style.display = "none";
	img3.src = "/";
	img3.style.display = "none";
}
function imageCheck(tag){
	if(tag.files.length>3){
		alert("이미지는 최대 3개까지 선택 가능합니다");
		tag.value = "";
		return false;
	}
	return true;
}