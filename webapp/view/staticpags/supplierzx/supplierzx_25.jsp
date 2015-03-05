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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f013067a59b3816bc.jsp" class="cmsHref_self" title="进军食品押宝化工 联想集团全面出击求解整体上市">进军食品押宝化工 联想集团全面出击求解整体上市</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f013067a2f00c16b8.jsp" class="cmsHref_self" title="五粮液以中国白酒文化“撬动”国际市场">五粮液以中国白酒文化“撬动”国际市场</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01306799e54d16ae.jsp" class="cmsHref_self" title="传闻飞利浦斥资20亿元收购奔腾电器">传闻飞利浦斥资20亿元收购奔腾电器</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01306794ea7916aa.jsp" class="cmsHref_self" title="石化双雄6月炼油大幅减亏 成品油批发价出现松动">石化双雄6月炼油大幅减亏 成品油批发价出现松动</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01306787a639169e.jsp" class="cmsHref_self" title="马自达将在日本投产新款中级车 供应北美">马自达将在日本投产新款中级车 供应北美</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01305321785407f8.jsp" class="cmsHref_self" title="中国电子产业今年有望保持两位数增速">中国电子产业今年有望保持两位数增速</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01305320aaea07f5.jsp" class="cmsHref_self" title="铅酸电池整顿持续 相关公司复产无期">铅酸电池整顿持续 相关公司复产无期</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f0130531f2a8107f1.jsp" class="cmsHref_self" title="三星声东击西占OLED高地 两岸面板业急呼危机">三星声东击西占OLED高地 两岸面板业急呼危机</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01305313e42706e6.jsp" class="cmsHref_self" title="2011中国电子信息百强企业揭晓 华为居榜首">2011中国电子信息百强企业揭晓 华为居榜首</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01305313039b06e2.jsp" class="cmsHref_self" title="5月全国汽车产销量环比双下降">5月全国汽车产销量环比双下降</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01305312771706e0.jsp" class="cmsHref_self" title="商务部附条件批准俄钾肥巨头合并计划">商务部附条件批准俄钾肥巨头合并计划</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01305311182906dd.jsp" class="cmsHref_self" title="宝马悄然调整部分进口车价 最高涨4.4万">宝马悄然调整部分进口车价 最高涨4.4万</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f0130530fdbf506db.jsp" class="cmsHref_self" title="三星抢占OLED制高点 液晶面板业面临生存危机">三星抢占OLED制高点 液晶面板业面临生存危机</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f013052f7e4320636.jsp" class="cmsHref_self" title="发改委预警供油趋紧 广东中石化被指常借机涨价">发改委预警供油趋紧 广东中石化被指常借机涨价</a></td>
							<td style="text-align:right">2011-06-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813045f6fc013048c1fdd300f8.jsp" class="cmsHref_self" title="零部件工厂闹罢工 韩系车或陷供货危机">零部件工厂闹罢工 韩系车或陷供货危机</a></td>
							<td style="text-align:right">2011-06-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813045f6fc013048bee20400ea.jsp" class="cmsHref_self" title="中石油中石化力保“三夏”油品供应">中石油中石化力保“三夏”油品供应</a></td>
							<td style="text-align:right">2011-06-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813045f6fc013048be4af500e8.jsp" class="cmsHref_self" title="毒黄瓜让欧洲很紧张 供应商多元化增加进口国管理难度">毒黄瓜让欧洲很紧张 供应商多元化增加进口国管理难度</a></td>
							<td style="text-align:right">2011-06-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813045f6fc013048b6e1b000ca.jsp" class="cmsHref_self" title="三星提前量产AMOLED面板以满足智能手机需求">三星提前量产AMOLED面板以满足智能手机需求</a></td>
							<td style="text-align:right">2011-06-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813043aa55013043b2d76d0032.jsp" class="cmsHref_self" title="韩国现代起亚因罢工停产 部分SUV车型生产中断">韩国现代起亚因罢工停产 部分SUV车型生产中断</a></td>
							<td style="text-align:right">2011-05-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081303fbeb7013043a46cdf03ca.jsp" class="cmsHref_self" title="云天化：干旱没有使化肥销售出现明显波动">云天化：干旱没有使化肥销售出现明显波动</a></td>
							<td style="text-align:right">2011-05-31</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_24.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_26.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：25/29 每页20条 </span>
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