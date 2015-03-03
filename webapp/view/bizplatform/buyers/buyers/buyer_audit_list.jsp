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
OrgInfoAuditList.toAudit = function(orgInfoId,buyerId) {
	/** 小额交易-机构审核*/
	$('#conBody').loadPage($('#initPath').val()+'/OrgInfoController.do?method=toAuditOrgInfo&orgInfoId='+orgInfoId+"&auditRole=BUYER");
	
	/**协议供货-机构审核
	var url = '';
	if(buyerId != "undefined" && buyerId != "") {
		url = $('#initPath').val()+'/OrgInfoController.do?method=toAuditOrgInfo_XYGH&orgInfoId='+orgInfoId+"&auditRole=BUYER";
	}else {
		url = $('#conBody').loadPage($('#initPath').val()+'/OrgInfoController.do?method=toAuditOrgInfo&orgInfoId='+orgInfoId+"&auditRole=BUYER");
	}
	$('#conBody').loadPage(url);
	*/
}

//查看
OrgInfoAuditList.view = function(orgInfoId,buyerId) {
	/**小额交易,查看页面*/
	 var url = $('#initPath').val()+'/ExOrgInfoController.do?method=getExAllBaseInfo&orgId='+orgInfoId;
	
	/** 协议供货,采购人查看页面
	var url = '';
	if(buyerId != "undefined" && buyerId != "") {
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
	$("#returnUrl").val("view/bizplatform/buyers/buyers/buyer_audit_list.jsp");
	
	OrgInfoAuditList.oTable = $('#OrgInfoAuditList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'orgCode,orgName,createTime',
		'alias':'orgCode,orgName,createTime',
		'hiddenColums' : 'buyerId',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			OrgInfoAuditList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			// 添加操作按钮
			$(nRow).append('<td align="center"><a href="javascript:void(0);" onclick="OrgInfoAuditList.view(\''+aData.objId + '\',\''+aData.buyerId+'\');return false;">查看</a><a href="javascript:void(0);" onclick="OrgInfoAuditList.toAudit(\''+aData.objId + '\',\''+aData.buyerId+'\')">审核</a></td>')
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/OrgInfoController.do?method=listOrgInfoForAudit&type=BUYER",
		'searchZone':'OrgInfoAuditListForm'
	});
	// 查询
	$("#query").click(function() {
		OrgInfoAuditList.reload();
	})
});
</script>
