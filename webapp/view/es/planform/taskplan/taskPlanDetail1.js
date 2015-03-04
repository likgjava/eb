
var taskPlanDetail={};
$(document).ready(function(){
	$("#return_to_list").click(function(){
		$("#taskPlanFormTitleInfo").show();
		$("#taskPlanItemFormTitleInfo").show();
		$("#taskPlanDetailTitle").show();
		$("#taskPlanDetailOption").show();
		taskPlanForm.taskPlanSubList($("#taskPlanForm").find('input[id=objId]').val());
		taskPlanForm.taskPlanDetailList($("#taskPlanForm").find('input[id=objId]').val());
		$("#taskPlanFormBaseInfoDetail").empty();
	})
});