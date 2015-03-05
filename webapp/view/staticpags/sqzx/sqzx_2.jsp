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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae013222b2081c0134.jsp" class="toContentMain" title="欧意德动力助推中国零部件新机遇">欧意德动力助推中国零部件新机遇</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae013222b12cfa0133.jsp" class="toContentMain" title="市场分析 “金九银十”能否为卫浴带来利润">市场分析 “金九银十”能否为卫浴带来利润</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae013222b03e050132.jsp" class="toContentMain" title="家居装饰建材行业重视品牌塑造成共识">家居装饰建材行业重视品牌塑造成共识</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae013222afb17d0131.jsp" class="toContentMain" title="煤炭机械有望步入黄金五年 多因素促需求">煤炭机械有望步入黄金五年 多因素促需求</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae013222ae7ad60130.jsp" class="toContentMain" title="空调政策红利庇护退出 明年涨价将成定局">空调政策红利庇护退出 明年涨价将成定局</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae013222ad8f65012f.jsp" class="toContentMain" title="冷水机在各行业的应用和选型计算方法">冷水机在各行业的应用和选型计算方法</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae013222ab469e012e.jsp" class="toContentMain" title="“十二五”安防业高速增长 产值将破千亿">“十二五”安防业高速增长 产值将破千亿</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae0132229ed8670108.jsp" class="toContentMain" title="日韩液晶巨头调整策略 国内面板企业承压">日韩液晶巨头调整策略 国内面板企业承压</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae0132229deb450107.jsp" class="toContentMain" title="2015年半导体照明芯片国产化率或达70%">2015年半导体照明芯片国产化率或达70%</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae0132229c0539008a.jsp" class="toContentMain" title="东芝索尼和日立宣布合并中小型液晶面板业务">东芝索尼和日立宣布合并中小型液晶面板业务</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae0132229b789e0088.jsp" class="toContentMain" title="我国云服务标准有望落地 国内厂商将获利">我国云服务标准有望落地 国内厂商将获利</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae0132229a73c40068.jsp" class="toContentMain" title="医药工业发展维持“黄金十年”预判">医药工业发展维持“黄金十年”预判</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae01322299ac120067.jsp" class="toContentMain" title="电子书行业面临市场困境还能坚持多久？">电子书行业面临市场困境还能坚持多久？</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae0132229682400063.jsp" class="toContentMain" title="医疗改革攻坚 医药分开提速">医疗改革攻坚 医药分开提速</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae013222934ed40062.jsp" class="toContentMain" title="液晶电视增速放缓 3D智能电视成新突围">液晶电视增速放缓 3D智能电视成新突围</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae0132229129c8005e.jsp" class="toContentMain" title="破解电动汽车难题：市场最终决定商业模式">破解电动汽车难题：市场最终决定商业模式</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae01322290a43e005d.jsp" class="toContentMain" title="合肥：获1500万国家物联网专项资金支持">合肥：获1500万国家物联网专项资金支持</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae0132228ab569005b.jsp" class="toContentMain" title="日韩液晶巨头调整策略 国内面板企业承压">日韩液晶巨头调整策略 国内面板企业承压</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae0132228952e2005a.jsp" class="toContentMain" title="我国将全面实施电子信息产品污染控制认证">我国将全面实施电子信息产品污染控制认证</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff808081321f7bae01322288e5b10059.jsp" class="toContentMain" title="乘用车企业及产品或将实施行业准入">乘用车企业及产品或将实施行业准入</a></td>
          <td style="text-align:right">2011-09-01</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_3.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：2/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
