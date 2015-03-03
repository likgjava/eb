<%@ page contentType="text/html;charset=UTF-8" %><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="list-content" id="requirementListDetail" >
	<ul class="list-view hlisting sell" >
	<c:forEach var="requirement" items="${PAGERESULT.data}">
	<li class="list-item" style="height: 20px;padding-left: 5px;">
		<a class="EventCanSelect" title="${requirement.title}" style="font-size:14px;" href="<%=request.getContextPath()%>/RequirementInfo/${requirement.objId}" target="_blank">
			<c:choose><c:when test="${fn:length(requirement.title) > 22}">${fn:substring(requirement.title,0,21)}...</c:when><c:otherwise>${requirement.title}</c:otherwise></c:choose>
		</a>
		<ul class="attribute" style="right: 5px;">
			<li class="srvlist"><fmt:formatDate value="${requirement.createTime}" pattern="yyyy-MM-dd"/></li>
			<li class="price"><em> ￥<fmt:formatNumber value="${requirement.purchaseBudget}" pattern="#,##0.00#" /></em></li>
			<li style="width: 170px;" title="${requirement.category.categoryName}">
				<c:choose><c:when test="${fn:length(requirement.category.categoryName) > 10}">${fn:substring(requirement.category.categoryName,0,10)}</c:when><c:otherwise>${requirement.category.categoryName}</c:otherwise></c:choose>
			</li>
		</ul>
	</li>					    
	</c:forEach>
	</ul>
</div>

<div><%@ include file="/view/pubservice/common/pageDirection.jsp" %></div>