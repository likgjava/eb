<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>钛材需求或井喷- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131db79dc0131df8c2aa40241.jsp" title="钛材需求或井喷" class="cmsHref_self">钛材需求或井喷</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>钛材需求或井喷</h1>
					<div class="source">
						<span>发布时间：2011-08-19</span>
						<span>发布人：-  </span>
					</div>
					<p><p>权威人士透露，“十二五”战略性新兴产业发展规划中将重点提及发展高性能钛合金、大型钛板和带材、焊管等。</p>
<p>中投证券的一份报告显示，我国钛材消费以民用为主，航空领域钛材需求占比不到10%，随着国民经济的发展和大客机、大运输机以及大发动机产业的发展，我国钛材消费有望出现爆发性增长。</p>
<p>从中国钛材消费结构来看，化工仍是中国钛加工材第一大用户，用钛量占全国用钛总量的38.6%，第二大用户是体育休闲业，占用钛总量的23.5%，第三大用量是航空航天业，占用钛总量的9.7%。</p>
<p>“未来国内钛材消费有巨大的前景。中国大飞机、航天空间站、舰船、航母等军工领域装备增长与提升都会大量使用钛材，军工领域的发展与突破将给国内钛行业尤其是优势钛材生产商带来巨大的发展空间。”上述报告称。</p>
<p>国都证券的报告也认 为，在航空业逐渐复苏带动 
下，全球商用飞机有望再次迎来订单高峰;受海水淡化、节能与新能源汽车、核电、海工装备、航母舰艇等新兴高端装备产业需求驱动，全球钛工业有望迎来井喷式的发展契机。</p>
<p>综合券商报告，目前钛合金材料领域的上市公司主要为宝钛股份和西部材料。其中，前者高端钛材项目投产，有望成为国内军用钛材需求突破的最大受益者;后者依托西北有色金属研究院的技术，做大做强金属新材料产业，同时还有望受益于第二大股东西安航天科技工业公司的行业地位和影响力，改善公司目前主导产品——钛材产品的结构。</p> 
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