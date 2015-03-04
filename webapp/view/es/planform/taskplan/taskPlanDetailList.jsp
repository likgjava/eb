<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script>
/*
 * 执行平台，采购资金明细列表
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var taskPlanDetailList={};

//新增
taskPlanDetailList.add=function(name,grid){
	$.epsDialog({
	    title:'资金明细',
	    url:$('#initPath').val()+'/TaskPlanDetailController.do?method=toCreateTaskPlanDetail',
	    width: '500',
	    height: '250',
	    isReload: false,
	    onClose: function(){ 
	    	$(grid).reload();//刷新
	    }
	});	
} 

//修改
taskPlanDetailList.update=function(name,grid){
	if(!taskPlanDetailList.validation(name,grid))return;
	
	var id = $('#taskPlanDetailGrid').getRowById($(grid).getSelect())["taskPlanDetail.objId"];
	$.epsDialog({
        title:'资金明细',
        url:$('#initPath').val()+'/TaskPlanDetailController.do?method=toUpdateTaskPlanDetail&taskPlanDetailId='+id,
        width: '500',
        height: '250',
        isReload: false,
        onClose: function(){ 
        	$(grid).reload();//刷新
        }
	});	
}   

//删除
taskPlanDetailList.remove=function(name,grid){
	if(!taskPlanDetailList.validation(name,grid))return;
	if(window.confirm('确定'+name+'?')){
		$.getJSON($('#initPath').val()+'/TaskPlanMDetailController.do?method=remove',{objId:$(grid).getSelect()},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$(grid).reload();//刷新
		});
	}
}

//列表操作验证
taskPlanDetailList.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一条资金明细');return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请只选择一条资金明细');return false;}//是否只选中一个
	return true;
}

//查询条件过滤
taskPlanDetailList.before=function(){
	var option={"taskPlan.objId":$("#objId").val(),"status":"00"}
	$('#taskPlanDetailGrid').flexOptions({params:option});
	return true;
}

taskPlanDetailList.success = function(){
	$("#taskPlanDetailGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">修改</a></span>' : function(btn,rowId,obj){
			btn.click(function(){
				$.epsDialog({
			        title:'资金明细',
			        url:$('#initPath').val()+'/TaskPlanDetailController.do?method=toUpdateTaskPlanDetail&quantity='+$('#taskPlanDetailGrid').getRowById(rowId)["taskPlanDetail.quantity"]+'&taskPlanDetailId='+ $('#taskPlanDetailGrid').getRowById(rowId)["taskPlanDetail.objId"],
			        width: '500',
			        height: '250',
			        isReload: 'false',
			        onClose: function(){ 
			        	$("#taskPlanDetailGrid").reload();//刷新
			        }
				});	
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">删除</a></span>' : function(btn,rowId,obj){
			btn.click(function(){
				if(window.confirm('确定删除?')){
					var b = $('#taskPlanDetailGrid').getRowById(rowId)["taskPlanDetail.quantity"];
					$("#moneyDetail").val(Number($("#moneyDetail").val())-Number(b));
					$.getJSON($('#initPath').val()+'/TaskPlanMDetailController.do?method=remove',{objId:rowId},function(json){
						if(json.result)alert(json.result);if(json.failure)return;
						$("#taskPlanDetailGrid").reload();//刷新
					});
				}
			}).appendTo(obj);
		}
	});


	$("#taskPlanDetailGrid").flexGetColByName({
		'taskPlanDetail.quantity':function(id,t){
			var json = $("#taskPlanDetailGrid").flexGetRowJsonById(id);
			var quantity = json['taskPlanDetail.quantity'];
			$(t).html(parseFloat(quantity)/10000);
		}
	});

	
}
</script>
<input type="hidden" id="taskPlanDetailZh" value="<dm:out value="local__taskPlanManager__taskPlanDetail_zh">采购资金</dm:out>明细">
<flex:flexgrid checkbox="false" height="170"
	id="taskPlanDetailGrid" url="TaskPlanMDetailController.do?method=list" queryColumns="taskPlanDetail.objId" rp="10" title="#taskPlanDetailZh"  
		onSubmit="taskPlanDetailList.before" usepager="false" onSuccess="taskPlanDetailList.success">
				<flex:flexCol name="taskPlanDetail.approvalNumber" display="taskPlanDetailForm.approvalNumber" sortable="true" width="160"align="center"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.superiorApp"format="money" display="taskPlanDetailForm.superiorApp" sortable="true" width="70"align="right"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.localApp" format="money" display="taskPlanDetailForm.localApp" sortable="true" width="70"align="right"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.ownerApp" format="money" display="taskPlanDetailForm.ownerApp" sortable="true" width="70"align="right"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.otherApp" format="money" display="taskPlanDetailForm.otherApp" sortable="true" width="70"align="right"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.quantity" format="money" display="总资金(万元)" sortable="true" width="100"align="right"></flex:flexCol>
	<flex:flexBtn name="globe.new" bclass="add" onpress="taskPlanDetailList.add"></flex:flexBtn>
</flex:flexgrid>
