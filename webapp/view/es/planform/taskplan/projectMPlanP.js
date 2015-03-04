var projectMPlanP={};
$(document).ready(function(){
	$("select[id='ebuyMethod']").change( function() {
			$("#search").click();
		}); 
	projectMPlanP.showDetail=function(objId){
		$.epsDialog({
	        title:'查看申报书信息',
	        url:$('#initPath').val()+'/TaskPlanController.do?method=showDetail&objId='+objId+'&type=selectAgent',
	        width: '800',
	        height: '600',
	        maxWin:false,
	 	    isReload:true
				});
	}
	projectMPlanP.viewProject=function(objId){
		$.epsDialog({
	        title:"项目信息",
	        url:$("#initPath").val()+"/ProjectViewController.do?method=toViewProjectForSupervise&projectId="+objId,
	        width: 650,
	        height: 265,
	        maxWin:false,
	        isReload: false
		});
	}
	projectMPlanP.viewTaskPlanSub=function(objId){
		$.epsDialog({
	    	title:'申报书条目-查看需求信息',
	    	url:$('#initPath').val()+'/TaskPlanController.do?method=toLookTaskPlanSubRequireInfoView&objId='+objId,
	    	width: '650',
	    	height: '450',
	    	maxWin:false,
	    	onClose: function(){}
		});
	}
	projectMPlanP.success=function(){
			$("#taskPlanSubForCreateProjGrid").flexGetColByName({
				'taskcode':function(id,t){var a=$('#taskPlanSubForCreateProjGrid').getRowById(id)["taskplanId"];$(t).html("<a href='#' class='abtn' onClick='projectMPlanP.showDetail(\""+a+"\")'>"+$(t).html()+"</a>")}
			});
			$("#taskPlanSubForCreateProjGrid").flexGetColByName({
				'taskName':function(id,t){var a=$('#taskPlanSubForCreateProjGrid').getRowById(id)["taskplanId"];$(t).html("<a href='#' class='abtn' onClick='projectMPlanP.showDetail(\""+a+"\")'>"+$(t).html()+"</a>")}
			});
			$("#taskPlanSubForCreateProjGrid").flexGetColByName({
				'purchaseName':function(id,t){var a=$('#taskPlanSubForCreateProjGrid').getRowById(id)["taskPlanSubId"];$(t).html("<a href='#' class='abtn' onClick='projectMPlanP.viewTaskPlanSub(\""+a+"\")'>"+$(t).html()+"</a>")}
			});
			$("#taskPlanSubForCreateProjGrid").flexGetColByName({
				'tproject.projName':function(id,t){var a=$('#taskPlanSubForCreateProjGrid').getRowById(id)["projectId"];$(t).html("<a href='#' class='abtn' onClick='projectMPlanP.viewProject(\""+a+"\")'>"+$(t).html()+"</a>")}
			});
			$("#taskPlanSubForCreateProjGrid").flexAddOptionStr({
				'<span><a href="#" class="abtn"><font color="blue">进入</font></a></span>' : function(btn,rowId,obj){
					btn.click(function(){
						$('#conBody').loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfo&toProjectInfoForBuyer=toProjectInfoForBuyer&isAJAX=true&objId='+$('#taskPlanSubForCreateProjGrid').getRowById(rowId)["projectId"]);
					}).appendTo(obj);
				}
			});
	}
	projectMPlanP.before =function(){
		var purchaseName =$("#purchaseName").val();
		var ebuyMethod =$("#ebuyMethod").val();
		var option={
				"purchaseName":purchaseName,"ebuyMethod":ebuyMethod
				}
		$('#taskPlanSubForCreateProjGrid').flexOptions({params:option});
		return true;
	}
	$("#purchaseName").autocomplete(
		$('#initPath').val() + '/PurCategoryController.do?method=getObjectQuery&queryColumns=objId,categoryName', 
		{
			matchColumn:'categoryName',//作为查询显示, 被选中之后匹配的列
			extraParams:{},
			mustMatch: true,
			formatItem: function(data, i, total) {
				return data.categoryName;
			},
			formatMatch: function(data, i, total) {
				return data.categoryName;
			},
			formatResult: function(data) {
				return data.categoryName;
			}
		}
	).result(function(event,data,formatted){
		if(data){
			$("#purchaseName").val(data.categoryName);//回填id
			$("#purCategoryId").val(data.objId);//回填id
		}
	});
});

