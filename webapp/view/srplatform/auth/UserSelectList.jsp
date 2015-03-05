<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%> 
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/UserSelectList.js"></script>
<!--页面内容开始-->
<input type="hidden" id="_property" value="${param.property}" />
<input type="hidden" id="_isCheckBox"  value="<c:out value="${param.isCheckBox}"/>"/>
<input type="hidden" id="_checkValues"  value="<c:out value="${param.checkValues}"/>"/>
<input type="hidden" id="_param"  value="<c:out value="${param.params}"/>"/>
<input type="hidden" id="_ID"  value="<c:out value="${param.IDS}"/>"/>
<input type="hidden" id="_NAME"  value="<c:out value="${param.NAMES}"/>"/>

<!--页面查询开始-->
<div id="simpleSearch" class="pageSearch" >
    <form name="UserqueryForm" id="UserqueryForm" action="<c:url value="/UserController.do"/>" method="post">
    <fieldset>
    <legend>查询条件</legend>

	<ul class="highclassSearch">
	    <li>
		    <spring:message code="UserForm.usName" />：
		    <input type="text" name="usName" value="">
		    <input type="hidden" name="usName_op" value="like">
		    <spring:message code="UserForm.usrCnName" />：
		    <input type="text" name="usrCnName" value="">
		    <input type="hidden" name="usrCnName_op" value="like">
		    <button id="UserqueryBuuton" class="btn"><span><spring:message code="globe.query"/></span></button>
		</li>
	</ul>

</fieldset></form>
</div>
<!--页面查询结束-->

<!--操作区开始-->
<table id="UserSelectTable" class="normalTable sortable scrolltable hidden" rowclass=",sortTable_bg">
  	<caption><span>用户信息</span></caption>
      <thead>
		<tr>
			<th><spring:message code="UserForm.usrCnName" /></th>
			<th><spring:message code="UserForm.usName" /></th>
		</tr>
	   </thead>
      <tbody id="UserSelectTbody">
	  </tbody>
	  <tfoot>
		<tr id="UserSelectTemplate" style="display: none">
    		<td class="left"><div id="usrCnName"></div></td>
    		<td class="left"><div id="usName"></div></td>
		</tr>
		<tr>
			<td colspan="2"><div id="UserSelectPage"></div></td>
		</tr>
	  </tfoot>
</table>

<table id="UserCheckTable" class="normalTable sortable scrolltable hidden" rowclass=",sortTable_bg">
  	<caption><span>用户信息</span></caption>
      <thead>
		<tr>
		    <th class="sorttable_nosort select"><input type="checkbox" id="checkboxAll"></th>
			<th><spring:message code="UserForm.usrCnName" /></th>
			<th><spring:message code="UserForm.usName" /></th>
		</tr>
	   </thead>
      <tbody id="UserCheckTbody">
	  </tbody>
	  <tfoot>
		<tr id="UserCheckTemplate" style="display: none">
		    <td class="select"><div id="objId" tabType="checkbox" scope="row"></div></td>
    		<td class="left"><div id="usrCnName"></div></td>
    		<td class="left"><div id="usName"></div></td>
		</tr>
		<tr>
			<td colspan="3"><div id="UserCheckPage"></div></td>
		</tr>
	  </tfoot>
</table>
<!--操作区结束-->

<div class="buttonClass">
	<button  id="_clear"><span>清空</span></button>
	<button class="btn hidden" id="_OK"><span>确定</span></button>
</div>