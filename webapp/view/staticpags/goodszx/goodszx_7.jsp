<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>供货资讯- 【阳光易购】</title>
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
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<table class="frontTableList" id="qualityTable">
					<thead>
						<tr>
							<th class="left">标题</th>
							<th class="center">时间</th>
						</tr>
					</thead>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813140dc47013145068bd1032b.jsp" class="cmsHref_self" title="江苏省上半年散装水泥供应量全国第一">江苏省上半年散装水泥供应量全国第一</a></td>
							<td style="text-align:right">2011-07-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081313c08ec01313ff020540226.jsp" class="cmsHref_self" title="发改委维稳中药材市场 54商户囤积党参遭严惩">发改委维稳中药材市场 54商户囤积党参遭严惩</a></td>
							<td style="text-align:right">2011-07-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081312d69ed01313ac997cb021f.jsp" class="cmsHref_self" title="明治国内断货 一年产地全部改为澳大利亚">明治国内断货 一年产地全部改为澳大利亚</a></td>
							<td style="text-align:right">2011-07-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081312d69ed01313abb6a880210.jsp" class="cmsHref_self" title="商务部回应奢侈品关税：应以扩大需求为出发点">商务部回应奢侈品关税：应以扩大需求为出发点</a></td>
							<td style="text-align:right">2011-07-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081312d69ed01313ab85e65020f.jsp" class="cmsHref_self" title="供应被操控 铁矿石价难下">供应被操控 铁矿石价难下</a></td>
							<td style="text-align:right">2011-07-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081312d69ed01313ab78017020d.jsp" class="cmsHref_self" title="苹果与三星专利之争或影响iPad处理器供应">苹果与三星专利之争或影响iPad处理器供应</a></td>
							<td style="text-align:right">2011-07-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081312d69ed01313ab43de10208.jsp" class="cmsHref_self" title="零部件供应不足 雷诺斯洛文尼亚工厂裁员520人">零部件供应不足 雷诺斯洛文尼亚工厂裁员520人</a></td>
							<td style="text-align:right">2011-07-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813118359201312b7742152464.jsp" class="cmsHref_self" title="金龙鱼否认断供传言 称成本压力缓解">金龙鱼否认断供传言 称成本压力缓解</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813118359201312b5acbb42430.jsp" class="cmsHref_self" title="电子行业中报预期喜忧参半 下半年需求有望回暖">电子行业中报预期喜忧参半 下半年需求有望回暖</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813118359201312b584037242e.jsp" class="cmsHref_self" title="保障房提升水泥需求 下半年价格仍维持高位">保障房提升水泥需求 下半年价格仍维持高位</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813118359201312b562962242c.jsp" class="cmsHref_self" title="江西成品油供应应急预案实施">江西成品油供应应急预案实施</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813118359201312b4b9d9b2418.jsp" class="cmsHref_self" title="下半年成品油供应偏紧">下半年成品油供应偏紧</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813118359201312b49c9402416.jsp" class="cmsHref_self" title="14家服装的供应商被指往水里排毒">14家服装的供应商被指往水里排毒</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813118359201312b4848372412.jsp" class="cmsHref_self" title="广州花生油供应正常 传北京现断供">广州花生油供应正常 传北京现断供</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813118359201312b47866d2410.jsp" class="cmsHref_self" title="渣打:中国猪肉价8月将平复 明年下半年供应过剩">渣打:中国猪肉价8月将平复 明年下半年供应过剩</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131183592013121027c2b0e88.jsp" class="cmsHref_self" title="烟叶供应链管理体系建设与原料保障上水平关系浅析">烟叶供应链管理体系建设与原料保障上水平关系浅析</a></td>
							<td style="text-align:right">2011-07-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813118359201312101dd3c0e86.jsp" class="cmsHref_self" title="温家宝：管住货币总量 抓好食品供应">温家宝：管住货币总量 抓好食品供应</a></td>
							<td style="text-align:right">2011-07-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813118359201312100a61b0e78.jsp" class="cmsHref_self" title="供应压力不减 期棉跌至年内新低">供应压力不减 期棉跌至年内新低</a></td>
							<td style="text-align:right">2011-07-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131183592013120fecfcf0e72.jsp" class="cmsHref_self" title="焦炭产能无序扩张 炼焦煤供应缺口将达6000万吨">焦炭产能无序扩张 炼焦煤供应缺口将达6000万吨</a></td>
							<td style="text-align:right">2011-07-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131183592013120fe05a30e6e.jsp" class="cmsHref_self" title="有色金属：紧缩政策趋缓及供应忧虑推高基本金属">有色金属：紧缩政策趋缓及供应忧虑推高基本金属</a></td>
							<td style="text-align:right">2011-07-13</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_6.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_8.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_19.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：7/19 每页20条 </span>
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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=goodszx&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
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