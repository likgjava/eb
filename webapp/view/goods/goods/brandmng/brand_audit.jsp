<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/goods/goods/brandmng/brand_audit.js"/>'></script>


<form:form id="brandForm" method="post" modelAttribute="goodsBrand">
<form:hidden path="objId"/>
<form:hidden path="currentId"/>
<div>

<div class="formTips attention">
	<ul>
		<li>
			<em>注意：</em><spring:message code="globe.brand.noscrappandforbid.prompt"/>
		</li>
		<c:if test="${!empty sameNameBrandList && fn:length(sameNameBrandList) > 0}">
		<li>
			<em>注意：</em>以下商品分类中已存在该品牌【<c:forEach var="brand" items="${sameNameBrandList}">${brand.goodsClassNames }</c:forEach>】，建议变更已审核通过的品牌的商品分类范围。
			<c:forEach var="brand" items="${sameNameBrandList}">${brand.brandName}-><a href="<%=request.getContextPath()%>/GoodsBrandController.do?method=toCreateOrUpdateView&objId=${brand.objId}" target="_blank">变更</a></c:forEach>
		</li>
		</c:if>
	</ul>
</div>

	<div class="formLayout imgAndForm">         
		<h4><span>新增商品品牌</span></h4>
		<div class="k1">
			<div class="img_250_1" id="newPreview">
				<c:choose>
				<c:when test="${goodsBrand.mainLogo==null}">
					<img src="<c:url value="/view/resource/skin/goods/img/goods_add.gif"/>"></img>
				</c:when>
				<c:otherwise>
					<img width="200" height="200" src="<c:url value="AttachmentController.do?method=showImg&objId=${goodsBrand.mainLogo}"/>"></img>
				</c:otherwise>
				</c:choose>
			</div>
		</div>
	  	<ul>
			<li class="fullLine">
		        <label for="brandName">品牌名称：</label>
		        <span id ="brandName">${goodsBrand.brandName }</span>
		        
		        <!-- 变更信息 -->
		        <c:if test="${brandName!=null}">
			        <span>【<font color="green">${brandName.newValue}</font>】</span>
		        </c:if>
		        
		        <c:if test="${brandNameCheck==false}">
		        	<span style="color:#CC0000;">【已有相同名称】</span>
		        </c:if>
		        
			</li>
			<li class="fullLine">
				<label for="englishName">品牌英文名称：</label>
				<span id= "englishName">${goodsBrand.englishName}</span>
				
				<!-- 变更信息 -->
		        <c:if test="${englishName!=null}">
			        <span>【<font color="green">${englishName.newValue}</font>】</span>
		        </c:if>
				
				<c:if test="${englishNameCheck==false}">
		        	<span style="color:#CC0000;">【已有相同名称】</span>
		        </c:if>
				
			</li>
			<li class="fullLine">
		        <label for="belongsName">所属机构：</label>
		        <span id ="belongsName">${goodsBrand.belongsName }</span>
			</li>
	   	  	<li>
		        <label for="brandCode">品牌编号：</label>
		        <span id ="brandCode">${goodsBrand.brandCode }</span>
			</li>
			<li>
		        <label for="shortSpellName">拼音简写：</label>
		        <span id ="shortSpellName">${goodsBrand.shortSpellName }</span>
			</li>
			<li>
		        <label for="ecdemic">是否外地品牌：</label>
		        <c:choose>
		        	<c:when test="${goodsBrand.ecdemic == true}"><span id="ecdemicCN">是</span></c:when>
		        	<c:when test="${goodsBrand.ecdemic == false}"><span id="ecdemicCN">否</span></c:when>
		        </c:choose>
			</li>
			<li>
		        <label for="foreigner">是否国外品牌：</label>
		        <c:choose>
		        	<c:when test="${goodsBrand.foreigner == true}"><span id="foreignerCN">是</span></c:when>
		        	<c:when test="${goodsBrand.foreigner == false}"><span id="foreignerCN">否</span></c:when>
		        </c:choose>
			</li>
			<li class="fullLine">
		       	<label for="goodsClassIds">商品分类：</label>
				<span id ="goodsClassNames">${goodsBrand.goodsClassNames }</span>
				
				<!-- 变更信息 -->
		        <c:if test="${changeClass!=null}">
			        <span>【<font color="green">${fn:split(changeClass.newValue,'##')[1]}</font>】</span>
		        </c:if>
				
			</li>
			<li class="fullLine">
    				<label for="brandDesc">品牌说明：</label>
    				<span id="brandDesc">${goodsBrand.brandDesc }</span>
			</li>
	   </ul>
	</div>
	
	<div class="formLayout form2Pa">
		<ul>
			<li class="formTextarea">
			<label for="opinion">审核意见：</label>
    			<textarea name="opinion">${goodsBrand.opinion }</textarea>
			</li>
		</ul>
	</div>
	
	<div class="conOperation">
		<c:choose>
		<c:when test="${brandName!=null||englishName!=null||changeClass!=null }">
			<c:if test="${brandNameCheck==true&&englishNameCheck==true}">
				<button type="button" onclick="brandAdudit.auditChange('pass');"  class="largeBtn" ><span>通过</span></button>
			</c:if>
			<button type="button" onclick="brandAdudit.auditChange('nopass');"  class="largeBtn"><span>不通过</span></button>
		</c:when>
		
		<c:otherwise>
			<c:if test="${brandNameCheck==true&&englishNameCheck==true}">
			<button type="button" onclick="brandAdudit.audit('pass');" class="largeBtn" ><span>通过</span></button>
			</c:if>
			<button type="button" onclick="brandAdudit.audit('nopass');" class="largeBtn"><span>不通过</span></button>
		</c:otherwise>
		</c:choose>
		
		<button type="button" name="historyBackBtn" class="largeBtn"  ><span><spring:message code="globe.return"/></span></button>
	</div>
</div>
</form:form>
