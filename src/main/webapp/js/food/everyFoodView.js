window.onload = function(){
	let foodNameZone = document.getElementById("foodList");
	foodlist.forEach(function(name, idx){
		let each = document.createElement("button");
		each.className = "btn";
		each.title = foodcalories[idx]+" kcal";
		each.innerText = name;
		foodNameZone.appendChild(each);
	});

	const replyFrm = document.getElementById("replyFrm");
	const replyOkBtn = document.getElementById("replyOkBtn");
	console.log(replyOkBtn);
	replyOkBtn.addEventListener("click", function(){
		event.preventDefault();
		const rContent = document.getElementById("replyContent");
		if(rContent.value==""){
			alert("댓글을 입력해 주세요");
			return;
		}
		replyFrm.submit();
	});
	
	const replyEditBtn = [...document.getElementsByClassName("replyEditBtn")];
	replyEditBtn.forEach(function(btn){
		btn.addEventListener("click", function(){
			const bottom = this.parentNode.parentNode;
			bottom.style.display = "none";
			bottom.nextSibling.nextSibling.style.display = "flex";
		});
	});
}

function boardDel(boardNo){
	if(!confirm("글을 삭제하시겠습니까?")){
		return;
	}
	location.href = "/everyFoodDel?no="+boardNo;
}
function replyDel(replyNo){
	if(!confirm("댓글을 삭제하시겠습니까?")){
		return;
	}
	$.ajax({
		url:"/everyFoodReplyDel",
		type:"POST",
		data:{"no":replyNo},
		success:function(result){
			if(result>0){
				alert("삭제 성공");
				location.reload();
			}else{
				alert("삭제 실패");
			}
		}
	})
}
function boardEdit(boardNo){
	location.href = "/everyFoodEdit?no="+boardNo;
}