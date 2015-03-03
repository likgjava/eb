
var BargainProjectList={};
BargainProjectList.oTable;	

BargainProjectList.currentTabID = "tabs_bargaining";

// 生成列表中每条数据的操作：根据Tab不同，生成“取消、确定”和“查看”超链接
BargainProjectList.getOperatorStr=function(objId,status,useStatus,evalStartTime,evalEndTime,projName,ebuyMethod){	
	var operatorStr = '<td>';
	
	/***********************竞价部分的逻辑先改*********************/
	if(ebuyMethod=='06'){
		//进入项目
		operatorStr += '<a class="checkBargainHistory" href="javascript:void(0);" onclick="BargainProjectList.openOperatorPage(\'viewProject\',\''+ebuyMethod+'\',\''+objId+'\');return false;" ><span>进入项目</span></a>';
		if(BargainProjectList.currentTabID == "tabs_begaining") {//即将开始竞价
			if(useStatus == '00') {//未提交
				operatorStr += '<a class="checkBargainHistory" href="javascript:void(0);" onclick="BargainProjectList.openOperatorPage(\'remove\',\''+ebuyMethod+'\',\''+objId+'\');return false;" ><span>删除</span></a>';
			}
		}
		else if(BargainProjectList.currentTabID == "tabs_over") {//竞价结束
			operatorStr += '<a class="checkBargainHistory" href="javascript:void(0);" onclick="BargainProjectList.openOperatorPage(\'invalid\',\''+ebuyMethod+'\',\''+objId+'\');return false;" ><span>作废</span></a>';
		}
	}
	/***********************竞价部分的逻辑先改*********************/
	
	
	
	
	
	/***********************议价部分的逻辑先不改*************************/
	if(ebuyMethod=='05'){
		
		if(BargainProjectList.currentTabID == "tabs_bargaining") {//竞价中
				operatorStr += '<a class="checkBargainHistory" href="javascript:void(0);" onclick="BargainProjectList.openOperatorPageForTalk(\'buerBargainHall\',\''+ebuyMethod+'\',\''+objId+'\');return false;" ><span>进入议价厅</span></a>';
		}
		else if(BargainProjectList.currentTabID == "tabs_begaining") {//即将开始竞价
			if(useStatus == '01') {//已提交
				operatorStr += '<a class="checkBargainHistory" href="javascript:void(0);" onclick="BargainProjectList.openOperatorPageForTalk(\'delay\',\''+ebuyMethod+'\',\''+objId+'\');return false;" ><span>延时</span></a>';
				operatorStr += '<a class="checkBargainHistory" href="javascript:void(0);" onclick="BargainProjectList.openOperatorPageForTalk(\'invalid\',\''+ebuyMethod+'\',\''+objId+'\');return false;" ><span>作废</span></a>';
			}else if(useStatus == '00') {//未提交
				operatorStr += '<a class="checkBargainHistory" href="javascript:void(0);" onclick="BargainProjectList.openOperatorPageForTalk(\'submit\',\''+ebuyMethod+'\',\''+objId+'\');return false;" ><span>提交</span></a>';
				operatorStr += '<a class="checkBargainHistory" href="javascript:void(0);" onclick="BargainProjectList.openOperatorPageForTalk(\'modifyProj\',\''+ebuyMethod+'\',\''+objId+'\');return false;" ><span>修改项目</span></a>';
				operatorStr += '<a class="checkBargainHistory" href="javascript:void(0);" onclick="BargainProjectList.openOperatorPageForTalk(\'remove\',\''+ebuyMethod+'\',\''+objId+'\');return false;" ><span>删除</span></a>';
			}
		}
		else if(BargainProjectList.currentTabID == "tabs_over") {//竞价结束
			//采购人手动结束 或者 竞价时间结束而采购人没有手动结束
			if( status=='170' || status=='160'){
				operatorStr += '<a class="checkBargainHistory" href="javascript:void(0);" onclick="BargainProjectList.openOperatorPageForTalk(\'result1\',\''+ebuyMethod+'\',\''+objId+'\');return false;" ><span>确定结果</span></a>';
			}
			else if (status=='200') {
				operatorStr += '<a class="checkBargainHistory" href="javascript:void(0);" onclick="BargainProjectList.openOperatorPageForTalk(\'viewResult\',\''+ebuyMethod+'\',\''+objId+'\');return false;" ><span>查看结果</span></a>';
			}
			operatorStr += '<a class="checkBargainHistory" href="javascript:void(0);" onclick="BargainProjectList.openOperatorPageForTalk(\'evaluate\',\''+ebuyMethod+'\',\''+objId +'\',\''+ projName +'\');return false;" ><span>评价</span></a>';
			operatorStr += '<a class="checkBargainHistory" href="javascript:void(0);" onclick="BargainProjectList.openOperatorPageForTalk(\'complaints\',\''+ebuyMethod+'\',\''+objId+'\');return false;" ><span>投诉</span></a>';
			operatorStr += '<a class="checkBargainHistory" href="javascript:void(0);" onclick="BargainProjectList.openOperatorPageForTalk(\'report\',\''+ebuyMethod+'\',\''+objId+'\');return false;" ><span>举报</span></a>';
			operatorStr += '<a class="checkBargainHistory" href="javascript:void(0);" onclick="BargainProjectList.openOperatorPageForTalk(\'invalid\',\''+ebuyMethod+'\',\''+objId+'\');return false;" ><span>作废</span></a>';
		}
		//查看项目
		operatorStr += '<a class="checkBargainHistory" href="javascript:void(0);" onclick="BargainProjectList.openOperatorPageForTalk(\'viewProject\',\''+ebuyMethod+'\',\''+objId+'\');return false;" ><span>查看</span></a>';
	}
	/***********************议价部分的逻辑先不改*************************/
	
	
	operatorStr += '</td>';
	return operatorStr;
}

//根据不同的操作，导向不同的页面
BargainProjectList.openOperatorPage=function(type,ebuyMethod,objId, projName){	 
	if("viewProject"==type){//查看项目
		if(ebuyMethod == '05') {//议价项目
			//$("#conBody").loadPage($("#initPath").val()+"/TalkProjectController.do?method=toProjectDetailView&userType=buyer&projectId="+objId );
			window.open($("#initPath").val()+"/TalkProjectController.do?method=toTalkProjectDetailView&userType=buyer&objId="+objId);
		} else {
			window.open( $("#initPath").val()+"/BargainProjectController.do?method=toProjectView&userType=buyer&projectId="+objId  );
		}
	}else if("remove"== type){//删除项目
		BargainProjectList.remove(objId,'remove');
	}else if("invalid"== type){//作废项目
		BargainProjectList.remove(objId,'invalid');
	}
}

/****************************为议价留下的逻辑********************************/
//根据不同的操作，导向不同的页面
BargainProjectList.openOperatorPageForTalk=function(type,ebuyMethod,objId, projName){	 
	if("announcement" == type){
		$('#conBody').loadPage($('#initPath').val()+"/BulletinAgreementController.do?method=toPublistBulletinFormView&projectId="+objId);
	}else if("view" == type){
		$('#conBody').loadPage($('#initPath').val()+"/BulletinAgreementController.do?method=toPublistBulletinFormView&projectId="+objId);
	}else if("sendInvitation" == type){
		$('#conBody').loadPage($('#initPath').val()+"/InvitationController.do?method=toSendInvitationForm&projectId="+objId);
	}else if("viewResult"== type){
		$("#conBody").loadPage($("#initPath").val()+"/BuyResultXyghController.do?method=toResultDetailView&appType=XEJY&projectId="+objId );
	}else if("buerBargainHall"==type){//进入竞价厅或议价厅
		var url = $('#initPath').val()+"/BargainProjectController.do?method=toBuyerBargainHall&objId="+objId;
		if(ebuyMethod == '05'){//进入议价厅
			url = $('#initPath').val()+"/TalkProjectController.do?method=toBuyerTalkHall&inType=buyer&objId="+objId;
		}
		loadPage_openModelWindow(url,"960");
		BargainProjectList.oTable.fnDraw();
	}else if("modifyProj"==type){//修改项目
		var url = $('#initPath').val()+"/BargainProjectController.do?method=toUpdateBargainProject&objId="+objId;
		if(ebuyMethod == '05'){//议价项目
			url = $('#initPath').val()+"/TalkProjectController.do?method=toUpdateTalkProjectView&objId="+objId;
		}
		$('#conBody').loadPage(url);
	}else if("modifyRule"==type){//修改规则
		var url = $('#initPath').val()+"/BargainProjectController.do?method=toUpdateTurnAndRule&objId="+objId;
		$('#conBody').loadPage(url);
	}else if("result1"== type){//成交结果
		$("#conBody").loadPage($("#initPath").val()+"/BuyResultXyghController.do?method=toConfirmResultBySupplierView&appType=XEJY&projectId="+objId );
	}else if("viewProject"==type){//查看项目
		if(ebuyMethod == '05') {//议价项目
			//$("#conBody").loadPage($("#initPath").val()+"/TalkProjectController.do?method=toProjectDetailView&userType=buyer&projectId="+objId );
			window.open($("#initPath").val()+"/TalkProjectController.do?method=toTalkProjectDetailView&userType=buyer&objId="+objId);
		} else {
			window.open( $("#initPath").val()+"/BargainProjectController.do?method=toProjectView&userType=buyer&projectId="+objId  );
			//$("#conBody").loadPage($("#initPath").val()+"/BargainProjectController.do?method=toProjectDetailView&userType=buyer&projectId="+objId ); //原来的链接
		}
	}else if("remove"== type){//删除项目
		BargainProjectList.remove(objId,'remove');
	}else if("delay"== type){//延时项目
		BargainProjectList.delay(objId);
	}else if("invalid"== type){//作废项目
		BargainProjectList.remove(objId,'invalid');
	}else if("submit" == type){//提交项目
		BargainProjectList.submitProject(objId,ebuyMethod);
	}else if("evaluate"== type){//评价
		BargainProjectList.openEvaluateDiv(objId,projName);
	}else if("complaints"== type){//投诉
		BargainProjectList.openComplainDiv(objId,'tell',ebuyMethod);
	}else if("report"== type){//举报
		BargainProjectList.openComplainDiv(objId,'complain',ebuyMethod);
	}
}

//延时项目
BargainProjectList.delay=function(objId){
	var url = $('#initPath').val() + "/BargainProjectController.do?method=toDelayProjectTime&objId="+objId;
	$('#conBody').loadPage(url);
} 
//删除或作废项目
BargainProjectList.remove=function(objId,removeType){
	var url = $('#initPath').val() + "/BargainProjectController.do?method=removeRBProject&type="+removeType;
	var msg = "确定删除该项目吗";
	if(removeType=="invalid") {
		msg = "确定作废该项目吗";
	}
	if(window.confirm(msg)){
		$.getJSON(url,{"objId":objId},function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			BargainProjectList.oTable.fnDraw();
		});
	}
} 
//提交项目
BargainProjectList.submitProject=function(objId,ebuyMethod){
	if(window.confirm("确定提交该项目吗")){
		//提示扩展信息的填写
		if(ebuyMethod == '06') {
			$.getJSON($('#initPath').val() + "/BargainProjectController.do?method=getProjectExInfo&projectId="+objId,{},function(json){
				if(json.failure){if(json.result)alert(json.result);return;}
				
				var res = '';
				if(json.ruleCount==0) {
					res += '规则和轮次信息,';
				}
				if(json.signCount==0) {
					res += '报名条件,';
				}
				if(json.payCount==0) {
					res += '交付信息,';
				}
				if(json.contactCount==0) {
					res += '联系方式,';
				}
				
				if(res.length > 0) {
					res = res.substring(0,res.length-1);
					alert('您的' + res + '未填写,请填写完整');
				}else{
					if(json.requireCount<1) {
						alert('您未填写需求条目!');
					}else {
						$.getJSON($('#initPath').val() + "/BargainProjectController.do?method=toSubmitProject&projectId="+objId+"&type=s",{"objId":objId},function(json){
							if(json.failure){if(json.result)alert(json.result);return;}
							BargainProjectList.oTable.fnDraw();
						});
					}
				}
			});
		}else if(ebuyMethod == '05'){
			$.getJSON($('#initPath').val() + "/TalkProjectController.do?method=toSubmitProject&projectId="+objId,{"objId":objId},function(json){
				if(json.failure){if(json.result)alert(json.result);return;}
				BargainProjectList.oTable.fnDraw();
			});
		}
	}
} 

//评价(显示被评价的人)
BargainProjectList.openEvaluateDiv = function(projectId,projectName){
	$.epsDialog({
		id:"evaluateDailog", 
		title:"对参与项目的机构评价",
		url:$("#initPath").val()+"/view/agreement/bargin/project/project_evaluate_div.jsp?userType=buyer&projectId="+projectId+"&projectName="+native2ascii(projectName)
	});
}

//投诉 /举报(显示被投诉/举报的人)
BargainProjectList.openComplainDiv = function(projectId,type,ebuyMethod){	
	/*$.getJSON($("#initPath").val()+"/SignUprecordController.do?method=getObjectQuery&queryColumns=supplier.objId,supplier.orgName,createUser.objId,createUser.emp.name",{"project.objId":projectId},function(json){
		if(json.success){			
			var ahref = '';			
			$.each(json.result,function(index,obj){	
				var userId = "";
				var orgId = "";
				var userName = "";
				var orgName = "";
				var email = "";				
				$.each(obj,function(key,ele){					
					if(key=="supplier.objId"){						
						orgId = ele;
					}
					if(key=="supplier.orgName"){						
						orgName = ele;
					}
					if(ebuyMethod != '05'){//议价项目 不取创建人
						if(key=="createUser.objId"){						
							userId = ele;
						}
						if(key=="createUser.emp.name"){						
							userName = ele;
						}
					}
				})
				
				ahref += '<li>&nbsp;<a href="javascript:void(0);" onclick="BargainProjectList.openComplainClick(\''+projectId+'\',\''+userId+'\',\''+orgId+'\',\''+userName+'\',\''+orgName+'\',\''+type+'\');return false;">'+orgName+'</a>&nbsp;</li>';			
				
			})
			var complainDivHtml = "";
			if(type=='tell'){				
				complainDivHtml = '<div class="formTips attention"><ul><li><em>被投诉机构</em></li></ul></div>'
			}
			else{
				complainDivHtml = '<div class="formTips attention"><ul><li><em>被举报机构</em></li></ul></div>'
			}
			complainDivHtml += '<div class="formLayout form2Pa"><ul>'+ahref+'</ul></div><div class="conOperation"><button id="complainDivBtn" type="button" class="largeBtn"><span>关闭</span></button></div>';
			$.epsDialog({id:"complainDiv", title:"选择参与项目的机构",content:complainDivHtml,width: 600,height: 300});
			//关闭
			$("#complainDivBtn").click(function(){$('.epsDialogClose').trigger('click');})
		}
	})*/
	
	$.epsDialog({
		id:"complainDiv", 
		title:"选择参与项目的机构",
		url:$("#initPath").val()+"/view/agreement/bargin/project/project_complain_div.jsp?userType=buyer&projectId="+projectId+"&type="+type+"&ebuyMethod="+ebuyMethod
	});

}

//弹出投诉举报
BargainProjectList.openComplainClick = function(projectId,userId,orgId,userName,orgName,type){
	var params = '&type='+type+'&beCompanyId='+orgId+'&beCompanyName='+orgName+"&beComplain="+userId + '&beComplainName='+userName + '&projectId=' + projectId ;
	var url = '/ComplainController.do?method=toCreateComplain'+params;
	$.epsDialog({
		id:'complainDailog',
        title:'投诉举报',
        url:$('#initPath').val()+url,
        width: '700',
        height: '500'
    }); 
}
/****************************为议价留下的逻辑********************************/


//创建或刷新列表数据
BargainProjectList.getTableList = function() {
	if(!BargainProjectList.oTable) {
		//项目列表
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
				//添加按钮
				$(nRow).append(BargainProjectList.getOperatorStr(aData['objId'],aData['projProcessStatus'],aData['useStatus'],aData['evalStartTime'].substring(0,19),aData['evalEndTime'].substring(0,19),aData['projName'],aData['ebuyMethod']));
				$(nRow).find("td[name=evalStartTime]").empty().append(aData['evalStartTime'].substring(0,16));
				$(nRow).find("td[name=evalEndTime]").empty().append(aData['evalEndTime'].substring(0,16));
				return nRow;
			},
			'params':{'status':BargainProjectList.currentTabID.replace("tabs_","")},//竞价中
			'searchZone':'projectSearchForm',
			"sAjaxSource": $('#initPath').val()+'/BargainProjectController.do?method=list&orgType=a'
		});
	}else {
		$(BargainProjectList.oTable.dataTableSettings).attr("params",{'status':BargainProjectList.currentTabID.replace("tabs_","")});
		BargainProjectList.oTable.fnDraw();
	}
}

$(document).ready(function(){
	//设定返回路径
	$("#returnUrl").val("view/agreement/bargin/project/bargain/bargain_project_list.jsp")
	
	//加载tabs,绑定选中事件为加载列表
	var $tabs = $('#epsTabs').tabs({
		select: function(event, ui) {
			BargainProjectList.currentTabID = ui.tab.id;
			$("#currentTab").val(ui.index); //当前tab的index
			BargainProjectList.getTableList();
		}
	});
	//指定某一个tab被选中，默认值为0
	$tabs.tabs('select', parseInt($("#currentTab").val()));
	
	//tab无法触发第一个选中，所以需要手动加载一次
	if($("#currentTab").val() == "0") {
		BargainProjectList.getTableList();
	}
	
	//创建竞价项目
	$("#createBargainProjectBtn").click(function(){
		window.open($('#initPath').val()+"/BargainProjectController.do?method=toCreateBidProject_1");
	});
});
	
