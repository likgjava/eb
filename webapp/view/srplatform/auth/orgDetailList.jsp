<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%request.getContextPath(); %>/view/srplatform/auth/orgDetailList.js"></script>  
<!--页面按钮开始-->
<div id="pageMenu" class="pageMenu ui-state-default">
  <a href="javascript:void(0);" id="recheck" class="addicon transfer-2-e-w">反选</a>
  <a href="javascript:void(0);" id="newOrgDetail" class="addicon plus-2">新增</a>
  <a href="javascript:void(0);" id="batchDelOrgDetail" class="addicon close">删除</a>
  <a href="javascript:void(0);" id="search" class="addicon search">查询</a>
  <a href="javascript:void(0);" id="refresh" class="addicon refresh">刷新</a>
  <a href="javascript:void(0);" id="expExcel" class="addicon copy">导出EXCEL</a>
  <a href="javascript:void(0);" id="print" class="addicon print">打印</a>
  <a href="javascript:void(0);" id="help" class="addicon help">帮助</a>
</div>
<!--页面按钮结束-->

<!--页面查询开始-->
<div id="simpleSearch" class="pageSearch hidden">	
<form name="OrgDetailqueryForm" id="OrgDetailqueryForm" action="<c:url value="/OrgDetailController.do"/>" method="post">
	<ul class="highclassSearch">
	  	<li>
							<spring:message code="OrgDetailForm.unitName"/>
							<input type="text" name="unitName" value=""   >
							   <input type="hidden" name="unitName_op" value="like"/>
							<spring:message code="OrgDetailForm.unitType"/>
							<input type="text" name="unitType" value=""   >
		</li>
	  	<li><button id="OrgDetailqueryBuuton"><spring:message code="globe.query"/></button></li>
	</ul>
</form>
</div>
<!--页面查询结束-->

<!--页面内容开始-->
<div id="pageContent">

  <!--页面导航开始-->
  <div id="pageNav">
  </div>
  <!--页面导航结束-->
  <!--操作区开始-->
  <div id="operatingArea">
  	<table id="OrgDetailTable" class="normalTable sortable scrolltable" rowclass=",sortTable_bg">
  	<caption><span></span></caption>
      <thead>
      	<tr>
      	  <th class="sorttable_nosort select"><input type="checkbox" id="checkboxAll"  scope="row"></th>
				     <th><spring:message code="OrgDetailForm.agentType"/></th>
				     <th><spring:message code="OrgDetailForm.authFirstDate"/></th>
				     <th><spring:message code="OrgDetailForm.contPerson"/></th>
				     <th><spring:message code="OrgDetailForm.contTel"/></th>
				     <th><spring:message code="OrgDetailForm.dunsNum"/></th>
				     <th><spring:message code="OrgDetailForm.fax"/></th>
				     <th><spring:message code="OrgDetailForm.historyId"/></th>
				     <th><spring:message code="OrgDetailForm.openAccount"/></th>
				     <th><spring:message code="OrgDetailForm.openBank"/></th>
				     <th><spring:message code="OrgDetailForm.orgCode"/></th>
				     <th><spring:message code="OrgDetailForm.orgCodeFile"/></th>
				     <th><spring:message code="OrgDetailForm.regAddress"/></th>
				     <th><spring:message code="OrgDetailForm.regAuthOrg"/></th>
				     <th><spring:message code="OrgDetailForm.regBusScope"/></th>
				     <th><spring:message code="OrgDetailForm.regCode"/></th>
				     <th><spring:message code="OrgDetailForm.regCoporate"/></th>
				     <th><spring:message code="OrgDetailForm.regDate"/></th>
				     <th><spring:message code="OrgDetailForm.regFile"/></th>
				     <th><spring:message code="OrgDetailForm.regToDate"/></th>
				     <th><spring:message code="OrgDetailForm.unitAddress"/></th>
				     <th><spring:message code="OrgDetailForm.unitBrief"/></th>
				     <th><spring:message code="OrgDetailForm.unitLog"/></th>
				     <th><spring:message code="OrgDetailForm.unitName"/></th>
				     <th><spring:message code="OrgDetailForm.unitScape"/></th>
				     <th><spring:message code="OrgDetailForm.unitType"/></th>
				     <th><spring:message code="OrgDetailForm.webUrl"/></th>
				     <th><spring:message code="OrgDetailForm.orgnization"/></th>
		   <th class="sorttable_nosort"><spring:message code="globe.operate"/></th>
        </tr>
      </thead>
      <tbody id="OrgDetailTbody">  
      </tbody>
      <tfoot>
	      <tr id="OrgDetailTemplate" style="display:none">
	      	  <td class="select"><div id="objId" tabType="checkbox" scope="row"></div></td>
				     <td class="left"><div id="agentType"></div></td>
				     <td class="left"><div id="authFirstDate"></div></td>
				     <td class="left"><div id="contPerson"></div></td>
				     <td class="left"><div id="contTel"></div></td>
				     <td class="left"><div id="dunsNum"></div></td>
				     <td class="left"><div id="fax"></div></td>
				     <td class="left"><div id="historyId"></div></td>
				     <td class="left"><div id="openAccount"></div></td>
				     <td class="left"><div id="openBank"></div></td>
				     <td class="left"><div id="orgCode"></div></td>
				     <td class="left"><div id="orgCodeFile"></div></td>
				     <td class="left"><div id="regAddress"></div></td>
				     <td class="left"><div id="regAuthOrg"></div></td>
				     <td class="left"><div id="regBusScope"></div></td>
				     <td class="left"><div id="regCode"></div></td>
				     <td class="left"><div id="regCoporate"></div></td>
				     <td class="left"><div id="regDate"></div></td>
				     <td class="left"><div id="regFile"></div></td>
				     <td class="left"><div id="regToDate"></div></td>
				     <td class="left"><div id="unitAddress"></div></td>
				     <td class="left"><div id="unitBrief"></div></td>
				     <td class="left"><div id="unitLog"></div></td>
				     <td class="left"><div id="unitName"></div></td>
				     <td class="left"><div id="unitScape"></div></td>
				     <td class="left"><div id="unitType"></div></td>
				     <td class="left"><div id="webUrl"></div></td>
				     <td class="left"><div id="orgnization"></div></td>
	          <td class="option"><div id="update"></div><div id="delete"></div>
	          </td>
	      </tr>
	      <tr><td><div id="OrgDetailPage"></div></td></tr>
      </tfoot>
	</table>
  </div>
  <!--操作区结束-->
</div>
<!--页面内容结束-->

<!--页面提示开始-->
<div class="tableTip">点击工具栏的查询，可展开查询条件！</div>
<!--页面提示结束-->

