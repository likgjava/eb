<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>电子采购 - 阳光易购电子采购与招标平台</title>
<meta name="description" content="为您提供免费发布采购需求、采购项目、最新求购信息，帮助您第一时间发布您的求购信息，为您搜集最合适的供应商，通过竞价议价的方式让您达到降低采购成本、提高采购产品质量的最终目标。" />
<meta name="keywords" content="采购，电子采购，竞价采购，政府采购，企业采购，求购信息，供应信息，商务服务，项目合作"/>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/showbulletin/show_bargain_index.js"></script>
</head>
<body>
<!--页面容器 开始-->
<div id="container"> 
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
      <div class="recommendArea headlinesArea" id="recommendBargainProjectDiv"></div>
    </div>
 
    <!--选择阳光易购原开始-->
    <div class='grid16_5 omega'>
      <div class="advantage">
          <h2>如何进行“电子采购”？</h2>
          <ul>
            <li>1、采购人创建竞价项目</li>
            <li>2、供应商在线报名</li>
            <li>3、供应商在线报价</li>
            <li>4、采购人在线监控报价</li>
            <li>5、采购人确定成交供应商</li>
            <li>6、采购人创建订单，签订合同</li>
            <li>7、发布结果公告</li>
          </ul>
        </div>
    </div>
    <!--选择阳光易购原因结束-->
  </div>
  <!--重点推荐结束-->
  
  <!--竞价、最近成交项目开始-->
  <div class="gridBox">
    <!--竞价开始-->
    <div class='grid16_11'>
      <%@ include file="/view/agreement/showbulletin/project_list_bargain.jsp" %>
    </div>
    <!--竞价  结束-->
    <!--最近成交项目开始-->
    <div class='grid16_5 omega'>
      <%@ include file="/view/agreement/showbulletin/project_list_bargain_dealed.jsp" %>
    </div>
    <!--最近成交项目结束-->
  </div>
  <!--竞价、最近成交项目结束-->
  
  <!--需求、热销商品开始-->
  <div class="gridBox">
    <!--需求开始-->
    <div class='grid16_11'>
      <%@ include file="/view/agreement/showbulletin/requirement_list_index.jsp" %>
    </div>
    <!--需求结束-->
    <!--热销商品开始-->
    <div class='grid16_5 omega'>
      <%@ include file="/view/agreement/showbulletin/recommend_goods_list.jsp" %>
    </div>
    <!--热销商品结束-->
  </div>
  <!--需求、热销商品结束-->

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
