
var buy_winner_list={};
buy_winner_list.edit=function(winnerId,subProjectId){
	$.epsDialog({
		 title:'查看通知书',
        url:$('#initPath').val()+'/NoticeController.do?method=toNoticeDetail&winnerId='+winnerId+'&subProjectId='+subProjectId,
        width: '700',
        height: '350',
        isReload:false,
        onOpen: function(){ },
        afterLoad: function(){ },
       
	})
	//$('#draftNoticeDiv').loadPage($('#initPath').val()+'/NoticeController.do?method=toDraftNotice&winnerId='+winnerId+'&subProjectId='+subProjectId);
}
$(document).ready(function(){
	
});