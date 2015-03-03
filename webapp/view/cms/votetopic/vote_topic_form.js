
var voteTopicForm={};
var voitItemCount = 0;

voteTopicForm.initVoteItem = function(objId, title, votePercent, count){
	var _voteItem = "";
	_voteItem += "<li class='fullLine'>";
	_voteItem += "<label for='投票项'>投票项</label>";
	_voteItem += "<input type='hidden' name='VoteItem["+voitItemCount+"].objId' value='"+objId+"'>";
	_voteItem += "<input type='text' name='VoteItem["+voitItemCount+"].title' id='title' class='required' value='"+title+"' size='50'/>";
	_voteItem += "<span class='eleRequired'>*</span><span class='spaceused' >"+votePercent+"</span><a href='javascript:void(0)' name='delete'>删除</a>";
	_voteItem += "</li>";
	$("#voteItemListForm").append(_voteItem).find("a[name=delete]").click(function(){
		if(objId != ""){
			if(window.comfirm("确认要删除此投票项吗？")){
				$.getJSON($('#initPath').val()+'/VoteItemController.do?method=remove',{objId:objId},function(json){
					if(json.failure){alert(json.result);return;}
				});
			}
		}
		$(this).parent().remove();
	});
}
$(document).ready(function(){
	$('#voteTopicForm').validate();
     $("#startTime").epsDatepicker();
     $("#endTime").epsDatepicker();
    if($('#voteTopicId').val()!=''){
    	$.getJSON($('#initPath').val()+'/VoteTopicController.do?method=createOrUpdate',{objId:$('#voteTopicId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('voteTopicForm', json.voteTopic);
    	});
    	voteTopicForm.voteItemsJson=$.ajax({url: $('#initPath').val()+"/VoteItemController.do?method=getObject",data:{"voteTopic.objId":$('#voteTopicId').val()}, async: false}).responseText;
    	$(JSON.parse(voteTopicForm.voteItemsJson).list).each(function(i,n){
    		voteTopicForm.initVoteItem(n.objId, n.title, n.votePercent+"%", voitItemCount++);
    	})
    }
    
	// 返回
	$('#historyBackBtn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/VoteTopicController.do");
	});
	
	// 提交
	$('#voteTopicSave').click(function(){
		if(!$('#voteTopicForm').valid()){alert('请正确填写表单!');return;}
		var voteTopic = formToJsonObject('voteTopicForm');
		if(undefined==voteTopic.close){voteTopic.close="false"}
		if(undefined==voteTopic.multSelect){voteTopic.multSelect="false"}
		voteTopic.voteItemsString = JSON.stringify(formToJsonObject('voteTopicForm','json').VoteItem);
		$.getJSON($('#initPath').val()+'/VoteTopicController.do?method=saveVoteTopic',voteTopic , function(json){
			if(json.failure){alert(json.result);return;}
			$('#conBody').loadPage($('#initPath').val()+'/VoteTopicController.do');
		});
	});
	$(".spaceused").progressBar();
});
$("#addVoteItem").click(function(){
	voteTopicForm.initVoteItem("","", "", voitItemCount++);
	
});
