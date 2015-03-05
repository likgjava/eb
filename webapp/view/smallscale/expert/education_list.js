var ExpertInfoList={};
ExpertInfoList.oTable;	
ExpertInfoList.currentTabID="auditStatus_00"; //当前Tab的ID

// 生成列表中每条数据的操作：根据Tab不同，生成“取消、确定”和“查看”超链接
ExpertInfoList.getOperatorStr=function(auditStatus){	
	var operatorStr = '<td class="operation">';
	if(auditStatus != "01"){
		operatorStr += '<a href="javascript:void(0);" name="modify">修改</a>';
	}
	operatorStr += '<a href="javascript:void(0);" name="delete">删除</a>';
	operatorStr += '</td>';
	return operatorStr;
}

//获取列表数据，如果列表没有，则创建，否则刷新数据
ExpertInfoList.getTableList = function() {
	if(!ExpertInfoList.oTable) {
		//专家信息列表
		ExpertInfoList.oTable=$('#ExpertInfoList').dataTable( {
			'searchZone':'ExpertInfoListForm',
			'singleSelect':true,
			'checkbox':false,
			'queryColumns':'graduateSchool,degree.dicName,speciality.dicName,graduateDate',
			'alias':'graduateSchool,degree.dicName,speciality.dicName,graduateDate',
			'hiddenColumns':'objId,expertInfo.objId,auditStatus',
			'fnInitComplete':function(oSettings) {
			},
			'fnDrawCallback':function(oSettings) {	
				ExpertInfoList.oTable.oSettings=oSettings;
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				   
				//添加按钮
				$(nRow).append(ExpertInfoList.getOperatorStr(ExpertInfoList.currentTabID.replace("auditStatus_",""))).find("a[name=modify]").click(function(){
					$('#conBody').loadPage($('#initPath').val()+"/EducationController.do?method=toCreateOrUpdate&objId="+aData['objId']);
				}).end().find("a[name=delete]").click(function(){
					
					if(window.confirm("确认要删除该教育经历吗?")){
						$.ajax({
							url:$('#initPath').val()+'/EducationController.do?method=remove',
							type:'POST',
							data:{objId:aData['objId']},
							async:false,
							dataType:'json',
							success:function(json){
								if(json.success){
									ExpertInfoList.getTableList();
								}
							}
						})
					}
					
				});
				$(nRow).find("td[name=graduateDate]").empty().append(aData['graduateDate'].substring(0,16));
				return nRow;
			},
			'params':{'auditStatus':ExpertInfoList.currentTabID.replace("auditStatus_","")},
			"sAjaxSource": $('#initPath').val()+'/EducationController.do?method=list&expertInfo.objId='+$('#expertInfoId').val()
		});
	}else {
		$(ExpertInfoList.oTable.dataTableSettings).attr("params",{
			'auditStatus':ExpertInfoList.currentTabID.replace("auditStatus_","")
		});
		ExpertInfoList.oTable.fnDraw();
	}
}


$(document).ready(function(){
	//设定返回路径
	$("#returnUrl").val($('#initPath').val()+"/EducationController.do?method=toEducationList")
	
	//加载tabs,绑定选中事件为加载列表
	var $tabs = $('#epsTabs').tabs({
		select: function(event, ui) {
			ExpertInfoList.currentTabID = ui.tab.id;
			if(ExpertInfoList.currentTabID.replace("auditStatus_","") == "00" || ExpertInfoList.currentTabID.replace("auditStatus_","")=="01"){
				$('#attentionModify').hide();
				$('#attentionAdd').show();
			}else{
				$('#attentionModify').show();
			}
			$("#currentTab").val(ui.index); //当前tab的index
			ExpertInfoList.getTableList();
		}
	});
	//指定某一个tab被选中，默认值为0
	$tabs.tabs('select', parseInt($("#currentTab").val()));
	//加载第一个tab数据
	if($("#currentTab").val() == "0"){
		ExpertInfoList.getTableList();
	}
	
	// 新增专家的职称信息
	$('#addExpertInfoBtn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/EducationController.do?method=toCreateOrUpdate");
	});
});

	
