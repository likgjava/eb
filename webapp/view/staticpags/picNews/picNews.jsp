<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>图片新闻- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/picNews/picNews.jsp" title="图片新闻" class="cmsHref_self">图片新闻</a>
		
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
				<input type="hidden"  id="channelId" value="402883d429a556d10129a5893623083a" />
				<table class="frontTableList" id="qualityTable">
					<thead>
						<tr>
							<th class="left">标题</th>
							<th class="center">时间</th>
						</tr>
					</thead>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/picNews/402887e02a8d1d28012a8e9190c00081.jsp" class="cmsHref_self" title="组图：河南航空公司高管对空难事件哭泣道歉">组图：河南航空公司高管对空难事件哭泣道歉</a></td>
							<td style="text-align:right">2010-08-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/picNews/402887e02a8ebf53012a8faf35c0003e.jsp" class="cmsHref_self" title="改委称房价仍然过高 开发商担心第二轮调控">改委称房价仍然过高 开发商担心第二轮调控</a></td>
							<td style="text-align:right">2010-08-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/picNews/402885ef2aabf66e012aac2abc6d007e.jsp" class="cmsHref_self" title="公共服务平台上线通知">公共服务平台上线通知</a></td>
							<td style="text-align:right">2010-08-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/picNews/402885ef2aabf66e012aac2b3c6b0080.jsp" class="cmsHref_self" title="专家解读北京人口承载力：2500万没有问题">专家解读北京人口承载力：2500万没有问题</a></td>
							<td style="text-align:right">2010-08-26</td>
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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=picNews&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
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