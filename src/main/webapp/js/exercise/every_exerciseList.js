function setPlaceInfo(){
	for(let i=0; i<9; i++){
		if(document.getElementById("placeinfo" + i)){
			let placeinfo = document.getElementById("placeinfo" + i).value;
			//	console.log(placeinfo);
			let placeJsonData = JSON.parse(placeinfo);
			//console.log(placeJsonData.address_name);
			document.getElementById("addressname" + i).innerHTML= placeJsonData.place_name;
		}
	}
}
setPlaceInfo();

/*검색*/
$(function(){
    $("#search-keyword").keyup(function(){
        var value = $(this).val().toLowerCase();
        
        $("p.thumb-text").filter(function(){
			var str=$(this).text().toLowerCase();
            var idx = str.indexOf(value); 
            $(this).parent().toggle(idx>-1);
        });
    });
});