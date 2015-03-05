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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130fce127ec01a0.jsp" class="cmsHref_self" title="智利供应引发担忧 伦铜周二涨至9540美元">智利供应引发担忧 伦铜周二涨至9540美元</a></td>
							<td style="text-align:right">2011-07-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130f7da80bf6e60.jsp" class="cmsHref_self" title="日本电子零组件供应链预计9月完全恢复">日本电子零组件供应链预计9月完全恢复</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130f7d9dfc26e5e.jsp" class="cmsHref_self" title="昆生活必需品供应充足 生活消费比去年高31.8%">昆生活必需品供应充足 生活消费比去年高31.8%</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130f7d7ae436e58.jsp" class="cmsHref_self" title="供应紧张 期棉价格受减税影响持续下跌">供应紧张 期棉价格受减税影响持续下跌</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130f7d50ea56e52.jsp" class="cmsHref_self" title="福建农村药品供应网络全覆盖">福建农村药品供应网络全覆盖</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130f7d3a7786e4c.jsp" class="cmsHref_self" title="增加供应 国内汽柴油批发价下调">增加供应 国内汽柴油批发价下调</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130f7d271f46e47.jsp" class="cmsHref_self" title="疫病影响生猪供应 猪肉价格暴涨谁之过">疫病影响生猪供应 猪肉价格暴涨谁之过</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130f2baecb80ab3.jsp" class="cmsHref_self" title="日本汽车业复苏利好中国铝合金锭出口">日本汽车业复苏利好中国铝合金锭出口</a></td>
							<td style="text-align:right">2011-07-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130f2b776920aa4.jsp" class="cmsHref_self" title="京沪高铁开通减少钢材运输成本">京沪高铁开通减少钢材运输成本</a></td>
							<td style="text-align:right">2011-07-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130f2b0762e0a98.jsp" class="cmsHref_self" title="家具出口货值同期骤降 须关注国外新标准">家具出口货值同期骤降 须关注国外新标准</a></td>
							<td style="text-align:right">2011-07-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130f29300910a48.jsp" class="cmsHref_self" title="东日本大地震严重冲击全球零部件供应链">东日本大地震严重冲击全球零部件供应链</a></td>
							<td style="text-align:right">2011-07-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130e3466924063b.jsp" class="cmsHref_self" title="稀土市场已失去正常资源供应量 业内预计价格继续上涨">稀土市场已失去正常资源供应量 业内预计价格继续上涨</a></td>
							<td style="text-align:right">2011-07-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130e343af700639.jsp" class="cmsHref_self" title="木器涂料市场需求乐观增长 发展仍临困境">木器涂料市场需求乐观增长 发展仍临困境</a></td>
							<td style="text-align:right">2011-07-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130e341ae6f0637.jsp" class="cmsHref_self" title="哪种价位的集成吊顶产品最受市场欢迎">哪种价位的集成吊顶产品最受市场欢迎</a></td>
							<td style="text-align:right">2011-07-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130e32e30810607.jsp" class="cmsHref_self" title="接种面积与库存均超预期 美国玉米期货暴跌12%">接种面积与库存均超预期 美国玉米期货暴跌12%</a></td>
							<td style="text-align:right">2011-07-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130e32a033605fb.jsp" class="cmsHref_self" title="珠海打造全系列船舶动力供应基地">珠海打造全系列船舶动力供应基地</a></td>
							<td style="text-align:right">2011-07-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130c14db40130ceaa05550182.jsp" class="cmsHref_self" title="中国最大液晶玻璃基板项目将在深圳投产">中国最大液晶玻璃基板项目将在深圳投产</a></td>
							<td style="text-align:right">2011-06-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130c14db40130ce8428ee003d.jsp" class="cmsHref_self" title="未来3到5年内中央空调需求增幅将超30％">未来3到5年内中央空调需求增幅将超30％</a></td>
							<td style="text-align:right">2011-06-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130c14db40130ce8351be0035.jsp" class="cmsHref_self" title="郑棉弱势震荡将延续 国内需求依旧不振">郑棉弱势震荡将延续 国内需求依旧不振</a></td>
							<td style="text-align:right">2011-06-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130c14db40130ce81b6db002b.jsp" class="cmsHref_self" title="黄大立：外国企业需了解农村医疗市场需求">黄大立：外国企业需了解农村医疗市场需求</a></td>
							<td style="text-align:right">2011-06-27</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_8.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_10.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_19.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：9/19 每页20条 </span>
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