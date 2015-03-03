/*采购人-议价厅*/
//定义文件全局变量 处理方法名重复问题
var bargainHall={};
bargainHall.bargain;
bargainHall.chatContentId='goodsContent';//聊天内容div的ID
bargainHall.currentSupplierOrgInfoId;//当前选中的聊天供应商
bargainHallTimerLeng=5555;//延迟的时间   毫秒
bargainHall.orgInfoList;

//查看报价详情
bargainHall.bargainDetail=function(recordId){
	$.epsDialog({
		id:'bargain_proccess_detail',
		title:'报价历史记录',
		url:$('#initPath').val()+'/BiddingRecordController.do?method=toBiddingRecordDetail&projId='+$('#projectObjId').val()+'&biddingRecordId='+recordId
	});
}

//更新最低报价
bargainHall.findLowestRecord=function(){
	$.getJSON($('#initPath').val()+'/TalkProjectController.do?method=getMinBiddingRecordByProjectId',{projectObjId:$('#projectObjId').val()},function(json){
		if(json.failure){if(json.result)alert(json.result);if(json.result.indexOf('时间')>-1)bargainHall.quitHall();return;}
		var html='';
		$.each(json.biddingRecordList,function(i,n){	 
			html+='<li class="conOperation fullLine"><b>'+n.supplier.orgName+'：</b>'
				+'￥<strong>'+formatAmount(n.goodsTotal,2)+'</strong>  '
				+'</li>';
		});
		if(html=='')html+='<li class="conOperation fullLine"><label>无</label><span>目前还没有供应商报价!</span></li>';
		$('#bargainInfo ul').html(html);
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
	var data = {projectObjId:$('#projectObjId').val()};
	data['supplierObjId']=bargainHall.currentSupplierOrgInfoId;
	
	$.getJSON($('#initPath').val()+'/TalkProjectController.do?method=getSupplierBiddingHistory',data,function(json){
		if(json.failure){alert(json.result);return;}
		var html='';
		var clazz='';
		$.each(json.biddingRecordList,function(i,n){
			html+='<li class="date">'+n.barginTime.replace('.0','')+'</li>';
			html+='<li>￥'+formatAmount(n.goodsTotal,2)+'<a href="javascript:void(0);" onclick="bargainHall.bargainDetail(\''+n.objId+'\');return false;" class="undLink" name='+n.objId+'>详情</a></li>';
		});
		$('#priceHistory ul').html(html);
	});
}

//获取供应商列表
bargainHall.findOnlineSupplierList=function(){
	$.getJSON($('#initPath').val()+'/TalkProjectController.do?method=getSignupRecordList',{projectObjId:$('#projectObjId').val(),type:$('#buyer').length!=0?'buyer':'supplier'},function(json){
		if(json.failure){if(json.result)alert(json.result);if(json.result.indexOf('时间')>-1)bargainHall.quitHall();return;}
		var html='';
		var clazz='';
		
		bargainHall.signUprecordList=json.signUprecordList;
		if(bargainHall.signUprecordList){
			$.each(bargainHall.signUprecordList,function(i,n){	//clazz='on';  
				if(i==0) {
					bargainHall.currentSupplierOrgInfoId=n.supplier.objId; 
					$('#currentOrgInfoName').html(n.supplier.orgName);
				}
				html += '<li>';
				html += '<a href="javascript:void(0);" isClick=0 name='+n.supplier.objId+' title="'+n.supplier.orgName+'">'+n.supplier.orgName.substring(0,7)+'...</a>';
				html += '<div class="hidden" id='+n.supplier.objId+'><ul></ul></div>';
				html += '</li>';
			});
		}
		
		$('#supplierNameList #menu ul').html(html);
		$('#supplierNameList #menu ul li a').click(function(){//采购人:	点击左边的供应商名称时切换聊天内容
			$('ul', '#'+bargainHall.chatContentId).empty();
			$('#currentOrgInfoName').html($(this).attr('title'));
			bargainHall.currentSupplierOrgInfoId=this.name;
			$('ul', '#'+bargainHall.chatContentId).append($('ul', '#'+bargainHall.currentSupplierOrgInfoId).html());
			bargainHall.bargainContent(this);//议价内容
			bargainHall.findBargainHistory();//报价历史
			$(this).attr('isClick', '1');//标记当前供应商已被点击过
			//处理当前选中供应商的样式
			$('#supplierNameList #menu ul li a').attr('style','font-weight:normal');
			$(this).attr('style','font-weight:bold');
		});
		$('#supplierNameList #menu ul li a:first').click();
	});
}

//获取聊天内容
bargainHall.bargainContent=function(aObj){	 

	var orgInfoId = bargainHall.currentSupplierOrgInfoId ;
	var url = $('#initPath').val()+'/BiddingChatController.do?method=findNotReadChatContent&sayType=receive';
	if($(aObj).attr('isClick') == 0){
		var url = $('#initPath').val()+'/BiddingChatController.do?method=findHistoryChatContent';
	}
	
	$.getJSON(url, {"projId":$('#projectObjId').val(),"orgInfoId":orgInfoId},function(json){
		var html='';
		$.each(json.chatList,function(i,n){
			var sayName = n.sayOrgInfo!=null?n.sayOrgInfo.orgName:"";
			html += '<li id='+n.objId+'>'+sayName+'<span class="date">['+n.createTime.replace('.0','')+']</span>:'+n.content+'</li>';
		});
		$('ul', '#'+bargainHall.chatContentId).append(html);
		$('ul', '#'+bargainHall.currentSupplierOrgInfoId).append(html);
		$('#c1').scrollTop($('#c1')[0].scrollHeight); //把滚动条移动到最底部
	})
}

//计算剩余时间
bargainHall.caculateEndTime=function(diffNum){
	var timeStr = $("#evalEndTime").val();
	if(timeStr == ""){
		var tt = $("#evalEndTime").attr("name");
		$("#evalEndTime").attr("value", StringToDate(tt).getTime()-new Date().getTime());
	}else{
		var timess = Number(timeStr);
		if(timess <= 0){
			alert("报价结束时间已到！");
			clearInterval(talkHallEndTimer); //关闭剩余报价时间的定时器
			clearInterval(talkHallTimer); //关闭获取聊天记录、历史报价、最低报价的定时器
			return ;
		}
		timess = timess - 1000;
		$("#evalEndTime").attr("value", timess);
		$("#endDate").html(getRemainTime(timess));
	}
}

$(document).ready(function(){ 
	talkHallEndTimer=setInterval('bargainHall.caculateEndTime()', 1000);
	
	bargainHall.findOnlineSupplierList();//获取供应商列表	采购人进入议价厅时需要
	bargainHall.bargainContent();//获取议价内容
	bargainHall.findBargainHistory();//显示报价历史
	bargainHall.findLowestRecord();//获取最低报价
	
	//定时器
	talkHallTimer=setInterval(function(){
		bargainHall.bargainContent(); 
		bargainHall.findLowestRecord();
		bargainHall.findBargainHistory();
	}, bargainHallTimerLeng);//定时刷新聊天内容
	
	//发送采购人聊天消息
	$('#sendBuyerContentBtn').click(function(){
		if($("#isLogin").val()=="false"){alert("请先登录！");return;}
		if($.trim($('#contentArea').val()).length == 0){
			alert("请不要发送空内容！");return;
		}
		
		var biddingChat={
				project:{objId:$('#projectObjId').val()},
				sayOrgInfo:{objId:$('#orgId').val()}, //发送人
				receiveOrgInfo:{objId:bargainHall.currentSupplierOrgInfoId}, //接受人
				content:$('#contentArea').val(),
				isPrivate:'0'
		}
		$.getJSON($('#initPath').val()+'/BiddingChatController.do?method=saveBiddingChat',{biddingChatStr:JSON.stringify(biddingChat)},function(json){
			if(json.failure){alert(json.result);return;}
			bargainHall.bargainContent();

			//追加
			var sayName = $('#orgInfoName').val();
			var html = '<li id='+json.biddingChat.objId+'>'+sayName+'<span class="date">['+json.biddingChat.createTime.replace('.0','')+']</span>:'+json.biddingChat.content+'</li>';
			$('ul', '#'+bargainHall.chatContentId).append(html);
			$('ul', '#'+bargainHall.currentSupplierOrgInfoId).append(html);
			
			$("#contentArea").val("");  //清空会话框biddingChat
			$('#c1').scrollTop($('#c1')[0].scrollHeight); //把滚动条移动到最底部
		});
	});
	
	//结束议价 
	$("#finishBargain").click(function(){
		if(window.confirm('确定要结束该议价项目吗？')){
			$.getJSON($('#initPath').val()+'/BargainProjectController.do?method=endBidding',{projId:$("#projectObjId").val()},function(json){
				if(json.failure){alert(json.result);return;}
				bargainHall.quitHall();
			});
		}
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
			$('#sendBuyerContentBtn').click();
			return false;
		}
	});
	
});
