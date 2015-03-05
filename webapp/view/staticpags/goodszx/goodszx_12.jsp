<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>供货资讯- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/goodszx.jsp" title="供货资讯" class="cmsHref_self">供货资讯</a>
		
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
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<table class="frontTableList" id="qualityTable">
					<thead>
						<tr>
							<th class="left">标题</th>
							<th class="center">时间</th>
						</tr>
					</thead>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813078a9560130867b49c70373.jsp" class="cmsHref_self" title="中国铜需求已恢复至危机前水平">中国铜需求已恢复至危机前水平</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813078a9560130867892c6036c.jsp" class="cmsHref_self" title="今年平板电脑出货6320万台 内存需求增9倍">今年平板电脑出货6320万台 内存需求增9倍</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813078a956013086777e890363.jsp" class="cmsHref_self" title="大豆库存大于市场实际需求 美豆粕涨势受抑">大豆库存大于市场实际需求 美豆粕涨势受抑</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813078a95601308676b1bc0361.jsp" class="cmsHref_self" title="世界黄金协会：2011下半年黄金需求仍然强劲">世界黄金协会：2011下半年黄金需求仍然强劲</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813078a95601308675a48e035f.jsp" class="cmsHref_self" title="太阳能光伏秘书长：全球光伏需求不会下降">太阳能光伏秘书长：全球光伏需求不会下降</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813078a9560130866bf1bf0350.jsp" class="cmsHref_self" title="印度供应商预计未来两个月铁矿石价格将下降10-15%">印度供应商预计未来两个月铁矿石价格将下降10-15%</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f0130772cac664327.jsp" class="cmsHref_self" title="欧佩克分歧引供应担忧 油价收高1.2%">欧佩克分歧引供应担忧 油价收高1.2%</a></td>
							<td style="text-align:right">2011-06-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f013076ff6a074228.jsp" class="cmsHref_self" title="中国铜需求增速或下滑至6-8% 仍将供不应求">中国铜需求增速或下滑至6-8% 仍将供不应求</a></td>
							<td style="text-align:right">2011-06-10</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f013071e28681306b.jsp" class="cmsHref_self" title="需求强劲及供应预期支撑 美玉米大幅攀高">需求强劲及供应预期支撑 美玉米大幅攀高</a></td>
							<td style="text-align:right">2011-06-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f013071d324dd3038.jsp" class="cmsHref_self" title="国家发改委:预计国内油品供应形势仍不容乐观">国家发改委:预计国内油品供应形势仍不容乐观</a></td>
							<td style="text-align:right">2011-06-09</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f01306cb2a34a230b.jsp" class="cmsHref_self" title="涂料市场在2011年还将有一次大范围提升">涂料市场在2011年还将有一次大范围提升</a></td>
							<td style="text-align:right">2011-06-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f01306cb21fa92309.jsp" class="cmsHref_self" title="未来三五年 钢木门市场需求会大副提升">未来三五年 钢木门市场需求会大副提升</a></td>
							<td style="text-align:right">2011-06-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f01306cb0ac3e2306.jsp" class="cmsHref_self" title="铅价料因供应吃紧而持续上扬">铅价料因供应吃紧而持续上扬</a></td>
							<td style="text-align:right">2011-06-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f01306cac44e022ff.jsp" class="cmsHref_self" title="食糖产销数据利好出尽 糖价仍有旺季需求支撑">食糖产销数据利好出尽 糖价仍有旺季需求支撑</a></td>
							<td style="text-align:right">2011-06-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f01306caba8e122fd.jsp" class="cmsHref_self" title="成品油需求南北两重天">成品油需求南北两重天</a></td>
							<td style="text-align:right">2011-06-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f01306cab089c22fb.jsp" class="cmsHref_self" title="刚性需求增长、干旱和游资推升 中药材价格半月翻番">刚性需求增长、干旱和游资推升 中药材价格半月翻番</a></td>
							<td style="text-align:right">2011-06-08</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f013067a8d42516be.jsp" class="cmsHref_self" title="安溪铁观音春茶减产价格普涨 高端茶叶售价翻倍">安溪铁观音春茶减产价格普涨 高端茶叶售价翻倍</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f013067a4238b16ba.jsp" class="cmsHref_self" title="5月化工品跌多涨少露疲态 氟化工纯碱强势不减">5月化工品跌多涨少露疲态 氟化工纯碱强势不减</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f0130679e386916b6.jsp" class="cmsHref_self" title="中药种植减少导致供不应求 处10年最快涨价期">中药种植减少导致供不应求 处10年最快涨价期</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f0130679d477a16b4.jsp" class="cmsHref_self" title="药企难忍原材料涨价压力 快速延伸产业链避险">药企难忍原材料涨价压力 快速延伸产业链避险</a></td>
							<td style="text-align:right">2011-06-07</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_11.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_13.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_19.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：12/19 每页20条 </span>
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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=goodszx&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
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