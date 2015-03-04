var list={};
//列表查询的结果集
list.rows=null;
//查询条件过滤
list.before=function(){
	return true;
}
//加载数据成功之后调用的函数
list.success=function(){}
$(document).ready(function(){
	
	//委托协议书
	$("#taskInfoDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/entrust.jsp");
	
	//协议书明细
	$("#purchaseDetailList").loadPage($("#initPath").val()+"//view/es/planform/project/sure_entrust_detail.jsp");
	
	//加载确认委托
	$("#sureEntrustInf").loadPage($("#initPath").val()+"/view/es/planform/project/sure_entrust_inf.jsp");
	
	//工作记录
	$("#historyDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	//委托协议书
	$("#taskbook").click(function(){
		$("#taskInfoDiv").toggle("slow");
		$("#taskbook span").toggleClass("collapsable");
	})

	//协议书明细
	$("#purchaseDetailList").hide();
	$("#purchaseDetail").click(function(){
		$("#purchaseDetailList").toggle("slow");
		$("#purchaseDetail span").toggleClass("collapsable");
	})
	
	//确认委托
	$("#sureentrust").click(function(){
		$("#sureEntrustInf").toggle("slow");
		$("#sureentrust span").toggleClass("collapsable");
	})

})