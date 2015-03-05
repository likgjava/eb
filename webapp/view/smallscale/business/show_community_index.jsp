<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 

<title>阳光易购采购交易平台--社区</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/plug-in/extjs/resources/css/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/web.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/smallscale/css/forumTopic.css"/>
<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/resource/plug-in/extjs/ext-all.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/smallscale/business/show_community_index.js'></script>
</head>
<body>
<!--页面容器 开始-->
<div id="container">
  <!--头部容器 开始-->
  <div class="header">
  	<%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
  </div>
  <!--头部容器 结束-->
  <!--主要内容 开始-->
  <div id="sysContent" class="page">
  	<input type="hidden" id="communityId" value="${community.objId}"/>
  	<input type="hidden" id="topicId" value="${topicId}"/>
	<div id="contentSub" class="col-sub" style="float:left">
		<!-- 第一列 社区介绍 -->
		<div class="cumInfo">
			<!-- 社区图片 -->
			<div class="cum-avatar" style="width: 160px; height: 120px;">
             	<img style="width: 160px; height: 120px;" src="<c:url value="AttachmentController.do?method=showImg&objId=${community.picture}" />" />
			</div>
			<!-- end 社区图片 -->
			
			<!-- 社区相关信息 -->
			<div class="cum-information">
				<h3><span>${community.communityName}</span></h3>
				<ul>
					<li><span>社区级别：</span>
					<c:if test="${community.isRecommended}">
						<img src="view/resource/skin/smallscale/img/flag_red.png" alt="推荐社区"/>
					</c:if>
					<c:if test="${community.isSpecial}">
						<img src="view/resource/skin/smallscale/img/flag_blue.png" alt="特色社区"/>
					</c:if>
					<c:if test="${!community.isSpecial && !community.isRecommended}">
						<img src="view/resource/skin/smallscale/img/flag_green.png" alt="普通社区"/>
					</c:if>
					</li>
					<li><span>QQ<img src="view/resource/skin/sysicon/16/group.png" />号：</span><a title="QQ群号" href="javascript:void(0);" onclick="window.open('http://qun.qq.com/air/#'+${community.groupId}+'/bbs')">${community.groupId}</a></li>
					<li><span>创建时间：</span><fmt:formatDate value="${community.createTime}" pattern="yyyy-MM-dd"/></li>
					<li><span class="bp-users-num">会员数量：</span><fmt:formatNumber value="${businessMemberNum}" pattern="##,###"/> 家</li>
				</ul>
			</div>
		</div>
		<!--end 第一列 社区介绍 -->
		
		<!-- 第二列 推荐社区 -->
		<div class="recomdCum">
			<div class="hd">
				<h3>推荐社区</h3>
			</div>
			<div class="bd">
				<ul>
					<c:forEach var="recommendCommunity" items="${recommendCommunityList}">
			        	<li>
			                <div class="avatar" style="width: 60px;height: 45px;">
			                	<img  style="width: 60px;height: 45px;" src="<c:url value="AttachmentController.do?method=showImg&objId=${recommendCommunity.picture}" />"/>
			                </div>
			                <div class="name"><a href="javascript:void(0);" title="${recommendCommunity.communityName}" onclick="common.goToCommunity('${recommendCommunity.objId}');return false;" target="_blank">
			                	<c:choose>
									<c:when test="${fn:length(recommendCommunity.communityName) > 4}">${fn:substring(recommendCommunity.communityName,0,4)}…</c:when>
									<c:otherwise>${recommendCommunity.communityName}</c:otherwise>
								</c:choose>
			                </a></div>
			            </li>
			        </c:forEach>
				</ul>
			</div>
		</div>
		<!--end 推荐社区-->
	</div>
	<div id="contentMain" class="col-main">
		<!-- 社区简介 -->
		<div class="introduce">
			<h3><span>${community.communityName}社区</span></h3>
			<div class="intMemo">${community.memo}</div>
		</div>
		
		<!-- 最新主题和会员展示 -->
		<div id="epsTabs" class="epsTabs">
			<ul>
				<li><a href="#newTopics" id="topicTab"><span>最新主题</span></a></li>
				<li><a href="#memberShow" id="memberTab"><span>会员展示</span></a></li>
			</ul>
			<div id="newTopics">
				<div id="noLoginHiddenDiv">
					<div id="mineTopicManagerDiv">
					</div>
				</div>
				<div class="operationBtnDiv r">
					<button id="newAddForumTopic"><span>发表主题</span></button>
					<button id="returnList" class="hidden"><span>返回列表</span></button>
				</div>
				<div id="topicList">
					<table class="frontTableList" id="forumTopicList">
						<thead>
							<tr>
						        <th>标题</th>
						        <th>发表机构</th>
						        <th class="center">回帖数</th>
						        <th class="date">更新时间</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				<div id="topicInfo" class="hidden"></div>
			</div>
			<div id="memberShow">
				<table class="frontTableList" id="businessMemberList">
					<thead>
						<tr>
							<th>企业名称</th>
							<th>行业</th>
							<th class="center">地区</th>
							<th class="date">加入时间</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div id="contentSupp" class="hidden"></div>
  </div>
  <!--主要内容 结束-->
  <!--底部容器 开始-->
  <div class="footer">
    <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
  </div>
  <!--底部容器 结束-->
</div>
<!--页面容器 结束-->
<div id="extraDiv">
  <!--扩展用容器，用于与内容无关的装饰性扩展-->
  <div id="extraDiv1"></div>
  <div id="extraDiv2"></div>
  <div id="extraDiv3"></div>
  <div id="extraDiv4"></div>
  <div id="extraDiv5"></div>
  <div id="extraDiv6"></div>
</div>
</body>
</html>