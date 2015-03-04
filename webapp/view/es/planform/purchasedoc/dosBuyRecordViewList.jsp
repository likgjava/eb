<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/dosBuyRecordListForTenderfee.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<div id="dosBuyRecordList">
    <input type="hidden" id="isSubProject" name="isSubProject" value="${isSubProject}">
	<c:forEach items="${projectList}" var="project" varStatus="i">
	<table class="tableList" id="SubProjectList">
		<caption>[${project.projCode}]${project.projName}
		</caption>
  		<thead>
      		<tr class="center">
      			<th>投标单位</th>
      			<th>标书费</th>
      			<th>缴纳方式</th>
      			<th>联系人</th>
      			<th>联系电话</th>
          		<th>状态</th>
          		<th>操作</th>
     		</tr>
		</thead>
	<tbody>
	<c:forEach items="${project.list}" var="dosBuyRecord" varStatus="i">
		<tr>
			<td>${dosBuyRecord.supplierName}</td>
			<input type="hidden" value="${dosBuyRecord.supplierId}" id="supplyerIds" name="${project.objId}"/>
			<td align="right"><fmt:formatNumber value="${dosBuyRecord.amount}" type="currency"/></td>
			<td align="center"">${dosBuyRecord.payTypeCN}</td>
			<td>${dosBuyRecord.linker}</td>
			<td>${dosBuyRecord.linkerTel}</td>
			<td class="center">${dosBuyRecord.useStatusCN}</td>
			<td class="center">
				<a href="#"><span class="sysicon siAccept" onclick="dosBuyRecordList.viewDosBuyRecord('${dosBuyRecord.objId}');" title="查看">查看&nbsp;&nbsp;&nbsp;</span></a>
			</td>
			</tr>
		
	</c:forEach>
	</tbody>
    </table>
    </c:forEach>
</div>

