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
		$("#conBody").loadPage($('#initPath').val()+'/CmsNewsController.do?method=search&chnlCodes=sqzx&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&searchResultForm=search_news_body_result.ftl');
	});
	$("#startTime").epsDatepicker();
     $("#endTime").epsDatepicker();
})
</script>
 <div id="conTitle">
 	<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/smallscale/business/shangquan.jsp" title="陶朱公商圈" class="cmsHref_self">陶朱公商圈</a>
	<a href="javascript:void(0)" id="/view/staticpags/sqzx/sqzx.jsp" title="商圈资讯" class="cmsHref_self">商圈资讯</a>
		
 	</div>
  <div class="formTips attention">
	<ul><li><em>商圈资讯展示：</em></li></ul>
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
 <input type="hidden"  id="channelId" value="ff8080812dd59be5012dda319fa70356" />
 <table class="frontTableList" id="qualityTable">
      <thead>
        <tr>
          <th class="left">标题</th>
          <th class="center">时间</th>
        </tr>
      </thead>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813225280f013227c539c4003d.jsp" class="toContentMain" title="倒闭潮来袭 陶瓷行业如何寻求避风港">倒闭潮来袭 陶瓷行业如何寻求避风港</a></td>
          <td style="text-align:right">2011-09-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813225280f013227c2e960003c.jsp" class="toContentMain" title="“十二五”期间焦化业仪器自动化将投资约35亿元">“十二五”期间焦化业仪器自动化将投资约35亿元</a></td>
          <td style="text-align:right">2011-09-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813225280f013227c25390003b.jsp" class="toContentMain" title="原材料涨势不停 2011地板价格走势几何？">原材料涨势不停 2011地板价格走势几何？</a></td>
          <td style="text-align:right">2011-09-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813225280f013227c0ddb7003a.jsp" class="toContentMain" title="太阳能行业乱象丛生 太阳能美企连续倒闭">太阳能行业乱象丛生 太阳能美企连续倒闭</a></td>
          <td style="text-align:right">2011-09-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813225280f013227bf6efc0039.jsp" class="toContentMain" title="轴承行业：民企整合产业资源大势所趋">轴承行业：民企整合产业资源大势所趋</a></td>
          <td style="text-align:right">2011-09-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813225280f013227be37420038.jsp" class="toContentMain" title="白炽灯5年后“下岗” 为节能灯发展“让路”">白炽灯5年后“下岗” 为节能灯发展“让路”</a></td>
          <td style="text-align:right">2011-09-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813225280f013227bc50ab0037.jsp" class="toContentMain" title="五金建材破解市场危机 提高附加值成首选">五金建材破解市场危机 提高附加值成首选</a></td>
          <td style="text-align:right">2011-09-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813225280f013227bbbc100036.jsp" class="toContentMain" title="平板计算机电子书 半导体芯片商机">平板计算机电子书 半导体芯片商机</a></td>
          <td style="text-align:right">2011-09-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813225280f013227b4f62b0035.jsp" class="toContentMain" title="工程机械行业发展关注龙头和潜力小公司">工程机械行业发展关注龙头和潜力小公司</a></td>
          <td style="text-align:right">2011-09-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813225280f013227b462e00034.jsp" class="toContentMain" title="涂料“五大现状”成经销商盈利致命伤">涂料“五大现状”成经销商盈利致命伤</a></td>
          <td style="text-align:right">2011-09-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813225280f013227b267720032.jsp" class="toContentMain" title="空气能热水器规范9月审定 明年或出台">空气能热水器规范9月审定 明年或出台</a></td>
          <td style="text-align:right">2011-09-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813225280f013227b1000a0030.jsp" class="toContentMain" title="双节临近 高端白酒悄然提价百元">双节临近 高端白酒悄然提价百元</a></td>
          <td style="text-align:right">2011-09-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813225280f013227a9f778002c.jsp" class="toContentMain" title="十二五可再生能源比重目标为9.5%">十二五可再生能源比重目标为9.5%</a></td>
          <td style="text-align:right">2011-09-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813225280f013227a7d1a8002a.jsp" class="toContentMain" title="中国铌业投资成功收购世界最大铌公司15%股权">中国铌业投资成功收购世界最大铌公司15%股权</a></td>
          <td style="text-align:right">2011-09-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813225280f013227a7610d0029.jsp" class="toContentMain" title="中国餐饮业转型升级亟待解决成本费率过高屏障">中国餐饮业转型升级亟待解决成本费率过高屏障</a></td>
          <td style="text-align:right">2011-09-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813225280f013227a6f4a30028.jsp" class="toContentMain" title="五虎入滇 云南水泥面临大洗牌">五虎入滇 云南水泥面临大洗牌</a></td>
          <td style="text-align:right">2011-09-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813225280f013227a61f3f0027.jsp" class="toContentMain" title="啤酒业力推高价产品变相涨价">啤酒业力推高价产品变相涨价</a></td>
          <td style="text-align:right">2011-09-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813225280f013227a029070023.jsp" class="toContentMain" title="比亚迪汽车大裁员背后：低成本模式或走到尽头">比亚迪汽车大裁员背后：低成本模式或走到尽头</a></td>
          <td style="text-align:right">2011-09-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813225280f0132279f72d50022.jsp" class="toContentMain" title="喜达屋酒店CEO中国上班一个月 看好中国市场">喜达屋酒店CEO中国上班一个月 看好中国市场</a></td>
          <td style="text-align:right">2011-09-02</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae013222b45d710135.jsp" class="toContentMain" title="太阳能与地源热泵“联姻” 如何运行？">太阳能与地源热泵“联姻” 如何运行？</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_2.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：1/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
