$(".btn-secondary").on("click", function () {
    window.location.href = 'main';
});

$(".btn-primary").on("click", function (event) {
	const project_title = $(".form-control[name='project_title']").val();
	const project_content = $(".form-control[name='project_content']").val();
	const project_start = $(".form-control[name='project_start']").val();
	const project_end = $(".form-control[name='project_end']").val();
	
	//날짜비교 객체 생성
	const project_start_date = new Date(project_start);
	const project_end_date = new Date(project_end);
	
	if (project_title == '' || project_title.trim() == '') {
		$(".project-title-alert").css("display", "block");
		event.preventDefault();
	} 
	
	if(project_content == '') {
		$(".project-content-alert").css("display", "block");
		event.preventDefault();
	} 

	if(project_start == '') {
		$(".project-start-alert").css("display", "block");
		event.preventDefault();
	} 
	
	if(project_end == '') {
		$(".project-end-alert").css("display", "block");
		event.preventDefault();
	}
	
	if(project_start_date>project_end_date){
		$(".project-chk-alert").css("display","block");
		event.preventDefault();
	}
});

$(".form-control").on("input", function (event) {
	const project_title = $(".form-control[name='project_title']").val();
	const project_content = $(".form-control[name='project_content']").val();
	const project_start = $(".form-control[name='project_start']").val();
	const project_end = $(".form-control[name='project_end']").val();

	if (project_title != '') {
		$(".project-title-alert").css("display", "none");
	} 
	
	if(project_content != '') {
		$(".project-content-alert").css("display", "none");
	} 
	
	if(project_start != '') {
		$(".project-start-alert").css("display", "none");
	} 
	
	if(project_end != '') {
		$(".project-end-alert").css("display", "none");
	}
});