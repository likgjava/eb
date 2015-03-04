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
		<caption><b>[${project.projCode}]${project.projName}</b>
		<a href="#"><span onclick="dosBuyRecordList.addDosBuyRecord('${project.objId}');"	 title="新增"  class="sysicon siAdd">新增&nbsp;&nbsp;&nbsp;</span></a>
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
				<%--
				<a href="#"><span class="sysicon siModify" onclick="dosBuyRecordList.updateDosBuyRecord('${dosBuyRecord.objId}');" title="修改">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span></a>
				--%>
				<c:choose>
					<c:when test="${dosBuyRecord.createUser.objId==userId}">
						<a href="#"><span class="sysicon siDelete" onclick="dosBuyRecordList.deleteDosBuyRecord('${dosBuyRecord.objId}');" title="删除">删除&nbsp;&nbsp;&nbsp;</span></a>
					</c:when>
					<c:otherwise>
						<a href="#"><span class="sysicon siAccept" onclick="dosBuyRecordList.confirmDosBuyRecord('${dosBuyRecord.objId}');" title="确认">确认&nbsp;&nbsp;&nbsp;</span></a>
					</c:otherwise>
				</c:choose>
			</td>
			</tr>
		
	</c:forEach>
	</tbody>
    </table>
    </c:forEach>
    <div class="conOperation">
   		<button id="finshSaveButton" type="button" tabindex="18"><span>完成</span></button>
   		<span><font color="red">(点击完成，方可进入下一步操作！)</font></span>
 	</div>
</div>

