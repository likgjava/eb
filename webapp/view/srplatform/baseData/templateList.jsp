<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%> 
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/baseData/templateList.js"></script>  

<div class="tableWrap">
<!--页面按钮开始-->

<!--页面查询开始-->
<div id="simpleSearch" class="pageSearch hidden" >
    <form name="TemplatequeryForm" id="TemplatequeryForm" action="<c:url value="/TemplateController.do"/>" method="post">
    <fieldset>
    <legend>查询条件</legend>

	<ul class="highclassSearch">
	  	<li><label for="siteSearch"><spring:message code="templateForm.name"/>:</label>
	  	<input type="text" name="name" id="name">
		<input type="hidden" name="name_op" value="like">
		<label for="siteSearch"><spring:message code="templateForm.type"/>:</label>
		<select id="typeId" name="type.objId"></select>
	  	<button id="TemplatequeryBuuton" class="btn"><span><spring:message code="globe.query"/></span></button></li>
	</ul>

</fieldset></form>
</div>
<!--页面查询结束-->

 <div class="pageOption">
  <a href="javascript:void(0);" id="recheck" class="btn"><span>反选</span></a>
  <a href="javascript:void(0);" id="newTemplate" class="btn"><span>新增</span></a>
  <a href="javascript:void(0);" id="batchDelTemplate" class="btn"><span>删除</span></a>
  <a href="javascript:void(0);" id="search" class="btn"><span>查询</span></a>
  
 </div>


  	<table id="TemplateTable" class="normalTable sortable scrolltable" rowclass=",sortTable_bg">
  	<caption><span>数据字典信息</span></caption>
      <thead>
      	<tr>
      	  <th class="sorttable_nosort select"><input type="checkbox" id="checkboxAll"  scope="row"></th>
          <th><spring:message code="templateForm.name"/></th>
          <th><spring:message code="templateForm.type"/></th>
          <th class="sorttable_nosort"><spring:message code="globe.operate"/></th>
          </tr>
      </thead>
      <tbody id="TemplateTbody">  
      </tbody>
      <tfoot>
	      <tr id="TemplateTemplate" style="display:none">
	      	  <td class="select"><div id="objId" tabType="checkbox" scope="row"></div></td>
	          <td class="left"><div id="name"></div></td>
	          <td class="left"><div id="type.dicName"></div></td>
	          <td class="option"><div id="update"></div><div id="delete"></div>
	          </td>
	      </tr>
	      <tr><td colspan="4"><div id="TemplatePage"></div></td></tr>
      </tfoot>
	</table>


<!--页面提示开始-->
<div class="tableTip">该模块主要用于配置系统的模版信息，点击工具栏中的<b>查询</b>，可以展开查询条件。</div>
</div>
<!--页面提示结束-->