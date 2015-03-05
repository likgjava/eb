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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130bf34b60130bf52b2cf00a0.jsp" class="cmsHref_self" title="原材料市场供应偏紧 卫浴市场整体价格上涨">原材料市场供应偏紧 卫浴市场整体价格上涨</a></td>
							<td style="text-align:right">2011-06-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130bf34b60130bf5232d1009e.jsp" class="cmsHref_self" title="6月中旬原材料供应减少钛白粉价格整体上浮">6月中旬原材料供应减少钛白粉价格整体上浮</a></td>
							<td style="text-align:right">2011-06-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130bf34b60130bf50ce87009a.jsp" class="cmsHref_self" title="全球大豆或供不应求 对南美供应依赖增强">全球大豆或供不应求 对南美供应依赖增强</a></td>
							<td style="text-align:right">2011-06-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130bf34b60130bf5011be0098.jsp" class="cmsHref_self" title="TDI新产能或将促使欧洲市场供货过剩">TDI新产能或将促使欧洲市场供货过剩</a></td>
							<td style="text-align:right">2011-06-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130bf34b60130bf4e18dc0096.jsp" class="cmsHref_self" title="日本地震导致中国汽车零配件减产或停产">日本地震导致中国汽车零配件减产或停产</a></td>
							<td style="text-align:right">2011-06-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130bc5ca70130bf177b210028.jsp" class="cmsHref_self" title="成品油供需紧张情况有所缓解">成品油供需紧张情况有所缓解</a></td>
							<td style="text-align:right">2011-06-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130b726330130ba1b054f0198.jsp" class="cmsHref_self" title="食品企业如何应对“下海大潮”？">食品企业如何应对“下海大潮”？</a></td>
							<td style="text-align:right">2011-06-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130b726330130ba19f11b0196.jsp" class="cmsHref_self" title="中国独占83%全球LED显示屏市场">中国独占83%全球LED显示屏市场</a></td>
							<td style="text-align:right">2011-06-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130b726330130b9f8233f0153.jsp" class="cmsHref_self" title="全球前4月铜市供应过剩116,900吨">全球前4月铜市供应过剩116,900吨</a></td>
							<td style="text-align:right">2011-06-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130afdee10130b4e7b0ef0520.jsp" class="cmsHref_self" title="零部件供应短缺 5月英国汽车销量有所下降">零部件供应短缺 5月英国汽车销量有所下降</a></td>
							<td style="text-align:right">2011-06-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130afdee10130b4e5ea31051c.jsp" class="cmsHref_self" title="三星将引领智能3G手机普及化浪潮">三星将引领智能3G手机普及化浪潮</a></td>
							<td style="text-align:right">2011-06-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130afdee10130b4e509f2051a.jsp" class="cmsHref_self" title="液晶面板连跌11个月 中企遭日韩价格绑架?">液晶面板连跌11个月 中企遭日韩价格绑架?</a></td>
							<td style="text-align:right">2011-06-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130ab37e40130afb7166c05db.jsp" class="cmsHref_self" title="工程机械销量连续两月下滑 行业或告别黄金时代">工程机械销量连续两月下滑 行业或告别黄金时代</a></td>
							<td style="text-align:right">2011-06-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130ab37e40130afb0362d05ab.jsp" class="cmsHref_self" title="日系车渡过“余震”市场供应趋向正常">日系车渡过“余震”市场供应趋向正常</a></td>
							<td style="text-align:right">2011-06-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130ab37e40130afad0fac059c.jsp" class="cmsHref_self" title="自主需求迫切国内车企资本融资提速">自主需求迫切国内车企资本融资提速</a></td>
							<td style="text-align:right">2011-06-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130ab37e40130afac00a20593.jsp" class="cmsHref_self" title="美国棉花出口需求疲软 新棉长势不佳">美国棉花出口需求疲软 新棉长势不佳</a></td>
							<td style="text-align:right">2011-06-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081309cd86d0130aa77e63f0013.jsp" class="cmsHref_self" title="国内啤酒业下半年涨价预期强烈">国内啤酒业下半年涨价预期强烈</a></td>
							<td style="text-align:right">2011-06-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081309200df01309b6e75b7060c.jsp" class="cmsHref_self" title="国信招标集团首次应用电子化平台公开招标">国信招标集团首次应用电子化平台公开招标</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081309200df01309b6c7f210601.jsp" class="cmsHref_self" title="投标商应用电子化招标平台投标反馈良好">投标商应用电子化招标平台投标反馈良好</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081309200df01309b208fc6038f.jsp" class="cmsHref_self" title="零部件供应短缺 5月英国汽车产量下降4.9%">零部件供应短缺 5月英国汽车产量下降4.9%</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_9.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_11.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_19.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：10/19 每页20条 </span>
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