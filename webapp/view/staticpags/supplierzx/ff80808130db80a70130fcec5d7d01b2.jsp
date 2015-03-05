<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>雀巢徐福记承认进行收购谈判- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130fcec5d7d01b2.jsp" title="雀巢徐福记承认进行收购谈判" class="cmsHref_self">雀巢徐福记承认进行收购谈判</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>雀巢徐福记承认进行收购谈判</h1>
					<div class="source">
						<span>发布时间：2011-07-06</span>
						<span>发布人：-法制晚报  </span>
					</div>
					<p><P>据外媒今晨报道，世界最大的食品制造商雀巢证实称，为寻求增加新兴市场的营收，该公司确实正在和中国市值最大的糖果制造商——徐福记国际集团进行收购初期谈判。英国媒体称，这一交易可能价值超过20亿美元。</P>
<P>徐福记也表示，雀巢正在就收购价格进行评估。实际上，为成为合作伙伴，两家公司已进行了长达数年的洽谈。不过，徐福记还特别强调，不会全部卖给对方，而是以合作方式继续做大。</P>
<P>报道称，在新加坡交易所，徐福记的市值为26亿美元。而身为全球最大食品制造商的雀巢，去年底拥有约合188亿美元的现金。雀巢已经表示，公司将考虑采取“补强收购”，意思是公司收购交易很自然地符合收购方的现有业务范围或战略。</P>
<P>雀巢此举是为了增加在新兴市场的份额。雀巢的目标是，新兴市场对于该公司总体营业收入的贡献率到了2020年要提高至45%。</P>
<P>徐福记在官网发布公告称，为顾及相关新闻报道有可能引发新加坡股市不正常波动，从而影响股东权益，徐福记自2011年7月4日起在新加坡交易所的股票将暂时停止交易。</P>
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