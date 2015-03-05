<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>三星在美国起诉西门子子公司LED侵权- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081312d69ed01313acfd5b20223.jsp" title="三星在美国起诉西门子子公司LED侵权" class="cmsHref_self">三星在美国起诉西门子子公司LED侵权</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>三星在美国起诉西门子子公司LED侵权</h1>
					<div class="source">
						<span>发布时间：2011-07-18</span>
						<span>发布人：-搜狐IT  </span>
					</div>
					<p><P>7月18日消息，三星LED公司周日在美国ITC起诉德国欧司朗(Osram)公司，要求美国禁止进口其产品。</P>
<P>欧司朗是德国西门子的一个子公司。三星LED起诉欧司朗侵犯8项专利，主要涉及节能LED灯，它是发光二极管核心技术，被使用在电视、手机显示屏等产品上。</P>
<P>被三星LED列入诉讼对象的包括欧司朗、欧司朗Opto半导体、欧司朗Sylvania，后两者是欧司朗的子公司。</P>
<P>三星LED公司是三星电子、三星电机组建的合资公司。</P>
<P>三星LED同时还说，已经在美国特拉华州地区法院起诉，申请永久禁令禁售欧司朗侵权产品。</P>
<P>不久前，欧司朗在美国和德国起诉三星LED。6月6日，西门子说欧司朗对三星集团和LG集团侵权采取法律行动，并说“通过此举，欧司朗准备强化自己的发光二级管技术专利”。</P>
<P>作为回应，三星LED上个月在韩国反诉欧司朗。在周日的声明中三星表示，它“会继续评估，可能会将那些在美国进口、使用和销售欧司朗LED的企业列入被告”。</P>
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