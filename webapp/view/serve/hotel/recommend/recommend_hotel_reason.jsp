<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="_property"  value="<c:out value="${param.property}"/>"/>
<input type="hidden" id="_dialogID"  value="<c:out value="${param.dialogId}"/>"/>

<div>
	<div>     
	<strong>推荐理由：</strong><br/>
	<textarea id="recommendReasonInput" name="recommendReason" rows="5" cols="30" style="width: 365px; height: 180px"></textarea>
	</div>
	
	<div class="conOperation">
		<button id="OK" type="button" tabindex="18"><span>确定</span></button>
	</div>
</div>

<script>
/*
 * 填写酒店推荐理由页面
 * created by likg
 */

$(document).ready(function(){
	//确定
	$("#OK").click(function(){
		var rr = $("#recommendReasonInput").val();
		
		if(rr == ""){
			alert("请填写推荐理由！");
		}else{
			document.getElementById($("#_property").val()).value=rr;
			//关闭弹出层
			if($("#_dialogID").val() != "")
				$(document.getElementById($("#_dialogID").val())).find('.epsDialogClose').trigger('click');
			else
				$('.epsDialogClose').trigger('click');
		}
	})
});
</script>