var client_bargaree_record_list = {};
client_bargaree_record_list.oTable1;
client_bargaree_record_list.oTable2;
client_bargaree_record_list.oTable3;
client_bargaree_record_list.currentTabId="my_orders_create";

var ebuyMethod={"05":"议价","06":"竞价","07":"反拍"};

//获取列表数据
client_bargaree_record_list.getTableList = function(){
	var clienterId = $('#clienterId').val();
	var currentOrgId = $('#currentOrgId').val();
	var currentUserId = $("#currentUser").val();
	var clientUserrId = $("#clientUser").val();
	//我发起的订单
	if(client_bargaree_record_list.currentTabId == "my_orders_create"){
		if(!client_bargaree_record_list.oTable1){
			client_bargaree_record_list.oTable1 = $('#orderInfoTableList').dataTable({
				'singleSelect':true,
				'checkbox':false,
				'queryColumns':'orderNo,categoryNames,goodsQty,goodsTotal,createTime,contract.contractNo,payStatus',
				'hiddenColumns':'buyer.objId,supplier.objId',
				'fnInitComplete':function(oSettings) {
					 //表格初始化完毕、未开始查询之前的方法
				},
				'fnDrawCallback':function(oSettings) {	
					client_bargaree_record_list.oTable1.oSettings=oSettings;
				},
				'params':{'buyer.objId':currentOrgId,'supplier.objId':clienterId},
				'searchZone':'purchaserOrderForm',
				'sAjaxSource': $('#initPath').val()+"/OrderController.do?method=list",
				'fnRowCallback': function( nRow, aData, iDisplayIndex ) {//查询完毕每行的回填事件	
					$(nRow).append('<td><a href="javascript:void(0);" name="viewOrder">查看</a></td>');
					$(nRow).find("a[name=viewOrder]").click(function(){
						$("#returnUrl").val($('#conBody').loadPage($('#initPath').val()+"/ConcernController.do?method=clientBargareeRecord&objId="+$('#concernId').val()));
						$('#conBody').loadPage($('#initPath').val()+"/OrderController.do?method=toOrderForm&appType=XEJY&navigate=bdetail&type=draft_contract&objId="+aData.objId+"&appType=XEJY");
					});
				
					//特殊处理
					$(nRow).find("td[name=payStatus]").html('<font color="'+ ( aData.payStatus=='01'?'green':'red' ) +'">'+( aData.payStatus=='01'?'已支付':'未支付' )+'</font>');
					return nRow;
				}
			});
		}else{
			$(client_bargaree_record_list.oTable1.dataTableSettings).attr('params', {'buyer.objId':currentOrgId,'supplier.objId':clienterId});
			client_bargaree_record_list.oTable1.fnDraw();
		}
	}
	//我接到的订单
	if(client_bargaree_record_list.currentTabId == "my_orders_receive"){
		$(client_bargaree_record_list.oTable1.dataTableSettings).attr('params', {'buyer.objId':clienterId,'supplier.objId':currentOrgId});
		client_bargaree_record_list.oTable1.fnDraw();
	}
	
	//我发起的项目记录
	if(client_bargaree_record_list.currentTabId == "my_create_projects"){
		if(!client_bargaree_record_list.oTable2){
			client_bargaree_record_list.oTable2 = $('#projectsInfoTableList').dataTable({
				'singleSelect':true,
				'checkbox':false,
				'queryColumns':'projName,ebuyMethod,createTime,buyrResult,isDeal',
				'hiddenColumns':'',
				'alias':'projName,ebuyMethodCN,createTime,buyrResultCN,isDealCN',
				'fnInitComplete':function(oSettings) {
				},
				'fnDrawCallback':function(oSettings) {	
					client_bargaree_record_list.oTable2.oSettings=oSettings;
				},
				'params':{},
				'searchZone':'signUpProjectSearch',
				"sAjaxSource": $('#initPath').val()+'/ProjectQueryController.do?method=getExchangeProjectRecord&supplierId='+clienterId+'&createUserId='+currentUserId,
				'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
					//$(nRow).find("td:eq(1)").html(ebuyMethod[aData['project.ebuyMethod']]);	
					$(nRow).append('<td></td>');
					//查看项目
					$(nRow).find("td:last").append('<a href="javascript:void(0);" name="viewResult">进入项目</a>');
					$(nRow).find("a[name=viewResult]").click(function(){
						var showProjUrl = $("#initPath").val()+"/BargainProjectController.do?method=toProjectView&userType=buyer&projectId="+aData.objId ;
						if(aData["ebuyMethod"] == '05'){//议价项目
							//showProjUrl = $("#initPath").val()+"/TalkProjectController.do?method=toProjectDetailView&userType=buyer&projectId="+aData.objId ;
							showProjUrl = $("#initPath").val()+"/TalkProjectController.do?method=toTalkProjectDetailView&userType=buyer&objId="+aData.objId ;
						}
						window.open(showProjUrl);
					});
					return nRow;
				}
			});
		}else{
			$(client_bargaree_record_list.oTable2.dataTableSettings).attr('params', {});
			client_bargaree_record_list.oTable2.fnDraw();
		}
	}
	
	//我报名参与的项目记录
	if(client_bargaree_record_list.currentTabId == "my_baoming_projects"){
		if(!client_bargaree_record_list.oTable3){
			client_bargaree_record_list.oTable3 = $('#signUpProjectList').dataTable({
				'singleSelect':true,
				'checkbox':false,
				'queryColumns':'projName,projCode,ebuyMethod,createTime,buyrResult,isDeal',
				'hiddenColumns':'',
				'alias':'projName,projCode,ebuyMethodCN,createTime,buyrResultCN,isDealCN',
				'fnInitComplete':function(oSettings) {
				},
				'fnDrawCallback':function(oSettings) {	
					client_bargaree_record_list.oTable3.oSettings=oSettings;
				},
				'params':{},
				'searchZone':'signUpProjectSearch',
				"sAjaxSource": $('#initPath').val()+'/ProjectQueryController.do?method=getExchangeProjectRecord&supplierId='+$("#currentOrgId").val()+'&createUserId='+clientUserrId,
				'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
					//$(nRow).find("td:eq(2)").html(ebuyMethod[aData['project.ebuyMethod']]);	
					$(nRow).append('<td></td>');
					//查看项目
					$(nRow).find("td:last").append('<a href="javascript:void(0);" name="viewResult">进入项目</a>');
					$(nRow).find("a[name=viewResult]").click(function(){
						var showProjUrl = $("#initPath").val()+"/BargainProjectController.do?method=toProjectView&userType=buyer&projectId="+aData.objId ;
						if(aData["ebuyMethod"] == '05'){//议价项目
							//showProjUrl = $("#initPath").val()+"/TalkProjectController.do?method=toProjectDetailView&userType=buyer&projectId="+aData.objId ;
							showProjUrl = $("#initPath").val()+"/TalkProjectController.do?method=toTalkProjectDetailView&userType=buyer&objId="+aData.objId ;
						}
						window.open(showProjUrl);
					});
					return nRow;
				}
			});
		}else{
			$(client_bargaree_record_list.oTable3.dataTableSettings).attr('params', {});
			client_bargaree_record_list.oTable3.fnDraw();
		}
	}
}

$(document).ready(function(){
	//tab无法触发第一个选中，所以需要手动加载一次
	client_bargaree_record_list.getTableList();
	
	//tabs页，绑定选中事件加载列表
	var tabs = $('#epsTabs').tabs({
			select: function(event,ui){
			client_bargaree_record_list.currentTabId = ui.tab.id;
			$("#currentTab2").val(ui.index);
			client_bargaree_record_list.getTableList();
		}
	});	
	
	//指定某一个tab被选中，默认值0
	tabs.tabs('select',parseInt($("#currentTab2").val()));
	
	
});