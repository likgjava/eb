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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813078a95601308b9c050c11d2.jsp" class="cmsHref_self" title="高振华：如何防范政府采购风险">高振华：如何防范政府采购风险</a></td>
							<td style="text-align:right">2011-06-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813078a95601308b9af4ae11d0.jsp" class="cmsHref_self" title="联合国下属机构首次参与主办西博会分论坛和采购商大会">联合国下属机构首次参与主办西博会分论坛和采购商大会</a></td>
							<td style="text-align:right">2011-06-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813078a95601308b98fcc311b7.jsp" class="cmsHref_self" title="安徽：加大采购力度积极应对旱情">安徽：加大采购力度积极应对旱情</a></td>
							<td style="text-align:right">2011-06-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813078a95601308b985f2011b2.jsp" class="cmsHref_self" title="政府采购信用融资为何叫好不叫座">政府采购信用融资为何叫好不叫座</a></td>
							<td style="text-align:right">2011-06-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813078a95601308b96baa0119f.jsp" class="cmsHref_self" title="越南政府采购鼎力“革新开放”">越南政府采购鼎力“革新开放”</a></td>
							<td style="text-align:right">2011-06-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813078a95601308b957328119b.jsp" class="cmsHref_self" title="欧盟拟设政府采购新规">欧盟拟设政府采购新规</a></td>
							<td style="text-align:right">2011-06-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813078a95601308b93b6291184.jsp" class="cmsHref_self" title="月饼提早一个月上市 原料采购更严苛需时更长">月饼提早一个月上市 原料采购更严苛需时更长</a></td>
							<td style="text-align:right">2011-06-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813078a95601308674f41b035d.jsp" class="cmsHref_self" title="4企业8产品含塑化剂被紧急查封 沪餐饮业禁止采购">4企业8产品含塑化剂被紧急查封 沪餐饮业禁止采购</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813078a95601308673a191035b.jsp" class="cmsHref_self" title="蓝代斯克入围2011年中央政府采购系统">蓝代斯克入围2011年中央政府采购系统</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813078a95601308671127c0356.jsp" class="cmsHref_self" title="大陆宣布惠及两岸新举措 对台农产品采购常态化">大陆宣布惠及两岸新举措 对台农产品采购常态化</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813078a9560130866ff7380354.jsp" class="cmsHref_self" title="公务车采购新规出台 涡轮增压车型受青睐">公务车采购新规出台 涡轮增压车型受青睐</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f013077254bda4304.jsp" class="cmsHref_self" title="沃尔玛“农超对接”采购百吨西瓜">沃尔玛“农超对接”采购百吨西瓜</a></td>
							<td style="text-align:right">2011-06-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f013077162c944280.jsp" class="cmsHref_self" title="中关村新兴产业全年采购任务已完成过半">中关村新兴产业全年采购任务已完成过半</a></td>
							<td style="text-align:right">2011-06-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f013076fa7570413a.jsp" class="cmsHref_self" title="铅市外强内弱 “电荒”引发铅采购减少">铅市外强内弱 “电荒”引发铅采购减少</a></td>
							<td style="text-align:right">2011-06-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f013076f96d364138.jsp" class="cmsHref_self" title="节能产品可进入省政府采购清单">节能产品可进入省政府采购清单</a></td>
							<td style="text-align:right">2011-06-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f013071deccec3059.jsp" class="cmsHref_self" title="采购商借棉价下探压价 服装出口订单持续胶着">采购商借棉价下探压价 服装出口订单持续胶着</a></td>
							<td style="text-align:right">2011-06-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f013071de166c3057.jsp" class="cmsHref_self" title="政府采购在公车改革中应起到突出作用">政府采购在公车改革中应起到突出作用</a></td>
							<td style="text-align:right">2011-06-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f013071dd7f633055.jsp" class="cmsHref_self" title="苹果超过惠普成第一大芯片采购商">苹果超过惠普成第一大芯片采购商</a></td>
							<td style="text-align:right">2011-06-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f013071dbe955304c.jsp" class="cmsHref_self" title="电动车遭禁快递协会急沟通 企业开始采购自行车">电动车遭禁快递协会急沟通 企业开始采购自行车</a></td>
							<td style="text-align:right">2011-06-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f01306cc5c18b2323.jsp" class="cmsHref_self" title="国内风电或将失采购补贴 产业所受影响不大">国内风电或将失采购补贴 产业所受影响不大</a></td>
							<td style="text-align:right">2011-06-08</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_9.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_11.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_18.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：10/18 每页20条 </span>
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