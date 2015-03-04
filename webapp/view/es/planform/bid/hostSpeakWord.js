var errorInfo = "操作失败。请按如下步骤操作即可：\n  1：在IE浏览器的Internet选项的安全选项卡中将本站设置为可信站点；\n  2：在可信站点的自定义级别中，启用[对未标记为可安全执行脚本的ActiveX控件初始化并执行脚本]；\n  3：重试。";



function ExpHtmlToWord(eDiv){
	try{
		var oWD = new ActiveXObject("Word.Application");
		var oDC = oWD.Documents.Add("",1,0);
		var oRange =oDC.Range(0,1);
		var sel = document.body.createTextRange();
		sel.moveToElementText(eDiv);
//		sel.select(); 
		sel.execCommand("Copy");
		oRange.Paste();
		oWD.Application.Visible = true;

	}catch(e){
		alert(errorInfo);
	}	
} 

$(document).ready(function(){
	$('#print_td button').remove();
	$("#print_info").click(function(){
		try{
			wb.ExecWB(6,1);	
		}catch (e) {
			alert(errorInfo);
		}
	})

	$("#print_view_info").click(function(){
		try{
			wb.ExecWB(7,1);	
		}catch (e) {
			alert(errorInfo);
		}
	})
	
	$("#exp_info").click(function(){
		try{
			ExpHtmlToWord(document.getElementById("checkRequestInfo"));
		}catch (e) {
			alert(errorInfo);
		}
	})
})

