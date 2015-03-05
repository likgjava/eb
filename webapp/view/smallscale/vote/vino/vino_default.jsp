<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
var vino_default = {};
//我要投票
vino_default.toVote = function(voteAssessedThingId,templateId,isSingleVote){
	var expertVoteFactor = $('#expertVoteFactor').val();//专家投票占分比例系数
	//指标投票
	if(${voteTemplate.isSingleVote == false}){
		$("#conBody").loadPage($('#initPath').val()+'/VoteTemplateShowController.do?method=toVoteJsp&templateId='+templateId+'&voteAssessedThingId='+voteAssessedThingId+'&isSingleVote='+isSingleVote+'&expertVoteFactor='+expertVoteFactor);
	}
}

//评论详情
vino_default.showVoteAssessedComment = function(voteAssessedThingId){
	vino_default.search = "";
	//分页信息
	if($('#rp') && $('#rp').val()==null){
		vino_default.search += "&rp=25";
		vino_default.search += "&page=1";
	}else{
		vino_default.search += "&rp="+$('#rp').val();
		vino_default.search += "&page="+$('#page').val();
	}
	$("#conBody").loadPage($('#initPath').val()+'/VoteTemplateShowController.do?method=showVoteUnitComment&voteAssessedThingId='+voteAssessedThingId+vino_default.search);
}

//跳转至更多参选品牌、评审专家、所属机构
vino_default.showMoreBrandsOrExperts = function(showMoreType){
	vino_default.search = "";
	//分页信息
	vino_default.search += "&rp=20";
	vino_default.search += "&page=1";

	var voteTemplateId = $('#templateId').val();
	$("#conBody").loadPage($('#initPath').val()+'/VoteTemplateShowController.do?method=showMoreUnitOrExpertOrOrg&voteTemplateId='+voteTemplateId+'&showMoreType='+showMoreType+vino_default.search);
}

</script>


<div id="main_01">
&nbsp;&nbsp;&nbsp;&nbsp;跨越了金融危机的冬季，当前面临着食品安全问题，酒业品牌认证专业性和公开性决定了我国酒水产业的发展质量。促进酒水产业的流通与健康发展，促进酒水产业与国际市场的接轨，我国酒水产业急需新一轮升级。食品安全法出台,酒水的营养与健康，酒水产业的自主创新程度，酒业关注低碳，绿色生产与采购的社会责任意识，都体现了我国酒业管理的总体水平高低。<br>
&nbsp;&nbsp;&nbsp;&nbsp;如何更好的执行与贯彻食品安全法，我们消费者如何能选购到值得永久珍藏的民族酒水品牌？本次2011年度的中国酒业采购评选推介活动隆重召开，谁能胜出展露峥嵘，就在2011年酒月酒日揭晓！
<span><a href="javascript:void(0);" onclick="vinoIndex.toVinoHdjs()">详细内容&gt;&gt;</a></span>
</div>

<div class="main_06">
<div class="main_06_left">
<div class="main_06_left_01"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/main_img_02.jpg" width="123" height="50" /></div>
<div class="main_06_left_02">
<div class="main_06_im"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/mingqipaihang.jpg" width="177" height="48" /></div>
<div class="main_06_im"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/yangguang.jpg" width="177" height="48" /></div>
<div style="clear:both;"></div>
</div>
</div>
<div class="main_06_right">
<div class="main_06_right_01"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/main_img_12.jpg" /></div>
<div class="main_06_right_02">
<div class="main_06_im"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/soubiaowang.jpg" width="177" height="48" /></div>
<div class="main_06_im"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/zhaobiaoshi.jpg" width="177" height="48" /></div>
<div class="main_06_im"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/caizhaowang.jpg" width="176" height="49" /></div>
<div style="clear:both;"></div>
</div>
<div style="clear:both;"></div>
</div>
<div style="clear:both;"></div>
</div>

<div class="main_04">
	<div class="main_04_left"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/main_img_03.jpg" /></div>
	<div class="main_04_right">
		<div class="main_04_right_01">
			<div style="float:left;"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/main_img_04.jpg" /></div>
			<div><a href="javascript:void(0);" onclick="vino_default.showMoreBrandsOrExperts('moreMediums')" style="display:block;height:50px;line-height:50px;float:right;margin-right:10px;font-size:14px;font-weight:600;color:#333;text-decoration:none;">更多&gt;&gt;</a></div>
		</div>
		
		<div class="main_04_right_02">
			<c:forEach items="${voteTemplateMediumList}" var="voteTemplateMedium">
				<div class="main_04_right_02_im">
					<a href="${voteTemplateMedium.orgInfo.webUrl}" target="_blank"><img src="AttachmentController.do?method=showImg&objId=${voteTemplateMedium.orgInfo.logo}" width="150" height="57"/></a>
				</div>
			</c:forEach>
		</div>
	</div>
</div>


<div class="main_02"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/main_img_05.jpg" width="121" height="50" /></div>
<div class="main_03"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/main_tu_04.jpg" /></div>
<div class="main_02"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/main_img_06.jpg" width="117" height="50" /></div>
<div class="main_03" style="padding: 0;"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/main_tu_05.jpg" width="906" height="105" /></div>
<div id="main_05"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/main_tu_06.jpg" /></div>

<div class="main_02">
<div class="tupian"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/main_img_08.jpg"
	width="367" height="50" /></div>
<div class="gengduo"><a href="javascript:void(0);" onclick="vino_default.showMoreBrandsOrExperts('moreExperts')">更多>></a></div>
</div>
<div class="main_03">
	<c:set var="startCount" value="1"/>
	<c:forEach begin="1" end="${(expertCount-1)/6+1}">
		<c:set var="endCount" value="${startCount+6-1}"/>
		<c:forEach items="${voteAssesseExpertList}" var="expertInfo" begin="${startCount-1}" end="${endCount-1}" varStatus="status">
			<div class="zhuanjia">
				<dl>
					<dt><img src="AttachmentController.do?method=showImg&objId=${expertInfo.expertPic}" height="128" width="121"/></dt>
					<dd>${expertInfo.expertName}<br>${expertInfo.expertComment}</dd>
					<c:set var="startCount" value="${startCount+1}"/>
				</dl>
			</div>
		</c:forEach>
	</c:forEach>
<div style="clear: both; height: 0;"></div>
</div>

<div class="main_02">
	<div class="tupian">
		<img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/main_img_09.jpg" width="122" height="50" />
	</div>
	<div class="gengduo"><a href="javascript:void(0);" onclick="vino_default.showMoreBrandsOrExperts('moreObjects')">更多>></a></div>
</div>
<div class="main_03">
	<c:set var="startCount" value="1"/>
	<c:forEach begin="1" end="${(trNumCount-1)/4+1}">
		<c:set var="endCount" value="${startCount+4-1}"/>
		<c:forEach items="${voteAssessThingList}" var="voteAssessedThing" begin="${startCount-1}" end="${endCount-1}" varStatus="status">
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
	<div style="clear: both;"></div>
</div>

<div class="main_02">
	<div class="tupian">
		<img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/main_img_10.jpg" width="122" height="50" />
	</div>
	<div class="gengduo"><a href="javascript:void(0);" onclick="vino_default.showMoreBrandsOrExperts('moreOrgs')">更多>></a></div>
</div>
<div class="main_03">
	<c:set var="startCount" value="1"/>
	<c:forEach begin="1" end="${(orgInfoListCount-1)/4+1}">
		<c:set var="endCount" value="${startCount+4-1}"/>
		<c:forEach items="${orgInfoList}" var="orgInfo" begin="${startCount-1}" end="${endCount-1}" varStatus="status">
			<div class="cypp">
				<dl>
					<dt><img src="AttachmentController.do?method=showImg&objId=${orgInfo[2]}" height="146" width="187"/></dt>
					<dd><a href="javascript:void(0);" onclick="template_unfurl.showOrgDetail('${orgInfo[0]}','${orgInfo[3]}','${orgInfo[4]}')"><span>${orgInfo[1]}</span></a><br></dd>
					<c:set var="startCount" value="${startCount+1}"/>
				</dl>
			</div>
		</c:forEach>
	</c:forEach>
	<div style="clear: both;"></div>
</div>
