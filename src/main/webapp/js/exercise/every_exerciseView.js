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

// 장소명+주소 가져오기
function placeinfo(places){
var placesinfo =places.place_name; //JSON.stringify(places);

$('#location').append(placesinfo);	
}

//(작성자 외)참가신청
function excerciseMember(){
	//유효성 검사 : 로그인 여부 확인
	if($("#loginNickName").val()==null ) {
		alert("로그인 후 참가신청 해주세요..!")
		location.href = "/member/login";
		return false;
	}
	if (confirm('참가 신청하시겠습니까? \n ※단, 라이딩은 개설자에 의해 취소될 수 있습니다.※')) {
		let url = "/exercise/excerciseMemberOk";
		let params = "no=" + $("#no").val();
		console.log(params)
		$.ajax({
			url: url,
			data :params,
			success: function() {
				alert("라이딩 신청 완료! 안전한 라이딩 되세요!");
			},
			error: function(e) {
				alert("라이딩 신청에 실패했어요..");
			}
		});
	}
}