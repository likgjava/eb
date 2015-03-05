<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>LED电视背光需求有望在9月回升- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131767356013187f2ed4c0b0a.jsp" title="LED电视背光需求有望在9月回升" class="cmsHref_self">LED电视背光需求有望在9月回升</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>LED电视背光需求有望在9月回升</h1>
					<div class="source">
						<span>发布时间：2011-08-02</span>
						<span>发布人：-DJ财经知识库  </span>
					</div>
					<p><P>LED需求回升期待再延至9月，今年6月光电展时，LED业界无不欣喜7月可望见LED背光订单回笼，但7月接单却未如预期见旺季，现在看来LED电视背光需求回升不如预期，不仅欧美市场如此，中国大陆的LED TV背光需求也趋缓，LED业界一度认为需求会再延至8月，但进入8月之初，包括LED晶粒厂晶电、璨圆以及LED封装厂亿光，都认为8月还要再观察，预估9月才会较明朗。</P>
<P>LED期待的第三季旺季已底定不旺，封装大厂亿光董事长叶寅夫指出，预估第三季营收将与第二季持平表现。而亿光总经理刘邦言则指出，目前第三季产能利用率与第二季相当约7成至8成，就订单能见度来看现在就期待9月了。上周五亿光入主上游晶粒厂泰谷底定，加上晶电及广镓，亿光握有台湾最大晶粒产能，也是为台湾LED找出海口关键厂商，就今年LED背光市场况不如预期，亿光今年积极挺入照明市场。</P>
<P>不过，目前照明占亿光占营收约1成，刘邦言也指出，预计到今年年底前我们的目标就是照明占1成，现在是期待LED背光的需求能在9月回升。刘邦言指出，今年LEDTV渗透率现在业界还在努力期能达到40%。手机按键背光的市场回温也预计到9月回升。刘邦言进一步指出，进入下半年，可感觉到全球经济状况变化让需求递延。</P>
<P>据了解，LEDTV渗透率今年即便可如期待达到40%，但相对LED TV的使用颗数正因单颗效能提升而缩减，因此颗数的减少对LED晶粒、封装厂的冲击已逐渐显见，亿光预期今年上下半年营收比是5:5。</P>
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