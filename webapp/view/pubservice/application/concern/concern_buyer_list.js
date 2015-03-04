var ConcernList={};
ConcernList.oTable;	
ConcernList.currentTabID="listType_01"; //当前Tab的ID

//管理客户分组
$("#editConcernGroupBtn").click(function(){
	$.epsDialog({
		title:"管理客户分组",
		width:500,
		height:300,
		url:$('#initPath').val()+"/view/pubservice/application/concern/edit_concern_group_form.jsp",
		afterLoad: function(){ },
		onClose: function(){ 
			$('#conBody').loadPage($('#initPath').val()+"/ConcernController.do?method=toConcernList&currentTab="+$('#currentTab').val());
    	}
	});
	return false;
});

//获取列表数据，如果列表没有，则创建，否则刷新数据
ConcernList.getTableList = function() {
	if(!ConcernList.oTable) {		
		ConcernList.oTable=$('#ConcernList').dataTable( {
			'searchZone':'concernListForm',
			'singleSelect':true,
			'checkbox':false,
			'queryColumns':'orgInfo.orgName,concernGroup.groupName,bargainSumNum,bargainSumMoney,bargainLastlyDate',
			'alias':'',
			'hiddenColumns':'objId,concernGroup.objId,listType,orgInfo.objId,concernGroup.belongsOrg.objId,belongsUser.objId',
			'fnInitComplete':function(oSettings) {},
			'fnDrawCallback':function(oSettings) {ConcernList.oTable.oSettings=oSettings;},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				
				//客户名称点击事件
				 $(nRow).find('td[name=orgInfo.orgName]').empty().append('<a href="javascript:void(0);" title="'+aData['orgInfo.orgName']+'">'+aData['orgInfo.orgName']+'</a>').find("a").click(function(){
					var url = $('#initPath').val()+'/ExOrgInfoController.do?method=getExAllBaseInfo&orgId='+aData['orgInfo.objId'];
					$.epsDialog({
				        title:'客户基本信息',
				        url:url,
				        width: '900',
				        height: '500'
				    }); 
				 });
				
				// 最近交易日期
				if(aData.bargainSumNum == 0){
					$(nRow).find('td[name=bargainLastlyDate]').empty().append("暂无成交记录");
				}
				 
				if(aData.listType != "03"){
					 //修改分组
					 $(nRow).append('<td><a href="javascript:void(0);" name="modifyGroup"><span>修改分组</span></a></td>');
					 $(nRow).find("a[name=modifyGroup]").click(function(){
						 $.epsDialog({
								title:"关注客户",
								width:500,
								height:300,
								url:$('#initPath').val()+"/ConcernController.do?method=toAddConcernForm&objId="+aData['objId']+"&concernGroupId="+aData['concernGroup.objId']+"&listType="+aData['listType']+"&orgInfoId="+aData['orgInfo.objId'],
								afterLoad: function(){$('#orgInfoName').html(aData['orgInfo.orgName']) },
								onClose: function(){ 
									$('#conBody').loadPage($('#initPath').val()+"/ConcernController.do?method=toConcernList&currentTab="+$('#currentTab').val());
						    	}
						 });
					 });
				}else{
					$(nRow).append('<td></td>');
				}
				 
				 //删除
				 $(nRow).find('td:last').append('<a href="javascript:void(0);" name="delete"><span>删除</span></a>');
				 $(nRow).find('a[name=delete]').click(function(){
					 if(confirm("确认要删除客户吗？")){
						$.ajax({
							url:$('#initPath').val()+'/ConcernController.do?method=remove',
							type:'POST',
							data:{objId:aData['objId']},
							async:false,
							dataType:'json',
							success:function(json){
								if(json.success){
									alert("删除成功！");
									ConcernList.getTableList();
								}
							}
						})
					 }
				 });
				 
				 if(aData['belongsUser.objId'] != null && aData['belongsUser.objId'] != ''){
					 if(aData.listType != "02"){
						//移至黑名单
						$(nRow).find('td:last').append('<a href="javascript:void(0);" name="addToHacker"><span>移至黑名单</span></a>');
						$(nRow).find("a[name=addToHacker]").click(function(){
							if(confirm("确认移至黑名单吗？")){
								$.getJSON($("#initPath").val()+"/ConcernController.do?method=transferInOrOutHacker",{"objId":aData.objId,"listType":"02"},function(json){
									if(json.result=='true'){alert("操作成功!");ConcernList.reload();}
								});
							}
						});
					 }
				 }
				
				 if(aData.listType != "01"){
					//移至我的客户
					$(nRow).find('td:last').append('<a href="javascript:void(0);" name="addToClient"><span>移至我的客户</span></a>');
					$(nRow).find("a[name=addToClient]").click(function(){
						if(confirm("确认移至我的客户吗？")){
							$.getJSON($("#initPath").val()+"/ConcernController.do?method=transferInOrOutHacker",{"objId":aData.objId,"listType":"01"},function(json){
								if(json.result=='true'){alert("操作成功!");ConcernList.reload();}
							});
						}
					});
				 }
				
				if(aData.listType != "03"){
					//交易记录
					$(nRow).find('td:last').append('<a href="javascript:void(0);" name="bargainRecords"><span>交易记录</span></a>');
					$(nRow).find("a[name=bargainRecords]").click(function(){
						$('#conBody').loadPage($('#initPath').val()+"/ConcernController.do?method=clientBargareeRecord&objId="+aData.objId);
					});
				}
				
				if((aData['belongsUser.objId'] == null || aData['belongsUser.objId'] == '') && $('#isOrgManager').val() != 'true'){
					//交易记录
					$(nRow).find('td:last').empty().append('<a href="javascript:void(0);" name="bargainRecords"><span>交易记录</span></a>');
					$(nRow).find("a[name=bargainRecords]").click(function(){
						$('#conBody').loadPage($('#initPath').val()+"/ConcernController.do?method=clientBargareeRecord&objId="+aData.objId);
					});
				}
				   
				return nRow;
			},
			'params':{'listType':ConcernList.currentTabID.replace("listType_",""),'belongsUser.objId':$('#curUserId').val()},
			"sAjaxSource": $('#initPath').val()+'/ConcernController.do?method=list'
		});
	}else{
		if(ConcernList.currentTabID == "listType_03"){
			$(ConcernList.oTable.dataTableSettings).attr("params",{'listType':'01','belongsUser.objId':null,'belongsUser.objId_op':'is','concernGroup.belongsOrg.objId':$('#curOrgInfoId').val()});			
		}else{
			$(ConcernList.oTable.dataTableSettings).attr("params",{'listType':ConcernList.currentTabID.replace("listType_",""),'belongsUser.objId':$('#curUserId').val()});			
		}
		ConcernList.oTable.fnDraw();
	}
}

//查询
$("#query").click(function(){
	ConcernList.oTable.fnDraw();
});

//刷新
ConcernList.reload = function(){
	ConcernList.oTable.fnDraw();
}

$(document).ready(function(){
	//设定返回路径
	$("#returnUrl").val("ConcernController.do?method=toConcernList")
	
	//tabs页，绑定选中事件加载列表
	var tabs = $('#epsTabs').tabs({
		select: function(event,ui){
			ConcernList.currentTabID = ui.tab.id;
			$("#currentTab").val(ui.index);
			ConcernList.getTableList();
		}
	});	
	
	//指定某一个tab被选中，默认值0
	tabs.tabs('select',parseInt($("#currentTab").val()));
	if($("#currentTab").val() == "0"){
		ConcernList.getTableList();
	}
});
	
