<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/assignDept.js"></script>
<div class="partContainers">
<div class="formLayout form2Pa">
		<h5><span>分配执行部门</span></h5>
   		<form id="assignDeptForm">
   		<input type="hidden" name="projDepartMent" id="projDepartMent" value="${project.projDepartMent }"/>
	<ul>
	      <li class="fullLine">
	        <label for="input02">执行部门：</label>
	        <select name="department" id="department" class="required">
	        <option value="采购一部">采购一部</option>
	        <option value="采购二部">采购二部</option>
	        <option value="采购三部">采购三部</option>
	        <option value="综合部">综合部</option>
	        <option value="财务部">财务部</option>
	        </select>
	        <span class='eleRequired'>*</span><span class="eleNote"></span>  
	      </li>
	      <li class="formTextarea">
	        <label for="input20">分配意见：</label>
	        <textarea name="assignOpition"></textarea>
	      </li>
	 </ul>
	 <div class="conOperation" style="text-align:center">
     		<button type="button" id="sure"><span>确定</span></button>
		</div>
	</form>
  	</div>
</div>