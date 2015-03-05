<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="currentViewName" value="orgshopEvaluationView"/>
<!--企业评价-->
<div class="introduction">
	<div class="titleright">
		<h2 class="marginright">企业评价</h2>
	</div>
	<div class="conrig">
		<c:forEach var="evaluate" items="${PAGERESULT.data}">
			<div class="list">
				<ul>
					<li><label>所属项目：</label><span>${evaluate.projectName}</span></li>
					<li>
						<label>评价级别：</label>
						<span>
							<c:set var="evaluateLeval" value="${evaluate.leval}e" />
							<c:choose>
								<c:when test="${evaluateLeval == '0e'}">好评</c:when>
								<c:when test="${evaluateLeval == '1e'}">中评</c:when>
								<c:otherwise>差评</c:otherwise>
							</c:choose>
						</span>
					</li>
					<li><label>评价总分：</label><span>${evaluate.summaryScore }分</span></li>
					<li><label>评价描述：</label><span>${evaluate.remark}</span></li>
					<li style="text-align: right;">
						评价人：
							<c:if var="isAonymous" test="${evaluate.isAonymous == '1'}">匿名</c:if>
							<c:if test="${!isAonymous}">${evaluate.rateOrg.orgName}</c:if>&nbsp;&nbsp;&nbsp;
						评价时间：<fmt:formatDate value="${evaluate.evalTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</li>
				</ul>
			</div>
		</c:forEach>
		<div style="padding-left: 8px;"><%@ include file="/view/pubservice/common/pageDirection.jsp" %></div>
	</div>
</div>

