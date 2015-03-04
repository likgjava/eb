<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!--搜索条件 -->
<div class="conSearch">
	<h4><span>搜索</span></h4>
	<form id="adverSubscribeSearchForm">
		<ul>
			<li>
				<label>广告位：</label>
				<input type="text" id="advertisingPosition.positionDictionary.dicName" name="advertisingPosition.positionDictionary.dicName" value=""/>
				<input type="hidden" id="advertisingPosition.positionDictionary.dicName_op" name="advertisingPosition.positionDictionary.dicName_op" value="like"/>
			</li>
			<li>
				<label>审核状态：</label>
				<select name="auditStatus" id="auditStatus">
					<option value=''>所有</option>
					<option value='00'>草稿</option>
					<option value='01'>待审核</option>
					<option value='02'>审核通过</option>
					<option value='03'>审核不通过</option>
				</select>
				<input type="hidden" name="auditStatus_op" id="auditStatus_op" value="="/>
			</li>
			<li>
				<label>使用状态：</label>
				<select name="useStatus" id="useStatus">
					<option value=''>所有</option>
					<option value='00'>临时</option>
					<option value='01'>有效</option>
					<option value='02'>报废</option>
				</select>
				<input type="hidden" name="useStatus_op" id="useStatus_op" value="="/>
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
		        <button id = "adverSubscribeSearchButton" type="button"><span>查询</span></button>
		    </li>
		</ul>
	</form>
</div>
	
<div class="formTips attention">
	<ul>
		<li>
			<em>注意：</em><spring:message code="smallscale.advertisement.management.prompt"/>
		</li>
	</ul>
</div>
	
<div class="operationBtnDiv l">
	<button id="addAdverSubscribe"><span>新增广告</span></button>
	<button id="delAdverSubscribe"><span>批量删除</span></button>
	<button id="pubAdverSubscribe"><span>批量发布</span></button>
</div>
	
<div id="epsTabs" class="epsTabs">
	<ul>
		<li><a href="#adverSubscribeTableDiv" id = "tabs_audit_yes" class="refreshData"><span>审核通过广告</span></a></li>
		<li><a href="#adverSubscribeTableDiv" id = "tabs_caogao" class="refreshData"><span>草稿广告</span></a></li>
		<li><a href="#adverSubscribeTableDiv" id = "tabs_audit_wait" class="refreshData"><span>待审核广告</span></a></li>			
		<li><a href="#adverSubscribeTableDiv" id = "tabs_audit_no" class="refreshData"><span>审核未通过广告</span></a></li>			
		<li><a href="#adverSubscribeTableDiv" id = "tabs_disuse" class="refreshData"><span>废弃广告</span></a></li>			
	</ul>
	<div id="adverSubscribeTableDiv">
		<table class="frontTableList" id="adverSubscribeTableList">
			<thead>
				<tr>
					<th class="left omission" omiLength="5">广告位</th>
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
</div>
<script type="text/javascript">
var adverSubscribeManagerList = {};
adverSubscribeManagerList.oTable;
adverSubscribeManagerList.currentTabId = "tabs_audit_yes";

$(document).ready(function(){
	//日期表单验证
	$("#startTime").epsDatepicker();
	$("#endTime").epsDatepicker();

	//加载tab页
	var tabs = $('#epsTabs').tabs();

	//tab页点击事件
	$('li a.refreshData').click(function(){
		adverSubscribeManagerList.currentTabId = $(this).attr("id");
		//点击时载入条件参数
		if(adverSubscribeManagerList.currentTabId == 'tabs_audit_yes'){
			$(adverSubscribeManagerList.oTable.dataTableSettings).attr('params',{'auditStatus':'02','useStatus':'01'});
			adverSubscribeManagerList.oTable.fnDraw();
		}
		if(adverSubscribeManagerList.currentTabId == 'tabs_caogao'){
			$(adverSubscribeManagerList.oTable.dataTableSettings).attr('params',{'auditStatus':'00','useStatus':'00'});
			adverSubscribeManagerList.oTable.fnDraw();
		}
		if(adverSubscribeManagerList.currentTabId == 'tabs_audit_wait'){
			$(adverSubscribeManagerList.oTable.dataTableSettings).attr('params',{'auditStatus':'01','useStatus':'00'});
			adverSubscribeManagerList.oTable.fnDraw();
		}
		if(adverSubscribeManagerList.currentTabId == 'tabs_audit_no'){
			$(adverSubscribeManagerList.oTable.dataTableSettings).attr('params',{'auditStatus':'03','useStatus':'00'});
			adverSubscribeManagerList.oTable.fnDraw();
		}
		if(adverSubscribeManagerList.currentTabId == 'tabs_disuse'){
			$(adverSubscribeManagerList.oTable.dataTableSettings).attr('params',{'auditStatus':'02','useStatus':'02'});
			adverSubscribeManagerList.oTable.fnDraw();
		}
	});
	
	//填充table列表
	adverSubscribeManagerList.oTable = $('#adverSubscribeTableList').dataTable({
		'singleSelect':false,
		'checkbox':true,
		'searchZone':'adverSubscribeSearchForm',
		'sAjaxSource':$('#initPath').val()+"/AdvertisingSubscribeController.do?method=list",
		 params:{'auditStatus':'02','useStatus':'01'},
		'queryColumns':'advertisingPosition.positionDictionary.dicName,orgName,startTime,totalOutlay,useStatus,auditStatus',
		'hiddenColumns':'endTime,advertisingPosition.adverType,advertisingPosition.objId',
		'alias':'advertisingPosition.positionDictionary.dicName,orgName,startTime,totalOutlay,useStatusCN,auditStatusCN',
		'fnInitComplete':function(oSettings){},
		'fnDrawCallback':function(oSettings){
			adverSubscribeManagerList.oTable.oSettings = oSettings;
			},
		'fnRowCallback':function(nRow,aData,iDisplayIndex){
			$(nRow).find('td[name=startTime]').empty().append(aData.startTime).append('—').append(aData.endTime);

			//查看
			$(nRow).append('<td><a href="javascript:void(0);" name="detail"><span>查看</span></a></td>');
			$(nRow).find("a[name=detail]").click(function(){
				$.epsDialog({
					title:"查看广告订阅信息",
					url:$("#initPath").val()+"/AdvertisingSubscribeController.do?method=toAdverSubscribeDetailOrAudit&objId="+aData.objId,
					onClose:function(){
					}
				})
			});

			//修改、提交审核
			if(aData.auditStatus == '00' || aData.auditStatus == '03'){
				$(nRow).find("td:last").append('<a href="javascript:void(0);" name="modify"><span>修改</span></a>');
				$(nRow).find("a[name=modify]").click(function(){
					$('#conBody').loadPage($('#initPath').val()+"/AdvertisingSubscribeController.do?method=toAddORModifyAdverSubscribe&objId="+aData.objId);
					});

				$(nRow).find("td:last").append('<a href="javascript:void(0);" name="submit"><span>提交</span></a>');
				$(nRow).find("a[name=submit]").click(function(){
					if(confirm("确认提交吗？")){
						$.getJSON($("#initPath").val()+"/AdvertisingSubscribeController.do?method=modifyUseORAuditStatus",{"objId":aData.objId,"field":"auditStatus","estatus":"01"},function(json){
							if(json.result=='true'){alert("操作成功!");adverSubscribeManagerList.reload();}
						});
					}
				});
			}

			//废弃、发布广告
			if(aData.useStatus == '01'){
				//废弃
				$(nRow).find("td:last").append('<a href="javascript:void(0);" name="disuse"><span>废弃</span></a>');
				$(nRow).find("a[name=disuse]").click(function(){
					if(confirm("确定废弃吗？")){
						$.getJSON($("#initPath").val()+"/AdvertisingSubscribeController.do?method=modifyUseORAuditStatus",{"objId":aData.objId,"field":"useStatus","estatus":"02"},function(json){
							if(json.result=='true'){alert("操作成功!");adverSubscribeManagerList.reload();}
						});
					}
				});

				//修改(此处修改只修改内容，不更改有效状态、审核状态)
				$(nRow).find("td:last").append('<a href="javascript:void(0);" name="limit_modify"><span>修改</span></a>');
				$(nRow).find("a[name=limit_modify]").click(function(){
					$('#conBody').loadPage($('#initPath').val()+"/AdvertisingSubscribeController.do?method=toAdverSubscribeDetailOrAudit&operationStyle=limit_modify&objId="+aData.objId);
				});

				//发布广告
				$(nRow).find("td:last").append('<a href="javascript:void(0);" name="publish"><span>发布</span></a>');
				$(nRow).find("a[name=publish]").click(function(){
					if(confirm("确定发布广告吗？")){
						$.getJSON($("#initPath").val()+"/AdvertisingSubscribeController.do?method=publishAdverSubscribe",{"adverSubscribeIds":aData.objId},function(json){
							if(json.result=='true'){alert("操作成功!");adverSubscribeManagerList.reload();}
						});
					}
				});
			}

			//跑马灯广告排序
			if(aData.auditStatus == '02'  && aData['advertisingPosition.adverType'] == '02'){
				$(nRow).find("td:last").append('<a href="javascript:void(0);" name="ad_sort"><span>排序</span></a>');
				$(nRow).find("a[name=ad_sort]").click(function(){
					$.epsDialog({
						title:"自由排序",
						url:$("#initPath").val()+"/view/pubservice/application/advertisement/adver_subscribe_sort_pmd.jsp?positionId="+aData['advertisingPosition.objId']
					})
				});
			}

			//删除
			if(aData.useStatus == '02'){
				$(nRow).find("td:last").append('<a href="javascript:void(0);" name="del"><span>删除</span></a>');
				$(nRow).find("a[name=del]").click(function(){
					if(confirm("确定删除吗？")){
						$.getJSON($("#initPath").val()+"/AdvertisingSubscribeController.do?method=removeAdverSubscribe",{"adverSubscribeIds":aData.objId},function(json){
							if(json.result=='true'){alert("操作成功!");adverSubscribeManagerList.reload();}
						});
					}
				});
			}
			return nRow;
		}
	});
});

//广告新增
$('#addAdverSubscribe').click(function(){
	$('#conBody').loadPage($('#initPath').val()+"/AdvertisingSubscribeController.do?method=toAddORModifyAdverSubscribe");
});

//广告批量删除
$('#delAdverSubscribe').click(function(){
	var adverSubscribeIds = adverSubscribeManagerList.oTable.dtSelects();
	if(adverSubscribeIds.length<=0){alert("至少选择一条记录");return;}
	var adverIdsArray = adverSubscribeIds.split(',');
	var param = "false";
	var paramTips = "";
	for(var i=0;i<adverIdsArray.length;i++){
		var useData = $('#adverSubscribeTableList').find('tr[objId='+adverIdsArray[i]+']').find('td[name=useStatus]').html();
		var auditData = $('#adverSubscribeTableList').find('tr[objId='+adverIdsArray[i]+']').find('td[name=auditStatus]').html();
		if("有效" == useData){
			param = "true";
			paramTips = "有效";
		}else if("待审核"==auditData){
			param = "true";
			paramTips = "待审核";
		}
	}
	if("true" == param){
		alert("选项中存在'"+paramTips+"'广告！请重新选择删除项");return;
	}
	if(confirm("确认删除广告订阅码？")){
		$.getJSON($('#initPath').val()+"/AdvertisingSubscribeController.do?method=removeAdverSubscribe",{"adverSubscribeIds":adverSubscribeIds},function(json){
			if(json.result == 'true'){alert("操作成功！");adverSubscribeManagerList.oTable.fnDraw();}
			if(json.failure){alert("操作没有成功,请重新操作");return;}
			});
	}
});

//批量发布
$('#pubAdverSubscribe').click(function(){
	var adverSubscribeIds = adverSubscribeManagerList.oTable.dtSelects();
	if(adverSubscribeIds.length<=0){alert("至少选择一条记录");return;}	
	if(confirm("确认批量发布广告订阅码？")){
		$.getJSON($('#initPath').val()+"/AdvertisingSubscribeController.do?method=publishAdverSubscribe",{"adverSubscribeIds":adverSubscribeIds},function(json){
			if(json.result == 'true'){alert("操作成功！");adverSubscribeManagerList.oTable.fnDraw();}
			if(json.failure){alert("操作没有成功,请重新操作");return;}
		});
	}
});

//批量废弃有效广告
$('#disuseAdverSubscribe').click(function(){
	var adverIdsArray = adverSubscribeManagerList.oTable.dtSelectArray();
	if(adverIdsArray.length<=0){alert("至少选择一条记录");return;}
	var param = "false";
	for(var i=0;i<adverIdsArray.length;i++){
		var data = $('#adverSubscribeTableList').find('tr[objId='+adverIdsArray[i]+']').find('td[name=useStatus]').html();
		if("有效" != data){
			param = "true";
		}
	}
	if("true" == param){
		alert("选项中存在'无效'的广告！请重新选择");return;
	}
	if(confirm("确定废弃有效广告吗？")){
		$.getJSON($("#initPath").val()+"/AdvertisingSubscribeController.do?method=modifyUseORAuditStatus",{"adverIdsArray":adverIdsArray,"field":"useStatus","estatus":"02"},function(json){
			if(json.result == 'true'){alert("操作成功!");adverSubscribeManagerList.reload();}
			if(json.failure){alert("操作没有成功,请重新操作");return;}
		});
		}
});

//刷新
adverSubscribeManagerList.reload = function(){
	adverSubscribeManagerList.oTable.fnDraw();
}

//查询过滤
$('#adverSubscribeSearchButton').click(function(){
	var table = adverSubscribeManagerList.oTable;
	var setting = adverSubscribeManagerList.oTable.dataTableSettings;
	
	if($("#startTime").val().length > 0 && $("#endTime").val().length == 0){
		$(table.dataTableSettings).attr('params', 
				$.extend(setting.params,{"startTime":$("#startTime").val(),"startTime_op":"ge"}));
	}
	else if($("#endTime").val().length > 0 && $("#startTime").val().length == 0){
		$(table.dataTableSettings).attr('params',
				$.extend(setting.params,{"endTime":$("#endTime").val(),"endTime_op":"le"}));
	}
	else if($("#endTime").val().length > 0 && $("#startTime").val().length > 0){
		$(table.dataTableSettings).attr('params',
				$.extend(setting.params,{"startTime":$("#startTime").val()+","+$("#endTime").val(),"startTime_op":"ge","endTime_op":"le"}));
	}
	table.fnDraw();
});
</script>
