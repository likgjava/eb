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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fbfbecc012fd23d37670687.jsp" class="cmsHref_self" title="国内需求旺盛 中石化已停止成品油出口">国内需求旺盛 中石化已停止成品油出口</a></td>
							<td style="text-align:right">2011-05-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fbfbecc012fc334c72301d9.jsp" class="cmsHref_self" title="大众C级轿车迫切进入公务车 商务车市场">大众C级轿车迫切进入公务车 商务车市场</a></td>
							<td style="text-align:right">2011-05-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fbfbecc012fc31e4983019c.jsp" class="cmsHref_self" title="空调业盛世显隐患 成本洗牌战或重启">空调业盛世显隐患 成本洗牌战或重启</a></td>
							<td style="text-align:right">2011-05-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fbfbecc012fc2e541b700e6.jsp" class="cmsHref_self" title="中色南方已开建国内最大稀土分离厂">中色南方已开建国内最大稀土分离厂</a></td>
							<td style="text-align:right">2011-05-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fbfbecc012fc2d639eb00de.jsp" class="cmsHref_self" title="本土品牌青岛啤酒或将首次收购外资品牌">本土品牌青岛啤酒或将首次收购外资品牌</a></td>
							<td style="text-align:right">2011-05-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fba4e32012fbe2082460233.jsp" class="cmsHref_self" title="石化双雄停止供油 重庆民营加油站面临倒闭">石化双雄停止供油 重庆民营加油站面临倒闭</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fba4e32012fbdd3abd40192.jsp" class="cmsHref_self" title="原材料供应不足 南海本田停产半月">原材料供应不足 南海本田停产半月</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fba4e32012fbdceae730190.jsp" class="cmsHref_self" title="联想电子商务供应链管理达成既定战略">联想电子商务供应链管理达成既定战略</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fba4e32012fbdc1f993016a.jsp" class="cmsHref_self" title="华硕平板供货紧张 组件短缺月产1万台">华硕平板供货紧张 组件短缺月产1万台</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fba4e32012fbdbb6cf60166.jsp" class="cmsHref_self" title="承德露露增长遇瓶颈关联交易采购额增35%">承德露露增长遇瓶颈关联交易采购额增35%</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fba4e32012fbdb704400164.jsp" class="cmsHref_self" title="宏达电与亚太移动联盟Conexus签订采购计划">宏达电与亚太移动联盟Conexus签订采购计划</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fba4e32012fbdac15cf015e.jsp" class="cmsHref_self" title="丰田称没有产业外移计划 将比以往更重视中国">丰田称没有产业外移计划 将比以往更重视中国</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fba4e32012fbda7d40a015a.jsp" class="cmsHref_self" title="东莞汽车经销商：车源紧 厂家配车量减少">东莞汽车经销商：车源紧 厂家配车量减少</a></td>
							<td style="text-align:right">2011-05-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fb4297b012fb8e033dd01ac.jsp" class="cmsHref_self" title="青岛汽车厂的供应链管理改进">青岛汽车厂的供应链管理改进</a></td>
							<td style="text-align:right">2011-05-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fb4297b012fb8db0c0701a6.jsp" class="cmsHref_self" title="阿根廷限制进口致国内市场供应短缺">阿根廷限制进口致国内市场供应短缺</a></td>
							<td style="text-align:right">2011-05-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fb4297b012fb8da546401a4.jsp" class="cmsHref_self" title="日本企业加紧恢复供应链 但尚需时日">日本企业加紧恢复供应链 但尚需时日</a></td>
							<td style="text-align:right">2011-05-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812fb4297b012fb8d9874901a2.jsp" class="cmsHref_self" title="零部件用品展重庆启幕 供应商看准日本震后市场">零部件用品展重庆启幕 供应商看准日本震后市场</a></td>
							<td style="text-align:right">2011-05-04</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812f9cf3c5012fb397d82e0556.jsp" class="cmsHref_self" title="供应吃紧，泰国汽车生产变数泰多">供应吃紧，泰国汽车生产变数泰多</a></td>
							<td style="text-align:right">2011-05-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812f9cf3c5012fb39664960554.jsp" class="cmsHref_self" title="零部件供应紧张 日系车遭遇复产危机">零部件供应紧张 日系车遭遇复产危机</a></td>
							<td style="text-align:right">2011-05-03</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080812f9cf3c5012fb3934b5f053f.jsp" class="cmsHref_self" title="iPad2二季度出货量大增 台湾供应链有望获益">iPad2二季度出货量大增 台湾供应链有望获益</a></td>
							<td style="text-align:right">2011-05-03</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_27.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/supplierzx/supplierzx_29.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：28/29 每页20条 </span>
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