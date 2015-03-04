//定义文件全局变量 处理方法名重复问题
var BulletinList={};
var option={"auditStatus":"00","useStatus":"00","bullType":"11","bullType_op":"in"};

//列表操作验证
BulletinList.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一个公告！');return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请只选择一个公告！');return false;}//是否只选中一个
	return true;
}

//查询条件过滤
BulletinList.before=function(){
	$('#BulletinGrid').flexOptions({params:option});
	return true;
}

//设定返回时的路径
BulletinList.setRetrun=function(){
	$("#returnUrl").val($('#initPath').val()+"/view/es/planform/bulletin/viewChangeBulletinList.jsp");
}

//新增
BulletinList.add=function(name,grid){
	//跳转到新增更正公告页面
	$('#changeBulletin').loadPage($('#initPath').val()+"/VariationBulletinController.do?method=toDraftVariationBulletin&projectId="+$("#projectId").val());
} 

$(document).ready(function(){
	//加载tabs
	$('#epsTabs').tabs();
	BulletinList.currentTabID = 'tabs_submit';
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		BulletinList.currentTabID = $(this).attr("id");
		//参数值
		var buttons = [];
		if(BulletinList.currentTabID == "tabs_submit"){//提交审核公告
			option={"auditStatus":"00","useStatus":"00","bullType":"11","bullType_op":"in"};
		}else if(BulletinList.currentTabID == "tabs_wait"){//待审核公告
			option={"auditStatus":"00","useStatus":"01","bullType":"11","bullType_op":"in"};
		}else if(BulletinList.currentTabID == "tabs_pass"){//已审核公告
			option={"auditStatus":"01","useStatus":"01","bullType":"11","bullType_op":"in"};
		}else if(BulletinList.currentTabID == "tabs_notpass"){//被退回公告
			option={"auditStatus":"02","useStatus":"01","bullType":"11","bullType_op":"in"};
		}
		$('#BulletinGrid').flexReDrawButtons(buttons);
		$('#BulletinGrid').reload();
	})
	
	
	//加载数据成功之后调用的函数
	BulletinList.success=function(){
		if(BulletinList.currentTabID == "tabs_submit"){//提交审核
			$("#BulletinGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">详情</a></span>' : function(btn,rowId,obj){
					btn.click(function(){
						BulletinList.setRetrun();
						$('#conBody').loadPage($('#initPath').val()+"/ChangebulletinController.do?method=toChangeBulletinListDetail&backUrl=viewChangeBulletinList&objId="+rowId);
					}).appendTo(obj);
				},
				'<span><a href="#" class="abtn">修改</a></span>' : function(btn,rowId,obj){
					btn.click(function(){
						BulletinList.setRetrun();
						$('#conBody').loadPage($('#initPath').val()+"/ChangebulletinController.do?method=toModifyChangeBulletin&objId="+rowId);
					}).appendTo(obj);
				},
				'<span><a href="#" class="abtn">删除</a></span>' : function(btn,rowId,obj){
					btn.click(function(){
						if(window.confirm('确定删除吗?')){
							$.getJSON($('#initPath').val()+'/BulletinController.do?method=remove',{objId:rowId},function(json){
								if(json.result)alert(json.result);if(json.failure)return;
								$("#BulletinGrid").reload();
							});
						}
					}).appendTo(obj);
				}
			});
		}else if(BulletinList.currentTabID == "tabs_wait"){//待审核
			$("#BulletinGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">详情</a></span>' : function(btn,rowId,obj){
					btn.click(function(){
						BulletinList.setRetrun();
						$('#conBody').loadPage($('#initPath').val()+"/ChangebulletinController.do?method=toChangeBulletinListDetail&backUrl=viewChangeBulletinList&objId="+rowId);
					}).appendTo(obj);
				}
			});
		}else if(BulletinList.currentTabID == "tabs_pass"){//已审核
			$("#BulletinGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">详情</a></span>' : function(btn,rowId,obj){
					btn.click(function(){
						BulletinList.setRetrun();
						$('#conBody').loadPage($('#initPath').val()+"/ChangebulletinController.do?method=toChangeBulletinListDetail&backUrl=viewChangeBulletinList&objId="+rowId);
					}).appendTo(obj);
				}
			});
		}else if(BulletinList.currentTabID == "tabs_notpass"){//被退回
			$("#BulletinGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">详情</a></span>' : function(btn,rowId,obj){
					btn.click(function(){
					BulletinList.setRetrun();
					$('#conBody').loadPage($('#initPath').val()+"/ChangebulletinController.do?method=toChangeBulletinListDetail&backUrl=viewChangeBulletinList&objId="+rowId);
					}).appendTo(obj);
				},
				'<span><a href="#" class="abtn">修改</a></span>' : function(btn,rowId,obj){
					btn.click(function(){
						BulletinList.setRetrun();
						$('#conBody').loadPage($('#initPath').val()+"/ChangebulletinController.do?method=toModifyChangeBulletin&objId="+rowId);
					}).appendTo(obj);
				},
				'<span><a href="#" class="abtn">删除</a></span>' : function(btn,rowId,obj){
					btn.click(function(){
						if(window.confirm('确定删除吗?')){
							$.getJSON($('#initPath').val()+'/BulletinController.do?method=remove',{objId:rowId},function(json){
								if(json.result)alert(json.result);if(json.failure)return;
								$("#BulletinGrid").reload();
							});
						}
					}).appendTo(obj);
				}
			});
		}
		
	}
	
	
	
});
	
