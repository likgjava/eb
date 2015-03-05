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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f51edd93501e4.jsp" class="toContentMain" title="浙江晨光电缆股份有限公司等6家企业中选国网电力光纤到户试点工程">浙江晨光电缆股份有限公司等6家企业中选国网电力光纤到户试点工程</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f51ec941801e2.jsp" class="toContentMain" title="亨通光电拟募资13亿扩大光棒光纤电缆生产">亨通光电拟募资13亿扩大光棒光纤电缆生产</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f51d294b801b1.jsp" class="toContentMain" title="欧盟拟对进口自中国铜版纸征最高39.1%关税">欧盟拟对进口自中国铜版纸征最高39.1%关税</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f51ced0ab0199.jsp" class="toContentMain" title="近五成家具不合格 甲醛释放超标比例大">近五成家具不合格 甲醛释放超标比例大</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f51c91d1a018c.jsp" class="toContentMain" title="光伏照明将成为“十二五”投资最大受益者">光伏照明将成为“十二五”投资最大受益者</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f51c671130185.jsp" class="toContentMain" title="渝中半岛：升级改造50个灯饰项目">渝中半岛：升级改造50个灯饰项目</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f51c471480178.jsp" class="toContentMain" title="第一季度挖掘机市场销售保持高速增长">第一季度挖掘机市场销售保持高速增长</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f51c1fedf016b.jsp" class="toContentMain" title="甘肃招募欲投资80亿元打造南绕城高速公路">甘肃招募欲投资80亿元打造南绕城高速公路</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f51c075be0169.jsp" class="toContentMain" title="首钢50亿投资落户漳州 建福建最大板材基地">首钢50亿投资落户漳州 建福建最大板材基地</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f51bc89750165.jsp" class="toContentMain" title="中小企业上网行为管理设备采购经验谈">中小企业上网行为管理设备采购经验谈</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f51b8ce880163.jsp" class="toContentMain" title="采购回扣无处不在，老板如何应招？">采购回扣无处不在，老板如何应招？</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f51b65356015b.jsp" class="toContentMain" title="10英寸面板将涨价 缘起苹果iPad2大量采购">10英寸面板将涨价 缘起苹果iPad2大量采购</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f51b4d9e60158.jsp" class="toContentMain" title="煤炭采购难度加大 环渤海动力煤本周明显涨价">煤炭采购难度加大 环渤海动力煤本周明显涨价</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f51af602c0108.jsp" class="toContentMain" title="行政采购人员如何化解信任危机">行政采购人员如何化解信任危机</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f51ae37e30106.jsp" class="toContentMain" title="广州国资委采购永中Office">广州国资委采购永中Office</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f51aad5f80104.jsp" class="toContentMain" title="政府采购注重创新、绿色和服务">政府采购注重创新、绿色和服务</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4e78d5012f51a2240000fe.jsp" class="toContentMain" title="隆众复合肥：下游厂家逐渐采购原料 复合肥市场平淡盘整">隆众复合肥：下游厂家逐渐采购原料 复合肥市场平淡盘整</a></td>
          <td style="text-align:right">2011-04-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4d77bc080271.jsp" class="toContentMain" title="对于团购网站广告传播的一些观点">对于团购网站广告传播的一些观点</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4d764328026f.jsp" class="toContentMain" title="“扶植”麦考林 新浪资本“局中局”">“扶植”麦考林 新浪资本“局中局”</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812f4c7799012f4d74b0b5026d.jsp" class="toContentMain" title="天津滨海区十项举措推进新能源产业发展">天津滨海区十项举措推进新能源产业发展</a></td>
          <td style="text-align:right">2011-04-13</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_61.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_63.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：62/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
