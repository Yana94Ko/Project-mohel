function adminMemberDelete() {
    const nickname = document.getElementById("nickname")
    const pwd = document.getElementById("pwd");
    const body = {
        nickname: nickname.value,
        pwd: pwd.value
    }
    if (confirm("정말로 탈퇴시키겠습니까?")) {
        axios.post("/admin/MemberDelete", body)
            .then((res) => {
                if (res.data === 0) {
                    alert("회원정보를 확인해주세요")
                } else {
                    location.href = "/admin/adminMember";
                }
            })
            .catch((res) => {
                alert(res);
                alert("에러 발생. 운영자에게 문의해주세요")
            })
    }
}
// 글자수 한정
function inputMaxLength(element, max) {
	if($(element).val().length>max){
		$(element).val($(element).val().substring(0, max));
	}
}
$(function() {
	// 비밀번호 확인
	let userpwd = $('#pwd');
    let regPw = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,20}$/;
    const pwCk = function() {
		console.log("dffff")
		inputMaxLength(this, 20);
        let str = "";
        pwdCheck = false;
        if(userpwd.val().length===0){
            str = '<span style="color: gray;">영문+숫자+특수문자 사용(8~20자리)</span>';
        } else if(!regPw.test(userpwd.val())){
            str = '<span style="color: red;">비밀번호 형식이 잘못되었습니다.</span>';
        }
        $("#pwdCkMsg").html(str);
    };
    userpwd.on('input', pwCk);
});
const atv = document.getElementById("active");
const active = document.getElementById('dbActive').value;
for (let i = 0; i < atv.options.length; i++) {
	if (atv.options[i].value == active) {
		atv.options[i].selected = true;
	}
}