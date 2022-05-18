//마커를 담을 배열입니다
var markers = [];

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({zIndex:1});

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
        imgOptions =  {
            spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
            spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
            marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage
        });

    marker.setMap(map); // 지도 위에 마커를 표출합니다
    markers.push(marker);  // 배열에 생성된 마커를 추가합니다

    return marker;
}

// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
    var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

    infowindow.setContent(content);
    infowindow.open(map, marker);
}

//(작성자 외)참가신청
function excerciseMember(){
	console.log($("#loginNickName").val())
	//유효성 검사 : 로그인 여부 확인
	if($("#loginNickName").val()=="" ) {
		alert("로그인 후 참가신청 해주세요..!")
		location.href = "/member/login";
		return false;
	}
	if (confirm('참가 신청하시겠습니까?')) {
		let url = "/exercise/excerciseMemberOk";
		let params = "no=" + $("#no").val();
		$.ajax({
			url: url,
			data :params,
			success: function() {
				alert("함께 운동하기 신청 완료! \n오늘도 건강한 하루 되세요!");
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
		let params = "no=" + $("#no").val();
		$.ajax({
			url: url,
			data :params,
			success: function() {
				alert("함께 운동하기가 취소되었습니다. 다음에 뵈어요!");
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
function exerciseStateCheck(){
	let statesCnt = $('input[id=stateUpdateBtn]').length
	for(let i= 0; i )
	if($('exerciseStatus').val==1){
		
	}
}
exerciseStateCheck();
}