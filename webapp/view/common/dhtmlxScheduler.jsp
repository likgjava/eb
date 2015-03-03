<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init_es.jsp"%>
<script src="<%=request.getContextPath()%>/view/resource/plug-in/dhtmlxScheduler/dhtmlxscheduler.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/view/resource/plug-in/dhtmlxScheduler/sources/locale_cn.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/view/resource/plug-in/dhtmlxScheduler/dhtmlxscheduler.css" type="text/css" media="screen" title="no title" charset="utf-8">

	
<style type="text/css" media="screen">
	html, body{
		margin:0px;
		padding:0px;
		height:100%;
		overflow:hidden;
	}	
</style>

<script type="text/javascript" charset="utf-8">
	var dhtmlxSchedulerEPP = {};
	$(document).ready(function(){
		init();
		
	});
	function init() {
		scheduler.config.multi_day = true;
		
		scheduler.config.xml_date="%Y-%m-%d %H:%i";
		scheduler.init('scheduler_here',new Date(),"week");
		scheduler.load($('#initPath').val()+"/DhtmlxSchedulerController.do?method=getDEvents","json");
		
		scheduler.attachEvent("onEventAdded", function(event_id,event_object){
			event_object.objId=event_object.id;
			event_object.startDate=new Date(event_object.start_date).Format('yyyy-MM-dd HH:mm:ss');
			event_object.endDate=new Date(event_object.end_date).Format('yyyy-MM-dd HH:mm:ss');
			$.getJSON($('#initPath').val()+'/DhtmlxSchedulerController.do?method=saveDEvents', event_object, function(json){
				scheduler.changeEventId(event_id,json.dEvent.objId);
			});
	  	});
		scheduler.attachEvent("onBeforeEventDelete", function(event_id,event_object){
			dhtmlxSchedulerEPP.delEvents(event_id,event_object);
			return true;
 		});
		scheduler.attachEvent("onEventChanged", function(event_id,event_object){
			event_object.objId=event_object.id;
			event_object.startDate=new Date(event_object.start_date).Format('yyyy-MM-dd HH:mm:ss');
			event_object.endDate=new Date(event_object.end_date).Format('yyyy-MM-dd HH:mm:ss');
			$.getJSON($('#initPath').val()+'/DhtmlxSchedulerController.do?method=saveDEvents', event_object, function(json){
				
			});
	   	});
		scheduler.attachEvent("onEventSave", function(event_id,event_object){
			return true;
	   	});
	}
	dhtmlxSchedulerEPP.delEvents = function(event_id,event_object){
		event_object.objId=event_object.id;
		$.getJSON($('#initPath').val()+'/DhtmlxSchedulerController.do?method=delDEvent', event_object, function(json){
			
		});
	}
</script>

<div id="scheduler_here" class="dhx_cal_container" style='width:100%; height:100%;'>
	<div class="dhx_cal_navline">
		<div class="dhx_cal_prev_button">&nbsp;</div>
		<div class="dhx_cal_next_button">&nbsp;</div>
		<div class="dhx_cal_today_button"></div>
		<div class="dhx_cal_date"></div>
		<div class="dhx_cal_tab" name="day_tab" style="right:204px;"></div>
		<div class="dhx_cal_tab" name="week_tab" style="right:140px;"></div>
		<div class="dhx_cal_tab" name="month_tab" style="right:76px;"></div>
	</div>
	<div class="dhx_cal_header">
	</div>
	<div class="dhx_cal_data">
	</div>
</div>