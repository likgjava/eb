//定义文件全局变量 处理方法名重复问题
var BulletinList={};
var option={"auditStatus":"00","useStatus":"01","bullType":"11","bullType_op":"in"};;

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
	$("#returnUrl").val($('#initPath').val()+"/view/es/planform/bulletin/auditChangeBulletinList.jsp");
}








$(document).ready(function(){
	//加载tabs
	$('#epsTabs').tabs();
	BulletinList.currentTabID = 'tabs_wait';
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		BulletinList.currentTabID = $(this).attr("id");
		//参数值
		var buttons = [];
		if(BulletinList.currentTabID == "tabs_wait"){//待审核公告
			option={"auditStatus":"00","useStatus":"01","bullType":"11","bullType_op":"in"};
		}else if(BulletinList.currentTabID == "tabs_pass"){//已审核公告
			option={"auditStatus":"01","useStatus":"01","bullType":"11","bullType_op":"in"};
		}
		$('#BulletinGrid').flexReDrawButtons(buttons);
		$('#BulletinGrid').reload();
	})
	
	
	//加载数据成功之后调用的函数
	BulletinList.success=function(){
		if(BulletinList.currentTabID == "tabs_wait"){//待审核
			$("#BulletinGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">详情</a></span>' : function(btn,rowId,obj){
					btn.click(function(){
						BulletinList.setRetrun();
						$('#conBody').loadPage($('#initPath').val()+"/ChangebulletinController.do?method=toChangeBulletinListDetail&backUrl=auditChangeBulletinList&objId="+rowId);
					}).appendTo(obj);
				},
				'<span><a href="#" class="abtn">审核</a></span>' : function(btn,rowId,obj){
					btn.click(function(){
						BulletinList.setRetrun();
						$('#conBody').loadPage($('#initPath').val()+"/ChangebulletinController.do?method=toAuditChangeBulletinByObjId&objId="+rowId);
					}).appendTo(obj);
				}
			});
		}else if(BulletinList.currentTabID == "tabs_pass"){//已审核
			$("#BulletinGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">详情</a></span>' : function(btn,rowId,obj){
					btn.click(function(){
						BulletinList.setRetrun();
						$('#conBody').loadPage($('#initPath').val()+"/ChangebulletinController.do?method=toChangeBulletinListDetail&backUrl=auditChangeBulletinList&objId="+rowId);
					}).appendTo(obj);
				}
			});
			
		}
		
	}
	
	
	
});
	
