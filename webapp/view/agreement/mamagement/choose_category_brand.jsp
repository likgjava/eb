<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/mamagement/choose_category_brand.js"></script>


<!-- 导航显示 -->
<div class="nextBar">
    <ol class="num4">
        <li class="done current-prev"><span class="first">1. 填写协议基本信息</span></li> 
        <li class="current"><span>2. 增加协议商品分类</span></li>
        <li class="last"><span>3. 增加协议单品</span></li>
    </ol>
</div>

<input type="hidden" name="resource" id="resource" value="<c:out value="${param.resource}"/>"/>
<input type="hidden" name="agreementSecondId" id="agreementSecondId" value="<c:out value="${param.agreementSecondId}"/>"/>
<input type="hidden" name="agreementType" id="agreementType" value="<c:out value="${param.agreementType}"/>"/>
<input type="hidden" name="isNew" id="isNew" value="<c:out value="${param.isNew}"/>"/>

<div class="formLayout form2Pa">
	<form:form id="agreementForm" method="post" modelAttribute="agreement">
	<form:hidden path="objId"></form:hidden>
    <h4><span>协议信息</span></h4>
    <ul>
        <li>
            <label>协议名称：</label>
            <span id="name">${agreement.name}</span>
        </li>   
        <li>
            <label>签订时间：</label>
            <span id="creTime">${agreement.creTime }</span>
        </li>
        <li>
            <label>甲方(代理机构)：</label>
            <span id="org.orgName">${agreement.org.orgName }</span>
        </li>    
        <li>
            <label>乙方(经销商)：</label>
           	<input type="hidden" id="org.objId" name="org.objId" value="${agreement.supplier.objId}" />
            <span id="org.orgName">${agreement.supplier.orgName }</span>
        </li>      
        <li class="fullLine">
            <label>协议期间：</label>
            <span id="period.periodName">
            <fmt:formatDate value="${agreement.period.beginDate }" pattern="yyyy-MM-dd"/>&nbsp;至&nbsp;
            <fmt:formatDate value="${agreement.period.endDate }" pattern="yyyy-MM-dd"/>
            </span>
        </li>
        <li>
            <label>协议区间：</label>
            <span id="area.areaName">${agreement.areaNames }</span>
        </li>
        <li >
            <label>备注：</label>
            <span id="memo">${agreement.memo }</span>
        </li>
    </ul>
    </form:form>
</div>

<!-- 操作 -->
<div class="functionBtnDiv right">
	<button type="button" id="chooseCategoryAndBrandAdd" onclick="chooseCategoryAndBrand.add();"><span>新增</span></button>
    <button type="button" id="chooseCategoryAndBrandDel" onclick="chooseCategoryAndBrand.del();"><span>删除</span></button>
</div>

<!--列表 -->
	<div>
	<table class="frontTableList" id="chooseCategoryAndBrand">
		<thead>
			<tr>
				<th>商品品牌</th>
				<th>商品分类</th>
				<th class="amount">折扣(%)</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody id="chooseCategoryAndBrandTBody">
		</tbody>
	</table>
	</div>
	
	<div class="conOperation center">
		<button  id="chooseCategoryAndBrandNext" type="button" onclick="chooseCategoryAndBrand.toChooseAgreementGoods();"><span><spring:message code="globe.next"/></span></button>
		<button  id="chooseCategoryAndBrandReturn" type="button" onclick="chooseCategoryAndBrand.toAgreementList();"><span><spring:message code="globe.return"/></span></button>
	</div>  

<!-- 模板 -->
<div id="tempTrDiv" class="hidden">
<table>	
<tr objid="newClassAndBrandTr">
	<td class="center">
		<input type="checkbox" value="" name="checkAll">
	</td>
	<td name="brand.brandName" >
		<input type="hidden" value="" id="brand.objId">
		<input type="text" value="" id="newBrandName" readonly="readonly" class="sysicon siSearch">
	</td>
	<td name="goodsClass.goodsClassName">
		<select id="newClassName">
			<option value="chooseBrandFirst">--请先选择品牌--</option>
		</select>
	</td>
	<td name="discountRatio" class="amount">
		<input type="text" style="width:20px;" value="" class="discountRatio" onchange="chooseCategoryAndBrand.discountRatioChange(this);">
	</td>
	<td class="operation"><a type="alink" name="newRowSave" href="javascript:void(0);"><span>保存</span></a></td>
</tr>
</table>
</div>
	
