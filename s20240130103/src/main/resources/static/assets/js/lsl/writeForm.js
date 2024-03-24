function goBack() {
    window.history.back();
}

$(".btn.bwComple").on("click", function() {
  
    const cboard_title = $(".form-control[name='cboard_title']").val();
    
    //  빈 문자열 확인
    if (cboard_title.trim() === "") {
        alert("제목을 입력하세요.");
        
        // 제목 포커스 이동
        $(".form-control[name='cboard_title']").focus();
        
        return false;
    }
    
  
});

$(".btn.bfmModify").on("click", function() {
  
    const cboard_title = $(".form-control[name='cboard_title']").val();
    
   
    if (cboard_title.trim() === "") {
        alert("제목을 입력하세요.");
        
        $(".form-control[name='cboard_title']").focus();
        
        return false;
    }
    

});
