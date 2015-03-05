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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081309200df01309b1dfd02038b.jsp" class="cmsHref_self" title="苹果供应商重庆建厂 电子配套业西迁之考">苹果供应商重庆建厂 电子配套业西迁之考</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081309200df01309b1d43a00389.jsp" class="cmsHref_self" title="我国服装企业实施供应链管理的方案">我国服装企业实施供应链管理的方案</a></td>
							<td style="text-align:right">2011-06-17</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813090cbdd013090fae6e80102.jsp" class="cmsHref_self" title="需求不旺价格下移 棉花现货市场不容乐观">需求不旺价格下移 棉花现货市场不容乐观</a></td>
							<td style="text-align:right">2011-06-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813090cbdd013090f4775e00fa.jsp" class="cmsHref_self" title="供应充足抑制上行 焦炭中期仍难有好表现">供应充足抑制上行 焦炭中期仍难有好表现</a></td>
							<td style="text-align:right">2011-06-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813090cbdd013090f2b89300f8.jsp" class="cmsHref_self" title="供应增加使国内部分农产品价格下降">供应增加使国内部分农产品价格下降</a></td>
							<td style="text-align:right">2011-06-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813090cbdd013090f1ad3600f6.jsp" class="cmsHref_self" title="2011制冷剂市场供应紧张 价格高位运行">2011制冷剂市场供应紧张 价格高位运行</a></td>
							<td style="text-align:right">2011-06-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813090cbdd013090f08fdc00f4.jsp" class="cmsHref_self" title="我国决心打破铁矿石垄断成全球头号供应国">我国决心打破铁矿石垄断成全球头号供应国</a></td>
							<td style="text-align:right">2011-06-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813090cbdd013090e69c0b00e4.jsp" class="cmsHref_self" title="供需矛盾纠结 六月钢材市场价格仍震荡">供需矛盾纠结 六月钢材市场价格仍震荡</a></td>
							<td style="text-align:right">2011-06-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813090cbdd013090e2314f00db.jsp" class="cmsHref_self" title="5月粗钢产量刷新单月最高纪录">5月粗钢产量刷新单月最高纪录</a></td>
							<td style="text-align:right">2011-06-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813090cbdd013090e1a7f100d9.jsp" class="cmsHref_self" title="Vale预计日本四季度铁矿石需求将回升">Vale预计日本四季度铁矿石需求将回升</a></td>
							<td style="text-align:right">2011-06-15</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813078a95601308bae2bd51237.jsp" class="cmsHref_self" title="谷物供应决定粮食安全 或推动大宗商品再掀波澜">谷物供应决定粮食安全 或推动大宗商品再掀波澜</a></td>
							<td style="text-align:right">2011-06-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813078a95601308ba9e6081227.jsp" class="cmsHref_self" title="供应充足需求疲软 油价盘中下挫1.1%">供应充足需求疲软 油价盘中下挫1.1%</a></td>
							<td style="text-align:right">2011-06-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813078a95601308ba8dc891225.jsp" class="cmsHref_self" title="钢材市场下半年难言乐观 长材需求或好于板材">钢材市场下半年难言乐观 长材需求或好于板材</a></td>
							<td style="text-align:right">2011-06-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813078a95601308ba774261214.jsp" class="cmsHref_self" title="重金属监测仪需求料大幅增加">重金属监测仪需求料大幅增加</a></td>
							<td style="text-align:right">2011-06-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813078a95601308ba50b8e1204.jsp" class="cmsHref_self" title="国内大宗商品需求强劲">国内大宗商品需求强劲</a></td>
							<td style="text-align:right">2011-06-14</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813078a9560130868a9cd303d6.jsp" class="cmsHref_self" title="2014年世界机床需求有望达到1210亿美元">2014年世界机床需求有望达到1210亿美元</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813078a9560130868332b303a7.jsp" class="cmsHref_self" title="机构称牛奶产量今年预计达到4000万吨">机构称牛奶产量今年预计达到4000万吨</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813078a9560130867ec99f0380.jsp" class="cmsHref_self" title="中国木材供应赤字将提高五成">中国木材供应赤字将提高五成</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813078a9560130867dd2a3037e.jsp" class="cmsHref_self" title="受供应增加影响 原油大幅走低">受供应增加影响 原油大幅走低</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813078a9560130867cb02f0378.jsp" class="cmsHref_self" title="供应压力略有缓解 豆油现货市场相对坚挺">供应压力略有缓解 豆油现货市场相对坚挺</a></td>
							<td style="text-align:right">2011-06-13</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_10.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_12.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/goodszx/goodszx_19.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：11/19 每页20条 </span>
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