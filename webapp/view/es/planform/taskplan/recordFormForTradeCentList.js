
var recordFormList={};
recordFormList.rows=null;//列表查询的结果集
	//查询条件过滤
	recordFormList.before=function(){
		var option={"order":"createTime",
				"order_flag":"true",
				"auditStatus":"00"}
		$('#recordFormGrid').flexOptions({params:option});
		return true;
	}
	recordFormList.audit = function (objId) {
		
	}
	//加载数据成功之后调用的函数
	recordFormList.success=function(){
		if(recordFormList.currentTabID == "tabs_toTemp"){//临时 
			$("#recordFormGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">查看</a></span>'   : function(btn,rowId,obj){
				btn.click(function(){
					$.epsDialog({
						title:'招标投标登记表',
						url:$('#initPath').val()+"/RecordFormController.do?method=toShowRecordForm&objId="+rowId,
						width: '800',
						height: '500',
						dragAndResize:true,
						hasBg:false,
						isReload: false
					});
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">审核</a></span>'   : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').loadPage($('#initPath').val()+"/RecordFormController.do?method=toRecordFormAuditForm&objId="+rowId);
				}).appendTo(obj);
			}
			});
		}else{
			$("#recordFormGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn">查看</a></span>'   : function(btn,rowId,obj){
				btn.click(function(){
					$.epsDialog({
						title:'招标投标登记表',
						url:$('#initPath').val()+"/RecordFormController.do?method=toShowRecordForm&objId="+rowId,
						width: '800',
						height: '500',
						dragAndResize:true,
						hasBg:false,
						isReload: false
					});
				}).appendTo(obj);
			}
			});
		}
	}
$(document).ready(function(){
	//加载tabs
	$('#epsTabs').tabs();
	
	recordFormList.currentTabID = "tabs_toTemp";//初始化时，默认当前TABID为tabs_toTemp
	recordFormList.preTabID = "";
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		$("#recordFormGrid").find("tDiv").empty();
		recordFormList.currentTabID = $(this).attr("id");
		//参数值
		var paramValue = "";
		var buttons = [];
		var userId = PlatForm.user.objId;
		if(recordFormList.currentTabID == "tabs_toTemp"){//临时	
			buttons = [];
			$('#recordFormGrid').attr("p").newp = 1;
			$('#recordFormGrid').attr("p").url = "RecordFormController.do?method=list&order=createTime&order_flag=true&auditStatus=00";
		}else if(recordFormList.currentTabID == "tabs_toFormal"){//正式
			buttons = [];
			$('#recordFormGrid').attr("p").newp = 1;
			$('#recordFormGrid').attr("p").url = "RecordFormController.do?method=list&order=createTime&order_flag=true&auditStatus=01";
		}else if(recordFormList.currentTabID == "tabs_toBack"){//被退回
			buttons = [];
			$('#recordFormGrid').attr("p").newp = 1;
			$('#recordFormGrid').attr("p").url = "RecordFormController.do?method=list&order=createTime&order_flag=true&auditStatus=02";
		}
		$('#recordFormGrid').flexReDrawButtons(buttons);
		$('#recordFormGrid').reload();
	})
});

