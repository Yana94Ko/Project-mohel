$(function() {
	// 로그인 input box 스타일
	const iconBorder = (e) => {
		if(e.val().length!=0){
			e.prev().css('color', 'black');
		}else {
			e.prev().css('color', '#ccc');
		}
	}
	iconBorder($('.info-input>input:eq(0)'));
	var inputText = $('.info-input>input');
	inputText.on('focus', function() {
		$(this).parent().css('outline', '2px solid black');
	});
	inputText.on('blur', function() {
		$(this).parent().css('outline', 'none');
	});
	inputText.on('input',function() {
		iconBorder($(this));
	});
	
	let regEmail = /^\w{6,}[@][a-zA-Z]{2,}[.][a-zA-Z]{2,3}([.][a-zA-Z]{2,3})?$/;
	$(loginFrm).submit(function() {
		if($(pwd).val().length<8 || !regEmail.test($(email).val())){
			$('.check-msg').html('<span style="color: red;">이메일 또는 비밀번호를 잘못 입력하였습니다</span>');
			return false;
		}
	});
});