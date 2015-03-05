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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=zhxx&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
	});
	$("#startTime").epsDatepicker();
     $("#endTime").epsDatepicker();
})
</script>
 <div id="conTitle">
 	<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/smallscale/business/shangquan.jsp" title="陶朱公商圈" class="cmsHref_self">陶朱公商圈</a>
	<a href="javascript:void(0)" id="/view/staticpags/zhxx/zhxx.jsp" title="展会信息" class="cmsHref_self">展会信息</a>
		
 	</div>
  <div class="formTips attention">
	<ul><li><em>展会信息展示：</em></li></ul>
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
 <input type="hidden"  id="channelId" value="2c9087f72f048daa012f04f4282f007b" />
 <table class="frontTableList" id="qualityTable">
      <thead>
        <tr>
          <th class="left">标题</th>
          <th class="center">时间</th>
        </tr>
      </thead>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="http://www.expo-china.com/pages/exhi/201103/38067/index.shtml" class="toContentMain" title="2012年法国里昂国际灯饰展览会">2012年法国里昂国际灯饰展览会</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="http://www.expo-china.com/pages/exhi/201103/37965/index.shtml" class="toContentMain" title="2011第17届哈萨克斯坦（秋季）建材展览会">2011第17届哈萨克斯坦（秋季）建材展览会</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="http://www.expo-china.com/pages/exhi/201103/37993/index.shtml" class="toContentMain" title="2011年墨西哥国际建筑与住宅展览会（EXPOCIHAC）">2011年墨西哥国际建筑与住宅展览会（EXPOCIHAC）</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="http://www.expo-china.com/pages/exhi/201103/37970/index.shtml" class="toContentMain" title="2011年第19届亚洲国际电力、能源展览会">2011年第19届亚洲国际电力、能源展览会</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="http://www.expo-china.com/pages/exhi/201102/36503/index.shtml" class="toContentMain" title="2011年巴西国际电子消费品展览会">2011年巴西国际电子消费品展览会</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="http://www.expo-china.com/pages/exhi/201103/37856/index.shtml" class="toContentMain" title="2011中国-东盟工业控制自动化及仪器仪表（越南）展览会">2011中国-东盟工业控制自动化及仪器仪表（越南）展览会</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="http://www.expo-china.com/pages/exhi/201103/37976/index.shtml" class="toContentMain" title="2012年英国国际电梯展览会">2012年英国国际电梯展览会</a></td>
          <td style="text-align:right">2011-03-30</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
<span class="totalNum">页次：1/1 每页20条 </span>
</div>		
      </div>
      
  </div>
	
