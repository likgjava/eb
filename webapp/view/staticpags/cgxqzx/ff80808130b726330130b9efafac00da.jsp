<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>集中采购降低成本 “宁波制造”青岛“取经”- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130b726330130b9efafac00da.jsp" title="集中采购降低成本 “宁波制造”青岛“取经”" class="cmsHref_self">集中采购降低成本 “宁波制造”青岛“取经”</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>集中采购降低成本 “宁波制造”青岛“取经”</h1>
					<div class="source">
						<span>发布时间：2011-06-23</span>
						<span>发布人：-中国宁波网  </span>
					</div>
					<p><P>“在行业协会的协调下，青岛塑机企业对进口零部件实行统一采购，采购成本下降了10%，有效提升了行业竞争力。”昨天的宁波周产业对接洽谈会后，宁波塑机协会秘书长陈栋连称“学了一招”，要把青岛塑机行业集中采购、抱团营销的做法引到宁波。</P>
<P>昨天，宁波纺织服装、电子、模具、文具、厨卫、塑机、汽车零部件等传统优势行业的70多家企业负责人现身“青岛·宁波周”，与青岛海尔等“航母级”企业零距离交流，学习其研发、管理经验，捕捉转型升级商机。</P>
<P>由于与韩国较近，青岛文具在设计、色彩、包装等方面形成鲜明的风格，并且更适合亚洲市场，这对以出口为主的“中国文具之都”宁波极具借鉴意义。贝发集团生产总监胡冬告诉记者，贝发正加快由制造企业向文具集成供应商转型，宁波周为贝发提供了一个开拓北方市场的契机。</P>
<P>宁波厨卫和汽车零部件企业也在青岛找到了商机。宁波厨卫产业协会秘书长李雪芬说，宁波虽然是中国厨卫之都，但方太、欧琳这样的知名品牌还太少。不少企业只着眼于宁波市场，没有真正走出去。两市加强战略合作，青岛有望成为宁波厨卫产品进军北方市场的“桥头堡”。</P>
<P>“海尔的相关负责人提出，可否为他们生产配套冲压件。”宁波汽车零部件产业协会副秘书长干文萍说，通过现有生产能力开发与家电产业配套，也许是宁波汽车零部件产业转型发展的一个新选择。针对这个大胆的“跨界计划”，干文萍表示回甬后将立即召集企业商讨可行性。</P>
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