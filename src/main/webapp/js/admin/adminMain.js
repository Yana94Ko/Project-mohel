document.addEventListener("DOMContentLoaded", function(){
	//게시글 열람 수, 회원가입 수
	const jointx = document.getElementById('joinChart').getContext('2d');
	var joinLabels=[];
	for(let i = 6; i > 0; i--){
		let today = new Date();
		if((today.getDate()-i)>0){
			var todayFormet = today.getFullYear()+"-"+(today.getMonth()+1)+"-"+(today.getDate()-i);
		}else{
			var todayFormet = today.getFullYear()+"-"+(today.getMonth())+"-"+(today.getDate()-i+30);
		}
		joinLabels.push(todayFormet);
	}
	const joinDatasets = {
			labels: joinLabels,
			datasets: [
				{
					label: '회원유입 현황',
					data: [],
					borderColor: '#ffc0cb' ,
					backgroundColor: '#ffc0cb',
					pointStyle: 'circle',
				    pointRadius: 10,
				    pointHoverRadius: 15
				}
			]
		};
	axios.post("joinCount")
        .then((res) => {
            joinDatasets.datasets[0].data.push(res.data.d6);
            joinDatasets.datasets[0].data.push(res.data.d5);
            joinDatasets.datasets[0].data.push(res.data.d4);
            joinDatasets.datasets[0].data.push(res.data.d3);
            joinDatasets.datasets[0].data.push(res.data.d2);
            joinDatasets.datasets[0].data.push(res.data.d1);
            joinDatasets.datasets[0].data.push(res.data.d0);
            makeJoinChart();
        })
        .catch((res) => {
            console.log(res);
    	})
	function makeJoinChart() {
		const genChart = new Chart(jointx, {
			type: 'line',
			data: joinDatasets,
			options: { 
				responsive: false,
			}
		});
	}
    //성별 분포
    const gentx = document.getElementById('genderChart').getContext('2d');
    const gendata = {
        labels: ['남','여'],
        datasets: [
            {
                label: '성별 분포',
                data: [],
                backgroundColor: ['#0067a3','#ff0000']
            }
        ]
    };
    axios.post("genderCount")
        .then((res) => {
            gendata.datasets[0].data.push(res.data[1].count);
            gendata.datasets[0].data.push(res.data[0].count);
            MakeGenderChart();
        })
        .catch((res) => {
            console.log(res);
    	})
    function MakeGenderChart() {
        const genChart = new Chart(gentx, {
            type: 'pie',
            data: gendata,
            options: {
                responsive: false,
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    title: {
                        display: true,
                        text: 'Mohel 회원 성별'
                    }
                }
            },
        });
    }
	//연령 분포
    const agetx = document.getElementById('ageChart').getContext('2d');
    const agedata = {
        labels: ['20세 미만','20대','30대','40대','50대','60세 이상'],
        datasets: [
            {
                label: '연령 분포',
                data: [],
                backgroundColor: ['#ff8c00','#ffff00','#008000','#0000ff','#4b0082',"#800080"]
            }
        ]
    };
    axios.post("ageCount")
        .then((res) => {
            agedata.datasets[0].data.push(res.data.under_20);
            agedata.datasets[0].data.push(res.data.age_20);
            agedata.datasets[0].data.push(res.data.age_30);
            agedata.datasets[0].data.push(res.data.age_40);
            agedata.datasets[0].data.push(res.data.age_50);
            agedata.datasets[0].data.push(res.data.over_60);
            MakeAgeChart();
        })
        .catch((res) => {
            console.log(res);
    	})
    function MakeAgeChart() {
        const ageChart = new Chart(agetx, {
            type: 'doughnut',
            data: agedata,
            options: {
                responsive: false,
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    title: {
                        display: true,
                        text: '이용자 연령대 분포'
                    }
                }
            },
        });
    }
    axios.get("availableRiding")
        .then((res) => {
            document.getElementById("ridingCount").innerText = `참여 가능한 라이딩 : ${res.data}`
        })
        .catch((error) => {
            console.log(error);
        });
    axios.get("todayRiding")
        .then((res) => {
            document.getElementById("todayRiding").innerText = `오늘 개설된 라이딩 : ${res.data}`
        })
        .catch((error) => {
            console.log(error);
        })
    axios.get("availableCourse")
        .then((res) => {
            document.getElementById("courseCount").innerText = `경험 가능한 추천 코스 : ${res.data}`
        })
        .catch((error) => {
            console.log(error);
        })
});
