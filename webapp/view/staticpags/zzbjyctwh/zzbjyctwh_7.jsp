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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=zzbjyctwh&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
	});
	$("#startTime").epsDatepicker();
     $("#endTime").epsDatepicker();
})
</script>
 <div id="conTitle">
 	<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/smallscale/business/shangquan.jsp" title="陶朱公商圈" class="cmsHref_self">陶朱公商圈</a>
	<a href="javascript:void(0)" id="/view/staticpags/zzbjyctwh/zzbjyctwh.jsp" title="诸子百家与传统文化" class="cmsHref_self">诸子百家与传统文化</a>
		
 	</div>
  <div class="formTips attention">
	<ul><li><em>诸子百家与传统文化展示：</em></li></ul>
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
 <input type="hidden"  id="channelId" value="ff8080812ed7f020012ee0c98e670995" />
 <table class="frontTableList" id="qualityTable">
      <thead>
        <tr>
          <th class="left">标题</th>
          <th class="center">时间</th>
        </tr>
      </thead>
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
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f1acedf871d08.jsp" class="toContentMain" title="《道德经》中的管理之学：宠辱不惊（一）">《道德经》中的管理之学：宠辱不惊（一）</a></td>
          <td style="text-align:right">2011-04-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f1aaea4591d02.jsp" class="toContentMain" title="老子的人生“四不”与企业管理之道">老子的人生“四不”与企业管理之道</a></td>
          <td style="text-align:right">2011-04-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f1aa096c21cfe.jsp" class="toContentMain" title="曾国藩的管理之道">曾国藩的管理之道</a></td>
          <td style="text-align:right">2011-04-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f1a9c30fd1ce1.jsp" class="toContentMain" title="圣哲曾国藩做人做事箴言">圣哲曾国藩做人做事箴言</a></td>
          <td style="text-align:right">2011-04-03</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f0f326294165e.jsp" class="toContentMain" title="从十大自相矛盾之处求解潮商之谜　">从十大自相矛盾之处求解潮商之谜　</a></td>
          <td style="text-align:right">2011-04-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f0f2a6b65165c.jsp" class="toContentMain" title="潮商精神与海洋文化">潮商精神与海洋文化</a></td>
          <td style="text-align:right">2011-04-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f0f288033165a.jsp" class="toContentMain" title="传承“潮商精神” 反哺潮汕家乡">传承“潮商精神” 反哺潮汕家乡</a></td>
          <td style="text-align:right">2011-04-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f0f2736161658.jsp" class="toContentMain" title="浅谈潮商文化性格">浅谈潮商文化性格</a></td>
          <td style="text-align:right">2011-04-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f0f213fab1656.jsp" class="toContentMain" title="潮商商业文化之“精、拼、义”">潮商商业文化之“精、拼、义”</a></td>
          <td style="text-align:right">2011-04-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f0f1c51c11653.jsp" class="toContentMain" title="漫谈潮商文化性格的海洋特质">漫谈潮商文化性格的海洋特质</a></td>
          <td style="text-align:right">2011-04-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012f009d036126f2.jsp" class="toContentMain" title="潮商文化精神内涵的诠释">潮商文化精神内涵的诠释</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812eec2137012eff76d34f1985.jsp" class="toContentMain" title="秦朝丞相">秦朝丞相</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812eec2137012eff6fa8c21983.jsp" class="toContentMain" title="先秦法家代表人物">先秦法家代表人物</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012eff66c3a1197c.jsp" class="toContentMain" title="甬商风采——近代甬商特有的精神素质">甬商风采——近代甬商特有的精神素质</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012eff4c06e21970.jsp" class="toContentMain" title="甬商与馓馓商、普商价值观研究">甬商与馓馓商、普商价值观研究</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012eff462d861967.jsp" class="toContentMain" title="山海交臂一甬商 红砖门楼背后的叹息与思索">山海交臂一甬商 红砖门楼背后的叹息与思索</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812eec2137012eff40c3e61965.jsp" class="toContentMain" title="甬商的人文特质">甬商的人文特质</a></td>
          <td style="text-align:right">2011-03-29</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/zzbjyctwh/zzbjyctwh.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/zzbjyctwh/zzbjyctwh_6.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/zzbjyctwh/zzbjyctwh_8.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/zzbjyctwh/zzbjyctwh_10.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：7/10 每页20条 </span>
</div>		
      </div>
      
  </div>
	
