<%@ page pageEncoding="UTF-8"%><%@page import="java.util.List,com.gpcsoft.bizplatform.base.application.domain.HotTags"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>阳光易购-中国权威的电子采购与招标第三方公共服务平台</title>
<meta name="description" content="阳光易购（ebid360.com）是中国最权威的电子采购与招标服务平台，拥有上百万的商家、上千万的商品及专家资源，具备方便、快捷、安全的在线电子采购系统，竭诚为客户提供专业的招标与采购、B2C网上购物及专业的技术咨询服务。" />
<meta name="keywords" content="阳光易购，采购，电子采购，竞价采购，采购代理，招标，电子招标，招标公告，招标代理，投标代理，求购信息，供应信息，采购单位，供应商，招投标专家，采购专家，精品商城，团购，b2b，电子商务，行业资讯，商务服务"/>
<!-- bffZa3s-4QsAPUGz6JRHhj1BucY  -->
<% if(request.getAttribute("ready")==null){%>
	<script>document.location.href="<%=request.getContextPath()%>/IndexViewController.do?method=index"</script>
<%}%>
<c:import url="/view/srplatform/common/init.jsp"><c:param name="isLastLoadPage">true</c:param></c:import>
<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/portal/index.js"></script>
</head>

<body>
<!--浏览器检测 开始-->
<%@ include file="/view/srplatform/portal/include/check_browser_ie6.jsp" %>
<!--浏览器检测 结束-->
<!--页面容器 开始-->
<div id="container"> 
  <!--头部容器 开始-->
  <div class="header">
    <%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
  </div>
  <!--CSS-->
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/index.css" />
  <!--头部容器 结束-->
 
  <!--主要内容容器 开始-->
  <div id="sysContent" class="page"> 
    <!--资讯、焦点、最新竞价 开始-->
    <div class="gridBox">
      <!--资讯开始-->
      <div class='grid16_4'>
        <div class="cols">
          <h2>通知通告</h2>
          <@f.gpcsoft list=notice ftl="notice" titLen=11 dataFormat='MM-dd'/>
        </div>
      </div>
      <!--资讯结束-->
      <!-- 跑马灯  开始 -->
      <div class='grid16_8'>
        <div class="focus">
          <%@ include file="/view/staticpags/adverhtml/adver_index_center.jsp" %>
        </div>
      </div>
      <!-- 跑马灯  结束 -->
      <div class='grid16_4 omega'>
        <!--重磅推荐 开始-->
        <div class="recommendArea" id="recommendProjectIndexDiv"></div>
        <!--重磅推荐 结束-->
        <!--登录、注册  开始-->
        <div id="loginRegDiv">
          <%@ include file="/view/srplatform/portal/include/login_reg_div.jsp" %>
        </div>
        <!--登录、注册  结束-->
      </div>
    </div>
    <!--资讯、焦点、最新竞价 结束-->
    
    <!--名企专区、广告位 开始-->
    <div class="gridBox">
      <div class='grid16_12'>
        <%@ include file="/view/srplatform/portal/include/well_known_company.jsp" %>
      </div>
      <!--首页右1广告位开始-->
      <div class='grid16_4 omega'><%@ include file="/view/staticpags/adverhtml/adver_index_right1.jsp" %></div>
      <!--首页右1广告位结束-->
    </div>
    <!--名企专区、广告位 结束-->
    
    <!--招标、采购、精品团购 开始-->
    <div class="gridBox">
      <!--招标、采购项目  开始-->
        <%@ include file="/view/srplatform/portal/include/project_list_index.jsp" %>
      <!--招标、采购项目  结束-->
      <!-- 精品团购  开始 -->
      <div class='grid16_4 omega'>
        <%@ include file="/view/srplatform/portal/include/group_buying_list_index.jsp" %>
      </div>
      <!-- 精品团购  结束 -->
    </div>
    <!--招标、采购、精品团购 结束-->
    
    <!--首页中1、右2广告位开始-->
    <div class="gridBox">
      <div class="ad_b_90 float_l"><%@ include file="/view/staticpags/adverhtml/adver_index_middle1.jsp" %></div>
      <div class="ad_s_90 float_r"><%@ include file="/view/staticpags/adverhtml/adver_index_right2.jsp" %></div>
    </div>
    <!--首页中1、右2广告位结束-->
    
    <!--商品展示 开始-->
    <div class="gridBox">
      <div class="cols goodsShowBox">
        <h2>商品展示</h2>
        <div class='brandBox'>
          <!--首页左部广告位开始-->
            <div class="brandAdv"><%@ include file="/view/staticpags/adverhtml/adver_index_left.jsp" %></div>
          <!--首页左部广告位结束-->
          <!-- 推荐品牌  开始 -->
            <%@ include file="/view/srplatform/portal/include/recommend_brand_list.jsp" %>
          <!-- 推荐品牌  结束 -->
        </div>
        <!-- 推荐商品  开始 -->
        <%@ include file="/view/srplatform/portal/include/recommend_goods_list.jsp" %>
        <!-- 推荐商品  结束 -->
      </div>
    </div>
    <!--商品展示 结束-->
    
    <!--供应商、采购单位 开始-->
    <div class="gridBox">
      <div class="grid16_8">
          <%@ include file="/view/srplatform/portal/include/recommend_supplier_list.jsp" %>
      </div>
      <div class="grid16_8 omega">
          <%@ include file="/view/srplatform/portal/include/buyer_important_index_list.jsp" %>
      </div>
    </div>
    <!--供应商、采购单位 结束-->
    
    <!--首页中2、右3广告位开始-->
    <div class="gridBox">
      <div class="ad_b_90 float_l"><%@ include file="/view/staticpags/adverhtml/adver_index_middle2.jsp" %></div>
      <div class="ad_s_90 float_r"><%@ include file="/view/staticpags/adverhtml/adver_index_right3.jsp" %></div>
    </div>
    <!--首页中2、右3广告位结束-->
    
    <!--推荐专家、广告位 开始-->
    <div class="gridBox">
      <!-- 推荐专家  开始 -->
      <div class="grid16_12" id="recommendExp"></div>
      <!-- 推荐专家  结束 -->
      <!--首页商圈新闻开始-->
      <div class="grid16_4 omega">
        <div class="cols">
		<h2>商圈新闻</h2>
			<ul class="newsList">
				<@f.gpcsoft list=xinwen ftl="list3" titLen=9/>
			</ul>
		<div class="more"><a href="javascript:void(0);" id="${xinwen_index.url!}" class="cmsHref_blank right">更多</a></div>
		</div>
      </div>
      <!--首页首页商圈新闻结束-->
    </div>
    <!--推荐专家、广告位 结束-->
    
    <!--帮助信息 开始-->
    <div class="operationsGuide">
      <%@ include file="/view/srplatform/portal/include/help_leader_sys_info.jsp" %>
    </div>
    <!--帮助信息 结束--> 
  </div>
  <!--主要内容容器 结束--> 
  
  <!--底部容器 开始-->
  <div class="footer">
    <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
  </div>
  <!--底部容器 结束-->
  <!--在线客服开始-->
  <%@ include file="/view/srplatform/portal/include/online_customer_service.jsp" %>
  <!--在线客服结束-->
</div>
<!--页面容器 结束-->

<!--扩展用容器，用于与内容无关的装饰性扩展-->
<div id="extraDiv">
	<div id="extraDiv1"></div>
	<div id="extraDiv2"></div>
	<div id="extraDiv3"></div>
	<div id="extraDiv4"></div>
	<div id="extraDiv5"></div>
	<div id="extraDiv6"></div>
	<input type="hidden" id="viewUrl" value="<%=request.getParameter("viewUrl") %>" />
</div>
</body>
</html>
