<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品库 - 阳光易购电子采购与招标平台</title>
<meta name="description" content="集合数十万个知名品牌及商品，为您提供专业的公司产品介绍、产品图片、最新报价等信息，专业销售和提供IT数码，家用电器，食品保健，办公用品，建筑建材等商品的信息资讯，诚招全国各地供应商合作，为完善商品库提供产品和技术支持，致力于打造国内最大采购行业电子商务交易平台。" />
<meta name="keywords" content="数码，电脑，包装，纸业，机械行业设备，照明工业，电子元器件，安全防护，办公文教，电工电气，纺织、皮革，服装，服饰，机械行业设备，五金，工具，化工，橡塑，环保，仪器仪表，家居用品，家用电器，建筑，建材，交通运输，礼品，工艺品，能源，农业，食品、饮料，通信产品，玩具，冶金矿产，医药保健，印刷，运动休闲，商务服务，项目合作"/>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/goods/showgoods/show_goods_index.js"></script>
</head>
<body>
<!--页面容器 开始-->
<div id="container"> 
  <!--头部容器 开始-->
  <div class="header">
    <%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
  </div>
  <!--CSS-->
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/goods.css" />
  <!--头部容器 结束-->
  
  <!--主要内容容器 开始-->
  <div class="page" id="sysContent">
    <!--登录容器开始-->
    <div class="gridBox"> 
      <!--商品分类 开始-->
      <div class="grid16_3">
        <%@ include file="/view/goods/showgoods/goods_class_list.jsp" %>
      </div>
      <!--商品分类 结束-->
      <div class='grid16_9'>
        <!--滚动图片开始-->
        <div class="goodsFocus">
          <%@ include file="/view/staticpags/adverhtml/adver_goods_center.jsp" %>
        </div>
        <!--滚动图片结束-->
        <!--推荐商品开始-->
        <div class="cols reProducts">
          <%@ include file="/view/goods/showgoods/recommend_goods_list_index.jsp" %>
        </div>
        <!--推荐商品结束-->
      </div>
      <div class="grid16_4 omega"> 
        <!--商品资讯 开始-->
        <div class="cols">
          <%@ include file="/view/staticpags/load/goodszx.jsp" %>
        </div>
        <!--商品资讯 结束-->
        <!--热门品牌 开始-->
        <div class="cols hotBrand">
          <%@ include file="/view/goods/showgoods/recommend_brand_list_index.jsp" %>
        </div>
        <!--热门品牌 结束-->
      </div>
    </div>
    <!--登录容器结束-->
    
    <!-- 按商品分类展示商品  开始 -->
    <div id="goodsListByClass"></div>
    <!-- 按商品分类展示商品  结束 -->
    
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