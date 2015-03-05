<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>成品油供需紧张情况有所缓解- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/goodszx.jsp" title="供货资讯" class="cmsHref_self">供货资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130bc5ca70130bf177b210028.jsp" title="成品油供需紧张情况有所缓解" class="cmsHref_self">成品油供需紧张情况有所缓解</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>成品油供需紧张情况有所缓解</h1>
					<div class="source">
						<span>发布时间：2011-06-24</span>
						<span>发布人：-中国证券报  </span>
					</div>
					<p><P>在国际油价走低和国内需求减缓的共同作用下，持续数月的“油荒”在近期有所缓解。国家发改委有关负责人日前在谈及价格问题时也表示，目前一些重要商品供应得到较好保障，国家加强了煤电油气运调节，实施有序用电措施控制高耗能行业过快发展，用电用油紧张状况有所缓解。</P>
<P>国家发改委数据显示，截至6月18日，中石油、中石化两公司在9个小麦主产区销售系统成品油库存合计370.08万吨，同比增加19.1%，其中柴油为211.00万吨，同比增加21.8%。</P>
<P>中信建投首席宏观分析师魏凤春对中国证券报记者表示，目前成品油供需紧张的情况的确有所缓解，主要是因为国际油价目前大幅度下行，减轻了国内油价上涨的压力。</P>
<P>上周，国际油价累计下跌6.2%，纽约商品交易所7月交割的原油期货合约上周五收于每桶93.01美元，这是原油主力合约自2011年2月中旬以来的最低收盘价。</P>
<P>市场人士指出，导致上周国际油价“跳水”的因素很多，其中最主要是受到希腊债务危机不断加深的影响，新财长的任命对提振市场信心效果有限。</P>
<P>对于未来国际油价的走势，国金期货首席分析师江明德表示，原油价格下跌空间不大，油价将在每桶90美元上下震荡。此前原油价格上冲受到北非、中东局势紧张影响，这种利多因素是短暂的，现在油价已经回归，油价的大幅上涨要等美国经济真正强劲复苏，未来将持续震荡。</P>
<P>大宗产品电子交易平台金银岛数据也显示，国内成品油供需层面相对平稳。主营炼厂虽然检修导致后期资源吃紧，但是目前由于华北、华东等地区的农业、渔业需求由旺转淡，中间商、调油商库存消耗缓慢，油市的“油荒”情绪销声匿迹。</P>
<P>6月份，长江中下游地区旱转涝，抑制了当地批发直销及终端汽柴油需求，再加上休渔期的开始，预计6月成品油库存仍有小涨可能性。国际油价持续低位也大大减轻了山东地方炼厂成本压力，在原料充裕的拉动下，地方炼厂的开工率有望持续上行，届时，国内成品油供应将得到有力的补充。</P>
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