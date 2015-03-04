//定义文件全局变量 处理方法名重复问题
var BulletinList={};
BulletinList.currentTabID='';//tabs页标识
var option={"auditStatus":"01","bullType":"01,06","useStatus":"01","status":"wait"};

//已审核：跳转到查看页面
BulletinList.showDetail_toView=function(id){
	$('#conBody').loadPage($('#initPath').val()+"/BulletinController.do?method=toBulletinInfoByObjId&fromS=yes&objId="+id);
}

//已审核：发布公告
BulletinList.relBulletin=function(ids){
	if (window.confirm("确认发送?")) {
		$.getJSON($('#initPath').val()+'/BulletinController.do?method=relBulletinForSupervise&bulletinIds='+ids,function(json){
		    if(json.result)alert(json.result);if(json.failure)return;
		    $('#BulletinGrid').reload();
		});	
	}
}


//查询条件过滤
BulletinList.before=function(){
	$('#BulletinGrid').flexOptions({params:option});
	return true;
}

//设定返回时的路径
BulletinList.setRetrun=function(){
	$("#returnUrl").val($('#initPath').val()+"/view/es/planform/bulletin/relBulletinListForSupervise.jsp");
}
BulletinList.success = function(){
	if(BulletinList.currentTabID==''||BulletinList.currentTabID == "tabs_wait"){//待发送
		$("#BulletinGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">发送</a></span>'   : function(btn,rowId,obj){
			btn.click(function(){
				BulletinList.relBulletin(rowId);
			}).appendTo(obj);
		}
		});
		$("#BulletinGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">查看</a></span>'   : function(btn,rowId,obj){
					btn.click(function(){
						BulletinList.showDetail_toView(rowId);
					}).appendTo(obj);
				}
			});
	}
	else if(BulletinList.currentTabID == "tabs_pass"){//已发送成功
		$("#BulletinGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">查看</a></span>'   : function(btn,rowId,obj){
					btn.click(function(){
						BulletinList.showDetail_toView(rowId);
					}).appendTo(obj);
				}
			});
	}
	else if(BulletinList.currentTabID == "tabs_notpass"){//已发送失败
		$("#BulletinGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">发送</a></span>'   : function(btn,rowId,obj){
			btn.click(function(){
				BulletinList.relBulletin(rowId);
			}).appendTo(obj);
		}
		});
		$("#BulletinGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">查看</a></span>'   : function(btn,rowId,obj){
					btn.click(function(){
						BulletinList.showDetail_toView(rowId);
					}).appendTo(obj);
				}
			});
	}
}
//此处的批量审核针对所有类型的公告
BulletinList.batchRel = function (name,grid){
	var ids = $(grid).getSelects().split(",");
	if(ids == ""){
		alert("请至少选择一个公告!");
		return false;
	}else{
		if (window.confirm("确认发送?")) {
			BulletinList.relBulletin(ids);
		}
	}
}
$(document).ready(function(){
	BulletinList.currentTabID ='tabs_wait';
	//加载tabs
	$('#epsTabs').tabs();
	
	//tabs的点击事件、
	
	$("select[name='bullTypes']").change( function() {
		$("#query").click();
	}); 
	
	
	$("li a.refreshData").click(function(){
		BulletinList.currentTabID = $(this).attr("id");
		//参数值
		var buttons = [];
		if(BulletinList.currentTabID == "tabs_wait"){//待发送
			buttons = [{name:"批量发送",bclass:"audit",onpress:BulletinList.batchRel}];
			option={"auditStatus":"01","bullType":"01,06","useStatus":"01","status":"wait"};
			$('#bulletinInfo').find('input[type=checkbox]').show();
			$('#BulletinGrid').attr("p").checkbox=true;
		}else if(BulletinList.currentTabID == "tabs_pass"){//已发送成功
			option={"auditStatus":"01","bullType":"01,06","useStatus":"01","status":"true"};
			$('#bulletinInfo').find('input[type=checkbox]').hide();
			$('#BulletinGrid').attr("p").checkbox=false;
		}else if(BulletinList.currentTabID == "tabs_notpass"){//已发送失败
			buttons = [{name:"批量发送",bclass:"audit",onpress:BulletinList.batchRel}];
			option={"auditStatus":"01","bullType":"01,06","useStatus":"01","status":"false"};
			$('#bulletinInfo').find('input[type=checkbox]').show();
			$('#BulletinGrid').attr("p").checkbox=true;
		}
		$('#BulletinGrid').flexReDrawButtons(buttons);
		$('#BulletinGrid').reload();
	})
});
	
