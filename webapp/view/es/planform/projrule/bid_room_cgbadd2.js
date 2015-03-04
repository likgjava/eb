function deleteAgent(obj){
	$("#tr"+obj).remove();
}
$(document).ready(function(){
	$("#temporaryRoom").hide();
	
	  $("#checkTemporaryRoom").click(function(){
		  $("#temporaryRoom").slideToggle();
	  })
	  var i = 0;
	  $("#addFacilities").click(function(){
				var a = i+1;
			    var html = "<tr id='tr"+a+"'><td id='td"+a+"'>";
	    		html+= "设施名称:<input type='text' name='facilitiesName' id='facilitiesName"+a+"'  class='required'/>";
	    		html+= "设施类型:<select  name='facilitiesType' id='facilitiesType"+a+"' class='required'/></select>";
	    		html+= "设施描述:<input type='text'  name='facilitiesMemo' id='facilitiesMemo"+a+"' class='required'/><br/>";
	    		html+= "使用方式:<select  name='facilitiesMethod' id='facilitiesMethod"+a+"' class='required'/></select>";
	    		html+= "访问地址:&nbsp;<input type='text'  name='facilitiesAddr' id='facilitiesAddr"+a+"' class='required'/>";
	    		html+="<a href='#' onclick='deleteAgent("+a+")'>移除</a>"
	    		html+= "</td></tr>";
	    		$("#facilities").append(html);
	    		$("#facilitiesType"+a+"").append("<option value='00'>电脑</option><option value='01'>摄像头</option>");
	    		$("#facilitiesMethod"+a+"").append("<option value='00'>主控</option><option value='01'>辅助</option>");
	    		i++;
	  })
	$("#addRoom").click(function(){
		  if($("#meetRoomName").val()=='') {
			 alert("评标室名称为必填项");
		  }
		  else if($("#meetRoomAddress").val()=='') {
			 alert("地址为必填项");
		  }
		  else {  
			var jsonObj = {};
			var meetRoom = formToJsonObject('RoomForm','json');
			jsonObj.meetRoomStr = JSON.stringify(meetRoom);
		    var jsonContracts = new Array();
			for(j=0;j<i;j++){
				var k= j+1;
				var obj = {};
				obj.facilitiesName=$('input[id="facilitiesName'+k+'"]').val();
	            obj.facilitiesType=$('#facilitiesType'+k+'').val();
	            obj.facilitiesMemo=$('input[id="facilitiesMemo'+k+'"]').val();
	            obj.facilitiesAddr=$('input[id="facilitiesAddr'+k+'"]').val();
	            obj.facilitiesMethod=$('#facilitiesMethod'+k+'').val();
	            alert( obj.facilitiesMethod);
				jsonContracts.push(obj);
		    }
			jsonObj.facilitiesStr = JSON.stringify(jsonContracts);
			$.getJSON($("#initPath").val()+'/MeetRoomController.do?method=saveTemporaryRoomAndFacilities',jsonObj,function(json){
			if(json.result)alert(json.result);if(json.failure)return;
				$("#conBody").empty().loadPage($("#initPath").val()+"/view/es/planform/projrule/bid_room_cgbDetail2.jsp?projectId="+$("#projectId").val());
			});  
		  }
     })
})