<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*,java.math.*" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@page import="com.gpcsoft.epp.bulletin.domain.Bulletin"%>

<style>
.unSignUp{background:#D0D0D0; width:86px; height:24px; display:inline-block; color:#fff; text-align:center; font-weight:bold; }/*我要报名*/
</style>

<script>
/** 采购需求首页-推荐项目页面 */
var recommendProjectListIndexView = {};
var recommendProjectTime;

//定时刷新时间
recommendProjectListIndexView.findBargainEndTime=function(){
	if(!$(".remainTime")) clearInterval(recommendProjectTime);
	$(".remainTime").each(function (index, domEle){ 
		var remainTime = getRemainTimeArray($(domEle).attr("name"))
		$(domEle).html("<em class='em'>"+remainTime[0]+"</em>天<em class='em'>"+remainTime[1]+"</em>时<em class='em'>"+remainTime[2]+"</em>分<em class='em'>"+remainTime[3]+"</em>秒");
		$(domEle).attr("name", (parseInt($(domEle).attr("name"))-1000));
	});
}

//报名
recommendProjectListIndexView.registration = function(obj,projectId){
	if(!common.isLogin(false)){
		var url = $('#initPath').val()+"/IndexViewController.do?method=toLogin&viewName=loginDivView"; 
		$.epsDialog({
			title:'系统登录',
			url:url,
			width:640,
			height:400,
			maxWin:false,
			onClose: function(){
				if($("#loginSuccess").val() == '1'){
					window.location.reload();
				}
			}
		});
		return ;
	}

	if(!common.isHasRole("s")) {alert("您还不是供应商，请申请为供应商再做此操作！");return;}
	
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
						var htmlStr = '<a href="javascript:void(0);" onclick="recommendProjectListIndexView.showProject(\''+projectId+'\',\'supplier\')" title="查看项目" class="signUp">查看项目</a>';
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
recommendProjectListIndexView.showProject = function(projectId, userType) {
	if(userType=='publisher' || userType=='agency'){userType = 'buyer';}
	window.location.href = $("#initPath").val()+"/BargainProjectController.do?method=toProjectView&userType="+userType+"&projectId="+projectId;
	return false;
}

$(document).ready(function(){
	recommendProjectTime = setInterval("recommendProjectListIndexView.findBargainEndTime()", 1000);
});
</script>

<h2>重磅推荐</h2>
<%pageContext.setAttribute("now",(new Date()).getTime());%>
<input type="hidden" id="signUpSuccess" value="0" />
<input type="hidden" id="loginSuccess" value="0" />
<c:forEach var="rp" items="${recommendProjects}" varStatus="status">
	<c:set var="remainEndTime" value="${rp.bulletin.project.evalEndTime.time - now}"></c:set>
	<c:set var="remainSignUpSTime" value="${rp.bulletin.project.signUpSTime.time - now}"></c:set>
	<c:set var="remainSignUpETime" value="${rp.bulletin.project.signUpETime.time - now}"></c:set>
	<c:if test="${status.index % 2 == 0}"><div class="clos"></c:if>
	<div class="goodsShow">
		<h4>
			<a href="javascript:void(0);" title="${rp.bulletin.project.projName}" onclick="common.goToBulletinDetail('${rp.bulletin.objId}');return false;">
				<c:choose>
					<c:when test="${fn:length(rp.bulletin.project.projName) > 15}">${fn:substring(rp.bulletin.project.projName,0,15)}… </c:when>
					<c:otherwise>${rp.bulletin.project.projName}</c:otherwise>
				</c:choose>
			</a>
		</h4>
		<p class="picturesShow" style="width: 100px; height: 100px;"><img style="width: 100px; height: 100px;" src="AttachmentController.do?method=showImg&objId=${rp.picture}"/></p>
        <ul class="HlineHeight">
          <li><label>采购单位：</label> <span title="${rp.bulletin.project.buyersName}">
          	<c:choose>
				<c:when test="${fn:length(rp.bulletin.project.buyersName) > 13}">${fn:substring(rp.bulletin.project.buyersName,0,13)}… </c:when>
				<c:otherwise>${rp.bulletin.project.buyersName}</c:otherwise>
			</c:choose>
          </span></li>
          <c:set var="budgetTotalMoney" value="${rp.bulletin.project.budgetTotalMoney}"></c:set>
	      <%pageContext.setAttribute("budgetTotalMoney",new BigDecimal(pageContext.getAttribute("budgetTotalMoney").toString()).divide(new BigDecimal(10000)));%>
          <li><label>采购金额：</label> <span>￥<fmt:formatNumber value="${budgetTotalMoney}" pattern="#,##0.00" />万元</span></li>
		<!-- 小额过滤 竞价和议价 -->
		<c:choose>
			<c:when test="${rp.bulletin.project.ebuyMethod == '06'}">
				<li><label>剩余时间：</label> <span name="${remainEndTime}" class="remainTime"><em class='em'>0</em>时<em class='em'>00</em>分<em class='em'>00</em>秒</span></li>
          		<c:choose>
					<c:when test="${userTypes[status.index]=='publisher' || userTypes[status.index]=='agency'}">
		          		<li><a href="javascript:void(0);" onclick="recommendProjectListIndexView.showProject('${rp.bulletin.project.objId}','publisher')" title="查看项目" class="signUp">查看项目</a></li>
					</c:when>
					<c:when test="${userTypes[status.index]=='supplier' && hasSignUps[status.index]}">
		          		<li><a href="javascript:void(0);" onclick="recommendProjectListIndexView.showProject('${rp.bulletin.project.objId}','supplier')" title="查看项目" class="signUp">查看项目</a></li>
					</c:when>
					<c:when test="${remainSignUpSTime>0}">
						<li><span class="unSignUp">报名未开始</span></li>
					</c:when>
					<c:when test="${remainSignUpETime<0}">
						<li><span class="unSignUp">报名已结束</span></li>
					</c:when>
					<c:when test="${userTypes[status.index]=='supplier' && !hasSignUps[status.index]}">
		          		<li><a href="javascript:void(0);" onclick="recommendProjectListIndexView.registration(this,'${rp.bulletin.project.objId}')" title="我要报名" class="signUp">我要报名</a></li>
					</c:when>
					<c:when test="${userType=='visitor'}">
		          		<li><a href="javascript:void(0);" onclick="recommendProjectListIndexView.registration(this,'${rp.bulletin.project.objId}')" title="登录后即可报名" class="signUp">登录后报名</a></li>
					</c:when>
					<c:otherwise>
		          		<li><span class="unSignUp">我要报名 &gt;</span></li>
					</c:otherwise>
				</c:choose>
			</c:when>
		</c:choose>
        </ul>
       </div>
       <c:if test="${status.index % 2 == 1 || status.index == fn:length(recommendProjects)-1}"></div></c:if>
</c:forEach>
