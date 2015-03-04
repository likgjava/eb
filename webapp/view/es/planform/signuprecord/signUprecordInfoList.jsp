<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="partContainers"id="epsTabs">
  <ul>
  <c:forEach items="${subPrjList }" var="subPrj">
  	<li>
      <a href="#taskPlanListInfo" id = "${subPrj.objId }" class="refreshData"><span>${subPrj.projName }</span></a>
    </li>
   </c:forEach>
   </ul>
<input type="hidden" id="subProjId" value="">
<div class="formLayout" id="taskPlanListInfo">
</div>
</div>
<div class="formLayout form2Pa">
 <div class="conOperation">
 	 <input type="hidden" name="projectId" value="${projectId}" id="projectId"/>
     <button id="finishSignUprecord" type="button" tabindex="19"><span>完成</span></button>
     <span><font color="red">(点击完成，方可进入下一步操作！)</font></span>
 </div>
</div>

<script language="javascript">
var signUprecordInfoList = {};
signUprecordInfoList.currentTabID = "";
$(document).ready(function(){
	$("#subProjId").val($("#projectId").val());    //第一次进入默认传入项目ID
	$('#epsTabs').tabs();


	 $("li.ui-state-disabled").each(function(){
			$(this).attr("class","ui-state-default ui-corner-top ui-state-active");
		})
	
	$("li a.refreshData").click(function(){
		signUprecordInfoList.currentTabID = $(this).attr("id");
		$("#subProjId").val($(this).attr("id"));    //点击后传入包组ID
		$("#taskPlanListInfo").loadPage($('#initPath').val()+"/SignUprecordController.do?method=findSignupRecordList&prjId="+$(this).attr("id"));
	})
    $("li a.refreshData:first").click();	
signUprecordInfoList.batchAudit = function(){
  var selectSignUprecordId = ""; 
  var selectSignUprecordName = ""; 
  var bailStatusFlag=false;
  var flag = true;
  if($("input[name=orgCheck]:checked").length==0){
         alert("至少选择一个报名信息！");
         return;
  }else{
	  $("input[name=orgCheck]:checked").each(function(){
		  var auditStatus = $(this).next().val();
		  var bailStatus = $(this).next().next().val();
		  var supplierName = $(this).next().next().next().val();
		  if(auditStatus!='00'){
 			 $(this).attr('checked',false);
 			 flag = false;	
 			 return;
		   }else{
			   selectSignUprecordId += $(this).val()+',';
			   if(bailStatus!='01')
				   selectSignUprecordName += supplierName + ' ',
				   bailStatusFlag = true;
			   }
	  })
	  if(flag){
		  var json = {};
			json['idsP']='ids';
			json['opinionP']='opinion';
			json['ids'] =selectSignUprecordId;
			json['auditStatusP'] ='auditStatus';
			json['passAction'] = 'SignUprecordController.do?method=auditSignUprecord';
			json['noPassAction'] = 'SignUprecordController.do?method=auditSignUprecord';

			if(bailStatusFlag){
				if(window.confirm(selectSignUprecordName+'未缴纳保证金，是否继续审核?')){
					$.epsDialog({
						title:'批量审核',
						url:$('#initPath').val()+'/view/srplatform/batch/audit.jsp?json='+obj2str(json), 
						width: 500,
						height: 200,
						afterLoad: function(){}, //加载完url用
						onClose: function(){
						 if($("#projectTaskId") && $("#projectTaskId").val() != ""){
				        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
					   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			            }
						} //关闭后调用
					});	
				}
			}else{
				$.epsDialog({
					title:'批量审核',
					url:$('#initPath').val()+'/view/srplatform/batch/audit.jsp?json='+obj2str(json), 
					width: 500,
					height: 200,
					afterLoad: function(){}, //加载完url用
					onClose: function(){
					 if($("#projectTaskId") && $("#projectTaskId").val() != ""){
			        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
				   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		            }
					} //关闭后调用
				});	
			}
			
		


			
		  }else{
			  alert("请选择待审核的投标单位报名记录！");
			  }
  }
	
 
    
	
}

signUprecordInfoList.viewSignUprecord =function(objId,bailStatus,supplierName){
	if(bailStatus=='01'){ //表示录入保证金
		$.epsDialog({
	        title:"报名信息",
	        url:$("#initPath").val()+"/SignUprecordController.do?method=toViewSignupPageByAgent&objId="+objId,
	        width: 700,
	        height: 450,
	        isReload: false,
	        onClose: function(){
	            if($("#projectTaskId") && $("#projectTaskId").val() != ""){
		        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
	            }
	       	}
		});
	}else if(bailStatus!='01'){//表示未录入保证金
		if(window.confirm(supplierName+'未缴纳保证金，是否继续审核?')){
			$.epsDialog({
		        title:"报名信息",
		        url:$("#initPath").val()+"/SignUprecordController.do?method=toViewSignupPageByAgent&objId="+objId,
		        width: 700,
		        height: 450,
		        isReload: false,
		        onClose: function(){
		            if($("#projectTaskId") && $("#projectTaskId").val() != ""){
			        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
				   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		            }
		       	}
			});
		}
	}
	

}

//查看供应商投标详情
signUprecordInfoList.viewSupplierBidDetail =function(packId,supplierId,supplierName){
	$.epsDialog({
        title:"供应商投标详情",
        url:$("#initPath").val()+"/BidController.do?method=toSupplierBidDetail&projectId="+$("#projectId").val()+"&packId="+packId+"&supplierId="+supplierId+"&supplierName="+native2ascii(supplierName),
        width: 700,
        height: 450
	});
}

signUprecordInfoList.addSignUprecord = function(){
	$.epsDialog({
        title:"补录投标单位",
        url:$("#initPath").val()+"/SignUprecordController.do?method=toPageForAgency&projectId="+$("#subProjId").val(),
        width: 650,
        height: 280,
        isReload: false,
        onClose: function(){
       	}
	});
}

$('#checkAll').toggle(function(){
    $("input[name=orgCheck]").each(function(){
        $(this).attr('checked',true);
    });
},function(){
    $("input[name=orgCheck]").each(function(){
        $(this).attr('checked',false);
    });
});

//提交
$("#finishSignUprecord").click(function(){
	$.getJSON($('#initPath').val()+'/SignUprecordController.do?method=finishSignupRecord',{objId:$('#projectId').val()},function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
	});
	
});
	
})









</script>