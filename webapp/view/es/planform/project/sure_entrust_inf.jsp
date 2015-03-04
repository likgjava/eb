<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/sure_entrust_inf.js"></script>
<div class="partContainers">
<div class="formLayout form2Pa">
<form id="sureEntrustForm">
	<ul style="padding-top:0px">
		 <li>
		    <label for="input01">确认委托</label>
		    <input type="radio" value="0" id="acceptPlanyes" name="acceptPlan" checked="checked">同意委托
		    <input type="radio" value="1" id="acceptPlanno" name="acceptPlan"/>拒绝委托
		    <em>*</em>
		 </li>
		 <li class="formTextarea">
		    <label for="input20">意见</label>
		    <textarea name="opinion" style="width:40%;height:105px;margin-top:3px;"></textarea>
		 </li>
		 <li>
		    <div class="conOperation" style="text-align:center">
		       <button type="button" id="sure"><span>确定</span></button>
		   	</div>
		 </li>
	</ul>
</form>
</div>
</div>