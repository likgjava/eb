<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/purchase_file_made.js"></script>

	<div class="formLayout">
	<span>当前位置：编制文件 >> <dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out> >> 制作<dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></span> 
	
	<div class="partContainers operationLog"><h5 id="fileMade"><span class="switch  left11">制作<dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></span></h5></div>
	
	    <div id="purchaseFileMadeContent"></div>
	    
	    
	</div>

 <div class="functionBtnDiv" style="text-align:center;">
	    <button type="submit" id="lastStep" style="width:70px"><span>上一步</span></button>
	    <button type="submit" id="nextStep" style="width:70px"><span>下一步</span></button>
 </div>
<div id="historyDiv"></div>