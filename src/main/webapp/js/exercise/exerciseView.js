/*
function del(){
	if(confirm('삭제할까요?')){
		location.href="/exercise/exerciseDel?no=${vo.no}";
	}
}
 */

$(function(){
	$('#formFile').change(function() {//이미지 첨부되면 실행
		setImage(this, '#preview');
	});

});
//이미지 미리보기
function setImage(input, preview) {
	$('#preview').css("display","block");//display설정 변경
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$(preview).attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
};

 // 지도에서 장소 클릭하면 placeinfo에 담음
function showInfo(places){
	$("#location").val(places)
	//alert(places+$('#location'))
}
