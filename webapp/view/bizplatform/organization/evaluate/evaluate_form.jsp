<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/base/css/supplierAgreement.css" />
<script type="text/javascript" src='<c:url value="/view/bizplatform/organization/evaluate/evaluate_form.js"/>'></script>
<form:form id="EvaluateForm" method="post" modelAttribute="evaluate">
	<form:hidden path="objId"></form:hidden>
	<form:hidden path="project"></form:hidden>
	<form:hidden path="projectName"></form:hidden>
	<input id="orgId" type= "hidden" value="${evaluate.org.objId}"/>
	
	<div id = "addEvaluate" class="formLayout form2Pa">
	    <h4><span>评价信息</span></h4>
		<ul>
			<li>
				<label>被评价机构名：</label>
				<form:hidden path="org.objId"/>
				<span>${evaluate.org.orgName}</span>
			</li>	
			<li>
				<label>项目：</label>
				<span>${evaluate.projectName }</span>
			</li>
			<li>
				<label>评价人：</label>
				<form:hidden path="rater.objId"/>
				<span>${evaluate.rater.username }</span>
			</li>
			<li>
				<label>评价级别：</label>
				<input name="leval" type="radio" value="0" checked="checked">好
				<input name="leval" type="radio" value="1">中			
				<input name="leval" type="radio" value="2">差				
			</li>
			<li>
				<label>总分：</label>
				<span id="summaryScore">0.00</span>
			</li>
			<li>
				<label>是否匿名：</label>
				<input name="isAonymous" type="radio" value="0" checked="checked">否				
				<input name="isAonymous" type="radio" value="1">是		
			</li>
		</ul>
	</div>
	<div class="formLayout form1Pa" id="scoreForm">	
		<ul>
		    <h4><span>指标得分</span></h4>
		</ul>
	</div>
	<div id="starUL">
	<c:forEach var="quota" items="${quotaList}" varStatus="status">
			<div class="shop-rating" id="starsDiv">
				<span class="title">${quota.name }：</span>
				<ul class="rating-level" id="stars${status.index }">
					<li><a class="aa1-star" star:value="1" href="#">1</a></li>
					<li><a class="aa2-stars" star:value="2" href="#">2</a></li>
					<li><a class="aa3-stars" star:value="3" href="#">3</a></li>
					<li><a class="aa4-stars" star:value="4" href="#">4</a></li>
					<li><a class="aa5-stars" star:value="5" href="#">5</a></li>
					<li><a class="aa6-stars" star:value="6" href="#">6</a></li>
					<li><a class="aa7-stars" star:value="7" href="#">7</a></li>
					<li><a class="aa8-stars" star:value="8" href="#">8</a></li>
					<li><a class="aa9-stars" star:value="9" href="#">9</a></li>
					<li><a class="aa10-stars" star:value="10" href="#">10</a></li>
				</ul>
				<span class="result" id="stars${status.index}-tips"></span>
				<input type="hidden" id="stars${status.index}-input" name="scores[${status.index}&com.gpcsoft.bizplatform.organization.domain.Score].score" value=""/>
				<input type="hidden" name="scores[${status.index}&com.gpcsoft.bizplatform.organization.domain.Score].quotaName" value="${quota.name }"/>
				<input type="hidden" name="scores[${status.index}&com.gpcsoft.bizplatform.organization.domain.Score].quotaId" value="${quota.objId }"/>
				<input type="hidden" id="proportion" name="scores[${status.index}&com.gpcsoft.bizplatform.organization.domain.Score].proportion" value="${quota.proportion }"/>
			</div>
	</c:forEach>
	</div>
	<div class="formLayout form1Pa" id="scoreForm">	
		<ul>
		    <li class="formTextarea">
					<label>评价描述：</label>
					<form:textarea path="remark"/>
			</li>
		</ul>
	</div>	
</form:form>

<div class="conOperation center">
	<button  id="evaluateSave" type="button" ><span><spring:message code="globe.save"/></span></button>
	<button  id="evaluateClose" type="button" ><span><spring:message code="globe.close"/></span></button>
</div>