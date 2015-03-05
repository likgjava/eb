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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb74308bd0614.jsp" class="toContentMain" title="无线感测器将为建筑自动化系统带来多元化">无线感测器将为建筑自动化系统带来多元化</a></td>
          <td style="text-align:right">2011-03-15</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb24ecad50113.jsp" class="toContentMain" title="深圳将花4亿治废气迎大运 环保新政将重拳推出">深圳将花4亿治废气迎大运 环保新政将重拳推出</a></td>
          <td style="text-align:right">2011-03-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb23154470111.jsp" class="toContentMain" title="面板设备延迟交货 闪存芯片价格看涨">面板设备延迟交货 闪存芯片价格看涨</a></td>
          <td style="text-align:right">2011-03-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb22e70c7010f.jsp" class="toContentMain" title="S80L驶入公务车市场 与奥迪竞争政府采购">S80L驶入公务车市场 与奥迪竞争政府采购</a></td>
          <td style="text-align:right">2011-03-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb22d7ac5010d.jsp" class="toContentMain" title="乐施会迅速采购物资">乐施会迅速采购物资</a></td>
          <td style="text-align:right">2011-03-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb22c5aa8010b.jsp" class="toContentMain" title="2011年家电市场价格上涨迷雾">2011年家电市场价格上涨迷雾</a></td>
          <td style="text-align:right">2011-03-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb227b5dd0109.jsp" class="toContentMain" title="三一重工获缅甸亿元大单">三一重工获缅甸亿元大单</a></td>
          <td style="text-align:right">2011-03-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb2241ad00107.jsp" class="toContentMain" title="钢价出现小幅下跌 储备良机或在眼前">钢价出现小幅下跌 储备良机或在眼前</a></td>
          <td style="text-align:right">2011-03-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb22114d70105.jsp" class="toContentMain" title="神州租车年内投资30亿 采购2.5万台运营车">神州租车年内投资30亿 采购2.5万台运营车</a></td>
          <td style="text-align:right">2011-03-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb21cb0380101.jsp" class="toContentMain" title="上周国内硫磺市场成交状况不一">上周国内硫磺市场成交状况不一</a></td>
          <td style="text-align:right">2011-03-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb219fb7300ff.jsp" class="toContentMain" title="南方电网物资管理目标：全网集中采购年内要达60%">南方电网物资管理目标：全网集中采购年内要达60%</a></td>
          <td style="text-align:right">2011-03-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb218bc6200fd.jsp" class="toContentMain" title="王文京：中国在应用上比国外更先进">王文京：中国在应用上比国外更先进</a></td>
          <td style="text-align:right">2011-03-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb217da8b00fb.jsp" class="toContentMain" title="中国炉料市场截止3月11日当周持续下调">中国炉料市场截止3月11日当周持续下调</a></td>
          <td style="text-align:right">2011-03-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb2162bce00f9.jsp" class="toContentMain" title="南京在台湾收获50亿美元大单">南京在台湾收获50亿美元大单</a></td>
          <td style="text-align:right">2011-03-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb209c30a00f7.jsp" class="toContentMain" title="杨涛:儿童家具必须解决安全环保等问题">杨涛:儿童家具必须解决安全环保等问题</a></td>
          <td style="text-align:right">2011-03-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012eb1f9d52900f5.jsp" class="toContentMain" title="衣柜环保很重要 教你识别衣柜行业3大陷阱">衣柜环保很重要 教你识别衣柜行业3大陷阱</a></td>
          <td style="text-align:right">2011-03-14</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ea2ed36ae0021.jsp" class="toContentMain" title="中等规模品牌上位 空调业2011年新格局">中等规模品牌上位 空调业2011年新格局</a></td>
          <td style="text-align:right">2011-03-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ea2e8528f001c.jsp" class="toContentMain" title="三年医改政府投入远超8500亿元">三年医改政府投入远超8500亿元</a></td>
          <td style="text-align:right">2011-03-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ea2e6c6760014.jsp" class="toContentMain" title="内蒙古规范基本药物采购机制">内蒙古规范基本药物采购机制</a></td>
          <td style="text-align:right">2011-03-11</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812ea2d882012ea2e536370010.jsp" class="toContentMain" title="阿尔斯通在华获4000万欧元核电订单 提供配套设备">阿尔斯通在华获4000万欧元核电订单 提供配套设备</a></td>
          <td style="text-align:right">2011-03-11</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_73.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_75.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：74/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
