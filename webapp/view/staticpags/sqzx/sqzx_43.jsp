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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813008ca4701301f99186b05e5.jsp" class="toContentMain" title="十二五煤层气产业规模或翻番 带动十倍市场空间">十二五煤层气产业规模或翻番 带动十倍市场空间</a></td>
          <td style="text-align:right">2011-05-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813008ca4701301f90138905b3.jsp" class="toContentMain" title="铅酸电池行业拉开整顿大幕 三分之二企业将淘汰">铅酸电池行业拉开整顿大幕 三分之二企业将淘汰</a></td>
          <td style="text-align:right">2011-05-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813008ca4701301f75995c057f.jsp" class="toContentMain" title="铁矿石开跌 紧缩政策影响钢铁行业">铁矿石开跌 紧缩政策影响钢铁行业</a></td>
          <td style="text-align:right">2011-05-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813008ca4701301f73b30d050a.jsp" class="toContentMain" title="柴油荒恐卷土重来">柴油荒恐卷土重来</a></td>
          <td style="text-align:right">2011-05-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813008ca4701301f725e540508.jsp" class="toContentMain" title="电荒真相调查:超1亿吨合同煤被倒卖 变身市场煤">电荒真相调查:超1亿吨合同煤被倒卖 变身市场煤</a></td>
          <td style="text-align:right">2011-05-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813008ca4701301f70941a0504.jsp" class="toContentMain" title="三大中游行业盈利或向下 关注四大下行动力">三大中游行业盈利或向下 关注四大下行动力</a></td>
          <td style="text-align:right">2011-05-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813008ca4701301f6ced560502.jsp" class="toContentMain" title="亚洲柴油裂解价差下滑,航煤价差触及两周低点">亚洲柴油裂解价差下滑,航煤价差触及两周低点</a></td>
          <td style="text-align:right">2011-05-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813008ca4701301f6a5f8704fe.jsp" class="toContentMain" title="干旱带来升水 粮价难现下跌">干旱带来升水 粮价难现下跌</a></td>
          <td style="text-align:right">2011-05-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080813008ca4701301f69c16204fc.jsp" class="toContentMain" title="大宗商品5月急挫 挂钩理财产品被动观望">大宗商品5月急挫 挂钩理财产品被动观望</a></td>
          <td style="text-align:right">2011-05-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ffcb9fb01300101cd9c0286.jsp" class="toContentMain" title="民资外资鲶鱼效应必显 多元办医格局有望形成">民资外资鲶鱼效应必显 多元办医格局有望形成</a></td>
          <td style="text-align:right">2011-05-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ffcb9fb013000ffc2240284.jsp" class="toContentMain" title="医疗医美行业十年修得“同船渡”">医疗医美行业十年修得“同船渡”</a></td>
          <td style="text-align:right">2011-05-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ffcb9fb013000f68bc5025d.jsp" class="toContentMain" title="上海新医改不强制市民定点医疗">上海新医改不强制市民定点医疗</a></td>
          <td style="text-align:right">2011-05-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ffcb9fb013000ee721c0253.jsp" class="toContentMain" title="受宏观政策交织影响 钢材价格持续震荡">受宏观政策交织影响 钢材价格持续震荡</a></td>
          <td style="text-align:right">2011-05-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ffcb9fb013000ec82cd0218.jsp" class="toContentMain" title="替代光源产值再攀高 2014年将超越LED灯具">替代光源产值再攀高 2014年将超越LED灯具</a></td>
          <td style="text-align:right">2011-05-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ffcb9fb013000e822b70208.jsp" class="toContentMain" title="2011年车企进军目标 二三级市场">2011年车企进军目标 二三级市场</a></td>
          <td style="text-align:right">2011-05-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ffcb9fb013000e1879501ae.jsp" class="toContentMain" title="“十二五”西北地区钢铁企业将“厚积薄发”">“十二五”西北地区钢铁企业将“厚积薄发”</a></td>
          <td style="text-align:right">2011-05-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ffcb9fb013000dee3b401a6.jsp" class="toContentMain" title="2011年无序竞争致卫浴五金市场“大乱”">2011年无序竞争致卫浴五金市场“大乱”</a></td>
          <td style="text-align:right">2011-05-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ffcb9fb013000daa5390198.jsp" class="toContentMain" title="国家发改委要求暂停柴油出口 电荒推高油荒预期">国家发改委要求暂停柴油出口 电荒推高油荒预期</a></td>
          <td style="text-align:right">2011-05-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ffcb9fb013000d1a0d90163.jsp" class="toContentMain" title="水泥业遭遇电荒在需求旺季再次提价">水泥业遭遇电荒在需求旺季再次提价</a></td>
          <td style="text-align:right">2011-05-18</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812fe93117012ff6c8f9d5032d.jsp" class="toContentMain" title="我国石材机械制造业发展初具规模">我国石材机械制造业发展初具规模</a></td>
          <td style="text-align:right">2011-05-16</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_42.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_44.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：43/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
