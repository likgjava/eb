<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="formLayout">        
	<form id="updateOpenBidMemberForm" method="post">
		<input type="hidden" name="objId" id="objId" value="${workgMember.objId }"/>
		<ul>
			<li>
				<label for="workgmName"><spring:message code="workgMemberForm.workgmName"/></label>
				<input type="text" name="workgmName" id="workgmName"  	class="required" value="${workgMember.workgmName }" />
				<span class="eleRequired">*</span>
			</li>
			<li>
				<label for="workgmType"><spring:message code="workgMemberForm.workgmType"/></label>
					<select name="workgmType" id="workgmType" class="required" >
						<option value="招标单位代表" <c:if test="${workgMember.workgmType == '00' }"></c:if> value="00" >招标单位代表</option>
						<option value="专家" <c:if test="${workgMember.workgmType == '01' }"></c:if> value="01">专家</option>
					</select>	 	  
					<span class="eleRequired">*</span>
			</li>
		</ul>
		<div class="conOperation">
			<button id="workgMemberSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="closeButton" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
		</div>
	</form>
</div>
<script language="javascript">
$(document).ready(function(){	
	//新增开标小组成员
	$("#workgMemberSave").click(function(){
		if(!$('#updateOpenBidMemberForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/WorkgMemberController.do?method=save', formToJsonObject('updateOpenBidMemberForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#epsDialogClose').click();
		});
	});
	$("#closeButton").click(function(){
		$('#epsDialogClose').click();
	})
	
})

</script>