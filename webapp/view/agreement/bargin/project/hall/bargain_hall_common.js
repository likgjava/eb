
var bargainHallCommon = {};
var bargainTurnTimerLeng=5000;//延迟的时间   毫秒
var bargainTurnTimer;//轮次定时器

var buyerPageTimerLeng=8000;//延迟的时间   毫秒
var buyerPageTimer;//轮次定时器

var ChatFormTimerLeng=3000;//对话定时器

var bargainHallEndTimerLeng=1000;
var nowTim = 0;
var bargainHallEndTimer;//倒计时

//读取对话信息
bargainHallCommon.getChatContent=function(projId,pageType){
	var params = {};
	//每次刷新前传入已报名供应商ID，下次过滤已报名供应商ID
	var oldSupplierList = '';
	$('p[name=supplierName]').each(function(i,n){
		oldSupplierList += $(n).attr('id') + ',';
	})
	
	var url = $('#initPath').val()+'/BiddingChatController.do?method=findNotReadChatContent&sayType=receive';
	params = {"projId":projId,"orgInfoId":$('#supplierList').find('.tdog-userinfo-username').attr('id'),"oldSupplierList":oldSupplierList};
	$.getJSON(url,params,function(json){
		if(pageType=='buyer') {
			//刷新新报名供应商
			$.each(json.newSupplierList,function(i,n){
				$('#supplierList').append('<p name="supplierName" onclick="javascript:selectSupplier(\''+n.supplier.objId+'\');" id="'+n.supplier.objId+'">'+n.supplier.orgName+'</p>');
			})
		}
		
		var html='';
		if(json.chatList.length > 0) {//有新消息
			$('#newMsg').removeClass('hidden').text('('+json.chatList.length + ')');
		
			//有新消息立刻弹出对话框
			$('#tstart').show();
			
			$('#newMsg').click(function(){
				$('#tstart').show();
			});
		}
		$.each(json.chatList,function(i,n){
			html += '<p class="tdog-talk-others talk" talkid='+n.sayOrgInfo.objId+'>';
			html += '<span class="tdog-talk-username">'+n.sayOrgInfo.orgName+'</span>';
			html += '<span class="tdog-talk-time">('+n.createTime.replace('.0','')+'):</span>';
			html += '</p>';
			html += '<div class="tdog-talk-content talk" talkid='+n.sayOrgInfo.objId+'>'+n.content+'</div>';
		});
		$('.tdog-popup-talkhistory').append(html);
	})
}

//定时刷新时间
bargainHallCommon.findBargainEndTime=function(){
	$('#leftTimeLI').find('span').html(getRemainTime($('#leftTimeLI').find('span').attr('name')));
	$('#leftTimeLI').find('span').attr('name',(parseInt($('#leftTimeLI').find('span').attr('name'))-1000));
	nowTim = nowTim + 1000;
	if($('#leftTimeLI').find('span').html().indexOf("0天0时00分00秒") != -1) {
		bargainHallCommon.quitHall();
	}
}
//获取轮次，项目报价信息等
bargainHallCommon.getCurrentTurn=function(projId,requireIds){
	$.getJSON($('#initPath').val()+'/BargainProjectController.do?method=getCurrentBargainTurn',{"projId":projId,"requireIds":requireIds,"supplierIds":$('#supplierIds').val()},function(json){
		if(json.failure){if(json.result)alert(json.result);window.close();}
		if(json.over) {
			bargainHallCommon.quitHall();//退出竞价厅
		} 
		else {
			if(!json.currentTurnId) {
				disabledBargain();//禁用报价
			} else {
				enableBargain(json);//启用报价
			}
			
			//根据规则显示需求条目最低报价[采购人不用规则也可显示]
			displayMinPriceByRule($('#pageType').val(),json);
			
			//根据规则显示我的排名-供应商
			if(!$('#pageType').val() && $('#selfRanking').val()=="true") {
				displayMyRankingByRule(json);
			}
			
			//判断是否被剔除-（供应商报价厅）
			if($('#isEliminated').val() && $('#isEliminated').val()=='false' && json.isEliminated == 'true'){
				alert('对不起，由于您的报价不符合报价规则，你不能再参与该项目的报价！');
				window.location.reload();
			}
			
			//刷新供应商报价
			refreshDetailList(json);
			
			//是否有新消息
			if($('#pageType').val() && $('#pageType').val() == 'buyer') {
				if(json.newChatCount[1] > 0) {
					$('#newMsg').removeClass('hidden').text('('+json.newChatCount[1] + ')');
					//有新消息立刻弹出对话框
					$('#tstart').show();
					
					//说话人高亮显示
					$('p[name=supplierName]').removeClass('tdog-userinfo-username');
					$('p[id='+json.newChatCount[0]+']').addClass('tdog-userinfo-username');
					
					selectSupplier( json.newChatCount[0] );//同时也显示说话人的历史聊天内容
					
					$('#newMsg').click(function(){
						$('#tstart').show();
						$('#newMsg').addClass('hidden').text('');
					});
				}
			}
		}
	})
}
//根据规则显示需求条目最低报价[采购人不用规则也可显示]
function displayMinPriceByRule(pageType,json) {
	if(pageType == 'buyer') {//采购人
		$('span[name=minItemPrice]').each(function(i,n){
			$(n).text($('table[id=table_'+$(n).attr('requireid')+']').find('tr').eq(1).find('td').eq(3).text());
		})
	} else {//供应商
		if($('#minPrice').val()=="true") {
			$('span[name=minItemPrice]').each(function(i,n){
				$.each(json.minRequirePriceList,function(j,m){	
					if($(n).attr('requireid')==m[0]) {
						$(n).text(formatAmount(m[1],2));
					}
				})
			})
		}
	}
}
//根据规则显示我的排名
function displayMyRankingByRule(json) {
	var myRank = 0;
	$('span[name=rankingItem]').each(function(i,n){
		var i = 0;
		var res=true;
		$.each(json.rankingList,function(j,m){	
			if($(n).attr('requireid') == m[1]) {
				i++;
				if($('#myOrgInfoId').val()==m[0]) {
					myRank = i;
				}
				res = false;
			} 
		})
		if(res) {
			myRank=0;
		}
		$(n).text(myRank==0?'--':myRank);
	})
}
//刷新供应商报价信息
function refreshDetailList(json) {
	$('table[id^=table_]').each(function(i,n){
		var html = '';
		$.each(json.bargainDetailList,function(j,m){	
			if($(n).attr('id').replace('table_','')==m[9]) {
				//判断是否为已剔除的供应商
				var hasEliminate = 0;
				var eliminateSupplierId = '';
				$.each(json.eliminateSupplierList,function(index,es){
					if(es.supplier.objId == m[13]){ hasEliminate = 1; eliminateSupplierId = es.objId;}
				});
				
				html += '<tr>';
				html += '<td style="width: 200px" title='+m[8];
				if(hasEliminate == 1){html += ' class="delete-line"';}
				html += '>';
				if(m[8].length > 18) {
					html += m[8].substring(0,18)+'...';
				} else {
					html += m[8];
				}
				html += '</td>';
				html += '<td style="width: 150px" title='+m[1]+'>';
				if(m[1].length > 16) {
					html += m[1].substring(0,15)+'...';
				} else {
					html += m[1];
				}
				if(m[7] != ' ' && m[12] == '01'){
					html += '<input title="商品比较" type="checkbox" name="compareCheckBox" gid="'+m[7]+'" gname="'+m[10]+'" gcode="'+m[11]+'"';
					$('#goodsCompare').find('li').each(function(index,domE){
						if($(domE).attr('id') == 'C_'+m[7]){
							html += 'checked="checked"';
							return ;
						}
					});
					html += ' />';
				}
				html += '</td>';
				html += '<td style="width: 120px" align="center">'+m[2]+'</td>';
				html += '<td style="width: 110px" align="right">'+formatAmount(m[3],2)+'</td>';
				html += '<td style="width: 110px" align="right">'+formatAmount(m[4],2)+'</td>';
				html += '<td style="width: 120px" title='+m[6]+'>';
				if(m[6].length > 10) {
					html += m[6].substring(0,10)+'...';
				} else {
					html += m[6];
				}
				html += '</td>';
				html += '<td style="width: 50px" align="center">';
				if(m[5] && m[5] != ' ') {
					html += '<a onclick="javascript:viewFile(\''+m[5]+'\');return false;" class="thickbox" href="javascript:void(0);">[查看]</a>';
				} else {
					html += '--';
				}	
				html += '</td>';
				html += '<td style="width:50px" align="center"><a onclick="javascript:viewHistory(\''+m[0]+'\');return false;" class="thickbox" href="javascript:void(0);">[查看]</a></td>';
				html += '<td style="width:60px" align="center">';
				if(hasEliminate == 0){
					html += '<a onclick="bargainHallCommon.endSupplier(\''+m[13]+'\');return false;" class="thickbox" href="javascript:void(0);">[剔除]</a>';
				}else{
					html += '<a onclick="bargainHallCommon.cancleEliminate(\''+eliminateSupplierId+'\');return false;" class="thickbox" href="javascript:void(0);">[取消剔除]</a>';
				}
				html += '</td>';
				html += '</tr>';
			}
		})
		$(n).find('tbody').empty().append(html);
	})
	
	//修改第一行为红色、需求条目最低价
	$('table[id^=table_]').each(function(i,n){
		$(n).find('tr').eq(1).attr('style','color:red');
		$(n).find('tr').find('td').eq(3).text()
	})
}

//查看历史报价
function viewHistory(bargainDetailId) {
	$.epsDialog({
        title:'历史报价',
        url:$('#initPath').val()+'/view/agreement/bargin/project/hall/bargain_history.jsp?bargainDetailId='+bargainDetailId,
        width: 500,
		height: 300    
	});
}
//查看报价文件
function viewFile(fileId) {
	$.epsDialog({
		  id:'toBargainFileView',
	      title:'报价文件',
	      url:'view/agreement/bargin/project/hall/bidding_file.jsp?relId='+fileId,
	      width: 600,
		  height: 200    
	})
}
//竞价结束
bargainHallCommon.quitHall=function(){
	alert('当前竞价已经结束，该窗口即将被关闭.');
	bargainHallCommon.exitHall();
}
//退出竞价厅
bargainHallCommon.exitHall=function(){
	//关闭定时器
	if(bargainHallEndTimer) {
		clearInterval(bargainHallEndTimer);
	}
	window.close();
}
//轮次结束或未开始，屏蔽报价按钮
function disabledBargain() {
	$('a[name=bargainBtn]').parent().attr('disabled','disabled');
	$('label[id=turnNo]').text('--');
	$('span[id=turnTime]').text('（本轮报价已结束或尚未开始...）');
}
//启用报价按钮
function enableBargain(json) {
	$('a[name=bargainBtn]').parent().removeAttr('disabled');
	$('input[id=bargainTurnId]').val(json.currentTurnId);//轮次ID
	$('label[id=turnNo]').text(json.turnNo);
	$('span[id=turnTime]').text('（'+json.startTime+' 至 '+json.endTime+'）');
	
	//控制历史轮次的显示
	$('span[name=turnSpan]').each(function(i,n){
		if(parseInt($(n).attr('id')) >= parseInt(json.currentTurnId)) {
			$(n).addClass('hidden');
		}
	})
}
//选择供应商聊天方法
function selectSupplier(ctrlId) {
	$('p[name=supplierName]').removeClass('tdog-userinfo-username');
	if($('p[id='+ctrlId+']').attr('class')!='tdog-userinfo-username') {
		$('p[id='+ctrlId+']').addClass('tdog-userinfo-username');
	} else {
		$('p[id='+ctrlId+']').removeClass('tdog-userinfo-username');
	}
	$('.tdog-popup-contact').text($('p[id='+ctrlId+']').text());
	
	$('.talk').removeClass('hidden');
	//隐藏非当前供应商对话内容
	$('.talk').each(function(i,n){
		if($(n).attr('talkid') != ctrlId) {
			$(n).addClass('hidden');
		}
	})
}
//查看历史轮次报价情况
function viewHistoryTurn(turnId,projId,roleType) {
	window.open($('#initPath').val()+'/BargainProjectController.do?method=getBargainDetailByTurnId&turnId='+turnId+"&projId="+projId+"&roleType="+roleType);
}

//剔除供应商
bargainHallCommon.endSupplier=function(supplierId) {
	var endSupplierArray = [];
	var endSupplier={
		project: {objId:$('#projId').val()},
		barginTurn: {objId:$('#bargainTurnId').val()},
		supplier: {objId:supplierId}
	}
	endSupplierArray.push(endSupplier);
	
	if(window.confirm('确定剔除?')) {
		$.getJSON($('#initPath').val()+'/BiddingEliminateSupplierController.do?method=endSupplier',{endSupplierListStr:JSON.stringify(endSupplierArray)},function(json){
			if(json.failure){alert(json.result);return;}
			alert("剔除成功");
			window.location.reload();
		});
	}
}

//取消剔除供应商
bargainHallCommon.cancleEliminate = function(eliminateSupplierId){
	if(window.confirm('确定取消剔除吗?')) {
		$.getJSON($('#initPath').val()+'/BiddingEliminateSupplierController.do?method=remove',{'objId':eliminateSupplierId},function(json){
			if(json.failure){alert(json.result);return;}
			alert("取消剔除成功");
			window.location.reload();
		});
	}
}

$(document).ready(function(){
	var projId = $("#projId").val();//项目ID
	var turnId = $("#bargainTurnId").val();//当前轮次ID
	var requireIds = $('#requireItemIds').val();//需求条目IDS
	
	//没有轮次
	if(!turnId) {
		disabledBargain();
	}
	
	//记录竞价结束时间
	var endDate = StringToDate($('#evalEndTime').val().replace('-', "/").replace('-', "/"));
	//nowDate为服务器端时间,和竞价结束时间对比获取倒计时
	var nowDate = StringToDate($('#currentDate').val().replace('-', "/").replace('-', "/"));
	$('#leftTimeLI').find('span').attr('name',nowDate.DateDiff('s',endDate)*1000);
	nowTim = nowDate.getTime();
	
	//倒计时
	bargainHallCommon.findBargainEndTime();
	
	bargainHallEndTimer=setInterval(function(){
		bargainHallCommon.findBargainEndTime();
	}, bargainHallEndTimerLeng);
	
	//定时器-请求轮次、供应商报价条目信息等
	bargainTurnTimer=setInterval(function(){
		bargainHallCommon.getCurrentTurn(projId,requireIds);
	}, bargainTurnTimerLeng);
	
	//采购人读取对话信息定时器
	if($('#pageType').val() && $('#pageType').val()=='buyer') {
		//定时器-读取聊天信息
		ChatFormTimer=setInterval(function(){
			//定时刷新未读聊天内容
			bargainHallCommon.getChatContent(projId,'buyer');
		}, ChatFormTimerLeng);
	} 
	//供应商读取对话信息定时器
	else {
		//定时器-读取聊天信息
		ChatFormTimer=setInterval(function(){
			//定时刷新未读聊天内容
			bargainHallCommon.getChatContent(projId,'supplier');
		}, ChatFormTimerLeng);
	}
	
	//选择供应商
	$('p[name=supplierName]').click(function(){
		selectSupplier($(this).attr('id'));
	})

	//结束竞价
	$("#stopBargain").click(function(){
		if(window.confirm('确定要结束该竞价项目吗？')){
			$.getJSON($('#initPath').val()+'/BargainProjectController.do?method=endBidding',{projId:$('#projId').val()},function(json){
				if(json.failure){if(json.result)alert(json.result);window.close();}
				bargainHallCommon.exitHall();
			});
		}
	});
	
	//发起会话
	$("#startChat").click(function(){
		$('#tstart').show();
		$('#newMsg').addClass('hidden').text('');
	});
	
	//关闭对话框
	$('.tdog-popup-close').click(function(){
		$('#tstart').hide();
		$('#newMsg').addClass('hidden').text('');
	})
	
	//默认显示第一个在线
	$('.tdog-popup-contact').text($('p[name=supplierName]').eq(0).text());
	
	//发送消息
	$('#sendMSG').click(function(){
		if($.trim($('#contentArea').val()).length == 0){
			alert("请不要发送空内容！");return;
		}
		var biddingChat={
				project:{objId:$('#projId').val()},
				sayOrgInfo:{objId:$('#myOrgInfoId').val()},  //发送人
				receiveOrgInfo:{objId:$('#supplierList').find('.tdog-userinfo-username').attr('id')},  //接受人
				content:$('#contentArea').val(),
				isPrivate:'0'
		}
		$.getJSON($('#initPath').val()+'/BiddingChatController.do?method=saveBiddingChat',{biddingChatStr:JSON.stringify(biddingChat)},function(json){
			if(json.failure){alert(json.result);return;}
			$("#contentArea").val("");  //清空会话框
			//追加
			var html ='';
			html += '<p class="tdog-talk-others talk" talkid='+json.biddingChat.receiveOrgInfo.objId+'>';
			html += '<span class="tdog-talk-username">'+$('#myOrgInfoName').val()+'</span>';
			html += '<span class="tdog-talk-time">('+json.biddingChat.createTime.replace('.0','')+'):</span>';
			html += '</p>';
			html += '<div class="tdog-talk-content talk" talkid='+json.biddingChat.receiveOrgInfo.objId+'>'+json.biddingChat.content+'</div>';
			$('.tdog-popup-talkhistory').append(html);
			$('.tdog-popup-talkhistory').scrollTop($('.tdog-popup-talkhistory')[0].scrollHeight); //把滚动条移动到最底部
		});
	})
});