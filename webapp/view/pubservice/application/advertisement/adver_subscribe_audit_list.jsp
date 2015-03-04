<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!--搜索条件 -->
<div class="conSearch">
	<h4><span>搜索</span></h4>
	<form id="adverSubscribeAuditSearch">
		<ul>
			<li>
				<label>广告位：</label>
				<input type="text" id="advertisingPosition.positionDictionary.dicName" name="advertisingPosition.positionDictionary.dicName" value=""/>
				<input type="hidden" id="advertisingPosition.positionDictionary.dicName_op" name="advertisingPosition.positionDictionary.dicName_op" value="like"/>
			</li>
			<li>
				<label>投放单位：</label>
				<input type="text" id="orgName" name="orgName" value=""/>
				<input type="hidden" id="orgName_op" name="orgName_op" value="like"/>
			</li>
			<li>
	  			<label>有效时间：</label>
	  			<input name="startTime" id="startTime">&nbsp;&nbsp;到
		        <input name="endTime" id="endTime">
	  		</li>
			<li class="operationBtnDiv">
		        <button id = "adverSearchAuditButton" type="button"><span>查询</span></button>
		    </li>
		</ul>
	</form>
</div>
	
<!--列表 -->
<div id="adverAuditTableDiv">
	<table class="frontTableList" id="adverAuditTableList">
		<thead>
			<tr>
				<th class="left omission">广告位</th>
				<th class="left omission">投放单位</th>
				<th>有效时间</th>
				<th class="money">总费用(元)</th>
				<th>使用状态</th>
				<th>审核状态</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>
<script type="text/javascript">
var adverSubscribeAuditList = {};
adverSubscribeAuditList.oTable;

$(document).ready(function(){
	//日期表单验证
	$("#startTime").epsDatepicker();
	$("#endTime").epsDatepicker();
	
	//填充table列表
	adverSubscribeAuditList.oTable = $('#adverAuditTableList').dataTable({
		'singleSelect':true,
		'checkbox':false,
		'searchZone':'adverSubscribeAuditSearch',
		'sAjaxSource':$('#initPath').val()+"/AdvertisingSubscribeController.do?method=list",
		 params:{'auditStatus':'01'},
		'queryColumns':'advertisingPosition.positionDictionary.dicName,orgName,startTime,totalOutlay,useStatus,auditStatus',
		'hiddenColumns':'endTime',
		'alias':'advertisingPosition.positionDictionary.dicName,orgName,startTime,totalOutlay,useStatusCN,auditStatusCN',
		'fnInitComplete':function(oSettings){},
		'fnDrawCallback':function(oSettings){
			adverSubscribeAuditList.oTable.oSettings = oSettings;
			},
		'fnRowCallback':function(nRow,aData,iDisplayIndex){
			$(nRow).find('td[name=startTime]').empty().append(aData.startTime).append('——').append(aData.endTime);

			//审核
			$(nRow).append('<td><a href="javascript:void(0);" name="audit"><span>审核</span></a></td>');
			$(nRow).find("a[name=audit]").click(function(){
				$('#conBody').loadPage($('#initPath').val()+"/AdvertisingSubscribeController.do?method=toAdverSubscribeDetailOrAudit&operationStyle=AuditAdd&objId="+aData.objId);
				});
			return nRow;
		}
	});
});

//查询过滤
$('#adverSearchAuditButton').click(function(){
	var table = adverSubscribeAuditList.oTable;
	var setting = adverSubscribeAuditList.oTable.dataTableSettings;
	
	if($("#startTime").val().length > 0 && $("#endTime").val().length == 0){
		$(table.dataTableSettings).attr('params', 
				$.extend(setting.params,{'auditStatus':'01',"startTime":$("#startTime").val(),"startTime_op":"ge"}));
	}
	else if($("#endTime").val().length > 0 && $("#startTime").val().length == 0){
		$(table.dataTableSettings).attr('params',
				$.extend(setting.params,{'auditStatus':'01',"endTime":$("#endTime").val(),"endTime_op":"le"}));
	}
	else if($("#endTime").val().length > 0 && $("#startTime").val().length > 0){
		$(table.dataTableSettings).attr('params',
				$.extend(setting.params,{'auditStatus':'01',"startTime":$("#startTime").val()+","+$("#endTime").val(),"startTime_op":"ge","endTime_op":"le"}));
	}
	table.fnDraw();
});
</script>
