// input 숫자만 입력 가능
function onlyNum(element) {
	var regOnlyNum = /[^0-9]/g;
	$(element).val($(element).val().replace(regOnlyNum, ''));
}

// 글자수 한정
function inputMaxLength(element, max) {
	if($(element).val().length>max){
		$(element).val($(element).val().substring(0, max));
	}
}

$(function() {
	// 프로필사진 선택한 사진으로 변경
	const fileInput = document.getElementById('imgFile');
	const handleFiles = () => {
		const selectedFile = [...fileInput.files];
		const fileReader = new FileReader();
		
		fileReader.readAsDataURL(selectedFile[0]);
		fileReader.onload = function() {
			document.getElementById("profileImg").src = fileReader.result;
		};
		$(profile).val('');
	};
	fileInput.addEventListener("input", handleFiles);
	// 이미지제거 클릭시 기본 이미지로 프로필 사진 변경
	$('#defaultProfile').click(function() {
		$('#profileImg').attr('src', '/img/profile/defaultProfile.png');
		$('#imgFile').val('');
		$('#profile').val('');
	});
	
	// 닉네임 정규식 정보
	$('.reg-info-icon').on('mouseenter', function() {
		$(this).removeClass("bi-info-circle");
		$(this).addClass("bi-info-circle-fill");
		$('.reg-info').css('display', 'block');
	});
	$('.reg-info-icon').on('mouseleave', function() {
		$(this).removeClass("bi-info-circle-fill");
		$(this).addClass("bi-info-circle");
		$('.reg-info').css('display', 'none');
	});
	
	$(height).on('input', function() {
		onlyNum(this);
		inputMaxLength(this, 3);
	});
	$(weight).on('input', function() {
		onlyNum(this);
		inputMaxLength(this, 3);
	});
	
	// 전화번호 인증
	var tel = $('#tel');
	tel.on('input', function() {
		onlyNum(this);
		inputMaxLength(this, 11);
	});
	var telCkNum = "";
	var nowTel = tel.val();
	var telCheck = true;
	var regTel = /^010|011|016|017|018|019/
	$('#telCheckNumSend').click(function() {
		var str = '';
		if(tel.val().length<10 || !regTel.test(tel.val())){
			str = '<span style="color: red;">전화번호를 정확히 입력해주세요.</span>';
		} else {
			$('#telCk').val('');
			$(".ck-msg").html('');
			$(basicTelCk).css('display', 'block');
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
		$(telCkMsg).html(str);
	});
	$('#telCkBtn').on('click', function() {
		var str='';
		if(telCkNum==$('#telCk').val() && $('#telCk').val()!=''){
			str = '<span style="color: blue;">인증 되었습니다.</span>'
			telCheck = true;
		}else {
			str = '<span style="color: red;">인증번호를 확인해주세요</span>'
		}
		$("#telCkMsg").html(str);
	});
	$('#tel').on('input', function(){
		if(nowTel==tel.val()){
			telCheck = true;
		}else {
			$('#telCk').val('');
			$("#telCkMsg").html('');
			$(basicTelCk).css('display', 'none');
			telCheck = false;
		}
	});
	
	
	// 닉네임 확인
	let nicknameCheck = true;
    let nicknameMaxLength =10;
    let regNickname = new RegExp("^[\\w가-힣]{2,"+nicknameMaxLength+"}$");
    let nickname = $('#nickname');
    let nowNickname = nickname.val();
    const checkNickname = (e) => {
		inputMaxLength(e, nicknameMaxLength);
		let str = "";
		if(nickname.val() == nowNickname){
			$("#nicknameCkMsg").html('');
			nicknameCheck = true;
		}else if(nickname.val().length>1 && regNickname.test(nickname.val())) {
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
		$("#nicknameCkMsg").html(str);
	}
    nickname.on('change', function() {
		nicknameCheck = false;
		checkNickname(this);
	});
	
	// 나이
	var today = new Date();
	var age = 0;
	const setAge = (e) => {
		var birthdateArr = $(e).val().split('-');
		var birthdate = new Date(birthdateArr[0], birthdateArr[1]-1, birthdateArr[2]);
		
		if(new Date(today.getFullYear(), today.getMonth(), today.getDate()) < birthdate){
			$(e).val(today.toISOString().split('T')[0]);
			age = 0;
		}else {
			age = today.getFullYear() - birthdate.getFullYear();
			var subMonth = today.getMonth()-birthdate.getMonth();
			if(age!=0 && (subMonth<0 || (subMonth === 0 && today.getDate()<birthdate.getDate()))){
				age--;
			}
		}
		$('#age').val(age);
	}
	setAge(birthdate);
	$('#birthdate').on('input', function() {
		setAge(this);
	});
	
	const setABMR = () => {
		var BMR = 0;
		if($('input[name="gender"]:checked').val()=='m'){
			BMR = 66.47+(13.75*$(weight).val())+(5*$(height).val())-(6.76*age);
		}else if($('input[name="gender"]:checked').val()=='w'){
			BMR = 655.1+(9.56*$(weight).val())+(1.85*$(height).val())-(4.68*age);
		}
		var AMR = BMR*$('input[name=active]:checked').val();
		$('#BMR>span').text(Math.round(BMR * 100)/100+'kcal');
		$('#AMR>span').text(Math.round(AMR * 100)/100+'kcal');
	}
	setABMR();
	$('input[name=active], #birthdate, input[name=gender], #height, #weight').on('change', function() {
		setABMR();
	});
	
	$('#userEditFrm').submit(function(e) {
		let userInfo = nicknameCheck && telCheck;
		let metabolicRate = age * $(height).val() * $(weight).val() * $('input[name=active]:checked').val()!=0
		if(!userInfo){
			alert("회원정보를 확인해주세요.");
			return false;
		}else if(!metabolicRate){
			alert("대사량 측정 입력란을 확인해주세요.");
			return false;
		}
		
		try{
			if($(editCkPwd).val().length==0){
				alert("비밀번호를 입력해주세요.");
				return false;
			}else {
				$.ajax({
					url: '/mypage/pwdCk',
					data: 'pwd='+$(editCkPwd).val(),
					type: 'post',
					async: false,
					success: function(r) {
						if(r==0) {
							alert("비밀번호가 틀렸습니다.");
							e.preventDefault();
						}
					}
				});
			}
		}catch (e) {}
	});
});