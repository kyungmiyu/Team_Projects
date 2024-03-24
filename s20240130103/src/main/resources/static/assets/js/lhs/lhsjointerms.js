let checkbox = document.getElementById("termsChkBx")
document.getElementById("okbtn").addEventListener('click',function(){
	if(checkbox.checked){
		location.href="/joinEmailForm";
	}else{
		alert("약관에 동의해주세요");
	}
})

document.getElementById("canclebtn").addEventListener("click",function(){
	location.href="/";
})