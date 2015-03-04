<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/inquiryprice/inqpdoc/configInqpDocPageForm.js"></script>
<div class="formLayout">
  			<h5>操作意见</h5>
      		<form id="purchaseDocDeptAuditForm" method="post">
      		<input type="hidden" name="objId" id="objId" value="<c:out value="${purchaseDoc.objId}"/>"/>
			<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
			<input type="hidden" name="auditStatus" id="auditStatus" />
			<ul style="padding-top:0px">
				<li class="formTextarea">
					<label>意见</label>
					<textarea name="opinion" id="opinion"></textarea>
				</li>
				<li>
					 <div class="conOperation" style="text-align:center">
			       	  	<button id="passButton" type="button" tabindex="18"><span>通过</span></button>
						<button id="noPassButton" type="button" tabindex="18"><span>不通过</span></button>
						<button id="returnButton" type="button" tabindex="18"><span>返回</span></button>
			   	    <button id="historyId" name="historyName" type="button" tabindex="20"><span>查看历史</span></button>
			   	    </div>
				</li>
			</ul>
			</form>
</div>

<div id="historyView"></div>