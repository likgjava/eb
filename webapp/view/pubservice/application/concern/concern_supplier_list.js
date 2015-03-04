var ConcernList={};
ConcernList.oTable;	
ConcernList.currentTabID="listType_01"; //当前Tab的ID

// 生成列表中每条数据的操作：根据Tab不同，生成“取消、确定”和“查看”超链接
ConcernList.getOperatorStr=function(){	
	var operatorStr = '<td>';
	operatorStr += '<a href="javascript:void(0);" name="modify" title="修改分组" >修改分组</a><a href="javascript:void(0);" name="delete" title="删除">删除</a>';
	operatorStr += '</td>';
	return operatorStr;
}

//获取列表数据，如果列表没有，则创建，否则刷新数据
ConcernList.getTableList = function() {
	if(!ConcernList.oTable) {
		//关注供应商列表
		ConcernList.oTable=$('#ConcernList').dataTable( {
			'searchZone':'concernListForm',
			'singleSelect':true,
			'checkbox':false,
			'queryColumns':'orgInfo.orgName,concernGroup.groupName,createTime',
			'alias':'orgInfo.orgName,concernGroup.groupName,createTime',
			'hiddenColumns':'objId,concernGroup.objId,concernGroup.groupType,listType,orgInfo.objId,orgInfo.orgName',
			'fnInitComplete':function(oSettings) {
			},
			'fnDrawCallback':function(oSettings) {	
				ConcernList.oTable.oSettings=oSettings;
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				 $(nRow).find('td[name=orgInfo.orgName]').empty().append('<a href="javascript:void(0);" title="'+aData['orgInfo.orgName']+'">'+aData['orgInfo.orgName']+'</a>').find("a").click(function(){
					$.epsDialog({
						id:"showSupplierDetail",
						title:"机构详情",
						url:$("#initPath").val()+"/ExOrgInfoController.do?method=getExAllBaseInfo&orgId="+aData['orgInfo.objId']
					})
					 return false;
				 });
				   
				//添加按钮
				$(nRow).append(ConcernList.getOperatorStr()).find("a[name=modify]").click(function(){
					ConcernList.epsDialog(aData['objId'],aData['concernGroup.objId'],aData['concernGroup.groupType'],aData['listType'],aData['orgInfo.objId'],aData['orgInfo.orgName']);
				}).end().find("a[name=delete]").click(function(){
					
					if(window.confirm("确认要删除对["+aData['orgInfo.orgName']+"]的关注吗?")){
						$.ajax({
							url:$('#initPath').val()+'/ConcernController.do?method=remove',
							type:'POST',
							data:{objId:aData['objId']},
							async:false,
							dataType:'json',
							success:function(json){
								if(json.success){
									ConcernList.getTableList();
								}
							}
						})
					}
					
				});
				$(nRow).find("td[name=createTime]").empty().append(aData['createTime'].substring(0,16));
				return nRow;
			},
			'params':{'listType':ConcernList.currentTabID.replace("listType_","")},
			"sAjaxSource": $('#initPath').val()+'/ConcernController.do?method=list&concernGroup.belongsUser.objId='+$('#curUserId').val()+'&concernGroup.groupType=01'
		});
	}else {
		$(ConcernList.oTable.dataTableSettings).attr("params",{
			'listType':ConcernList.currentTabID.replace("listType_","")
		});
		ConcernList.oTable.fnDraw();
	}
}

// 修改客户所属分组
ConcernList.epsDialog = function(objId,concernGroupId,groupType,listType,orgInfoId,orgInfoName){
	$.epsDialog({
		title:"关注供应商",
		width:500,
		height:300,
		url:$('#initPath').val()+"/ConcernController.do?method=toAddConcernForm&objId="+objId+"&concernGroupId="+concernGroupId+"&groupType="+groupType+"&listType="+listType+"&orgInfoId="+orgInfoId,
		afterLoad: function(){$('#orgInfoName').html(orgInfoName) },
		onClose: function(){ 
			$('#conBody').loadPage($('#initPath').val()+"/ConcernController.do?method=toConcernSupplierList&currentTab="+$('#currentTab').val());
    	}
	});
	return false;
}

$(document).ready(function(){
	//设定返回路径
	$("#returnUrl").val("ConcernController.do?method=toConcernSupplierList")
	
	//加载tabs,绑定选中事件为加载列表
	var $tabs = $('#epsTabs').tabs({
		select: function(event, ui) {
			ConcernList.currentTabID = ui.tab.id;
			$("#currentTab").val(ui.index); //当前tab的index
			ConcernList.getTableList();
		}
	});
	//指定某一个tab被选中，默认值为0
	$tabs.tabs('select', parseInt($("#currentTab").val()));
	//加载第一个tab数据
	if($("#currentTab").val() == "0"){
		ConcernList.getTableList();
	}
	
	//编辑供应商分组
	$("#editConcernGroupBtn").click(function(){
		$.epsDialog({
			title:"管理供应商分组 ",
			width:500,
			height:300,
			url:$('#initPath').val()+"/view/pubservice/application/concern/edit_concern_group_form.jsp?groupType=01",
			onClose: function(){ 
				$('#conBody').loadPage($('#initPath').val()+"/ConcernController.do?method=toConcernSupplierList&currentTab="+$('#currentTab').val());
	    	}
		});
		return false;
	});
	
	//查询
	$("#query").click(function(){
		ConcernList.oTable.fnDraw();
	});
});
	
