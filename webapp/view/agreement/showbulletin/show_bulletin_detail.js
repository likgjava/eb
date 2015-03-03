var ShowBulletinDetail={};
ShowBulletinDetail.signUpInterval = {};

//定时刷新剩余报名时间
ShowBulletinDetail.flushRemainSignUpTime=function(){
	var remainTime = parseInt($("#remainSignUpTime").attr("name"));
	if(remainTime <= 0){
		clearInterval(ShowBulletinDetail.signUpInterval);
		$("#remainSignUpTime").html($("#remainSignUpTime").attr('tipname')+'已结束');
		$("#signUpBut").removeClass().addClass('base_btns1_disabled').attr('disabled',true);
		return ;
	}
	$("#remainSignUpTime").html(getRemainTime(remainTime));
	$("#remainSignUpTime").attr("name", (remainTime-1000));
}

//供应商报名
ShowBulletinDetail.signUp = function(projectId, bullType){
	//判断是否登录
	if(!common.isLogin(true,true)){ return; }
	//判断是否为供应商
	//if(!common.isHasRole("s")) {alert("您还不是供应商，请申请为供应商再做此操作！");return;} by yucy

	//判断是否报过名
	$.getJSON($("#initPath").val()+"/SupplierSignupController.do?method=ifHasSignUp",{"projectId":projectId},function(json){
		if(json.success){
			//招标公告报名
			var currentOrgId = $('#currentOrgId').val();
			if(bullType=='01' && currentOrgId!=null && currentOrgId!=''){
				$.epsDialog({
					title:'我要报名',
					url:$('#initPath').val()+'/SignUprecordController.do?method=toPage&projectId='+projectId+'&orgInfoId='+currentOrgId,
					width: '850',
					height: '350',
					onClose: function(){
						window.location.reload();
					}
				});
			}
			//竞价公告报名
			else{
				$.epsDialog({
					id:"registrationDiv",
					url:$("#initPath").val()+"/SupplierSignupController.do?method=toSupplierSignUp&projectId="+projectId,
					title:"供应商报名",
					maxWin:false,
					onClose: function(){
						//保存关注我的客户
						var buyerOrgInfoId = $('#buyerOrgInfoId').val();
						buyerOrgInfoId = buyerOrgInfoId + ",";
						$.getJSON($('#initPath').val()+"/ConcernController.do?method=saveClientConcern",{"orgInfoIds":buyerOrgInfoId,"projectCreator":$('#projectCreator').val()},function(json){
						});
						if($("#signUpSuccess").val() == '1'){
							window.location.reload();
						}
					}
				})
			}
		}else{
			alert(json.result);
		}
	});
}

//查看项目详情
ShowBulletinDetail.showProject = function(projectId, userType) {
	//招标公告
	if($("#bulletinType").val() == '01'){
		window.location.href = $('#initPath').val()+"/ModelIndexController.do?method=toDeskTopIndex&viewName=deskTopIndexViewES";
		return false;
	}
	//竞价公告
	else{
		if(userType=='publisher' || userType=='agency'){userType = 'buyer';}
		window.location.href = $("#initPath").val()+"/BargainProjectController.do?method=toProjectView&userType="+userType+"&projectId="+projectId;
		return false;
	}
}

//跳转注册
ShowBulletinDetail.register = function(){
	window.open($('#initPath').val()+'/RegistrationController.do?method=toRegistration');
	return false;
}

//登录系统
ShowBulletinDetail.login = function(){
	var url = $('#initPath').val()+"/IndexViewController.do?method=toLogin&viewName=loginDivView"; 
	$.epsDialog({
		title:'系统登录',
		url:url,
		width:640,
		height:400,
		maxWin:false,
		onClose: function(){
			if($("#loginSuccess").val() == '1'){
				window.location.reload();
			}
		}
	})
}

//采购人发布采购需求
ShowBulletinDetail.createRequirement = function(){
	//判断是否登录
	if(!common.isLogin(true,true)){ return; }
	//判断是否为采购人
	if(!common.isHasRole("b")) {alert("您还不是采购人，请申请为采购人后再做此操作！");return;}
	
	window.open($('#initPath').val()+'/RequirementController.do?method=toChooseCategory');
	return false;
}

//采购人发布招标项目
ShowBulletinDetail.createBidProject = function(){
	//判断是否登录
	if(!common.isLogin(true,true)){ return; }
	//判断是否为采购人
	if(!common.isHasRole("b")) {alert("您还不是采购人，请申请为采购人后再做此操作！");return;}
	
	window.open($('#initPath').val()+'/ModelIndexController.do?method=toDeskTopIndex&viewName=deskTopIndexViewES');
	return false;
}

//打印项目公告
ShowBulletinDetail.printBulletin = function(){
	window.open($("#initPath").val()+"/view/agreement/showbulletin/bulletin_print_preview.jsp?objId="+$("#bulletinObjId").val());
}

//收藏项目公告
ShowBulletinDetail.favoritesBulletin = function(favoriteId,favoriteName,favoriteType){
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

//推荐项目公告（通过发送邮件分享给好友）
ShowBulletinDetail.shareBulletin = function(bulletinId){
	//判断是否登录
	if(common.isLogin(true,"请先登录再进行推荐！")){
		$.epsDialog({
	        title:'将此公告推荐给好友',
	        width:320,
	        height:150,
	        url:$('#initPath').val()+'/view/agreement/showbulletin/share_bulletin.jsp?bulletinId='+bulletinId
		});
	}
}

//跳转到购买采购文件支付页面(如果项目分包，则针对包件进行支付，此时projId为包件id)
ShowBulletinDetail.toDocPay = function(projId,docPrice) {
	var url = $('#initPath').val()+"/DocAndBailPayController.do?method=toDocPayView&projId=" + projId+"&v_amount="+docPrice;
	window.open(url);
}

//跳转到购买保证金支付页面(如果项目分包，则针对包件进行支付，此时projId为包件id)
ShowBulletinDetail.toBailPay = function(projId,bailPrice) {
	var url = $('#initPath').val()+"/DocAndBailPayController.do?method=toBailPayView&projId=" + projId+"&v_amount="+bailPrice;
	window.open(url);
}

//查看采购文件支付记录
ShowBulletinDetail.showDocPayRecord = function(pay_business_id) {
	var url = $('#initPath').val()+"/PayController.pay?method=toDocPayRecordDetail&pay_business_id=" + pay_business_id;
	$.epsDialog({
        title:'采购文件支付记录',
        width:820,
        height:350,
        url:url
	});
}

//查看保证金支付记录
ShowBulletinDetail.showBailPayRecord = function(pay_business_id) {
	var url = $('#initPath').val()+"/PayController.pay?method=toBailPayRecordDetail&pay_business_id=" + pay_business_id;
	$.epsDialog({
        title:'保证金支付记录',
        width:820,
        height:350,
        url:url
	});
}

//下载招标文件
ShowBulletinDetail.downloadPurchaseDocFile = function(projectId) {
	var url = $('#initPath').val()+"/BulletinShowController.do?method=toDownloadPurchaseDocFileView&projectId="+projectId;
	$.epsDialog({
		title: '下载招标文件',
		width: 600,
		height: 300,
		url: url
	});
}

//查看标书费的缴费信息（分包的项目）
ShowBulletinDetail.showPacksDocPay = function(projectId,projName){
	var url = $('#initPath').val()+"/DocAndBailPayController.do?method=toPackDocPayView&projectId="+projectId;
	$.epsDialog({
		title: '['+projName+']标书费缴费信息',
		width: 650,
		height: 300,
		url: url
	});
}

//查看保证金的缴费信息（分包的项目）
ShowBulletinDetail.showPacksBailPay = function(projectId,projName){
	var url = $('#initPath').val()+"/DocAndBailPayController.do?method=toPackBailPayView&projectId="+projectId;
	$.epsDialog({
		title: '['+projName+']保证金缴费信息',
		width: 650,
		height: 300,
		url: url
	});
}

$(document).ready(function(){
	//选中顶部菜单
	if($('#bulletinType').val() == '01'){
		changeTabsCss("goToBidding");
	}else{
		changeTabsCss("goToBargain");
	}
	
	//选中查询下拉框
	keyWordTypeChange('1');
	
	//公告模板table奇偶行显示不同背景色
	$(".conbody-beer-three tr:even").css("background-Color","#f9f7e5");
	
	//处理项目报名倒计时
	if($("#remainSignUpTime")) {
		ShowBulletinDetail.signUpInterval = setInterval("ShowBulletinDetail.flushRemainSignUpTime()", 1000);
	}

});