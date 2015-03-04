<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/eval/indexView.js"></script>
<input type="hidden" id="html" name="html" value="${StringBuffer}"/>
<c:if test="${StringBuffer!=''}">
	<center><span><b>${project.projName}招标项目指标详情</b></span></center>
</c:if>
<div id="test" name="test">
</div>