<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bulletin/moreForBulletinList.js"></script>
 <style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<!-- 查询条件 -->
<div class="conSearch">
    <h4><span><spring:message code="globe.query"/></span></h4>
    <form id="contractSearchZone">
	    <ul>
	      <li>
	        <label><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out>编号：</label>
			<input name="bulletinNo" type="text" >	
			<input type="hidden" name="bulletinNo_op" value="like">
	      </li>
	      <li>
	        <label><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out>标题：</label>
		  	<input name="bullTitle" type="text" >	
		  	<input name="bullTitle_op" type="hidden" value="like">		
	      </li>
	      <li class="operationBtnDiv right">
	       	<button type="submit" id="query"><span><spring:message code="globe.query"/></span></button>
	      	<input type="hidden" name="auditStatus" id="auditStatus" value="${auditStatus}" />
	      	<input type="hidden" name="bullType" id="bullType" value="${bullType}" />
	      </li>
	    </ul>
    </form>
</div>

<!-- Tab页 -->
<div id="epsTabs" class="">
 <table id="BulletinGrid"></table>
</div>