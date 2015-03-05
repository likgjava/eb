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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fba4e32012fbe19efb70212.jsp" class="cmsHref_self" title="时下钢贸商关注一个热点 钢铁业营销模式转型">时下钢贸商关注一个热点 钢铁业营销模式转型</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fba4e32012fbe12ef86020c.jsp" class="cmsHref_self" title="澳州第四大铁矿石生产商 欲寻觅中国投资者">澳州第四大铁矿石生产商 欲寻觅中国投资者</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fba4e32012fbe106a92020a.jsp" class="cmsHref_self" title="5月4日国内炉料市场小幅上探">5月4日国内炉料市场小幅上探</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fba4e32012fbe0e46b10208.jsp" class="cmsHref_self" title="澳大利亚3月零售销售意外月降0.5%">澳大利亚3月零售销售意外月降0.5%</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fba4e32012fbdfb484101f2.jsp" class="cmsHref_self" title="韩45种乳品抽检全部含甲醛 问题奶粉已入中国市场">韩45种乳品抽检全部含甲醛 问题奶粉已入中国市场</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fba4e32012fbdf3879501e8.jsp" class="cmsHref_self" title="泰国大米供应充足 国际粮价走势或下调">泰国大米供应充足 国际粮价走势或下调</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fba4e32012fbdf0b92301e1.jsp" class="cmsHref_self" title="五一期间 家具建材品牌冷热不均">五一期间 家具建材品牌冷热不均</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fb4297b012fb8d378c80172.jsp" class="cmsHref_self" title="原材料涨价10% 衣柜企业受“蝴蝶效用”">原材料涨价10% 衣柜企业受“蝴蝶效用”</a></td>
							<td style="text-align:right">2011-05-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fb4297b012fb8d1a1f30170.jsp" class="cmsHref_self" title="湖南岳阳全力建设高速公路 三年将投73亿">湖南岳阳全力建设高速公路 三年将投73亿</a></td>
							<td style="text-align:right">2011-05-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fb4297b012fb8d11118016e.jsp" class="cmsHref_self" title="海淀区北部15个重大产业项目2011年将开建">海淀区北部15个重大产业项目2011年将开建</a></td>
							<td style="text-align:right">2011-05-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f9cf3c5012fb38aacb4051c.jsp" class="cmsHref_self" title="徐工起重机助力内蒙古重大风电项目">徐工起重机助力内蒙古重大风电项目</a></td>
							<td style="text-align:right">2011-05-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f9cf3c5012fb3890ce9051a.jsp" class="cmsHref_self" title="山东政府采购一季度增62.7%">山东政府采购一季度增62.7%</a></td>
							<td style="text-align:right">2011-05-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f9cf3c5012fb387203c0516.jsp" class="cmsHref_self" title="轴承需求量年增13.8% 2011年将达1250亿">轴承需求量年增13.8% 2011年将达1250亿</a></td>
							<td style="text-align:right">2011-05-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f9cf3c5012fb3866f8d0514.jsp" class="cmsHref_self" title="浙江宁波常洪隧道大修5月开工 年内竣工">浙江宁波常洪隧道大修5月开工 年内竣工</a></td>
							<td style="text-align:right">2011-05-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f9cf3c5012fb385d73c0512.jsp" class="cmsHref_self" title="厦沙高速公路三明段开工 预投资71.57亿元">厦沙高速公路三明段开工 预投资71.57亿元</a></td>
							<td style="text-align:right">2011-05-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f96faee012f99da6fc10306.jsp" class="cmsHref_self" title="日本灾后重建产生巨大物资进口需求">日本灾后重建产生巨大物资进口需求</a></td>
							<td style="text-align:right">2011-04-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f7c8d65012f8fe8590808dd.jsp" class="cmsHref_self" title="三一重工斥资2亿美元在印度尼西亚建厂">三一重工斥资2亿美元在印度尼西亚建厂</a></td>
							<td style="text-align:right">2011-04-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f7c8d65012f8fe721de08db.jsp" class="cmsHref_self" title="2011年余姚投资22亿完成16个重点交通项目">2011年余姚投资22亿完成16个重点交通项目</a></td>
							<td style="text-align:right">2011-04-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f7c8d65012f8fe5390508b4.jsp" class="cmsHref_self" title="振华重工中标全球最大工程船起重臂项目">振华重工中标全球最大工程船起重臂项目</a></td>
							<td style="text-align:right">2011-04-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f7c8d65012f8fde0f8c088b.jsp" class="cmsHref_self" title="加快路网建设 甘肃十二五将开建17条高速">加快路网建设 甘肃十二五将开建17条高速</a></td>
							<td style="text-align:right">2011-04-26</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_14.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_16.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_18.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：15/18 每页20条 </span>
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