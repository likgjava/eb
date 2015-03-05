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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131f586b50131f96cab520072.jsp" class="cmsHref_self" title="格力电器投资50亿元在渝建设西南产业基地">格力电器投资50亿元在渝建设西南产业基地</a></td>
							<td style="text-align:right">2011-08-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131f586b50131f94687e10041.jsp" class="cmsHref_self" title="传苹果供应商正生产8GB闪存低价iPhone 4">传苹果供应商正生产8GB闪存低价iPhone 4</a></td>
							<td style="text-align:right">2011-08-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131f586b50131f945f5ba0040.jsp" class="cmsHref_self" title="传苹果选定LG三星夏普为iPad3显示屏供应商">传苹果选定LG三星夏普为iPad3显示屏供应商</a></td>
							<td style="text-align:right">2011-08-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131f586b50131f93ef520003b.jsp" class="cmsHref_self" title="新飞电器接连发生人事大地震">新飞电器接连发生人事大地震</a></td>
							<td style="text-align:right">2011-08-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131f586b50131f93d0682003a.jsp" class="cmsHref_self" title="锦湖轮胎南京工厂因污染被勒令搬迁">锦湖轮胎南京工厂因污染被勒令搬迁</a></td>
							<td style="text-align:right">2011-08-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131f586b50131f93c10a60039.jsp" class="cmsHref_self" title="新亚纸业新项目因环境违法被环保部勒令停产">新亚纸业新项目因环境违法被环保部勒令停产</a></td>
							<td style="text-align:right">2011-08-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131f586b50131f93b87ee0038.jsp" class="cmsHref_self" title="奇瑞拓非洲市场 协议已签将建合资公司">奇瑞拓非洲市场 协议已签将建合资公司</a></td>
							<td style="text-align:right">2011-08-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131f188870131f4448da6002f.jsp" class="cmsHref_self" title="燕京啤酒拟1.08亿购天牛啤酒">燕京啤酒拟1.08亿购天牛啤酒</a></td>
							<td style="text-align:right">2011-08-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131f188870131f425a1010021.jsp" class="cmsHref_self" title="茅台涨价是供需失衡还是投石问路">茅台涨价是供需失衡还是投石问路</a></td>
							<td style="text-align:right">2011-08-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131f188870131f4185baf000d.jsp" class="cmsHref_self" title="太子奶重整案再起波澜 新华联接盘未定">太子奶重整案再起波澜 新华联接盘未定</a></td>
							<td style="text-align:right">2011-08-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131f188870131f417b8f0000c.jsp" class="cmsHref_self" title="吉利汽车上半年净利润增长17%">吉利汽车上半年净利润增长17%</a></td>
							<td style="text-align:right">2011-08-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131e0e1bd0131ef177bc60043.jsp" class="cmsHref_self" title="苹果HTC等削减四季度智能手机芯片订单">苹果HTC等削减四季度智能手机芯片订单</a></td>
							<td style="text-align:right">2011-08-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131e0e1bd0131ef0f31d6003d.jsp" class="cmsHref_self" title="美的中央空调今年有望破百亿元">美的中央空调今年有望破百亿元</a></td>
							<td style="text-align:right">2011-08-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131db79dc0131df95dad5024a.jsp" class="cmsHref_self" title="华润雪花啤酒昨日宣布与贵州茅台共同增资合资贵州茅台啤酒，组建华润雪花啤酒(遵义)有限公司。华润雪花出资2.7亿元人民币，获得70%股权。">华润雪花啤酒昨日宣布与贵州茅台共同增资合资贵州茅台啤酒，组建华润雪花啤酒(遵义)有限公司。华润雪花出资2.7亿元人民币，获得70%股权。</a></td>
							<td style="text-align:right">2011-08-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131db79dc0131df951d2f0249.jsp" class="cmsHref_self" title="华润雪花2.7亿整合茅台啤酒 坚持双品牌运营">华润雪花2.7亿整合茅台啤酒 坚持双品牌运营</a></td>
							<td style="text-align:right">2011-08-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131db79dc0131df944a2d0248.jsp" class="cmsHref_self" title="天价五粮液29万一瓶 180克千足金底座涉嫌过度包装">天价五粮液29万一瓶 180克千足金底座涉嫌过度包装</a></td>
							<td style="text-align:right">2011-08-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131db79dc0131df93aff10247.jsp" class="cmsHref_self" title="光明食品拟购玛纳森75%股权">光明食品拟购玛纳森75%股权</a></td>
							<td style="text-align:right">2011-08-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131d7d3750131da72f472001d.jsp" class="cmsHref_self" title="细分市场需求 海尔空调引领行业向定制转变">细分市场需求 海尔空调引领行业向定制转变</a></td>
							<td style="text-align:right">2011-08-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131d7d3750131da6fcb6f001c.jsp" class="cmsHref_self" title="净化政府采购环境 12家不良供应商被罚">净化政府采购环境 12家不良供应商被罚</a></td>
							<td style="text-align:right">2011-08-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131d7d3750131da6dbfca001a.jsp" class="cmsHref_self" title="华药成为辉瑞无菌人用原料药供应商">华药成为辉瑞无菌人用原料药供应商</a></td>
							<td style="text-align:right">2011-08-18</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_2.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_4.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：3/29 每页20条 </span>
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