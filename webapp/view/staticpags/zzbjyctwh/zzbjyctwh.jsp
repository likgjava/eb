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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812fbfbecc012fd2dbe608084f.jsp" class="toContentMain" title="企业与社会价值体系亟待重塑">企业与社会价值体系亟待重塑</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812fbfbecc012fd2d9adc8084d.jsp" class="toContentMain" title="企业的制度文化必须有专人管理">企业的制度文化必须有专人管理</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812fbfbecc012fd2d7feb9083f.jsp" class="toContentMain" title="电子商务时代企业文化的重塑探讨">电子商务时代企业文化的重塑探讨</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812fbfbecc012fd2d59396081d.jsp" class="toContentMain" title="管理员工三种力量：制度、管理、企业文化">管理员工三种力量：制度、管理、企业文化</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812fbfbecc012fd2d3883a0811.jsp" class="toContentMain" title="企业文化的自发、自觉与自强">企业文化的自发、自觉与自强</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812fbfbecc012fd2d0adfe080d.jsp" class="toContentMain" title="企业文化与员工行为塑造有何关系？">企业文化与员工行为塑造有何关系？</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812fbfbecc012fd2ce2e8b0809.jsp" class="toContentMain" title="中华传统文化与企业文化建设">中华传统文化与企业文化建设</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812fbfbecc012fd2cc478d07ff.jsp" class="toContentMain" title="浅谈如何将精益融入企业文化">浅谈如何将精益融入企业文化</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812fbfbecc012fd2c9f8ac07fd.jsp" class="toContentMain" title="EAP，企业员工的精神按摩法">EAP，企业员工的精神按摩法</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812fbfbecc012fd2c7a8c107f9.jsp" class="toContentMain" title="孙兵企业文化管理论语（一）">孙兵企业文化管理论语（一）</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f9cf3c5012fa0115d890167.jsp" class="toContentMain" title="企业价值观：当品牌忠诚被超越">企业价值观：当品牌忠诚被超越</a></td>
          <td style="text-align:right">2011-04-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f9cf3c5012fa00f6ba50165.jsp" class="toContentMain" title="做了总比不做强——对抗消极的企业文化">做了总比不做强——对抗消极的企业文化</a></td>
          <td style="text-align:right">2011-04-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f9cf3c5012fa00d231c015c.jsp" class="toContentMain" title="做了总比不做强——对抗消极的企业文化">做了总比不做强——对抗消极的企业文化</a></td>
          <td style="text-align:right">2011-04-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f9cf3c5012fa00b76740158.jsp" class="toContentMain" title="破解扩张与衰落悖论 基业常青须先让伯乐易存">破解扩张与衰落悖论 基业常青须先让伯乐易存</a></td>
          <td style="text-align:right">2011-04-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f9cf3c5012fa0098c610155.jsp" class="toContentMain" title="从“让子弹飞”看团队文化">从“让子弹飞”看团队文化</a></td>
          <td style="text-align:right">2011-04-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f9cf3c5012fa0075bf70118.jsp" class="toContentMain" title="“有用”是我们一切的出发点和归宿点">“有用”是我们一切的出发点和归宿点</a></td>
          <td style="text-align:right">2011-04-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f9cf3c5012fa004e32100f6.jsp" class="toContentMain" title="行我所信，信我所行——同心动力企业文化建设与管理解读">行我所信，信我所行——同心动力企业文化建设与管理解读</a></td>
          <td style="text-align:right">2011-04-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f9cf3c5012fa001cd8100f3.jsp" class="toContentMain" title="卓识-----清晰的持续的认识">卓识-----清晰的持续的认识</a></td>
          <td style="text-align:right">2011-04-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f9cf3c5012f9ffc9ba000ef.jsp" class="toContentMain" title="文化“深植”比“落地”更重要">文化“深植”比“落地”更重要</a></td>
          <td style="text-align:right">2011-04-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f9cf3c5012f9ffaf83100ed.jsp" class="toContentMain" title="企业文化是科学解读出来的">企业文化是科学解读出来的</a></td>
          <td style="text-align:right">2011-04-29</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/zzbjyctwh/zzbjyctwh_2.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/zzbjyctwh/zzbjyctwh_10.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：1/10 每页20条 </span>
</div>		
      </div>
      
  </div>
	
