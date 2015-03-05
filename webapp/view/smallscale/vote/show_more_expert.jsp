<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
	var showMoreExpertJsp = {};
	var show_list = {};
	//分页跳转
	show_list.makeSearchData = function(){
		var voteUnitGroupId = $('#voteUnitGroupId').val();
		showMoreExpertJsp.showMoreExpert(voteUnitGroupId);
	}
	
	//参选对象
	showMoreExpertJsp.showMoreExpert = function(voteUnitGroupId){
		
		showMoreExpertJsp.search = "";
		//分页信息
		if($('#rp') && $('#rp').val()==null){
			showMoreExpertJsp.search += "&rp=20";
			showMoreExpertJsp.search += "&page=1";
		}else{
			showMoreExpertJsp.search += "&rp="+$('#rp').val();
			showMoreExpertJsp.search += "&page="+$('#page').val();
		}

		var voteTemplateId = $('#templateId').val();
		$("#conBody").loadPage($('#initPath').val()+'/VoteTemplateShowController.do?method=showMoreUnitOrExpertOrOrg&voteTemplateId='+voteTemplateId+'&voteUnitGroupId='+voteUnitGroupId+'&showMoreType=moreExperts'+showMoreExpertJsp.search);

}
</script>
<input type="hidden" id="voteUnitGroupId" value="${voteUnitGroupId}"/>
<div class="main_02">
	<div class="tupian"><img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/main_img_08.jpg" id="expertTitlePic"/></div>
	<div>
		<ul>
			<c:forEach items="${voteUnitGroupList}" var="voteUnitGroup">
				<li style="float: right;padding: 10px 10px;list-style: none;font-size: 25px;">
					<a href="javascript:void(0);" onclick="showMoreExpertJsp.showMoreExpert('${voteUnitGroup.objId }')">${voteUnitGroup.groupName }</a>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<div class="main_03">
	<c:set var="startCount" value="1"/>
	<c:forEach begin="1" end="${(PAGERESULT.totalRowNum-1)/6+1}">
		<c:set var="endCount" value="${startCount+6-1}"/>
		<c:forEach items="${PAGERESULT.data}" var="voteAssesseExpert" begin="${startCount-1}" end="${endCount-1}" varStatus="status">
			<div class="zhuanjia">
				<dl>
					<dt><img src="AttachmentController.do?method=showImg&objId=${voteAssesseExpert.expertPic }" height="128" width="121"/></dt>
					<dd>
						<span>${voteAssesseExpert.expertName }</span> <br>
					</dd>
					<dd>
						<span style="margin-top: 5px;">${voteAssesseExpert.expertComment }</span>
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
