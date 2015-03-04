<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript">
/*
 * 品牌查看页面
 * created by yucy
 */
var brandAdudit = {}
$(document).ready(function(){
})
</script>
<div>
	<div class="formLayout form2Pa">         
	  	<form:form id="brandForm" method="post" modelAttribute="goodsBrand">
	  	<form:hidden path="objId"/>
				<div class="k1">
					<div class="img_250_1" id="newPreview" style="width:200px;height:150px;">
					<c:choose>
					<c:when test="${goodsBrand.mainLogo==null}">
						<img width="200" height="150" src="<c:url value="/view/resource/skin/goods/img/goods_add.gif"/>"></img>
					</c:when>
					<c:otherwise>
						<img width="200" height="150" src="<c:url value="AttachmentController.do?method=showImg&objId=${goodsBrand.mainLogo}"/>"></img>
					</c:otherwise>
					</c:choose>
					</div>
				</div>
			  	<ul>
					<li>
				        <label for="brandName">品牌名称：</label>
				        <span id="brandName">${goodsBrand.brandName }</span>
					</li>
			   	  	<li>
				        <label for="brandCode">品牌编号：</label>
				        <span id="brandCode">${goodsBrand.brandCode }</span>
					</li>
					<li>
						<label for="englishName">品牌英文名称：</label>
						<span id="englishName">${goodsBrand.englishName}</span>
					</li>
					<li>
				        <label for="shortSpellName">拼音简写：</label>
				        <span id="shortSpellName">${goodsBrand.shortSpellName }</span>
					</li>
					<li>
				        <label for="shortSpellName">是否外地品牌：</label>
				        <span id="ecdemic">
				        <c:choose>
				        	<c:when test="${goodsBrand.ecdemic == true}">是</c:when>
				        	<c:when test="${goodsBrand.ecdemic == false}">否</c:when>
				        </c:choose>
				        </span>
					</li>
					<li>
				        <label for="shortSpellName">是否国外品牌：</label>
				        <span id="foreigner">
				        <c:choose>
				        	<c:when test="${goodsBrand.foreigner == true}">是</c:when>
				        	<c:when test="${goodsBrand.foreigner == false}">否</c:when>
				        </c:choose>
				        </span>
					</li>
					<li class="fullLine">
				       	<label for="goodsClassIds">所属供应商：</label>
						<span id="belongsName" style="display:inline;">${goodsBrand.belongsName}</span>
					</li>
					<li class="fullLine">
				       	<label for="goodsClassIds">商品分类：</label>
						<span id="goodsClassNames" style="display:inline;">${goodsBrand.goodsClassNames }</span>
					</li>

					<li class="fullLine">
	     				<label for="brandDesc">品牌说明：</label>
	     				<span id="brandDesc"  style="display:inline;">${goodsBrand.brandDesc }</span>
					</li>
					<c:if test="${goodsBrand.opinion!=null}">
						<li class="fullLine">
		     				<label for="opinion">审核意见：</label>
		     				<span>${goodsBrand.opinion }</span>
						</li>
					</c:if>
			   </ul>
		</form:form>
		<h4><span>品牌历史</span></h4>
		
		<div class="tableList">
		<table>
			<tr>
				<th style="text-align:center;">变更属性</th>
				<th style="text-align:center;">变更前</th>
				<th style="text-align:center;">变更后</th>
				<th style="text-align:center;">变更时间</th>
				<th style="text-align:center;">审核状态</th>
				<th style="text-align:center;">生效时间</th>
			</tr>
			<c:choose>
				<c:when test="${!empty goodsBrandChangeList}">
					<c:forEach var="goodsBrandChange" items="${goodsBrandChangeList }">
						<tr>
						<td align="left">
							<c:if test="${'brandName'==goodsBrandChange.modifyType}">品牌名称</c:if>
							<c:if test="${'englishName'==goodsBrandChange.modifyType}">英文名称</c:if>
							<c:if test="${'goodsClass'==goodsBrandChange.modifyType}">商品分类</c:if>
						</td>
						<td align="left">
							<c:choose>
							<c:when test="${'goodsClass'==goodsBrandChange.modifyType}">${fn:split(goodsBrandChange.oldValue,'##')[1]}</c:when>
							<c:otherwise>${goodsBrandChange.oldValue}</c:otherwise>
							</c:choose>
						</td>
						<td align="left">
							<c:choose>
							<c:when test="${'goodsClass'==goodsBrandChange.modifyType}">${fn:split(goodsBrandChange.newValue,'##')[1]}</c:when>
							<c:otherwise>${goodsBrandChange.newValue}</c:otherwise>
							</c:choose>
						</td>
						<td align="center"><span><fmt:formatDate value="${goodsBrandChange.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></td>
						<td align="center"><span>${goodsBrandChange.auditStatusCN }</span></td>
						<td align="center"><span><fmt:formatDate value="${goodsBrandChange.verifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></td>
						</tr>
					</c:forEach>
					<tr><td align="center" colspan="6"></td></tr>
				</c:when>
				<c:otherwise>
						<tr><td align="center" colspan="6">品牌暂无变更历史</td></tr><tr><td align="center" colspan="6"></td></tr>
				</c:otherwise>
			</c:choose>
		</table>
		</div>
		<div class="conOperation">
			<button id="closeHistory" type="button"  class="largeBtn" onclick="$('.epsDialogClose').trigger('click');" ><span><spring:message code="globe.close"/></span></button>
		</div>
 	</div>
</div>