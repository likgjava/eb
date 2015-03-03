/** 上级公司监控下级公司项目列表页面 */
var OrgProjectMonitorList = {};
OrgProjectMonitorList.tree;
OrgProjectMonitorList.oTable;

//初始化组织机构树
OrgProjectMonitorList.initOrgnizationTree = function(companyId){
	OrgProjectMonitorList.tree = new dhtmlXTreeObject("orgnizationTreeGrid","100%","100%",0);
	OrgProjectMonitorList.tree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	OrgProjectMonitorList.tree.setOnClickHandler(OrgProjectMonitorList.nodeClick);
	OrgProjectMonitorList.tree.setXMLAutoLoading("OrganizationController.do?method=getTree&order=sort&action=listById&isOpen=0");
	OrgProjectMonitorList.tree.loadXML($("#initPath").val()+"/OrganizationController.do?method=getTree&id=1&action=listById&order=sort&objId="+companyId,function(){
		OrgProjectMonitorList.nodeClick(companyId);
		OrgProjectMonitorList.tree.selectItem(companyId);
	});
}

//点击节点事件
OrgProjectMonitorList.nodeClick = function(id){
	var nodeLevel = OrgProjectMonitorList.tree.getLevel(id); //获取节点层级
	//显示上级公司的项目
	if(nodeLevel == 1){
		OrgProjectMonitorList.getProjectList($('#orgInfoId').val());
	}
	//显示下级公司的项目
	else{
		//通过companyId获取orgInfoId
		$.getJSON($("#initPath").val()+"/OrgInfoController.do?method=getObjectQuery", {"queryColumns":"objId", "company.objId":id}, function(json){
			if(json.success){
				OrgProjectMonitorList.getProjectList(json.result[0].objId);
			}
		});
	}
}

//获取项目列表数据
OrgProjectMonitorList.getProjectList = function(orgInfoId) {
	if(!OrgProjectMonitorList.oTable) {
		OrgProjectMonitorList.oTable=$('#projectList').dataTable( {
			'singleSelect':true,
			'checkbox':false,
			'queryColumns':'projName,ebuyMethod,evalStartTime,evalEndTime',
			'alias':'projName,ebuyMethodCN,evalStartTime,evalEndTime',
			'hiddenColumns':'useStatus,projProcessStatus',
			'fnInitComplete':function(oSettings) {
			},
			'fnDrawCallback':function(oSettings) {	
				OrgProjectMonitorList.oTable.oSettings=oSettings;
			},
			'fnRowCallback': function(nRow, aData, iDisplayIndex) {
				//判断项目状态
				$(nRow).append('<td class="center">'+OrgProjectMonitorList.checkProjectStatus(aData.useStatus,aData.evalStartTime,aData.evalEndTime,aData.projProcessStatus)+'</td>');
				//添加按钮
				var oper = '<td class="operation"><a href="javascript:void(0);" onclick="OrgProjectMonitorList.showProjectDetail(\''+aData['objId']+'\',\''+aData['ebuyMethod']+'\');return false;" ><span>进入项目</span></a></td>';
				$(nRow).append(oper);
				$(nRow).find("td[name=evalStartTime]").empty().append(aData['evalStartTime'].substring(0,16));
				$(nRow).find("td[name=evalEndTime]").empty().append(aData['evalEndTime'].substring(0,16));
				return nRow;
			},
			'params':{'buyersId':orgInfoId},
			'searchZone':'projectSearchForm',
			"sAjaxSource": $('#initPath').val()+'/BargainProjectController.do?method=list'
		});
	}else {
		$(OrgProjectMonitorList.oTable.dataTableSettings).attr("params",{'buyersId':orgInfoId});
		OrgProjectMonitorList.oTable.fnDraw();
	}
}

//判断项目状态
OrgProjectMonitorList.checkProjectStatus = function(useStatus,evalStartTimeStr,evalEndTimeStr,projProcessStatus){
	var evalStartTime = StringToDate(evalStartTimeStr.replace('-', "/").replace('-', "/")).getTime();
	var evalEndTime = StringToDate(evalEndTimeStr.replace('-', "/").replace('-', "/")).getTime();
	var nowTime = new Date().getTime();
	var projectStatus = '';
	
	//未开始
	if(useStatus=='00' || evalStartTime>nowTime || (useStatus=='01' && projProcessStatus=='20')){
		projectStatus = '未开始';
	}
	//进行中
	else if(evalStartTime<nowTime && evalEndTime>nowTime && projProcessStatus=='160'){
		projectStatus = '进行中';
	}
	//已结束
	else if(useStatus=='01' && (evalEndTime<nowTime || projProcessStatus=='170')){
		projectStatus = '已结束';
	}
	return projectStatus;
}

//查看项目信息
OrgProjectMonitorList.showProjectDetail = function(objId, ebuyMethod){
	//议价项目
	if(ebuyMethod == '05'){
		window.open($("#initPath").val()+"/TalkProjectController.do?method=toTalkProjectDetailView&userType=manager&objId="+objId );
	}
	//竞价项目
	else{
		window.open($("#initPath").val()+"/BargainProjectController.do?method=toProjectView&userType=buyer&projectId="+objId);
	}
}

$(document).ready(function(){
	//初始化组织机构树
	OrgProjectMonitorList.initOrgnizationTree($("#companyId").val());
});
