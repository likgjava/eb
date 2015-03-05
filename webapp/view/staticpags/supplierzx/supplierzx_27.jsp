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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813008ca4701301f77b2b50581.jsp" class="cmsHref_self" title="啤酒巨头回归建厂扩张模式">啤酒巨头回归建厂扩张模式</a></td>
							<td style="text-align:right">2011-05-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813008ca4701301f6b33a30500.jsp" class="cmsHref_self" title="山煤集团两座整合煤矿开工奠基">山煤集团两座整合煤矿开工奠基</a></td>
							<td style="text-align:right">2011-05-24</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fe93117012ff6b2b0e902f7.jsp" class="cmsHref_self" title="车轮企业急上市 欲抢并购先机">车轮企业急上市 欲抢并购先机</a></td>
							<td style="text-align:right">2011-05-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fe93117012ff6afd55a02f5.jsp" class="cmsHref_self" title="印度石油企业大幅上调油价">印度石油企业大幅上调油价</a></td>
							<td style="text-align:right">2011-05-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fe93117012ff698acf602b5.jsp" class="cmsHref_self" title="电缆电线：特殊国情造就特殊电缆行业">电缆电线：特殊国情造就特殊电缆行业</a></td>
							<td style="text-align:right">2011-05-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fe93117012ff692649602b3.jsp" class="cmsHref_self" title="2011年 小型工程机械或成亮点">2011年 小型工程机械或成亮点</a></td>
							<td style="text-align:right">2011-05-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fe93117012ff6665aac016c.jsp" class="cmsHref_self" title="零部件供应恐中断 二三级供应商应寻其他来源">零部件供应恐中断 二三级供应商应寻其他来源</a></td>
							<td style="text-align:right">2011-05-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fe93117012ff661ee9a0149.jsp" class="cmsHref_self" title="我国准备拓展海外核电站建设市场">我国准备拓展海外核电站建设市场</a></td>
							<td style="text-align:right">2011-05-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fe93117012ff65d7092013b.jsp" class="cmsHref_self" title="供应受限 日系车型优惠取消价格迅速回升">供应受限 日系车型优惠取消价格迅速回升</a></td>
							<td style="text-align:right">2011-05-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fe93117012ff6438ce400d3.jsp" class="cmsHref_self" title="发展下游产业 中石油收购英力士资产获批">发展下游产业 中石油收购英力士资产获批</a></td>
							<td style="text-align:right">2011-05-16</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fde6366012fe1975fd30015.jsp" class="cmsHref_self" title="宝钢6月出厂价开出平盘 铁矿石进口或回升">宝钢6月出厂价开出平盘 铁矿石进口或回升</a></td>
							<td style="text-align:right">2011-05-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fde6366012fe19637080013.jsp" class="cmsHref_self" title="两巨头提高开工率减少出口 成品油出口量下跌">两巨头提高开工率减少出口 成品油出口量下跌</a></td>
							<td style="text-align:right">2011-05-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fbfbecc012fdc742eb60d7c.jsp" class="cmsHref_self" title="汽车板需求下降 宝钢6月出厂价不变">汽车板需求下降 宝钢6月出厂价不变</a></td>
							<td style="text-align:right">2011-05-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fbfbecc012fd78643310c84.jsp" class="cmsHref_self" title="用工成本增加 西欧服装采购商陷入困境">用工成本增加 西欧服装采购商陷入困境</a></td>
							<td style="text-align:right">2011-05-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fbfbecc012fd2d7b2d20821.jsp" class="cmsHref_self" title="驰宏锌锗豪掷37亿扩产能 荣盛发展融资圈地忙">驰宏锌锗豪掷37亿扩产能 荣盛发展融资圈地忙</a></td>
							<td style="text-align:right">2011-05-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fbfbecc012fd2d5e3f7081f.jsp" class="cmsHref_self" title="方正电机上海佳豪优化结构 南玻打通任督二脉">方正电机上海佳豪优化结构 南玻打通任督二脉</a></td>
							<td style="text-align:right">2011-05-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fbfbecc012fd2d3cc450813.jsp" class="cmsHref_self" title="安泰技权激励行权在即有冲劲 非晶带材全球第二">安泰技权激励行权在即有冲劲 非晶带材全球第二</a></td>
							<td style="text-align:right">2011-05-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fbfbecc012fd2d1ff8c080f.jsp" class="cmsHref_self" title="同方股份12大产业交叉 金风科技订单充足看未来">同方股份12大产业交叉 金风科技订单充足看未来</a></td>
							<td style="text-align:right">2011-05-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fbfbecc012fd2d051eb080b.jsp" class="cmsHref_self" title="战略调整成就国电南自 置信电气拿下两网订单">战略调整成就国电南自 置信电气拿下两网订单</a></td>
							<td style="text-align:right">2011-05-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fbfbecc012fd2c6e84207f5.jsp" class="cmsHref_self" title="宝钢像航母分享行业景气 方大特钢与汽车紧密捆绑">宝钢像航母分享行业景气 方大特钢与汽车紧密捆绑</a></td>
							<td style="text-align:right">2011-05-09</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_26.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_28.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：27/29 每页20条 </span>
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