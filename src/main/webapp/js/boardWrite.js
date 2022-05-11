$(function(){
	CKEDITOR.replace("contents");
		$("#board-Frm").submit(function(){
			if($("#title").val()==''){
				alert("글 제목을 입력하세요");
				return false;
			}
			if(CKEDITOR.instances.contents.getData()==''){
				alert("글 내용을 입력하세요");
				return false;
			}
		});
	}); 