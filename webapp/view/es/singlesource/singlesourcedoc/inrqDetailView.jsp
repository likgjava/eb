<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style type="text/css">
<!--
h5.htop {text-align: right;margin-right:10px;}
--> 
</style>

<input type="hidden" id="fromType" value="${fromType}">
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/singlesource/singlesourcedoc/inrqDetailView.js"></script>
<div class="formLayout formPa">
	<h5 class="htop">
		<c:if test="${signUprecordF=='no'}">
	  <c:choose>
	  	<c:when test="${isApply=='01'}">
	  		<span>
			  <a onclick="getSignUprecordForm('${inrqDetail.inviterollrequ.project.objId}');" href="#">[我要报名]</a>
			 </span>
	  	</c:when>
	  	<c:otherwise>
	  		<span class="em highlight">报名时间未到或已过</span>
	  	</c:otherwise>
	  </c:choose>
		 </c:if>
		<c:if test="${signUprecordF=='yes'}">
			 <span>
			  <a onclick="viewSupplierDetail('${signUprecord.objId}');" href="#">[查看<dm:out value="local__signup" tenderType="${project.tenderType}">报名信息</dm:out>]</a>
			 </span>
		 </c:if>
	</h5>
	<span style="height:auto;">${inrqDetail.contents}</span>
	<input type="hidden" name="fromProj" value="${fromProj}" id="fromProj"/>
</div>
<div class="conOperation">
	<button id="returnBtn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
</div>
<script type="text/javascript">

$(document).ready(function(){
   if( $("#fromType").val()!='look'){
      $("#returnBtn").hide();
	 }

   $("#returnBtn").click(function(){
	   $('#epsDialogClose').click();
	   })
	
})


function getSignUprecordForm(projectId){
	if ($('#fromProj').val()=='yes') {
		$("#tabform4").click();
	}else{
		$.epsDialog({
			title:'我要报名',
			url:$('#initPath').val()+'/SignUprecordController.do?method=toPage&projectId='+projectId,
			width: '720',
			height: '320',
			isReload:false,
			onClose: function(){
		}
		});	
	}
};
function viewSupplierDetail(objId){
	if ($('#fromProj').val()=='yes') {
		$("#tabform4").click();
	}else{
		$.epsDialog({
			title:'报名信息',
			url:$('#initPath').val()+'/SignUprecordController.do?method=toViewSignupPageTab&objId='+objId,
			width: '650',
			height: '320',
			isReload:false,
			onClose: function(){
		}
		});	
	}
};
//返回
function bullentinReturn(){
	var auditStatus = $('#auditStatus').val();
	var bullType = $('#bullType').val();
	$('#conBody').loadPage($('#initPath').val()+'/BulletinController.do?method=getMoreForBulletinList&auditStatus='+auditStatus+'&bullType='+bullType);

}
</script>