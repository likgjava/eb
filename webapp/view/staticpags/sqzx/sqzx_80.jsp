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
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e519782012e5ab4e0030161.jsp" class="toContentMain" title="笔记本佯装“秘籍” 另类文具谁来把关？">笔记本佯装“秘籍” 另类文具谁来把关？</a></td>
          <td style="text-align:right">2011-02-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e519782012e5ab25d4f015f.jsp" class="toContentMain" title="学生用品有毒到底是谁的错">学生用品有毒到底是谁的错</a></td>
          <td style="text-align:right">2011-02-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e519782012e5aae589d015d.jsp" class="toContentMain" title="家具保修服务？ 行家揭秘售后六大盲区">家具保修服务？ 行家揭秘售后六大盲区</a></td>
          <td style="text-align:right">2011-02-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e519782012e5aa80213015b.jsp" class="toContentMain" title="“家具主张”模式悄然兴起 装修跟着家具走">“家具主张”模式悄然兴起 装修跟着家具走</a></td>
          <td style="text-align:right">2011-02-25</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e519782012e55be71dc00f2.jsp" class="toContentMain" title="团时代的省钱攻略 专家教你分辨馅饼与陷阱">团时代的省钱攻略 专家教你分辨馅饼与陷阱</a></td>
          <td style="text-align:right">2011-02-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e519782012e55b78e7f00f0.jsp" class="toContentMain" title="阿里进入物流市场 速度由3天变1天">阿里进入物流市场 速度由3天变1天</a></td>
          <td style="text-align:right">2011-02-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e519782012e55b25d9700ee.jsp" class="toContentMain" title="武钢澳洲煤炭收购谈判暂停 力拓或得手">武钢澳洲煤炭收购谈判暂停 力拓或得手</a></td>
          <td style="text-align:right">2011-02-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e519782012e55ae945600ec.jsp" class="toContentMain" title="高端白酒涨价 92.9%的人认为与公款吃喝有关">高端白酒涨价 92.9%的人认为与公款吃喝有关</a></td>
          <td style="text-align:right">2011-02-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e519782012e55ab739000ea.jsp" class="toContentMain" title="汇丰2月中国制造业PMI初值降至7个月新低">汇丰2月中国制造业PMI初值降至7个月新低</a></td>
          <td style="text-align:right">2011-02-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e519782012e55a5eafe00e8.jsp" class="toContentMain" title="澳洲墨尔本市现“蜗居”公寓 12名留学生共居一屋">澳洲墨尔本市现“蜗居”公寓 12名留学生共居一屋</a></td>
          <td style="text-align:right">2011-02-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e519782012e55a2d32100e6.jsp" class="toContentMain" title="家居照明大有讲究 五大功能间照明解决方案">家居照明大有讲究 五大功能间照明解决方案</a></td>
          <td style="text-align:right">2011-02-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e519782012e559fd54800e4.jsp" class="toContentMain" title="家装市场鱼龙混杂 催热装修验房服务">家装市场鱼龙混杂 催热装修验房服务</a></td>
          <td style="text-align:right">2011-02-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e519782012e559cf21100e2.jsp" class="toContentMain" title="衣柜低碳时代">衣柜低碳时代</a></td>
          <td style="text-align:right">2011-02-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e519782012e559abbbb00e0.jsp" class="toContentMain" title="欧风卫浴PK本土卫浴谁是赢家">欧风卫浴PK本土卫浴谁是赢家</a></td>
          <td style="text-align:right">2011-02-24</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e50c87be30466.jsp" class="toContentMain" title="中国风电累计装机超过美国，跃居世界第一">中国风电累计装机超过美国，跃居世界第一</a></td>
          <td style="text-align:right">2011-02-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e50c67ba20464.jsp" class="toContentMain" title="我国新能源产业最新数据公布：太阳能应用滞后">我国新能源产业最新数据公布：太阳能应用滞后</a></td>
          <td style="text-align:right">2011-02-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e50a20de40455.jsp" class="toContentMain" title="百思买“断腕” 寄望五星电器翻身">百思买“断腕” 寄望五星电器翻身</a></td>
          <td style="text-align:right">2011-02-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e50955e1c044a.jsp" class="toContentMain" title="电子书市场增速或锐减 汉王面临三大挑战">电子书市场增速或锐减 汉王面临三大挑战</a></td>
          <td style="text-align:right">2011-02-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e508e27610448.jsp" class="toContentMain" title="橱柜企业 争夺低碳市场">橱柜企业 争夺低碳市场</a></td>
          <td style="text-align:right">2011-02-23</td>
        </tr>
        <tr>
          <td style="text-align:left"><a href="javascript:void(0);" id="/view/staticpags/sqzx/ff8080812e4689bf012e508936fd0446.jsp" class="toContentMain" title="“低碳”正当时 展望门窗五金发展方向">“低碳”正当时 展望门窗五金发展方向</a></td>
          <td style="text-align:right">2011-02-23</td>
        </tr>
  </table>
      <div class="flipPage">
<div class="dataTables_paginate paging_full_numbers">
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx.jsp" title="首页">首页</span> 
	<span class="previous paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_79.jsp" title="上一页">上一页</span> 
	<span class="next paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_81.jsp" title="下一页">下一页</span> 
	<span class="last paginate_button cmsHref_self" id="/view/staticpags/sqzx/sqzx_83.jsp" title="尾页">尾页</span>
<span class="totalNum">页次：80/83 每页20条 </span>
</div>		
      </div>
      
  </div>
	
