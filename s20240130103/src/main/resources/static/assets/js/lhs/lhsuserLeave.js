function chk(){
	if(document.getElementById("passwd1").value===document.getElementById("passwd2").value){
		return true;
	}else{
		alert("비밀번호가 다릅니다");
		document.getElementById("passwd1").value="";
		document.getElementById("passwd2").value="";
		return false;
	}
}
