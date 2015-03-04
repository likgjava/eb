/*
 * 执行平台，采购申报书 表单
 * author: yangx
 * mail: yangx@gpcsoft.com
 */
var taskPlanFormForSum={};

//加载子项
taskPlanFormForSum.showSubsAndDetails = function(){
	$("#taskPlanSubListView").empty().loadPage($('#initPath').val()+'/view/es/planform/taskplan/taskPlanSubList.jsp');
	$("#taskPlanDetailListView").empty().loadPage($('#initPath').val()+'/view/es/planform/taskplan/taskPlanDetailList.jsp');
}

$(document).ready(function(){
	$.validator.addMethod(
			"unique",
			function(value,element,param){
				return uniqueHandler("TaskPlan",param,value,$("#objId").val());
			},
			'申报书编号已存在'
		);
	$("#taskPlanForm").validate({
		rules:{
			taskCode:{unique:"taskCode"}
		}
	});	
	
	//加载tabs
	$('#epsTabs').tabs();
	
	//时间控件
    $("#applyDate").val(new Date().Format('yyyy-MM-dd')).epsDatepicker();
    $("#finishDate").epsDatepicker();
     			
    //判断id是否为空
    if($("#objId").val()=='')
    {
    	$("#epsTabs").hide();  //隐藏下面的子项
    	$('#taskPlanSubmit').hide();  //隐藏提交审核按钮
    	
    	//取得当前人的办公电话
        $("#budgetLinkerTel").val(PlatForm.user.emp.telOffice);
    } 			
    else
    {
    	//编号置为不可用
    	$("#taskCode").attr("disabled","disabled");

    	$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=createOrUpdate',{objId:$("#objId").val()},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('taskPlanForm', json.taskPlan);
    	});
    	
    	//加载子项
    	taskPlanFormForSum.showSubsAndDetails();
    } 			

	//返回
	$('#taskPlanReturn').click(function(){
		//到列表页面
		$('#conBody').empty().loadPage($('#initPath').val()+'/view/es/planform/taskplan/blockTradeListForSum.jsp');
	});
	
	//下级条目
	$("#tabs_subs_s").click(function(){
		if($("#taskPlanSubListForSumView").text().length == 0){
			$("#taskPlanSubListForSumView").loadPage($('#initPath').val()+'/view/es/planform/taskplan/taskPlanSubListForSum.jsp');
		}
	})
	
	//下级资金明细
	$("#tabs_details_s").click(function(){
		if($("#taskPlanDetailListForSumView").text().length == 0){
			$("#taskPlanDetailListForSumView").loadPage($('#initPath').val()+'/view/es/planform/taskplan/taskPlanDetailListForSum.jsp');
		}
	})
	
	//选择主管单位
	$("#departmentName").autocomplete(
		$('#initPath').val() + '/CompanyController.do?method=getObjectQuery&queryColumns=objId,name', 
		{
			matchColumn:'name,shortSpellName',//作为查询显示, 被选中之后匹配的列
			extraParams:{},
			mustMatch: true,
			formatItem: function(data, i, total) {
				return data.name;
			},
			formatMatch: function(data, i, total) {
				return data.name;
			},
			formatResult: function(data) {
				return data.name;
			}
		}
	).result(function(event,data,formatted){
		if(data){
			$("#departmentName").val(data.name);//回填id
			$("input[id=department.objId]").val(data.objId);//回填id
		}
	});
	
	//选择业务处室
	$("#governmentName").autocomplete(
		$('#initPath').val() + '/DepartmentController.do?method=getObjectQuery&queryColumns=objId,name', 
		{
			matchColumn:'name,shortSpellName',//作为查询显示, 被选中之后匹配的列
			extraParams:{},
			mustMatch: true,
			formatItem: function(data, i, total) {
				return data.name;
			},
			formatMatch: function(data, i, total) {
				return data.name;
			},
			formatResult: function(data) {
				return data.name;
			}
		}
	).result(function(event,data,formatted){
		if(data){
			$("#governmentName").val(data.name);//回填id
			$("input[id=government.objId]").val(data.objId);//回填id
		}
	});
	
	//选择招标中心
	$("#taskAgentName").autocomplete(
		$('#initPath').val() + '/OrgInfoController.do?method=getObjectQuery&queryColumns=objId,orgName', 
		{
			matchColumn:'orgName',//作为查询显示, 被选中之后匹配的列
			extraParams:{"agencyId":null,"agencyId_op":"!="},
			mustMatch: true,
			formatItem: function(data, i, total) {
				return data.orgName;
			},
			formatMatch: function(data, i, total) {
				return data.orgName;
			},
			formatResult: function(data) {
				return data.orgName;
			}
		}
	).result(function(event,data,formatted){
		if(data){
			$("#taskAgentName").val(data.orgName);//回填id
			$("input[id=taskAgent.objId]").val(data.objId);//回填id
		}
	});
	
	//保存
	$('#taskPlanSave').click(function(){
		if(!$('#taskPlanFormForSum').valid()){alert('请正确填写表单!');return;}
		$("#auditDetail").val("00");
		$("#confirmStatus").val("00");
		$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=saveOrUpdateTaskPlan', formToJsonObject('taskPlanFormForSum'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			
			alert("保存成功！");
			if($("#objId").val() == ''){
				$("#objId").val(json.taskPlanId);
				
				//显示子项和提交按钮
				$("#epsTabs").show();
				$("#taskPlanSubmit").show();
				taskPlanFormForSum.showSubsAndDetails();
			}
		});
	});
	
	//提交审核
	$('#taskPlanSubmit').click(function(){
		$("#auditDetail").val("01");
		$("#confirmStatus").val("00");
		$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=saveOrUpdateTaskPlan', 
				formToJsonObject('taskPlanFormForSum'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			
			alert("已提交成功，请等待审核！")
			$('#taskPlanReturn').click();
		});
	});
});
