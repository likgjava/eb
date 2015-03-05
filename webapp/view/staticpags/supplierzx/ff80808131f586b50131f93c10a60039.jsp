<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>新亚纸业新项目因环境违法被环保部勒令停产- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131f586b50131f93c10a60039.jsp" title="新亚纸业新项目因环境违法被环保部勒令停产" class="cmsHref_self">新亚纸业新项目因环境违法被环保部勒令停产</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>新亚纸业新项目因环境违法被环保部勒令停产</h1>
					<div class="source">
						<span>发布时间：2011-08-24</span>
						<span>发布人：-  </span>
					</div>
					<p><p>据环保部网站消息，环保部今日发布关于河南新乡新亚纸业集团的行政处罚决定书，新亚纸业因10万吨APMP杨木化浆技改项目配套建设的环境保护设施未经验收合格即满负荷生产，被环保部责令停止生产，并处罚款十万元。</p>
<p>据中新网能源频道了解，新亚纸业10万吨APMP杨木化浆技改项目2008年12月擅自进行试生产，在配套建设的环境保护设施未经验收合格的情况下，主体工程又满负荷生产。</p>
<p>根据《建设项目环境保护管理条例》第二十八条规定，建设项目需要配套建设的环境保护设施未建成、未经验收或者经验收不合格，主体工程正式投入生产或者使用的，由审批该建设项目环境影响报告书、环境影响报告表或者环境影响登记表的环境保护行政主管部门责令停止生产或者使用，可以处10万元以下的罚款。</p>
<p>根据上述规定，环保部决定责令新亚纸业10万吨APMP杨木化浆技改项目停止生产，罚款十万元，在该项目配套建设的环境保护设施经我部验收合格后方可投入生产。</p>
<p>据中新网能源频道了解，新亚纸业集团是以制浆造纸为主，集热电联产、医药化工、物流商贸、机械制造、林基地开发、环保综合治理于一体的企业集团，是河南省造纸行业龙头企业，河南省循环经济试点企业，全国制浆造纸30强企业。据悉，新亚纸业在2010年9月25日曾进行了陈述申辩，称已启动该项目环保验收工作，并已按照环评批复要求建设了相关环境保护设施，淘汰了相关生产线。</p> 
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