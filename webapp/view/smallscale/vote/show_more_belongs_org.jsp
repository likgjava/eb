<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
	var showMoreBelongOrgs = {};
	var show_list = {};
	//分页跳转
	show_list.makeSearchData = function(){
		showMoreBelongOrgs.showMoreOrgs();
	}
	
	//参与机构
	showMoreBelongOrgs.showMoreOrgs = function(){
		
		showMoreBelongOrgs.search = "";
		//分页信息
		if($('#rp') && $('#rp').val()==null){
			showMoreBelongOrgs.search += "&rp=20";
			showMoreBelongOrgs.search += "&page=1";
		}else{
			showMoreBelongOrgs.search += "&rp="+$('#rp').val();
			showMoreBelongOrgs.search += "&page="+$('#page').val();
		}

		var voteTemplateId = $('#templateId').val();
		$("#conBody").loadPage($('#initPath').val()+'/VoteTemplateShowController.do?method=showMoreUnitOrExpertOrOrg&voteTemplateId='+voteTemplateId+'&showMoreType=moreOrgs'+showMoreBelongOrgs.search);

}
</script>
<div class="main_02">
	<div class="tupian">
		<img src="<%=request.getContextPath()%>/view/smallscale/vote/vino/images/main_img_10.jpg" width="122" height="50" />
	</div>
</div>
<div class="main_03">
	<c:set var="startCount" value="1"/>
	<c:forEach begin="1" end="${(PAGERESULT.totalRowNum-1)/4+1}">
		<c:set var="endCount" value="${startCount+4-1}"/>
		<c:forEach items="${PAGERESULT.data}" var="orgInfo" begin="${startCount-1}" end="${endCount-1}" varStatus="status">
			<div class="cypp">
				<dl>
					<dt><img src="AttachmentController.do?method=showImg&objId=${orgInfo[2]}" height="146" width="187"/></dt>
					<dd><a href="javascript:void(0);" onclick="template_unfurl.showOrgDetail('${orgInfo[0]}','${orgInfo[3]}','${orgInfo[4]}')"><span>${orgInfo[1]}</span></a><br></dd>
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
