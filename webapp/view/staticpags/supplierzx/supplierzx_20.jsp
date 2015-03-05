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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130bc5ca70130bf2539c30036.jsp" class="cmsHref_self" title="苹果中国供应商中毒员工已离职">苹果中国供应商中毒员工已离职</a></td>
							<td style="text-align:right">2011-06-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130bc5ca70130bf2402b80034.jsp" class="cmsHref_self" title="安利纽崔莱27款产品提价11% 行业高利润被诟病">安利纽崔莱27款产品提价11% 行业高利润被诟病</a></td>
							<td style="text-align:right">2011-06-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130bc5ca70130bf21b3ab0032.jsp" class="cmsHref_self" title="雅戈尔否认贱卖新马股权: 战略转向品牌运营型">雅戈尔否认贱卖新马股权: 战略转向品牌运营型</a></td>
							<td style="text-align:right">2011-06-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130bc5ca70130bf21001e0030.jsp" class="cmsHref_self" title="河北钢铁集团将参股澳企联手开发美国铁矿">河北钢铁集团将参股澳企联手开发美国铁矿</a></td>
							<td style="text-align:right">2011-06-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130b726330130ba205ef6019e.jsp" class="cmsHref_self" title="AMD公布CPU+GPU融合进程 2014年搞定">AMD公布CPU+GPU融合进程 2014年搞定</a></td>
							<td style="text-align:right">2011-06-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130b726330130ba0e5c300188.jsp" class="cmsHref_self" title="电动车龙头逆势扩张 助推中部崛起">电动车龙头逆势扩张 助推中部崛起</a></td>
							<td style="text-align:right">2011-06-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130b726330130ba0ce2560184.jsp" class="cmsHref_self" title="中石油和壳牌合资石化项目获批">中石油和壳牌合资石化项目获批</a></td>
							<td style="text-align:right">2011-06-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130b726330130ba09877a017e.jsp" class="cmsHref_self" title="报告称三星宏碁竞夺平板电脑市场第二把交椅">报告称三星宏碁竞夺平板电脑市场第二把交椅</a></td>
							<td style="text-align:right">2011-06-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130b726330130ba043f850178.jsp" class="cmsHref_self" title="联想斥资1.3亿收购武陵酒业 进入白酒领域">联想斥资1.3亿收购武陵酒业 进入白酒领域</a></td>
							<td style="text-align:right">2011-06-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130b726330130b9fd21af0162.jsp" class="cmsHref_self" title="大飞机发动机供应商敲定 CFM战胜美国普惠">大飞机发动机供应商敲定 CFM战胜美国普惠</a></td>
							<td style="text-align:right">2011-06-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130b726330130b9fa36bb0157.jsp" class="cmsHref_self" title="传苹果要求供应商降低iPad 2组件价格">传苹果要求供应商降低iPad 2组件价格</a></td>
							<td style="text-align:right">2011-06-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130b726330130b9f969350155.jsp" class="cmsHref_self" title="东芝将向福特供应混合动力车用逆变器">东芝将向福特供应混合动力车用逆变器</a></td>
							<td style="text-align:right">2011-06-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130b726330130b9f54b78014c.jsp" class="cmsHref_self" title="互助县积极推行政府采购管办分离改革">互助县积极推行政府采购管办分离改革</a></td>
							<td style="text-align:right">2011-06-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130b726330130b9f41dc60144.jsp" class="cmsHref_self" title="上海大众：在参与政府采购中实现跨越发展">上海大众：在参与政府采购中实现跨越发展</a></td>
							<td style="text-align:right">2011-06-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130b726330130b9ed9e33000b.jsp" class="cmsHref_self" title="发改委:6月CPI涨幅可能超过5月 总体态势仍可控">发改委:6月CPI涨幅可能超过5月 总体态势仍可控</a></td>
							<td style="text-align:right">2011-06-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130b726330130b9ec87d20009.jsp" class="cmsHref_self" title="红帽第一财季净利润4700万美元 同比增32%">红帽第一财季净利润4700万美元 同比增32%</a></td>
							<td style="text-align:right">2011-06-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130b726330130b9eb87b50007.jsp" class="cmsHref_self" title="澳洲最大葡萄酒集团福斯特拒绝95亿并购">澳洲最大葡萄酒集团福斯特拒绝95亿并购</a></td>
							<td style="text-align:right">2011-06-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130afdee10130b4f3acfd0534.jsp" class="cmsHref_self" title="武钢与奇瑞将建汽车用钢材合资公司">武钢与奇瑞将建汽车用钢材合资公司</a></td>
							<td style="text-align:right">2011-06-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130afdee10130b4e1ec010510.jsp" class="cmsHref_self" title="雷诺称前五月全球汽车销量同比上涨4.2%">雷诺称前五月全球汽车销量同比上涨4.2%</a></td>
							<td style="text-align:right">2011-06-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130afdee10130b4d641f404f2.jsp" class="cmsHref_self" title="惠普联想推平板电脑 夏季加入热战">惠普联想推平板电脑 夏季加入热战</a></td>
							<td style="text-align:right">2011-06-22</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_19.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_21.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：20/29 每页20条 </span>
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