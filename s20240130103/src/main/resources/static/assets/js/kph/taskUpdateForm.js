$(".btn-secondary").on("click", function () {
    const project_no = $('#project_no').val();
    window.location.href = 'detailProject?project_no=' + project_no;
});

$(".btn-primary").on("click", function (event) {
	const task_title = $(".form-control[name='task_title']").val();
	const task_member = $(".form-check-input[name='user_no']").is(":checked");
	const task_start = $(".form-control[name='task_start']").val();
	const task_end_day = $(".form-control[name='task_end_day']").val();
	const task_end_time = $(".form-control[name='task_end_time']").val();
	
	//날짜비교 객체 생성
	const task_start_date = new Date(task_start);
	const task_end_date = new Date(task_end_day);
	
	if (task_title == '' || task_title.trim() == '') {
		$(".task-title-alert").css("display", "block");
		event.preventDefault();
	} 

	if (!task_member) {
		$(".task-member-alert").css("display", "block");
		event.preventDefault();
	}

	if(task_start == '') {
		$(".task-start-alert").css("display", "block");
		event.preventDefault();
	} 
	
	if(task_end_day == '' || task_end_time == '') {
		$(".task-end-alert").css("display", "block");
		event.preventDefault();
	}
	
	if(task_start_date>task_end_date){
		$(".task-chk-alert").css("display","block");
		event.preventDefault();
	}
	
});

$(".form-control, .form-check-input").on("input", function (event) {
	const task_title = $(".form-control[name='task_title']").val();
	const task_member = $(".form-check-input[name='user_no']").is(":checked");
	const task_start = $(".form-control[name='task_start']").val();
	const task_end_day = $(".form-control[name='task_end_day']").val();
	const task_end_time = $(".form-control[name='task_end_time']").val();

	if (task_title != '') {
		$(".task-title-alert").css("display", "none");
	} 
	
	if(task_member) {
		$(".task-member-alert").css("display", "none");
	} 
	
	if(task_start != '') {
		$(".task-start-alert").css("display", "none");
	} 
	
	if(task_end_day != '' && task_end_time != '') {
		$(".task-end-alert").css("display", "none");
	}
});

$('#all').on('click', function () {  
	let checked = $('#all').is(':checked');

	if(checked) {
		$('input:checkbox').prop('checked', true);
	} else {
		$('input:checkbox').prop('checked', false);
	}
});