<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>青岛市政府采购网</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta content="${chnl.name}" name="keywords" />
<meta content="${chnl.name}" name="description" />
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
<link href="${resbase}/skin/css/index.css" type="text/css" rel="stylesheet" />

<script language="javascript" type="text/javascript">
//临设置选中菜单样式
$(function(){
	$("#menuList a[id=chnl_${chnl.objId}]").addClass("homePage");
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
    <#list menu as m>
      <li><a id="chnl_${m.objId}" href="${m.url!}" class="">${m.name!}</a></li>
	</#list>
    </ul>
    <div class="notice"> <a href="#">电子化政府采购系统相关角色设备配置建议化政府采购系统</a></div>
    <form action="">
      <span class="search">
      <input name="search" type="text"  id="search"/>
      <button><span>搜索</span></button>
      </span>
    </form>
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
        <ul>
          <li><a href="#" class="selected">招标公告</a></li>
          <li><a href="#">中标公</a></li>
          <li><a href="#">成交公告</a></li>
          <li><a href="#">更正公告</a></li>
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
   <!--     <ol>
          <li><span class="highLight">当前位置：</span></li>
          <li><a href="index.htm">网站首页</a></li>
          <li><a href="procurementNotice.htm">采购公告</a></li>
          <li><a href="news.htm">招标公告</a></li>
        </ol>-->
       <span class="highLight">当前位置：</span> <a href="index.htm">网站首页</a>|<a href="procurementNotice.htm">采购公告</a>|<a href="news.htm">招标公告</a>
      </div>
      <table>
        <tr>
          <th>标题</th>
          <th class="date">时间</th>
        </tr>
        <tr>
          <td><a href="news.htm">青岛市对口援建四川省松潘县学校计算机等设备采购项目（项目编号：AHZB2010-019-G）</a></td>
          <td>2010-3-17</td>
        </tr>
        <tr>
          <td><a href="#">青岛市省直单位2010年第2期协议供货项目招标公告（项目编号:AH-H2010033）</a></td>
          <td>2010-3-17</td>
        </tr>
        <tr>
          <td><a href="#">青岛市政府采购中心询价采购函（项目编号：AH-G2010045）---实验仪器、实验室维修改造</a></td>
          <td>2010-3-16</td>
        </tr>
        <tr>
          <td><a href="#">青岛市政府采购中心招标公告(项目编号 ：AH-H2010037)</a></td>
          <td>2010-3-16</td>
        </tr>
        <tr>
          <td><a href="#">青岛市宁国市五个财政所为民服务大厅建设工程施工[招标公告]</a></td>
          <td>2010-3-16</td>
        </tr>
        <tr>
          <td><a href="#">青岛市政府采购中心询价采购函(项目编号：AH-H2009662/AH-H2010041)</a></td>
          <td>2010-3-16</td>
        </tr>
        <tr>
          <td><a href="#">青岛市对口援建四川省松潘县学校计算机等设备采购项目（项目编号：AHZB2010-019-G）</a></td>
          <td>2010-3-16</td>
        </tr>
        <tr>
          <td><a href="#">青岛市省直单位2010年第2期协议供货项目招标公告（项目编号:AH-H2010033）</a></td>
          <td>2010-3-16</td>
        </tr>
        <tr>
          <td><a href="#">青岛市政府采购中心询价采购函（项目编号：AH-G2010045）---实验仪器、实验室维修改造</a></td>
          <td>2010-3-16</td>
        </tr>
        <tr>
          <td><a href="#">青岛市政府采购中心招标公告(项目编号 ：AH-H2010037)</a></td>
          <td>2010-3-16</td>
        </tr>
        <tr>
          <td><a href="#">青岛市宁国市五个财政所为民服务大厅建设工程施工[招标公告]</a></td>
          <td>2010-3-16</td>
        </tr>
        <tr>
          <td><a href="#">青岛市政府采购中心询价采购函(项目编号：AH-H2009662/AH-H2010041)</a></td>
          <td>2010-3-15</td>
        </tr>
        <tr>
          <td><a href="#">青岛市政府采购中心招标公告(项目编号 ：AH-H2010037)</a></td>
          <td>2010-3-15</td>
        </tr>
        <tr>
          <td><a href="#">青岛市宁国市五个财政所为民服务大厅建设工程施工[招标公告]</a></td>
          <td>2010-3-15</td>
        </tr>
        <tr>
          <td><a href="#">青岛市政府采购中心询价采购函(项目编号：AH-H2009662/AH-H2010041)</a></td>
          <td>2010-3-15</td>
        </tr>
      </table>
      <div class="flipPage">页次：1/74 每页20条 文章120 <a href="#">首页</a> <a href="#">上一页</a> <a href="#">下一页</a> <a href="#">尾页</a>跳转到 
        <input class="small" size="10" /></div>
      
    </div>
    <!--主内容 结束-->
   
  </div>
  <!--内容容器 结束-->
  <!--网站信息 开始，显示系统版权、技术支持、备案号、补充导航等-->
  <div id="webInfo">
    <!--辅助链接 开始-->
    <a href="#">关于我们</a> ｜ <a href="#">法律申明</a> ｜ <a href="#">网站地图</a> ｜ <a href="#">在线反馈</a> ｜ <a href="#">使用帮助</a>
    <!--辅助链接 结束-->
    <!--网站信息 开始-->
    <p>Copyright @ 2007 All Rights Reserved 青岛市财政厅   技术支持：<a href="http://www.gpcsoft.com">珠海政采软件技术有限公司</a></p>
    <p>地址：青岛市合肥市辖区黄山路25号  邮编：450008  邮箱：<a href="#">henancgc@126.com</a> 备案序号：豫ICP备09005258号</p>
    <!--网站信息 结束-->
  </div>
  <!--网站信息 结束-->
</div>
<!--网站容器 结束-->
</body>
</html>
