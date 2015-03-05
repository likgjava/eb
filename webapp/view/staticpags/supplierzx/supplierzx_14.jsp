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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312b3f565623f8.jsp" class="cmsHref_self" title="美的中央空调成政府机构最大采购供应商">美的中央空调成政府机构最大采购供应商</a></td>
							<td style="text-align:right">2011-07-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081311835920131264e9fa319c9.jsp" class="cmsHref_self" title="中国彩电市场10年消失20品牌 集中度提高">中国彩电市场10年消失20品牌 集中度提高</a></td>
							<td style="text-align:right">2011-07-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312644f3a0195a.jsp" class="cmsHref_self" title="LG发布D2000裸眼LCD显示器 带眼球跟踪功能">LG发布D2000裸眼LCD显示器 带眼球跟踪功能</a></td>
							<td style="text-align:right">2011-07-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081311835920131263643b2192e.jsp" class="cmsHref_self" title="诺基亚调整策略以求柳暗花明">诺基亚调整策略以求柳暗花明</a></td>
							<td style="text-align:right">2011-07-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312631c4481925.jsp" class="cmsHref_self" title="苹果撤诉 与三星竞合关系扑朔迷离">苹果撤诉 与三星竞合关系扑朔迷离</a></td>
							<td style="text-align:right">2011-07-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312623759118fd.jsp" class="cmsHref_self" title="鲁花哭诉成本倒挂 花生油涨价掀起万层浪">鲁花哭诉成本倒挂 花生油涨价掀起万层浪</a></td>
							<td style="text-align:right">2011-07-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312622aea718fb.jsp" class="cmsHref_self" title="沪深部分奥的斯电梯恢复使用">沪深部分奥的斯电梯恢复使用</a></td>
							<td style="text-align:right">2011-07-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312621ce5618f9.jsp" class="cmsHref_self" title="雅戈尔纺织城就绿和水污染报告发布公开声明">雅戈尔纺织城就绿和水污染报告发布公开声明</a></td>
							<td style="text-align:right">2011-07-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312620cd5418f7.jsp" class="cmsHref_self" title="达芬奇北京开发布会 CEO松口称并非完全原装">达芬奇北京开发布会 CEO松口称并非完全原装</a></td>
							<td style="text-align:right">2011-07-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081311835920131261eeb9f18f5.jsp" class="cmsHref_self" title="李宁CEO张志勇：李宁走过最低谷">李宁CEO张志勇：李宁走过最低谷</a></td>
							<td style="text-align:right">2011-07-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081311835920131261114971714.jsp" class="cmsHref_self" title="阿迪达斯供应商被指排放有毒废水">阿迪达斯供应商被指排放有毒废水</a></td>
							<td style="text-align:right">2011-07-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081311835920131261055591712.jsp" class="cmsHref_self" title="远程会议供应商全时推出云会议视频系统">远程会议供应商全时推出云会议视频系统</a></td>
							<td style="text-align:right">2011-07-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131183592013121253f970ed0.jsp" class="cmsHref_self" title="食用油巨头暂未涨价 开工率下降至四成">食用油巨头暂未涨价 开工率下降至四成</a></td>
							<td style="text-align:right">2011-07-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081311835920131211aceab0ebe.jsp" class="cmsHref_self" title="比亚迪上半年净利降85%-95%">比亚迪上半年净利降85%-95%</a></td>
							<td style="text-align:right">2011-07-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081311835920131211a29510ebc.jsp" class="cmsHref_self" title="辉瑞意欲分拆瘦身 惠氏奶粉将再易主">辉瑞意欲分拆瘦身 惠氏奶粉将再易主</a></td>
							<td style="text-align:right">2011-07-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312118c9f10eba.jsp" class="cmsHref_self" title="辉瑞或出售惠氏奶粉 雀巢达能等巨头有意接盘">辉瑞或出售惠氏奶粉 雀巢达能等巨头有意接盘</a></td>
							<td style="text-align:right">2011-07-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081311835920131211809a70eb8.jsp" class="cmsHref_self" title="奥的斯回应电扶梯事故 称将展开全面调查">奥的斯回应电扶梯事故 称将展开全面调查</a></td>
							<td style="text-align:right">2011-07-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081311835920131211575690eb0.jsp" class="cmsHref_self" title="涪陵区委书记：天价榨菜不违法 存在过度包装">涪陵区委书记：天价榨菜不违法 存在过度包装</a></td>
							<td style="text-align:right">2011-07-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131183592013121061ed00e92.jsp" class="cmsHref_self" title="有色金属：锡供应趋紧 锡价维持高位">有色金属：锡供应趋紧 锡价维持高位</a></td>
							<td style="text-align:right">2011-07-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131183592013121046b8a0e8c.jsp" class="cmsHref_self" title="零部件供应商麦格纳获的22.5亿美元贷款">零部件供应商麦格纳获的22.5亿美元贷款</a></td>
							<td style="text-align:right">2011-07-13</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_13.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_15.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：14/29 每页20条 </span>
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