/*
 * 执行平台,变更公告
 * author: 杨兴
 * mail: yangx@gpcsoft.com
 */
var purchaseDocList={};


//查询条件过滤
purchaseDocList.before=function(){
	var option={"fileType":'08',
				"useStatus":'00',
				"project.objId":$('#projectId').val()
				}
	$('#purchaseDocGrid').flexOptions({params:option});
	return true;
}

purchaseDocList.delMore=function(name,grid){//批量删除
	if($(grid).isSelectEmpty()){alert('请选择要删除的更正澄清文件!');return false;}//是否选中
	if(window.confirm('确定'+name+'?')){
		var ids = $(grid).getSelects().split(",");
		$.getJSON($('#initPath').val()+'/PurchaseDocController.do?method=remove',{objId:ids},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$(grid).reload();//刷新
		});
	}
}

purchaseDocList.add=function(){//新增更正澄清文件
	$.epsDialog({
        title:"新增澄清文件",
        url:$("#initPath").val()+'/PurchaseDocController.do?method=toAddClarificationDoc&projectId='+$('#projectId').val(),
        width:'500',
        height:'280',
        isReload:false
	});
}  

purchaseDocList.modify=function(variationId){//修改更正澄清文件
	$.epsDialog({
		title:"修改澄清文件",
		url:$("#initPath").val()+'/PurchaseDocController.do?method=toUpdateClarificationDoc&objId='+variationId,
		width:'500',
		height:'380',
		isReload:false
	});
}  

purchaseDocList.showDetail=function(variationId){//详情
	$.epsDialog({
        title:"澄清文件详情",
        url:$("#initPath").val()+'/PurchaseDocController.do?method=toViewClarificationDoc&objId='+variationId,
        width:'400',
        height:'300',
        isReload:false
	});
}

//加载数据成功之后调用的函数
purchaseDocList.success=function(){
	if(purchaseDocList.currentTabID == "tabs_toTemp"){//临时 
		$("#purchaseDocGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					purchaseDocList.showDetail(rowId);
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					purchaseDocList.modify(rowId);
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					if(window.confirm('确定删除吗?')){
						$.getJSON($('#initPath').val()+'/PurchaseDocController.do?method=remove',{objId:rowId},function(json){
							if(json.result)alert(json.result);if(json.failure)return;
							$("#purchaseDocGrid").reload();
						});
					}
				}).appendTo(obj);
			}
		});
	}else if(purchaseDocList.currentTabID == "tabs_toFormal"){//待确认
		$("#purchaseDocGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					purchaseDocList.showDetail(rowId);
				}).appendTo(obj);
			}
		});
	}else if(purchaseDocList.currentTabID == "tabs_toQDJCJ"){//待监察局审核
		$("#purchaseDocGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>': function(btn,rowId,obj){
				btn.click(function(){
					purchaseDocList.showDetail(rowId);
				}).appendTo(obj);
			}
		});
	}else if(purchaseDocList.currentTabID == "tabs_toAudit"){//待审核
		$("#purchaseDocGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					purchaseDocList.showDetail(rowId);
				}).appendTo(obj);
			}
		});
		 
	}else if(purchaseDocList.currentTabID == "tabs_toAlAudit"){//已审核
		$("#purchaseDocGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					purchaseDocList.showDetail(rowId);
				}).appendTo(obj);
			}
		})
	}else if(purchaseDocList.currentTabID == "tabs_toAdjust"){//被退回
		$("#purchaseDocGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					purchaseDocList.showDetail(rowId);
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					purchaseDocList.modify(rowId);
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					if(window.confirm('确定删除吗?')){
						$.getJSON($('#initPath').val()+'/PurchaseDocController.do?method=remove',{objId:rowId},function(json){
							if(json.result)alert(json.result);if(json.failure)return;
							$("#purchaseDocGrid").reload();
						});
					}
				}).appendTo(obj);
			}
		});
	}
}

$(document).ready(function(){
	//加载tabs
	$('#variationTabId').tabs();
	purchaseDocList.currentTabID = "tabs_toTemp";//初始化时，默认当前TABID为tabs_toTemp
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		$("#purchaseDocGrid").find("tDiv").empty();
		purchaseDocList.currentTabID = $(this).attr("id");
		var buttons = [];
		if (purchaseDocList.currentTabID=="tabs_toTemp") {//待确认
			buttons = [{name:"新增",bclass:"add",onpress:purchaseDocList.add}
			];
			$('#purchaseDocGrid').attr("p").url = "PurchaseDocController.do?method=list&fileType=08&useStatus=00&project.objId="+$('#projectId').val();
		}else if (purchaseDocList.currentTabID=="tabs_toFormal") {//待确认
			buttons = [];
			$('#purchaseDocGrid').attr("p").url = "PurchaseDocController.do?method=list&fileType=08&useStatus=01&auditStatus=00&project.objId="+$('#projectId').val();
		}else if (purchaseDocList.currentTabID=="tabs_toQDJCJ") {//待检查局审核
			buttons = [];
			$('#purchaseDocGrid').attr("p").url = "PurchaseDocController.do?method=list&fileType=08&useStatus=01&auditStatus=05&project.objId="+$('#projectId').val();
		}else if(purchaseDocList.currentTabID == "tabs_toAudit"){//待审核
			buttons = [];
			$('#purchaseDocGrid').attr("p").url = "PurchaseDocController.do?method=list&fileType=08&useStatus=01&auditStatus=06&project.objId="+$('#projectId').val();
		}else if(purchaseDocList.currentTabID == "tabs_toAlAudit"){//已审核
			buttons = [];
			$('#purchaseDocGrid').attr("p").url = "PurchaseDocController.do?method=list&fileType=08&useStatus=01&auditStatus=03&project.objId="+$('#projectId').val();
		}else if(purchaseDocList.currentTabID == "tabs_toAdjust"){//被退回
			buttons = [];
		$('#purchaseDocGrid').attr("p").url = "PurchaseDocController.do?method=list&fileType=08&useStatus=01&auditStatus=04&project.objId="+$('#projectId').val();
		}
		$('#purchaseDocGrid').flexReDrawButtons(buttons);
		$('#purchaseDocGrid').reload();
	})
});

