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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fdc7e3e370d8c.jsp" class="cmsHref_self" title="需求扩大 中国集装箱涂料市场发展乐观">需求扩大 中国集装箱涂料市场发展乐观</a></td>
							<td style="text-align:right">2011-05-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fdc7d2a320d8a.jsp" class="cmsHref_self" title="需求迫切 中国科技五金城发电机组销量火爆">需求迫切 中国科技五金城发电机组销量火爆</a></td>
							<td style="text-align:right">2011-05-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fdc7c4b610d88.jsp" class="cmsHref_self" title="全球生物燃料和化学品需求将快速增长">全球生物燃料和化学品需求将快速增长</a></td>
							<td style="text-align:right">2011-05-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fdc7b980a0d86.jsp" class="cmsHref_self" title="5月份全球棕榈油需求料持续上升">5月份全球棕榈油需求料持续上升</a></td>
							<td style="text-align:right">2011-05-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fd7c535060cfc.jsp" class="cmsHref_self" title="山西省新增产能约1亿吨保障煤炭供应">山西省新增产能约1亿吨保障煤炭供应</a></td>
							<td style="text-align:right">2011-05-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fd7b232180cfa.jsp" class="cmsHref_self" title="大豆市场价格仍存走高基础 长期发展趋势仍是上涨">大豆市场价格仍存走高基础 长期发展趋势仍是上涨</a></td>
							<td style="text-align:right">2011-05-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fd7addab80ceb.jsp" class="cmsHref_self" title="日本半导体元件供应情况大致为何？">日本半导体元件供应情况大致为何？</a></td>
							<td style="text-align:right">2011-05-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fd7a8d2e50ce7.jsp" class="cmsHref_self" title="需求逐渐回落 汽车行业高库存警报拉响">需求逐渐回落 汽车行业高库存警报拉响</a></td>
							<td style="text-align:right">2011-05-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fd79fd9510cdb.jsp" class="cmsHref_self" title="全球海贸活动蓬勃 集箱需求依然殷切">全球海贸活动蓬勃 集箱需求依然殷切</a></td>
							<td style="text-align:right">2011-05-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fd79e54070cd8.jsp" class="cmsHref_self" title="台湾电子产品与机械出口创历年单月新高">台湾电子产品与机械出口创历年单月新高</a></td>
							<td style="text-align:right">2011-05-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fd79c0c2b0ccf.jsp" class="cmsHref_self" title="我国医疗卫生领域对纺织材料需求潜力巨大">我国医疗卫生领域对纺织材料需求潜力巨大</a></td>
							<td style="text-align:right">2011-05-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fd79936490ca5.jsp" class="cmsHref_self" title="欧盟市场植物提取物需求引人瞩目">欧盟市场植物提取物需求引人瞩目</a></td>
							<td style="text-align:right">2011-05-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fd797611e0ca3.jsp" class="cmsHref_self" title="日本灾后重建带来行业出口猛增机会">日本灾后重建带来行业出口猛增机会</a></td>
							<td style="text-align:right">2011-05-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fd7820de40c80.jsp" class="cmsHref_self" title="纺织品服装累计出口498.66亿美元 同比增二成四">纺织品服装累计出口498.66亿美元 同比增二成四</a></td>
							<td style="text-align:right">2011-05-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fd2c279e007eb.jsp" class="cmsHref_self" title="北交大卢苇:我国软件服务业产值将跃居世界之首">北交大卢苇:我国软件服务业产值将跃居世界之首</a></td>
							<td style="text-align:right">2011-05-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fd2be06d207e5.jsp" class="cmsHref_self" title="聚碳酸酯需求全球性暴增 内地PC市场呈现良好开端">聚碳酸酯需求全球性暴增 内地PC市场呈现良好开端</a></td>
							<td style="text-align:right">2011-05-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fd2bb32fc07db.jsp" class="cmsHref_self" title="软体家具市场渐成熟 提高产品质量成为大势所趋">软体家具市场渐成熟 提高产品质量成为大势所趋</a></td>
							<td style="text-align:right">2011-05-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fd2b8e8fc07d9.jsp" class="cmsHref_self" title="包钢稀土眼光望向全国 江西铜业全国资源第一">包钢稀土眼光望向全国 江西铜业全国资源第一</a></td>
							<td style="text-align:right">2011-05-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fd2b7568107d7.jsp" class="cmsHref_self" title="研究报告：化肥需求将以年均3.8%速度增长">研究报告：化肥需求将以年均3.8%速度增长</a></td>
							<td style="text-align:right">2011-05-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fd2b575bb07d5.jsp" class="cmsHref_self" title="白银价格於六个月内升近一倍 长远需求仍存">白银价格於六个月内升近一倍 长远需求仍存</a></td>
							<td style="text-align:right">2011-05-09</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_16.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_18.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_19.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：17/19 每页20条 </span>
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