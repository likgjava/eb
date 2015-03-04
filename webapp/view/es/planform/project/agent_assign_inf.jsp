<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/agent_assign_inf.js"></script>
<div class="partContainers">
<div class="formLayout form2Pa">
<form id="agentAssignForm">
	<ul style="padding-top:0px">
		<li>
		   <label for="input02">指定经办人</label>
		   <input type="hidden" name="project.objId" id="project.id" value="${param.projectId }"/>
		   <select name="linkGovMan" id="linkGovMan">
		        <option>-请选择-</option>
		        <option value="范晓玲">范晓玲</option>
		        <option value="李兴邦">李兴邦</option>
		        <option value="朱传文">朱传文</option>
		        <option value="李建国">李建国</option>
		        <option value="张文斌">张文斌</option>
		        </select>
		        <span class='eleRequired'>*</span><span class="eleWarning"></span>
		      </li>
		      <li class="formTextarea">
		        <label for="input20">备注</label>
		        <textarea name="remark" style="font-size:12px;width:40%;height:105px;margin-top:3px"></textarea>
		        <span class="eleNote"></span>
		      </li>
	</ul>
	<div class="conOperation" style="text-align:center">
			    	<button type="button" id="sure"><span>确定</span></button>
				 </div>
</form>
</div>
</div>