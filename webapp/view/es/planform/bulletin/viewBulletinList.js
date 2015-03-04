//定义文件全局变量 处理方法名重复问题
var BulletinList={};
BulletinList.currentTabID='';//tabs页标识
var option={"auditStatus":"00","bullType":"01,05,06,02","bullType_op":"in"};

//待审核:跳转到审核页面
BulletinList.showDetail_toAudit=function(id){
	$('#conBody').loadPage($('#initPath').val()+"/BulletinController.do?method=toBulletinAuditInfoByObjId&objId="+id);
}

//已审核：跳转到查看页面
BulletinList.showDetail_toView=function(id){
	$('#conBody').loadPage($('#initPath').val()+"/BulletinController.do?method=toBulletinInfoByObjId&objId="+id);
}

//被退回：跳转到修改页面
BulletinList.showDetail_toModify=function(id){
	$('#conBody').loadPage($('#initPath').val()+"/BulletinController.do?method=toBulletinInfoByObjId&objId="+id);
	//$('#conBody').loadPage($('#initPath').val()+"/BulletinController.do?method=toResultPublicityOrBulletinByObjId&objId="+id);
}


//查询条件过滤
BulletinList.before=function(){
	var ebuyMethod =$("#bullTypes").val();
	var option={"auditStatus":"00","bullType":"01,05,06,02","bullType_op":"in",
			"ebuyMethod":ebuyMethod
		}
	$('#BulletinGrid').flexOptions({params:option});
	return true;
}

//设定返回时的路径
BulletinList.setRetrun=function(){
	$("#returnUrl").val($('#initPath').val()+"/view/es/planform/bulletin/viewBulletinList.jsp");
}
BulletinList.success = function(){
	var role = $("#userType").val();
	if(role=='buyer'){  //当前用户是招标单位  //隐藏批量审核按钮
	  $("div.fbutton").hide();
	}else if(role=='supplier'){  //当前用户是投标单位 //隐藏批量审核按钮
	  $("div.fbutton").hide();
	}else if(role=='supervision'){
		
	}
	if(BulletinList.currentTabID==''||BulletinList.currentTabID == "tabs_wait"){//待审核公告
		$("#BulletinGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">审核</a></span>'   : function(btn,rowId,obj){
					btn.click(function(){
						BulletinList.showDetail_toAudit(rowId);
					}).appendTo(obj);
				}
			});
	}
	else if(BulletinList.currentTabID == "tabs_pass"){//已审核公告
		$("#BulletinGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a></span>'   : function(btn,rowId,obj){
					btn.click(function(){
						BulletinList.showDetail_toView(rowId);
					}).appendTo(obj);
				}
			});
	}
	else if(BulletinList.currentTabID == "tabs_notpass"){//被退回公告
		$("#BulletinGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a></span>'   : function(btn,rowId,obj){
					btn.click(function(){
						BulletinList.showDetail_toView(rowId);
					}).appendTo(obj);
				}
			});
	}
}
//此处的批量审核针对所有类型的公告
BulletinList.batchAudit = function (name,grid){
	var ids = $(grid).getSelects().split(",");
	if(ids == ""){
		alert("请至少选择一个公告!");
		return false;
	}else{
		var json = {};
		json['idsP']='ids';
		json['opinionP']='opinion';
		json['ids'] =ids;
		json['passAction'] = 'BulletinController.do?method=allBatchAudit;passStatus=01';
		json['noPassAction'] = 'BulletinController.do?method=allBatchAudit;passStatus=02';
		$.epsDialog({
					title:'批量审核',
					url:$('#initPath').val()+'/view/srplatform/batch/audit.jsp?json='+obj2str(json), 
					width: 500,
					height: 200,
					afterLoad: function(){}, //加载完url后调用
					onClose: function(){$(grid).reload()} //关闭后调用
				});	
	}
}
$(document).ready(function(){
	BulletinList.currentTabID ='tabs_wait';
	//加载tabs
	$('#epsTabs').tabs();
	
	var role = $("#userType").val();
	if(role=='buyer'){  //当前用户是招标单位
		$("li a[id=tabs_pass]").click();
		$("li a[id=tabs_wait]").hide();
		$("li a[id=tabs_notpass]").hide();
		option={"auditStatus":"01","bullType":"01,05,06,02","bullType_op":"in"};
		BulletinList.currentTabID = 'tabs_pass';
		
	}else if(role=='supplier'){  //当前用户是投标单位
		$("li a[id=tabs_pass]").click();
		$("li a[id=tabs_wait]").hide();
		$("li a[id=tabs_notpass]").hide();
		option={"auditStatus":"01","bullType":"01,05,06,02","bullType_op":"in"};
		BulletinList.currentTabID = 'tabs_pass';
	}else if(role=='supervision'){
		$("li a[id=tabs_pass]").hide();
	}
	
	
	$("select[name='bullTypes']").change( function() {
		$("#query").click();
	}); 
	
	
	
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		BulletinList.currentTabID = $(this).attr("id");
		//参数值
		var buttons = [];
		if(BulletinList.currentTabID == "tabs_wait"){//待审核公告
			$('#BulletinGrid').attr("p").newp = 1;
			//buttons = [{name:"批量审核",bclass:"audit",onpress:BulletinList.batchAudit}];
			option={"auditStatus":"00","bullType":"01,05,06,02","bullType_op":"in"};
		}else if(BulletinList.currentTabID == "tabs_pass"){//已审核公告
			$('#BulletinGrid').attr("p").newp = 1;
			//buttons = [{name:"详情",bclass:"look",onpress:BulletinList.showDetail_toView}];
			option={"auditStatus":"01","bullType":"01,05,06,02","bullType_op":"in"};
		}else if(BulletinList.currentTabID == "tabs_notpass"){//被退回公告
			$('#BulletinGrid').attr("p").newp = 1;
			//buttons = [{name:"修改",bclass:"modify",onpress:BulletinList.showDetail_toModify}];
			option={"auditStatus":"02","bullType":"01,05,06,02","bullType_op":"in"};
		}
		$('#BulletinGrid').flexReDrawButtons(buttons);
		$('#BulletinGrid').reload();
	})
});
	
