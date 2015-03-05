<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!--成功案例-->
<div class="introduction">
	<div class="titleright">
		<h2 class="marginright">成功案例</h2>
	</div>
	<div class="conrig">
		<c:if test="${fn:length(supplier.orgInfo.successCases) == 0}">		<div style="padding-left: 8px;" class="dataTables_paginate paging_full_numbers" id="QuotaList_paginate">
		<span class="l">没有检索到数据！</span>
		</div></c:if>
		<c:forEach var="scase" items="${supplier.orgInfo.successCases}">
			<div class="list">
				<h2>${scase.projectName}</h2>
				<ul>
					<li><label>开始时间：</label><span><fmt:formatDate value="${scase.startTime}" pattern="yyyy年MM月dd日"/></span></li>
					<li><label>结束时间：</label><span><fmt:formatDate value="${scase.endTime}" pattern="yyyy年MM月dd日"/></span></li>
					<li><label>采购品目：</label><span>${scase.categoryNames}</span></li>
					<li><label>案例描述：</label><span>${scase.description}</span></li>
				</ul>
			</div>
		</c:forEach>
	</div>
</div>