<link href="${url}/css/exercise/every_exerciseView.css" rel="stylesheet"
	type="text/css" />
<!-- 카카오 api 라이브러리  -->
<!-- services와 clusterer, drawing 라이브러리 불러오기 -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=096ec0036610b77d5b4e1aa8571cbb1e&libraries=services,clusterer,drawing"></script>
<script>
	document.addEventListener("DOMContentLoaded", function(event){
		//지도 중심좌표 설정
		map.setCenter(new kakao.maps.LatLng(${y}, ${x}));
		//마커 생성
		var marker = new kakao.maps.Marker({
		    map: map,
		    position: new kakao.maps.LatLng(${y}, ${x})
		});
		marker.setMap(map);
	})
	function every_del() {
		// 사용자가 yes(true)와 no(false)를 선택할 수 있는 대화상자
		if (confirm("삭제하시겠습니까?")) {
			// 확인버튼 선택시
			location.href = "/exercise/every_exerciseDel?no=${vo.no}";
		}
	}

	// 댓글----------------------------------------------------------
	$(function() {
		// 댓글 목록을 가져오는 함수
		function replyListAll() { // 현재 글의 댓글을 모두 가져오기
			var url = "/exercise/exerciseReplyList";
			var params = "no=${vo.no}"; // 32번 글인경우: no=32 

			$.ajax({
						url : url,
						data : params,
						success : function(result) {
							var $result = $(result); // vo, vo, vo, vo...

							var tag = "<ul clss='list-group'>";

							$result
									.each(function(idx, vo) {
										tag += "<li class='list-group-item'><div>" + vo.nickname;
										tag += "(" + vo.writedate + ")";
										//  'goguma' ==  'goguma'
										if (vo.nickname == '${userInfo.nickname}') {

											tag += "<input type='button' class='btn btn-warning' value='수정' style='margin:5px; background-color:white;'/>";
											tag += "<input type='button' class='btn btn-danger' value='삭제' style='background-color:white; color:black;'title='"+vo.no+"'/>";
										}

										tag += "<br/>" + vo.contents + "</div>";

										// 본인글일때 수정폼이 있어야 한다.
										if (vo.nickname == '${userInfo.nickname}') {
											tag += "<div style='display:none;'><form method='post'>";
											tag += "<input type='hidden' name='no' value='"+vo.no+"'/>";
											tag += "<textarea name='contents' style='width:400px;height:50px;'>"
													+ vo.contents
													+ "</textarea>";
											tag += "<input type='submit' class='btn btn-warning' value='수정' style='background-color:white; position:relative; top:-20px; margin:5px;'/>";
											tag += "</form></div>";

										}
										tag += "</li>"; // vo의 개수만큼 순환
									});

							tag += "</ul>";
							$("#replyList").html(tag);

						},
						error : function(e) {
							console.log(e.responseText);
						}
					})
		}

		// 댓글등록
		$("#replyFrm").submit(function() {
			event.preventDefault(); // form 기본이벤트 제거
			if ($("#contents").val() == '') {
				alert("댓글 입력 후 등록하세요.");
				return;
			} else { // 댓글을 입력한 경우
				var params = $("#replyFrm").serialize(); // form에 있는 데이터가 담김
				$.ajax({
					url : '/exercise/exerciseReplyWriteOk',
					data : params,
					type : 'POST',
					success : function(r) {
						$("#reply-contents").val("");
						// 에러가 안난다면 => 댓글목록이 refresh되어야 한다. 
						replyListAll();
					},
					error : function(e) {
						console.log(e.responseText);
					}
				});
			}
		});
		// 댓글 수정(Edit)버튼 선택 시 해당 폼 보여주기
		$(document).on('click', '#replyList input[value=수정]', function() {
			$(this).parent().css("display", "none"); // 숨기기
			// 보여주기
			$(this).parent().next().css("display", "block");
		});
		// 댓글 수정(DB)
		$(document).on('submit', '#replyList form', function() {
			event.preventDefault();
			// 데이터
			var params = $(this).serialize();
			var url = '/exercise/exerciseReplyEditOk';
			$.ajax({
				url : url,
				data : params,
				type : "POST",
				success : function(result) {
					console.log(result);
					replyListAll();
				},
				error : function() {
					console.log('수정에러발생');
				}
			});
		});

		// 댓글 삭제
		$(document).on('click', '#replyList input[value=삭제]', function() {
			if (confirm('댓글을 삭제하시겠습니까?')) {
				var params = "no=" + $(this).attr("title");
				console.log(params)
				$.ajax({
					url : '/exercise/exercisegReplyDel',
					data : params,
					success : function(result) {
						console.log(result);
						replyListAll();
					},
					error : function() {
						console.log("댓글삭제에러...");
					}
				})
			}
		});

		// 현재글의 댓글
		replyListAll();
	});
</script>
<div class="container" id="every-exercise-mainFrm">
	<form method="post" id="every-exerciseFrm">
		<input type="hidden" id="no" value=${vo.no }>
		<fieldset>
			<legend id="every-main-title">모두의 운동</legend>
			<h5>운동 함께할 사람 모집</h5>
			<div>
				<div class="form-group row">
					<label for="title" class="col-sm-2 col-form-label">제목</label> <input
						type='hidden' id='dbExerciseNo' value='${vo.no }'>
					<div class="col-sm-10">
						<input readonly type="text" class="form-control"
							placeholder="${vo.title}"><br/>
					</div><label for="title" class="col-sm-2 col-form-label">작성자</label>
					<div class="col-sm-10">
						<input readonly type="text" class="form-control"
							placeholder="${vo.nickname}">
					</div>
				</div>
				
				<div class="form-group">
					<label for="keyword" class="form-label mt-4">오늘의 키워드</label> <input
						type="text" class="form-control" id="keyword" readonly
						placeholder="${vo.hashtag }"> <small id="keywordhelp"
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
				</div>

				<div class="form-group">
					<label for="location" class="form-label mt-4">모임장소</label> <input
						value="${placeinfo}" class="form-control" readonly id="location">
				</div>
				<div class="form-group">
					<label for="exercise-date" class="form-label mt-4">운동날짜</label><br />
					<div id="exercise-date">
						<label for="exercise-sdate" class="form-label mt-4"
							id="sdate-text">운동시작일</label> <input type="datetime-local"
							class="form-control" id="exercise-sdate" name="startdate"
							value="${vo.startdate}" readonly> <label
							for="exercise-edate" class="form-label mt-4" id="edate-text">운동종료일</label>
						<input type="datetime-local" class="form-control"
							id="exercise-edate" name="enddate" value="${vo.enddate}" readonly>
					</div>
				</div>
				<div class="form-group">
					<label for="exampleSelect1" class="form-label mt-4">최대 참가자
						수</label> <input type="text" class="form-control" id="exampleSelect1"
						name="applicantMax" value="${vo.applicantMax}" readonly>
				</div>
				<div class="form-group">
					<label for="applicant" class="form-label mt-4">참가자</label>
					<input type="hidden" id="applicantCnt" value=${vo.applicantCnt} >
					<!-- 신청 신청취소 구현 후 다시하기-->
					<!-- START : 작성자 화면 -->
					<c:if test="${nickname == vo.nickname}">
						 <ul class="author-view">
                         	<li class="author-view-label">번호</li>
                         	<li class="author-view-label">참가 상태</li>
                         	<li class="author-view-label">닉네임</li>
                         	<li class="author-view-label">승낙/거절</li>
                         	<c:forEach var="emvo" items="${emvo}" varStatus="st">
                         		<c:if test="${vo.no == emvo.exerciseNo}">
                         			<li>${emvo.no }</li>
                         			<li>
                         				<span style = "display:none" id="exerciseStatus${st.index}">${emvo.status}</span>
                         				<span id="exerciseStatusShow${st.index}"><a href="/member/login">열람하기</a></span>
                         			</li>
                         			<li><span id="applierNickname${st.index}">${emvo.nickname }</span></li>
                         			<li>
                                    	<input type='button' id="stateUpdateBtn${st.index}" class="applicantSave" value="승낙">
                                    	<input type='button' id="stateDeleteBtn${st.index}" class="applicantDel" value="거절">
                                    	<span id="exerciseTogether${st.index}"></span>
                                    </li>
                         		</c:if>
                         	</c:forEach>
                         </ul>
					</c:if>
					<form id="nicknameTest">
						<input type="text" name="applicantNickName" id="applicantNickName"
							style="display: none;">
					</form>
					<!-- 작성자 화면 : END -->
					<!-- START : 작성자가 아닐 때 화면 추후에 조건식을 !=로 변경 -->
					<c:if test="${nickname != vo.nickname}">
						<ul class="applier-view">
							<li class="applier-view-label">번호</li>
							<li class="applier-view-label">참가 상태</li>
							<li class="applier-view-label">닉네임</li>
							<c:forEach var="emvo" items="${emvo}" varStatus="st">
								<c:if test="${vo.no == emvo.exerciseNo}">
									<li>${emvo.no }</li>
									<li><span style="display: none"
										id="exerciseStatus${st.index}">${emvo.status}</span> <span
										id="exerciseStatusShow${st.index}"><a
											href="/member/login">열람하기</a></span></li>
									<li><span id="applierNickname${st.index}">${emvo.nickname }</span></li>
								</c:if>
							</c:forEach>
						</ul>
						<div id="applicantBox">
							<input type="hidden" id="loginNickName" value=${nickname}>
							<input type="button" id="excerciseMemberApply" class='btn btn-light'
								onclick="excerciseMember()" value="참가 신청하기" /> <input
								type="button" id="excerciseMemberApplyDel" class='btn btn-light'
								onclick="excerciseMemberCancel()" value="참가 신청취소" />
						</div>
					</c:if>
					<!-- 작성자가 아닐 때 화면 : END-->
				</div>

			</div>
		</fieldset>
	</form>
	<div>
		<!-- 로그인 아이디와 글쓴이가 같을 경우 수정삭제 표시 -->
		<c:if test="${nickname==vo.nickname}">
			<button type="button" class="btn btn-warning"
				onclick="location.href='/exercise/every_exerciseEdit?no=${vo.no }' ">수정하기</button>
			<button type="button" class="btn btn-danger"
				onclick="javascript:every_del()" id="delete-btn">삭제하기</button>

			<button type="submit" class="btn btn-primary"
				onclick="location.href='/exercise/every_exerciseList' "
				id="every-exerciselist-btn">모두의 운동목록</button>
		</c:if>
	</div>

	<!-- 댓글쓰기 -->

	<c:if test="${nickname!=null}">
		<hr id="line"/>
		<form method="post" id="replyFrm">
			<input type="hidden" name="no" value="${vo.no }" />
			<div class="form-group">
				<label for="contents" class="form-label mt-4">댓글 작성하기</label>
				<textarea class="form-control" id="reply-contents" rows="1"
					name="contents"></textarea>
			</div>

			<input type="submit" class="btn btn-outline-dark" value="댓글등록하기" id="reply-submit"/>
		</form>
	</c:if>

	<!-- 댓글 목록이 나올자리 -->
	<div id="replyList"></div>
</div>
<script type="text/javascript"
	src="${url}/js/exercise/every_exerciseView.js"></script>