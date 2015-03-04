<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script>
var MemberList = {};

//查看已订阅服务
MemberList.view = function(orgInfoId,orgName,memberClassName,totalFee){
	$.epsDialog({
		title:"订阅的服务列表",
		width: 600,
		height: 300,
		url:$('#initPath').val()+'/view/pubservice/application/member/member_services_div.jsp?orgInfoId='+orgInfoId+'&orgName='+native2ascii(orgName)+'&memberClassName='+native2ascii(memberClassName)+'&totalFee='+totalFee
	});
}

//重新加载列表数据
MemberList.reload = function(){
	MemberList.oTable.fnDraw();
}

$(document).ready(function(){
	//加载会员列表
	MemberList.oTable = $('#MemberList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'orgInfo.orgName,memberClass.memberClassName,payAmount',
		'alias': 'orgInfo.orgName,memberClass.memberClassName,payAmount',
		'hiddenColumns' : 'orgInfo.objId',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			MemberList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			//添加操作按钮
			var operStr = '<td class="operation">';
			operStr += '<a href="javascript:void(0);" title="查看已订阅服务" onclick="MemberList.view(\''+aData["orgInfo.objId"]+'\',\''+aData["orgInfo.orgName"]+'\',\''+aData["memberClass.memberClassName"]+'\',\''+aData["payAmount"]+'\');return false;">查看已订阅服务</a>';
			operStr += '</td>';
			$(nRow).append(operStr);
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/MemberController.do?method=list",
		'searchZone':'MemberClassSearchForm'
	});
	
	//查询
	$("#query").click(function() {
		MemberList.reload();
	})
});
</script>

<!-- 查询条件 -->
<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>
<form id="MemberClassSearchForm">
	<ul>
		<li>
			<label>会员级别：</label>
			<input type="text" name="memberClass.memberClassName" id="memberClass.memberClassName" value="">
			<input type="hidden" name="memberClass.memberClassName_op" value="like">
		</li>
		<li>
			<label>机构名称：</label>
			<input type="text" name="orgInfo.orgName" id="orgInfo.orgName" value="">
			<input type="hidden" name="orgInfo.orgName_op" value="like">
		</li>
		<li class="operationBtnDiv">
			<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
		</li>
	</ul>
</form>
</div>

<table class="frontTableList" id="MemberList">
	<thead>
		<tr>
			<th class="omission" omiLength="30">机构名称</th>
			<th class="center">会员级别</th>
			<th class="money">已缴费金额（元）</th>
			<th class="operation">操作</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>