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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813192fe7c0131ac04b51b141d.jsp" class="cmsHref_self" title="花茶发力 四川茉莉花供应趋紧">花茶发力 四川茉莉花供应趋紧</a></td>
							<td style="text-align:right">2011-08-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813192fe7c0131a70d7e620bf6.jsp" class="cmsHref_self" title="世界皮业报告：各国生皮产量多于销售量">世界皮业报告：各国生皮产量多于销售量</a></td>
							<td style="text-align:right">2011-08-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813192fe7c0131a7076f590bf2.jsp" class="cmsHref_self" title="日本纺织进口商开拓秘鲁市场 激起巨大期望">日本纺织进口商开拓秘鲁市场 激起巨大期望</a></td>
							<td style="text-align:right">2011-08-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813192fe7c0131a6eba1050bdc.jsp" class="cmsHref_self" title="原材料涨价产品降价双重夹击 药业中考成绩下滑">原材料涨价产品降价双重夹击 药业中考成绩下滑</a></td>
							<td style="text-align:right">2011-08-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813192fe7c0131a6dfc4970bd2.jsp" class="cmsHref_self" title="欧盟对华征收反倾销税引发意大利紧固件供应短缺">欧盟对华征收反倾销税引发意大利紧固件供应短缺</a></td>
							<td style="text-align:right">2011-08-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813192fe7c0131a6daed9f0bcc.jsp" class="cmsHref_self" title="未来奢侈品有非常大的需求">未来奢侈品有非常大的需求</a></td>
							<td style="text-align:right">2011-08-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813192fe7c0131a6d93b770af0.jsp" class="cmsHref_self" title="童装市场需求持续扩容">童装市场需求持续扩容</a></td>
							<td style="text-align:right">2011-08-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813192fe7c0131a6d65acb0aef.jsp" class="cmsHref_self" title="天津进口红酒需求增大 红酒质量令人堪忧">天津进口红酒需求增大 红酒质量令人堪忧</a></td>
							<td style="text-align:right">2011-08-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813192fe7c0131976b9a87024c.jsp" class="cmsHref_self" title="发改委：目前国内油料油脂市场供应充足">发改委：目前国内油料油脂市场供应充足</a></td>
							<td style="text-align:right">2011-08-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813192fe7c01319765d0930249.jsp" class="cmsHref_self" title="豆类终端需求可能摆脱低迷">豆类终端需求可能摆脱低迷</a></td>
							<td style="text-align:right">2011-08-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813192fe7c01319764d4880248.jsp" class="cmsHref_self" title="汽车和房产需求疲软 玻璃价格普跌">汽车和房产需求疲软 玻璃价格普跌</a></td>
							<td style="text-align:right">2011-08-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813192fe7c013197640ade0247.jsp" class="cmsHref_self" title="需求和成本双轮驱动 甲醇价格较年初上涨13%">需求和成本双轮驱动 甲醇价格较年初上涨13%</a></td>
							<td style="text-align:right">2011-08-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081317673560131923d455319f7.jsp" class="cmsHref_self" title="市场需求放缓 中国机械工业亟待加快转型升级">市场需求放缓 中国机械工业亟待加快转型升级</a></td>
							<td style="text-align:right">2011-08-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813176735601318d34f5f81254.jsp" class="cmsHref_self" title="纺织服装出口产品价格增长21%">纺织服装出口产品价格增长21%</a></td>
							<td style="text-align:right">2011-08-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813176735601318d1cfe721241.jsp" class="cmsHref_self" title="业内人士称中国可能加强稀土出口管制">业内人士称中国可能加强稀土出口管制</a></td>
							<td style="text-align:right">2011-08-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813176735601318d10da6d1234.jsp" class="cmsHref_self" title="棉花价格疲软 需求羸弱或雪上加霜">棉花价格疲软 需求羸弱或雪上加霜</a></td>
							<td style="text-align:right">2011-08-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813176735601318d103da01233.jsp" class="cmsHref_self" title="水泥中长期总体需求无忧 投资机会正在显现">水泥中长期总体需求无忧 投资机会正在显现</a></td>
							<td style="text-align:right">2011-08-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081317673560131894d538010f0.jsp" class="cmsHref_self" title="中国奶制品行业 质量和规模能否并行不悖">中国奶制品行业 质量和规模能否并行不悖</a></td>
							<td style="text-align:right">2011-08-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081317673560131881b29400b23.jsp" class="cmsHref_self" title="液晶监视器品牌及系统整合商6月出货谷底攀升">液晶监视器品牌及系统整合商6月出货谷底攀升</a></td>
							<td style="text-align:right">2011-08-02</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131767356013188180bbb0b21.jsp" class="cmsHref_self" title="我国下半年铁矿石需求增长将继续放慢">我国下半年铁矿石需求增长将继续放慢</a></td>
							<td style="text-align:right">2011-08-02</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_3.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_5.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_19.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：4/19 每页20条 </span>
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