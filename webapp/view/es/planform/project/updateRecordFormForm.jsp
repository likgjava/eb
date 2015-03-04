<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/updateRecordFormForm.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<div class="formLayout form2Pa">
<form id="recordFormForm" name="recordFormForm" method="post" >
		<input type="hidden" name="objId" id="objId"  value="${recordForm.objId }"/>
		<input type="hidden" name="project.objId" id="project.objId"  value="${recordForm.project.objId }"/>
     	<h5 align="center"><span>录入备案书信息</span></h5>
     	<ul>
     		 <li>
	     		<label for="" class="short">招管备案号:</label>
					<input type="text"  class="required" 
						      id="recFormNo" name="recFormNo" value="${recordForm.recFormNo }"/>
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="" class="short">建设规模:</label>
					<input type="text"  class="required" 
						    id="recFormScale"  name="recFormScale" value="${recordForm.recFormScale }"/>
    	   			<span class="eleRequired">*</span>
    	    </li>
    	       	<li>
	     		<label for="" class="short">建设地点:</label>
					<input type="text"  class="required" 
						      id="recFormAddr" name="recFormAddr" value="${recordForm.recFormAddr }"/>
    	   			<span class="eleRequired">*</span>
    	    </li>
    	     <li>
	     		<label for="" class="short">结构类型:</label>
					<input type="text"  class="required" 
						      id="recFormStructureType" name="recFormStructureType" value="${recordForm.recFormStructureType }"/>
    	   			<span class="eleRequired">*</span>
    	    </li>
    	    <li>
	     		<label for="" class="short">工程名称:</label>
					<input type="text"  class="required" 
						   id="engineeringName" name="engineeringName"   value="${recordForm.engineeringName }"/>
    	   			<span class="eleRequired">*</span>
    	    </li>
    	     <li>
	     		<label for="" class="short">建设工程规划许可证:</label>
					<input type="text"  class="required" 
						   id="planPermit" name="planPermit"   value="${recordForm.planPermit }"/>
    	   			<span class="eleRequired">*</span>
    	    </li>
			  <li >
	     		<label for="" class="short">招标人证明:</label>
	     		<input type="file" id="tendererProve"  name="tendererProve1" contentEditable="false" >
	     		<input type="hidden" id="isUploadFile1" name="isUploadFile1">
    	   		<span class="eleRequired">*</span>
    	    </li>
    	    	 <li >
	     		<label for="" class="short">原文件下载:</label>
    	   		<span class="eleRequired"><a href="<%=request.getContextPath()%>/AttachmentController.do?method=downLoadFile&objId=${attrId1}" target="_blank" id="a1">${tendererProve }</a></span>
    	    </li>
    	     <li >
		     	<label for="" class="short">立项证明:</label>
		     	<input type="file" id="projApproval"  name="projApproval1" contentEditable="false" >
		     	<input type="hidden" id="isUploadFile2" name="isUploadFile2">
	    	   	<span class="eleRequired">*</span>
    	    </li>
    	    <li >
	     		<label for="" class="short">原文件下载:</label>
    	   		<span class="eleRequired"><a href="<%=request.getContextPath()%>/AttachmentController.do?method=downLoadFile&objId=${attrId2}" target="_blank" id="a2">${projApproval }</a></span>
    	    </li>
    	    <li >
	     		<label for="" class="short">资金落实证明:</label>
	     		<input type="file" id="fundsProof"  name="fundsProof1" contentEditable="false" >
	     			<input type="hidden" id="isUploadFile3" name="isUploadFile3">
    	   		<span class="eleRequired">*</span>
    	    </li>
    	    <li >
	     		<label for="" class="short">原文件下载:</label>
    	   		<span class="eleRequired"><a href="<%=request.getContextPath()%>/AttachmentController.do?method=downLoadFile&objId=${attrId3}" target="_blank" id="a3">${fundsProof }</a></span>
    	    </li>
		</ul>
</form>
</div>
<div class="formLayout form2Pa">
		   <div class="conOperation">
				<button type="button" id="recordFormSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="recordFormReturn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
		   </div>
</div>
<div class="formTips">
      <ul>
          <li><spring:message code="globe.input.required.prompt"/></li>
	  </ul>
</div>