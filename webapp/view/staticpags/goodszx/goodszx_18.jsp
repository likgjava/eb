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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fd2b33fd407d3.jsp" class="cmsHref_self" title="建筑钢价冲至年初高位 需求不稳又见回落风险">建筑钢价冲至年初高位 需求不稳又见回落风险</a></td>
							<td style="text-align:right">2011-05-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fc33a20e001e5.jsp" class="cmsHref_self" title="欧盟批准软小麦出口量达五周最高水平">欧盟批准软小麦出口量达五周最高水平</a></td>
							<td style="text-align:right">2011-05-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fc32c8f9201bd.jsp" class="cmsHref_self" title="农产品拟定增募资25亿以夯实主业">农产品拟定增募资25亿以夯实主业</a></td>
							<td style="text-align:right">2011-05-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fc328526901b3.jsp" class="cmsHref_self" title="动漫衍生产品进货慎重侵犯知识产权">动漫衍生产品进货慎重侵犯知识产权</a></td>
							<td style="text-align:right">2011-05-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fc3251cee01ae.jsp" class="cmsHref_self" title="铜价“倒挂” 铜加工企业 进口多 亏得多">铜价“倒挂” 铜加工企业 进口多 亏得多</a></td>
							<td style="text-align:right">2011-05-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fc3188c4b015b.jsp" class="cmsHref_self" title="棉价坐上“过山车” 纺织业艰难“去库存”">棉价坐上“过山车” 纺织业艰难“去库存”</a></td>
							<td style="text-align:right">2011-05-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fba4e32012fbde06382019a.jsp" class="cmsHref_self" title="两大因素决定了进口车需求量会继续增加">两大因素决定了进口车需求量会继续增加</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fba4e32012fbddd91f80196.jsp" class="cmsHref_self" title="担忧中美需求放缓 铜价大幅下挫">担忧中美需求放缓 铜价大幅下挫</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fba4e32012fbdd729b00194.jsp" class="cmsHref_self" title="动荡局势影响中东和北非现货糖需求分布不均">动荡局势影响中东和北非现货糖需求分布不均</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fba4e32012fbdc7a09e016e.jsp" class="cmsHref_self" title=""有量无质 有质无量"铝土矿供应日趋紧张">"有量无质 有质无量"铝土矿供应日趋紧张</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fba4e32012fbdc56b54016c.jsp" class="cmsHref_self" title="国际市场供应增加 农产品季节性下滑">国际市场供应增加 农产品季节性下滑</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fba4e32012fbdbf213d0168.jsp" class="cmsHref_self" title="海口食品药品监管局严禁医疗机构违法采购中药饮片">海口食品药品监管局严禁医疗机构违法采购中药饮片</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fba4e32012fbdb2826e0162.jsp" class="cmsHref_self" title="汽车 IT信息产业驱动模具业辐射效应显现">汽车 IT信息产业驱动模具业辐射效应显现</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fba4e32012fbda9ceac015c.jsp" class="cmsHref_self" title="商务部公布《汽车品牌销售管理实施办法》">商务部公布《汽车品牌销售管理实施办法》</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fb4297b012fb8e6302601b0.jsp" class="cmsHref_self" title="楼市中心城区供货降七成">楼市中心城区供货降七成</a></td>
							<td style="text-align:right">2011-05-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fb4297b012fb8e47ec701ae.jsp" class="cmsHref_self" title="日本余震供货紧张">日本余震供货紧张</a></td>
							<td style="text-align:right">2011-05-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fb4297b012fb8de508f01aa.jsp" class="cmsHref_self" title="我国农村和小城镇急需清洁便利的能源供应">我国农村和小城镇急需清洁便利的能源供应</a></td>
							<td style="text-align:right">2011-05-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fb4297b012fb8dd22d201a8.jsp" class="cmsHref_self" title="中国电子设计活跃度提升 小批量采购供应面临挑战">中国电子设计活跃度提升 小批量采购供应面临挑战</a></td>
							<td style="text-align:right">2011-05-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812f9cf3c5012fb3a393d20560.jsp" class="cmsHref_self" title="佛山车市供货紧张价格坚挺">佛山车市供货紧张价格坚挺</a></td>
							<td style="text-align:right">2011-05-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812f9cf3c5012fb3a0bb12055e.jsp" class="cmsHref_self" title="12家团购网为消费者“上保险” 供货商预交押金">12家团购网为消费者“上保险” 供货商预交押金</a></td>
							<td style="text-align:right">2011-05-03</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_17.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_19.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_19.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：18/19 每页20条 </span>
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