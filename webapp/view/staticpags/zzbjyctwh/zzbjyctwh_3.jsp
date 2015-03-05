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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812f04f7fd012f424d2d223cc5.jsp" class="toContentMain" title="“春秋第一相”">“春秋第一相”</a></td>
          <td style="text-align:right">2011-04-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f3555f7492e36.jsp" class="toContentMain" title="孔子的管理思想境界（下）">孔子的管理思想境界（下）</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f355525e92e34.jsp" class="toContentMain" title="孔子的管理思想境界（中）">孔子的管理思想境界（中）</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f355406a22e32.jsp" class="toContentMain" title="孔子的管理思想境界（上）">孔子的管理思想境界（上）</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f344508332e12.jsp" class="toContentMain" title="企业家谈孙子兵法与企业管理(5)">企业家谈孙子兵法与企业管理(5)</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f344431362e10.jsp" class="toContentMain" title="企业家谈孙子兵法与企业管理(4）">企业家谈孙子兵法与企业管理(4）</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f344340d42e0e.jsp" class="toContentMain" title="企业家谈孙子兵法与企业管理(3)">企业家谈孙子兵法与企业管理(3)</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f344243d32e0c.jsp" class="toContentMain" title="企业家谈孙子兵法与企业管理(2)">企业家谈孙子兵法与企业管理(2)</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f344169a22e0a.jsp" class="toContentMain" title="企业家谈孙子兵法与企业管理">企业家谈孙子兵法与企业管理</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f342513ce2d22.jsp" class="toContentMain" title="《孙子兵法》与现代企业战略管理">《孙子兵法》与现代企业战略管理</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f341ae2dc2d1e.jsp" class="toContentMain" title="商家的新圣经——《孙子兵法》">商家的新圣经——《孙子兵法》</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f341716012cfd.jsp" class="toContentMain" title="试论孙子兵法与企业竞争">试论孙子兵法与企业竞争</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f33d7030e2c1a.jsp" class="toContentMain" title="《孙子兵法》中的管理之道：战略选择">《孙子兵法》中的管理之道：战略选择</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f33ce3c372b9e.jsp" class="toContentMain" title="从《孙子兵法》中悟企业管理之道">从《孙子兵法》中悟企业管理之道</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f33c9f9372b97.jsp" class="toContentMain" title="《孙子兵法》与企业管理的几点启示">《孙子兵法》与企业管理的几点启示</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f33c3c5a62b94.jsp" class="toContentMain" title="浅论孙子兵法在企业经营管理中的应用">浅论孙子兵法在企业经营管理中的应用</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f33c138d02b92.jsp" class="toContentMain" title="现代企业经营管理从“孙子兵法”得到的启示">现代企业经营管理从“孙子兵法”得到的启示</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f33bd44e52b90.jsp" class="toContentMain" title="看《孙子兵法》 学企业管理">看《孙子兵法》 学企业管理</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f33b3dc222b8e.jsp" class="toContentMain" title="《孙子兵法》能否应用于企业管理之我见">《孙子兵法》能否应用于企业管理之我见</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f33a2b3552b8a.jsp" class="toContentMain" title="孙子兵法与企业管理（下）">孙子兵法与企业管理（下）</a></td>
          <td style="text-align:right">2011-04-08</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/zzbjyctwh/zzbjyctwh.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/zzbjyctwh/zzbjyctwh_2.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/zzbjyctwh/zzbjyctwh_4.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/zzbjyctwh/zzbjyctwh_10.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：3/10 每页20条 </span>
</div>		
      </div>
      
  </div>
	
