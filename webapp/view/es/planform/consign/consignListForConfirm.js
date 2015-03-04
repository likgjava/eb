/*
 * 执行平台，委托书接收列表
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var consignListForAccept={};

consignListForAccept.batchAudit = function(name ,grid){
	var ids = $(grid).getSelects().split(",");
	if(ids == ""){
		alert("请至少选择一个委托协议!");
		return false;
	}else{
		var json = {};
		json['idsP']='ids';
		json['opinionP']='opinion';
		json['ids'] =ids;
		json['passAction'] = 'ConsignController.do?method=affirmConsignPass';
		json['noPassAction'] = 'ConsignController.do?method=affirmConsignReject';
		$.epsDialog({
					title:'批量确认',
					url:$('#initPath').val()+'/view/srplatform/batch/audit.jsp?json='+obj2str(json), 
					width: 500,
					height: 200,
					afterLoad: function(){}, //加载完url后调用
					onClose: function(){$(grid).reload()} //关闭后调用
				});	
	}
}

//设定返回时的路径
//consignListForAccept.setRetrun=function(){
//	$("#returnUrl").val($('#initPath').val()+"/view/es/planform/consign/consignListForConfirm.jsp");
//}

//接收
consignListForAccept.accept=function(name,grid){
	if(!consignListForAccept.validation(name,grid))return;
	$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/consign/consignFormForAccept.jsp?type=confirm&objId='+$(grid).getSelect());
}

//列表操作验证
consignListForAccept.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一份委托协议');return false;}//是否选中
	return true;
}
//查询条件过滤
consignListForAccept.before=function(){
	 var option={
	"confirmStatus":"01",
	"taskType":"00"	 
	 }
	$('#consignGrid').flexOptions({params:option});
	return true;
}

//查看
consignListForAccept.showDetail=function(name,grid){
	if(!consignListForAccept.validation(name,grid))return;
	$('#conBody').empty().loadPage($('#initPath').val()+'/ConsignController.do?method=showDetail&objId='+$(grid).getSelect());
}

consignListForAccept.success = function(){
	
	if(consignListForAccept.currentTabID == "tabs_toAccept"){
		$('.cth input[type=checkbox]').show();
		$("#consignGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">确认</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/consign/consignFormForAccept.jsp?type=confirm&objId='+rowId);
				}).appendTo(obj);
			}
		});
	}else if(consignListForAccept.currentTabID == "tabs_toDone"){
		$('.cth input[type=checkbox]').hide();
		$("#consignGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').empty().loadPage($('#initPath').val()+'/ConsignController.do?method=showDetail&objId='+rowId);
				}).appendTo(obj);
			}
		});
	}
}

$(document).ready(function(){
	$("#returnUrl").val($('#initPath').val()+"/view/es/planform/consign/consignListForConfirm.jsp");
	consignListForAccept.currentTabID = "tabs_toAccept";
	//日历控件
	$('#consTime').epsDatepicker();
	
	//设定返回时的路径
	//consignListForAccept.setRetrun();
	
	//加载tabs
	$('#epsTabs').tabs();
	
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		consignListForAccept.currentTabID = $(this).attr("id");
		//参数值
		var paramValue = "";
		var buttons = [];
		if(consignListForAccept.currentTabID == "tabs_toAccept"){//待确认
			buttons = [{name:"批量确认",bclass:"audit",onpress:consignListForAccept.batchAudit}];
			$('#consignGrid').attr("p").url = "ConsignController.do?method=getConsignList&confirmStatus=01&taskType=00";
		}else if(consignListForAccept.currentTabID == "tabs_toDone"){//已确认
			buttons = [];
			$('#consignGrid').attr("p").url = "ConsignController.do?method=getConsignList&confirmStatus=02&taskType=00";
		}
		$('#consignGrid').flexReDrawButtons(buttons);
		$('#consignGrid').reload();
	})
});

