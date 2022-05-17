$(function() {
	$(selectCategory).on('change', function() {
		$('tbody').html('');
		$.ajax({
			url: "/mypage/selectCategoryMyBoardList",
			data: "category="+$(this).val(),
			type: "get",
			success: function(r) {
				let now = new Date();
				let nowDate = new Date(now.getFullYear(), now.getMonth(), now.getDate());
				r.forEach(data => {
					let writeDateTime = data.writedate.split(" ");
					console.log(writeDateTime);
					let writeDate = writeDateTime[0].split("-");
					console.log(writeDate);
					let writeTime = writeDateTime[1].split(":");
					console.log(writeTime);
					
					if(now.getFullYear() != writeDate[0]){
						data.writedate = writeDateTime[0];
					}else if(new Date(writeDate[0], writeDate[1]-1, writeDate[2]).getTime() != now.getTime()){
						data.writedate = writeDate[1]+"-"+writeDate[2];
					}else {
						data.writedate = writeTime[0]+":"+writeTime[1];
					}
					
					let html = "<tr>";
					
					switch (data.category) {
						case 'everyMeal':
							html += "<td>모두의 식단</td>";
							break;
							
						case 'myExercise':
							html += "<td>나만의 운동</td>";
							break;
							
						case 'free':
							html += "<td>자유 게시판</td>";
							break;
							
						case 'challenge':
							html += "<td>챌린지 게시판</td>";
							break;
							
						case 'ba':
							html += "<td>Before&After</td>";
							break;
					}
					
					html += "<td><a href='#'>"+data.title+"</a></td>";
					html += "<td>"+data.writedate+"</td>";
					html += "<td>"+data.hit+"</td>";
					html += "</tr>";
					
					$('tbody').append(html);
				})
			}
		});
	});
});