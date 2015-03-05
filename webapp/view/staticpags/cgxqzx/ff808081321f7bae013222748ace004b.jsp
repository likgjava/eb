<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>公务车采购解冻广汽量体造车- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081321f7bae013222748ace004b.jsp" title="公务车采购解冻广汽量体造车" class="cmsHref_self">公务车采购解冻广汽量体造车</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>公务车采购解冻广汽量体造车</h1>
					<div class="source">
						<span>发布时间：2011-09-01</span>
						<span>发布人：-  </span>
					</div>
					<p><P>作为后来者，广汽集团自主品牌选择了夯实基础再发力。面对中国车市放慢增长，广汽乘用车公司将更多精力放在提升内、外部能力上，如生产、内部管理及提升经销商能力，以抓住未来机会。短期内，瞄准公务车采购市场即将解冻，广汽乘用车推出1.8升“传祺”，以求迅速切入公务车采购市场。</P>
<P>值公务车采购解冻前夕，广汽乘用车公司更加不讳言其对公务车市场的觊觎。日前，广汽“传祺1.8L”正式下线并接受预订，指导价从10.98万元17.98万元。由于“传祺1.8L”紧贴公务车采购门槛，业内普遍认为，“传祺1.8L”瞄准的就是公务车采购市场。因为按照《党政机关公务用车配备使用管理办法》明确规定，一般公务用车采购标准为1.8排量、18万元以内。广汽乘用车副总经理徐育林则承认：“1.8L传祺正是为了争抢这一市场。公务车采购已经停止了半年，预计10月各地方政府将启动公务车采购。”</P>
<P>这是一份迟到的蛋糕。广汽乘用车公司总经理吴松表示，“传祺2.0L”上市之初就看好公务车采购市场。但2.0升排量“传祺”刚推出，今年7月新公务车采购标准就出炉了。一般公务用车采购标准将由目前2.0排量、25万元内的标准下调至1.8排量、18万元以内。因此广汽自主品牌不得不提前两三个月推出1.8升版本的“传祺”。在广汽乘用车看来，切入公务车市场，有利于吸引私人消费者。奥迪是最成功的例子。按照规划，“传祺1.8L”的高端车型，主打公务车采购市场;手动舒适版、精英版更多针对私人用户。</P>
<P>当谈及今年市场形势时，吴松并不掩饰自主品牌的困境。上市至今年7月，“传祺”累计销售1万辆左右。这离全年销售3万辆的目标相距甚远，故公司调低了销售目标。吴松说：“现在看来应该只能达到2万辆。”</P>
<P>广汽乘用车公司内部人士表示，短期调低目标，也并非坏事。公司有更多时间用于培训内部员工、提升经销商能力、建设经销商网络上。如果2012年、2013年市场步入快速增长，“传祺”可以抓住机会。</P>
<P>另据了解，广汽乘用车经销网络正呈现快速扩张态势，2011年，广汽乘用车的经销商将增加15家，最终达到65家。</P>
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