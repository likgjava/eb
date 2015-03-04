/*
 * 标准平台，项目计划表单
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var projectPlanForm={};

$(document).ready(function(){
	$('#projectPlanForm').validate();
	
	//计划时间
    $("#planStartDate").epsDatepicker();
    $("#planEndDate").epsDatepicker();
    
    //前置节点
	$('input[id=prePlan.name]').click(function(e){
	    $.epsDialog({
	        title:'请选择前置节点',
	        id:'choosePreTask',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=prePlan&className=ProjectPlan&action=listTop&isOpen=1&dialogId=choosePreTask&params='+escape("&project.objId="+$("input[name=project.objId]").val()),
	        width: '500',
	        height: '400'
	    }); 
	});	
    //back节点
	$('input[id=backPlan.name]').click(function(e){
	    $.epsDialog({
	        title:'请选择退回节点',
	        id:'chooseBackTask',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=backPlan&className=ProjectPlan&action=listTop&isOpen=1&dialogId=chooseBackTask&params='+escape("&project.objId="+$("input[name=project.objId]").val()),
	        width: '500',
	        height: '400'
	    }); 
	});	
 	
 	//选择角色
 	$('input[id=role.chName]').click(function(e){
		// 根据资源ID获取角色Id
 		var resourceId = $("#resourceId").val();
 		if("" == resourceId){
 			alert("请先选择资源！")
 			return false;
 		}else{
 			$.getJSON($('#initPath').val()+'/ProjectPlanController.do?method=getRoleIdsByResourceId',{"resourceId":resourceId}, function(json){
 			    $.epsDialog({
 			        title:'请选择角色',
 			        id:'chooseRole',
 			        url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=role.objId&NAMES=role.chName&className=Role&action=listTop&isOpen=1&dialogId=chooseRole&params='+escape("objId="+json.roleIds+"&objId_op=in"),
 			        width: '500',
 			        height: '400'
 			    }); 
 			})
 		}
 	});	
 	
 	//选择执行人
 	$('input[id=employee.name]').click(function(e){
 		// 根据角色ID获取员工ID
 		var roleId = $("[id=role.objId]").val();
 		if("" == roleId){
 			alert("请先选择角色！");
 			return false;
 		}else{
 			$.getJSON($('#initPath').val()+'/ProjectPlanController.do?method=getEmployeeIdsByRoleId',{"roleId":roleId}, function(json){
 			    $.epsDialog({
 			        title:'请选择操作人',
 			        id:'chooseEmployee',
 			        url:$('#initPath').val()+'/view/srplatform/empSelect/selectList.jsp?property=employee&dialogId=chooseEmployee&params='+escape("objId="+json.employeeIds+"&objId_op=in"),
 			        width: '700',
 			        height: '450'
 			    }); 
 			})
 		}
 	});	
	
	//提交
	$('#projectPlanSave').click(function(){
		if(!$('#projectPlanForm').valid()){alert('请正确填写表单!');return;}
		var useStatus = $("[id=useStatus][type=radio][checked=true]").val();
		if(undefined == useStatus && null == useStatus && '' == useStatus){
			alert($("#ruleLi label").html().toString().replace("：","")+" 为必填项！");
			return false;
		}else{
			$.getJSON($('#initPath').val()+'/ProjectPlanController.do?method=saveOrUpdate', formToJsonObject('projectPlanForm'), function(json){
				if(json.failure){alert(json.result);return;}
				$("#epsDialogCloseReload").click();
				try {
					$("#menuTree").empty();
					projectagent.loadMenuTree();
				} catch (e) {
					return false;
				}
			});
		}
	});
	
	//关闭
	$('#projectPlanClose').click(function(){
		$('#epsDialogCloseNoReload').click();
	});

	var rule = $("#rule").val();
	if(undefined == rule || null == rule || "" == rule){
		$("#ruleLi").remove();
	}
});
