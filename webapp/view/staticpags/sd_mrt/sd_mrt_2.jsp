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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=sd_mrt&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
	});
	$("#startTime").epsDatepicker();
     $("#endTime").epsDatepicker();
})
</script>
 <div id="conTitle">
 	<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/smallscale/business/shangquan.jsp" title="陶朱公商圈" class="cmsHref_self">陶朱公商圈</a>
	<a href="javascript:void(0)" id="/view/staticpags/sd_mrt/sd_mrt.jsp" title="商道.名人堂" class="cmsHref_self">商道.名人堂</a>
		
 	</div>
  <div class="formTips attention">
	<ul><li><em>商道.名人堂展示：</em></li></ul>
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
 <input type="hidden"  id="channelId" value="ff8080812dd59be5012dda32c9ca035a" />
 <table class="frontTableList" id="qualityTable">
      <thead>
        <tr>
          <th class="left">标题</th>
          <th class="center">时间</th>
        </tr>
      </thead>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f04f7fd012f4246612f3ca3.jsp" class="toContentMain" title="美国康奈尔大学经济学系终身教授">美国康奈尔大学经济学系终身教授</a></td>
          <td style="text-align:right">2011-04-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f04f7fd012f4240ab073c7d.jsp" class="toContentMain" title="北京视野咨询中心主任">北京视野咨询中心主任</a></td>
          <td style="text-align:right">2011-04-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f04f7fd012f42371bc93c7b.jsp" class="toContentMain" title="世界银行研究部研究员">世界银行研究部研究员</a></td>
          <td style="text-align:right">2011-04-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f04f7fd012f345cf7382e1b.jsp" class="toContentMain" title="华人著名经济学家">华人著名经济学家</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f04f7fd012f2ea511f527c1.jsp" class="toContentMain" title="原中国民主建国会中央委员会主席">原中国民主建国会中央委员会主席</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f04f7fd012f2e9ddd2327bf.jsp" class="toContentMain" title="规模需求理论创始人">规模需求理论创始人</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f04f7fd012f2e98108427b8.jsp" class="toContentMain" title="中宏网首席经济学家">中宏网首席经济学家</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f04f7fd012f2e8893a927a5.jsp" class="toContentMain" title="中国农村经济学家、社会学家、社会活动家">中国农村经济学家、社会学家、社会活动家</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f04f7fd012f2e7df0042792.jsp" class="toContentMain" title="中国当代经济学家、教育学家、人口学家">中国当代经济学家、教育学家、人口学家</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f04f7fd012f28be3467236e.jsp" class="toContentMain" title="北京大学经济学教授、博士生导师">北京大学经济学教授、博士生导师</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f04f7fd012f28b6380b234d.jsp" class="toContentMain" title="中国科学院-清华大学国情研究中心主任">中国科学院-清华大学国情研究中心主任</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f04f7fd012f28b155c1234b.jsp" class="toContentMain" title="香港中文大学讲座教授">香港中文大学讲座教授</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f04f7fd012f28a219232345.jsp" class="toContentMain" title="北京大学光华管理学院前任院长、经济学教授">北京大学光华管理学院前任院长、经济学教授</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f04f7fd012f28954443231b.jsp" class="toContentMain" title="北京大学光华管理学院原院长">北京大学光华管理学院原院长</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f04f7fd012f288bec972303.jsp" class="toContentMain" title="经济学家">经济学家</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812eec2137012f004e91451b99.jsp" class="toContentMain" title="世界银行副行长">世界银行副行长</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812eec2137012f004548be1b88.jsp" class="toContentMain" title="著名经济学家">著名经济学家</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812eec2137012f002d1fb419d2.jsp" class="toContentMain" title="中国社会科学院研究员">中国社会科学院研究员</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812eec2137012effa05cc01998.jsp" class="toContentMain" title="“离诺贝尔奖最近的华人”">“离诺贝尔奖最近的华人”</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812eec2137012eff981b731996.jsp" class="toContentMain" title="香港经济学家">香港经济学家</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sd_mrt/sd_mrt.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sd_mrt/sd_mrt.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sd_mrt/sd_mrt_3.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sd_mrt/sd_mrt_3.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：2/3 每页20条 </span>
</div>		
      </div>
      
  </div>
	
