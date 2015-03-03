<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">

var confrim_giveup_view = {};

//确认放弃
confrim_giveup_view.giveup = function(){
	//灰掉按钮
	$("#operationDiv button").attr("disabled",true);
	$.getJSON($("#initPath").val()+"/BuyResultXyghController.do?method=saveBuyResultAndResultItem",
		{
			"projectId":$("#projectId").val(),
			"opinion":$("textarea[name=opinion]").val(),
			"confirmType":"giveup"
		}
	,function(json){
		if(json.success){
			alert("操作成功！");
			confrim_giveup_view.success();
		}else{
			//释放按钮
			$("#operationDiv button").attr("disabled",false);
		}
	})
}

//成功后操作
confrim_giveup_view.success = function(){
	$("#operationDiv").html(null);
	$("input[name=confirmResult]").remove();
	$("button[name=toConfirmResultBtn]").remove();
	$('.epsDialogClose').trigger('click');
}

</script>

<div class="formLayout formPa">
	<ul>
		<li class="formTextarea">
			<label>放弃原因：</label>
			<textarea name="opinion"></textarea>
		</li>
	</ul>
</div>

<div class="conOperation">
	<button type="button" onclick="confrim_giveup_view.giveup();"><span>确定放弃</span></button>
	<button type="button" onclick="$('.epsDialogClose').trigger('click');"><span>关闭</span></button>
</div>
	
	
