document.addEventListener("DOMContentLoaded", () => {
	$.ajax({
		type: 'get',
		dataType:'json',
		url : 'userCntListAjax',
		success:function(result){
			new Chart(document.querySelector("#barChart"), {
                      type: "bar",
                      data: {
                        labels: [
                          result['4']['DAY'],
                          result['3']['DAY'],
                          result['2']['DAY'],
                          result['1']['DAY'],
                          result['0']['DAY'],
                        ],
                        datasets: [
                          {
                            label: "user",
                            data: [
								result['4']['USER_CNT'], 
								result['3']['USER_CNT'], 
								result['2']['USER_CNT'], 
								result['1']['USER_CNT'], 
								result['0']['USER_CNT']
							],
                            backgroundColor: [
                              "rgba(255, 99, 132, 0.2)",
                              "rgba(255, 159, 64, 0.2)",
                              "rgba(255, 205, 86, 0.2)",
                              "rgba(75, 192, 192, 0.2)",
                              "rgba(54, 162, 235, 0.2)"
                            ],
                            borderColor: [
                              "rgb(255, 99, 132)",
                              "rgb(255, 159, 64)",
                              "rgb(255, 205, 86)",
                              "rgb(75, 192, 192)",
                              "rgb(54, 162, 235)"
                            ],
                            borderWidth: 1,
                          },
                        ],
                      },
                      options: {
                        scales: {
                          y: {
                            beginAtZero: true,
                          },
                        },
                      },
                    });
		}
	})
});