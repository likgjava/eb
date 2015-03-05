<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>光明食品拟收购一家澳大利亚葡萄酒企业- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f2ab2b820a94.jsp" title="光明食品拟收购一家澳大利亚葡萄酒企业" class="cmsHref_self">光明食品拟收购一家澳大利亚葡萄酒企业</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>光明食品拟收购一家澳大利亚葡萄酒企业</h1>
					<div class="source">
						<span>发布时间：2011-07-04</span>
						<span>发布人：-网易财经  </span>
					</div>
					<p><P>知情人士透露目前总部位于上海的光明食品集团正在考虑对澳大利亚葡萄酒企业TreasuryWineEstates提出收购报价。该知情人士表示，光明食品已经与Treasury公司就收购后者进行了多次内部谈判。</P>
<P>据悉，Treasury公司曾经是澳大利亚福斯特集团(Foster’sGroupLtd.)旗下的一个部门，目前Treasury公司的市值为22亿澳元(合24亿美元)。据悉，在减记25亿澳元之后，福斯特集团于今年5月10日将TreasuryWineEstates拆分为新葡萄酒业务公司，使福斯特集团可专注于其占有的澳大利亚50%的啤酒市场份额。</P>
<P>另悉，福斯特集团是澳大利亚最大的啤酒生产商，也是世界第二大酿酒商，该集团拥有Lindemans及Penfolds等著名品牌。福斯特集团的发言人瑞贝卡-史密斯(RebeccaSmith)对此拒绝置评。</P>
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