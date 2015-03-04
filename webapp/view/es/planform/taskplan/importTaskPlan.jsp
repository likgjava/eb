<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript">

$('#taskPlanImport').click(function(){
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
	taskPlanList.submit();
});

taskPlanList.submit = function(){
		$('#budgetDetailForm').ajaxSubmit({
			url:$('#initPath').val()+'/TaskPlanController.do?method=inputTaskplanXML',
			dataType:'json',
			success:function(json){
				alert(json.result);
				if(json.result){alert(json.result); $('#taskPlanGrid').reload();}if(json.failure)return;
				$('#taskPlanClose').click();
			},
			error:function(json){
				alert(json.result);
				return;
			}
		});
}

$('#taskPlanClose').click(function(){
	$('#epsDialogCloseNoReload').click();
});

</script>

<div id="partContainers" class="partContainers" style="width: 100%">
<form id="budgetDetailForm" >
	<div class="conSearch">
		<h4><span>导入执行计划</span></h4>
		<ul>
			<li class="fullLine" id="xmlUrlLi" style="width:400px"> 
					执行计划XML文件：
		    	<input type="file" name="xmlUrl" id="xmlUrl" class="long"  value="" class="required"/>
		    	<span class="eleRequired"></span>
			</li>
		</ul>
	</div>
	<div class="conOperationBtnDiv">
		<button id="taskPlanImport" class="subBtn" type="button" tabindex="19"><span>导入</span></button>
		<button id="taskPlanClose" class="subBtn" type="button" tabindex="19"><span>关闭</span></button>
	</div>
</form>
</div>