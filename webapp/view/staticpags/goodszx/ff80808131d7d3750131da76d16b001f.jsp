<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>工程陆续开工 水泥需求旺季降至- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131d7d3750131da76d16b001f.jsp" title="工程陆续开工 水泥需求旺季降至" class="cmsHref_self">工程陆续开工 水泥需求旺季降至</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>工程陆续开工 水泥需求旺季降至</h1>
					<div class="source">
						<span>发布时间：2011-08-18</span>
						<span>发布人：-  </span>
					</div>
					<p><P>8月16日，中金公司发布水泥行业调研报告称，水泥价格现已企稳，随着旺季到来及保障房和水利工程的集中建设，供需情况将得到进一步改善，预计水泥价格下半年至明年将继续上涨。</P>
<P>据了解，华东和华中地区水泥价格8月以来已经稳定，进入9月中旬旺季后将开始上涨，预计涨幅在30-50元;华南广西、广东、海南和贵州价格近两周涨幅在20-40元，主要因广西和贵州限电影响水泥供应，预计华南将成为四季度旺季水泥价格走势最强的区域，价格或超去年高点;而西南四川重庆等地的价格已经走出低谷，下半年将走稳;陕西价格已经低于大部分企业的成本线，基本见底;甘肃因铁路建设放缓明显，当前供需状态不佳，水泥价格走势较弱，预计将在明年下半年回升。</P>
<P>中金公司表示，9-11月，东、中、南部迎来旺季，保障房和水利建设集中施工消费水泥，水泥价格将上涨。往明年看，供应情况继续改善，对增速放缓的水泥需求形成保护。此外，政府于09年颁布的暂停批准兴建新的水泥生产线的38号文件无放开迹象，水泥价格现在并不高，占下游成本低，政府不会限价。</P>
<P>另外，发改委17日还公布，7月份，全国水泥量价齐升，重点建材企业水泥平均出厂价为395.7元(人民币，下同)/吨，环比上涨1.0%，同比上涨34.6%。月末，重点建材企业水泥库存1622万吨，同比下降3.2%。上半年，水泥行业实现利润460亿元，同比增长1.6倍。</P>
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