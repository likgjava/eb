<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="categoriesB">
	<h2>品牌下所有分类</h2>
	<ul>
		<c:forEach var="goodsClass" items="${goodsClassList}">
			<li>
			<h3>
				<a href="javascript:show_list.makeSearchData('${goodsClass.objId}');" title="${goodsClass.goodsClassName}"><span>
				<c:choose>
  					<c:when test="${fn:length(goodsClass.goodsClassName) > 10}">${fn:substring(goodsClass.goodsClassName,0,9)}…</c:when>
  					<c:otherwise>${goodsClass.goodsClassName}</c:otherwise>
  				</c:choose>
				</span></a>
			</h3>
				<ul>
				<c:forEach var="goodsClassSec" items="${goodsClass.children}">
					<li ><a href="javascript:show_list.makeSearchData('${goodsClassSec.objId}');"><span>${goodsClassSec.goodsClassName}</span></a>
					<ul>
						<c:forEach var="goodsClassThr" items="${goodsClassSec.children}">
							<li class="selected"><a href="javascript:show_list.makeSearchData('${goodsClassThr.objId}');"><span>${goodsClassThr.goodsClassName}</span></a></li>
						</c:forEach>
					</ul>
					</li>
				</c:forEach>
				</ul>
			</li>
		</c:forEach>
	</ul>
</div>
