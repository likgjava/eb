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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131d7d3750131da6eba41001b.jsp" class="cmsHref_self" title="铝价暴涨暴落的背后 供应偏紧仍将存在">铝价暴涨暴落的背后 供应偏紧仍将存在</a></td>
							<td style="text-align:right">2011-08-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131d7d3750131da6cacaf0019.jsp" class="cmsHref_self" title="苹果或将投资夏普来保iPads等显示屏供应">苹果或将投资夏普来保iPads等显示屏供应</a></td>
							<td style="text-align:right">2011-08-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131d7d3750131da676df90017.jsp" class="cmsHref_self" title="陶瓷卫浴行业跨产品资源整合或将加剧">陶瓷卫浴行业跨产品资源整合或将加剧</a></td>
							<td style="text-align:right">2011-08-18</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131d1eaa60131d5514f430060.jsp" class="cmsHref_self" title="2011上半年国内皮革业总产量同比增长26.3%">2011上半年国内皮革业总产量同比增长26.3%</a></td>
							<td style="text-align:right">2011-08-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131d1eaa60131d550cd9b005f.jsp" class="cmsHref_self" title="通胀下 玻璃瓶涨价有原因">通胀下 玻璃瓶涨价有原因</a></td>
							<td style="text-align:right">2011-08-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131d1eaa60131d548fc660056.jsp" class="cmsHref_self" title="PC仍为DRAM最大需求来源　但成长动能趋缓">PC仍为DRAM最大需求来源　但成长动能趋缓</a></td>
							<td style="text-align:right">2011-08-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131ccdcaa0131d00dcf650006.jsp" class="cmsHref_self" title="安防监控网络化主流趋势需求正急剧加速">安防监控网络化主流趋势需求正急剧加速</a></td>
							<td style="text-align:right">2011-08-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131ccdcaa0131d00d19df0005.jsp" class="cmsHref_self" title="欧盟拟对华征收高关税 或狙击国内涂料出口需求">欧盟拟对华征收高关税 或狙击国内涂料出口需求</a></td>
							<td style="text-align:right">2011-08-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131ccdcaa0131d00bdcc90004.jsp" class="cmsHref_self" title="全球需求疲软 中国面板业恐陷投产即亏损困局">全球需求疲软 中国面板业恐陷投产即亏损困局</a></td>
							<td style="text-align:right">2011-08-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131ccdcaa0131d00a85b30003.jsp" class="cmsHref_self" title="光伏扩张 需求激增">光伏扩张 需求激增</a></td>
							<td style="text-align:right">2011-08-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131c418af0131cb01c1d3002b.jsp" class="cmsHref_self" title="热泵热水器发展需要“有形之手”扶持">热泵热水器发展需要“有形之手”扶持</a></td>
							<td style="text-align:right">2011-08-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131c418af0131cafed99d0029.jsp" class="cmsHref_self" title="白酒、食用油、月饼“涨迎”中秋">白酒、食用油、月饼“涨迎”中秋</a></td>
							<td style="text-align:right">2011-08-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131c418af0131caf9f7dc0022.jsp" class="cmsHref_self" title="创新还是炒作？格力空调1赫兹惹争议">创新还是炒作？格力空调1赫兹惹争议</a></td>
							<td style="text-align:right">2011-08-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131c418af0131caf2560a0021.jsp" class="cmsHref_self" title="国产自动变速器靠自主车企推动">国产自动变速器靠自主车企推动</a></td>
							<td style="text-align:right">2011-08-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131c418af0131caf1938b0020.jsp" class="cmsHref_self" title="糖价冲击8000元：多重因素叠加 炒作成背后推手">糖价冲击8000元：多重因素叠加 炒作成背后推手</a></td>
							<td style="text-align:right">2011-08-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131c418af0131caf0c736001e.jsp" class="cmsHref_self" title="亚洲丙烯市场价格开始走低">亚洲丙烯市场价格开始走低</a></td>
							<td style="text-align:right">2011-08-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813192fe7c0131b12c0ca01c8c.jsp" class="cmsHref_self" title="需求疲软面板价格再降 大陆液晶面板生不逢时">需求疲软面板价格再降 大陆液晶面板生不逢时</a></td>
							<td style="text-align:right">2011-08-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813192fe7c0131b12905ef1c89.jsp" class="cmsHref_self" title="花茶发力 川内茉莉花供应趋紧">花茶发力 川内茉莉花供应趋紧</a></td>
							<td style="text-align:right">2011-08-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813192fe7c0131ac23da7c1435.jsp" class="cmsHref_self" title="工程机械销售收入和出口或将双“倍增”">工程机械销售收入和出口或将双“倍增”</a></td>
							<td style="text-align:right">2011-08-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813192fe7c0131ac05c58e141f.jsp" class="cmsHref_self" title="如何完善优化我国纺织行业供应链">如何完善优化我国纺织行业供应链</a></td>
							<td style="text-align:right">2011-08-09</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_2.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_4.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_19.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：3/19 每页20条 </span>
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