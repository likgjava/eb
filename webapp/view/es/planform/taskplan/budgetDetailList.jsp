<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script>
var BudgetDetailForm = {};
BudgetDetailForm.success=function(){
	$("#budgetDetailGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				$("#returnUrl").val($('#initPath').val()+'/BudgetDetailController.do');
				$('#conBody').empty().loadPage($('#initPath').val()+'/BudgetDetailController.do?method=toShowDetail&objId='+rowId);
			}).appendTo(obj);
		}
	});
}
/**
 * 上传预算文件
 */
BudgetDetailForm.submit = function(){
	$('#budgetDetailForm').ajaxSubmit({
		url:$('#initPath').val()+'/BudgetDetailController.do?method=inputBudgetDetailXML',
		dataType:'json',
		success:function(json){
			if (json.isResult == 'false') {
				var html = json.message;
				html += '<div class="conOperation">';
				html += '<button type="button" tabindex="18"><span>关闭</span></button>';
				html += '</div>';
				$.epsDialog({
					content:html,
		    		id:'message_content',
		        	title:'您的预算文件数据有错,导入失败!以下是错误信息..',
		        	url:'',
		        	width: '650',
		        	height: '380',
		        	isReload:false
		    	});
				$('#message_content').find('button').click(function(){
					$('#message_content .epsDialogCloseReload').click();
				})
			} else {
				alert('采购预算导入成功!');
				$('#budgetDetailGrid').reload();
			}
		}
	});
}


/**
 * 上传文件按钮事件
 */
$('#submitFileBtn').click(function(){
	if (null == $('#budgetDetailFile').val() || null == $('#budgetDetailFile').val() || $('#budgetDetailFile').val().toString().length<1) {
		alert('请选择 预算XML文件');
		return false;
	}
	var filePath = $('#budgetDetailFile').val().replace(/.+\\([^\\]+)/,'$1');
	var i = filePath.lastIndexOf('.');        //从右边开始找第一个'.'
	var len = filePath.length;                //取得总长度
	var str = filePath.substring(len,i+1);    //取得后缀名
	var exName = "XLS,XML";       //允许的后缀名
	var k = exName.indexOf(str.toUpperCase());//转成大写后判断
	if(k==-1) {
	  alert("上传文件错误！只能上传"+exName);
	  return false;
	}
	BudgetDetailForm.submit();
})
</script>
<div id="partContainers" class="partContainers">
<form id="budgetDetailForm" >
	<div class="conSearch">
		<h4><span>导入采购预算</span></h4>
		<ul>
			<li class="fullLine">
					预算XML文件：
		    	<input type="file" name="budgetDetailFile" id="budgetDetailFile" class="long"  value="" class="required"/>
		    	<span class="eleRequired"></span>
			</li>
			<li class="operationBtnDiv">
				<span class="sysicon siUpBtn"  title="导入" style="cursor: hand" id="submitFileBtn">&nbsp;</span>
			</li>
		</ul>
	</div>
</form>
	<flex:flexgrid checkbox="false" id="budgetDetailGrid" url="BudgetDetailController.do?method=list" 
	onSuccess="BudgetDetailForm.success" rp="10" title="采购预算明细" queryColumns="">
		<flex:flexCol name="year" display="budgetDetailForm.year" sortable="true" width="100" align="center"></flex:flexCol>
		<flex:flexCol name="organizationName" display="budgetDetailForm.organizationName" sortable="true" width="200" align="left"></flex:flexCol>
		<flex:flexCol name="purchaseItemName" display="budgetDetailForm.purchaseItemName" sortable="true" width="200" align="left"></flex:flexCol>
		<flex:flexCol name="category.categoryName" display="budgetDetailForm.category" sortable="true" width="100" align="left"></flex:flexCol>
		<flex:flexCol name="zjlyZj" display="budgetDetailForm.zjlyZj" sortable="true" width="120" align="right"></flex:flexCol>
	</flex:flexgrid>
</div>