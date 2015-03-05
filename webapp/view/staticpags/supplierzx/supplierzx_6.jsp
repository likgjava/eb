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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c01319775c3a40253.jsp" class="cmsHref_self" title="太子奶重组大限将至 托管公司老总被双规">太子奶重组大限将至 托管公司老总被双规</a></td>
							<td style="text-align:right">2011-08-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131977522ef0252.jsp" class="cmsHref_self" title="金龙鱼称产品价格处亏损边缘 涨价是市场行为">金龙鱼称产品价格处亏损边缘 涨价是市场行为</a></td>
							<td style="text-align:right">2011-08-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c013197739cb50250.jsp" class="cmsHref_self" title="力拓净利同比增长30% 中国钢企困境重重">力拓净利同比增长30% 中国钢企困境重重</a></td>
							<td style="text-align:right">2011-08-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c013197704382024e.jsp" class="cmsHref_self" title="韩国车企在华扩张 现代起亚7月销8.4万辆">韩国车企在华扩张 现代起亚7月销8.4万辆</a></td>
							<td style="text-align:right">2011-08-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131976f9d64024d.jsp" class="cmsHref_self" title="发改委限价令解禁 金龙鱼提价5%多品牌有意跟进">发改委限价令解禁 金龙鱼提价5%多品牌有意跟进</a></td>
							<td style="text-align:right">2011-08-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c013197673238024a.jsp" class="cmsHref_self" title="用户需求旺盛 苹果二季度成最大智能机厂商">用户需求旺盛 苹果二季度成最大智能机厂商</a></td>
							<td style="text-align:right">2011-08-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601319265984c1bbf.jsp" class="cmsHref_self" title="日企悉数亏损 谁打垮了日本家电企业？">日企悉数亏损 谁打垮了日本家电企业？</a></td>
							<td style="text-align:right">2011-08-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601319264cb721bbe.jsp" class="cmsHref_self" title="海尔收购三洋：白电同行“鸭梨山大”">海尔收购三洋：白电同行“鸭梨山大”</a></td>
							<td style="text-align:right">2011-08-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081317673560131925618741b45.jsp" class="cmsHref_self" title="雀巢回应高钙奶细菌超标 源于零售贮存不当">雀巢回应高钙奶细菌超标 源于零售贮存不当</a></td>
							<td style="text-align:right">2011-08-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081317673560131924f8db31ae9.jsp" class="cmsHref_self" title="金龙鱼提价获发改委批准 二线品牌或率先追涨">金龙鱼提价获发改委批准 二线品牌或率先追涨</a></td>
							<td style="text-align:right">2011-08-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601319249b6d41ade.jsp" class="cmsHref_self" title="飞鹤称出售牧场并非放弃全产业链模式">飞鹤称出售牧场并非放弃全产业链模式</a></td>
							<td style="text-align:right">2011-08-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601319248df681add.jsp" class="cmsHref_self" title="文迪波被双规 太子奶重整再添变数">文迪波被双规 太子奶重整再添变数</a></td>
							<td style="text-align:right">2011-08-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081317673560131924821591adc.jsp" class="cmsHref_self" title="达芬奇再发公开信坚称产品无问题不退货">达芬奇再发公开信坚称产品无问题不退货</a></td>
							<td style="text-align:right">2011-08-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081317673560131924759fc1ada.jsp" class="cmsHref_self" title="标普调低诺基亚评级 长期企业信用降至BBB">标普调低诺基亚评级 长期企业信用降至BBB</a></td>
							<td style="text-align:right">2011-08-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081317673560131924699211ad9.jsp" class="cmsHref_self" title="日立公司拟外包电视机生产业务">日立公司拟外包电视机生产业务</a></td>
							<td style="text-align:right">2011-08-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081317673560131924619ef1ad7.jsp" class="cmsHref_self" title="英特尔2200万美元投资中国三家公司">英特尔2200万美元投资中国三家公司</a></td>
							<td style="text-align:right">2011-08-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601319245759e1ad6.jsp" class="cmsHref_self" title="苹果公司因手机跟踪定位事件在韩遭罚款">苹果公司因手机跟踪定位事件在韩遭罚款</a></td>
							<td style="text-align:right">2011-08-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601319243e5231ad5.jsp" class="cmsHref_self" title="味千"骨汤门"后续 质监将查"味千"调料供应商">味千"骨汤门"后续 质监将查"味千"调料供应商</a></td>
							<td style="text-align:right">2011-08-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601319242f44c1ad3.jsp" class="cmsHref_self" title="味千拉面、雀巢、亨氏等油类配料供应商系同一家">味千拉面、雀巢、亨氏等油类配料供应商系同一家</a></td>
							<td style="text-align:right">2011-08-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601318d415c8b125e.jsp" class="cmsHref_self" title="智能手机大佬退位 诺基亚能否重振市场">智能手机大佬退位 诺基亚能否重振市场</a></td>
							<td style="text-align:right">2011-08-03</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_5.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_7.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：6/29 每页20条 </span>
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