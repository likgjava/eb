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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813043aa55013043b0d5a50019.jsp" class="cmsHref_self" title="PVC产能今年料增逾150万吨">PVC产能今年料增逾150万吨</a></td>
							<td style="text-align:right">2011-05-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813043aa55013043af55130015.jsp" class="cmsHref_self" title="产粮大县监利粮食减产几成定局">产粮大县监利粮食减产几成定局</a></td>
							<td style="text-align:right">2011-05-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813043aa55013043ad1013000b.jsp" class="cmsHref_self" title="我国铜库存降低">我国铜库存降低</a></td>
							<td style="text-align:right">2011-05-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081303fbeb70130439f85a403c2.jsp" class="cmsHref_self" title="力拓首次从中国购矿用自卸车">力拓首次从中国购矿用自卸车</a></td>
							<td style="text-align:right">2011-05-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081303fbeb701304394a015038b.jsp" class="cmsHref_self" title="铅需求推动价格 美联储紧缩是潜在利空？">铅需求推动价格 美联储紧缩是潜在利空？</a></td>
							<td style="text-align:right">2011-05-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081303fbeb70130439331800389.jsp" class="cmsHref_self" title="欧盟官员拟赴朝评估粮食需求">欧盟官员拟赴朝评估粮食需求</a></td>
							<td style="text-align:right">2011-05-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081303fbeb70130439082b80387.jsp" class="cmsHref_self" title="亚洲现货橡胶价格走高 因需求强劲">亚洲现货橡胶价格走高 因需求强劲</a></td>
							<td style="text-align:right">2011-05-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081303fbeb70130437e52b40373.jsp" class="cmsHref_self" title="中国的钢铁需求至2015年将最多再增长25%">中国的钢铁需求至2015年将最多再增长25%</a></td>
							<td style="text-align:right">2011-05-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130361e0401303e6db77f00c4.jsp" class="cmsHref_self" title="朱立毅：煤炭供应不能迎合需求的无序增长">朱立毅：煤炭供应不能迎合需求的无序增长</a></td>
							<td style="text-align:right">2011-05-30</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130361e0401303e6cc3a2002f.jsp" class="cmsHref_self" title="需求持续旺盛 我国今年将进口黄金超300吨">需求持续旺盛 我国今年将进口黄金超300吨</a></td>
							<td style="text-align:right">2011-05-30</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130361e0401303e6afe25002b.jsp" class="cmsHref_self" title="全球2011-12年铜需求增速或放缓至8.4%">全球2011-12年铜需求增速或放缓至8.4%</a></td>
							<td style="text-align:right">2011-05-30</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130361e0401303e6a33940029.jsp" class="cmsHref_self" title="养殖市场饲料需求增加 水产饲料企业将有望受益">养殖市场饲料需求增加 水产饲料企业将有望受益</a></td>
							<td style="text-align:right">2011-05-30</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130361e0401303e61423b001d.jsp" class="cmsHref_self" title="欧美粮食供应紧张 俄7月起取消出口禁令">欧美粮食供应紧张 俄7月起取消出口禁令</a></td>
							<td style="text-align:right">2011-05-30</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130361e0401303e5d93cc0015.jsp" class="cmsHref_self" title="中国1-4月份炼油行业运行平稳，市场供应趋紧">中国1-4月份炼油行业运行平稳，市场供应趋紧</a></td>
							<td style="text-align:right">2011-05-30</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130361e0401303e5b3da90013.jsp" class="cmsHref_self" title="担忧供应前景 原油连续三个交易日走高">担忧供应前景 原油连续三个交易日走高</a></td>
							<td style="text-align:right">2011-05-30</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130361e0401303e5a84460011.jsp" class="cmsHref_self" title="朱立毅：煤炭供应不能迎合需求的无序增长">朱立毅：煤炭供应不能迎合需求的无序增长</a></td>
							<td style="text-align:right">2011-05-30</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130361e0401303e52105e000d.jsp" class="cmsHref_self" title="中国石油伊拉克公司首船原油发运">中国石油伊拉克公司首船原油发运</a></td>
							<td style="text-align:right">2011-05-30</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130361e0401303e510f32000b.jsp" class="cmsHref_self" title="吴文章：全球铁矿石供应紧张局面将持续到2012年">吴文章：全球铁矿石供应紧张局面将持续到2012年</a></td>
							<td style="text-align:right">2011-05-30</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813026ae99013029c9b9140021.jsp" class="cmsHref_self" title="2015年全国炼焦煤需求或新增1.8亿吨">2015年全国炼焦煤需求或新增1.8亿吨</a></td>
							<td style="text-align:right">2011-05-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813026ae99013029c827fe001f.jsp" class="cmsHref_self" title="政策虽未明朗 汽车消费圈刚性需求依然存在">政策虽未明朗 汽车消费圈刚性需求依然存在</a></td>
							<td style="text-align:right">2011-05-26</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_14.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_16.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_19.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：15/19 每页20条 </span>
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