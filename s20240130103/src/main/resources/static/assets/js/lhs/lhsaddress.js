window.onload = function(){
	addressList();
	let requestbtn = document.getElementById("addressrequest");
	let responsebtn = document.getElementById("addressresponse");
	let addressListbtn = document.getElementById("addressList");
	requestbtn.addEventListener("click",requestList);
	responsebtn.addEventListener("click",responseList);
	addressListbtn.addEventListener("click",addressList);
}

function addressList(){
	$('.address-main').empty();
	$.ajax({
		type : 'get',
		url : '/addressList',
		success:function(list){
			for(const content of list){
				let profileName;
				let score = Math.floor(content.user_score);
				if(content.user_profile === null){
					profileName = "987654321487321564defaultImg.jpg";
				}else{
					profileName = content.user_profile
				}
				$('.address-main').append(`
					<div class="address-main-inner">
			            <div class="address-list-card-detail">
			              <div class="profile-img-user-name">
			                <img src="upload/userImg/${profileName }" alt="Profile" class="rounded-circle address-list-profile-img">
			                <div>
			                  <p class="user-name">${content.user_name }</p>
			                  <p class="user-id">#${content.user_id }</p>
			                </div>
			              </div>
			              <div class="score-message">
			              	<div class="user-score rounded-circle justify-content-center" style="margin-left: 20px;">${score }</div>
			                <form action="msgWrite" method="get"> 
			                  <input type="hidden" name="user_no" value="${content.user_no }">
			                  <button type="submit" class="rounded-circle message">
			                    <i class="bi bi-envelope-fill"></i>
			                  </button>
			                </form>
			                <div> 
				                <a href="/addressRequestDelete?re_user_no=${content.user_no}" style="color:white;">
				                  <button type="button" class="rounded-circle message">
				                    <i class="bi bi-trash-fill"></i>
				                  </button>
				                </a>
			                </div>
			              </div>
			            </div>
			          </div>
				`)
			}
		},
		error:function(){
			alert("에러발생");
		}
	});
}

function requestList(){
	$('.address-main').empty();
	$.ajax({
		type : 'get',
		url : '/addressRequestList',
		success:function(list){
			console.log(list);
			for(const content of list){
				let profileName;
				let score = Math.floor(content.user_score);
				if(content.user_profile === null){
					profileName = "987654321487321564defaultImg.jpg";
				}else{
					profileName = content.user_profile
				}
				$('.address-main').append(`
					<div class="address-main-inner">
			            <div class="address-list-card-detail">
			              <div class="profile-img-user-name">
			                <img src="upload/userImg/${profileName }" alt="Profile" class="rounded-circle address-list-profile-img">
			                <div>
			                  <p class="user-name">${content.user_name }</p>
			                  <p class="user-id">#${content.user_id }</p>
			                </div>
			              </div>
			              <div class="score-message">
			                <div class="user-score rounded-circle justify-content-center">${score }</div>
			                <div> 
				                <a href="/addressRequestDelete?re_user_no=${content.user_no}" style="color:white;">
				                  <button type="button" class="rounded-circle message">
				                    <i class="bi bi-trash-fill"></i>
				                  </button>
				                </a>
			                </div>
			              </div>
			            </div>
			          </div>
				`)
			}
		},
		error:function(){
			alert("에러발생");
		}
	});
}
function responseList(){
	$('.address-main').empty();
	$.ajax({
		type : 'get',
		url : '/addressResponseList',
		success:function(list){
			console.log(list);
			for(const content of list){
				let profileName;
				let score = Math.floor(content.user_score);
				if(content.user_profile === null){
					profileName = "987654321487321564defaultImg.jpg";
				}else{
					profileName = content.user_profile
				}
				$('.address-main').append(`
					<div class="address-main-inner">
			            <div class="address-list-card-detail">
			              <div class="profile-img-user-name">
			                <img src="upload/userImg/${profileName }" alt="Profile" class="rounded-circle address-list-profile-img">
			                <div>
			                  <p class="user-name">${content.user_name }</p>
			                  <p class="user-id">#${content.user_id }</p>
			                </div>
			                
			              </div>
			              <div class="score-message">
			              	<div> 
			              		<div class="user-score rounded-circle justify-content-center" style="margin-left: 20px;">${score }</div>
			                </div>
			                <div> 
				                <a href="/addressResponsePermit?user_no=${content.user_no}" style="color:white;">
				                  <button type="button" class="rounded-circle message" style="background-color: rgb(23, 255, 162); color: white;">
				                    <i class="bi bi-check-lg"></i>
				                  </button>
				                </a>
			                </div>
			                <div> 
				                <a href="/addressResponseDeny?user_no=${content.user_no}" style="color:white;">
				                  <button type="button" class="rounded-circle message" style="background-color: rgb(255, 6, 126); color: white;">
                                	<i class="ri-close-fill"></i>
				                  </button>
				                </a>
			                </div>
			              </div>
			            </div>
			          </div>
				`)
			}
		},
		error:function(){
			alert("에러발생");
		}
	});
}