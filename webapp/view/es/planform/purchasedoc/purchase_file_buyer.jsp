<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/purchase_file_buyer.js"></script>
<div class="formLayout">
<div class="partContainers operationLog"><h5 id="taskbook"><span class="switch left11"><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></span></h5></div>

<div id="noticeDiv"></div>
<div class="formLayout form2Pa">
	<form id="sureEntrustForm" method="post">
	<div class="conOperation">									
		<button type="button" id="sure"><span>确定</span></button>
	</div>
	</form>
</div>
</div>
