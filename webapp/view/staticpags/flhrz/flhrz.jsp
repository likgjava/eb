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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=flhrz&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
	});
	$("#startTime").epsDatepicker();
     $("#endTime").epsDatepicker();
})
</script>
 <div id="conTitle">
 	<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/smallscale/business/shangquan.jsp" title="陶朱公商圈" class="cmsHref_self">陶朱公商圈</a>
	<a href="javascript:void(0)" id="/view/staticpags/flhrz/flhrz.jsp" title="陶朱风流后人赞" class="cmsHref_self">陶朱风流后人赞</a>
		
 	</div>
  <div class="formTips attention">
	<ul><li><em>陶朱风流后人赞展示：</em></li></ul>
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
 <input type="hidden"  id="channelId" value="ff8080812dd59be5012dda33eded0362" />
 <table class="frontTableList" id="qualityTable">
      <thead>
        <tr>
          <th class="left">标题</th>
          <th class="center">时间</th>
        </tr>
      </thead>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/flhrz/ff8080812e4689bf012e46e6ed0100cf.jsp" class="toContentMain" title="洞庭用白乐天韵（其一）">洞庭用白乐天韵（其一）</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/flhrz/ff8080812e4689bf012e46e69cd800cd.jsp" class="toContentMain" title="睡起戏笔">睡起戏笔</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/flhrz/ff8080812e4689bf012e46e63e8100cb.jsp" class="toContentMain" title="渔父">渔父</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/flhrz/ff8080812e4689bf012e46e5c70e00c9.jsp" class="toContentMain" title="失题三首（其一）">失题三首（其一）</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/flhrz/ff8080812e4689bf012e46e53ec500c7.jsp" class="toContentMain" title="钓台">钓台</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/flhrz/ff8080812e4689bf012e46e4be0300c5.jsp" class="toContentMain" title="和赵宫保别杭州">和赵宫保别杭州</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/flhrz/ff8080812e4689bf012e46e3a27800c3.jsp" class="toContentMain" title="雪西施">雪西施</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/flhrz/ff8080812e4689bf012e46e282fc00c0.jsp" class="toContentMain" title="悼古">悼古</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/flhrz/ff8080812e4689bf012e46e222b300be.jsp" class="toContentMain" title="春秋战国门？范蠡">春秋战国门？范蠡</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/flhrz/ff8080812e4689bf012e46e1d51400bc.jsp" class="toContentMain" title="经范蠡旧居">经范蠡旧居</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/flhrz/ff8080812e4689bf012e46df805d00ba.jsp" class="toContentMain" title="范蠡">范蠡</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/flhrz/ff8080812e4689bf012e46df2eda00b8.jsp" class="toContentMain" title="吴越怀古">吴越怀古</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/flhrz/ff8080812e4689bf012e46decc3f00b6.jsp" class="toContentMain" title="吴中书事">吴中书事</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/flhrz/ff8080812e4689bf012e46ddf23300b4.jsp" class="toContentMain" title="松江怀古">松江怀古</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/flhrz/ff8080812e4689bf012e46dd4e9900b2.jsp" class="toContentMain" title="陶朱公庙">陶朱公庙</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/flhrz/ff8080812e4689bf012e46d9f76a0098.jsp" class="toContentMain" title="利州南渡">利州南渡</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/flhrz/ff8080812e4689bf012e46d312e30096.jsp" class="toContentMain" title="咏史诗·五湖">咏史诗·五湖</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/flhrz/ff8080812e4689bf012e46c552740053.jsp" class="toContentMain" title="陶朱公庙">陶朱公庙</a></td>
          <td style="text-align:right">2011-02-21</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/flhrz/ff8080812dd59be5012dda41cdf803d3.jsp" class="toContentMain" title="五 湖">五 湖</a></td>
          <td style="text-align:right">2011-01-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/flhrz/ff8080812dd59be5012dda412f4703d1.jsp" class="toContentMain" title="范 蠡">范 蠡</a></td>
          <td style="text-align:right">2011-01-31</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/flhrz/flhrz_2.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/flhrz/flhrz_2.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：1/2 每页20条 </span>
</div>		
      </div>
      
  </div>
	
