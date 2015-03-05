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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f7c8d65012f8fdca2f10889.jsp" class="cmsHref_self" title="八面开工 229台打桩机参与厦门仙岳路改造">八面开工 229台打桩机参与厦门仙岳路改造</a></td>
							<td style="text-align:right">2011-04-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f7c8d65012f8b72678b053f.jsp" class="cmsHref_self" title="东莞台博会落幕 创造采购商机逾20亿">东莞台博会落幕 创造采购商机逾20亿</a></td>
							<td style="text-align:right">2011-04-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f7c8d65012f8b671e2b053d.jsp" class="cmsHref_self" title="山东某大型纺企下调皮棉采购价500元">山东某大型纺企下调皮棉采购价500元</a></td>
							<td style="text-align:right">2011-04-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f7c8d65012f8b22ab3604cf.jsp" class="cmsHref_self" title="内蒙古再投5亿元加大京藏高速扩能改造">内蒙古再投5亿元加大京藏高速扩能改造</a></td>
							<td style="text-align:right">2011-04-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f7c8d65012f8b21fecb04be.jsp" class="cmsHref_self" title="青岛公布十二五交通规划 建两纵两横大通道">青岛公布十二五交通规划 建两纵两横大通道</a></td>
							<td style="text-align:right">2011-04-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f7c8d65012f8b2152aa04ae.jsp" class="cmsHref_self" title="甘肃和政重大项目集中开工 总投资13.5亿">甘肃和政重大项目集中开工 总投资13.5亿</a></td>
							<td style="text-align:right">2011-04-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f7c8d65012f8b20e58404a3.jsp" class="cmsHref_self" title="内蒙古45个投资亿元以上重点项目集中开工">内蒙古45个投资亿元以上重点项目集中开工</a></td>
							<td style="text-align:right">2011-04-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f7ae5ba012f7b2f37e600f6.jsp" class="cmsHref_self" title="移动将采购TD-LTE试验终端 5月份完成首轮招标">移动将采购TD-LTE试验终端 5月份完成首轮招标</a></td>
							<td style="text-align:right">2011-04-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f7ae5ba012f7b2b2fb000e9.jsp" class="cmsHref_self" title="3月空调采购凸显政策功能">3月空调采购凸显政策功能</a></td>
							<td style="text-align:right">2011-04-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f7ae5ba012f7b2a404e00e4.jsp" class="cmsHref_self" title="安徽家具采购劲刮“绿色风”">安徽家具采购劲刮“绿色风”</a></td>
							<td style="text-align:right">2011-04-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f7ae5ba012f7b273a3f00dc.jsp" class="cmsHref_self" title="第四届中国高端水采购交易会27日举办">第四届中国高端水采购交易会27日举办</a></td>
							<td style="text-align:right">2011-04-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f7ae5ba012f7b2590d400d7.jsp" class="cmsHref_self" title="印度2010/2011年销售季节大米采购同期下降1％">印度2010/2011年销售季节大米采购同期下降1％</a></td>
							<td style="text-align:right">2011-04-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f6dbbe8012f708d85f80059.jsp" class="cmsHref_self" title="印度采购中国复合肥">印度采购中国复合肥</a></td>
							<td style="text-align:right">2011-04-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f588033012f6b9396f2090e.jsp" class="cmsHref_self" title="瓷海国际中国艺术瓷砖节暨巴西采购节举行">瓷海国际中国艺术瓷砖节暨巴西采购节举行</a></td>
							<td style="text-align:right">2011-04-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f588033012f6b733e2208fd.jsp" class="cmsHref_self" title="全国各地集中采购正版软件提速">全国各地集中采购正版软件提速</a></td>
							<td style="text-align:right">2011-04-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f588033012f6b70e37708f1.jsp" class="cmsHref_self" title="苗木行情看涨迎"牛市" 南昌组团去外地采购">苗木行情看涨迎"牛市" 南昌组团去外地采购</a></td>
							<td style="text-align:right">2011-04-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f588033012f6b62874208e0.jsp" class="cmsHref_self" title="河南省财政2.7亿元补贴电煤采购">河南省财政2.7亿元补贴电煤采购</a></td>
							<td style="text-align:right">2011-04-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f588033012f6664bec80417.jsp" class="cmsHref_self" title="首届“Interwine深圳世界葡萄酒采购博览会”将于5月13日拉开帷幕">首届“Interwine深圳世界葡萄酒采购博览会”将于5月13日拉开帷幕</a></td>
							<td style="text-align:right">2011-04-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f4e78d5012f56f54fa414c2.jsp" class="cmsHref_self" title="埃及SIIC招标采购了5万吨原糖">埃及SIIC招标采购了5万吨原糖</a></td>
							<td style="text-align:right">2011-04-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f4e78d5012f56e28b0914ae.jsp" class="cmsHref_self" title="第65届医博会16日在深开幕 600余种新品将上市">第65届医博会16日在深开幕 600余种新品将上市</a></td>
							<td style="text-align:right">2011-04-15</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_15.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_17.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_18.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：16/18 每页20条 </span>
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