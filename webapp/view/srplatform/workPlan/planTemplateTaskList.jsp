<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script>
/*
 * 标准平台，项目 计划模板明细列表
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var planTemplateTaskList={};

//新增
planTemplateTaskList.add=function(name,grid){
	if(!$(grid).isSelectOne()){alert('请选择一个计划模版明细'+name);return false;}//是否只选中一个
	var url = "/PlanTemplateTaskController.do?method=toCreate&templateId="+$("#templateId").val();

	//跳转到新增页面
	if($(grid).isSelectOne())
		url += "&parentId="+$(grid).getSelect();
	
	$.epsDialog({
		id:'planTemplateTask',
        title:'计划模板明细',
        url:$('#initPath').val()+url,
        onClose: function(){ 
        	$(grid).reload();//刷新
        }
	});	
}  

//修改
planTemplateTaskList.update=function(name,grid){
	if(!planTemplateTaskList.validation(name,grid))return;
	//跳转到修改页面
	$.epsDialog({
		id:'planTemplateTask',
        title:'计划模板明细',
        url:$('#initPath').val()+'/PlanTemplateTaskController.do?method=toUpdate&objId='+$(grid).getSelect(),
        isReload:false,
        onClose: function(){ 
        	$(grid).reload();//刷新
        }
	});	
}  

//删除
planTemplateTaskList.remove=function(name,grid){
	if(!planTemplateTaskList.validation(name,grid))return;
	if(window.confirm('确定'+name+'?')){
		$.getJSON($('#initPath').val()+'/PlanTemplateTaskController.do?method=remove',{objId:$(grid).getSelect()},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$(grid).reload();//刷新
		});
	}
}

//列表操作验证
planTemplateTaskList.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择计划模版明细'+name);return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请选择一个计划模版明细'+name);return false;}//是否只选中一个
	return true;
}

//查询条件过滤
planTemplateTaskList.before=function(){
	var option={"planTemplate.objId":$("#templateId").val()}
	$('#planTemplateTaskGrid').flexOptions({params:option});
	return true;
}
$(document).ready(function(){
	//加载模板信息
	$("#planTemplate").loadPage($('#initPath').val()+'/PlanTemplateController.do?method=toShowDetail&objId='+$("#templateId").val())
});
</script>

<input type="hidden" id="templateId" value="<c:out value="${param.templateId}"/>"/>

<div id="planTemplate"></div>

<flex:flexgrid checkbox="true"
	id="planTemplateTaskGrid" url="PlanTemplateTaskController.do?method=list" queryColumns="isLeaf,path,level,parent.objId"  
		usepager="false" title="计划模版明细" height="400" tree="true"
		onSubmit="planTemplateTaskList.before">
				<flex:flexCol name="name" display="planTemplateTaskForm.name" sortable="true" width="200"align="left"></flex:flexCol>
				<flex:flexCol name="code" display="planTemplateTaskForm.code" sortable="true" width="80"align="left"></flex:flexCol>
				<flex:flexCol name="resource.name" display="planTemplateTaskForm.resource" sortable="true" width="150"align="left"></flex:flexCol>
				<flex:flexCol name="prePlan.name" display="planTemplateTaskForm.prePlan" sortable="true" width="200"align="left"></flex:flexCol>
				<flex:flexCol name="role.chName" display="planTemplateTaskForm.role" sortable="true" width="120"align="left"></flex:flexCol>
				<flex:flexCol name="isAutoEnd" alias="isAutoEndCN" display="planTemplateTaskForm.isAutoEnd" sortable="true" width="50"align="center"></flex:flexCol>
				<flex:flexCol name="duration" display="planTemplateTaskForm.duration" sortable="true" width="30"align="left"></flex:flexCol>
				<flex:flexCol name="bizRuleName" display="业务规则" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="memo" display="planTemplateTaskForm.memo" sortable="true" width="100"align="left"></flex:flexCol>
	<flex:flexBtn name="globe.new" bclass="add" onpress="planTemplateTaskList.add"></flex:flexBtn>	
	<flex:flexBtn name="globe.modify" bclass="modify" onpress="planTemplateTaskList.update"></flex:flexBtn>	
	<flex:flexBtn name="globe.delete" bclass="delete" onpress="planTemplateTaskList.remove"></flex:flexBtn>	
</flex:flexgrid>