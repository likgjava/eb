/**
 * 专家管理列表页面
 * create by likg
 */
var ExpertInfoManageList={};
ExpertInfoManageList.currentTabID = "tabs_temp"; //当前Tab页

//获得操作字符串
ExpertInfoManageList.getOperationStr = function(expertId,useStatus,isOff){
	var operStr = '<td class="operation">';
	operStr += '<a href="javascript:void(0);" onclick="ExpertInfoManageList.view(\'view\',\'' + expertId + '\');return false;">查看</a>';
	if(isOff==2){
		if(useStatus=='01'){//如果是【禁用且有效】,则添加"启用"按钮
			operStr += '<a href="javascript:void(0);" onclick="ExpertInfoManageList.disableOrEnable(\'' + expertId + '\',1);return false;">启用</a>';
		}
	}else{
		operStr += '<a href="javascript:void(0);" onclick="ExpertInfoManageList.view(\'update\',\'' + expertId + '\');return false;">修改</a>';
		if(useStatus=='01'){//如果是【启用且有效】,则添加"禁用"按钮
			operStr += '<a href="javascript:void(0);" onclick="ExpertInfoManageList.disableOrEnable(\'' + expertId + '\',2);return false;">禁用</a>';
		}
	}
	operStr += '</td>';
	return operStr;
}

//启用或禁用
ExpertInfoManageList.disableOrEnable = function(expertId,isOff) {
	var tempStr = "";
	tempStr = (isOff==1?"启用":"禁用");
	if(window.confirm("确定要"+tempStr+"该专家吗?")){
	    $.getJSON($('#initPath').val()+'/ExpertInfoController.do?method=updateDisableOrEnable&expertId='+expertId+"&isOff="+isOff,function(json){
	        if(json.result)alert(json.result);
	        if(json.failure){
	            return;
	        }
	        ExpertInfoManageList.oTable.fnDraw();
	    });
	}
}

//操作
ExpertInfoManageList.view = function(operName, expertId) {
	if(operName == 'view'){
		var url = $('#initPath').val()+'/ExpertInfoController.do?method=getExpertAllInfo&expertId='+expertId;
		$.epsDialog({
	        title:'专家详细信息',
	        url:url,
	        width: '900',
	        height: '500'
	    });
	}else if(operName == 'update'){
		$("#conBody").loadPage($("#initPath").val()+'/ExpertInfoController.do?method=toExpertInfoModifyView&expertId='+expertId);
	}else if(operName == 'updateFinance'){
		$("#returnUrl").val($("#initPath").val()+"/view/bizplatform/organization/manager/organization_manage_list.jsp");
		$("#conBody").loadPage($("#initPath").val()+'/ExOrgInfoController.do?method=toOrgFinanceInfoView&qualificationClassId=402886dc2c3f650f012c3fc83190002e&qualificationCode=C01&orgId='+orgInfoId);
	}else if(operName == 'updateLegal'){
		$("#returnUrl").val($("#initPath").val()+"/view/bizplatform/organization/manager/organization_manage_list.jsp");
		$("#conBody").loadPage($("#initPath").val()+'/ExOrgInfoController.do?method=toOrgLegalInfo&qualityClassId=402886dc2c3f650f012c3fd26bca006f&orgId='+orgInfoId);
	}
}

$(document).ready(function(){
	$("#createTime_show").epsDatepicker();
	
	var $tabs = $('#epsTabs').tabs({}); 
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		ExpertInfoManageList.currentTabID = $(this).attr("id");
		if ("tabs_temp"==ExpertInfoManageList.currentTabID) {//临时
			$(ExpertInfoManageList.oTable.dataTableSettings).attr("params", {"useStatus":"00"});
		} else if ("tabs_valid"==ExpertInfoManageList.currentTabID) {//已通过
			$(ExpertInfoManageList.oTable.dataTableSettings).attr("params", {"auditStatus":"02"});
		}
		ExpertInfoManageList.oTable.fnDraw();
	})
	
	ExpertInfoManageList.oTable = $('#expertInfoManageList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'name,belongIndustry.name,createUser.usName,createTime,auditStatus,isOff',
		'alias':'name,belongIndustry.name,createUser.usName,createTime,auditStatusCN,isOffCN',
		'hiddenColumns':'useStatus',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			ExpertInfoManageList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			var str = ExpertInfoManageList.getOperationStr(aData.objId, aData.useStatus, aData.isOff);
			$(nRow).append(str);
			
			return nRow;
		},
		"params":{"useStatus":"00"},
		"sAjaxSource" : $('#initPath').val()+ "/ExpertInfoController.do?method=list&type=manage",
		'searchZone':'expertInfoManageListForm'
	});
	
	//查询
	$("#query").click(function() {
		//查询某天创建的记录
		var dateStr1 = $('#createTime_show').val();
		if(dateStr1!=null && dateStr1!=''){
			var dd = StringToDate(dateStr1);
			dd.setDate(dd.getDate()+1);   
			var dateStr2 = dd.getFullYear()+'-'+(dd.getMonth()+1)+'-'+dd.getDate();
			$('#createTime').val(dateStr1+','+dateStr2);
		}else{
			$('#createTime').val('');
		}

		ExpertInfoManageList.oTable.fnDraw();
	})
	
	//创建专家
	$("#createExpertInfoBtn").click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/view/smallscale/expert/create_expert_info.jsp");
	});
});

