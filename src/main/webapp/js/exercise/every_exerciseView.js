//마커를 담을 배열입니다
var markers = [];

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

//지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption);


// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
    var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

    infowindow.setContent(content);
    infowindow.open(map, marker);
}
// 장소명+주소 가져오기
function placeinfo(places){
	var placesinfo =places.place_name; //JSON.stringify(places);   로....
	$('#location').append(placesinfo);	
}

var loginNickName = $("#loginNickName").val();

//(작성자 외)참가신청
function excerciseMember(){
	//유효성 검사 : 로그인 여부 확인
	if(loginNickName=="" ) {
		alert("로그인 후 참가신청 해주세요..!")
		location.href = "/member/login";
		return false;
	}
	if($("#exampleSelect1").val()<=$("#applicantCnt").val()){
		alert("모든 인원이 모집되어, 참가신청이 불가합니다.")
		return false;
	}
	if (confirm('참가 신청하시겠습니까?')) {
		let url = "/exercise/excerciseMemberOk";
		let params = "exerciseNo=" + $("#no").val();
		$.ajax({
			url: url,
			data :params,
			success: function() {
				alert("함께 운동하기 신청 완료! \n오늘도 건강한 하루 되세요!");
				location.href = "/exercise/every_exerciseView?no=" + exerciseNo;
			},
			error: function(e) {
				alert("운동 신청에 실패했어요..");
			}
		});
	}
}
function excerciseMemberCancel(){
	//유효성 검사 : 로그인 여부 확인
	if($("#loginNickName").val()==null ) {
		alert("로그인 후 참가취소 해주세요..!")
		location.href = "/member/login";
		return false;
	}
	if(confirm('참가신청 취소하시겠습니까?')) {
		let url = "/exercise/excerciseMemberCancel";
		let params = "exerciseNo=" + $("#no").val();
		$.ajax({
			url: url,
			data :params,
			success: function() {
				alert("함께 운동하기가 취소되었습니다. 다음에 뵈어요!");
				location.href = "/exercise/every_exerciseView?no=" + exerciseNo;
			},
			error: function(e) {
				alert("운동 취소에 실패했어요..");
			}
		});
	}
}
var exerciseNo =  $("#dbExerciseNo").val();
//작성자 : 참가자 수락
$(".applicantSave").on("click", function(event) {
	//최대 참여 인원수 유효성 검사
	if($("#exampleSelect1").val()<=$("#applicantCnt").val()){
		alert("더이상 참가자 수를 늘릴 수 없습니다 \n 사유 : 현재 운동의 최대 참가자 수는"+$("#exampleSelect1").val()+"명입니다.")
		return false;
	}
	if (confirm($(this).parent().prev().text()+'님과 함께 운동하시겠습니까?')) {
		$('input[name=applicantNickName]').val($(this).parent().prev().text());
		let applicantNickName = $('input[name=applicantNickName]').val();
		console.log(exerciseNo+"dhk"+applicantNickName)
		$.ajax({
			url: "/exercise/excerciseStateOk",
			type: "GET",
			data:  "exerciseNo="+exerciseNo+"&nickname="+applicantNickName,
			success: function(result) {
				alert(applicantNickName+"님과 함께 운동합니다.");
				location.href = "/exercise/every_exerciseView?no=" + exerciseNo;
			}, error: function(request, status, error) {
				console.log("code=" + request.status + "message=" + request.responseText + "error=" + errors);
			}
		});
	}
});

//작성자 : 참가자 거절
$(".applicantDel").on("click", function(event) {
	if (confirm('다음에 함께하시겠습니까?')) {
		$('input[name=applicantNickName]').val($(this).parent().prev().text());
		let applicantNickName = $('input[name=applicantNickName]').val();
		$.ajax({
			url: "/exercise/excerciseStateDel",
			type: "GET",
			data:  "exerciseNo="+exerciseNo+"&nickname="+applicantNickName,
			success: function(result) {
				alert(applicantNickName+"님의 신청을 거절하였습니다.");
				location.href = "/exercise/every_exerciseView?no=" + exerciseNo;
			}, error: function(request, status, error) {
				console.log("code=" + request.status + "message=" + request.responseText + "error=" + errors);
			}
		});
	}
});
//참가자 상태 확인
function exerciseStateCheck(){
	let statesCnt = $('span[id^=exerciseStatusShow]').length;
	if(loginNickName!=""){
		for(let i= 0; i <statesCnt; i++ ){
			let exerciseStatus = document.getElementById('exerciseStatus'+i).innerHTML;
			//(작성자 외)기존에 신청되어 있을 경우, 신청하기 버튼 숨기기
			if(document.getElementById('applierNickname'+i).innerText==loginNickName){
				$('#excerciseMemberApply').css('display', 'none');
			}
			//(작성자, 작성자 외) : 로그인 되어 있을 시, 참가자 리스트 내의 참가자의 확정 상태를 보여줌
			if(exerciseStatus==1){
				document.getElementById('exerciseStatusShow'+i).innerText = "참가확정"
				if($('.applicantSave').length!=0){
					document.getElementById('stateUpdateBtn'+i).style.display = "none";
					document.getElementById('stateDeleteBtn'+i).style.display = "none";
				}
			}else if(exerciseStatus==0){
				document.getElementById('exerciseStatusShow'+i).innerText = "수락대기"
			}
		}
	}
}
exerciseStateCheck();
//해시태그 입력 관련
$('#hashtag').on("keyup", function(event) {
	let keyword = document.getElementById("hashtag").value;
	if (window.event.keyCode == 32) {
		$('input[id=hashtag]').val(keyword.substr(0, keyword.length - 1)+"#");
	}
	if(window.event.keyCode == 8){
		if(keyword==""){
			keyword.value="#"
		}
	}
});
$('#hashtag').on("keyup", function(event) {
	let keyword = document.getElementById("hashtag").value;
	if (keyword.substring(0,0)!="#" && keyword.length==0) {
		$('input[id=hashtag]').val("#");
	}
});
$('#hashtag').on("focus", function(event) {
	if($('input[id=hashtag]').val()!="#"){
		$('input[id=hashtag]').val($('input[id=hashtag]').val( )+"#");
	}
});
$('#hashtag').on("focusout", function(event) {
	let keyword = document.getElementById("hashtag").value;
	if($('input[id=hashtag]').val()=="#"){
		$('input[id=hashtag]').val("");
	}
	if(keyword.substring(keyword.length-1)=='#'){
		$('#hashtag').val(keyword.substring(0,keyword.length-1));
	}
})
