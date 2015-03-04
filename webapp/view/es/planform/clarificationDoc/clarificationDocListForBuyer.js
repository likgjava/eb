/*
 * 执行平台,变更公告
 * author: 杨兴
 * mail: yangx@gpcsoft.com
 */
var purchaseDocList2={};


//查询条件过滤
purchaseDocList2.before=function(){
	var option={"fileType":'08',
				"auditStatus":'00',
				"useStatus":'01',
				"project.objId":$('#projectId').val()
				}
	$('#purchaseDocGrid').flexOptions({params:option});
	return true;
}


purchaseDocList2.conFirm=function(variationId){//确认
	$.epsDialog({
        title:"招标单位确认澄清文件",
        url:$("#initPath").val()+'/PurchaseDocController.do?method=toClarificationDocPageForBuyer&objId='+variationId,
        width:'700',
        height:'400',
        isReload:false
	});
}
purchaseDocList2.showDetail=function(variationId){//详情
	$.epsDialog({
        title:"澄清文件详情",
        url:$("#initPath").val()+'/PurchaseDocController.do?method=toViewClarificationDoc&objId='+variationId,
        width:'500',
        height:'300',
        isReload:false
	});
}
//加载数据成功之后调用的函数
purchaseDocList2.success=function(){
	if(purchaseDocList2.currentTabID == "tabs_toTemp"){//临时 
		$("#purchaseDocGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">确认</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>'  : function(btn,rowId,obj){
				btn.click(function(){
					purchaseDocList2.conFirm(rowId);
				}).appendTo(obj);
			}
		});
	}else if(purchaseDocList2.currentTabID == "tabs_toAudit"){//待审核
		$("#purchaseDocGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					purchaseDocList2.showDetail(rowId);
				}).appendTo(obj);
			}
		});
		 
	}else if(purchaseDocList2.currentTabID == "tabs_toQDJCJ"){//待审核
		$("#purchaseDocGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>'  : function(btn,rowId,obj){
				btn.click(function(){
					purchaseDocList2.showDetail(rowId);
				}).appendTo(obj);
			}
		});
		 
	}else if(purchaseDocList2.currentTabID == "tabs_toAlAudit"){//已审核
		$("#purchaseDocGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>'  : function(btn,rowId,obj){
				btn.click(function(){
					purchaseDocList2.showDetail(rowId);
				}).appendTo(obj);
			}
		})
	}
}

$(document).ready(function(){
	//加载tabs
	$('#variationTabId').tabs();
	purchaseDocList2.currentTabID = "tabs_toTemp";//初始化时，默认当前TABID为tabs_toAudit
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		$("#purchaseDocGrid").find("tDiv").empty();
		purchaseDocList2.currentTabID = $(this).attr("id");
		var buttons = [];
		if (purchaseDocList2.currentTabID=="tabs_toTemp") {//待确认
			$('#purchaseDocGrid').attr("p").url = "PurchaseDocController.do?method=list&fileType=08&auditStatus=00&project.objId="+$('#projectId').val();
		}else if (purchaseDocList2.currentTabID=="tabs_toQDJCJ") {//待检查局审核
			buttons = [];
			$('#purchaseDocGrid').attr("p").url = "PurchaseDocController.do?method=list&fileType=08&useStatus=01&auditStatus=05&project.objId="+$('#projectId').val();
		}else if(purchaseDocList2.currentTabID == "tabs_toAudit"){//待审核
			buttons = [];
			$('#purchaseDocGrid').attr("p").url = "PurchaseDocController.do?method=list&fileType=08&auditStatus=06&project.objId="+$('#projectId').val();
		}else if(purchaseDocList2.currentTabID == "tabs_toAlAudit"){//已审核
			buttons = [];
			$('#purchaseDocGrid').attr("p").url = "PurchaseDocController.do?method=list&fileType=08&auditStatus=03&project.objId="+$('#projectId').val();
		}else if(purchaseDocList2.currentTabID == "tabs_toAdjust"){//被退回
			buttons = [];
		$('#purchaseDocGrid').attr("p").url = "PurchaseDocController.do?method=list&fileType=08&auditStatus=04&project.objId="+$('#projectId').val();
		}
		$('#purchaseDocGrid').flexReDrawButtons(buttons);
		$('#purchaseDocGrid').reload();
	})
});

