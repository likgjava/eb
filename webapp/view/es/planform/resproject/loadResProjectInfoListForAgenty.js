var resProjectList = {};
resProjectList.loadInit = function(){
	$('#resProjectListGrid').flexOptions({params:{"projectName":$('#projectName').val(),"projectStatus":$('#projectStatus').val()}});
	return true;
}

function getAgentMessage(resProjectId){
	$('#conBody').loadPage($('#initPath').val()+'/ProjectController.do?method=searchProjectListDljg&serviceName=searchProjectListForAgentManager&resProjectId='+resProjectId);			
}
resProjectList.success = function(){
	//添加查看招标中心信息超链接
	   $("#resProjectListGrid").flexGetColByName({
	   	'projectName':function(id,t){
	   		var json = $("#resProjectListGrid").flexGetRowJsonById(id); 
	   		var isLeaf = json['isLeaf'];
		    if(isLeaf=='1'){
		    	 $(t).html("<a href='#' onClick=getAgentMessage('"+id+"');>"+$(t).html()+"</a>");
		    }
		   }
		});
	var optionStr ;
	if($('#projectStatus').val()=='all'){
		optionStr={
			'<span><a href="#" class="abtn">查看</a>&nbsp;</span>': function(btn,rowId,obj){
				 btn.click(function(){
					 $('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=loadResProjectInfo&viewName=look&resProjectId="+rowId);
				 }).appendTo(obj);}		
	   	}
	}else if($('#projectStatus').val()=='02'){
		optionStr={
			'<span><a href="#" class="abtn">查看</a>&nbsp;</span>': function(btn,rowId,obj){
				 btn.click(function(){
					 $('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=loadResProjectInfo&viewName=look&resProjectId="+rowId);
				 }).appendTo(obj);}
		,'<span><a href="#" class="abtn">子项目管理</a>&nbsp;</span>': function(btn,rowId,obj){
			var json = $("#resProjectListGrid").flexGetRowJsonById(rowId); 
			var isLeaf = json['isLeaf'];
			if(isLeaf=='1'){
				$(btn).text('');
			}else{
				btn.click(function(){
					 $('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=loadDisassemblePage&resProjectId="+rowId);
				 }).appendTo(obj);
			}}
	   	}
	}else{
		optionStr={
				'<span><a href="#" class="abtn">立项</a>&nbsp;</span>': function(btn,rowId,obj){
						var json = $("#resProjectListGrid").flexGetRowJsonById(rowId); 
						var isLeaf = json['isLeaf'];
						if(isLeaf=='0'){
							$(btn).text('');
						}else{
							btn.click(function(){
								 $('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=loadCreateTenderProjectPage&resProjectId="+rowId);
							 }).appendTo(obj);}}
				,'<span><a href="#" class="abtn">子项目管理</a>&nbsp;</span>': function(btn,rowId,obj){
					var json = $("#resProjectListGrid").flexGetRowJsonById(rowId); 
					var isLeaf = json['isLeaf'];
					if(isLeaf=='1'){
						$(btn).text('');
					}else{
						btn.click(function(){
							 $('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=loadDisassemblePage&resProjectId="+rowId);
						 }).appendTo(obj);
					}}
				,'<span><a href="#" class="abtn">修改</a>&nbsp;</span>': function(btn,rowId,obj){
					 btn.click(function(){
						 $('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=loadResProjectInfo&resProjectId="+rowId);
					 }).appendTo(obj);}
			   ,'<span style="width=80px;"><a href="#" class="abtn">删除</a>&nbsp;</span>': function(btn,rowId,obj){
				     btn.click(function(){
				    	 $.getJSON($('#initPath').val()+'/ResProjectController.do?method=deleteResProjectInfo',{'resProjectId':rowId}, function(json){
				    			if(json.result)alert(json.result);if(json.failure)return;
				    			$("#resProjectListGrid").reload();
				    		});
				 	}).appendTo(obj);}
			   ,'<span style="width=80px;"><a href="#" class="abtn">结束</a>&nbsp;</span>': function(btn,rowId,obj){
				     btn.click(function(){
				    	 $.getJSON($('#initPath').val()+'/ResProjectController.do?method=endResProject',{'resProjectId':rowId}, function(json){
				    			if(json.result)alert(json.result);if(json.failure)return;
				    			$("#resProjectListGrid").reload();
				    		});
				 	}).appendTo(obj);}
		   	}
	}
	$("#resProjectListGrid").flexAddOptionStr(optionStr);
}

resProjectList.add = function(){
	$('#conBody').loadPage($('#initPath').val()+'/ResProjectController.do?method=loadResProjectInfo');
}

$(document).ready(function(){
});
