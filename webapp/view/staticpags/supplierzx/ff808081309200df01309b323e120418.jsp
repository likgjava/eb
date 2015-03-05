<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中粮以商标侵权起诉福宜康生产销售商- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081309200df01309b323e120418.jsp" title="中粮以商标侵权起诉福宜康生产销售商" class="cmsHref_self">中粮以商标侵权起诉福宜康生产销售商</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>中粮以商标侵权起诉福宜康生产销售商</h1>
					<div class="source">
						<span>发布时间：2011-06-17</span>
						<span>发布人：-  </span>
					</div>
					<p><p>近日，中粮集团将生产&ldquo;福宜康&rdquo;食用油的生产商、销售商起诉到北京市丰台区法院，理由是&ldquo;福宜康&rdquo;食用油酷似&ldquo;福临门&rdquo;，并要求被告停止侵害并赔偿损失、公证费、律师代理费等共计305135元。</p>
<p>中粮集团起诉称，集团于2003年取得第29类商品中食用油的商标专用权&ldquo;福临门&rdquo;，是该注册商标的合法持有人。2011年3月，集团在个体户宗先生经营的商铺发现 &ldquo;福宜康&rdquo;食用油，在注册商标的字样、商标、商品等方面，均与&ldquo;福临门&rdquo;食用油相似。</p>
<p>中粮集团认为，北京金亭建鑫食用油有限公司未经其许可，在同一种商品或类似商品上使用与其注册商标相同或近似的商标，侵犯了其注册商标专用权。</p>
<p>据悉，金亭建鑫食用油有限公司成立于2005年，主要经营食用油分装、销售。记者致电该公司负责人张志成，他表示目前还不知道该消息，也未收到法院的通知。&ldquo;&quot;福宜康&quot;的商标注册证去年10月份已经下来了，但目前产品并没有在北京卖，我也没见过起诉书中提到的宗先生。&rdquo;张志成说。</p>
<p>然而，记者在中国商标网上并未查询到&ldquo;福宜康&rdquo;的注册信息，倒是该公司的另一品牌&ldquo;金康鱼&rdquo;食用油显示注册人是张志成，而他却表示&ldquo;金康鱼&rdquo;商标还在申请注册中。</p>
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