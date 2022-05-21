<link rel="stylesheet" href="/css/member/resetPwd.css" type="text/css">
<script src="/js/member/resetPwd.js"></script>
<div class="resetPwd container">

	<form id="resetPwdFrm" class="resetPwdFrm" method="post" action="/member/resetPwdOk">
		<a href="/" class="newPwd-img"><img src="/img/mohel-logo-11.png"></a>
		<h3>새 비밀번호 설정</h3>
		<p>새로운 비밀번호를 입력해주세요.</p>
		<input type="hidden" name="authCode" value="${redirectAuthCode}">
		<ul>
			<li>새로운 비밀번호</li>
			<li><input type="password" id="newPwd" name="pwd"></li>
			<li>새 비밀번호 확인</li>
			<li><input type="password" id="newPwdCk"></li>
			<li id="pwdCkMsg"><span style="color: gray;">영문+숫자+특수문자 사용(8~20자리)</span></li>
			<li><button class="newPwd-button">비밀번호 설정</button></li>
		</ul>
	</form>

</div>