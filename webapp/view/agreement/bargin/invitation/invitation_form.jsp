<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!--如果是外网样式，需单独引入ExtJs的编辑器样式-->
<c:if test="${isOutCss}">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/plug-in/extjs/resources/css/ext-all.css"/>
	<script type="text/javascript" src='<%=request.getContextPath()%>/view/resource/plug-in/extjs/ext-all.js'></script>
</c:if>

<div class="formTips attention">
	<ul>
	<c:if test="${inrqDetailList!=null }">
		<li id="send_supplier_list">
			<em>已发送邀请供应商：</em>
			<c:set var="supplierIds" value=""></c:set>
			<c:forEach items="${inrqDetailList}" var="inrqDetail">
				${inrqDetail.supplier.orgName }&nbsp;
				<c:set var="supplierIds" value="${supplierIds},${inrqDetail.supplier.objId }"></c:set>
			</c:forEach>
			<input type="hidden" value="${supplierIds}" id="supplierNAMES"/>
		</li>
	</c:if>
		<li>
			<em>发送邀请函请点击：
			<span class="sysicon siAdd"><a id="addSupplier_concener" href="javascript:void(0);"><strong>从客户选择供应商</strong></a></span>
			<span class="sysicon siAdd"><a id="addSupplierBtn" href="javascript:void(0);"><strong>从供应商库选择供应商</strong></a></span>
			</em>
		</li>
	</ul>
</div>
<select size="20" id="inrqDetailSupplier" multiple="multiple" class="hidden">
<c:forEach items="${inrqDetailList}" var="inrqDetail">
				<option value="${inrqDetail.supplier.objId }">${inrqDetail.supplier.objId }</option>
			</c:forEach>
</select>
<input type="hidden" id="_checkValues"  value=""/>
<form:form id="invitationForm" method="post" modelAttribute="inviterollrequ">
<div class="formLayout form2Pa">
	<span class="hidden" id="inviterollrequContents" >${inviterollrequContents }</span>
	<input	type="hidden" name="objId" id="objId"	value="<c:out value="${inviterollrequ.objId}"/>" />
	<input	type="hidden" name="project.objId" id="projectId"	value="<c:out value="${param.projectId}"/>" />
	<input	type="hidden" name="projCode" id="projCode"	value="${project.projCode }" />
	<input	type="hidden" name="projName" id="projName"	value="${project.projName }" />
	<input	type="hidden" name="auditStatus" id="auditStatus"	value="00" />
	<ul>
		<li class="fullLine"><label>供   应  商：</label><span id="selected_supplier_list" style="float:left"></span><input name="selectedSupplierId" id="selectedSupplierId" type="hidden"/></li>
		<li><label>项目名称：</label> ${project.projName}</li>
		<li><label>项目编号：</label> ${project.projCode}</li>
		<li class="fullLine">
			<label>供应商纳入项目：<br></label>
			<input type="radio" name="isJoin" checked="checked" value="false"/>否
			<input type="radio" name="isJoin" value="true"/>是
			（选择是， 则供应商无须报名，直接参与项目，否则供应商需自主报名才能参与项目）
		</li>
	</ul>
	<textarea name="invtitationContent" id="invtitationContent" class="hidden">${inviterollrequContents }</textarea>
</div>
</form:form>

<div id="htmlEditor"></div>

<div class="conOperation">
	<button type="button" name="bulletinSaveBtn" id="save"><span>发送邀请函</span></button>
	<button type="button" name="backBtn" onclick="invitationForm.close();"><span><spring:message code="globe.close" /></span></button>
</div>

<script>
var invitationForm = {};
var htmlEditor;

//关闭
invitationForm.close = function(){
	$('.epsDialogClose').trigger('click');	
}


$(document).ready(function(){

	var _ids = $('#inrqDetailSupplier>option').map(function(){ return $(this).val();	}).get().join(",");
	$('#_checkValues').val($('#inrqDetailSupplier>option').map(function(){ return $(this).val();	}).get().join(","));
	
	//表单验证
	$('#invitationForm').validate();
	
	//加载ExtJs的HTML编辑器
	htmlEditor = new Ext.form.HtmlEditor({
		height: 300,
		width: 750,
		renderTo: 'htmlEditor'
	});
	htmlEditor.setValue($('#invtitationContent').val());

	//保存
	$("button[name=bulletinSaveBtn]").click(function(){
		$('#invtitationContent').val(htmlEditor.getValue());
		if(!$('#invitationForm').valid()){alert('请正确填写表单!');return;}
		if($('#selectedSupplierId').val() ==""){alert('请选择要邀请的供应商');return;}
		var inviterollrequ = formToJsonObject('invitationForm');
		var inviterollrequContents = htmlEditor.getValue();
		inviterollrequContents = inviterollrequContents.replace(/&ldquo;/gi,"“");
		inviterollrequContents = inviterollrequContents.replace(/&rdquo;/gi,"”");
		inviterollrequ.content = inviterollrequContents;
		$.ajax({
			url: $('#initPath').val()+'/InvitationController.do?method=saveInvitation&supplierIds='+$('#selectedSupplierId').val()+'&isJoin='+$("input[name=isJoin]:checked").val(),
			type:"POST",
			data:inviterollrequ,
			dataType:'json',
			success:function(json){
				if(json.failure){
					if(json.result)alert(json.result);return;
				}
				//成功关闭
				alert("操作成功！");
				invitationForm.close();
			},
			error:function(msg){
				alert('操作失败');
			}
		})
		
	});

	//从客户中选择供应商
	$("#addSupplier_concener").click(function(){
		$.epsDialog({
			id:"selectSupplierDiv",
			title:"选择供应商",
			height:500, 
			width:900, 
			url:$('#initPath').val()+"/view/pubservice/application/concern/select_concern_supplier_list.jsp"
		})
		return false;
	});
	
	//从供应商库里选择供应商
	$("#addSupplierBtn").click(function(){
		$.epsDialog({
			id:"selectSupplierDiv",
			title:"选择供应商",
			height:500, 
			width:900, 
			url:$('#initPath').val()+"/view/srplatform/empSelect/selectBox.jsp?domainName=OrgInfo&queryColumns=objId,orgName&IDS=selectedSupplierId&SHOWS=selected_supplier_list&backfill=false&showAlreadySelect=false&escapeValues="+$("#supplierNAMES").val()+"&params="+escape("&supplierId=null&supplierId_op=!=&useStatus=01&isOff=1")+"&dialogId=selectSupplierDiv"
		})
		return false;
	});
	
})
</script>