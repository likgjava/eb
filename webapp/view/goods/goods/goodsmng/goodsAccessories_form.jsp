<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/goods/goods/goodsmng/goodsAccessories_form.js"></script>

<div class="formLayout form2Pa">
	<form:form id="GoodsAccessoryForm" method="post" modelAttribute="accessory">
			<input type="hidden" name="objId" id="objId" value="${accessory.objId}"/>
			
			<h4 class="title"><span>商品信息</span></h4>
			<div class="k1">
				<img width="200px" height="175px" src="" id="img">
			</div>
			<ul>
				<li>
			 		<label>商品名称：</label>
			 		<span id="productName"></span>
			 	</li>
			 	<li>
			 		<label>采购品目：</label>
			 		<span id="purCategory.categoryName"></span>
			 	</li>
			 	<li>
			 		<label>商品分类：</label>
			 		<span id="goodsClass.goodsClassName"></span>
			 	</li>
				<li>
			 		<label>品牌名称：</label>
			 		<span id="goodsBrand.brandName"></span>
			 	</li>
			 	<li>
			 		<label>计量单位：</label>
			 		<span id="measureUnit"></span>
			 	</li>
			 	<li>
			 		<label>规格型号：</label>
			 		<span id="productCode"></span>
			 	</li>
			 	<li>
			 		<label>参考价(元)：</label>
			 		<span id="referPrice"></span>
			 	</li>
			 	<li>
			 		<label>制造商：</label>
			 		<span id="factory"></span>
			 	</li>
			 	<li class="fullLine">
			 		<label>产地：</label>
			 		<span id="madeIn"></span>
			 	</li>
			 	<li class="fullLine">
			 		<label>外部链接：</label>
			 		<span id="externalInforLink"></span>
			 	</li>
			 	
			 	<c:if test="${goods.paramInputType==02}">
			 		<li id="spec_1" class="fullLine">
		 				<label>详细配置</label>
		 				<span id="spec"></span>
		 			</li>
			 	</c:if>
		 		
		 		<c:if test="${goods.functionIntro!=null && goods.functionIntro != ''}">
			 		<li class="fullLine">
			 				<label>商品描述：</label>
			 				<span id="functionIntro"></span>
			 		</li>
			 	</c:if>
			</ul>
		
			<h4><span>零配件</span></h4>
			<ul>
			<c:choose>
			  <c:when test="${empty accessory.objId}">
				  <li class="fullLine hidden">
				  <label>主商品1:</label>
				  <span>${productName}</span>
				  <input type="hidden" name="goods.objId" id="mainGoodsId" class="required" value="${goodsId}"/>
				  </li>
				  <li class="fullLine">
				  <label>零配件商品:</label>
				  <input type="text" name="accessoryGoods.productName" id="accessoryGoodsName" class="required sysicon siSearch" value="" readonly="readonly" size="45"/>
				  <input type="hidden" name="accessoryGoods.objId" id="accessoryGoodsId" class="required" value=""/>
				  </li>
				  <li class="fullLine">
				  <label>配件数量:</label>
				  <input type="text" name="accessoryQty" id="accessoryQty" class="required digits amount" value="" size="50"/>
				  </li>
				  <li class="fullLine hidden">
				  <label>是否启用:</label>
				  <select id="isOff" name="isOff">
				  	<option value=""></option>
				  	<option value="1" selected="selected">启用</option>
				  	<option value="2">不启用</option>
				  </select>
				  </li>
			  </c:when>
			  <c:otherwise>	
			  	  <input type="hidden" name="createUser.objId" id="createUser" value="${accessory.createUser.objId}"/>
				  <input type="hidden" name="createTime" id="createTime" value="${accessory.createTime}"/>
			  	  <li class="fullLine hidden">
				  <label>主商品2:</label>
				  <span>${accessory.goods.productName}</span>
				  <input type="hidden" name="goods.objId" id="mainGoodsId" class="required" value="${accessory.goods.objId}"/>
				  </li>
				  <li class="fullLine">
				  <label>零配件商品:</label>
				  <input type="text" name="accessoryGoods.productName" id="accessoryGoodsName" class="required sysicon siSearch" value="${accessory.accessoryGoods.productName}" size="50"/>
				  <input type="hidden" name="accessoryGoods.objId" id="accessoryGoodsId" class="required" value="${accessory.accessoryGoods.objId}"/>
				  <span class="eleRequired">*</span>
				  </li>	 
				  <li class="fullLine">
				  <label>配件数量:</label>
				  <input type="text" name="accessoryQty" id="accessoryQty" class="required digits amount" value="${accessory.accessoryQty}" size="50"/>
				  <span class="eleRequired">*</span>
				  </li>
				  <li class="fullLine hidden">
				  <label>是否启用:</label>
				  <select id="isOff" name="isOff">
				  	<option value="1" <c:if test="${accessory.isOff=='1'}">selected="selected"</c:if>>启用</option>
				  	<option value="2" <c:if test="${accessory.isOff=='2'}">selected="selected"</c:if>>不启用</option>
				  </select>
				  </li>
			  </c:otherwise>
			</c:choose>
			</ul>
		   <div class="conOperation">
				<button type="button" id="save"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="return"><span><spring:message code="globe.return"/></span></button>
		   </div>
	</form:form>
</div>
