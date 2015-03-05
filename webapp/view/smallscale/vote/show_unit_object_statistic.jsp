<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div id="conBody" class="conBody hxConBody" >
	<div class="formLayout form2Pa">
		<ul>
			<li class="fullLine"><label>单位名称：</label>${param.voteAssessedThingName}</li>
			<li><label>投票共计：</label>${statisticVoteCount}&nbsp;人</li>
			<li><label>最终成绩：</label><fmt:formatNumber value="${assessedFinalGrade}" pattern="##0.00#" />&nbsp;分</li>
		</ul>
	</div>
	<div>
		<table cellspacing="0"  cellpadding="0" width="100%;" border="1" class="parentTable">
			<tr>
				<th colspan="2" rowspan="2">评价项目</th>
				<th colspan="1" class="t1"><h1>数据统计展示</h1></th>
			</tr>
			<tr class="th2">
				<th>投票平均成绩（分）</th>
			</tr>
			<c:set var="pointerValidCount" value="0"></c:set>
			<c:set var="count" value="1"></c:set>
			<c:set var="prePointer" value="prePointer"></c:set>
			<c:forEach var="votePointer" items="${votePointerList}" varStatus="status">
				<c:set var="pointerValidCount" value="${pointerValidCount + 1}"></c:set>
				<tr>
					<c:if test="${votePointer.pointerFactor == 0 }">
						<th id="CODE_${votePointer.pointerCode}" rowspan="">${votePointer.pointerName }
							<c:if test="${count != 1 && prePointer != ''}">
								<input type="hidden" id="HIDDEN_${prePointer}" value="${count}"/>
								<script>$('#CODE_${prePointer}').attr("rowspan",${count});</script>
							</c:if>
						</th><th></th>
						
						<c:set var="count" value="1"></c:set>
						<c:set var="prePointer" value="${votePointer.pointerCode}"></c:set>
					</c:if>
					<c:if test="${votePointer.pointerFactor != 0 }">
						<c:if test="${fn:startsWith(votePointer.pointerCode, prePointer)}">
							<c:set var="count" value="${count + 1}"></c:set>
						</c:if>
						<th <c:if test="${count == 1}">colspan="2"</c:if>>${votePointer.pointerName }</th>
						<td>
							<c:choose>
								<c:when test="${fn:length(assessedAvgGradeList) > 0}">
									<c:forEach var="assessedAvgGrad" items="${assessedAvgGradeList}" varStatus="status">
										<c:if test="${assessedAvgGrad[0]==votePointer.pointerCode}"><fmt:formatNumber value="${assessedAvgGrad[3]}" pattern="#,##0.00#" />&nbsp;分</c:if>
									</c:forEach>
								</c:when>
								<c:otherwise>0&nbsp;分</c:otherwise>
							</c:choose>
						</td>
					</c:if>										
				</tr>
			</c:forEach>
		</table>
		<input type="hidden" id="HIDDEN_${prePointer}" value="${count}"/>
		<script>$('#CODE_${prePointer}').attr("rowspan",${count});</script>
		<input type="hidden" id="pointerValidCount" value="${pointerValidCount}"/>
          </div>
	<div class="conOperation">
		<button type="button" id="voteStatisticReturn" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	</div>
</div>

<script>
	$('#voteStatisticReturn').click(function(){
		$('.epsDialogClose').trigger('click');
	});
</script>
