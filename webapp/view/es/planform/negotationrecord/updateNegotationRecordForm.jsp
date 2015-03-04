<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/negotationrecord/updateNegotationRecordForm.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<div class="partContainers">
	<div class="formLayout form2Pa">        
		<h5 align="center">谈判记录</h5>
    	<ul>
	<form id="negotationRecordForm" method="post">
		<input type="hidden" name="tenderId" value="${param.projectId}">
		<input type="hidden" name="subProject.objId" value="${param.subProjectId}">
		<input type="hidden" name="objId" value="${negotationRecord.objId}">
			<li>
				   <label class="short">投标单位名称：</label>
				   <select name="supplier.objId" id="supplierId" class="required" disabled="disabled">
				        <c:forEach items="${singUpList}" var="singUp" >	
				        <option value="${singUp.supplier.objId}" <c:if test="${singUp.supplier.objId==negotationRecord.supplier.objId}">selected="selected"</c:if>>${singUp.supplier.orgName}</option>
				        </c:forEach>
				    </select>
				        <span class='eleRequired'>*</span><span class="eleWarning"></span>
			</li>
			<li>
				<label class="short" for="recordTotal">报价总金额（元）：</label>
				<input type="text" name="recordTotal" id="recordTotal" value="${negotationRecord.recordTotal}" class="required money"  disabled="disabled"/>
					  <span class="eleRequired">*</span>
			</li>
 			<li class="formTextarea" style="height:50px;">
				<label class="short" for="paymentClause">报价备注：</label>
				<textarea name="recordMemo" style="height:40px;width:535px;" id="recordMemo" maxlength="200" class="required">${negotationRecord.recordMemo}</textarea>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine"><label  class="short">报价时间：</label>
				 <input type="text" name="recordTime" id="recordTime" class="required" style="width: 150px" readonly="readonly" value="<fmt:formatDate value="${negotationRecord.recordTime}" pattern="yyyy-MM-dd HH:mm:SS"/>"/>
				 <span class="eleRequired">*</span>
 			</li>		
		</form>
			<li class="fullLine"><label  class="short">报价文件：</label>
				<div id="attachRelaId" class="uploadFile" >${negotationRecord.negRecordFile}</div>
 			</li>
		</ul>
	</div>
	<div class="formLayout" style="margin-top: -11px;">
	<form id="negotationRecordItemForm" method="post" enctype="multipart/form-data">
		 <table class="tableList">
		  <caption>谈判记录明细</caption>
		  <thead>
		    <tr>
		      <th class="name" >需求条目</th>
		      <th class="name" >报价金额（元）</th>
		      <th class="name" >备注</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach items ="${NegotationRecordItemList}" var="negotationRecordItem" varStatus="i">
		   	<tr>
		      <td class="name"><a href="#" class="abtn" onclick="updateNegotationRecordForm.showPreqEntryDetail('${negotationRecordItem.preqEntry.taskPlanSub.objId}')">${negotationRecordItem.preqEntry.purchaseName}</a><input type="hidden" name="negotationRecordItem[${i.count}].objId" value="${negotationRecordItem.objId}"><input type="hidden" name="negotationRecordItem[${i.count}].preqEntry.objId" value="${negotationRecordItem.preqEntry.objId}"></td>
		      <td class="name"><input type="text" class="required money"  onblur="updateNegotationRecordForm.sumMoney();" name="negotationRecordItem[${i.count}].negRecItemTotal" value="${negotationRecordItem.negRecItemTotal}" nam='nam'><span class="eleRequired">*</span></td>
		      <td class="name"><textarea maxlength="200" class="required" style="height:40px;width:400px" name="negotationRecordItem[${i.count}].negRecItemMemo">${negotationRecordItem.negRecItemMemo}</textarea><span class="eleRequired">*</span></td>
		    </tr>
		   	</c:forEach>
		  </tbody>
		</table>
		   
			<div class="conOperation">
				<button id="taskPlanSubSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
				<button id="closeBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
			</div>
		
	</form>
	</div>
</div>