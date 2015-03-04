<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="GoodsChangeForm" method="post"> 
	<input type="hidden" name="goodsId" id="goodsId" value="${goodsId}"/>
	<c:set var="goodsName"></c:set>
	<c:set var="modUser"></c:set>
	<c:set var="modTime"></c:set>
    <div class="formTips light">
		<ul>
		<li class="big"><em><span id="goodsNameSpan"></span></em> 申请变更，变更信息如下：</li>
		<c:forEach var="changeGoods" items="${modifyAudit}" varStatus="status1">
			<c:set var="goodsName" value="${changeGoods.goods.productName}"></c:set>
			<c:set var="modUser" value="${changeGoods.createUser.emp.name}"></c:set>
			<c:set var="modTime" value="${changeGoods.createTime}"></c:set>
			<li>
				<c:if test="${changeGoods.modifyType=='purCategory'}">
							<strong>商品品目：</strong>
						</c:if>
						<c:if test="${changeGoods.modifyType=='goodsClass'}">
							<strong>商品分类：</strong>
						</c:if>
						<c:if test="${changeGoods.modifyType=='goodsBrand'}">
							<strong>商品品牌：</strong>
						</c:if>
						${changeGoods.oldValueName}<font color='red'> 变更为  >> </font>${changeGoods.newValueName}
			</li>
		</c:forEach>
		<li class="r">申请变更人：${modUser}</li>
		<li class="r">申请时间：<fmt:formatDate value="${modTime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
		</ul>
		<input type="hidden" value="${goodsName}" id="goodsNameInput"/>
	</div>
	<div class="formLayout form2Pa">
		<ul>
			<li class="formTextarea">
				<label>审核意见：</label> 
				<textarea name="opinion" maxlength="250" class="required"></textarea>
				<span class="eleRequired">*</span>
			</li>
		</ul>
	</div>
	<div class="conOperation">
		<button type="button" id="agree"><span>通过</span></button>
		<button type="button" id="notAgree"><span>不通过</span></button>
		<button type="button" id="return"><span>返回</span></button>
	</div>
</form:form>		

<script>
var GoodsChangeForm={};

//审核
GoodsChangeForm.audit=function(status){
	if(!$("#GoodsChangeForm").valid()){
		alert("请正确填写信息!");return;
	}
	
	var jsonObj = formToJsonObject('GoodsChangeForm');
	jsonObj.status=status;
	  
	var msg = status=='02'?"通过":"不通过";
	if(window.confirm('确定审核'+msg+"吗?")) {
	  $.getJSON($('#initPath').val()+'/GoodsChangeController.do?method=auditGoodsChange',jsonObj,function(json){
	      if(json.result)alert(json.result);
	      if(json.failure){return;}
	      $('#return').click();
	  });	
	}
}

$(document).ready(function(){
	$("#goodsNameSpan").text($("#goodsNameInput").val());

	$("#GoodsChangeForm").validate();
	
	//通过
	$('#agree').click(function(){
		GoodsChangeForm.audit('02');
	})
	//不通过
	$('#notAgree').click(function(){
		GoodsChangeForm.audit('03');
	})
	
	//返回
	$('#return').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/GoodsChangeController.do?method=toGoodsChange&viewName=goodsChangeAuditListView');
	})
})
</script>