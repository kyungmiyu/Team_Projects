/**
 * 
 */

 function removeChk(project_no,sch_no){
	 if(confirm("정말로 삭제 하시겠습니까?")){
		 location.href="scheduleDelete?project_no="+project_no+"&sch_no="+sch_no;
	 }else{
	 }
 }