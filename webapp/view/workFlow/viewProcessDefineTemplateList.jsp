<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script>
var ProcessDefineList={};
ProcessDefineList.pageContent;

ProcessDefineList.success = function(){
	$("#processDefineGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">选择</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				var json = $("#processDefineGrid").flexGetRowJsonById(rowId);
				$('input[id=subProcesseTemplateId]').val(rowId);
				$('input[id=subProcesseTemplate]').val(json.processDefName);
				$("#chooseProcessTemplate").find('.epsDialogClose').trigger('click');
			}).appendTo(obj);
		}
	});
	$("#processDefineGrid").flexGetColByName({
		 'effective' : function(id,t){
		 	var json = $("#processDefineGrid").flexGetRowJsonById(id); 
		 	var content = '';
		 	if ('' == json.effective || false == json.effective || 'false' == json.effective) {
		 		content = '否';
			} else {
				content = '是';
			}
			$(t).html(content);
		}
	});
}
ProcessDefineList.add = function(){
	$('#conBody').empty().loadPage($('#initPath').val()+'/ProcessDefineTemplateController.do?method=toCreateOrUpdate');
}
$(document).ready(function(){
	$("#returnUrl").val($('#initPath').val()+'/ProcessDefineTemplateController.do');
})
</script>
<div class="partContainers">	  
	<flex:flexgrid checkbox="false" id="processDefineGrid" url="ProcessDefineTemplateController.do?method=list&order=createTime&order_flag=true" 
	onSuccess="ProcessDefineList.success" rp="200" title="流程定义模板" queryColumns="" usepager="false">
		<flex:flexCol name="processDefName" display="processDefineForm.processDefName" sortable="true" width="180" align="left"></flex:flexCol>
		<flex:flexCol name="bizType" display="processDefineForm.bizType" sortable="true" width="100" align="left"></flex:flexCol>
		<flex:flexCol name="status" display="processDefineForm.status" alias="statusCN" sortable="true" width="40" align="center"></flex:flexCol>
		<flex:flexCol name="effective" display="processDefineTemplateForm.effective" sortable="true" width="50" align="center"></flex:flexCol>
		<flex:flexCol name="createTime" display="processDefineForm.createTime" sortable="true" width="120" align="center"></flex:flexCol>
		<flex:flexCol name="objId" display="操作" sortable="true" width="60" align="center"></flex:flexCol>
	</flex:flexgrid>
</div>