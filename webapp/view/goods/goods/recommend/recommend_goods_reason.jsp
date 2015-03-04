<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="_dialogID"  value="<c:out value="${param.dialogId}"/>"/>
<div>
	<div>     
	<strong>推荐理由：</strong><br/>
	<textarea id="recommendReason" name="recommendReason" rows="5" cols="30" style="width: 365px; height: 180px"></textarea>
	</div>
	
	<div class="conOperation">
		<button id="CLOSE" type="button" tabindex="18"><span>关闭</span></button>
		<button id="OK" type="button" tabindex="18"><span>确定</span></button>
	</div>
</div>

<script>
$(document).ready(function(){
	//确定
	$("#OK").click(function(){
		var reason = $("#recommendReason").val();
		if(reason == ""){ alert("请填写推荐理由！"); return ;}

		//保存
		$.getJSON($('#initPath').val()+'/RecommendGoodsController.do?method=recommendGoods',
    			{"goodsIds":$("#goodsIds").val(),"recommendReason":reason},
    			function(json){
    				if(json.success){
    					alert("推荐成功！");
    					$("#recommendSuccess").val('1'); //标记推荐成功
    					$("#CLOSE").click();
    				}else{
    					alert("操作失败！");
    				}
    			}
    	);
		
	})

	//关闭
	$("#CLOSE").click(function(){
		if($("#_dialogID").val() != "")
			$(document.getElementById($("#_dialogID").val())).find('.epsDialogClose').trigger('click');
		else
			$('.epsDialogClose').trigger('click');
	});
});
</script>