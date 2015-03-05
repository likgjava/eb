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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f0130679c2de616b2.jsp" class="cmsHref_self" title="今年棉价累计下跌近两成 囤货商损失惨重">今年棉价累计下跌近两成 囤货商损失惨重</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f0130679b626b16b0.jsp" class="cmsHref_self" title="6月钢材销售淡季到来 市场看跌气氛渐浓">6月钢材销售淡季到来 市场看跌气氛渐浓</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f01306797174616ac.jsp" class="cmsHref_self" title="门禁识别系统之节能环保应用现状及意义">门禁识别系统之节能环保应用现状及意义</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f013067909e4216a8.jsp" class="cmsHref_self" title="业内质疑多晶硅产能过剩说 中国企业痛失千亿市场">业内质疑多晶硅产能过剩说 中国企业痛失千亿市场</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f0130678b947616a6.jsp" class="cmsHref_self" title="现货市场趋紧 螺纹或将再创新高">现货市场趋紧 螺纹或将再创新高</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f0130678ad82416a4.jsp" class="cmsHref_self" title="中药材市场四次涨价 医药企业直呼“伤不起”">中药材市场四次涨价 医药企业直呼“伤不起”</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f01306789b2e116a2.jsp" class="cmsHref_self" title="我国仪器仪表国外市场占有率呈上升趋势">我国仪器仪表国外市场占有率呈上升趋势</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f013067861d58169c.jsp" class="cmsHref_self" title="日系车复产供应渐恢复 京城车市面临更大降价压力">日系车复产供应渐恢复 京城车市面临更大降价压力</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f01306784ae22169a.jsp" class="cmsHref_self" title="中国夏季电荒将意外催降亚洲燃料油需求">中国夏季电荒将意外催降亚洲燃料油需求</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f01306783345f1698.jsp" class="cmsHref_self" title="刚性需求明显 小排量车关注度明显提高">刚性需求明显 小排量车关注度明显提高</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f013067821c6c1696.jsp" class="cmsHref_self" title="江苏吴江纺织机械进口需求旺盛">江苏吴江纺织机械进口需求旺盛</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f0130677ca452167e.jsp" class="cmsHref_self" title="端午假期北京空调销售猛增 同比增长近两倍">端午假期北京空调销售猛增 同比增长近两倍</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f0130677b5332167c.jsp" class="cmsHref_self" title="市场煤70天劲涨10% 电价上调未解电厂经营困局">市场煤70天劲涨10% 电价上调未解电厂经营困局</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f0130677a41ff167a.jsp" class="cmsHref_self" title="中国夏季电荒将意外催降亚洲燃料油需求">中国夏季电荒将意外催降亚洲燃料油需求</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f01305317b25e07bc.jsp" class="cmsHref_self" title="葡萄酒市场上演新旧势力博弈">葡萄酒市场上演新旧势力博弈</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f0130531689090745.jsp" class="cmsHref_self" title="豆浆机国标正式实施 1/3豆浆机品牌面临淘汰">豆浆机国标正式实施 1/3豆浆机品牌面临淘汰</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f013053155dbb06ed.jsp" class="cmsHref_self" title="钒资源开发思路将做重大调整 或拉动万亿需求">钒资源开发思路将做重大调整 或拉动万亿需求</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f0130530c90440658.jsp" class="cmsHref_self" title="全球PC供应链7月或迎“最艰难时刻”">全球PC供应链7月或迎“最艰难时刻”</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f0130530b994e0656.jsp" class="cmsHref_self" title="中国阀门签署$5000万高端阀门研发和供应协议">中国阀门签署$5000万高端阀门研发和供应协议</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f01305309612f0654.jsp" class="cmsHref_self" title="供应吃紧担忧 美玉米延续升势">供应吃紧担忧 美玉米延续升势</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_12.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_14.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_19.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：13/19 每页20条 </span>
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