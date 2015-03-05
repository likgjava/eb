<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>“阳光采购”节约资金317万元- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813192fe7c0131abff9d171418.jsp" title="“阳光采购”节约资金317万元" class="cmsHref_self">“阳光采购”节约资金317万元</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>“阳光采购”节约资金317万元</h1>
					<div class="source">
						<span>发布时间：2011-08-09</span>
						<span>发布人：-  </span>
					</div>
					<p><P>近日，笔者在华蓥市获悉，今年以来，该市增强部门监督合力，促进政府采购阳光运行。1月至7月，该市采购项目累计预算资金1508万余元，实际采购资金1148万余元，累计节约资金317万余元，节资率达21.05%。</P>
<P>据了解，华蓥市通过制定《关于进一步加强政府采购管理工作的通知》，对部门职责交叉等环节以及实践中出现的新情况予以明确规范。绘制政府采购招标程序、竞争性谈判采购程序、询价采购程序等流程图，严格按照工作规程实施，采购全程接受主管部门、财政监察部门的监督。</P>
<P>该市督促相关单位规范提出采购申请，实行招标项目上网公示，采购中心自建供应商信息库，主动邀请符合招投标资格的供应商，确保供应商的广泛参与。评审专家从四川省政府采购网专家库中随机抽取，以防信息提前泄漏。对开标、评标、定标进行全程监督，杜绝暗箱操作。建立纸质、电子台账备查，确保档案常态化。</P>
<P>同时，该市还引入公开评议和黑名单制度，探索建立对采购单位、评审专家、供应商、集中采购机构和社会代理机构的考核评价制度和不良行为公告制度，对围标、串标、欺诈等行为依法追究责任并向社会公布。</P>
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