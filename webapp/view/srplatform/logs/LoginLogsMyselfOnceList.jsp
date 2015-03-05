<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>  
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/logs/LoginLogsMyselfOnceList.js"></script>
<input type="hidden" id="loginLogsId" value="<c:out value="${param.loginLogsId}"/>" >
<!--页面内容开始-->
<div id="pageContent"> 
  	<!--操作区开始-->
  	<div id="operatingArea">
		<table id="OperLogsTable" class="normalTable sortable scrolltable" rowclass=",sortTable_bg">
			<caption><span>操作日志信息</span></caption>
      		<thead>
      			<tr>
 		          	<th><spring:message code="OperLogsForm.orgname"/></th>     	  			
		          	<th><spring:message code="UserForm.usrCnName"/></th>
		          	<th><spring:message code="OperLogsForm.logVisitDate"/></th>
		          	<th><spring:message code="OperLogsForm.logUsrIP"/></th>
		          	<th><spring:message code="OperLogsForm.classesName"/></th>
		          	<th><spring:message code="OperLogsForm.methodName"/></th>
				</tr>
      		</thead>
      		<tbody id="OperLogsTbody">  
      		</tbody>
      		<tfoot>
      			<tr id="OperLogsTemplate" style="display: none">
           			<td class="left"><div id="orgname"></div></td>     	  			
          			<td class="left"><div id="user.usrCnName"></div></td>
          			<td class="left"><div id="logVisitDate"></div></td>
          			<td class="left"><div id="logUsrIP"></div></td>
          			<td class="left"><div id="classesName"></div></td>
          			<td class="left"><div id="methodName"></div></td>
      			</tr>
      			<tr><td colspan="6"><div id="OperLogsPage"></div></td></tr>
      		</tfoot>
		</table>
	</div>
</div>
<!--页面内容结束-->