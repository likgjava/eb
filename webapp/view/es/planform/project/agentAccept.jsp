<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/agentAccept.js"></script>
<div class="partContainers">
<div class="formLayout form2Pa">
	<h5><span>经办人接收</span></h5>
	   <form id="agentAcceptForm">
		  <ul>
		      <li class="fullLine">
		        <label for="input01">经办人接收：</label>
		        <input type="radio" value="1" id="acceptyes" name="agentAcceptStatus" checked="checked">同意接收
		        <input type="radio" value="2" id="acceptno" name="agentAcceptStatus"/>拒绝接收
		      </li>
		      <li class="formTextarea">
		        <label for="input20">备注：</label>
		        <textarea name="agentAcceptRemark"></textarea>
		      </li>
		  </ul>
	     <div class="conOperation">
       		<button type="button" id="sure"><span>确定</span></button>
   		 </div>
	</form>
</div>
</div>