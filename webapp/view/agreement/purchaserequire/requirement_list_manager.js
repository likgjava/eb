/*
 * 维护品牌页面
 * created by yucy
 */
var requirement_list={};
requirement_list.oTable;


//跳转到修改或者新增页面
requirement_list.toCreateOrUpdate = function(objId){
	$("#conBody").loadPage($("#initPath").val()+"/RequirementController.do?method=toCreateOrUpdateRequirement&objId="+(objId!=null?objId:'') );
}

//批量操作前确认
requirement_list.batConfrim = function(){
	if(requirement_list.oTable.dtSelects().length<=0){
		alert('请至少选择一行数据！');
		return false
	}else{
		return true;
	}
}

//批量审核
requirement_list.batAudRequirementHref = function(){
	$.epsDialog({
		title:"批量审核",
		content:"<div class='conOperation'>" +
		"<button type='button' class='largeBtn' onclick='requirement_list.batAudRequirement(\"02\");'><span>通过</span></button>" +
		"<button type='button' class='largeBtn' onclick='requirement_list.batAudRequirement(\"03\");'><span>不通过</span></button>" +
		"</div>",
		height:100,
		width:200
	})
}

//批量审核
requirement_list.batAudRequirement = function(auditStatus){
	$.getJSON($('#initPath').val()+'/RequirementController.do?method=updateStatus', {objIds:requirement_list.oTable.dtSelects(),"auditStatus":auditStatus}, function(json){
		if(json.failure){
			if(json.result)alert(json.result);return;
		}else{
			alert("操作成功！");
		}
		$('.epsDialogClose').trigger('click');
		requirement_list.getRequirementList();
	});
}


//批量发布
requirement_list.batPubRequirement = function(objIds){
	if(confirm("确认发布？")){
		$.getJSON($('#initPath').val()+'/RequirementController.do?method=updateStatus', {objIds:objIds,"pubStatus":"01"}, function(json){
			if(json.failure){
				if(json.result)alert(json.result);return;
			}else{
				alert("操作成功！");
			}
			requirement_list.getRequirementList();
		});
	}
}

//取得品牌列表
requirement_list.getRequirementList = function(params){
	if(null==requirement_list.oTable){
		requirement_list.oTable = $('#requirementList').dataTable({   
			"params":params,
			'searchZone':'requirementSearchForm',
			'singleSelect':true,	
			'checkbox':true,		
			'queryColumns':'title,pubOrg.orgName,category.categoryName,purchaseQty,purchaseBudget,districtNames,endTime,toAuditCount',
			'hiddenColumns':'auditStatus,pubStatus,pubOrg.auditStatus,auditedCount,pubOrg.objId',
			'alias':'',
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				
				$(nRow).append('<td align="center"></td>');
				
				if(aData.pubStatus=='00'||aData.auditStatus=='01'){
					$(nRow).find("td[name=toAuditCount]").html("--/--");
				}else{
					$(nRow).find("td[name=toAuditCount]").html(aData.toAuditCount+"/"+ aData.auditedCount);
				}
				
				//禁用复选
				if(aData.pubStatus=='01'||aData['pubOrg.auditStatus']=='01'||aData['pubOrg.auditStatus']=='00'){
					$(nRow).find('input:checkbox').attr("disabled","disabled");
				}
				
				if(aData.pubStatus=='01'){
					//审核报名
					$(nRow).find("td:last").append('<a href="javascript:void(0);" name="auditReg" title="审核报名"><span>审核报名</span></a>').find("a[name=auditReg]").click(function(){
						$("#conBody").loadPage($("#initPath").val()+"/RequirementRegController.do?method=toRequirementRegListManger&objId="+aData.objId );
					});
				}
				
				if(aData.auditStatus=='02'&&aData.pubStatus =='00'){
					//发布			
					$(nRow).find("td:last").append('<a href="javascript:void(0);" name="publish" title="发布"><span>发布</span></a>').find("a[name=publish]").click(function(){
						requirement_list.batPubRequirement(aData.objId);
					})
				}
				
				if(aData.auditStatus=='02'){
					//修改				
					$(nRow).find("td:last").append('<a href="javascript:void(0);" name="modify" title="修改"><span>修改</span></a>').find("a[name=modify]").click(function(){
						//跳转到修改页面
						requirement_list.toCreateOrUpdate(aData.objId);
					})
					
				}
				if(aData.auditStatus!='01'&&aData.pubStatus!='01' ){
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
				
				if(aData.auditStatus=='01'&&aData['pubOrg.auditStatus']=='02'){
					//审核
					$(nRow).find("td:last").append('<a href="javascript:void(0);" name="audit" title="审核"><span>审核</span></a>').find("a[name=audit]").click(function(){
						$.epsDialog({
							title:"采购需求审核",
							id:"requirementAuditDiv",
							url:$("#initPath").val()+"/RequirementController.do?method=toRequirementAudit&objId="+aData.objId
						});
					});
				}else if(aData.auditStatus=='01'&&aData['pubOrg.auditStatus']=='01'){
					//先审核机构审核
					$(nRow).find("td:last").append('<a href="javascript:void(0);" name="auditOrg"><span>审核机构</span></a>').find("a[name=auditOrg]").click(function(){
						$("#conBody").loadPage($("#initPath").val()+"/OrgInfoController.do?method=toAuditOrgInfo&orgInfoId="+aData["pubOrg.objId"] );
					});
				}else if(aData.auditStatus=='01'&&(aData['pubOrg.auditStatus']=='00'||aData['pubOrg.auditStatus']=='03')){
					//先审核机构审核
					$(nRow).find("td:last").append('<a href="javascript:void(0);" name="auditOrg"><span>机构信息未完善</span></a>').find("a[name=auditOrg]").click(function(){
						alert("请联系发布机构完善机构信息提交并审核通过后,再对该需求审核操作！");
						$("#conBody").loadPage($("#initPath").val()+"/ExOrgInfoController.do?method=toEntBaseInfo&orgId="+aData["pubOrg.objId"] );
					});
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
			"sAjaxSource": $('#initPath').val()+"/RequirementController.do?method=list"
		});
	}else{
		$(requirement_list.oTable.dataTableSettings).attr("params",params);
		requirement_list.oTable.fnDraw();
	}
}

$(document).ready(function(){
	
	//返回路径
	$("#returnUrl").val($("#initPath").val()+"/RequirementController.do?method=toRequirementManagerList");
	
	//加载tabs,绑定选中事件为加载列表
	var $tabs = $('#epsTabs').tabs({
		select: function(event, ui) {
			if(ui.index==0){				//已发布
				requirement_list.getRequirementList({'pubStatus':'01'})
				$("#batPubRequirement").hide();
				$("#batAudRequirement").hide();
			}else if(ui.index==1){			//未发布
				requirement_list.getRequirementList({'pubStatus':'00','auditStatus':'02'})
				$("#batPubRequirement").show();
				$("#batAudRequirement").hide();
			}else if(ui.index==2){			//待审核
				requirement_list.getRequirementList({'auditStatus':'01'})
				$("#batAudRequirement").show();
				$("#batPubRequirement").hide();
			}
			$("#currentTab").val(ui.index); //当前tab的index
		}
	});
	
	//tab无法触发第一个选中，所以需要手动加载一次
	if($("#currentTab").val() == "0") {
		requirement_list.getRequirementList({'pubStatus':'01'});
	}
	
	//指定某一个tab被选中，默认值为0
	$tabs.tabs('select', parseInt($("#currentTab").val()));
	
});

