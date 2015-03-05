<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>网站公告- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/notice/notice.jsp" title="网站公告" class="cmsHref_self">网站公告</a>
		
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
				<input type="hidden"  id="channelId" value="402883d429a14e050129a1871ed1044c" />
				<table class="frontTableList" id="qualityTable">
					<thead>
						<tr>
							<th class="left">标题</th>
							<th class="center">时间</th>
						</tr>
					</thead>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/notice/ff8080813249c67601324cc7eedd0b30.jsp" class="cmsHref_self" title="废旧物资处置说明">废旧物资处置说明</a></td>
							<td style="text-align:right">2011-09-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/notice/ff808081321f7bae013222f81a9602c3.jsp" class="cmsHref_self" title="理光MP7001采购项目成交通告">理光MP7001采购项目成交通告</a></td>
							<td style="text-align:right">2011-09-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/notice/ff808081321f7bae013222f0207c022b.jsp" class="cmsHref_self" title="紧急征寻防水问题解决专家、清华同方地源热泵、中央空调送风系统问题解决专家">紧急征寻防水问题解决专家、清华同方地源热泵、中央空调送风系统问题解决专家</a></td>
							<td style="text-align:right">2011-09-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/notice/ff8080813204bef6013212fea5cd038b.jsp" class="cmsHref_self" title="中秋节月饼采购项目成交公告">中秋节月饼采购项目成交公告</a></td>
							<td style="text-align:right">2011-08-29</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/notice/ff80808131ff33bb013203d18731015c.jsp" class="cmsHref_self" title="热敏打印纸采购项目成交通告">热敏打印纸采购项目成交通告</a></td>
							<td style="text-align:right">2011-08-26</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/notice/ff80808131d1eaa60131d5bdc72f02aa.jsp" class="cmsHref_self" title="国信创新中秋月饼采购活动">国信创新中秋月饼采购活动</a></td>
							<td style="text-align:right">2011-08-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/notice/ff8080813140dc4701314f6b0be51244.jsp" class="cmsHref_self" title="笔记本电脑采购项目成交通告">笔记本电脑采购项目成交通告</a></td>
							<td style="text-align:right">2011-07-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/notice/ff8080813078a9560130868180100384.jsp" class="cmsHref_self" title="稻香村粽子采购项目成交通告">稻香村粽子采购项目成交通告</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/notice/ff8080813043eecc013044037efc0149.jsp" class="cmsHref_self" title="联想一体电脑采购项目成交通告">联想一体电脑采购项目成交通告</a></td>
							<td style="text-align:right">2011-05-31</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/notice/ff8080812fbfbecc012fd2868ece0734.jsp" class="cmsHref_self" title="锡盘、奖牌、证书、水晶奖杯采购项目成交通告">锡盘、奖牌、证书、水晶奖杯采购项目成交通告</a></td>
							<td style="text-align:right">2011-05-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/notice/ff8080812fb4297b012fb99cf8c402fa.jsp" class="cmsHref_self" title="联想一体电脑机采购项目成交通告">联想一体电脑机采购项目成交通告</a></td>
							<td style="text-align:right">2011-05-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/notice/ff8080812fb4297b012fb90b02c5021f.jsp" class="cmsHref_self" title="印刷书籍采购项目成交通告">印刷书籍采购项目成交通告</a></td>
							<td style="text-align:right">2011-05-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/notice/ff8080812f919712012f964176340225.jsp" class="cmsHref_self" title="办公用台式电脑采购项目成交通告">办公用台式电脑采购项目成交通告</a></td>
							<td style="text-align:right">2011-04-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/notice/ff8080812f919712012f9631039901be.jsp" class="cmsHref_self" title="数据库服务器采购项目成交通告">数据库服务器采购项目成交通告</a></td>
							<td style="text-align:right">2011-04-27</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/notice/ff8080812f04f7fd012f340966b72c73.jsp" class="cmsHref_self" title="佳能LiDE 110 扫描仪采购项目成交通告">佳能LiDE 110 扫描仪采购项目成交通告</a></td>
							<td style="text-align:right">2011-04-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/notice/ff8080812eec2137012efa66fd580be3.jsp" class="cmsHref_self" title="短信猫采购项目成交通告">短信猫采购项目成交通告</a></td>
							<td style="text-align:right">2011-03-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/notice/ff8080812eec2137012efa44076b0a51.jsp" class="cmsHref_self" title="联想一体电脑采购项目成交通告">联想一体电脑采购项目成交通告</a></td>
							<td style="text-align:right">2011-03-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/notice/ff8080812eec2137012efa2a987c0921.jsp" class="cmsHref_self" title="夏普复合机MX-M261采购项目成交通告">夏普复合机MX-M261采购项目成交通告</a></td>
							<td style="text-align:right">2011-03-28</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/notice/2c9087922ca1337c012caac9eaf6093e.jsp" class="cmsHref_self" title="企业会议、客房、娱乐、餐饮等协议预订采购项目成交通告">企业会议、客房、娱乐、餐饮等协议预订采购项目成交通告</a></td>
							<td style="text-align:right">2010-12-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/notice/2c9087922c781b2f012c7c72ed1e02d4.jsp" class="cmsHref_self" title="北京地区首届纸品采购供需联谊酒会公告">北京地区首届纸品采购供需联谊酒会公告</a></td>
							<td style="text-align:right">2010-11-24</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/notice/notice_2.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/notice/notice_2.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：1/2 每页20条 </span>
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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=notice&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
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