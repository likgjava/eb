/*
 * 执行平台，委托书接收列表
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var consignListForAccept={};

//设定返回时的路径
//consignListForAccept.setRetrun=function(){
//	$("#returnUrl").val($('#initPath').val()+"/view/es/planform/consign/consignListForAccept.jsp");
//}

//接收
consignListForAccept.accept=function(name,grid){
	if(!consignListForAccept.validation(name,grid))return;
	$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/consign/consignFormForAccept.jsp?type=accept&objId='+$(grid).getSelect());
}

//列表操作验证
consignListForAccept.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一份委托协议');return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请选择只一个份委托协议');return false;}//是否只选中一个
	return true;
}
//查询条件过滤
consignListForAccept.before=function(){
	var option={"confirmStatus":"01","taskType":"01"};
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
		$("#consignGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">确认</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/consign/consignFormForAccept.jsp?type=accept&objId='+rowId);
				}).appendTo(obj);
			}
		});
	}else if(consignListForAccept.currentTabID == "tabs_toDone"){
		$("#consignGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').empty().loadPage($('#initPath').val()+'/ConsignController.do?method=showDetail&fromto=dazong&objId='+rowId);
				}).appendTo(obj);
			}
		});
	}
}

$(document).ready(function(){
	//日历控件
	$('#consTime').epsDatepicker();
	
	//设定返回时的路径
	//consignListForAccept.setRetrun();
	
	//加载tabs
	$('#epsTabs').tabs();
	consignListForAccept.currentTabID = "tabs_toAccept";
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		consignListForAccept.currentTabID = $(this).attr("id");
		//参数值
		var paramValue = "";
		var buttons = [];
		if(consignListForAccept.currentTabID == "tabs_toAccept"){//待接收	
			buttons = [];
			$('#consignGrid').attr("p").url = "ConsignController.do?method=getConsignList&confirmStatus=01&taskType=01";
		}else if(consignListForAccept.currentTabID == "tabs_toDone"){//已接受
			buttons = [];
			$('#consignGrid').attr("p").url = "ConsignController.do?method=getConsignList&confirmStatus=02&taskType=01";
		}
		$('#consignGrid').flexReDrawButtons(buttons);
		$('#consignGrid').reload();
	})
});

