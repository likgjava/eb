<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>下半年成品油供应偏紧- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813118359201312b4b9d9b2418.jsp" title="下半年成品油供应偏紧" class="cmsHref_self">下半年成品油供应偏紧</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>下半年成品油供应偏紧</h1>
					<div class="source">
						<span>发布时间：2011-07-15</span>
						<span>发布人：-大江网-江南都市报  </span>
					</div>
					<p><P>昨日(14日)，记者从省商务厅获悉，上半年，我省成品油市场运行总体平稳，全省成品油采购301.4万吨，销售285.22万吨。下半年，九江炼油厂停产扩能1个月等因素将使我省成品油市场供应偏紧，我省将协调督促成品油市场主营企业积极组织油源应对。</P>
<P>【现状】市场需求旺盛</P>
<P>省商务厅发布报告称，上半年，我省成品油市场需求旺盛，供应资源充足，供应保障能力增强。全省成品油采购301.4万吨，同比增长22%;销售285.22万吨，同比增长18%;6月底库存43.5万吨，同比增长25%，其中柴油库存增长29%。</P>
<P>上半年，我省建立了江西省成品油市场监管信息平台和网上直报系统，建立了遍布全省的成品油市场运行监测网络，监测样本零售企业达1209家，重点监测企业44家，分布在高速公路、城区、重要国省道和农网，覆盖面达50%，监测率在全国最高。</P>
<P>此外，我省还加强了成品油仓储基础设施建设，中石油九江湖口油库已建成投入使用，全省成品油仓储库容增加5.4万立方米，从而加强了省内成品油市场仓储能力。</P>
<P>【预测】下半年供应偏紧</P>
<P>“下半年，将有多重因素影响我省成品油市场，预计届时我省成品油市场总体需求旺盛，供应偏紧。”业内人士分析说，下半年是成品油市场传统需求旺季，柴油需求旺盛。同时，九江炼油厂将于9月3日~10月3日停产扩能1个月，给省内成品油市场供应带来压力。</P>
<P>同时，电力紧张可能增加成品油市场供应压力，由于电力紧张等原因，柴油市场需求将会增加。此外，“七城会”将增加省内成品油销售，拉动我省旅游等各方面经济发展，对我省成品油销售尤其是汽油销售有明显促进作用。</P>
<P>【应对】将积极组织油源</P>
<P>针对下半年我省成品油市场供应将出现偏紧的情况，省商务厅有关人士透露，我省将进一步加强市场监测预警，密切关注节日及特殊时期成品油市场运行动态。加强成品油市场资源供应和调运，协调督促成品油市场主营企业积极组织油源，加大外采力度，在九江炼油厂停产扩能前继续垫高成品油库存，全力保证我省成品油市场供应需求。</P>
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