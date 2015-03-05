/*
 * 平台开发demo
 * author: wangsw
 * mail: sinlff@163.com
 */
var myDesktop={};
myDesktop.timer = 60000;   //定时器的时间

//跳转到站内信列表页面
myDesktop.showStationMessage=function(){
	$('#conBody').loadPage($('#initPath').val()+"/MessageController.do?method=toMessageListView");
}

//改变未读的站内信数量
myDesktop.changeNotReadMessageNum=function(num){
	if(num > 0) {
		$("#notReadMessageNum").text("("+num+")");
		$("#notReadMessageNum").attr('num',num);
	}else{
		$("#notReadMessageNum").text("");
		$("#notReadMessageNum").attr('num','0');
	}
}

//获得当前用户的【未读和未提示】的站内信数目
myDesktop.processMessage=function(){
	$.getJSON($('#initPath').val()+"/MessageController.do?method=getMessageNum", function(json){
		//修改未读站内信的数目
		myDesktop.changeNotReadMessageNum(json.notReadMessageNum);
		
		//显示未提示的站内信内容
		if(json.notTipMessageNum > 0) {
			var url = $('#initPath').val()+'/MessageController.do?method=getNotTipMessage&dialogId=messageTip';
			$.epsDialog({
				id:"messageTip",
		        title:'系统消息',
		        url:url,
		        width: '380',
		        height: '200',
		        timeOut: 0,
		        isTips:true //是否右下角提示,默认否
		    });
		}
	});
}

myDesktop.unlogin=function(){//退出
	alert($("#initPath").val()+"/logout.do")
	if(confirm('确定要退出吗？')){
		$.ajax({
			url : $("#initPath").val()+"/logout.do",
			type :"POST"
		});
		window.location.href=$("#initPath").val();
	}
}

//判断机构的状态，提示用户完善信息
myDesktop.isfinishInfo=function(){
	//判断是否审核通过，跳转维护信息页面,没有供应商或采购人角色说明未审核通过
	if($('#currSuppStatus').val()!='' || $('#currBuyStatus').val()!='') {
		if($('#currBuyStatus').val()=='03' || $('#currSuppStatus').val()=='03') {
			$.epsDialog({
				id:"messageTip",
				url:$('#initPath').val() + "/view/pubservice/mydesktop/add_info_div.jsp?suppbuyStatus=03",
		        title:'系统消息',
		        width: '380',
		        height: '200',
		        onClose: function(){ 
	        		$('#conBody').loadPage($('#initPath').val()+'/ExOrgInfoController.do?method=toEntBaseInfo');
	        	}
		    });
			return false;
		}
		else if($('#currSuppStatus').val()=='00' || $('#currBuyStatus').val()=='00') {
			$.epsDialog({
				id:"messageTip",
				url:$('#initPath').val() + "/view/pubservice/mydesktop/add_info_div.jsp?suppbuyStatus=00",
		        title:'系统消息',
		        width: '380',
		        height: '200',
		        onClose: function(){ 
	        		$('#conBody').loadPage($('#initPath').val()+'/ExOrgInfoController.do?method=toEntBaseInfo');
	        	}
		    });
			return false;
		}
		return true;
	}
	return true;
}

$(document).ready(function(){
	
	//判断是否登录
	if($('#ssousername').length > 0){
		$.addMenu({
			target:'#conBody',//菜单target
			precall:myDesktop.isfinishInfo,
			isAutoLoad:false
		})
	}
	
	//定时获取未读信息
	setInterval('myDesktop.processMessage()', myDesktop.timer);
	
	//处理当前用户的站内信
	myDesktop.processMessage();
});
