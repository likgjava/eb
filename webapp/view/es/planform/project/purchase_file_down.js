var list={};
list.rows=null;
list.before=function(){
	return true;
}
list.success=function(){
	 $("#fileGrid").flexGetColByName({
	        'type':function(id,t){$(t).html(
	        		'<img src="'+$("#initPath").val()+'/view/esdemo/projectmanager/image/rar.gif" width="16" height="16" />'
	        		)},
    		'option':function(id,t){$(t).html(
	        		'<button type="button" onclick="modifyItem()">下载</button>'
	        		)}
	 });
}
function modifyItem()
{
	alert("下载成功");
}

$(document).ready(function(){
	$("#fileDown").click(function (){
		$("#fileDownDetail").toggle("slow");
		$("#fileDown span").toggleClass("collapsable");	
		})
	
	
 	//加载工作记录
	$("#historyDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	//返回上一步
	$("#lastStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/esdemo/projectmanager/purchase_file_validate.jsp");
	})
	//进行下一步
	$("#nextStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/esdemo/projectmanager/supplier_evaluation_online.jsp");
	})
	
})