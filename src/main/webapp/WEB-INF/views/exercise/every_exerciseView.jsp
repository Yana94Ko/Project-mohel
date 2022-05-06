<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap CSS by bootswatch -->
<link
	href="https://cdn.jsdelivr.net/npm/bootswatch@5.0.1/dist/minty/bootstrap.min.css"
	rel="stylesheet" crossorigin="anonymous" />
<!-- 카카오 api 라이브러리  -->
<!-- services와 clusterer, drawing 라이브러리 불러오기 -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=096ec0036610b77d5b4e1aa8571cbb1e&libraries=services,clusterer,drawing"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=096ec0036610b77d5b4e1aa8571cbb1e&libraries=services,clusterer,drawing"></script>
<script>
	function every_del() {
		// 사용자가 yes(true)와 no(false)를 선택할 수 있는 대화상자
		if (confirm("삭제하시겠습니까?")) {
			// 확인버튼 선택시
			location.href = "/exercise/every_exerciseDel?no=${vo.no}";

		}
	}
	/*
	 // 댓글----------------------------------------------------------
	 $(function(){
	 // 댓글 목록을 가져오는 함수
	 function replyListAll(){ // 현재 글의 댓글을 모두 가져오기
	 var url = "/myapp/reply/list";
	 var params = "no=${vo.no}"; // 32번 글인경우: no=32 
	 $.ajax({
	 url:url,
	 data:params,
	 success:function(result){
	 var $result = $(result); // vo, vo, vo, vo...
	
	 var tag = "<ul>";
	
	 $result.each(function(idx, vo){
	 tag += "<li><div>"+vo.userid;
	 tag += "("+vo.writedate+")";
	 //  'goguma' ==  'goguma'
	 if(vo.userid == '${logId}'){
	
	 tag +="<input type='button' value='Edit'/>";
	 tag +="<input type='button' value='Del' title='"+vo.replyno+"'/>";
	 }
	
	 tag += "<br/>"+vo.coment+"</div>";
	
	 // 본인글일때 수정폼이 있어야 한다.
	 if(vo.userid=='${logId}'){
	 tag += "<div style='display:none;'><form method='post'>";
	 tag += "<input type='hidden' name='replyno' value='"+vo.replyno+"'/>";
	 tag += "<textarea name='coment' style='width:400px;height:50px;'>"+vo.coment+"</textarea>";
	 tag += "<input type='submit' value='수정'/>";
	 tag += "</form></div>";
	
	 }
	 tag +="<hr/></li>"; // vo의 개수만큼 순환
	 });
	
	 tag += "</ul>";
	
	 $("#replyList").html(tag);
	
	 }, error:function(e){
	 console.log(e.responseText);
	 }
	 })
	 }
	
	 // 댓글등록
	 $("#replyFrm").submit(function(){
	 event.preventDefault(); // form 기본이벤트 제거
	 if($("#coment").val()==''){
	 alert("댓글 입력 후 등록하세요.");
	 return;
	 }else{ // 댓글을 입력한 경우
	 var params = $("#replyFrm").serialize(); // form에 있는 데이터가 담김
	
	 $.ajax({
	 url:'/myapp/reply/writeOk',
	 data: params,
	 type:'POST',
	 success: function(r){
	 $("coment").val("");
	 // 에러가 안난다면 => 댓글목록이 refresh되어야 한다. 
	 replyListAll();
	 }, error:function(e){
	 console.log(e.responseText);
	 }
	 });
	 }
	 });
	 // 댓글 수정(Edit)버튼 선택 시 해당 폼 보여주기
	 $(document).on('click', '#replyList input[value=Edit]', function(){
	 $(this).parent().css("display","none"); // 숨기기
	 // 보여주기
	 $(this).parent().next().css("display", "block");
	 });
	 // 댓글 수정(DB)
	 $(document).on('submit','#replyList form',function(){
	 event.preventDefault();
	 // 데이터
	 var params = $(this).serialize();
	 var url = '/myapp/reply/editOk';
	 $.ajax({
	 url:url,
	 data:params,
	 type:"POST",
	 success:function(result){
	 console.log(result);
	 replyListAll();
	 },error:function(){
	 console.log('수정에러발생');
	 }
	 });
	 });
	
	 // 댓글 삭제
	 $(document).on('click','#replyList input[value=Del]',function(){
	 if(confirm('댓글을 삭제하시겠습니까?')){
	 var params = "replyno="+$(this).attr("title");
	 $.ajax({
	 url:'/myapp/reply/del',
	 data:params,
	 success:function(result){
	 console.log(result);
	 replyListAll();
	 }, error:function(){
	 console.log("댓글삭제에러...");
	 }
	 })
	 }
	 });
	
	 // 현재글의 댓글
	 replyListAll();
	 });
	 */
</script>
<style>
#every-exercise-mainFrm {
	width: 1000px;
}

#every-main-title {
	text-align: center;
	margin: 30px;
	position: relative;
	font-size: 30px;
}

h5 {
	position: relative;
	left: 43%;
	margin-bottom: 40px;
}

#every-exerciselist-btn {
	position: relative;
	left: 66%;
}
#exercise-sdate{
	position: relattive;
	float: left;
	width:150px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container" id="every-exercise-mainFrm">
		<form method="post" id="every-exerciseFrm">
			<fieldset>
				<legend id="every-main-title">모두의 운동</legend>
				<h5>운동 함께할 사람 모집</h5>
				<div>
					<div class="form-group row">
						<label for="title" class="col-sm-2 col-form-label">제목</label>
						<div class="col-sm-10">
							<input readonly type="text" class="form-control" placeholder="${vo.title}">
						</div>
					</div>
					<div class="form-group">
						<label for="keyword" class="form-label mt-4">오늘의 키워드</label> <input
							type="text" class="form-control" id="keyword" readonly
							placeholder="#하체 #스쿼트"> <small id="keywordhelp"
							class="form-text text-muted">오늘의 운동 키워드를 해시태그로 입력하세요</small>
					</div>

					<div class="form-group">
						<label for="contents" class="form-label mt-4">내용</label>
						<textarea class="form-control" id="contents" rows="3"
							name="contents" readonly>${vo.contents }</textarea>
					</div>

					<!-- 지도 -->
					<div class="map_wrap">
						<div id="map"
							style="width: 100%; height: 350px; position: relative; overflow: hidden;"></div>

						<div id="menu_wrap" class="bg_white">
							<div class="option">
								<div>
									<div>
										키워드 : <input type="text" id="keyword" size="15" readonly>
										<button type="button" onclick="searchPlaces()">검색하기</button>
									</div>
								</div>
							</div>
							<hr>
							<ul id="placesList"></ul>
							<div id="pagination"></div>
						</div>
					</div>


					<div class="form-group">
						<label for="location" class="form-label mt-4">장소</label> <input
							value="${vo.placeinfo}" class="form-control" readonly
							id="location">
					</div>

					<div class="form-group">
						<label for="exercise-date" class="form-label mt-4">운동날짜</label> 
						<input type="date" class="form-control" id="exercise-sdate" name="startdate" readonly> 
						<input type="date" class="form-control" id="exercise-edate" name="enddate" readonly>
					</div>

					<div class="form-group">
						<label for="exampleSelect1" class="form-label mt-4" >참가인원수</label>
						<select class="form-select" id="exampleSelect1" disabled="disabled"> 
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
						</select>
					</div>
					<div class="form-group">
						<label for="applicant" class="form-label mt-4">참가자</label>
						<textarea class="form-control" id="applicant" rows="3" readonly></textarea>
					</div>
					<div class="form-group">
						<label for="formFile" class="form-label mt-4">사진 업로드</label> <input
							class="form-control" type="file" id="formFile" readonly>
					</div>

				</div>

			</fieldset>
		</form>
		<div>
			<!-- 로그인 아이디와 글쓴이가 같을 경우 수정삭제 표시 -->

			<button type="button" class="btn btn-warning"
				onclick="location.href='/exercise/every_exerciseEdit?no=${vo.no }' ">수정하기</button>
			<button type="button" class="btn btn-danger"
				onclick="javascript:every_del()" id="delete-btn">삭제하기</button>


			<button type="submit" class="btn btn-primary"
				onclick="location.href='/exercise/every_exerciseList' "
				id="every-exerciselist-btn">모두의 운동목록</button>
		</div>

		<!-- 댓글쓰기 -->
		<c:if test="${logStatus=='Y'}">
			<form method="post" id="replyFrm">
				<input type="hidden" name="no" value="${vo.no }" />
				<textarea name="coment" id="coment"
					style="width: 500px; height: 80px;"></textarea>
				<input type="submit" value="댓글등록" />
			</form>
		</c:if>

		<!-- 댓글 목록이 나올자리 -->
		<div id="replyList"></div>
	</div>

	<script>
		//마커를 담을 배열입니다
		var markers = [];

		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
			level : 3
		// 지도의 확대 레벨
		};

		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption);

		// 장소 검색 객체를 생성합니다
		var ps = new kakao.maps.services.Places();

		// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
		var infowindow = new kakao.maps.InfoWindow({
			zIndex : 1
		});

		// 키워드로 장소를 검색합니다
		//searchPlaces();

		// 키워드 검색을 요청하는 함수입니다
		function searchPlaces() {
			var keyword = document.getElementById('keyword').value;

			if (!keyword.replace(/^\s+|\s+$/g, '')) {
				alert('키워드를 입력해주세요!');
				return false;
			}

			// 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
			ps.keywordSearch(keyword, placesSearchCB);
		}

		// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
		function placesSearchCB(data, status, pagination) {
			if (status === kakao.maps.services.Status.OK) {

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

			var listEl = document.getElementById('placesList'), menuEl = document
					.getElementById('menu_wrap'), fragment = document
					.createDocumentFragment(), bounds = new kakao.maps.LatLngBounds(), listStr = '';

			// 검색 결과 목록에 추가된 항목들을 제거합니다
			removeAllChildNods(listEl);

			// 지도에 표시되고 있는 마커를 제거합니다
			removeMarker();

			for (var i = 0; i < places.length; i++) {

				// 마커를 생성하고 지도에 표시합니다
				var placePosition = new kakao.maps.LatLng(places[i].y,
						places[i].x), marker = addMarker(placePosition, i), itemEl = getListItem(
						i, places[i]); // 검색 결과 항목 Element를 생성합니다

				// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
				// LatLngBounds 객체에 좌표를 추가합니다
				bounds.extend(placePosition);

				// 마커와 검색결과 항목에 mouseover 했을때
				// 해당 장소에 인포윈도우에 장소명을 표시합니다
				// mouseout 했을 때는 인포윈도우를 닫습니다
				(function(marker, title) {
					kakao.maps.event.addListener(marker, 'mouseover',
							function() {
								displayInfowindow(marker, title);
							});

					kakao.maps.event.addListener(marker, 'mouseout',
							function() {
								infowindow.close();
							});

					itemEl.onmouseover = function() {
						displayInfowindow(marker, title);
					};

					itemEl.onmouseout = function() {
						infowindow.close();
					};
				})(marker, places[i].place_name);

				fragment.appendChild(itemEl);
			}

			// 검색결과 항목들을 검색결과 목록 Element에 추가합니다
			listEl.appendChild(fragment);
			menuEl.scrollTop = 0;

			// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
			map.setBounds(bounds);
		}

		// 검색결과 항목을 Element로 반환하는 함수입니다
		function getListItem(index, places) {

			var el = document.createElement('li'), itemStr = '<span class="markerbg marker_'
					+ (index + 1)
					+ '"></span>'
					+ '<div class="info">'
					+ '   <h5>' + places.place_name + '</h5>';

			if (places.road_address_name) {
				itemStr += '    <span>' + places.road_address_name + '</span>'
						+ '   <span class="jibun gray">' + places.address_name
						+ '</span>';
			} else {
				itemStr += '    <span>' + places.address_name + '</span>';
			}

			itemStr += '  <span class="tel">' + places.phone + '</span>'
					+ '</div>';

			el.innerHTML = itemStr;
			el.className = 'item';

			return el;
		}

		// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
		function addMarker(position, idx, title) {
			var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
			imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
			imgOptions = {
				spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
				spriteOrigin : new kakao.maps.Point(0, (idx * 46) + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
				offset : new kakao.maps.Point(13, 37)
			// 마커 좌표에 일치시킬 이미지 내에서의 좌표
			}, markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize,
					imgOptions), marker = new kakao.maps.Marker({
				position : position, // 마커의 위치
				image : markerImage
			});

			marker.setMap(map); // 지도 위에 마커를 표출합니다
			markers.push(marker); // 배열에 생성된 마커를 추가합니다

			return marker;
		}

		// 지도 위에 표시되고 있는 마커를 모두 제거합니다
		function removeMarker() {
			for (var i = 0; i < markers.length; i++) {
				markers[i].setMap(null);
			}
			markers = [];
		}

		// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
		function displayPagination(pagination) {
			var paginationEl = document.getElementById('pagination'), fragment = document
					.createDocumentFragment(), i;

			// 기존에 추가된 페이지번호를 삭제합니다
			while (paginationEl.hasChildNodes()) {
				paginationEl.removeChild(paginationEl.lastChild);
			}

			for (i = 1; i <= pagination.last; i++) {
				var el = document.createElement('a');
				el.href = "#";
				el.innerHTML = i;

				if (i === pagination.current) {
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
			var content = '<div style="padding:5px;z-index:1;">' + title
					+ '</div>';

			infowindow.setContent(content);
			infowindow.open(map, marker);
		}

		// 검색결과 목록의 자식 Element를 제거하는 함수입니다
		function removeAllChildNods(el) {
			while (el.hasChildNodes()) {
				el.removeChild(el.lastChild);
			}
		}
	</script>
</body>
</html>