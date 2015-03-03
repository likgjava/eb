var OrgInfoManageList={};
OrgInfoManageList.currentTabID = "tabs_waitAudit";

//禁用
OrgInfoManageList.disableOrEnable = function(orgInfoId,isOff) {
	var tempStr = "";
	tempStr = isOff==1?"启用":"禁用";
	if(window.confirm("确定要"+tempStr+"该机构?")){
	    $.getJSON($('#initPath').val()+'/OrgInfoController.do?method=updateDisableOrEnable&orgInfoId='+orgInfoId+"&isOff="+isOff,function(json){
	        if(json.result)alert(json.result);
	        if(json.failure){
	            return;
	        }
	        OrgInfoManageList.oTable.fnDraw();
	    });
	}
}

//查看
OrgInfoManageList.view = function(orgInfoId) {
	var url = $('#initPath').val()+'/ExOrgInfoController.do?method=getExAllBaseInfo&orgId='+orgInfoId;
	$.epsDialog({
        title:'机构详情',
        url:url,
        width: '900',
        height: '500'
    }); 
}

$(document).ready(function(){
	var $tabs = $('#epsTabs').tabs({}); 
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		OrgInfoManageList.currentTabID = $(this).attr("id");
		if ("tabs_waitAudit"==OrgInfoManageList.currentTabID) {//临时
			$(OrgInfoManageList.oTable.dataTableSettings).attr("params", {"useStatus":"00"});
		} else if ("tabs_hadAudit"==OrgInfoManageList.currentTabID) {//已通过
			$(OrgInfoManageList.oTable.dataTableSettings).attr("params", {"auditStatus":"02"});
		}
		OrgInfoManageList.oTable.fnDraw();
	})
	
	OrgInfoManageList.oTable = $('#OrgInfoManageList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'orgCode,orgName,validTime,invalidTime,auditStatus,isOff',
		'alias':'orgCode,orgName,validTime,invalidTime,auditStatusCN,isOffCN',
		'hiddenColumns':'useStatus',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			OrgInfoManageList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			if(aData.isOff==2){//如果是禁用,则添加"启用"按钮
				if(aData.useStatus=='01'){
					$(nRow).append('<td align="center"><a href="javascript:void(0);" onclick="OrgInfoManageList.view(\'' + aData.objId + '\');return false;">查看</a><a href="javascript:void(0);" onclick="OrgInfoManageList.disableOrEnable(\'' + aData.objId + '\',1)">启用</a></td>')
				}else{
					$(nRow).append('<td align="center"><a href="javascript:void(0);" onclick="OrgInfoManageList.view(\'' + aData.objId + '\');return false;">查看</a></td>')
				}
			}else{
				if(aData.useStatus=='01'){
					$(nRow).append('<td align="center"><a href="javascript:void(0);" onclick="OrgInfoManageList.view(\'' + aData.objId + '\');return false;">查看</a><a href="javascript:void(0);" onclick="OrgInfoManageList.disableOrEnable(\'' + aData.objId + '\',2)">禁用</a></td>')
				}else{
					$(nRow).append('<td align="center"><a href="javascript:void(0);" onclick="OrgInfoManageList.view(\'' + aData.objId + '\');return false;">查看</a></td>')
				}
			}
			
			return nRow;
		},
		"params":{"useStatus":"00"},
		"sAjaxSource" : $('#initPath').val()+ "/OrgInfoController.do?method=list&type=manage&orgType=AGENT",
		'searchZone':'OrgInfoManageListForm'
	});
	
	// 查询
	$("#query").click(function() {
		OrgInfoManageList.oTable.fnDraw();
	})
});

