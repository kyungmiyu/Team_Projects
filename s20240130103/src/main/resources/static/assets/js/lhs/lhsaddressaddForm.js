document.getElementById("searchbtn").addEventListener("click",function(){
	$(".address-main").empty();
	let inputresult = document.getElementById("inputid").value;
	$.ajax({
		type : 'post',
		url : 'addressIdSearch',
		data: {'user_id': inputresult},
		dataType : 'json',
		success : function(result){
			let profileName;
			let score = Math.floor(result.eval_score);
			if(result.user_profile === null){
					profileName = "987654321487321564defaultImg.jpg";
			}else{
					profileName = result.user_profile
			}
			if(result.address_chk == '1'){
				$(".address-main").append(`
				<div class="address-main-inner">
		            <div class="address-list-card-detail">
		              <div class="profile-img-user-name">
		                <img src="upload/userImg/${profileName}" alt="Profile" class="rounded-circle address-list-profile-img">
		                <div>
		                  <p class="user-name">${result.user_name}</p>
		                  <p class="user-id">#${result.user_id}</p>
		                </div>
		              </div>
		              <div class="score-message">
		              	<div class="user-score rounded-circle justify-content-center">${score}</div> 
		              </div>
		            </div>
		          </div>
				`);
			}else{
				$(".address-main").append(`
				<div class="address-main-inner">
		            <div class="address-list-card-detail">
		              <div class="profile-img-user-name">
		                <img src="upload/userImg/${profileName}" alt="Profile" class="rounded-circle address-list-profile-img">
		                <div>
		                  <p class="user-name">${result.user_name}</p>
		                  <p class="user-id">#${result.user_id}</p>
		                </div>
		              </div>
		              <div class="score-message">
		              	<div class="user-score rounded-circle justify-content-center">${score}</div> 
		              	<div>
			              	<a href="/addressAdd?re_user_no=${result.user_no}" style="color:white;">
				                <div class="user-score rounded-circle justify-content-center" >
				                  <i class="bi bi-plus"></i>
				                </div> 
				            </a>
			            </div>
		              </div>
		            </div>
		          </div>
				`);
			}
		},
		error : function(){
			alert("잘못된 아이디입니다.");
		}
	});

})