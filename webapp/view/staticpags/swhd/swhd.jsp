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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=swhd&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
	});
	$("#startTime").epsDatepicker();
     $("#endTime").epsDatepicker();
})
</script>
 <div id="conTitle">
 	<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/smallscale/business/shangquan.jsp" title="陶朱公商圈" class="cmsHref_self">陶朱公商圈</a>
	<a href="javascript:void(0)" id="/view/staticpags/swhd/swhd.jsp" title="商务活动" class="cmsHref_self">商务活动</a>
		
 	</div>
  <div class="formTips attention">
	<ul><li><em>商务活动展示：</em></li></ul>
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
 <input type="hidden"  id="channelId" value="ff8080812ed7f020012ee0cb591209ab" />
 <table class="frontTableList" id="qualityTable">
      <thead>
        <tr>
          <th class="left">标题</th>
          <th class="center">时间</th>
        </tr>
      </thead>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/swhd/ff8080812f588033012f58d001be0031.jsp" class="toContentMain" title="2011年度中国民营医院暨医疗设备企业发展论坛">2011年度中国民营医院暨医疗设备企业发展论坛</a></td>
          <td style="text-align:right">2011-04-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/swhd/ff8080812eec2137012f0123b1843187.jsp" class="toContentMain" title="第五届中国最具竞争力的机构十强评选">第五届中国最具竞争力的机构十强评选</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/swhd/ff8080812eec2137012f0117b88f3165.jsp" class="toContentMain" title="中国招投标行业30年经典项目评选">中国招投标行业30年经典项目评选</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/swhd/ff8080812eec2137012f010c30753114.jsp" class="toContentMain" title="关于举办2011第一届LED产品招标采购评价推介的通知">关于举办2011第一届LED产品招标采购评价推介的通知</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/swhd/ff8080812eec2137012eed4152e0003b.jsp" class="toContentMain" title="2011全国招投标领域年度聚焦">2011全国招投标领域年度聚焦</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/swhd/ff8080812eec2137012eed3c789e0038.jsp" class="toContentMain" title="北京地区第一届办公设备企业团购节">北京地区第一届办公设备企业团购节</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
<span class="totalNum">页次：1/1 每页20条 </span>
</div>		
      </div>
      
  </div>
	
