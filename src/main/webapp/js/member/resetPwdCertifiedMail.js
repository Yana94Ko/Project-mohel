$(function() {
	// 선택시 테두리 굵은 검은색 변경
	$(".form-use-btn>input:first-child").on('focus', function() {
		$(this).parent().css('outline', '2px solid black');
	});
	$(".form-use-btn>input:first-child").on('blur', function() {
		$(this).parent().css('outline', 'none');
	});
	
	// 인증박스 활성화
	const activeCheckBox = (element) => {
		element.each(function () {
			$(this).removeAttr('disabled').parent().css('background-color', '#fff');
		});
	}
	// 인증박스 비활성화
	const disabledCheckBox = (element) => {
		element.each(function () {
			$(this).attr('disabled','disabled').parent().css('background-color', '#FAFAFA');
		});
	}
	
	var emailCheck = false;
	// 이메일 인증
	var mailCkNum = "";
	let min = 0;
	let sec = 0;
	let interval = null;
	var emailCkTime = function() {
		if(sec==0){
			min--;
			sec = 60;
		}
		sec--;
		let secStr = sec;
		if(sec<10) secStr = "0"+sec;
		
		$('#emailCheckBox>span').text("0"+min+":"+secStr);
		
		if(sec==0 && min==0){
			mailCkNum = "";
			clearInterval(interval);
		}
	};
	
	let regEmail = /^\w{6,}[@][a-zA-Z]{2,}[.][a-zA-Z]{2,3}([.][a-zA-Z]{2,3})?$/;
	$('#sendEmailCkNum').click(function() {
		emailCheck = false;
		mailCkNum = "";
		var str = '';
		if(regEmail.test($('#email').val())){
			$.ajax({
				url: '/member/searchEmail',
				data: 'email='+$('#email').val(),
				type: 'post',
				success: function(result) {
					if(result==1){
						activeCheckBox($('#emailCheckBox>input'));
						$.ajax({
							url: '/member/checkMail',
							data: "email="+$('#email').val(),
							type: 'post',
							success: function(result) {
								mailCkNum=result;
							}
						});
						$('#emailCheckBox>span').text("10:00");
						min = 10;
						sec = 0;
						clearInterval(interval);
						interval = setInterval(emailCkTime, 1000);
						str = '<span style="color: green;">메일이 발송되었습니다.</span>';
					}else {
						str = '<span style="color: red;">해당 메일로 가입된 계정이 없습니다.</span>';
					}
					$("#emailCkMsg").html(str);
				}
			});
		}else {
			str = '<span style="color: red;">이메일을 확인해주세요.</span>';
		}
		$("#emailCkMsg").html(str);
	});
	$('#emailCkBtn').on('click', function() {
		var str='';
		if(mailCkNum==$('#emailCk').val() && $('#emailCk').val().length==6){
			emailCheck = true;
			str = '<span style="color: blue;">인증 되었습니다.</span>';
			disabledCheckBox($('#emailCheckBox>input'));
			clearInterval(interval);
			$('#emailCheckBox>span').text('');
		}else {
			str = '<span style="color: red;">인증번호를 확인해주세요</span>';
		}
		$("#emailCkMsg").html(str);
	});
	$('#email').on('input', function(){
		emailCheck = false;
		$('#emailCk').val('');
		$("#emailCkMsg").html('');
		$('#emailCheckBox>span').text('');
		clearInterval(interval);
		disabledCheckBox($('#emailCheckBox>input'));
	});
	
	$(findPwdFrm).submit(function() {
		if(!emailCheck){
			alert("이메일 인증을 해주세요.");
			return false;
		}
	})
});