var ProjectInfoView = {};
$(document).ready(function(){
	//发布更正公告
	ProjectInfoView.releaseChangeBulletin = function(){
		  $.epsDialog({
		        title:'发布变更公告',
		        id:"changeBulletin",
		        url:$('#initPath').val()+'/VariationBulletinController.do?method=toDraftVariationBulletin&fromType=fromDesk&projectId='+$("#projectId").val(),
		        width: '800',
		        height: '400',
		 	    isReload:true,
		        onOpen: function(){ },
		        afterLoad: function(){ },
		        onClose: function(){ }
					});
	}
	
	//设置开标小组
	ProjectInfoView.setOpenBidGroup = function(){
		  $.epsDialog({
		        title:'设置开标小组',
		        id:"setOpenBidGroup",
		        url:$('#initPath').val()+'/WorkGroupController.do?method=toOpenBidWorkGroupForProject&fromType=fromDesk&groupType=03&projectId='+$("#projectId").val(),
		        width: '600',
		        height: '400',
		 	    isReload:true,
		        onOpen: function(){ },
		        afterLoad: function(){ },
		        onClose: function(){
		        }
					});
	}
	
	//查看质疑
	ProjectInfoView.lookOppugnReqAgency = function(){
		  $.epsDialog({
		        title:'查看质疑',
		        id:"lookOppugnReqAgency",
		        url:$('#initPath').val()+'/view/es/planform/oppugnrequisition/oppugnReqAgencyList.jsp?projectId='+$("#projectId").val(),
		        width: '600',
		        height: '400',
		 	    isReload:true,
		        onOpen: function(){ },
		        afterLoad: function(){ },
		        onClose: function(){
		        }
					});
		
	}
	
	//起草结果公示
	ProjectInfoView.draftResultPublicity = function(){
		$.epsDialog({
	        title:'起草结果公示',
	        id:"draftResultPublicity",
	        url:$('#initPath').val()+'/ResultPublicityController.do?method=toDraftResultPublicity&fromType=fromDesk&projectId='+$("#projectId").val(),
	        width: '800',
	        height: '400',
	 	    isReload:true,
	        onOpen: function(){ },
	        afterLoad: function(){ },
	        onClose: function(){
	        }
				});
	}
	
	//调整项目时间
	ProjectInfoView.setProjectTime = function(){
		$.epsDialog({
	        title:'项目时间维护',
	        id:"setProjectTime",
	        url:$('#initPath').val()+'/ProjectController.do?method=toUpdateProjectTime&fromType=fromDesk&projectId='+$("#projectId").val(),
	        width: '600',
	        height: '200',
	 	    isReload:true,
	        onOpen: function(){ },
	        afterLoad: function(){ },
	        onClose: function(){
	        }
				});
		
	}
	
	//查看开标一览表
	ProjectInfoView.lookOpenbidGeneral = function(){
		$.epsDialog({
	        title:'查看开标一览表',
	        id:"lookOpenbidGeneral",
	        url:$('#initPath').val()+'/OpenbidGeneralviewController.do?method=toOpenbidGeneralListview&projectId='+$("#projectId").val(),
	        width: '600',
	        height: '200',
	 	    isReload:true,
	        onOpen: function(){ },
	        afterLoad: function(){ },
	        onClose: function(){
	        }
				});
	}
	
	
	//查看历史
	ProjectInfoView.lookHistory = function(){
		$.epsDialog({
	        title:'查看历史',
	        id:"lookHistory",
	        url:$('#initPath').val()+'/ProjectViewController.do?method=toProjectHistoryRecord&projectId='+$("#projectId").val(),
	        width: '800',
	        height: '400',
	 	    isReload:true,
	        onOpen: function(){ },
	        afterLoad: function(){ },
	        onClose: function(){
	        }
				});
	}
	
	
})
 