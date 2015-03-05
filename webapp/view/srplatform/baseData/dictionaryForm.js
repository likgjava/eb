
var dictionaryForm={};

$(document).ready(function(){
	$('#dictionaryForm').validate();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/DictionaryController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:'dicType'},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('dictionaryForm', json.dictionary);
    	});
    }
    
	//返回
	$('#dictionaryReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/DictionaryController.do");
	});
	//提交
	$('#dictionarySave').click(function(){
		if(!$('#dictionaryForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/DictionaryController.do?method=save', formToJsonObject('dictionaryForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#conBody').loadPage($('#initPath').val()+'/DictionaryController.do');
		});
	});
	
	$('#dicTypeGroupName').autocomplete($('#initPath').val() + '/DictionaryTypeController.do?method=getObjectQuery&queryColumns=objId,groupName', {
		extraParams:{
//			'status':'1',
//			'status_relative':'[and]'
		},//查询条件  例如    {objId:111}
		matchColumn:'groupName',//作为查询显示, 被选中之后匹配的列
		minChars: 1,
		max:10,
		autoFill: true,
		mustMatch: true,
		scrollHeight: 220,
		formatItem: function(data, i, total) {
//			return '<I>'+data.shortSpellName+'</I>';
			return data.groupName;
		},
		formatMatch: function(data, i, total) {
			return data.groupName;
		},
		formatResult: function(data) {
			return data.groupName;
		}
	}).result(function(event,data,formatted){
		if(data){	 
			$('#dicTypeId').val(data.objId);
		}
	});   

});
