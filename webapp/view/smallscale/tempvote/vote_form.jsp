<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>2010北京及周边地区我最满意的滑雪场评选调查</title>
<link href="../../resource/common/plug_in.css" type="text/css" rel="stylesheet"/>
<link href="../../resource/skin/skin07/css/main.css" type="text/css" rel="stylesheet"/>
<link href="hx.css" type="text/css" rel="stylesheet"/><!--滑雪页面样式表-->
<link href="../../resource/plug-in/jquery/dataTables/media/css/demo_page.css" type="text/css" rel="stylesheet"/>
<link href="../../resource/plug-in/jquery/dataTables/media/css/demo_table.css" type="text/css" rel="stylesheet"/>
<link href="../../resource/plug-in/jquery/dataTables/media/css/tableForm.css" type="text/css" rel="stylesheet"/>
<link href="../../resource/skin/goods/css/goods_add.css" type="text/css" rel="stylesheet"/>
<link href="../../resource/skin/bizplatform/css/orginfo_add.css" type="text/css" rel="stylesheet"/>
<link href="../../resource/skin/pubservice/css/my_desk.css" type="text/css" rel="stylesheet"/>
<script src="../../resource/plug-in/jquery/jquery.js" type="text/javascript"></script>
<script src="../../resource/plug-in/jquery/jquery.ui.js" type="text/javascript"></script>
<script src="../../resource/plug-in/jquery/jquery.wresize.js" type="text/javascript"></script>
<script src="../../resource/plug-in/jquery/jquery.autoHeight.js" type="text/javascript"></script>
<script src="../../resource/plug-in/jquery/jquery.form.js" type="text/javascript"></script>
<script src="../../resource/plug-in/jquery/flexigrid/flexigrid.js" type="text/javascript"></script>
<script src="../../resource/plug-in/jquery/dataTables/media/js/jquery.dataTables.js" type="text/javascript"></script>
<script src="../../resource/plug-in/dhtmlxTree/dhtmlxcommon.js" type="text/javascript"></script>
<script src="../../resource/plug-in/dhtmlxTree/dhtmlxtree.js" type="text/javascript"></script>
<script src="../../resource/plug-in/jquery/autocomplete/jquery.autocomplete.js" type="text/javascript"></script>
<script src="../../resource/scripts/jquery/epsValidate/jquery.validate.js" type="text/javascript"></script>
<script src="../../resource/plug-in/jquery/select/jQuery.CascadingSelect.js" type="text/javascript"></script>
<script src="../../resource/plug-in/jquery/select/jQuery.FillOptions.js" type="text/javascript"></script>
<script src="../../resource/plug-in/jquery/select/jquery.dxCombobox.js" type="text/javascript"></script>
<script src="../../resource/plug-in/jquery/progressbar/jquery.progressbar.js" type="text/javascript"></script>
<script src="../../resource/plug-in/jquery/jquery.bxGallery.js" type="text/javascript"></script>
<script src="../../resource/plug-in/FCKeditor/fckeditor.js" type="text/javascript"></script>
<script src="../../resource/scripts/jquery/epsDatepicker/epsDatepicker.js" type="text/javascript"></script>
<script src="../../resource/scripts/jquery/epsDialog/epsDialog.js" type="text/javascript"></script>
<script src="../../resource/scripts/jquery/epsContentMenu/epsContentMenu.js" type="text/javascript"></script>
<script src="../../resource/scripts/json/json-eps.js" type="text/javascript"></script>
<script src="../../resource/scripts/util/obj2str.js" type="text/javascript"></script>
<script src="../../resource/scripts/util/dateUtil.js" type="text/javascript"></script>
<script src="../../resource/scripts/util/select.js" type="text/javascript"></script>
<script src="../../resource/scripts/util/listTree.js" type="text/javascript"></script>
<script src="../../resource/scripts/util/treeUtil.js" type="text/javascript"></script>
<script src="../../resource/scripts/util/tabSet.js" type="text/javascript"></script>
<script src="../../resource/scripts/util/otherUtils.js" type="text/javascript"></script>
<script src="../../resource/scripts/util/loadPage.js" type="text/javascript"></script>
<script src="../../resource/scripts/util/sysTools.js" type="text/javascript"></script>
<script src="../../resource/scripts/util/date.js" type="text/javascript"></script>
<script src="../../resource/scripts/util/changeSkin.js" type="text/javascript"></script>
<script src="../../resource/scripts/util/sysInfo.js" type="text/javascript"></script>
<!--Tab面板-->

</head>
<body onload= "hasVote();">
<!--浏览器检测 开始-->
<!--[if IE 6]>
<div class="userTips">
	<p>尊敬的用户，您好！您使用的浏览器版本过低，请升级到较高版本，以获得更好的安全性及操作、视觉体验。<a href="http://www.microsoft.com/china/windows/internet-explorer/worldwide-sites.aspx" title="点击下载IE8浏览器安装文件">下载IE8浏览器</a></p>
</div>
<![endif]-->
<noscript>
<div class="userTips">
  <p>尊敬的用户，您好！您使用的浏览器不支持或禁用JavaScript脚本。请使用支持JavaScript脚本的浏览器或启用浏览器JavaScript脚本功能。</p>
  <p>如有任何疑问或需要帮助，请<a href="http://www.gpcsoft.com" target="_blank" title="珠海政采软件技术有限公司 技术支持">与我们联系</a>。</p>
</div>
</noscript>
<!--浏览器检测 结束-->
<script> 
	var gpcsoftDate = new Date("2010","12","7","9","21","37");//设定 当前 服 器时间 
</script>
<input type="hidden" id="ssousername" value="null" />
<input type="hidden" id="initPath" value="<%=request.getContextPath()%>" />
<input type="hidden" id="returnUrl" value="" />
<input type="hidden" id="roleType" value="null" />
<input type="hidden" id="contentSubUrl" name="contentSubUrl" value="null"/>
<input type="hidden" id="contentMainUrl" name="contentMainUrl" value="null"/>
<input type="hidden" id="contentSuppUrl" name="contentSuppUrl" value="null"/>
<input type="hidden" id="viewUrl" value="null" />
<input type="hidden" id="currentTab" value="0"/>

<div id="sysContainer">
  <div class="header hidden">
    <div id="allLoginSysDiv" class="hidden" style="width:100%;background:url(../img/userLogin_bg.gif) repeat-x scroll left top #FAFAFA;position:fixed;right:420px;top:20px;width:100px;z-index:9999999"></div>
    <div id="sysBranding">
      <h1><span class="webName">阳光易购采购交易平台</span></h1>
      <p><span class="versionSys">
        <!--系统版本-->
        </span> <span class="dataSys">
        <!--系统发布时间-->
        </span></p>
      <p class="sysSlogan">
        <!--系统口号-->
      </p>
      <div class="search search-other">
        <label id="searchTypeLabel">采购项目</label>
        <input type="hidden" name="searchType" id="searchType" value="1"/>
        <input id="keyWords" name="keyWords" value="请输入关键字"/>
        <button type="button" id="keyWordSearch"></button>
        <div class="shopCarBtn" onclick="index.goinToShopCart()">查看购物车<span>共<em name="shoppingCartGoodsTotal">0</em>件</span></div>
        <ul class="select hidden" id="searchTypeSelect">
          <li class="current" id="1">采购项目</li>
          <li id="2">采购人</li>
          <li id="3">商品库</li>
          <li id="4">供应商库</li>
        </ul>
      </div>
    </div>
    <div id="navMain" class="navMain">
      <ul>
        <!--主导航菜单-->
        <li><a id="goToIndex" class="homePage" href="javascript:void(0)"><span>首页</span></a></li>
        <li><a id="goToBulltin" href="javascript:void(0)"><span>找采购项目</span></a></li>
        <li><a id="goToGoods" href="javascript:void(0)"><span>找商品</span></a></li>
        <li><a id="goToSupplier" href="javascript:void(0)"><span>找供应商</span></a></li>
        <li><a id="goToBuyer" href="javascript:void(0)"><span>找采购人</span></a></li>
        <li><a id="goToExpert" href="javascript:void(0)"><span>找专家</span></a></li>
        <li class="last"><a id="toPromoter" href="javascript:void(0)"><span>我要推广</span></a></li>
      </ul>
    </div>
    <!--#navMain-->
    <div id="loginInfo">
      <p class="dateTime"> <span class="nowDate"></span> <span class="nowTime"></span> <span class="hello"></span> </p>
      <!--登录信息-->
      <p class="userInfo"> <a href="javascript:void(0)" id="tologin">登录</a> <a href="javascript:void(0)" id="toregistration">注册</a> </p>
      <!--户信息-->
    </div>
    <div id="sysTools">
      <!--系统工具-->
      <ul>
        <li id="allLoginSysLi" class="hidden"><a href="javascript:void(0)">选择其他系统</a></li>
        <li id="numberArea" class="hidden"><a href="javascript:void(0)" class="number">我的商务室</a></li>
        <li><a href="javascript:void(0)" class="map" id="toPromoterA">我要推广</a></li>
        <li><a href="javascript:void(0)" class="home" id="">客户意见</a></li>
        <li><a href="javascript:void(0)" class="collect cmsHref_index" id="view/staticpags/customer/customer.html">客服中心</a></li>
        <script>

//跳转到我要推广

$('#toPromoterA').click(function(){

	var targetUrl = $('#initPath').val()+"/PromoterRegisterController.do?method=toRegistPromoter";

	

	if($("a.index").html()==null){//加载

		$('#sysContent').loadPage(targetUrl);

	}else{//跳转

		window.location.href = $('#initPath').val()+"/IndexViewController.do?method=index&contentMainUrl="+targetUrl;

	}

})

</script>
      </ul>
    </div>
  </div>
  <!--.header-->
  <div class="shangquanThroughAd"></div>
  <!--.shangquanThroughAd陶朱公大图-->
  <div id="sysContent" class="sysContent" >
    <!--系统内容-->
    <div id="contentSub" class="contentSub"> </div>
    <!--#contentSub-->
    <div id="contentMain" class="contentMain">
      <!--主内容-->
      <div id="conTitle" class="hidden">
        <div class="navCurrent">
          <!--面包屑导航条-->
        </div>
        <h3><span>
          <!--功能点标题-->
          </span></h3>
      </div>
      <div id="conBody" class="conBody hxConBody" >
       
         <table cellspacing="0"  cellpadding="0" width="100%;" border="1" class="parentTable">
                <tr>
                  <th colspan="2" rowspan="2">评价项目</th>
                  <th colspan="5" class="t1"><h1>满意度调查在线问卷</h1></th>
                </tr>
                <tr class="th2">
                  <td>最满意（100分）</td>
                  <td>一般满意（85分）</td>
                  <td>还行（75分）</td>
                  <td>不满意（65分）</td>
                  <td>很差（45分）</td>
                </tr>
                <tr>
                  <th colspan="2">总体环境</th>
                  <td><input name="option_0" value="1" type="radio" /></td>
                  <td><input name="option_0" value="2" type="radio" /></td>
                  <td><input name="option_0" value="3" checked="checked" type="radio" /></td>
                  <td><input name="option_0" value="4" type="radio" /></td>
                  <td><input name="option_0" value="5" type="radio" /></td>
                </tr>
                <tr>
                  <th rowspan="3">设施</th>
                  <th >高级道</th>
                  <td><input name="option_1" value="1" type="radio" /></td>
                  <td><input name="option_1" value="2" type="radio" /></td>
                  <td><input name="option_1" value="3"  checked="checked"  type="radio" /></td>
                  <td><input name="option_1" value="4" type="radio" /></td>
                  <td><input name="option_1" value="5" type="radio" /></td>
                </tr>
                <tr>
                  <th >中级道</th>
                  <td><input name="option_2" value="1" type="radio" /></td>
                  <td><input name="option_2" value="2" type="radio" /></td>
                  <td><input name="option_2" value="3"  checked="checked"  type="radio" /></td>
                  <td><input name="option_2" value="4" type="radio" /></td>
                  <td><input name="option_2" value="5" type="radio" /></td>
                </tr>
                <tr>
                  <th >初级道</th>
                  <td><input name="option_3" value="1" type="radio" /></td>
                  <td><input name="option_3" value="2" type="radio" /></td>
                  <td><input name="option_3" value="3"  checked="checked"  type="radio" /></td>
                  <td><input name="option_3" value="4" type="radio" /></td>
                  <td><input name="option_3" value="5" type="radio" /></td>
                </tr>
                <tr>
                  <th rowspan="2">装备</th>
                  <th >滑雪板</th>
                  <td><input name="option_4" value="1" type="radio" /></td>
                  <td><input name="option_4" value="2" type="radio" /></td>
                  <td><input name="option_4" value="3"  checked="checked"  type="radio" /></td>
                  <td><input name="option_4" value="4" type="radio" /></td>
                  <td><input name="option_4" value="5" type="radio" /></td>
                </tr>
                <tr>
                  <th >滑雪服</th>
                  <td><input name="option_5" value="1" type="radio" /></td>
                  <td><input name="option_5" value="2" type="radio" /></td>
                  <td><input name="option_5" value="3"  checked="checked"  type="radio" /></td>
                  <td><input name="option_5" value="4" type="radio" /></td>
                  <td><input name="option_5" value="5" type="radio" /></td>
                </tr>               
                <tr>
                   <th rowspan="10">服务</th>
                  <th >停车场</th>
                  <td><input name="option_6" value="1" type="radio" /></td>
                  <td><input name="option_6" value="2" type="radio" /></td>
                  <td><input name="option_6" value="3"  checked="checked"  type="radio" /></td>
                  <td><input name="option_6" value="4" type="radio" /></td>
                  <td><input name="option_6" value="5" type="radio" /></td>
                </tr>
                <tr>
                  <th >餐  饮</th>
                  <td><input name="option_7" value="1" type="radio" /></td>
                  <td><input name="option_7" value="2" type="radio" /></td>
                  <td><input name="option_7" value="3"  checked="checked"  type="radio" /></td>
                  <td><input name="option_7" value="4" type="radio" /></td>
                  <td><input name="option_7" value="5" type="radio" /></td>
                </tr>
                <tr>
                  <th >住 宿</th>
                  <td><input name="option_8" value="1" type="radio" /></td>
                  <td><input name="option_8" value="2" type="radio" /></td>
                  <td><input name="option_8" value="3"  checked="checked"  type="radio" /></td>
                  <td><input name="option_8" value="4" type="radio" /></td>
                  <td><input name="option_8" value="5" type="radio" /></td>
                </tr>
                <tr>
                  <th >教 练</th>
                  <td><input name="option_9" value="1" type="radio" /></td>
                  <td><input name="option_9" value="2" type="radio" /></td>
                  <td><input name="option_9" value="3"  checked="checked"  type="radio" /></td>
                  <td><input name="option_9" value="4" type="radio" /></td>
                  <td><input name="option_9" value="5" type="radio" /></td>
                </tr>
                <tr>
                  <th >现场救护</th>
                  <td><input name="option_10" value="1" type="radio" /></td>
                  <td><input name="option_10" value="2" type="radio" /></td>
                  <td><input name="option_10" value="3"  checked="checked"  type="radio" /></td>
                  <td><input name="option_10" value="4" type="radio" /></td>
                  <td><input name="option_10" value="5" type="radio" /></td>
                </tr>
                <tr>
                  <th >现场管理</th>
                  <td><input name="option_11" value="1" type="radio" /></td>
                  <td><input name="option_11" value="2" type="radio" /></td>
                  <td><input name="option_11" value="3"  checked="checked"  type="radio" /></td>
                  <td><input name="option_11" value="4" type="radio" /></td>
                  <td><input name="option_11" value="5" type="radio" /></td>
                </tr>
                <tr>
                  <th >卫生质量</th>
                  <td><input name="option_12" value="1" type="radio" /></td>
                  <td><input name="option_12" value="2" type="radio" /></td>
                  <td><input name="option_12" value="3"  checked="checked"  type="radio" /></td>
                  <td><input name="option_12" value="4" type="radio" /></td>
                  <td><input name="option_12" value="5" type="radio" /></td>
                </tr>
                <tr>
                  <th >安全防护</th>
                  <td><input name="option_13" value="1" type="radio" /></td>
                  <td><input name="option_13" value="2" type="radio" /></td>
                  <td><input name="option_13" value="3"  checked="checked"  type="radio" /></td>
                  <td><input name="option_13" value="4" type="radio" /></td>
                  <td><input name="option_13" value="5" type="radio" /></td>
                </tr>
                <tr>
                  <th >公交服务</th>
                  <td><input name="option_14" value="1" type="radio" /></td>
                  <td><input name="option_14" value="2" type="radio" /></td>
                  <td><input name="option_14" value="3"  checked="checked"  type="radio" /></td>
                  <td><input name="option_14" value="4" type="radio" /></td>
                  <td><input name="option_14" value="5" type="radio" /></td>
                </tr>
                <tr>
                  <th >其他服务</th>
                  <td><input name="option_15" value="1" type="radio" /></td>
                  <td><input name="option_15" value="2" type="radio" /></td>
                  <td><input name="option_15" value="3"  checked="checked"  type="radio" /></td>
                  <td><input name="option_15" value="4" type="radio" /></td>
                  <td><input name="option_15"  value="5" type="radio" /></td>
                </tr>
              </table>
            
		<form id="dataForm" method="post" action="/VoteController.do?method=saveVote">
 		<input type="hidden" name="result" id="result" value="" />
		<input type="hidden" name="companyId" id="companyId" value="${param.companyId}" />
		
		<%
//判断是否投过票
	Cookie cookies[]=request.getCookies(); 
	Cookie sCookie=null; 
	String svalue=null; 
	String sname=null; 
	
	String companyId = request.getParameter("companyId");
	if(cookies!=null){
		for(int i=0;i<cookies.length;i++) { 
			sCookie=cookies[i]; 
			sname=sCookie.getName(); 
			svalue=sCookie.getValue(); 			
			if(sname.equals(companyId+"_votedone")){//已经投过票
				out.print("<input type='hidden'  id='isVote' value='yes' />");
			break;
			}	
		}
	}
%>
		
         <input type="button" value="提交" id="submitBtn" onclick="saveData();" class="btn" />
               
		 </form>
      </div>
      <!--#conBody-->
    </div>
    <!--#contentMain-->
    <div id="contentSupp" class="hidden" >
      <!--补充内容-->
    </div>
    <!--#contentSupp-->
  </div>
  <!--#sysContainer第一通栏结束-->
  <div id="sysInfo" class="sysInfo hidden">
    <!--系统信息-->
    <div class="guestCenter">
      <h1>客服热线</h1>
      <ul>
        <li>010-88354986-517</li>
      </ul>
      <span>Email:Chengx@chinabidding.com.cn</span> </div>
    <!--.guestCenter-->
    <div class="helpLeaderOther">
      <h1>新手帮助</h1>
      <ul>
        <li><a href="#">如何实现竞价采购</a></li>
        <li><a href="#">如何注册和身份验证</a></li>
        <li><a href="#">如何成为推广员</a></li>
      </ul>
    </div>
    <!--.helpLeaderOther-->
    <div class="helpLeaderOther">
      <h1>客户意见</h1>
      <ul>
        <li><a href="#">新版建议</a></li>
        <li><a href="#">我要投诉</a></li>
      </ul>
    </div>
    <!--.helpLeaderOther-->
    <div class="helpLeaderOther">
      <h1>加入我们</h1>
      <ul>
        <li><a href="#">成为采购人</a></li>
        <li><a href="#">成为供应商</a></li>
        <li><a href="#">成为推广员</a></li>
      </ul>
    </div>
    <!--.helpLeaderOther-->
    <div class="helpLeaderOther">
      <h1>用户调查</h1>
      <ul>
        <li><a href="#">使用满意度调查</a></li>
      </ul>
    </div>
    <!--.helpLeaderOther-->
  </div>
  <!--#sysInfo-->
  <div class="foot">
    <div class="footLink"> <a href="#">关于陶朱公阳光易购</a><a href="#">广告服务</a><a href="#">合作伙伴</a><a href="#">帮助中心</a><a href="#">诚征英才</a><a href="#">联系我们</a><a href="#">网站地图</a><a href="#">版权说明</a><a href="#">加入商城</a> </div>
    <!--.footLink-->
    Copyright © 2001-2009 中国采购与招标网 Rights Reserved 京ICP备09089782号 北京市公安局海淀分局备案编号1101081890<br />
    本站网络实名/通用网址："中国采购与招标网"<br />
    信息发布热线：86-010-82744233、入会咨询：86-010-82743196、客服热线：86-010-82743157 、广告热线：86-10-68350571 更多联系电话...<br />
  </div>
  <!--.foot-->
</div>
<!--页面容器 结束-->
<div id="extraDiv">
  <!--扩展用容器，用于与内容无关的装饰性扩展-->
  <div id="extraDiv1"></div>
  <div id="extraDiv2"></div>
  <div id="extraDiv3"></div>
  <div id="extraDiv4"></div>
  <div id="extraDiv5"></div>
  <div id="extraDiv6"></div>
</div>
</body>
</html>

<script>
function hasVote(){
	if($("#isVote").val()=='yes'){
		alert("该雪场你已经评过分， 不能再评分！");
		//$('#submitBtn').attr('disabled',"true");
		//$('#button').removeAttr("disabled"); 移除disabled属性 
		 window.location.href = $('#initPath').val()+"/view/smallscale/tempvote/hxindex.jsp";
		return;
	}
}

   function saveData(){
	
	var count = 0;
	var ck = null;
	var result = "";
	var name = "";
	for(var index=0;index<=15;index++){
		name = "option_"+index;
	   ck = document.getElementsByName(name);
	   for ( var i = 0; i < ck.length; i++) {
		   if (ck[i].checked) {
			   count ++;
			   result += name+"#" + ck[i].value + ";;";
			   
		   }
	   }
	}

	//if(count<16){
	//	alert("请完成所有选项再提交!");
	//	return;
	//}		
  
   $('#result').attr('value',result);    
	$.getJSON($('#initPath').val()+'/VoteController.do?method=saveVote', formToJsonObject('dataForm'), function(json){		
		if(json.failure){alert(json.result); return};		
			alert("提交成功，谢谢你的参与！");
		 window.location.href = $('#initPath').val()+"/view/smallscale/tempvote/hxindex.jsp";		
				
	});	}  

function backIndex(){
	window.location.href = $('#initPath').val()+"/view/smallscale/tempvote/hxindex.jsp";
}
</script>