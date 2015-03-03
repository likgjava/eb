/*供应商-议价厅*/
//定义文件全局变量 处理方法名重复问题
var bargainHall={};
bargainHall.bargain;
bargainHall.chatContentId='goodsContent';//聊天内容div的ID
bargainHallTimerLeng=5555;//延迟的时间   毫秒
bargainHall.isFirstGetChatContent=true;
//查看报价详情
bargainHall.bargainDetail=function(recordId){
	$.epsDialog({
		id:'bargain_proccess_detail',
		title:'报价历史记录',
		url:$('#initPath').val()+'/BiddingRecordController.do?method=toBiddingRecordDetail&projId='+$('#projectObjId').val()+'&biddingRecordId='+recordId
	});
}

//退出议价厅
bargainHall.quitHall=function(){
	//关闭定时器
	clearInterval(talkHallEndTimer);
	clearInterval(talkHallTimer);
	window.close();
}

//显示报价历史
bargainHall.findBargainHistory=function(){
	
	var data = {projectObjId:$('#projectObjId').val(),supplierObjId:$('#orgId').val()};
	
	$.getJSON($('#initPath').val()+'/TalkProjectController.do?method=getSupplierBiddingHistory',data,function(json){
		if(json.failure){alert(json.result);return;}
		//该议价项目已经结束
		if(json.projStatus == '170'){
			alert('该议价项目已经结束，议价厅将关闭！');
			bargainHall.quitHall(); //退出议价厅
			return ;
		}
		var html='';
		var clazz='';
		$.each(json.biddingRecordList,function(i,n){
			html+='<li class="date">'+n.barginTime.replace('.0','')+'</li>';
			html+='<li>￥'+formatAmount(n.goodsTotal,2)+'<a href="javascript:void(0);" onclick="bargainHall.bargainDetail(\''+n.objId+'\');return false;" class="undLink" name='+n.objId+' >详情</a></li>';
		});
		$('#priceHistory ul').html(html);
	});
}

//获取聊天内容
bargainHall.bargainContent=function(){	 
	var orgInfoId = $('#buyerObjId').val() ;
	var url = $('#initPath').val()+'/BiddingChatController.do?method=findNotReadChatContent&sayType=receive';
	if(bargainHall.isFirstGetChatContent){
		var url = $('#initPath').val()+'/BiddingChatController.do?method=findHistoryChatContent';
	}
	$.getJSON(url,{"projId":$('#projectObjId').val(),"orgInfoId":orgInfoId},function(json){
		var html='';
		$.each(json.chatList,function(i,n){
			var sayName = n.sayOrgInfo!=null?n.sayOrgInfo.orgName:"";
			html += '<li id='+n.objId+'>'+sayName+'<span class="date">['+n.createTime.replace('.0','')+']</span>:'+n.content+'</li>';
		});
		$('ul', '#'+bargainHall.chatContentId).append(html);
		bargainHall.isFirstGetChatContent = false;//修改标记表示已获得历史聊天记录
		$('#c1').scrollTop($('#c1')[0].scrollHeight); //把滚动条移动到最底部
	})
}
//发送供应商聊天消息----供应商报价之后产生的聊天信息
bargainHall.bargainSendSupplierContent=function(content,bargainRecordId){	
	var biddingChat={
			project:{objId:$('#projectObjId').val()},
			sayOrgInfo:{objId:$('#orgId').val()}, //发送人ID
			receiveOrgInfo:{objId:$('#buyerObjId').val()}, //接收人ID
			content:content,
			isPrivate:'0'
	}
	
	$.getJSON($('#initPath').val()+'/BiddingChatController.do?method=saveBiddingChat',{biddingChatStr:JSON.stringify(biddingChat)},function(json){
		if(json.failure){alert(json.result);return;}
		bargainHall.bargainContent();

		//追加
		var sayName = $('#orgName').val();
		var html = '<li id='+json.biddingChat.objId+'>'+sayName+'<span class="date">['+json.biddingChat.createTime.replace('.0','')+']</span>:'+json.biddingChat.content+'</li>';
		$('ul', '#'+bargainHall.chatContentId).append(html);
		
		$("#contentArea").val("");  //清空会话框biddingChat
		$('#c1').scrollTop($('#c1')[0].scrollHeight); //把滚动条移动到最底部
	});
};

//计算剩余时间
bargainHall.caculateEndTime=function(diffNum){
	var timeStr = $("#evalEndTime").val();
	if(timeStr == ""){
		var tt = $("#evalEndTime").attr("name");
		$("#evalEndTime").attr("value", StringToDate(tt).getTime()-new Date().getTime());
	}else{
		var timess = Number(timeStr);
		if(timess <= 0){
			alert("报价结束时间已到！议价厅将关闭！");
			bargainHall.quitHall(); //退出议价厅
			return ;
		}
		timess = timess - 1000;
		$("#evalEndTime").attr("value", timess);
		$("#endDate").html(getRemainTime(timess));
	}
}

$(document).ready(function(){
	talkHallEndTimer=setInterval('bargainHall.caculateEndTime()', 1000);
	
	bargainHall.bargainContent();//获取议价内容
	bargainHall.findBargainHistory();//显示报价历史
	
	//定时器
	talkHallTimer=setInterval(function(){
		bargainHall.bargainContent(); 
		bargainHall.findBargainHistory();
	}, bargainHallTimerLeng);//定时刷新聊天内容
	
	//对商品、配件进行报价
	$('#toBargainBtn').click(function(){
		$.epsDialog({
			id:'bargain_proccess_detail',
			title:'供应商报价',
			url:$('#initPath').val()+'/view/agreement/bargin/project/talk/bargain_proccess_detail.jsp?projectObjId='+$('#projectObjId').val()
		});
	});
	
	//发送供应商聊天消息
	$('#sendSupplierContentBtn').click(function(){
		if($("#isLogin").val()=="false"){alert("请先登录！");return;}
		if($.trim($('#contentArea').val()).length == 0){
			alert("请不要发送空内容！");return;
		}
		bargainHall.bargainSendSupplierContent($('#contentArea').val());
	});
	
	//退出议价厅
	$("#quit").click(function(){
		if(window.confirm('确定要退出议价厅吗？')){
			window.close();
		}
	});
	
	//监听聊天输入框的回车事件
	$('#contentArea').keypress(function(event){
		if(event.keyCode == 13){
			$('#sendSupplierContentBtn').click();
			return false;
		}
	});
	
});
 