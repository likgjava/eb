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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081303fbeb7013043a3b3b103c8.jsp" class="cmsHref_self" title="大湖股份：目前旱情未严重影响到水产养殖业">大湖股份：目前旱情未严重影响到水产养殖业</a></td>
							<td style="text-align:right">2011-05-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081303fbeb7013043a20db203c6.jsp" class="cmsHref_self" title="力拓签约湘电 矿用自卸车将出口澳大利亚">力拓签约湘电 矿用自卸车将出口澳大利亚</a></td>
							<td style="text-align:right">2011-05-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081303fbeb7013043a14a1403c4.jsp" class="cmsHref_self" title="艾迪西：子公司2亿投资铸铁阀门生产线">艾迪西：子公司2亿投资铸铁阀门生产线</a></td>
							<td style="text-align:right">2011-05-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130361e0401303e808d780109.jsp" class="cmsHref_self" title="中粮集团接棒光明竞购澳洲糖企">中粮集团接棒光明竞购澳洲糖企</a></td>
							<td style="text-align:right">2011-05-30</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130361e0401303e7f8b760107.jsp" class="cmsHref_self" title="西藏城投大力发展锂产业">西藏城投大力发展锂产业</a></td>
							<td style="text-align:right">2011-05-30</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130361e0401303e5f779d001b.jsp" class="cmsHref_self" title="博世三星或为大众供应电动车电池">博世三星或为大众供应电动车电池</a></td>
							<td style="text-align:right">2011-05-30</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130361e0401303e5ea9550019.jsp" class="cmsHref_self" title="佛吉亚为日产美国工厂供应汽车座椅">佛吉亚为日产美国工厂供应汽车座椅</a></td>
							<td style="text-align:right">2011-05-30</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813026ae99013029cf32ee00a2.jsp" class="cmsHref_self" title="零部件供应商罢工暴露韩汽车制造商弱点">零部件供应商罢工暴露韩汽车制造商弱点</a></td>
							<td style="text-align:right">2011-05-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813026ae99013029cdadd0009e.jsp" class="cmsHref_self" title="供应商爆发劳资纠纷 韩国汽车陷全面停产">供应商爆发劳资纠纷 韩国汽车陷全面停产</a></td>
							<td style="text-align:right">2011-05-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813026ae99013029b575b40005.jsp" class="cmsHref_self" title="“十二五”渤海钢铁集团产能锁定3000万吨">“十二五”渤海钢铁集团产能锁定3000万吨</a></td>
							<td style="text-align:right">2011-05-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130210701013024a6acd1013a.jsp" class="cmsHref_self" title="英特尔进军平板电脑 欧德宁欲重塑移动市场格局">英特尔进军平板电脑 欧德宁欲重塑移动市场格局</a></td>
							<td style="text-align:right">2011-05-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130210701013024a2c0da0136.jsp" class="cmsHref_self" title="茶叶经营可适当创造需求">茶叶经营可适当创造需求</a></td>
							<td style="text-align:right">2011-05-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081302107010130249f1af60134.jsp" class="cmsHref_self" title="美零部件供应商天纳克公布股份回购方案">美零部件供应商天纳克公布股份回购方案</a></td>
							<td style="text-align:right">2011-05-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081302107010130249dd6a50131.jsp" class="cmsHref_self" title="巴陵石化增产成品油力保市场供应">巴陵石化增产成品油力保市场供应</a></td>
							<td style="text-align:right">2011-05-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081302107010130249ce700012f.jsp" class="cmsHref_self" title="韩国警方 介入现代起亚零部件供应商罢工">韩国警方 介入现代起亚零部件供应商罢工</a></td>
							<td style="text-align:right">2011-05-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813008ca4701301f8cb24305a5.jsp" class="cmsHref_self" title="蜀中制药中药GMP被收回 生产记录不完整引关注">蜀中制药中药GMP被收回 生产记录不完整引关注</a></td>
							<td style="text-align:right">2011-05-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813008ca4701301f83094b0598.jsp" class="cmsHref_self" title="公务车改革波及车市 奥迪遭退单风险">公务车改革波及车市 奥迪遭退单风险</a></td>
							<td style="text-align:right">2011-05-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813008ca4701301f7e26520589.jsp" class="cmsHref_self" title="宝钢有意参与中铝和力拓几内亚铁矿项目">宝钢有意参与中铝和力拓几内亚铁矿项目</a></td>
							<td style="text-align:right">2011-05-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813008ca4701301f7bcc6b0587.jsp" class="cmsHref_self" title="中钢审计结果曝多项问题 被合作企业占款超70亿">中钢审计结果曝多项问题 被合作企业占款超70亿</a></td>
							<td style="text-align:right">2011-05-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813008ca4701301f791ce40585.jsp" class="cmsHref_self" title="审计风暴揭开中铝重组能力画皮">审计风暴揭开中铝重组能力画皮</a></td>
							<td style="text-align:right">2011-05-24</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_25.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_27.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：26/29 每页20条 </span>
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