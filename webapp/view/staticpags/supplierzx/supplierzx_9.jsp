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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01316e3d6b0206ee.jsp" class="cmsHref_self" title="铁道部彻查列车控制系统直指通号系">铁道部彻查列车控制系统直指通号系</a></td>
							<td style="text-align:right">2011-07-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01316e3bc11c06ed.jsp" class="cmsHref_self" title="澳优斥资千万并购荷兰百年乳企">澳优斥资千万并购荷兰百年乳企</a></td>
							<td style="text-align:right">2011-07-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01316e38375906ea.jsp" class="cmsHref_self" title="达芬奇的副作用 国产品牌纷纷撇清洋味">达芬奇的副作用 国产品牌纷纷撇清洋味</a></td>
							<td style="text-align:right">2011-07-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01316e3136d606e6.jsp" class="cmsHref_self" title="7.23事故大部分高铁供应商集体沉默">7.23事故大部分高铁供应商集体沉默</a></td>
							<td style="text-align:right">2011-07-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01316934db1104ed.jsp" class="cmsHref_self" title="卫浴市场竞争悄然改变 方式逐渐明朗">卫浴市场竞争悄然改变 方式逐渐明朗</a></td>
							<td style="text-align:right">2011-07-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01316933852304ec.jsp" class="cmsHref_self" title="三星电子计划加大对存储芯片投资">三星电子计划加大对存储芯片投资</a></td>
							<td style="text-align:right">2011-07-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01316932f91f04eb.jsp" class="cmsHref_self" title="电子节能灯 四大优势为其高价撑腰">电子节能灯 四大优势为其高价撑腰</a></td>
							<td style="text-align:right">2011-07-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a013169320fda04ea.jsp" class="cmsHref_self" title="美的中央空调再签上千套空气能热水机大单">美的中央空调再签上千套空气能热水机大单</a></td>
							<td style="text-align:right">2011-07-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a0131691fa8db04c9.jsp" class="cmsHref_self" title="格力电器半年净利增四成 同比增长60.03%">格力电器半年净利增四成 同比增长60.03%</a></td>
							<td style="text-align:right">2011-07-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a0131691ee29904c8.jsp" class="cmsHref_self" title="通号集团迷雾待解 手握中国高铁命脉">通号集团迷雾待解 手握中国高铁命脉</a></td>
							<td style="text-align:right">2011-07-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a0131691df9b804c7.jsp" class="cmsHref_self" title="欧盟批准联想集团收购德国梅迪昂电脑公司">欧盟批准联想集团收购德国梅迪昂电脑公司</a></td>
							<td style="text-align:right">2011-07-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01316919a9af04c5.jsp" class="cmsHref_self" title="台媒称华为为降价左右台湾供应商出货量">台媒称华为为降价左右台湾供应商出货量</a></td>
							<td style="text-align:right">2011-07-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01316918b0cd04c4.jsp" class="cmsHref_self" title="为降iPad3成本 苹果寻找新的IC供应商">为降iPad3成本 苹果寻找新的IC供应商</a></td>
							<td style="text-align:right">2011-07-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a0131691847c204c3.jsp" class="cmsHref_self" title="供应商监而不控 事故动车通信监控系统招标存疑">供应商监而不控 事故动车通信监控系统招标存疑</a></td>
							<td style="text-align:right">2011-07-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a01316913037004c1.jsp" class="cmsHref_self" title="北京将考核公车定点维修供应商">北京将考核公车定点维修供应商</a></td>
							<td style="text-align:right">2011-07-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a013163fa37e202d2.jsp" class="cmsHref_self" title="佳能因日本地震因素影响利润降三成">佳能因日本地震因素影响利润降三成</a></td>
							<td style="text-align:right">2011-07-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a013163f8082902ce.jsp" class="cmsHref_self" title="上半年吉利沃尔沃合并收入700亿">上半年吉利沃尔沃合并收入700亿</a></td>
							<td style="text-align:right">2011-07-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a013163f0a7d802c7.jsp" class="cmsHref_self" title="达芬奇开通投诉热线 鉴定结果未出维权艰难">达芬奇开通投诉热线 鉴定结果未出维权艰难</a></td>
							<td style="text-align:right">2011-07-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a013163eed15b02c6.jsp" class="cmsHref_self" title="机构扎堆买醉 茅台股价创新高">机构扎堆买醉 茅台股价创新高</a></td>
							<td style="text-align:right">2011-07-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131518b2a013163e64a0302c2.jsp" class="cmsHref_self" title="动车追尾原因仍未公布 相关供应商急于撇清关系">动车追尾原因仍未公布 相关供应商急于撇清关系</a></td>
							<td style="text-align:right">2011-07-26</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_8.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_10.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：9/29 每页20条 </span>
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