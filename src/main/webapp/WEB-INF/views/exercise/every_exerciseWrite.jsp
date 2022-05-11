<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link href="${url}/css/exercise/every_exerciseWrite.css"
	rel="stylesheet" type="text/css" />
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

<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function placeinfo() { // 현재 글의 댓글을 모두 가져오기      
    var params = "id=" + id; // 32번 글인경우: no=32
   
    var url = "/every_exercise/placeinfo?" + params;
   	 $.ajax({
             type : 'get',
             url : url,
             // data:params,
             success : function(result) {
				  var store =result.store;
				  var tag = "<div>";
				  	  tag += "<div>"+everyexercise.placeinfo+"</div>";
				  	  tag +="</div>";
				  	 let logId='${logId}';
				  	 
					$('#location').html(tag);
            
             },
             error : function(e) {
                console.log(e.responseText);
             }
          })
          
          
 }
</script>
</head>
<body>
	<div class="container" id="every-exercise-mainFrm">
		<form id ="everyExerciseFrm" method="post" action="/exercise/every_exerciseWriteOk"
			id="everyexerciseFrm">
			<fieldset>
				<legend id="every-main-title">모두의 운동</legend>
				<h5>운동 함께할 사람 모집</h5>
				<div class="form-group row">
					<label for="title" class="col-sm-2 col-form-label">제목</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="title"
							placeholder="글의 제목을 입력하세요">
					</div>
				</div>
				<div class="form-group">
					<label for="today-keyword" class="form-label mt-4">오늘의 키워드</label>
					<input type="text" class="form-control" id="today-keyword"
						placeholder="#하체 #스쿼트"> <small id="keywordhelp"
						class="form-text text-muted">오늘의 운동 키워드를 해시태그로 입력하세요</small>
				</div>

				<div class="form-group">
					<label for="contents" class="form-label mt-4">내용</label>
					<textarea class="form-control" id="contents" name="contents"
						rows="3"></textarea>
				</div>

				<div class="form-group">
					<label for="location" class="form-label mt-4">장소</label> 
					<input type="text" class="form-control" id="location">
					<!-- 지도 -->
					<div class="map_wrap">
						<div id="map"
							style="width: 100%; height: 350px; position: relative; overflow: hidden;"></div>
	
						<div id="menu_wrap" class="bg_white" style="display:none">
							<div class="option">
								<div>
									<div>
										<input type="hidden" id="keyword" size="15">
										<button type="button" onclick="searchPlaces()" style="display:none">검색하기</button>
									</div>
								</div>
							</div>
							<hr>
							<ul id="placesList"></ul>
							<div id="pagination"></div>
						</div>
					</div>
					<input type="hidden" name="placeinfo" id="placeinfo">
				</div>

				<div class="form-group">
					<label for="exercise-date" class="form-label mt-4">운동날짜</label><br />
					<div id="exercise-date">
						<label for="exercise-sdate" class="form-label mt-4"
							id="sdate-text">운동시작일</label> <input type="date"
							class="form-control" id="exercise-sdate" name="startdate">
						<label for="exercise-edate" class="form-label mt-4"
							id="edate-text">운동종료일</label> <input type="date"
							class="form-control" id="exercise-edate" name="enddate">
					</div>
				</div>


				<div class="form-group">
					<label for="exampleSelect1" class="form-label mt-4">참가인원수</label>
					<select class="form-select" id="exampleSelect1" name="applicantMax">
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
						<option>5</option>
					</select>
				</div>
				<div class="form-group">
					<label for="applicant" class="form-label mt-4">참가자</label>
					<textarea class="form-control" id="applicant" rows="3"></textarea>
				</div>
				<div class="form-group">
					<label for="formFile" class="form-label mt-4">사진 업로드</label> <input
						class="form-control" type="file" id="formFile" name="img1">
				</div>

				<button type = "button" onclick="everyExerciseWriteOk()" class="btn btn-primary">등록하기</button>
			</fieldset>
		</form>
	</div>

	<script type="text/javascript"
		src="${url}/js/exercise/every_exerciseWrite.js"></script>
</body>
</html>