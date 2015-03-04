<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/morePurchaseDocRecordList.js"></script>
<input type="hidden" id="param_type" value="${param.fromType}"></input>
<input type="hidden" id="fileType" value="${fileType}">
<input type="hidden" id="useStatus" value="${useStatus}">
<input type="hidden" id="auditStatus" value="${auditStatus}">
<input type="hidden" id="userType" value="${param.userType}">
<!-- 查询条件 -->
<div class="conSearch">
    <h4><span><spring:message code="globe.query"/></span></h4>
    <form id="purchaseDocSearchZone">
	    <ul>
	      <li>
	        <label>项目编号：</label>
			<input name="projCode" type="text" >	
	      </li>
	      <li>
	        <label>招标名称：</label>
		  	<input name="projName" type="text" >	
	      </li>
	      <li class="fullLine">
				<label><spring:message code="projectForm.ebuyMethod"/>：</label>
				<html:select styleClass="required" id="ebuyMethod" name="ebuyMethod" code="ebuyMethod" selectedValue="${ebuyMethod}" >
				<html:option value="">—显示全部—</html:option>
				</html:select>
			</li>
	      <li class="operationBtnDiv right">
	       	<button type="submit" id="query"><span><spring:message code="globe.query"/></span></button>
	      	<input type="hidden" name="auditStatus" id="auditStatus" value="${auditStatus}"/>
	      </li>
	    </ul>
    </form>
</div>
	<table id="purchaseDocGrid">
	
	</table>
