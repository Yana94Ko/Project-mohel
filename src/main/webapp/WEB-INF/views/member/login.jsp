<link rel="stylesheet" href="/css/member/login.css" type="text/css">
<script src="/js/member/login.js"></script>
<div class="login-main container">
	<img class="login-logo" src="/img/mohel-logo-05.png">
	<form id="loginFrm" method="post" action="/member/loginOk">
		<div class="info-box">
			<div class="info-input email-cast">
				<i class="bi bi-person"></i>
				<input id="email" name="email" type="text" placeholder="Email" value="${email}">
			</div>
			<div class="info-input pwd-cast">
				<i class="bi bi-key"></i>
				<input id="pwd" name="pwd" type="password" placeholder="Password">
			</div>
		</div>
		<button id="loginBtn">로그인</button>
			<div class="check-msg">
				<c:if test="${nologin==true}">
				<span style="color: red;">이메일 또는 비밀번호를 잘못 입력하였습니다.</span>
				</c:if>
			</div>
		<hr>
		<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=8299169b3aa46a93e89d0f3fe4ed0583&redirect_uri=http://localhost:8040/member/kakaologin"><img class="kakao-login-img" src="/img/kakao_login.png"></a>
		
		<a href="/member/signup" class="search-and-signup">아직 회원이 아니신가요?</a>
		<a href="/member/resetPwdCertifiedMail" class="search-and-signup">아이디 또는 비밀번호를 잊어버리셨나요?</a>
	</form>
</div>