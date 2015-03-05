/*
 * 标准平台，项目 计划模板列表
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var planTemplateList={};

//新增
planTemplateList.add=function(name,grid){
	//跳转到修改页面
	$('#conBody').loadPage($('#initPath').val()+"/PlanTemplateController.do?method=toSaveOrUpdate");
}   

//修改
planTemplateList.update=function(name,grid){
	if(!planTemplateList.validation(name,grid))return;
	//跳转到修改页面
	$('#conBody').loadPage($('#initPath').val()+'/PlanTemplateController.do?method=toSaveOrUpdate&objId='+$(grid).getSelect());
}   

//删除
planTemplateList.remove=function(name,grid){
	if(!planTemplateList.validation(name,grid))return;
	if(window.confirm('确定'+name+'?')){
		$.getJSON($('#initPath').val()+'/PlanTemplateController.do?method=remove',{objId:$(grid).getSelect()},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$(grid).reload();//刷新
		});
	}
}

//计划明细
planTemplateList.tasks=function(name,grid){
	if(!planTemplateList.validation(name,grid))return;
	//跳转到计划详情列表页面
	$('#conBody').loadPage($('#initPath').val()+'/view/srplatform/workPlan/planTemplateTaskList.jsp?templateId='+$(grid).getSelect());
}

//列表操作验证
planTemplateList.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择计划模版'+name);return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请选择一个计划模版'+name);return false;}//是否只选中一个
	return true;
}

$(document).ready(function(){
	//设定返回路径
	$("#returnUrl").val($('#initPath').val() + "/view/srplatform/workPlan/planTemplateList.jsp");
	
	//查询模板名称
	$('#templateName').autocomplete($('#initPath').val() + '/PlanTemplateController.do?method=getObjectQuery&queryColumns=name', {
			extraParams:{},//查询条件  例如    {objId:111}
			matchColumn:'name',//作为查询显示, 被选中之后匹配的列
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
	});   
	
	//查询组织机构
	$('#orgName').autocomplete($('#initPath').val() + '/CompanyController.do?method=getObjectQuery&queryColumns=name,shortSpellName,shortName', {
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
	});  
	
});

