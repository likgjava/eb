<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>阳光易购采购交易平台--帮助中心</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/web.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin01/css/common.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin01/css/listDetail.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin01/css/help.css"/>
<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>

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
    <div class="wrap">
      <div class="twoCol_fist2">
        <div class="fastTools">
          <h3>自助服务</h3>
          <ul>
            <li>直接订购</li>
            <li>电子议价</li>
            <li>电子竞价</li>
          </ul>
        </div>
        <!--商品分类 开始-->
        <div class="categories">
          <h2>帮助中心</h2>
          <ul>
          	<#if customer?? && customer?size != 0>
			  <li>
				<h3><a href="javascript:void(0)"  class="icon1"><#if customer[0].parent??>${customer[0].parent.name}</#if></a></h3>
				<ul class="subnav">
				<#list customer as l>
					<#if l.display>
						<li class="" id="${l.objId}"><a href="javascript:void(0)" class="cmsHref_self" id="${l.url!}" >${l.name}</a></li>
					</#if>
				</#list>
				</ul>
			  </li>
			</#if>
          </ul>
        </div>
        <!--商品分类 结束-->
        <div class="question_search">
          <h3>搜索 <strong>输入您的问题</strong></h3>
          <input type="text" id="searchValue"/>
          <button id="searchBtn">搜索</button>
        </div>
      </div>
      <div class="index3pa" id="contentMain">
        <div class="helpBase help_gou">
          <h3>购，让采购更公平</h3>
          <p>直接订购，简单快捷</p>
          <ol>
            <li><h4><strong>1.</strong>挑选商品</h4></li>
            <li><h4><strong>2.</strong>直接订购</h4></li>
            <li><h4><strong>3.</strong>提交订单</h4></li>
            <li><h4><strong>4.</strong>供应商确认订单</h4></li>
            <li><h4><strong>5.</strong>起草合同</h4></li>
            <li><h4><strong>6.</strong>对方确认订单</h4></li>
            <li class="end"><h4><strong>7.</strong>交易完成</h4></li>
          </ol>
        </div>
        <div class="helpBase help_yi">
          <h3>议，让价格更优惠</h3>
          <p>在线议价，让价格更优惠</p>
          <ol>
            <li><h4><strong>1.</strong>挑选商品</h4></li>
            <li><h4><strong>2.</strong>发起议价</h4></li>
            <li><h4><strong>3.</strong>创建议价项目</h4></li>
            <li><h4><strong>4.</strong>议价</h4></li>
            <li><h4><strong>5.</strong>确定结果</h4></li>
            <li><h4><strong>6.</strong>生成订单</h4></li>
            <li><h4><strong>7.</strong>供应商确认订单</h4></li>
            <li><h4><strong>8.</strong>起草合同</h4></li>
            <li><h4><strong>9.</strong>对方确认订单</h4></li>
            <li class="end"><h4><strong>10.</strong>交易完成</h4></li>
          </ol>
        </div>
        <div class="helpBase help_jing">
          <h3>竞，让利一步到位</h3>
          <p>电子竞价，让利一步到位</p>
          <ol>
            <li><h4><strong>1.</strong>挑选商品</h4></li>
            <li><h4><strong>2.</strong>发起竞价</h4></li>
            <li><h4><strong>3.</strong>创建竞价项目</h4></li>
            <li><h4><strong>4.</strong>供应商报名</h4></li>
            <li><h4><strong>5.</strong>竞价</h4></li>
            <li><h4><strong>6.</strong>确定结果</h4></li>
            <li><h4><strong>7.</strong>生成订单</h4></li>
            <li><h4><strong>8.</strong>供应商确认订单</h4></li>
            <li><h4><strong>9.</strong>起草合同</h4></li>
            <li><h4><strong>10.</strong>对方确认合同</h4></li>
            <li class="end"><h4><strong>11.</strong>交易完成</h4></li>
          </ol>
        </div>
    
        <div class="layout helpNews">
        <h3>常见问题</h3>
        <ul>
        	<#list normalPro as l>
					<li><a href="javascript:void(0)" id="${l.url!}" class="cmsHref_blank" title="${l.title!}">${l.getTit(25)}</a><span class="date">${(l.date)!?string("MM-dd")}</span></li>
			</#list>
        </ul>
        <span class="more"><a class="right" title="更多" href="#">更多</a></span> </div>
      </div>
    </div>
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

<script>
/*
 * 小额交易平台.帮助中心
 */
var HelpIndex = {};

$(document).ready(function(){

	//首页选中样式
	changeTabsCss('goToIndex');
	
	//查询按钮
	$('#searchBtn').click(function() {
		$("#contentMain").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&searchKey='+strIgnore($('#searchValue').val())+'&pageSize=20&searchResultForm=search_news_body_result.ftl');
	});
});
</script>