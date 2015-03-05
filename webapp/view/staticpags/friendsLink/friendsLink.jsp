<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>友情链接- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/friendsLink/friendsLink.jsp" title="友情链接" class="cmsHref_self">友情链接</a>
		
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
				<input type="hidden"  id="channelId" value="402887e02a6062e9012a60b35edb0031" />
				<table class="frontTableList" id="qualityTable">
					<thead>
						<tr>
							<th class="left">标题</th>
							<th class="center">时间</th>
						</tr>
					</thead>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="http://www.ahzfcg.gov.cn/" class="cmsHref_self" title="安徽政府采购网">安徽政府采购网</a></td>
							<td style="text-align:right">2010-08-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="http://www.bjztb.gov.cn/" class="cmsHref_self" title="北京招投标信息平台">北京招投标信息平台</a></td>
							<td style="text-align:right">2010-08-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="http://www.ccgp.gov.cn/new/" class="cmsHref_self" title="中国政府采购网">中国政府采购网</a></td>
							<td style="text-align:right">2010-08-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="http://www.baidu.com" class="cmsHref_self" title="百度">百度</a></td>
							<td style="text-align:right">2010-08-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="http://www.google.com" class="cmsHref_self" title="google">google</a></td>
							<td style="text-align:right">2010-08-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="http://www.gpcsoft.com" class="cmsHref_self" title="政采科技">政采科技</a></td>
							<td style="text-align:right">2010-08-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="http://www.zycg.cn/" class="cmsHref_self" title="中央政府采购网-电子化政府采购平台">中央政府采购网-电子化政府采购平台</a></td>
							<td style="text-align:right">2010-08-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="http://www.sczfcg.com/" class="cmsHref_self" title="四川政府采购网">四川政府采购网</a></td>
							<td style="text-align:right">2010-08-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="http://www.ccgp-hubei.gov.cn/" class="cmsHref_self" title="中国湖北政府采购网">中国湖北政府采购网</a></td>
							<td style="text-align:right">2010-08-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="http://www.hebgp.gov.cn/" class="cmsHref_self" title="河北省政府采购网">河北省政府采购网</a></td>
							<td style="text-align:right">2010-08-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="http://www.yngp.com/" class="cmsHref_self" title="云南省政府采购网">云南省政府采购网</a></td>
							<td style="text-align:right">2010-08-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="http://www.shzfcg.gov.cn:8090/zfcg.jsp" class="cmsHref_self" title="上海市政府采购中心">上海市政府采购中心</a></td>
							<td style="text-align:right">2010-08-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="http://www.caigou2003.com/" class="cmsHref_self" title="政府采购信息网">政府采购信息网</a></td>
							<td style="text-align:right">2010-08-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="http://www.ccgp-liaoning.gov.cn/" class="cmsHref_self" title="辽宁政府采购网">辽宁政府采购网</a></td>
							<td style="text-align:right">2010-08-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="http://www.ccgp-liaoning.gov.cn/" class="cmsHref_self" title="珠海市政府采购中心">珠海市政府采购中心</a></td>
							<td style="text-align:right">2010-08-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="http://www.chinabidding.org.cn/" class="cmsHref_self" title="中国政府招标网">中国政府招标网</a></td>
							<td style="text-align:right">2010-08-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="http://www.ccgp-shaanxi.gov.cn/" class="cmsHref_self" title="陕西政府采购">陕西政府采购</a></td>
							<td style="text-align:right">2010-08-27</td>
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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=friendsLink&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
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