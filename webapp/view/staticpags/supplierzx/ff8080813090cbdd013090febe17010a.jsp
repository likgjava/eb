<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>华润雪花3亿控股墨尼啤酒 欲一统辽宁市场- 【阳光易购】</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="/view/resource/skin/thems/default/web.css" />
<link rel="stylesheet" type="text/css" href="/view/resource/skin/skin07/css/seller.css" />
<link rel="stylesheet" type="text/css" href="/view/resource/skin/thems/default/listDetail.css" />
<!--JS-->
<script type="text/javascript" src='/view/srplatform/portal/include/common.js'></script>
</head>

<body>

<div id="container">
	<!-- 头部开始 -->
	<div class="header">
		<%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
	</div>
	<!-- 头部结束-->
    <!--主要内容 开始-->
	<div id="sysContent" class="page">
		<div id="contentSub" class="index3pa">
			<%@ include file="/view/staticpags/load/left_cms.jsp" %>
		</div>
		<div id="contentMain" class="index3pa">
			<div id="conTitle">
				<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/supplierzx.jsp" title="供应商资讯" class="cmsHref_self">供应商资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813090cbdd013090febe17010a.jsp" title="华润雪花3亿控股墨尼啤酒 欲一统辽宁市场" class="cmsHref_self">华润雪花3亿控股墨尼啤酒 欲一统辽宁市场</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>华润雪花3亿控股墨尼啤酒 欲一统辽宁市场</h1>
					<div class="source">
						<span>发布时间：2011-06-15</span>
						<span>发布人：-  </span>
					</div>
					<p><p>6月9日上午，华润雪花正式宣布，出资3亿元收购北方绿色食品股份有限公司清河墨尼啤酒分公司80.1%股权，全新成立华润雪花啤酒(铁岭)有限公司。</p>
<p>北方绿色食品股份有限公司清河墨尼啤酒分公司年产能19万千升，是辽北地区最大啤酒企业，岛城牌啤酒为该公司的主打品牌。合资合作后，华润雪花啤酒已经投入资金近4000万进行项目改造。</p>
<p>华润雪花啤酒辽宁分公司总经理那永卓告诉记者，&ldquo;已经开始投产雪花啤酒&rdquo;。</p>
<p>1994年诞生的华润雪花正是从辽宁走向全国的，目前东三省市场已经成为华润雪花牢固的根据地市场，辽宁市场主要份额也已多年都在雪花手中。此次并购的成功，意味着全国开疆扩土的华润雪花，在自己的老家辽宁，距离实现&ldquo;一统辽啤&rdquo;的梦想再进一步。</p>
<p>今年初，位于阜新的梅雪啤酒厂也已归至华润雪花麾下。此次收购完成后，华润雪花啤酒已在辽宁的沈阳、大连、鞍山、盘锦、辽阳、丹东、葫芦岛、朝阳、铁岭、阜新等城市拥有多家啤酒生产企业，产能已超过230万千升。</p>
<p>华润雪花在辽宁占据68%的份额，竞争对手百威英博份额不到17%，青岛啤酒所占份额为6%-7%。</p>
<p>目前，华润雪花啤酒在辽西南环渤海沿线的丹东、大连、盘锦、葫芦岛等地，在辽宁西部内陆的朝阳、阜新，辽宁中部的沈阳、辽阳、鞍山等地区都建有生产厂。</p>
</p>
				</div>
			</div>
		</div>
	</div>
	<!--主要内容 结束-->
	<!-- 脚开始 -->
	<div class="footer">
	    <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
	</div>
	<!-- 脚结束 -->
</div>
</body>

</html>

<script>
	// 为左边栏设定选中样式,判断用于解决点击左边栏后样式被覆盖问题
	var id = $("#channelId").val();
	if(null != $('.subnav>li[id='+id+']').html()) {
		$('.subnav>li').removeClass('selected');
		$('.subnav>li[id='+id+']').addClass('selected');
	}
</script>