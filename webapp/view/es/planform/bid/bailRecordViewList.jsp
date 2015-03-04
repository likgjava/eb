<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bid/bailRecordViewList.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<div id="bailRecordList">
	<c:forEach items="${projectList}" var="project" varStatus="i">
	<table class="tableList" id="SubProjectList">
		<caption>[${project.projCode}]${project.projName}
		</caption>
  		<thead>
      		<tr class="center">
      			<th>投标单位</th>
      			<th>保证金（元）</th>
      			<th>缴纳方式</th>
      			<th>联系人</th>
      			<th>联系电话</th>
          		<th>状态</th>
          		<th>操作</th>
     		</tr>
		</thead>
	<tbody>
	<c:forEach items="${project.list}" var="bailRecord" varStatus="i">
		<tr>
			<td>${bailRecord.supplyerName}</td>
			<input type="hidden" value="${bailRecord.supplyerId}" id="supplyerIds" name="${project.objId}"/>
			<td align="right"><fmt:formatNumber value="${bailRecord.ballMoney}" type="currency"/></td>
			<td align="center">${bailRecord.payTypeCN}</td>
			<td>${bailRecord.linker}</td>
			<td align="center">${bailRecord.linkerTel}</td>
			<td class="center">${bailRecord.useStatusCN}</td>
			<td class="center">
				<a href="#"><span class="sysicon siAccept" onclick="bailRecordList.viewBailRecord('${bailRecord.objId}');" title="查看">查看&nbsp;&nbsp;&nbsp;</span></a>
			</td>
			</tr>
		
	</c:forEach>
	</tbody>
    </table>
    </c:forEach>
</div>

