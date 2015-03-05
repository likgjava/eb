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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131db79dc0131df879852023d.jsp" class="cmsHref_self" title="一次有益的服务外包采购尝试">一次有益的服务外包采购尝试</a></td>
							<td style="text-align:right">2011-08-19</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131d1eaa60131d5465a3b0053.jsp" class="cmsHref_self" title="“一站式”教育设备采购能否颠覆传统？">“一站式”教育设备采购能否颠覆传统？</a></td>
							<td style="text-align:right">2011-08-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131d1eaa60131d54582670052.jsp" class="cmsHref_self" title="分析：我国三大缝制机械市场对比介绍">分析：我国三大缝制机械市场对比介绍</a></td>
							<td style="text-align:right">2011-08-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131ccdcaa0131d008d0970002.jsp" class="cmsHref_self" title="采购新政将引发公务车市场变革">采购新政将引发公务车市场变革</a></td>
							<td style="text-align:right">2011-08-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131c418af0131cae106f40015.jsp" class="cmsHref_self" title="我市政府采购管理制度实现重大突破">我市政府采购管理制度实现重大突破</a></td>
							<td style="text-align:right">2011-08-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131c418af0131cae0937a0014.jsp" class="cmsHref_self" title="进口软件垄断中国政府采购 国产软件如何立足?">进口软件垄断中国政府采购 国产软件如何立足?</a></td>
							<td style="text-align:right">2011-08-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131c418af0131cadf7fa40013.jsp" class="cmsHref_self" title="海尔电脑商用市场再突破获千万电脑采购订单">海尔电脑商用市场再突破获千万电脑采购订单</a></td>
							<td style="text-align:right">2011-08-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131c418af0131cadedfda0012.jsp" class="cmsHref_self" title="北京工程建设政府采购将优先使用自主创新产品">北京工程建设政府采购将优先使用自主创新产品</a></td>
							<td style="text-align:right">2011-08-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813192fe7c0131b12f53e31c91.jsp" class="cmsHref_self" title="中国在国际市场的大豆采购愈加活跃">中国在国际市场的大豆采购愈加活跃</a></td>
							<td style="text-align:right">2011-08-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813192fe7c0131b12e90391c8f.jsp" class="cmsHref_self" title="新兴市场加大黄金采购量 推进外储多样化">新兴市场加大黄金采购量 推进外储多样化</a></td>
							<td style="text-align:right">2011-08-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813192fe7c0131ac00c5a1141a.jsp" class="cmsHref_self" title="药水比矿泉水还便宜? 药物采购现超低价苗头">药水比矿泉水还便宜? 药物采购现超低价苗头</a></td>
							<td style="text-align:right">2011-08-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813192fe7c0131abff9d171418.jsp" class="cmsHref_self" title="“阳光采购”节约资金317万元">“阳光采购”节约资金317万元</a></td>
							<td style="text-align:right">2011-08-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813192fe7c0131abfe6aa31417.jsp" class="cmsHref_self" title="晋中政府采购借力纪检监">晋中政府采购借力纪检监</a></td>
							<td style="text-align:right">2011-08-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813192fe7c0131a6d42cee0aee.jsp" class="cmsHref_self" title="华西能源采购计划与采购实施严格分离">华西能源采购计划与采购实施严格分离</a></td>
							<td style="text-align:right">2011-08-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813192fe7c0131976063b90246.jsp" class="cmsHref_self" title="政府采购能否“倒逼”安全生产？">政府采购能否“倒逼”安全生产？</a></td>
							<td style="text-align:right">2011-08-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813192fe7c0131975eccc10245.jsp" class="cmsHref_self" title="盐城政府采购节能环保产品使用比例明显提高">盐城政府采购节能环保产品使用比例明显提高</a></td>
							<td style="text-align:right">2011-08-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813176735601319239e00619f4.jsp" class="cmsHref_self" title="广东政府采购将优先购买自主创新产品">广东政府采购将优先购买自主创新产品</a></td>
							<td style="text-align:right">2011-08-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813176735601319239259a19f3.jsp" class="cmsHref_self" title="中韩签订2500亿元“硅能蓄电池”合作协议">中韩签订2500亿元“硅能蓄电池”合作协议</a></td>
							<td style="text-align:right">2011-08-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813176735601319237f59619f2.jsp" class="cmsHref_self" title="河北32项制度规范政府采购流程">河北32项制度规范政府采购流程</a></td>
							<td style="text-align:right">2011-08-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813176735601318d311ba01251.jsp" class="cmsHref_self" title="政府采购让空气能热水器市场增温75°">政府采购让空气能热水器市场增温75°</a></td>
							<td style="text-align:right">2011-08-03</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_3.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_18.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：2/18 每页20条 </span>
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