<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中石油和壳牌合资石化项目获批- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130b726330130ba0ce2560184.jsp" title="中石油和壳牌合资石化项目获批" class="cmsHref_self">中石油和壳牌合资石化项目获批</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>中石油和壳牌合资石化项目获批</h1>
					<div class="source">
						<span>发布时间：2011-06-23</span>
						<span>发布人：-财新网  </span>
					</div>
					<p><P>国家发改委原则同意浙江台州的炼油化工一体化项目，其中中国石油天然气集团公司持股51%，壳牌和卡塔尔石油国际分别持股24.5%。</P>
<P>台州大石化项目总投资800亿元，原油来自卡塔尔。项目包括每年炼油2000万吨、年生产乙烯120万吨以及30万吨级原油码头等工程。建成后，预计可实现年销售收入1000亿元，并新增3000亿元下游产业及相关配套行业的销售收入。项目有望成为浙江建国以来最大、中国单个引进外资最多的工业项目。</P>
<P>中石油和壳牌最近还签署全球性合作协议，深化两者在中国及海外项目的合作，合作重点包括加拿大油砂资源、非常规天然气开发及工程技术等。双方还计划成立钻井合资公司，各占50%的股份，合资计划仍需政府批准。</P>
<P>随着中国2010年取代美国成为世界上最大的能源市场，中国市场对国际石油公司的重要性日益增加。壳牌公司今年首度将其董事会在中国召开，显示了对中国市场的重视。</P>
<P>国际石油公司一直看好中国国内市场，而中国国家石油公司则倚重国际公司的海外谈判经验及技术优势。在海外项目中，中国公司和国际大公司合作，也可一定程度上规避政治风险。</P>
<P>台州大石化项目可视为中石油和壳牌的合作进一步加深。壳牌与中石油已有不少成功合作的项目。壳牌和中石油2005年签署长北天然气田的产品分成合同，壳牌负责运营。长北气田2007年开始商业生产，目前可向北京和山东等地输送每天30亿立方米的天然气;2010年，两家公司联合收购了Arrow Energy公司，着手开发澳大利亚的煤层气资源;目前双方还在四川盆地联合勘探致密气和页岩气。通过与中国海洋石油公司等合作，壳牌也参与了中国南海的海上石油开发。</P>
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