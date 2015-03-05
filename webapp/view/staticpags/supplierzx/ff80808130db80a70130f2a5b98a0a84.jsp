<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>三星高管称下半年业务将比上半年更困难- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f2a5b98a0a84.jsp" title="三星高管称下半年业务将比上半年更困难" class="cmsHref_self">三星高管称下半年业务将比上半年更困难</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>三星高管称下半年业务将比上半年更困难</h1>
					<div class="source">
						<span>发布时间：2011-07-04</span>
						<span>发布人：-凤凰网  </span>
					</div>
					<p><P>据《华尔街日报》报道，三星电子芯片业务部门负责人权五铉(KwonOh-hyun)将担任由芯片部门和平板显示屏部门合并之后新组建的设备解决方案业务部门负责人。他说，三星下半年的业务将比上半年更困难。</P>
<P>权五铉称，半导体和平板显示屏生产这两个业务部门去年提供了三星电子70%的经营利润。但是，这两个部门都遇到了困难。</P>
<P>权五铉对新闻记者称，过去，半导体市场一般都是上半年弱一些，下半年强劲一些。但是，今年下半年半导体市场将一直不景气。三星电子发言人证实了权五铉的讲话内容。</P>
<P>权五铉表示，平板显示屏业务正在经历一个长期的下降周期。这项业务今年第一季度出现了亏损。分析师认为，三星平板显示屏业务在截止到上周四的财年第二季度也将亏损。</P>
<P>三星本周将提供财年第二季度的财报预测并且在7月末发布完整的财报。三星将面临艰难的对比。去年第二季度，三星电子的运营利润达到了创纪录的5.01万亿韩元(约47亿美元)。分析师预测三星电子今年第二季度经营利润将下降到4万亿韩元。</P>
<P>三星电子把芯片和液晶显示屏业务部门合并在一起是为了解决三星高管很少讨论的一个较大的结构问题。这个问题就是三星元件业务的客户与三星的手机、电视机、计算机和其它消费电子产品等部门进行竞争。</P>
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