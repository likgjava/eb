<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!-- 查询条件 -->
<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>
<form id="OrgInfoAuditListForm">
	<ul>
		<li><label>机构名称 ：</label><input type="text" name="orgName" id="orgName" value=""></li>
		<li><label>状态：</label> 
			<select name="auditType" id="auditType">
				<option value="">不限</option>
				<option value="00">注册待审核</option>
				<option value="01">变更待审核</option>
				<option value="02">申请待审核</option>
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
			<th class="center">审核状态</th>
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
OrgInfoAuditList.toAudit = function(orgInfoId,buyerId,agencyId) {
	/** 小额交易-机构审核*/
	var url = $('#initPath').val()+'/OrgInfoController.do?method=toAuditOrgInfo&orgInfoId='+orgInfoId;
	
	/**协议供货-机构审核
    var url = '';
	if((buyerId != "undefined" && buyerId != "") || (agencyId != "undefined" && agencyId != "")) {
		url = $('#initPath').val()+'/OrgInfoController.do?method=toAuditOrgInfo_XYGH&orgInfoId='+orgInfoId;
	}else {
		url = $('#conBody').loadPage($('#initPath').val()+'/OrgInfoController.do?method=toAuditOrgInfo&orgInfoId='+orgInfoId);
	}*/
 	$('#conBody').loadPage(url);
}

//查看
OrgInfoAuditList.view = function(orgInfoId,buyerId,agencyId) {
	/**小额交易,查看页面*/
	var url = $('#initPath').val()+'/ExOrgInfoController.do?method=getExAllBaseInfo&orgId='+orgInfoId;

	/**协议供货,采购人查看页面
	var url = '';
	if((buyerId != "undefined" && buyerId != "") || (agencyId != "undefined" && agencyId != "")) {
		url = $('#initPath').val()+'/OrgInfoController.do?method=getOrgAllInfo&orgInfoId='+orgInfoId;
	}else {
		url = $('#initPath').val()+'/ExOrgInfoController.do?method=getExAllBaseInfo&orgId='+orgInfoId;
	}*/
	
	$.epsDialog({
        title:'机构详情',
        url:url,
        width: '900',
        height: '500'
    }); 
}

$(document).ready(function() {
	//设定返回路径
	$("#returnUrl").val("view/bizplatform/organization/manager/organization_audit_list.jsp");
	
	OrgInfoAuditList.oTable = $('#OrgInfoAuditList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'orgCode,orgName,auditType,createTime',
		'alias':'orgCode,orgName,auditTypeCN,createTime',
		'hiddenColums' : 'buyerId,agencyId',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			OrgInfoAuditList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).find('td[name=auditType]').append("待审核");
			$(nRow).find('td[name=auditType]').attr("align","center");
			// 添加操作按钮
			$(nRow).append('<td align="center"><a href="javascript:void(0);" onclick="OrgInfoAuditList.view(\''+aData.objId + '\',\''+aData.buyerId+'\',\''+aData.agencyId+'\');return false;">查看</a><a href="javascript:void(0);" onclick="OrgInfoAuditList.toAudit(\''+aData.objId + '\',\''+aData.buyerId+'\',\''+aData.agencyId+'\')">审核</a></td>')
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/OrgInfoController.do?method=listOrgInfoForAudit",
		'searchZone':'OrgInfoAuditListForm'
	});
	// 查询
	$("#query").click(function() {
		OrgInfoAuditList.reload();
	})
});
</script>
