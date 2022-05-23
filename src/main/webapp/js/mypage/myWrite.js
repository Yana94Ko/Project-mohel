$(function() {
	$(selectCategory).on('change', function() {
		let link = '/mypage/myWrite';
		if($(this).val()!=""){
			link+='?category='+$(this).val();
		}
		location.href=link;
	});
});