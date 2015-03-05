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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f4e78d5012f56c4b021146e.jsp" class="cmsHref_self" title="山东607个产品中标基本药物集中采购 6月完成">山东607个产品中标基本药物集中采购 6月完成</a></td>
							<td style="text-align:right">2011-04-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f4e78d5012f56c3224f145b.jsp" class="cmsHref_self" title="申沃接到北美电动客车采购大订单">申沃接到北美电动客车采购大订单</a></td>
							<td style="text-align:right">2011-04-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f4e78d5012f51a985260102.jsp" class="cmsHref_self" title="运营商大规模采购平板电脑">运营商大规模采购平板电脑</a></td>
							<td style="text-align:right">2011-04-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f4e78d5012f51a537330100.jsp" class="cmsHref_self" title="中国工业转包展等三展聚首　全球逾400名展商齐聚 采购金额过亿美元">中国工业转包展等三展聚首　全球逾400名展商齐聚 采购金额过亿美元</a></td>
							<td style="text-align:right">2011-04-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f4c7799012f4cf0dcb401dc.jsp" class="cmsHref_self" title="老区改造开始采购主材">老区改造开始采购主材</a></td>
							<td style="text-align:right">2011-04-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f4c7799012f4cde77ef0193.jsp" class="cmsHref_self" title="中联通今年采购终端将超2500万部">中联通今年采购终端将超2500万部</a></td>
							<td style="text-align:right">2011-04-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f4c7799012f4cce406a0163.jsp" class="cmsHref_self" title="电子书采购喊停 电信三雄改买平板电脑">电子书采购喊停 电信三雄改买平板电脑</a></td>
							<td style="text-align:right">2011-04-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f4c7799012f4cb4e99e0137.jsp" class="cmsHref_self" title="震后日本企业赴中国采购 寻求供应链多元化">震后日本企业赴中国采购 寻求供应链多元化</a></td>
							<td style="text-align:right">2011-04-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f4c7799012f4ca77f32010d.jsp" class="cmsHref_self" title="上药今年采购额将破200亿">上药今年采购额将破200亿</a></td>
							<td style="text-align:right">2011-04-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f4c7799012f4ca520ee010a.jsp" class="cmsHref_self" title="陆益民称联通今年将采购100万部平板电脑">陆益民称联通今年将采购100万部平板电脑</a></td>
							<td style="text-align:right">2011-04-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f4c7799012f4c9e33ec00fc.jsp" class="cmsHref_self" title="年内将开始配件采购 iPad 3将支持4G网络">年内将开始配件采购 iPad 3将支持4G网络</a></td>
							<td style="text-align:right">2011-04-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f4c7799012f4c91286d00c7.jsp" class="cmsHref_self" title="日本东部再生铝合金生产商减少废铝采购">日本东部再生铝合金生产商减少废铝采购</a></td>
							<td style="text-align:right">2011-04-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f04f7fd012f1a88451b1cc2.jsp" class="cmsHref_self" title="硫酸工业呼吁应理性采购硫磺">硫酸工业呼吁应理性采购硫磺</a></td>
							<td style="text-align:right">2011-04-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f04f7fd012f1a4277241b6f.jsp" class="cmsHref_self" title="2015年美国政府将仅采购新能源汽车">2015年美国政府将仅采购新能源汽车</a></td>
							<td style="text-align:right">2011-04-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f04f7fd012f1a3fc7e41b6d.jsp" class="cmsHref_self" title="绍兴两企业正常价格采购假酒被告上法庭">绍兴两企业正常价格采购假酒被告上法庭</a></td>
							<td style="text-align:right">2011-04-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812eec2137012effa73810199a.jsp" class="cmsHref_self" title="中国冷库未来发展方向瞄准大型综合冷库">中国冷库未来发展方向瞄准大型综合冷库</a></td>
							<td style="text-align:right">2011-03-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812eec2137012eff8d689b198b.jsp" class="cmsHref_self" title="新疆基建力度加大 建材市场需求旺盛">新疆基建力度加大 建材市场需求旺盛</a></td>
							<td style="text-align:right">2011-03-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812eec2137012eff4946631969.jsp" class="cmsHref_self" title="LED灯具市场商机浮现 散热材料是关键">LED灯具市场商机浮现 散热材料是关键</a></td>
							<td style="text-align:right">2011-03-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812eec2137012eff3f87a31963.jsp" class="cmsHref_self" title="中国LED路灯发展将围绕“以人为本”">中国LED路灯发展将围绕“以人为本”</a></td>
							<td style="text-align:right">2011-03-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812eec2137012eff2b2185189a.jsp" class="cmsHref_self" title="苏州高新区投资１２０亿开展２０个重点项目">苏州高新区投资１２０亿开展２０个重点项目</a></td>
							<td style="text-align:right">2011-03-29</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_16.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_18.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_18.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：17/18 每页20条 </span>
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