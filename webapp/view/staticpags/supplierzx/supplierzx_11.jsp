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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314f4942e111b9.jsp" class="cmsHref_self" title="雷诺三星寻求摆脱日本零部件供应商影响">雷诺三星寻求摆脱日本零部件供应商影响</a></td>
							<td style="text-align:right">2011-07-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314a46c6e90b0e.jsp" class="cmsHref_self" title="联想、苹果双进三：PC产业格局面临重塑">联想、苹果双进三：PC产业格局面临重塑</a></td>
							<td style="text-align:right">2011-07-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314a4234a20b0a.jsp" class="cmsHref_self" title="清酷凉茶欲挑战王老吉一哥地位 掀起红绿大战">清酷凉茶欲挑战王老吉一哥地位 掀起红绿大战</a></td>
							<td style="text-align:right">2011-07-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314a3978f30b02.jsp" class="cmsHref_self" title="锦湖南京厂被列为重点污染企业">锦湖南京厂被列为重点污染企业</a></td>
							<td style="text-align:right">2011-07-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314a38dc9b0b01.jsp" class="cmsHref_self" title="达芬奇造假维权团拟索赔4000万">达芬奇造假维权团拟索赔4000万</a></td>
							<td style="text-align:right">2011-07-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314a36b60e0afe.jsp" class="cmsHref_self" title="奇瑞汽车巴西工厂开工奠基 建成后年产能达15万辆">奇瑞汽车巴西工厂开工奠基 建成后年产能达15万辆</a></td>
							<td style="text-align:right">2011-07-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314a356c650afc.jsp" class="cmsHref_self" title="必和必拓铁矿等多种商品产量大幅增长">必和必拓铁矿等多种商品产量大幅增长</a></td>
							<td style="text-align:right">2011-07-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314a24d3b60aea.jsp" class="cmsHref_self" title="林洋电子打造电工仪器仪表金牌供应商">林洋电子打造电工仪器仪表金牌供应商</a></td>
							<td style="text-align:right">2011-07-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314a203ef20ae8.jsp" class="cmsHref_self" title="公车采购“被动超标”成一大漏洞">公车采购“被动超标”成一大漏洞</a></td>
							<td style="text-align:right">2011-07-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314a1f3a1d0ae7.jsp" class="cmsHref_self" title="甘肃移动采购4亿元设备用于G网升级">甘肃移动采购4亿元设备用于G网升级</a></td>
							<td style="text-align:right">2011-07-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314a1e879c0ae6.jsp" class="cmsHref_self" title="雨润农产品全球采购中心开建">雨润农产品全球采购中心开建</a></td>
							<td style="text-align:right">2011-07-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314530a25f034e.jsp" class="cmsHref_self" title="美的掌握全球新冷媒主动权">美的掌握全球新冷媒主动权</a></td>
							<td style="text-align:right">2011-07-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314527c1510347.jsp" class="cmsHref_self" title="未来5年LED车用照明市场商机无限">未来5年LED车用照明市场商机无限</a></td>
							<td style="text-align:right">2011-07-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc470131452650fb0344.jsp" class="cmsHref_self" title="耐克公司打败对手获得橄榄球联盟提供商">耐克公司打败对手获得橄榄球联盟提供商</a></td>
							<td style="text-align:right">2011-07-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc470131451fe95f0341.jsp" class="cmsHref_self" title="新能源汽车发展目标悬疑 比亚迪思路转向">新能源汽车发展目标悬疑 比亚迪思路转向</a></td>
							<td style="text-align:right">2011-07-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc470131451b68a6033e.jsp" class="cmsHref_self" title="苹果或联姻中移动 开拓中国市场">苹果或联姻中移动 开拓中国市场</a></td>
							<td style="text-align:right">2011-07-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc470131451a02d7033b.jsp" class="cmsHref_self" title="达芬奇被揭多拿3.6亿元退税">达芬奇被揭多拿3.6亿元退税</a></td>
							<td style="text-align:right">2011-07-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314518ea6c033a.jsp" class="cmsHref_self" title="中粮耗资近10亿元收编澳洲糖业99%股份">中粮耗资近10亿元收编澳洲糖业99%股份</a></td>
							<td style="text-align:right">2011-07-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314517ac340339.jsp" class="cmsHref_self" title="雅戈尔遭环保组织污染榜点名 欲自证清白">雅戈尔遭环保组织污染榜点名 欲自证清白</a></td>
							<td style="text-align:right">2011-07-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc47013145166ff50337.jsp" class="cmsHref_self" title="鲁花停涨通知效果不佳 多地花生油价格再上调">鲁花停涨通知效果不佳 多地花生油价格再上调</a></td>
							<td style="text-align:right">2011-07-20</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_10.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_12.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：11/29 每页20条 </span>
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