/**
 * 
 */
const dataTransfer = new DataTransfer();
const dataList = dataTransfer.items;
const inputfile = document.getElementById("formFile");
const fileList = document.getElementById("fileList");

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
				inputfile.files = dataTransfer.files;
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

inputfile.addEventListener("change",function(){
	let files = this.files;
	for (var i = 0; i < files.length; i++) {
            let file = files[i];
            dataList.add(file);
            let fileDiv = fileDivDom(file.name,index++);
            fileList.appendChild(fileDiv);
        }
        inputfile.files = dataTransfer.files;
});


// -----------------드래그 앤 드롭 테스트중-------------------------

fileList.addEventListener('dragover',function(e){
	e.preventDefault();
	e.stopPropagation();
});
fileList.addEventListener('drop',function(e){
	e.preventDefault();
	e.stopPropagation();
	for(let i = 0; i<e.dataTransfer.files.length;i++){
		dataList.add(e.dataTransfer.files[i]);
        let fileDiv = fileDivDom(e.dataTransfer.files[i].name,index++);
        fileList.appendChild(fileDiv);
	}
    inputfile.files = dataTransfer.files;
})

if(document.querySelector('.fileXbtn')){
	document.querySelectorAll('.fileXbtn').forEach((item)=>{
		item.addEventListener('click',function(){
			document.getElementById('importFile'+this.dataset.index).remove();
			document.getElementById('deleteFiles').value += this.dataset.index+'-';
		})
	});
}
