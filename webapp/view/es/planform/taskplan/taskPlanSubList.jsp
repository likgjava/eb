<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<script>
/*
 * 执行平台，采购申报书明细列表
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var taskPlanSubList={};

//新增
taskPlanSubList.add=function(name,grid){
	$.epsDialog({
        title:'申报书条目',
        url:$('#initPath').val()+'/TaskPlanSubController.do?method=toCreateTaskPlanSubFormView',
        width: '700',
        height: '450',
        maxWin:false,
        onClose: function(){ 
        	$(grid).reload();//刷新
        }
	});	
}   
//修改
taskPlanSubList.update=function(name,grid){
	if(!taskPlanSubList.validation(name,grid))return;
	
	var id = $('#taskPlanSubGrid').getRowById($(grid).getSelect())["taskPlanSub.objId"];
	//跳转到修改页面
	$.epsDialog({
        title:'申报书条目',
        url:$('#initPath').val()+'/TaskPlanSubController.do?method=toCreateOrUpdate&taskPlanSubId='+id,
        width: '700',
        height: '450',
        maxWin: false,
        onClose: function(){ 
        	$(grid).reload();//刷新
        }
	});	
}   

//删除
taskPlanSubList.remove=function(name,grid){
	if(!taskPlanSubList.validation(name,grid))return;
	if(window.confirm('确定'+name+'?')){
		$.getJSON($('#initPath').val()+'/TaskPlanSubController.do?method=removeTaskPlanSubAndPreqBySubId',{taskPlanSubId:$('#taskPlanSubGrid').getRowById($(grid).getSelect())["taskPlanSub.objId"]},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$(grid).reload();//刷新
		});
	}
}

//列表操作验证
taskPlanSubList.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一条申报书明细');return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请只选择一条申报书明细');return false;}//是否只选中一个
	return true;
}

//查询条件过滤
taskPlanSubList.before=function(){
	var option={"taskPlan.objId":$("#objId").val()}
	$('#taskPlanSubGrid').flexOptions({params:option});
	return true;
}

taskPlanSubList.success1 = function(){
	$("#taskPlanSubGrid").flexGetColByName({
		'taskPlanSub.totalPrice':function(id,t){
			var json = $("#taskPlanSubGrid").flexGetRowJsonById(id);
			var auditDetail = json['taskPlanSub.totalPrice'];
			$(t).html(parseFloat(auditDetail)/10000);
		}
	});
	$("#taskPlanSubGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">修改</a></span>' : function(btn,rowId,obj){
			btn.click(function(){
				$.epsDialog({
			        title:'申报书条目',
			        url:$('#initPath').val()+'/TaskPlanSubController.do?method=toUpdateTaskPlanSubFormView&totalPrice='+$('#taskPlanSubGrid').getRowById(rowId)["taskPlanSub.totalPrice"]+'&taskPlanSubId='+$('#taskPlanSubGrid').getRowById(rowId)["taskPlanSub.objId"],
			        width: '700',
			        height: '450',
			        maxWin: false,
			        onClose: function(){ 
			        	$("#taskPlanSubGrid").reload();//刷新
			        }
				});	
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">删除</a></span>' : function(btn,rowId,obj){
			btn.click(function(){
				var totalPrice=$('#taskPlanSubGrid').getRowById(rowId)["taskPlanSub.totalPrice"];
				if(window.confirm('确定删除?')){
					$("#moneySub").val(Number($("#moneySub").val())-Number(totalPrice));
					$.getJSON($('#initPath').val()+'/TaskPlanSubController.do?method=removeTaskPlanSubAndPreqBySubId',{taskPlanSubId:$('#taskPlanSubGrid').getRowById(rowId)["taskPlanSub.objId"]},function(json){
						if(json.result)alert(json.result);if(json.failure)return;
						$("#taskPlanSubGrid").reload();//刷新
					});
				}
			}).appendTo(obj);
		}
	});
}

</script>
<input type="hidden" id="taskPlanZh" value="<dm:out value="local__taskPlanManager__taskplan_zh">申报书</dm:out>列表">
<flex:flexgrid checkbox="false" minGridHeight="170" height="170"
	id="taskPlanSubGrid" url="TaskPlanMSubController.do?method=list" queryColumns="taskPlanSub.objId" rp="10" title="#taskPlanZh"  
		onSubmit="taskPlanSubList.before" usepager="false" onSuccess="taskPlanSubList.success1">
				<flex:flexCol name="taskPlanSub.purchaseName" display="taskPlanSubForm.purchaseName" sortable="true" width="220"align="left"></flex:flexCol>
				<flex:flexCol name="taskPlanSub.quantity" format="amount" display="taskPlanSubForm.quantity" sortable="true" width="60"align="right"></flex:flexCol>
				<flex:flexCol name="taskPlanSub.unit" display="taskPlanSubForm.unit" sortable="true" width="60"align="center"></flex:flexCol>
				<flex:flexCol name="taskPlanSub.totalPrice" format="money" display="预算总金额(万元)" sortable="true" width="120"align="right"></flex:flexCol>
	<flex:flexBtn name="globe.new" bclass="add" onpress="taskPlanSubList.add"></flex:flexBtn>
</flex:flexgrid>

