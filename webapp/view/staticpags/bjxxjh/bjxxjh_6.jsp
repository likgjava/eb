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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f295c42ea2466.jsp" class="toContentMain" title="现代企业文化和品牌建设">现代企业文化和品牌建设</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f29577e072464.jsp" class="toContentMain" title="家族企业该向徽商学什么？">家族企业该向徽商学什么？</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f28e6d2d923ea.jsp" class="toContentMain" title="家族文化与徽商">家族文化与徽商</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f28dc583c23bf.jsp" class="toContentMain" title="徽商兴衰的文化解读">徽商兴衰的文化解读</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f28da3f96239f.jsp" class="toContentMain" title="徽商文化批判与创新">徽商文化批判与创新</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f28d3ad722393.jsp" class="toContentMain" title="徽商经营理念与商业文化">徽商经营理念与商业文化</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f28cd7d622390.jsp" class="toContentMain" title="徽商文化——徽商兴衰考">徽商文化——徽商兴衰考</a></td>
          <td style="text-align:right">2011-04-06</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f1b2b7cd01d9e.jsp" class="toContentMain" title="红楼梦智慧与科学决策管理（三）">红楼梦智慧与科学决策管理（三）</a></td>
          <td style="text-align:right">2011-04-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f1b2ace761d9c.jsp" class="toContentMain" title="红楼梦智慧与科学决策管理（二）">红楼梦智慧与科学决策管理（二）</a></td>
          <td style="text-align:right">2011-04-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f1b29d8371d9a.jsp" class="toContentMain" title="红楼梦智慧与科学决策管理（一）">红楼梦智慧与科学决策管理（一）</a></td>
          <td style="text-align:right">2011-04-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f1b1e3e231d96.jsp" class="toContentMain" title="韩非子管理智慧的实用性（三）">韩非子管理智慧的实用性（三）</a></td>
          <td style="text-align:right">2011-04-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f1b1d5e1b1d94.jsp" class="toContentMain" title="韩非子管理智慧的实用性（二）">韩非子管理智慧的实用性（二）</a></td>
          <td style="text-align:right">2011-04-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f1b1c922c1d92.jsp" class="toContentMain" title="韩非子管理智慧的实用性（一）">韩非子管理智慧的实用性（一）</a></td>
          <td style="text-align:right">2011-04-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f1b0afbee1d36.jsp" class="toContentMain" title="孔子的“四毋”与企业家修炼">孔子的“四毋”与企业家修炼</a></td>
          <td style="text-align:right">2011-04-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f1b0907cf1d34.jsp" class="toContentMain" title="《论语》管理解读（下）">《论语》管理解读（下）</a></td>
          <td style="text-align:right">2011-04-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f1b07a6d31d32.jsp" class="toContentMain" title="《论语》管理解读（上）">《论语》管理解读（上）</a></td>
          <td style="text-align:right">2011-04-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f1adc32921d30.jsp" class="toContentMain" title="法家智慧的企业管理应用（下）">法家智慧的企业管理应用（下）</a></td>
          <td style="text-align:right">2011-04-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f1adb836c1d2e.jsp" class="toContentMain" title="法家智慧的企业管理应用（中）">法家智慧的企业管理应用（中）</a></td>
          <td style="text-align:right">2011-04-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f1ada94461d2c.jsp" class="toContentMain" title="法家智慧的企业管理应用（上）">法家智慧的企业管理应用（上）</a></td>
          <td style="text-align:right">2011-04-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f1ad045131d0a.jsp" class="toContentMain" title="《道德经》中的管理之学：宠辱不惊（二）">《道德经》中的管理之学：宠辱不惊（二）</a></td>
          <td style="text-align:right">2011-04-03</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/bjxxjh/bjxxjh.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/bjxxjh/bjxxjh_5.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/bjxxjh/bjxxjh_7.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/bjxxjh/bjxxjh_9.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：6/9 每页20条 </span>
</div>		
      </div>
      
  </div>
	
