$(function() {
	$(selectCategory).on('change', function() {
		let link = '/mypage/myComment';
		if($(this).val()!=""){
			link+='?category='+$(this).val();
		}
		location.href=link;
	});
});