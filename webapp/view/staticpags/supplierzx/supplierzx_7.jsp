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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601318d3ddb4a125a.jsp" class="cmsHref_self" title="东风日产发动机产能扩建启动 新事业计划">东风日产发动机产能扩建启动 新事业计划</a></td>
							<td style="text-align:right">2011-08-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601318d3ab4e41257.jsp" class="cmsHref_self" title="飞鹤乳业卖掉两家牧场 还债还是放弃全产业链">飞鹤乳业卖掉两家牧场 还债还是放弃全产业链</a></td>
							<td style="text-align:right">2011-08-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601318d2c7416124b.jsp" class="cmsHref_self" title="太子奶托管公司董事长文迪波被“双规”">太子奶托管公司董事长文迪波被“双规”</a></td>
							<td style="text-align:right">2011-08-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601318d20d92a1247.jsp" class="cmsHref_self" title="海尔洽购三洋电机 白电产业全球主导权将转至中国">海尔洽购三洋电机 白电产业全球主导权将转至中国</a></td>
							<td style="text-align:right">2011-08-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601318d1c55771240.jsp" class="cmsHref_self" title="各地公布铅蓄电池行业企业名单 接受社会监督">各地公布铅蓄电池行业企业名单 接受社会监督</a></td>
							<td style="text-align:right">2011-08-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601318d1b5b4e123f.jsp" class="cmsHref_self" title="上半年有色金属工业增加值同比增长12.3%">上半年有色金属工业增加值同比增长12.3%</a></td>
							<td style="text-align:right">2011-08-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601318d1a2bd3123e.jsp" class="cmsHref_self" title="达芬奇案最快本周起诉 或临民事刑事双重处罚">达芬奇案最快本周起诉 或临民事刑事双重处罚</a></td>
							<td style="text-align:right">2011-08-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601318d191082123c.jsp" class="cmsHref_self" title="太子奶托管公司高科奶业董事长文迪波被双规">太子奶托管公司高科奶业董事长文迪波被双规</a></td>
							<td style="text-align:right">2011-08-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601318d18684b123b.jsp" class="cmsHref_self" title="荣华月饼商标战再起波澜">荣华月饼商标战再起波澜</a></td>
							<td style="text-align:right">2011-08-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601318d1735591239.jsp" class="cmsHref_self" title="丰田第一财季营业亏损千亿 仍上调全年利润预期">丰田第一财季营业亏损千亿 仍上调全年利润预期</a></td>
							<td style="text-align:right">2011-08-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601318d1687f41238.jsp" class="cmsHref_self" title="海尔意向收购三洋电机洗衣机和冰箱等家用电器业务">海尔意向收购三洋电机洗衣机和冰箱等家用电器业务</a></td>
							<td style="text-align:right">2011-08-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601318d15b90b1236.jsp" class="cmsHref_self" title="华润雪花3亿收购西湖啤酒55%股权 实现全资控股">华润雪花3亿收购西湖啤酒55%股权 实现全资控股</a></td>
							<td style="text-align:right">2011-08-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601318815e7200b1f.jsp" class="cmsHref_self" title="青岛海尔向凯雷投资发行10亿港元可转债">青岛海尔向凯雷投资发行10亿港元可转债</a></td>
							<td style="text-align:right">2011-08-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131767356013188099dc40b17.jsp" class="cmsHref_self" title="苹果三星争端升级 封杀三星平板电脑在澳销售">苹果三星争端升级 封杀三星平板电脑在澳销售</a></td>
							<td style="text-align:right">2011-08-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601318801b5730b13.jsp" class="cmsHref_self" title="铅蓄电池业密集整治 多家上市公司幸为剩者">铅蓄电池业密集整治 多家上市公司幸为剩者</a></td>
							<td style="text-align:right">2011-08-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131767356013187fa3e010b0f.jsp" class="cmsHref_self" title="汉王电子书价量齐跌传递危险信号">汉王电子书价量齐跌传递危险信号</a></td>
							<td style="text-align:right">2011-08-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131767356013187ecdac40b04.jsp" class="cmsHref_self" title="骨汤供应商未明 味千又陷“添加剂门”">骨汤供应商未明 味千又陷“添加剂门”</a></td>
							<td style="text-align:right">2011-08-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131767356013182f08927010f.jsp" class="cmsHref_self" title="索尼新索双拳出击 逐鹿BD光盘市场">索尼新索双拳出击 逐鹿BD光盘市场</a></td>
							<td style="text-align:right">2011-08-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131767356013182eb87b4010e.jsp" class="cmsHref_self" title="苹果取代诺基亚成世界头号智能手机生产商">苹果取代诺基亚成世界头号智能手机生产商</a></td>
							<td style="text-align:right">2011-08-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131767356013182e8b28f010b.jsp" class="cmsHref_self" title="三星停止公布手机平板电脑销售数据及预期">三星停止公布手机平板电脑销售数据及预期</a></td>
							<td style="text-align:right">2011-08-01</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_6.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_8.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：7/29 每页20条 </span>
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