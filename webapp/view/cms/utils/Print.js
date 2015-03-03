var errorInfo = "操作失败。请按如下步骤操作即可：\n  1：在IE浏览器的Internet选项的安全选项卡中将本站设置为可信站点；\n  2：在可信站点的自定义级别中，启用[对未标记为可安全执行脚本的ActiveX控件初始化并执行脚本]；\n  3：重试。";

	//指定页面区域内容导入Word
	//eDiv：要导出具体内容的div
	function ExpHtmlToWord(eDiv){
		try{
			var oWD = new ActiveXObject("Word.Application");
			var oDC = oWD.Documents.Add("",1,0);
			var oRange =oDC.Range(0,1);
			var sel = document.body.createTextRange();
			sel.moveToElementText(eDiv);
//			sel.select();//全部选中内容
			sel.execCommand("Copy");
			oRange.Paste();
			oWD.Application.Visible = true;

		}catch(e){
			$("#printInfo").empty();
			alert(errorInfo);
		}	
	}
	
	//导出Excel
	function ExpHtmlToExcel(eDiv){
		try{
			
		    var oXL = new ActiveXObject("Excel.Application");
		    var oWB = oXL.Workbooks.Add();
		    var oSheet = oWB.ActiveSheet;
		    var sel = document.body.createTextRange();
		    sel.moveToElementText(eDiv);
//		    sel.select();//全部选中
		    sel.execCommand("Copy");
		    oSheet.Paste();
		    oXL.Visible = true;
		    
		}catch (e) {
		}
	}

$(document).ready(function(){
//	var printId = $("#printId").val();
//	$.ajax({
//		url:$("#initPath").val()+"/RequestController.do?method=getTempLateContent",
//		type:"POST",
//		data:{"printId":printId},
//		async:false,
//		success:function(msg){
//			$("#printInfo").empty().append(msg);
//		}
//	})	
	
	$("#exp_excel").click(function(){
		try{
			ExpHtmlToExcel(document.getElementById("printInfo"));
		}catch (e) {
			alert(errorInfo);
		}
	})
	$("#exp_word").click(function(){
		try{
			ExpHtmlToWord(document.getElementById("printInfo"));
		}catch (e) {
			alert(errorInfo);
		}
	})
})

