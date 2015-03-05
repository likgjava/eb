<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>上药今年采购额将破200亿- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f4c7799012f4ca77f32010d.jsp" title="上药今年采购额将破200亿" class="cmsHref_self">上药今年采购额将破200亿</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>上药今年采购额将破200亿</h1>
					<div class="source">
						<span>发布时间：2011-04-13</span>
						<span>发布人：-  </span>
					</div>
					<p><p>4月12日与69家跨国药企签订了战略合作协议，2011年向跨国药企采购金额将突破200亿元人民币，占目前新上药全国分销业务的约60%。</p>
<p>这69家跨国企业包括辉瑞、赛诺菲-安万特、默克、诺和诺德等公司。上海医药营销副总裁李永忠表示，目前上海医药在中国医药 <br />
相关公司股票走势<br />
&nbsp;<br />
他还指出，除了进一步与跨国药企在分销、零售上加强合作之外，公司还希望在药品研发、药品注册、政府事务、委托加工、共同开发推广潜力产品等方面开展深度合作。上海医药将开放集团强大的政府事务资源，为供应商在市场准入、政府招标、政策信息提供、产品准入、物价等多方面提供支持；并承诺在上海、北京、广州这三个城市，为供应商新品上市提供明显优于其他竞争对手的最快的覆盖速度，在最短的时间内完成产品的医院覆盖。</p>
<p>重组后的上海医药一鼓作气，相继并购整合了江苏、山东、广东、福建、北京等地区的一批区域分销领先企业。随着三月底最终完成中信医药100%股权的收购，上海医药实质上已形成以华东、华北、华南三大区域为核心的全国性的医药分销平台。2010年年报显示，其医药分销与零售业务实现销售收入290.95亿元，较上年同期增长22.95%，毛利率达到8.6%。</p>
<p>得益于与跨国企业的良好战略合作关系，上海医药目前在高端药品、器械与高值耗材、疫苗三个领域均处于中国市场领先地位，其中器械和高值耗材业务位居市场第一位，疫苗业务位居第二。（黄淑慧） <br />
&nbsp;</p>
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