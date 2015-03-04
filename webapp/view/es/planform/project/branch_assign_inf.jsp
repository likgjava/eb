<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/branch_assign_inf.js"></script>
<form id="branchAssignForm">
			<ul style="padding:0;margin:0;">
			      <li>
			        <label for="input02">执行部门</label>
			        <select name="department" id="department">
			        <option>-请选择-</option>
			        <option value="采购一部">采购一部</option>
			        <option value="采购二部">采购二部</option>
			        <option value="采购三部">采购三部</option>
			        <option value="综合部">综合部</option>
			        <option value="财务部">财务部</option>
			        </select>
			        <em>*</em>
			      </li>
			      <li class="formTextarea">
			        <label for="input20">分配意见</label>
			        <textarea name="assignOpition" style="font-size:12px;width:40%;height:105px;margin-top:3px"></textarea>
			      </li>
			      <li>
			      	<div class="conOperation" style="text-align:center">
			      		<button type="button" id="sure"><span>确定</span></button>
			 		</div>
			      </li>
			 </ul>
</form>