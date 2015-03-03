<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>供应商 - 阳光易购电子采购与招标平台</title>
<meta name="description" content="拥有海量的公司信息，每天有大量公司前来注册。为您提供详细的公司介绍、联系人资料、联系方式等公司信息。加入阳光易购电子采购平台，您立即可以成为其中一员！查找公司信息产品、需求以及提高您公司的知名度，为您提供全方位的满意服务。" />
<meta name="keywords" content="交通运输，机械电子电器，能源化工，冶金矿产原材料，建筑装饰，电气机械及器材，仪器仪表，办公用品，网络通讯，数码，房地产，水利桥梁，环保，农林牧渔，轻工纺织食品，出版印刷，商业服务"/>

<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/bizplatform/suppliers/showsupplier/show_supplier_index.js"></script>
</head>
<body>
<!--页面容器 开始-->
<div id="container"> 
  <!--头部容器 开始-->
  <div class="header">
    <%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
  </div>
  <!--CSS-->
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/supplier.css" />
  <!--头部容器 结束--> 
  
  <!--主要内容容器 开始-->
  <div id="sysContent" class="page">
    <!--登录容器开始-->
    <div class="gridBox"> 
      <!--滚动图片开始-->
      <div class='grid16_11'>
        <div class="supplierFocus">
          <%@ include file="/view/staticpags/adverhtml/adver_supplier_center.jsp" %>
        </div>
      </div>
      <!--滚动图片结束-->
      <!--选择阳光易购原因开始-->
      <div class="grid16_5 omega"> 
        <div class="advantage">
          <h2>海量供应商信息、资质、案例查询</h2>
          <ul>
            <li>1.加入行业供应商库，供采购人查询</li>
            <li>2.企业资质、案例、产品信息发布</li>
            <li>3.快速参加竞价、议价项目</li>
          </ul>
        </div>
      </div>
      <!--选择阳光易购原因结束-->
    </div>
    <!--登录容器结束-->
    
    <!--推荐供应商容器开始-->
    <div class="gridBox"> 
      <!--推荐供应商开始-->
      <div class='grid16_11'>
        <%@ include file="/view/bizplatform/suppliers/showsupplier/supplier_recommend_list_index.jsp" %>
      </div>
      <!--推荐供应商结束--> 
      <!--供应商最新资讯开始-->
      <div class='grid16_5 omega'>
        <%@ include file="/view/staticpags/load/supplierzx.jsp" %>
      </div>
      <!--供应商最新资讯结束--> 
    </div>
    <!--推荐供应商容器结束-->
    
    <!--行业分类容器开始-->
    <div class="gridBox"> 
      <!--行业分类开始-->
      <div class='grid16_11'>
        <%@ include file="/view/bizplatform/suppliers/showsupplier/pur_category_list.jsp" %>
      </div>
      <!--行业分类结束--> 
      <!--最新加盟供应商开始-->
      <div class='grid16_5 omega'>
        <%@ include file="/view/bizplatform/suppliers/showsupplier/supplier_new_list.jsp" %>
      </div>
      <!--最新加盟供应商结束--> 
    </div>
    <!--行业分类容器结束-->
    
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