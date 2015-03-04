<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/branch_assign.js"></script>
<div class="formLayout">
	<span style="margin-left:5px">当前位置：任务立项 >> 分配执行部门</span>
    <h5 id="taskbook"><span class="switch">项目任务书</span></h5>
    <div id="taskInfoDiv"></div>

	<h5 id="taskDetail"><span class="switch">任务书明细</span></h5>
	<div id="taskDetailList"></div>
	
	<h5 id="branchAssign"><span class="switch">分配执行部门</span></h5>
	<form id="branchAssignForm">
	    <div id="branchAssignDetail">
			<ul style="padding-top:0px">
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
			      	<div class="conOperation" style="padding-left:280px">
			      		<button type="button" id="sure"><span>确定</span></button>
			 		</div>
			      </li>
			      <li>
			      	 <div class="functionBtnDiv" style="text-align:right;padding-right:20px;padding-top:5px;padding-bottom:5px">
		    			<button type="button" id="next"><span>下一步</span></button>
	     			 </div>
			      </li>
			 </ul>
	     </div>
	 </form>
 </div>
 <div id="historyDiv"></div>