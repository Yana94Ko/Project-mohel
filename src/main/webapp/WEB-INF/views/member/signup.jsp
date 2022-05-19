<link rel="stylesheet" href="/css/member/signup.css" type="text/css">
<script src="/js/member/signup.js"></script>
<div class="signup-main container">
	<img id="signupLogo" src="/img/mohel-logo-05.png">
	<form id="signupFrm" method="post" action="/member/signupOk" enctype="multipart/form-data">
		<ul id="sugnupFrmBox">
			<li>
				<input id="profile" name="filename" type="file" hidden="true" accept="image/jpg, image/jpeg, image/png">
				<c:if test="${kakaoVO==null || kakaoVO.profile==null}">
					<label for="profile" title="이미지 업로드"><img class="profile-img" id="profileImg" src="/img/profile/defaultProfile.png"></label>
				</c:if>
				<c:if test="${kakaoVO!=null && kakaoVO.profile!=null}"> 
					<label for="profile" title="이미지 업로드"><img class="profile-img" id="profileImg" src="${kakaoVO.profile }"></label>
				</c:if>
				<br><input id="defaultProfile" type="button" value="이미지 제거">
			</li>
			
			<li>
				<c:if test="${kakaoVO!=null}">
					<input id="email" name="email" class="text-box" type="hidden" placeholder="이메일" value="${kakaoVO.email }">
				</c:if>
				<c:if test="${kakaoVO==null}">
					<div class="form-use-btn input-first">
						<input id="email" name="email" class="text-box" type="text" placeholder="이메일">
						<input id="emailCheckNumSend" type="button" value="인증">
					</div>
					<div id="emailCheckBox" class="form-use-btn check">
						<input id="emailCk" class="text-box" type="text" placeholder="인증번호 6자리" disabled>
						<input id="emailCkBtn" type="button" value="확인" disabled>
					</div>
					<div id="emailCkMsg" class="ck-msg"></div>
				</c:if>
			</li>
			
			<c:if test="${kakaoVO==null}">
				<li>
					<input id="pwd" name="pwd" class="text-box input-first" type="password" placeholder="비밀번호">
					<input id="pwdCk" class="text-box" type="password" placeholder="비밀번호 확인">
					<div id="pwdCkMsg" class="ck-msg"><span style="color: gray;">영문+숫자+특수문자 사용(8~20글자)</span></div>
				</li>
			</c:if>
			<li>
				<input id="nickname" name="nickname" class="text-box" type="text" placeholder="닉네임" value="${kakaoVO.nickname }">
				<div id="nicknameCkMsg" class="ck-msg"><span style="color: gray;">문자+숫자 사용(2~10글자)</span></div>
			</li>
			<li>
				<div class="form-use-btn input-first">
					<input id="tel" name="tel" class="text-box" type="text" placeholder="전화번호">
					<input id="telCheckNumSend" type="button" value="인증">
				</div>
				<div id="telCheckBox" class="form-use-btn check">
					<input id="telCk" class="text-box" type="text" placeholder="인증번호 입력" disabled>
					<input id="telCkBtn" type="button" value="확인" disabled>
				</div>
				<div id="telCkMsg" class="ck-msg"></div>
			</li>
			
			<li><h3>기초/활동 대사량 측정</h3></li>
			<li id="metabolicRateList">
				<ul id="metabolicRateBox">
					<li class="not-ck-msg list-left">
						생년월일 <input id="birthdate" name="birthdate" type="date">
						<input id="age" name="age" type="hidden">
						<div class="selectGender">
							<div class="m-box">
								<label for="m">남 </label>
								<input id="m" type="radio" name="gender" value="m" checked>
							</div>
							<div class="w-box">
								<label for="w">여 </label>
								<input id="w" type="radio" name="gender" value="w">
							</div>
						</div>
					</li>
					<li class="body-size list-left">
						<div id="heightBox">
							<label class="body-size-label" for="height">키</label>
							<input id="height" name="height" class="body-size-input" type="text"> cm
						</div>
						<div id="weightBox">
							<label class="body-size-label" for="weight">체중</label>
							<input id="weight" name="weight" class="body-size-input" type="text"> kg
						</div>
					</li>
					<li>
						<h4 class="not-ck-msg">활동량</h4>
						<ul class="active-quantity">
							<li>
								<label for="select1">거의 운동을 하지 않음</label>
								<input id="select1" name="active" type="radio" value="0.2">
							</li>
							<li>
								<label for="select2">가벼운 운동(주 1~3일)</label>
								<input id="select2" name="active" type="radio" value="0.375">
							</li>
							<li>
								<label for="select3">보통(주 3~5일)</label>
								<input id="select3" name="active" type="radio" value="0.55">
							</li>
							<li>
								<label for="select4">적극적으로 운동(주 5~7일)</label>
								<input id="select4" name="active" type="radio" value="0.725">
							</li>
							<li>
								<label for="select5">매우 적극적으로 운동(매일)</label>
								<input id="select5" name="active" type="radio" value="0.9">
							</li>
						</ul>
					</li>
					<li><input id="checkMetabolic" type="button" value="측정"></li>
					<li class="list-left not-ck-msg bmr-amr">
						<div id="BMR">기초대사량 <span></span></div>
						<div id="AMR">활동대사량 <span></span></div>
					</li>
				</ul>
			</li>
			<li class="submit-btn"><button>가입하기</button></li>
		</ul>
	</form>
</div>