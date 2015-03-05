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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f01306ca8f44d22f9.jsp" class="cmsHref_self" title="不采购和滥用食品添加剂物质">不采购和滥用食品添加剂物质</a></td>
							<td style="text-align:right">2011-06-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f01306ca76d6422f7.jsp" class="cmsHref_self" title="公车采购新标准出台 1.8L车型将迎来春天">公车采购新标准出台 1.8L车型将迎来春天</a></td>
							<td style="text-align:right">2011-06-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f01306ca5e20b22f5.jsp" class="cmsHref_self" title="墨西哥采购消息利多 美玉米收复部分失地">墨西哥采购消息利多 美玉米收复部分失地</a></td>
							<td style="text-align:right">2011-06-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f01306ca54e9622f3.jsp" class="cmsHref_self" title="国内风电或将失采购补贴">国内风电或将失采购补贴</a></td>
							<td style="text-align:right">2011-06-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f013067c5d1ee174d.jsp" class="cmsHref_self" title="大屏幕LCD液晶拼接墙的技术特点及采购">大屏幕LCD液晶拼接墙的技术特点及采购</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f013067c3bdfc174b.jsp" class="cmsHref_self" title="南京政府采购引入竞价机制 5个月节约超1成预算">南京政府采购引入竞价机制 5个月节约超1成预算</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f013067c14d471749.jsp" class="cmsHref_self" title="公务车采购新规 合资B级车面临集体出局">公务车采购新规 合资B级车面临集体出局</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f013067c02c3f1745.jsp" class="cmsHref_self" title="6月7日豆粕市场预测及采购建议">6月7日豆粕市场预测及采购建议</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f0130531cbe3107e6.jsp" class="cmsHref_self" title="植入性医疗器械购用混乱">植入性医疗器械购用混乱</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f013052fff50f0644.jsp" class="cmsHref_self" title="棉花现货报价滞涨 纺企不敢贸然采购">棉花现货报价滞涨 纺企不敢贸然采购</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f013052ff405e0642.jsp" class="cmsHref_self" title="力拓在华抛10亿美元采购单再次示好">力拓在华抛10亿美元采购单再次示好</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f013052fe89930640.jsp" class="cmsHref_self" title="宁夏退耕还林补助粮竞价采购成交近4万吨">宁夏退耕还林补助粮竞价采购成交近4万吨</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f013052fe0ec4063e.jsp" class="cmsHref_self" title="国家机关完成软件正版化 采购正版软件176763套">国家机关完成软件正版化 采购正版软件176763套</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f013052fd9647063c.jsp" class="cmsHref_self" title="禁止采购使用台湾问题企业产品">禁止采购使用台湾问题企业产品</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813045f6fc013048b1012000ba.jsp" class="cmsHref_self" title="法国已暂停向巴基斯坦军队提供重型军事装备">法国已暂停向巴基斯坦军队提供重型军事装备</a></td>
							<td style="text-align:right">2011-06-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813045f6fc013048b044ec00b8.jsp" class="cmsHref_self" title="劳工成本增加 西欧纺织服装采购商陷入困境">劳工成本增加 西欧纺织服装采购商陷入困境</a></td>
							<td style="text-align:right">2011-06-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813045f6fc013048aee32a00b6.jsp" class="cmsHref_self" title="成都物流企业欲抱团采购柴油">成都物流企业欲抱团采购柴油</a></td>
							<td style="text-align:right">2011-06-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813045f6fc013048ad652900b4.jsp" class="cmsHref_self" title="安徽省将进一步规范采购使用基本药物限价药品行为">安徽省将进一步规范采购使用基本药物限价药品行为</a></td>
							<td style="text-align:right">2011-06-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813045f6fc013048abc48500b2.jsp" class="cmsHref_self" title="路灯节能监控系统政府采购项目全票通过">路灯节能监控系统政府采购项目全票通过</a></td>
							<td style="text-align:right">2011-06-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081303fbeb70130438ad7940383.jsp" class="cmsHref_self" title="江苏如东棉价小幅回升 纺企采购量增">江苏如东棉价小幅回升 纺企采购量增</a></td>
							<td style="text-align:right">2011-05-31</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_10.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_12.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_18.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：11/18 每页20条 </span>
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