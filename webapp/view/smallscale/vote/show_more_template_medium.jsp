<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="hdjs_main">
	<div class="hdjs_main_left">
		<img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/hdjs_bg01.jpg" />
		<div id="showVoteResultTopNum">
			<%@ include file="/view/smallscale/vote/show_vote_result_topNum.jsp" %>
		</div>
	</div>
	<div class="hdjs_main_right">
		<span style="display: block; text-align: center;color: #333;">酒业评选合作媒体名单</span>
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0" class="table">
			<c:set var="startCount" value="1"/>
			<c:forEach begin="1" end="4">
				<c:set var="endCount" value="${startCount+5-1}"/>
				<tr>
					<c:forEach items="${voteTemplateMediumList}" var="voteTemplateMedium" varStatus="status" begin="${startCount-1}" end="${endCount-1}">
						<td>
							<c:if test="${voteTemplateMedium.orgInfo.webUrl != null}">
								<a href="${voteTemplateMedium.orgInfo.webUrl}" target="_blank">${voteTemplateMedium.orgInfo.orgName}</a>
							</c:if>
							<c:if test="${voteTemplateMedium.orgInfo.webUrl == null}">
								${voteTemplateMedium.orgInfo.orgName}
							</c:if>
						</td>
						<c:set var="startCount" value="${startCount+1}"/>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div style="clear: both;"></div>
</div>