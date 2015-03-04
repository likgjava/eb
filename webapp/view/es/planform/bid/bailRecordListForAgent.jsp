<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bid/bailRecordListForAgent.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<div id="bailRecordList">
<c:set value="0" var="subProjectCount"/>
	<c:forEach items="${projectList}" var="project" varStatus="i">
	<table class="tableList" id="SubProjectList">
		<caption><b>[${project.projCode}]${project.projName}</b>
		<a href="#"><span onclick="bailRecordList.addBailRecord('${project.objId}');"	 title="新增"  class="sysicon siAdd">新增&nbsp;&nbsp;&nbsp;</span></a>
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
				<%--
				<a href="#"><span class="sysicon siModify" onclick="dosBuyRecordList.updateDosBuyRecord('${dosBuyRecord.objId}');" title="修改">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span></a>
				--%>
				<c:choose>
					<c:when test="${bailRecord.createUser.objId==userId}">
						<a href="#"><span class="sysicon siDelete" onclick="bailRecordList.deleteBailRecord('${bailRecord.objId}');" title="删除">删除&nbsp;&nbsp;&nbsp;</span></a>
					</c:when>
					<c:otherwise>
						<a href="#"><span class="sysicon siAccept" onclick="bailRecordList.confirmBailRecord('${bailRecord.objId}');" title="确认">确认&nbsp;&nbsp;&nbsp;</span></a>
					</c:otherwise>
				</c:choose>
			</td>
			</tr>
		
	</c:forEach>
	</tbody>
    </table>
    </c:forEach>

    <c:if test="${fn:length(bailRecordList)>0}">
    <div class="conOperation">
   		<button id="finshSaveButton" type="button" tabindex="18"><span>完成</span></button>
   		<span><font color="red">(点击完成，方可进入下一步操作！)</font></span>
 	</div>
 	</c:if>
</div>

