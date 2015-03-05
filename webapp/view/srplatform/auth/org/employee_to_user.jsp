<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
var employee_to_user={};  //定义文件全局变量 处理方法名重复问题
employee_to_user.oTable;	

employee_to_user.getList = function(){
	if(employee_to_user.oTable){
		
	}else{
		//加载列表
		employee_to_user.oTable=$('#UserList').dataTable( {   
			'singleSelect':true,	
			'checkbox':false,		
			'queryColumns':'name,company.name,mobile',
			'fnDrawCallback' : function(oSettings) {
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				$(nRow).append('<td class="operator"><a href="javascript:void(0);" name="chooseEmployee"><span>选择</span></a></td>').find("a[name=chooseEmployee]").click(function(){
					$("input[id='"+$("#hiddenProperty").val()+"']").val(aData.objId);
					$("input[id='"+$("#showProperty").val()+"']").val(aData.name);
					employee_to_user.close();
				});
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/EmployeeController.do?method=list",
			"searchZone":"roleSearchZone"
		});
	}
}

employee_to_user.close = function(){
	$('#close').closest('.epsDialog').find('.epsDialogClose').trigger('click');
}

$(document).ready(function(){
	employee_to_user.getList();
});
</script>
<input type="hidden" name="hiddenProperty" id="hiddenProperty" value="<c:out value="${param.hiddenProperty}"/>"/>
<input type="hidden" name="showProperty" id="showProperty" value="<c:out value="${param.showProperty}"/>"/>

<div class="conSearch" >
<h4><span><spring:message code="globe.query"/></span></h4>
	<form id="roleSearchZone" >
	<ul>
		<li>名称:
			<input type="text" name="name" value="">
			<input type="hidden" name="name_op" value="like"></li>
		<li>机构:
			<input type="text" name="company.name" value="">
			<input type="hidden" name="company.name_op" value="like">
		</li>
		<li class="operationBtnDiv">
	      <button type="button" onclick="employee_to_user.oTable.fnDraw();"><span><spring:message code="globe.search"/></span></button>
	    </li>
	</ul>
	</form>
</div>

<table class="frontTableList" id="UserList">
      <thead>
        <tr>
          <th class="left">联系人名称</th>
          <th class="left">机构</th>
          <th class="center">电话</th>
          <th class="operator">操作</th>
        </tr>
      </thead>
      <tbody>
      </tbody>
</table>

<div class="conOperation">
	<button id="close" onclick="employee_to_user.close();"><span>关闭</span></button>
</div>