<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@page import="com.gpcsoft.srplatform.auth.domain.User"%><%@page import="com.gpcsoft.plugin.acegi.AuthenticationHelper"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>【${supplier.orgInfo.orgName}】- ${fn:split(supplier.orgInfo.bidForRange,'##||##')[1]}供应 - 供应商库 - 阳光易购电子采购与招标平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>
<% User user = AuthenticationHelper.getCurrentUser(); %>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/base/css/button.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/base/css/form.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/baseEdit.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/orgshop/css/orgshop.css"/>
<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/smallscale/orgshop/show_orgshop_index.js'></script>
</head>
<body>
<!--用户登录信息 开始-->
<div class="hidden">
<p class="userInfo">
<%
	if(user!=null) {
%>
	<span id="loginUser"><%=user.getUsName()%></span>
<%
	}
%>
</p>
</div>
<!--用户登录信息 结束-->

<div class="wrap">
  <!--头部容器 开始-->
  <div id="shopcontainer">
    <!--快捷菜单 开始-->
    <div class="header">
      <a href="<%=request.getContextPath()%>/IndexViewController.do?method=index"><img src="<%=request.getContextPath()%>/view/resource/skin/orgshop/img/logo_bluegrd.png" width="246" height="29" border="0" class="logo"/></a>
      <ul class="quickMenu">
        <li><a id="goToIndex" href="javascript:void(0);">首页</a></li> |
        <li><a id="goToBidding" href="javascript:void(0);">电子招标</a></li> |
        <li><a id="goToBargain" href="javascript:void(0);">电子采购</a></li> |
        <li><a id="goToGoods" href="javascript:void(0);">商品库</a></li> |
        <li><a id="goToSupplier" href="javascript:void(0);">供应商库</a></li> |
        <li><a id="goToGoodsMall" href="javascript:void(0);">精品商城</a></li>
        <%
			if(user!=null) {
		%>
			| <li><a id="concernClient" name="concernClient" href="javascript:void(0);" onclick="show_list.addClient('00','${supplier.orgInfo.objId}','${supplier.orgInfo.orgName}')" title="关注此客户">关注此客户</a></li>
		<%
			}
		%>      
      </ul>
    </div>
    <!--快捷菜单 结束-->

    <!--企业名称信息 开始-->
    <div class="branding">
      <img src="<%=request.getContextPath() %>/AttachmentController.do?method=showImg&objId=${supplier.orgInfo.logo}" width="110" height="90" border="0" class="logo"/>
      <h1 class="webName">${supplier.orgInfo.orgName}</h1>
    </div>
    <!--企业名称信息 结束-->
    
    <!--主导航菜单 开始-->
    <div  class="navMain">
      <ul>
        <li class="selected"><a href="javascript:void(0);" title="首页" onclick="OrgShopIndex.showView(this)">首页</a></li>
        <li><a href="javascript:void(0);" id="gsjs" title="公司介绍 " onclick="OrgShopIndex.showView(this,'orgshopIntroductionView')">公司介绍 </a></li>
        <li><a href="javascript:void(0);" id="zzzs" title="资质证书" onclick="OrgShopIndex.showView(this,'orgshopCertificateView')">资质证书</a></li>
        <li><a href="javascript:void(0);" id="cgal" title="成功案例" onclick="OrgShopIndex.showView(this,'orgshopCaseView')">成功案例</a></li>
        <li><a href="javascript:void(0);" id="cpxx" title="产品信息" onclick="OrgShopIndex.showView(this,'orgshopGoodsInfoView')">产品信息</a></li>
        <li><a href="javascript:void(0);" id="qypj" title="企业评价" onclick="OrgShopIndex.showView(this,'orgshopEvaluationView')">企业评价</a></li>
        <li><a href="javascript:void(0);" id="shjd" title="投诉举报" onclick="OrgShopIndex.showView(this,'orgshopComplainView')">监督信息</a></li>
        <c:if test="${contact != null}">
        	<!-- li><a href="javascript:void(0);" title="联系方式" onclick="OrgShopIndex.showView(this,'orgshopContactView')">联系方式</a></li-->
        </c:if>
      </ul>
    </div>
    <!--主导航菜单 结束-->
    
    <!--广告  开始-->
    <!--div class="advert">
      <div class="text">品牌创造价值</div>
      <div class="text2">一流的服务 一流的品质</div>
      <div class="text2">细节决定成败 执行决定战略</div>
    </div-->
    <!--广告  结束-->
  
    <div class="wrapper" >
      <!--搜索  开始-->
      <div class="crumbs">
        <div class="marlf">当前位置 <em>&gt;</em> <a href="javascript:void(0);" id="currentLocation">首页</a> </div>
        <!-- 暂时屏蔽
        <div class="marrg">站内搜索：
          <input type="text" name="keywords" size="22" class="bor-comments" />
        </div>
         -->
      </div>
      <!--搜索  结束-->
   
      <!-- 内容部分 开始-->
      <div id="shopcontainer">
      <!-- 左侧信息 开始-->
      <div class="boxleft">
        <!-- 企业基本信息  开始-->
        <div class="Ent">
          <div class="title"><h2 class="marginlf">企业基本信息</h2></div>
          <div class="lefcon">
            <div class="scores"><img src="<%=request.getContextPath()%>/view/resource/skin/orgshop/img/logo_q.gif" height="61" width="62"/></div>
            <ul class="martop">
              <li><img src="<%=request.getContextPath()%>/view/resource/skin/orgshop/img/star1.png" width="118" height="19" /></li>
              <li> 评价总分：<fmt:formatNumber type="number" value="${supplier.evalSum}" pattern="#0.0"/></li>
            </ul>
            <div class="scorestext">
              <input type="hidden" id="orgInfoId" value="${supplier.orgInfo.objId}" />
              <p><span class="text3">组织机构代码：</span>${supplier.orgInfo.orgCode}</p>
              <p><span class="text3">供应商名称：</span>${supplier.orgInfo.orgName}</p>
              <p><span class="text3">入库时间：</span><fmt:formatDate value="${supplier.orgInfo.validTime}" pattern="yyyy年MM月dd日"/></p>
              <p><span class="text3">所属区域：</span>${supplier.orgInfo.distinctName}</p>
            </div>
            <div class="scoresbtn">
              <input type="button" style="cursor:hand" class="btn" onclick="common.addFavorites('${supplier.orgInfo.objId }','${supplier.orgInfo.orgName}','02');return false;" title="收藏此供应商"/>
              <input type="button" style="cursor:hand" class="btn1" onclick="OrgShopIndex.addNote('${supplier.orgInfo.objId }');return false;"  title="给此供应商留言"/>
            </div>
          </div>
        </div>
        <!--企业基本信息 结束-->
        <!--产品类别信息 开始-->
        <div class="Ent ">
          <div class="title martop"><h2 class="marginlf">产品类别信息</h2></div>
          <div class="categories">
          <c:if test="${goodsClassList != null && fn:length(goodsClassList) > 0}">
          <ul>
            <c:forEach var="gc" items="${goodsClassList}">
            <li>
              <h3><a href="javascript:void(0);" onclick="OrgShopIndex.showGoodsInfoView('${gc.goodsClassCode}');return false;">${gc.goodsClassName}</a></h3>
              <c:if test="${gc.children != null && fn:length(gc.children) > 0}">
              <dl>
                <c:forEach var="gc2" items="${gc.children}">
                <dt><a href="javascript:void(0);" onclick="OrgShopIndex.showGoodsInfoView('${gc2.goodsClassCode}');return false;" title="${gc2.goodsClassName}">${gc2.goodsClassName}</a></dt>
                  <c:if test="${gc2.children != null && fn:length(gc2.children) > 0}">
                  <dd>
                    <c:forEach var="gc3" items="${gc2.children}">
                      <a href="javascript:void(0);" onclick="OrgShopIndex.showGoodsInfoView('${gc3.goodsClassCode}');return false;" title="${gc3.goodsClassName}">${gc3.goodsClassName}</a>
                    </c:forEach>
                  </dd>
                  </c:if>
                </c:forEach>
              </dl>
              </c:if>
            </li>
            </c:forEach>
          </ul>
          </c:if>
          </div>
        </div>
        <!--产品类别信息 结束-->
        <!--联系方式 开始-->
        <c:if test="${contact != null}">
        <div class="Ent ">
          <div class="title martop">
          	<h2 class="marginlf">联系方式</h2>
          	<!--a href="#" title="更多联系方式" class="more" onclick="OrgShopIndex.showView(this,'orgshopContactView')">更多</a-->
          </div>
          <div class="lefcon1">
		      <div class="scorestext">
		        <p><span class="text4">联&nbsp;系&nbsp;人&nbsp;：</span>${contact.name}</p>
		        <p><span class="text4">联系电话：</span>${contact.telOffice}</p>
		        <p><span class="text4">电子邮箱：</span>${contact.email}</p>
		        <p><span class="text4">传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真：</span>${contact.fax} </p>
		      </div>
          </div>
        </div>
        </c:if>
        <!--联系方式 结束-->
      </div>
      <!-- 左侧信息 结束-->
 
      <!-- 右侧信息 开始-->
      <div class="boxright" id="boxRightContent">
        <!--公司介绍  开始-->
        <div class="introduction">
	        <div class="titleright">
	          <h2 class="marginright">公司介绍</h2>
	          <div class="more"><a href="javascript:void(0);" title="公司详细介绍 " onclick="OrgShopIndex.showView(this,'orgshopIntroductionView')">更多</a></div>
	        </div>
	        <div class="conrig">
	          <img class="orgImg" src="<%=request.getContextPath()%>/view/resource/skin/orgshop/img/figure.jpg" height="103" width="96" class="marlf"/>
	          <p class="details">
	            <c:choose>
  					<c:when test="${fn:length(supplier.orgInfo.descCn) > 180}">${fn:substring(supplier.orgInfo.descCn,0,180)}……</c:when>
  					<c:otherwise>${supplier.orgInfo.descCn}</c:otherwise>
  				</c:choose>
	          </p>
	        </div>
        </div>
        <!--公司介绍  结束-->
        <!--产品橱窗 开始-->
        <div class="introduction">
          <div class="titleright martop">
            <h2 class="marginright">产品橱窗</h2>
            <div class="more"><a href="javascript:void(0);" title="更多产品信息" onclick="OrgShopIndex.showView(this,'orgshopGoodsInfoView')">更多</a></div>
          </div>
          <div class="conrig">
            <c:forEach var="goods" items="${goodsList}">
            <div class="figure"> 
              <div class="photo"><img class="goodsImg" src="<%=request.getContextPath()%>/AttachmentController.do?method=showImg&objId=${goods[1]}" height="95" width="142" /></div>
              <p class="name"><a title="${goods[2]}" href="javascript:void(0);" onclick="OrgShopIndex.showGoodsDetail('${goods[0]}');">${goods[2]}</a></p>
            </div>
            </c:forEach>
          </div>
        </div>
        <!--产品橱窗 结束-->

        <div class="introduction">
          <div class="titleright  martop">
            <h2 class="marginright">企业评价</h2>
            <div class="more"><a href="javascript:void(0);" title="更多企业评价" onclick="OrgShopIndex.showView(this,'orgshopEvaluationView')">更多</a></div>
          </div>
          <div class="conrig" style="padding-left: 8px;">
            <c:forEach var="evaluate" items="${evaluateList}">
            <div class="list">
				<ul>
					<li><label>所属项目：</label><span>${evaluate.projectName}</span></li>
					<li>
						<label>评价级别：</label>
						<span>
							<c:set var="evaluateLeval" value="${evaluate.leval}e" />
							<c:choose>
								<c:when test="${evaluateLeval == '0e'}">好评</c:when>
								<c:when test="${evaluateLeval == '1e'}">中评</c:when>
								<c:otherwise>差评</c:otherwise>
							</c:choose>
						</span>
					</li>
					<li><label>评价总分：</label><span>${evaluate.summaryScore }分</span></li>
					<li><label>评价描述：</label><span>${evaluate.remark}</span></li>
					<li style="text-align: right;">
						评价人：
							<c:if var="isAonymous" test="${evaluate.isAonymous == '1'}">匿名</c:if>
							<c:if test="${!isAonymous}">${evaluate.rateOrg.orgName}</c:if>&nbsp;&nbsp;&nbsp;
						评价时间：<fmt:formatDate value="${evaluate.evalTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</li>
				</ul>
			</div>
            </c:forEach>
          </div>
        </div>

      </div>
      <!-- 右侧信息 结束-->
    </div>
    <!-- 内容部分 结束-->
    <!--底部 开始-->
    <div class="footer">
        <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
    </div>
    <!--底部 结束-->
  </div>
  </div>
</div>

<div id="extraDiv">
  <!-- 页面参数 -->
  <input type="hidden" id="initPath" value="<%=request.getContextPath()%>" />
  <input type="hidden" id="returnUrl" value="" />
  <input type="hidden" id="roleType" value="<%=request.getAttribute("roleStr")%>" />
  <input type="hidden" id="currentTab" value="${currentTab}"/>
</div>   
</body>
</html>