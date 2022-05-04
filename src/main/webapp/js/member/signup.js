$(function() {
	// 프로필사진 선택한 사진으로 변경
	const fileInput = document.getElementById('profile');
	const handleFiles = (e) => {
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
	
	$(".form-use-btn>input:first-child").on('focus', function() {
		$(this).parent().css('border', '2px solid black').css('border-radius', '4px');
	});
	$(".form-use-btn>input:first-child").on('blur', function() {
		$(this).parent().css('border', '1px solid #ccc').css('border-radius', '0px');
	});
	
	
});