<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>好想你拟购80家专卖店直营 加强销售网络控制力- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081313c08ec01313feaa5f80220.jsp" title="好想你拟购80家专卖店直营 加强销售网络控制力" class="cmsHref_self">好想你拟购80家专卖店直营 加强销售网络控制力</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>好想你拟购80家专卖店直营 加强销售网络控制力</h1>
					<div class="source">
						<span>发布时间：2011-07-19</span>
						<span>发布人：-中国证券网-上海证券报  </span>
					</div>
					<p><P>好想你今日公告，公司拟通过新设立的子公司收购郑州地区约21家经销商约80家好想你专卖店的经营性资产，包括固定资产、店面装修、存货等实体资产，以及门店经营的渠道信息、客户信息、商业关系、知识产权等无形资产，收购金额不超过4000万元。该项交易计划在2011年8月底之前完成。</P>
<P>本项收购预计年投资收益率为29%，投资回收期约3.45年。收购完成后，可为公司增加销售收入约2100万元，增加净利润约480万元。</P>
<P>根据公告，截至2010年底，公司在全国283个城市开设了1819家连锁专卖店，其中公司直营店12家、连锁加盟店1807家。公司2010年通过特许经营模式实现的销售收入占总销售收入的比例为91.86%，通过商场超市模式实现的销售收入占比为4.80%，通过直营模式实现的销售收入占比为1.61%，其他方式实现的销售收入占比为1.72%。</P>
<P>公司表示，特许经营模式是目前公司实现销售的主要模式。但是，公司虽然可以通过特许经销合同对连锁加盟店实施一定的控制，但加盟店与公司的利益并不完全一致，在公司遇到困难时，加盟商可能就会转行或者成为竞争对手的加盟商，不仅对公司销售产生不利影响，也会对公司形象产生负面效应。相反，直营店相对加盟店，更有利于加强各项管理，便于执行公司的各项政策，有利于提升公司品牌形象。</P>
<P>郑州地区是公司发展的起步区和重点区域，市场培育比较成熟。2010年，公司对郑州地区实现的销售收入约占公司总体销售收入的24%。因此，公司拟定将郑州地区作为公司直营店建设的重点。为减少直营店与加盟店的利益竞争，郑州地区直营店建设以收购经销商既有店面并升级改造为主，以新建专卖店为辅。</P>
<P>公司表示，此次资产收购后，加强了公司对销售网络的控制，减少了对加盟商的依赖，有利于公司对销售渠道的升级改造，提升公司品牌形象。</P>
<P>今日，好想你同时公告，公司拟以土地承包经营权转租赁方式从新郑市政府取得“郑州大枣种质资源保护小区”约8096.72亩土地使用权。土地转租赁期16年，每亩土地年租金600元。公司取得土地使用权后，近期主要建设优质红枣原料基地。</P>
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