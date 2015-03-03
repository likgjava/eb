<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>电子招标 - 阳光易购电子采购与招标平台</title>
<meta name="description" content="是全国领先的电子招标采购平台，以强大的线下服务和招投标资源引领招投标行业走向电子商务在线招投标交易流程，精确匹配等一站式招投标信息资源是成千上万企业进行网络贸易首选服务、网络营销的最佳渠道。" />
<meta name="keywords" content="电子招标，投标，开标，评标，招投标动态，招投标专家，招投标案例，电子标书，项目信息，招标代理，投标代理"/>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>
<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/showbulletin/show_bidding_index.js"></script>
</head>
<body>
<!--页面容器 开始-->
<div id="container">
  <input type="hidden" id="currentOrgId" value="${currentOrgId }" />
  <!--头部容器 开始-->
  <div class="header">
    <%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
  </div>
  <!--CSS-->
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/zbcg.css"/>
  <!--头部容器 结束--> 
  <!--主要内容容器 开始-->
  <div class="page" id="sysContent">
    <!--重点推荐开始-->
    <div class="gridBox">
      <div class='grid16_11'>
        <div class="recommendArea headlinesArea" id="recommendBiddingProjectDiv"></div>
      </div>
      <!--如何进行“电子招标”开始-->
      <div class='grid16_5 omega'>
        <div class="advantage">
          <h2>如何进行“电子招标”？</h2>
          <ul>
            <li>1、招标单位提交项目需求委托</li>
            <li>2、项目经理立项和发布公告</li>
            <li>3、投标单位在线报名，并缴纳标书费</li>
            <li>4、投标单位远程投标</li>
            <li>5、电子开标，开标过程现场直播</li>
            <li>6、发布中标公告</li>
            <li>7、合同履约</li>
          </ul>
        </div>
      </div>
    </div>
    <!--如何进行“电子招标”结束--> 
    <!--重点推荐结束--> 
    <!--工程开始-->
    <div class="gridBox">
      <div class='grid16_11'>
        <%@ include file="/view/agreement/showbulletin/project_list_engineering.jsp" %>
      </div>
      <!--开标直播室开始-->
      <div class='grid16_5 omega'>
        <%@ include file="/view/agreement/showbulletin/bidding_live_hall.jsp" %>
      </div>
      <!--开标直播室结束--> 
    </div>
    <!--工程结束--> 
    <!--货物开始-->
    <div class="gridBox">
      <div class='grid16_11'>
      	<%@ include file="/view/agreement/showbulletin/project_list_goods.jsp" %>
      </div>
      <!--最近成交项目开始-->
      <div class='grid16_5 omega'>
        <%@ include file="/view/agreement/showbulletin/project_list_bidding_dealed.jsp" %>
      </div>
      <!--最近成交项目结束--> 
    </div>
    <!--货物结束--> 
    <!--服务开始-->
    <div class="gridBox">
      <div class='grid16_11'>
        <%@ include file="/view/agreement/showbulletin/project_list_service.jsp" %>
      </div>
      <!--重点招标单位开始-->
      <div class='grid16_5 omega'>
        <%@ include file="/view/agreement/showbulletin/buyer_list_important.jsp" %>
      </div>
      <!--重点招标单位结束--> 
    </div>
    <!--服务结束--> 
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
</body>
</html>
