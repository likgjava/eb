<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%
	pageContext.setAttribute("gpc", request.getContextPath());
	String content = request.getSession().getAttribute("content")+"";
%>
<script type="text/javascript" src='${gpc }/view/resource/plug-in/jquery/jquery.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>//view/es/common/RequestContentPrint.js"></script>
<style type="text/css" media="print">
.noprint{display : none }
</style>
	<div class="noprint" style="text-align: left;">
		<button type="button" id="print_info" ><span><span id="submitSpan">打印</span></span></button>
		<button type="button" id="print_view_info" ><span><span>预览</span></span></button>
		<button type="button" id="exp_info" ><span><span>导出Word</span></span></button>
	</div>
<div id="checkRequestInfo" class="frameReport">
<input type="hidden"  id="projectId" value="${param.projectId}">
   	<div class="reportTitle">
     		<h2 align="center">${project.projName }</h2>
    		<span class="projectNumber">招标编号：${project.projCode }</span>
    		<span class="publishTime">立项时间：<fmt:formatDate value="${project.createTime }" pattern="yyyy-MM-dd HH:mm"/></span>
    	</div>
	<div class="abstract">
		<p><span>报名时间：<fmt:formatDate value="${projectRule.signUpSTime }" pattern="yyyy-MM-dd HH:mm"/> 至 <fmt:formatDate value="${projectRule.signUpETime }" pattern="yyyy-MM-dd HH:mm"/></span>
			<c:set var="isContains" value="0"></c:set>
			<authz:authorize ifAnyGranted="RULE_A">
				<c:set var="isContains" value="1"></c:set>
			</authz:authorize>
			<span><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>时间：<fmt:formatDate value="${projectRule.submitStTime }" pattern="yyyy-MM-dd HH:mm"/> 至 <fmt:formatDate value="${projectRule.submitETime }" pattern="yyyy-MM-dd HH:mm"/></span>
			<c:set var="isContains" value="0"></c:set>
			<authz:authorize ifAnyGranted="RULE_A">
				<c:set var="isContains" value="1"></c:set>
			</authz:authorize>
			<c:if test="${isContains == 0}">
				<authz:authorize ifAnyGranted="RULE_B">
					<c:if test="${projectRule.submitStTime<thisTime && thisTime<projectRule.submitETime || '' == project.submitETime}">
						<button class="sysicon siModify" id="modify_supplier_bid_time" title="修改投标单位投标时间" projectId="${project.objId}"><span></span></button>
					</c:if>
				</authz:authorize>
			</c:if>
		</p>
       	<p></p>
   	</div>
	<p >本项目采购(采取<span class="em highlight">${project.ebuyMethodCN}</span>采购方式)，项目采购公告刊登门户网上，截止至<span class="em"><fmt:formatDate value="${projectRule.signUpETime }" pattern="yyyy-MM-dd HH:mm"/></span> ，共有<span class="em">${project.projectCountView.signUpC }</span>家公司购买了<dm:out value="local__OpenTendering__purchasedoc_zh">招标文件</dm:out>。截至<span class="em"><fmt:formatDate value="${projectRule.submitETime }" pattern="yyyy-MM-dd HH:mm"/></span>，共有来自省内外的<span class="em">${project.projectCountView.bidC }</span>家公司参加本项目的投标。评审程序是严格按照政府采购相关法律法规来进行的，评审办法及评分细则是依照该项目<dm:out value="local__OpenTendering__purchasedoc_zh">招标文件</dm:out>中相关规定制定的。</p>
	<authz:authorize ifAnyGranted="GK_PROJECT_SIGNUPLIST">
	<p>申报书信息：</p>
	<table style="width: 60%;"  >
    	<tr>
	        <th align="center">序号</th>
	        <th align="center">申报书名称</th>
	        <th align="center">申报书明细品目</th>
	        <th align="center">招标单位名称</th>
      	</tr>
      	<c:set var="count" value="0"></c:set>
      	<c:forEach items="${TaskPlanMSubList}" var="TaskPlanMSub">      
      	<tr>
	        <td   align="center" width="2%"><c:set var="count" value="${count+1}"></c:set>${count}</td>
	        <td align="center" width="10%">${TaskPlanMSub.taskPlan.taskName}</td>
	        <td align="center" width="10%">${TaskPlanMSub.taskPlanSub.purchaseName}</td>
	        <td align="center" width="10%">${TaskPlanMSub.taskPlanSub.budgetName}</td>
      	</tr>
      	</c:forEach>
	</table>
	</authz:authorize>
	
	
	<authz:authorize ifAnyGranted="GK_PROJECT_SIGNUPLIST">
	<p >参与报名的投标单位：</p>
	<table style="width: 60%"  >
    	<tr >
	        <th align="center">序号</th>
	        <th align="center">公司名称</th>
	        <th align="center">报名审核情况</th>
      	</tr>
      	<c:set value="0" var="signupListCount" />
      	<c:forEach items="${signupList}" var="signUprecord">      
      	<tr >
	        <td   align="center" width="5%"><c:set value="${signupListCount+1 }" var="signupListCount" />${signupListCount }</td>
	        <td align="center" width="50%">${signUprecord.supplier.orgName}</td>
	        <td align="center" >${signUprecord.auditStatusCN}</td>
      	</tr>
      	</c:forEach>
	</table>
	</authz:authorize>
	
	
	<authz:authorize ifAnyGranted="GK_PROJECT_BIDLIST">
	<c:forEach items="${bidmodel}" var="bidList">
	<p>参与${bidList.key }<dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>的投标单位：</p>
	<table style="width: 60%;table-layout: auto" >
 		<tr>
	        <th align="center">序号</th>
	        <th align="center">公司名称</th>
	        <th align="center"><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>情况（元）</th>
      	</tr>
      	<c:set value="0" var="bidListCount" />
      	<c:forEach items="${bidList.value}" var="bidPackage">
      	<tr>
	        <td align="center" width="5%"><c:set value="${bidListCount+1 }" var="bidListCount" />${bidListCount }</td>
	        <td align="center" width="50%"><c:if test="${pac =='NO'}"> ${bidPackage.supplierName}</c:if>
	       					  <c:if test="${pac =='YES'}"> ${bidPackage.bid.supplierName}</c:if>
	        </td>
	        <td align="center" ><c:if test="${bidPackage.isOpenBid =='YES'}"><fmt:formatNumber pattern="￥#,#00.0#">
	        				  <c:if test="${pac =='NO'}"> ${bidPackage.bidQuoteSum}</c:if>
	        				  <c:if test="${pac =='YES'}"> ${bidPackage.quotesum}</c:if>
	        				  </fmt:formatNumber></c:if>
	        				  <c:if test="${bidPackage.isOpenBid =='NO'}">还未<dm:out value="local__OpenTendering__openBid_zh">开标</dm:out></c:if>
	        </td>
      	</tr>
		</c:forEach></table>
	</c:forEach>
    </authz:authorize>
</div>
<OBJECT id='wb' name='wb' classid='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2' height='0' width='0' ></OBJECT>
