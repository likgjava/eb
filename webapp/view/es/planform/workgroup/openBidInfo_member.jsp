<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/workgroup/openBidInfo_member.js"></script>
<div class="partContainers">
<form id="workgMemberFormId" method="post">  
	<div class="formLayout form2Pa">
		<h5><span><%=java.net.URLDecoder.decode(request.getParameter("t"),"UTF-8") %>信息</span></h5>      
	    	<ul>
	    		<li>
					<label  class="short"><%=java.net.URLDecoder.decode(request.getParameter("t"),"UTF-8") %>： </label>
					<input type="text" id="workgmName" name="workgmName" value="" class="required"/>
					<span class="eleRequired">*</span>
				</li>
	    		<li>
					<label  class="short"><%=java.net.URLDecoder.decode(request.getParameter("t"),"UTF-8") %>帐号：</label>
					<input type="text" id="workgmAccount" name="workgmAccount" value=""/>
				</li>
	    		<li>
					<label  class="short">联系电话： </label>
					<input type="text" id="linkerPhoneId" name="linkerPhone"  class="cnMobile required" value=""/>
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
		<input type="hidden" id="subOrProjectId1" value="${projectId}"/>
		<input type="hidden" id="groupType" value="${groupType}"/>
		<input type="hidden" id="empId" value=""/>
		<input type="hidden" id="empIds" value="${empIds}"/>
		<input type="hidden" name="workgmType" value="${workgmType}"/>
		<input type="hidden" name="workGroupId" value="${workGroupId}"/>
		<input type="hidden" id="orgId" value="${orgId}"/>
		<button id="saveBtnId" type="button" ><span><spring:message code="globe.save"/></span></button>
		<button id="closeBtn" type="button" ><span>关闭</span></button>
	</div>
	</div>    
 </form>
</div>    