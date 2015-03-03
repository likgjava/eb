<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
var requirementRegForm={};

//保存 报名信息
requirementRegForm.saveReg = function(type){
	//表单验证
	if(!$('#requirementRegFrom').valid()||$("input[name=cardIdFlag]").val()=='0'){alert('请正确填写表单!');return;}

	var regJson = formToJsonObject("requirementRegFrom");

	regJson.auditStatus = type;
	
	if(confirm("确认操作？")){
		var url = $("#initPath").val()+"/RequirementRegController.do?method=saveRequirementReg";
		if($('#oldLinker').val()!=$('#linker').val() && $('#oldIdCard').val()!=$('#idCard').val()) {
			url += "&type=create";
		}else {
			url += "&type=update";
		}
		$.getJSON(url,regJson,function(json){
			if(json.success){
				$('.epsDialogClose').trigger('click');
				if(requirement_list && requirement_list.oTable){
					requirement_list.oTable.fnDraw();
				}
			}else{
				alert("操作失败！");
			}
		});
	}
}

//身份证号唯一性的校验
requirementRegForm.cardIdChange = function(){
	var idCard = $("#idCard").val();
	var requirementId = $("input[name=requirement.objId]").val();
	$.getJSON($("#initPath").val()+"/RequirementRegController.do?method=checkedIdCard",{"idCard":idCard,"requirementId":requirementId},function(json){
		if(json.failure){
			$("div[id=tips]").html('<ul><em>已有相同身份证号报名！</em></ul>');
			$("input[name=cardIdFlag]").val('0');
			$("div[id=tips]").show();
		}else{
			$("input[name=cardIdFlag]").val('1');
			$("div[id=tips]").hide();
		}	
	});
}

$(document).ready(function(){

	//预验证
	$("#requirementRegFrom").validate();
	
	//附件处理
	$('div[id=file]').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=file&attachRelaId='+ ($('div[id=file]').attr('name')!=""?$('div[id=file]').attr('name'):"") );
});
</script>

<form id="requirementRegFrom">
	<input type="hidden" name="requirement.objId" id="requirement.objId" value="${param.requirementId}">
	<input type="hidden" name="cardIdFlag" >
	<!-- 以下用来判断是否应该新增一条联系人信息 -->
	<input type="hidden" name="oldLinker" id="oldLinker" value="${requirementReg.linkMen}" />
	<input type="hidden" name="oldIdCard" id="oldIdCard" value="${requirementReg.idCard}" />
	<input type="hidden" name="currentEmpId" id="currentEmpId" value="${currentEmpId}" />
	
	<div class="formTips warm hidden" id="tips"></div>
	<div class="formLayout form1Pa">
	    <h4><span>指标信息</span></h4>
		<ul>
			<li>
				<label>联系人：</label>
				<input name="linkMen" class="required"  value="${requirementReg.linkMen}" >
				<span class="eleRequired">*</span>
			</li>	
			<li>
				<label>联系电话：</label>
				<input name="linkTel" class="required number" value="${requirementReg.linkTel}" >
				<span class="eleRequired">*</span>
			</li>
			<li>
				<label>身份证号：</label>
				<input name="idCard" id="idCard" value="${requirementReg.idCard }" maxlength="18" onchange="requirementRegForm.cardIdChange()" size="30" class="required cnIdCard">
				<span class='eleRequired'>*</span>
			</li>
			<li>
				<label>邮编：</label>
				<input name="zipCode" id="zipCode" value="${requirementReg.zipCode }" class="cnZipCode" maxlength="6" size="30">
			</li>
			<li>
				<label>传真：</label>
				<input name="fax" class="cnPhone" value="${requirementReg.fax}">
			</li>
			<li>
				<label>电子邮件：</label>
				<input name="email" class="required email" value="${requirementReg.email}">
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label style="height:30%;">报名附件：</label>
				<div id="file" class="uploadFile" name="${requirementReg.file}"></div>
			</li>
		</ul>
	</div>
</form>   	

<div class="conOperation center">
	<!-- 被退回时有保存操作 -->
	<c:if test="${requirementReg.auditStatus=='03'||requirementReg.auditStatus=='00'}">
		<button  id="regSave" type="button" onclick="requirementRegForm.saveReg('${requirementReg.auditStatus}');"><span><spring:message code="globe.save"/></span></button>
	</c:if>
	<button  id="regSave" type="button" onclick="requirementRegForm.saveReg('01');"><span>提交报名</span></button>
	<button  id="regClose" type="button" onclick="$('.epsDialogClose').trigger('click');" ><span><spring:message code="globe.close"/></span></button>
</div>
