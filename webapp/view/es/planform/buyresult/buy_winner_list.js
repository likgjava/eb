
var buy_winner_list={};
buy_winner_list.edit=function(winnerId,subProjectId){
	$.epsDialog({
		 title:'发送通知书',
        url:$('#initPath').val()+'/NoticeController.do?method=toDraftNotice&winnerId='+winnerId+'&subProjectId='+subProjectId,
        width: '700',
        height: '400',
        isReload:false,
        onOpen: function(){ },
        afterLoad: function(){ },
        onClose: function(){ 
        	$("#tabs"+subProjectId).click();
        }
	})
	//$('#draftNoticeDiv').loadPage($('#initPath').val()+'/NoticeController.do?method=toDraftNotice&winnerId='+winnerId+'&subProjectId='+subProjectId);
}
$(document).ready(function(){
	
});