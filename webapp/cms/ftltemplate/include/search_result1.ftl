<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>青岛市政府采购网</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta content="" name="keywords" />
<meta content="" name="description" />
<script type="text/javascript" src="${resbase}/scripts/jquery.js">//jQuery JavaScript Library v1.3.2</script>
<script type="text/javascript" src="${resbase}/scripts/jqueryUI.js">//jQuery UI 插件</script>
<script type="text/javascript" src="${resbase}/scripts/home.js"></script>
<script type="text/javascript" src="${resbase}/scripts/tabSet.js"></script>
<script type="text/javascript" src="${resbase}/plug-in/jqueryTabs/jquery.tabs.min.js">//Tabs插件</script>
<script type="text/javascript" src="${resbase}/plug-in/dateandtime/dateandtime.js">//时间选择插件</script>
<script type="text/javascript" src="${resbase}/scripts/listTree.js">//下拉树</script>
<script type="text/javascript" src="${resbase}/plug-in/dhtmlxTree/sources/dhtmlxcommon.js"><!--树控件--></script>
<script type="text/javascript" src="${resbase}/plug-in/dhtmlxTree/sources/dhtmlxtree.js"><!--树控件--></script>
<!--jQuery JavaScript Library v1.3.2-->
<link href="${resbase}/skin/css/mainHome.css" type="text/css" rel="stylesheet" />
<link href="${resbase}/skin/css/portalFrame.css" type="text/css" rel="stylesheet" />

<script language="javascript" type="text/javascript">
//临设置选中菜单样式
$(function(){
	$("#foot").load("${load}/foot.html");
	$("#leftCggg").load("${load}/left.html");
});
</script>
</head>
<body>
<!--装饰扩展用容器 开始，通过引用extraDiv.htm实现页面装饰扩展。-->
<div id="extraDiv">
  <div id="dateTime" class="date"></div>
  <ul>
    <li><a href="#" class="number">会员专区</a></li>
    <li><a href="#" class="map">网站地图</a></li>
    <li><a href="#" class="home">设为首页</a></li>
    <li><a href="#" class="collect">收藏本站</a></li>
  </ul>
</div>
<!--装饰扩展用容器 结束-->
<!--网站容器 开始，用于包含页面内容HTML代码。-->
<div id="webContainer">
  <!--标识容器 开始，用于放置系统名称、宣传口号等。-->
  <div id="webBranding">
    <h1><span>青岛市电子化政府采购网</span></h1>
  </div>
  <!--标识容器 结束-->
  <!--主菜单容器 开始，用于放置系统主导航菜单。-->
  <div id="navMain">
     <ul id="menuList">
     <@f.gpcsoft list=menu ftl="menu"/>
    </ul>
   
    <div class="notice"> <a href="#">电子化政府采购系统相关角色设备配置建议化政府采购系统</a></div>
    
 <div class="search">
	<#include "/include/search_form.ftl"/>
    
	</div>
  </div>
  <!--主菜单容器 结束-->
  <!--登录信息 开始，用于放置用户登录信息等。-->
  <div id="loginInfo"> </div>
  <!--登录信息 结束-->
  <!--工具容器 开始，用于放置系统框架工具，如退出、刷新、返回等。使用"ul"无序列表标签。-->
  <div id="webTools"></div>
  <!--工具容器 结束-->
  <!--内容容器 开始，用于放置系统内容-->
  <div id="webContent">
    <!--辅内容 开始，显示系统辅助内容，如导航、信息关联等-->
    <div id="contentSub">
    <!--采购公告 开始-->
      <div class="secNav">
        <h4><span>采购公告</span></h4>
        <ul id="leftCggg">
         
        </ul>
      </div>
      <!--采购公告 结束-->
      <!--搜索栏 开始-->
      <!--<div class="secNav">
        <h4><span>信息搜索</span></h4>
       
      </div>-->
      <!--搜索栏 结束-->
    </div>
    <!--辅内容 结束-->
    <!--主内容 开始，显示系统功能内容-->
    <div id="contentMain">
      <div class="navCurrent">
       <span class="highLight">当前位置：</span><@f.position/>
      </div>
     <table>
        <tr>
          <th>标题</th>
          <th class="date">时间</th>
        </tr>
        <#if searList?size!=0>
         <#list searList as l>
        <tr>
          <td><a href="${l.url!}" title="${l.title!}">${l.getTit(20)!}</a></td>
          <td>${l.date}</td>
        </tr>
	       </#list>
	       <#else>
	       <tr>
          <td colSpan="2">没有检索到记录！</td>
        </tr>
	       </#if>
      </table>
      <div class="flipPage">
      <@f.searchPage/>
      </div>
    </div>
    <!--主内容 结束-->
   
  </div>
  <!--内容容器 结束-->
  <!--网站信息 开始，显示系统版权、技术支持、备案号、补充导航等-->
<div id="foot"></div>
  <!--网站信息 结束-->
</div>
<!--网站容器 结束-->
</body>
</html>

