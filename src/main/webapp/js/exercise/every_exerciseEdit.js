/*
	function every_del() {
		// 사용자가 yes(true)와 no(false)를 선택할 수 있는 대화상자
		if (confirm("삭제하시겠습니까?")) {
			// 확인버튼 선택시
			location.href = "/exercise/every_exerciseDel?no=${vo.no}";

		}
	}
 */
	
/*지도*/

//마커를 담을 배열입니다
var markers = [];

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();  

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({zIndex:1});

// 키워드로 장소를 검색합니다
//searchPlaces();

//장소 검색란에 키워드를 입력하면, 자동으로 search 진행
$('#location').on("keyup", function() {
	$('#keyword').val($('#location').val());
	// 엔터키가 눌렸을 때 실행할 내용
	if (window.event.keyCode == 13) {
		searchPlaces();
	}
});
// 키워드 검색을 요청하는 함수입니다
function searchPlaces() {
    var keyword = document.getElementById('keyword').value;
	
    if (!keyword.replace(/^\s+|\s+$/g, '')) {
        alert('키워드를 입력해주세요!');
        return false;
    }

    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
    ps.keywordSearch( keyword, placesSearchCB); 
}

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
		//검색이 완료되면 리스트 보이기
        $("#menu_wrap").css('display','block');
        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        displayPlaces(data);

        // 페이지 번호를 표출합니다
        displayPagination(pagination);

    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

        alert('검색 결과가 존재하지 않습니다.');
        return;

    } else if (status === kakao.maps.services.Status.ERROR) {

        alert('검색 결과 중 오류가 발생했습니다.');
        return;

    }
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {

    var listEl = document.getElementById('placesList'), 
    menuEl = document.getElementById('menu_wrap'),
    fragment = document.createDocumentFragment(), 
    bounds = new kakao.maps.LatLngBounds(), 
    listStr = '';
    
    // 검색 결과 목록에 추가된 항목들을 제거합니다
    removeAllChildNods(listEl);

    // 지도에 표시되고 있는 마커를 제거합니다
    removeMarker();
    
    for ( var i=0; i<places.length; i++ ) {

        // 마커를 생성하고 지도에 표시합니다
        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
            marker = addMarker(placePosition, i), 
            itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);

        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        (function(marker, places,placePosition) {
			var placesinfo = JSON.stringify(places);
            kakao.maps.event.addListener(marker, 'mouseover', function() {
                displayInfowindow(marker, places.place_name);
            });

            kakao.maps.event.addListener(marker, 'mouseout', function() {
                infowindow.close();
            });

            kakao.maps.event.addListener(marker, 'click', function() {
                $("#location").val(places.place_name);
                $("#placeinfo").val(placesinfo);
                $("#menu_wrap").css('display','none');
                removeMarker();
				setPlaceMarker(placePosition);
				map.setLevel(4, {anchor: placePosition});
            });
            
            itemEl.onmouseover =  function () {
                displayInfowindow(marker, places.place_name);
            };

            itemEl.onmouseout =  function () {
                infowindow.close();
            };
            
            itemEl.onclick =  function () {
                $("#location").val(places.place_name);
                $("#placeinfo").val(placesinfo);
                $("#menu_wrap").css('display','none');
                removeMarker();
				setPlaceMarker(placePosition);
				map.setLevel(4, {anchor: placePosition});
            };
        })(marker, places[i],placePosition);

        fragment.appendChild(itemEl);
    }

    // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    map.setBounds(bounds);
}

//(custom)검색결과에서 선택한 장소에 마커를 생성해주는 함수
function setPlaceMarker(placePosition){
	
	// 마커를 생성합니다
	var selectedMarker = new kakao.maps.Marker({
	    position: placePosition
	});
	
	// 마커가 지도 위에 표시되도록 설정합니다
	selectedMarker.setMap(map);
}

// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {
	var pinfo=places.place_name+' '+ places.address_name;
    var el = document.createElement('li'),
   
    itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
                '<div class="info" onclick="showInfo(\''+pinfo+'\')">' +
                '   <h5>' + places.place_name + '</h5>';

    if (places.road_address_name) {
        itemStr += '    <span>' + places.road_address_name + '</span>' +
                    '   <span class="jibun gray">' +  places.address_name  + '</span>';
    } else {
        itemStr += '    <span>' +  places.address_name  + '</span>'; 
    }
                 
      itemStr += '  <span class="tel">' + places.phone  + '</span>' +
                '</div>';           

    el.innerHTML = itemStr;
    el.className = 'item';

    return el;
}
// 지도에서 장소 클릭하면 placeinfo에 담음
function showInfo(places){
	$("#location").val(places)
	//alert(places+$('#location'))
}

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

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
    for ( var i = 0; i < markers.length; i++ ) {
        markers[i].setMap(null);
    }   
    markers = [];
}

// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
    var paginationEl = document.getElementById('pagination'),
        fragment = document.createDocumentFragment(),
        i; 

    // 기존에 추가된 페이지번호를 삭제합니다
    while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild (paginationEl.lastChild);
    }

    for (i=1; i<=pagination.last; i++) {
        var el = document.createElement('a');
        el.href = "#";
        el.innerHTML = i;

        if (i===pagination.current) {
            el.className = 'on';
        } else {
            el.onclick = (function(i) {
                return function() {
                    pagination.gotoPage(i);
                }
            })(i);
        }

        fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
    var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

    infowindow.setContent(content);
    infowindow.open(map, marker);
}

 // 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {   
    while (el.hasChildNodes()) {
        el.removeChild (el.lastChild);
    }
}
function everyExerciseEditOk(){
	$("#every-exerciseFrm").submit();
}

///////////////////////////////////////////////////////////////////////////////////

// 모두의 운동 글쓰기 유효성 검사
$(function(){
	$("#every-exerciseFrm").submit(function(){
		if($("#title").val()==''){
			alert("제목을 입력하세요.");
			return false;
		}
		if($("#hashtag").val()==''){
			alert("키워드를 입력하세요.");
			return false;
		}
		if($("#contents").val()==''){
			alert("내용을 입력하세요.");
			return false;
		}
		if($("#location").val()==''){
			alert("장소를 입력하세요.");
			return false;
		}
		if($("#exercise-sdate").val()==''){
			alert("운동 시작일자를 입력하세요.");
			return false;
		}
		if($("#exercise-edate").val()==''){
			alert("운동 종료일자를 입력하세요.");
			return false;
		}
		if($("#applicantMax").val()==''){
			alert("최대 참가자수를 입력하세요.");
			return false;
		}
         
    });

	var regOnlyNum = /[^0-9]/g;
	$('#applicantMax').on('input', function() {
		$(this).val($(this).val().replace(regOnlyNum, ''));
	});
	
	
});

// 운동 시작 날짜 기본값 지정
    let sdate = document.getElementById('exercise-sdate');
    let edate = document.getElementById('exercise-edate');
    let date = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().slice(0, -5);
	
	function setMinValue(){
            if(sdate.value > edate.value) {
                alert('운동 시작 일자보다 이전의 날짜는 설정할 수 없습니다.');
                sdate.value = date;
            }
        }
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