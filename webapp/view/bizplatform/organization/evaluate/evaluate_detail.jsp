<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<style type="text/css">.starWrapper{border:0px solid #FFCC00;padding:5px;width:250px;}.starWrapper img{cursor:pointer;}</style>
	
<script type="text/javascript">
var EvaluateDetail={};

$(document).ready(function(){
	
	//返回
	$("#evaluateReturn").click(function(){		
		alert("返回暂无!");
	})
	
});
</script>	
	
	<!-- 评价主信息 -->
	<div class="formLayout form2Pa">
	    <h4><span>评价信息</span></h4>
		<ul>
			<li>
				<label>被评价机构名：</label>
				<span>${evaluate.org.orgName}</span>
			</li>	
			<li>
				<label>项目：</label>
				<span>${evaluate.project }</span>
			</li>
			<li>
				<label>评价人：</label>
				<span>${evaluate.rater.username }</span>
			</li>
			<li>
				<label>评价级别：</label>
				<span>${evaluate.leval }</span>
			</li>
			<li>
				<label>总分：</label>
				<span >${evaluate.summaryScore }</span>
			</li>
			<li>
				<label>是否匿名：</label>
				<span>${evaluate.isAonymous }</span>
			</li>
		</ul>
	</div>
	
	<!-- 指标得分信息 -->
	<div class="formLayout form1Pa" id="scoreForm">
		<ul>
		    <h4><span>指标得分</span></h4>
				<c:forEach var="score" items="${evaluate.scores}" varStatus="status">
					<li>
						<label>${score.quotaName }：</label>
						<span class="starWrapper" >
							<c:if test="${score.score != 1}"><img src="<%=request.getContextPath()%>/view/resource/images/evaluate_1.gif"/></c:if>
							<c:if test="${score.score == 1}"><img src="<%=request.getContextPath()%>/view/resource/images/evaluate_2.gif"/></c:if>
							<c:if test="${score.score != 2}"><img src="<%=request.getContextPath()%>/view/resource/images/evaluate_1.gif"/></c:if>
							<c:if test="${score.score == 2}"><img src="<%=request.getContextPath()%>/view/resource/images/evaluate_2.gif"/></c:if>
							<c:if test="${score.score != 3}"><img src="<%=request.getContextPath()%>/view/resource/images/evaluate_1.gif"/></c:if>
							<c:if test="${score.score == 3}"><img src="<%=request.getContextPath()%>/view/resource/images/evaluate_2.gif"/></c:if>
							<c:if test="${score.score != 4}"><img src="<%=request.getContextPath()%>/view/resource/images/evaluate_1.gif"/></c:if>
							<c:if test="${score.score == 4}"><img src="<%=request.getContextPath()%>/view/resource/images/evaluate_2.gif"/></c:if>
							<c:if test="${score.score != 5}"><img src="<%=request.getContextPath()%>/view/resource/images/evaluate_1.gif"/></c:if>
							<c:if test="${score.score == 5}"><img src="<%=request.getContextPath()%>/view/resource/images/evaluate_2.gif"/></c:if>
							<c:if test="${score.score != 6}"><img src="<%=request.getContextPath()%>/view/resource/images/evaluate_1.gif"/></c:if>
							<c:if test="${score.score == 6}"><img src="<%=request.getContextPath()%>/view/resource/images/evaluate_2.gif"/></c:if>
							<c:if test="${score.score != 7}"><img src="<%=request.getContextPath()%>/view/resource/images/evaluate_1.gif"/></c:if>
							<c:if test="${score.score == 7}"><img src="<%=request.getContextPath()%>/view/resource/images/evaluate_2.gif"/></c:if>
							<c:if test="${score.score != 8}"><img src="<%=request.getContextPath()%>/view/resource/images/evaluate_1.gif"/></c:if>
							<c:if test="${score.score == 8}"><img src="<%=request.getContextPath()%>/view/resource/images/evaluate_2.gif"/></c:if>
							<c:if test="${score.score != 9}"><img src="<%=request.getContextPath()%>/view/resource/images/evaluate_1.gif"/></c:if>
							<c:if test="${score.score == 9}"><img src="<%=request.getContextPath()%>/view/resource/images/evaluate_2.gif"/></c:if>
							<c:if test="${score.score != 10}"><img src="<%=request.getContextPath()%>/view/resource/images/evaluate_1.gif"/></c:if>
							<c:if test="${score.score == 10}"><img src="<%=request.getContextPath()%>/view/resource/images/evaluate_2.gif"/></c:if>
						</span>
						<font color="red"><span>${score.score }分</span></font>
					</li>
				</c:forEach>
				
				<!-- 评价描述 -->
				<li class="fullLine">
					<label>评价描述：</label>
					<span>${evaluate.remark }</span>
				</li>
		</ul>
	</div>
	
   	<div class="conOperation center">
	  	<button  id="evaluateReturn" type="button" ><span><spring:message code="globe.return"/></span></button>
	</div>
