<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!-- 查询条件 -->
<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>
<form id="expertInfoAuditListForm">
	<ul>
		<li>
			<label>专家姓名 ：</label>
			<input type="text" name="name" id="name" value="">
			<input type="hidden" name="name_op" value="like">
		</li>
		<li class="operationBtnDiv">
			<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
		</li>
	</ul>
</form>
</div>

<!-- Tab页 -->
<div id="epsTabs">
	<ul>
		<li>
			<a href="#expertInfosList" id = "tabs_waitAudit" class="refreshData"><span>待审核</span></a>
		</li>
		<li>
			<a href="#expertInfosList" id = "tabs_hadAudit" class="refreshData"><span>已通过</span></a>
		</li>
	</ul>
	<div id="expertInfosList">
		<table class="frontTableList" id="expertInfoAuditList">
			<thead>
				<tr>
					<th class="left">专家姓名</th>
					<th class="center">所属行业</th>
					<th class="center">审核状态</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>

<script>
/**
 * 专家信息审核列表页面
 * create by likg
 */
var ExpertInfoAuditList = {};
ExpertInfoAuditList.currentTabID = "tabs_waitAudit";

//审核
ExpertInfoAuditList.toAudit = function(expertId) {
	$('#conBody').loadPage($('#initPath').val()+'/ExpertInfoController.do?method=toAuditExpertInfoView&expertId='+expertId);
}

//查看
ExpertInfoAuditList.view = function(expertId) {
	var url = $('#initPath').val()+'/ExpertInfoController.do?method=getExpertAllInfo&expertId='+expertId;
	$.epsDialog({
        title:'专家详细信息',
        url:url,
        width: '900',
        height: '500'
    });
}

$(document).ready(function() {
	//设定返回路径
	$("#returnUrl").val("view/smallscale/expert/expert_info_audit_list.jsp");

	var $tabs = $('#epsTabs').tabs({}); 
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		ExpertInfoAuditList.currentTabID = $(this).attr("id");
		if ("tabs_waitAudit"==ExpertInfoAuditList.currentTabID) {//待审核
			$(ExpertInfoAuditList.oTable.dataTableSettings).attr("params", {"auditStatus":"01"});
		} else if ("tabs_hadAudit"==ExpertInfoAuditList.currentTabID) {//已通过
			$(ExpertInfoAuditList.oTable.dataTableSettings).attr("params", {"auditStatus":"02"});
		}
		ExpertInfoAuditList.oTable.fnDraw();
	})
	
	ExpertInfoAuditList.oTable = $('#expertInfoAuditList').dataTable({
		'params' : {'auditStatus':'01'},
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'name,belongIndustry.name,auditStatus',
		'alias':'name,belongIndustry.name,auditStatusCN',
		'hiddenColums' : 'auditStatus',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			ExpertInfoAuditList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			//添加操作按钮
			var operStr = '<td align="center" class="operation">';
				operStr += '<a href="javascript:void(0);" onclick="ExpertInfoAuditList.view(\'' + aData.objId + '\');return false;">查看</a>';
			if(aData.auditStatus == '01'){
				operStr += '<a href="javascript:void(0);" onclick="ExpertInfoAuditList.toAudit(\'' + aData.objId + '\');return false;">审核</a>';
			}
			operStr += '</td>';
			$(nRow).append(operStr);
			
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/ExpertInfoController.do?method=list",
		'searchZone':'expertInfoAuditListForm'
	});
	
	//查询
	$("#query").click(function() {
		ExpertInfoAuditList.oTable.fnDraw();
	})
});
</script>
