<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>华润雪花啤酒继续加速快跑- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130bf287a0130bf3348e70011.jsp" title="华润雪花啤酒继续加速快跑" class="cmsHref_self">华润雪花啤酒继续加速快跑</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>华润雪花啤酒继续加速快跑</h1>
					<div class="source">
						<span>发布时间：2011-06-24</span>
						<span>发布人：-浙江在线-今日早报  </span>
					</div>
					<p><P>日前，华润雪花啤酒(中国)有限公司正式宣布，投资3亿元收购北方绿色食品股份有限公司清河墨尼啤酒分公司80.1%股权，全新成立华润雪花啤酒(铁岭)有限公司。一天之后，华润雪花啤酒湖南娄底生产基地正式开始建设，占地面积300亩，总投资3.3亿元人民币，规划年生产能力达到20万千升。</P>
<P>北方绿色食品股份有限公司清河墨尼啤酒分公司年产能19万千升，是辽北地区最具影响力的啤酒企业。完成此次并购后，华润雪花啤酒已在辽宁的沈阳、大连、鞍山、盘锦、辽阳、丹东、葫芦岛、朝阳、铁岭、阜新等城市拥有多家啤酒生产企业，产能已超过230万千升，销售网络遍布全省，在各地市场占有绝对优势。</P>
<P>华润雪花在2011年并没有停步，而是继续加速快跑。这也传递出一个积极信号：中国啤酒行业或将继续保持健康高速增长。</P>
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