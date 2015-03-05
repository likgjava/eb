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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a013163e9918a02c4.jsp" class="cmsHref_self" title="市场需求量增大 3D电视销量超预期">市场需求量增大 3D电视销量超预期</a></td>
							<td style="text-align:right">2011-07-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a013163e4ad7702c0.jsp" class="cmsHref_self" title="市人大代表：食盐供应能否实行“双轨制”">市人大代表：食盐供应能否实行“双轨制”</a></td>
							<td style="text-align:right">2011-07-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a01315f1503e900fb.jsp" class="cmsHref_self" title="食糖需求方“越涨越买”究竟是谁制造了“疯狂的糖价”？">食糖需求方“越涨越买”究竟是谁制造了“疯狂的糖价”？</a></td>
							<td style="text-align:right">2011-07-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a01315f141c5900f9.jsp" class="cmsHref_self" title="西方不亮东方亮：澳洲高端葡萄酒在华需求旺盛">西方不亮东方亮：澳洲高端葡萄酒在华需求旺盛</a></td>
							<td style="text-align:right">2011-07-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a01315f127c0500f8.jsp" class="cmsHref_self" title="动车组需求面临短期风险">动车组需求面临短期风险</a></td>
							<td style="text-align:right">2011-07-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a01315f10e63500f5.jsp" class="cmsHref_self" title="中国需求下降担忧 日胶承压挫跌">中国需求下降担忧 日胶承压挫跌</a></td>
							<td style="text-align:right">2011-07-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a01315f101bce00f4.jsp" class="cmsHref_self" title="中国需求下降 外盘橡胶下跌">中国需求下降 外盘橡胶下跌</a></td>
							<td style="text-align:right">2011-07-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131518b2a01315f0e9d1400f3.jsp" class="cmsHref_self" title="黑龙江大豆压榨利润好转　供应渐增">黑龙江大豆压榨利润好转　供应渐增</a></td>
							<td style="text-align:right">2011-07-25</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813140dc4701314f5a9e9c121b.jsp" class="cmsHref_self" title="中国鞋理性重返欧盟市场 从找订单到选订单">中国鞋理性重返欧盟市场 从找订单到选订单</a></td>
							<td style="text-align:right">2011-07-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813140dc4701314f54b6a21213.jsp" class="cmsHref_self" title="需求继续好转PVC或将走强">需求继续好转PVC或将走强</a></td>
							<td style="text-align:right">2011-07-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813140dc4701314f51be8111bf.jsp" class="cmsHref_self" title="需求释放 光伏产品价格止跌回稳">需求释放 光伏产品价格止跌回稳</a></td>
							<td style="text-align:right">2011-07-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813140dc4701314f50f38711be.jsp" class="cmsHref_self" title="光伏产品需求释放价格企稳 小企业生存仍不乐观">光伏产品需求释放价格企稳 小企业生存仍不乐观</a></td>
							<td style="text-align:right">2011-07-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813140dc4701314f4e8c8211bd.jsp" class="cmsHref_self" title="多家日系车下调全年销售目标 零部件供应致欠产">多家日系车下调全年销售目标 零部件供应致欠产</a></td>
							<td style="text-align:right">2011-07-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813140dc4701314f4b3ab111bb.jsp" class="cmsHref_self" title="本榨季食糖供应恐再度预减实增">本榨季食糖供应恐再度预减实增</a></td>
							<td style="text-align:right">2011-07-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813140dc4701314f48c2e011b8.jsp" class="cmsHref_self" title="雷诺三星寻求摆脱日本零部件供应商影响">雷诺三星寻求摆脱日本零部件供应商影响</a></td>
							<td style="text-align:right">2011-07-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813140dc4701314a43086f0b0b.jsp" class="cmsHref_self" title="欧盟对华自行车反倾销税或再延三年">欧盟对华自行车反倾销税或再延三年</a></td>
							<td style="text-align:right">2011-07-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813140dc4701314a3724540b00.jsp" class="cmsHref_self" title="奇瑞汽车巴西工厂开工奠基 建成后年产能达15万辆">奇瑞汽车巴西工厂开工奠基 建成后年产能达15万辆</a></td>
							<td style="text-align:right">2011-07-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813140dc4701314a2ac0180af0.jsp" class="cmsHref_self" title="中国进口需求下降 棉价下跌趋势难改">中国进口需求下降 棉价下跌趋势难改</a></td>
							<td style="text-align:right">2011-07-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813140dc4701314a271b5d0aec.jsp" class="cmsHref_self" title="“生猪供应将持续偏紧” 爱森拟投7亿新建猪场">“生猪供应将持续偏紧” 爱森拟投7亿新建猪场</a></td>
							<td style="text-align:right">2011-07-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813140dc4701314a2598a10aeb.jsp" class="cmsHref_self" title="天胶供应紧张格局将持续到2018年">天胶供应紧张格局将持续到2018年</a></td>
							<td style="text-align:right">2011-07-21</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_5.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_7.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_19.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：6/19 每页20条 </span>
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