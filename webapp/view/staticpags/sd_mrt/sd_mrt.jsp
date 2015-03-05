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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=sd_mrt&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
	});
	$("#startTime").epsDatepicker();
     $("#endTime").epsDatepicker();
})
</script>
 <div id="conTitle">
 	<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/smallscale/business/shangquan.jsp" title="陶朱公商圈" class="cmsHref_self">陶朱公商圈</a>
	<a href="javascript:void(0)" id="/view/staticpags/sd_mrt/sd_mrt.jsp" title="商道.名人堂" class="cmsHref_self">商道.名人堂</a>
		
 	</div>
  <div class="formTips attention">
	<ul><li><em>商道.名人堂展示：</em></li></ul>
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
 <input type="hidden"  id="channelId" value="ff8080812dd59be5012dda32c9ca035a" />
 <table class="frontTableList" id="qualityTable">
      <thead>
        <tr>
          <th class="left">标题</th>
          <th class="center">时间</th>
        </tr>
      </thead>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812fbfbecc012fd34f40260968.jsp" class="toContentMain" title="香港中文大学经济系教授">香港中文大学经济系教授</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812fbfbecc012fd346c1950944.jsp" class="toContentMain" title="清华大学首批特聘教授">清华大学首批特聘教授</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812fbfbecc012fd341d8ef0932.jsp" class="toContentMain" title="美国波士顿大学终身教授">美国波士顿大学终身教授</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812fbfbecc012fd33bde060918.jsp" class="toContentMain" title="北京大学光华管理学院应用经济学系副教授">北京大学光华管理学院应用经济学系副教授</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812fbfbecc012fd33896da090e.jsp" class="toContentMain" title="美国哈佛大学经济学教授">美国哈佛大学经济学教授</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812fbfbecc012fd333997d0903.jsp" class="toContentMain" title="华人经济学家">华人经济学家</a></td>
          <td style="text-align:right">2011-05-09</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f9cf3c5012fa042530d01fa.jsp" class="toContentMain" title="“欧元之父”">“欧元之父”</a></td>
          <td style="text-align:right">2011-04-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f9cf3c5012fa03c754601be.jsp" class="toContentMain" title="诺贝尔经济学奖获得者">诺贝尔经济学奖获得者</a></td>
          <td style="text-align:right">2011-04-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f9cf3c5012fa037c99501ba.jsp" class="toContentMain" title="最伟大的计量经济学家之一">最伟大的计量经济学家之一</a></td>
          <td style="text-align:right">2011-04-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f9cf3c5012fa034aa9301b8.jsp" class="toContentMain" title="实验经济学之父">实验经济学之父</a></td>
          <td style="text-align:right">2011-04-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f9cf3c5012fa030df9001b6.jsp" class="toContentMain" title="现代行为经济学大师">现代行为经济学大师</a></td>
          <td style="text-align:right">2011-04-29</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f588033012f6cdc5e3a0b76.jsp" class="toContentMain" title="1998年诺贝尔经济学奖得主">1998年诺贝尔经济学奖得主</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f588033012f6cd4317f0b72.jsp" class="toContentMain" title="美联储前主席">美联储前主席</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f588033012f67d992d10732.jsp" class="toContentMain" title="著名经济学家，中国证券理论的奠基人">著名经济学家，中国证券理论的奠基人</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f588033012f67d351b6069b.jsp" class="toContentMain" title="南京大学经济学院院长">南京大学经济学院院长</a></td>
          <td style="text-align:right">2011-04-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f4e78d5012f52eee2010aed.jsp" class="toContentMain" title="能源经济学家">能源经济学家</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f4e78d5012f52eb70110acc.jsp" class="toContentMain" title=""南京大学长江三角洲经济社会发展研究中心"执行主任">"南京大学长江三角洲经济社会发展研究中心"执行主任</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f4e78d5012f52e49b0d0ac0.jsp" class="toContentMain" title="我国著名青年经济学家">我国著名青年经济学家</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f4e78d5012f52db41880a56.jsp" class="toContentMain" title="著名经济学家、中国研究“三农”问题专家">著名经济学家、中国研究“三农”问题专家</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sd_mrt/ff8080812f4e78d5012f52d2e0f909e0.jsp" class="toContentMain" title="瑞银证券中国首席经济学家">瑞银证券中国首席经济学家</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sd_mrt/sd_mrt_2.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sd_mrt/sd_mrt_3.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：1/3 每页20条 </span>
</div>		
      </div>
      
  </div>
	
