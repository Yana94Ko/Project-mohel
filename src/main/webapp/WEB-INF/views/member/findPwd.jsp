<link rel="stylesheet" href="/css/member/findPwd.css" type="text/css">
<script src="/js/member/findPwd.js"></script>
<div class="findPwd container">

	<form id="findPwdFrm" method="post" action="/member/changePwdSendMail">
		<h3>비밀번호를 잊으셨나요?</h3>
		<p>이메일을 입력해주세요.</p>
		<ul>
			<li>
				<div class="email-ck-input ck-num form-use-btn">
					<input id="email" name="email" type="text" placeholder="Email" value="">
					<input id="sendEmailCkNum" type="button" value="인증">
				</div>
			</li>
			<li>
				<div class="email-ck-input ck-num form-use-btn" id="emailCheckBox">
					<input id="emailCk" type="text" placeholder="인증번호 6자리" disabled>
					<span class="ck-time"></span>
					<input id="emailCkBtn" type="button" value="확인" disabled>
				</div>
				<div id="emailCkMsg" class="ck-msg"></div>
			</li>
			<li><button class="findPwd-button">계속</button></li>
		</ul>
	</form>

	<div class="explain">
		<hr />
		<a class="findPwd-icon"><i class="bi bi-person-circle"></i></a>
		<p>
			가입하신 이메일을 통하여<br> 새로운 비밀번호 설정 링크를 보내드리겠습니다.
		</p>
	</div>
</div>