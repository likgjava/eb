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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f0130771b057142ab.jsp" class="cmsHref_self" title="世纪晶源战略调整 5年打造百亿高科技产值">世纪晶源战略调整 5年打造百亿高科技产值</a></td>
							<td style="text-align:right">2011-06-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01307717a8014285.jsp" class="cmsHref_self" title="铅蓄电池遇全行业忐忑 个别省九成企业停产整顿">铅蓄电池遇全行业忐忑 个别省九成企业停产整顿</a></td>
							<td style="text-align:right">2011-06-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01307710dbad426e.jsp" class="cmsHref_self" title="中国铜需求已恢复至危机前水平 2020年达1000万吨">中国铜需求已恢复至危机前水平 2020年达1000万吨</a></td>
							<td style="text-align:right">2011-06-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f0130770f546c426b.jsp" class="cmsHref_self" title="板材需求持续低迷 宝钢7月降价">板材需求持续低迷 宝钢7月降价</a></td>
							<td style="text-align:right">2011-06-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01307706e8834248.jsp" class="cmsHref_self" title="华润雪花3亿控股清河墨尼啤酒">华润雪花3亿控股清河墨尼啤酒</a></td>
							<td style="text-align:right">2011-06-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01307702b8dd4235.jsp" class="cmsHref_self" title="朗盛新产品应用于卡车外部组件">朗盛新产品应用于卡车外部组件</a></td>
							<td style="text-align:right">2011-06-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01307700b496422d.jsp" class="cmsHref_self" title="TCL持续掀起国内智能手机攻势">TCL持续掀起国内智能手机攻势</a></td>
							<td style="text-align:right">2011-06-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f013071f7c1b530bb.jsp" class="cmsHref_self" title="韩IT产品5月对华出口增5.6%达61.7亿美元">韩IT产品5月对华出口增5.6%达61.7亿美元</a></td>
							<td style="text-align:right">2011-06-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f013071f5e96630b8.jsp" class="cmsHref_self" title="思科推基于至强E7的下一代UCS服务器">思科推基于至强E7的下一代UCS服务器</a></td>
							<td style="text-align:right">2011-06-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f013071ea1c58308b.jsp" class="cmsHref_self" title="微软收购企业管理软件供应商Prodiance">微软收购企业管理软件供应商Prodiance</a></td>
							<td style="text-align:right">2011-06-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f013071e71d4b307f.jsp" class="cmsHref_self" title="华为终端瞄准印度平板电脑市场:五年内跻身前五强">华为终端瞄准印度平板电脑市场:五年内跻身前五强</a></td>
							<td style="text-align:right">2011-06-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f013071da7434304a.jsp" class="cmsHref_self" title="中铝公司收编江苏5家稀土企业 产业布局成型">中铝公司收编江苏5家稀土企业 产业布局成型</a></td>
							<td style="text-align:right">2011-06-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f013071d965093048.jsp" class="cmsHref_self" title="日钢入股五矿下属企业 杜双华接连出手引遐想">日钢入股五矿下属企业 杜双华接连出手引遐想</a></td>
							<td style="text-align:right">2011-06-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f013071d7b6b7303c.jsp" class="cmsHref_self" title="中粮总裁赴澳拜会政府要员 并购塔利糖业或翻盘">中粮总裁赴澳拜会政府要员 并购塔利糖业或翻盘</a></td>
							<td style="text-align:right">2011-06-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f013071d2143e3036.jsp" class="cmsHref_self" title="酒企频赴西部“圈地”建厂">酒企频赴西部“圈地”建厂</a></td>
							<td style="text-align:right">2011-06-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01306cd4f1252332.jsp" class="cmsHref_self" title="五粮液以国际化标准引领白酒行业发展">五粮液以国际化标准引领白酒行业发展</a></td>
							<td style="text-align:right">2011-06-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01306cbb1ee02313.jsp" class="cmsHref_self" title="苹果试水即时通讯市场弥补应用软肋">苹果试水即时通讯市场弥补应用软肋</a></td>
							<td style="text-align:right">2011-06-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01306caf7eb22304.jsp" class="cmsHref_self" title="汽车零部件供应商电装任命新北美总裁兼CEO">汽车零部件供应商电装任命新北美总裁兼CEO</a></td>
							<td style="text-align:right">2011-06-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01306cae7b2c2301.jsp" class="cmsHref_self" title="调查显示：零部件供应商利润高于整车企业">调查显示：零部件供应商利润高于整车企业</a></td>
							<td style="text-align:right">2011-06-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01306ca3668522f1.jsp" class="cmsHref_self" title="发改委对下游行业价格干预可能接近尾声">发改委对下游行业价格干预可能接近尾声</a></td>
							<td style="text-align:right">2011-06-08</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_23.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_25.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：24/29 每页20条 </span>
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