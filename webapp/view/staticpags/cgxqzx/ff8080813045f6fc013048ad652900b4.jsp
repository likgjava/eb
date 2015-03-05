<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>安徽省将进一步规范采购使用基本药物限价药品行为- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813045f6fc013048ad652900b4.jsp" title="安徽省将进一步规范采购使用基本药物限价药品行为" class="cmsHref_self">安徽省将进一步规范采购使用基本药物限价药品行为</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>安徽省将进一步规范采购使用基本药物限价药品行为</h1>
					<div class="source">
						<span>发布时间：2011-06-01</span>
						<span>发布人：-  </span>
					</div>
					<p><p>针对部分地区近来出现的基本药物限价药品采购量大幅上升的现象，省卫生厅昨天下发通知进一步规范采购使用基本药物限价药品。</p>
<p>我省基本药物中标目录自去年9月20号起执行至今，采购工作开局良好。但是，在全省基本药物采购、使用监测中发现，限价药品采购总额呈逐月递增的趋势，有些品种近几个月在个别县、市、区采购量大幅度上升。有17个县(市、区)限价药品采购金额占总采购金额比例达20%以上，这些地方的群众反映用药贵，看病负担加重。</p>
<p>为此，省卫生下发通知强调基层医疗卫生机构应根据诊疗范围、临床路径及有关规定，合理采购使用基本药物。基层医疗卫生机构的医务人员要按规定合理使用基本药物，应当根据患者的病情，合理开具治疗所需的药品，尽可能减轻群众用药负担，对药品的有关情况要告知患者。</p>
<p>省卫生厅还将各地的采购使用情况纳入到各市实施国家基本药物制度年度目标考核内容之中，并实行每季度通报一次。</p>
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