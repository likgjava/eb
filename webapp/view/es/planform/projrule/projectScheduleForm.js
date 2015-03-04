	var projectScheduleForm={};

	//比较日期相差天数
	function better_time(strDateStart,strDateEnd){
		   var strSeparator = "-"; //日期分隔符
		   var strDateArrayStart;
		   var strDateArrayEnd;
		   var intDay;
		   strDateArrayStart = strDateStart.split(strSeparator);
		   strDateArrayEnd = strDateEnd.split(strSeparator);
		   var strDateS = new Date(strDateArrayStart[0] + "/" + strDateArrayStart[1] + "/" + strDateArrayStart[2]);
		   var strDateE = new Date(strDateArrayEnd[0] + "/" + strDateArrayEnd[1] + "/" + strDateArrayEnd[2]);
		   intDay = (strDateE-strDateS)/(1000*3600*24);
		   return intDay;
		}
	$(document).ready(function(){
		//获得页面显示
		
		$.getJSON($("#initPath").val()+'/ProjectScheduleController.do?method=getProjectSchedule&projectId='+$("#projectId").val(),function(json){
		
			jsonObjectToForm("projectScheduleForm", json.projectSchedule);
			})
		
		
		
		$('#projectScheduleForm').validate();
	    $("#signUpSTime").epsDatepicker();
	    $("#signUpETime").epsDatepicker();
	    $("#sellBidDocSTime").epsDatepicker();
	    $("#sellBidDocETime").epsDatepicker();
	    $("#tenderStartTime").epsDatepicker();
	    $("#tenderEndTime").epsDatepicker();
	    $("#openBidTime").epsDatepicker();
	    $("#createTime").epsDatepicker();
	     			
		//返回
		$('#projectScheduleReturn').click(function(){
			checkProjectMenu('menu_projectTime');
		});
		//提交
		$('#projectScheduleSave').click(function(){
			 
			if(!$('#projectScheduleForm').valid()){alert('请正确填写表单!');return;}
			if($("#signUpSTime").val()>=$("#signUpETime").val()){alert('报名结束时间应大于报名开始时间！');return;}
			if(better_time($("#signUpSTime").val(),$("#signUpETime").val())<20){alert('报名开始时间与报名结束时间最少要相差20天！');return;}
			
			if($("#signUpETime").val()>=$("#sellBidDocSTime").val()){alert('购买标书开始时间应大于报名结束时间！');return;}
			
			if($("#sellBidDocSTime").val()>=$("#sellBidDocETime").val()){alert('购买标书结束时间应大于购买标书开始时间！');return;}
			if(better_time($("#sellBidDocSTime").val(),$("#sellBidDocETime").val())<7){alert('购买标书开始时间与购买标书结束时间最少要相差7天！');return;}
			
			if($("#sellBidDocSTime").val()>=$("#tenderStartTime").val()){alert('投标开始时间应大于购买标书结束时间！');return;}
			
			if($("#tenderStartTime").val()>=$("#tenderEndTime").val()){alert('投标结束时间应大于投标开始时间！');return;}
			if(better_time($("#tenderStartTime").val(),$("#tenderEndTime").val())<15){alert('投标开始时间与投标结束时间最少要相差15天！');return;}
			
			if($("#tenderEndTime").val()>=$("#openBidTime").val()){alert('开标时间应大于投标结束时间！');return;}

		
			
			
			
			$.getJSON($('#initPath').val()+'/ProjectScheduleController.do?method=saveOrUpdateProjectSchedule&projectId='+$("#projectId").val(), formToJsonObject('projectScheduleForm'), function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/projrule/projectScheduleForm.jsp?projectId='+$("#projectId").val());
			});
		});
	});
