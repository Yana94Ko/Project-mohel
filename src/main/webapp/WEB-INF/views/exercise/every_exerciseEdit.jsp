<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
<head>
<link href="${url}/css/exercise/every_exerciseEdit.css" rel="stylesheet"
	type="text/css" />
<!-- 카카오 api 라이브러리  -->
<!-- services와 clusterer, drawing 라이브러리 불러오기 -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=096ec0036610b77d5b4e1aa8571cbb1e&libraries=services,clusterer,drawing"></script>
<script>
	// 장소명+주소 가져오기
	 function placeinfo(){
		var placeinfoJson = ${vo.placeinfo};
	 	var placesinfo =placeinfoJson.place_name; //JSON.stringify(places);
		var address_name = placeinfoJson.address_name;
	 	$('#location').val(placesinfo+' '+address_name);	
	 }
</script>
<style>
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body onload="placeinfo()">
	<div class="container" id="every-exercise-mainFrm">
	<form method="post" id="every-exerciseFrm" action="/exercise/every_exerciseEditOk" enctype="multipart/form-data">
		<input type="hidden" name="no" id ="no" value='${vo.no }'>
		<fieldset>
			<legend id="every-main-title">모두의 운동</legend>
			<h5>운동 함께할 사람 모집</h5>
			<div>
				<div class="form-group row">
					<label for="title" class="col-sm-2 col-form-label">제목</label>
					<div class="col-sm-10">
						<input type="text" id="title" name="title" class="form-control" value="${vo.title}">
					</div>
				</div>
				<div class="form-group">
					<label for="keyword" class="form-label mt-4">오늘의 키워드</label>
					<input type="text" class="form-control" name="hashtag" id="hashtag" value="${vo.hashtag }"> 
					<small id="keywordhelp"	class="form-text text-muted">오늘의 운동 키워드를 해시태그로 입력하세요</small>
				</div>
				<div class="form-group">
					<label for="contents" class="form-label mt-4">내용</label>
					<textarea class="form-control" id="contents" rows="3" name="contents">${vo.contents }</textarea>
				</div>
				<!-- 지도 -->
				<!-- 
				<div class="map_wrap">
					<div id="map" style="width: 100%; height: 350px; position: relative; overflow: hidden;"></div>
				</div>

				<div class="form-group">
					<label for="location" class="form-label mt-4">장소</label>
					<input value="${placeinfo}" class="form-control" id="location">
				</div>
				 -->
				 <div class="form-group">
					<label for="location" class="form-label mt-4">모임장소</label> 
					<input type="text" class="form-control" id="location" name="location" >
				 	<input type="hidden" name="placeinfo" id="placeinfo">
				 	
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
					
				 
				</div>
				 
				<div class="form-group">
					<label for="exercise-date" class="form-label mt-4">운동날짜</label><br />
					<div id="exercise-date">
						<label for="exercise-sdate" class="form-label mt-4" id="sdate-text">운동시작일</label> 
						<input type="datetime-local" class="form-control" id="exercise-sdate" name="startdate" value="${vo.startdate}"> 
						<label for="exercise-edate" class="form-label mt-4" id="edate-text">운동종료일</label>
						<input type="datetime-local" class="form-control" id="exercise-edate" name="enddate" value="${vo.enddate}" onchange="setMinValue()">
					</div>
				</div>
				<div class="form-group">
					<label for="exampleSelect1" class="form-label mt-4">최대 참가자 수</label> 
					<input type="text" class="form-control" id="applicantMax" name="applicantMax" value="${vo.applicantMax}">
				</div>
				<div class="form-group">
					<label for="applicant" class="form-label mt-4">참가자</label>
					<!-- 신청 신청취소 구현 후 다시하기-->
					<!-- START : 작성자 화면 -->
					<c:if test="${nickName == vo.nickname}">
						 <ul class="author-view">
                         	<li class="author-view-label">번호</li>
                         	<li class="author-view-label">참가 상태</li>
                         	<li class="author-view-label">닉네임</li>
                         	<li class="author-view-label">승낙/거절</li>
                         	<c:forEach var="vo" items="${lst2}" varStatus="st">
                         		<c:if test="${vo.ridingNo == vo.ridingNo}">
                         			<li><span id="ridingState${st.index}">
                         				<a href="/member/loginForm">열람하기</a></span>
                         			</li>
                         			<li>${vo.nickname }</li>
                         			<li>${vo.gender }</li>
                         			<li>${vo.ridingCount }</li>
                         			<li>${vo.userScore }</li>
                         		</c:if>
                         	</c:forEach>
                         </ul>
					</c:if>
					<!-- 작성자 화면 : END -->
					<!-- START : 작성자가 아닐 때 화면 추후에 조건식을 !=로 변경 -->
					<c:if test="${nickname == vo.nickname}">
                        <input type="hidden" id="loginNickName" value=${nickName}/>
						<input type="button" onclick="excerciseMember()" value="참가 신청하기"/>
						<input type="button" onclick="excerciseMemberCancel()" value="참가 신청취소"/>
					</c:if>
					<!-- 작성자가 아닐 때 화면 : END-->
				</div>
				<div class="form-group">
					<label for="formFile" class="form-label mt-4">사진 업로드</label>
					<input class="form-control" type="file" name="filename" id=filename>
				</div>
			</div>
		</fieldset>
	
	<div>
		<!-- 로그인 아이디와 글쓴이가 같을 경우 수정삭제 표시 -->
		<input type="button" class="btn btn-warning" value="수정완료" onclick="everyExerciseEditOk()">
		

	</div>
	</form>

		<button type="button" class="btn btn-primary"
			onclick="location.href='/exercise/every_exerciseList' "
			id="every-exerciselist-btn">모두의 운동목록</button>
	<!-- 댓글쓰기 -->
	<c:if test="${nickname!=null}">
		<form method="post" id="replyFrm">
			<input type="hidden" name="no" value="${vo.no }" />
			<textarea name="coment" id="coment" style="width: 500px; height: 80px;"></textarea>
			<input type="submit" value="댓글등록" />
		</form>
	</c:if>

	<!-- 댓글 목록이 나올자리 -->
	<div id="replyList"></div>
</div>

	<script type="text/javascript"
		src="${url}/js/exercise/every_exerciseEdit.js"></script>
</body>
</html>