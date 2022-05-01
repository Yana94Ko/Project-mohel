$(function() {
	$(".top-body-category").mouseenter(() => {
		$('.category-list').animate({
			height: '110px'
		}, 150);
	});
	$(".top-and-category").mouseleave(() => {
		$('.category-list').animate({
			height: '0'
		}, 150);
	});
});