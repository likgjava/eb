<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>零部件供应恐中断 二三级供应商应寻其他来源- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fe93117012ff6665aac016c.jsp" title="零部件供应恐中断 二三级供应商应寻其他来源" class="cmsHref_self">零部件供应恐中断 二三级供应商应寻其他来源</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>零部件供应恐中断 二三级供应商应寻其他来源</h1>
					<div class="source">
						<span>发布时间：2011-05-16</span>
						<span>发布人：-  </span>
					</div>
					<p><p>日产汽车联盟CEO卡洛斯 戈恩(Carlos Ghosn)日前表示，在3.11日本大地震过去三个月以后，仍然有部分汽车零部件供应商还未摆脱生产困难的状况，不过仍处于运营困难供应商的数量已经降至二十家以下。</p>
<p>戈恩在日本接受采访时表示：&ldquo;少于一半的汽车零部件供应商还没有恢复到正常的生产运营。;戈恩还透露，这将允许日产汽车公司于2011年10月份将全球汽车产能恢复至正常状况。</p>
<p>据悉，日产汽车公司曾于大地震爆发后表示，大约有四十家日本汽车零部件厂商处于地震后的艰难复苏期，这将给日产的整车生产活动的恢复带来不确定性。</p>
<p>戈恩表示，为了阻止未来生产活动因零部件供应问题而中断，日产汽车公司要求旗下的二级和三级供应商寻找其他的生产配件的来源，而这正是日产公司当前最缺乏的。</p>
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