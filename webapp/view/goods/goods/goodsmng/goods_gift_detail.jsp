<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="_dialogID"  value="<c:out value="${param.dialogId}"/>"/>

<div id="goodsGiftInfo" class="formLayout form2Pa">
	<h4 class="title"><span>礼包信息</span></h4>
	<div class="k1">
		<div class="img_250_2" id="newPreview">
		<img src="<c:url value="AttachmentController.do?method=showImg&objId=${goodsGift.giftPicture}" />" width="200px" height="175px">
		</div>
	</div>
	<ul>
		<li class="fullLine">
			<label>礼包名称 ：</label> 
			<span>${goodsGift.giftName}</span>
		</li>
		<li class="fullLine">
			<label>礼包描述：</label>
			${goodsGift.giftDesc}
		</li>
	</ul>
	<div class="conOperation">
		<button type="button" id="goodsGiftDetailCloseBtn"><span>关闭</span></button>
	</div>
</div>
	
<script>
/**
 * 礼包详情页面
 * create by likg
 */
$(document).ready(function(){	

	//关闭
	$("#goodsGiftDetailCloseBtn").click(function(){
		if($("#_dialogID").val() != ""){
			$(document.getElementById($("#_dialogID").val())).find('.epsDialogClose').trigger('click');
		}else{
			$('.epsDialogClose').trigger('click');
		}
	})
});
</script>
