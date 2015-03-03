<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script>
$(document).ready(function(){
	$('#fileTabs').tabs();

	// 设置开启关闭样式
	
	function initCloseOrOpen(divId,timerItem){
		$('#'+divId).find('[id='+timerItem+']').find('input,select').attr("disabled",false);
		$('#'+divId).find('[id='+timerItem+']').find('[name^=openorclosetimeritem]').find('span').html("关闭");
	}
	// 加载定时器
	$.getJSON($('#initPath').val()+'/ChannelTimerController.do?method=getObject',{'channelId':channelId},function(json){
		$.each(json.list,function(i,n){
			if(n.timeType=="channel"){
				jsonObjectToForm('channelTimerForm', n);
				if(n.closeData=='false'){
					initCloseOrOpen('channelTimerForm','timer_data');
				}
				if(n.closePeriod=='false'){
					initCloseOrOpen('channelTimerForm','timer_period');
				}
				if(n.closeDelay=='false'){
					initCloseOrOpen('channelTimerForm','timer_delay');
				}
				if(n.timerClose=='false'){
					$('#openTimerBtn_channel').attr('timerClose',n.timerClose);
					$('#openTimerBtn_channel').find('span').html('关闭定时器');
				}
				$('#openTimerBtn_channel').attr('disabled',false);
			}else if(n.timeType=="content"){
				jsonObjectToForm('contentTimerForm', n);
				if(n.closeData=='false'){
					initCloseOrOpen('contentTimerForm','timer_data');
				}
				if(n.closePeriod=='false'){
					initCloseOrOpen('contentTimerForm','timer_period');
				}
				if(n.closeDelay=='false'){
					initCloseOrOpen('contentTimerForm','timer_delay');
				}
				if(n.timerClose=='false'){
					$('#openTimerBtn_content').attr('timerClose',n.timerClose);
					$('#openTimerBtn_content').find('span').html('关闭定时器');
				}
				$('#openTimerBtn_content').attr('disabled',false);
			}
		})
	})
	
	//开启、关闭
	$('button[name^=openorclosetimeritem]').click(function(){
		var temp = $('#'+$(this).attr('name').replace('openorclosetimeritem_','')+'TimerForm');
		if($(this).parent().find('input[name^=close]').val()=="false"){
			$(this).find('span').html("开启");
			$(this).parent().parent().find('input,select').attr("disabled",true);
			$(this).parent().find('input[name^=close]').val(true);
			if($(this).parent().find('input[name^=close]').attr('name')=="closeDelay"){
				$(temp).find('[id=timer_data]').find('button[name^=openorclosetimeritem]').find('span').html('关闭');
				$(temp).find('[id=timer_data]').find('input,select').attr("disabled",false);
				$(temp).find('[id=timer_data]').find('input[name^=close]').val(false);
			} else if($(this).parent().find('input[name^=close]').attr('name')=="closeData"){
				$(temp).find('[id=timer_delay]').find('button[name^=openorclosetimeritem]').find('span').html('关闭');
				$(temp).find('[id=timer_delay]').find('input,select').attr("disabled",false);
				$(temp).find('[id=timer_delay]').find('input[name^=close]').val(false);
			}
		}else{
			$(this).find('span').html("关闭");
			$(this).parent().parent().find('input,select').attr("disabled",false);
			$(this).parent().find('input[name^=close]').val(false);
			if($(this).parent().find('input[name^=close]').attr('name')=="closeDelay"){
				$(temp).find('[id=timer_data]').find('button[name^=openorclosetimeritem]').find('span').html('开启');
				$(temp).find('[id=timer_data]').find('input,select').attr("disabled",true);
				$(temp).find('[id=timer_data]').find('input[name^=close]').val(true);
			} else if($(this).parent().find('input[name^=close]').attr('name')=="closeData"){
				$(temp).find('[id=timer_delay]').find('button[name^=openorclosetimeritem]').find('span').html('开启');
				$(temp).find('[id=timer_delay]').find('input,select').attr("disabled",true);
				$(temp).find('[id=timer_delay]').find('input[name^=close]').val(true);
			}
		}
		return false;
	}); 

	// 减时
	$('[name^=reduce_time]').click(function(){
		var curVal = parseInt($(this).next('input').val());
		var operatorName = $(this).attr('name').replace('reduce_time_','');
		if(operatorName=="hour"){
			if(curVal==0){
				$(this).next('input').val("23");
			}else if(curVal <= 10){
				$(this).next('input').val("0"+(curVal-1));
			}else{
				$(this).next('input').val(curVal-1);
			}
		}else if(operatorName=="minute" || operatorName=="second"){
			if(curVal==0){
				$(this).next('input').val("59");
			}else if(curVal <= 10){
				$(this).next('input').val("0"+(curVal-1));
			}else{
				$(this).next('input').val(curVal-1);
			}
		}else{
			if(curVal==1){
				$(this).next('input').val("1");
			}else{
				$(this).next('input').val(curVal-1);
			}
			
		}
		return false;
	});

	// 加时
	$('[name^=add_time]').click(function(){
		var curVal = parseInt($(this).prev('input').val());
		var operatorName = $(this).attr('name').replace('add_time_','');
		if(operatorName=="hour"){
			if(curVal==23){
				$(this).prev('input').val("00");
			}else if(curVal >= 10){
				$(this).prev('input').val(curVal+1);
			}else{
				$(this).prev('input').val("0"+(curVal+1));
			}
		}else if(operatorName=="minute" || operatorName=="second"){
			if(curVal ==59){
				$(this).prev('input').val("00");
			}else if(curVal >= 10){
				$(this).prev('input').val(curVal+1);
			}else{
				$(this).prev('input').val("0"+(curVal+1));
			}
		}else{
				$(this).prev('input').val(curVal+1);
		}
		return false;
	});

	// 保存定时器
	$('button[id^=saveTimerBtn]').click(function(){
		$('button[id^=saveTimerBtn]').parent().find('button').attr('disabled',true);
		var timer = {};
		var  timerForm = 'channelTimerForm';
		if($(this).attr('id').replace('saveTimerBtn_','')=="channel"){
			timer = formToJsonObject('channelTimerForm');
		}else{
			timer = formToJsonObject('contentTimerForm');
			timerForm = 'contentTimerForm';
		}
		timer.channelId=channelId;
		$.getJSON($('#initPath').val()+'/ChannelTimerController.do?method=saveTimer', timer, function(json){
			if(json.failure){alert(json.result);$('button[id^=saveTimerBtn]').parent().find('button').attr('disabled',false);return;}
			alert("已保存定时器");
			if(!$('#'+timerForm).find('input[name=objId]').val()){// 在第一次保存后，重新回填
				$.getJSON($('#initPath').val()+'/ChannelTimerController.do?method=createOrUpdate',{objId:json.channelTimer.objId},function(obj){
					jsonObjectToForm(timerForm, obj.channelTimer);
				})
			}
			$('button[id^=saveTimerBtn]').parent().find('button').attr('disabled',false);
		});
	});

	
	// 开启定时器
	$('button[id^=openTimerBtn]').click(function(){
		var curBtn = this;
		var objId = "";
		if($(this).attr('id').replace('openTimerBtn_','')=="channel"){
			objId = $('#channelTimerForm').find('input[name=objId]').val();
		}else{
			objId = $('#contentTimerForm').find('input[name=objId]').val();
		}

		if(objId == ""){
			alert("请先保存定时器！");
			return false;
		}
		$('button[id^=openTimerBtn]').parent().find('button').attr('disabled',true);

		var url = $('#initPath').val()+'/ChannelTimerController.do?method=openTimer';
		// 如果已经是开启的，则关闭
		if($(curBtn).attr('timerClose')=='false'){
			url = $('#initPath').val()+'/ChannelTimerController.do?method=closeTimer';
		}
		$.getJSON(url, {'objId':objId}, function(json){
			if(json.failure){alert(json.result);$('button[id^=openTimerBtn]').parent().find('button').attr('disabled',false);return;}
			if($(curBtn).attr('timerClose')=='true'){
				alert("已开启定时间器");
				$(curBtn).find('span').html("关闭定时器");
				$(curBtn).attr('timerClose','false');
			}else{
				alert("已关闭定时间器");
				$(curBtn).find('span').html("开启定时器");
				$(curBtn).attr('timerClose','true');
			}
			$('button[id^=openTimerBtn]').parent().find('button').attr('disabled',false);
		});
	});

	$('input[name=dateHour]').keyup(function(){
		if(/\D/.test($(this).val())){$(this).val("");;return false;}
		if(parseInt($(this).val())>23 || parseInt($(this).val()) < 0){$(this).val("00");;return false;}
	}); 
	$('input[name=dateMinute],input[name=dateSecond]').keyup(function(){
		if(/\D/.test($(this).val())){$(this).val("");;return false;}
		if(parseInt($(this).val())>59 || parseInt($(this).val()) < 0){$(this).val("00");;return false;}
	}); 
	$('input[name=delayTime],input[name=periodTime]').keyup(function(){
		if(/\D/.test($(this).val())){$(this).val("");return false;}
		if(parseInt($(this).val()) < 1){$(this).val("1");return false;}
		$(this).val(parseInt($(this).val()));
	}); 
	
});

</script>
<div id="fileTabs">
    <ul >
    <li><a href="#channelTimerTask" id="clickFinaFile"><span>栏目发布定时器</span></a></li>
    <li><a href="#contentTimerTask"><span>内容发布定时器</span></a></li>
    </ul>
	<div id="channelTimerTask">
	<form id="channelTimerForm" name="channelTimerForm">
	<input type="hidden" name="timeType" value="channel"/>
	<input type="hidden" name="objId" />
		<div class="formLayout form2a" id="timer_data">
		<span style="width:300px">定时时间</span>
		时<span class="sysicon siPrev  noText" name="reduce_time_hour">&nbsp;&nbsp;&nbsp;&nbsp;</span><input type="text" style="width:20px" name="dateHour" disabled="disabled" maxlength="2" value="00"/><span class="sysicon siNext noText" name="add_time_hour">&nbsp;&nbsp;&nbsp;&nbsp;</span>
		分<span class="sysicon siPrev  noText" name="reduce_time_minute">&nbsp;&nbsp;&nbsp;&nbsp;</span><input type="text" style="width:20px" name="dateMinute" disabled="disabled" maxlength="2" value="00"/><span class="sysicon siNext noText" name="add_time_minute">&nbsp;&nbsp;&nbsp;&nbsp;</span>
		秒<span class="sysicon siPrev  noText" name="reduce_time_second">&nbsp;&nbsp;&nbsp;&nbsp;</span><input type="text" style="width:20px" name="dateSecond" disabled="disabled" maxlength="2" value="00"/><span class="sysicon siNext noText" name="add_time_second">&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<span class="functionBtnDiv"><BUTTON type="button" name="openorclosetimeritem_channel"><SPAN>开启</SPAN></BUTTON><input type="hidden" name="closeData" value="true"/></span>
		</div>
		
		<div class="formLayout form2a" id="timer_period">
		<span sytle="width:500px">固定频率</span>
		<span class="sysicon siPrev  noText" name="reduce_time_period">&nbsp;&nbsp;&nbsp;&nbsp;</span><input type="text" style="width:80px" name="periodTime" disabled="disabled" maxlength="8" value="0"/><span class="sysicon siNext noText" name="add_time_period">&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<select style="width:80px" name="periodUnit" disabled="disabled">
		<option value="second">秒</option>
		<option value="minute">分钟</option>
		<option value="hour">小时</option>
		<option value="day">天</option>
		</select>
		<span class="functionBtnDiv"><BUTTON type="button" name="openorclosetimeritem_channel"><SPAN>开启</SPAN></BUTTON><input type="hidden" name="closePeriod" value="true"/></span>
		</div>
		
		<div class="formLayout form2a" id="timer_delay">
		<span style="width:300px">指定延迟</span>
		<span class="sysicon siPrev  noText" name="reduce_time_delay">&nbsp;&nbsp;&nbsp;&nbsp;</span><input type="text" style="width:80px" name="delayTime" disabled="disabled" maxlength="8" value="0"/><span class="sysicon siNext noText" name="add_time_delay">&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<select style="width:80px" name="delayUnit" disabled="disabled">
		<option value="second">秒</option>
		<option value="minute">分钟</option>
		<option value="hour">小时</option>
		</select>
		<span class="functionBtnDiv"><BUTTON type="button" name="openorclosetimeritem_channel"><SPAN>开启</SPAN></BUTTON><input type="hidden" name="closeDelay" value="true"/></span>
		</div>
		<div class="conOperation">
			<button type="button" id="openTimerBtn_channel" timerClose="true" disabled="disabled"><span>开启定时间器</span></button>
			<button type="button" id="saveTimerBtn_channel"><span>保存定时器</span></button>
		</div>
	</form>
	</div>
	<div id="contentTimerTask">
	<form id="contentTimerForm" name="contentTimerForm">
	<input type="hidden" name="timeType" value="content"/>
	<input type="hidden" name="objId" />
		<div class="formLayout form2a" id="timer_data">
		<span style="width:300px">定时时间</span>
		时<span class="sysicon siPrev  noText" name="reduce_time_hour">&nbsp;&nbsp;&nbsp;&nbsp;</span><input type="text" style="width:20px" name="dateHour" disabled="disabled" maxlength="2" value="00"/><span class="sysicon siNext noText" name="add_time_hour">&nbsp;&nbsp;&nbsp;&nbsp;</span>
		分<span class="sysicon siPrev  noText" name="reduce_time_minute">&nbsp;&nbsp;&nbsp;&nbsp;</span><input type="text" style="width:20px" name="dateMinute" disabled="disabled" maxlength="2" value="00"/><span class="sysicon siNext noText" name="add_time_minute">&nbsp;&nbsp;&nbsp;&nbsp;</span>
		秒<span class="sysicon siPrev  noText" name="reduce_time_second">&nbsp;&nbsp;&nbsp;&nbsp;</span><input type="text" style="width:20px" name="dateSecond" disabled="disabled" maxlength="2" value="00"/><span class="sysicon siNext noText" name="add_time_second">&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<span class="functionBtnDiv"><BUTTON type="button" name="openorclosetimeritem_content"><SPAN>开启</SPAN></BUTTON><input type="hidden" name="closeData" value="true"/></span>
		</div>
		
		<div class="formLayout form2a" id="timer_period">
		<span sytle="width:500px">固定频率</span>
		<span class="sysicon siPrev  noText" name="reduce_time_period">&nbsp;&nbsp;&nbsp;&nbsp;</span><input type="text" style="width:80px" name="periodTime" disabled="disabled" maxlength="8" value="0"/><span class="sysicon siNext noText" name="add_time_period">&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<select style="width:80px" name="periodUnit" disabled="disabled">
		<option value="second">秒</option>
		<option value="minute">分钟</option>
		<option value="hour">小时</option>
		<option value="day">天</option>
		</select>
		<span class="functionBtnDiv"><BUTTON type="button" name="openorclosetimeritem_content"><SPAN>开启</SPAN></BUTTON><input type="hidden" name="closePeriod" value="true"/></span>
		</div>
		
		<div class="formLayout form2a" id="timer_delay">
		<span style="width:300px">指定延迟</span>
		<span class="sysicon siPrev  noText" name="reduce_time_delay">&nbsp;&nbsp;&nbsp;&nbsp;</span><input type="text" style="width:80px" name="delayTime" disabled="disabled" maxlength="8" value="0"/><span class="sysicon siNext noText" name="add_time_delay">&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<select style="width:80px" name="delayUnit" disabled="disabled">
		<option value="second">秒</option>
		<option value="minute">分钟</option>
		<option value="hour">小时</option>
		</select>
		<span class="functionBtnDiv"><BUTTON type="button" name="openorclosetimeritem_content"><SPAN>开启</SPAN></BUTTON><input type="hidden" name="closeDelay" value="true"/></span>
		</div>
		<div class="conOperation">
			<button type="button" id="openTimerBtn_content" timerClose="true" disabled="disabled"><span>开启定时间器</span></button>
			<button type="button" id="saveTimerBtn_content"><span>保存定时器</span></button>
		</div>
	</form>
	</div>
</div>