<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="GoodsHistoryDetailForm" method="post" modelAttribute="goodsHistory">
		<input type="hidden" id="objId_history" name="objId" value="${goodsHistory.objId}"/>
	
	<div class="formLayout form2Pa">
		<h4 class="title"><span>商品信息</span><span class="eleRequired"></span></h4>
		<div class="k1">
			<img class="imgInch" src="<c:url value="AttachmentController.do?method=showImg&objId=${goodsHistory.picture}" />">
		</div>
		<ul>
			<li>
		 		<label for="goodsBrand.brandName">品牌名称：</label>
		 		${goodsHistory.goodsBrand.brandName}
		 	</li>
		 	<li>
		 		<label for="purCategory.categoryName">采购品目：</label>
		 		${goodsHistory.purCategory.categoryName}
		 	</li>
		 	<li>
		 		<label for="goodsClass.goodsClassName">商品分类：</label>
		 		${goodsHistory.goodsClass.goodsClassName}
		 	</li>
		 	<li>
		 		<label for="productName">商品名称：</label>
		 		${goodsHistory.productName}
		 	</li>
		 	<li>
		 		<label for="productCode">规格型号：</label>
		 		${goodsHistory.productCode}
		 	</li>
		 	<li>
		 		<label for="measureUnit">计量单位：</label>
		 		${goodsHistory.measureUnit}
		 	</li>
		 	<li>
		 		<label for="productDateIssued">发布日期：</label>
		 		<fmt:formatDate value="${goodsHistory.productDateIssued}" pattern="yyyy-MM-dd"/>
		 	</li>
		 	<li>
		 		<label for="referPrice">参考价(元)：</label>
		 		<span><fmt:formatNumber value="${goodsHistory.referPrice}" pattern="#,#0.00#" /></span>
		 	</li>
		 	<li>
		 		<label for="factory">制造商：</label>
		 		${goodsHistory.factory}
		 	</li>
		 	<li>
		 		<label for="factory">产地：</label>
		 		${goodsHistory.madeIn}
		 	</li>
		 	<li class="fullLine">
		 		<label for="externalInforLink">外部链接：</label>
		 		${goodsHistory.externalInforLink}
		 	</li>
		 	
		 	<c:if test="${goods.paramInputType==02}">
		 		<li id="spec_1" class="formTextarea">
	 				<label for="spec">详细配置：</label>
	 				${goodsHistory.spec}
	 			</li>
		 	</c:if>
		</ul>
	</div>
	
	<div class="formLayout form2Pa">
		<h4><span>商品参数</span></h4>
		<table class="tableList">
			<c:forEach var="goodsParam" items="${goodsHistory.goodsParamSet}">
				<c:if test="${goodsParam.goodsClassParam.isLeaf == false}">
				<tr>
					<td colspan="2"><em>${goodsParam.goodsClassParam.paramName}</em></td>
					<c:forEach var="paramLeaf" items="${goodsHistory.goodsParamSet}">
						<c:if test="${paramLeaf.goodsClassParam.isLeaf == true && paramLeaf.goodsClassParam.parent.objId==goodsParam.goodsClassParam.objId}">
							<tr>
								<td width="30%"><label>${paramLeaf.paramName}</label></td>
								<td>
									<c:choose>
										<c:when test="${!empty paramLeaf.goodsOptionalFittingSet}">
											${paramLeaf.paramValue}
											[选配:
											<c:forEach var="optinalFitting" items="${paramLeaf.goodsOptionalFittingSet}" varStatus="status2">
												<c:if test="${optinalFitting.isUse != '02'}">
													${optinalFitting.optionContent}
												</c:if>
											</c:forEach>
											]
										</c:when>
										<c:otherwise>
											${paramLeaf.paramValue}
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>
</form:form>
