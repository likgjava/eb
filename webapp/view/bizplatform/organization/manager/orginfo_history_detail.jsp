<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="OrgHistoryDetailForm" method="post" modelAttribute="orgHistory">
		<input type="hidden" id="objId_history" name="objId" value="${orgHistory.objId}"/>
		<input type="hidden" id="supplierId_history" name="supplierId" value="${orgHistory.supplierId}"/>
		<input type="hidden" id="buyerId_history" name="buyerId" value="${orgHistory.buyerId}"/>
		<input type="hidden" id="agencyId_history" name="agencyId" value="${orgHistory.agencyId}"/>
	
	<div class="formLayout form2Pa">
		<h4><span>机构信息</span></h4>
		<ul>
			<li class="fullLine"><label>企业名称：</label> <span>${orgInfo.orgName}</span> </li>
			<li><label>机构代码：</label> <span>${orgInfo.orgCode}</span></li>
			<li><label>企业法定代表人：</label> <span>${orgInfo.company.croporate}</span> </li>
			<li><label>联系手机：</label> <span>${orgInfo.company.mobilePhone}</span></li>
			<li><label>办公电话：</label> <span>${orgInfo.company.tel}</span> </li>
			<li><label>办公传真：</label> <span>${orgInfo.company.fax}</span> </li>
			<li><label>企业email：</label> <span>${orgInfo.company.email}</span> </li>
			<li><label>行政区域：</label> <span>${orgInfo.distinctName}</span></li>
			<li><label>邮编：</label> <span>${orgInfo.company.postCode}</span> </li>
			<li class="fullLine"><label>详细通信地址：</label> <span>${orgInfo.company.address}</span></li>
		</ul>
		
		<ul>
          <li>
          	<label for="belongIndustry">所属行业：</label>
          	<span>${orgInfo.belongIndustry.name}</span>
    	  </li>
    	  <li>
             <label for="purSbjct">企业类型：</label>
             <span>${orgInfo.entPrptCN}</span>
          </li>
          <li>
             <label for="purSbjct">企业规模：</label>
             <span>${orgInfo.unitScapeCN}</span>
          </li>
          <li>
             <label for="purSbjct">开业日期：</label>
             <span>${orgInfo.begainDate}</span>
          </li>
          <li class="fullLine">
             <label for="purSbjct">企业网址：</label>
             <span>${orgInfo.webUrl}</span>
          </li>
          <li class="fullLine">
             <label for="purSbjct">经营范围：</label>
             <span id="bidForRangeSpan">${orgInfo.bidForRange}</span>
          </li>
          <li class="fullLine">
             <label for="purSbjct">主营产品：</label>
             <span>${orgInfo.mainProducts}</span>
          </li>
          <li class="fullLine">
             <label for="purSbjct">企业产能：</label>
             <span>${orgInfo.entCapacity}</span>
          </li>
    	  <li class="fullLine">
             <label for="input02">企业简介：</label>
             <span>${orgInfo.descCn}</span>
		  </li>
    	</ul>
	</div>
	
	<!-- 扩展信息部分开始 -->
	<div id="infoDiv">
		<ul id="infoUl">
			<c:if test="${orgInfo.qualitys != null && fn:length(orgInfo.qualitys) > 0}">
				<li id="qualityLi"><a href="#qualityInfo" id="loadAgencyBtn">资质信息</a></li>
			</c:if>
			<c:if test="${orgInfo.successCases != null && fn:length(orgInfo.successCases) > 0}">
				<li id="successcaseLi"><a href="#successcaseInfo" id="loadAgencyBtn">成功案例信息</a></li>
			</c:if>
		</ul>
		<div id="qualityInfo">
			<c:if test="${orgInfo.qualitys != null && fn:length(orgInfo.qualitys) > 0}">
				<table class="frontTableList" id="orgQualitysCaseList">
				<c:choose>
				<c:when test="${orgInfo.qualitys != null && fn:length(orgInfo.qualitys) > 0}">
					<c:forEach var="orgQual" items="${orgInfo.qualitys}">
						<tr>
							<td>${orgQual.qualificationClass.qualityName}</td>
							<td>${orgQual.qualificationDefine.qualityName}</td>
							<td align="center"><fmt:formatDate value="${orgQual.createTime}" pattern="yyyy年MM月dd日"/></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
						<tr><td align="center" colspan="4">该机构暂无资质信息</td></tr>
				</c:otherwise>
				</c:choose>
				</table>
			</c:if>
		</div>
		<div id="successcaseInfo">
			<c:if test="${orgInfo.successCases != null && fn:length(orgInfo.successCases) > 0}">
				<table class="frontTableList" id="orgSuccessCaseList">
				<c:choose>
				<c:when test="${orgInfo.successCases != null && fn:length(orgInfo.successCases) > 0}">
					<c:forEach var="orgSucc" items="${orgInfo.successCases}">
						<tr>
							<td>${orgSucc.projectName}</td>
							<td align="center"><fmt:formatDate value="${orgSucc.startTime}" pattern="yyyy年MM月dd日"/></td>
							<td align="center"><fmt:formatDate value="${orgSucc.endTime}" pattern="yyyy年MM月dd日"/></td>
							<td>${orgSucc.categoryNames}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
						<tr><td align="center" colspan="4">该机构暂无成功案例信息</td></tr>
				</c:otherwise>
				</c:choose>
				</table>
			</c:if>
		</div>
	</div>
</form:form>

<script>
var ExBaseHistoryOrgInfoDetailForm={};
$(document).ready(function(){
  	//加载扩展信息
  	var $tabs=$("#infoDiv").tabs();
});
</script>