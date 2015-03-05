//定义文件全局变量 处理方法名重复问题
var OrgDetailForm={};

$(document).ready(function(){	 
	OrgDetailForm.vform=$("#OrgDetailForm").validate();	
	
	//获得代理类型
	var agentList=$.ajax({ url: "/DictionaryController.do?method=getBaseObjectListByProperty",data:{"dicType":"dllx"}, async: false }).responseText
	var agentJosn =JSON.parse(agentList);
	$.each(agentJosn,function(i,n){
			$("#agentType").append("<option value='"+n.objId+"'>"+n.dicName+"</option>")
	})
	
	//获得开户银行
	var openBankList=$.ajax({ url: "/DictionaryController.do?method=getBaseObjectListByProperty",data:{"dicType":"khyh"}, async: false }).responseText
	var openBankJosn =JSON.parse(openBankList);
	$.each(openBankJosn,function(i,n){
			$("#openBank").append("<option value='"+n.objId+"'>"+n.dicName+"</option>")
	})
	
	
 	$("input[id=orgCodeFile.name]").click(function(e){	
 		jQuery("#floaterDiv").empty();
		jQuery("#floaterDiv").loadPage("/AttachmentController.do?property=orgCodeFile");	
		jQuery("#floaterDiv").dialog('open');return false;
 	})	
 	$("input[id=regFile.name]").click(function(e){
 		jQuery("#floaterDiv").empty();
		jQuery("#floaterDiv").loadPage("/AttachmentController.do?property=regFile");	
		jQuery("#floaterDiv").dialog('open');return false;
 	})	
 
	//返回
	$("*[name=return]").click(function(){	
		$("#operatingArea").loadPage("OrganizationController.do?method=toCreateOrUpdate&objId="+$("input[id=orgnization.objId]").val());
	})
	//重置
	$("*[name=reset]").click(function(){	 
		$("#OrgDetailForm")[0].reset();
	})
	//提交
	$("*[name=submit]").click(function(){	 
		if(OrgDetailForm.vform.checkForm()){	 
			var josnObj=formToJsonObject($("#OrgDetailForm")[0]);   
			if(josnObj.objId==""||$("#objId").val()=="null"){
				delete josnObj.objId;
			}
			josnObj.json=JSON.stringify(josnObj);
			$.ajax({
				url:"OrgDetailController.do?method=save",
				type:"POST",
				data:josnObj,
				success:function(msg){
					alert("保存组织机构详细信息成功")
					$('#operatingArea').loadPage("/OrganizationController.do?method=toCreateOrUpdate");
				}
			})
		}else{	  
			OrgDetailForm.vform.showErrors();
		}
	  
	})

})
