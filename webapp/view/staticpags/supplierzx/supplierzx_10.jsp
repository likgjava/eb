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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a013163e3358b02be.jsp" class="cmsHref_self" title="光明乳业获世泳赛推荐供应商资格">光明乳业获世泳赛推荐供应商资格</a></td>
							<td style="text-align:right">2011-07-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a013163e21cd702bd.jsp" class="cmsHref_self" title="出事高铁ATP系统供应商和利时称与其产品无关">出事高铁ATP系统供应商和利时称与其产品无关</a></td>
							<td style="text-align:right">2011-07-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a013163e12fe402bb.jsp" class="cmsHref_self" title="高铁配套设备供应商面临洗牌">高铁配套设备供应商面临洗牌</a></td>
							<td style="text-align:right">2011-07-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01315f19dc4b0101.jsp" class="cmsHref_self" title="比亚迪拟14.82亿元增资旗下四公司">比亚迪拟14.82亿元增资旗下四公司</a></td>
							<td style="text-align:right">2011-07-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01315f19362b0100.jsp" class="cmsHref_self" title="大众拟提高合资公司股权比例 奥迪或建独立公司">大众拟提高合资公司股权比例 奥迪或建独立公司</a></td>
							<td style="text-align:right">2011-07-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01315f188c4300fe.jsp" class="cmsHref_self" title="双汇锁定东南亚海外市场 万隆称必要时进口猪肉">双汇锁定东南亚海外市场 万隆称必要时进口猪肉</a></td>
							<td style="text-align:right">2011-07-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01315f0dc59300f2.jsp" class="cmsHref_self" title="三只铁路安全设备供应商周一临时停牌">三只铁路安全设备供应商周一临时停牌</a></td>
							<td style="text-align:right">2011-07-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01315f0cc6bc00f0.jsp" class="cmsHref_self" title="国际供应商垄断加剧 钾肥价格连年上涨">国际供应商垄断加剧 钾肥价格连年上涨</a></td>
							<td style="text-align:right">2011-07-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01315f0baf000014.jsp" class="cmsHref_self" title="温州高铁事故 设备系统供应商受关注">温州高铁事故 设备系统供应商受关注</a></td>
							<td style="text-align:right">2011-07-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01315f0a3bfa0013.jsp" class="cmsHref_self" title="萨博汽车附属公司供应商宣布破产">萨博汽车附属公司供应商宣布破产</a></td>
							<td style="text-align:right">2011-07-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01315f0957fa0012.jsp" class="cmsHref_self" title="12家不法红酒供应商被立案查处">12家不法红酒供应商被立案查处</a></td>
							<td style="text-align:right">2011-07-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314f77dcff12c1.jsp" class="cmsHref_self" title="诺基亚一年半首亏 第二季度净亏5.21亿">诺基亚一年半首亏 第二季度净亏5.21亿</a></td>
							<td style="text-align:right">2011-07-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314f74618312bc.jsp" class="cmsHref_self" title="华为PK苹果：靠综合实力创新最重要">华为PK苹果：靠综合实力创新最重要</a></td>
							<td style="text-align:right">2011-07-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314f6b5b7f12b1.jsp" class="cmsHref_self" title="欧洲最严玩具法令施行 玩具企业成本或增三成">欧洲最严玩具法令施行 玩具企业成本或增三成</a></td>
							<td style="text-align:right">2011-07-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314f6ac30e1243.jsp" class="cmsHref_self" title="华润雪花三下河南收购蓝牌 挑战金星">华润雪花三下河南收购蓝牌 挑战金星</a></td>
							<td style="text-align:right">2011-07-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314f5f8507123a.jsp" class="cmsHref_self" title="达芬奇绑国际品牌获高额利润 长期处监管真空">达芬奇绑国际品牌获高额利润 长期处监管真空</a></td>
							<td style="text-align:right">2011-07-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314f5e7f081239.jsp" class="cmsHref_self" title="雀巢收购徐福记待批 多家媒体称难通过">雀巢收购徐福记待批 多家媒体称难通过</a></td>
							<td style="text-align:right">2011-07-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314f5d1be91238.jsp" class="cmsHref_self" title="达芬奇事件余震不断 官网隐藏公共关系邮箱">达芬奇事件余震不断 官网隐藏公共关系邮箱</a></td>
							<td style="text-align:right">2011-07-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314f59b9d8121a.jsp" class="cmsHref_self" title="汽车新三巨头：大众日产通用战胜市场下滑">汽车新三巨头：大众日产通用战胜市场下滑</a></td>
							<td style="text-align:right">2011-07-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314f58b6081219.jsp" class="cmsHref_self" title="苹果2011中国营收38亿美元 同比增6倍">苹果2011中国营收38亿美元 同比增6倍</a></td>
							<td style="text-align:right">2011-07-22</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_9.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_11.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：10/29 每页20条 </span>
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