let regExp = /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;
let number;
document.getElementById("emailchkbtn").addEventListener('click',function(){
	const emailform = document.getElementById('email');
	if(emailform.value.match(regExp)){
		$.ajax({
			type: 'post',
			url : '/emailChkAjax',
			data: {'email' : emailform.value},
			dataType: 'json',
			success:function(result){
				number = result;
				emailform.readOnly=true;
				document.getElementById('cnumber').disabled = false;
			},
			error : function(){
				console.log('에러방생');
			}
		});
	}else{
		alert("이메일 형식이 아닙니다");
	}
});

function chk(){
	if(number==document.getElementById('cnumber').value){
		return true;
	}else{
		alert("인증번호 확인 바람");
		return false;
	}
}