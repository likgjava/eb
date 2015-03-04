<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projreview/putInOpenbidGeneralview.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<input type="hidden" id="isOpenBid" value="${isOpenBid}"></input>
<input type="hidden" id="vitemSize" value="${vitemSize}"></input>
<input type="hidden" id="_project_id" value="${projectId}"></input>
<input type="hidden" id="currentTabId" value=""></input>
<div id="openbidGeneralList">
	<ul>
		<c:forEach items="${packList}" var="pack">
			<li><a href="#${pack.objId}"  class="refreshData" ids="${pack.objId}"><span>${pack.projName}</span></a></li>
		</c:forEach>
	</ul>
	
	<c:forEach items="${packList}" var="pack">
		<div id="${pack.objId}">
			<table class="tableList">
				<tr>
			    	<th>投标单位名称</th>
			    	<c:forEach items="${genviewDefineList}" var="genviewDefine">
			    	    <c:if test="${genviewDefine.project.objId==pack.objId}"><th>${genviewDefine.factorName}</th></c:if>
			    	</c:forEach>
		   		</tr>
		   		   <c:forEach items="${openBidRecordList}" var="openBidRecord">
		   		   		<c:if test="${openBidRecord.subProjId==pack.objId}">
		   		   		<tr>
		   		   		<td nowrap="nowrap" >${openBidRecord.sellerName}</td>
		   		   			<!-- 遍历指标响应值 start -->   
		   		   		   <c:forEach items="${genviewDefineList}" var="genviewDefine">
		   		   		   	   <c:if test="${genviewDefine.project.objId==pack.objId}">
	   		   		   	   	   		<c:forEach items="${bidPackageList}" var="bidPackage">
	   		   		   	   	   		  	<c:if test="${bidPackage.bid.supplier.objId == openBidRecord.supplier.objId && bidPackage.pack.objId == pack.objId}">
		   		   		  			   		<td class="genviewDefinetd" id="${genviewDefine.objId}|${genviewDefine.factorId}|${pack.objId}|${openBidRecord.supplier.objId}|">
		   		   		  			   		<c:choose>
		   		   		  			   		<c:when test="${vitemSize == 'isOK'}">
		   		   		  			   		  <c:forEach items="${openBidGeneralVitemList}" var="openBidGeneralVitem">
		   		   		  			   		  <c:if test="${openBidGeneralVitem.supplierId == bidPackage.bid.supplier.objId && openBidGeneralVitem.genviewDefine.objId == genviewDefine.objId}">
		   		   		  			   		  <span>${openBidGeneralVitem.respValue}</span>
		   		   		  			   		  </c:if>
		   		   		  			   		</c:forEach>
		   		   		  			   		</c:when>
		   		   		  			   		<c:otherwise></c:otherwise>
		   		   		  			   		</c:choose>
		   		   		  			   		</td>
		   		   						</c:if>
	   		   		   	   	   		</c:forEach>
		   		   		   	   </c:if>
		   		   		   </c:forEach>
		   		   		   <!-- 遍历指标响应值 end -->
		   		   		</tr>
		   		   		</c:if>
		   		   </c:forEach>
			</table>
		</div>
	</c:forEach>
	<div align="center" id="message"></div>
</div>