//定义文件全局变量 处理方法名重复问题
var TaskPlanList={};
//查询条件过滤
TaskPlanList.before=function(){
	var option={
	   "orgId":PlatForm.user.emp.company.objId	
	};
	$('#TaskPlanGrid').flexOptions({params:option});
	return true;
}

//加载数据成功之后调用的函数
TaskPlanList.success=function(){

		$("#TaskPlanGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">审核申报书</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				var json = $("#TaskPlanGrid").flexGetRowJsonById(rowId); 
				$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&type=sub&objId='+rowId);
			}).appendTo(obj);
		}
			});	

}
//待审核资金的申报书
TaskPlanList.auditFund=function(rowId){
	$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/taskplan/taskPlanFormForAuditDe.jsp?objId='+rowId);
}
//待修改的申报书
TaskPlanList.modify=function(rowId){
	$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toSaveOrUpdate&objId='+rowId+'&paramType=desktopList');
}
//待审核的申报书
TaskPlanList.audit=function(rowId){
	$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toAuditTaskPlanPage&objId='+rowId);
}
$(document).ready(function(){
})
