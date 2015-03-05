<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>独揽政策收储特权 中储粮暂按兵不动- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70131074de1380bde.jsp" title="独揽政策收储特权 中储粮暂按兵不动" class="cmsHref_self">独揽政策收储特权 中储粮暂按兵不动</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>独揽政策收储特权 中储粮暂按兵不动</h1>
					<div class="source">
						<span>发布时间：2011-07-08</span>
						<span>发布人：-每日经济新闻  </span>
					</div>
					<p><P>按照5月份国家粮食局等部门联合下发的《2011年小麦最低收购价执行预案》，在主产区执行最低收购价的企业确定为中储粮总公司及其分公司。</P>
<P>中华粮网分析师张智先表示，与往年不同，中储粮尚未大规模收购，而只是在各地进行正常储备轮换。储备粮轮换价格按照市场粮价走，不受国家最低收购价格限制。</P>
<P>以山东省潍坊市为例，今年该市储备粮轮换计划6.17万吨，目前已轮出1.53万吨，轮入1.87万吨。潍坊国家粮食储备库业务科相关负责人向《每日经济新闻》记者表示，该粮库目前正以1.07元/斤的价格收购小麦，但受市场粮价影响，收购进度缓慢。</P>
<P>按照农发行河南省分行安排，中储、地储粮轮换的57亿斤小麦所需的贷款要求随需随到。</P>
<P>然而，中储粮的各大收购点却没有展开大规模的行动。“目前中储粮在河南只收一部分用于轮换，总量不到10亿斤。”农发行河南省分行相关人士对记者表示。</P>
<P>国家粮食局数据显示，自6月5日小麦主产区收购工作陆续展开以来，国有粮食企业收购占收购总量的比重呈下降趋势。截至6月25日，国有粮食企业收购612.6万吨，占收购总量的比重下降到65%。去年同期，国有粮食企业收购1956万吨，占收购总量的91%。</P>
<P>据知情人透露，今年中粮集团、华粮集团已经不再作为独立的收购主体入市，而只是中储粮的委托收购企业。这意味着，今年中储粮重新获得了政策性粮食收储的独家特权，并根据最新规定，制定收储库点等原本需要多方研究的流程，今年也一并划拨给中储粮总公司独家决定。</P>
<P>“托市预案没有启动，在农发行千亿收购款未能进入市场的情况下，今年小麦价格将趋稳，后期或处于一种比较温和的慢性上涨。”张智先认为，从国家导向来看，粮食价格稳步上涨或仍是一个趋势。</P>
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