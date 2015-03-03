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

//创建竞价
requirement_list.batCreateBargin = function(objIds){
	window.open($('#initPath').val()+"/BargainProjectController.do?method=toCreateBidProject_1&requirementIds="+objIds);
}

//创建议价
requirement_list.batCreateTalkin = function(objIds){
	$('#conBody').loadPage($('#initPath').val()+"/TalkProjectController.do?method=toCreateTalkProjectView&requirementIds="+objIds);
}

//跳转到修改或者新增页面
requirement_list.toCreateOrUpdate = function(objId){
	$("#conBody").loadPage($("#initPath").val()+"/RequirementController.do?method=toCreateOrUpdateRequirementPurchaser&objId="+(objId!=null?objId:'') );
}

//取得品牌列表
requirement_list.getRequirementList = function(param){
	if(null==requirement_list.oTable){
		requirement_list.oTable = $('#requirementList').dataTable({   
			'params':param,
			'searchZone':'requirementSearchForm',
			'singleSelect':false,	
			'checkbox':true,		
			'queryColumns':'title,category.categoryName,purchaseQty,purchaseBudget,districtNames,endTime,auditStatus,auditedCount',
			'hiddenColumns':'pubStatus,projectId',
			'alias':'title,category.categoryName,purchaseQty,purchaseBudget,districtNames,endTime,auditStatusCN,auditedCount',
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				
				$(nRow).append('<td align="center"></td>');
				
				//禁用复选
				if(aData.pubStatus=='00'||aData.projectId!=''){
					$(nRow).find('input:checkbox').attr("disabled","disabled");
				}
				
				if(aData.pubStatus=='01'){
					//查看报名				
					$(nRow).find("td:last").append('<a href="javascript:void(0);" name="viewReg" title="查看报名"><span>查看报名</span></a>').find("a[name=viewReg]").click(function(){
						//跳转到查看报名
						$("#conBody").loadPage($("#initPath").val()+"/RequirementRegController.do?method=toRequirementRegList&objId="+aData.objId );
					})
				}
				
				if(aData.auditStatus=='00'||aData.auditStatus=='03' ){
					//修改				
					$(nRow).find("td:last").append('<a href="javascript:void(0);" name="modify" title="修改"><span>修改</span></a>').find("a[name=modify]").click(function(){
						//跳转到修改页面
						requirement_list.toCreateOrUpdate(aData.objId);
					})
					
					//删除
					$(nRow).find("td:last").append('<a href="javascript:void(0);" name="delete" title="删除"><span>删除</span></a>').find("a[name=delete]").click(function(){
						if(confirm('确认删除？')){
							$.getJSON($("#initPath").val()+"/RequirementController.do?method=remove",{"objId":aData.objId},function(json){
								if(json.success){
									requirement_list.getRequirementList();//刷新列表
								}
							})
						}
					})
				}
				
				//查看
				$(nRow).find("td:last").append('<a href="javascript:void(0);" name="view" title="查看"><span>查看</span></a>').find("a[name=view]").click(function(){
					$.epsDialog({
						title:"采购需求详情",
						id:"requirementDetailDiv",
						url:$("#initPath").val()+"/RequirementController.do?method=toRequirementDetail&objId="+aData.objId
					});
				});
				
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/RequirementController.do?method=list&userType=purchaser"
		});
	}else{
		requirement_list.oTable.fnDraw();
	}
}

$(document).ready(function(){
	
	//返回路径
	$("#returnUrl").val($("#initPath").val()+"/RequirementController.do?method=toRequirementPurchaserList");
	
	//初始化tabs
	$('#epsTabs').tabs({}); 
	
	//切换列表
	$('#epsTabs').bind('tabsselect', function(event, ui) {
		if(ui.index==0){				//已发布
			$(requirement_list.oTable.dataTableSettings).attr("params",{'pubStatus':'01'});
			$("#batCreateProjectSpan").show();
		}else if(ui.index==1){			//未发布
			$(requirement_list.oTable.dataTableSettings).attr("params",{'pubStatus':'00'});
			$("#batCreateProjectSpan").hide();
		}
		requirement_list.getRequirementList();
	});
	
	//加载列表
	requirement_list.getRequirementList({'pubStatus':'01'});
	
});

