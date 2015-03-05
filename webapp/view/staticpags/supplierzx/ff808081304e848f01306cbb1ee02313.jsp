<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>苹果试水即时通讯市场弥补应用软肋- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01306cbb1ee02313.jsp" title="苹果试水即时通讯市场弥补应用软肋" class="cmsHref_self">苹果试水即时通讯市场弥补应用软肋</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>苹果试水即时通讯市场弥补应用软肋</h1>
					<div class="source">
						<span>发布时间：2011-06-08</span>
						<span>发布人：-  </span>
					</div>
					<p><p>当苹果之父乔布斯将兴趣转移到无线即时通讯领域，众多涉及该领域的厂商是否会感到压力?昨日，苹果公司宣布推出即时通讯产品&mdash;&mdash;iMessage，这是苹果首次涉足该领域。有分析认为，这将改变即时通讯市场目前的格局。</p>
<p>昨日，一年一度的苹果全球开发者大会召开。虽然没有公布万众瞩目的iPhone 5，但是无线通讯型产品iMessage的推出亦引发广大苹果迷的期待。据了解，此产品基于iOS操作系统，可适用于iPhone、iPad以及iPod touch等移动终端。可以即时发送文字、照片、视频、通讯录以及位置消息等，并支持多人聊天，届时用户可以直接绕开电信运营商实现沟通。iMessage支持WiFi和3G网络。</p>
<p>目前，苹果的iPhone和iPad等终端产品雄霸市场，但应用一直是其软肋，大多依赖于第三方开发，此次发布无线通讯产品iMessage首次打通了自身产品链，在社交工具开发上实现了突破。</p>
<p>据了解，2011年第一季度中国IM(即时通讯)累计账号数已达4.92亿，并且从市场格局来看，QQ、飞信和MSN分别以53.9%、30.7%和10.4%的市场份额，连续几个季度包揽国内即时通讯市场累计账户份额排名前三，市场格局基本已成型。</p>
<p>易观国际分析师胡志辉表示，此次苹果试水无线通讯领域，可谓是一箭双雕，在完善了自身产品的同时，蚕食无线通讯市场份额，对现有的行业格局甚至是移动运营商形成了巨大压力。仅无线终端方面，iPhone在中国超过800万的用户量，对于其他无线通讯厂商而言将是无形的压力。</p>
<p>对此，米聊的运营商小米科技董事长雷军(微博)表示，自己与苹果的发展方向相同，但是绝不惧怕苹果，并称近期会发布米聊的新功能。</p>
<p>胡志辉强调，苹果试水无线通讯可能带来最大的后果是，可能会引发其他移动、平板电脑厂商效仿。</p>
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