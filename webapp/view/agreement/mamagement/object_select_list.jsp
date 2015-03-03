<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%> 
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/mamagement/object_select_list.js"></script>

<%	
String temps  = (-1!= request.getHeader("USER-AGENT").indexOf("MSIE"))?new String(request.getParameter("columCns").getBytes("ISO-8859-1"),"gbk"):new String(request.getParameter("columCns").getBytes("ISO-8859-1"),"UTF-8");
%>

<!-- params -->
<input type="hidden" id="_isCheckBox"  value="<c:out value="${param.isCheckBox}"/>"/>
<input type="hidden" id="_columCns"  value="<c:out value="<%=temps %>"/>"/>
<input type="hidden" id="_domain"  value="<c:out value="${param.domain}"/>"/>
<input type="hidden" id="_colums"  value="<c:out value="${param.colums}"/>"/>
<input type="hidden" id="_returnColums"  value="<c:out value="${param.returnColums}"/>"/>
<input type="hidden" id="_DialogId"  value="<c:out value="${param.DialogId}"/>"/>
<input type="hidden" id="_defineRetuColums"  value="<c:out value="${param.defineRetuColums}"/>"/>
<input type="hidden" id="_queryParams"  value="<c:out value="${param.queryParams}"/>"/>



<!-- button -->
<div class="functionBtnDiv right">
    <button type="button" id="_clear"><span>清空</span></button>
	<button type="button" class="hidden" id="_OK"><span>确 定</span></button>
</div>

<!-- table -->
<table class="frontTableList" id="objectList">
  <thead>
  	<tr>
  	</tr>
  </thead>
  <tbody>
  </tbody>
</table>

