<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="GoodsAddParamForm" method="post" modelAttribute="goods">
	<div class="formLayout form2Pa">
	<input type="hidden" name="disableFitting" id="disableFitting" value="${param.disableFitting}"/>
    	<table class="tableList" id="fittingTable">
    	<caption>商品参数</caption>
			<tr>
				<td colspan='3'>商品名称：${goods.productName},商品型号：${goods.productCode}</td>
			</tr>
    	<c:choose>
			<c:when test="${!empty goods.goodsParamSet && fn:length(goods.goodsParamSet) > 0}">
			<c:forEach var="goodsParam" items="${goods.goodsParamSet}">
				<c:if test="${goodsParam.goodsClassParam.isLeaf == false}">
				<tr>
					<td colspan="3"><em>${goodsParam.goodsClassParam.paramName}</em></td>
					<c:forEach var="paramLeaf" items="${goods.goodsParamSet}">
						<c:if test="${paramLeaf.goodsClassParam.isLeaf == true && paramLeaf.goodsClassParam.parent.objId==goodsParam.goodsClassParam.objId}">
							<tr>
								<td width="30%">${paramLeaf.paramName}<c:if test="${!empty paramLeaf.goodsClassParam.paramUnit}">(${paramLeaf.goodsClassParam.paramUnit})</c:if>
								<input type="hidden" name="goodsParam.objId" value="${paramLeaf.objId}" />
								</td>
								<td width="50%" id="goodsParamValue_${paramLeaf.objId}">
									<c:choose>
									<c:when test="${!empty paramLeaf.goodsOptionalFittingSet}">
										${paramLeaf.paramValue}
										<c:forEach var="optinalFitting" items="${paramLeaf.goodsOptionalFittingSet}" varStatus="status2">
											<span id="${optinalFitting.objId}">
												[选配:<a href="javascript:void(0);" onclick="GoodsModifyParamForm.view('${optinalFitting.objId}');return false;" >${optinalFitting.optionContent}</a>&nbsp;
												<c:if test="${param.disableFitting == '0'}" >
													<a href="javascript:void(0);" onclick="GoodsModifyParamForm.remove('${optinalFitting.objId}');return false;">删除</a>]&nbsp;
												</c:if>
												<c:if test="${param.disableFitting == '1'}" >
													<c:choose>
														<c:when test="${optinalFitting.isUse != null && optinalFitting.isUse != '' && optinalFitting.isUse == '02'}">
															<font color="gray">已禁用</font>]
														</c:when>
														<c:otherwise>
															<a  id="${optinalFitting.objId}" href="javascript:void(0);" onclick="GoodsModifyParamForm.disable('${optinalFitting.objId}');return false;">禁用</a>
															<span class="hidden" name="disabledSpan"><font color="gray">已禁用</font></span>
															]
														</c:otherwise>
													</c:choose>
												</c:if>
											</span>
										</c:forEach>
									</c:when>
									<c:otherwise>
										${paramLeaf.paramValue}
									</c:otherwise>
									</c:choose>
								</td>
								<td class="center">
								<c:if test="${paramLeaf.goodsClassParam.canSelect==true}">
								<a href="javascript:void(0);" id="goodsParamId_${paramLeaf.objId}">添加选配</a>
								</c:if>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</tr>
				</c:if>
			</c:forEach>
			</c:when>
			<c:otherwise>
				<td colspan='3'>此商品暂无参数!</td>
			</c:otherwise>
		</c:choose>
		</table>
	</div>
	
	<div class="conOperation">
	<button type="button" id="managecloseBtn"><span>关闭</span></button>
	</div>
</form:form>

<script>
var GoodsModifyParamForm={};
//删除
GoodsModifyParamForm.remove=function(id){
	if(window.confirm("确定要删除此选配吗")){
		$.getJSON($('#initPath').val()+'/GoodsOptionalFittingController.do?method=remove',{objId:id},function(json){
			if(json.failure) {
				return;
			}
			$('span[id='+id+']').remove();
		});
	}
}
//禁用
GoodsModifyParamForm.disable=function(id){
	if(window.confirm("确定要禁用此选配吗")){
		$.getJSON($('#initPath').val()+'/GoodsOptionalFittingController.do?method=disableFitting',{objId:id},function(json){
			if(json.failure) {
				return;
			}
			$('span[id='+id+']').find('a[id='+id+']').addClass('hidden');
			$('span[id='+id+']').find('span').removeClass('hidden');
		});
	}
}
//查看选配
GoodsModifyParamForm.view=function(id){
	$.epsDialog({
		id:'viewFitting',
        title:'查看',
        url:$("#initPath").val()+'/GoodsOptionalFittingController.do?method=toOptionDetail&dialogId=viewFitting&objId='+id,
        width: '400',
        height: '300'
    })
}
$(document).ready(function(){
	//添加选配
	$("tr a[id^=goodsParamId_]").click(function() {
		var goodsParamId = $(this).attr("id").replace("goodsParamId_","");
		 $.epsDialog({
			 	id:'manageFitting',
		        title:'添加选配',
		        url:$("#initPath").val()+"/GoodsOptionalFittingController.do?method=toCreateOrUpdate&dialogId=manageFitting&goodsParamId=" + goodsParamId+"&disableFitting="+$('#disableFitting').val(),
		        width: '700',
		        height: '300',
		        onClose:function(){
		        	$('#manageFitting').find('#epsDialogCloseNoReload').click(); 
			    }
		 }); 
	})
	//关闭
	$('button[id=managecloseBtn]').click(function() {
		$('#epsDialogClose').click(); 
	})
})
</script>