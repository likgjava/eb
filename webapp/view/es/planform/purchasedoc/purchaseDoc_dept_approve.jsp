<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/purchaseDoc_dept_approve.js"></script>
<input type="hidden" id="projectId"   name="projectId"  value="${param.projectId}">
<input type="hidden" id="nodoc" name="nodoc" value="${NopurDoc}">
<div id="purchaseDoc"></div>
<div class="formLayout">        
	<form id="purchaseDocDeptApproveForm" method="post">
     		<ul>
					 <li>			
			<label>审核结果</label>
			<input type="radio" name="radio" id="radio"  checked="checked" value="1"/>通过 
			<input type="radio" name="radio" id="radio"  value="0"/>不通过 
					</li>
		<li class="formTextarea">
			<label>审核意见</label>
			<textarea id="opinion" name="opinion" cols="120" rows="5" style="width: 80%;height:105px;margin-top:3px"></textarea>
		</li>
					</ul>
	 </form>	
		<div class="conOperation">
		 <button type="button" id="submitBtn"><span>确定</span></button>
		</div>
	
</div>