var meetRoomForm={};
function deleteAgent(obj){
	$("#tr"+obj).remove();
}
$(document).ready(function(){
	$('#meetRoomForm').validate();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/MeetRoomController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('meetRoomForm', json.meetRoom);
    	});
    }
    var i = $('[name=facilitiesName]').length;
	$("#addFacilities2").click(function(){
				var a = i+1;
			    var html = "<div id='tr"+a+"'><div id='td"+a+"'>";
	    		html+= "设施名称:<input type='text' name='facilitiesName' id='facilitiesName"+a+"'  class='required'/>";
	    		html+= "设施类型:<select  name='facilitiesType' id='facilitiesType"+a+"' class='required'/></select><br>";
	    		html+= "设施描述:<input type='text'  name='facilitiesMemo' id='facilitiesMemo"+a+"' class='required'/>";
	    		html+= "使用方式:<select  name='facilitiesMethod' id='facilitiesMethod"+a+"' class='required'/></select><br/>";
	    		html+= "访问地址:&nbsp;<input type='text'  name='facilitiesAddr' id='facilitiesAddr"+a+"' class='required'/>";
	    		html+="<a href='#' onclick='deleteAgent("+a+")'>移除</a>"
	    		html+= "</div></div>";
	    		$("#facilities2").append(html);
	    		$("#facilitiesType"+a+"").append("<option value='00'>电脑</option><option value='01'>摄像头</option>");
	    		$("#facilitiesMethod"+a+"").append("<option value='00'>主控</option><option value='01'>辅助</option>");
	    		i++;
	 });
	//返回
	$('#meetRoomClose').click(function(){
		$('#epsDialogClose').click();
	});
	//提交
	$('#meetRoomSave').click(function(){
		if(!$('#meetRoomForm').valid()){alert('请正确填写表单!');return;}
		var jsonObj = {};
		var meetRoom = formToJsonObject('meetRoomForm','json');
		jsonObj.meetRoomStr = JSON.stringify(meetRoom);
	    var jsonContracts = new Array();
		for(j=0;j<$('[name=facilitiesName]').length;j++){
			var k= j+1;
			var obj = {};
			obj.facilitiesName=$('input[id="facilitiesName'+k+'"]').val();
            obj.facilitiesType=$('#facilitiesType'+k+'').val();
            obj.facilitiesMemo=$('input[id="facilitiesMemo'+k+'"]').val();
            obj.facilitiesAddr=$('#facilitiesAddr'+k+'').val();
            obj.facilitiesMethod=$('select[id="facilitiesMethod'+k+'"]').val();
			jsonContracts.push(obj);
	    }
		jsonObj.facilitiesStr = JSON.stringify(jsonContracts);
		$.getJSON($('#initPath').val()+'/MeetRoomController.do?method=updateTemporaryRoomAndFacilities', jsonObj, function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#meetRoomClose').click();
			$('#conBody').empty().loadPage($('#initPath').val()+'/view/es/planform/projrule/meetRoomcgbShowView2.jsp');
		});
	});

});
