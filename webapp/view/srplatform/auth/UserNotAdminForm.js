//定义文件全局变量 处理方法名重复问题
var UserForm={};
UserForm.sumbitClick=function(){
	if(UserForm.vform.checkForm()){
		var josnObj=formToJsonObject($("#UserForm")[0]);
		if(josnObj.objId!=""&&josnObj.objId!="null"){
			delete josnObj.usrPassrowd;
		}			
		josnObj.json=JSON.stringify(josnObj);			
		var thisObj=this;
		$.ajax({
			url:"UserController.do?method=save",
			type:"POST",
			data:josnObj,
			success:function(msg){	
				alert("保存成功");
			}
		})
	}
	else{
		UserForm.vform.showErrors();
	}
}

$(document).ready(function(){  
	 
	UserForm.vform=$("#UserForm").validate();	

    if(PlatForm.user.objId!=""&&PlatForm.user.objId!="null"){
    	$.getJSON("UserController.do?method=createOrUpdate",{objId:PlatForm.user.objId},function(json){
    		jsonObjectToForm($("#UserForm")[0],json.list[0]);
    		json2ObjectDiv("UserForm",json.list[0]);
    	})
    } 
	//提交
	$("#submit").click(UserForm.sumbitClick)
   	
   	//查询组织机构
 	$("input[id=org.name]").click(function(e){
 			jQuery("#floaterDiv").empty();
 			jQuery("#floaterDiv").loadPage("/OrganizationTreeController.do?property=org&className=Organization");	
 			jQuery("#floaterDiv").dialog('open');return false;
 	})

})
