<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/workFlow/SchedulingDetail.js"></script>
<div class="form-container">
<form method="post" name="SchedulingDetailForm" id="SchedulingDetailForm">
<input type="hidden" name="scheId" id="scheId" value="<c:out value="${param.scheId}"/>"/>
	<div class="tableTip"> 
	<div id="receiverNameF"></div>
	
	<div id="msg" style="margin-left:10px"></div>
	
	</div>
</form>
</div>
