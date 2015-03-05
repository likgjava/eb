<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="stats">
  <h3>认证采购人</h3>
  <p><fmt:formatNumber value="${systemStatisticsInfo.buyerCount}" pattern="#,###" /> 家</p>
  <h3>认证供应商</h3>
  <p><fmt:formatNumber value="${systemStatisticsInfo.supplierCount}" pattern="#,###" /> 家</p>
  <h3>已成交金额</h3>
  <p>￥<fmt:formatNumber value="${systemStatisticsInfo.bargainTotalMoney / 10000}" pattern="#,##0.00#" /> 万元</p>
</div>