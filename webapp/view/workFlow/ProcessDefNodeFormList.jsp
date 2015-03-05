<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<input type="hidden" id="processDefNodeId" value="${param.processDefNodeId}"/>
<script>
var ProcessDefNodeFormList = {};
ProcessDefNodeFormList.success = function(){
	$("#processDefNodeFormGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				$.epsDialog({
					id:'process_node_form_form',
			        title:'修改流程节点审核表单',
			        url:$("#initPath").val()+'/ProcessDefNodeFormController.do?method=toprocessDefNodeFormFormView&objId='+rowId,
			        width: '800',
			        height: '500',
			        maxWin:false,
			        onClose: function(){
			        	
			        }
				});
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">删除</a></span>' : function(btn,rowId,obj){
			btn.click(function(){
				$.getJSON($('#initPath').val()+'/ProcessDefNodeFormController.do?method=removeProcessDefNodeForm',{'objId':rowId}, function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					$("#processDefNodeFormGrid").reload();
				});
			}).appendTo(obj);
		}
	});
}
ProcessDefNodeFormList.add = function () {
	$.epsDialog({
		id:'process_node_form_form',
        title:'新增流程节点审核表单',
        url:$("#initPath").val()+'/ProcessDefNodeFormController.do?method=toprocessDefNodeFormFormView&nodeId='+$('#processDefNodeId').val(),
        width: '800',
        height: '500',
        maxWin:false,
        onClose: function(){
        	
        }
	});
}
$('[name=return_to_process_def_node_list]').click(function(){
	$('#process_node_form_list .epsDialogCloseReload').click();
})
</script>
<div class="partContainers">
	<flex:flexgrid checkbox="false" id="processDefNodeFormGrid" url="ProcessDefNodeFormController.do?method=list&processDefNode.objId=${param.processDefNodeId}&order=formPropertyNo" 
	onSuccess="ProcessDefNodeFormList.success" rp="200" title="流程节点表单" queryColumns="processDefNode.processDefine.disposeResultEnum" usepager="false">
		<flex:flexCol name="formPropertyNo" display="processDefNodeFormForm.formPropertyNo" sortable="true" width="60" align="center"></flex:flexCol>
		<flex:flexCol name="formPropertyName" display="processDefNodeFormForm.formPropertyName" sortable="true" width="120" align="left"></flex:flexCol>
		<flex:flexCol name="formPropertyType" display="processDefNodeFormForm.formPropertyType" alias="formPropertyTypeCN" sortable="true" width="80" align="left"></flex:flexCol>
		<flex:flexCol name="formPropertyOperate" display="processDefNodeFormForm.formPropertyOperate" alias="formPropertyOperateCN" sortable="true" width="80" align="left"></flex:flexCol>
		<flex:flexCol name="processNodeNo" display="processDefNodeFormForm.processNodeNo" sortable="true" width="45" align="center"></flex:flexCol>
		<flex:flexCol name="auditResult" display="processDefNodeFormForm.auditResult" sortable="true" width="45" align="center"></flex:flexCol>
		<flex:flexCol name="disposeResult" display="processDefNodeFormForm.disposeResult" sortable="true" width="60" align="left"></flex:flexCol>
		<flex:flexCol name="isTakeBack" display="processDefNodeFormForm.isTakeBack" alias="isTakeBackCN" sortable="true" width="45" align="center"></flex:flexCol>
		<flex:flexCol name="objId" display="操作" sortable="true" width="60" align="center"></flex:flexCol>
		<flex:flexBtn name="添加流程节点表单" bclass="add" onpress="ProcessDefNodeFormList.add"></flex:flexBtn>
	</flex:flexgrid>
	<div class="conOperation">
		<button name="return_to_process_def_node_list" type="button" tabindex="19"><span>关闭</span></button>
	</div>
</div>
