<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>2011中国电子信息百强企业揭晓 华为居榜首- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01305313e42706e6.jsp" title="2011中国电子信息百强企业揭晓 华为居榜首" class="cmsHref_self">2011中国电子信息百强企业揭晓 华为居榜首</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>2011中国电子信息百强企业揭晓 华为居榜首</h1>
					<div class="source">
						<span>发布时间：2011-06-03</span>
						<span>发布人：-  </span>
					</div>
					<p><p>6月2日，2011年电子信息百强企业评选在广东省惠州市揭晓，华为技术、联想、海尔、长城科技、中兴通讯、海信、长虹电子、TCL、北大方正、比亚迪等分列前10名。惠州本土企业TCL、德赛、华阳、侨兴、光弘科技等5家企业入选百强。</p>
<p>中国电子信息百强企业评选由工信部主办，从规模、效益、研发能力3方面进行综合评价。本届百强企业主营业务收入合计为1.5万亿元人民币，比上届增长20%。其中，主营业务收入超百亿的企业有27家;华为、联想、海尔三家企业主营业务收入超过1000亿元。本届百强企业入围门槛为20亿元，比上届提高1亿元。</p>
<p>据悉，电子百强评选已是第25届。1987年首届百强企业中90%以上为从事彩电整机及配套件生产厂家，本届百强中从事软件、元器件、材料等企业数量达40余家。有数家企业凭借多晶硅、动力电池行业首次跻身百强行列;部分百强企业把握新一代信息技术产业发展机遇，已将战略重点转向移动智能终端、云计算、物联网等新兴领域。</p>
<p>工信部副部长杨学山认为，电子信息百强企业通过战略转型、科技创新、国际经营、兼并重组、生态发展成为推动行业增速回升和加快转变发展方式的中坚力量。但电子信息产业需要靠规模经济，中国电子信息企业相比较海外而言，在整机、元器件、软件等方面市场占有率不高，规模还不够大，希望企业继续做大做强，提高创新能力。</p>
<p>由19家从事OLED产品及应用研究、开发、制造、服务的企、事业单位及有关机构自愿组成的中国OLED产业联盟也于当天宣布成立。</p>
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