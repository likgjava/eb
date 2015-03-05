<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>日本半导体元件供应情况大致为何？- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fd7addab80ceb.jsp" title="日本半导体元件供应情况大致为何？" class="cmsHref_self">日本半导体元件供应情况大致为何？</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>日本半导体元件供应情况大致为何？</h1>
					<div class="source">
						<span>发布时间：2011-05-10</span>
						<span>发布人：-  </span>
					</div>
					<p><p>目前日本半导体元件供应情况大致为何?根据市调机构Gartner的预估，包括微控制器、离散元件、电源和类比元件这三大项目可能会受到明显的影响。</p>
<p>全日本有206座晶圆厂，其中82座位于东北地震海啸受灾区域之内， Gartner研究总监王端表示，目前应该有一些半导体整合制造厂正在与晶圆代工厂讨论转移生产的课题，不过最快也需要6个月受到影响的半导体整合制造晶圆厂的产能，才会恢复正常。</p>
<p>王端指出，日本半导体厂的产能正在恢复，但是依旧受限，原因在于电力供应不足且不够稳定，水和交通物流的供应也有问题存在。值得注意的是，消费电子所需要的关键半成品和原物料依旧有供应不足的隐忧，这些关键的半成品和原物料是矽晶圆材料、电池芯、封装材料BT树脂(BTresin)、玻璃纤维、防焊绿漆(soldermaskfiller)和超薄铜箔(thin-copperfoil)。</p>
<p>另外王端表示，日本供应全球65%的半导体用矽晶圆，主要大厂为信越化学(Shin-Etsuchemical)和SUMCO，特别是在12寸矽晶圆材料部份，这两家大厂为主要供应厂商。信越化学位于白川乡的生产基地先前关闭了大型300mm矽晶圆的生产线，已经威胁到全球18%的矽晶圆供应量，不过在4月20日部份产能已经恢复运作，信越化学预估到6月之前出货供应将可恢复正常。因此Gartner预估矽晶圆供应短缺的问题将可获得解决。</p>
<p>至于BT树脂是用在先进半导体封装制程的聚氨酯基板(laminatesubstrate)关键材料，有一些手机的PCB板也会用得上。三菱瓦斯化学(Mitsubishi Gas Chemical)因地震关掉的两座工厂产能就供应全球90%的BT树脂，因此引起市场高度关注。不过三菱瓦斯化学已经宣布将可在5月恢复正常产能。</p>
<p>此外在电池芯部份，包括Sony在内的主要日本锂电池芯供应大厂，都受到程度不一的影响。不过由于业者还有大概2个月的库存水位，因此目前笔电和智慧型手机的锂电池供应暂时还不会受到干扰，不过到下一季有些电子产品的锂电池供应可能会受到影响。</p>
<p>王端认为，低获利的电子产品能会因为关键原物料和零组件价格的提高而受到冲击。仍然还有一些关键电子零组件目前的生产状况还没被外界所瞭解，值得继续密切关注。</p>
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