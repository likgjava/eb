/*
 * 我的任务书打印页面
 * author: liangxj
 * mail: liangxj@gpcsoft.com
 */

//定义文件全局变量 处理方法名重复问题
var printContractDiv={};


//导出
printContractDiv.exportWord = function(){
	window.location.href= $("#initPath").val()+"/AgContractController.do?method=expertContract&objId="+$("#objId").val();
}

//打印
printContractDiv.print = function(){
	window.open($("#initPath").val()+"/view/agreement/contract/contract_print_preview.jsp?objId="+$("#objId").val());
}

//关闭
printContractDiv.close = function(){
	$('.epsDialogClose').trigger('click');
}

$(document).ready(function(){
	var errorInfo = "操作失败。请按如下步骤操作即可：\n  1：在IE浏览器的Internet选项的安全选项卡中将本站设置为可信站点；\n  2：在可信站点的自定义级别中，启用[对未标记为可安全执行脚本的ActiveX控件初始化并执行脚本]；\n  3：重试。";
	
	//读取模板内容
	$.ajax({
		url: $("#initPath").val()+"/AgContractController.do?method=printContract&objId="+$("#objId").val(),
		dataType:'json',
		success:function(json){
			$('#contractContent').html(json.content);
		},
		error:function(msg){
			alert('操作失败');
		}
	})
	
	
});
	
