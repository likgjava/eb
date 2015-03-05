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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130b726330130ba25f25d01ab.jsp" class="toContentMain" title="4月仪器仪表行业销售产值增长24.35%">4月仪器仪表行业销售产值增长24.35%</a></td>
          <td style="text-align:right">2011-06-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130b726330130ba23d0d201a5.jsp" class="toContentMain" title="纺织服装业：选择中国优势 布局持续成长">纺织服装业：选择中国优势 布局持续成长</a></td>
          <td style="text-align:right">2011-06-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130b726330130ba181a2c0194.jsp" class="toContentMain" title="台湾LED厂5月营收增 Q3将更旺">台湾LED厂5月营收增 Q3将更旺</a></td>
          <td style="text-align:right">2011-06-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130b726330130ba158af90192.jsp" class="toContentMain" title="工程机械园区荥阳开工30多家品牌将入驻">工程机械园区荥阳开工30多家品牌将入驻</a></td>
          <td style="text-align:right">2011-06-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130b726330130ba14e5ad0190.jsp" class="toContentMain" title="汽车黑匣子从“B2B”走向“B2C” 普及成趋势">汽车黑匣子从“B2B”走向“B2C” 普及成趋势</a></td>
          <td style="text-align:right">2011-06-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130b726330130ba05b1d9017a.jsp" class="toContentMain" title="高端白酒价格上升中的市场机会">高端白酒价格上升中的市场机会</a></td>
          <td style="text-align:right">2011-06-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130b726330130ba02e58e0174.jsp" class="toContentMain" title="安溪铁观音重兵出征北方茶叶市场">安溪铁观音重兵出征北方茶叶市场</a></td>
          <td style="text-align:right">2011-06-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130b726330130ba011a1c0172.jsp" class="toContentMain" title="葡萄酒相关行业迎来井喷之势">葡萄酒相关行业迎来井喷之势</a></td>
          <td style="text-align:right">2011-06-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130b726330130ba008148016a.jsp" class="toContentMain" title="券商唱多食品饮料 白酒葡萄酒公司最被看好">券商唱多食品饮料 白酒葡萄酒公司最被看好</a></td>
          <td style="text-align:right">2011-06-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130afdee10130b4f5db960536.jsp" class="toContentMain" title="传统涂料经销商 如何把眼光放得更远些">传统涂料经销商 如何把眼光放得更远些</a></td>
          <td style="text-align:right">2011-06-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130afdee10130b4f1e9d10532.jsp" class="toContentMain" title="电缆电线：光伏电缆亟待大规模国产化">电缆电线：光伏电缆亟待大规模国产化</a></td>
          <td style="text-align:right">2011-06-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130afdee10130b4f0a5990530.jsp" class="toContentMain" title="中国缝制设备：出口印度市场战果如何">中国缝制设备：出口印度市场战果如何</a></td>
          <td style="text-align:right">2011-06-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130afdee10130b4eec4cf052a.jsp" class="toContentMain" title="“靴子”落地后国内奢侈品消费将迎来爆发期">“靴子”落地后国内奢侈品消费将迎来爆发期</a></td>
          <td style="text-align:right">2011-06-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130afdee10130b4ebd9c70528.jsp" class="toContentMain" title="中国家具出口市场受重创 企业回国谋出路">中国家具出口市场受重创 企业回国谋出路</a></td>
          <td style="text-align:right">2011-06-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130afdee10130b4ea7cb90526.jsp" class="toContentMain" title="我国电线电缆业在蓬勃发展中遭遇瓶颈">我国电线电缆业在蓬勃发展中遭遇瓶颈</a></td>
          <td style="text-align:right">2011-06-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130afdee10130b4dd4eda0504.jsp" class="toContentMain" title="美国严控甲醛释放提高三倍 家具出口成本大涨">美国严控甲醛释放提高三倍 家具出口成本大涨</a></td>
          <td style="text-align:right">2011-06-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130afdee10130b4d7d85404f6.jsp" class="toContentMain" title="需求决定市场 个人电脑鏖战移动互联网终端">需求决定市场 个人电脑鏖战移动互联网终端</a></td>
          <td style="text-align:right">2011-06-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130afdee10130b4bfa3f803f0.jsp" class="toContentMain" title="27家钢厂同日下调钢价">27家钢厂同日下调钢价</a></td>
          <td style="text-align:right">2011-06-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130afdee10130b4bdffea03ee.jsp" class="toContentMain" title="政府定价产品下半年或延续涨势">政府定价产品下半年或延续涨势</a></td>
          <td style="text-align:right">2011-06-22</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808130afdee10130b4bd582b03ec.jsp" class="toContentMain" title="日本粗钢减产影响汽车生产">日本粗钢减产影响汽车生产</a></td>
          <td style="text-align:right">2011-06-22</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_34.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_36.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：35/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
