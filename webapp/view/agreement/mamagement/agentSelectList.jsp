<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%> 
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/mamagement/agentSelectList.js"></script>

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

<!-- 查询条件 -->
<div class="conSearch">
	<form id="ObjectSearchForm">
    <h4><span>搜索</span></h4>
    <ul>
      <li>
        <label  for="input01">机构名称：</label>
        <input type="text" id="orgName" name="orgName" value=""/>
        <input type="hidden" id="orgName_op" name="orgName_op" value="like"/>
      </li>
      
      <li>
        <label  for="input01">机构代码：</label>
        <input type="text" id="orgCode" name="orgCode" value=""/>
        <input type="hidden" id="orgCode_op" name="orgCode_op" value="like"/>
      </li>
      
      <li class="operationBtnDiv">
        	<button id="objectSearch" type="button"><span>查询</span></button>
      </li>
    </ul>
    </form>
</div>

<!-- button -->
<div class="functionBtnDiv right">
    <button type="button" id="_clear"><span>清空</span></button>
	<button type="button" class="hidden" id="_OK"><span>确 定</span></button>
</div>

<!-- table -->
<table cellpadding="0" cellspacing="0" border="0" class="frontTableList" id="objectList">
  <thead>
  	<tr>
  	</tr>
  </thead>
  <tbody>
  </tbody>
</table>

