var blockTradeForRandom = {};

var org_id="";
var selectedOrgName = "";
var selectedOrgId = "";
//修改包组
function updateSubProject(id,projectId){
	$.epsDialog({
        title:"修改包组",
        url:$("#initPath").val()+"/ProjectController.do?method=toSubProjectCraeteOrUpdate&subProjId="+id,
        width: 800,
        height: 400,
        isReload: false,
        onClose: function(){
			$('#conBody').loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfoForAgent&objId='+$("#projectId").val());
       	}
	});
}

    //显示图片
	function showImg() {
    	$.epsDialog({
		    id :"showImg",
	        title:"抽取招标中心",
	        url:$("#initPath").val()+"/view/es/planform/taskplan/RandomAgent.jsp",
	        width: 400,
	        height: 150,
	        isReload: true,
	       	afterLoad:function() {
	       		$('#showImg').find(".epsDialogClose").hide();
	       		$('#selectAgent').find(".epsDialogClose").hide();
	       	}
		});
       
}	

	//关闭
	$("#subProjectReturn").click(function(){
		$('.epsDialogClose').click();
	});
	
	//确认 
	  $("#subProjectSure").click(function(){
		   var flag = '0';
		  $("input[name='name']").each(function(){
			  if($(this).val()==''){
				  flag ='1';
			  }
		  })
		  if(flag=='1'){
			  alert("请录入足够的招标中心！");
		  }else{
			  $("input[name='consAgent.orgName']").val(selectedOrgName);
			  $("input[name='consAgent.objId']").val(selectedOrgId);   
		 	  $('.epsDialogClose').click();
		  }
		  
	 	});
	
	
	//重置
	$("#subProjectReset").click(function(){
		$("#randomAgent").empty().loadPage($('#initPath').val()+'/BlockTradeController.do?method=getAgents');
	});

	
	$(document).ready(function(){
		var count = 0;
		function saveAgent(button)
		{
			var eps = showImg();
			$.getJSON($('#initPath').val()+'/BlockTradeController.do?method=getAgentForRandom',{}, function(json){
				if(json.result)alert(json.result);
				if(json.failure){
					$('#showImg').find(".epsDialogCloseNoReload").click();
					return;
				}
				$(button).attr("disabled","disabled").attr("value","抽取中");
				//暂停4秒后关闭弹出层
			    setTimeout(
			    	function(){
			    		$('#showImg').find(".epsDialogCloseNoReload").click();
			    		$(button).next("input").val(json.company.objId);
			    		$(button).prev("input").val(json.company.orgName);
						$(button).attr("disabled","disabled").attr("value","已经抽取");
						count = count +1;
						if((count)== ($("input[name='agentObjId']").length)){
							var org_ids="";
							$("input[name='agentObjId']").each(  
									function(){  
										if($(this).val()!='')
										{
											org_ids += $(this).val()+",";
										}
									}  
							)
							$.getJSON($('#initPath').val()+'/BlockTradeController.do?method=getWinAgent&org_id='+org_ids,{}, function(json){
							if(json.result)alert(json.result);if(json.failure)return;
							if(json.company=="false"){
								$("#reminder").html(" 此次抽取失败！              请重新抽取！ ");
								$("#subProjectReset").show();
							}else{
							     $("#reminder").html("恭喜："+json.company.orgName+"被抽中！");
							     $("#reminderid").val(json.company.objId);
							     selectedOrgName=json.company.orgName;
							     selectedOrgId=json.company.objId;   
							   
							    
							}
						});
						}
			    	},
			    	parseInt(4*1000)
			    );
			});
    	}
		
		$("#subProjectReset").hide();
		
		$(':button[name=randomButton]').click(function(){
			var button = $(this);
			 saveAgent(button);
		});
		
	});