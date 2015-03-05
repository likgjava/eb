<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>采购焦点资讯- 【阳光易购】</title>
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
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<table class="frontTableList" id="qualityTable">
					<thead>
						<tr>
							<th class="left">标题</th>
							<th class="center">时间</th>
						</tr>
					</thead>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081313c08ec01313fd6337d0207.jsp" class="cmsHref_self" title="药品集中采购制度未充分发挥作用">药品集中采购制度未充分发挥作用</a></td>
							<td style="text-align:right">2011-07-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081312d69ed01313ab2a3140206.jsp" class="cmsHref_self" title="苹果8月开始采购芯片 台湾多家企业受益">苹果8月开始采购芯片 台湾多家企业受益</a></td>
							<td style="text-align:right">2011-07-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081312d69ed01313ab181be0205.jsp" class="cmsHref_self" title="中国移动启动液晶电视产品集中采购 规模约为3385台">中国移动启动液晶电视产品集中采购 规模约为3385台</a></td>
							<td style="text-align:right">2011-07-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081312d69ed01313ab0833e0204.jsp" class="cmsHref_self" title="奥的斯工厂货物积压到人行道 北京交委暂停采购">奥的斯工厂货物积压到人行道 北京交委暂停采购</a></td>
							<td style="text-align:right">2011-07-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081312d69ed01313aaf6ee80203.jsp" class="cmsHref_self" title="欧盟拟设政府采购新规 给中国入GPA施压">欧盟拟设政府采购新规 给中国入GPA施压</a></td>
							<td style="text-align:right">2011-07-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813118359201312b43f1762404.jsp" class="cmsHref_self" title="苹果为企业准备应用商店批量采购计划">苹果为企业准备应用商店批量采购计划</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813118359201312b434b132402.jsp" class="cmsHref_self" title="海南香蕉再现卖难 威海增大采购量助蕉农解难题">海南香蕉再现卖难 威海增大采购量助蕉农解难题</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813118359201312b4210112400.jsp" class="cmsHref_self" title="价格拉抽屉 整晕采购员">价格拉抽屉 整晕采购员</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813118359201312b40908d23fe.jsp" class="cmsHref_self" title="今年上半年达芬奇近10%“进口”家具在国内采购">今年上半年达芬奇近10%“进口”家具在国内采购</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813118359201312b3ef6d323f6.jsp" class="cmsHref_self" title="美的中央空调成政府机构最大采购供应商">美的中央空调成政府机构最大采购供应商</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081311835920131260f1e4a1710.jsp" class="cmsHref_self" title="丹江口市为政府采购供应商建立诚信档案">丹江口市为政府采购供应商建立诚信档案</a></td>
							<td style="text-align:right">2011-07-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081311835920131260e6fbe170e.jsp" class="cmsHref_self" title="基本药物制采用新采购办法 药价平均降38%">基本药物制采用新采购办法 药价平均降38%</a></td>
							<td style="text-align:right">2011-07-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081311835920131260cef8d170c.jsp" class="cmsHref_self" title="女老总发布会情绪失控 达芬奇10多批家具系国内采购">女老总发布会情绪失控 达芬奇10多批家具系国内采购</a></td>
							<td style="text-align:right">2011-07-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081311835920131260c0b9c170a.jsp" class="cmsHref_self" title="中国物流与采购联合会副会长崔忠付致辞">中国物流与采购联合会副会长崔忠付致辞</a></td>
							<td style="text-align:right">2011-07-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131183592013120f7fbcb0e5c.jsp" class="cmsHref_self" title="开鲁县上半年采购规模同比增长80%">开鲁县上半年采购规模同比增长80%</a></td>
							<td style="text-align:right">2011-07-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131183592013120f73a900e58.jsp" class="cmsHref_self" title="山东某大型纺企第四次下调四级皮棉采购价">山东某大型纺企第四次下调四级皮棉采购价</a></td>
							<td style="text-align:right">2011-07-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131183592013120f5e7440e56.jsp" class="cmsHref_self" title="贵州将建电煤供应与采购"考核奖惩制"破解"燃煤之急"">贵州将建电煤供应与采购"考核奖惩制"破解"燃煤之急"</a></td>
							<td style="text-align:right">2011-07-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131183592013120f42b1c0e4c.jsp" class="cmsHref_self" title="广东珠海市财政局扎实推进政府采购监管工作">广东珠海市财政局扎实推进政府采购监管工作</a></td>
							<td style="text-align:right">2011-07-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131183592013120f296e30e4a.jsp" class="cmsHref_self" title="五金产品价格上涨 采购外商表示理解">五金产品价格上涨 采购外商表示理解</a></td>
							<td style="text-align:right">2011-07-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813118359201311bd1194d036d.jsp" class="cmsHref_self" title="政府采购与激励本土创新不直接挂钩">政府采购与激励本土创新不直接挂钩</a></td>
							<td style="text-align:right">2011-07-12</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_4.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_6.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_18.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：5/18 每页20条 </span>
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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=cgxqzx&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
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