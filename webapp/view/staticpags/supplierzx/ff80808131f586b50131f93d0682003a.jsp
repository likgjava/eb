<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>锦湖轮胎南京工厂因污染被勒令搬迁- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131f586b50131f93d0682003a.jsp" title="锦湖轮胎南京工厂因污染被勒令搬迁" class="cmsHref_self">锦湖轮胎南京工厂因污染被勒令搬迁</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>锦湖轮胎南京工厂因污染被勒令搬迁</h1>
					<div class="source">
						<span>发布时间：2011-08-24</span>
						<span>发布人：-  </span>
					</div>
					<p><p>据《科技日报》报道，近日在南京市环境保护局日前宣布的关停并转的173家“三高两低”工业企业名单中，锦湖轮胎南京工厂位于“搬迁”之列。</p>
<p>早在一个月前，锦湖轮胎在华第三家工厂南京工厂就被南京市环保局列为市重点污染企业。当地的扬子晚报报道称“锦湖南京基地因污染问题被责令停产”。据称锦湖轮胎工厂在半夜冒黑烟，偷排废气。</p>
<p>污染停产一事曾令锦湖传出要退出中国的消息。对此，有锦湖轮胎相关负责人回应称，锦湖南京工厂已在就所散发出的气味问题采取多项治理措施，并应政府的要求正在规划搬迁等事宜。</p>
<p>据介绍，1994年锦湖轮胎入驻当时人烟稀少的燕子矶地区。轮胎工厂本身就是污染比较严重的企业，锦湖轮胎工厂曾就环境问题多次作出治理。有人称，根据环保局的要求，锦湖南京工厂将从2012年底开始搬迁，新工厂已在选址中。</p>
<p>不过，南京市环境保护局局长韦昌明在接受媒体采访时透露，锦湖轮胎总部高层曾专程前往南京，与有关部门进行沟通、协商。目前正在进行规划选址，明年初有望搬入江北的化工园区。</p> 
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