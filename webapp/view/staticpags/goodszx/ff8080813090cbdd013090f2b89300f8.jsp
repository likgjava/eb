<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>供应增加使国内部分农产品价格下降- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813090cbdd013090f2b89300f8.jsp" title="供应增加使国内部分农产品价格下降" class="cmsHref_self">供应增加使国内部分农产品价格下降</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>供应增加使国内部分农产品价格下降</h1>
					<div class="source">
						<span>发布时间：2011-06-15</span>
						<span>发布人：-  </span>
					</div>
					<p><p>去年以来，游资热衷于炒作各种农产品。不过，据《中国证券报》的调查，一度席卷全国的农产品炒作&ldquo;大战&rdquo;现在暂时归于平静。农产品价格接连下跌造成巨额亏损，炒作资金不得不大面积撤退。例如，去年蜂拥至山东栖霞&ldquo;抢&rdquo;苹果的游资们撤退了。</p>
<p>去年曾为不少大客户做苹果&ldquo;代办&rdquo;的老王称，大概2011年农历春节过后，苹果价格开始趋于平稳。老王介绍说，在去年到栖霞收购苹果的批发商当中，目前有近50%的人亏损，这其中80%都是以前不做苹果生意的&ldquo;外行人&rdquo;。&ldquo;蔬菜、水果都有一个保鲜期，苹果也一样，一旦苹果的皮软了或品相被破坏了，就算1分钱1斤也没人要了。&rdquo;去年的&ldquo;蒜你狠&rdquo;也风光不再。不少大蒜批发商表示，今年这大蒜生意太难做了。批发商李先生说：&ldquo;要是再卖不出去，过几天肯定会赔本。我前段时间从江苏进了不少货，当时市场上的价格还相对较高，但现在山东的大蒜大量集中上市，市场价格一天比一天低。&rdquo;</p>
<p>业内人士还表示，大蒜、大白菜、苹果等蔬菜、水果预计今年将大幅增产，下半年的批发收购价格将趋于平稳，甚至可能部分下降，果蔬的品质和卖相也将好于去年同期。但在干旱洪涝等极端天气影响下，各路炒作资金可能再次借题发挥。</p>
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