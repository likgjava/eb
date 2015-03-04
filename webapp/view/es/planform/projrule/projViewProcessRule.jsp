<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/common/planTemplateTask.js"></script>
<div class="formLayout form2Pa">        
		<h5><span>查看项目规则</span></h5>
		<ul>
			<c:forEach items="${projProcessRuleList}" var="projProcessRule" varStatus="i">
				<li>
					<label class="short">
					<input type="checkbox" name="resValue" style="margin-top:5px;" disabled="disabled"
					<c:if test="${projProcessRule.resValue==true}">checked="checked"</c:if> />
					</label>
					<span>${projProcessRule.sysItemName}</span>
				</li>
			</c:forEach>
		</ul>
</div>
<script>
var projProcessRuleForm={};

$(document).ready(function(){
});
</script>