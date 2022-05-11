function setPlaceInfo(){
	for(let i=0; i<9; i++){
		if(document.getElementById("placeinfo" + i)){
			let placeinfo = document.getElementById("placeinfo" + i).value;
			//	console.log(placeinfo);
			let placeJsonData = JSON.parse(placeinfo);
			//console.log(placeJsonData.address_name);
			document.getElementById("addressname" + i).innerHTML= placeJsonData.address_name;
		}
	}
}
setPlaceInfo();