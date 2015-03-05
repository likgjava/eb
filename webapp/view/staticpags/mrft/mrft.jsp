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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=mrft&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
	});
	$("#startTime").epsDatepicker();
     $("#endTime").epsDatepicker();
})
</script>
 <div id="conTitle">
 	<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/smallscale/business/shangquan.jsp" title="陶朱公商圈" class="cmsHref_self">陶朱公商圈</a>
	<a href="javascript:void(0)" id="/view/staticpags/mrft/mrft.jsp" title="名人访谈" class="cmsHref_self">名人访谈</a>
		
 	</div>
  <div class="formTips attention">
	<ul><li><em>名人访谈展示：</em></li></ul>
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
 <input type="hidden"  id="channelId" value="ff8080812dd59be5012dda336f2d035e" />
 <table class="frontTableList" id="qualityTable">
      <thead>
        <tr>
          <th class="left">标题</th>
          <th class="center">时间</th>
        </tr>
      </thead>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/mrft/ff8080812f919712012f9571a9270108.jsp" class="toContentMain" title="当病魔缠上企业家">当病魔缠上企业家</a></td>
          <td style="text-align:right">2011-04-27</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/mrft/ff8080812f588033012f6cb010950b50.jsp" class="toContentMain" title="马宏：清洁科技助力酒店业跳出红海走向蓝洋">马宏：清洁科技助力酒店业跳出红海走向蓝洋</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/mrft/ff8080812f588033012f6ca8adcb0b40.jsp" class="toContentMain" title="罗杰斯:商品牛市或将延续至2018年">罗杰斯:商品牛市或将延续至2018年</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/mrft/ff8080812f588033012f6ca580dd0b3c.jsp" class="toContentMain" title="马云：过去一个多月我很痛苦、很纠结、很愤怒">马云：过去一个多月我很痛苦、很纠结、很愤怒</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/mrft/ff8080812f588033012f6c8d94ea0b2d.jsp" class="toContentMain" title="世界时装大师皮尔-卡丹新浪聊天实录">世界时装大师皮尔-卡丹新浪聊天实录</a></td>
          <td style="text-align:right">2011-04-19</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/mrft/ff8080812f04f7fd012f0eaa3dfa1564.jsp" class="toContentMain" title="为什么超人是李嘉诚？（中）">为什么超人是李嘉诚？（中）</a></td>
          <td style="text-align:right">2011-04-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/mrft/ff8080812f04f7fd012f09d1ae040f78.jsp" class="toContentMain" title="派亿玛总裁柯细兴：未来电商是后端供应链和品牌影响力的较量">派亿玛总裁柯细兴：未来电商是后端供应链和品牌影响力的较量</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/mrft/ff8080812f04f7fd012f09cb43980f70.jsp" class="toContentMain" title="王峻涛访谈录  电子商务是什么？">王峻涛访谈录  电子商务是什么？</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/mrft/ff8080812f04f7fd012f09c6f4b30f65.jsp" class="toContentMain" title="访恰恰公司陈先保董事长">访恰恰公司陈先保董事长</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/mrft/ff8080812f04f7fd012f09c079180f63.jsp" class="toContentMain" title="尹同耀：挣外国人钱最爽">尹同耀：挣外国人钱最爽</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/mrft/ff8080812f04f7fd012f09b8feb80f61.jsp" class="toContentMain" title="访长江实业主席李嘉诚">访长江实业主席李嘉诚</a></td>
          <td style="text-align:right">2011-03-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/mrft/ff8080812e31dec9012e37a4029e017a.jsp" class="toContentMain" title="责任、共赢、未来">责任、共赢、未来</a></td>
          <td style="text-align:right">2011-02-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/mrft/ff8080812e31dec9012e37a01e110178.jsp" class="toContentMain" title="自由学者王东岳">自由学者王东岳</a></td>
          <td style="text-align:right">2011-02-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/mrft/ff8080812e31dec9012e379081dd016a.jsp" class="toContentMain" title="改变人生最重要的120个定律(精选集)">改变人生最重要的120个定律(精选集)</a></td>
          <td style="text-align:right">2011-02-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/mrft/ff8080812e31dec9012e37879bc6014e.jsp" class="toContentMain" title="塔尔·宾－夏哈尔博士和《哈佛幸福课》">塔尔·宾－夏哈尔博士和《哈佛幸福课》</a></td>
          <td style="text-align:right">2011-02-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/mrft/ff8080812e31dec9012e37843e79011b.jsp" class="toContentMain" title="安.兰德和她的《商人为什么需要哲学》">安.兰德和她的《商人为什么需要哲学》</a></td>
          <td style="text-align:right">2011-02-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/mrft/ff8080812e31dec9012e377ca8850119.jsp" class="toContentMain" title="集聚供求资源 打造阳光采购（下）">集聚供求资源 打造阳光采购（下）</a></td>
          <td style="text-align:right">2011-02-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/mrft/ff8080812e31dec9012e377bb2380117.jsp" class="toContentMain" title="集聚供求资源 打造阳光采购（上）">集聚供求资源 打造阳光采购（上）</a></td>
          <td style="text-align:right">2011-02-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/mrft/ff8080812dd59be5012dda44237a03df.jsp" class="toContentMain" title="为什么“超人”是李嘉诚？（上）">为什么“超人”是李嘉诚？（上）</a></td>
          <td style="text-align:right">2011-01-31</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/mrft/ff8080812dd59be5012dda44012203dd.jsp" class="toContentMain" title="为什么“超人”是李嘉诚？（下）">为什么“超人”是李嘉诚？（下）</a></td>
          <td style="text-align:right">2011-01-31</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
<span class="totalNum">页次：1/1 每页20条 </span>
</div>		
      </div>
      
  </div>
	
