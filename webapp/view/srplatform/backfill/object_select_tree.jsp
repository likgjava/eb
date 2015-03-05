<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script src="<%=request.getContextPath()%>/view/srplatform/backfill/object_select_tree.js"></script>
<button id="_clear">清空</button>
<button id="_cancel">取消</button>
<input type="hidden" id="_className"  value="<c:out value="${param.className}"/>"/>
<input type="hidden" id="_property"  value="<c:out value="${param.property}"/>"/>
<input type="hidden" id="_isCheckBox"  value="<c:out value="${param.isCheckBox}"/>"/>
<input type="hidden" id="_childNodeOnly"  value="<c:out value="${param.childNodeOnly}"/>"/>
<input type="hidden" id="_checkValues"  value="<c:out value="${param.checkValues}"/>"/>
<input type="hidden" id="_params"  value="<c:out value="${param.params}"/>"/>
<input type="hidden" id="_hiddenId"  value="<c:out value="${param.hiddenId}"/>"/>
<input type="hidden" id="_displayId"  value="<c:out value="${param.displayId}"/>"/>
<input type="hidden" id="_dialogID"  value="<c:out value="${param.dialogId}"/>"/>
<div id="dialogTreeBox"  style="zoom:1;"></div>