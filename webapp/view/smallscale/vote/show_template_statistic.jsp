<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout form2Pa">
	<h4>主题信息：</h4>
   	<ul>
    	<li>
    		<label>评选主题：</label>
			${voteTemplate.templateName }
			<input type="hidden" name="voteTemplate.objId" id="voteTemplateId" value="${voteTemplate.objId }"/>
  	    </li>
    	<li>
    		<label>主题编号：</label>
			${voteTemplate.templateCode }
  	    </li>
    	<li>
    		<label>活动开始时间：</label>
			${voteTemplate.startTime }
  	    </li>
    	<li>
    		<label>活动结束时间：</label>
			${voteTemplate.endTime }
  	    </li>
  	    <li>
    		<label>评价专员：</label>
			${voteTemplate.appraiseCommissioner }
  	    </li>
  	    <li>
    		<label>评选类型：</label>
    		<c:if test="${voteTemplate.isSingleVote == true}">单一投票</c:if><c:if test="${voteTemplate.isSingleVote == false}">指标评选</c:if>			
  	    </li>
	</ul>
	
	<div class="formLayout">
		<h4>参选对象投票信息统计：</h4>
			<table class="frontTableList">
				<thead>
					<tr>
						<th class="left ">参选对象</th>
						<th class="left ">获得票数</th>
						<c:if test="${voteTemplate.isSingleVote == false}"><th class="left ">分&nbsp;&nbsp;值</th></c:if>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="templateStatistic" items="${templateStatisticList}">
						<tr>
							<td align="center">${templateStatistic[0]}</td>
							<td align="center">
								<c:if test="${null != templateStatistic[1]}">${templateStatistic[1]}</c:if>
								<c:if test="${null == templateStatistic[1]}">0</c:if>
							</td>
							<c:if test="${voteTemplate.isSingleVote == false}"><td align="center"><fmt:formatNumber value="${templateStatistic[3]}" pattern="##0.0#"/>&nbsp;分</td></c:if>
						</tr>
					</c:forEach>										
				</tbody>
			</table>
		</div>
</div>
<div class="conOperation">
	<button type="button" id="templateStatisticReturn" tabindex="19"><span><spring:message code="globe.return"/></span></button>
</div>

<script>
	$('#templateStatisticReturn').click(function(){
		$('.epsDialogClose').trigger('click');
	});
</script>
