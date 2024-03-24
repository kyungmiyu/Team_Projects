const extenReg = /.+\.(jpeg|jpg|png)/;
document.getElementById('file').addEventListener('change',function(){
	if(this.value.match(extenReg)){
		if(this.files[0]){
			let reader = new FileReader();
			reader.onload = function(event){
				document.getElementById('userProfile').src = event.target.result;
			};
			reader.readAsDataURL(this.files[0]);
		}else{
			alert("파일선택해주세요");
			document.getElementById('joinPicture').src = "";
		}
	}else{
		alert('이미지파일을 선택해주세요');
		this.value='';
	}
});