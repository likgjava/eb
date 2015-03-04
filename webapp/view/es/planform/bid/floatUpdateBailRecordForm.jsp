<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%> 
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bid/floatUpdateBailRecordForm.js"></script>
<input type="hidden" name="SubobjId" id="SubobjId" value="${param.projectId}"/>
<div class="formLayout form2Pa">
<input type="hidden" name="signUprecord.objId" id="signUprecordId" value="${signUprecordId}"/>
	<form id="bailRecordForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="objId" id="objId" value="${objId}"/>
     	<h5><span><dm:out  value="local__BAILRECORD" tenderType="${project.tenderType}">保证金</dm:out>记录修改</span></h5>
     	<ul>
	     	<li>
	     		<label  class="short" for="bailRecordForm.ballMoney"><dm:out  value="local__BAILRECORD" tenderType="${project.tenderType}">保证金</dm:out>（元）</label>
					<input type="text" name="ballMoney" id="ballMoney" class="required digits" value="${bailRecord.ballMoney }" />
    	   			<span class="eleRequired" id="span1">*</span>
    	    </li>
    	    <li>
    	    	<label  class="short" for="bailRecordForm.bailStatus"><dm:out  value="local__BAILRECORD" tenderType="${project.tenderType}">保证金</dm:out>状态</label>
				<select name="bailStatus" id="bailStatus" class="required">
				<c:choose >
				<c:when test="${bailRecord.bailStatus=='00' }">
				<option value="00">未缴纳</option>
				<option value="01">已缴纳</option>
				<option value="02">已退回</option>
				</c:when>
				<c:when test="${bailRecord.bailStatus=='01' }">
				<option value="01">已缴纳</option>
				<option value="00">未缴纳</option>
				<option value="02">已退回</option>
				</c:when>
				<c:otherwise>
				<option value="02">已退回</option>
				<option value="01">已缴纳</option>
				<option value="00">未缴纳</option>
				</c:otherwise>
				</c:choose>
				 </select>
    	   			<span class="eleRequired">*</span>
    	    </li>
    	    <li>
	     		<label class="short" for="bailRecordForm.renderTime">缴纳时间</label>
					<input type="text" name="renderTime" id="renderTime" readonly="readonly" 
					value="<fmt:formatDate value="${bailRecord.renderTime }" pattern="yyyy-MM-dd"/>"  />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label  class="short" for="bailRecordForm.returnedTime">退回时间</label>
	     			<input type="hidden" name="returnedTime" id="returnedTime" />
					<input type="text" name="returnedTime1" id="returnedTime1" readonly="readonly" 
					value="<fmt:formatDate value="${bailRecord.returnedTime }" pattern="yyyy-MM-dd"/>"/>
    	    </li>
    	    <li>
	     		<label class="short" for="bailRecordForm.renderAtt">缴纳证明文件</label>
					<input type="hidden" name="renderAtt" id="renderAtt"  value="${bailRecord.renderAtt }"/>
					<input type="hidden" name="isUploadFile1" id="isUploadFile1" value=""/>	      
					<input type="file" name="attachFile1" id="attachFile1"   size="9"/>
    	    </li>
    	    <li>
    	    <label class="short" for="bailRecordForm.renderAtt">原文件下载</label>
    	      <c:choose>
    	    <c:when test="${attrName1==null}"><span >暂无信息</span></c:when>
    	    <c:otherwise><span ><a href="<%=request.getContextPath()%>/AttachmentController.do?method=downLoadFile&objId=${attrId1}">${attrName1}</a></span></c:otherwise>
    	     </c:choose>
    	    </li>
	     	<li>
	     		<label  class="short" for="bailRecordForm.returnedAtt">退回证明文件</label>
					<input type="hidden" name="returnedAtt" id="returnedAtt" value="${bailRecord.returnedAtt }"/>
					<input type="hidden" name="isUploadFile2" id="isUploadFile2" 
						      value=""/>	
					<input type="file" name="attachFile2" id="attachFile2" size="9"/>	
    	    </li>
    	     <li>
    	    <label class="short" for="bailRecordForm.renderAtt">原文件下载</label>
    	    <c:choose>
    	    <c:when test="${attrName2==null}"><span >暂无信息</span></c:when>
    	    <c:otherwise><span ><a href="<%=request.getContextPath()%>/AttachmentController.do?method=downLoadFile&objId=${attrId2}">${attrName2}</a></span></c:otherwise>
    	    </c:choose>
    	    </li>
		</ul>
		   <div class="conOperation">
				<button type="button" id="bailRecordSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" tabindex="19" id="bailRecordReturn"><span><spring:message code="globe.return"/></span></button>
		   </div>
	</form>
	 <div id="downLoadView"></div>
</div>