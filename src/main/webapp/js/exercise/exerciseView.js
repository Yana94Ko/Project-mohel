/*
function del(){
	if(confirm('삭제할까요?')){
		location.href="/exercise/exerciseDel?no=${vo.no}";
	}
}
 */
 
 // 지도에서 장소 클릭하면 placeinfo에 담음
function showInfo(places){
	$("#location").val(places)
	//alert(places+$('#location'))
}
