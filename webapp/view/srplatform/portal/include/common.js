/*
 ******** ******** ********
 *
 * common.js 系统共用方法
 * 
 *
 * by:Anson,E-mail:chents@gpcsoft.com
 *
 * Depends:
 ******** ******** *********/
var common = {};
common.loginUrl;
common.indexUrl;
common.useStaticIndexPage = false; //标记是否使用首页静态页面

common.array = ["","采购需求","采购人","商品","供应商","专家","酒店","礼品"];

common.unlogin=function(){//退出
	if(confirm('确定要退出吗？')){
		$.ajax({
			url : $("#initPath").val()+"/logout.do",
			type :"POST",
			success:function(msg){
				window.location.href = common.indexUrl;
			}
		});
	}
}

//异步获取当前登录用户，并写到页面顶部
common.getCurrUser=function(user){
	var html = '您好，<span class="currentUser" id="loginUser"><a href="javascript:void(0);" id="'+user.cuid+'">'+user.cuname+'</a></span>&nbsp;<span id="logout"><a href="javascript:common.unlogin()">[退出]</a></span>';
	html += '&nbsp;<span id="security" class=""><span class="modifier">为了你的密码安全，请填写[<a href="javascript:common.addSecurityQuestions()">密保资料</a>]</span></span>';
	
	$('p[class=userInfo]').html(html);
	$('div[class=header]').find('input[id=roleType]').val(user.rolestr);
	$("#globleOrg").val(user.coid);
	
	//显示"电子采购室"
	if(user.hasXejy=='true' && $('#quickMenu').find('a[class=dzcghome]').length==0){
		$('#quickMenu').prepend('<li><a href="javascript:void(0);" class="dzcghome" onclick="common.goToModelIndex();return false;">电子采购室</a></li>');
	}
	//显示"电子招标室"
	if(user.hasZtb=='true' && $('#quickMenu').find('a[class=ztbhome]').length==0){
		$('#quickMenu').prepend('<li><a href="javascript:void(0);" class="ztbhome" onclick="common.goToModelIndexES();return false;">电子招标室</a></li>');
	}
}

//判断用户是否登录,如果传入值，则有提示框，否则只有返回值
common.isLogin=function(isAlert,hasOrg){
	 if($("#loginUser") !=null && $("#loginUser").length > 0){
		if (hasOrg && $("#globleOrg").val() == 'null'){
				alert("您不是企业用户！暂不支持此操作！");
				return false ;
		} 
		return true;
	} else {
		if(isAlert){
			var url = $('#initPath').val()+"/IndexViewController.do?method=toLogin&viewName=loginDivView"; 
			$.epsDialog({
		        title:'系统登录',
		        url:url,
		        width:640,
		        height:400,
		        maxWin:false
		    })
		}
		return false;
	}
}

//当前用户是否有某种角色
common.isHasRole = function(roleTyle) {
	if($("#roleType").val().indexOf(roleTyle) > -1) {
		return true;
	}else {
		return false;
	}
}

//展示或隐藏更多数据
common.showOrHiddenMore=function(conId,viewId,text){
	if($("#"+conId).hasClass("siDownGray")) {
		$("#"+conId).removeClass("siDownGray").addClass("siUpGray");
		$("#"+conId).empty().html("精简显示"+text);
		$("#"+viewId).removeClass("expand");
	}else{
		$("#"+conId).removeClass("siUpGray").addClass("siDownGray");
		$("#"+conId).empty().html("显示全部"+text);
		$("#"+viewId).addClass("expand");
	}
}

//排序
common.order = function(orderObj){
	if($(orderObj).hasClass("siUp")) {
		$(orderObj).removeClass("siUp").addClass("siDown");
	}
	else {
		$(orderObj).removeClass("siDown").addClass("siUp");
	}
}

//加入收藏
common.addFavorites = function(favoriteId,favoriteName,favoriteType){
	//判断是否登录
	if(common.isLogin(true,"请先登录再进行收藏！")){
		$.epsDialog({
	        title:'加入收藏',
	        width:400,
	        height:150,
	        url:$('#initPath').val()+'/FavoritesController.do?method=toFavoritesForm&favoriteId='+favoriteId+'&favoriteName='+encodeURIComponent(favoriteName)+'&favoriteType='+favoriteType
		});
	}
}

//显示代理机构和供应商详情
common.showOrgInfo=function(objId,type){ 
	fnHiddenSub(); 
	if(type == "supplier") {
		$("#contentMain").empty().loadPage($('#initPath').val()+"/SupplierShowController.do?method=getSupplierInfo&objId=" + objId);
		$("#contentSupp").empty().loadPage($('#initPath').val()+'/SupplierShowController.do?method=getRecommendSupplierInfo&rp=5&page=1');
	}else {
		$("#contentMain").empty().loadPage($('#initPath').val()+"/AgencyShowController.do?method=getAgencyInfo&objId=" + objId);
		$("#contentSupp").empty().loadPage($('#initPath').val()+'/AgencyShowController.do?method=getRecommendAgency&rp=5&page=1');
	}
}

//填写密保资料
common.addSecurityQuestions=function(){
	$.epsDialog({
        title : '填写密保资料',
		url : $('#initPath').val()+"/UserSecurityController.do?method=toSecurityForm"
    }); 
}

//验证用户是否填写密保
common.checkUserSecurity=function(){
	$.getJSON($('#initPath').val()+'/UserSecurityController.do?method=checkSecurity&userId='+$("#loginUser a").attr("id"), function(json){
		if(json.result=='success'){
			$("span[id=security]").hide();
		}else{
			$("span[id=security]").removeClass("hidden").show();
		}
	});
	
}

//打开一个新的窗口url路径，param参数
common.open=function(url,param){
	window.open(url+param);
}

//跳转到购物车
common.goinToShopCart = function(){
	if(common.isLogin(true,true )){
		var targetUrl = $("#initPath").val()+"/ShoppingCartController.do?method=toMyShoppinCart&isOutCss=true";
		//$("#conBody").html()?$("#conBody").loadPage( targetUrl ):$("#sysContent").loadPage( targetUrl );
		window.open(targetUrl);
	}
}

//刷新购物车信息
function getCartInfo(){
	//加载购物车信息
	$.getJSON($('#initPath').val()+'/ShoppingCartController.do?method=findMyShoppingCar',{},function(json){
		if(!json.rows || !json.rows.length || json.rows.length==0){
			$('em[id=shoppingCartGoodsTotal]').html('0');
			return;
		}
		$.each(json.rows,function(i,n){
			$('em[id=shoppingCartGoodsTotal]').html(formatAmount(n.shoppingCart.goodsQty));
		});
	});
}

//顶部菜单切换样式
function changeTabsCss(hrefId){
	$("ul.navMain li").removeClass('selected');
	$("#"+hrefId).parent().addClass("selected");
}

//关键字搜索类型切换事件
function keyWordTypeChange(obj, value){
	$(obj).parent().siblings().removeClass('selected');
	$(obj).parent().addClass('selected');
	$("#searchType").val(value);
}

//关键字搜索
function keyWordSearch(keyWord){
	//模糊搜索:1=找商品；2=找公告；3=找供应商；4=找专家
	var searchType = $("[id=searchType]").val();
	var value = native2ascii(strIgnore($('#keyWords').val().replace("请输入关键字","")));
	//var value = base64encode( encodeURIComponent(strIgnore($('#keyWords').val().replace("请输入关键字","")) ) );
	
	if(keyWord){//传过来关键字
		value = native2ascii(strIgnore(keyWord));
		//value = base64encode(strIgnore(keyWord));//base64转码
	}
	
	fnRemoveOtherMain();
	var contentUrl;
	var sysContentUrl;
	
	if(searchType!=null && "1"==searchType){ //找商品
		sysContentUrl = $('#initPath').val()+'/GoodsShowController.do?method=toGoodsList&rp=21&page=1&keyWord='+value;
	}
	else if(searchType!=null && "2"==searchType){ //找公告
		sysContentUrl = $('#initPath').val()+'/ProjectShowController.do?method=toShowBargainProjectList&rp=21&page=1&keyWord='+value;
	}
	else if(searchType!=null && "3"==searchType){ //找供应商
		sysContentUrl = $('#initPath').val()+'/SupplierShowController.do?method=toSupplierList&rp=21&page=1&districtLevel=1&keyWord='+value;
	}
	else if(searchType!=null && "4"==searchType){ //找专家
		sysContentUrl = $('#initPath').val()+'/ExpertShowController.do?method=toExpertList&rp=21&page=1&districtLevel=1&keyWord='+value;
	}
	window.location.href = sysContentUrl+"&searchType="+searchType;
	return false;
}

//跳转到企业商铺页面
common.goToOrgShop = function(orgInfoId,type){
	var url = $('#initPath').val()+"/OrgShop/"+orgInfoId+"/";
	if(type) {
		if(type == "CGAL") {
			url += "CGAL";
		}
	}
	window.open(url);
}

//跳转到社区首页
common.goToCommunity = function(communityId,topicId){
	var url = $('#initPath').val()+"/CommunityShowController.do?method=toCommunityIndexView&communityId="+communityId;
	if(topicId) {
		url += "&topicId="+topicId;
	}
	window.open(url);
}

//跳转到公告详情页面
common.goToBulletinDetail = function(projectId, bulletinType){
	window.open( $('#initPath').val()+'/BulletinInfo/'+projectId+''+bulletinType );
	return false;
}

//跳转到专家详情
common.goToExpertDetail = function(expertId){
	window.open( $('#initPath').val()+'/ExpertInfo/'+expertId);
	return false;
}

//跳转到采购人详情
common.geToBuyerDetail = function(buyerId,tabId){
	window.open( $('#initPath').val()+'/BuyerInfo/'+buyerId+'/'+(tabId?tabId:""));
}

//跳转到商品详情
common.geToGoodsDetail = function(goodsId){
	window.open( $('#initPath').val()+'/GoodsInfo/'+goodsId);
}


//点击电子采购室
common.goToModelIndex = function(){
	window.location.href = $('#initPath').val()+"/ModelIndexController.do?method=toDeskTopIndex&viewName=deskTopIndexView";
	return false;
}

//点击电子招标室
common.goToModelIndexES = function(){
	window.location.href = $('#initPath').val()+"/ModelIndexController.do?method=toDeskTopIndex&viewName=deskTopIndexViewES";
	return false;
}

//跳转到登录页面
common.goToLogin = function(){
	window.location.href = $('#initPath').val()+"/IndexViewController.do?method=toLogin";;
	return false;
}

//跳转到注册页面
common.goToRegistration = function(){
	window.open($('#initPath').val()+'/RegistrationController.do?method=toRegistration');
	return false;
}

$(document).ready(function(){
	//系统首页路径和登录路径
	common.loginUrl = $('#initPath').val()+"/IndexViewController.do?method=toLogin";  //"https://casweb:8043/cas/login";
	common.indexUrl = $('#initPath').val()+"/IndexViewController.do?method=index";
	
	//处理密码保护
	common.checkUserSecurity();
	
	//全局返回按钮事件
	$('button[name=historyBackBtn]').live('click',function(){
		
		//如果有链接的地址优先跳转
		if($("#hrefUrl").val() != '' && $("#hrefUrl").val() != '0'){
			window.location.href= $("#hrefUrl").val()
			return;
		}
		
		if($("#returnUrl").val() != '' && $("#returnUrl").val() != '0'){
			$('#conBody').loadPage($("#returnUrl").val());
		}
		return false;
	});
	
	//点击顶部菜单
	$('#navMain ul li a').live("click",function(){
		//changeTabsCss($(this).attr("id"));
	})
	
	// 左边菜单的点击事件
	$('.subnav>li').find("a").live("click",function(){
		$('.subnav>li').removeClass('selected');
		$(this).parent().addClass('selected');
	})
	
	$('#contentSub>ul li').find('a').live("click",function(){
		if($(this).parent().hasClass("selected"))
			$(this).parent().removeClass('selected');
		else
			$(this).parent().addClass('selected');
	})
	
	//跳转登录
	$("a[name=toLogin]").click(function(){
		window.location.href = common.loginUrl;
		return false;
	})
	
	//跳转注册
	$("a[name=toRegistration]").click(function(){
		window.open($('#initPath').val()+'/RegistrationController.do?method=toRegistration');
		return false;
	})
	
	//跳转到首页	
	$('#goToIndex').click(function(){
		if(common.useStaticIndexPage){
			window.location.href = $('#initPath').val()+"/view/srplatform/staticpage/static_index.html";
		}else{
			window.location.href = $('#initPath').val()+"/IndexViewController.do?method=index";
		}
		return false;
	});

	//跳转到商品库	
	$('#goToGoods').click(function(){
		if(common.useStaticIndexPage){
			window.location.href = $('#initPath').val()+"/view/srplatform/staticpage/static_goods_index.html";
		}else{
			window.location.href = $('#initPath').val()+"/GoodsShowController.do?method=toShowGoodsIndexView";
		}
		return false;
	});
	
	//跳转到电子招标
	$('#goToBidding').click(function(){
		if(common.useStaticIndexPage){
			window.location.href = $('#initPath').val()+"/view/srplatform/staticpage/static_bidding_index.html";
		}else{
			window.location.href = $('#initPath').val()+"/BulletinShowController.do?method=toShowBiddingIndexView";
		}
		return false;
	});
	
	//跳转到电子采购
	$('#goToBargain').click(function(){
		if(common.useStaticIndexPage){
			window.location.href = $('#initPath').val()+"/view/srplatform/staticpage/static_bargain_index.html";
		}else{
			window.location.href = $('#initPath').val()+"/BulletinShowController.do?method=toShowBargainIndexView";
		}
		return false;
	});

	//跳转到供应商库	
	$('#goToSupplier').click(function(){
		if(common.useStaticIndexPage){
			window.location.href = $('#initPath').val()+"/view/srplatform/staticpage/static_supplier_index.html";
		}else{
			window.location.href = $('#initPath').val()+'/SupplierShowController.do?method=toShowSupplierIndexView';
		}
		return false;
	}); 
	
	//跳转到精品商城	
	$('#goToGoodsMall').click(function(){
		window.location.href = $('#initPath').val()+'/GroupBuyingShowController.do?method=toShowGroupBuyingIndexView';
		return false;
	});
	
	//点击小额采购室
	$("#numberAreaXY,a[name=numberArea]").click(function(){		
		window.location.href = $('#initPath').val()+"/ModelIndexController.do?method=toDeskTopIndex&viewName=deskTopIndexView";
		return false;
	})
	
	//点击招投标室
	$("#numberAreaZT,a[name=numberAreaZT]").click(function(){		
		window.location.href = $('#initPath').val()+"/ModelIndexController.do?method=toDeskTopIndex&viewName=deskTopIndexViewES";
		return false;
	})
	
	//跳转补充资料
	$("a[name=addMyInfo]").live('click',function(){		
		window.location.href = $('#initPath').val()+"/ModelIndexController.do?method=toDeskTopIndex";
		return false;
	})

	//修改账户信息
	$("#loginUser").live('click',function(){
		$.epsDialog({
	        title:'修改账户信息',
			url:$('#initPath').val()+'/view/pubservice/common/update_user_info.jsp'
		}); 
		return false;
	})
	
	//关键字搜索鼠标悬停
	$("#keySearchKind").hover(
		function(){$("#keySearchKind").addClass("ts-cat-expand");}
		,
		function(){$("#keySearchKind").removeClass("ts-cat-expand");}
	)
	
	//关键字获得焦点事件
	$("#keyWords").focus(
		function(){$("#keyLabel").addClass("hidden");}
	).blur(
		function(){if($("#keyWords").val() == "") $("#keyLabel").removeClass("hidden");
	})
	
	$("#keyLabel").click(function(){
		$("#keyWords").focus();
	})
	
	
	/*++++++++
	+ 
	+ 设定链接跳转目标，记录操作历史，对应loadPage功能，增加iframe用法
	+ 
	++++++++*/
	$("a,button.buttonLink").live("click",function(ev){	
		
		var targetID = this.getAttribute("target");//取目标链接target值
		var href = this.getAttribute("href");//取目标链接地址
		var thisId = this.id;
		if(targetID&&targetID.indexOf("#")!=-1) {
			if(thisId&&$('#contentSub > a #'+thisId)){
				var f = $('#navMain>ul>li>a.homePage').clone(true);
				var s = $('#'+thisId).parent().parent().parent().find("a:first").clone(true);
				var t = $('#'+thisId).clone(true);
				$('#conTitle .navCurrent').removeClass("hidden").empty().append(f).append(s).append(t);
				$("#currentTab").val(0); //初始化tab定位
			}
			$(targetID).loadPage(href,null,function(){
				if('#conBody'==targetID){
					current_menu_href = href;
				}
			});
			return false;
		}
	});
	
	//cms首页上的超链接,需要加载左边栏
	$(".cmsHref_self").live("click",function(){
		var targetUrl = $(this).attr("id");
		window.location.href = $('#initPath').val()+targetUrl ;
		return false;
	})
	
	$(".cmsHref_blank").live("click",function(){
		var targetUrl = $(this).attr("id");
		window.open( $('#initPath').val()+targetUrl);
		return false;
	})
	
	//监听回车事件
	$('body').bind("keypress", function(event){
		if (event.keyCode == 13 && $(".enterBtn")) {								   
			$(".enterBtn").click();
			return false;
		}
	});
	
	//帮助中心跳转
	$("a[name=helpcenter]").live("click",function(ev){	
		var targetUrl = $('#initPath').val()+"/HelpCenterController.do?method=toHelpCenter";
		window.open(targetUrl);
	}); 
	
	//Google
	var _gaq = _gaq || [];
	_gaq.push(['_setAccount', 'UA-25058085-1']);
	_gaq.push(['_trackPageview']);

	(function() {
		var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		ga.src = ('https:' == document.location.protocol ? ' https://ssl' : ' http://www') + '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	})();
})

