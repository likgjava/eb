<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>  
<script>
var projectPlanList={};
	
//修改
projectPlanList.update=function(name,grid){
	if(!projectPlanList.validation(name,grid))return;
	//跳转到修改页面
	$.epsDialog({
		id:'projectPlan',
        title:'项目计划',
        url:$('#initPath').val()+'/ProjectPlanController.do?method=toUpdate&objId='+$(grid).getSelect(),
        onClose: function(){ 
        	$(grid).reload();//刷新
			try {
				$("#menuTree").empty();
				projectagent.loadMenuTree();
			} catch (e) {
				return false;
			}
        }
	});
}   
//删除
projectPlanList.remove=function(name,grid){
	if(!projectPlanList.validation(name,grid))return;
	if(window.confirm('确定'+name+'?')){
		$.getJSON($('#initPath').val()+'/ProjectPlanController.do?method=remove',{objId:$(grid).getSelect()},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$(grid).reload();//刷新
			try {
				$("#menuTree").empty();
				projectagent.loadMenuTree();
			} catch (e) {
				return false;
			}
		});
	}
}

//进入项目
projectPlanList.intoProject=function(){
    var projectId = $("#projectId").val()
	$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfo&objId='+projectId);
}




//列表操作验证
projectPlanList.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择项目计划'+name);return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请选择一个项目计划'+name);return false;}//是否只选中一个
	return true;
}
//查询条件过滤
projectPlanList.before=function(){
	var option={"project.objId":$("#projectId").val()}
	$('#projectPlanGrid').flexOptions({params:option});
	return true;
}
$(document).ready(function(){
});
</script>

<input type="hidden" id="projectId" value="${projectId}" />
<flex:flexgrid checkbox="true"
	id="projectPlanGrid" url="ProjectPlanController.do?method=list" queryColumns="isLeaf,path,level,parent.objId"  
		usepager="false" title="项目计划"  height="400" 
		onSubmit="projectPlanList.before" tree="true">
				<flex:flexCol name="name" display="projectPlanForm.name" sortable="true" width="200"align="left"></flex:flexCol>
				<flex:flexCol name="planStartDate" display="projectPlanForm.planStartDate" sortable="true" width="80"align="center"></flex:flexCol>
				<flex:flexCol name="planEndDate" display="projectPlanForm.planEndDate" sortable="true" width="80"align="center"></flex:flexCol>
				<flex:flexCol name="resource.name" display="projectPlanForm.resource" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="prePlan.name" display="projectPlanForm.prePlan" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="role.chName" display="projectPlanForm.role" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="employee.name" display="projectPlanForm.employee" sortable="true" width="80"align="left"></flex:flexCol>
				<flex:flexCol name="memo" display="projectPlanForm.memo" sortable="true" width="150"align="left"></flex:flexCol>
	<flex:flexBtn name="globe.modify" bclass="modify" onpress="projectPlanList.update"></flex:flexBtn>	
	<flex:flexBtn name="globe.delete" bclass="delete" onpress="projectPlanList.remove"></flex:flexBtn>
	<flex:flexBtn name="进入项目" bclass="add" onpress="projectPlanList.intoProject"></flex:flexBtn>		
</flex:flexgrid>
