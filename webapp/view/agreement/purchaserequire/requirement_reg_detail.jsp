<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
var requirementRegDetail={};

$(document).ready(function(){
	//附件处理
	$('div[id=file]').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=file&isSelect=yes&attachRelaId='+$("#file").attr("name"));
});
</script>
	<div class="formLayout form1Pa">
	    <h4><span>指标信息</span></h4>
		<ul>
			<li>
				<label>需求：</label>
				<span>${requirementReg.requirement.title}</span>
			</li>
			<li>
				<label>报名机构：</label>
				<span>${requirementReg.regOrg.orgName}</span>
			</li>	
			<li>
				<label>联系人：</label>
				<span>${requirementReg.linkMen}</span>
			</li>	
			<li>
				<label>联系电话：</label>
				<span>${requirementReg.linkTel }</span>
			</li>
			<li>
				<label>传真：</label>
				<span>${requirementReg.fax}</span>
			</li>
			<li>
				<label>电子邮件：</label>
				<span>${requirementReg.email}</span>
			</li>
			<li class="fullLine">
				<label style="height:30%;">报名附件：</label>
				<div id="file" name="${requirementReg.file}" class="uploadFile"></div>
			</li>
		</ul>
	</div>
<div class="conOperation center">
	<button  id="regClose" type="button" onclick="$('.epsDialogClose').trigger('click');" ><span><spring:message code="globe.close"/></span></button>
</div>
