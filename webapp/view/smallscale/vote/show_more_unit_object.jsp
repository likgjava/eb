<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
	var showMoreUnitObject = {};
	var show_list = {};
	//分页跳转
	show_list.makeSearchData = function(){
		var voteUnitGroupId = $('#voteUnitGroupId').val();
		showMoreUnitObject.showMoreUnitObjects(voteUnitGroupId);
	}
	
	//参选对象
	showMoreUnitObject.showMoreUnitObjects = function(voteUnitGroupId){
		
		showMoreUnitObject.search = "";
		//分页信息
		if($('#rp') && $('#rp').val()==null){
			showMoreUnitObject.search += "&rp=20";
			showMoreUnitObject.search += "&page=1";
		}else{
			showMoreUnitObject.search += "&rp="+$('#rp').val();
			showMoreUnitObject.search += "&page="+$('#page').val();
		}

		var voteTemplateId = $('#templateId').val();
		$("#conBody").loadPage($('#initPath').val()+'/VoteTemplateShowController.do?method=showMoreUnitOrExpertOrOrg&voteTemplateId='+voteTemplateId+'&voteUnitGroupId='+voteUnitGroupId+'&showMoreType=moreObjects'+showMoreUnitObject.search);

}
</script>
<input type="hidden" id="voteUnitGroupId" value="${voteUnitGroupId}"/>
<div class="main_02">
	<div class="tupian">
		<img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/main_img_09.jpg" width="122" height="50" />
	</div>
	<div>
		<ul>
			<c:forEach items="${voteUnitGroupList}" var="voteUnitGroup">
				<li style="float: right;padding: 10px 10px;list-style: none;font-size: 25px;">
					<a href="javascript:void(0);" onclick="showMoreUnitObject.showMoreUnitObjects('${voteUnitGroup.objId }')">${voteUnitGroup.groupName }</a>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<div class="main_03">
	<c:set var="startCount" value="1"/>
	<c:forEach begin="1" end="${(PAGERESULT.totalRowNum-1)/4+1}">
		<c:set var="endCount" value="${startCount+4-1}"/>
		<c:forEach items="${PAGERESULT.data}" var="voteAssessedThing" begin="${startCount-1}" end="${endCount-1}" varStatus="status">
			<div class="cypp">
				<dl>
					<dt><img src="AttachmentController.do?method=showImg&objId=${voteAssessedThing.picture }" height="146" width="187"/></dt>
					<dd>
						<span>${voteAssessedThing.attenderName }</span> <br>
						<span>投票数：<span>${voteAssessedThing.voteCountNum }&nbsp;次</span></span><br>
						<a href="javascript:void(0);" onclick="vino_default.toVote('${voteAssessedThing.objId}','${voteTemplate.objId }','${voteTemplate.isSingleVote }')">
							<img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/main_img_11.jpg" border="0" />
						</a>
						<a href="javascript:void(0);" onclick="vino_default.showVoteAssessedComment('${voteAssessedThing.objId}')"><span>评论详情</span></a>
					</dd>
					<c:set var="startCount" value="${startCount+1}"/>
				</dl>
			</div>
		</c:forEach>
	</c:forEach>
	<div>
		<%@ include file="/view/pubservice/common/pageDirection.jsp" %>
	</div>
	<div style="clear: both;"></div>
</div>
