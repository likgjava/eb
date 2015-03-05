<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script src="<%=request.getContextPath()%>/view/srplatform/backfill/object_select_grid.js"></script>
<input type="hidden" id="_grid"  value="<c:out value="${param.grid}"/>"/>
<table id="objectSelectGridTable"></table>