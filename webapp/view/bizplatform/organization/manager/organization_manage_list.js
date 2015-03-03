var OrgInfoManageList={};
OrgInfoManageList.currentTabID = "tabs_waitAudit";
var appType = "";
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

//操作
OrgInfoManageList.view = function(operName, orgInfoId, buyerId,agencyId) {
	
	//设置返回路径
	$("#returnUrl").val($("#initPath").val()+"/view/bizplatform/organization/manager/organization_manage_list.jsp?appType=XEJY");
	
	if(operName == 'view'){
		/**小额交易,查看页面*/
		var url = $('#initPath').val()+'/ExOrgInfoController.do?method=getExAllBaseInfo&orgId='+orgInfoId;
		
		$.epsDialog({
	        title:'机构详情',
	        url:url,
	        width: '900',
	        height: '500'
	    });
	}else if(operName == 'update'){
		if($('#appType').val()=='XEJY') {
			$("#conBody").loadPage($("#initPath").val()+'/ExOrgInfoController.do?method=toEntBaseInfo&orgId='+orgInfoId);
		} else if($('#appType').val()=='XYGH') {
			if((buyerId != "undefined" && buyerId != "") || (agencyId != "undefined" && agencyId != "")) {
				$("#conBody").loadPage($("#initPath").val()+'/OrgInfoController.do?method=toModifyOrgInfo&orgId='+orgInfoId);
			} else {
				$("#conBody").loadPage($("#initPath").val()+'/ExOrgInfoController.do?method=toEntBaseInfo&orgId='+orgInfoId);
			}
		}
	}else if(operName == 'updateFinance'){
		$("#conBody").loadPage($("#initPath").val()+'/ExOrgInfoController.do?method=toOrgFinanceInfoView&qualificationClassId=402886dc2c3f650f012c3fc83190002e&qualificationCode=C01&orgId='+orgInfoId);
	}else if(operName == 'updateLegal'){
		$("#conBody").loadPage($("#initPath").val()+'/ExOrgInfoController.do?method=toOrgLegalInfo&qualityClassId=402886dc2c3f650f012c3fd26bca006f&orgId='+orgInfoId);
	}
}

$(document).ready(function(){
	
	//为了防止查询后url传过来的参数丢失定义变量记录该值
	appType = $('#appType').val();
	
	//开始时间
    $("#startDate").epsDatepicker();  //增加结束时间的规则
    //结束时间
    $("#endDate").epsDatepicker();  //增加开始时间的规则
	
	var $tabs = $('#epsTabs').tabs({}); 
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		OrgInfoManageList.currentTabID = $(this).attr("id");
		if ("tabs_waitAudit"==OrgInfoManageList.currentTabID) {//临时
			$(OrgInfoManageList.oTable.dataTableSettings).attr("params", {"auditStatus":"01","useStatus":"00","auditStatus_relative":"[and:or]","useStatus_relative":"[and:or]"});
		} else if ("tabs_hadAudit"==OrgInfoManageList.currentTabID) {//已通过
			$(OrgInfoManageList.oTable.dataTableSettings).attr("params", {"auditStatus":"02"});
		}
		OrgInfoManageList.oTable.fnDraw();
	})
	
	OrgInfoManageList.oTable = $('#OrgInfoManageList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'orgName,createUser.emp.name,createUser.usName,createTime,auditStatus,isOff,useStatus',
		'alias':'orgName,createUser.emp.name,createUser.usName,createTime,auditStatusCN,isOffCN,useStatus',
		'hiddenColumns':'supplierAuditStatus,buyerAuditStatus,agencyAuditStatus,supplierId,buyerId,agencyId',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			OrgInfoManageList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			if($('#appType').val()=="" || $('#appType').val()==null) {
				$('#appType').val(appType);
			}
			
			//判断机构类型
			var tempStr = "";
			if(aData.supplierId){
				if(aData.supplierAuditStatus=='02') {
					tempStr += "供应商/";
				}
				if(aData.supplierAuditStatus=='00') {
					tempStr += "供应商(草稿)/";
				}
				if(aData.supplierAuditStatus=='01') {
					tempStr += "供应商(待审核)/";
				}
				if(aData.supplierAuditStatus=='03') {
					tempStr += "供应商(未通过)/";
				}
			}
			if(aData.buyerId){
				if(aData.buyerAuditStatus=='02') {
					tempStr += "采购人/";
				}
				if(aData.buyerAuditStatus=='00') {
					tempStr += "采购人(草稿)/";
				}
				if(aData.buyerAuditStatus=='01') {
					tempStr += "采购人(待审核)/";
				}
				if(aData.buyerAuditStatus=='03') {
					tempStr += "采购人(未通过)/";
				}
			}
			if(aData.agencyId){
				if(aData.agencyAuditStatus=='02') {
					tempStr += "代理机构/";
				}
				if(aData.agencyAuditStatus=='00') {
					tempStr += "代理机构(草稿)/";
				}
				if(aData.agencyAuditStatus=='01') {
					tempStr += "代理机构(待审核)/";
				}
				if(aData.agencyAuditStatus=='03') {
					tempStr += "代理机构(未通过)/";
				}
			}
			$(nRow).find('td[name=useStatus]').empty().append(tempStr.substring(0,tempStr.length-1));

			if(aData.isOff==2){//如果是禁用,则添加"启用"按钮
				if(aData.useStatus=='01'){
					$(nRow).append('<td><a href="javascript:void(0);" onclick="OrgInfoManageList.view(\'view\',\'' + aData.objId + '\',\''+aData.buyerId+'\',\''+aData.agencyId+'\');return false;">查看</a><a href="javascript:void(0);" onclick="OrgInfoManageList.disableOrEnable(\'' + aData.objId + '\',1)">启用</a></td>')
				}else{
					$(nRow).append('<td><a href="javascript:void(0);" onclick="OrgInfoManageList.view(\'view\',\'' + aData.objId + '\',\''+aData.buyerId+'\',\''+aData.agencyId+'\');return false;">查看</a></td>')
				}
			}else{
				var str = '<td><a href="javascript:void(0);" onclick="OrgInfoManageList.view(\'update\',\'' + aData.objId+ '\',\''+aData.buyerId+'\',\''+aData.agencyId+'\');return false;">修改</a>';
				if($('#appType').val()=='XEJY' || aData.supplierId) {
					str += '<a href="javascript:void(0);" onclick="OrgInfoManageList.view(\'updateFinance\',\'' + aData.objId + '\');return false;">修改财务信息</a>';
					str += '<a href="javascript:void(0);" onclick="OrgInfoManageList.view(\'updateLegal\',\'' + aData.objId + '\');return false;">修改法务信息</a>';
				}
				str += '<a href="javascript:void(0);" onclick="OrgInfoManageList.view(\'view\',\'' + aData.objId + '\',\''+aData.buyerId+'\',\''+aData.agencyId+'\');return false;">查看</a>';
				if(aData.useStatus=='01'){
					str += '<a href="javascript:void(0);" onclick="OrgInfoManageList.disableOrEnable(\'' + aData.objId + '\',2);return false;">禁用</a>';
				}else{
				}
				str += '</td>';
				$(nRow).append(str);
			}
			
			return nRow;
		},
		"params":{"auditStatus":"01","useStatus":"00","auditStatus_relative":"[and:or]","useStatus_relative":"[and:or]"},
		"sAjaxSource" : $('#initPath').val()+ "/OrgInfoController.do?method=list&type=manage",
		'searchZone':'OrgInfoManageListForm'
	});
	
	// 查询
	$("#query").click(function() {
		if($("#startDate").val() != "" && $("#endDate").val() != ""){
			if($("#endDate").val() < $("#startDate").val()){
				alert("请输入正确的创建时间范围!");return;
			}
		}
		if($("#startDate").val().length > 0 && $("#endDate").val().length == 0){
			$(OrgInfoManageList.oTable.dataTableSettings).attr("params",
					$.extend(OrgInfoManageList.oTable.dataTableSettings[0].params,{"createTime":$("#startDate").val(),"createTime_op":"ge"}));
		}
		else if($("#endDate").val().length > 0 && $("#startDate").val().length == 0){
			$(OrgInfoManageList.oTable.dataTableSettings).attr("params",
					$.extend(OrgInfoManageList.oTable.dataTableSettings[0].params,{"createTime":$("#startDate").val(),"createTime_op":"le"}));
		}
		else if($("#endDate").val().length > 0 && $("#startDate").val().length > 0){
			$(OrgInfoManageList.oTable.dataTableSettings).attr("params",
					$.extend(OrgInfoManageList.oTable.dataTableSettings[0].params,{"createTime":$("#startDate").val()+","+$("#endDate").val(),"createTime_op":"bt"}));
		}else{
			$(OrgInfoManageList.oTable.dataTableSettings).attr("params",$.extend(OrgInfoManageList.oTable.dataTableSettings[0].params,{"createTime":""}));		
		}
		OrgInfoManageList.oTable.fnDraw();
		if($('#appType').val()=="" || $('#appType').val()==null) {
			$('#appType').val(appType);
		}
	})
	
	
	//创建采购人/供应商
	$("a[id^=createOrgInfoBtn_]").click(function(){
		var createType = $(this).attr('id').replace('createOrgInfoBtn_','');
		if(createType == 'supplier' || $('#appType').val()=='XEJY') {
			$('#conBody').loadPage($('#initPath').val()+"/view/bizplatform/organization/manager/create_orginfo.jsp?createType="+createType+"&appType="+$('#appType').val());
		} else {
			//协议供货创建采购人
			$('#conBody').loadPage($('#initPath').val()+"/ExOrgInfoController.do?method=toCreateBuerOrgInfo&appType="+$('#appType').val());
		}
	});
});

