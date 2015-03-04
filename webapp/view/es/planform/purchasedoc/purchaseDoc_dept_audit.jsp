<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/purchaseDoc_dept_audit.js"></script>
<input type="hidden" id="nodoc" name="nodoc" value="${NopurDoc}">
<div id="purchaseDoc"></div>

<div class="formLayout">
  			<h5>操作意见</h5>
      		<form id="purchaseDocDeptAuditForm" method="post">
      		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
			<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
			<input type="hidden" name="auditStatus" id="auditStatus" value="Y"/>
			<ul style="padding-top:0px">
				<li class="formTextarea">
					<label>意见</label>
					<textarea name="opinion" id="opinion"></textarea>
				</li>
				<li>
					 <div class="conOperation" style="text-align:center">
			       	  	<button id="passButton" type="button" tabindex="18"><span>通过</span></button>
						<button id="noPassButton" type="button" tabindex="18"><span>不通过</span></button>
			   	    </div>
				</li>
			</ul>
			</form>
    	</div>
