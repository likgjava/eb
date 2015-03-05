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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081312d69ed01313ac303570217.jsp" class="cmsHref_self" title="聚焦雅戈尔等纺织企业身陷水污染疑云">聚焦雅戈尔等纺织企业身陷水污染疑云</a></td>
							<td style="text-align:right">2011-07-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081312d69ed01313ac24ca80216.jsp" class="cmsHref_self" title="达芬奇家居杭州店被查封 家具市场虚假标注乱象已久">达芬奇家居杭州店被查封 家具市场虚假标注乱象已久</a></td>
							<td style="text-align:right">2011-07-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081312d69ed01313ac1ac5c0215.jsp" class="cmsHref_self" title="奥的斯电梯再害死1人 电梯事故演变成城市病">奥的斯电梯再害死1人 电梯事故演变成城市病</a></td>
							<td style="text-align:right">2011-07-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081312d69ed01313abea9370212.jsp" class="cmsHref_self" title="达芬奇广州店不肯退订金 消费者怒斥老赖">达芬奇广州店不肯退订金 消费者怒斥老赖</a></td>
							<td style="text-align:right">2011-07-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081312d69ed01313ab70039020c.jsp" class="cmsHref_self" title="苹果与三星专利之争或影响iPad处理器供应">苹果与三星专利之争或影响iPad处理器供应</a></td>
							<td style="text-align:right">2011-07-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081312d69ed01313ab67b34020b.jsp" class="cmsHref_self" title="中国供应商进军亚奥会照明">中国供应商进军亚奥会照明</a></td>
							<td style="text-align:right">2011-07-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081312d69ed01313ab5f18a020a.jsp" class="cmsHref_self" title="耐克等知名服装品牌供应商被指排放环境激素类物质">耐克等知名服装品牌供应商被指排放环境激素类物质</a></td>
							<td style="text-align:right">2011-07-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312b76129f2462.jsp" class="cmsHref_self" title="金龙鱼否认断供传言 称成本压力缓解">金龙鱼否认断供传言 称成本压力缓解</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312b74b7f02460.jsp" class="cmsHref_self" title="卫浴企业跨界营销 “多元化”争取利润">卫浴企业跨界营销 “多元化”争取利润</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312b73d09e245e.jsp" class="cmsHref_self" title="雅戈尔身陷污染疑云引发中外监管标准讨论">雅戈尔身陷污染疑云引发中外监管标准讨论</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312b7316b6245c.jsp" class="cmsHref_self" title="西门子百万美元签约爱达荷太阳能项目">西门子百万美元签约爱达荷太阳能项目</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312b7241fe245a.jsp" class="cmsHref_self" title="美的、格力、海尔进入中国百强企业">美的、格力、海尔进入中国百强企业</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312b6387ad2442.jsp" class="cmsHref_self" title="中国最大橡胶防老剂企业圣奥陷税务危机">中国最大橡胶防老剂企业圣奥陷税务危机</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312b6281722440.jsp" class="cmsHref_self" title="中粮35亿港元增持蒙牛乳业">中粮35亿港元增持蒙牛乳业</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312b61f915243e.jsp" class="cmsHref_self" title="华润雪花收购两啤酒企业">华润雪花收购两啤酒企业</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312b60c89f2438.jsp" class="cmsHref_self" title="东南亚快消巨头加入中国凉茶大战">东南亚快消巨头加入中国凉茶大战</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312b5fe2522434.jsp" class="cmsHref_self" title="方正诉宝洁字库侵权终审维持原判 被判不成立">方正诉宝洁字库侵权终审维持原判 被判不成立</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312b5541b42426.jsp" class="cmsHref_self" title="铅酸蓄电池供应不足 电池电动车联袂涨价">铅酸蓄电池供应不足 电池电动车联袂涨价</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312b5404f32422.jsp" class="cmsHref_self" title="14家服装品牌被指涉污 供应商称检测取样口有误">14家服装品牌被指涉污 供应商称检测取样口有误</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312b447bf82408.jsp" class="cmsHref_self" title="苹果为企业准备应用商店批量采购计划">苹果为企业准备应用商店批量采购计划</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_12.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_14.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：13/29 每页20条 </span>
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