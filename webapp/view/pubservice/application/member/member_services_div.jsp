<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ page import="com.gpcsoft.bizplatform.base.common.util.StringUtils" %>

<script>
var MemberServicesList = {};

//取消订阅
MemberServicesList.cancel = function(subscribeId){
	if(window.confirm('确定取消该服务的订阅吗?')) {
		$.getJSON($("#initPath").val()+"/ServiceSubscribeController.do?method=cancelSubscribe",{"objId":subscribeId},function(json){
			if(json.success){
				MemberServicesList.reload();//刷新列表
			}else{
				alert("操作失败！");
			}
		});
	}
}

//重新加载列表数据
MemberServicesList.reload = function(){
	MemberServicesList.oTable.fnDraw();
}

$(document).ready(function(){
	//加载订阅服务列表
	MemberServicesList.oTable = $('#MemberServicesList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'serviceBase.serviceName,startTime,endTime,payAmount,auditStatus',
		'alias': 'serviceBase.serviceName,startTime,endTime,payAmount,auditStatusCN',
		'hiddenColumns':'remark',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			MemberServicesList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			var operStr = '<td class="operation">';
			//添加操作按钮
			if(aData['remark']) {//取消后会更新remark字段值
				$(nRow).find("td[name=auditStatus]").empty().append("已取消");
			} else {
				operStr += '<a href="javascript:void(0);" title="取消订阅" onclick="MemberServicesList.cancel(\''+aData.objId+'\');return false;">取消订阅</a>';
			}
			operStr += '</td>';
			$(nRow).append(operStr);
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/ServiceSubscribeController.do?method=list",
		"params":{"orgInfo.objId":$('#orgInfoId').val()},
		'searchZone':'MemberServicesSearchForm'
	});
	
	//查询
	$("#query").click(function() {
		MemberServicesList.reload();
	})
});
</script>

<!-- 查询条件 -->
<div class="conSearch">
<h4><span>会员信息</span></h4>
<form id="MemberServicesSearchForm">
<input type="hidden" name="orgInfoId" id="orgInfoId" value="${param.orgInfoId}"/>
<div class="formLayout form2Pa">
	<ul>
		<li class="fullLine">
			<label>机构名称：</label>
			<span id="orgName"><%=StringUtils.ascii2Native(request.getParameter("orgName"))%></span>
		</li>
		<li class="fullLine">
			<label>会员级别：</label>
			<span id="memberClassName"><%=StringUtils.ascii2Native(request.getParameter("memberClassName"))%></span>
		</li>
		<li class="fullLine">
			<label>已缴费金额(元)：</label>
			<span id="totalFee"><fmt:formatNumber value="${param.totalFee}" pattern="#,##0.00#" /></span>
		</li>
	</ul>
</div>
</form>
</div>

<table class="frontTableList" id="MemberServicesList">
	<thead>
		<tr>
			<th class="center">服务名称</th>
			<th class="date">有效期从</th>
			<th class="date">有效期到</th>
			<th class="money">已缴纳金额</th>
			<th class="center">状态</th>
			<th class="operation">操作</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>