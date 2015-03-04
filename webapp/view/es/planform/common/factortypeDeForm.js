
var factortypeDeForm={};

$(document).ready(function(){
	$("#facType").val($("#_facType").val());
	
	$('#save_btn').click(function(){
		if(!$('#factortypeDeForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/FactortypeDeController.do?method=createFactortypeDe', formToJsonObject('factortypeDeForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$("#factortypeDeForm").find('input').val("");
			$("#facType").val("0");
			factortypeDeList.initMenuTree("-1");// 刷新树
			factortypeDeList.laodFactorListView("");
		});
	});
});
