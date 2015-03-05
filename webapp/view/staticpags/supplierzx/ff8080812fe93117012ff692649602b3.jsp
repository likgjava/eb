<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>2011年 小型工程机械或成亮点- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fe93117012ff692649602b3.jsp" title="2011年 小型工程机械或成亮点" class="cmsHref_self">2011年 小型工程机械或成亮点</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>2011年 小型工程机械或成亮点</h1>
					<div class="source">
						<span>发布时间：2011-05-16</span>
						<span>发布人：-  </span>
					</div>
					<p><p>5月13日，吉峰农机召开了2010年年度股东大会。会后，吉峰农机董事长王新明在接受本报记者采访时表示，&ldquo;在保持传统农机板块发展的基础上，吉峰农机在2011年将积极推进经营机构调整，特别是工程机械板块发展，公司将着力从传统农机业务占主营业务80%向农业机械、农用汽车、工程机械、通用机电产品四大业务板块均衡协调发展进行调整，为打造&lsquo;大农机、大流通、大平台&rsquo;奠定基础。&rdquo;</p>
<p>&ldquo;战略是企业的生命线。&rdquo;王新明认为，&ldquo;公司在2011年将进一步推进行业全国化战略，加强信息化平台建设和人才培养，优化经营机构，将吉峰农机打造成中国未来农村机电销售行业的领跑者。&rdquo;战略的有效实施也为吉峰农机带来了漂亮的业绩，2010年作为吉峰农机真正全国化的元年，公司在国内传统农机市场的占有率首次突破1%，达到了1.32%，公司到2010年底已经进入了全国18个省份市场，全年实现营业收入36.52亿元，较上年同期增幅为127.76%。</p>
<p>&ldquo;信息化建设和人才培养是公司在未来发展中会持续关注和加大投入的板块。&rdquo;王新明告诉记者，&ldquo;目前公司财务管理和供应链管理都已经全面实施了信息化管理，2011年吉峰农机要高度加强信息化平台建设，这不仅是内部管控的需要，也是提升销售业绩的重要途径。另一方面，公司去年在团队建设特别是中高端人才培训上花了大力气，投入资金800余万元。随着吉峰农机全国化发展，公司2011年将继续加大人才培训和团队建设上的投入，力争人力资源发展跟上市场扩张的脚步。&rdquo;</p>
<p>此外，王新明还就工程机械板块的发展回答了部分中小股东的疑问，他表示&ldquo;吉峰农机未来在工程机械板块的发展主要还是围绕小型工程机械。随着我国城乡一体化建设和小型工程机械加入农机下乡补贴的范畴，小型工程机械市场具有很大的发展空间，我们认为小型工程机械板块在未来20年都属于高成长板块。而公司整合的吉峰三立、吉峰联科和吉峰长城都是在工程机械行业历练了20年左右的团队，他们将成为吉峰农机推进工程机械板块的发展坚实基础。&rdquo;</p>
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