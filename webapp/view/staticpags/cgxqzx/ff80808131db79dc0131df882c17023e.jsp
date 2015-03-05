<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>西安市电子化政府采购工作全面启动- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131db79dc0131df882c17023e.jsp" title="西安市电子化政府采购工作全面启动" class="cmsHref_self">西安市电子化政府采购工作全面启动</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>西安市电子化政府采购工作全面启动</h1>
					<div class="source">
						<span>发布时间：2011-08-19</span>
						<span>发布人：-  </span>
					</div>
					<p><font color="#333333" face="宋体"><span style="font-size: 14px; line-height: 24px;"><p>记者昨日从市政府有关部门获悉，西安市级单位电子化政府采购管理系统已正式上线运行，这标志着电子化政府采购工作在西安市全面启动。</p>
<p>西安市电子化政府采购系统包括采购业务审批、电子化招投标、专家库管理、供应商库管理、网上电子竞价、报表统计以及政府采购门户网站7个子系统，基本上覆盖了政府采购业务的全部过程和各个环节。</p>
<p>通过政府采购操作平台和监管平台，可以实现管理部门、采购人、供应商、评审专家、社会代理机构等政府采购各参与方完成政府采购活动，并使财政部门、纪检部门以及审计部门等监管部门通过电子系统对政府采购活动进行全程监督。</p>
<p>此项改革是对现行政府采购管理模式的一次重大变革，通过制度创新、技术创新提升政府采购工作效率，缩短采购周期,通过更加公开透明的网络采购方式广泛接受社会各界的监督，促进政府采购领域的反腐倡廉工作。</p></span></font>
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