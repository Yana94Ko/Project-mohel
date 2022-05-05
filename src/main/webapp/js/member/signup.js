$(function() {
	// 프로필사진 선택한 사진으로 변경
	const fileInput = document.getElementById('profile');
	const handleFiles = () => {
		const selectedFile = [...fileInput.files];
		const fileReader = new FileReader();
		
		fileReader.readAsDataURL(selectedFile[0]);
		fileReader.onload = function() {
			document.getElementById("profileImg").src = fileReader.result;
		};
	};
	fileInput.addEventListener("change", handleFiles);
	// 이미지제거 클릭시 기본 이미지로 프로필 사진 변경
	$('#defaultProfile').click(function() {
		$('#profileImg').attr('src', '/img/member/defaultProfile.png');
	});
	
	// 선택시 테두리 굵은 검은색 변경
	$(".form-use-btn>input:first-child").on('focus', function() {
		$(this).parent().css('border', '2px solid black').css('border-radius', '4px');
	});
	$(".form-use-btn>input:first-child").on('blur', function() {
		$(this).parent().css('border', '1px solid #ccc').css('border-radius', '0px');
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
	var pwdCheck = false;
	var nicknameCheck = false;
	var telCheck = false;
	// 이메일 인증
	var mailCkNum = "";
	let regEmail = /^\w{6,}[@][a-zA-Z]{2,}[.][a-zA-Z]{2,3}([.][a-zA-Z]{2,3})?$/;
	$('#emailCheckNumSend').click(function() {
		if(regEmail.test($('#email').val())){
			$('#emailCk').val('');
			$(".ck-msg:eq(0)").html('');
			activeCheckBox($('#emailCheckBox>input'));
			
			$.ajax({
				url: '/member/checkMail',
				data: "email="+$('#email').val(),
				type: 'post',
				success: function(result) {
					mailCkNum=result;
				}
			});
			str = '<span style="color: green;">메일이 발송되었습니다.</span>';
		}else {
			str = '<span style="color: red;">이메일을 확인해주세요.</span>';
		}
		$(".ck-msg:eq(0)").html(str);
	});
	$('#emailCkBtn').on('click', function() {
		var str='';
		if(mailCkNum==$('#emailCk').val() && $('#emailCk').val()!=''){
			str = '<span style="color: blue;">인증 되었습니다.</span>';
			emailCheck = true;
		}else {
			str = '<span style="color: red;">인증번호를 확인해주세요</span>';
		}
		$(".ck-msg:eq(0)").html(str);
	});
	$('#email').on('input', function(){
		emailCheck = false;
		$('#emailCk').val('');
		$(".ck-msg:eq(0)").html('');
		disabledCheckBox($('#emailCheckBox>input'));
	});
	
	// 비밀번호 확인
	let userpwd = $('#pwd');
    let userpwdCk = $('#pwdCk');
    let regPw = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,20}$/;
    const pwCk = function() {
        let str = "";
        pwdCheck = false;
        if(userpwd.val().length===0 || userpwdCk.val().length===0){
            str = '<span style="color: gray;">영문+숫자+특수문자 사용(8~20자리)</span>';
        } else if(!regPw.test(userpwd.val())){
            str = '<span style="color: red;">비밀번호 형식이 잘못되었습니다.</span>';
        } else if(userpwd.val() != userpwdCk.val()){
            str = '<span style="color: red;">비밀번호가 동일하지 않습니다.</span>';
        } else {
            str = '<span style="color: blue;">비밀번호가 동일합니다.</span>';
            pwdCheck = true;
        }
        $(".ck-msg:eq(1)").html(str);
    };
    userpwd.on('input', pwCk);
    userpwdCk.on('input', pwCk);
    
    // 닉네임 확인
    let nicknameMaxLength =10;
    let regNickname = new RegExp("^[\\w가-힣]{2,"+nicknameMaxLength+"}$");
    let nickname = $('#nickname');
    nickname.on('input', function() {
		let str = "";
		nicknameCheck = false;
		if(nickname.val().length<2) {
			str = '<span style="color: gray;">문자+숫자 사용(2~10글자)</span>';
		}else if(nickname.val().length>nicknameMaxLength){
			nickname.val(nickname.val().substring(0, nicknameMaxLength));
		}else if(regNickname.test(nickname.val())) {
			$.ajax({
				url: "/member/searchNickname",
				data: "nickname="+nickname.val(),
				type: "get",
				async: false,
				success: function(result) {
					if(result==0){
						str = '<span style="color: blue;">사용이 가능합니다.</span>';
						nicknameCheck = true;
					}else {
						str = '<span style="color: red;">중복된 닉네임이 존재합니다.</span>';
					}
				}
			});
		}else {
			str = '<span style="color: red;">사용 불가능한 닉네임입니다.</span>';
		}
		$(".ck-msg:eq(2)").html(str);
	});
	
	var regOnlyNum = /[^0-9]/g;
	// 전화번호 인증
	var tel = $('#tel');
	var phonNumLength = 11;
	tel.on('input', function() {
		$(this).val($(this).val().replace(regOnlyNum, '')); // 숫자만 입력가능
		
		if($(this).val().length>phonNumLength){ // 번호가 11글자를 넘으면 안됨
			$(this).val($(this).val().substring(0, phonNumLength));
		}
	});
	var telCkNum = "";
	var regTel = /^010|011|016|017|018|019/
	$('#telCheckNumSend').click(function() {
		var str = '';
		if(tel.val().length<phonNumLength-1 || !regTel.test(tel.val())){
			str = '<span style="color: red;">전화번호를 정확히 입력해주세요.</span>';
		} else {
			$('#telCk').val('');
			$(".ck-msg:eq(3)").html('');
			activeCheckBox($('#telCheckBox>input'));
			$.ajax({
				url: "/member/sendSMS",
				data: "tel="+$('#tel').val(),
				type: "post",
				success: function(result) {
					telCkNum=result;
				}
			});
			str = '<span style="color: green;">인증번호가 발송되었습니다.</span>';
		}
		$(".ck-msg:eq(3)").html(str);
	});
	$('#telCkBtn').on('click', function() {
		var str='';
		if(telCkNum==$('#telCk').val() && $('#telCk').val()!=''){
			str = '<span style="color: blue;">인증 되었습니다.</span>'
			telCheck = true;
		}else {
			str = '<span style="color: red;">인증번호를 확인해주세요</span>'
		}
		$(".ck-msg:eq(3)").html(str);
	});
	$('#tel').on('input', function(){
		$('#telCk').val('');
		$(".ck-msg:eq(3)").html('');
		disabledCheckBox($('#telCheckBox>input'));
		telCheck = false;
	});
	
	
});