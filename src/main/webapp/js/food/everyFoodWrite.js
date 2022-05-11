window.onload=function(){
	const btn = document.getElementById("everyFoodWrite-selectPicture");
	btn.addEventListener("click", function(){
		document.getElementById("select").click();
	});
	
	function readImage(input){
		//input 태그에 파일이 있을 경우
		if(input.files){
			//forEach 사용을 위해 files를 배열화(files는 forEach 사용 불가);
			const fArr = Array.from(input.files);
			
			fArr.forEach(function(file, index){
				//3개를 초과하여 이미지 선택시 초과분에 대해서는 작업하지 않음
				if(index>2){
					return;
				}
				
				const fReader = new FileReader();
				fReader.onload = function(){
					const img = document.getElementById("image"+(index+1));
					img.src = this.result;
					img.style.width = "100px";
					img.style.height = "100px";
					img.style.display = "block";
				}
				fReader.readAsDataURL(file);
			});
		}
	}
	
	const imgInput = document.getElementById("select");
	imgInput.addEventListener("change", function(){
		const img1 = document.getElementById("image1");
		const img2 = document.getElementById("image2");
		const img3 = document.getElementById("image3");
		img1.src = "/";
		img1.style.display = "none";
		img2.src = "/";
		img2.style.display = "none";
		img3.src = "/";
		img3.style.display = "none";
		
		readImage(this);
	});
}