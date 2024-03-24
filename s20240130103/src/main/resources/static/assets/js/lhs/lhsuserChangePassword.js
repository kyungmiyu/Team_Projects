function chk(){
	if(document.getElementById("renewPassword").value===document.getElementById("newPassword").value){
		return true;
	}else{
		alert("비밀번호가 다릅니다");
		document.getElementById("renewPassword").value="";
		document.getElementById("newPassword").value="";
		return false;
	}
}
