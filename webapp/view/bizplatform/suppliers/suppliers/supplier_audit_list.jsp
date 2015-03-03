<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!-- 查询条件 -->
<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>
<form id="OrgInfoAuditListForm">
	<ul>
		<li><label>机构名称 ：</label><input type="text" name="orgName" id="orgName" value=""></li>
		<input type="hidden" name="orgName_op" id="orgName_op" value="like">
		
		<li class="operationBtnDiv">
			<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
		</li>
	</ul>
</form>
</div>

<table class="frontTableList" id="OrgInfoAuditList">
	<thead>
		<tr>
			<th class="left">机构代码</th>
			<th class="left">机构名称</th>
			<th class="date center">创建时间</th>
			<th class="operation">操作</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>

<script>
var OrgInfoAuditList = {};
//查询
OrgInfoAuditList.reload = function() {
	var table = OrgInfoAuditList.oTable;
	table.fnDraw();
}
//审核
OrgInfoAuditList.toAudit = function(orgInfoId) {
	$('#conBody').loadPage($('#initPath').val()+'/OrgInfoController.do?method=toAuditOrgInfo&orgInfoId='+orgInfoId+"&auditRole=SUPPLIER");
}

//查看
OrgInfoAuditList.view = function(orgInfoId) {
	var url = $('#initPath').val()+'/ExOrgInfoController.do?method=getExAllBaseInfo&orgId='+orgInfoId;
	$.epsDialog({
        title:'机构详情',
        url:url,
        width: '900',
        height: '500'
    }); 
}

$(document).ready(function() {
	//设定返回路径
	$("#returnUrl").val("view/bizplatform/suppliers/suppliers/supplier_audit_list.jsp");
	
	OrgInfoAuditList.oTable = $('#OrgInfoAuditList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'orgCode,orgName,createTime',
		'alias':'orgCode,orgName,createTime',
		'hiddenColums' : '',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			OrgInfoAuditList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			// 添加操作按钮
			$(nRow).append('<td align="center"><a href="javascript:void(0);" onclick="OrgInfoAuditList.view(\'' + aData.objId + '\');return false;">查看</a><a href="javascript:void(0);" onclick="OrgInfoAuditList.toAudit(\'' + aData.objId + '\')">审核</a></td>')
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/OrgInfoController.do?method=listOrgInfoForAudit&type=SUPPLIER",
		'searchZone':'OrgInfoAuditListForm'
	});
	// 查询
	$("#query").click(function() {
		OrgInfoAuditList.reload();
	})
});
</script>
