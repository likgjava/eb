<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/bid_notice_release.js"></script>
<div class="formLayout">
<span style="margin-left:5px">当前位置：采购预告 >> 发布采购预告</span>
<div class="partContainers operationLog"><h5 id="preNotice"><span class="switch left11">采购预告</span></h5></div>
<div id="noticeDiv"></div>
<ul style="padding-top:0px">
	<li>
		<label>附件</label>
		<a href="#">公告附件.doc</a> 540KB
	</li>
	<li>
		<div align="center"><input type="button" value="发布预告" id="sub"></div>
	</li>
	<li>
		<div class="functionBtnDiv" style="padding-top:5px;padding-bottom:5px">
			<button type="button" id="pre"><span>上一步</span></button><button type="button" id="next"><span>下一步</span></button>
		</div>
	</li>
</ul>
</div>
<div id="auditHistory"></div>