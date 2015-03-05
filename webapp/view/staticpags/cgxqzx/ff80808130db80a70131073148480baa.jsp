<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>联合国采购专家给中国电子企业“集体补课”- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70131073148480baa.jsp" title="联合国采购专家给中国电子企业“集体补课”" class="cmsHref_self">联合国采购专家给中国电子企业“集体补课”</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>联合国采购专家给中国电子企业“集体补课”</h1>
					<div class="source">
						<span>发布时间：2011-07-08</span>
						<span>发布人：-中国新闻网  </span>
					</div>
					<p><P>中新社青岛7月7日电 “中国作为全球电子制造业大国却仅占联合国跨国采购全球份额的1%，说明中国电子企业在提升实力的同时该补‘ 跨国采购’课了。”联合国采购专家、中国联合国采购促进会官员朱毅7日在此间举行的2011中国国际消费电子博览会中接受记者采访时做如上表示。</P>
<P>作为规模仅次于美国拉斯维加斯国际消费电子展(CES)的世界第二大消费电子博览会，2011中国国际消费电子博览会在展示国际顶尖电子科技成就的同时，积极搭建跨国采购平台。本届博览会首次邀请联合国采购促进中心相关专家，举行联合国跨国采购说明会，对中国电子企业就跨国采购进行“集体补课”。</P>
<P>朱毅介绍说，由于联合国对中国企业不熟悉、中国企业不了解联合国采购规则、信息沟通不畅等原因，2010年联合国在中国的采购额约为1.4亿美元，这个数字仅为全球份额的1%，这与中国作为拥有联想、海尔等众多国际知名电子品牌的制造业大国极不相符。在中国举办联合国跨国采购说明会，可以向参会企业介绍联合国采购的规则、程序、主要采购的产品及服务种类，以及与联合国进行商贸活动的经验技巧，帮助企业了解联合国采购规则和程序，从而使中国企业与联合国各机构建立长期、稳定的合作关系。</P>
<P>朱毅表示，计算机硬、软件，通信设备，影印设备，办公设备，冷藏设备等是联合国采购的重要类别，而中国的电子企业在这些领域均具备一定的国际竞争能力。此次说明会旨在有针对性地帮助更多的中国消费电子企业成为联合国注册供应商，并通过联合国采购这个渠道使更多的消费电子企业进入到全球贸易市场。</P>
<P>朱毅认为，作为前沿消费电子产品、技术发布平台，中国消费电子博览会吸引了众多世界主流消费电子零售商，如美国百思买、DESA、英国乐购、泰国易初莲花等，总采购额初步估计超过上亿美元。这表明中国不但拥有巨大的市场，而且中国企业具有很强的国际竞争力。他相信，中国企业进入联合国采购渠道后会拥有更大的市场，而且可以通过联合国采购进入全球采购系统中，借助此平台更好地参与国际竞争。</P>
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