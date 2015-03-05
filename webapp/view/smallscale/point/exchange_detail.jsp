<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/smallscale/point/exchange_detail.js"></script>
<form:form id="OrgInfoDetailForm" method="post" modelAttribute="goodsPrice">
	<!--<input type="hidden" id="objId" name="objId" value="${orgInfo.objId}"/>-->
	<div class="formLayout form2Pa">
		<ul>
            <h4 align="center"><span>积分详细信息</span></h4>
			<li><label>获取方式  ：</label> <span>${exchange.exchangeTypeCN}</span></li>
			<li><label>购买金额：</label> <span><strong>￥<fmt:formatNumber value="${exchange.exchangeMoney}" pattern="#,##0.00#" /> 元</strong></span></li>
			<li><label>积分额度：</label> <span><strong><fmt:formatNumber value="${exchange.exchangeNumber}"  pattern="#,###.###" type="number"/></strong></span></li>
			<li><label>积分状态：</label> <span>${exchange.currentStatusCN}</span></li>
			<li><label>获得日期：</label> <span><fmt:formatDate value="${exchange.obtainDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></li>
			<li><label>有限日期：</label> <span><fmt:formatDate value="${exchange.valDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></li>
			<li class="formTextarea"><label>备注：</label> <span>${exchange.exchangeMemo}</span></li>
		</ul>
	</div>	
</form:form>
<div style="height: 50px"></div>
<div class="conOperation">
	<button class="operationBtnDiv" type="button" id="close"><span>关闭</span></button>
</div>
