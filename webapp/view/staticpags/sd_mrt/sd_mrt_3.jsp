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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812eec2137012eff8e62ca198d.jsp" class="toContentMain" title="中国最有影响的经济学家之一">中国最有影响的经济学家之一</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812ee243a4012eeb775a711b87.jsp" class="toContentMain" title="世界著名管理哲学家">世界著名管理哲学家</a></td>
          <td style="text-align:right">2011-03-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812e31dec9012e3764276800dd.jsp" class="toContentMain" title="哈佛教授塔尔博士">哈佛教授塔尔博士</a></td>
          <td style="text-align:right">2011-02-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812e31dec9012e375110f000d9.jsp" class="toContentMain" title="俄裔美国作家、哲学家">俄裔美国作家、哲学家</a></td>
          <td style="text-align:right">2011-02-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812dd59be5012dda43749b03db.jsp" class="toContentMain" title="独立学者">独立学者</a></td>
          <td style="text-align:right">2011-01-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812dd59be5012dda4319c903d9.jsp" class="toContentMain" title="企业管理博士">企业管理博士</a></td>
          <td style="text-align:right">2011-01-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812dd59be5012dda42663303d5.jsp" class="toContentMain" title="华夏老人">华夏老人</a></td>
          <td style="text-align:right">2011-01-31</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sd_mrt/sd_mrt.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sd_mrt/sd_mrt_2.jsp" title="上一页">上一页</span> 
<span class="totalNum">页次：3/3 每页20条 </span>
</div>		
      </div>
      
  </div>
	
