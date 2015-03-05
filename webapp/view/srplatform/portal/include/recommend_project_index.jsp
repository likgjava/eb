<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<c:if test="${recommendProject != null}">
<input type="hidden" id="signUpSuccess" value="0" />
<div class="hotArea">
	<c:set var="remainEndTime" value="${recommendProject.project.evalEndTime.time - nowDate.time}"></c:set>
	<c:set var="remainSignUpSTime" value="${recommendProject.project.signUpSTime.time - nowDate.time}"></c:set>
	<c:set var="remainSignUpETime" value="${recommendProject.project.signUpETime.time - nowDate.time}"></c:set>
	<h3>
		<a href="javascript:void(0);" title="${recommendProject.project.projName}" onclick="common.goToBulletinDetail('${recommendProject.project.objId}','12');">
			<c:choose><c:when test="${fn:length(recommendProject.project.projName) > 14}">${fn:substring(recommendProject.project.projName,0,13)}…</c:when><c:otherwise>${recommendProject.project.projName}</c:otherwise></c:choose>
		</a>
	</h3>
	<p class="hotImg"><img style="width: 90px; height: 90px;" src="<%=request.getContextPath()%>/AttachmentController.do?method=showImg&objId=${recommendProject.picture}&fileNameSuffix=_160*160"/></p>
	<p class="signUp">
		<c:choose>
			<c:when test="${userType=='publisher' || userType=='agency'}">
          		<a href="javascript:void(0);" onclick="recommendProjectIndexView.showProject('${recommendProject.project.objId}','publisher')" title="查看项目" class="bigBtn">查看项目</a>
			</c:when>
			<c:when test="${userType=='supplier' && hasSignUp}">
          		<a href="javascript:void(0);" onclick="recommendProjectIndexView.showProject('${recommendProject.project.objId}','supplier')" title="查看项目" class="bigBtn">查看项目</a>
			</c:when>
			<c:when test="${remainSignUpSTime>0}">
				<a href="javascript:void(0);" class="bigBtn" onclick="common.goToBulletinDetail('${recommendProject.project.objId}','12');">报名未开始</a>
			</c:when>
			<c:when test="${remainSignUpETime<0}">
				<a href="javascript:void(0);" class="bigBtn" onclick="common.goToBulletinDetail('${recommendProject.project.objId}','12');">报名已结束</a>
			</c:when>
			<c:when test="${userType=='supplier' && !hasSignUp}">
          		<a href="javascript:void(0);" onclick="recommendProjectIndexView.registration(this,'${recommendProject.project.objId}')" title="我要报名" class="bigBtn">我要报名</a>
			</c:when>
			<c:when test="${userType=='visitor'}">
          		<a href="javascript:void(0);" onclick="recommendProjectIndexView.registration(this,'${recommendProject.project.objId}')" title="登录后即可报名" class="bigBtn">登录后报名</a>
			</c:when>
			<c:otherwise>
          		<a href="javascript:void(0);" class="bigBtn" onclick="common.goToBulletinDetail('${recommendProject.project.objId}','12');">我要报名</a>
			</c:otherwise>
		</c:choose>
	</p>
</div>
<ul class="newsList">
	<li title="${recommendProject.project.buyersName}">采购单位：<c:choose><c:when test="${fn:length(recommendProject.project.buyersName) > 10}">${fn:substring(recommendProject.project.buyersName,0,9)}…</c:when><c:otherwise>${recommendProject.project.buyersName}</c:otherwise></c:choose></li>
	<li>采购金额：<em>￥<fmt:formatNumber value="${recommendProject.project.budgetTotalMoney}" pattern="#,##0.00" /></em>元</li>
	<li>剩余时间：<span name="${remainEndTime}" id="remainTime"><em class='em'>0</em>时<em class='em'>00</em>分<em class='em'>00</em>秒</span></li>
</ul>
</c:if>

<script>
/** 首页重磅推荐项目页面 */
var recommendProjectIndexView = {};
var recommendProjectTime;

//定时刷新时间
recommendProjectIndexView.findBargainEndTime=function(){
	if(!$("#remainTime")) clearInterval(recommendProjectTime);
	var remainTime = getRemainTimeArray($("#remainTime").attr("name"))
	$("#remainTime").html("<em class='em'>"+remainTime[0]+"</em>天<em class='em'>"+remainTime[1]+"</em>时<em class='em'>"+remainTime[2]+"</em>分<em class='em'>"+remainTime[3]+"</em>秒");
	$("#remainTime").attr("name", (parseInt($("#remainTime").attr("name"))-1000));
}

//报名
recommendProjectIndexView.registration = function(obj, projectId){
	//判断是否登录
	if(!common.isLogin(true,true)){ return; }

	//判断是否报过名
	$.getJSON($("#initPath").val()+"/SupplierSignupController.do?method=ifHasSignUp",{"projectId":projectId},function(json){
		if(json.success){
			$.epsDialog({
				id:"registrationDiv",
				url:$("#initPath").val()+"/SupplierSignupController.do?method=toSupplierSignUp&projectId="+projectId,
				title:"供应商报名",
				maxWin:false,
				onClose: function(){
					if($("#signUpSuccess").val() == '1'){
						var htmlStr = '<a href="javascript:void(0);" onclick="recommendProjectIndexView.showProject(\''+projectId+'\',\'supplier\')" title="查看项目" class="bigBtn">查看项目</a>';
						$(obj).parent().html(htmlStr);
					}
				}
			})
		}else{
			alert(json.result);
		}
	});
}

//查看项目详情
recommendProjectIndexView.showProject = function(projectId, userType) {
	if(userType=='publisher' || userType=='agency'){userType = 'buyer';}
	window.location.href = $("#initPath").val()+"/BargainProjectController.do?method=toProjectView&userType="+userType+"&projectId="+projectId;
	return false;
}

$(document).ready(function(){
	recommendProjectTime = setInterval("recommendProjectIndexView.findBargainEndTime()", 1000);
});
</script>