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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f330631b82ace.jsp" class="toContentMain" title="孙子兵法与企业管理（4）">孙子兵法与企业管理（4）</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f3303f56f2ac5.jsp" class="toContentMain" title="孙子兵法与企业管理（3）">孙子兵法与企业管理（3）</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f3301b0372ac3.jsp" class="toContentMain" title="孙子兵法与企业管理（2）">孙子兵法与企业管理（2）</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f32fe79152abf.jsp" class="toContentMain" title="孙子兵法与企业管理">孙子兵法与企业管理</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f32f5b6e92abd.jsp" class="toContentMain" title="从《孙子兵法》解读企业管理者的才能">从《孙子兵法》解读企业管理者的才能</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f32efaa262abb.jsp" class="toContentMain" title="孙子兵法与企业管理中的真谛之三">孙子兵法与企业管理中的真谛之三</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f32ed6dea2ab9.jsp" class="toContentMain" title="孙子兵法与企业管理中的真谛之二">孙子兵法与企业管理中的真谛之二</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f32eab07d2ab7.jsp" class="toContentMain" title="孙子兵法与企业管理中的真谛之一">孙子兵法与企业管理中的真谛之一</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f32e14d8a2ab5.jsp" class="toContentMain" title="“孙子兵法”在企业管理中的应用">“孙子兵法”在企业管理中的应用</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f32d88b482ab3.jsp" class="toContentMain" title="《孙子兵法》的企业管理启示">《孙子兵法》的企业管理启示</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f32d4701f2ab1.jsp" class="toContentMain" title="《孙子兵法》之企业管理有道">《孙子兵法》之企业管理有道</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f32d09c7a2aaf.jsp" class="toContentMain" title="《孙子兵法》与企业管理(2)">《孙子兵法》与企业管理(2)</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f2efc032428d5.jsp" class="toContentMain" title="现代企业也应施行“自然无为”式管理——从老子“虚”与“静”思想所想到的">现代企业也应施行“自然无为”式管理——从老子“虚”与“静”思想所想到的</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f2ef799c928d3.jsp" class="toContentMain" title="迷途的管理者，老子、孔子、诸子谁能救企业？">迷途的管理者，老子、孔子、诸子谁能救企业？</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f2ef430b428d1.jsp" class="toContentMain" title="领导的风范——老子的管理学">领导的风范——老子的管理学</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f2ee213ed28cf.jsp" class="toContentMain" title="现代企业人力资源管理“道”与“德”------老子《道德经》管理思想启示之一">现代企业人力资源管理“道”与“德”------老子《道德经》管理思想启示之一</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f2ed8bebe28ca.jsp" class="toContentMain" title="老子的人力资源管理黄金法则">老子的人力资源管理黄金法则</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f2ed497eb28c5.jsp" class="toContentMain" title="老子的人生“四不”与企业管理之道">老子的人生“四不”与企业管理之道</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f2ed145fd28be.jsp" class="toContentMain" title="老子的人生三宝与企业管理">老子的人生三宝与企业管理</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f2ecc73cd28b7.jsp" class="toContentMain" title="老子云：“上善若水”，试从企业管理的角度论述这句话的现实意义">老子云：“上善若水”，试从企业管理的角度论述这句话的现实意义</a></td>
          <td style="text-align:right">2011-04-07</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/bjxxjh/bjxxjh.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/bjxxjh/bjxxjh_3.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/bjxxjh/bjxxjh_5.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/bjxxjh/bjxxjh_9.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：4/9 每页20条 </span>
</div>		
      </div>
      
  </div>
	
