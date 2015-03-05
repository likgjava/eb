<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script language="javascript" type="text/javascript">
$(document).ready(function(){
$("#contentMain").addClass("sqindex3pa");
	// 为左边栏设定选中样式,判断用于解决点击左边栏后样式被覆盖问题
	var id = $("#channelId").val();
	if(null != $('.subnav>li[id='+id+']').html()) {
		$('.subnav>li').removeClass('selected');
		$('.subnav>li[id='+id+']').addClass('selected');
	}
	$('#searchNewsContentMainBtn').click(function() {
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=sqzx&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
	});
	$("#startTime").epsDatepicker();
     $("#endTime").epsDatepicker();
})
</script>
 <div id="conTitle">
 	<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/smallscale/business/shangquan.jsp" title="陶朱公商圈" class="cmsHref_self">陶朱公商圈</a>
	<a href="javascript:void(0)" id="/view/staticpags/sqzx/sqzx.jsp" title="商圈资讯" class="cmsHref_self">商圈资讯</a>
		
 	</div>
  <div class="formTips attention">
	<ul><li><em>商圈资讯展示：</em></li></ul>
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
 <input type="hidden"  id="channelId" value="ff8080812dd59be5012dda319fa70356" />
 <table class="frontTableList" id="qualityTable">
      <thead>
        <tr>
          <th class="left">标题</th>
          <th class="center">时间</th>
        </tr>
      </thead>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4d73b8f5026b.jsp" class="toContentMain" title="国内首台20兆瓦大容量超高速防爆电机研发成功">国内首台20兆瓦大容量超高速防爆电机研发成功</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4d723fad0269.jsp" class="toContentMain" title="上海电气成功研制天然气长输管道核心设备">上海电气成功研制天然气长输管道核心设备</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4d71016b0267.jsp" class="toContentMain" title="航天产业渐成全球关注热点 五大科技亮点闪耀">航天产业渐成全球关注热点 五大科技亮点闪耀</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4d6f42230265.jsp" class="toContentMain" title="国内世界先进压水堆核电关键设备实现“中国制造”">国内世界先进压水堆核电关键设备实现“中国制造”</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4d6db3330263.jsp" class="toContentMain" title="两岸合资LED项目“开发晶”年底开工 总投资达５亿美元">两岸合资LED项目“开发晶”年底开工 总投资达５亿美元</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4d6cb8a30261.jsp" class="toContentMain" title="塑料光纤项目落户在广西来宾来华">塑料光纤项目落户在广西来宾来华</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4d6bb7e7025f.jsp" class="toContentMain" title="LED制造成本下降 2011年LED灯具渗透率增加">LED制造成本下降 2011年LED灯具渗透率增加</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4d6a9288025d.jsp" class="toContentMain" title="广州6亿美元打造华南LED芯片基地　">广州6亿美元打造华南LED芯片基地　</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4d6995fb025b.jsp" class="toContentMain" title="整体衣柜使用率仅为6.8%　市场仍有待发掘">整体衣柜使用率仅为6.8%　市场仍有待发掘</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4d68249f0259.jsp" class="toContentMain" title="国外玻纤新产品向多材料复合方向发展">国外玻纤新产品向多材料复合方向发展</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4d66b9d10257.jsp" class="toContentMain" title="乌鲁木齐高速公路互通式立交工程开工">乌鲁木齐高速公路互通式立交工程开工</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4d6584140255.jsp" class="toContentMain" title="广西：2011计划投资25亿元建设农村公路网">广西：2011计划投资25亿元建设农村公路网</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4d6473820253.jsp" class="toContentMain" title="新疆首条沙漠高速公路G216线开工建设">新疆首条沙漠高速公路G216线开工建设</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4cef29c101c6.jsp" class="toContentMain" title="GigOptix获得75万美元100G DWDM采购订单">GigOptix获得75万美元100G DWDM采购订单</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4cedf61901c4.jsp" class="toContentMain" title="晋：统一经销 冀：统一采购 山西焦炭抱团出省">晋：统一经销 冀：统一采购 山西焦炭抱团出省</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4cd08be50165.jsp" class="toContentMain" title="市场上两成节能灯不达标 飞利浦照明上黑榜">市场上两成节能灯不达标 飞利浦照明上黑榜</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4cc9e71b0160.jsp" class="toContentMain" title="台湾采购团长：两岸工程机械合作没有上限">台湾采购团长：两岸工程机械合作没有上限</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4cc73a670156.jsp" class="toContentMain" title="联合国采购或起示范作用 中国疫苗出口之日可期">联合国采购或起示范作用 中国疫苗出口之日可期</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4cbf73440154.jsp" class="toContentMain" title="劳志杰：优质供应商的前期介入可节约采购成本">劳志杰：优质供应商的前期介入可节约采购成本</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4cbcfedb0152.jsp" class="toContentMain" title="8人小作坊进超市采购清单 食品安全检测沦为形式">8人小作坊进超市采购清单 食品安全检测沦为形式</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_62.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_64.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：63/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
