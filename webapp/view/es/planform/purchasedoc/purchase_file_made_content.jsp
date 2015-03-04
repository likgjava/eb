<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/purchase_file_made_content.js"></script>
<form id="purchaseFileForm">
<ul style="padding-left:7px;padding-top:10px">
	         <li>
      		<label><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>标题</label>
      		<input type="text" class="required" id="purchaseFileTitle" />
      		<em>*</em>
      		</li>
			<li>
			<label>上传<dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>：</label>
			<input type="file" name="title" id="title">&nbsp;&nbsp;<button type="button" id="sure"><span>确定</span></button>
			</li>
</ul>
</form>