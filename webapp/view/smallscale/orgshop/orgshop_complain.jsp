<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<input type="hidden" id="complainType" value="${param.complainType}"/>
<input type="hidden" id="currentViewName" value="orgshopComplainView"/>

<!--违法信息-->
<div class="introduction">
	<div class="titleright  martop">
		<h2 class="marginright">违规信息</h2>
	</div>
	<div class="conrig">
		<c:forEach var="illegalRec" items="${illegalRecList}">
			<div class="list">
				<ul>
					<li><label>标题：</label><span>${illegalRec.title}</span></li>
					<li><label>违规内容：</label><span>${illegalRec.reason}</span></li>
					<li style="text-align: right;">
						违规时间：<fmt:formatDate value="${illegalRec.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</li>
					
				</ul>
			</div>
		</c:forEach>
		
		<div style="padding-left: 8px;" class="dataTables_paginate paging_full_numbers" id="QuotaList_paginate">
		<c:if test="${fn:length(illegalRecList)<=0}"><span class="l">没有检索到数据！</span></c:if>
		</div>
	</div>
</div>


<!--投诉举报-->
<div class="introduction">
	<div class="titleright martop">
		<h2 class="marginright">投诉/举报</h2>
	</div>
	<div class="conrig">
		<c:forEach var="complain" items="${PAGERESULT.data}">
			<div class="list">
				<ul>
					<li><label><c:out value="${complain.type=='00'?'投诉':'举报'}"></c:out>标题：</label><span>${complain.title}</span></li>
					<li><label><c:out value="${complain.type=='00'?'投诉':'举报'}"></c:out>内容：</label><span>${complain.content}</span></li>
					<li><label>处理结果：</label><span>${complain.result}</span></li>
					
					<li style="text-align: right;">
						<c:out value="${complain.type=='00'?'投诉':'举报'}"></c:out>时间：<fmt:formatDate value="${complain.complainTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</li>
					
				</ul>
			</div>
		</c:forEach>
		<div style="padding-left: 8px;" class="dataTables_paginate paging_full_numbers" id="QuotaList_paginate">
		<c:if test="${fn:length(PAGERESULT.data)<=0}"><span class="l">没有检索到数据！</span></c:if>
		</div>
	</div>
</div>











