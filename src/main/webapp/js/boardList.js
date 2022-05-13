$(function(){
		$("#searchFrm").submit(function(){
			if($("#searchWord").val()==''){
				alert("검색어를 입력하세요");
				return false;
			}
		});
	}); 
