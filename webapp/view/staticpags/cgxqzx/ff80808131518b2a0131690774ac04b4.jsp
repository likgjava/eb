<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>上海青浦区三项措施推进政府采购制度改革- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a0131690774ac04b4.jsp" title="上海青浦区三项措施推进政府采购制度改革" class="cmsHref_self">上海青浦区三项措施推进政府采购制度改革</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>上海青浦区三项措施推进政府采购制度改革</h1>
					<div class="source">
						<span>发布时间：2011-07-27</span>
						<span>发布人：-中华人民共和国财政部  </span>
					</div>
					<p><P>2011年以来，上海青浦区积极健全制度体系、完善管理体制、规范运行机制，不断推进本区政府采购制度改革向纵深发展。</P>
<P>一是积极推进政府采购信息化建设。</P>
<P>今年年初，青浦区正式进入上海市政府采购信息管理平台实施电子集市采购，把政府采购规范化、精细化、科学化、一体化管理的要求全面贯彻到工作推进过程中。</P>
<P>二是加大制度建设力度。</P>
<P>制定并印发了《关于进一步加强和完善区级预算单位政府采购管理的通知》，修订《青浦区政府采购中心采购工作程序》、签订《采购中心工作人员廉洁采购承诺书》，进一步强化制度化管理。</P>
<P>三是加强监督检查。</P>
<P>以开展镇、街道财政性资金管理专项治理工作重点检查为契机，对乡镇街道在政府采购方面的不规范行为和不足之处提出整改意见和建议，进一步规范乡镇街道政府采购行为。</P>
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