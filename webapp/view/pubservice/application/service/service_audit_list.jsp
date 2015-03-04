<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!-- 查询条件 -->
<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>
<form id="SubscribeAuditListForm">
	<ul>
		<li><label>机构名称 ：</label><input type="text" name="orgInfo.orgName" id="orgInfo.orgName" value="">
			<input type="hidden" name="orgInfo.orgName_op" value="like">
		</li>
		<li><label>服务名称 ：</label><input type="text" name="serviceBase.serviceName" id="serviceBase.serviceName" value="">
			<input type="hidden" name="serviceBase.serviceName_op" value="like">
		</li>
		<li class="operationBtnDiv">
			<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
		</li>
	</ul>
</form>
</div>

<table class="frontTableList" id="SubscribeAuditList">
	<thead>
		<tr>
			<th class="left">机构名称</th>
			<th class="left">订阅服务名称</th>
			<th class="date center">订阅时间</th>
			<th class="date center">有效期从</th>
			<th class="date center">有效期到</th>
			<th class="center">状态</th>
			<th class="operation">操作</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>

<script>
var SubscribeAuditList = {};
//查询
SubscribeAuditList.reload = function() {
	var table = SubscribeAuditList.oTable;
	table.fnDraw();
}
//审核
SubscribeAuditList.toAudit = function(objId,type) {
	var url = $('#initPath').val()+'/ServiceSubscribeController.do?method=toAuditSubscribe&objId='+objId+'&type='+type;
 	if(type=="view") {
 		$.epsDialog({
 	        title:'订阅服务详情',
 	        url:url,
 	        width: '700',
 	        height: '500'
 	    }); 
 	} else if(type="audit"){
 		$('#conBody').loadPage(url);
 	}
}

$(document).ready(function() {
	//设定返回路径
	$("#returnUrl").val("view/pubservice/application/service/service_audit_list.jsp");
	
	SubscribeAuditList.oTable = $('#SubscribeAuditList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'orgInfo.orgName,serviceBase.serviceName,createTime,startTime,endTime,auditStatus',
		'alias':'orgInfo.orgName,serviceBase.serviceName,createTime,startTime,endTime,auditStatusCN',
		'hiddenColums' : '',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			SubscribeAuditList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			var html = '';
			html += '<td align="center">';
			html += '<a href="javascript:void(0);" onclick="SubscribeAuditList.toAudit(\''+aData.objId+'\',\''+"view"+'\');return false;">查看</a>';
			if(aData["auditStatus"]=='01') {
				html += '<a href="javascript:void(0);" onclick="SubscribeAuditList.toAudit(\''+aData.objId+'\',\''+"audit"+'\')">审核</a>';
			}
			html += '</td>';
			// 添加操作按钮
			$(nRow).append(html)
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/ServiceSubscribeController.do?method=list",
		'searchZone':'SubscribeAuditListForm'
	});
	// 查询
	$("#query").click(function() {
		SubscribeAuditList.reload();
	})
});
</script>
