<!-- 구글폰트 -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
<!-- CSS -->
<link rel="stylesheet" href="/css/mypage/userEdit.css" type="text/css">

<div class="mypage-main container">
	<%@include file="/WEB-INF/views/inc/mypage/mypageNav.jsp" %>
	
	<div class="info-box">
		<div class="info-basic">
			<img class="profile-img" src="${userInfo.profile }" width="15%">
			<ul>
				<li>닉네임</li>
				<li>${userInfo.nickname }</li>
				<c:if test="${kakao!=true }">
					<li>이메일</li>
					<li>${userInfo.email }</li>
				</c:if>
				<li>연락처</li>
				<li>${userInfo.tel }</li>
			</ul>
		</div>
		<div class="info-metabolic-rate">
			<h4>기초/활동 대사량</h4>
			<ul>
				<li>
					<label for="select1">거의 운동을 하지 않음</label>
					<input id="select1" name="active" type="radio" value="1.2">
				</li>
				<li>
					<label for="select2">가벼운 운동 (주 1~3일)</label>
					<input id="select2" name="active" type="radio" value="1.375">
				</li>
				<li>
					<label for="select3">보통 (주 3~5일)</label>
					<input id="select3" name="active" type="radio" value="1.55">
				</li>
				<li>
					<label for="select4">적극적으로 운동 (주 5~7일)</label>
					<input id="select4" name="active" type="radio" value="1.725">
				</li>
				<li>
					<label for="select5">매우 적극적으로 운동 (매일)</label>
					<input id="select5" name="active" type="radio" value="1.9">
				</li>
			</ul>
		</div>
	</div>
</div>
