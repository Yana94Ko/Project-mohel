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
function every_del() {
	// 사용자가 yes(true)와 no(false)를 선택할 수 있는 대화상자
	if (confirm("삭제하시겠습니까?")) {
		// 확인버튼 선택시
		location.href = "/exercise/every_exerciseDel?no=${vo.no}";

	}
}
</script>
<script>
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
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container" id="every-exercise-mainFrm">
		<form method="post" action="/exercise/every_exerciseEditOk"
			id="every-exerciseFrm">
			<fieldset>
				<legend id="every-main-title">모두의 운동</legend>
				<h5>운동 함께할 사람 모집</h5>
				<div>
					<div class="form-group row">
						<label for="title" class="col-sm-2 col-form-label">제목</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="title"
								placeholder="${vo.title}">
						</div>
					</div>
					<div class="form-group">
						<label for="keyword" class="form-label mt-4">오늘의 키워드</label> <input
							type="text" class="form-control" id="keyword"
							placeholder="#하체 #스쿼트"> <small id="keywordhelp"
							class="form-text text-muted">오늘의 운동 키워드를 해시태그로 입력하세요</small>
					</div>

					<div class="form-group">
						<label for="contents" class="form-label mt-4">내용</label>
						<textarea class="form-control" id="contents" rows="3"
							name="contents">${vo.contents }</textarea>
					</div>

					<!-- 지도 -->
					<div class="map_wrap">
						<div id="map"
							style="width: 100%; height: 350px; position: relative; overflow: hidden;"></div>

						<div id="menu_wrap" class="bg_white">
							<div class="option">
								<div>
									<div>
										키워드 : <input type="text" id="keyword" size="15">
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
							value="${vo.placeinfo}" class="form-control" name="placeinfo"
							id="location">
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
						<select class="form-select" id="exampleSelect1"
							name="applicantMax">
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
					<input type='hidden' name='category' id='category'
						value='${category}' /> <input type='hidden' name='nickname'
						id='nickname' value='${nickname}' /> <input type='hidden'
						name='no' value='${vo.no }' />

				</div>

			</fieldset>

		</form>
		<div>
			<!-- 로그인 아이디와 글쓴이가 같을 경우 수정삭제 표시 -->

			<button class="btn btn-warning">수정완료</button>
			<button class="btn btn-danger" onclick="javascript:every_del()"
				id="delete-btn">삭제하기</button>


			<button class="btn btn-primary"
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

	<script type="text/javascript"
		src="${url}/js/exercise/every_exerciseEdit.js"></script>
</body>
</html>