<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
<!--
a.abtn {text-decoration:underline}
-->
</style>
<script>
var taskPlanSubListForSum={};

//查询条件过滤
taskPlanSubListForSum.before=function(){
	var option={"taskPlan.objId":$("#objId").val(),
			"status":"01"}
	$('#taskPlanSubForSumGrid').flexOptions({params:option});
	return true;
}
//汇总
taskPlanSubListForSum.sum=function(name,grid){
	$.epsDialog({
      title:'下级申报书条目和资金明细',
      url:$('#initPath').val()+'/view/es/planform/taskplan/taskPlanSubAndDetail.jsp',
      width: '800',
      height: '300',
      onClose: function(){ 
      	$(grid).reload();//刷新
      	//刷新资金明细
			if($("#taskPlanDetailForSumGrid"))
				$("#taskPlanDetailForSumGrid").reload();
      }
	});
}

//批量退回
taskPlanSubListForSum.back=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择记录');return false;}//是否选中
	taskPlanSubListForSum.removeOrBack(name,grid,"backSumMSubs");
}

//批量删除
taskPlanSubListForSum.remove=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择记录');return false;}//是否选中
	taskPlanSubListForSum.removeOrBack(name,grid,"removeSumMSubs");
}


taskPlanSubListForSum.removeOrBack=function(name,grid,method){
	if(window.confirm('确定'+name+'?')){

		//获得选中条目的id
		var ids = $(grid).getSelects().split(",");
		var planSubIds = new Array();
		for(var i=0; i< ids.length; i++){
			planSubIds.push($("#taskPlanSubForSumGrid").getRowById(ids[i])["taskPlanSub.objId"]);
		}
		//删除，但不级联删除条目信息，同步删除对应的资金中间表数据
		$.getJSON($('#initPath').val()+'/TaskPlanMSubController.do?method='+method,{taskPlanSubIds:planSubIds.toString()},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$(grid).reload();//刷新
			//刷新资金明细
			if($("#taskPlanDetailForSumGrid"))
				$("#taskPlanDetailForSumGrid").reload();
		});
	}
}

taskPlanSubListForSum.success1 = function(){
	$("#taskPlanSubForSumGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				if(window.confirm('确定删除?')){
					//删除，但不级联删除条目信息，同步删除对应的资金中间表数据
					$.getJSON($('#initPath').val()+'/TaskPlanMSubController.do?method=removeSumMSubs',{taskPlanSubIds:$("#taskPlanSubForSumGrid").getRowById(rowId)["taskPlanSub.objId"]},function(json){
						if(json.result)alert(json.result);if(json.failure)return;
							$("#taskPlanDetailForSumGrid").reload();
						$("#taskPlanSubForSumGrid").reload();
					});
				}
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">退回</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				if(window.confirm('确定退回?')){
					//删除，但不级联删除条目信息，同步删除对应的资金中间表数据
					$.getJSON($('#initPath').val()+'/TaskPlanMSubController.do?method=backSumMSubs',{taskPlanSubIds:$("#taskPlanSubForSumGrid").getRowById(rowId)["taskPlanSub.objId"]},function(json){
						if(json.result)alert(json.result);if(json.failure)return;
							$("#taskPlanDetailForSumGrid").reload();
						$("#taskPlanSubForSumGrid").reload();
					});
				}
			}).appendTo(obj);
		}
	});
}

</script>
<flex:flexgrid checkbox="true"
	id="taskPlanSubForSumGrid" url="TaskPlanMSubController.do?method=list" queryColumns="taskPlanSub.objId" rp="10" title="申报书明细"  
		onSubmit="taskPlanSubListForSum.before" usepager="false" onSuccess="taskPlanSubListForSum.success1">
				<flex:flexCol name="taskPlanSub.budgetName" display="taskPlanForm.budget" sortable="true" width="120"align="left"></flex:flexCol>
				<flex:flexCol name="taskPlanSub.purchaseName" display="taskPlanSubForm.purchaseName" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="taskPlanSub.unit" display="taskPlanSubForm.unit" sortable="true" width="50"align="center"></flex:flexCol>
				<flex:flexCol name="taskPlanSub.quantity" format="amount" display="taskPlanSubForm.quantity" sortable="true" width="50"align="right"></flex:flexCol>
				<flex:flexCol name="taskPlanSub.totalPrice" format="money" display="taskPlanSubForm.totalPrice" sortable="true" width="80"align="right"></flex:flexCol>
	<flex:flexBtn name="汇总" bclass="import" onpress="taskPlanSubListForSum.sum"></flex:flexBtn>
	<flex:flexBtn name="批量删除" bclass="delete" onpress="taskPlanSubListForSum.remove"></flex:flexBtn>
	<flex:flexBtn name="批量退回" bclass="tableGo" onpress="taskPlanSubListForSum.back"></flex:flexBtn>
</flex:flexgrid>
