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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=sqwh&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
	});
	$("#startTime").epsDatepicker();
     $("#endTime").epsDatepicker();
})
</script>
 <div id="conTitle">
 	<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/smallscale/business/shangquan.jsp" title="陶朱公商圈" class="cmsHref_self">陶朱公商圈</a>
	<a href="javascript:void(0)" id="/view/staticpags/sqwh/sqwh.jsp" title="商圈文化" class="cmsHref_self">商圈文化</a>
		
 	</div>
  <div class="formTips attention">
	<ul><li><em>商圈文化展示：</em></li></ul>
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
 <input type="hidden"  id="channelId" value="ff8080812dd59be5012dda30f3900352" />
 <table class="frontTableList" id="qualityTable">
      <thead>
        <tr>
          <th class="left">标题</th>
          <th class="center">时间</th>
        </tr>
      </thead>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqwh/ff8080812f04f7fd012f1aa943f91d00.jsp" class="toContentMain" title="向一代商圣学中国经营智慧">向一代商圣学中国经营智慧</a></td>
          <td style="text-align:right">2011-04-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqwh/ff8080812e4689bf012e46b793340007.jsp" class="toContentMain" title="陶朱公五字商训">陶朱公五字商训</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqwh/ff8080812dd59be5012ddaae7fbe0456.jsp" class="toContentMain" title="陶朱公财富观">陶朱公财富观</a></td>
          <td style="text-align:right">2011-01-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqwh/ff8080812dd59be5012ddaa96c6e0454.jsp" class="toContentMain" title="陶朱公理财十二戒">陶朱公理财十二戒</a></td>
          <td style="text-align:right">2011-01-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqwh/ff8080812dd59be5012ddaa7cf870452.jsp" class="toContentMain" title="陶朱公商训十二则">陶朱公商训十二则</a></td>
          <td style="text-align:right">2011-01-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqwh/ff8080812dd59be5012ddaa7646d0450.jsp" class="toContentMain" title="陶朱公生意经">陶朱公生意经</a></td>
          <td style="text-align:right">2011-01-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqwh/ff8080812dd59be5012dda37d88d038c.jsp" class="toContentMain" title="陶朱公传奇之造秤">陶朱公传奇之造秤</a></td>
          <td style="text-align:right">2011-01-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqwh/ff8080812dd59be5012dda37c37b038a.jsp" class="toContentMain" title="范蠡：从政治家到商圣的智慧">范蠡：从政治家到商圣的智慧</a></td>
          <td style="text-align:right">2011-01-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqwh/ff8080812dd59be5012dda378ca20388.jsp" class="toContentMain" title="商圣的传说">商圣的传说</a></td>
          <td style="text-align:right">2011-01-31</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
<span class="totalNum">页次：1/1 每页20条 </span>
</div>		
      </div>
      
  </div>
	
