<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/blockTradeListForCreate.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<form id="taskPlanSearchZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
			<li>
				<label>
					<spring:message code="taskPlanForm.taskCode" />：
				</label>
		    	<input type="text" name="taskCode" value="">
				<input type="hidden" name="taskCode_op" value="like">
			</li>
			<li>
				<label>
					<spring:message code="taskPlanForm.applyDate" />：
				</label>
		    	<input class="sysicon siDate" id="applyDate" name="applyDate">
			</li>
			<li class="operationBtnDiv">
				<button type="submit"><span><spring:message code="globe.query"/></span></button>
			</li>
		</ul>
	</div>
</form>
<div id ="blockTradeListView">
<div id="epsTabs">
  <ul>
  	<li id="li_new">
  	     <a href="#taskPlanAndProjectView" id = "tabs_new" class="refreshData"><span>创建委托</span></a>
    </li>
  </ul>
  <div id="taskPlanAndProjectView"></div>
  </div>
  


</div>