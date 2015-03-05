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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=bjxxjh&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
	});
	$("#startTime").epsDatepicker();
     $("#endTime").epsDatepicker();
})
</script>
 <div id="conTitle">
 	<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/staticpags/zzbjyctwh/zzbjyctwh.jsp" title="诸子百家与传统文化" class="cmsHref_self">诸子百家与传统文化</a>
	<a href="javascript:void(0)" id="/view/staticpags/bjxxjh/bjxxjh.jsp" title="百家思想精华" class="cmsHref_self">百家思想精华</a>
		
 	</div>
  <div class="formTips attention">
	<ul><li><em>百家思想精华展示：</em></li></ul>
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
 <input type="hidden"  id="channelId" value="ff8080812ed7f020012ee0caa13d09a3" />
 <table class="frontTableList" id="qualityTable">
      <thead>
        <tr>
          <th class="left">标题</th>
          <th class="center">时间</th>
        </tr>
      </thead>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f2e725e952790.jsp" class="toContentMain" title="老子的管理哲学解读">老子的管理哲学解读</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f2de2b36426dd.jsp" class="toContentMain" title="老子管理思想的核心">老子管理思想的核心</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f2de0e56e26db.jsp" class="toContentMain" title="向“老子”学管理">向“老子”学管理</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f2ddac8ba26d9.jsp" class="toContentMain" title="老子的不争哲学与企业经营管理">老子的不争哲学与企业经营管理</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f2dd3a67526d7.jsp" class="toContentMain" title="《老子》管理思想管窥">《老子》管理思想管窥</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f2dcf1a8c26d3.jsp" class="toContentMain" title="老子与管理思想">老子与管理思想</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f2dc9eeda26cd.jsp" class="toContentMain" title="谈老子管理四层次">谈老子管理四层次</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f2db29ab826b3.jsp" class="toContentMain" title="老子的思想与现代管理哲学的联系">老子的思想与现代管理哲学的联系</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f2da3d56426a3.jsp" class="toContentMain" title="老子与现代企业管理">老子与现代企业管理</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f2d96771a269f.jsp" class="toContentMain" title="学点老子的和谐管理">学点老子的和谐管理</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f2d93714b269d.jsp" class="toContentMain" title="老子：管理的最高境界！">老子：管理的最高境界！</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f2a08559e2532.jsp" class="toContentMain" title="经济与文化互动———徽商兴衰的一个重要启示">经济与文化互动———徽商兴衰的一个重要启示</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f29e590dc252d.jsp" class="toContentMain" title="徽商精神与徽州文化">徽商精神与徽州文化</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f29e30703252b.jsp" class="toContentMain" title="谈儒贾徽商的思想特色,儒家文化">谈儒贾徽商的思想特色,儒家文化</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f2984961a24ab.jsp" class="toContentMain" title="徽商文化与新徽商的崛起">徽商文化与新徽商的崛起</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f297e8a5c24a9.jsp" class="toContentMain" title="从经营的心理角度探寻徽商崛起之原因">从经营的心理角度探寻徽商崛起之原因</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f297c1a5024a7.jsp" class="toContentMain" title="明清徽商经营长江流域商业的现代解读">明清徽商经营长江流域商业的现代解读</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f297408a02485.jsp" class="toContentMain" title="论徽商经营文化">论徽商经营文化</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f2961b414246a.jsp" class="toContentMain" title="徽商的兴盛与徽文化的作用">徽商的兴盛与徽文化的作用</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f295fb0922468.jsp" class="toContentMain" title="徽商会馆的文化价值">徽商会馆的文化价值</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/bjxxjh/bjxxjh.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/bjxxjh/bjxxjh_4.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/bjxxjh/bjxxjh_6.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/bjxxjh/bjxxjh_9.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：5/9 每页20条 </span>
</div>		
      </div>
      
  </div>
	
