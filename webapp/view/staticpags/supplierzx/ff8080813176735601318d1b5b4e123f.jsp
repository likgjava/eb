<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>上半年有色金属工业增加值同比增长12.3%- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601318d1b5b4e123f.jsp" title="上半年有色金属工业增加值同比增长12.3%" class="cmsHref_self">上半年有色金属工业增加值同比增长12.3%</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>上半年有色金属工业增加值同比增长12.3%</h1>
					<div class="source">
						<span>发布时间：2011-08-03</span>
						<span>发布人：-  </span>
					</div>
					<p><P>8月2日电 工业和信息化部2日公布的数据显示，今年上半年有色金属产业规模保持平稳增长，经济效益明显好转。按可比价格计算，有色金属工业增加值同比增长了12.3%，但增幅比全国工业增加值低2个百分点。</P>
<P>据统计，今年上半年，10种有色金属产量为1655.3万吨，比去年同期增长7.27%，与“十一五”期间年均增幅13.8%相比，出现明显回落。其中，精炼铜251.9万吨、原铝868.6万吨、铅219.7万吨、锌252.9万吨，分别比去年同期增长11.54%、4.40%、23.50%和2.14%。</P>
<P>此外，1-5月全国规模以上有色金属工业企业(主营业务收入2000万元以上的企业，不包括独立黄金企业)实现主营业务收入14467.1亿元，同比增长37.1%;实现利润654.2亿元，同比增长42.4%，增幅比同期全国规模以上工业企业实现利润增幅高14.5个百分点。</P>
<P>在进出口方面，上半年有色金属进出口贸易总额达760.9亿美元，比去年同期增长27.73%。其中，进口额547.7亿美元，比去年同期增长17.87%;出口额213.2亿美元，比去年同期增长62.28%;进出口贸易逆差额为334.5亿美元，同比增长0.28%。</P>
<P>据工信部判断，下半年有色金属工业生产将继续保持平稳增长态势，预计今年全年十种有色金属产量增幅在10%左右，企业经济效益将呈持续增长的态势。</P>
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