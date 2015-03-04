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
<div class="formLayout" id="taskPlanListInfo">
	<table class="tableList" id="SubProjectList">
	<caption style=" text-align: right">
		<a href="#"><span onclick="signUprecordInfoList.addSignUprecord();"	 title="补录投标单位"  class="sysicon siAdd">补录投标单位&nbsp;&nbsp;&nbsp;</span></a>
		<a href="#"><span onclick="signUprecordInfoList.batchAudit();"	 title="批量审核"  class="sysicon siAdd">批量审核&nbsp;&nbsp;&nbsp;</span></a>
		</caption>
  		<thead>
      		<tr class="center">
      			<td style="width: 25px;text-align: center"><input type="checkbox" id="checkAll"></td>
      			<th>投标单位名称</th>
          		<th>状态</th>
          		<th>操作</th>
     		</tr>
		</thead>
	<tbody>
	<c:forEach items="${signUprecordList}" var="signUprecord" varStatus="i">
		<tr>
			<td style="width: 25px;text-align: center"><input type="checkbox" name="orgCheck" value="${signUprecord.objId}"><input type="hidden" name="auditStatus" value="${signUprecord.auditStatus}"></td>
			<td>${signUprecord.supplier.orgName}</td>
			<td class="center">${signUprecord.auditStatusCN}</td>
			<td class="center"><a href="#"><span class="sysicon siAccept" onclick="signUprecordInfoList.viewSignUprecord('${signUprecord.objId}');" title="审核">审核</span></a></td>
			</tr>
		
	</c:forEach>
	</tbody>
    </table>
</div>
</div>

<script language="javascript">
var signUprecordInfoList = {};

$(document).ready(function(){
	$('#epsTabs').tabs();
signUprecordInfoList.batchAudit = function(){
  var selectSignUprecordId = ""; 
  var flag = true;
  if($("input[name=orgCheck]:checked").length==0){
         alert("至少选择一个报名信息！");
         return;
  }else{
	  $("input[name=orgCheck]:checked").each(function(){
		  var auditStatus = $(this).next().val();
		  if(auditStatus!='00'){
 			 $(this).attr('checked',false);
 			 flag = false;	
 			 return;
		   }else{
			   selectSignUprecordId += $(this).val()+',';
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
			//alert(obj2str(json));
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
		  }else{
			  alert("请选择待审核的投标单位报名记录！");
			  }
  }
	
 
    
	
}

signUprecordInfoList.viewSignUprecord =function(objId){
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
signUprecordInfoList.addSignUprecord = function(){
	$.epsDialog({
        title:"补录投标单位",
        url:$("#initPath").val()+"/SignUprecordController.do?method=toPageForAgency&projectId="+$("#projectId").val(),
        width: 600,
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


	
})









</script>