<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@page import="java.util.*,com.gpcsoft.epp.noticemanage.domain.*" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/noticemanage/own_notice_list.js"></script>
<c:choose>
<c:when test="${nodata!=true}">
<div id="superviseDoDiv" class="partContainers">
			<ul id="resultTabUl">
				<c:forEach items="${list}" var="notice" varStatus="i">
				<li id="tab${i.count}" class=""><a href="#" onClick="own_notice_list.receipt('${i.count}','${notice.objId}');"><span>${notice.project.projName}</span></a></li>
				</c:forEach>
			</ul>
	<div id="resultTabDiv" class="tabsContent"></div>
</div>
</c:when>
<c:otherwise>
<h4><span>暂无相关信息!</span></h4>
</c:otherwise>
</c:choose>