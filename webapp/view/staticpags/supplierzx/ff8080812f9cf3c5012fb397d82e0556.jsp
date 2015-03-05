<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>供应吃紧，泰国汽车生产变数泰多- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812f9cf3c5012fb397d82e0556.jsp" title="供应吃紧，泰国汽车生产变数泰多" class="cmsHref_self">供应吃紧，泰国汽车生产变数泰多</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>供应吃紧，泰国汽车生产变数泰多</h1>
					<div class="source">
						<span>发布时间：2011-05-03</span>
						<span>发布人：-  </span>
					</div>
					<p><p>据泰国泰华农民研究中心报告称，2011年伊始，泰国汽车市场仍然保持快速增长势头，虽然此前曾经预期汽车市场因2010年的比较基数较高原因，2011年汽车市场会呈现减缓趋势，但是最新数据显示，2011年第1季泰国汽车市场仍然保持年增43.1％的高水平。</p>
<p>不过，2011年泰国汽车市场除需要面对市场需求风险、以及油价随着中东和北非国家紧张局势升级而上涨压力外，还将面临供应断链的风险。日本大地震引发汽车组装配件供应短缺的问题，导致目前市场越来越担心，配件匮乏问题会随着时间推移变得更加严重，可能导致汽车市场放慢增长，甚至出现萎缩情况，尤其是即将到来的第2季。</p>
<p>假设未来形势较好，即2011年下半年汽车配件短缺得到缓解，汽车生产企业还可以赶工交货，有助减轻对2011年汽车销售总额造成的影响。在这种假设情况下，泰华农民研究中心预期，2011年全年泰国汽车销售总量还可保持约6-12％的增长速度，相当于85-90万辆，增幅低于2010年的45.8％，汽车销量则高于2010年的80万357辆；全年汽车产量仍能够实现原定180万辆的目标。</p>
<p>如果假设形势较坏，即汽车配件短缺情况因日本国内电力不足，直至2011年第3季仍未好转，汽车生产企业无法如第1个假设情形般赶工交货。</p>
<p>研究中心预期，2011年全年泰国汽车销售总量仅增长4-6％，销量介于83-85万辆之间；全年汽车总产量势必受到影响，以至低于原定全年生产180万辆汽车的目标。</p>
<p>至于减产数量到底会是多少，目前则暂无法预测，因为汽车配件短缺情况还未明朗。此问题关系重大，接下来需要密切关注，尤其日本此波地震尚未平静，余震还持续不断，加上其国内电力供应不足问题还未得到解决，而且用电高峰夏季也即将来临。</p>
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