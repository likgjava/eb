<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="GoodsAuditForm" method="post" modelAttribute="goodsAudit">
	<form:hidden path="objId" />
	<input type="hidden" name="currentId" id="currentId" value="${goodsAudit.currentId}" />
	<input type="hidden" name="useStatus" id="useStatus" value="${goodsAudit.useStatus}" />
	<input type="hidden" id="classId" value="${goodsAudit.goodsClass.objId}" />
	<input type="hidden" id="brandId" value="${goodsAudit.goodsBrand.objId}" />

	<div class="formLayout form2Pa">
	<h4 class="title"><span>商品信息</span></h4>
	<div class="k1">
		<img style="width:280px;height:280px;" class="imgInch" src="<c:url value="AttachmentController.do?method=showImg&objId=${goodsAudit.picture}" />">
	</div>
	<ul>
		<div class="formTips warm hidden" id="tips"></div>
		<li><label>商品名称：</label><span id="productName">${goodsAudit.productName}</span></li>
		<li><label>规格型号：</label><span id="productCode">${goodsAudit.productCode}</span></li>
		<li><label>品牌名称：</label><span id="goodsBrand.brandName">${goodsAudit.goodsBrand.brandName}</span></li>
		<li><label>采购品目：</label><span id="purCategory.categoryName">${goodsAudit.purCategory.categoryName}</span></li>
		<li><label>商品分类：</label><span id="goodsClass.goodsClassName">${goodsAudit.goodsClass.goodsClassName}</span></li>
		<li><label>计量单位：</label><span id="measureUnit">${goodsAudit.measureUnit}</span></li>
		<c:if test="${goodsAudit.useStatus=='01'}">
			<li class="fullLine"><label>发布时间：</label><span id="productDateIssued">${goodsAudit.productDateIssued}</span></li>
		</c:if>
		<li><label>参考价(元)：</label><span id="referPrice"><fmt:formatNumber value="${goodsAudit.referPrice}" pattern="#,##0.00#" /></span></li>
		<li><label>制造商：</label> <span id="factory">${goodsAudit.factory}</span></li>
		<li class="fullLine"><label>产地：</label> <span id="madeIn">${goodsAudit.madeIn}</span></li>
		<li class="fullLine"><label>外部链接：</label><span id="externalInforLink">${goodsAudit.externalInforLink}</span></li>
		<li><label>是否特供商品：</label><c:choose><c:when test="${goodsAudit.special == true}">是</c:when><c:otherwise>否</c:otherwise></c:choose></li>
		<li><label>是否自定义商品：</label><c:choose><c:when test="${goodsAudit.isCustom == true}">是</c:when><c:otherwise>否</c:otherwise></c:choose></li>
		<li><label>是否零配件：</label><c:choose><c:when test="${goodsAudit.isAccessory == true}">是</c:when><c:otherwise>否</c:otherwise></c:choose></li>
		<li><label>是否可单独出售：</label><c:choose><c:when test="${goodsAudit.soleToSell == true}">是</c:when><c:otherwise>否</c:otherwise></c:choose></li>
		<li><label>自主创新认定编号：</label>${goodsAudit.creationCode}<c:if test="${goodsAudit.creationCode == null}">无</c:if></li>
		<li><label>环境标志产品编号：</label>${goodsAudit.environmentLabel}<c:if test="${goodsAudit.environmentLabel == null}">无</c:if></li>
		<li><label>节能产品编号编号：</label>${goodsAudit.energySavingProductNo}<c:if test="${goodsAudit.energySavingProductNo == null}">无</c:if></li>
		<li><label>密码技术产品编号：</label>${goodsAudit.cryptographyTechCode}<c:if test="${goodsAudit.cryptographyTechCode == null}">无</c:if></li>
		<c:if test="${goodsAudit.paramInputType==02}">
			<li id="spec_1" class="fullLine"><label>详细配置：</label><p>${goodsAudit.spec}</p></li>
		</c:if>
	</ul>
	</div>
	<c:if test="${goodsAudit.functionIntro!=null && goodsAudit.functionIntro != ''}">
	<div class="formLayout form2Pa">
		<h4 class="title" style="clear:both;"><span>商品描述</span></h4>
		<div>${goodsAudit.functionIntro}</div>
	</div>
	</c:if>

	<div class="formLayout form2Pa">
	<h4><span>商品参数</span></h4>
	<table class="tableList">
		<c:forEach var="goodsParam" items="${goodsAudit.goodsParamSet}">
			<c:if test="${goodsParam.goodsClassParam.isLeaf == false}">
				<tr>
					<td colspan="2"><em>${goodsParam.goodsClassParam.paramName}</em></td>
					<c:forEach var="paramLeaf" items="${goodsAudit.goodsParamSet}">
						<c:if
							test="${paramLeaf.goodsClassParam.isLeaf == true && paramLeaf.goodsClassParam.parent.objId==goodsParam.goodsClassParam.objId}">
							<tr>
								<td width="30%"><label>${paramLeaf.paramName}<c:if test="${!empty paramLeaf.goodsClassParam.paramUnit}">(${paramLeaf.goodsClassParam.paramUnit})</c:if></label></td>
								<td><c:choose>
									<c:when test="${!empty paramLeaf.goodsOptionalFittingSet}">
											${paramLeaf.paramValue}
											[选配:
											<c:forEach var="optinalFitting"
											items="${paramLeaf.goodsOptionalFittingSet}"
											varStatus="status2">
												<c:if test="${optinalFitting.isUse != '02'}">
													${optinalFitting.optionContent}
												</c:if>
											</c:forEach>
											]
										</c:when>
									<c:otherwise>
											${paramLeaf.paramValue}
										</c:otherwise>
								</c:choose></td>
							</tr>
						</c:if>
					</c:forEach>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	</div>

	<div class="formLayout form2Pa">
	<ul>
	<li class="formTextarea"><label>审核意见：</label> 
		<textarea name="opinion" maxLength="100" class="required">${goodsAudit.opinion }</textarea>
	</li>
	</ul>
	</div>
	<div class="conOperation ">
	<button class="operationBtnDiv" type="button" id="agreeBtn"><span>审核通过</span></button>
	<button class="operationBtnDiv" type="button" id="refuseBtn"><span>审核不通过</span></button>
	<button class="largeBtn" id="returnBtn" type="button"><span><spring:message
		code="globe.return" /></span></button>
	<button type="button" class="largeBtn" id="viewHistory"><span>查看历史</span></button>
	</div>
	
</form:form>

<script>
var GoodsAuditForm = {};
//审核
GoodsAuditForm.auditGoods=function(goodsId,auditStatus){
  var jsonObj = formToJsonObject('GoodsAuditForm');
  jsonObj.auditStatus=auditStatus;
  $.getJSON($('#initPath').val()+'/GoodsController.do?method=auditGoods',jsonObj,function(json){
      if(json.result)alert(json.result);
      if(json.failure){return;}
      $('#conBody').loadPage($('#initPath').val()+"/view/goods/goods/goodsmng/goods_audit_list.jsp");
  });
}
$(document).ready(function(){
	$("#GoodsAuditForm").validate();
	
	$('#agreeBtn').click(function(){//通过
		if(window.confirm("确认通过吗?")){
			//校验商品是否重复
			$.getJSON($("#initPath").val()+"/GoodsController.do?method=checkGoodsUnique",{
				"objId":$('#objId').val(),
				"productName":native2ascii($('#productName').text()),
				"classId":$('#classId').val(),
				"brandId":$('#brandId').val()
				},function(json){
				if(json.success){
					$("#tips").empty().append(json.result);
					$("#tips").show();
				} else {
					$("#tips").hide();
					GoodsAuditForm.auditGoods($("#objId").val(),'02');
				}
			});
		}
	});
	$('#refuseBtn').click(function(){//不通过
		if($('textarea[name=opinion]').val() == ''){alert('请填写审核不通过的意见！谢谢...');return;}
		if(window.confirm("确认不通过吗?")){
		  GoodsAuditForm.auditGoods($("#objId").val(),'03');
		}
	});
	//返回
	$("#returnBtn").click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/view/goods/goods/goodsmng/goods_audit_list.jsp');
	})
	//查看历史
	$("#viewHistory").click(function(){
		var url = $('#initPath').val()+"/GoodsController.do?method=getGoodsHistory";
		if($("#currentId").val()!="" && $("#useStatus").val()=="00"){//变更
			url += "&id="+$("#currentId").val();
		}else{
			url += "&id="+$("#objId").val();
		}
		$.epsDialog({
			id:'goodsChangeHistoryDiv',
	        title:'商品变更历史',
	        url:url,
	        width:600,
	        height:300
	    }); 
	})
})
</script>
