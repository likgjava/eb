<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>车轮企业急上市 欲抢并购先机- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fe93117012ff6b2b0e902f7.jsp" title="车轮企业急上市 欲抢并购先机" class="cmsHref_self">车轮企业急上市 欲抢并购先机</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>车轮企业急上市 欲抢并购先机</h1>
					<div class="source">
						<span>发布时间：2011-05-16</span>
						<span>发布人：-  </span>
					</div>
					<p><p>日前，厦门日上车轮和正兴车轮集团几乎同时在A股和纽交所上市，这是继去年兴民钢圈和金固车轮上市后的又一拨车轮企业上市潮。业内认为，通过上市达到规模效应，以避免被其他企业并购的风险是车轮企业上市初衷。正兴车轮选择上市就是为以后并购重组做准备。</p>
<p>得益于国内汽车市场这两年的飞速发展，而与商用车增速大体一致的中国车轮市场也在飞快发展。根据Frost &amp; Sullivan的调查数据，中国车轮市场的规模近5年保持了20%的年复合增长率。Frost &amp; Sullivan预计，汽车零部件市场的产值将从2010年的52亿元增长到2015年的107亿元，年均复合增长率将达到15.5%。</p>
<p>分析人士认为，由于车轮为可持续模块化定制的标准化零配件，技术含量相对不高，企业准入门槛也比较低，很容易形成规模化效应。而且车轮等部分汽车零部件生产属于规模、资金、技术和劳动密集型产业，不跟随整车市场需求进行扩张，将面临被淘汰的危险。</p>
<p>&ldquo;如果不上市，很难募集资金。当别家上市后做到规模化，就给了其他企业危机意识，尤其是民营企业。&rdquo;上述业内人士告诉记者。</p>
<p>但是企业达到规模化生产后也要提升自主研发能力。中国汽车工业协会车轮委员会秘书长李晓擎认为，与国际水平相比，我们车轮行业还存在很大差距，尤其在核心技术和制造设备以及生产工艺方面。</p>
<p>盖世汽车网总裁陈文凯认为，车轮企业比较容易把规模做大，但应该不是国家重点鼓励行业，将来肯定要整合，所以各家企业都纷纷用资本市场来做文章。</p>
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