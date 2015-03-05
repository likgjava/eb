<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中国最大蛋企要求CNN向全球皮蛋消费者道歉- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130fceedf3401b8.jsp" title="中国最大蛋企要求CNN向全球皮蛋消费者道歉" class="cmsHref_self">中国最大蛋企要求CNN向全球皮蛋消费者道歉</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>中国最大蛋企要求CNN向全球皮蛋消费者道歉</h1>
					<div class="source">
						<span>发布时间：2011-07-06</span>
						<span>发布人：-荆楚网  </span>
					</div>
					<p><P>近日，美国有线电视新闻网CNN 评全球十大恶心食品，将中国传统食品皮蛋列为第一，此事在中国引来一片抗议声。7月4日，我国最大的蛋品加工企业——湖北神丹健康食品有限公司，以董事长刘华桥及3000名员工的名义正式发函抗议，要求美国CNN道歉。</P>
<P>《抗议信》中说，将鲜鸭蛋腌制成皮蛋是中国人民的伟大发明，至今已有数百年的历史，在中国及世界各地有二十亿消费者，深受人们喜爱。鲜鸭蛋腌制成皮蛋后，胆固醇含量下降20%以上，蛋白质与脂质被分解，更容易被人体吸收。中国的传统医学认为，皮蛋性凉、味辛，有解热、去肠火、治牙疼、去痘等食疗功效。皮蛋瘦肉粥还是肯德基(KFC)的畅销产品。</P>
<P>《抗议信》指出，美国CNN将皮蛋评为十大恶心食品之首，没有科学依据，评选者本人不是皮蛋消费者，没有亲身感受。他们敢对其他国家传统食品说三道四，肆意贬损，源于他们的无知和傲慢，是对他国文化习俗的不敬。神丹公司董事长及员工对CNN的评选行为表示强烈抗议，要求他们取消该评选结果，并向中国及世界各地的皮蛋消费者道歉。</P>
<P>神丹公司表示，为弘扬中华传统饮食文化，将保留进一步诉诸法律的权力。</P>
<P>附：抗议信</P>
<CENTER><IMG border=1 alt=抗议信。 src="http://i3.sinaimg.cn/cj/2011/0705/U1985P31DT20110705164556.jpg" width=450 height=537></CENTER>
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