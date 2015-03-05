<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>大陆企业在台达成3项采购合约价值24亿美元- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813140dc47013144fde3e7031d.jsp" title="大陆企业在台达成3项采购合约价值24亿美元" class="cmsHref_self">大陆企业在台达成3项采购合约价值24亿美元</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>大陆企业在台达成3项采购合约价值24亿美元</h1>
					<div class="source">
						<span>发布时间：2011-07-20</span>
						<span>发布人：-新华网  </span>
					</div>
					<p><P>由蒋耀平率领的两岸经贸促进考察团目前正在台湾开展经贸促进活动。随团来台的华为、中国电信以及广东的两家企业，19日在此间与岛内相关企业举行签约仪式，将采购总计24亿美元的产品。</P>
<P>当日签订的3项采购合约分别为：华为技术公司向台湾富士康、百一电子采购电信设备、电子零件、连接器;中国电信与台达电签约采购ADSL局端设备、开关电源与配电设备;广东佛山大晟农产品与广州恒通和顺进出口公司向台湾的台粤农产开发公司签约采购相关产品。</P>
<P>华为技术有限公司副总裁蒋亚非表示，2010年华为向100多家台湾企业采购了价值995亿元新台币的产品，今年采购金额预计将超过1100亿元新台币。</P>
<P>19日当天，两岸经贸促进考察团在台北举行了首场采购洽谈会，据台北世界贸易中心介绍，当天的采购洽谈会总计有36家大陆企业参加，其中有22家大陆企业将采购ECFA早收清单产品。出席采购洽谈会的台湾厂商包括台湾力丽、台湾化学纤维、台塑、友讯科技、东讯、嘉义县梅山茶油生产合作社等90家台湾企业。</P>
<P>据台北世贸初步统计，本次大陆企业来台考察采购的项目包括纺织布料、毛巾、小家电、纺织机械、聚丙烯、水果、茶叶、冷冻水产、工具机及零件、五金钢材等ECFA早收清单产品。其他采购项目包括生活用品、食品、机械等。</P>
<P>另据了解，考察团还将于22日在高雄办理第2场采购洽谈会，目前台湾企业已有奇美实业、阿里山联兴茶叶、屏东县长庆果菜运销合作社等62家中南部厂商报名参加。</P>
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