<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>下载中心- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/down/down.jsp" title="下载中心" class="cmsHref_self">下载中心</a>
		
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
				<input type="hidden"  id="channelId" value="ff808081299de09b01299de590050012" />
				<table class="frontTableList" id="qualityTable">
					<thead>
						<tr>
							<th class="left">标题</th>
							<th class="center">时间</th>
						</tr>
					</thead>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/down/402885ef2a5ed7b7012a5f2b1ed700a9.jsp" class="cmsHref_self" title="2010年1期协议供货中标供应商">2010年1期协议供货中标供应商</a></td>
							<td style="text-align:right">2010-08-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/down/402885ef2a5ed7b7012a5f29f72000a7.jsp" class="cmsHref_self" title="省直行政事业单位定价采购供应商及价格表">省直行政事业单位定价采购供应商及价格表</a></td>
							<td style="text-align:right">2010-08-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/down/402885ef2a5ed7b7012a5f28ca4900a5.jsp" class="cmsHref_self" title="国家电子政务外网数字证书中心机构数字证书登记表">国家电子政务外网数字证书中心机构数字证书登记表</a></td>
							<td style="text-align:right">2010-08-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/down/402885ef2a5ed7b7012a5f26aefa00a2.jsp" class="cmsHref_self" title="2010年1月至2010年3月协议供货数据">2010年1月至2010年3月协议供货数据</a></td>
							<td style="text-align:right">2010-08-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/down/402885ef2a5ed7b7012a5f25bf0c00a0.jsp" class="cmsHref_self" title="供应商投标通知函">供应商投标通知函</a></td>
							<td style="text-align:right">2010-08-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/down/402885ef2a5ed7b7012a5f23a479008e.jsp" class="cmsHref_self" title="预算单位培训收集表">预算单位培训收集表</a></td>
							<td style="text-align:right">2010-08-11</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
<span class="totalNum">页次：1/1 每页20条 </span>
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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=down&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
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