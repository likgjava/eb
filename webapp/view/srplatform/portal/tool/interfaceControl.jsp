<%@ page pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/portal/tool/interfaceControl.js"></script>
<!--本文件为服务器调用页面,请于skin.js修改路径-->
<h5>更换主题</h5>
<ul class="sysOptionsIcon" id="changeThemeZone"><!--换肤列表--></ul>
<h5>屏幕设定</h5>
<ul class="sysOptionsIcon">
  <li id="screenStandard" title="切换到标准结构,可使用快捷键 F8"><span>标准结构</span></li>
  <li id="screenFull" title="切换到全屏显示,可使用快捷键 F8"><span>全屏显示</span></li>
  <li id="navSubHide" title="隐藏导航,可使用快捷键 F9"><span>隐藏导航</span></li>
  <li id="navSubShow" title="显示导航,可使用快捷键 F9"><span>显示导航</span></li>
</ul>
<h5>字体设定</h5>
<ul class="sysOptionsIcon">
  <li id="fontBase" title="使用标准字体"><span>标准文字</span></li>
  <li id="fontLarge" title="使用大号字体"><span>放大文字</span></li>
  <!--<li id="changeMenu" title="菜单调整"><span>菜单调整</span></li>-->
</ul>
<div class="conOperation">
  <button id="closeEpsDialogBtn"><span>确定</span></button>
</div>

