<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/contract/contract_file_div.js"></script>
<input type="hidden" id="fileId" value="<c:out value="${param.attachRelaId}"/>"/>
<div id="contractFile"></div>

<div class="conOperation">		
    <button  id="contractDivclose" type="button"><span>关闭</span></button>
</div>


