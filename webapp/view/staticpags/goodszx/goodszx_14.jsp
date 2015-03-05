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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f01305308cf2b0652.jsp" class="cmsHref_self" title="中国氧化铝价格自3月以来下滑7% 因供应增加">中国氧化铝价格自3月以来下滑7% 因供应增加</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f013053042f64064e.jsp" class="cmsHref_self" title="5月汽车产销量双降 刚性需求被看好">5月汽车产销量双降 刚性需求被看好</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f013053035192064c.jsp" class="cmsHref_self" title="现货需求将回暖玉米振荡盘升为主">现货需求将回暖玉米振荡盘升为主</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f013053026671064a.jsp" class="cmsHref_self" title="钢木门市场在未来几年需求量会大幅提升">钢木门市场在未来几年需求量会大幅提升</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f01305301c97e0648.jsp" class="cmsHref_self" title="新疆水泥需求逐月趋旺">新疆水泥需求逐月趋旺</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f013053010c610646.jsp" class="cmsHref_self" title="亚洲需求预计将推动金价继续上涨">亚洲需求预计将推动金价继续上涨</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813045f6fc013048bd96b400e6.jsp" class="cmsHref_self" title="10/11年度 全球糖市供应短缺280万吨">10/11年度 全球糖市供应短缺280万吨</a></td>
							<td style="text-align:right">2011-06-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813045f6fc013048bcd04f00d4.jsp" class="cmsHref_self" title="俄罗斯计划7月起取消谷物出口禁令提高国际市场供应">俄罗斯计划7月起取消谷物出口禁令提高国际市场供应</a></td>
							<td style="text-align:right">2011-06-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813045f6fc013048bb425000d2.jsp" class="cmsHref_self" title="基础化工行业：需求旺季到来 化肥价格普涨">基础化工行业：需求旺季到来 化肥价格普涨</a></td>
							<td style="text-align:right">2011-06-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813045f6fc013048b9ec8300d0.jsp" class="cmsHref_self" title="需求回暖支撑糖价">需求回暖支撑糖价</a></td>
							<td style="text-align:right">2011-06-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813045f6fc013048b93ad100ce.jsp" class="cmsHref_self" title="随着前期热点的散去，大连商品交易所焦炭期价走势渐趋平静，呈现振荡走势。笔者认为在成本支撑及需求减弱的共同作用下 连焦有望延续振荡走势">随着前期热点的散去，大连商品交易所焦炭期价走势渐趋平静，呈现振荡走势。笔者认为在成本支撑及需求减弱的共同作用下 连焦有望延续振荡走势</a></td>
							<td style="text-align:right">2011-06-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813045f6fc013048b7afa100cc.jsp" class="cmsHref_self" title="成本支撑及需求减弱 振荡将成焦炭后市主旋律">成本支撑及需求减弱 振荡将成焦炭后市主旋律</a></td>
							<td style="text-align:right">2011-06-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813045f6fc013048b6379a00c8.jsp" class="cmsHref_self" title="重庆市“十二五”生物医药产业需求呈现三大特点">重庆市“十二五”生物医药产业需求呈现三大特点</a></td>
							<td style="text-align:right">2011-06-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813045f6fc013048b59e5500c6.jsp" class="cmsHref_self" title="因需求减少忧虑 美玉米盘终收低">因需求减少忧虑 美玉米盘终收低</a></td>
							<td style="text-align:right">2011-06-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813045f6fc013048b538e300c4.jsp" class="cmsHref_self" title="豆油需求增加 中期高位震荡格局未变">豆油需求增加 中期高位震荡格局未变</a></td>
							<td style="text-align:right">2011-06-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813045f6fc013048b4c69100c2.jsp" class="cmsHref_self" title="需求维持强劲 日胶维持升势">需求维持强劲 日胶维持升势</a></td>
							<td style="text-align:right">2011-06-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813045f6fc013048b3e80d00c0.jsp" class="cmsHref_self" title="光伏需求不佳时晶圆代工业务填补空缺">光伏需求不佳时晶圆代工业务填补空缺</a></td>
							<td style="text-align:right">2011-06-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813045f6fc013048b31dd900be.jsp" class="cmsHref_self" title="超大型履带起重机需求日趋倒向“中国制造”">超大型履带起重机需求日趋倒向“中国制造”</a></td>
							<td style="text-align:right">2011-06-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813045f6fc013048b2836500bc.jsp" class="cmsHref_self" title="商务需求是主流 中高级车用户呈年轻化趋势">商务需求是主流 中高级车用户呈年轻化趋势</a></td>
							<td style="text-align:right">2011-06-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813043aa55013043b5c7c9006f.jsp" class="cmsHref_self" title="江西稀土产量超标 1-4月同比增长30.15%">江西稀土产量超标 1-4月同比增长30.15%</a></td>
							<td style="text-align:right">2011-05-31</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_13.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_15.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_19.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：14/19 每页20条 </span>
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