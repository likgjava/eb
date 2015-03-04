<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div id="partContainers" class="partContainers">
<form id="budgetDetailForm" >
	<div class="conSearch">
		<h4><span>导入采购预算</span></h4>
		<ul>
			<li class="fullLine">
					预算XML文件：
		    	<input type="file" name="xmlUrl" id="xmlUrl" class="long"  value="" class="required"/>
		    	<span class="eleRequired"></span>
			</li>
			<li class="operationBtnDiv">
				<span class="sysicon siUpBtn"  title="导入" style="cursor: hand" id="upload">&nbsp;</span>
			</li>
		</ul>
	</div>
</form>
</div>

<script>
var BudgetDetailForm = {};
$(document).ready(function(){
	BudgetDetailForm.submit = function(){
		$('#budgetDetailForm').ajaxSubmit({
			url:$('#initPath').val()+'/TaskPlanController.do?method=inputTaskplanXML',
			dataType:'json',
			success:function(json){
				if(json.result)alert(json.result);if(json.failure)return;
			}
		});
	}
	
	$('#upload').click(function(){
		if (null == $('#xmlUrl').val() || null == $('#xmlUrl').val() || $('#xmlUrl').val().toString().length<1) {
			alert('请选择 预算XML文件');
			return false;
		}
		var filePath = $('#xmlUrl').val().replace(/.+\\([^\\]+)/,'$1');
		var i = filePath.lastIndexOf('.');        //从右边开始找第一个'.'
		var len = filePath.length;                //取得总长度
		var str = filePath.substring(len,i+1);    //取得后缀名
		var exName = "XML";       //允许的后缀名
		var k = exName.indexOf(str.toUpperCase());//转成大写后判断
		if(k==-1) {
		  alert("上传文件错误！只能上传"+exName);
		  return false;
		}
		BudgetDetailForm.submit();
	})
})
</script>