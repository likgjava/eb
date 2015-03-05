<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
	var showVoteUnitComment = {};
	var show_list = {};
	//分页跳转
	show_list.makeSearchData = function(){
		showVoteUnitComment.showVoteAssessedComment();
	}
	
	//评选详情列表
	showVoteUnitComment.showVoteAssessedComment = function(){
		showVoteUnitComment.search = "";
		//分页信息
		if($('#rp') && $('#rp').val()==null){
			showVoteUnitComment.search += "&rp=25";
			showVoteUnitComment.search += "&page=1";
		}else{
			showVoteUnitComment.search += "&rp="+$('#rp').val();
			showVoteUnitComment.search += "&page="+$('#page').val();
		}

		//参选对象记录Id
		var voteAssessedThingId = $('#voteAssessedThingId').val();
		
		$("#conBody").loadPage($('#initPath').val()+'/VoteTemplateShowController.do?method=showVoteUnitComment&voteAssessedThingId='+voteAssessedThingId+showVoteUnitComment.search);
	}
</script>
<input type="hidden" id="voteAssessedThingId" value="${voteAssessedThing.objId}"/>
<div class="hdjs_main">
	<div class="hdjs_main_left">
		<img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/hdjs_bg01.jpg" />
		<div id="showVoteResultTopNum">
			<%@ include file="/view/smallscale/vote/show_vote_result_topNum.jsp" %>
		</div>
	</div>
	<div class="hdjs_main_right">
		<div style="margin-bottom: 5px;">
			<span style="float:left;margin-left: 15px;">
				<img src="AttachmentController.do?method=showImg&objId=${voteAssessedThing.picture }" height="146" width="187" /><br />
			</span>
			<span style="float:left;line-height:200%;font-size:14px;margin-left: 15px;color: #333">
				品牌名称：${voteAssessedThing.attenderName }<br>
				所属评选组：${voteAssessedThing.voteUnitGroup.groupName }<br>
			</span>
			<div style="clear:both;"></div>
		</div>
		<div class="formLayout form2Pa">
			<c:forEach var="voteAssessedComment" items="${PAGERESULT.data}" varStatus="status">
				<ul>
					<li>
						评论人：<c:if test="${voteAssessedComment.isAnonymity == 'fasle' }">${voteAssessedComment.userName }</c:if><c:if test="${voteAssessedComment.isAnonymity != 'fasle' }">匿名者</c:if>
					&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${voteAssessedComment.createTime }" pattern="yyyy-MM-dd"/>
					</li>
					<li>
						${voteAssessedComment.memo }
					</li>
				</ul>
			</c:forEach>
			<div style="color: #333;">
				<%@ include file="/view/pubservice/common/pageDirection.jsp" %>
			</div>
		</div>
		<br>
	</div>
	<div style="clear: both;"></div>
</div>


