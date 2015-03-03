<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>阳光易购采购交易平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!-- CSS -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin01/css/index.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin01/css/purchasingNeed.css"/>

<!-- JS -->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/agreement/showbulletin/show_bulletin_index.js'></script>

</head>
<body>
<!--浏览器检测 开始-->
<!--[if IE 6]>
<div class="userTips">
	<p>尊敬的用户，您好！您使用的浏览器版本过低，请升级到较高版本，以获得更好的安全性及操作、视觉体验。<a href="http://www.microsoft.com/china/windows/internet-explorer/worldwide-sites.aspx" title="点击下载IE8浏览器安装文件">下载IE8浏览器</a></p>
</div>
<![endif]-->
<noscript>
<div class="userTips">
  <p>尊敬的用户，您好！您使用的浏览器不支持或禁用JavaScript脚本。请使用支持JavaScript脚本的浏览器或启用浏览器JavaScript脚本功能。</p>
  <p>如有任何疑问或需要帮助，请<a href="http://www.gpcsoft.com" target="_blank" title="珠海政采软件技术有限公司 技术支持">与我们联系</a>。</p>
</div>
</noscript>
<!--浏览器检测 结束-->

<div id="container">
  <!--头部容器 开始-->
  <div class="header">
  	<%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
  </div>
  <!--头部容器 结束-->
  <!--主要内容 开始-->
  <div id="sysContent">
    <!--首栏内容展示 开始-->
    <div class="wrap">
      <div class="twoCol_fist2">
        <!--商品分类 开始-->
        <div class="categories">
          <h2>采购品目分类</h2>
          <ul>
            <c:forEach var="gc" items="${purCategoryList}">
            <li>
              <h3 <c:if test="${empty gc.children || fn:length(gc.children) == 0}">class="nochild"</c:if>><a href="javascript:void(0);" onclick="ShowBulletinIndex.byPurCategory('${gc.objId}','${gc.categoryCode}');">${gc.categoryName}</a></h3>
              <c:if test="${gc.children != null && fn:length(gc.children) > 0}">
	              <dl>
	                <c:forEach var="gc2" items="${gc.children}">
	                  <dt><a href="javascript:void(0);" onclick="ShowBulletinIndex.byPurCategory('${gc2.objId}','${gc2.categoryCode}');" title="${gc2.categoryName}">${gc2.categoryName}</a></dt>
	                  <dd>
	                    <c:forEach var="gc3" items="${gc2.children}">
	                      <a href="javascript:void(0);" onclick="ShowBulletinIndex.byPurCategory('${gc3.objId}','${gc3.categoryCode}');" title="${gc3.categoryName}">${gc3.categoryName}</a>
	                    </c:forEach>
	                  </dd>
	                </c:forEach>
	              </dl>
              </c:if>
            </li>
            </c:forEach>
          </ul>
        </div>
        <!--商品分类 结束-->
      </div>
      <div class="twoCol_fist8">
        <div class="wrap">
          <div class="twoCol_fist6">
            <%@ include file="/view/staticpags/adverhtml/adver_bulletin_center.jsp" %>
          </div>
	       <div class="twoCol_fist4">
	         <%@ include file="/view/staticpags/load/cgxq_count.jsp" %>
	         <div class="layout columns layoutGray" style="height:auto;">
	 			<%@ include file="/view/staticpags/load/cgxqzx.jsp" %>
	       	</div>
	   	  </div>
   	  </div>
      </div>
      <div style="padding-left:10px;overflow:hidden;">
      	  <!--重磅推荐 开始-->
	      <div class="wrap recommend twoCol">
	        <%@ include file="/view/agreement/showbulletin/recommend_bulletin_list.jsp"%>
	      </div>
	      <!--重磅推荐 结束-->
	      <!-- 采购需求、采购预告 开始 -->
		  <div class="wrap">
			<div class="twoCol_fist layout columns">
				<%@ include file="/view/agreement/showbulletin/project_requirement_list.jsp" %>
			</div>
	      	<div class="twoCol_last layout columns">
	           	<%@ include file="/view/agreement/showbulletin/project_pre_list.jsp" %> 	
	      	</div>
		  </div>
		  <!-- 采购需求、采购预告 结束 -->
		  <div class="wrapAdv">
	      	<%@ include file="/view/staticpags/adverhtml/adver_bulletin_left.jsp" %>
	      </div>
      </div>
    </div>
    <!--首栏内容展示 结束-->
    
    <!--竞价公告、结果公告 开始-->
    <div class="wrap">
		<div class="twoCol_fist layout columns">
			 <%@ include file="/view/agreement/showbulletin/preview_bulletin_list.jsp" %>
		</div>
      <div class="twoCol_last layout columns">
           <%@ include file="/view/agreement/showbulletin/project_bargained_list.jsp" %> 	
      </div>
    </div>
    <!--竞价公告、结果公告  结束-->
    <div class="wrapAdv">
    	<%@ include file="/view/staticpags/adverhtml/adver_bulletin_bottom.jsp" %>
    </div>
    <!--招标公告、中标公告  开始-->
    <div class="wrap">
		<div class="twoCol_fist layout columns">
			 <%@ include file="/view/agreement/showbulletin/bidding_bulletin_list.jsp" %>
		</div>
      <div class="twoCol_last layout columns">
           <%@ include file="/view/agreement/showbulletin/winbid_bulletin_list.jsp" %>
      </div>
    </div>
    <!--招标公告、中标公告  结束-->
  </div>
  <!--主要内容 结束-->
  <!--底部容器 开始-->
  <div id="footer">
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
