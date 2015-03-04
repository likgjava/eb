/*
 * 执行平台,变更公告
 * author: 杨兴
 * mail: yangx@gpcsoft.com
 */
var purchaseDocList3={};


//查询条件过滤
purchaseDocList3.before=function(){
	var option={"fileType":'08',
				"auditStatus":'06',
				"project.objId":$('#projectId').val()
				}
	$('#purchaseDocGrid').flexOptions({params:option});
	return true;
}


purchaseDocList3.conFirm=function(variationId){//审核
	$.epsDialog({
        title:"采购办审核澄清文件",
        url:$("#initPath").val()+'/PurchaseDocController.do?method=toClarificationDocForOffice&objId='+variationId,
        width:'700',
        height:'400',
        isReload:false
	});
}
purchaseDocList3.showDetail=function(variationId){//详情
	$.epsDialog({
        title:"澄清文件详情",
        url:$("#initPath").val()+'/PurchaseDocController.do?method=toViewClarificationDoc&objId='+variationId,
        width:'500',
        height:'300',
        isReload:false
	});
}
//加载数据成功之后调用的函数
purchaseDocList3.success=function(){
	if(purchaseDocList3.currentTabID == "tabs_toAudit"){//待审核
		$("#purchaseDocGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">审核</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					purchaseDocList3.conFirm(rowId);
				}).appendTo(obj);
			}
		})
	}else if(purchaseDocList3.currentTabID == "tabs_toAlAudit"){//已审核
		$("#purchaseDocGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>'  : function(btn,rowId,obj){
				btn.click(function(){
					purchaseDocList3.showDetail(rowId);
				}).appendTo(obj);
			}
		})
	}else if(purchaseDocList3.currentTabID == "tabs_toAdjust"){//被退回
		$("#purchaseDocGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>'  : function(btn,rowId,obj){
				btn.click(function(){
					purchaseDocList3.showDetail(rowId);
				}).appendTo(obj);
			}
		});
	}
}

$(document).ready(function(){
	//加载tabs
	$('#variationTabId').tabs();
	purchaseDocList3.currentTabID = "tabs_toAudit";//初始化时，默认当前TABID为tabs_toAudit
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		$("#purchaseDocGrid").find("tDiv").empty();
		purchaseDocList3.currentTabID = $(this).attr("id");
		var buttons = [];
		if(purchaseDocList3.currentTabID == "tabs_toAudit"){//待审核
			buttons = [];
			$('#purchaseDocGrid').attr("p").url = "PurchaseDocController.do?method=list&fileType=08&auditStatus=06&project.objId="+$('#projectId').val();
		}else if(purchaseDocList3.currentTabID == "tabs_toAlAudit"){//已审核
			buttons = [];
			$('#purchaseDocGrid').attr("p").url = "PurchaseDocController.do?method=list&fileType=08&auditStatus=03&project.objId="+$('#projectId').val();
		}else if(purchaseDocList3.currentTabID == "tabs_toAdjust"){//被退回
			buttons = [];
		$('#purchaseDocGrid').attr("p").url = "PurchaseDocController.do?method=list&fileType=08&auditStatus=04&project.objId="+$('#projectId').val();
		}
		$('#purchaseDocGrid').flexReDrawButtons(buttons);
		$('#purchaseDocGrid').reload();
	})
});

