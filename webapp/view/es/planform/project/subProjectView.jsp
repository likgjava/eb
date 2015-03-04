<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="formLayout form2Pa">
	<h5><span><dm:out value="local__package" tenderType="${param.tenderType}">包组</dm:out>信息</span></h5>
		<ul>
  			<li class="fullLine">
     			<label for="projCode"><dm:out value="local__package" tenderType="${param.tenderType}">包组</dm:out>编号：</label>
     			<span>${project.projCode}</span>
   			</li>
   			<li class="fullLine">
   				<label for="projName"><dm:out value="local__package" tenderType="${param.tenderType}">包组</dm:out>名称：</label>
   				<span>${project.projName}</span>
   			</li> 
   			<!--
   			<li class="fullLine">
   				<label for="budgetTotalMoney"><dm:out value="local__package" tenderType="${param.tenderType}">包组</dm:out>金额：</label>
   				<span>${project.budgetTotalMoney}</span>
   			</li>
   			  -->
   			<li class="fullLine">
   				<label for="purCategoryNames"><dm:out value="local__package" tenderType="${param.tenderType}">包组</dm:out>品目：</label>
   				<span>${project.purCategoryNames}</span>
   			</li> 
			</ul>
   			<div class="conOperation">
				<button id="subProjectReturn" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
 			</div>
</div>
<div class="hidden">
	<table class="tableList" id="SubProjectList">
		<caption>采购申报条目</caption>
  		<thead>
      		<tr class="center">
          		<th>品目编号</th>
          		<th>品目名称</th>
          		<th>预算（元）</th>
     		</tr>
		</thead>
	<tbody>
		<c:forEach items="${projectMTaskPlanList}" var="subProjectMTaskPlanSub" varStatus="i">
		<tr>
			<td>${subProjectMTaskPlanSub.taskPlanSub.purchase.categoryCode}</td>
			<td>${subProjectMTaskPlanSub.taskPlanSub.purchase.categoryName}</td>
			<td>${subProjectMTaskPlanSub.money}</td>
		</tr>	
	</c:forEach>
	</tbody>
    </table>
</div>
<script language="javascript">
//关闭
$("#subProjectReturn").click(function(){
	$('#epsDialogCloseNoReload').click();
});
</script>