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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4cb75be90145.jsp" class="toContentMain" title="重庆药品采购节首日订单超500万元">重庆药品采购节首日订单超500万元</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4cb2bc070133.jsp" class="toContentMain" title="今年“灯配展”主推LED">今年“灯配展”主推LED</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4cb12f510123.jsp" class="toContentMain" title="备耕有创意，按方采购、团购受青睐">备耕有创意，按方采购、团购受青睐</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4cae234f0121.jsp" class="toContentMain" title="2011年税务局系统公车政府采购项目于近日开标">2011年税务局系统公车政府采购项目于近日开标</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4cab25c2011d.jsp" class="toContentMain" title="家装采购如何抉择？消费者更青睐老字号">家装采购如何抉择？消费者更青睐老字号</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4c9f8d3d00fe.jsp" class="toContentMain" title="政府采购将向中小企业倾斜">政府采购将向中小企业倾斜</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4c9c61df00f2.jsp" class="toContentMain" title="银价飙升 冶炼企业加大采购销售力度">银价飙升 冶炼企业加大采购销售力度</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4c99da2100ee.jsp" class="toContentMain" title="荣成政府采购拒绝“黑名单”供应商">荣成政府采购拒绝“黑名单”供应商</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4c98623900e6.jsp" class="toContentMain" title="首季全市政府采购节约资金1.58亿元">首季全市政府采购节约资金1.58亿元</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4c93286b00d1.jsp" class="toContentMain" title="内地服装客深圳采购忙">内地服装客深圳采购忙</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4c8f931900c5.jsp" class="toContentMain" title="4月13日豆粕市场预测及采购建议">4月13日豆粕市场预测及采购建议</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4c8e117800c1.jsp" class="toContentMain" title="茶叶企业对茶加盟商商圈范围的保护">茶叶企业对茶加盟商商圈范围的保护</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4c8a762e00b5.jsp" class="toContentMain" title="石台茶叶节将于4月18日开幕">石台茶叶节将于4月18日开幕</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4c81d0720085.jsp" class="toContentMain" title="成本增加茶叶价涨 “疯狂的茶叶”是否上演？">成本增加茶叶价涨 “疯狂的茶叶”是否上演？</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f423598413c5c.jsp" class="toContentMain" title="如何不让整体衣柜“辐射”甲醛?">如何不让整体衣柜“辐射”甲醛?</a></td>
          <td style="text-align:right">2011-04-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f422f73e23c12.jsp" class="toContentMain" title="生态产品将主导未来整体衣柜市场">生态产品将主导未来整体衣柜市场</a></td>
          <td style="text-align:right">2011-04-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f422d68313bf3.jsp" class="toContentMain" title="衣柜行业l两级分化 高价品牌消费者“吃不消”?">衣柜行业l两级分化 高价品牌消费者“吃不消”?</a></td>
          <td style="text-align:right">2011-04-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f3e404e082f27.jsp" class="toContentMain" title="赞比亚灌溉发展和支持项目">赞比亚灌溉发展和支持项目</a></td>
          <td style="text-align:right">2011-04-10</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f20b4b0471fe1.jsp" class="toContentMain" title="福州马尾“十二五”规划：将投资百亿元建“十大工程”">福州马尾“十二五”规划：将投资百亿元建“十大工程”</a></td>
          <td style="text-align:right">2011-04-04</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f04f7fd012f20b37e7a1fdf.jsp" class="toContentMain" title="商务部预计未来5年电子商务交易额将保持在年增20%">商务部预计未来5年电子商务交易额将保持在年增20%</a></td>
          <td style="text-align:right">2011-04-04</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_63.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_65.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：64/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
