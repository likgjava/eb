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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812eec2137012efa992ae60f54.jsp" class="cmsHref_self" title="中联拟建华东基地 江阴欲将引来百亿投资">中联拟建华东基地 江阴欲将引来百亿投资</a></td>
							<td style="text-align:right">2011-03-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812eec2137012efa905af90f52.jsp" class="cmsHref_self" title="北车再添大单 高铁行业“底气”十足">北车再添大单 高铁行业“底气”十足</a></td>
							<td style="text-align:right">2011-03-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812eec2137012efa538be20b19.jsp" class="cmsHref_self" title="宝钢金属型钢为海湾大桥加紧生产护栏管">宝钢金属型钢为海湾大桥加紧生产护栏管</a></td>
							<td style="text-align:right">2011-03-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812eec2137012efa05e880077b.jsp" class="cmsHref_self" title="海尔产业园区本月开建 年内启动LED工业项目">海尔产业园区本月开建 年内启动LED工业项目</a></td>
							<td style="text-align:right">2011-03-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812eec2137012ef9fe0de406c8.jsp" class="cmsHref_self" title="6亿美元LED项目落户增城">6亿美元LED项目落户增城</a></td>
							<td style="text-align:right">2011-03-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812ee243a4012eebaa54671cc5.jsp" class="cmsHref_self" title="兰新铁路第二双线哈密火石泉段全线复工">兰新铁路第二双线哈密火石泉段全线复工</a></td>
							<td style="text-align:right">2011-03-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812ee243a4012eeba92ec11cba.jsp" class="cmsHref_self" title="四川新建4条藏区公路2015年汶九高速建成">四川新建4条藏区公路2015年汶九高速建成</a></td>
							<td style="text-align:right">2011-03-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812ee243a4012eeba79cbe1cae.jsp" class="cmsHref_self" title="十二五总体规划编制18专项规划">十二五总体规划编制18专项规划</a></td>
							<td style="text-align:right">2011-03-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812ee243a4012eeba6fd381c35.jsp" class="cmsHref_self" title="湖北荆州水利建设6大工程力争引资160亿">湖北荆州水利建设6大工程力争引资160亿</a></td>
							<td style="text-align:right">2011-03-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812ee243a4012eea83b4ae1a3e.jsp" class="cmsHref_self" title="山西计划年内开展6个高速公路项目">山西计划年内开展6个高速公路项目</a></td>
							<td style="text-align:right">2011-03-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812ee243a4012ee64b5be11361.jsp" class="cmsHref_self" title="中国北车拟募资百亿投向“高铁车辆装备技术研发与提能改造”等五大板块">中国北车拟募资百亿投向“高铁车辆装备技术研发与提能改造”等五大板块</a></td>
							<td style="text-align:right">2011-03-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812ee243a4012ee60053c912a5.jsp" class="cmsHref_self" title="江西预7月份开工 建抚吉高速">江西预7月份开工 建抚吉高速</a></td>
							<td style="text-align:right">2011-03-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812ee243a4012ee5fc20a91293.jsp" class="cmsHref_self" title="河北拟建高密度环京交通圈">河北拟建高密度环京交通圈</a></td>
							<td style="text-align:right">2011-03-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812ee243a4012ee5f40f4c1264.jsp" class="cmsHref_self" title="西藏完成投资85亿开工建设一大批重点项目">西藏完成投资85亿开工建设一大批重点项目</a></td>
							<td style="text-align:right">2011-03-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812ee243a4012ee5b8b1d0112d.jsp" class="cmsHref_self" title="麻城建成年产12万吨聚乙烯塑料管道基地">麻城建成年产12万吨聚乙烯塑料管道基地</a></td>
							<td style="text-align:right">2011-03-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812ed7f020012ee1e36b2811b1.jsp" class="cmsHref_self" title="IT崛起 重大项目陆续投产 重庆今年新高电量带动电缆行业发展">IT崛起 重大项目陆续投产 重庆今年新高电量带动电缆行业发展</a></td>
							<td style="text-align:right">2011-03-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812ed7f020012ee1a02dca0d99.jsp" class="cmsHref_self" title="天津投资百亿启动清水工程 水处理设备和材料采购有商机">天津投资百亿启动清水工程 水处理设备和材料采购有商机</a></td>
							<td style="text-align:right">2011-03-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812ed7f020012ee18af4c40bb6.jsp" class="cmsHref_self" title="500强建材采购首选品牌测评研究">500强建材采购首选品牌测评研究</a></td>
							<td style="text-align:right">2011-03-23</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_17.jsp" title="上一页">上一页</span> 
<span class="totalNum">页次：18/18 每页20条 </span>
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