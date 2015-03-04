<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script language="javascript">
//定义文件全局变量 处理方法名重复问题
var InrqDetailList={};
//查询条件过滤
InrqDetailList.before=function(){
	var option={"projName":$('#projName').val(),"projCode":$('#projCode').val(),"ebuyMethod":$('#ebuyMethodType').val()};
	$('#InrqDetailGrid').flexOptions({params:option});
	return true;
}


//加载数据成功之后调用的函数
InrqDetailList.success=function(){
	$("#InrqDetailGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				var json = $("#InrqDetailGrid").flexGetRowJsonById(rowId);
				var projectId = json["project"]["objId"];
				InrqDetailList.view(projectId);
			}).appendTo(obj);
			}
			});
}

InrqDetailList.view=function(projectId){
	$('#conBody').loadPage($('#initPath').val()+'/InrqDetailController.do?method=toViewInrqDetailForSupplier&projectId='+projectId);
}
$(document).ready(function(){

})
</script>
 <style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<!-- 查询条件 -->
<div class="conSearch">
    <h4><span><spring:message code="globe.query"/></span></h4>
    <form id="contractSearchZone">
	    <ul>
	      <li>
	        <label>项目名称：</label>
			<input name="projName" id="projName" type="text" value="">	
	      </li>
	      <li>
	        <label>项目编号：</label>
		  	<input name="projCode" id="projCode"  type="text" value="">	
	      </li>
	      <li>
	        <label>采购方式：</label>
		  	<html:select styleClass="required" id="ebuyMethodType" name="ebuyMethodType" code="ebuyMethod" selectedValue="${param.ebuyMethod}">
						   </html:select>
	      </li>
	      <li class="operationBtnDiv right">
	       	<button type="submit" id="query"><span><spring:message code="globe.query"/></span></button>
	      </li>
	    </ul>
    </form>
</div>

<!-- Tab页 -->
<div id="epsTabs" class="">
  <div id="InrqDetailInfo">
    <flex:flexgrid checkbox="false"
		id="InrqDetailGrid" url="InrqDetailController.do?method=searchInvrqteByQueryObject" queryColumns=""
			searchZone="contractSearchZone" rp="10"  title="邀请函列表" onSuccess="InrqDetailList.success" 
			onSubmit="InrqDetailList.before">
			<flex:flexCol name="projName" display="项目名称" sortable="true" width="200" align="left"></flex:flexCol>
			<flex:flexCol name="projCode" display="项目编号" sortable="true" width="200" align="left"></flex:flexCol>
			<flex:flexCol name="auditStatus" alias="auditStatusCN" display="审核状态" sortable="true" width="150" align="center"></flex:flexCol>
			<flex:flexCol name="inrqDKind" alias="inrqDKindCN" display="邀请函种类" sortable="true" width="150" align="center"></flex:flexCol>
	</flex:flexgrid>
  </div>
</div>