<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>2010北京及周边地区我最满意的滑雪场评选活动</title>
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
<script src="SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<style>
html,body{ background:none; height:100%; width:100%;}
#extraDiv1{ height:489px; background:url(indexHxBg.png) no-repeat center; z-index:-1; border:0px;}
#extraDiv2{background:url(hxbg.jpg) repeat fixed; height:100%; width:100%; position:fixed; z-index:-2; top:0px; }
.sysContent{ overflow:hidden; background:#fff;}
.hxBody{ overflow:hidden; background:#f0fafc}
.adheader{ margin:0px; padding:0px; height:400px;}
.adheader .bmb{ background: #9F3;display: block;height: 36px;margin-left: 813px;margin-top: 321px;width: 120px; position:absolute; background:url(btn.jpg) no-repeat; border:0px; padding:0px; line-height:36px; text-align:center;}
</style>
</head>
<body>
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
  <div class="adheader"><a href="pingxuan.doc" class="bmb"></a></div>
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

function toVote(companyId){
	  window.location.href = "vote_form.jsp?companyId="+companyId;
	  
	  //window.location.href = $('#initPath').val()+'/VoteController.do?method=toVoteView&companyId='+companyId;
}



</script>
      </ul>
    </div>
  </div>
  <!--.header-->
  <div id="sysContent" class="sysContent" >
    <!--系统内容-->
    <div id="contentSub" class="contentSub hxSub">
      <div class="hxSubCol">
        <h2>活动组织单位</h2>
        <h3>1.主办方：</h3>
        <ul>
          <li><a href="http://www.ebid360.com/" target="_blank"><img src="weby.jpg" /></a></li>
          <li><a href="http://www.chinabidding.com.cn/zbw/index.jsp" target="_blank"><img src="webz.jpg" /></a></li>
          <li><a href="http://www.paihang360.com/" target="_blank"><img src="webm.jpg" /></a></li>
        </ul>
        <h3>2.协办方:</h3>
        <ul>
          <li><a href="http://www.zbsonline.com/" target="_blank"><img src="webzb.jpg" /></a></li>
          <li><a href="http://www.togo815.com/" target="_blank"><img src="webt.jpg" /></a></li>
          <li><a href="#" target="_blank"><img src="webqlb.jpg" /></a></li>
        </ul>
        <h3>3.支持媒体</h3>
        <ul>
          <li><a href="http://www.bjski.cn/ski/" target="_blank"><img src="webb.jpg" /></a></li>
          <li><a href="http://www.weaven.net/" target="_blank"><img src="webk.jpg" /></a></li>
          <li><a href="#">滑雪QQ圈</a></li>
        </ul>
      </div>
      <!--.hxSubCol左边第一卷-->
      <div class="hxSubCol  hxSubCol2">
        <h2><center>联系方式</center></h2>
        <ul>
          <li><span>评价专员：</span>张丽兵</li>
          <li><span>手    机：</span>13581984575</li>
          <li><span>邮    箱：</span>zhanglb@paihang360.com</li>
          <li><span>电    话：</span>010-88354986转585</li>
          <li><span>传    真：</span>010-88354985</li>
        </ul>
      </div>
      <!--.hxSubCol左边第二卷-->
    </div>
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
      <div id="conBody" class="conBody hxBody" >
        <div class="hxBodyCol">
          <h2>关于开展我最满意的滑雪场评选活动的通知</h2>
          <p>北京及周边地区各滑雪场：</p>
          <p>滑雪作为一项季节性的户外运动，越来越受到都市白领的喜爱，仅北京郊区就有14家滑雪场，接待全国各
            地的滑雪爱好者。由于各滑雪场的景区环境、基础设施、服务质量等参差不齐，游客的满意度也各不相同，为
            给广大滑雪爱好者提供消费指引，中国名企排行网、阳光易购、开心乐购、通购网、北京滑雪网、滑雪QQ圈等
            共同发起对北京及周边地区的滑雪场开展我最满意的滑雪场评价活动。</p>
          <p>请各滑雪场踊跃参与，接受客户和公众的评价。</p>
          <p class="lastP">北京及周边地区滑雪场调查评价办公室<br />二零一零年十二月十六日</p>
          
        </div>
        <!--.hxBodyCol第一卷-->
        <div class="hxBodyCol">
          <h2>一、评价时间：</h2>
          <p>2010年12月16日--2011年1月上旬;</p>
        </div>
        <!--.hxBodyCol第二卷-->
        <div class="hxBodyCol">
          <h2>二、评价方式：</h2>
          <p>1、网上评价，客户通过网络对调查问卷进行打分评价（占70%）；</p>
          <p>2、团购客户现场体验后进行打分评价（占30%）</p>
        </div>
        <!--.hxBodyCol第三卷-->
        <div class="hxBodyCol">
          <h2>三、评价流程图示</h2>
          <div class="lc">
          <ul>
          <li>递交材料</li>
          <li>公布评论</li>
          <li>核实数据</li>
          <li class="nobg">颁发奖章</li>
          <li class="nobg">时间：2010年12月16日--2011年1月上旬</li>
          </ul>         
          </div>

        </div>
        <!--.hxBodyCol第四卷-->
        <div class="hxBodyCol">
          <h2>四、奖励和服务</h2>
          <p>1、对上榜企业授予荣誉证书；</p>
          <p>2、通过合作媒体进行宣传推介；</p>
          <p>3、在百度进行榜单宣传推广；</p>
          <p>4、上榜滑雪场有资格进入阳光易购平台滑雪爱好者社区、与滑雪爱好者团购圈子对接开展活动；</p>
        </div>
        <!--.hxBodyCol第五卷-->
        <div class="hxBodyCol lastCol">
          <h2>五、参与名单</h2>
          <div>
            <ul class="firttUl">
              <li><a href="#"><img src="img/jds.jpg" /></a></li>
              <li><span>昌平军都山滑雪场</span>
                <input type="button" value="我要投票" class="btn" onclick="toVote('1')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_1">0</label></li>
            </ul>
            <ul>
              <li><a href="#"><img src="img/xsj.jpg"/></a></li>
              <li><span>昌平雪世界滑雪场</span>
                <input type="button" value="我要投票"  class="btn"  onclick="toVote('2')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_2">0</label></li>
            </ul>
            <ul>
              <li><a href="#"><img src="img/yj.jpg" /></a></li>
              <li><span>房山云居滑雪场</span>
                <input type="button" value="我要投票" class="btn"  onclick="toVote('3')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_3">0</label></li>
            </ul>
            <ul class="firttUl">
              <li><a href="#"><img src="img/wlby.jpg" /></a></li>
              <li><span>丰台万龙八易滑雪场</span>
                <input type="button" value="我要投票" class="btn"  onclick="toVote('4')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_4">0</label></li>
            </ul>
            <ul>
              <li><a href="#"><img src="img/hb.jpg"/></a></li>
              <li><span>怀柔怀北滑雪场</span>
                <input type="button" value="我要投票"  class="btn"  onclick="toVote('5')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_5">0</label></li>
            </ul>
            <ul>
              <li><a href="#"><img src="img/lfs.jpg" /></a></li>
              <li><span>门头沟龙凤山滑雪场</span>
                <input type="button" value="我要投票" class="btn"  onclick="toVote('6')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_6">0</label></li>
            </ul>
            <ul class="firttUl">
              <li><a href="#"><img src="img/ns.jpg" /></a></li>
              <li><span>密云南山滑雪场</span>
                <input type="button" value="我要投票" class="btn"  onclick="toVote('7')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_7">0</label></li>
            </ul>
            <ul>
              <li><a href="#"><img src="img/yfs.jpg"/></a></li>
              <li><span>密云云佛山滑雪场</span>
                <input type="button" value="我要投票"  class="btn"  onclick="toVote('8')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_8">0</label></li>
            </ul>
            <ul>
              <li><a href="#"><img src="img/yy.jpg" /></a></li>
              <li><span>平谷渔阳滑雪场</span>
                <input type="button" value="我要投票" class="btn"  onclick="toVote('9')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_9">0</label></li>
            </ul>
            
            <ul class="firttUl">
              <li><a href="#"><img src="img/lhs.jpg" /></a></li>
              <li><span>顺义莲花山滑雪场</span>
                <input type="button" value="我要投票" class="btn"  onclick="toVote('10')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_10">0</label></li>
            </ul>
            <ul>
              <li><a href="#"><img src="img/qb.jpg"/></a></li>
              <li><span>顺义乔波滑雪场</span>
                <input type="button" value="我要投票"  class="btn"  onclick="toVote('11')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_11">0</label></li>
            </ul>
            <ul>
              <li><a href="#"><img src="img/bdl.jpg" /></a></li>
              <li><span>延庆八达岭滑雪场</span>
                <input type="button" value="我要投票" class="btn"  onclick="toVote('12')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_12">0</label></li>
            </ul>
            <ul class="firttUl">
              <li><a href="#"><img src="img/sjlh.jpg" /></a></li>
              <li><span>延庆石京龙滑雪场</span>
                <input type="button" value="我要投票" class="btn"  onclick="toVote('13')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_13">0</label></li>
            </ul>
            <ul>
              <li><a href="#"><img src="img/xscb.jpg"/></a></li>
              <li><span>延庆雪山城堡滑雪场</span>
                <input type="button" value="我要投票"  class="btn"  onclick="toVote('14')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_14">0</label></li>
            </ul>
            <ul>
              <li><a href="#"><img src="img/bygj.jpg" /></a></li>
              <li><span>白云国际滑雪场</span>
                <input type="button" value="我要投票" class="btn"  onclick="toVote('15')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_15">0</label></li>
            </ul>
            
            <ul class="firttUl">
              <li><a href="#"><img src="img/css.jpg" /></a></li>
              <li><span>东北吉华长寿山滑雪场</span>
                <input type="button" value="我要投票" class="btn"  onclick="toVote('16')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_16">0</label></li>
            </ul>
            <ul>
              <li><a href="#"><img src="img/bdh.jpg"/></a></li>
              <li><span>吉林北大湖滑雪场</span>
                <input type="button" value="我要投票"  class="btn"  onclick="toVote('17')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_17">0</label></li>
            </ul>
            <ul>
              <li><a href="#"><img src="img/els.jpg" /></a></li>
              <li><span>东北龙珠二龙山滑雪场</span>
                <input type="button" value="我要投票" class="btn"  onclick="toVote('18')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_18">0</label></li>
            </ul>
            
            <ul class="firttUl">
              <li><a href="#"><img src="img/ryx.jpg" /></a></li>
              <li><span>东北铁力日月峡滑雪场</span>
                <input type="button" value="我要投票" class="btn"  onclick="toVote('19')"/>                
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_19">0</label></li>            
            </ul>
            <ul>
              <li><a href="#"><img src="img/ybl.jpg"/></a></li>
              <li><span>东北亚布力滑雪场</span>
                <input type="button" value="我要投票"  class="btn"  onclick="toVote('20')"/>                
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_20">0</label></li>
            </ul>
            <ul>
              <li><a href="#"><img src="img/yblxh.jpg" /></a></li>
              <li><span>东北亚布力新濠滑雪场</span>
                <input type="button" value="我要投票" class="btn"  onclick="toVote('21')"/>                
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_21">0</label></li>
            </ul>
            <ul class="firttUl">
              <li><a href="#"><img src="img/ccl.jpg" /></a></li>
              <li><span>河北长城岭滑雪场</span>
                <input type="button" value="我要投票" class="btn"  onclick="toVote('22')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_22">0</label></li>
            </ul>
            <ul>
              <li><a href="#"><img src="img/dlm.jpg"/></a></li>
              <li><span>河北多乐美地滑雪场</span>
                <input type="button" value="我要投票"  class="btn"  onclick="toVote('23')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_23">0</label></li>
            </ul>
            <ul>
              <li><a href="#"><img src="img/wl.jpg" /></a></li>
              <li><span>河北万龙滑雪场</span>
                <input type="button" value="我要投票" class="btn"  onclick="toVote('24')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_24">0</label></li>
            </ul>

            <ul class="firttUl">
              <li><a href="#"><img src="img/jyt.jpg" /></a></li>
              <li><span>长春净月潭滑雪场</span>
                <input type="button" value="我要投票" class="btn"  onclick="toVote('25')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_25">0</label></li>
            </ul>
            <ul>
              <li><a href="#"><img src="img/cclhs.jpg"/></a></li>
              <li><span>长春莲花山滑雪场</span>
                <input type="button" value="我要投票"  class="btn"  onclick="toVote('26')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_26">0</label></li>
            </ul>
            <ul>
              <li><a href="#"><img src="img/xlh.jpg" /></a></li>
              <li><span>长春新立湖滑雪场</span>
                <input type="button" value="我要投票" class="btn"  onclick="toVote('27')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_27">0</label></li>
            </ul> 
            
            <ul class="firttUl">
              <li><a href="#"><img src="img/qps.jpg" /></a></li>
              <li><span>棋盘山滑雪场</span>
                <input type="button" value="我要投票" class="btn"  onclick="toVote('28')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_28">0</label></li>
            </ul>
            <ul>
              <li><a href="#"><img src="img/ndm.jpg"/></a></li>
              <li><span>呼伦贝尔那达慕</span>
                <input type="button" value="我要投票"  class="btn"  onclick="toVote('29')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_29">0</label></li>
            </ul>
            <ul>
              <li><a href="#"><img src="img/jlpts.jpg" /></a></li>
              <li><span>吉林炮台山滑雪场</span>
                <input type="button" value="我要投票" class="btn"  onclick="toVote('30')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_30">0</label></li>
            </ul>         
          
           <ul class="firttUl">
              <li><a href="#"><img src="img/ps.jpg" /></a></li>
              <li><span>天津盘山滑雪场</span>
                <input type="button" value="我要投票" class="btn"  onclick="toVote('31')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_31">0</label></li>
            </ul>
            <ul>
              <li><a href="#"><img src="img/xlxs.jpg"/></a></li>
              <li><span>西岭雪山</span>
                <input type="button" value="我要投票"  class="btn"  onclick="toVote('32')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_32">0</label></li>
            </ul>
            <ul>
              <li><a href="#"><img src="img/gcl.jpg" /></a></li>
              <li><span>弓长岭滑雪场</span>
                <input type="button" value="我要投票" class="btn"  onclick="toVote('33')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_33">0</label></li>
            </ul>
             <ul class="firttUl">
              <li><a href="#"><img src="img/ydgj.jpg" /></a></li>
              <li><span>黑河龙珠远东国际滑雪场</span>
                <input type="button" value="我要投票" class="btn"  onclick="toVote('34')"/>
              </li>
              <li><span>参与评选人数：</span><label id="lab_total_34">0</label></li>
              
            </ul>

          </div>
          
        </div>
        <!--.hxBodyCol第六卷-->
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
$.getJSON($('#initPath').val()+'/VoteController.do?method=showCount',{objId:'', includedProperties:''},function(json){
	if(json.result)alert(json.result);if(json.failure)return;
	
	$.each(json.voteCounts,function(index,obj){			
		//$("#lab_total_"+obj.orgInfoId).attr("value" ,obj.voteCount)  ;	
		document.getElementById("lab_total_"+obj.orgInfoId).innerHTML=""+obj.voteCount ;	
	});	    		
});
</script>