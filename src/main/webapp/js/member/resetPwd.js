// 글자수 한정
function inputMaxLength(element, max) {
	if($(element).val().length>max){
		$(element).val($(element).val().substring(0, max));
	}
}

$(function() {
	// 비밀번호 확인
	let pwdCheck = false;
	let userpwd = $('#newPwd');
    let userpwdCk = $('#newPwdCk');
    let regPw = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,20}$/;
    const pwCk = function() {
		inputMaxLength(this, 20);
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
        console.log(pwdCheck);
        $("#pwdCkMsg").html(str);
    };
    userpwd.on('input', pwCk);
    userpwdCk.on('input', pwCk);
    
    $(resetPwdFrm).submit(function() {
		if(!pwdCheck){
			alert("비밀번호를 확인해주세요.");
			return false;
		}
	});
});