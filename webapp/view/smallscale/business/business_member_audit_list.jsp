<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!-- 查询条件 -->
<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>
<form id="MemberAuditListForm">
	<ul>
		<li><label>机构名称 ：</label><input type="text" name="orgInfo.orgName" id="orgName" value=""></li>
		<li class="operationBtnDiv">
			<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
		</li>
	</ul>
</form>
</div>

<div id="epsTabs">
  <ul>
  	<li>
      <a href="#memberList" id = "tabs_toAudit" class="refreshData"><span>待审核</span></a>
    </li>
    <li>
      <a href="#memberList" id = "tabs_hadAudit" class="refreshData"><span>已审核通过</span></a>
    </li>
  </ul>
	<div id="memberList">
			<table class="frontTableList" id="MemberAuditList">
				<thead>
					<tr>
						<th class="left">机构名称</th>
						<th class="center">会员时长</th>
						<th class="date center">到期时间</th>
						<th class="center">会员状态</th>
						<th class="operation">操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
	</div>
</div>

<script>
var MemberAuditListForm = {};
MemberAuditListForm.currentTabID = "tabs_toAudit";

//查看
MemberAuditListForm.toView=function(objId) {
	$('#conBody').loadPage($('#initPath').val()+"/BusinessMemberController.do?method=toView&objId="+objId);
}

//审核
MemberAuditListForm.toAudit=function(objId) {
	$('#conBody').loadPage($('#initPath').val()+"/BusinessMemberController.do?method=toAudit&objId="+objId);
}

$(document).ready(function() {
	//设定返回路径
	$("#returnUrl").val("view/smallscale/business/business_member_audit_list.jsp");
	
	var $tabs = $('#epsTabs').tabs({}); 
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		MemberAuditListForm.currentTabID = $(this).attr("id");
		if ("tabs_toAudit"==MemberAuditListForm.currentTabID) {//待审核
			$(MemberAuditListForm.oTable.dataTableSettings).attr("params", {"auditStatus":"01"});
		} else if ("tabs_hadAudit"==MemberAuditListForm.currentTabID) {//审核通过
			$(MemberAuditListForm.oTable.dataTableSettings).attr("params", {"auditStatus":"02"});
		}
		MemberAuditListForm.oTable.fnDraw();
	})
	
	MemberAuditListForm.oTable = $('#MemberAuditList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'orgInfo.orgName,timeType,endDate,useStatus',
		'alias':'orgInfo.orgName,timeTypeCN,endDate,useStatusCN',
		'hiddenColumns':'begainDate,endDate',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			MemberAuditListForm.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			var html = '';
			html += '<td align="center"><a  title="查看" href="javascript:void(0);" onclick="MemberAuditListForm.toView(\''+aData.objId+'\');return false;">查看</a>';
			if(aData.useStatus=='00') {
				html += '<a title="审核" href="javascript:void(0);" onclick="MemberAuditListForm.toAudit(\''+aData.objId+'\');return false;">审核</a>';
			}
			html += '</td>';
			$(nRow).append(html);
			if(StringToDate(aData.endDate.replace('-', "/").replace('-', "/")).getTime() < new Date().getTime()) {//过期
				$(nRow).find('td[name=useStatus]').html('已过期');
			}
			return nRow;
		},
		"params":{"auditStatus":"01"},
		"sAjaxSource" : $('#initPath').val()+ "/BusinessMemberController.do?method=list",
		'searchZone':'MemberAuditListForm'
	});
	
	// 查询
	$("#query").click(function() {
		MemberAuditListForm.reload();
	})
});
</script>
