<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>新措稳定生猪生产保供应- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/goodszx.jsp" title="供货资讯" class="cmsHref_self">供货资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70131074243830bc2.jsp" title="新措稳定生猪生产保供应" class="cmsHref_self">新措稳定生猪生产保供应</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>新措稳定生猪生产保供应</h1>
					<div class="source">
						<span>发布时间：2011-07-08</span>
						<span>发布人：-中安在线-安徽日报  </span>
					</div>
					<p><P>大力发展生猪标准化规模养殖;力争实现能繁母猪应保尽保;发挥项目资金引导带动作用</P>
<P>今年以来，我省生猪生产整体保持稳定发展的态势，但也出现部分散养户加快退出，规模养殖发展增速趋缓，特别是个别地区中小养殖场母猪繁殖性能和仔猪成活率下降的问题。对此，日前省畜牧局发出通知，要求各地落实政策和措施，以稳定生猪生产，确保市场供应。</P>
<P>通知要求，各级畜牧兽医主管部门要大力发展生猪标准化规模养殖，积极推进生猪规模场和养殖小区的标准化改造，支持规模场、养殖小区的粪污处理和沼气池等基础设施建设，提升设施装备水平。研究解决规模养殖发展中用地难、贷款难和技术缺乏等方面的实际问题。各地要强化生猪生产和市场价格动态监测，及时发布市场预警信息，积极引导养殖者正确认识形势，指导养殖户合理安排生产，实现生猪平稳生产和增产增收。各地要加强技术指导和服务，从母猪和商品仔猪选择、饲养管理、疫病防控等方面，多渠道、多形式宣传推广相关技术，指导养殖场户改进饲养管理技术，提高生猪生产水平。</P>
<P>通知强调，各地要抓好口蹄疫、高致病性猪蓝耳病、猪瘟等生猪重大疫病基础免疫，加强疫情监测预警和动物标识及可追溯体系建设，强化动物卫生监督管理。抓紧落实扶持生猪生产的各项政策，继续推进能繁母猪保险工作，努力扩大保险覆盖面，力争实现能繁母猪应保尽保的目标。加快落实生猪调出大县奖励政策、生猪标准化规模养殖场小区建设项目和生猪良种补贴项目，切实发挥项目资金的引导和带动作用，确保各项强农惠农政策尽快落实到场户。</P>
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