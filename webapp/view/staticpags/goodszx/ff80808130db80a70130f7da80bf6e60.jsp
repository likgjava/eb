<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>日本电子零组件供应链预计9月完全恢复- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130f7da80bf6e60.jsp" title="日本电子零组件供应链预计9月完全恢复" class="cmsHref_self">日本电子零组件供应链预计9月完全恢复</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>日本电子零组件供应链预计9月完全恢复</h1>
					<div class="source">
						<span>发布时间：2011-07-05</span>
						<span>发布人：-  </span>
					</div>
					<p><P>日本历经311大地震，大部分东北工厂均受影响，电子零组件及原料的供给链一举被震断。如今根据一份市调，日本供给链将在2011年9月完全复原。</P>
<P>此次311大地震受灾的日本东北地区工厂，受到难以估计的设备及财物损失，但美国市调公司IHSiSuppli却表示，受灾厂商将在2011年9月，也就是地震后半年，随着电子产品及半导体在3Q的热卖的同时恢复到地震前的产能。</P>
<P>目前已有部分厂商的工厂完全复工，另一部分则是受到工厂或设备受损以及停电的阻碍，而迟迟无法开工，其它还有正在逐步复工的工厂。但半导体大厂瑞萨电子(RenesasElectronics)的那珂工厂及德州仪器的美浦工厂均预估要到9月才能完全复工。</P>
<P>根据IHS分析，工厂受创的程度和离震央的距离有很大关系，距离较远的工厂平均只花1~2周的时间复工，而距离较近的可能会需要4~6个月的时间。IHS提到富士通半导体(FujitsuSemiconductor)自2008年起便导入地震防灾系统，所以此次尽管工厂靠近震央，富士通半导体仍为日本半导体业者中恢复最快的厂商，且到6月9日便已有6座工厂都恢复到地震前的产量。</P>
<P>根据IHS报告指出，日本有14家半导体业者、4家硅晶圆受到311大地震的影响。但未来预计全球半导体营收将在2011年3Q达到最高的7.4%，同时也将是日本供应链完全恢复的时刻，并在4Q再度趋缓回到3.1%的成长。</P>
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