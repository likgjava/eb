
var list={};
list.rows=null;
list.before=function(){
	return true;
}

list.success=function(){
//	 $("#supplierGrid").flexGetColByName({
//	        'isPaid':function(id,t){
//		            $(t).html(
//	        		  '<input type="radio" checked="checked"/>是 <input type="radio" />否'
//	        		)}
//	 });
		return true;
    }
$(document).ready(function(){
  
	$("#supplierAffirm").click(function (){
		$("#supplierAffirmDetail").toggle("slow");
		$("#supplierAffirm span").toggleClass("collapsable");	
		})
	
	
  $("#saveBtn").click(function()
	{
	    alert("已经确认报名数据");	
    })
  
  $("#printBtn").click(function()
    {
	   alert("打印成功");	
   })
   
   	//加载工作记录
	$("#historyDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	
	//返回上一步
	$("#lastStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/esdemo/projectmanager/supplier_enter.jsp");
	})
	//进行下一步
	$("#nextStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/esdemo/projectmanager/purchase_file_buy.jsp");
	})
   
})