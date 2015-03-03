<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!-- 查询条件 -->
<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>
<form id="OrgInfoAuditListForm">
	<ul>
		<li><label>机构名称 ：</label><input type="text" name="orgInfo.orgName" id="orgName" value="">
			<input type="hidden" name="orgInfo.orgName_op" value="like">
		</li>
		<li>
			<label>审核状态：</label> 
			<select name="auditStatus" id="auditStatus">
				<option value="">不限</option>
				<option value="01">待审核</option>
				<option value="02">审核通过</option>
				<option value="03">审核不通过</option>
			</select>
		</li>
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
			<th class="center">申请类型</th>
			<th class="center">状态</th>
			<th class="hidden"></th>
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
OrgInfoAuditList.toAudit = function(orgInfoId,applyType) {
	$('#conBody').loadPage($('#initPath').val()+'/OrgInfoApplyController.do?method=toAuditApplyOrg&orgId='+orgInfoId+"&applyType="+applyType);
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
	$("#returnUrl").val("view/bizplatform/organization/manager/organization_apply_audit_list.jsp");
	
	OrgInfoAuditList.oTable = $('#OrgInfoAuditList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'orgInfo.orgCode,orgInfo.orgName,applyType,auditStatus,orgInfo.objId',
		'alias':'orgInfo.orgCode,orgInfo.orgName,applyType,auditStatusCN,orgInfo.objId',
		'hiddenColums' : '',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			OrgInfoAuditList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			var type = aData["applyType"];
			if(type == 'r') {
				$(nRow).find('td[name=applyType]').html("服务商");
			}
			if(type == 'm') {
				$(nRow).find('td[name=applyType]').html("厂商");
			}
			$(nRow).find('td[name=applyType]').attr("align","center");
			var html = '';
			// 添加操作按钮
			html += '<td align="center"><a href="javascript:void(0);" onclick="OrgInfoAuditList.view(\'' + aData["orgInfo.objId"] + '\');return false;">查看</a>';
			if(aData["auditStatus"]=='01') {
				html += '<a href="javascript:void(0);" onclick="OrgInfoAuditList.toAudit(\'' + aData["orgInfo.objId"] + '\',\'' + type + '\')">审核</a>';
			}
			html += '</td>';
			$(nRow).append(html);
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/OrgInfoApplyController.do?method=list",
		'searchZone':'OrgInfoAuditListForm'
	});
	// 查询
	$("#query").click(function() {
		OrgInfoAuditList.reload();
	})
});
</script>
