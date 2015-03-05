<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>政策虽未明朗 汽车消费圈刚性需求依然存在- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813026ae99013029c827fe001f.jsp" title="政策虽未明朗 汽车消费圈刚性需求依然存在" class="cmsHref_self">政策虽未明朗 汽车消费圈刚性需求依然存在</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>政策虽未明朗 汽车消费圈刚性需求依然存在</h1>
					<div class="source">
						<span>发布时间：2011-05-26</span>
						<span>发布人：-  </span>
					</div>
					<p><p>四川在线-华西都市报2011年5月26日报道　&ldquo;节能车型补贴3000元即将取消&rdquo;的消息，可说是目前车市炒得最热的话题。对此，各方反响不一。回顾去年4月&ldquo;成都车市即将限牌&rdquo;消息发布后，一时间，购买力集体释放。彼时，各个集团经销商的现场上户中心，排队上牌的消费者络绎不绝。</p>
<p>而这一次，&ldquo;节能补贴3000元&rdquo;似乎成为2011年车市惨淡一季之后的&ldquo;催生剂&rdquo;，被各个经销商用以刺激消费。紧接着，&ldquo;节能补贴3000元取消不靠谱&rdquo;、&ldquo;借机炒作有失公道&rdquo;等相反的声音相继出现。记者在与多家车企与车商的沟通中发现，&ldquo;虽然政策最终并未确定，但无论市场、车商还是消费者都应该做好应对&rdquo;这一观点，成为大家的共识。</p>
<p>目前来看，国家用于支持节能补贴的120亿元资金即将用完，而后续是否继续拨款尚未有定论。就此，海马汽车已发布紧急通知，让各经销店做好应对措施。而其他品牌如奇瑞、江淮、长城、吉利等，也纷纷表示做好准备，一旦政策取消，将要打一场硬仗。</p>
<p>且不论政策最终是否取消，对经销商来说，做买卖的，借机促销无可厚非;对消费者来说，汽车消费圈里本就存在大量刚需，他们通过汽车拓宽生活半径、改变生活方式，买车只是时间问题，但3000元对于小排量的受众来说还是颇为具体。一位身怀六甲的准车主原本打算年底购车，到那时她刚好生完小孩复出工作，但一听到节能汽车补贴或许取消，&ldquo;3000元能买不少尿片和奶粉&rdquo;是她的第一反应。因此，政策虽未明朗，但刚需们明显&ldquo;伤不起&rdquo;。只要有可能，做好准备才是王道。</p>
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