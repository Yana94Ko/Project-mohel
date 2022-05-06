<style>
.bottom {
	display: none;
}
.login-main {
	text-align: center;
}
.login-logo {
	width: 300px;
	margin: 40px 0;
}
#loginFrm {
	width: 500px;
	margin: 0 auto;
	padding: 30px;
	border: 1px solid #D8D8D8;
	background-color: #F9F9F9;
	border-radius: 12px;
}
#loginFrm > * {
	display: block;
}

#loginBtn {
	margin: 5px auto;
}

.info-input {
	margin: 5px auto;
	border: 1px solid #D8D8D8;
	text-align: left;
	height: 45px;
	line-height:40px;
	width: 300px;
	background-color: #fff;
	border-radius: 5px;
}
.info-input>i {
	margin-left: 8px;
	font-size: 22px;
	color: #757575;
}
#loginBtn,
.kakao-login-img {
	width: 300px;
}

.info-input > input {
	border: none;
	background-color: rgba(0,0,0,0);
	position: relative;
	top: -3px;
	width: 250px;
}
.info-input > input:focus {
	outline: none;
}
#loginBtn{
	border: none;
	background-color: #01C9C6;
	color: #fff;
	font-weight: bold;
	border-radius: 5px;
	height: 40px;
	margin-top: 15px;
}

</style>
<div class="login-main container">
	<img class="login-logo" src="/img/mohel-logo-05.png">
	<form id="loginFrm" method="post" action="/member/loginOk">
		<div class="info-box">
			<div class="info-input email-cast">
				<i class="bi bi-person"></i>
				<input id="email" name="eamil" type="text" placeholder="Email">
			</div>
			<div class="info-input pwd-cast">
				<i class="bi bi-key"></i>
				<input id="pwd" name="pwd" type="password" placeholder="Password">
			</div>
		</div>
		<button id="loginBtn">로그인</button>
		<hr>
		<a><img class="kakao-login-img" src="/img/kakao_login.png"></a>
		
		<a>아직 회원이 아니신가요?</a>
		<a>아이디 또는 비밀번호를 잊어버리셨나요?</a>
	</form>
</div>