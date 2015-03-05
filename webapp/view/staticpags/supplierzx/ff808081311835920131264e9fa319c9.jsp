<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中国彩电市场10年消失20品牌 集中度提高- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081311835920131264e9fa319c9.jsp" title="中国彩电市场10年消失20品牌 集中度提高" class="cmsHref_self">中国彩电市场10年消失20品牌 集中度提高</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>中国彩电市场10年消失20品牌 集中度提高</h1>
					<div class="source">
						<span>发布时间：2011-07-14</span>
						<span>发布人：-北京商报  </span>
					</div>
					<p><P>竞争激烈的彩电市场，经历了一轮轮洗牌。数据显示，10年间新科、上广电等20多个彩电品牌相继退出历史舞台。专家预计，未来平板电视行业的增速将放缓，竞争日趋惨烈，行业的集中度会越来越高。</P>
<P>昨日，在中国数字电视发展高峰论坛上记者了解到，2002年左右平板电视消费市场启动之时，市面上仍然存在着30多个品牌。将近10年的时间，近20多个耳熟能详的品牌已经退出了平板市场。现阶段，国内平板市场主要集中着10个品牌。从销量占比上比较，外资品牌的销售量占比是35%，国内品牌的市场占比是65%。</P>
<P>随着平板电视市场的日趋饱和，彩电厂商开始将3D、智能电视视为新的利润增长点。据中国电子商会调查办公室《2011上半年中国平板电视城市消费者需求状况调研报告》显示，预计到今年年底，LED电视的销量将达到1800万台。报告还指出，得益于3D电视价格迅速下降，今年3D电视消费超预期增长，全年有望突破600万台，智能电视的需求也将达到400万台。</P>
<P>然而市场的竞争还是不可避免的，为抢市场企业也不得不使用“价格屠刀”，即使是高端产品，也将面临低利润的挑战。</P>
<P>对此，中国电子技术标准化研究所副所长赵新华表示，硬件、软件、网络、媒体资讯条件的逐步成熟，为彩电业向新的产品阶段过渡提供了很好的基础。“要摆脱低利润的困境，彩电企业必须继续加大创新力度。另外有实力的企业要学会通过结盟、合作等方式实现规模效应、共同协作打通产业链，整合资源才能降低成本。”</P>
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