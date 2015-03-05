<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>第四届中国高端水采购交易会27日举办- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="采购焦点资讯" class="cmsHref_self">采购焦点资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f7ae5ba012f7b273a3f00dc.jsp" title="第四届中国高端水采购交易会27日举办" class="cmsHref_self">第四届中国高端水采购交易会27日举办</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>第四届中国高端水采购交易会27日举办</h1>
					<div class="source">
						<span>发布时间：2011-04-22</span>
						<span>发布人：-  </span>
					</div>
					<p><p>近日，记者从&ldquo;2011第四届中国高端瓶装水采购交易会（CBW2011）&rdquo;主办方获悉：</p>
<p>由中国食品土畜进出口商会、北京公众健康饮用水研究所、中国同源有限公司共同主办，上海天盛会展服务有限公司承办的&ldquo;2011第四届中国高端瓶装水采购交易会（CBW2011）&rdquo;将于4月27-29日在上海光大会展中心隆重举办。</p>
<p>在萨摩亚中国大使馆、俄罗斯伊尔库茨克州北京代表处和广西巴马县政府及相关协会的大力支持下，本届展会招展顺利。萨摩亚的天然水、奥地利的活泉水、加拿大的万年冰川矿泉水、新西兰的纯天然水、俄罗斯贝尔加湖矿深层湖水等首次隆重登陆中国市场。国内方面：西藏、青海、新疆、内蒙古、云南、贵州、四川、河南、广东、广西、吉林、辽宁、黑龙江、浙江、山东等地的天然矿泉水企业参展。世界长寿之乡-巴马天然矿泉水首次将以政府组团的形式参与本届展会。展品涉及：矿泉水、山泉水、冰川水、生态水、苏打水、碱性水、婴儿饮用水、深层海洋水、天然小分子团水、泡茶水等。</p>
<p>现场活动纷呈&ldquo;2011中国高端水产业发展高峰论坛&rdquo;将同期举办。</p>
<p>国家发改委公众营养与发展中心饮用水产业委员会主任李复兴教授《关于中国高端水市场发展的几点建议》，俄罗斯贝尔加湖矿泉水发布会、加拿大万年冰川水产品发布会、 &ldquo;品水&rdquo;、&ldquo;斗水&rdquo;、&ldquo;签单抽大奖&rdquo;、&ldquo;参观有礼&rdquo;、&ldquo;评奖&rdquo;、&ldquo;观众投票&rdquo;等活动精彩不断。</p>
<p>上海天盛会展服务有限公司将一如既往，满怀激情与力量，致力于中国高端水产业的发展，为高端水生产商、经销商、代理商以及广大关注中国高端水产业发展的人群搭建起一个交流互动及产品展示的平台。</p>
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