<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<form:form id="OrgInfoDetailForm" method="post" modelAttribute="goodsPrice">
	<!--<input type="hidden" id="objId" name="objId" value="${orgInfo.objId}"/>-->
	<div class="formLayout form2Pa">
		<ul>
            <h4 align="center"><span>交易积分详细信息</span></h4>
			<li><label>交易积分额度  ：</label> <span><strong><fmt:formatNumber value="${deal.dealNumber}"  pattern="#,###.###" type="number"/></strong></span></li>
			<li><label>交易日期：</label> <span><fmt:formatDate value="${deal.dealDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></li>
			<li><label>谁赠送：</label> <span>${deal.formUser.usName}</span></li>
			<li><label>赠送给：</label> <span>${deal.toUser.usName}</span></li>
			<li class="formTextarea"><label>备注：</label> <span>${deal.dealMemo}</span></li>
		</ul>
	</div>	
</form:form>
<div style="height: 50px"></div>
<div class="conOperation">
	<button class="operationBtnDiv" type="button" id="close"><span>关闭</span></button>
</div>
<script type="text/javascript">
$(document).ready(function(){
	//关闭
	$("#close").click(function(){
		$('.epsDialogClose').trigger('click');
	})
});
</script>