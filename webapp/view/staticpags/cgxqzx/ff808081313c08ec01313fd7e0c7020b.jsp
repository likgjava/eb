<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>夏普联手鸿海降低面板采购成本- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081313c08ec01313fd7e0c7020b.jsp" title="夏普联手鸿海降低面板采购成本" class="cmsHref_self">夏普联手鸿海降低面板采购成本</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>夏普联手鸿海降低面板采购成本</h1>
					<div class="source">
						<span>发布时间：2011-07-19</span>
						<span>发布人：-孙聪颖  </span>
					</div>
					<p><P>夏普开始与鸿海在面板领域强强联合。据报道， 夏普会长町田胜彦表示，夏普已和台湾鸿海签署了合作契约，双方各出资一半，计划于今年内在台湾设立一家合资公司,除了共同采购电视用液晶面板材料之外，也将相互供应面板。</P>
<P>台湾鸿海旗下拥有面板大厂奇美电，故双方期望通过技术合作以及共同采购的方式来节约成本。在面板供应方面，奇美电将供应价格竞争激烈的20-40英寸面板产品给夏普，夏普则将供应60英寸以上超大尺寸面板给奇美电。行业机构DisplaySearch的数据显示，在电视用大尺寸液晶面板市场上，夏普和奇美电2010年合计市占率达24.5%，直逼行业龙头三星电子，2010年三星液晶面板的市场份额是25.5%。</P>
<P>报道还称，鸿海也有意与夏普在白色家电等领域进行合作。对于以上消息 ，夏普商贸中国企划部负责人称，目前还没有收到总部的正式通知。</P>
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