<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>日本芯片巨头将提早恢复震前供应- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813078a95601308bab17451229.jsp" title="日本芯片巨头将提早恢复震前供应" class="cmsHref_self">日本芯片巨头将提早恢复震前供应</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>日本芯片巨头将提早恢复震前供应</h1>
					<div class="source">
						<span>发布时间：2011-06-14</span>
						<span>发布人：-  </span>
					</div>
					<p><p>日本芯片巨头瑞萨电子日前称，包括委托其他工厂生产的数量在内，在东日本大地震中受灾的茨城县那珂工厂将于9月底恢复到震前的出货水平。由于恢复进展快于预期，那珂工厂将比原先制定的10月底提前一个月完成目标。</p>
<p>据日本共同社报道，瑞萨的主打产品&ldquo;微控制器&rdquo;广泛用于汽车引擎控制等，震后供应中断对汽车生产也造成了严重影响。社长赤尾泰在那珂工厂召开的记者会上表示，&ldquo;客户也受到了巨大影响，供应的紧张情况(随着出货量的恢复)可以逐步得到缓和，终于松了口气。&rdquo;</p>
<p>本月上旬恢复生产的那珂工厂将从8月底起重新出货。然而，9月底前出货水平仅为震前的35%，其余65%将委托集团其他工厂和其他公司完成生产。</p>
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