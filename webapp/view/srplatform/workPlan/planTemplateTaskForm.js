/*
 * 标准平台，项目 计划模板详情表单
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var planTemplateTaskForm={};

$(document).ready(function(){
	$('#planTemplateTaskForm').validate();
	
	//选择资源
 	$('input[id=resource.name]').click(function(e){
	    $.epsDialog({
	        title:'请选择组件资源',
	        id:'chooseResource',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=resource&className=Resource&action=listTop&isOpen=1&dialogId=chooseResource',
	        width: '500',
	        height: '400'
	    }); 
 	});	
 	//查看组件资源
 	$('input[id=resourceView.name]').click(function(e){
 		$.epsDialog({
 			title:'请选择查看组件资源',
 			id:'chooseResourceView',
 			url:$('#initPath').val()+'/TreeController.do?method=toTree&property=resourceView&className=Resource&action=listTop&isOpen=1&dialogId=chooseResourceView',
 			width: '500',
 			height: '400'
 		}); 
 	});	
 	
 	//前置节点
	$('input[id=prePlan.name]').click(function(e){
	    $.epsDialog({
	        title:'请选择前置节点',
	        id:'choosePreTask',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=prePlan&className=PlanTemplateTask&action=listTop&isOpen=1&dialogId=choosePreTask&params='+escape("&planTemplate.objId="+$("input[name=planTemplate.objId]").val()),
	        width: '500',
	        height: '400'
	    }); 
	});	
 	//退回节点
	$('input[id=backPlan.name]').click(function(e){
	    $.epsDialog({
	        title:'请选择退回节点',
	        id:'chooseBackTask',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=backPlan&className=PlanTemplateTask&action=listTop&isOpen=1&dialogId=chooseBackTask&params='+escape("&planTemplate.objId="+$("input[name=planTemplate.objId]").val()),
	        width: '500',
	        height: '400'
	    }); 
	});	
	
	//选择角色
 	$('input[id=role.chName]').click(function(e){
	    $.epsDialog({
	        title:'请选择角色',
	        id:'chooseRole',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=role.objId&NAMES=role.chName&className=Role&action=listTop&isOpen=1&dialogId=chooseRole',
	        width: '500',
	        height: '400'
	    }); 
 	});	
 	
 	//选择子流程模板
 	$('a[id=selectSubProcesseTemplate]').click(function(e){
	    $.epsDialog({
	        title:'请选择流程',
	        id:'chooseProcessTemplate',
	        url:$('#initPath').val()+'/view/workFlow/viewProcessDefineTemplateList.jsp',
	        width: '650',
	        height: '500'
	    }); 
 	});	
 	$('#deleteSubProcesseTemplate').click(function(){
 		$('#subProcesseTemplateId').val('');
 		$('#subProcesseTemplate').val('');
 	});
 	
	//提交
	$('#planTemplateTaskSave').click(function(){
		//alert(obj2str(formToJsonObject('planTemplateTaskForm')))
		if(!$('#planTemplateTaskForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/PlanTemplateTaskController.do?method=saveOrUpdate', formToJsonObject('planTemplateTaskForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$("#epsDialogCloseReload").click();
		});
	});
	
	//关闭
	$('#planTemplateTaskClose').click(function(){
		$('#epsDialogCloseNoReload').click();
	});

	$("#bizRuleNameId").click(function(){
	    $.epsDialog({
	        title:'请选择项目规则',
	        id:'project_rule',
	        url:$('#initPath').val()+'/ProjectPlanController.do?method=toSelectSysconfigitemListView',
	        width: '800',
	        height: '500'
	    }); 
	})
	/*资源详情*/
	$("#pttfResourceDetail").click(function(){
		var content = "资源ID："+ $("[id=resource.objId]").val() + "<br>" + "资源位置："+$("[id=resource.path]").val();
		    content += "<br>" + "URL：" + $("[id=resource.url]").val(); 
	    $.epsDialog({
	        title:'资源详情',
	        width: '600',
	        height: '200',
	        content: content
	    }); 
	});
	/*资源详情*/
	$("#pttfResourceViewDetail").click(function(){
		var content = "资源ID："+ $("[id=resourceView.objId]").val() + "<br>" + "资源位置："+$("[id=resourceView.path]").val();
		content += "<br>" + "URL：" + $("[id=resourceView.url]").val(); 
		$.epsDialog({
			title:'资源详情',
			width: '600',
			height: '200',
			content: content
		}); 
	});
});
