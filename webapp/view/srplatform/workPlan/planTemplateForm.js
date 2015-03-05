/*
 * 标准平台，项目 计划模板表单
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var planTemplateForm={};

$(document).ready(function(){
	$('#planTemplateForm').validate();
	
	if($("#objId").val() == "")
		$("#planTemplateTask").hide();
	
	if ($('#tempTypeId').val()=='00') {//为默认时不添加代理机构
		$("input[name=org.objId]").val("");
		$("input[name=org.name]").val("");
		$('#planTemplateOrg').addClass("planTempDis");
		$("input[name=org.name]").removeClass("required");
	}
	planTemplateForm.changeTempType = function(tempType){//选择模板类型
		if (tempType=='00') {//为默认时不添加代理机构
			$("input[name=org.objId]").val("");
			$("input[name=org.name]").val("");
			$('#planTemplateOrg').addClass("planTempDis");
			$("input[name=org.name]").removeClass("required");
		}else{//为指定代理机构时添加代理机构
			$('#planTemplateOrg').removeClass("planTempDis");
			$("input[name=org.name]").addClass("required");
		}
	}
	//查询组织机构
	$('input[name=org.name]').autocomplete($('#initPath').val() + '/CompanyController.do?method=getObjectQuery&queryColumns=objId,name,shortSpellName,shortName', {
		extraParams:{},//查询条件  例如    {objId:111}
		matchColumn:'name,shortSpellName,shortName',//作为查询显示, 被选中之后匹配的列
		mustMatch: false,
		formatItem: function(data, i, total) {
			return data["name".split(",")[0]];
		},
		formatMatch: function(data, i, total) {
			return data["name".split(",")[0]];
		},
		formatResult: function(data) {
			return data["name".split(",")[0]];
		}
	}).result(function(event,data,formatted){
		$("input[name=org.objId]").val(data.objId);
		$("input[name=org.name]").val(data.name);
	});  
	
	//提交
	$('#planTemplateSave').click(function(){
		if(!$('#planTemplateForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/PlanTemplateController.do?method=savePlanTemplate', formToJsonObject('planTemplateForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$("#objId").val(json.planTemplate.objId);
			$("#planTemplateTask").click();
		});
	});

	//计划详情
	$('#planTemplateTask').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/view/srplatform/workPlan/planTemplateTaskList.jsp?templateId='+$("#objId").val());
	})
});
