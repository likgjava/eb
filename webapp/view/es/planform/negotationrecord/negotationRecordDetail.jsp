<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/negotationrecord/negotationRecordDetail.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<input type="hidden" name="tenderId" value="${param.projectId}">
<input type="hidden" name="subProject.objId" value="${param.subProjectId}">
<div class="partContainers">
	<div class="formLayout form2Pa">        
	<form id="negotationRecordForm" method="post">
		<input type="hidden" name="supplierName">
		<h5 align="center">谈判记录</h5>
    	<ul>
			<li>
				  <label class="short">投标单位名称：</label>
				  <span>${negotationRecord.supplierName}</span>
			</li>
			<li>
				<label class="short" for="recordTotal">报价总金额（元）：</label>
				<span><fmt:formatNumber value="${negotationRecord.recordTotal}" pattern="￥#,#00.00#"/></span>
			</li>
			<li><label class="short">报价文件：</label>
				<div id="negRecordFile" class="uploadFile" >${negotationRecord.negRecordFile}</div>
 			</li>
 			<li><label class="short" for="recordTime">报价时间：</label>
				<span>${fn:substring(negotationRecord.recordTime,0,16)}</span>
 			</li>
 			<li class="formTextarea" style="height:50px;">
				<label class="short" for="paymentClause">报价备注：</label>
				<span id="recordMemo" >${negotationRecord.recordMemo}</span>
			</li>	
		</ul>
	</form>
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
		      <td class="name"><a href="#" class="abtn" onclick="negotationRecordDetail.showPreqEntryDetail('${negotationRecordItem.preqEntry.taskPlanSub.objId}')">${negotationRecordItem.preqEntry.purchaseName}</a></td>
		      <td class="name">${negotationRecordItem.negRecItemTotal}</td>
		      <td class="formTextarea"><textarea name="recordMemo" style="height:40px;width:400px" readonly="readonly" id="recordMemo" maxlength="200" class="maxInput">${negotationRecordItem.negRecItemMemo}</textarea></td>
		    </tr>
		   	</c:forEach>
		  </tbody>
		</table>
		   
			<div class="conOperation">
				<button id="closeBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
			</div>
		
	</form>
	</div>
</div>