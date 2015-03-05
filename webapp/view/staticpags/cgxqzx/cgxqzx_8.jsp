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
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130b726330130b9f35f760142.jsp" class="cmsHref_self" title="在国家层面建立政府采购管理机构">在国家层面建立政府采购管理机构</a></td>
							<td style="text-align:right">2011-06-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130b726330130b9f265bb0140.jsp" class="cmsHref_self" title="富美绿色硒鼓推动政府耗材分包采购">富美绿色硒鼓推动政府耗材分包采购</a></td>
							<td style="text-align:right">2011-06-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130b726330130b9f159b3013e.jsp" class="cmsHref_self" title="稀土冶炼企业原矿采购陷停滞">稀土冶炼企业原矿采购陷停滞</a></td>
							<td style="text-align:right">2011-06-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130b726330130b9f077d3011f.jsp" class="cmsHref_self" title="河南采购800多万元台湾农产品 首批货物已启运">河南采购800多万元台湾农产品 首批货物已启运</a></td>
							<td style="text-align:right">2011-06-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130b726330130b9efafac00da.jsp" class="cmsHref_self" title="集中采购降低成本 “宁波制造”青岛“取经”">集中采购降低成本 “宁波制造”青岛“取经”</a></td>
							<td style="text-align:right">2011-06-23</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130afdee10130b4e0870f050c.jsp" class="cmsHref_self" title="辽宁省投入3.16亿元采购消防器材装备">辽宁省投入3.16亿元采购消防器材装备</a></td>
							<td style="text-align:right">2011-06-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130afdee10130b4da2e4704fc.jsp" class="cmsHref_self" title="全球酒业品牌50强出炉 中国茅台排第九">全球酒业品牌50强出炉 中国茅台排第九</a></td>
							<td style="text-align:right">2011-06-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130afdee10130b4c7959903f8.jsp" class="cmsHref_self" title="日本模具企业采购两种重要战略浅析">日本模具企业采购两种重要战略浅析</a></td>
							<td style="text-align:right">2011-06-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130afdee10130b4c7000d03f6.jsp" class="cmsHref_self" title="公务车新政实施 合资品牌领军采购市场">公务车新政实施 合资品牌领军采购市场</a></td>
							<td style="text-align:right">2011-06-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130afdee10130b4c6020703f4.jsp" class="cmsHref_self" title="中央和地方软件正版化采购额达3.7亿元">中央和地方软件正版化采购额达3.7亿元</a></td>
							<td style="text-align:right">2011-06-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130afdee10130b4c4b83203f2.jsp" class="cmsHref_self" title="中电信再抛4000万终端采购">中电信再抛4000万终端采购</a></td>
							<td style="text-align:right">2011-06-22</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130ab37e40130afa970d30591.jsp" class="cmsHref_self" title="国产医疗器械迎发展良机 十二五获优先采购">国产医疗器械迎发展良机 十二五获优先采购</a></td>
							<td style="text-align:right">2011-06-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130ab37e40130afa72bc6058f.jsp" class="cmsHref_self" title="吴楠：应强化对采购人行为的约束">吴楠：应强化对采购人行为的约束</a></td>
							<td style="text-align:right">2011-06-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808130ab37e40130afa675ea058a.jsp" class="cmsHref_self" title="王建明：如何防范政府采购评审风险">王建明：如何防范政府采购评审风险</a></td>
							<td style="text-align:right">2011-06-21</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081309cd86d0130aa8489fd0027.jsp" class="cmsHref_self" title="海南矿业每年将向宁波钢铁供应铁矿石约30万吨">海南矿业每年将向宁波钢铁供应铁矿石约30万吨</a></td>
							<td style="text-align:right">2011-06-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081309cd86d0130aa81aa2b0023.jsp" class="cmsHref_self" title="大陆彩电企业采购台湾面板55亿美元">大陆彩电企业采购台湾面板55亿美元</a></td>
							<td style="text-align:right">2011-06-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081309cd86d0130aa810b530021.jsp" class="cmsHref_self" title="今年60亿元政府采购任务完成过半">今年60亿元政府采购任务完成过半</a></td>
							<td style="text-align:right">2011-06-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081309cd86d0130aa7f1b36001f.jsp" class="cmsHref_self" title="家电业将集体“补课”联合国采购">家电业将集体“补课”联合国采购</a></td>
							<td style="text-align:right">2011-06-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081309cd86d0130aa7d7773001d.jsp" class="cmsHref_self" title="山东菏泽：皮棉价格下跌 采购依然迟缓">山东菏泽：皮棉价格下跌 采购依然迟缓</a></td>
							<td style="text-align:right">2011-06-20</td>
						</tr>
						<tr>
							<td style="text-align:left"><a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081309cd86d0130aa7c687e0019.jsp" class="cmsHref_self" title="中国电信用户逾亿 签400亿终端采购大单">中国电信用户逾亿 签400亿终端采购大单</a></td>
							<td style="text-align:right">2011-06-20</td>
						</tr>
				</table>
				<div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_7.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_9.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/cgxqzx/cgxqzx_18.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：8/18 每页20条 </span>
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