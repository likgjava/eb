/*
 * 我的任务书打印页面
 * author: liangxj
 * mail: liangxj@gpcsoft.com
 */

//定义文件全局变量 处理方法名重复问题
var printTaskDiv={};

$(document).ready(function(){
	var errorInfo = "操作失败。请按如下步骤操作即可：\n  1：在IE浏览器的Internet选项的安全选项卡中将本站设置为可信站点；\n  2：在可信站点的自定义级别中，启用[对未标记为可安全执行脚本的ActiveX控件初始化并执行脚本]；\n  3：重试。";
	
	//读取模板内容
	$.ajax({
		url: $("#initPath").val()+"/ProcurementtaskController.do?method=printProtask&objId="+$("#objId").val(),
		dataType:'json',
		success:function(json){
			$('#content').html(json.content);
		},
		error:function(msg){
			alert('操作失败');
		}
	})
	
	//关闭
	$("#close").click(function(){
		$('.epsDialogClose').trigger('click');
	})
	
	//打印
	$("#print").click(function(){
		try{
			wb.ExecWB(6,1); 
		}catch(e){
			alert(errorInfo);
		}
	})
	
	//导出
	$("#export").click(function(){
		try{
			var oWD = new ActiveXObject("Word.Application");
			var oDC = oWD.Documents.Add("",1,0);
			var oRange =oDC.Range(0,1);
			var sel = document.body.createTextRange();
			sel.moveToElementText(document.getElementById("printDiv"));
			sel.execCommand("Copy");
			oRange.Paste();
			oWD.Application.Visible = true;
		}catch(e){
			alert(errorInfo);
		}	
	})
});
	
