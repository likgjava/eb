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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fbfbecc012fdc7ff4870d8e.jsp" class="cmsHref_self" title="采购新规下 谁会是新宠？">采购新规下 谁会是新宠？</a></td>
							<td style="text-align:right">2011-05-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fbfbecc012fdc7511630d7e.jsp" class="cmsHref_self" title="广东钢材交易中心今开业">广东钢材交易中心今开业</a></td>
							<td style="text-align:right">2011-05-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fbfbecc012fd79135870c9a.jsp" class="cmsHref_self" title="政府采购标准化建设应从三方面着手">政府采购标准化建设应从三方面着手</a></td>
							<td style="text-align:right">2011-05-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fbfbecc012fd78dcf470c98.jsp" class="cmsHref_self" title="纺企采购意愿淡漠 棉花报价大幅下跌成交低迷">纺企采购意愿淡漠 棉花报价大幅下跌成交低迷</a></td>
							<td style="text-align:right">2011-05-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fbfbecc012fd78a541b0c8e.jsp" class="cmsHref_self" title="东北最大国际农产品采购中心项目被疑乌龙">东北最大国际农产品采购中心项目被疑乌龙</a></td>
							<td style="text-align:right">2011-05-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fbfbecc012fd2adf21507c9.jsp" class="cmsHref_self" title="中国劳工成本增加 西欧纺织服装采购商陷入困境">中国劳工成本增加 西欧纺织服装采购商陷入困境</a></td>
							<td style="text-align:right">2011-05-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fbfbecc012fd2ab975f07c7.jsp" class="cmsHref_self" title="药监局：甜品站采购食品有规定">药监局：甜品站采购食品有规定</a></td>
							<td style="text-align:right">2011-05-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fbfbecc012fd2a79abe07c5.jsp" class="cmsHref_self" title="巢湖首批招标采购监督员持证上岗">巢湖首批招标采购监督员持证上岗</a></td>
							<td style="text-align:right">2011-05-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fbfbecc012fd2a651d707c3.jsp" class="cmsHref_self" title="采购商：为水产收购 加工旺季做好准备">采购商：为水产收购 加工旺季做好准备</a></td>
							<td style="text-align:right">2011-05-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fbfbecc012fd2a1eccd07ba.jsp" class="cmsHref_self" title="生猪疫苗采购效果不佳 巨额财政资金或打水漂">生猪疫苗采购效果不佳 巨额财政资金或打水漂</a></td>
							<td style="text-align:right">2011-05-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fbfbecc012fd29d6b6007b2.jsp" class="cmsHref_self" title="安次区国家税务局实施有力举措规范政府采购">安次区国家税务局实施有力举措规范政府采购</a></td>
							<td style="text-align:right">2011-05-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fbfbecc012fc357ba7d027b.jsp" class="cmsHref_self" title="商务部公布对美部分进口汽车双反调查终裁决定">商务部公布对美部分进口汽车双反调查终裁决定</a></td>
							<td style="text-align:right">2011-05-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fbfbecc012fc353e7490279.jsp" class="cmsHref_self" title="天津出台《加强中药饮片监督管理工作的通知》">天津出台《加强中药饮片监督管理工作的通知》</a></td>
							<td style="text-align:right">2011-05-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fbfbecc012fc34f0380026d.jsp" class="cmsHref_self" title="中国工程机械正迎来扩大东盟市场好时机">中国工程机械正迎来扩大东盟市场好时机</a></td>
							<td style="text-align:right">2011-05-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fba4e32012fbeb444f0059f.jsp" class="cmsHref_self" title="浙江永康2011年将投3400万整治农村公路">浙江永康2011年将投3400万整治农村公路</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fba4e32012fbeb3e752059d.jsp" class="cmsHref_self" title="重庆渝黔新线和成渝客运高铁2011年开工">重庆渝黔新线和成渝客运高铁2011年开工</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fba4e32012fbeb3166e059b.jsp" class="cmsHref_self" title="唐津高速津塘公路至荣乌高速路段将扩建">唐津高速津塘公路至荣乌高速路段将扩建</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fba4e32012fbeb26bb30599.jsp" class="cmsHref_self" title="抢占市场 国内线缆企业要尽快实现产业升级">抢占市场 国内线缆企业要尽快实现产业升级</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fba4e32012fbeb1b61f0597.jsp" class="cmsHref_self" title="大截面导线风力发电电缆设备生产基地开工">大截面导线风力发电电缆设备生产基地开工</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fba4e32012fbe1daee00214.jsp" class="cmsHref_self" title="美国最新石油供需状况分析报告">美国最新石油供需状况分析报告</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_13.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_15.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_18.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：14/18 每页20条 </span>
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