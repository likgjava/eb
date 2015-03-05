<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>供应商资讯- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/supplierzx.jsp" title="供应商资讯" class="cmsHref_self">供应商资讯</a>
		
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
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<table class="frontTableList" id="qualityTable">
					<thead>
						<tr>
							<th class="left">标题</th>
							<th class="center">时间</th>
						</tr>
					</thead>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131183592013120fcd51e0e6c.jsp" class="cmsHref_self" title="和利时斥资7670万美元收购新加波电气化供应商">和利时斥资7670万美元收购新加波电气化供应商</a></td>
							<td style="text-align:right">2011-07-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201311c03411a04c3.jsp" class="cmsHref_self" title="受制商标所有权 广药曲线突围拓展欧美凉茶市场">受制商标所有权 广药曲线突围拓展欧美凉茶市场</a></td>
							<td style="text-align:right">2011-07-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201311c01e58f04c1.jsp" class="cmsHref_self" title="惠氏奶粉或再被倒卖 业界盛传雀巢等或接盘">惠氏奶粉或再被倒卖 业界盛传雀巢等或接盘</a></td>
							<td style="text-align:right">2011-07-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201311c012c7b04bf.jsp" class="cmsHref_self" title="华菱钢铁中报再亏2亿多 机构认为有望走出低谷">华菱钢铁中报再亏2亿多 机构认为有望走出低谷</a></td>
							<td style="text-align:right">2011-07-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201311be8e24203a3.jsp" class="cmsHref_self" title="纺织进口税7月1日起下调 尚未影响生产企业">纺织进口税7月1日起下调 尚未影响生产企业</a></td>
							<td style="text-align:right">2011-07-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201311be5e982039d.jsp" class="cmsHref_self" title="紫金矿业溃坝案昨开庭 涉赔金额逾3亿元">紫金矿业溃坝案昨开庭 涉赔金额逾3亿元</a></td>
							<td style="text-align:right">2011-07-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201311be4fcd9039b.jsp" class="cmsHref_self" title="飞利浦宣布正式牵手奔腾">飞利浦宣布正式牵手奔腾</a></td>
							<td style="text-align:right">2011-07-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201311be439c20399.jsp" class="cmsHref_self" title="达芬奇踢爆高端家具潜规则 贴外国牌子价格翻数倍">达芬奇踢爆高端家具潜规则 贴外国牌子价格翻数倍</a></td>
							<td style="text-align:right">2011-07-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201311be3474e0397.jsp" class="cmsHref_self" title="鲁花声明从未通知涨价 称捆绑销售被误读">鲁花声明从未通知涨价 称捆绑销售被误读</a></td>
							<td style="text-align:right">2011-07-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201311be25e670391.jsp" class="cmsHref_self" title="内地体育用品或将大洗牌 李宁一哥地位难保">内地体育用品或将大洗牌 李宁一哥地位难保</a></td>
							<td style="text-align:right">2011-07-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201311be1ae8b038f.jsp" class="cmsHref_self" title="雀巢拟购徐福记60%股份 徐福记称品牌不会消失">雀巢拟购徐福记60%股份 徐福记称品牌不会消失</a></td>
							<td style="text-align:right">2011-07-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a7013116cbbca510d7.jsp" class="cmsHref_self" title="李宁十年高增长戛止 库存过高成死穴">李宁十年高增长戛止 库存过高成死穴</a></td>
							<td style="text-align:right">2011-07-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a7013116c2f0f910c9.jsp" class="cmsHref_self" title="中石化支持把宁夏石化建成最大规模化肥企业">中石化支持把宁夏石化建成最大规模化肥企业</a></td>
							<td style="text-align:right">2011-07-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a7013116bda76e10c3.jsp" class="cmsHref_self" title="小家电王苏泊尔套现之后　大举杀入房地产">小家电王苏泊尔套现之后　大举杀入房地产</a></td>
							<td style="text-align:right">2011-07-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a7013116b63e2a10b5.jsp" class="cmsHref_self" title="光明收回总裁奶业标准最差论 抨击者称备受公关压力">光明收回总裁奶业标准最差论 抨击者称备受公关压力</a></td>
							<td style="text-align:right">2011-07-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a7013116b382e110b3.jsp" class="cmsHref_self" title="瑞麒经销商频退网 奇瑞难撑中高端品牌">瑞麒经销商频退网 奇瑞难撑中高端品牌</a></td>
							<td style="text-align:right">2011-07-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a7013116b2a1c210b1.jsp" class="cmsHref_self" title="上海地铁停用22台奥的斯问题型号扶梯">上海地铁停用22台奥的斯问题型号扶梯</a></td>
							<td style="text-align:right">2011-07-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a7013116aefb8f10ab.jsp" class="cmsHref_self" title="7月份建筑钢材市场价格或将震荡下行">7月份建筑钢材市场价格或将震荡下行</a></td>
							<td style="text-align:right">2011-07-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a7013116ae56ad10a9.jsp" class="cmsHref_self" title="煤机装备业好年头 外企成最大受益者">煤机装备业好年头 外企成最大受益者</a></td>
							<td style="text-align:right">2011-07-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a7013116ad340010a5.jsp" class="cmsHref_self" title="汽车龙头仍存机会">汽车龙头仍存机会</a></td>
							<td style="text-align:right">2011-07-11</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_14.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_16.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：15/29 每页20条 </span>
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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=supplierzx&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
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