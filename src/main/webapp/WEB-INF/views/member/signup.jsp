<link rel="stylesheet" href="/css/member/signup.css" type="text/css">
<script src="/js/member/signup.js"></script>
<div class="signup-main container">
	<div class="logo"></div>
	<form id="signupFrm" method="post" action="/member/signupOk" enctype="multipart/form-data">
		<ul id="sugnupFrmBox">
			<li>
				<input id="profile" type="file" accept="image/jpg, image/jpeg, image/png" multiple="multiple" hidden="true">
				<label class="thumbnail-area" for="profile" title="이미지 업로드"><img id="profileImg" src="/img/member/defaultProfile.png"></label>
				<br><input id="defaultProfile" type="button" value="이미지 제거">
			</li>
			<li class="not-ck-msg">
				<div class="form-use-btn">
					<input id="email" name="email" type="text" placeholder="이메일">
					<input type="button" value="인증">
				</div>
				<div class="form-use-btn">
					<input id="emailCk" type="text" placeholder="이메일 인증번호">
					<input type="button" value="확인">
				</div>
			</li>
			<li>
				<input id="pwd" name="pwd" type="password" placeholder="비밀번호">
				<input id="pwdCk" type="password" placeholder="비밀번호 확인">
				<div class="ck-msg">비밀번호가 일치합니다!!</div>
			</li>
			<li>
				<input id="nickname" name="nickname" type="text" placeholder="닉네임">
				<div class="ck-msg">중복된 닉네임 입니다.</div>
			</li>
			<li>
				<div class="form-use-btn">
					<input id="tel" name="tel" type="text" placeholder="전화번호">
					<input type="button" value="인증">
				</div>
				<div class="form-use-btn">
					<input id="telCk" type="text" placeholder="인증번호 입력">
					<input type="button" value="확인">
				</div>
			</li>
			<hr style="width: 1000px; position: relative; left: -250px;">
			<li><h3>기초/활동 대사량 체크</h3></li>
			<li class="not-ck-msg list-left">
				생년월일: <input id="birthdate" name="birthdate" type="date">
				<label for="m">남 :</label>
				<input id="m" type="radio" name="gender" value="m">
				<label for="w">여 :</label>
				<input id="w" type="radio" name="gender" value="w">
			</li>
			<li class="list-left">
				<label class="body-size-label" for="height">키</label>
				<input id="height" name="height" class="body-size-input" type="text"> cm
			</li>
			<li class="list-left">
				<label class="body-size-label" for="weight">체중</label>
				<input id="weight" name="weight" class="body-size-input" type="text"> kg
			</li>
			<li class="list-left">
				<h4 class="not-ck-msg">활동량</h4>
				<ul class="active-quantity">
					<li>
						<label for="select1">거희 운동을 하지 않음</label>
						<input id="select1" name="active" type="radio" value="1.2">
					</li>
					<li>
						<label for="select2">가벼운 운동(주 1~3일)</label>
						<input id="select2" name="active" type="radio" value="1.375">
					</li>
					<li>
						<label for="select3">보통(주 3~5일)</label>
						<input id="select3" name="active" type="radio" value="1.55">
					</li>
					<li>
						<label for="select4">적극적으로 운동(주 5~7일)</label>
						<input id="select4" name="active" type="radio" value="1.725">
					</li>
					<li>
						<label for="select5">매우 적극적으로 운동(매일)</label>
						<input id="select5" name="active" type="radio" value="1.9">
					</li>
				</ul>
			</li>
			<li class="list-left not-ck-msg bmr-amr">
				<div id="BMR">기초대사량: <span></span></div>
				<div id="AMR">활동대사량: <span></span></div>
			</li>
			<li><button>가입하기</button></li>
		</ul>
	</form>
</div>