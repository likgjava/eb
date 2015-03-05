<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!--招标项目开始-->
<div class='grid16_6'>
	<div id="bidding" class="cols groupBuy ebidTabs">
		<h2><span class="titleDown">招标</span></h2>
		<ul>
			<li><a href="#biddingIn">正在进行</a></li>
			<li><a href="#biddingDeal" id="biddingDealTab">已经成交</a></li>
		</ul>
		<!-- 正在进行 -->
		<div id="biddingIn" class="infoList">
			<ul>
				<c:forEach var="project" items="${biddingProjectList}" varStatus="status">
				<li>
					<h3>
						<a href="javascript:void(0);" title="${project.projName}" onclick="common.goToBulletinDetail('${project.objId}','01');">
							<c:choose><c:when test="${fn:length(project.projName) > 23}">${fn:substring(project.projName,0,22)}…</c:when><c:otherwise>${project.projName}</c:otherwise></c:choose>
						</a>
					</h3>
					<ul>
						<li>采购方式：<em>${project.ebuyMethodCN}</em></li>
						<li class="time">报名截止时间：<em><fmt:formatDate value="${projectRuleList[status.index].signUpETime}" pattern="MM-dd HH:mm"/></em></li>
						<li>采购区域：<em>${project.createUser.emp.company.town.parent.parent.name},${project.createUser.emp.company.town.parent.name},${project.createUser.emp.company.town.name}</em></li>
					</ul>
					<div class="btnBoxR">
						<c:choose>
							<c:when test="${projectRuleList[status.index].signUpETime.time < nowDate.time}">
								<a href="javascript:void(0);" class="bigBtn" onclick="common.goToBulletinDetail('${project.objId}','01');">报名已结束</a>
							</c:when>
							<c:when test="${projectRuleList[status.index].signUpSTime.time > nowDate.time}">
								<a href="javascript:void(0);" class="bigBtn" onclick="common.goToBulletinDetail('${project.objId}','01');">报名未开始</a>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0);" class="bigBtn" onclick="common.goToBulletinDetail('${project.objId}','01');">去报名</a>
							</c:otherwise>
						</c:choose>
					</div>
				</li>
				</c:forEach>
			</ul>
		</div>
		<!--已经成交-->
		<div id="biddingDeal" class="infoList">
			<ul>
              <c:forEach var="project" items="${biddingDealedProjectList}">
				<li>
					<h3>
						<a href="javascript:void(0);" title="${project.projName}" onclick="common.goToBulletinDetail('${project.objId}','01');">
							<c:choose><c:when test="${fn:length(project.projName) > 23}">${fn:substring(project.projName,0,22)}…</c:when><c:otherwise>${project.projName}</c:otherwise></c:choose>
						</a>
					</h3>
					<ul>
						<li>采购方式：<em>${project.ebuyMethodCN}</em></li>
						<li>项目编号：<em>${project.projCode}</em></li>
						<li>采购区域：<em>${project.createUser.emp.company.town.parent.parent.name},${project.createUser.emp.company.town.parent.name},${project.createUser.emp.company.town.name}</em></li>
					</ul>
					<div class="btnBoxR"><a href="javascript:void(0);" class="bigBtn" onclick="common.goToBulletinDetail('${project.objId}','01');">去看看</a></div>
				</li>
				</c:forEach>
			</ul>
		</div>
		<div class="more"><a title="点击查看更多" href="<%=request.getContextPath()%>/ProjectShowController.do?method=toShowBiddingProjectList&rp=21&page=1" target="_blank">更多</a></div>
	</div>
</div>
<!--招标项目结束-->
<!--采购项目开始 -->
<div class='grid16_6'>
	<div id="purchasing" class="cols groupBuy ebidTabs">
		<h2><span class="titleUp">采购</span></h2>
		<ul>
			<li><a href="#purchasingIn">正在进行</a></li>
			<li><a href="#purchasingDeal" id="purchasingDealTab">已经成交</a></li>
		</ul>
		<!-- 正在进行 -->
		<div id="purchasingIn" class="infoList">
			<ul>
				<c:forEach var="project" items="${bargainProjectList}">
				<li>
					<h3>
						<a href="javascript:void(0);" title="${project.projName}" onclick="common.goToBulletinDetail('${project.objId}','12');">
							<c:choose><c:when test="${fn:length(project.projName) > 23}">${fn:substring(project.projName,0,22)}…</c:when><c:otherwise>${project.projName}</c:otherwise></c:choose>
						</a>
					</h3>
					<ul>
						<li>采购方式：<em>${project.ebuyMethodCN}</em></li>
						<li class="time">报名截止时间：<em><fmt:formatDate value="${project.signUpETime}" pattern="MM-dd HH:mm"/></em></li>
						<li>采购区域：<em>${project.createUser.emp.company.town.parent.parent.name},${project.createUser.emp.company.town.parent.name},${project.createUser.emp.company.town.name}</em></li>
					</ul>
					<div class="btnBoxR">
						<c:choose>
							<c:when test="${project.signUpETime.time < nowDate.time}">
								<a href="javascript:void(0);" class="bigBtn" onclick="common.goToBulletinDetail('${project.objId}','12');">报名已结束</a>
							</c:when>
							<c:when test="${project.signUpSTime.time > nowDate.time}">
								<a href="javascript:void(0);" class="bigBtn" onclick="common.goToBulletinDetail('${project.objId}','12');">报名未开始</a>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0);" class="bigBtn" onclick="common.goToBulletinDetail('${project.objId}','12');">去报名</a>
							</c:otherwise>
						</c:choose>
					</div>
				</li>
				</c:forEach>
			</ul>
		</div>
		<!--已经成交-->
		<div id="purchasingDeal" class="infoList">
			<ul>
				<c:forEach var="project" items="${bargainDealedProjectList}">
				<li>
					<h3>
						<a href="javascript:void(0);" title="${project.projName}" onclick="common.goToBulletinDetail('${project.objId}','12');">
							<c:choose><c:when test="${fn:length(project.projName) > 23}">${fn:substring(project.projName,0,22)}…</c:when><c:otherwise>${project.projName}</c:otherwise></c:choose>
						</a>
					</h3>
					<ul>
						<li>采购方式：<em>${project.ebuyMethodCN}</em></li>
						<li>项目编号：<em>${project.projCode}</em></li>
						<li>采购区域：<em>${project.createUser.emp.company.town.parent.parent.name},${project.createUser.emp.company.town.parent.name},${project.createUser.emp.company.town.name}</em></li>
					</ul>
					<div class="btnBoxR"><a href="javascript:void(0);" class="bigBtn" onclick="common.goToBulletinDetail('${project.objId}','12');">去看看</a></div>
				</li>
				</c:forEach>
			</ul>
		</div>
		<div class="more"><a title="点击查看更多" href="<%=request.getContextPath()%>/ProjectShowController.do?method=toShowBargainProjectList&rp=21&page=1" target="_blank">更多</a></div>
	</div>
</div>
<!--采购项目结束 -->

<script type="text/javascript">
$(document).ready(function(){
	//初始化Tab
	$('#bidding').tabs();
	$('#purchasing').tabs();
	//$('#biddingDealTab').click();
	//$('#purchasingDealTab').click();
});
</script>