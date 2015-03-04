<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projreview/newOpenbidGeneralviewList.js"></script>
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
			    	<c:forEach items="${genviewDefineList}" var="genviewDefine">
			    	    <c:if test="${genviewDefine.project.objId==pack.objId}"><th>${genviewDefine.factorName}</th></c:if>
			    	</c:forEach>
			    
		   		</tr>
		   		   <c:forEach items="${openBidRecordList}" var="openBidRecord">
		   		   		<c:if test="${openBidRecord.subProjId==pack.objId}">
		   		   		<tr>
		   		   		<td>${openBidRecord.sellerName}</td>
		   		   			<!-- 遍历指标响应值 start -->   
		   		   		   <c:forEach items="${genviewDefineList}" var="genviewDefine">
		   		   		   	   <c:if test="${genviewDefine.project.objId==pack.objId}">
	   		   		   	   	   		<c:forEach items="${bidPackageList}" var="bidPackage">
	   		   		   	   	   		  	<c:if test="${bidPackage.bid.supplier.objId == openBidRecord.supplier.objId && bidPackage.pack.objId == pack.objId}">
		   		   		  			   			   <c:forEach items="${packCongFactorResponseList}" var="packCongFactorResponse">
		   		   		  							<c:if test="${packCongFactorResponse.bidPackageId == bidPackage.objId && packCongFactorResponse.congFactorResponse.factorId == genviewDefine.factor.objId }">
		   		   		  					    		<td class="genviewDefinetd" id="${genviewDefine.objId}|${genviewDefine.factor.objId}|${packCongFactorResponse.congFactorResponse.respValue}|${openBidRecord.objId}">${packCongFactorResponse.congFactorResponse.respValue}</td>
		   		   		  							</c:if>
		   		   		  							</c:forEach>
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
	
	<div class="conOperation">
		<button tabindex="18" type="button" id="openBidGeneralVitemSave"><span>保存</span></button>
	</div>	
</div>