<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>前苹果供应商认罪 曾泄露iPhone机密- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a7013102065b3906f8.jsp" title="前苹果供应商认罪 曾泄露iPhone机密" class="cmsHref_self">前苹果供应商认罪 曾泄露iPhone机密</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>前苹果供应商认罪 曾泄露iPhone机密</h1>
					<div class="source">
						<span>发布时间：2011-07-07</span>
						<span>发布人：-泡泡网  </span>
					</div>
					<p><P>苹果公司供应商伟创力前高管已经承认串谋泄露苹果机密，其泄露的内容涉及未发布的iPhone和iPad。</P>
<P>据此前资料显示，2009年10月1日，伟创力公司(Flextronics)主管华尔特·西蒙与某家对冲基金的一名雇员通了电话，期间透露了iPhone第三季度实际销售数据，这一数据应于两周半后才会公布，这影响到了苹果的股价。</P>
<P>另外，西蒙还被指控向这名雇员透露，苹果将在下一年发布很棒的手机，配有500万像素摄像头，前置VGA视频摄像头。实际上，8个月后，苹果才发布了这款手机——iPhone4。</P>
<P>西蒙还曾透露苹果一项代号为“K48”的新类别产品，“我猜这可能是一款阅读器，或类阅读器的玩意，这个神秘项目内部名字叫K48”，当然这就是4个月后发布的iPad。</P>
<P>在周二的听审会上，西蒙承认曾接受PGR的每小时200美元酬劳，为对冲基金和投资者提供伟创力及其客户的机密信息。他所承认的共谋罪和证券欺诈罪，将令其在2013年7月8日的法庭审判中面临长达30年的徒刑。</P>
<P>伟创力对此未予置评。</P>
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