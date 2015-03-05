<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>三星电子计划加大对存储芯片投资- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01316933852304ec.jsp" title="三星电子计划加大对存储芯片投资" class="cmsHref_self">三星电子计划加大对存储芯片投资</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>三星电子计划加大对存储芯片投资</h1>
					<div class="source">
						<span>发布时间：2011-07-27</span>
						<span>发布人：-SEMI  </span>
					</div>
					<p><P>三星电子CEO崔志成周一表示，虽然经济不确定性对零部件需求造成了冲击，但该公司今年仍将加大对存储芯片的投资，而且不会削减下半年的整体投资力度。</P>
<P>欧洲和美国债务问题正在持续压制存储芯片和平板显示器的价格以及PC和电视机的销量，这也导致三星的主要竞争对手纷纷削减投资额度，并调低预期。</P>
<P>崔志成说：“存储芯片所获得的投资将高于我们最初的计划，而我们下半年的整体资本开支也不会削减。”在谈到下半年的需求预期时，他表示：“我们不会只根据一年的预期来制定投资计划。”</P>
<P>作为三星在平板显示器领域的竞争对手，LGDisplay已经将2011年的资本开支计划削减了18%，至4万亿到4.5万亿韩元之间(约合38亿至43亿美元)。该公司已经连续第三个季度亏损，而且拒绝对今后的业绩作出预测。</P>
<P>三星是全球第一大存储芯片制造商，同时也是全球第二大手机制造商。该公司第一季度的资本开支为5.5万亿韩元(约合52亿美元)。</P>
<P>三星在4月末发布第一财季业绩时表示，计划根据市场预期将2011年的资本开支预算上调至23万亿韩元(约合218亿美元)。</P>
<P>三星将于本周五公布第二财季业绩。该公司本月初公布的初步业绩显示，第二财季营业利润下滑25%，至3.7万亿韩元(约合35亿美元)。外界普遍预计，三星液晶平板显示器业务将再度亏损。</P>
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