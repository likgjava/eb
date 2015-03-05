<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<form:form id="OrgInfoDetailForm" method="post" modelAttribute="goodsPrice">
	<input type="hidden" id="fileId"  value="${cash.cashFile}"/>
	<div class="formLayout form2Pa">
		<ul>
            <h4 align="center"><span>兑现积分详细信息</span></h4>
			<li><label>兑换积分额度  ：</label> <span><strong><fmt:formatNumber value="${cash.cashNumber}"  pattern="#,###.###" type="number"/></strong></span></li>
			<li><label>兑现金额：</label> <span><strong>￥<fmt:formatNumber value="${cash.cashMoney}" pattern="#,##0.00#" /> 元</strong></span></li>
			<li><label>兑换日期：</label> <span><fmt:formatDate value="${cash.cashDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></li>
			<li class="formTextarea"><label>备注：</label> <span>${cash.cashMemo}</span></li>
			<li class="fullLine" style="width: 800px;"><label for="">附件：</label>
				 <div id="cashFileDiv" style="width: 600px;" class="uploadFile"></div>
            </li>
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
	
    //附件
	$("#cashFileDiv").loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=cashFile&isSelect=yes&attachRelaId='+$('#fileId').val());
	
});
</script>