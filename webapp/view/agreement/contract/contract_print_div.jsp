<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<input type="hidden" id="objId" name="objId" value="${param.objId}">
<input type="hidden" id="initPath" value="<%=request.getContextPath()%>" />
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/contract/contract_print_div.js"></script>
<div id="contractContent"></div>  
<div id="buttonDiv" class="operationBtnDiv noprint">	
 		<button type="button" tabindex="17" onclick="printContractDiv.print()"><span>打印</span></button>	
   		<button type="button" tabindex="17" onclick="printContractDiv.exportWord()"><span>导出</span></button>			
        <button type="button" tabindex="17" onclick="printContractDiv.close()"><span>关闭</span></button>
</div>