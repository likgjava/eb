<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中国石油伊拉克公司首船原油发运- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130361e0401303e52105e000d.jsp" title="中国石油伊拉克公司首船原油发运" class="cmsHref_self">中国石油伊拉克公司首船原油发运</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>中国石油伊拉克公司首船原油发运</h1>
					<div class="source">
						<span>发布时间：2011-05-30</span>
						<span>发布人：-  </span>
					</div>
					<p><p>伊拉克当地时间5月28日2时45分，超级油轮九华山号满载200万桶原油缓缓驶离伊拉克巴士拉港口。至此，中国石油在伊拉克合作项目提取的首船原油起锚发运，将横渡印度洋运往中国。</p>
<p>中国石油在伊拉克项目首船原油发运，使鲁迈拉项目成为伊拉克第一轮和第二轮国际招标项目中，第一个实现提油及开始成本回收的项目，这是中国石油在伊拉克地区所有服务合同中的第一次提油。第一船油销往中国，保证国内炼厂的供应，将对这一地区后续的销售工作起到积极推动作用。第一船油的起运，标志着中国石油伊拉克公司鲁迈拉项目已开始成本回收，获取服务报酬，并迈入滚动发展阶段。</p>
<p>鲁迈拉项目是在伊拉克第一轮国际招标中唯一成功中标的巨型油田项目，是中国石油在伊拉克石油战略合作的三个项目之一。2010年7月1日，联合作业机构正式实施对油田作业接管。2010年12月25日，在中国石油、BP公司和伊拉克南方石油公司组成的鲁迈拉联合作业机构的通力合作与共同努力下，提前实现鲁迈拉油田原油初始产量提高10%的IPT目标，兑现向伊拉克政府的承诺，并具备启动投资回收程序的必要条件，实现投资方和资源国利益双赢的格局。</p>
<p>今年年初以来，面对伊拉克复杂局面和多重困难，中国石油伊拉克公司在中国石油天然气集团公司和中国石油天然气股份有限公司的正确领导下，在海外勘探开发公司的精心指导下，继续加强与伙伴公司BP之间的紧密合作，在中联油的全力支持和配合下，一方面加强政府公关，推动伊政府对项目的各项审批;一方面积极敦促加快《原油出口协议》的签署进程。经过3个多月的不懈努力，于4月12日联合签署《原油出口协议》。</p>
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