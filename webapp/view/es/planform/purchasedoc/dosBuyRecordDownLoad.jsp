<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/dosBuyRecordDownLoad.js"></script>
<input type="hidden" id="nodoc" name="nodoc" value="${NopurDoc}">
<input type="hidden" id="purdocId" name="purdocId" value="${purchaseDocId}">
<input type="hidden" id="isBuy" name="isBuy" value="${isBuy}">
<div id="purchaseDoc"></div>
<div id="purchaseDocDownLoad"></div>	


