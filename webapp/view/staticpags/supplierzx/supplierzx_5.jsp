<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>供应商资讯- 【阳光易购】</title>
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
		
				</div>
				<div class="formTips attention">
					<ul><li><em>网站公告展示：</em>发布网站公告，展示网站信息</li></ul>
				</div>	
				<div class="simpleSearch">
					<ul>
						<li><label> 关键字：</label>
							<input type="text" name="searchKeyContentMain" id="searchKeyContentMain" value="" style="width:120px"/>
						</li>
						<li><label> 发布时间：</label>
							<input type="text" name="startTime" id="startTime" readonly="readonly" style="width:80px"/>&nbsp;至
							<input type="text" name="endTime" id="endTime" readonly="readonly" style="width:80px"/>
						</li>
						<li><button type="button" id="searchNewsContentMainBtn"><span>搜索</span></button></li>
					</ul>
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<table class="frontTableList" id="qualityTable">
					<thead>
						<tr>
							<th class="left">标题</th>
							<th class="center">时间</th>
						</tr>
					</thead>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131b1335d361c95.jsp" class="cmsHref_self" title="动车故障多涉外购件 长客股份把关不严被整顿">动车故障多涉外购件 长客股份把关不严被整顿</a></td>
							<td style="text-align:right">2011-08-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131b132ba6d1c94.jsp" class="cmsHref_self" title="五粮液广告赴美被质疑浪费 回应称并非主动要求">五粮液广告赴美被质疑浪费 回应称并非主动要求</a></td>
							<td style="text-align:right">2011-08-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131b13249501c93.jsp" class="cmsHref_self" title="万科副总裁袁伯银闪电离职 郁亮称遭遇中年危机">万科副总裁袁伯银闪电离职 郁亮称遭遇中年危机</a></td>
							<td style="text-align:right">2011-08-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131b131635f1c92.jsp" class="cmsHref_self" title="包钢稀土欲吃独食 众企业不买账自寻出路">包钢稀土欲吃独食 众企业不买账自寻出路</a></td>
							<td style="text-align:right">2011-08-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131ac2255631434.jsp" class="cmsHref_self" title="索尼与三星联手推动3D眼镜技术标准化">索尼与三星联手推动3D眼镜技术标准化</a></td>
							<td style="text-align:right">2011-08-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131ac0f76971426.jsp" class="cmsHref_self" title="万科上半年净利30亿元同比增长5.9%">万科上半年净利30亿元同比增长5.9%</a></td>
							<td style="text-align:right">2011-08-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131ac0ed4921425.jsp" class="cmsHref_self" title="双汇产品销售已经恢复至瘦肉精事件前水平">双汇产品销售已经恢复至瘦肉精事件前水平</a></td>
							<td style="text-align:right">2011-08-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131ac0d5bf71424.jsp" class="cmsHref_self" title="中国南车500亿动车订单未完工">中国南车500亿动车订单未完工</a></td>
							<td style="text-align:right">2011-08-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131ac0b47781421.jsp" class="cmsHref_self" title="日本家电巨头集体亏损 需求疲弱成主因">日本家电巨头集体亏损 需求疲弱成主因</a></td>
							<td style="text-align:right">2011-08-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131a6fc6ee40be9.jsp" class="cmsHref_self" title="美的“跨界”应对“整体家居”时代">美的“跨界”应对“整体家居”时代</a></td>
							<td style="text-align:right">2011-08-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131a6ef42af0bdd.jsp" class="cmsHref_self" title="酱香白酒国标出台 茅台助习酒拼中端市场">酱香白酒国标出台 茅台助习酒拼中端市场</a></td>
							<td style="text-align:right">2011-08-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131a6e57a4c0bd9.jsp" class="cmsHref_self" title="凯雷集团斥资1.37亿美元渗入海尔电器">凯雷集团斥资1.37亿美元渗入海尔电器</a></td>
							<td style="text-align:right">2011-08-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131a6e43d560bd8.jsp" class="cmsHref_self" title="TCL华星光电8日投产 为深圳建市来规模最大投资">TCL华星光电8日投产 为深圳建市来规模最大投资</a></td>
							<td style="text-align:right">2011-08-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131a6e3ccce0bd7.jsp" class="cmsHref_self" title="海信撤出淄博留补偿未了局">海信撤出淄博留补偿未了局</a></td>
							<td style="text-align:right">2011-08-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131a6e32bae0bd5.jsp" class="cmsHref_self" title="美的电器收购开利拉美业务51%权益">美的电器收购开利拉美业务51%权益</a></td>
							<td style="text-align:right">2011-08-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131978778560341.jsp" class="cmsHref_self" title="戴尔服务器为大型企业建高效云平台">戴尔服务器为大型企业建高效云平台</a></td>
							<td style="text-align:right">2011-08-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c01319786b11c033f.jsp" class="cmsHref_self" title="LG、西门子、三星等获美国新能效星标签">LG、西门子、三星等获美国新能效星标签</a></td>
							<td style="text-align:right">2011-08-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c01319778d43a0330.jsp" class="cmsHref_self" title="纺织企业利润增长有持续回落表现">纺织企业利润增长有持续回落表现</a></td>
							<td style="text-align:right">2011-08-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c013197785dc8032f.jsp" class="cmsHref_self" title="日立与三菱重工合并磋商基本达成一致">日立与三菱重工合并磋商基本达成一致</a></td>
							<td style="text-align:right">2011-08-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c01319776ec09032e.jsp" class="cmsHref_self" title="雀巢收购徐福记收官 传有意再接手惠氏奶粉">雀巢收购徐福记收官 传有意再接手惠氏奶粉</a></td>
							<td style="text-align:right">2011-08-05</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_4.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_6.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：5/29 每页20条 </span>
</div>		
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
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	$('#searchNewsContentMainBtn').click(function() {
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=supplierzx&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
	});
	$("#startTime").epsDatepicker();
     $("#endTime").epsDatepicker();
     
    // 为左边栏设定选中样式,判断用于解决点击左边栏后样式被覆盖问题
	var id = $("#channelId").val();
	if(null != $('.subnav>li[id='+id+']').html()) {
		$('.subnav>li').removeClass('selected');
		$('.subnav>li[id='+id+']').addClass('selected');
	}
})
</script>