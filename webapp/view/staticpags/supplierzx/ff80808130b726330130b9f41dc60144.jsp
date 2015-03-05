<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>上海大众：在参与政府采购中实现跨越发展- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130b726330130b9f41dc60144.jsp" title="上海大众：在参与政府采购中实现跨越发展" class="cmsHref_self">上海大众：在参与政府采购中实现跨越发展</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>上海大众：在参与政府采购中实现跨越发展</h1>
					<div class="source">
						<span>发布时间：2011-06-23</span>
						<span>发布人：-经济参考报  </span>
					</div>
					<p><P>《政府采购法》出台10年来，已经形成了以《政府采购法》为主体，多部法律共同作用的政府采购法律框架体系，对经济的高速健康发展发挥了极其重要的作用。</P>
<P>上海大众作为国内公务车市场的排头兵，各省市政府采购的重要汽车品牌，长期恪守“追求卓越，永争第一”的核心价值观，致力于为政府提供质优价廉、高性价比的公务轿车，在参与政府采购中自身也实现了跨越和发展。</P>
<P>上海大众汽车有限公司自1985年成立以来，始终坚持以客户和市场为导向的理念，以先进的科技、优秀的产品和卓越的服务赢得了市场的认可，上海大众汽车基于大众、斯柯达两大品牌，为中国顾客提供符合国际标准的安全、优质、节能、环保的汽车和全天候无缝隙的服务，成功探索利用外资、引进技术与自我发展相结合的发展模式，成为国内享有盛誉的知名汽车品牌。</P>
<P>26年来，为适应中国消费者尊享、安全的行车诉求，上海大众建立了功能完善、具备国际领先水平的技术开发中心，整车开发能力逐步提升。为适应政府采购对环保、节能等指标的要求，一直致力于优质、安全、节能产品的开发。2008年获得了中国环境标志产品认证证书，并荣获“中国政府采购十大最具竞争力车型”。2010年，上海大众年度产销同步突破100万辆;其中VW品牌年销量突破80万辆，实现了历史性的跨越式突破。</P>
<P>26年来，上海大众售后服务网络从最初的8家特约维修站发展到了500多家，形成了分布最广、布点最密的轿车售后服务体系，遍布大江南北的10万从业人员为全国近700万上海大众用户提供了优质的服务。这不仅缔造和支撑着全国最庞大的售后服务网络，更改变了整个国内轿车售后服务行业的面貌。通过完善的技术、优质的服务、合理的价格和严格的管理，上海大众已经成为深得消费者信赖和喜爱的优质汽车品牌。</P>
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