<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>开鲁县上半年采购规模同比增长80%- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131183592013120f7fbcb0e5c.jsp" title="开鲁县上半年采购规模同比增长80%" class="cmsHref_self">开鲁县上半年采购规模同比增长80%</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>开鲁县上半年采购规模同比增长80%</h1>
					<div class="source">
						<span>发布时间：2011-07-13</span>
						<span>发布人：-  </span>
					</div>
					<p><P>截至6月末，内蒙古开鲁县政府采购中心共完成采购267批(次)，采购项目预算总金额为4262万元，实际采购支出为3752万元，节约资金510万元，资金节约率为12 %。采购规模与上年同期相比增加2085万元，增长80%，实现了时间过半、任务超半的采购工作目标。</P>
<P>另据了解，开鲁县政府采购工作今年将根据财政部部署的2011年政府采购工作要点，重点做好以下五项工作:一是继续扩大政府采购实施范围和规模,加大对工程类和服务类采购项目的实施力度，确保政府采购规模不断增长;二是规范完善各项基础工作，制定采购文件编制、采购合同等标准文本;三是做好政法专项经费和校安工程采购项目的采购工作;四是充分发挥政府采购的政策功能，不断提高节能环保产品的采购份额;五是创新政府采购宣传形式,继续加大政府采购宣传力度,为深化政府采购制度改革营造良好的氛围。</P>
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