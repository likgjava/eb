<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>采购焦点资讯- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="采购焦点资讯" class="cmsHref_self">采购焦点资讯</a>
		
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
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<table class="frontTableList" id="qualityTable">
					<thead>
						<tr>
							<th class="left">标题</th>
							<th class="center">时间</th>
						</tr>
					</thead>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813118359201311bd06569036b.jsp" class="cmsHref_self" title="电梯采购：坚守生命的安全防线">电梯采购：坚守生命的安全防线</a></td>
							<td style="text-align:right">2011-07-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813118359201311bcdb9460365.jsp" class="cmsHref_self" title="台香蕉产量过剩大陆紧急采购 马英九被批反应慢">台香蕉产量过剩大陆紧急采购 马英九被批反应慢</a></td>
							<td style="text-align:right">2011-07-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813118359201311bcccb39035f.jsp" class="cmsHref_self" title="脱离政府采购支持 国产软件还能走多远">脱离政府采购支持 国产软件还能走多远</a></td>
							<td style="text-align:right">2011-07-12</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a7013116b9a68410b7.jsp" class="cmsHref_self" title="五金产品价格普遍上涨 采购外商表示理解">五金产品价格普遍上涨 采购外商表示理解</a></td>
							<td style="text-align:right">2011-07-11</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70131074012910bbe.jsp" class="cmsHref_self" title="消息称苹果年底内计划供应2500万台iPhone5">消息称苹果年底内计划供应2500万台iPhone5</a></td>
							<td style="text-align:right">2011-07-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70131073551b40bae.jsp" class="cmsHref_self" title="触摸屏全年需求涨三成">触摸屏全年需求涨三成</a></td>
							<td style="text-align:right">2011-07-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a701310733e9ee0bac.jsp" class="cmsHref_self" title="秋季全球平板电脑需求将增至2100万台">秋季全球平板电脑需求将增至2100万台</a></td>
							<td style="text-align:right">2011-07-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70131073148480baa.jsp" class="cmsHref_self" title="联合国采购专家给中国电子企业“集体补课”">联合国采购专家给中国电子企业“集体补课”</a></td>
							<td style="text-align:right">2011-07-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70131073067330ba8.jsp" class="cmsHref_self" title="河北石家庄棉价继续下跌 纺企几无采购">河北石家庄棉价继续下跌 纺企几无采购</a></td>
							<td style="text-align:right">2011-07-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70131072fe5ea09d7.jsp" class="cmsHref_self" title="重庆：分散采购步入标准化时代">重庆：分散采购步入标准化时代</a></td>
							<td style="text-align:right">2011-07-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70131072e413809d3.jsp" class="cmsHref_self" title="湖南谋划“十二五” 推动绿色采购">湖南谋划“十二五” 推动绿色采购</a></td>
							<td style="text-align:right">2011-07-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a701310204778e06f6.jsp" class="cmsHref_self" title="五金行业主涨价很忐忑 采购外商表示理解">五金行业主涨价很忐忑 采购外商表示理解</a></td>
							<td style="text-align:right">2011-07-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a701310203a8e106f4.jsp" class="cmsHref_self" title="政府采购仍是各国实现自主创新重要手段">政府采购仍是各国实现自主创新重要手段</a></td>
							<td style="text-align:right">2011-07-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a701310202fdc706f2.jsp" class="cmsHref_self" title="中央单位批量集中采购执行是关键">中央单位批量集中采购执行是关键</a></td>
							<td style="text-align:right">2011-07-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a701310201ebe4051f.jsp" class="cmsHref_self" title="北京市交通委称将暂停采购奥迪斯品牌电梯">北京市交通委称将暂停采购奥迪斯品牌电梯</a></td>
							<td style="text-align:right">2011-07-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70130fcdd7b6e019a.jsp" class="cmsHref_self" title="吉木乃县政府采购监管节约221万元">吉木乃县政府采购监管节约221万元</a></td>
							<td style="text-align:right">2011-07-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70130fcda4c500198.jsp" class="cmsHref_self" title="国产医疗器械十二五期间将获优先采购">国产医疗器械十二五期间将获优先采购</a></td>
							<td style="text-align:right">2011-07-06</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70130f7ca3b536e3c.jsp" class="cmsHref_self" title="中国工业转包展拟采购金额突破1亿美元">中国工业转包展拟采购金额突破1亿美元</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70130f7c95c836e3a.jsp" class="cmsHref_self" title="硅片价格大幅下跌 尚德赔2亿美元终止采购合同">硅片价格大幅下跌 尚德赔2亿美元终止采购合同</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130db80a70130f7c8a5f66e38.jsp" class="cmsHref_self" title="政府采购难成国产软件救命稻草">政府采购难成国产软件救命稻草</a></td>
							<td style="text-align:right">2011-07-05</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_5.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_7.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_18.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：6/18 每页20条 </span>
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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=cgxqzx&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
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