/*
 * 执行平台,变更公告
 * author: 杨兴
 * mail: yangx@gpcsoft.com
 */
var auditVariationList={};


//查询条件过滤
auditVariationList.before=function(){
	var option={"bullType":'02',
				"useStatus":"01",
				"auditStatus":"00",
				"project.objId":$('#projectId').val()
				}
	$('#variationBulletinGrid').flexOptions({params:option});
	return true;
}

auditVariationList.auditBulletin=function(variationId){//审核更正公告
	$.epsDialog({
		title:"更正公告",
		url:$("#initPath").val()+'/VariationBulletinController.do?method=toAuditVariationBulletinByObjId&objId='+variationId,
		width:'700',
		height:'500',
		isReload:false
	});
}  

auditVariationList.showDetail=function(variationId){//详情
	$.epsDialog({
        title:"更正公告",
        url:$("#initPath").val()+'/VariationBulletinController.do?method=toViewVariationBulletinByObjId&objId='+variationId,
        width:'700',
        height:'500',
        isReload:false
	});
}

//加载数据成功之后调用的函数
auditVariationList.success=function(){
	if(auditVariationList.currentTabID == "tabs_toAudit"){//待审核
		$("#variationBulletinGrid").flexAddOptionStr({
			'<button class="enable" type="button"><span>审核</span></button>' : function(btn,rowId,obj){
				btn.click(function(){
					auditVariationList.auditBulletin(rowId);
				}).appendTo(obj);
			}
		});
		 
	}else if(auditVariationList.currentTabID == "tabs_toAlAudit"){//已审核
		$("#variationBulletinGrid").flexAddOptionStr({
			'<button class="enable" type="button"><span>详情</span></button>' : function(btn,rowId,obj){
				btn.click(function(){
					auditVariationList.showDetail(rowId);
				}).appendTo(obj);
			}
		});
	}
}

$(document).ready(function(){
	//加载tabs
	$('#variationTabId').tabs();
	auditVariationList.currentTabID = "tabs_toAudit";//初始化时，默认当前TABID为tabs_toAudit
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		$("#variationBulletinGrid").find("tDiv").empty();
		auditVariationList.currentTabID = $(this).attr("id");
		var buttons = [];
		if(auditVariationList.currentTabID == "tabs_toAudit"){//待审核
			buttons = [];
			$('#variationBulletinGrid').attr("p").url = "BulletinController.do?method=list&bullType=02&auditStatus=00&useStatus=01&project.objId="+$('#projectId').val();
		}else if(auditVariationList.currentTabID == "tabs_toAlAudit"){//已审核
			buttons = [];
			$('#variationBulletinGrid').attr("p").url = "BulletinController.do?method=list&bullType=02&auditStatus=01&useStatus=01&project.objId="+$('#projectId').val();
		}
		$('#variationBulletinGrid').flexReDrawButtons(buttons);
		$('#variationBulletinGrid').reload();
	})
});

