$(function() {
	$('input[name="reason"]').on('change', function() {
		if($(this).attr("id")=='delReason5'){
			$(delReason5Text).removeAttr("disabled");
		}else {
			$(delReason5Text).attr("disabled", true);
		}
	});
	
	$(delFrm).submit(function(e) {
		if($(delReason5).is(':checked') && $(delReason5Text).val().length==0){
			alert("사유를 입력해주세요.");
			return false;
		}
		try{
			if($(pwd).val().length==0){
				alert("비밀번호를 입력해주세요.");
				return false;
			}else {
				$.ajax({
					url: '/mypage/pwdCk',
					data: 'pwd='+$(pwd).val(),
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