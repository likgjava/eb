<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">

$(document).ready(function() {
 var objId = $("#expertInfoForm").find("input[id=objId]").val()
	
	//回填附件
	$.getJSON($("#initPath").val()+"/CertificateController.do?method=createOrUpdate&includedProperties=expertInfo&objId="+objId,{},function(json){
		var fileId = json.certificate.file;
		if(null!=fileId && ""!=fileId){
			$('#expInfoFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=file&isSelect=false&attachRelaId='+fileId);
		}
		json2ObjectSpan('expertInfoForm', json.certificate);
	});
	
	// 返回
	$('#closeCertificateBtn').click(function(){
		$('#epsDialogClose').click();
	});
})

</script>

<div class="partContainers formLayout form2Pa">
	<form id="expertInfoForm">
	<input type="hidden" id="objId" value="${param.objId }"/>
     	<ul>
     		<li>
	     		<label>专家姓名：</label>
	     			<span id="expertInfo.name"></span>
    	    </li>
	     	<li>
	     		<label>职称名称：</label>
	     			<span id="titleName"></span>
    	    </li>
	     	<li>
	     		<label>证书编号：</label>
	     			<span id="certificateNo"></span>
    	    </li>
	     	<li>
	     		<label>颁发机构：</label>
	     			<span id="issueUnit"></span>
    	    </li>
	     	<li>
	     		<label>获得证书时间：</label>
	     			<span id="acquireTime"></span>
    	    </li>
	     	<li>
	     		<label>证书有效时间：</label>
	     			<span id="valDate"></span>
    	    </li>
	     	<li class="fullLine">
	     		<label>证书附件：</label>
	     			<div id="expInfoFile" name="expInfoFile"  class="uploadFile"></div>
    	    </li>
	     	
    	</ul>
     </form>
        <div class="conOperation">
				<button type="button" id="closeCertificateBtn"><span><spring:message code="globe.close"/></span></button>
		   </div>
 </div>
    	    