<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script>
var ProcessDefNodeList={};

$(document).ready(function(){
	
})

ProcessDefNodeList.add = function(){
	$.epsDialog({
		id:'process_node_form',
        title:'新增流程节点',
        url:$("#initPath").val()+"/ProcessDefNodeTemplateController.do?method=toCreateOrUpdate&processDefId="+$('#processDefId').val(),
        width: '800',
        height: '500',
        maxWin:false,
        onClose: function(){
        	
        }
	});
}
/**
 * 插入流程节点，在当前选择节点后面添加流程节点
 */
ProcessDefNodeList.selectAdd = function(){
	var processDefNodeIds = $('#processDefNodeGrid').getSelects();
	if(processDefNodeIds.indexOf(',') > 0 || processDefNodeIds.length < 1){
		alert('请选择一个节点进行插入节点操作');
		return false;
	}
	$.epsDialog({
		id:'process_node_form',
        title:'插入流程节点',
        url:$("#initPath").val()+"/ProcessDefNodeTemplateController.do?method=toCreateOrUpdate&isSelectAdd=true&processDefId="+$('#processDefId').val()+'&preObjId='+processDefNodeIds,
        width: '800',
        height: '500',
        maxWin:false,
        onClose: function(){
        	
        }
	});
}

ProcessDefNodeList.success = function(){
	$("#processDefNodeGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				$.epsDialog({
					id:'process_node_form',
			        title:'更新流程节点',
			        url:$("#initPath").val()+"/ProcessDefNodeTemplateController.do?method=toCreateOrUpdate&processDefId="+$('#processDefId').val()+'&objId='+rowId,
			        width: '800',
			        height: '500',
			        maxWin:false,
			        onClose: function(){
			        	
			        }
				});
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">编辑审核表单</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				$.epsDialog({
					id:'process_node_form_list',
			        title:'编辑流程节点审核表单',
			        url:$("#initPath").val()+'/view/workFlow/ProcessDefNodeFormTemplateList.jsp?processDefNodeId='+rowId,
			        width: '800',
			        height: '500',
			        maxWin:false,
			        onClose: function(){
			        	
			        }
				});
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				var json = $("#processDefNodeGrid").flexGetRowJsonById(rowId); 
				if(window.confirm('确定删除['+json.nodesName+']吗?')){
					$.getJSON($('#initPath').val()+'/ProcessDefNodeTemplateController.do?method=removeProcessDefNodeTemplateCascadeRemoveProcessDefNodeForm',{'objId':rowId},function(json){
						$("#processDefNodeGrid").reload();
					});
				}
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">上移</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				var json = $("#processDefNodeGrid").flexGetRowJsonById(rowId);
				var nodeNo = json['nodeSort'] * 1;
				nodeNo--;
				$.getJSON($('#initPath').val()+'/ProcessDefNodeTemplateController.do?method=updateDefNodeNo',{'objId':rowId,'nodeNo':nodeNo},function(json){
					$("#processDefNodeGrid").reload();
				});
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">下移</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				var json = $("#processDefNodeGrid").flexGetRowJsonById(rowId);
				var nodeNo = json['nodeSort'] * 1;
				nodeNo++;
				$.getJSON($('#initPath').val()+'/ProcessDefNodeTemplateController.do?method=updateDefNodeNo',{'objId':rowId,'nodeNo':nodeNo},function(json){
					$("#processDefNodeGrid").reload();
				});
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">复制</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				$.getJSON($('#initPath').val()+'/ProcessDefNodeTemplateController.do?method=copyProcessDefNodeTemplate',{'objId':rowId},function(json){
					$("#processDefNodeGrid").reload();
				});
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">删除级联更新编号</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				var json = $("#processDefNodeGrid").flexGetRowJsonById(rowId); 
				if(window.confirm('确定删除['+json.nodesName+']吗?')){
					$.getJSON($('#initPath').val()+'/ProcessDefNodeTemplateController.do?method=removeProcessDefNodeTemplateCascadeRemoveProcessDefNodeForm&isChangeOtherNodeSort=true',{'objId':rowId},function(json){
						$("#processDefNodeGrid").reload();
					});
				}
			}).appendTo(obj);
		}
	});
}
</script>
<input type="hidden" id="processDefId" value="${param.processDefId}"/>
<div class="partContainers">	  
	<flex:flexgrid checkbox="true"  id="processDefNodeGrid" url="ProcessDefNodeTemplateController.do?method=list&processDefine.objId=${param.processDefId}&order=nodeSort" 
	onSuccess="ProcessDefNodeList.success" rp="200" title="流程节点模版" queryColumns="" usepager="false">
		<flex:flexCol name="nodeSort" display="processDefNodeForm.nodeSort" sortable="true" width="50" align="center"></flex:flexCol>
		<flex:flexCol name="nodesName" display="processDefNodeForm.nodesName" sortable="true" width="150" align="left"></flex:flexCol>
		<flex:flexCol name="nodeType" display="processDefNodeForm.nodeType" alias="nodeTypeCN" sortable="true" width="100" align="left"></flex:flexCol>
		<flex:flexCol name="department.name" display="processDefNodeForm.department" sortable="true" width="100" align="left"></flex:flexCol>
		<flex:flexCol name="role.chName" display="processDefNodeForm.role" sortable="true" width="100" align="left"></flex:flexCol>
		<flex:flexCol name="nodeDes" display="processDefNodeForm.nodeDes" sortable="true" width="120" align="left"></flex:flexCol>
		<flex:flexCol name="nodeSign" display="processDefNodeForm.nodeSign" alias="nodeSignCN" sortable="true" width="50" align="center"></flex:flexCol>
		<flex:flexCol name="isShowTodo" display="processDefNodeForm.isShowTodo" alias="isShowTodoCN" sortable="true" width="50" align="center"></flex:flexCol>
		<flex:flexCol name="auditType" display="processDefNodeForm.auditType" alias="auditTypeCN" sortable="true" width="50" align="center"></flex:flexCol>
		<flex:flexCol name="objId" display="操作"  width="380" align="center" sortable="true"></flex:flexCol>
		<flex:flexBtn name="插入流程节点" bclass="add" onpress="ProcessDefNodeList.selectAdd"></flex:flexBtn>
		<flex:flexBtn name="添加流程节点" bclass="add" onpress="ProcessDefNodeList.add"></flex:flexBtn>
	</flex:flexgrid>
	<div class="conOperation">
		<button name="historyBackBtn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	</div>
</div>