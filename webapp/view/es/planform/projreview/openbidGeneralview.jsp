<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projreview/openbidGeneralview.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<form id="openbidGeneralviewForm" method="post" enctype="multipart/form-data">
<input type="hidden" id="_project_id" value="${projectId}"></input>
<input type="hidden" id="n"></input>
<input type="hidden" id="num" value="0"></input>
<input type="hidden" id="thisTime" value="${thisTime}"></input>
<input type="hidden" id="openBidSDate" value="${rule.openBidSDate}"></input>
<div id="openbidGeneralList">
	<ul>
		<c:forEach items="${packList}" var="pack">
			<li><a href="#${pack.objId}" class="refreshData"><span>${pack.projName}</span></a></li>
		</c:forEach>
	</ul>
	
	<c:forEach items="${packList}" var="pack">
		<div id="${pack.objId}">
		    <ul>
			<li><a onClick="openbidGeneralview.insertRow('${pack.objId}');" class="linkButton"><span>新增</span></a></li>
			</ul>
			<table class="tableList">
				<tr>
			    	<th width='15%'>投标单位名称</th>
			    	<th width='5%'>投标报价</th>
			    	<c:forEach items="${genviewDefineList}" var="genviewDefine">
			    	    <c:if test="${genviewDefine.project.objId==pack.objId}"><th width='5%' id="${genviewDefine.objId}|${genviewDefine.factor.objId}|${genviewDefine.factorName}">${genviewDefine.factorName}</th></c:if>
			    	</c:forEach>
			        <th width='15%'>投标文件</th>
			        <th width='10%'>操作</th>
		   		</tr>
		   		   <tbody id="openbidGeneralview_${pack.objId}">
		   		  </tbody>  
			</table>
		</div>
	</c:forEach>
	<div id="message" align="center"></div>
	<div class="conOperation">
		<button tabindex="18" type="button" id="openBidGeneralVitemSave"><span>保存</span></button>
	</div>	
</div>
</form>