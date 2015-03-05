<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>抢占行业先机 金锣规模化养殖初见成效- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f299733b0a4e.jsp" title="抢占行业先机 金锣规模化养殖初见成效" class="cmsHref_self">抢占行业先机 金锣规模化养殖初见成效</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>抢占行业先机 金锣规模化养殖初见成效</h1>
					<div class="source">
						<span>发布时间：2011-07-04</span>
						<span>发布人：-CCTV《经济信息联播》  </span>
					</div>
					<p><P>国家统计局本月24日公布的“50个城市主要食品平均价格变动情况”显示，6月中旬猪肉五花肉上涨4.3%，猪肉后腿肉上涨4.1%，均接近每斤15元，已直逼牛肉价格。</P>
<P>面对来势汹汹的“猪坚强”，众多肉制品企业纷纷加入抢猪大战，面临原料紧张，缺米下锅的窘境。金锣集团采购部负责人卢念松却表示：“相对去年而言，今年压力不大”。究其原因，主要是因为金锣集团前几年开始酝酿自己“圈地”养猪，积极进军上游养殖产业的结果。</P>
<P>2008年，为了避免被市场牵着鼻子走，金锣集团领导层经多方论证，决定进军上游养殖产业，开始自建猪场，自繁自养种猪、商品猪。</P>
<P>经过3年的探索，金锣集团牧业公司生猪的出栏量实现了稳步提升，目前金锣集团自己供应的商品猪已经占到整个集团屠宰量的半壁江山。由于集约化、规模化养殖的实行，金锣牧业公司每年可为集团节省近几千万元，可见，规模化养殖效益已初见端倪。</P>
<P>依托自有的养殖基地，金锣集团对于生猪收购，无论从质量上还是数量上均实现了可控操作。同时随着出栏量的逐渐增加，企业一方面对社会猪源的依赖进一步降低，另一方面从根本上健全了食品安全产业链，实现“从牧场到餐桌”的产品追溯体系，为消费者提供了可以放心食用的冷鲜肉及肉制品。</P>
<P>目前金锣集团依托现有资源与经验，积极开发了“公司+基地+农户”的产业化发展模式，充分发挥龙头带动作用，形成龙头带基地、基地连农户，相互依托、共同发展的产业化格局。</P>
<P>截止目前，金锣集团在现有养殖场基础上，又注资18亿元，修建了12座养猪场，同时预计2到3年内还将多建10座，以期达到每年向公司供应200到250万头商品猪的能力。</P>
<P>得基地者得天下，金锣集团将继续积极探索全产业链发展的新模式，在降低企业自身风险的同时，拉动当地就业，加强食品安全源头控制，这种探索和创新对于促进和维护整个产业，稳定社会发展起到了积极作用。(有删节)</P>
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