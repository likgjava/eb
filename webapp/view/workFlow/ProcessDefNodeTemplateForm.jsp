<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="partContainers formLayout form2Pa">
<input type="hidden" name="isSelectAdd" id="isSelectAdd" value="${param.isSelectAdd }">
	<form method="post" name="ProcessDefNodeForm" id="ProcessDefNodeForm"  action="ProcessDefNodeTemplateController.do?method=save" >
		<input type="hidden" name="objId" id="objId" value="${processDefNode.objId}"/>
		<input type="hidden" name="objId" id="objId" value="${processDefNode.objId}"/>
		<input type="hidden" name="processDefine.objId" id="processDefine.objId" value="${processDefNode.processDefine.objId}"/>
		<ul>
			<li class="fullLine">
				<label class=""><spring:message code="processDefNodeForm.nodeSort"/>：</label>
				<input type="text" name="nodeSort" value="${processDefNode.nodeSort}" class="required long" maxlength="8"></input>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label class=""><spring:message code="processDefNodeForm.nodesName"/>：</label>
				<input type="text" name="nodesName" value="${processDefNode.nodesName}" class="required long" maxlength="200"></input>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label class=""><spring:message code="processDefNodeForm.nodeType"/>：</label>
				<html:select id="nodeType" name="nodeType" code="processDefNode.nodeType" selectedValue="${processDefNode.nodeType}" style="width:306px;"></html:select>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label class=""><spring:message code="processDefNodeForm.department"/>：</label>
				<input type="hidden" name="department.objId" id="department.objId" value="${processDefNode.department.objId}"></input>
				<input type="text" name="department.name" id="department.name" value="${processDefNode.department.name}" readonly="readonly" class="long"></input>
			</li>
			<li class="fullLine">
				<label class=""><spring:message code="processDefNodeForm.role"/>：</label>
				<input type="hidden" name="role.objId" id="role.objId" value="${processDefNode.role.objId}"></input>
				<input type="text" name="role.chName" id="role.chName" value="${processDefNode.role.chName}" readonly="readonly" maxlength="200"  class="long"></input>
			</li>
			<li class="fullLine">
				<label class=""><spring:message code="processDefNodeForm.nodeSign"/>：</label>
				<input type="radio" name="nodeSign" id="nodeSign_0" <c:if test="${fn:contains(processDefNode.nodeSign,0) || processDefNode.objId == null}">checked="checked"</c:if> value="0" />起点
				<input type="radio" name="nodeSign" id="nodeSign_1" <c:if test="${fn:contains(processDefNode.nodeSign,1)}">checked="checked"</c:if> value="1" />中间节点
				<input type="radio" name="nodeSign" id="nodeSign_2" <c:if test="${fn:contains(processDefNode.nodeSign,2)}">checked="checked"</c:if> value="2" />结束节点
			</li>
			<li class="fullLine">
				<label class=""><spring:message code="processDefNodeForm.resLink"/>：</label>
				<input type="text" name="resLink" value="${processDefNode.resLink}" class="required" maxlength="300" style="width: 300px;"></input>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label class=""><spring:message code="processDefNodeForm.extendParamUrl"/>：</label>
				<input type="text" name="extendParamUrl" value="${processDefNode.extendParamUrl}" maxlength="500" style="width: 300px;"></input>
			</li>
			<li class="fullLine">
				<label class=""><spring:message code="processDefNodeForm.nodeDes"/>：</label>
				<input type="text" name="nodeDes" value="${processDefNode.nodeDes}" maxlength="300" style="width: 300px;"></input>
			</li>
			<li class="fullLine">
				<label class=""><spring:message code="processDefNodeForm.isShowTodo"/>：</label>
				<input type="radio" name="isShowTodo" id="isShowTodo" <c:if test="${processDefNode.isShowTodo == true}">checked="checked"</c:if> value="1" />  是
				<input type="radio" name="isShowTodo" id="isShowTodo" <c:if test="${processDefNode.isShowTodo == false}">checked="checked"</c:if> value="0" />  否
			</li>
			<li class="fullLine">
				<label class=""><spring:message code="processDefNodeForm.auditType"/>：</label>
				<input type="radio" name="auditType" <c:if test="${fn:contains(processDefNode.auditType,1)}">checked="checked"</c:if> value="1" />  常规审批
				<input type="radio" name="auditType" <c:if test="${fn:contains(processDefNode.auditType,2)}">checked="checked"</c:if> value="2" />  会签
				<input type="radio" name="auditType" <c:if test="${fn:contains(processDefNode.auditType,0)}">checked="checked"</c:if> value="0" />  查阅
			</li>
		</ul>
	</form>
    <div class="conOperation">
    	<button name="save_node" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
		<button name="return_to_process_list" type="button" tabindex="19"><span>返回流程定义列表</span></button>
		<button name="return_to_node_list" type="button" tabindex="19"><span>关闭</span></button>
	</div>
</div>
<script>
var ProcessDefNodeForm={};

$(document).ready(function(){
	$('[id=role.chName]').click(function(){
		$.epsDialog({
			 title:'请选择执行角色',
	         width: '650',
	         height: '420',
			 id:'select_role',
			 url:"view/workFlow/selectRoleList.jsp"
		})
	})
	$('[id=department.name]').click(function(){
		$.epsDialog({
			 title:'请选择执行部门',
	         width: '650',
	         height: '420',
			 id:'select_dept',
			 url:'view/workFlow/selectOrganizationList.jsp'
		})
	})
	
	$('[name=save_node]').click(function(){
		if(!$('#ProcessDefNodeForm').valid()){return;}
		$.getJSON($('#initPath').val()+'/ProcessDefNodeTemplateController.do?method=saveProcessDefNodeTemplate&isSelectAdd='+$('#isSelectAdd').val(), formToJsonObject('ProcessDefNodeForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$("#processDefNodeGrid").reload();
			$('#process_node_form .epsDialogCloseReload').click();
		});
	})
	$('[name=return_to_process_list]').click(function(){
		$('#conBody').empty().loadPage($('#initPath').val()+'/ProcessDefineController.do');
		$('#process_node_form .epsDialogCloseReload').click();
	})
	$('[name=return_to_node_list]').click(function(){
		$('#process_node_form .epsDialogCloseReload').click();
	})
})
</script>