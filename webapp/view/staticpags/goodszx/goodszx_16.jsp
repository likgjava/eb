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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813026ae99013029c6e8da001d.jsp" class="cmsHref_self" title="药用辅料业进入景气期 年需求高速增长">药用辅料业进入景气期 年需求高速增长</a></td>
							<td style="text-align:right">2011-05-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813026ae99013029c64990001b.jsp" class="cmsHref_self" title="从满足需求到创造需求 中国家电业智能转型">从满足需求到创造需求 中国家电业智能转型</a></td>
							<td style="text-align:right">2011-05-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813026ae99013029c401030019.jsp" class="cmsHref_self" title="内外需求放缓 美豆粕承压收低">内外需求放缓 美豆粕承压收低</a></td>
							<td style="text-align:right">2011-05-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813026ae99013029b8b4f40009.jsp" class="cmsHref_self" title="当前国内煤炭供应充足 发电企业存煤达16天">当前国内煤炭供应充足 发电企业存煤达16天</a></td>
							<td style="text-align:right">2011-05-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081302107010130249b4d72012d.jsp" class="cmsHref_self" title="大连柴油供应状况平稳">大连柴油供应状况平稳</a></td>
							<td style="text-align:right">2011-05-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813008ca4701301fa1f6b60615.jsp" class="cmsHref_self" title="甲醛价格年年高 成本与需求谁主沉浮？">甲醛价格年年高 成本与需求谁主沉浮？</a></td>
							<td style="text-align:right">2011-05-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813008ca4701301f93766905c7.jsp" class="cmsHref_self" title="进口铁矿石价格连跌两周 紧缩政策影响钢铁行业">进口铁矿石价格连跌两周 紧缩政策影响钢铁行业</a></td>
							<td style="text-align:right">2011-05-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813008ca4701301f90e1df05bc.jsp" class="cmsHref_self" title="水泥价格继续上涨 全年趋势向好">水泥价格继续上涨 全年趋势向好</a></td>
							<td style="text-align:right">2011-05-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813008ca4701301f7f2c10058b.jsp" class="cmsHref_self" title="棉价再出现大起大落 部分棉农至今一斤棉花未卖">棉价再出现大起大落 部分棉农至今一斤棉花未卖</a></td>
							<td style="text-align:right">2011-05-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813008ca4701301f67b67504fa.jsp" class="cmsHref_self" title="中药材价遇近十年来最猛涨幅 被称“药你命”">中药材价遇近十年来最猛涨幅 被称“药你命”</a></td>
							<td style="text-align:right">2011-05-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fe93117012ff6a1245e02c5.jsp" class="cmsHref_self" title="化工行业：海外尿素持续上涨 国内纯碱稳中有升">化工行业：海外尿素持续上涨 国内纯碱稳中有升</a></td>
							<td style="text-align:right">2011-05-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fe93117012ff69c4c8102b7.jsp" class="cmsHref_self" title="泰国建材价格2011年4月上涨4.5％">泰国建材价格2011年4月上涨4.5％</a></td>
							<td style="text-align:right">2011-05-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fe93117012ff68a21e302a5.jsp" class="cmsHref_self" title="“绿色”趋势：低碳将成打印机新标配">“绿色”趋势：低碳将成打印机新标配</a></td>
							<td style="text-align:right">2011-05-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fe93117012ff684bb3002a1.jsp" class="cmsHref_self" title="中小企业安全路由器功能及选购">中小企业安全路由器功能及选购</a></td>
							<td style="text-align:right">2011-05-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fe93117012ff6818665029f.jsp" class="cmsHref_self" title="2011年 红木市场是增值保值的金山？">2011年 红木市场是增值保值的金山？</a></td>
							<td style="text-align:right">2011-05-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fe93117012ff67a74ff029d.jsp" class="cmsHref_self" title="台州起草阀门新标准 有望填补国内行业空白">台州起草阀门新标准 有望填补国内行业空白</a></td>
							<td style="text-align:right">2011-05-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fe93117012ff65a82b30139.jsp" class="cmsHref_self" title="供应吃紧支持 纽约原糖继续收升">供应吃紧支持 纽约原糖继续收升</a></td>
							<td style="text-align:right">2011-05-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fde6366012fe19c680e001f.jsp" class="cmsHref_self" title="公务车新政 推高1.8L轿车需求">公务车新政 推高1.8L轿车需求</a></td>
							<td style="text-align:right">2011-05-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fdc86b15b0d94.jsp" class="cmsHref_self" title="日本成为越南纺织品出口第二大市场">日本成为越南纺织品出口第二大市场</a></td>
							<td style="text-align:right">2011-05-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fdc85a16e0d92.jsp" class="cmsHref_self" title="山东原材料涨价 企业技改明显放缓">山东原材料涨价 企业技改明显放缓</a></td>
							<td style="text-align:right">2011-05-11</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_15.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_17.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_19.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：16/19 每页20条 </span>
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