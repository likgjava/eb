<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/reviewConditionDetail.js"></script>
<div class="partContainers">
<div class="formLayout form2Pa">	
	<ul>
		<li class="formTextarea">
			<label>专家意见</label>
			<textarea name="textarea1" rows="6" cols="80"></textarea>
		</li>
		<li class="formTextarea">
			<label>特殊情况说明</label>
			<textarea name="textarea1"  rows="6" cols="80"></textarea>
		</li>
	</ul>
	<div class="operationBtnDiv" align="center">
		<button id="submitBtn" type="button"><span>提交</span></button>
	</div>	
</div>
</div>