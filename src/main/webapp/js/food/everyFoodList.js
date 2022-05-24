window.onload = function(){
	$('#everyFoodList-pagination').twbsPagination({
		totalPages: totalPage,	// 총 페이지 번호 수
		visiblePages: 5,	// 하단에서 한번에 보여지는 페이지 번호 수
		startPage: pageNum, // 시작시 표시되는 현재 페이지
		initiateStartPageClick: false,	// 플러그인이 시작시 페이지 버튼 클릭 여부 (default : true)
		first: "<<",	// 페이지네이션 버튼중 처음으로 돌아가는 버튼에 쓰여 있는 텍스트
		prev: "<",	// 이전 페이지 버튼에 쓰여있는 텍스트
		next: ">",	// 다음 페이지 버튼에 쓰여있는 텍스트
		last: ">>",	// 페이지네이션 버튼중 마지막으로 가는 버튼에 쓰여있는 텍스트
		onPageClick: function(event, pnum) {
			location.href = "/everyFoodList?pageNum="+pnum;
		}
	});
	
}