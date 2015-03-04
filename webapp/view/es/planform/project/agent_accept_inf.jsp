<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/agent_accept_inf.js"></script>
<form id="agentAcceptForm">
	    <ul style="padding-top:0px">
	      <li>
	        <label for="input01">经办人接收</label>
	        <input type="radio" value="1" id="acceptyes" name="agentAcceptStatus" checked="checked">同意接收
	        <input type="radio" value="2" id="acceptno" name="agentAcceptStatus"/>拒绝接收
	        <em>*</em>
	      </li>
	      <li class="formTextarea">
	        <label for="input20">备注</label>
	        <textarea name="agentAcceptRemark" style="width:40%;height:105px;margin-top:3px;font-size:12px"></textarea>
	      </li>
	      <li>
	      	 <div class="conOperation" style="text-align:center">
	       		<button type="button" id="sure"><span>确定</span></button>
	   		 </div>
	      </li>
	    </ul>
</form>