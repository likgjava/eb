<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>日本311大地震促使三菱电机半导体供应链走向多样化- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01307728fab04312.jsp" title="日本311大地震促使三菱电机半导体供应链走向多样化" class="cmsHref_self">日本311大地震促使三菱电机半导体供应链走向多样化</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>日本311大地震促使三菱电机半导体供应链走向多样化</h1>
					<div class="source">
						<span>发布时间：2011-06-10</span>
						<span>发布人：-  </span>
					</div>
					<p><p>在Sankei Biz日前的一次访谈中，三菱电机的社长山西健一郎提到，三菱电机在本次日本311大地震中，只有1个主要生产监视相机等产品的福岛县郡山工厂受到影响，公司整体而言没有蒙受太大损失。</p>
<p>在三菱电机的关系企业中，也只有生产汽车零件用半导体的瑞萨电子(Renesas Electronics)受到地震的影响。瑞萨为三菱电机重要半导体零件如微处理器等的供应伙伴，三菱电机也有约60人以工程师为主的小队常驻在瑞萨电子。所幸三菱电机的汽车零件库存到5月为止仍然足够，瑞萨在灾后生产线的恢复又迅速，所以可望将损失降到最低。</p>
<p>经过这次地震后，因影响到部分只有在茨城县那珂工厂生产的零件的供给，使得三菱公司内部已开始研究改变零件采购及生产体制的可行性。并计划将原来日本国内的2处补给点，改为由日本东北及九州岛等复数的工厂供货，同时加上透过委托海外如台湾等晶圆代工等方式分散风险。</p>
<p>三菱电机的山西社长宣称，加强海外的生产比例是其公司从地震前便开始的计划。公司尤其重视扩大在美国及亚洲地区的生产规模这部分。透过这计划的实行，三菱电机的海外生产比例在2010年达到了33%~34%的新高。此后他们也将继续推动，并将目标设为从2013年起尽快达到40%的程度。</p>
<p>山西社长并表示，受到震灾的启示，今后日本将对于再生能源及智慧电网更加的重视。而三菱电机本身已有生产太阳能电池模块与电力控制设备。另外在兵库县的尼崎市也从去年开始进行智慧电网的实证实验。目前唯一的难题在于智慧电网在利用再生能源等的时候，会造成电费的提高。但是日本在地震后因节省用电的观念高涨，使得电费涨价可望因用电量的减少而打平。</p>
<p>在今年夏天，三菱电机公司本身也响应日本政府的省电措施，透过将公司内的照明设备改为LED等方式，达到日本政府提出的15%的省电目标。</p>
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