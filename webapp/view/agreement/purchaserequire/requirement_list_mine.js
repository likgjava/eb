/*
 * 维护品牌页面
 * created by yucy
 */
var requirement_list={};
requirement_list.oTable;

//批量操作前确认
requirement_list.batConfrim = function(){
	if(requirement_list.oTable.dtSelects().length<=0){
		alert('请至少选择一行数据！');
		return false
	}else{
		return true;
	}
}

//跳转到修改或者新增页面
requirement_list.toCreateOrUpdate = function(objId,requirementId){
	$.epsDialog({
		title:"修改报名信息",
		url:$("#initPath").val()+"/RequirementRegController.do?method=toRequirementRegForm&objId="+objId+"&requirementId="+requirementId
	});
}

//取得需求列表
requirement_list.getRequirementList = function(param){
	if(null==requirement_list.oTable){
		requirement_list.oTable = $('#requirementList').dataTable({   
			'params':param,
			'searchZone':'requirementSearchForm',
			'singleSelect':false,	
			'checkbox':false,		
			'queryColumns':'requirement.title,requirement.category.categoryName,requirement.purchaseQty,requirement.purchaseBudget,requirement.districtNames,requirement.endTime,auditStatus',
			'hiddenColumns':'requirement.objId,requirement.pubStatus,requirement.projectId',
			'alias':'requirement.title,requirement.category.categoryName,requirement.purchaseQty,requirement.purchaseBudget,requirement.districtNames,requirement.endTime,auditStatusCN',
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				
				$(nRow).append('<td class="operation"></td>');
				
				if(aData.auditStatus=='00'||aData.auditStatus=='03' ){
					//修改				
					$(nRow).find("td:last").append('<a href="javascript:void(0);" name="modify" title="修改报名"><span>修改报名</span></a>').find("a[name=modify]").click(function(){
						//跳转到修改页面
						requirement_list.toCreateOrUpdate(aData.objId,aData["requirement.objId"]);
					})
					
					//删除
					$(nRow).find("td:last").append('<a href="javascript:void(0);" name="delete" title="删除"><span>删除</span></a>').find("a[name=delete]").click(function(){
						if(confirm('确认删除？')){
							$.getJSON($("#initPath").val()+"/RequirementRegController.do?method=remove",{"objId":aData.objId},function(json){
								if(json.success){
									requirement_list.getRequirementList();//刷新列表
								}
							})
						}
					})
				}
				
				//查看
				$(nRow).find("td:last").append('<a href="javascript:void(0);" name="view" title="需求信息"><span>需求信息</span></a>').find("a[name=view]").click(function(){
					$.epsDialog({
						title:"采购需求详情",
						id:"requirementDetailDiv",
						url:$("#initPath").val()+"/RequirementController.do?method=toRequirementDetail&objId="+aData['requirement.objId']
					});
				});
				
				//查看报名
				$(nRow).find("td:last").append('<a href="javascript:void(0);" name="viewReg" title="报名信息"><span>报名信息</span></a>').find("a[name=viewReg]").click(function(){
					$.epsDialog({
						title:"查看报名信息",
						url:$("#initPath").val()+"/RequirementRegController.do?method=toRequirementRegDetail&objId="+aData.objId
					});
				});
				
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/RequirementRegController.do?method=list&orgType=supplier"
		});
	}else{
		requirement_list.oTable.fnDraw();
	}
}

$(document).ready(function(){
	
	//返回路径
	$("#returnUrl").val($("#initPath").val()+"/RequirementRegController.do?method=toMyRequirementReg");
	
	//初始化tabs
	$('#epsTabs').tabs({}); 
	
	//切换列表
	$('#epsTabs').bind('tabsselect', function(event, ui) {
		if(ui.index==0){				//已报名
			$(requirement_list.oTable.dataTableSettings).attr("params",{'auditStatus':'01,02','auditStatus_op':'in'});
			$("#batCreateProjectSpan").show();
		}else if(ui.index==1){			//未通过
			$(requirement_list.oTable.dataTableSettings).attr("params",{'auditStatus':'03'});
			$("#batCreateProjectSpan").hide();
		}
		requirement_list.getRequirementList();
	});
	
	//加载列表
	requirement_list.getRequirementList({'auditStatus':'01,02','auditStatus_op':'in'});
	
});

