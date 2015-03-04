<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/workgroup/updateOpenBidInfo_member.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/common/planTemplateTask.js"></script>
<div class="partContainers">
<form id="workgMemberFormId" method="post">  
	<div class="formLayout form2Pa">
		<h5><span><dm:out value="local__OpenTendering__openBid_zh">开标</dm:out>人信息</span></h5>      
	    	<ul>
	    		<li>
					<label  class="short"><dm:out value="local__OpenTendering__openBid_zh">开标</dm:out>人： </label>
					<input type="text" id="workgmName" name="workgmName" value="${workgMember.workgmName}" class="required"/>
					<span class="eleRequired">*</span>
				</li>
	    		<li>
					<label  class="short"><dm:out value="local__OpenTendering__openBid_zh">开标</dm:out>人帐号：</label>
					<input type="text" id="workgmAccount" name="workgmAccount" value="${workgMember.workgmAccount}"/>
				</li>
	    		<li>
					<label  class="short">联系电话： </label>
					<input type="text" id="linkerPhoneId" name="linkerPhone"  class="cnPhone" value="${workgMember.linkerPhone}"/>
					<span class="eleRequired">*</span>
				</li>
				<c:choose>
				<c:when test="${param.workgmType=='00'||param.workgmType=='01'}">
				<li>
					<label  class="short">是否组长： </label>
					<input type="checkbox" id="isLeader" name="isLeader" value="00">
					<span id="isLeaderDiv" style="display:none">			
						组长<input type="radio" id="isAmount1" name="isAmount"  class="required" value="00" checked="checked"/>
						副组长<input type="radio" id="isAmount2" name="isAmount"  value="01" class="required" />					
					</span>
				</li>
				</c:when>
				<c:otherwise>
				<li id="amountShow">
				<input type="hidden" id="isLeader" name="isLeader" value="00">
					<label  class="short">是否正选： </label>
					是<input type="radio" id="isAmount1" name="isAmount"  class="required" value="00" checked="checked"/>
					否<input type="radio" id="isAmount2" name="isAmount"  value="01" class="required" />
				</li>
				</c:otherwise>
				</c:choose>
				<li>
					<label  class="short">是否在现场： </label>
					是<input type="radio" id="signinStatus1" name="signinStatus"  class="required" value="01" checked="checked"/>
					否<input type="radio" id="signinStatus2" name="signinStatus"  class="required"  value="00"/>
				</li>
	    	</ul>
	<div class="conOperation">
		<input type="hidden" id="orgId" value="${orgId}"/>
		<input type="hidden" id="empId" value=""/>
		<input type="hidden" id="empIds" value="${empIds}"/>
		<input type="hidden" name="objId" value="${workgMember.objId}"/>
		<button id="saveBtnId" type="button" ><span><spring:message code="globe.save"/></span></button>
		<button id="closeBtn" type="button" ><span>关闭</span></button>
	</div>
	</div>    
 </form>
</div>    
<script>
var workgMemberFormId = {};

$(document).ready(function(){
	$("#workgMemberFormId").validate({
		
	});	
});
</script>