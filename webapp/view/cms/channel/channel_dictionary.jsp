<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script>
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
//		sel.select();//全部选中内容
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
//	    sel.select();//全部选中
	    sel.execCommand("Copy");
	    oSheet.Paste();
	    oXL.Visible = true;
	}catch (e) {
		alert(errorInfo);
	}
}

$(document).ready(function(){
	// 查询模型条目
	var queryColumns="objId,label,name,dataType,formType,required,keyVal,defaultValue";
	var ChannelModelItemJson=$.ajax({url:$("#initPath").val()+"/ChannelModelItemController.do?method=getObjectQuery&queryColumns="+queryColumns,data:{"order":"sort","channelModel.objId":$("#channelModelId").val(),"display":true}, async: false}).responseText;
	var ChannelModelJsonObj = JSON.parse(ChannelModelItemJson).result;
	var channelDictionary = "";
	$(ChannelModelJsonObj).each(function(i,n){
		var isRequired = "--";
		if(n.required =="true")isRequired="必填";
		channelDictionary += '<tr>';
		channelDictionary += '<td>'+n.label+'</td>';
		channelDictionary += '<td>'+n.name+'</td>';
		channelDictionary += '<td align="center">'+isRequired+'</td>';
		channelDictionary += '<td>'+n.dataType+'</td>';
		channelDictionary += '<td>'+n.formType+'</td>';
		if(("select,radio,checkbox").indexOf(n.formType)>=0){
			channelDictionary += '<td>';
			var keyValArry = n.keyVal.split(",");
    		for(var i = 0; i <  keyValArry.length; i++) {
    			if(i > 0)channelDictionary += ",";
    			if(keyValArry[i].split("#")[1] == undefined || keyValArry[i].split("#")[1] == "" ){
    				channelDictionary += keyValArry[i].split("#")[0];
    			}else {
    				channelDictionary += keyValArry[i].split("#")[1];
    			}
    		}
			channelDictionary += '</td>';
		}else if(n.formType == "text"){
			channelDictionary += '<td>'+n.defaultValue+'</td>';
		}
		channelDictionary += '</tr>';
	})
	$('#channelDictionary').empty().append(channelDictionary);

	// 导出excel
	$("#exp_excel").click(function(){
		try{
			ExpHtmlToExcel(document.getElementById("printInfo"));
		}catch (e) {
			alert(errorInfo);
		}
	})
	// 导出word
	$("#exp_word").click(function(){
		try{
			ExpHtmlToWord(document.getElementById("printInfo"));
		}catch (e) {
			alert(errorInfo);
		}
	})
})

</script>
	
<div class="formLayout detail">
<input type="hidden" id="channelModelId" value="${param.channelModelId }"/>
	<div class="treeEditNav">
		<ul>
			<li class="pageExcel"><a href="javascript:void(0)" id="exp_excel"><span>导出Excel</span></a></li>
		</ul>
	</div>
  	<h5><span id="channelNameDataDictionary"></span></h5>
  	<div id="printInfo">
	<table>
	   <tr>
		<th style="text-align:center">字段名称</th>
		<th style="text-align:center">属性</th>
		<th style="text-align:center">是否必填</th>
		<th style="text-align:center">数据类型</th>
		<th style="text-align:center">表单类型</th>
		<th style="text-align:center">默认值/选项值</th>
	  </tr>
	   <tbody id="channelDictionary">
	   </tbody>
	</table>
		

</div>
</div>

<OBJECT id='wb' name='wb' classid='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2' height='0' width='0' ></OBJECT>