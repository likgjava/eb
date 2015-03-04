var setOpenbidGeneralview={};
setOpenbidGeneralview.begin =function(){
	var index= $("#lastNum").val() ;
$("input[name='factor'][type='checkbox']:checked").each(function(){
	  	var selectId = $(this).attr("id");
	    var projId = $(this).next().val();
	    var factorIds = $("#factorIds").val();
	    var factorName = $(this).parent().next().text();
	    var selectprojIds = projId.split(',');
	    	 factorIds += selectId +',' ;
	    	$("#factorIds").val(factorIds);	
	    	index ++;
	    	for(var i=0;i<selectprojIds.length;i++){ //添加Table列
	   		 var table = $("div[id='genViewDefine'] table[id="+selectprojIds[i]+"]");
	   		 var subProjectId = table.find("input[id='subProjectId']").val();
	   		 table.find("tr:first").append("<th>"+factorName+"<input type='hidden' id='"+selectId+"' name='factorMessage' value='"+selectId+"|"+subProjectId+"|"+factorName+"|"+index+"'></th>");
	    	}
})
	$("#lastNum").val(index);

	
}
$(document).ready(function(){
	$("#genViewDefine").hide();
	$("#saveId").hide();
	$("#backId").hide();
	$('#tabs').tabs();
	$("#fenzhi1").remove();
	$("td[name='score1']").each(
			function(){
				if($(this).text()=='0.0'){
					$(this).remove();
				}
			}
		)
	setOpenbidGeneralview.begin();
	var index= $("#lastNum").val();
    $("input[name='factor'][type='checkbox']").click(function(){
    var selectId = $(this).attr("id");
    var projId = $(this).next().val();
    var factorIds = $("#factorIds").val();
    var factorName = $(this).parent().next().text();
    var selectprojIds = projId.split(',');
   
    if($(this).attr("checked")){//选中
    	 factorIds += selectId +',' ;
    	$("#factorIds").val(factorIds);	
    	index ++;
    	for(var i=0;i<selectprojIds.length;i++){ //添加Table列
   		 var table = $("div[id='genViewDefine'] table[id="+selectprojIds[i]+"]");
   		 var subProjectId = table.find("input[id='subProjectId']").val();
   		 table.find("tr:first").append("<th>"+factorName+"<input type='hidden' id='"+selectId+"' name='factorMessage' value='"+selectId+"|"+subProjectId+"|"+factorName+"|"+index+"'></th>");
    	}
    }else{//没选中
       var factorIdArrays = factorIds.split(',');
    	factorIds='';
    	for(var i=0;i<factorIdArrays.length-1;i++){
    		if(selectId!=factorIdArrays[i]){
    			factorIds+=factorIdArrays[i]+',';
    		}
    	}
    	
	  $("div[id='genViewDefine'] table").each(function(){//删除Table列
		    $(this).find("input[id="+selectId+"]").parent().remove();
		    });
	  
      $("#factorIds").val(factorIds);
    }
    })
    
    $("input[id='checkAll'][type='checkbox']").toggle(
    	function(){
    		var id=$(this).attr("val");
    		 $("input[name='factor'][type='checkbox']").each(function(){
    			 if($(this).attr("val")==id){
    			 $(this).attr("checked","checked")	 
    			 $(this).click();
    			 $(this).attr("checked","checked")	 
    			}
    		 });
    	},
    	function(){
    		var id=$(this).attr("val");	
   		 	$("input[name='factor'][type='checkbox']").each(function(){
   			 if($(this).attr("val")==id){
   				$(this).attr("checked","");
   				$(this).click();
   				$(this).attr("checked","");
   			}
   		 });
    	}
    )
    
    
    
    
    $("#nextId").click(function(){
    	var factorIds = $("#factorIds").val();
    	if(factorIds==null||factorIds==''||factorIds==undefined){
    		alert("至少选择一个指标！");
    	}else{
    		$("#genViewDefine").show();
        	$("#saveId").show();
        	$("#backId").show();
        	$("#nextId").hide();
        	$("#tabs").hide();
    	}
    	
    })
    
    
    $("#backId").click(function(){
    	$("#tabs").show();
    	$("#nextId").show();
    	$("#genViewDefine").hide();
    	$("#saveId").hide();
    	$("#backId").hide();
    })
    	
    
    
    
    
    $("#saveId").click(function(){
    	var allMessage = '';
    	$("input[name='factorMessage']").each(function(){
    		var factorMessage = $(this).val();
    		allMessage += factorMessage +',';
    	})
    	var projectId = $("input[id='project_objId']").val();
    	$.getJSON($("#initPath").val()+"/GenviewDefineController.do?method=saveGenviewDefine",{message:allMessage,projectId:projectId},function(json){
    		if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
    			planTemplateTask.clickMethod($("#projectTaskId").val()+"");
    			planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
    		  } else {
    			$("#myDesktop").click();
    		}


    	});

    })
	
})