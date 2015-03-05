<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>双鹤药业万东医疗划归华润旗下- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130e3380ac70625.jsp" title="双鹤药业万东医疗划归华润旗下" class="cmsHref_self">双鹤药业万东医疗划归华润旗下</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>双鹤药业万东医疗划归华润旗下</h1>
					<div class="source">
						<span>发布时间：2011-07-01</span>
						<span>发布人：-生活新报  </span>
					</div>
					<p><P>6月27日，双鹤药业、万东医疗双双发布公告，称两家公司的共同大股东北京医药集团有限责任公司(下称北药集团)实际控制人发生变更，原来北京国有资本经营管理中心(下称北京国管中心)和中国华润总公司(下称华润)各掌北药集团半壁江山的局面被打破，转而由华润绝对控股。</P>
<P>北药集团原本由北京国管中心及华润各持股50%，根据公告，北京国管中心拟将持有的北药集团1%国有股权无偿划转给华润，划转后，北京国管中心对北药集团持股比例降为49%，华润则通过其全资子公司华润北药投资有限公司(下称华润北药)和其自身共持有北药集团51%的股权，成为双鹤药业及万东医疗的实际控制人。北药集团持有双鹤药业49.12%的股权，持有万东医疗51.51%的股权，是两公司的共同大股东。</P>
<P>早在2010年7月，双鹤药业和万东医疗即公告了华润、北药集团重组协议，根据协议，为打造央企医药平台，整合内部医药资源，华润和北药集团医药类资产进行重组。重组完成后，华润和北药集团的医药类资产将被纳入一家合资公司，北京国管中心和华润将共同直接、间接持有合资公司的全部股权。此次1%国有股权划转，也预示着华润在重组方面的主导地位。</P>
<P>这也表明，随着华润与北药集团重组的不断推进，华润、国药集团、上药集团医药巨头“三足鼎立”的局面逐渐形成。公开资料显示，2009年按年销售规模计算，国药集团居我国百强医药商业企业之首。而上药集团旗下的上海医药已率先完成“三合一”整体上市，成为目前国内A股最大的医药类上市公司，并于2011年5月份成功登陆港交所主板，成为内地首家A+H大型医药上市公司。6月27日，双鹤药业收报24.4元，与上一交易日持平;万东医疗收报13.13元，涨0.08%。</P>
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