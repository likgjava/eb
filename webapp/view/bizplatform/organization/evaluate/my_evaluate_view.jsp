<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/base/css/supplierAgreement.css"/>
<script type="text/javascript" src='<c:url value="/view/bizplatform/organization/evaluate/my_evaluate_view.js"/>'></script>
<div class="formLayout">         
<input type="hidden" id="currentOrgId" name="currentOrgId" value="${currentOrgId }">
<h4><span>我的评价信息</span></h4>
<!-- 统计的表 -->
<div class="partContainers" id="totalTable">
	<table class="tableList">
		<tr>
			<th></th>
			<th>最近一周</th>
			<th>最近一个月</th>
			<th>最近6个月</th>
			<th>6个月前</th>
			<th>总计</th>
		</tr>
		<tr>
			<td align="center"><span class="praise">好评</span></td>
			<td align="center"><c:out value="${row1.lastWeek}"/></td>
			<td align="center"><c:out value="${row1.lastMonth}"/></td>
			<td align="center"><c:out value="${row1.lastSixMonth}"/></td>
			<td align="center"><c:out value="${row1.beforSixMonth}"/></td>
			<td align="center"><c:out value="${row1.total}"/></td>
		</tr>
		<tr>
			<td align="center"><span class="mediumReview">中评</span></td>
			<td align="center"><c:out value="${row2.lastWeek}"/></td>
			<td align="center"><c:out value="${row2.lastMonth}"/></td>
			<td align="center"><c:out value="${row2.lastSixMonth}"/></td>
			<td align="center"><c:out value="${row2.beforSixMonth}"/></td>
			<td align="center"><c:out value="${row2.total}"/></td>
		</tr>
		<tr>
			<td align="center"><span class="badReview">差评</span></td>
			<td align="center"><c:out value="${row3.lastWeek}"/></td>
			<td align="center"><c:out value="${row3.lastMonth}"/></td>
			<td align="center"><c:out value="${row3.lastSixMonth}"/></td>
			<td align="center"><c:out value="${row3.beforSixMonth}"/></td>
			<td align="center"><c:out value="${row3.total}"/></td>
		</tr>
		<tr>
			<td align="center">总计</td>
			<td align="center"><c:out value="${row4.lastWeek}"/></td>
			<td align="center"><c:out value="${row4.lastMonth}"/></td>
			<td align="center"><c:out value="${row4.lastSixMonth}"/></td>
			<td align="center"><c:out value="${row4.beforSixMonth}"/></td>
			<td align="center"><c:out value="${row4.total}"/></td>
		</tr>
	</table>
</div>

<h4><span>指标评分情况</span></h4>
<div class="evaluationOfManagement">
<c:forEach var="quotaDetai" items="${quotaDetailList}">
	<div class="evaluate">
		<span class="valueDic">${quotaDetai[0] }：</span> 
		<span class="valuePic">
			<div class="score">
				<span style="width:${(quotaDetai[2]+0.001)*10}%"></span>
			</div>
		</span>
		<span class="value">
			<c:choose>
				<c:when test="${quotaDetai[2]==null}"> &nbsp; 0分</c:when>
				<c:otherwise> &nbsp; <fmt:formatNumber type="number" value="${quotaDetai[2]}" maxFractionDigits="2"/>分</c:otherwise>
			</c:choose>
		</span>
	</div>
</c:forEach>
	<div class="evaluate">
		<span class="valueDic">&nbsp;</span> 
		<span class="valuePic">
			<ul>
			  <li><span>2分</span><span></span></li>
			  <li><span>4分</span><span></span></li>
			  <li><span>6分</span><span></span></li>
			  <li><span>8分</span><span></span></li>
			  <li><span>10分</span><span></span></li>
			</ul>
		</span>
		<span class="value">&nbsp;</span>
	</div>
</div>
</div>

<br>
<!-- 来自各机构的评价Tabs -->
<div id="epsTabs">
	<ul>
		<c:if test="${Supplier }">
			<li><a href="#newEvaluate" id="supplier" ><span>来自供应商的评价</span></a></li>
		</c:if>
		<c:if test="${Buyer }">
			<li><a href="#newEvaluate" id="buyer" ><span>来自采购人的评价</span></a></li>
		</c:if>
		<c:if test="${Agent }">
			<li><a href="#newEvaluate" id="agent" ><span>来自代理机构的评价</span></a></li>
		</c:if>
	</ul>
	<div id="newEvaluate" class="">
		<table class="frontTableList" id="evaluateList">
		      <thead>
		        <tr>
		          <th class="center operation">评论级别</th>
		          <th class="omission" omiLength="25">评论</th>
		          <th class="omission" omiLength="10">评价人</th>
		          <th class="omission" omiLength="25">项目名称</th>
		          <th class="center">评价时间</th>
		        </tr>
		      </thead>
	     	  <tbody></tbody>
		</table>
	</div>
</div>