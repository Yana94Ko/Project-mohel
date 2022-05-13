<!-- CSS -->
<link rel="stylesheet" href="/css/mypage/userEdit.css" type="text/css">
<script type="module" src="/js/mypage/userEdit.js"></script>
<div class="mypage-main container">
	<%@include file="/WEB-INF/views/inc/mypage/mypageNav.jsp" %>
	
	<form method="post" class="info-box" enctype="multipart/form-data">
		<div class="info-basic">
			<div class="basic-img-box">
				<input id="imgFile" name="profileImg" type="file" hidden="true" accept="image/jpg, image/jpeg, image/png">
				<input id="profile" name="profile" type="hidden" value="${userInfo.profile }">
				<c:if test="${userInfo.profile=='' || userInfo.profile==null}">
					<label for="imgFile"><img id="profileImg" class="profile-img" src="/img/profile/defaultProfile.png"></label>
				</c:if>
				<c:if test="${userInfo.profile!=''}">
					<label for="imgFile"><img id="profileImg" class="profile-img" src="${userInfo.profile }"></label>
				</c:if>
				<input id="defaultProfile" type="button" value="기본 이미지">
			</div>
			<ul>
				<c:if test="${kakao!=true }">
					<li>이메일</li>
					<li class="basic-list-email">${userInfo.email }</li>
				</c:if>
				<li>닉네임</li>
				<li class="basic-list-nickname">
					<input class="basic-info-input" type="text" value="${userInfo.nickname }">
					<i class="reg-info-icon bi bi-info-circle"></i>
					<div class="ck-msg"><span>사용 가능합니다.</span></div>
					<div class="reg-info"><span>문자+숫자 사용(2~10글자)</span></div>
				</li>
				<li>연락처</li>
				<li class="basic-list-tel">
					<div class="basic-tel-box">
						<input id="tel" name="tel" class="basic-info-input" type="text" value="${userInfo.tel }">
						<input id="telCheckNumSend" class="tel-btn btn btn-info btn-sm" type="button" value="인증">
					</div>
					<div id="basicTelCk" class="basic-tel-box">
						<input id="telCk"class="basic-info-input" type="text" placeholder="인증번호 6자리">
						<input id="telCkBtn" class="tel-btn btn btn-success btn-sm" type="button" value="확인">
						<div id="telCkMsg" class="ck-msg"><span>인증되었습니다.</span></div>
					</div>
				</li>
			</ul>
		</div>
		<div class="info-metabolic-rate">
			<h4>기초/활동 대사량</h4>
			<ul class="metabolic-rate-values">
				<li>
					<input id="birthdate" name="birthdate" type="date" value="${userInfo.birthdate }">
					<div class="gender-box">
						<label for="man">남</label>
						<input id="man" name="gender" type="radio" value="m" <c:if test="${userInfo.gender=='m' }">checked</c:if>>
						<label for="woman">여</label>
						<input id="woman" name="gender" type="radio" value="w" <c:if test="${userInfo.gender=='w' }">checked</c:if>>
					</div>
				</li>
				<li>
					<div>
						<label for="height">키</label>
						<input id="height" name="height" type="text" value="${userInfo.height }"> cm
					</div>
					<div>
						<label for="weight">체중</label>
						<input id="weight" name="weight" type="text" value="${userInfo.weight }"> kg
					</div>
				</li>
			</ul>
			<hr style="width: 600px;">
			<h4>활동량</h4>
			<ul class="activity-rate">
				<li>
					<label for="select1">거의 운동을 하지 않음</label>
					<input id="select1" name="active" type="radio" value="0.2" <c:if test="${userInfo.active==0.2 }">checked</c:if>>
				</li>
				<li>
					<label for="select2">가벼운 운동 (주 1~3일)</label>
					<input id="select2" name="active" type="radio" value="0.375" <c:if test="${userInfo.active==0.375 }">checked</c:if>>
				</li>
				<li>
					<label for="select3">보통 (주 3~5일)</label>
					<input id="select3" name="active" type="radio" value="0.55" <c:if test="${userInfo.active==0.55 }">checked</c:if>>
				</li>
				<li>
					<label for="select4">적극적으로 운동 (주 5~7일)</label>
					<input id="select4" name="active" type="radio" value="0.725" <c:if test="${userInfo.active==0.725 }">checked</c:if>>
				</li>
				<li>
					<label for="select5">매우 적극적으로 운동 (매일)</label>
					<input id="select5" name="active" type="radio" value="0.9" <c:if test="${userInfo.active==0.9 }">checked</c:if>>
				</li>
			</ul>
		</div>
		<button class="info-edit-pw-btn btn btn-info btn-sm">수정</button>
		<c:if test="${kakao!=true }">
			<input id="editCkPw" class="info-edit-pw-btn" type="password" placeholder="Password">
		</c:if>
	</form>
</div>
