<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<c:if test="${recommendProject != null}">
<c:set var="remainSignUpSTime" value="${projectRule.signUpSTime.time - nowDate.time}"></c:set>
<c:set var="remainSignUpETime" value="${projectRule.signUpETime.time - nowDate.time}"></c:set>
<c:set var="projStatus" value="0" />
<c:choose>
	<c:when test="${recommendProject.project.projProcessStatus == '280'}">
		<c:set var="projStatus" value="4" />
	</c:when>
	<c:when test="${recommendProject.project.projProcessStatus >= 200}">
		<c:set var="projStatus" value="3" />
	</c:when>
	<c:when test="${recommendProject.project.projProcessStatus >= 160}">
		<c:set var="projStatus" value="2" />
	</c:when>
	<c:when test="${recommendProject.project.projProcessStatus >= 130}">
		<c:set var="projStatus" value="1" />
	</c:when>
	<c:otherwise></c:otherwise>
</c:choose>

<h3>
	<a href="javascript:void(0);" title="${recommendProject.project.projName}" onclick="common.goToBulletinDetail('${recommendProject.project.objId}','01');">
		<c:choose><c:when test="${fn:length(recommendProject.project.projName) > 24}">${fn:substring(recommendProject.project.projName,0,23)}…</c:when><c:otherwise>${recommendProject.project.projName}</c:otherwise></c:choose>
	</a>
</h3>
<p class="hotImg"><img style="width: 180px; height: 180px;" src="AttachmentController.do?method=showImg&objId=${recommendProject.picture}&fileNameSuffix=_180*180"/></p>
<ul class="hotDetails">
	<li>项目进度：<div class="projectProgress">
		<ul>
			<li <c:choose><c:when test="${projStatus > 1}">class="fist"</c:when></c:choose>>供应商报名</li>
			<li <c:choose><c:when test="${projStatus == 2}">class="selected"</c:when><c:when test="${projStatus > 2}">class="center"</c:when></c:choose>>投标评标</li>
			<li <c:choose><c:when test="${projStatus == 3}">class="selected"</c:when><c:when test="${projStatus > 3}">class="center"</c:when></c:choose>>定标</li>
			<li <c:choose><c:when test="${projStatus == 4}">class="selected"</c:when></c:choose>>结束</li>
		</ul></div>
	</li>
	<li>项目编号：${recommendProject.project.projCode}</li>
	<li>采购方式：${recommendProject.project.ebuyMethodCN}</li>
	<li>采购单位：<c:choose><c:when test="${fn:length(recommendProject.project.buyersName) > 24}">${fn:substring(recommendProject.project.buyersName,0,23)}…</c:when><c:otherwise>${recommendProject.project.buyersName}</c:otherwise></c:choose></li>
	<li>报名截止时间：<em><fmt:formatDate value="${projectRule.signUpETime}" pattern="yyyy-MM-dd HH:mm"/></em></li>
	<li>招标代理：${recommendProject.project.agencies.orgName}</li>
</ul>
<div class="btnBoxR">
	<a class="good" href="javascript:void(0);" onclick="ShowBiddingIndex.shareBulletin('${recommendProject.project.objId}');"><span>推荐</span></a>
	<a class="collection" href="javascript:void(0);" onclick="ShowBiddingIndex.addFavorites('${recommendProject.project.objId}');"><span>收藏</span></a>
	<c:choose>
		<c:when test="${userType=='publisher' || userType=='agency'}">
			<a href="javascript:void(0);" class="bigBtn" onclick="RecommendBiddingProject.showProject()">查看项目</a>
		</c:when>
		<c:when test="${userType=='supplier' && hasSignUp}">
         	<a href="javascript:void(0);" class="bigBtn" onclick="RecommendBiddingProject.showProject()">查看项目</a>
		</c:when>
		<c:when test="${remainSignUpSTime>0}">
			<a href="javascript:void(0);" class="bigBtn" onclick="common.goToBulletinDetail('${recommendProject.project.objId}','01');">报名未开始</a>
		</c:when>
		<c:when test="${remainSignUpETime<0}">
			<a href="javascript:void(0);" class="bigBtn" onclick="common.goToBulletinDetail('${recommendProject.project.objId}','01');">报名已结束</a>
		</c:when>
		<c:when test="${userType=='supplier' && !hasSignUp}">
         	<a href="javascript:void(0);" class="bigBtn" onclick="RecommendBiddingProject.registration('${recommendProject.project.objId}')">我要报名</a>
		</c:when>
		<c:when test="${userType=='visitor'}">
         	<a href="javascript:void(0);" class="bigBtn" onclick="RecommendBiddingProject.registration('${recommendProject.project.objId}')">登录后报名</a>
		</c:when>
		<c:otherwise>
         	<a href="javascript:void(0);" class="bigBtn" onclick="common.goToBulletinDetail('${recommendProject.project.objId}','01');">我要报名</a>
		</c:otherwise>
	</c:choose>
</div>
</c:if>

<script>
/**电子招标--重磅推荐项目 */
var RecommendBiddingProject = {};

//报名
RecommendBiddingProject.registration = function(projectId){
	//判断是否登录
	if(!common.isLogin(true,true)){ return; }

	//判断是否报过名
	$.getJSON($("#initPath").val()+"/SupplierSignupController.do?method=ifHasSignUp",{"projectId":projectId},function(json){
		if(json.success){
			$.epsDialog({
				id:"registrationDiv",
				url:$('#initPath').val()+'/SignUprecordController.do?method=toPage&projectId='+projectId+'&orgInfoId='+$('#currentOrgId').val(),
				title:"供应商报名",
				maxWin:false,
				onClose: function(){
					window.location.reload();
				}
			})
		}else{
			alert(json.result);
		}
	});
}

//查看项目详情
RecommendBiddingProject.showProject = function() {
	window.location.href = $('#initPath').val()+"/ModelIndexController.do?method=toDeskTopIndex&viewName=deskTopIndexViewES";
	return false;
}

</script>