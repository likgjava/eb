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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813008ca4701301f96b74b05d5.jsp" class="cmsHref_self" title="今年小麦托市收购或暂停 代储库哄抬粮价面临整顿">今年小麦托市收购或暂停 代储库哄抬粮价面临整顿</a></td>
							<td style="text-align:right">2011-05-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813008ca4701301f9596de05cf.jsp" class="cmsHref_self" title="光伏产品价格全线跳水 组件电池三个月下跌两成">光伏产品价格全线跳水 组件电池三个月下跌两成</a></td>
							<td style="text-align:right">2011-05-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813008ca4701301f8175520592.jsp" class="cmsHref_self" title="多路资本逐鹿粮食市场 囤地抢粮现象加剧">多路资本逐鹿粮食市场 囤地抢粮现象加剧</a></td>
							<td style="text-align:right">2011-05-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fe93117012ff65868e20110.jsp" class="cmsHref_self" title="PCB厂：“电荒”恐会影响印刷电路板供应">PCB厂：“电荒”恐会影响印刷电路板供应</a></td>
							<td style="text-align:right">2011-05-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fe93117012ff654363e0105.jsp" class="cmsHref_self" title="存储采购：应用为中心的存储">存储采购：应用为中心的存储</a></td>
							<td style="text-align:right">2011-05-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fe93117012ff652485b0101.jsp" class="cmsHref_self" title="省卫生厅规范药品集中采购 招标“严丝合缝”">省卫生厅规范药品集中采购 招标“严丝合缝”</a></td>
							<td style="text-align:right">2011-05-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fe93117012ff651d23700ff.jsp" class="cmsHref_self" title="海外采购商青睐中国文化项目">海外采购商青睐中国文化项目</a></td>
							<td style="text-align:right">2011-05-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fe3e2c6012fe6d19ae6017f.jsp" class="cmsHref_self" title="山东中烟强化供应链管理实施无缝联接">山东中烟强化供应链管理实施无缝联接</a></td>
							<td style="text-align:right">2011-05-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fe3e2c6012fe6d01022017b.jsp" class="cmsHref_self" title="苹果供应链紧张 影响iPad和iPhone销售">苹果供应链紧张 影响iPad和iPhone销售</a></td>
							<td style="text-align:right">2011-05-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fe3e2c6012fe6ce33500179.jsp" class="cmsHref_self" title="“十二五”药用辅料需求将保持高增长">“十二五”药用辅料需求将保持高增长</a></td>
							<td style="text-align:right">2011-05-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fe3e2c6012fe6cd23150177.jsp" class="cmsHref_self" title="国际能源署下调全球原油需求预期">国际能源署下调全球原油需求预期</a></td>
							<td style="text-align:right">2011-05-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fe3e2c6012fe6cbee9f0175.jsp" class="cmsHref_self" title="机械制造业用工需求增最多">机械制造业用工需求增最多</a></td>
							<td style="text-align:right">2011-05-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fe3e2c6012fe6ca7e300173.jsp" class="cmsHref_self" title="鞍钢股份：铁矿石采购模式引争议">鞍钢股份：铁矿石采购模式引争议</a></td>
							<td style="text-align:right">2011-05-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fe3e2c6012fe6c8dd720171.jsp" class="cmsHref_self" title="中汽协有望发布政府市场汽车采购数据">中汽协有望发布政府市场汽车采购数据</a></td>
							<td style="text-align:right">2011-05-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fe3e2c6012fe6c835de016f.jsp" class="cmsHref_self" title="财政部：前期受托服务供应商不得再参与采购">财政部：前期受托服务供应商不得再参与采购</a></td>
							<td style="text-align:right">2011-05-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fe3e2c6012fe6c7490d016b.jsp" class="cmsHref_self" title="山东率先完成基本药物采购 降价过半">山东率先完成基本药物采购 降价过半</a></td>
							<td style="text-align:right">2011-05-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fe3e2c6012fe6c679d00129.jsp" class="cmsHref_self" title="韩企将组团来渝采购汽车零部件">韩企将组团来渝采购汽车零部件</a></td>
							<td style="text-align:right">2011-05-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fde6366012fe19a1e2e001b.jsp" class="cmsHref_self" title="波音在华采购额计划5年翻番">波音在华采购额计划5年翻番</a></td>
							<td style="text-align:right">2011-05-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fde6366012fe19905ab0017.jsp" class="cmsHref_self" title="蔬菜供应商采购1万公斤包心菜">蔬菜供应商采购1万公斤包心菜</a></td>
							<td style="text-align:right">2011-05-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fbfbecc012fdc80b6030d90.jsp" class="cmsHref_self" title="中国将全面实施政府采购与自主创新分离政策">中国将全面实施政府采购与自主创新分离政策</a></td>
							<td style="text-align:right">2011-05-11</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_12.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_14.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_18.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：13/18 每页20条 </span>
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