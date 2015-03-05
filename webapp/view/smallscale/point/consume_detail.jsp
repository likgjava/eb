<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<form:form id="OrgInfoDetailForm" method="post" modelAttribute="goodsPrice">
	<!--<input type="hidden" id="objId" name="objId" value="${orgInfo.objId}"/>-->
	<div class="formLayout form1Pa">
		<ul>
            <h4 align="center"><span>消费积分详细信息</span></h4>
			<li><label>消费类型  ：</label> <span>${consume.consumeTypeCN}</span></li>
			<li><label>消费日期：</label> <span><fmt:formatDate value="${consume.consumeDate}" pattern="yyyy-MM-dd"/></span></li>
			<li><label>消费额度：</label> <span><strong><fmt:formatNumber value="${consume.consumeNumber}"  pattern="#,###.###" type="number"/></strong></span></li>
			<li><label>使用来源：</label> <span>${consume.consumeSource}</span></li>
			
			<li class="formTextarea"><label>备注：</label> <span>${consume.consumeMemo}</span></li>
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