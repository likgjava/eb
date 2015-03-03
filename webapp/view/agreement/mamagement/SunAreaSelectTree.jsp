<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script src="<%=request.getContextPath()%>/view/agreement/mamagement/SunAreaSelectTree.js"></script>
<button id="_clear">清空</button>
<input type="hidden" id="_className"  value="<c:out value="${param.className}"/>"/>
<input type="hidden" id="_property"  value="<c:out value="${param.property}"/>"/>
<input type="hidden" id="_isCheckBox"  value="<c:out value="${param.isCheckBox}"/>"/>
<input type="hidden" id="_checkValues"  value="<c:out value="${param.checkValues}"/>"/>
<input type="hidden" id="_isOpen"  value="<c:out value="${param.isOpen}"/>"/>
<input type="hidden" id="_param"  value="<c:out value="${param.params}"/>"/>
<input type="hidden" id="_ID"  value="<c:out value="${param.IDS}"/>"/>
<input type="hidden" id="_NAME"  value="<c:out value="${param.NAMES}"/>"/>
<input type="hidden" id="txtURL"  value="<c:out value="${param.txtURL}"/>"/>
<input type="hidden" id="_DialogId"  value="<c:out value="${param.DialogId}"/>"/>
<input type="hidden" id="_Column"  value="<c:out value="${param.Column}"/>"/>
<input type="hidden" id="_defineRetuColums"  value="<c:out value="${param.defineRetuColums}"/>"/>
<input type="hidden" id="_queryParams"  value="<c:out value="${param.queryParams}"/>"/>
<input type="hidden" id="_nodeId"  value="<c:out value="${param.nodeId}"/>"/>


<div id="dialogTreeBox" style="width:90%;height:400" style="zoom:1;"></div>

