<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
	var showVoteResultList = {};
	var show_list = {};
	//分页跳转
	show_list.makeSearchData = function(){
		var voteUnitGroupId = $('#voteUnitGroupId').val();
		showVoteResultList.showVoteResultLists(voteUnitGroupId);
	}
	
	//参选对象
	showVoteResultList.showVoteResultLists = function(voteUnitGroupId){
		
		showVoteResultList.search = "";
		//分页信息
		if($('#rp') && $('#rp').val()==null){
			showVoteResultList.search += "&rp=20";
			showVoteResultList.search += "&page=1";
		}else{
			showVoteResultList.search += "&rp="+$('#rp').val();
			showVoteResultList.search += "&page="+$('#page').val();
		}

		var voteTemplateId = $('#templateId').val();
		$("#conBody").loadPage($('#initPath').val()+'/VoteTemplateShowController.do?method=showVoteResult&templateId='+voteTemplateId+'&voteUnitGroupId='+voteUnitGroupId+showVoteResultList.search);

}
</script>
<div class="hdjs_main">
	<div class="hdjs_main_left">
		<img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/hdjs_bg01.jpg" />
		<div id="showVoteResultTopNum">
			<%@ include file="/view/smallscale/vote/show_vote_result_topNum.jsp" %>
		</div>
	</div>
	<div class="hdjs_main_right">
		<span style="color: #333;"><center>投票结果</center></span>
		<input type="hidden" id="voteUnitGroupId" value="${voteUnitGroup.objId}"/>
		<div>
			<ul>
				<c:forEach items="${voteUnitGroupList}" var="voteUnitGroup">
					<li style="float: right;padding: 1px 10px;list-style: none;font-size: 25px;">
						<a href="javascript:void(0);" onclick="showVoteResultList.showVoteResultLists('${voteUnitGroup.objId }')">${voteUnitGroup.groupName }</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<br>
		<div>
			<span style="color: #333;">${voteUnitGroup.groupName}投票结果</span>
			<table width="640" border="0" align="center" cellpadding="0" cellspacing="1" class="table" bgcolor="#CCCCCC">
				<tr>
					<td height="30" valign="middle" align="center" bgcolor="#FFFFFF">参选品牌</td>
					<td valign="middle" align="center" bgcolor="#FFFFFF">投票次数</td>
					<td valign="middle" align="center" bgcolor="#FFFFFF">评论次数</td>
					<td valign="middle" align="center" bgcolor="#FFFFFF">成绩</td>
				</tr>
				<c:forEach items="${PAGERESULT.data}" var="voteResult" varStatus="status">
					<tr>
						<td>${voteResult.attenderName}</td>
						<td>${voteResult.voteCountNum}</td>
						<td>${voteResult.voteCommentNum}</td>
						<td>${voteResult.voteFinalCountGrade}</td>
					</tr>
				</c:forEach>
			</table>
			<div>
				<%@ include file="/view/pubservice/common/pageDirection.jsp" %>
			</div>
		</div>
	</div>
	<div style="clear: both;"></div>
</div>

