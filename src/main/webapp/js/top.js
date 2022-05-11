$(function() {
	$(".top-body-category").mouseenter(() => {
		$('.category-list').animate({
			height: '120px'
		}, 150);
	});
	$(".top-body").mouseleave(() => {
		$('.category-list').animate({
			height: '0'
		}, 150);
	});
	$('#topLogo').click(function() {
		location.href="/";
	});
});