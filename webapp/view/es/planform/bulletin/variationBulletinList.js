/*
 * 执行平台,变更公告
 * author: 杨兴
 * mail: yangx@gpcsoft.com
 */
var variationList={};


//查询条件过滤
variationList.before=function(){
	var option={"bullType":'02',
				"useStatus":"00",
				"project.objId":$('#projectId').val()
				}
	$('#variationBulletinGrid').flexOptions({params:option});
	return true;
}

variationList.delMore=function(name,grid){//批量删除
	if($(grid).isSelectEmpty()){alert('请选择要删除的更正公告!');return false;}//是否选中
	if(window.confirm('确定'+name+'?')){
		var ids = $(grid).getSelects().split(",");
		$.getJSON($('#initPath').val()+'/BulletinController.do?method=remove',{objId:ids},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$(grid).reload();//刷新
		});
	}
}

variationList.add=function(){//新增更正公告
	$.epsDialog({
        title:"更正公告",
        url:$("#initPath").val()+'/VariationBulletinController.do?method=toDraftVariationBulletin&projectId='+$('#projectId').val(),
        width:'700',
        height:'500',
        isReload:false
	});
}  

variationList.modify=function(variationId){//修改更正公告
	$.epsDialog({
		title:"更正公告",
		url:$("#initPath").val()+'/VariationBulletinController.do?method=toModifyVariationBulletin&variationId='+variationId,
		width:'700',
		height:'500',
		isReload:false
	});
}  

variationList.showDetail=function(variationId){//详情
	$.epsDialog({
        title:"更正公告",
        url:$("#initPath").val()+'/BulletinController.do?method=toBulletinInfoByObjId&objId='+variationId,
        width:'700',
        height:'500',
        isReload:false
	});
}

//加载数据成功之后调用的函数
variationList.success=function(){
	if(variationList.currentTabID == "tabs_toTemp"){//临时 
		$("#variationBulletinGrid").flexAddOptionStr({
			'<button class="enable" type="button"><span>详情</span></button>' : function(btn,rowId,obj){
				btn.click(function(){
					variationList.showDetail(rowId);
				}).appendTo(obj);
			},
			'<button class="enable" type="button"><span>修改</span></button>' : function(btn,rowId,obj){
				btn.click(function(){
					variationList.modify(rowId);
				}).appendTo(obj);
			},
			'<button class="enable" type="button"><span>删除</span></button>' : function(btn,rowId,obj){
				btn.click(function(){
					if(window.confirm('确定删除吗?')){
						$.getJSON($('#initPath').val()+'/BulletinController.do?method=remove',{objId:rowId},function(json){
							if(json.result)alert(json.result);if(json.failure)return;
							$("#variationBulletinGrid").reload();
						});
					}
				}).appendTo(obj);
			}
		});
	}else if(variationList.currentTabID == "tabs_toAudit"){//待审核
		$("#variationBulletinGrid").flexAddOptionStr({
			'<button class="enable" type="button"><span>详情</span></button>' : function(btn,rowId,obj){
				btn.click(function(){
					variationList.showDetail(rowId);
				}).appendTo(obj);
			}
		});
		 
	}else if(variationList.currentTabID == "tabs_toAlAudit"){//已审核
		$("#variationBulletinGrid").flexAddOptionStr({
			'<button class="enable" type="button"><span>详情</span></button>' : function(btn,rowId,obj){
				btn.click(function(){
					variationList.showDetail(rowId);
				}).appendTo(obj);
			}
		})
	}else if(variationList.currentTabID == "tabs_toAdjust"){//已审核
		$("#variationBulletinGrid").flexAddOptionStr({
			'<button class="enable" type="button"><span>详情</span></button>' : function(btn,rowId,obj){
				btn.click(function(){
					variationList.showDetail(rowId);
				}).appendTo(obj);
			},
			'<button class="enable" type="button"><span>修改</span></button>' : function(btn,rowId,obj){
				btn.click(function(){
					variationList.modify(rowId);
				}).appendTo(obj);
			}
		});
	}
}

$(document).ready(function(){
	//加载tabs
	$('#variationTabId').tabs();
	variationList.currentTabID = "tabs_toTemp";//初始化时，默认当前TABID为tabs_toAudit
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		$("#variationBulletinGrid").find("tDiv").empty();
		variationList.currentTabID = $(this).attr("id");
		var buttons = [];
		if (variationList.currentTabID=="tabs_toTemp") {//起草
			buttons = [{name:"新增",bclass:"add",onpress:variationList.add},
			           {name:"批量删除",bclass:"delete",onpress:variationList.delMore}
			];
			$('#variationBulletinGrid').attr("p").url = "BulletinController.do?method=list&bullType=02&useStatus=00&project.objId="+$('#projectId').val();
		}else if(variationList.currentTabID == "tabs_toAudit"){//待审核
			buttons = [];
			$('#variationBulletinGrid').attr("p").url = "BulletinController.do?method=list&bullType=02&auditStatus=00&useStatus=01&project.objId="+$('#projectId').val();
		}else if(variationList.currentTabID == "tabs_toAlAudit"){//已审核
			buttons = [];
			$('#variationBulletinGrid').attr("p").url = "BulletinController.do?method=list&bullType=02&auditStatus=01&useStatus=01&project.objId="+$('#projectId').val();
		}else if(variationList.currentTabID == "tabs_toAdjust"){//被退回
			buttons = [];
		$('#variationBulletinGrid').attr("p").url = "BulletinController.do?method=list&bullType=02&auditStatus=02&useStatus=01&project.objId="+$('#projectId').val();
		}
		$('#variationBulletinGrid').flexReDrawButtons(buttons);
		$('#variationBulletinGrid').reload();
	})
});

