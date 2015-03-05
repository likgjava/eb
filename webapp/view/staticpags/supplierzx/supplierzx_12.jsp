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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314507731a032d.jsp" class="cmsHref_self" title="苹果将与三星和LG签订显示屏供应大单">苹果将与三星和LG签订显示屏供应大单</a></td>
							<td style="text-align:right">2011-07-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc470131450176920326.jsp" class="cmsHref_self" title="志高中央空调跃升为政府采购新选择">志高中央空调跃升为政府采购新选择</a></td>
							<td style="text-align:right">2011-07-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081313c08ec01314003dad40237.jsp" class="cmsHref_self" title="白酒巨头倡议：严守白酒安全底线">白酒巨头倡议：严守白酒安全底线</a></td>
							<td style="text-align:right">2011-07-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081313c08ec01314002b43c0236.jsp" class="cmsHref_self" title="品牌延伸正在扼杀王老吉">品牌延伸正在扼杀王老吉</a></td>
							<td style="text-align:right">2011-07-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081313c08ec01314001b6f90234.jsp" class="cmsHref_self" title="黄酒快速发展凸显人才“短板”">黄酒快速发展凸显人才“短板”</a></td>
							<td style="text-align:right">2011-07-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081313c08ec0131400035570233.jsp" class="cmsHref_self" title="飞利浦业绩遭受严重打击 二季度亏13亿欧元">飞利浦业绩遭受严重打击 二季度亏13亿欧元</a></td>
							<td style="text-align:right">2011-07-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081313c08ec01313ffb85b8022e.jsp" class="cmsHref_self" title="投入1.2万亿韩元 三星电子拟进军医疗设备业">投入1.2万亿韩元 三星电子拟进军医疗设备业</a></td>
							<td style="text-align:right">2011-07-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081313c08ec01313febb7c60221.jsp" class="cmsHref_self" title="思科计划裁员6500人 削减成本10亿元">思科计划裁员6500人 削减成本10亿元</a></td>
							<td style="text-align:right">2011-07-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081313c08ec01313feaa5f80220.jsp" class="cmsHref_self" title="好想你拟购80家专卖店直营 加强销售网络控制力">好想你拟购80家专卖店直营 加强销售网络控制力</a></td>
							<td style="text-align:right">2011-07-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081313c08ec01313fe987c6021e.jsp" class="cmsHref_self" title="苹果诉HTC初胜 或成对决Android前哨战">苹果诉HTC初胜 或成对决Android前哨战</a></td>
							<td style="text-align:right">2011-07-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081313c08ec01313fe8ff1a021d.jsp" class="cmsHref_self" title="康美药业去年炒三七赚1.2亿 遭发改委查处">康美药业去年炒三七赚1.2亿 遭发改委查处</a></td>
							<td style="text-align:right">2011-07-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081313c08ec01313fe79478021c.jsp" class="cmsHref_self" title="宏碁再撬惠普中国副总裁 冲刺智能手机与平板">宏碁再撬惠普中国副总裁 冲刺智能手机与平板</a></td>
							<td style="text-align:right">2011-07-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081313c08ec01313fe6da41021b.jsp" class="cmsHref_self" title="全国紧急排检逆行奥的斯：正在使用大项目一览">全国紧急排检逆行奥的斯：正在使用大项目一览</a></td>
							<td style="text-align:right">2011-07-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081313c08ec01313fe5cfec021a.jsp" class="cmsHref_self" title="家具协会为达芬奇开脱：出口再进口合法就行">家具协会为达芬奇开脱：出口再进口合法就行</a></td>
							<td style="text-align:right">2011-07-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081313c08ec01313fddece10214.jsp" class="cmsHref_self" title="威海高新区建立政府采购投标供应商准入机制">威海高新区建立政府采购投标供应商准入机制</a></td>
							<td style="text-align:right">2011-07-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081313c08ec01313fdca2a00213.jsp" class="cmsHref_self" title="运营商不愿只"吃"苹果 今年重点采购3G手机">运营商不愿只"吃"苹果 今年重点采购3G手机</a></td>
							<td style="text-align:right">2011-07-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081312d69ed01313ade9e09022b.jsp" class="cmsHref_self" title="升特推出全球最小的4A双通道POL稳压器">升特推出全球最小的4A双通道POL稳压器</a></td>
							<td style="text-align:right">2011-07-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081312d69ed01313ad1aa700224.jsp" class="cmsHref_self" title="商务部收到收购申报材料 雀巢收徐福记将受反垄断审查">商务部收到收购申报材料 雀巢收徐福记将受反垄断审查</a></td>
							<td style="text-align:right">2011-07-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081312d69ed01313acfd5b20223.jsp" class="cmsHref_self" title="三星在美国起诉西门子子公司LED侵权">三星在美国起诉西门子子公司LED侵权</a></td>
							<td style="text-align:right">2011-07-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081312d69ed01313ac4dc900218.jsp" class="cmsHref_self" title="苹果控HTC侵权部分成立 HTC称将会作出上诉">苹果控HTC侵权部分成立 HTC称将会作出上诉</a></td>
							<td style="text-align:right">2011-07-18</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_11.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_13.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：12/29 每页20条 </span>
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