var resProjectSubList = {};
resProjectSubList.loadInit = function(){
	$('#resProjectSubListGrid').flexOptions({params:{"parentId":$('#parentId').val()}});
	return true;
}
function getAgentMessage(resProjectId){
	$('#conBody').loadPage($('#initPath').val()+'/ProjectController.do?method=searchProjectListDljg&serviceName=searchProjectListForAgentManager&resProjectId='+resProjectId);			
}
resProjectSubList.success = function(){
	var projectStatus = "";
	//添加查看招标中心信息超链接
	   $("#resProjectSubListGrid").flexGetColByName({
	   	'projectName':function(id,t){
	   		var json = $("#resProjectSubListGrid").flexGetRowJsonById(id); 
	   		projectStatus = json['projectStatus'];
	   		var isLeaf = json['isLeaf'];
		    if(isLeaf=='1'){
		    	 $(t).html("<a href='#' onClick=getAgentMessage('"+id+"');>"+$(t).html()+"</a>");
		    }
		   }
		});
	optionStr={
			'<span><a href="#" class="abtn">立项</a>&nbsp;</span>': function(btn,rowId,obj){
				if(projectStatus=='02'){
					$(btn).text('');
				}else{
					 btn.click(function(){
						 $('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=loadCreateTenderProjectPage&parentId="+$('#parentId').val()+"&resProjectId="+rowId);
					 }).appendTo(obj);
				}
			}
		   ,'<span><a href="#" class="abtn">修改</a>&nbsp;</span>': function(btn,rowId,obj){
				if(projectStatus=='02'){
					$(btn).children('a').text('查看');
					btn.click(function(){
						 $('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=loadResProjectInfo&viewName=look&parentId="+$('#parentId').val()+"&resProjectId="+rowId);
					 }).appendTo(obj);
				}else{
					btn.click(function(){
						 $('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=loadResProjectInfo&parentId="+$('#parentId').val()+"&resProjectId="+rowId);
					 }).appendTo(obj);
				}
			}
		   ,'<span style="width=80px;"><a href="#" class="abtn">删除</a>&nbsp;</span>': function(btn,rowId,obj){
			     btn.click(function(){
			    	 $.getJSON($('#initPath').val()+'/ResProjectController.do?method=deleteResProjectInfo',{'resProjectId':rowId}, function(json){
			    			if(json.result)alert(json.result);if(json.failure)return;
			    			$("#resProjectSubListGrid").reload();
			    		});
			 	}).appendTo(obj);}
		   ,'<span style="width=80px;"><a href="#" class="abtn">结束</a>&nbsp;</span>': function(btn,rowId,obj){
				if(projectStatus=='02'){
					$(btn).text('');
				}else{
					btn.click(function(){
				    	 $.getJSON($('#initPath').val()+'/ResProjectController.do?method=endResProject',{'resProjectId':rowId}, function(json){
				    			if(json.result)alert(json.result);if(json.failure)return;
				    			$("#resProjectSubListGrid").reload();
				    		});
				 	}).appendTo(obj);
				}
			 }
	   	}
	$("#resProjectSubListGrid").flexAddOptionStr(optionStr);
}

resProjectSubList.add = function(){
	$('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=loadResProjectInfo&parentId="+$('#parentId').val());
}

$(document).ready(function(){
	$('#back').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/ResProjectController.do?method=loadResProjectManagePage');
	});
});