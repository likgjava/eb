<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>“十二五”可再生能源发展目标明确- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813218571c01321d61b8d00232.jsp" title="“十二五”可再生能源发展目标明确" class="cmsHref_self">“十二五”可再生能源发展目标明确</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>“十二五”可再生能源发展目标明确</h1>
					<div class="source">
						<span>发布时间：2011-08-31</span>
						<span>发布人：-  </span>
					</div>
					<p><P>笔者从权威渠道获悉，目前政策上对于未来五年可再生能源发展的思路已基本清晰，“十二五”期间，全国商品化可再生能源占全部能源消费总量的比重要达到9.5%以上。</P>
<P>水电方面，政策将着力推动西部8个千万千瓦级水电基地建设，至2015年底，常规水电利用规模要达到2.6亿千瓦，年发电量要达到9100亿千瓦时，抽水蓄能利用量要达到3000万千瓦。</P>
<P>风电方面，至2015年末，并网风电累计装机容量要达到1亿千瓦，年发电量要达到1900亿千瓦时。其中，分布式风电累计装机要达到2500万千瓦。</P>
<P>太阳能发电方面，至2015年底，光伏发电装机要达到900万千瓦，光热发电装机要达到100万千瓦，太阳能热水器推广面积要达到4亿平方米。</P>
<P>生物质发电方面，到2015年末，生物质发电装机建成规模要达到1300万千瓦。</P>
<P>政策上还首次提出地热能、潮汐能和海洋能的发展目标。到2015年末，地热能年利用量要达到1500万吨标煤，地热能发电装机要达到10万千瓦;建成1到2个万千瓦级潮汐电站;建成5个万千瓦海洋能发电站。</P>
<P>在相关的政策补贴和税收政策方面提出，要通过市场竞争的机制，完善可再生能源产品的政策补贴机制，鼓励可再生能源发电企业与用电户的直接交易，全面落实完善可再生能源发电补贴政策及可再生能源集中供热、供气和液体燃料的价格及服务收费标准。</P>
<P>此外，政策上还将推动建立可再生能源发电配额制度，提出要制定电网企业年度收购非水电可再生能源电量额度，规定大型发电企业投资可再生能源发电项目的比例，并确定各省(区、市)消纳可再生能源发电量的指标。</P>
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