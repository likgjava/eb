<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>壳牌与中石油成立合资企业开发天然气- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130ab37e40130afb3d80f05c2.jsp" title="壳牌与中石油成立合资企业开发天然气" class="cmsHref_self">壳牌与中石油成立合资企业开发天然气</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>壳牌与中石油成立合资企业开发天然气</h1>
					<div class="source">
						<span>发布时间：2011-06-21</span>
						<span>发布人：-财经网  </span>
					</div>
					<p><P>荷兰皇家壳牌有限公司(壳牌)与中国石油天然气集团公司(中石油)6月20日签署了一份全球合作协议，表达了在中国和世界其它地区寻求互惠合作机会的共同意愿。</P>
<P>双方还签署协议，成立一家双方各持股50%的建井企业，但该协议有待签约双方和政府的最终批准。依照双方意愿，该合资企业将开发一套创新性的和高度自动化的建井系统，以大幅提升陆上钻井和完井的效率。双方各自对合资企业的具体投入将在今后数月的过渡阶段讨论确定。</P>
<P>壳牌首席执行官傅赛和中石油总经理蒋洁敏出席了在北京举行的签字仪式。傅赛说：“建井合资企业的股东协议表明，壳牌与中石油正共同努力，以创新的和有成本竞争力的技术开发天然气资源。”</P>
<P>致密气、页岩气和煤层气的大规模商业化开采往往持续多年，每年钻井数百口。依照双方意愿，这套建井系统的设计将采用先进的自动化技术，以标准化的方式重复钻井和完井。该系统将综合双方的最佳技术和采购优势。</P>
<P>该合资企业计划应用自动定向钻井和钻井优化等最先进的技术，其中包括壳牌在北美致密气开采作业中率先应用的一些技术。该合资企业将向低成本的中国供应商采购所需的大部分钻机、服务和钻井设备。这一合作模式将以较低成本大规模开采资源量可观的天然气资源。</P>
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