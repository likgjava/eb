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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f7e028176e68.jsp" class="cmsHref_self" title="雀巢徐福记展开收购谈判 市场价值预计26亿美元">雀巢徐福记展开收购谈判 市场价值预计26亿美元</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f7dc9a0e6e66.jsp" class="cmsHref_self" title="玉米旺季价格难旺 宏观调控保证供应">玉米旺季价格难旺 宏观调控保证供应</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f7dbb8546e64.jsp" class="cmsHref_self" title="大宇造船研发气体燃料供应系统">大宇造船研发气体燃料供应系统</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f7d698386e56.jsp" class="cmsHref_self" title="苹果触控面板最大供应商1.5亿美元加码东莞项目">苹果触控面板最大供应商1.5亿美元加码东莞项目</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f7d5dfce6e54.jsp" class="cmsHref_self" title="德国零部件供应商莱尼发行300万股新股份">德国零部件供应商莱尼发行300万股新股份</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f7d145cb6e45.jsp" class="cmsHref_self" title="伊利成为京沪高铁乳品供应商">伊利成为京沪高铁乳品供应商</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f2acb9c80a96.jsp" class="cmsHref_self" title="中国“芯”加速半导体热点应用全新化">中国“芯”加速半导体热点应用全新化</a></td>
							<td style="text-align:right">2011-07-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f2ab2b820a94.jsp" class="cmsHref_self" title="光明食品拟收购一家澳大利亚葡萄酒企业">光明食品拟收购一家澳大利亚葡萄酒企业</a></td>
							<td style="text-align:right">2011-07-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f2a94a640a91.jsp" class="cmsHref_self" title="中国地板企业抱团抗诉美国“双反调查”">中国地板企业抱团抗诉美国“双反调查”</a></td>
							<td style="text-align:right">2011-07-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f2a7a1a00a8e.jsp" class="cmsHref_self" title="淘汰落后产能 国内建筑钢材价格会止跌">淘汰落后产能 国内建筑钢材价格会止跌</a></td>
							<td style="text-align:right">2011-07-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f2a6e3100a8c.jsp" class="cmsHref_self" title="预计2012年之前建筑涂料市场有大幅上升">预计2012年之前建筑涂料市场有大幅上升</a></td>
							<td style="text-align:right">2011-07-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f2a5b98a0a84.jsp" class="cmsHref_self" title="三星高管称下半年业务将比上半年更困难">三星高管称下半年业务将比上半年更困难</a></td>
							<td style="text-align:right">2011-07-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f29b26700a50.jsp" class="cmsHref_self" title="韩刚拆解迪士尼在华消费品战略">韩刚拆解迪士尼在华消费品战略</a></td>
							<td style="text-align:right">2011-07-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f299733b0a4e.jsp" class="cmsHref_self" title="抢占行业先机 金锣规模化养殖初见成效">抢占行业先机 金锣规模化养殖初见成效</a></td>
							<td style="text-align:right">2011-07-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f297fbbc0a4c.jsp" class="cmsHref_self" title="中储粮跨界扩张步伐加快 全产业链帝国隐现">中储粮跨界扩张步伐加快 全产业链帝国隐现</a></td>
							<td style="text-align:right">2011-07-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f294189d0a4a.jsp" class="cmsHref_self" title="美零部件供应商DLH为扩产能设新工厂">美零部件供应商DLH为扩产能设新工厂</a></td>
							<td style="text-align:right">2011-07-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130f29186ce0a46.jsp" class="cmsHref_self" title="苹果三星互咬暗战专利 苹果供货或紧张">苹果三星互咬暗战专利 苹果供货或紧张</a></td>
							<td style="text-align:right">2011-07-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130e33905ac0627.jsp" class="cmsHref_self" title="国产大飞机确定唯一西方发动机">国产大飞机确定唯一西方发动机</a></td>
							<td style="text-align:right">2011-07-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130e3380ac70625.jsp" class="cmsHref_self" title="双鹤药业万东医疗划归华润旗下">双鹤药业万东医疗划归华润旗下</a></td>
							<td style="text-align:right">2011-07-01</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130e336cac5061d.jsp" class="cmsHref_self" title="强生声明称霉味药品未进入中国市场">强生声明称霉味药品未进入中国市场</a></td>
							<td style="text-align:right">2011-07-01</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_17.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_19.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：18/29 每页20条 </span>
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