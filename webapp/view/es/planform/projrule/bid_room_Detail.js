/**
 * 选择评标室JS
 * author: liuke
 * date：  2010-5-7
 * */

var m=0;
var isdraw=0;
function changeDate(d) {
	  var now=new Date();
	  var n=now.getDay(); //得到今天是星期几
	  var monday = new Date();
	  var tuesday = new Date();
	  var wednesday = new Date();
	  var thursday = new Date();
	  var friday = new Date();
	  var saturday = new Date();
	  var sunday = new Date();
	  if(n==0) {
		  monday.setDate(now.getDate()-n-7+1+7*d);
		   tuesday.setDate(now.getDate()-n-7+2+7*d);
		   wednesday.setDate(now.getDate()-n-7+3+7*d);
		   thursday.setDate(now.getDate()-n-7+4+7*d);
		   friday.setDate(now.getDate()-n-7+5+7*d);
		   saturday.setDate(now.getDate()-n-7+6+7*d);
		   sunday.setDate(now.getDate()-n-7+7+7*d)
	  } else {
		   monday.setDate(now.getDate()-n+1+7*d);
		   tuesday.setDate(now.getDate()-n+2+7*d);
		   wednesday.setDate(now.getDate()-n+3+7*d);
		   thursday.setDate(now.getDate()-n+4+7*d);
		   friday.setDate(now.getDate()-n+5+7*d);
		   saturday.setDate(now.getDate()-n+6+7*d);
		   sunday.setDate(now.getDate()-n+7+7*d)   
	  }
	 
	   var mond = Datetransform(monday);
	   var tuesd = Datetransform(tuesday);
	   var wednesd = Datetransform(wednesday);
	   var thursd = Datetransform(thursday);
	   var frid = Datetransform(friday);
	   var saturd = Datetransform(saturday);
	   var sund = Datetransform(sunday);
	   $("#mond").val(mond);
	   $("#tuesd").val(tuesd);
	   $("#wednesd").val(wednesd);
	   $("#thursd").val(thursd);
	   $("#frid").val(frid);
	   $("#saturd").val(saturd);
	   $("#sund").val(sund);
	   
	  monday = (monday.getMonth()+1) + "月" + monday.getDate()+"日"; 
	  tuesday =(tuesday.getMonth()+1) + "月" + tuesday.getDate()+"日";
	  wednesday =(wednesday.getMonth()+1) + "月" + wednesday.getDate()+"日";
	  thursday =(thursday.getMonth()+1) + "月" + thursday.getDate()+"日";
	  friday =(friday.getMonth()+1) + "月" + friday.getDate()+"日";
	  saturday =(saturday.getMonth()+1) + "月" + saturday.getDate()+"日"; 
	  sunday =(sunday.getMonth()+1) + "月" + sunday.getDate()+"日";
	  $("#monday").empty().html(monday)  ;
	  $("#tuesday").empty().html(tuesday) ;
	  $("#wednesday").empty().html(wednesday) ;
	  $("#thursday").empty().html(thursday)  ;
	  $("#friday").empty().html(friday) ;
	  $("#saturday").empty().html(saturday) ;
	  $("#sunday").empty().html(sunday) ;
    
	}
//将当天日期背景颜色转换成红色方法
  function changeTodayColor() {
	  //先将颜色全部设置回原来颜色
	  $(".mon").css("background-color","#F5F5DC");
	  $(".tue").css("background-color","#F5F5DC");
	  $(".wed").css("background-color","#F5F5DC");
	  $(".thu").css("background-color","#F5F5DC");
	  $(".fri").css("background-color","#F5F5DC");
	  $(".sat").css("background-color","#b0e0e6");
	  $(".sun").css("background-color","#b0e0e6");
	  
		var now=new Date();
		var n=now.getDay(); //得到今天是星期几
		var today = Datetransform(now);
		if((today >=$("#mond").val())&&(today<=$("#sund").val())){   //在本周之内
			  switch(n) {
			  case 1:
				  $(".mon").css("background-color","#ffe4e1");
			  break;
			  case 2:
				  $(".tue").css("background-color","#ffe4e1");
			  break;
			  case 3: 
				  $(".wed").css("background-color","#ffe4e1");
			  break;
			  case 4:
			      $(".thu").css("background-color","#ffe4e1");
			  break;
			  case 5:
				  $(".fri").css("background-color","#ffe4e1");
			  break;
			  case 6:
				  $(".sat").css("background-color","#ffe4e1");
			  break;
			  case 0:
				  $(".sun").css("background-color","#ffe4e1");
			  break;
			  }
		}
	
  }


 //展示时间方法
 function showdate() { 
	 changeDate(m);
	 dothing(isdraw);
 } 
    //增加时间方法
    function addDate() { 
  m++;
  isdraw=1;
  changeDate(m);
  dothing(isdraw);
 } 
    //减少时间方法 
    function deleteDate(day)  { 
     m--;
     isdraw=1;
     changeDate(m);
     dothing(isdraw);
    }   
    //时间转换方法
    function Datetransform(date) {
    	var d = date;
            d =d.getFullYear()+"-"+
 	   ((d.getMonth()+1)>9?(d.getMonth()+1):"0"+(d.getMonth()+1))+"-"+
 	   (d.getDate()>9?d.getDate():"0"+d.getDate());
    	return d;
    }
    //删除表格方法
    function dropTable(ro) {
    	  for(var i=0;i<ro.meetRooms.length;i++) {
     	   var roomName = ro.meetRooms[ro.meetRooms.length-1-i].meetRoomName;
     	   $("tr").remove("."+roomName); 
   	    }
    }
    //画表格方法
    function drawTable(ro) {
    	  for(var i=0;i<ro.meetRooms.length;i++) {
     	    
     	    //画表格
     	   var meetRoomId = ro.meetRooms[ro.meetRooms.length-1-i].objId;
       	   var  roomName = ro.meetRooms[ro.meetRooms.length-1-i].meetRoomName;
       	      $("#title").after('<tr class="content" name="Morning"><td>'+roomName+' <span class="sysicon siExit" title="删除标评室" name="'+meetRoomId+'"></span></td><td class="mon"><span class="orderRoom">空闲</span></td><td class="tue"><span class="orderRoom">空闲</span></td><td class="wed"><span class="orderRoom">空闲</span></td><td class="thu"><span class="orderRoom">空闲</span></td><td class="fri"><span class="orderRoom">空闲</span></td><td class="sat"><span class="orderRoom">空闲</span></td><td class="sun"><span class="orderRoom">空闲</span></td></tr>');	
       	      $(".content").removeClass("content").addClass(roomName); 
       	      //给每个td设置一个唯一的id
       	      $("."+roomName+"[name='Morning'] td").filter(".mon").attr("id",$("#mond").val()+meetRoomId+"Morning");
       	      $("."+roomName+"[name='Morning'] td").filter(".tue").attr("id",$("#tuesd").val()+meetRoomId+"Morning");
       	      $("."+roomName+"[name='Morning'] td").filter(".wed").attr("id",$("#wednesd").val()+meetRoomId+"Morning");    //设置上午的时间ID
       	      $("."+roomName+"[name='Morning'] td").filter(".thu").attr("id",$("#thursd").val()+meetRoomId+"Morning");
       	      $("."+roomName+"[name='Morning'] td").filter(".fri").attr("id",$("#frid").val()+meetRoomId+"Morning");
       	      $("."+roomName+"[name='Morning'] td").filter(".sat").attr("id",$("#saturd").val()+meetRoomId+"Morning");
       	      $("."+roomName+"[name='Morning'] td").filter(".sun").attr("id",$("#sund").val()+meetRoomId+"Morning");
       	      
       	      //改变当天日期颜色
       	      if(i==ro.meetRooms.length-1) { //只在表格全画完时改变颜色
       	    	 changeTodayColor();
       	      }
       	      //删除按钮样式修改
       	       $("span[class='sysicon siExit']").css("cursor","hand");
       	      
     	    }
    }
    
    //查找预定时间方法
    function PresetTime(rotime,ro) {
    	var projectId = $("#projpackId").val();//当前项目Id
    	//循环标评室
    	for(var k =0;k<ro.meetRooms.length;k++) {
    		var  roomName = ro.meetRooms[ro.meetRooms.length-1-k].meetRoomName;
    		var  roomId = ro.meetRooms[ro.meetRooms.length-1-k].objId;
    		//循环所有时间
    		for(var j = 0;j<rotime.presetTime.length;j++) {
   	    	var tdate = rotime.presetTime[j].startDate.substring(0,10);
   	    	var timeBucket = rotime.presetTime[j].timeBucket;
   	    	var tid = rotime.presetTime[j].meetRoom.objId;
   	    	var pid = rotime.presetTime[j].project.objId;
   	    	var obid = rotime.presetTime[j].objId;
   	    	var projName = rotime.presetTime[j].project.projName;
   	    	var proCode = rotime.presetTime[j].project.projCode;
   	    	var dayArray = new Array("#mond","#tuesd","#wednesd","#thursd","#frid","#saturd","#sund");
	    	for(var i=0;i<dayArray.length;i++){
	    		if(tdate==$(dayArray[i]).val()&& tid == roomId) {  //判断日期是否相等，标评室Id是否相等
	    			//修改页面显示  //下午为1 ，上午为0;
	    			if(timeBucket=='1') {
	    				$("#"+$(dayArray[i]).val()+roomId+"Afternoon" +" span").text("占用");
	    				if(pid==projectId) {  //修改当前项目预定房间的颜色
	    					$("#"+$(dayArray[i]).val()+roomId+"Afternoon").css("background-color","#ff4500");
	    					$("#"+$(dayArray[i]).val()+roomId+"Afternoon" +" span").attr("id",obid);
	    					$("#"+$(dayArray[i]).val()+roomId+"Afternoon" +" span").attr("title",proCode);
	    				}
	    			}
	    			else {
	    				$("#"+$(dayArray[i]).val()+roomId+"Morning" +" span").text("占用");
	    				if(pid==projectId) {  
	    					$("#"+$(dayArray[i]).val()+roomId+"Morning").css("background-color","#ff4500");
	    					$("#"+$(dayArray[i]).val()+roomId+"Morning" +" span").attr("id",obid);
	    					$("#"+$(dayArray[i]).val()+roomId+"Morning" +" span").attr("title",proCode);
	    				}
	    			}
	    		}
	    	}

 	  	    }
    	}	
    }
    
	//查看项目信息
	function getProjectMessage(projectId){
		$.epsDialog({
	        title:'项目信息',
	        url:$('#initPath').val()+'/ProjectController.do?method=toProjectMessagePage&projectId='+projectId,
	        width: '800',
	        height: '600',
	        onOpen: function(){ },
	        afterLoad: function(){ },
	        isReload:false,
	        onClose: function(){ 
	        }
		});	
	}
    
    function dothing(isdraw) {
    	var rooms =null;
    	var roomtimes =null;
    	$.getJSON($('#initPath').val()+'/MeetRoomController.do?method=getBidMeetRoom',function(jsonrooms){
    	    rooms = jsonrooms;
    	    $.getJSON($("#initPath").val()+'/MeetRoomController.do?method=getPresetTime',{startTime:$("#mond").val() ,endTime:$("#sund").val()},function(json){   
        		roomtimes = json;
        		if(isdraw!=0) {//如果点击了上一周或下一周，表示不是第一次进入
        			dropTable(rooms);
        		}
        		drawTable(rooms);
        		PresetTime(roomtimes,rooms);
        		$(".orderRoom").mouseout(function(){
        			if($(this).text()=="空闲"){return false;}
        			var parentId = $(this).parent().attr("id");
        		    if(parentId.indexOf("Afternoon")!=-1) {
          	         } else {                                 
          	         }
        		})
        		  //点击事件触发
        	    $(".orderRoom").click(function(){
        	    	var parentId = $(this).parent().attr("id");
        	        var rid = null;
        	        var rtime =null;
        	        var timBuc =null;
        	        var isSelect = 'no';
        	        var isSelectId =null;
        	         if(parentId.indexOf("Afternoon")!=-1) {//判断为下午
        	        	 var pareId = parentId.replace("Afternoon","");
        	        	 rtime = $(this).parent().attr("id").substring(0,10);
        	        	 rid = pareId.substring(10);
        	        	 timBuc ='1';                      // timBuc ='1'表示为上午
        	         } else {                                  //否则为上午
        	        	 var pareId = parentId.replace("Morning","");
        	        	 rtime = $(this).parent().attr("id").substring(0,10);
        	        	 rid = pareId.substring(10);
        	        	 timBuc ='0';                      //timBuc ='0'表示为下午
        	         }
        	         if($(this).parent().css("background-color")=='rgb(255, 69, 0)'||$(this).parent().css("background-color")=='yellow'||$(this).parent().css("background-color")=='rgb(255, 255, 0)'){ //表示该占用是当前项目
        	        	 isSelect = 'yes';
        	             isSelectId = $(this).attr("id");
        	         }else{                      //标示该占用是其他项目
        	        	 isSelectId = $(this).attr("id");
        	         }
        	    	if($(this).text()=="空闲"){
        	    		var now=new Date();
        	    		var n=now.getDay(); //得到今天是星期几
        	    		var today = Datetransform(now);
        	    		if(rtime<=today){
        	    			alert("预定时间必须大于当前日期！");
        	    		}else {
        	    			$.epsDialog({
            	    	        title:'评标室信息',
            	    	        url:$('#initPath').val()+'/view/es/planform/projrule/reserveRoomFrom.jsp?rid='+rid+'&rtime='+rtime+'&timBuc='+timBuc+'&projpackId='+$("#projpackId").val(),
            	    	        width: '450',
            	    	        height: '200',
            	    	        onOpen: function(){ },
            	    	        afterLoad: function(){ },
            	    	        isReload:false,
            	    	        onClose: function(){ 
            	    				var workFlowTaskId = $('[id=auditTaskId]').val();
            	    				if(undefined == workFlowTaskId || null == workFlowTaskId || '' == workFlowTaskId){
            	    					
            	    				}else{
            	    					$("#myDesktop").click();
            	    				}
            	    	        }
            	    		});	
        	    		}
        	    		
        	    	}else {
        	 			var parentId = $(this).parent().attr("id");
            			//通过主键查询标评室信息
        		    	var startDateStr = parentId.substring(0,10)+"00:00:00";
        		    	var endDateStr =	parentId.substring(0,10)+"23:59:59";
        		    	var rid = "";
        		    	if(parentId.indexOf("Afternoon")!=-1) {
        		    		rid = parentId.replace("Afternoon","").substring(10);
        		    	}else{
        		    		rid = parentId.replace("Morning","").substring(10);
        		    	}
        		    	var str ="&startDate="+startDateStr+"&endDate="+endDateStr+"&meetroomId="+rid
        		    	$.epsDialog({
        	    	        title:'预定信息',
        	    	        url:$('#initPath').val()+'/MeetRoomController.do?method=toBidRoomsList'+str+'',
        	    	        width: '500',
        	    	        height: '230',
        	    	        onOpen: function(){ },
        	    	        isReload:false,
        	    	        afterLoad: function(){ },
        	    	        onClose: function(){
        	    				var workFlowTaskId = $('[id=auditTaskId]').val();
        	    				if(undefined == workFlowTaskId || null == workFlowTaskId || '' == workFlowTaskId){
        	    					
        	    				}else{
        	    					$("#myDesktop").click();
        	    				}
        	    	        }
        	    		});	
       	    		}
        		});
        	    
        	    //删除评标室
        	    $("span[class='sysicon siExit']").click(function(){
        	    	if(window.confirm('确定删除评标室?')){
        	    		jQuery.getJSON($("#initPath").val()+'/MeetRoomController.do?method=removeMeetRoomByojbId',{objId:$(this).attr("name")},function(json){
        	    		if(json.result)alert(json.result);if(json.failure)return;
        	    		if(json.refreshPage=='true'){
        	    			planTemplateTask.clickMethod($("#projectTaskId").val()+"");
        	    		}
       	    			});
    	    		}
        	    });
        	    
        	    
        	    
        	})
      	})
    }
    $(document).ready(function(){
    	showdate();
    })
   