/*推荐项目列表页面*/

var RecommendProject = {};
RecommendProject.currentPage = ''; //当前页数
RecommendProject.totalPage = ''; //总页数

//取消推荐
RecommendProject.cancleRrecommend=function(singleRecommendProjectId){
	var recommendProjectIds;
	if(singleRecommendProjectId == null){ //批量取消推荐
		recommendProjectIds = $("#projectList2").dtSelects();
		if(recommendProjectIds.length<=0){alert("请至少选择一行数据！");return;}
	}else{ //单个取消推荐
		recommendProjectIds = singleRecommendProjectId;
	}
	
	if(confirm("确定取消推荐？")){
		$.getJSON($("#initPath").val()+"/RecommendProjectController.do?method=remove", {"objId":recommendProjectIds.split(",")}, function(json){
			if(json.success){alert("操作成功！")}
			RecommendProject.oTable2.fnDraw(); //刷新列表
		});
	}
};

//推荐项目
RecommendProject.recommendProject=function(singleProjectId){
	var projectIds;
	if(singleProjectId == null){ //批量推荐
		projectIds = $("#projectList1").dtSelects();
		if(projectIds.length<=0){alert("请至少选择一行数据！");return;}
	}else{ //单个推荐
		projectIds = singleProjectId;
	}
	
	//填写项目推荐理由，上传项目图片
	$.epsDialog({
        title:'填写项目推荐理由',
        url:$('#initPath').val()+'/view/agreement/bargin/project/recommend/recommend_project_reason.jsp?&projectIds='+projectIds,
        width: '400',
        height: '450',
        onClose: function(){
			if($('#recommendSuccess').val() == '1'){
				RecommendProject.oTable1.fnDraw();
				$('#recommendSuccess').val('0');
			}
        }
    }); 
};

//加载已推荐项目列表
RecommendProject.recommendProjectList=function(){
	RecommendProject.oTable2 = $('#projectList2').dataTable({
		'singleSelect' : false,
		'checkbox' : true,
		'queryColumns' : 'picture,project.projCode,project.projName,project.evalStartTime,project.evalEndTime',
		'hiddenColumns':'',
		'params' : {"order":"sort"},
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			RecommendProject.oTable1.oSettings = oSettings;
			var totalRecords = oSettings._iRecordsTotal;
			RecommendProject.currentPage = oSettings._iDisplayStart;
			var pageSize = oSettings._iDisplayLength;
			var totalPage = totalRecords % pageSize == 0 ? totalRecords/pageSize : totalRecords/pageSize+1;
			RecommendProject.totalPage = parseInt(totalPage);
		   
			//第一页
			if(RecommendProject.currentPage==1){
				$("#projectList2 tbody tr:first a[name=up]>span").removeClass("siUp").addClass("siUpGray");
			}
			//最后一页
			if(RecommendProject.currentPage==RecommendProject.totalPage){
				$("#projectList2 tr:last a[name=down]>span").removeClass("siDown").addClass("siDownGray");
			}
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).find("td[name=picture]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData.picture+'" style="cursor:pointer" width="30px" height="30px" />');
			$(nRow).append('<td class="operation"><a href="javascript:void(0);" onclick="RecommendProject.upClick(this);return false;" name="up"><span class="sysicon siUp">&nbsp;</span></a><a href="javascript:void(0);" name="down" onclick="RecommendProject.downClick(this);return false;"><span class="sysicon siDown">&nbsp;</span></a></td>');
			$(nRow).append('<td class="operation"><a href="javascript:void(0);" onclick="RecommendProject.cancleRrecommend(\''+aData.objId+'\');return false;">取消推荐</a></td>');
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/RecommendProjectController.do?method=list",
		'searchZone':'recommendProjectSearchForm'			
	});
	
};

$(document).ready(function(){
	//加载tab页
	$('#epsTabs').tabs({}); 
	
	//加载未推荐项目列表
	RecommendProject.oTable1 = $('#projectList1').dataTable({
		'singleSelect' : false,
		'checkbox' : true,
		'queryColumns' : 'projCode,projName,budgetTotalMoney,evalStartTime,evalEndTime',
		'hiddenColumns':'',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			RecommendProject.oTable1.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).append('<td class="operation"><a href="javascript:void(0);" onclick="RecommendProject.recommendProject(\''+aData.objId+'\');return false;">推荐</a></td>');
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/RecommendProjectController.do?method=listNoRecommendProject",
		'searchZone':'noRecommendProjectSearchForm'			
	});
	
	//查询未推荐项目
	$("#query1").click(function() {
		RecommendProject.oTable1.fnDraw();
	});
	
	//查询已推荐项目
	$("#query2").click(function() {
		RecommendProject.oTable2.fnDraw();
	});
	
	//批量推荐项目
	$("#recommendProjectBatch").click(function(){
		RecommendProject.recommendProject();
	});
	
	//批量取消推荐
	$("#cancleRecommendBatch").click(function(){
		RecommendProject.cancleRrecommend();
	});
	
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		RecommendProject.currentTabID = $(this).attr("id");
		if(RecommendProject.currentTabID == "tabs_toNoRecommend"){ //未推荐
			RecommendProject.oTable1.fnDraw();
			$("#recommendProjectSearchForm").hide();
			$("#noRecommendProjectSearchForm").show();
			$("#cancleRecommendBatchsLi").hide();
			$("#recommendProjectBatchLi").show();
		}else if(RecommendProject.currentTabID == "tabs_toRecommend"){ //已推荐
			if(!RecommendProject.oTable2){
				RecommendProject.recommendProjectList();
			}else{
				RecommendProject.oTable2.fnDraw();
			}
			$("#noRecommendProjectSearchForm").hide();
			$("#recommendProjectSearchForm").show();
			$("#recommendProjectBatchLi").hide();
			$("#cancleRecommendBatchsLi").show();
		}
	});
});

//设置上下移动的样式
RecommendProject.drawUpAndDownCss=function(){
	$("#projectList2 tbody tr a[name=up]>span").removeClass("siUpGray").addClass("siUp");
	$("#projectList2 tbody tr a[name=down]>span").removeClass("siDownGray").addClass("siDown");
	
	//顶级节点 
	if(RecommendProject.currentPage==1){
		$("#projectList2 tbody tr:first a[name=up]>span").removeClass("siUp").addClass("siUpGray");
	}
	if(RecommendProject.currentPage==RecommendProject.totalPage){
		$("#projectList2 tr:last a[name=down]>span").removeClass("siDown").addClass("siDownGray");
	}
}

//向上
RecommendProject.upClick=function(obj){
	if($(obj).find("span").hasClass("siUpGray")) { return false; }
	
	var curRow = $(obj).parent().parent();
	var targetRow = $(curRow).prev();
	var id = $(curRow).attr("objid");
	$.getJSON($('#initPath').val()+"/RecommendProjectController.do?method=updateSort",{"sourceObjId":id,"isToUp":"true"},function(json){})
	
	var firstRow = $("#projectList2 tr").eq(1);
	var firstObjId = $(firstRow).attr("objid");
	if(firstObjId == id){ //如果移动的是第一行，则重画列表。
		RecommendProject.oTable2.fnDraw();
	}else{
		$(targetRow).before(curRow);	//把当前行放到目标行之前
		RecommendProject.drawUpAndDownCss();	//重画向上向下的样式
	}
};

//向下
RecommendProject.downClick=function(obj){
	if($(obj).find("span").hasClass("siDownGray")) { return false; }
	
	var curRow = $(obj).parent().parent();
	var targetRow = $(curRow).next();
	var id = $(curRow).attr("objid");
	$.getJSON($('#initPath').val()+"/RecommendProjectController.do?method=updateSort",{"sourceObjId":id,"isToUp":"false"},function(json){})
	
	var lastRow = $("#projectList2 tr:last");
	var lastObjId = $(lastRow).attr("objid");
	if(lastObjId == id){ //如果移动的是最后一行，则重画列表。
		RecommendProject.oTable2.fnDraw();
	}else{
		$(targetRow).after(curRow);	//把当前行放到目标行之后
		RecommendProject.drawUpAndDownCss();	//重画向上向下的样式
	}
};
