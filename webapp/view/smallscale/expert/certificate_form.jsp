<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">

$(document).ready(function() {

	$("#acquireTime").epsDatepicker({applyRule: endRule });
	$("#valDate").epsDatepicker({applyRule: startRule });

	//表单验证
	$("#expertInfoForm").validate();	
	
	//回填附件
	var fileId = $('#expertInfoForm').find('input[id=expInfoFileId]').val();
	if(null!=fileId&&""!=fileId){
		$('#expInfoFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=file&attachRelaId='+fileId);
	}
	else{
		$('#expInfoFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=file');
	}
	// 保存专家职称信息
	$('button[id^=certificateBtn]').click(function() {
		if(!$('#expertInfoForm').valid()){alert('请正确填写表单!');return;}
		$('button[id^=certificateBtn]').attr("disabled",true);
		var certificate = formToJsonObject('expertInfoForm');
		certificate.saveType = $(this).attr('id').replace("certificateBtn_","");
		$.getJSON($("#initPath").val()+"/CertificateController.do?method=saveCertificate",certificate,function(json){
			if(json.failure){alert(json.result);$('button[id^=certificateBtn]').attr("disabled",false);return;}
			if(json.success){
				if(certificate.saveType =="submit"){
					alert("已提交审核！");
				}else{
					alert("保存成功！");
				}
				$('#conBody').loadPage($("#returnUrl").val());
			}
		});
	})
	
	// 返回
	$('#returnCertificateBtn').click(function(){
		$('#conBody').loadPage($('#returnUrl').val());
	});
})

</script>

<div class="partContainers formLayout form2Pa">
	<form:form id="expertInfoForm" method="post" modelAttribute="certificate">
	<form:hidden path="objId"></form:hidden>
	<form:hidden path="expertInfo.objId"></form:hidden>
	<input type="hidden" id="expInfoFileId" value="${certificate.file}"/>
     	<ul>
	     	<li class="fullLine">
	     		<label>职称名称：</label>
	     			<form:input path="titleName" cssClass="required" maxlength="25" size="60%"></form:input><span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label>证书编号：</label>
	     			<form:input path="certificateNo" cssClass="required" maxlength="50"></form:input><span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label>颁发机构：</label>
	     			<form:input path="issueUnit" cssClass="required" maxlength="50"></form:input><span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label>获得证书时间：</label>
	     			<form:input path="acquireTime" cssClass="required" ></form:input><span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label>证书有效时间：</label>
	     			<form:input path="valDate" cssClass="required" ></form:input><span class="eleRequired">*</span>
    	    </li>
	     	<li class="fullLine">
	     		<label>证书附件：</label>
	     			<div id="expInfoFile" name="expInfoFile"  class="uploadFile"></div>
    	    </li>
	     	
    	</ul>
     </form:form>
        <div class="conOperation">
				<button type="button" id="certificateBtn_save"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="certificateBtn_submit"><span>提交</span></button>
				<button type="button" id="returnCertificateBtn"><span><spring:message code="globe.return"/></span></button>
		   </div>
 </div>
    	    