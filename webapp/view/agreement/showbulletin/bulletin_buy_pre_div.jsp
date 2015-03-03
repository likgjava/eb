<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="list-content" id="bulletinListDetail" >
	<ul class="list-view hlisting sell" >
	<c:forEach var="bulletin" items="${PAGERESULT.data}">
	<li class="list-item" style="height: 20px;padding-left: 5px;">
		<a class="EventCanSelect" title="${bulletin.bullTitle}" style="font-size:14px;" href="javascript:void(0);" onclick="show_list.showDetail('${bulletin.objId}');return false;" title="${bulletin.bullTitle}">
			<c:choose>
				<c:when test="${fn:length(bulletin.bullTitle) > 35}">${fn:substring(bulletin.bullTitle,0,35)}... </c:when>
				<c:otherwise>${bulletin.bullTitle}</c:otherwise>
			</c:choose>
		</a>
		<ul class="attribute">
			<li class="srvlist">
				<fmt:formatDate value="${bulletin.createTime}" pattern="yyyy-MM-dd HH:mm"/>
			</li>
		</ul>
	</li>					    
	</c:forEach>
	</ul>
</div>

<div>
	<%@ include file="/view/pubservice/common/pageDirection.jsp" %>
</div>
