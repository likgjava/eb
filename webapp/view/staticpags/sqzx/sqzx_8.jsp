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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f586b50131f9557fa2004c.jsp" class="toContentMain" title="太阳能光热与建筑一体化的未来发展机遇">太阳能光热与建筑一体化的未来发展机遇</a></td>
          <td style="text-align:right">2011-08-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f586b50131f9546348004b.jsp" class="toContentMain" title="维C行业呼唤提高准入行业门槛">维C行业呼唤提高准入行业门槛</a></td>
          <td style="text-align:right">2011-08-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f586b50131f953c9d5004a.jsp" class="toContentMain" title="钛白粉等生产资料国际价格不断上扬">钛白粉等生产资料国际价格不断上扬</a></td>
          <td style="text-align:right">2011-08-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f586b50131f952798b0048.jsp" class="toContentMain" title="2012年全球家用药械市场规模达1300亿元">2012年全球家用药械市场规模达1300亿元</a></td>
          <td style="text-align:right">2011-08-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f586b50131f9510bde0047.jsp" class="toContentMain" title="云计算标准良莠不齐 企业存浑水摸鱼情况">云计算标准良莠不齐 企业存浑水摸鱼情况</a></td>
          <td style="text-align:right">2011-08-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f586b50131f9500f4e0046.jsp" class="toContentMain" title="专家称家电行业新一轮技术创新蓄势待发">专家称家电行业新一轮技术创新蓄势待发</a></td>
          <td style="text-align:right">2011-08-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f586b50131f94f0add0045.jsp" class="toContentMain" title="第二季度中国首超美国成全球最大PC市场">第二季度中国首超美国成全球最大PC市场</a></td>
          <td style="text-align:right">2011-08-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f586b50131f9428db8003f.jsp" class="toContentMain" title="棉花收储在即价格企稳回升">棉花收储在即价格企稳回升</a></td>
          <td style="text-align:right">2011-08-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f586b50131f941d7a2003e.jsp" class="toContentMain" title="新材料十二五规划将公布 重点支持六类产品">新材料十二五规划将公布 重点支持六类产品</a></td>
          <td style="text-align:right">2011-08-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f586b50131f94137dc003d.jsp" class="toContentMain" title="中国铁路机车将首次进入欧盟市场">中国铁路机车将首次进入欧盟市场</a></td>
          <td style="text-align:right">2011-08-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f586b50131f940b4fd003c.jsp" class="toContentMain" title="云南太阳能产业：深挖绿色能源走低碳发展之路">云南太阳能产业：深挖绿色能源走低碳发展之路</a></td>
          <td style="text-align:right">2011-08-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f188870131f441a3a2002e.jsp" class="toContentMain" title="渠道变革 橱柜行业电子商务路在何方？">渠道变革 橱柜行业电子商务路在何方？</a></td>
          <td style="text-align:right">2011-08-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f188870131f43f488b002c.jsp" class="toContentMain" title="橱柜行业遭遇家电巨头来袭：有钱不是万能">橱柜行业遭遇家电巨头来袭：有钱不是万能</a></td>
          <td style="text-align:right">2011-08-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f188870131f43e96ec002b.jsp" class="toContentMain" title="浅析：中国模具标准件行业未来前景几何">浅析：中国模具标准件行业未来前景几何</a></td>
          <td style="text-align:right">2011-08-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f188870131f43d19d2002a.jsp" class="toContentMain" title="转危为安再化为“机”：推动玩具行业转型升级">转危为安再化为“机”：推动玩具行业转型升级</a></td>
          <td style="text-align:right">2011-08-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f188870131f43bbbbd0029.jsp" class="toContentMain" title="逆市发力受阻 五金配件行业奋力自救">逆市发力受阻 五金配件行业奋力自救</a></td>
          <td style="text-align:right">2011-08-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f188870131f43aaa970028.jsp" class="toContentMain" title="风电零部件“传染”微利寒流频“感冒”">风电零部件“传染”微利寒流频“感冒”</a></td>
          <td style="text-align:right">2011-08-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f188870131f439b0020027.jsp" class="toContentMain" title="全球太阳能光伏市场发展重心将转移">全球太阳能光伏市场发展重心将转移</a></td>
          <td style="text-align:right">2011-08-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f188870131f430d43c0026.jsp" class="toContentMain" title="中国鞋企不断提升自身水平 应对贸易堡垒">中国鞋企不断提升自身水平 应对贸易堡垒</a></td>
          <td style="text-align:right">2011-08-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff80808131f188870131f42f64820025.jsp" class="toContentMain" title="卫浴供需矛盾紧张 或导致行业重新洗牌">卫浴供需矛盾紧张 或导致行业重新洗牌</a></td>
          <td style="text-align:right">2011-08-23</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_7.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_9.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：8/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
