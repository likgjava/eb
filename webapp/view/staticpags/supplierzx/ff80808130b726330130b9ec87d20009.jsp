<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>红帽第一财季净利润4700万美元 同比增32%- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130b726330130b9ec87d20009.jsp" title="红帽第一财季净利润4700万美元 同比增32%" class="cmsHref_self">红帽第一财季净利润4700万美元 同比增32%</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>红帽第一财季净利润4700万美元 同比增32%</h1>
					<div class="source">
						<span>发布时间：2011-06-23</span>
						<span>发布人：-新浪科技  </span>
					</div>
					<p><P>北京时间6月23日凌晨消息，红帽今天发布截至2011年5月31日的 2012财年第一季度财报。上一财季，红帽总营收达2. 647亿美元，同比增27%;按非美国通用会计准则计算，净利润为4700万美元，同比增长32%。</P>
<P>上一财季，按美国通用会计准则计算，红帽运营利润为4540万美元，运营利润率为17.1%;按非美国通用会计准则计算，红帽运营利润为6650万美元，同比增长28%;运营利润率为25.1%，同比增长30个基点。</P>
<P>上一财季，按美国通用会计准则计算，红帽净利润为3250万美元，稀释后每股收益为0.17美元;去年同期分别为2410万和0.12美元。按 非美国通用会计准则计算，红帽净利润为4700万美元，稀释后每股收益为0.24美元;去年同期分别为3560万和0.18美元。</P>
<P>上一财季，红帽运营现金流为9020万美元，去年同期为6060万美元。截至季末，红帽递延收入为7.86亿美元，同比增长26%;现金、现金等价物和投资总额为12.7亿美元。</P>
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