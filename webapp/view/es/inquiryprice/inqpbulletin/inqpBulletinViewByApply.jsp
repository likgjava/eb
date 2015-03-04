<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="formLayout formPa">
	 <h5 style="text-align: right;margin-right:10px;">
		 <c:if test="${signUprecordMsg=='01'}">
	  <span>
	  <a onclick="viewSupplierDetail('${signUprecord.objId}');" href="#">[查看<dm:out value="local__signup" tenderType="${project.tenderType}">报名信息</dm:out>]</a>
	  </span>
	  </c:if>
	 <c:if test="${signUprecordMsg=='02'}">
	 
	  <c:choose>
	  	<c:when test="${isApply=='01'}">
	  		<span>
		  		<a onclick="getSignUprecordForm('${bulletin.project.objId}','${orgInfoId}');" href="#">[我要报名]</a>
		  	</span>
	  	</c:when>
	  	<c:otherwise>
	  		<span class="em highlight">报名时间未到或已过</span>
	  	</c:otherwise>
	  </c:choose>
	 </c:if>
	 
	 <span><c:if test="${returnUrl=='yes'}">
			<a href="#" onclick="bullentinReturn();">返回</a>
		</c:if></span>
	 
	  </h5>
	  <input type="hidden" name="projectId" id="projectId" value="${bulletin.project.objId}">
	  <input type="hidden" name="project.objId" id="project.objId" value="${bulletin.project.objId}">
	  <input type="hidden" name="orgInfo.objId" id="orgInfo.objId" value="${orgInfoId}">
	  <input type="hidden" name="auditStatus" id="auditStatus" value="${bulletin.auditStatus}">
	  <input type="hidden" name="bullType" id="bullType" value="${bulletin.bullType}">
	  
	<span style="height:auto;">${bulletin.bullContents}</span>
</div>


<script type="text/javascript">
$(document).ready(function(){
//$("#signUpCondFactorView").loadPage($('#initPath').val()+'/SignUpCondFactorController.do?method=getSignUpCondFactorListByProjectId&type=detail&projectId='+$("input[name=projectId]").val());
$("strong").css("color","black");
 
	
})

function getSignUprecordForm(projectId,orgInfoId){
	$.epsDialog({
		title:'我要报名',
		url:$('#initPath').val()+'/SignUprecordController.do?method=toPage&projectId='+projectId+'&orgInfoId='+orgInfoId,
		width: '700',
		height: '220',
		isReload:false,
		onClose: function(){
	}
	});	
};
function viewSupplierDetail(objId){
	$.epsDialog({
		title:'报名信息',
		url:$('#initPath').val()+'/SignUprecordController.do?method=toViewSignupPage&objId='+objId+"&prjId="+$("#projectId").val(),
		width: '700',
		height: '220',
		isReload:false,
		onClose: function(){
	}
	});	
};
//返回
function bullentinReturn(){
	var auditStatus = $('#auditStatus').val();
	var bullType = $('#bullType').val();
	$('#conBody').loadPage($('#initPath').val()+'/BulletinController.do?method=getMoreForBulletinList&auditStatus='+auditStatus+'&bullType='+bullType);

}
</script>