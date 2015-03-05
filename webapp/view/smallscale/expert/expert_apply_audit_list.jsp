<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!-- 查询条件 -->
<div class="conSearch">
	<h4><span><spring:message code="globe.query" /></span></h4>
	<form id="expertInfoAuditListForm">
		<ul>
			<li>
				<label>专家姓名 ：</label>
				<input type="text" name="expertInfo.name" id="name" value="">
				<input type="hidden" name="expertInfo.name_op" value="like">
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

<table class="frontTableList" id="expertInfoAuditList">
	<thead>
		<tr>
			<th class="hidden"></th>
			<th class="center">专家姓名</th>
			<th class="center">申请类型</th>
			<th class="center">状态</th>
			<th class="operation">操作</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>

<script>
/**
 * 专家申请审核列表页面
 * create by likg
 */
var ExpertInfoAuditList = {};

//审核
ExpertInfoAuditList.toAudit = function(expertId, applyType) {
	var url = $('#initPath').val()+'/ExpertInfoApplyController.do?method=toAuditApplyExpertView&expertId='+expertId+"&applyType="+applyType;
	$.epsDialog({
        title:'审核专家的申请',
        url:url,
        width: '500',
        height: '300',
        onClose: function(){
			ExpertInfoAuditList.oTable.fnDraw(); //刷新列表
        }
    });
}

//查看
ExpertInfoAuditList.view = function(expertId) {
	var url = $('#initPath').val()+'/ExpertInfoController.do?method=getExpertAllInfo&expertId='+expertId;
	$.epsDialog({
        title:'专家详情信息',
        url:url,
        width: '900',
        height: '500'
    }); 
}

$(document).ready(function() {
	//设定返回路径
	$("#returnUrl").val("view/smallscale/expert/expert_apply_audit_list.jsp");

	//加载列表
	ExpertInfoAuditList.oTable = $('#expertInfoAuditList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'expertInfo.objId,expertInfo.name,applyType,auditStatus',
		'alias': 'expertInfo.objId,expertInfo.name,applyType,auditStatusCN',
		'hiddenColums' : '',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			ExpertInfoAuditList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			var type = aData["applyType"];
			if(type == 'consultant') {
				$(nRow).find('td[name=applyType]').html("咨询员");
			}else if(type == 'reviewers') {
				$(nRow).find('td[name=applyType]').html("评审员");
			}
			$(nRow).find('td[name=applyType]').attr("align","center");
			var html = '';
			// 添加操作按钮
			html += '<td class="operation"><a href="javascript:void(0);" onclick="ExpertInfoAuditList.view(\'' + aData["expertInfo.objId"] + '\');return false;">查看</a>';
			if(aData["auditStatus"]=='01') {
				html += '<a href="javascript:void(0);" onclick="ExpertInfoAuditList.toAudit(\'' + aData["expertInfo.objId"] + '\',\'' + type + '\')">审核</a>';
			}
			html += '</td>';
			$(nRow).append(html);
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/ExpertInfoApplyController.do?method=list",
		'searchZone':'expertInfoAuditListForm'
	});
	
	// 查询
	$("#query").click(function() {
		ExpertInfoAuditList.oTable.fnDraw();
	})
});
</script>
