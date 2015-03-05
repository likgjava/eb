<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>LG：制造出全球最先进冰箱压缩机- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70131022e152d074c.jsp" title="LG：制造出全球最先进冰箱压缩机" class="cmsHref_self">LG：制造出全球最先进冰箱压缩机</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>LG：制造出全球最先进冰箱压缩机</h1>
					<div class="source">
						<span>发布时间：2011-07-07</span>
						<span>发布人：-机电商情网  </span>
					</div>
					<p><P>日前，泰州LG电子冷机有限公司线性压缩机生产项目取得突破性进展，公司投入1亿多元，经研发人员近一年的集中攻关，整套压缩机100多个零部件基本实现了国产化，这意味着世界上最先进的冰箱压缩机项目将实现泰州制造。</P>
<P>压缩机技术是冰箱生产的核心，线性压缩机与普通和变频压缩机原理不同，靠谐振弹簧存储能量，根据外部输入的载荷调整压缩量，属变容量压缩机。线性压缩机代表了全球最新压缩机生产技术。泰州LG公司研发的线性压缩机拥有70多个国际专利，堪称世界第一。</P>
<P>该项目的实施将使泰州LG公司逐步实现线性压缩机的本土配套，结束进口线性压缩机产品的历史，成为韩国LG集团在海外的唯一一家线性压缩机生产基地。到9月份，泰州LG公司线性压缩机项目可以释放全部产能，满足公司全部生产需要。此前，线性压缩机只在韩国国内生产，泰州LG电子冷机有限公司每月要花1000多万元通过海外采购才可以使用这一高效低耗的新产品。</P>
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