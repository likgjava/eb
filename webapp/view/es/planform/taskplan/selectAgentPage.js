var selectAgentPage ={};

selectAgentPage.showDetail=function(objId){
	$.epsDialog({
        title:'查看申报书信息',
        url:$('#initPath').val()+'/TaskPlanController.do?method=showDetail&objId='+objId+'&type=selectAgent',
        width: '800',
        height: '600',
 	    isReload:true
			});
}

selectAgentPage.selectedTypePage=function(){
	var oldTypePage = $("#_drawType").val();
	if(oldTypePage!=null&&oldTypePage!=''){
		$("input[type=radio]").removeAttr('checked');
		$("input[type=radio]").each(function(){
			if($(this).val()==oldTypePage){
				$(this).attr('checked','checked');
			}
		})
	//	$("input[type=radio]").attr('disabled','disabled');
	}
	
	
	$("input[type=radio]:checked").each(function(){
		if($(this).val()=='00'){//随机抽取
		$("#selectAgentPage").loadPage($('#initPath').val()+'/view/es/planform/taskplan/randomSelectAgentDetailView.jsp');
		};
		if($(this).val()=='01'){//单项选择
		$("#selectAgentPage").loadPage($('#initPath').val()+'/view/es/planform/taskplan/onlyOneSelectAgentDetailView.jsp');	
		};
	})
}

$(document).ready(function(){
	selectAgentPage.selectedTypePage();
	$(":radio").click(function(){
		if($(this).val()=='00'){//随机抽取
			$("#selectAgentPage").loadPage($('#initPath').val()+'/view/es/planform/taskplan/randomSelectAgentDetailView.jsp');
			};
		if($(this).val()=='01'){//单项选择
			$("#selectAgentPage").loadPage($('#initPath').val()+'/view/es/planform/taskplan/onlyOneSelectAgentDetailView.jsp');	
			};
	})
})