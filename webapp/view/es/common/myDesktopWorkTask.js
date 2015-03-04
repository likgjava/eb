$(document).ready(function(){
	toMoreWaitTask = function(taskType){
		var url = $('#initPath').val()+'/WaitprocWorkTaskController.do?method=toWaitProcWorkTaskListPage&worktaskType='+taskType;
		$('#conBody').loadPage(url);
	}
})