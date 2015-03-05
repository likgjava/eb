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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f588033012f67f339090738.jsp" class="toContentMain" title="企业文化建设与企业文化管理">企业文化建设与企业文化管理</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f588033012f67eed4db0736.jsp" class="toContentMain" title="企业诚信来自哪里?">企业诚信来自哪里?</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f4e78d5012f53168cf00b0f.jsp" class="toContentMain" title="“幸福企业”是什么样的？">“幸福企业”是什么样的？</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f4e78d5012f5315052f0b0d.jsp" class="toContentMain" title="企业文化战略的三大误区（二）">企业文化战略的三大误区（二）</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f4e78d5012f531453f60b0b.jsp" class="toContentMain" title="企业文化战略的三大误区（一）">企业文化战略的三大误区（一）</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f4e78d5012f530abb7d0afe.jsp" class="toContentMain" title="试论孙中山振兴中国商业的经济思想及其演变(6)">试论孙中山振兴中国商业的经济思想及其演变(6)</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f4e78d5012f5308edda0afc.jsp" class="toContentMain" title="试论孙中山振兴中国商业的经济思想及其演变(5)">试论孙中山振兴中国商业的经济思想及其演变(5)</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f4e78d5012f53063cf30afa.jsp" class="toContentMain" title="试论孙中山振兴中国商业的经济思想及其演变(4)">试论孙中山振兴中国商业的经济思想及其演变(4)</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f4e78d5012f5303d00c0af8.jsp" class="toContentMain" title="试论孙中山振兴中国商业的经济思想及其演变(3)">试论孙中山振兴中国商业的经济思想及其演变(3)</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f4e78d5012f530054c90af6.jsp" class="toContentMain" title="试论孙中山振兴中国商业的经济思想及其演变(2)">试论孙中山振兴中国商业的经济思想及其演变(2)</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f4e78d5012f52fdacd20af4.jsp" class="toContentMain" title="试论孙中山振兴中国商业的经济思想及其演变">试论孙中山振兴中国商业的经济思想及其演变</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f437382d13d92.jsp" class="toContentMain" title="曾国藩的中国式管理智慧(4)">曾国藩的中国式管理智慧(4)</a></td>
          <td style="text-align:right">2011-04-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f437317813d90.jsp" class="toContentMain" title="曾国藩的中国式管理智慧(3)">曾国藩的中国式管理智慧(3)</a></td>
          <td style="text-align:right">2011-04-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f437205723d8e.jsp" class="toContentMain" title="曾国藩的中国式管理智慧(2)">曾国藩的中国式管理智慧(2)</a></td>
          <td style="text-align:right">2011-04-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f437108b53d8c.jsp" class="toContentMain" title="曾国藩的中国式管理智慧">曾国藩的中国式管理智慧</a></td>
          <td style="text-align:right">2011-04-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f429927b63d60.jsp" class="toContentMain" title="曾国藩从政为官方略——不能不学的管理哲学">曾国藩从政为官方略——不能不学的管理哲学</a></td>
          <td style="text-align:right">2011-04-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f428621b33d5c.jsp" class="toContentMain" title="“大企业家”曾国藩">“大企业家”曾国藩</a></td>
          <td style="text-align:right">2011-04-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f42702c413cd1.jsp" class="toContentMain" title="曾国藩的管理之道">曾国藩的管理之道</a></td>
          <td style="text-align:right">2011-04-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjxxjh/ff8080812f04f7fd012f426b8b173ccf.jsp" class="toContentMain" title="如果曾国藩来管理我们的企业?！">如果曾国藩来管理我们的企业?！</a></td>
          <td style="text-align:right">2011-04-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/bjdbrw/ff8080812f04f7fd012f42586d2a3cc7.jsp" class="toContentMain" title="“史圣”及古代著名经济学家">“史圣”及古代著名经济学家</a></td>
          <td style="text-align:right">2011-04-11</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/zzbjyctwh/zzbjyctwh.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/zzbjyctwh/zzbjyctwh.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/zzbjyctwh/zzbjyctwh_3.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/zzbjyctwh/zzbjyctwh_10.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：2/10 每页20条 </span>
</div>		
      </div>
      
  </div>
	
