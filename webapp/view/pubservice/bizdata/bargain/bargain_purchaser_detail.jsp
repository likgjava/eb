<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/bizdata/bargain/bargain_detail.js"></script>

<div class="formLayout form2Pa">
	<form:form id="BargainPurchaserList" method="post" modelAttribute="bargain"> 
			<form:hidden path="objId"></form:hidden>
			<input type="hidden" id="type" value="<c:out value="${type}"/>"/>
	     	<h4><span>议价信息</span></h4>
			<ul>
				<li><label>议价编号：</label>${bargain.bargainNo}</li>
				<li><label>项目名称：</label>${bargain.bargainName}</li>
				<li class="fullLine"><label>采购人：</label><a href="javascript:void(0);" onclick="linkInfo('${bargain.buyerId}');return false;">${bargain.buyerName}</a></li>
				<li><label>降价幅度：</label>${bargain.totalCut}<span>元</span></li>
				<li><label for="bargainForm.useStatusCN">议价状态：</label><span id="useStatus">${bargain.useStatusCN}</span></li>
				<li><label>开始时间：</label><fmt:formatDate value="${bargain.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
				<li><label>结束时间：</label><fmt:formatDate value="${bargain.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
				<li><label>创建人：</label>${bargain.createUser.usName}</li>
				<li><label>创建日期：</label><fmt:formatDate value="${bargain.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
				<li class="fullLine"><label>议价备注:</label>${bargain.memo}</li>
			</ul>
	</form:form>
</div>
<div class="formLayout form3Pa">
<h4><span>协议供应商信息</span></h4>
	<ul>
		<c:forEach var="bargainSuppliers" items="${bargain.bargainSuppliers}">
			<li><a href="javascript:void(0);" onclick="linkInfo('${bargainSuppliers.supplierId}');return false;">${bargainSuppliers.supplierName}</a></li>
		</c:forEach>
	</ul>
</div>
<div class="formLayout form2Pa">
	<h4><span>商品详细信息</span></h4>
	<table id="BargainItemList" class="frontTableList">
		<thead>
			<tr>
				<th>商品编号</th>
				<th>商品名称</th>
				<th class="money">市场价</th>
				<th class="money">协议价</th>
				<th class="money">单价</th>
				<th class="amount">数量</th>
				<th class="money">金额</th>
			</tr>
		</thead>
	</table>
</div>
<div class="billingInfo">
	<ul>
		<li>数量总计：<span id="countGoods">0</span>件</li>
		<li>金额总计: <span id="totalMoney">0.00</span>元</li>
		<li>节省金额: <span id="saveAmount">0.00</span>元</li>
	</ul>
</div>
 <div class="conOperation">
 	<button type="button" name="enterBackBtn" type="button" tabindex="20" onclick="common.open('${project.srcUrl}','${project.objId}');"><span>进入</span></button>
	<button type="button" name="historyBackBtn" type="button" tabindex="20""><span><spring:message code="globe.return"/></span></button>
 </div>
