var BargainProjectList={};
BargainProjectList.oTable;	

//获取操作字符串
BargainProjectList.getOperatorStr=function(objId,ebuyMethod,useStatus,evalStartTimeStr,evalEndTimeStr,projProcessStatus){
	var operatorStr = '<td class="operation">';
	//进入项目
	operatorStr += '<a class="checkBargainHistory" href="javascript:void(0);" onclick="BargainProjectList.openOperatorPage(\'viewProject\',\''+ebuyMethod+'\',\''+objId+'\');return false;" ><span>进入项目</span></a>';
	
	//未提交
	if(useStatus == '00'){
		operatorStr += '<a class="checkBargainHistory" href="javascript:void(0);" onclick="BargainProjectList.openOperatorPage(\'remove\',\''+ebuyMethod+'\',\''+objId+'\');return false;" ><span>删除</span></a>';
	}
	//已提交
	else{
		operatorStr += '<a class="checkBargainHistory" href="javascript:void(0);" onclick="BargainProjectList.openOperatorPage(\'invalid\',\''+ebuyMethod+'\',\''+objId+'\');return false;" ><span>作废</span></a>';
	}
	operatorStr += '</td>';
	return operatorStr;
}

//根据不同的操作，导向不同的页面
BargainProjectList.openOperatorPage=function(type,ebuyMethod,objId){
	//查看项目
	if("viewProject"==type){
		//议价项目
		if(ebuyMethod == '05'){
			window.open($("#initPath").val()+"/TalkProjectController.do?method=toTalkProjectDetailView&userType=buyer&objId="+objId );
		}
		//竞价项目
		else{
			window.open( $("#initPath").val()+"/BargainProjectController.do?method=toProjectView&userType=buyer&projectId="+objId  );
		}
	}
	//删除项目
	else if("remove"== type){
		BargainProjectList.remove(objId,'remove');
	}
	//作废项目
	else if("invalid"== type){
		BargainProjectList.remove(objId,'invalid');
	}
}

//删除或作废项目
BargainProjectList.remove=function(objId,removeType){
	var url = $('#initPath').val() + "/BargainProjectController.do?method=removeRBProject&type="+removeType;
	var msg = "确定删除该项目吗";
	if(removeType=="invalid") { msg = "确定作废该项目吗"; }
	
	if(window.confirm(msg)){
		$.getJSON(url,{"objId":objId},function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			BargainProjectList.oTable.fnDraw();
		});
	}
}

//判断项目状态
BargainProjectList.checkProjectStatus = function(useStatus, projProcessStatus){
	var projectStatus = '';
	//未发布
	if(useStatus == '00'){
		projectStatus = '未发布';
	}
	//已结束
	else if(projProcessStatus >= 170){
		projectStatus = '已结束';
	}
	//进行中
	else{
		projectStatus = '进行中';
	}
	return projectStatus;
}

//创建或刷新列表数据
BargainProjectList.getTableList = function() {
	if(!BargainProjectList.oTable) {
		BargainProjectList.oTable=$('#BargainProjectList').dataTable( {
			'singleSelect':true,
			'checkbox':false,
			'queryColumns':'projCode,projName,ebuyMethod,evalStartTime,evalEndTime',
			'alias':'projCode,projName,ebuyMethodCN,evalStartTime,evalEndTime',
			'hiddenColumns':'projProcessStatus,useStatus',
			'fnInitComplete':function(oSettings) {
			},
			'fnDrawCallback':function(oSettings) {	
				BargainProjectList.oTable.oSettings=oSettings;
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				//报价未结束的项目，报价时间特殊标记
				var evalEndTime = StringToDate(aData.evalEndTime.replace('-', "/").replace('-', "/")).getTime();
				if(gpcsoftDate.getTime() < evalEndTime){$(nRow).find('td[name=evalEndTime]').addClass('evalTime'); }
				//判断项目状态
				$(nRow).append('<td class="center">'+BargainProjectList.checkProjectStatus(aData.useStatus,aData.projProcessStatus)+'</td>');
				//添加按钮
				$(nRow).append(BargainProjectList.getOperatorStr(aData['objId'],aData['ebuyMethod'],aData['useStatus'],aData['evalStartTime'],aData['evalEndTime'],aData['projProcessStatus']));
				$(nRow).find("td[name=evalStartTime]").empty().append(aData['evalStartTime'].substring(0,16));
				$(nRow).find("td[name=evalEndTime]").empty().append(aData['evalEndTime'].substring(0,16));
				return nRow;
			},
			'params':{},
			'searchZone':'projectSearchForm',
			"sAjaxSource": $('#initPath').val()+'/BargainProjectController.do?method=list&orgType=b'
		});
	}else {
		BargainProjectList.oTable.fnDraw();
	}
}

$(document).ready(function(){
	//设定返回路径
	$("#returnUrl").val("view/agreement/bargin/project/bargain/bargain_project_list.jsp")
	
	//加载列表数据
	BargainProjectList.getTableList();
	
	//创建竞价项目
	$("#createBargainProjectBtn").click(function(){
		window.open($('#initPath').val()+"/BargainProjectController.do?method=toCreateBidProject_1");
	});
});
	
