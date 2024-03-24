$(".btn-secondary").on("click", function () {
	const project_no = $('.project_no').val();
    window.location.href = 'detailProject?project_no=' + project_no;
});