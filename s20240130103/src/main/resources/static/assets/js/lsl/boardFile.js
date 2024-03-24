const fileArray = []; //파일 저장하는 배열
const dataTransfer = new DataTransfer();
const dataList = dataTransfer.items;
var fileListDiv = document.getElementById('fileList');
var formFileInput = document.getElementById('formFile');
let index = 0;
const fileDivDom = function(fileName,index){
	let firstDiv = document.createElement('div');
	let filetitleDiv = document.createElement('div');
	let xBtnDiv = document.createElement('div');
	let xBtn = document.createElement('button');
	firstDiv.className = "d-flex justify-content-between file-list"+index;
	filetitleDiv.textContent = fileName;
	xBtn.className = "file-Xbtn";
	xBtn.textContent = "X";
	xBtn.dataset.index = index;
	xBtn.dataset.fileName = fileName;
	xBtn.type = 'button';
	xBtn.addEventListener('click',function(){
		document.querySelector('.file-list'+this.dataset.index).remove();
		for(let i= 0;i<dataList.length;i++){
			if(dataList[i].getAsFile().name == this.dataset.fileName){
				dataList.remove(i);
				formFileInput.files = dataTransfer.files;
			}
		}
		//console.log(dataList);
		//for(let i=0;i<dataList.length;i++){
		//	console.log(dataList[i].getAsFile());
		//}
	})
	xBtnDiv.appendChild(xBtn);
	firstDiv.appendChild(filetitleDiv);
	firstDiv.appendChild(xBtnDiv);
	return firstDiv;
}

$(document).ready(function() {
    formFileInput.addEventListener('change', function() {
        var files = this.files;
        if (files.length === 0) {
            fileListDiv.textContent = '파일이 없습니다.';
            return;
        }
        for (var i = 0; i < files.length; i++) {
            var file = files[i];
            dataList.add(file);
            let fileDiv = fileDivDom(file.name,index++);
            fileListDiv.appendChild(fileDiv);
        }
        formFileInput.files = dataTransfer.files;
    });
});

// -----------------드래그 앤 드롭 테스트중-------------------------

document.querySelector('.upload-title').addEventListener('dragover',function(e){
	e.preventDefault();
	e.stopPropagation();
});
document.querySelector('.upload-title').addEventListener('drop',function(e){
	e.preventDefault();
	e.stopPropagation();
	for(let i = 0; i<e.dataTransfer.files.length;i++){
		dataList.add(e.dataTransfer.files[i]);
        let fileDiv = fileDivDom(e.dataTransfer.files[i].name,index++);
        fileListDiv.appendChild(fileDiv);
	}
    formFileInput.files = dataTransfer.files;
})

if(document.querySelector('.fileXbtn')){
	document.querySelectorAll('.fileXbtn').forEach((item)=>{
		item.addEventListener('click',function(){
			document.getElementById('importFile'+this.dataset.index).remove();
			document.getElementById('deleteFiles').value += this.dataset.index+'-';
		})
	});
}

