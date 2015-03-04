<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projreview/OpenbidGeneralviewList.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<input type="hidden" id="_project_id" value="${projectId}"></input>
<div id="openbidGeneralList">
	<ul>
		<c:forEach items="${packList}" var="pack">
			<li><a href="#${pack.objId}" class="refreshData"><span>${pack.projName}</span></a></li>
		</c:forEach>
	</ul>
	<c:forEach items="${packList}" var="pack">
		<div id="${pack.objId}">
			<table class="tableList">
				<tr>
			    	<th>投标单位名称</th>
			    	<th>报价总金额（元）</th>
			    	<th>操作</th>
		   		</tr>
				<c:forEach items="${openbidGeneralviewList}" var="openBidGeneralview">
					<c:if test="${pack.objId == openBidGeneralview.subProj.objId && null != pack.objId}">
	   					<tr>
	   						<form id="${openBidGeneralview.openBidRecord.objId}">
	   							<input type="hidden" name="objId" value="${openBidGeneralview.objId}"></input>
	   							<input type="hidden" name="bid.objId" value="${openBidGeneralview.bid.objId}"></input>
	   							<input type="hidden" name="openBidRecord.objId" value="${openBidGeneralview.openBidRecord.objId}"></input>
	   							<input type="hidden" name="bidQuotesum" value="${openBidGeneralview.bidQuotesum}"></input>
	   							<input type="hidden" name="supplierId" value="${openBidGeneralview.supplierId}"></input>
	   							<input type="hidden" name="supplierName" value="${openBidGeneralview.supplierName}"></input>
	   							<input type="hidden" name="subProj.objId" value="${openBidGeneralview.subProj.objId}"></input>
	   						</form>
	   						<td style="text-align: left">${openBidGeneralview.supplierName}</td>
	   						<td style="text-align: right" type="bidQuotesum">${openBidGeneralview.bidQuotesum}</td>
	    					<td style="text-align: center"><a class="abtn" openBidRecordId="${openBidGeneralview.openBidRecord.objId}" identifier="openbid"><span>修改</span></a></td>
	   					</tr>
					</c:if>
				</c:forEach>
			</table>
		</div>
	</c:forEach>	
</div>