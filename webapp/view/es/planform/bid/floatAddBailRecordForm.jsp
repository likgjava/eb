<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%> 
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bid/floatAddBailRecordForm.js"></script>
<input type="hidden" name="SubobjId" id="SubobjId" value="${param.projectId}"/>
<div class="formLayout form2Pa">
	<input type="hidden" name="signUprecord.objId" id="signUprecordId" value="${signUprecordId}"/>
	<form id="bailRecordForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="signUprecord.objId" id="signUprecord.objId" value="<c:out value="${param.signUprecordId}"/>"/>
     	<h4><span><dm:out  value="local__BAILRECORD" tenderType="${project.tenderType}">保证金</dm:out>记录新增</span></h4>
     	<ul>
	     	<li>
		   		<label class="short" for="bailRecordForm.ballMoney"><dm:out  value="local__BAILRECORD" tenderType="${project.tenderType}">保证金</dm:out>（元）</label>
				<input type="text" name="ballMoney" id="ballMoney" class="required digits" value="${bailRecord.ballMoney}" /><span class="eleRequired">*</span>   	   			
	    	</li>
    	  	<li>
    	    	<label class="short" for="bailRecordForm.bailStatus"><dm:out  value="local__BAILRECORD" tenderType="${project.tenderType}">保证金</dm:out>状态</label>
				<select name="bailStatus" id="bailStatus" class="required">
					<option value="00">未缴纳</option>
					<option value="01">已缴纳</option>
					<option value="02">已退回</option>
				</select>
    	   		<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label class="short" for="bailRecordForm.renderTime">缴纳时间</label>
				<input type="text" name="renderTime"  id="renderTime" class="required" readonly="readonly"/>
				<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label class="short" for="bailRecordForm.returnedTime">退回时间</label>
	     		<input type="hidden" name="returnedTime" id="returnedTime" />
				<input type="text" name="returnedTime1"  id="returnedTime1" readonly="readonly"/>
    	    </li>
    	    <li class="fullLine">
	     		<label class="short" for="bailRecordForm.renderAtt">缴纳证明</label>
				<input type="hidden" name="renderAtt" id="renderAtt" value=""/>
				<input type="hidden" name="isUploadFile1" id="isUploadFile1" value=""/>	      
				<input type="file" name="attachFile1" id="attachFile1"  size="9"/>
    	    </li>

	     	<li class="fullLine">
	     		<label class="short" for="bailRecordForm.returnedAtt">退回证明</label>
				<input type="hidden" name="returnedAtt" id="returnedAtt" value=""/>
				<input type="hidden" name="isUploadFile2" id="isUploadFile2" value=""/>	
				<input type="file" name="attachFile2" id="attachFile2" size="9"/>
    	    </li>
    	   
		</ul>
		   <div class="conOperation">
				<button type="button" id="bailRecordSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" tabindex="19" id="bailRecordReturn"><span><spring:message code="globe.return"/></span></button>
		   </div>
	</form>
</div>