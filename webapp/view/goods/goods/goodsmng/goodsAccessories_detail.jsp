<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<input type="hidden" id="_dialogID"  value="<c:out value="${param.dialogId}"/>"/>

<div class="formLayout form2Pa">
	<form:form id="GoodsAccessoryForm" method="post" modelAttribute="goodsAccess">
			<h4 class="title"><span>零配件信息</span></h4>
			<div class="k1">
				<img width="200px" height="175px" src="<c:url value="AttachmentController.do?method=showImg&objId=${goodsAccess.accessoryGoods.picture}" />">
			</div>
		
			<ul>
				  <li class="fullLine">
				  <label>配件商品名称:</label>
				  ${goodsAccess.accessoryGoods.productName}
				  </li>
				  <li class="fullLine">
				  <label>配件数量:</label>
				  ${goodsAccess.accessoryQty}
				  </li>
				  <li class="fullLine hidden">
				  <label>是否启用:</label>
				  <c:if test="${goodsAccess.isOff=='1'}">启用</c:if>
				  <c:if test="${goodsAccess.isOff=='2'}">禁用</c:if>
				  </li>
			</ul>
		   <div class="conOperation">
				<button type="button" id="goodsAccessDetailCloseBtn"><span>关闭</span></button>
		   </div>
	</form:form>
</div>

<script>
$(document).ready(function(){	
	//关闭
	$("#goodsAccessDetailCloseBtn").click(function(){
		if($("#_dialogID").val() != ""){
			$(document.getElementById($("#_dialogID").val())).find('.epsDialogClose').trigger('click');
		}else{
			$('.epsDialogClose').trigger('click');
		}
	})
});
</script>
